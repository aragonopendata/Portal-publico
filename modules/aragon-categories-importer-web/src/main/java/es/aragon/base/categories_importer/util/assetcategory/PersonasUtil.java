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

public class PersonasUtil {
	
	/**
	 * Imports all the people in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
 	 * @param legislaturaIdFilter Legislature identifier
	 * @param serviceContext Service context
	 */
	public static void importPersonas(long groupId, long userId, String legislaturaIdFilter, ServiceContext serviceContext) {
		//List to store the modified personas in the process
		List<Long> modifiedPersonasIds = new ArrayList<Long>();	
		//Add vocabulary if does not exists		
		Map<Locale, String> personasTitleMap = new HashMap<Locale, String>();
		personasTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES);
		personasTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_EN);
		AssetVocabulary personasVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, personasTitleMap, personasTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(personasVocabulary.getCompanyId(), personasVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(personasVocabulary.getCompanyId(), personasVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(personasVocabulary.getCompanyId(), personasVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(personasVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(personasVocabulary.getCompanyId(), personasVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(personasVocabulary.getVocabularyId());
		//Get all personas from opendata
		List<CategoryDTO> fullOpendataPersonasCategoryDTOList = getPersonasCategoryDTOList(legislaturaIdFilter);
		if (Validator.isNotNull(fullOpendataPersonasCategoryDTOList)) {
			//Load categories in the current group
			for (CategoryDTO categoryDTO : fullOpendataPersonasCategoryDTOList) {
				CategoriesImporterUtil.addOrGetChildCategory(modifiedPersonasIds, categoryDTO, userId, groupId, serviceContext, personasVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullOpendataPersonasCategoryDTOList);
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(personasVocabulary.getVocabularyId(), modifiedPersonasIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(personasVocabulary.getVocabularyId());
		}
	}
	
	/**
	 * Gets a categorydto list with personas loaded from Arargon Opendata
	 * @return CategoryDTO list with personas loaded from Arargon Opendata
	 */
	public static List<CategoryDTO> getPersonasCategoryDTOList(String idLegislaturaFilter) {
		_log.info("Getting people of legislature " + idLegislaturaFilter);
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		try {
			//Read the Cargos JSON from OpenData
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=160&formato=json&name=Organigrama%20del%20Gobierno%20de%20Arag%C3%B3n&nameRes=Cargos");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String personasJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				personasJSONArrayString = personasJSONArrayString + line;
			}
			//JSON processing
			if (!personasJSONArrayString.isEmpty()) {
				//Get all organizations from last legislature
				List<String> organismosIds = getOrganismosIds(idLegislaturaFilter);
				JSONArray personasJSONArray = JSONFactoryUtil.createJSONArray(personasJSONArrayString);
				//Iteration of the obtained results (the first entry contains the headers)
				for (int i = 1; i < personasJSONArray.length(); i++) {
					//"ID_CARGO", "ID_ENTIDAD", "ORDEN", "CARGO", "FECHA_INI", "FECHA_FIN", "NOMBRE", "EDIFICIO", "DIRECCION", "CP","LOCALIDAD","PROVINCIA","TELEFONO","BIOGRAFIA","FOTO_PATH","FOTO_NOMBRE","EMAIL","PUBLICADO","COOR_X","COOR_Y","RNUM"
					JSONArray personaJSONArray = personasJSONArray.getJSONArray(i);
					if (personaOriginDataIsValid(personaJSONArray, organismosIds)) {
						CategoryDTO categoryDTO = new CategoryDTO();
						//Id
						String id = String.valueOf(personaJSONArray.get(0));
						categoryDTO.setId(id);
						//Title
						String title = personaJSONArray.getString(6);
						Map<Locale, String> titleMap = new HashMap<Locale, String>();
						titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
						titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
						categoryDTO.setTitleMap(titleMap);
						//Name
						categoryDTO.setName(CategoriesImporterUtil.getCategoryName(id, title));
						//OpenData Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, id);
						//Ei2a Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID, id);
						//Id entidad
						String idEntidad = personaJSONArray.getString(1);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ENTIDAD_ID, idEntidad);
						//Cargo
						String cargo = personaJSONArray.getString(3);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CARGO, cargo);
						//Edificio
						String edificio = personaJSONArray.getString(7);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO, edificio);
						//Direccion
						String direccion = personaJSONArray.getString(8);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION, direccion);
						//CP
						String cp = personaJSONArray.getString(9);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP, cp);
						//Localidad
						String localidad = personaJSONArray.getString(10);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD, localidad);
						//Provincia
						String provincia = personaJSONArray.getString(11);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA, provincia);
						//Telefono
						String telefono = personaJSONArray.getString(12);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO, telefono);
						//Biografia
						String biografia = personaJSONArray.getString(13);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_BIOGRAFIA, biografia);
						//Foto_Path
						String fotoPath = personaJSONArray.getString(14);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_PATH, fotoPath);
						//Foto_Nombre
						String fotoNombre = personaJSONArray.getString(15);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_NOMBRE, fotoNombre);
						//Email
						String email = personaJSONArray.getString(16);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL, email);

						//Add category to list
						categoriesDTOList.add(categoryDTO);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			_log.error("There was an error getting the list of personas CategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return categoriesDTOList;
	}
	
	/**
	 * Checks if the obtained persona JsonArray is valid
	 * @param personaJSONArray Persona JsonArray
	 * @param organismosIds Current legislature organization ids 
	 * @return True if the obtained persona JsonArray is valid
	 */
	private static boolean personaOriginDataIsValid(JSONArray personaJSONArray, List<String> organismosIds) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();
		//"ID_CARGO", "ID_ENTIDAD", "ORDEN", "CARGO", "FECHA_INI", "FECHA_FIN", "NOMBRE", "EDIFICIO", "DIRECCION", "CP","LOCALIDAD","PROVINCIA","TELEFONO","BIOGRAFIA","FOTO_PATH","FOTO_NOMBRE","EMAIL","PUBLICADO","COOR_X","COOR_Y","RNUM"
		if (personaJSONArray != null && personaJSONArray.length() > 0) {
			//ID_CARGO is mandatory
			String idCargo = String.valueOf(personaJSONArray.get(0));
			if (idCargo == null || idCargo.trim().isEmpty() || idCargo.equals("null")) {
				errorCauseList.add("FIELD ID_CARGO IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//ID_ENTIDAD is mandatory
			String idEntidad = String.valueOf(personaJSONArray.get(1));
			if (idEntidad == null || idEntidad.trim().isEmpty() || idEntidad.equals("null")) {
				errorCauseList.add("FIELD ID_ENTIDAD IS MANDATORY IN POSITION 1");
				result = Boolean.FALSE;
			} else {
				if (!organismosIds.contains(idEntidad)) {
					errorCauseList.add("THE IDENTIFIER OF THE ASSOCIATED ORGANIZATION DOES NOT BELONG TO THE CURRENT LEGISLATURE");
					result = Boolean.FALSE;
				}
			}
			//ID_FECHA_FIN is mandatory
			String fechaFin = String.valueOf(personaJSONArray.get(5));
			if (fechaFin != null && !fechaFin.equals("null")) {
				errorCauseList.add("FIELD FECHA_FIN MUST BE NULL");
				result = Boolean.FALSE;
			} 
			
			//NOMBRE is mandatory
			String nombre = String.valueOf(personaJSONArray.get(6));
			if (nombre == null || nombre.trim().isEmpty() || nombre.equals("null")) {
				errorCauseList.add("FIELD NOMBRE IS MANDATORY IN POSITION 6");
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
			_log.error(personaJSONArray.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}
	
	/**
	 * Get all organismos opendata ids 
	 * @return List with all organismos opendata ids
	 */
	private static List<String> getOrganismosIds(String legislaturaIdFilter) {
		List<String> result = new ArrayList<String>();
		List<CategoryDTO> organismosDTOList = OrganismosUtil.getOrganismosCategoryDTOList(legislaturaIdFilter);
		if (organismosDTOList != null && organismosDTOList.size() > 0) {
			for (CategoryDTO organismoDTO : organismosDTOList) {
				Map<String, String> customProperties = organismoDTO.getCustomProperties();
				if(customProperties != null && !customProperties.isEmpty()) {
					if (customProperties.containsKey(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID)) {
						result.add(customProperties.get(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID));
					}
				}
			}
		}
		return result;
	}

	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(PersonasUtil.class);
	
}
