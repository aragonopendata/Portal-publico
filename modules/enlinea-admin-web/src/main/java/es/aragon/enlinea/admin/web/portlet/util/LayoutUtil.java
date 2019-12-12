package es.aragon.enlinea.admin.web.portlet.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sites.kernel.util.SitesUtil;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;

/**
 * @author Asier Guillo
 */
public class LayoutUtil {
	
	private static Log log = LogFactoryUtil.getLog(LayoutUtil.class);
	
	private static final String DEFAULT_LOCALE = "es_ES";
	private static final String TOPIC_CATEGORY_ID = "topic-category-id";
	
	private AssetCategoryLocalService assetCategoryLocalService;
	private CustomCategoryPropertyLocalService customCategoryPropertyLocalService;
	private LayoutLocalService layoutLocalService;
	private LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService;
	
	public LayoutUtil(AssetCategoryLocalService assetCategoryLocalService, CustomCategoryPropertyLocalService customCategoryPropertyLocalService,
			LayoutLocalService layoutLocalService, LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService) {
		this.assetCategoryLocalService = assetCategoryLocalService;
		this.customCategoryPropertyLocalService = customCategoryPropertyLocalService;
		this.layoutLocalService = layoutLocalService;
		this.layoutPageTemplateEntryLocalService = layoutPageTemplateEntryLocalService;
	}
	
