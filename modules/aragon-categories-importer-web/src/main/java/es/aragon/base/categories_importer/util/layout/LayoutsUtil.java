package es.aragon.base.categories_importer.util.layout;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sites.kernel.util.SitesUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;

import es.aragon.base.categories_importer.util.assetcategory.ExpandoUtil;

/**
 * @author pfalcon
 * Layouts creation utilities class
 */
public class LayoutsUtil {
	
	/**
	 * Create all layouts related to the categories of given vocabulary
	 * @param userId User identifier
	 * @param groupId Group identifier
	 * @param assetVocabulary Origin vocabulary
	 * @param categoriesLayoutPageTemplateId layout template identifier for the new layouts
	 */
	public static void createVocabularyLayouts(long userId, long groupId, long companyId, AssetVocabulary assetVocabulary, long categoriesLayoutPageTemplateId) {
		if (Validator.isNotNull(assetVocabulary)) {
			//Service context
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(groupId);
			//Create vocabulary layout
			Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
			String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(assetVocabulary.getTitle(siteDefaultLocale));
			Layout vocabularyLayout = createLayout(userId, groupId, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, assetVocabulary.getTitle(siteDefaultLocale), vocabularyURL, -1, serviceContext);
			if (vocabularyLayout != null) {
				//Delete previous children layouts
				List<Layout> childrenLayouts = vocabularyLayout.getAllChildren();
				for (Layout childrenLayout : childrenLayouts) {
					try {
						Layout layoutToDelete = LayoutLocalServiceUtil.fetchLayout(childrenLayout.getPlid());
						if (layoutToDelete != null) {
							_log.info("Delete layout with url " + layoutToDelete.getFriendlyURL());
							LayoutLocalServiceUtil.deleteLayout(layoutToDelete);
						}
					} catch (Exception e) {
						_log.error(e.getMessage());
					}
				}
				//First, clean new url map
				ExpandoUtil.setNewUrlValue(assetVocabulary.getCompanyId(), assetVocabulary.getVocabularyId(), "");
				//Create Categories layout
				List<AssetCategory> rootAssetCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				if (Validator.isNotNull(rootAssetCategoryList)) {
					for (AssetCategory rootCategory : rootAssetCategoryList) {
						createCategoryTreeLayouts(userId, groupId, companyId,vocabularyLayout.getLayoutId(), vocabularyURL, rootCategory, categoriesLayoutPageTemplateId, serviceContext);
					}
				}
			}
		} else {
			_log.error("El vocabulario indicado no puede ser nulo");
		}
	}
	
