package es.aragon.base.categories_importer.portlet;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;
import es.aragon.base.categories_importer.constants.AragonCategoriesImporterPortletKeys;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;
import es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalService;
import es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalServiceUtil;
import es.aragon.base.categories_importer.util.assetcategory.CategoriesImporterUtil;
import es.aragon.base.categories_importer.util.assetcategory.ExpandoUtil;
import es.aragon.base.categories_importer.util.assetcategory.LegislaturesUtil;
import es.aragon.base.categories_importer.util.layout.LayoutsUtil;
import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.admin.web.portlet.util.LayoutUtil;

/**
 * @author pfalcon
 * Aragon categories importer portlet class
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AragonCategoriesImporterPortletKeys.ARAGON_CATEGORIES_IMPORTER_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonCategoriesImporterPortlet extends MVCPortlet {
	
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	@Reference
	private LayoutLocalService layoutLocalService;
	@Reference
	private LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService;
	@Reference
	private CustomCategoryPropertyLocalService customCategoryPropertyLocalService;
	@Reference
	private CompanyLocalService companyLocalService;
	@Reference
	private GroupLocalService groupLocalService;
	@Reference
	private PortletPreferencesLocalService portletPreferencesLocalService;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		renderRequest.setAttribute("importCategoriesRegistryLocalService", _importCategoryRegistryLocalService);
		super.render(renderRequest, renderResponse);
	}

	/**
	 * Load all the categories from Aragon Open data
	 * @param actionRequest Action request
	 * @param actionResponse Action response
	 */
	public void loadCategories(ActionRequest actionRequest, ActionResponse actionResponse) {
		//Get parameters
		String nameVocabularyToImport = ParamUtil.getString(actionRequest, "vocabularyToImport", null);
		Boolean allCategories = ParamUtil.getBoolean(actionRequest, "allCategories");
		String expandoAttribute = "load-categories-status-process";
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), nameVocabularyToImport);
			ExpandoBridge expandoBridge = assetVocabulary.getExpandoBridge();
			//create expando if not exist
			if (!expandoBridge.hasAttribute(expandoAttribute)) {
				try {
					expandoBridge.addAttribute(expandoAttribute,false);
				} catch (Exception e) {
					_log.error("There was an error adding the expando attribute: " + e.toString());
				}
			}
			//set permission update to ExpandoColumn
			expandoBridge.setAttributeDefault(expandoAttribute, StringPool.BLANK);
			expandoBridge.setClassName(AssetVocabulary.class.getName());
			expandoBridge.setClassPK(assetVocabulary.getVocabularyId());
			if (!allCategories) {
				expandoBridge.setAttribute(expandoAttribute, "Importando categor\u00EDas de " + assetVocabulary.getTitle(themeDisplay.getLocale()),false);
			} else {
				expandoBridge.setAttribute(expandoAttribute, "Importando todas las categor\u00EDas",false);
			}
			long latestRegisteredLegistureId = LegislaturesUtil.getLatestRegisteredLegistureId();
			//String legislaturaIdFilter = ParamUtil.getString(actionRequest, null);
			if (latestRegisteredLegistureId > 0) {
				//Process import categories
				CategoriesImporterUtil.importCategories(themeDisplay.getScopeGroupId(), (allCategories ? null : nameVocabularyToImport), String.valueOf(latestRegisteredLegistureId));
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
				String dateString = dateFormat.format(new Date());
				//Modify the expando value when the import process is finished
				if (!allCategories) {
					expandoBridge.setAttribute(expandoAttribute, "Finalizado el proceso de importaci\u00F3n de "
							+ assetVocabulary.getTitle(themeDisplay.getLocale()) + " a d\u00EDa " + dateString,false);
				} else {
					expandoBridge.setAttribute(expandoAttribute,
							"Finalizado el proceso de importaci\u00F3n de todas las categor\u00EDas a d\u00EDa "
									+ dateString,false);
				}
				String redirect = ParamUtil.getString(actionRequest, "redirect", null);
				if (Validator.isNotNull(redirect)) {
					try {
						actionResponse.sendRedirect(ParamUtil.getString(actionRequest, "redirect"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				_log.error("Legislature identifier not found");
			}
		} catch (Exception e1) {
			_log.error("Error load categories");
			e1.printStackTrace();
		}
	}

	/**
	 * Create layous for the categories of the given vocabulary
	 * @param actionRequest Action request
	 * @param actionResponse Action response
	 */
	public void createVocabularyLayouts(ActionRequest actionRequest, ActionResponse actionResponse) {
		//Get theme display
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//Get parameters
		long assetVocabularyId = ParamUtil.getLong(actionRequest, "assetVocabularyId", -1);
		AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(assetVocabularyId);
		long categoriesLayoutPageTemplateId = ParamUtil.getLong(actionRequest, "layoutPageTemplateEntryId", -1);
		String expandoAttribute = "create-layout-status-process";
		if (assetVocabularyId != -1) {
			//Create expando if not exists
			ExpandoBridge expandoBridge = assetVocabulary.getExpandoBridge();
			if (!expandoBridge.hasAttribute(expandoAttribute)) {
				try {
					expandoBridge.addAttribute(expandoAttribute,false);
				} catch (Exception e) {
					_log.error("There was an error adding the expando attribute: " + e.toString());
				}
			}
			expandoBridge.setClassName(AssetVocabulary.class.getName());
			expandoBridge.setClassPK(assetVocabulary.getVocabularyId());
			expandoBridge.setAttribute(expandoAttribute, "Generando p\u00E1ginas de "+assetVocabulary.getTitle(themeDisplay.getLocale())+ "...",false);
			LayoutsUtil.createVocabularyLayouts(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),assetVocabulary, categoriesLayoutPageTemplateId);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
			String dateString = dateFormat.format(new Date());
			expandoBridge.setAttribute(expandoAttribute, "Finalizado el proceso de generaci\u00F3n de p\u00E1ginas de "+assetVocabulary.getTitle(themeDisplay.getLocale()) +" a d\u00EDa "+ dateString,false);
			if(Validator.isNotNull(assetVocabulary) && 
					(assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES) || assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES))) {
				try {
					long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
					long groupId = groupLocalService.getFriendlyURLGroup(companyId, GroupConstants.CONTROL_PANEL_FRIENDLY_URL).getGroupId();
					long plid = layoutLocalService.getDefaultPlid(groupId);
					PortletPreferences preferences = portletPreferencesLocalService.getPreferences(companyId, 0, PortletKeys.PREFS_OWNER_TYPE_LAYOUT, plid, EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET);
					if(Validator.isNotNull(preferences)) {
						long level1PageTemplate = -1;
						long level23PageTemplate = -1;
						if(assetVocabulary.getName().equals("Temas")) {
							level1PageTemplate = GetterUtil.getLong(preferences.getValue("level1TopicPageTemplate", "-1"), -1);
							level23PageTemplate = GetterUtil.getLong(preferences.getValue("level23TopicPageTemplate", "-1"), -1);
						} else if(assetVocabulary.getName().equals("Perfiles")) {
							level1PageTemplate = GetterUtil.getLong(preferences.getValue("level1ProfilePageTemplate", "-1"), -1);
							level23PageTemplate = GetterUtil.getLong(preferences.getValue("level23ProfilePageTemplate", "-1"), -1);
						}
						if(level1PageTemplate != -1 && level23PageTemplate != -1) {
							new LayoutUtil(assetCategoryLocalService, customCategoryPropertyLocalService, layoutLocalService, layoutPageTemplateEntryLocalService)
							.createLayouts(actionRequest, preferences, assetVocabulary, themeDisplay, level1PageTemplate, level23PageTemplate);
						} else {
							_log.error("Error getting default layout templates to update enlinea layouts");
						}
					} else {
						_log.error("Error getting default layout templates to update enlinea layouts");
					}
				} catch(PortalException e) {
					_log.error("Error getting necesary information to update enlinea layouts", e);
				}
			}
		} else {
			_log.error("Vocabulary id is not valid");
		}
	}

	/**
	 * Adds the given categories to all asset entries that contains the source category id.
	 * @param actionRequest Action Request
	 * @param actionResponse Action Response
	 */
	public void relocateCategories(ActionRequest actionRequest, ActionResponse actionResponse) {
		long sourceAssetCategoryId = ParamUtil.getLong(actionRequest, "sourceAssetCategoryId", -1);
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<Long> newCategoryIds = new ArrayList<Long>();
		List<AssetVocabulary> vocabularies = _assetVocabularyLocalService.getCompanyVocabularies(themeDisplay.getCompanyId());
		for(AssetVocabulary vocabulary : vocabularies) {
			long[] newCategoryIdsAux = ParamUtil.getLongValues(actionRequest, "newCategoryIds_"+vocabulary.getVocabularyId());
			for(long categoryId : newCategoryIdsAux) {
				newCategoryIds.add(categoryId);
			}
		}
		
		String expandoAttribute = "import-category-status-process";
		//Create expando if not exists
		AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(sourceAssetCategoryId);
		ExpandoBridge expandoBridge = assetCategory.getExpandoBridge();
		if (!expandoBridge.hasAttribute(expandoAttribute)) {
			try {
				expandoBridge.addAttribute(expandoAttribute, false);
			} catch (Exception e) {
				_log.error("There was an error adding the expando attribute: " + e.toString());
			}
		}
		expandoBridge.setClassName(AssetCategory.class.getName());
		expandoBridge.setClassPK(assetCategory.getCategoryId());
		expandoBridge.setAttribute(expandoAttribute, "status-Reasignando...", false);
		expandoBridge.setAttributeDefault(expandoAttribute, "Sin procesar");
		if (sourceAssetCategoryId != -1) {
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setAnyCategoryIds(new long[] {sourceAssetCategoryId});
			assetEntryQuery.setStart(QueryUtil.ALL_POS);
			assetEntryQuery.setEnd(QueryUtil.ALL_POS);
			assetEntryQuery.setAndOperator(true);
			List<AssetEntry> assetEntryList = _assetEntryLocalService.getEntries(assetEntryQuery);
			if (assetEntryList != null && !assetEntryList.isEmpty()) {
				for (AssetEntry assetEntry : assetEntryList) {
					String addedCategories = StringPool.BLANK;
					List<Long> finalCategoryIdList = new ArrayList<Long>();
					long[] categoryIds = assetEntry.getCategoryIds();
					//Add previous categories
					for (long categoryId : categoryIds) {
						if(categoryId!=sourceAssetCategoryId) {
							finalCategoryIdList.add(categoryId);
						}
					}
					//Add new categories
					for (long categoryId : newCategoryIds) {
						if (!finalCategoryIdList.contains(categoryId) && categoryId!=sourceAssetCategoryId) {
							finalCategoryIdList.add(categoryId);
							if (!addedCategories.isEmpty()) {
								addedCategories = addedCategories + StringPool.COMMA;
							}
							addedCategories = addedCategories + categoryId;
						}
					}
					//Create final category array
					long[] finalCategoryIdArray = new long[finalCategoryIdList.size()];
					for (int i = 0; i < finalCategoryIdArray.length; i++) {
						finalCategoryIdArray[i] = finalCategoryIdList.get(i);
					}
					//Update asset
					try {
						_log.info("Added categories " + addedCategories + " to the " + assetEntry.getClassName() + " (entryId: " + assetEntry.getEntryId() + ", title: " + assetEntry.getTitle(LocaleUtil.getDefault()) + ") ");
						_assetEntryLocalService.updateEntry(assetEntry.getUserId(), assetEntry.getGroupId(), assetEntry.getClassName(), assetEntry.getClassPK(), finalCategoryIdArray, assetEntry.getTagNames());
					} catch (PortalException e) {
						_log.error("There was an error updating the asset: " + e.toString());
						e.printStackTrace();
					}
				}
			}
		}
		for (long categoryId : newCategoryIds) {
			AssetCategory newAssetCategory = _assetCategoryLocalService.fetchAssetCategory(categoryId);
			if(newAssetCategory!=null && assetCategory.getCategoryId()!=newAssetCategory.getCategoryId()) {
				if(assetCategory.getVocabularyId() == newAssetCategory.getVocabularyId()) {
					String data = assetCategory.getCategoryId() + "," + categoryId;
					ExpandoUtil.addCategoryMapValue(themeDisplay.getCompanyId(), newAssetCategory.getVocabularyId(), data);
					break;
				}
			}
		}
		expandoBridge.setAttribute(expandoAttribute, "status-Reasignacion Finalizada",false);
		String redirect = ParamUtil.getString(actionRequest, "redirect", StringPool.BLANK);
		if (Validator.isNotNull(redirect)) {
			try {
				actionResponse.sendRedirect(redirect);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Deletes all historic registries of a specific vocabulary. If a vocabulary name is not specified, all historic registries will be removed.
	 * @param actionRequest Action Request
	 * @param actionResponse Action Response
	 */
	public void resetHistoricalCategories(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String menuId = ParamUtil.getString(actionRequest, "menuId", null);
		try {
			//Delete vocabulary categories historic registries 
			if (Validator.isNotNull(menuId)) {
				_log.info("Deleting " + menuId + " categories registries");
				AssetVocabulary assetVocabulary = _assetVocabularyLocalService.getGroupVocabulary(themeDisplay.getScopeGroupId(), menuId);
				if (Validator.isNotNull(assetVocabulary)) {
					CategoriesImporterUtil.cleanImportCategoryRegistry(assetVocabulary.getVocabularyId());
				}
				_log.info("Finish deleting " + menuId + " categories registries");
			} 
			//Delete all categories historic registries 
			else {
				_log.info("Deleting categories registries");
				List <ImportCategoryRegistry> listImportCategoryRegistry = _importCategoryRegistryLocalService.getImportCategoryRegistries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				if (Validator.isNotNull(listImportCategoryRegistry)) {
					for (ImportCategoryRegistry registry : listImportCategoryRegistry ) {
						_log.info("Deleting category registry " + registry.getCategoryId());
						_importCategoryRegistryLocalService.deleteImportCategoryRegistry(registry);
					}
				}
				_log.info("Finish deleting categories registries");
			}
		} catch (Exception e) {
			_log.error("There was an error deleting the categories import historic: " + e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes all assignments to assets of the category associated with an import record
	 * @param actionRequest Action Request
	 * @param actionResponse Action Response
	 */
	public void deleteCategory(ActionRequest actionRequest, ActionResponse actionResponse) {
		//Gets the import category registry
		long importCategoryRegistryId = ParamUtil.getLong(actionRequest, "importCategoryRegistryId", 0);
		ImportCategoryRegistry importCategoryRegistry = ImportCategoryRegistryLocalServiceUtil.fetchImportCategoryRegistry(importCategoryRegistryId);
		long categoryId = importCategoryRegistry.getCategoryId();
		String expandoAttribute = "import-category-status-process";
		//Create expando if not exists
		AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
		ExpandoBridge expandoBridge = assetCategory.getExpandoBridge();
		if (!expandoBridge.hasAttribute(expandoAttribute)) {
			try {
				expandoBridge.addAttribute(expandoAttribute, false);
			} catch (Exception e) {
				_log.error("There was an error adding the expando attribute: " + e.toString());
			}
		}
		expandoBridge.setClassName(AssetCategory.class.getName());
		expandoBridge.setClassPK(assetCategory.getCategoryId());
		expandoBridge.setAttribute(expandoAttribute, "status-Eliminando...", false);
		expandoBridge.setAttributeDefault(expandoAttribute, "Sin procesar");
		//Gets all relationships between the found category and the assigned assets
		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRelList = AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(categoryId);
		for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : assetEntryAssetCategoryRelList) {
			long assetEntryId = assetEntryAssetCategoryRel.getAssetEntryId();
			//Finds the assetEntry and delete the associated category
			AssetEntry assetEntry = _assetEntryLocalService.fetchAssetEntry(assetEntryId);
			if (assetEntry != null && JournalArticle.class.getName().equalsIgnoreCase(assetEntry.getClassName())) {
				//Deletes the Journal category relationship and updates de entry
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestIndexableArticle(assetEntry.getClassPK());
				AssetEntryAssetCategoryRelLocalServiceUtil.deleteAssetEntryAssetCategoryRel(assetEntryAssetCategoryRel);
				if(journalArticle!=null) {
					JournalArticleLocalServiceUtil.updateJournalArticle(journalArticle);
				}
			} else if (assetEntry != null && DLFileEntry.class.getName().equalsIgnoreCase(assetEntry.getClassName())) {
				//Deletes the File category relationship and updates de entry
				DLFileEntry dlFileEntry = _dlFileEntryLocalService.fetchDLFileEntry(assetEntry.getClassPK());
				_assetEntryAssetCategoryRelLocalService.deleteAssetEntryAssetCategoryRel(assetEntryAssetCategoryRel);
				if(dlFileEntry!=null) {
					_dlFileEntryLocalService.updateDLFileEntry(dlFileEntry);
				}
			} else {
				//Deletes the asset category relationship
				AssetEntryAssetCategoryRelLocalServiceUtil.deleteAssetEntryAssetCategoryRel(assetEntryAssetCategoryRel);
			}
		}
		expandoBridge.setAttribute(expandoAttribute, "status-Eliminada", false);
	}
	/**
	 * Permission update Expando column
	 * @param actionRequest
	 * @param expandoAttribute
	 */
	private void setPermissionExpandoColumn(ActionRequest actionRequest, String expandoAttribute, String className) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			ExpandoTable table = ExpandoTableLocalServiceUtil.getDefaultTable(themeDisplay.getCompanyId(), className);
			ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), expandoAttribute);
			Role guestUserRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.GUEST);
			if (guestUserRole != null) {
				// define actions
				String[] actionIds = new String[] { ActionKeys.UPDATE };
				// define permission
				ResourcePermissionLocalServiceUtil.setResourcePermissions(themeDisplay.getCompanyId(),
						ExpandoColumn.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
						String.valueOf(column.getColumnId()), guestUserRole.getRoleId(), actionIds);
			}
		} catch (Exception e) {
			_log.error(e.toString());
		}
	}
	
	/**
	 * Deletes all deprecated categories and all its assignments
	 * @param actionRequest Action Request
	 * @param actionResponse Action Response
	 */
	public void deleteUnusedOldCategories(ActionRequest actionRequest, ActionResponse actionResponse) {
		//Gets all deprecated registries
		long vocabularyId = ParamUtil.getLong(actionRequest, "vocabularyId", 0);
		List<ImportCategoryRegistry> importerCategoriesRegistryList = _importCategoryRegistryLocalService.findByVocabularyIdType(vocabularyId, "deprecated", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for (ImportCategoryRegistry importCategoryRegistry : importerCategoriesRegistryList) {
			long categoryId = importCategoryRegistry.getCategoryId();
			AssetCategory assetCategory = _assetCategoryLocalService.fetchAssetCategory(categoryId);
			if (assetCategory != null) {
				List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRelList = _assetEntryAssetCategoryRelLocalService.getAssetEntryAssetCategoryRelsByAssetCategoryId(categoryId);
				if (assetEntryAssetCategoryRelList.size() == 0) {
					//Delete value expado
					ExpandoValueLocalServiceUtil.deleteValues(AssetCategory.class.getName(), assetCategory.getCategoryId());
					_assetCategoryLocalService.deleteAssetCategory(assetCategory);
					_importCategoryRegistryLocalService.deleteImportCategoryRegistry(importCategoryRegistry);
				}
			}
		}
	}
	

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		//Get theme display
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//Get parameters
		long assetVocabularyId = ParamUtil.getLong(resourceRequest, "assetVocabularyId", -1);
		if (assetVocabularyId != -1) {
			try {
				String oldURLMapStr = ExpandoUtil.getOldUrlValue(themeDisplay.getCompanyId(), assetVocabularyId);
				HashMap<Long, String> oldURLMap = getStringMap(oldURLMapStr);
				String newURLMapStr = ExpandoUtil.getNewUrlValue(themeDisplay.getCompanyId(), assetVocabularyId);
				HashMap<Long, String> newURLMap = getStringMap(newURLMapStr);
				String categoryMapStr = ExpandoUtil.getCategoryMapValue(themeDisplay.getCompanyId(), assetVocabularyId);
				HashMap<Long, Long> categoryMap = getLongMap(categoryMapStr);
				//Generate URL redirect files to send to AST
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baos);
				StringBuilder reglas = new StringBuilder();
				reglas.append("RewriteEngine On");
				for(long categoryId : oldURLMap.keySet()) {
					String urlToReplace = oldURLMap.get(categoryId);
					Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), Boolean.FALSE, urlToReplace);
					if (layout == null) {
						String urlToFinish = "/"+urlToReplace.split("/")[1];
						if(newURLMap.containsKey(categoryId)) {
							urlToFinish = newURLMap.get(categoryId);
						} else if (categoryMap.containsKey(categoryId)) {
							long relatedCategoryId = categoryMap.get(categoryId);
							if(newURLMap.containsKey(relatedCategoryId)) {
								urlToFinish = newURLMap.get(relatedCategoryId);
							}
						}
						if(!urlToReplace.equals(urlToFinish)) {
							reglas.append("\n");
							reglas.append("RewriteRule ^"+urlToReplace+"$ https://www.aragon.es"+urlToFinish+"? [L,R=301]");
						}
					}
				}
				AssetVocabulary assetVocabulary = _assetVocabularyLocalService.fetchAssetVocabulary(assetVocabularyId);
				zos.putNextEntry(new ZipEntry("redirecciones_"+assetVocabulary.getName()+".conf"));
				zos.write(reglas.toString().getBytes());
				zos.closeEntry();
				zos.flush();
				baos.flush();
				zos.close();
				baos.close();
				PortletResponseUtil.sendFile(resourceRequest, resourceResponse,	"ficheros.zip", baos.toByteArray(), "application/zip");
			} catch (Exception e) {
				_log.error("ERROR GENERANDO FICHERO DE REDIRECCIONES",e);
			}
		} else {
			_log.error("Vocabulary id is not valid");
		}
	}
	
	private HashMap<Long, String> getStringMap(String strMap) {
		HashMap<Long, String> result = new HashMap<Long, String>();
		try {
			String[] elements = strMap.split(ExpandoUtil.SEPARATOR);
			for(String element : elements) {
				String[] items = element.split(",");
				result.put(Long.parseLong(items[0]), items[1]);
			}
		} catch (Exception e) {
			_log.error("ERROR A LA HORA DE PARSEAR LA INFORMACION", e);
		}
		return result;
	}
	
	private HashMap<Long, Long> getLongMap(String strMap) {
		HashMap<Long, Long> result = new HashMap<Long, Long>();
		try {
			String[] elements = strMap.split(ExpandoUtil.SEPARATOR);
			for(String element : elements) {
				String[] items = element.split(",");
				result.put(Long.parseLong(items[0]), Long.parseLong(items[1]));
			}
		} catch (Exception e) {
			_log.error("ERROR A LA HORA DE PARSEAR LA INFORMACION", e);
		}
		return result;
	}
	
	/**
	 * Asset category local service
	 */
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	
	/**
	 * Asset entry asset category local service
	 */
	@Reference
	private AssetEntryAssetCategoryRelLocalService _assetEntryAssetCategoryRelLocalService;
	
	/**
	 * Asset entry local service
	 */
	@Reference
	private AssetEntryLocalService _assetEntryLocalService;
	
	/**
	 * Asset vocabulary local service
	 */
	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;
	
	/**
	 * File entry local service
	 */
	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;
	
	/**
	 * Import category registry local service
	 */
	@Reference
	private ImportCategoryRegistryLocalService _importCategoryRegistryLocalService;
	
	/**
	 * Journal article local service
	 */
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	
	/**
	 * Log of the class
	 */
	private final Log _log = LogFactoryUtil.getLog(AragonCategoriesImporterPortlet.class);

}