	public void createLayouts(ActionRequest actionRequest, PortletPreferences preferences, AssetVocabulary assetVocabulary, ThemeDisplay themeDisplay, long level1PageTemplate, long level23PageTemplate) {
		log.info("Started enlinea layouts creation process");
		if(themeDisplay.getScopeGroup().getExpandoBridge().hasAttribute("procedure-url")) {
			String rootFriendlyURL = GetterUtil.getString(themeDisplay.getScopeGroup().getExpandoBridge().getAttribute("procedure-url", false), StringPool.BLANK);
			if(!rootFriendlyURL.isEmpty()) {
				Layout rootLayout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, rootFriendlyURL);
				if(Validator.isNotNull(rootLayout)) {
					deleteChildLayouts(rootLayout, assetVocabulary.getVocabularyId());
					List<AssetCategory> level1Categories = assetCategoryLocalService.getVocabularyRootCategories(assetVocabulary.getVocabularyId(), -1, -1, null);
					if(!level1Categories.isEmpty()) {
						for(AssetCategory level1Category : level1Categories) {
							String friendlyURL = rootFriendlyURL + createLayoutFriendlyURL(level1Category);
							String layoutName = level1Category.getTitle(LocaleUtil.fromLanguageId(DEFAULT_LOCALE));
							Layout layout = createLayout(themeDisplay, friendlyURL, layoutName, level1PageTemplate, rootLayout.getLayoutId());
							if(Validator.isNotNull(layout)) {
								try {
									if(!layout.getExpandoBridge().hasAttribute(TOPIC_CATEGORY_ID)) {
										layout.getExpandoBridge().addAttribute(TOPIC_CATEGORY_ID, false);
									}
									layout.getExpandoBridge().setAttribute(TOPIC_CATEGORY_ID, String.valueOf(level1Category.getCategoryId()), false);
								} catch (PortalException e) {
									log.error("Error adding categoryId to layout " + layoutName, e);
								}
								List<AssetCategory> childCategories = assetCategoryLocalService.getChildCategories(level1Category.getCategoryId(), -1, -1, null);
								if(Validator.isNotNull(childCategories)) {
									for(AssetCategory childCategory : childCategories) {
										createLevel23Layouts(layout, childCategory, themeDisplay, rootFriendlyURL, level23PageTemplate);
									}
								}
							}
						}
						try {
							SimpleDateFormat df = new SimpleDateFormat("HH:mm, dd MMMM yyyy", LocaleUtil.fromLanguageId(DEFAULT_LOCALE));
							if(assetVocabulary.getName().equals("Temas")) {
								preferences.setValue("lastTopicPageGeneration", df.format(new Date()));
							} else if(assetVocabulary.getName().equals("Perfiles")) {
								preferences.setValue("lastProfilePageGeneration", df.format(new Date()));
							}
							preferences.store();
						} catch(Exception e) {
							log.error("Error updating the page generation date", e);
						}
						try {
							if(assetVocabulary.getName().equals("Temas")) {
								preferences.setValue("level1TopicPageTemplate", String.valueOf(level1PageTemplate));
								preferences.setValue("level23TopicPageTemplate", String.valueOf(level23PageTemplate));
							} else if(assetVocabulary.getName().equals("Perfiles")) {
								preferences.setValue("level1ProfilePageTemplate", String.valueOf(level1PageTemplate));
								preferences.setValue("level23ProfilePageTemplate", String.valueOf(level23PageTemplate));
							}
							preferences.store();
						} catch(Exception e) {
							log.error("Error saving the page template selections", e);
						}
						log.info("Finished enlinea layouts creation process");
					} else {
						SessionErrors.add(actionRequest, "action.error.no-categories");
					}
				} else {
					SessionErrors.add(actionRequest, "action.error.defined-procedure-url-not-found");
				}
			} else {
				SessionErrors.add(actionRequest, "action.error.no-procedure-url-defined");
			}
		} else {
			SessionErrors.add(actionRequest, "action.error.no-procedure-url-defined");
		}
	}
	
	private void deleteChildLayouts(Layout parentLayout, long vocabularyId) {
		List<Layout> childrenLayouts = parentLayout.getAllChildren();
		for(Layout childrenLayout : childrenLayouts) {
			try {
				Layout layout = layoutLocalService.fetchLayout(childrenLayout.getPlid());
				if(Validator.isNotNull(layout)) {
					if(layout.getExpandoBridge().hasAttribute(TOPIC_CATEGORY_ID) &&
							!layout.getExpandoBridge().getAttribute(TOPIC_CATEGORY_ID).equals(StringPool.BLANK)) {
						long categoryId = GetterUtil.getLong(layout.getExpandoBridge().getAttribute(TOPIC_CATEGORY_ID), -1);
						if(categoryId != -1) {
							AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(categoryId);
							if((Validator.isNotNull(assetCategory) && assetCategory.getVocabularyId() == vocabularyId)
									|| Validator.isNull(assetCategory)) {
								layoutLocalService.deleteLayout(layout);
							}
						}
					}
				}
			} catch (PortalException e) {
				log.error("Error deleting layout " + childrenLayout.getName(), e);
			}
		}
	}
	
	private Layout createLayout(ThemeDisplay themeDisplay, String friendlyURL, String layoutName, long pageTemplate, long parentLayoutId) {
		Layout layout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), Boolean.FALSE, friendlyURL);
		if(Validator.isNull(layout)) {
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());
			UnicodeProperties typeSettingsProperties = new UnicodeProperties();
			LayoutPageTemplateEntry layoutPageTemplateEntry = layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(pageTemplate);
			if(Validator.isNotNull(layoutPageTemplateEntry)) {
				LayoutPrototype layoutPrototype = LayoutPrototypeLocalServiceUtil.fetchLayoutPrototype(layoutPageTemplateEntry.getLayoutPrototypeId());
				if(Validator.isNotNull(layoutPrototype)) {
					typeSettingsProperties.setProperty("layoutPageTemplateEntryId", String.valueOf(layoutPageTemplateEntry));
					serviceContext.setAttribute("layoutPrototypeUuid", layoutPrototype.getUuid());
				}
			}
			int maxLength = ModelHintsUtil.getMaxLength(Layout.class.getName(), "friendlyURL");
			if(layoutName.length() > maxLength) {
				layoutName = layoutName.substring(0, maxLength);
			}
			Map<Locale, String> nameMap = new HashMap<>();
			nameMap.put(LocaleUtil.fromLanguageId(DEFAULT_LOCALE), layoutName);
			Map<Locale, String> friendlyUrlMap = new HashMap<>();
			friendlyUrlMap.put(LocaleUtil.fromLanguageId(DEFAULT_LOCALE), friendlyURL);
			try {
				layout = layoutLocalService.addLayout(
						themeDisplay.getUserId(), 
						themeDisplay.getScopeGroupId(), 
						Boolean.FALSE, 
						parentLayoutId, 
						nameMap, 
						nameMap, 
						new HashMap<>(), 
						new HashMap<>(), 
						new HashMap<>(), 
						LayoutConstants.TYPE_PORTLET, 
						typeSettingsProperties.toString(), 
						Boolean.FALSE, 
						friendlyUrlMap, 
						serviceContext);
				if(Validator.isNotNull(layout)) {
					SitesUtil.mergeLayoutPrototypeLayout(layout.getGroup(), layout);
					layout = layoutLocalService.updateLayout(layout);
				}
			} catch(PortalException e) {
				log.error("Error while creating layout" + layoutName, e);
			} catch (Exception e) {
				log.error("Error applying layout template to " + layoutName, e);
			}
		} else {
			log.error("Layout with url " + friendlyURL + " is already created");
		}
		return layout;
	}
	
	private void createLevel23Layouts(Layout parentLayout, AssetCategory assetCategory, ThemeDisplay themeDisplay, String rootFriendlyURL, long pageTemplate) {
		if(Validator.isNotNull(assetCategory)) {
			String friendlyURL = rootFriendlyURL + createLayoutFriendlyURL(assetCategory);
			String layoutName = assetCategory.getTitle(LocaleUtil.fromLanguageId(DEFAULT_LOCALE));
			Layout createdLayout = createLayout(themeDisplay, friendlyURL, layoutName, pageTemplate, parentLayout.getLayoutId());
			if(Validator.isNotNull(createdLayout)) {
				try {
					if(!createdLayout.getExpandoBridge().hasAttribute(TOPIC_CATEGORY_ID)) {
						createdLayout.getExpandoBridge().addAttribute(TOPIC_CATEGORY_ID, false);
					}
					createdLayout.getExpandoBridge().setAttribute(TOPIC_CATEGORY_ID, String.valueOf(assetCategory.getCategoryId()), false);
				} catch (PortalException e) {
					log.error("Error adding categoryId to layout " + layoutName, e);
				}
				List<AssetCategory> childCategories = assetCategoryLocalService.getChildCategories(assetCategory.getCategoryId(), -1, -1, null);
				if(Validator.isNotNull(childCategories)) {
					for(AssetCategory childCategory : childCategories) {
						createLevel23Layouts(createdLayout, childCategory, themeDisplay, rootFriendlyURL, pageTemplate);
					}
				}
			}
		}
	}
	
	private String createLayoutFriendlyURL(AssetCategory assetCategory) {
		StringBuilder friendlyURL = new StringBuilder(StringPool.BLANK);
		try {
			List<AssetCategory> ancestorCategories = assetCategory.getAncestors();
			Collections.reverse(ancestorCategories);
			if(Validator.isNotNull(ancestorCategories)) {
				for (AssetCategory ancestorCategory : ancestorCategories) {
					String ancestorCategoryURL = ancestorCategory.getTitle(LocaleUtil.getDefault());
					CustomCategoryProperty aliasProperty = customCategoryPropertyLocalService.fetchByCategoryIdAndKey(ancestorCategory.getCategoryId(), "ALIAS");
					if (aliasProperty != null && aliasProperty.getText() != null && !aliasProperty.getText().isEmpty()) {
						ancestorCategoryURL = aliasProperty.getText();
					}
					friendlyURL.append(StringPool.SLASH + ancestorCategoryURL);
				}
			}
			String currentCategoryURL = assetCategory.getTitle(LocaleUtil.getDefault());
			CustomCategoryProperty aliasProperty = customCategoryPropertyLocalService.fetchByCategoryIdAndKey(assetCategory.getCategoryId(), "ALIAS");
			if (aliasProperty != null && aliasProperty.getText() != null && !aliasProperty.getText().isEmpty()) {
				currentCategoryURL = aliasProperty.getText();
			}
			friendlyURL.append(StringPool.SLASH + currentCategoryURL);
		} catch (Exception e) {
			log.error("There was an error generating the categoryFriendlyURL", e);
		}
		return FriendlyURLNormalizerUtil.normalize(friendlyURL.toString());
	}

}