	/**
	 * Create all layouts related to one category and all of his children categories
	 * @param userId User identifier
	 * @param groupId Group identifier
	 * @param parentlayoutId Parent layout identifier
	 * @param rootUrl Root url to create the layouts
	 * @param assetCategory Origin category
	 * @param layoutPageTemplateId Layout template identifier for the new layouts
	 * @param serviceContext Service context
	 */
	private static void createCategoryTreeLayouts(long userId, long groupId, long companyId, long parentlayoutId, String rootUrl, AssetCategory assetCategory, long layoutPageTemplateId, ServiceContext serviceContext) {
		if (Validator.isNotNull(assetCategory)) {
			String friendlyURL = rootUrl + generateCategoryFriendlyURL(assetCategory);
			//Create layout
			Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
			Layout createdLayout = createLayout(userId, groupId, parentlayoutId, assetCategory.getTitle(siteDefaultLocale), friendlyURL, layoutPageTemplateId, serviceContext);
			//Assign expando values to the page
			ExpandoBridge expandoBridge = createdLayout.getExpandoBridge();
			String expandoAttribute = "related-category-id";
			try {
				ExpandoTable table = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, Layout.class.getName());
				ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), expandoAttribute);
				Role guestUserRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
				if (guestUserRole != null) {
					// define actions
					String[] actionIds = new String[] { ActionKeys.UPDATE };
					// define permission
					ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId,
							ExpandoColumn.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
							String.valueOf(column.getColumnId()), guestUserRole.getRoleId(), actionIds);
				}
			} catch (Exception e) {
				_log.error(e.toString());
			}
			expandoBridge.setAttribute(expandoAttribute, assetCategory.getCategoryId(), false);
			//Assign expando new url map values
			long categoryId = assetCategory.getCategoryId();
			if(categoryId>0) {
				String data = categoryId + "," + friendlyURL;
				ExpandoUtil.addNewUrlValue(assetCategory.getCompanyId(), assetCategory.getVocabularyId(), data);
			}
			//Recursive method
			parentlayoutId = createdLayout.getLayoutId();
			List<AssetCategory> childCategories = AssetCategoryLocalServiceUtil.getChildCategories(assetCategory.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			AssetVocabulary organizationsVocabulary = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(groupId,AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
			if (Validator.isNotNull(childCategories)) {
				for (AssetCategory childCategory : childCategories) {
					//Create the organization pages
					if (organizationsVocabulary != null && childCategory.getVocabularyId() == organizationsVocabulary.getVocabularyId()) {
						createCategoryTreeLayouts(userId, groupId, companyId, parentlayoutId, rootUrl, childCategory, layoutPageTemplateId, serviceContext);
					}
					//Create all the pages of the categories whose vocabulary is not organizations
					else {
						createCategoryTreeLayouts(userId, groupId,companyId, parentlayoutId, rootUrl, childCategory, layoutPageTemplateId, serviceContext);
					}
				}
			}
		}
	}
	
	/**
	 * Creates a layout with the given config
	 * @param userId User identifier
	 * @param groupId Group identifier
	 * @param parentlayoutId Parent layout identifier
	 * @param layoutTitle Layout title
	 * @param layoutFriendlyURL Layout friendly URL 
	 * @param layoutPageTemplateId Layout template identifier for the new layouts
	 * @param serviceContext Service context
	 * @return The created layout
	 */
	private static Layout createLayout(long userId, long groupId, long parentlayoutId, String layoutTitle, String layoutFriendlyURL, long layoutPageTemplateId, ServiceContext serviceContext) {
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, layoutFriendlyURL);
		if (layout == null) {
			//Layout properties
			UnicodeProperties typeSettingsProperties = new UnicodeProperties();
			typeSettingsProperties.setProperty("addToAutoMenus", "on");
			//If a layout template is defined, is necessary adding the template identifier to the layout properties 
			if (layoutPageTemplateId != -1) {
				LayoutPageTemplateEntry layoutPageTemplateEntry = LayoutPageTemplateEntryLocalServiceUtil.fetchLayoutPageTemplateEntry(layoutPageTemplateId);
				if (Validator.isNotNull(layoutPageTemplateEntry)) {
					LayoutPrototype layoutPrototype = LayoutPrototypeLocalServiceUtil.fetchLayoutPrototype(layoutPageTemplateEntry.getLayoutPrototypeId());
					if (Validator.isNotNull(layoutPrototype)) {
						typeSettingsProperties.setProperty("layoutPageTemplateEntryId", String.valueOf(layoutPageTemplateEntry));
						serviceContext.setAttribute("layoutPrototypeUuid", layoutPrototype.getUuid());
					}
				}
			}
			try {
				Locale defaultSiteLocale = LocaleUtil.fromLanguageId("es_ES");
				String language = defaultSiteLocale.getLanguage();
				String country = defaultSiteLocale.getCountry();
				String variant = defaultSiteLocale.getVariant();
				LocaleUtil.setDefault(language, country, variant);
				int maxLength = ModelHintsUtil.getMaxLength(Layout.class.getName(), "friendlyURL");
				if (layoutTitle.length() > maxLength) {
					//Truncate title to max size
					layoutTitle = layoutTitle.substring(0, maxLength);
				}
				//Layout name
				Map<Locale, String> nameMap = new HashMap<>();
				nameMap.put(defaultSiteLocale, layoutTitle);
				//Layout friendly URL
				Map<Locale, String> friendlyUrlMap = new HashMap<>();
				friendlyUrlMap.put(defaultSiteLocale, layoutFriendlyURL);
				//Create layout
				_log.info("Creating layout: " + layoutTitle + " (" + layoutFriendlyURL + ")");
				layout = LayoutLocalServiceUtil.addLayout(userId, groupId, Boolean.FALSE, parentlayoutId, nameMap, new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), LayoutConstants.TYPE_PORTLET, typeSettingsProperties.toString(), Boolean.FALSE, friendlyUrlMap, serviceContext);
				if (Validator.isNotNull(layout)) {
					//Update page with the given prototype
					SitesUtil.mergeLayoutPrototypeLayout(layout.getGroup(), layout);
					//Update page config
					//layout.setLayoutPrototypeLinkEnabled(false);
					layout = LayoutLocalServiceUtil.updateLayout(layout);
				}
			} catch (Exception e) {
				_log.error("There was an error creating the page: " + e.toString());
			}
		} else {
			_log.info("Layout with url " + layoutFriendlyURL + " is already created");
		}
		return layout;
	}
	
	/**
	 * Returns a category relative url 
	 * @param assetCategory Category
	 * @return Category relative URL
	 */
	private static String generateCategoryFriendlyURL(AssetCategory assetCategory) {
		String friendlyURL = StringPool.BLANK;
		try {
			//Append all ancestor categories to url
			List<AssetCategory> ancestorCategories = assetCategory.getAncestors();
			Collections.reverse(ancestorCategories); 
			if (Validator.isNotNull(ancestorCategories)) {
				for (AssetCategory ancestorCategorie : ancestorCategories) {
					String ancestorCategoryURLPart = ancestorCategorie.getTitle(LocaleUtil.getDefault());
					CustomCategoryProperty aliasProperty = CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(ancestorCategorie.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS);
					if (aliasProperty != null && aliasProperty.getText() != null && !aliasProperty.getText().isEmpty()) {
						ancestorCategoryURLPart = aliasProperty.getText();
					}
					friendlyURL = friendlyURL + StringPool.SLASH + ancestorCategoryURLPart;
				}
			}
			//Append current category to url
			String currentCategoryURLPart = assetCategory.getTitle(LocaleUtil.getDefault());
			CustomCategoryProperty aliasProperty = CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(assetCategory.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS);
			if (aliasProperty != null && aliasProperty.getText() != null && !aliasProperty.getText().isEmpty()) {
				currentCategoryURLPart = aliasProperty.getText();
			}
			friendlyURL = friendlyURL + StringPool.SLASH + currentCategoryURLPart;
		} catch (Exception e) {
			_log.error("There was an error generating the categoryFriendlyURL: " + e.toString());
		}
		return FriendlyURLNormalizerUtil.normalize(friendlyURL);
	}
	
	private final static Log _log = LogFactoryUtil.getLog(LayoutsUtil.class);
	
}
