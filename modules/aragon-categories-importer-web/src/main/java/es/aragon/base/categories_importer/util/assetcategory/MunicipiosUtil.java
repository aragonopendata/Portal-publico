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
 * Municipios categories import utilities class
 */
public class MunicipiosUtil {
	
	/**
	 * Imports all the municipalities in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
	 * @param serviceContext Service context
	 */
	public static void importMunicipios(long groupId, long userId, ServiceContext serviceContext) {
		//List to store the modified municipios in the process
		List<Long> modifiedMunicipiosIds = new ArrayList<Long>();
		//Add vocabulary if does not exists 
		Map<Locale, String> municipiosTitleMap = new HashMap<Locale, String>();
		municipiosTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES);
		municipiosTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_EN);
		AssetVocabulary municipiosVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, municipiosTitleMap, municipiosTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(municipiosVocabulary.getCompanyId(), municipiosVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(municipiosVocabulary.getCompanyId(), municipiosVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(municipiosVocabulary.getCompanyId(), municipiosVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(municipiosVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(municipiosVocabulary.getCompanyId(), municipiosVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(municipiosVocabulary.getVocabularyId());
		//Get all municipios from opendata
		List<CategoryDTO> fullOpenDataMunicipiosCategoryDTOList = getMunicipiosCategoryDTOList();
		if (Validator.isNotNull(fullOpenDataMunicipiosCategoryDTOList)) {
			//Load categories in the current group
			for (CategoryDTO rootMunicipiosCategoryDTO : fullOpenDataMunicipiosCategoryDTOList) {	
				CategoriesImporterUtil.addOrGetChildCategory(modifiedMunicipiosIds, rootMunicipiosCategoryDTO, userId, groupId, serviceContext, municipiosVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullOpenDataMunicipiosCategoryDTOList);
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(municipiosVocabulary.getVocabularyId(), modifiedMunicipiosIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(municipiosVocabulary.getVocabularyId());
		}
	}
	
	/**
	 * Gets a categorydto list with municipios loaded from Arargon Opendata
	 * @return CategoryDTO list with municipios loaded from Arargon Opendata
	 */
	public static List<CategoryDTO> getMunicipiosCategoryDTOList() {
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		try {
			//Read the Municipios JSON from OpenData
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=11&formato=json&name=Informaci%C3%B3n%20general%20de%20los%20municipios%20de%20Arag%C3%B3n&nameRes=Informaci%C3%B3n%20general%20de%20los%20municipios%20de%20Arag%C3%B3n");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String municipiosJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				municipiosJSONArrayString = municipiosJSONArrayString + line;
			}
			//JSON processing
			if (!municipiosJSONArrayString.isEmpty()) {
				JSONArray municipiosJSONArray = JSONFactoryUtil.createJSONArray(municipiosJSONArrayString);
				//Iteration of the obtained results (the first entry contains the headers)
				for (int i = 1; i < municipiosJSONArray.length(); i++) {
					//"MUN_ID", "CODIGO_MUN", "DENOMINACION", "HABITANTES", "SUPERFICIE", "ALCALDE", "DIRECCION", "TELEFONO", "FAX", "EMAIL", "CP", "NIF", "COD_PROVINCIA", "PROVINCIA", "CODIGO_COMARC", "COMARCA", "DIPUTACION_ID", "DIPUTACION", "RNUM"
					JSONArray municipioJSONArray = municipiosJSONArray.getJSONArray(i);
					if (municipioOriginDataIsValid(municipioJSONArray)) {
						CategoryDTO categoryDTO = new CategoryDTO();
						//Id
						String id = String.valueOf(municipioJSONArray.get(0));
						categoryDTO.setId(id);
						//Title
						String title = (String)municipioJSONArray.get(2);
						Map<Locale, String> titleMap = new HashMap<>();
						titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
						titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
						categoryDTO.setTitleMap(titleMap);
						//Name
						categoryDTO.setName(CategoriesImporterUtil.getCategoryName(id, title));
						//OpenData Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, id);
						//Ei2a Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID, id);
						//Codigo Mun
						String codigoMun = String.valueOf(municipioJSONArray.get(1));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CODIGO_MUN, codigoMun);
						//Habitantes
						String habitantes = String.valueOf(municipioJSONArray.get(3));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_HABITANTES, habitantes);
						//Superficie
						String superficie = String.valueOf(municipioJSONArray.get(4));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_SUPERFICIE, superficie);
						//Alcalde
						String alcalde = String.valueOf(municipioJSONArray.get(5));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALCALDE, alcalde);
						//Direccion
						String direccion = String.valueOf(municipioJSONArray.get(6));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION, direccion);
						//Telefono
						String telefono = String.valueOf(municipioJSONArray.get(7));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO, telefono);
						//Fax
						String fax = String.valueOf(municipioJSONArray.get(8));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FAX, fax);
						//Email
						String email = String.valueOf(municipioJSONArray.get(9));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL, email);
						//CP
						String cp = String.valueOf(municipioJSONArray.get(10));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP, cp);
						//NIF
						String nif = String.valueOf(municipioJSONArray.get(11));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_NIF, nif);
						//Cod provincia
						String codProvincia = String.valueOf(municipioJSONArray.get(12));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_PROVINCIA, codProvincia);
						//Provincia
						String provincia = String.valueOf(municipioJSONArray.get(13));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA, provincia);
						//Codigo comarca
						String codigoComarca = String.valueOf(municipioJSONArray.get(14));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CODIGO_COMARC, codigoComarca);
						//Comarca
						String comarca = String.valueOf(municipioJSONArray.get(15));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COMARCA, comarca);
						//Diputacion id
						String diputacionId = String.valueOf(municipioJSONArray.get(16));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIPUTACION_ID, diputacionId);
						//Diputacion
						String diputacion = String.valueOf(municipioJSONArray.get(17));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIPUTACION, diputacion);
						//Rnum
						String rnum = String.valueOf(municipioJSONArray.get(18));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_RNUM, rnum);
						//Add category to list
						categoriesDTOList.add(categoryDTO);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			_log.error("There was an error getting the list of municipios CategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return categoriesDTOList;
	}
	/**
	 * Checks if the obtained municipio JsonArray is valid
	 * @param municipioJSONArray Municpio JsonArray
	 * @return True if the obtained municipio JsonArray is valid
	 */
	private static boolean municipioOriginDataIsValid(JSONArray municipioJSONArray) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();
		//"MUN_ID", "CODIGO_MUN", "DENOMINACION", "HABITANTES", "SUPERFICIE", "ALCALDE", "DIRECCION", "TELEFONO", "FAX", "EMAIL", "CP", "NIF", "COD_PROVINCIA", "PROVINCIA", "CODIGO_COMARC", "COMARCA", "DIPUTACION_ID", "DIPUTACION", "RNUM"
		if (municipioJSONArray != null && municipioJSONArray.length() > 0) {
			//MUN_ID is mandatory
			String idMunicipio = String.valueOf(municipioJSONArray.get(0));
			if (idMunicipio == null || idMunicipio.trim().isEmpty() || idMunicipio.equals("null")) {
				errorCauseList.add("FIELD MUN_ID IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//NOMBRE is mandatory
			String nombre = String.valueOf(municipioJSONArray.get(2));
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
			_log.error(municipioJSONArray.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}
	
	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(MunicipiosUtil.class);
	
}
