package es.aragon.base.categories_importer.util.assetcategory;

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_importer.dto.CategoryDTO;

/**
 * @author pfalcon
 * Perfiles categories import utilities class
 */
public class PerfilesUtil {
	
	/**
	 * Imports all the profiles in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
	 * @param serviceContext Service context
	 */
	public static void importPerfiles(long groupId, long userId, ServiceContext serviceContext) {
		//List to store the modified perfiles in the process
		List<Long> modifiedPerfilesIds = new ArrayList<Long>();
		//Add vocabulary if does not exists 
		Map<Locale, String> perfilTitleMap = new HashMap<Locale, String>();
		perfilTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES);
		perfilTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_EN);
		AssetVocabulary perfilesVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, perfilTitleMap, perfilTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(perfilesVocabulary.getCompanyId(), perfilesVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(perfilesVocabulary.getCompanyId(), perfilesVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(perfilesVocabulary.getCompanyId(), perfilesVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(perfilesVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(perfilesVocabulary.getCompanyId(), perfilesVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(perfilesVocabulary.getVocabularyId());
		//Get all perfiles from opendata
		List<CategoryDTO> fullOpenDataPerfilesCategoryDTOList = getPerfilesCategoryDTOList();
		if (Validator.isNotNull(fullOpenDataPerfilesCategoryDTOList)) {
			//Load categories in the current group
			List <CategoryDTO> rootPerfilesCategoryDTOList = CategoriesImporterUtil.getChildCategoryDTOList("0", fullOpenDataPerfilesCategoryDTOList);
			if (Validator.isNotNull(rootPerfilesCategoryDTOList)) {
				for (CategoryDTO rootPerfilesCategoryDTO : rootPerfilesCategoryDTOList) {
					CategoriesImporterUtil.addOrGetChildCategory(modifiedPerfilesIds, rootPerfilesCategoryDTO, userId, groupId, serviceContext, perfilesVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullOpenDataPerfilesCategoryDTOList);
				}
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(perfilesVocabulary.getVocabularyId(), modifiedPerfilesIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(perfilesVocabulary.getVocabularyId());
		}
	}

	/**
	 * Gets a categorydto list with organismos loaded from Arargon Opendata
	 * @return CategoryDTO list with organismos loaded from Arargon Opendata
	 */
	public static List<CategoryDTO> getPerfilesCategoryDTOList() {
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		try {
			//Read the Temas JSON from OpenData
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=162&formato=json&name=DESFOR-Sede-Colectivo&nameRes=Sede%20Colectivo");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String perfilesJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				perfilesJSONArrayString = perfilesJSONArrayString + line;
			}
			//JSON processing
			if (!perfilesJSONArrayString.isEmpty()) {
				JSONArray perfilesJSONArray = JSONFactoryUtil.createJSONArray(perfilesJSONArrayString);
				//Iteration of the obtained results (the first entry contains the headers)
				for (int i = 1; i < perfilesJSONArray.length(); i++) {
					//"ID_COLECTIVO", "ALIAS", "NOMBRE", "DESCRIPCION", "NIVEL", "ID_PADRE", "RNUM"
					JSONArray perfilJSONArray = perfilesJSONArray.getJSONArray(i);
					if (perfilOriginDataIsValid(perfilJSONArray)) {
						CategoryDTO categoryDTO = new CategoryDTO();
						//Id
						String id = String.valueOf(perfilJSONArray.get(0));
						categoryDTO.setId(id);
						//Alias
						String alias = (String)perfilJSONArray.get(1);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS, alias);
						//Title
						String title = (String)perfilJSONArray.get(2);
						Map<Locale, String> titleMap = new HashMap<Locale, String>();
						titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
						titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
						categoryDTO.setTitleMap(titleMap);
						//Name
						categoryDTO.setName(CategoriesImporterUtil.getCategoryName(id, title));
						//Description
						String description = perfilJSONArray.getString(3);
						if (description != null && !description.trim().isEmpty()) {
							Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
							descriptionMap.put(LocaleUtil.fromLanguageId("es_ES"), description);
							descriptionMap.put(LocaleUtil.fromLanguageId("en_EN"), description);
							categoryDTO.setDescriptionMap(descriptionMap);	
						}
						//Parent Id
						String parentId = String.valueOf(perfilJSONArray.get(5));
						categoryDTO.setParentId(parentId);
						//OpenData Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, id);
						//Ei2a Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID, id);
						//Add category to list
						categoriesDTOList.add(categoryDTO);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			_log.error("There was an error getting the list of perfiles CategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return categoriesDTOList;
	}
	/**
	 * Checks if the obtained perfil JsonArray is valid
	 * @param perfilJSONArray Perfil JsonArray
	 * @return True if the obtained perfil JsonArray is valid
	 */
	private static boolean perfilOriginDataIsValid(JSONArray perfilJSONArray) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();
		//"ID_COLECTIVO", "ALIAS", "NOMBRE", "DESCRIPCION", "NIVEL", "ID_PADRE", "RNUM"
		if (perfilJSONArray != null && perfilJSONArray.length() > 0) {
			//ID_COLECTIVO is mandatory
			String idColectivo = String.valueOf(perfilJSONArray.get(0));
			if (idColectivo == null || idColectivo.trim().isEmpty() || idColectivo.equals("null")) {
				errorCauseList.add("FIELD ID_PERFIL IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//NOMBRE is mandatory
			String nombre = String.valueOf(perfilJSONArray.get(2));
			if (nombre == null || nombre.trim().isEmpty() || nombre.equals("null")) {
				errorCauseList.add("FIELD NOMBRE IS MANDATORY IN POSITION 2");
				result = Boolean.FALSE;
			}
		} else {
			result = Boolean.FALSE;
		}
		if (result == Boolean.FALSE) {
			//Show custom error message
			StringBuilder errorCauseSb = new StringBuilder();
			if (errorCauseList != null && !errorCauseList.isEmpty()) {
				int count = 0;
				for (String errorCause : errorCauseList) {
					if (count > 0) {
						errorCauseSb.append(StringPool.COMMA + StringPool.SPACE);
					}
					errorCauseSb.append(errorCause);
					count++;
				}
			}
			_log.error(perfilJSONArray.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}

	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(OrganismosUtil.class);
	
}

