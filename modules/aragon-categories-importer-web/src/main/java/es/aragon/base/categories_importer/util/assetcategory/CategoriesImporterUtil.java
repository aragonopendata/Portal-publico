package es.aragon.base.categories_importer.util.assetcategory;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.categories_importer.dto.CategoryDTO;
import es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;
import es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalServiceUtil;

/**
 * @author pfalcon
 * @author jjorge
 * @author anunez
 * @author migarcia
 * Generic categories import utilities class
 */
public class CategoriesImporterUtil {

	/**
	 * Imports all the categories from Aragon Open Data
	 * @param groupId Group identifier
	 * @param vocabularyName Vocabulary name to import. If the value of this parameter is null, all vocabularies will be imported
 	 * @param legislaturaIdFilter Legislature identifier to import
	 */
	public static void importCategories(long groupId, String vocabularyName, String legislaturaIdFilter) {
		_log.info("IMPORT CATEGORIES PROCESS STARTED");
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAddGroupPermissions(true);
		serviceContext.setScopeGroupId(groupId);
		User user = null;
		try {
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);
			if (group != null) {
				Company company = CompanyLocalServiceUtil.fetchCompany(group.getCompanyId());
				if (company != null) {
					user = company.getDefaultUser();
					if (user != null) {
						//Temas
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES))) {
							TemasUtil.importTemas(groupId, user.getUserId(), serviceContext);
						} 
						//Organismos
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES))) {
							OrganismosUtil.importOrganismos(groupId, user.getUserId(), legislaturaIdFilter, serviceContext);
						}
						//Municipios
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES))) {
							MunicipiosUtil.importMunicipios(groupId, user.getUserId(), serviceContext);
						}
						//Tipos de documento
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES))) {
							TiposDocumentoUtil.importTiposDocumento(groupId, user.getUserId(), serviceContext);
						}
						//Perfiles
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES))) {
							PerfilesUtil.importPerfiles(groupId, user.getUserId(), serviceContext);
						}
						//Tramites
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_ES))) {
							TramitesUtil.importTramites(company.getCompanyId(),groupId, user.getUserId(), serviceContext);
						}
						//Personas
						if (vocabularyName == null || (vocabularyName != null && vocabularyName.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES))) {
							PersonasUtil.importPersonas(groupId, user.getUserId(), legislaturaIdFilter, serviceContext);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error("There was an error importing the categories: " + e.toString());
		}
		_log.info("IMPORT CATEGORIES PROCESS FINISHED");
	}

	/**
	 * Get the vocabulary from given parameters. Create the vocabulary if does not exists
	 * @param userId User identifier
	 * @param groupId Group identifier
	 * @param serviceContext Sevice context
	 * @param titleMap Translated title values
	 * @param descriptionMap Translated description values
	 * @return Found or created vocabulary
	 */
	public static AssetVocabulary addOrGetVocabulary(long userId, long groupId, ServiceContext serviceContext, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap){
		AssetVocabulary assetVocabulary = null;
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String titleDefaultLocale = titleMap.get(siteDefaultLocale);
		try {
			List<AssetVocabulary> vocabularies = null;
			try {
				vocabularies = AssetVocabularyLocalServiceUtil.getGroupVocabularies(groupId);
			} catch(PortalException e){
				_log.error(e);
			}
			if (vocabularies != null) {
				for (AssetVocabulary vocabulary : vocabularies) {
					if (vocabulary.getTitle(siteDefaultLocale).equalsIgnoreCase(titleDefaultLocale)) {
						vocabulary.setTitleMap(titleMap);
						vocabulary.setDescriptionMap(descriptionMap);
						vocabulary.setName(vocabulary.getTitle(siteDefaultLocale));
						vocabulary = AssetVocabularyLocalServiceUtil.updateAssetVocabulary(vocabulary);
						_log.info("UPDATED VOCABULARY... " + vocabulary.getName());
						return vocabulary;
					}
				}
			}
			titleMap.put(LocaleUtil.getSiteDefault(), titleDefaultLocale);
			assetVocabulary = AssetVocabularyLocalServiceUtil.addVocabulary(userId, groupId, titleDefaultLocale, titleMap, descriptionMap, StringPool.BLANK, serviceContext);
			if (assetVocabulary != null) {
				_log.info("ADDED VOCABULARY... " + assetVocabulary.getName());
			}
		} catch (Exception e) {
			_log.error("There was an error adding the vocabulary: " + e.toString());
			e.printStackTrace();
		}
		return assetVocabulary;
	}

	/**
	 * Gets a list of categorydto which contains the same parentId as the specified in parameters
	 * @param parentId Parent categorydto identifier
	 * @param categoryDTOList List of categorydto to search
	 * @return List of categorydto which contains the same parentId as the specified in parameters
	 */
	public static List<CategoryDTO> getChildCategoryDTOList(String parentId, List<CategoryDTO> categoryDTOList) {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			if (parentId.equals(categoryDTO.getParentId())) {
				result.add(categoryDTO);
			}
		}
		return result;
	}

	/**
	 * Recursive function to store a category and his children
	 * @param parentCategoryDTO Parent category Data transfer object
	 * @param userId User identifier
	 * @param groupId Grouyp identifier
	 * @param serviceContext Service context
	 * @param assetVocabulary Vocabulary that will contains the categories
	 * @param parentCategoryId Parent category of current
	 * @param fullCategoryDTOList List with all categories loaded from opendata
	 */
	public static void addOrGetChildCategory(List<Long> modifiedCategoriesIds, CategoryDTO categoryDTO, long userId, long groupId, ServiceContext serviceContext, AssetVocabulary assetVocabulary, long parentCategoryId, List<CategoryDTO> fullCategoryDTOList) {
		try {
			AssetCategory category = null;
			//Creamos o actualizamos la categoria actual
			category = addOrGetCategory(userId, groupId, parentCategoryId, categoryDTO, assetVocabulary.getVocabularyId(), serviceContext);
			if (category != null) {
				//Se almacena el id de la categoría en el listado
				modifiedCategoriesIds.add(category.getCategoryId());
				//Se almacenan las propiedades personalizadas correspondientes
				saveCustomCategoryProperties(category, categoryDTO);
				//Iteramos recursivamente sobre los hijos de la categoria actual
				if (categoryDTO.getId() != null && !categoryDTO.getId().isEmpty()) {
					List<CategoryDTO> childCategoryDTOList = getChildCategoryDTOList(categoryDTO.getId(), fullCategoryDTOList);
					if (childCategoryDTOList != null && !childCategoryDTOList.isEmpty()) {
						for (CategoryDTO childCategoryDTO : childCategoryDTOList) {
							addOrGetChildCategory(modifiedCategoriesIds, childCategoryDTO, userId, groupId, serviceContext, assetVocabulary, category.getCategoryId(), fullCategoryDTOList);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error("There was an error adding the child category: " + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Add or retrieve a category from system
	 * @param userId User identifier
	 * @param groupId Group identifier
	 * @param serviceContext Service context
	 * @param vocabulary Vocabulary that will contains the category
	 * @param titleMap Translated title of the category
	 * @param descriptionMap Translated description of the category
	 * @param parentCategoryId Parent category of current
	 * @return Created or retrieved category
	 */
	public static AssetCategory addOrGetCategory(long userId, long groupId, long parentCategoryId, CategoryDTO categoryDTO, long vocabularyId, ServiceContext serviceContext) {
		AssetCategory assetCategory = null;
		try {	
			Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
			String titleDefaultLocale = categoryDTO.getTitleMap().get(siteDefaultLocale);
			if (titleDefaultLocale == null) {
				titleDefaultLocale =  categoryDTO.getTitleMap().get(LocaleUtil.US);
			}
			//Search category by properties
			Map<String, String> customPropertiesMap = categoryDTO.getCustomProperties();
			if (customPropertiesMap != null && !customPropertiesMap.isEmpty()) {
				//Search category by opendata id
				if (assetCategory == null) {
					if (customPropertiesMap.containsKey(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID)) {
						String opendataId = customPropertiesMap.get(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID);
						assetCategory = findCategoryByProperty(vocabularyId, AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, opendataId, titleDefaultLocale, siteDefaultLocale);
					}
				}
				//Search category by cod siu
				if (assetCategory == null) {
					if (customPropertiesMap.containsKey(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU)) {
						String codSiu = customPropertiesMap.get(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU);
						assetCategory = findCategoryByProperty(vocabularyId, AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU, codSiu, titleDefaultLocale, siteDefaultLocale);
					}
				}
			}
			//Search category by name and parent
			if (assetCategory == null) {
				assetCategory = AssetCategoryLocalServiceUtil.fetchCategory(groupId, parentCategoryId, categoryDTO.getName(), vocabularyId);
			}
			//Search category by name
			if (assetCategory == null) {
				List <AssetCategory> listAssetCategoriesEqualsTitle = findByTitle(groupId, vocabularyId, titleDefaultLocale, siteDefaultLocale);
				if(listAssetCategoriesEqualsTitle.size() == 1 ) {
					assetCategory = listAssetCategoriesEqualsTitle.get(0);
				}
			}
			if (Validator.isNotNull(assetCategory)) {
				_log.info("UPDATING CATEGORY... " + categoryDTO.getName());
				assetCategory.setVocabularyId(vocabularyId);
				assetCategory.setUserId(userId);
				assetCategory.setParentCategoryId(parentCategoryId);
				assetCategory.setTitleMap(categoryDTO.getTitleMap());
				assetCategory.setDescriptionMap(categoryDTO.getDescriptionMap());
				assetCategory.setName(categoryDTO.getName());
				assetCategory = AssetCategoryLocalServiceUtil.updateAssetCategory(assetCategory);
				//Add update category registry
				saveImportCategoryRegistry(assetCategory, "update");
			} else {
				_log.info("ADDING CATEGORY... " + categoryDTO.getName());
				Map<Locale,String> nameMap = new HashMap<Locale, String>();
				nameMap.put(LocaleUtil.getSiteDefault(), categoryDTO.getName());
				assetCategory = AssetCategoryLocalServiceUtil.addCategory(userId, groupId, parentCategoryId, nameMap, categoryDTO.getDescriptionMap(), vocabularyId, null, serviceContext);
				assetCategory.setName(categoryDTO.getName());
				categoryDTO.getTitleMap().put(LocaleUtil.getSiteDefault(), titleDefaultLocale);
				assetCategory.setTitleMap(categoryDTO.getTitleMap());
				assetCategory = AssetCategoryLocalServiceUtil.updateAssetCategory(assetCategory);
				//Add create category table
				saveImportCategoryRegistry(assetCategory, "create");
			}
		} catch (Exception e) {
			_log.error("There was an error adding the category " + categoryDTO.getTitleMap() + ": " + e.toString());
			//e.printStackTrace();
		}
		return assetCategory;
	}

	private static List<AssetCategory> findByTitle(long groupId, long vocabularyId, String title, Locale locale) {
		DynamicQuery dynamicQuery = AssetCategoryLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("vocabularyId", vocabularyId));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike("title", "%"+title+"%"));
		List<AssetCategory> listAssetCategoriesEqualTitle = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		List<AssetCategory> results = new ArrayList<AssetCategory>();
		for(AssetCategory category : listAssetCategoriesEqualTitle) {
			String categoryTitle = category.getTitle(locale);
			if(Validator.isNotNull(categoryTitle) && categoryTitle.equalsIgnoreCase(title)) {
				results.add(category);
			}
		}
		return listAssetCategoriesEqualTitle;
	}

	/**
	 * Saves all properties from categoryDTO in the custom category properties table
	 * @param category Created asset category
	 * @param categoryDTO Category DTO loaded with all required properties
	 */
	public static void saveCustomCategoryProperties(AssetCategory category, CategoryDTO categoryDTO) {
		Date dateNow = new Date();
		if (category != null && categoryDTO != null) {
			Map<String, String> customCategoryPropertiesMap = categoryDTO.getCustomProperties();
			if (customCategoryPropertiesMap != null && !customCategoryPropertiesMap.isEmpty()) {
				for (Map.Entry<String, String> entry : customCategoryPropertiesMap.entrySet()) {					
					String key = entry.getKey();
					String value = entry.getValue();
					if (key != null && !key.trim().isEmpty()) {
						CustomCategoryProperty customCategoryProperty = CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(category.getCategoryId(), key);
						if (customCategoryProperty != null) {
							if (!customCategoryProperty.getText().equals(value)) {
								_log.info("Updating custom property " + key + "=" + value);
								customCategoryProperty.setText(value);
								CustomCategoryPropertyLocalServiceUtil.updateCustomCategoryProperty(customCategoryProperty);
							}
						} else {
							_log.info("Adding custom property " + key + "=" + value);
							CustomCategoryPropertyLocalServiceUtil.addCustomCategoryProperty(category.getGroupId(), category.getCompanyId(), category.getUserId(), category.getUserName(), dateNow, dateNow, category.getVocabularyId(), category.getCategoryId(), key, value);	
						}						
					}
				}
			}
		}
	}
	
	public static void saveImportCategoryRegistry(AssetCategory assetCategory, String type) {
		try {
			ImportCategoryRegistry importCategoryRegistry = null;
			try {
				importCategoryRegistry = ImportCategoryRegistryLocalServiceUtil.findByCategoryId(assetCategory.getCategoryId());	
			} catch (NoSuchImportCategoryRegistryException e) {
				importCategoryRegistry = null;
			}
			if (importCategoryRegistry == null) {
				ImportCategoryRegistryLocalServiceUtil.addImportCategoryRegistry(assetCategory.getCategoryId(), assetCategory.getVocabularyId(), type, StringPool.BLANK);
			} else {
				importCategoryRegistry.setType(type);
				ImportCategoryRegistryLocalServiceUtil.updateImportCategoryRegistry(importCategoryRegistry);
			}
		} catch (Exception e) {
			_log.error("There was an error saving the import category registry: " + e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * Register as deprecated all the categories stored in the database not received from the import process
	 * @param vocabularyId Vocabulary identifier
	 * @param modifiedCategoriesIds List of categories identifiers modified or created during the import process
	 */
	public static void registerDeprecatedCategories(long vocabularyId, List<Long> modifiedCategoriesIds) {
		_log.info("SEARCHING FOR DEPRECATED CATEGORIES");
		if (vocabularyId > 0) {
			List<AssetCategory> dbCategories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			if (dbCategories != null && !dbCategories.isEmpty()) {
				for (AssetCategory dbCategory : dbCategories) {
					if (!modifiedCategoriesIds.contains(dbCategory.getCategoryId())) {
						_log.info("Deprecated category: " + dbCategory.getTitle(LocaleUtil.SPAIN) + " (" + dbCategory.getCategoryId() + ")");
						ImportCategoryRegistryLocalServiceUtil.addImportCategoryRegistry(dbCategory.getCategoryId(), dbCategory.getVocabularyId(), "deprecated", StringPool.BLANK);
					}
				}
			}
		}
	}
	
	/**
	 * Reindex all categories of the given vocabulary
	 * @param vocabularyId Vacabulary identifier
	 */
	public static void reindexCategories(long vocabularyId) {
		List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		Indexer<AssetCategory> indexer = IndexerRegistryUtil.getIndexer(AssetCategory.class.getName());
		for(AssetCategory category : categories) {
			try {
				indexer.reindex(category);
			} catch (SearchException e) {
				_log.error("Error reindexing asset category " + category.getName());
			}
		}
	}
	
	/**
	 * Deletes all import categories historic registry of the given vocabulary
	 * @param vocabularyId Vocabulary identifier
	 */
	public static void cleanImportCategoryRegistry(long vocabularyId) {
		List<ImportCategoryRegistry> importCategoryRegistries = ImportCategoryRegistryLocalServiceUtil.findByVocabularyId(vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		if (importCategoryRegistries != null && !importCategoryRegistries.isEmpty()) {
			for (ImportCategoryRegistry importCategoryRegistry : importCategoryRegistries) {
				ImportCategoryRegistryLocalServiceUtil.deleteImportCategoryRegistry(importCategoryRegistry);
			}
		}
	}
	
	/**
	 * Finds a category by custom property
	 * @param vocabularyId Vocabulary identifier
	 * @param key Property key
	 * @param text Property value
	 * @param siteDefaultLocale 
	 * @param titleDefaultLocale 
	 * @return Category by custom property
	 */
	private static AssetCategory findCategoryByProperty(long vocabularyId, String key, String text, String title, Locale locale) {
		AssetCategory assetCategory = null;
		if (key != null && !key.trim().isEmpty() && text!=null && !text.trim().isEmpty()) {
			List<CustomCategoryProperty> customCategoryPropertyList = CustomCategoryPropertyLocalServiceUtil.findByVocabularyIdAndKeyAndText(vocabularyId, key, text);
			if (customCategoryPropertyList != null && !customCategoryPropertyList.isEmpty()) {
				CustomCategoryProperty customCategoryProperty = customCategoryPropertyList.get(0);
				assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(customCategoryProperty.getCategoryId());
				// If there are more than one result, we will try to get the category that have the same name of the category that we are updating
				if(customCategoryPropertyList.size()>1) {
					for(CustomCategoryProperty ccp : customCategoryPropertyList) {
						AssetCategory assetCategoryAux = AssetCategoryLocalServiceUtil.fetchAssetCategory(ccp.getCategoryId());
						if(assetCategoryAux!=null && Validator.isNotNull(assetCategoryAux.getTitle(locale)) && title.equalsIgnoreCase(assetCategoryAux.getTitle(locale))) {
							assetCategory = assetCategoryAux;
							break;
						}
					}
				}
			}
		}
		return assetCategory;
	}
	
	/**
	 * Returns a formatted String with the name of the category
	 * @param prefix Prefix
	 * @param suffix Suffix
	 * @return String with the name of the category
	 */
	public static String getCategoryName(String prefix, String suffix) {
		String result = StringPool.BLANK;
		if (prefix != null && !prefix.isEmpty()) {
			result = prefix + StringPool.UNDERLINE;
		}
		result = result + suffix;
		if (result.length() > 75) {
			result = result.substring(0, 75);
		}
		return result;
	}

	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(CategoriesImporterUtil.class);
	
}