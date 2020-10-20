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
 * Organismos categories import utilities class
 */
public class OrganismosUtil {
	
	/**
	 * Imports all the organizations in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
 	 * @param legislaturaIdFilter Legislature identifier 
	 * @param serviceContext Service context
	 */
	public static void importOrganismos(long groupId, long userId, String legislaturaIdFilter, ServiceContext serviceContext) {
		//List to store the modified organismos in the process
		List<Long> modifiedOrganismosIds = new ArrayList<Long>();
		//Add vocabulary if does not exists
		Map<Locale, String> organismosTitleMap = new HashMap<Locale, String>();
		organismosTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
		organismosTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_EN);
		AssetVocabulary organismosVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, organismosTitleMap, organismosTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(organismosVocabulary.getCompanyId(), organismosVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(organismosVocabulary.getCompanyId(), organismosVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(organismosVocabulary.getCompanyId(), organismosVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(organismosVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(organismosVocabulary.getCompanyId(), organismosVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(organismosVocabulary.getVocabularyId());
		//Get all organismos from opendata
		List<CategoryDTO> fullOpendataOrganismosCategoryDTOList = getOrganismosCategoryDTOList(legislaturaIdFilter);
		if (Validator.isNotNull(fullOpendataOrganismosCategoryDTOList)) {
			//Load categories in the current group
			List <CategoryDTO> rootOrganismosCategoryDTOList = CategoriesImporterUtil.getChildCategoryDTOList("null", fullOpendataOrganismosCategoryDTOList);
			if (Validator.isNotNull(rootOrganismosCategoryDTOList)) {
				for (CategoryDTO rootOrganismoCategoryDTO : rootOrganismosCategoryDTOList) {
					CategoriesImporterUtil.addOrGetChildCategory(modifiedOrganismosIds, rootOrganismoCategoryDTO, userId, groupId, serviceContext, organismosVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullOpendataOrganismosCategoryDTOList);
				}
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(organismosVocabulary.getVocabularyId(), modifiedOrganismosIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(organismosVocabulary.getVocabularyId());
		}
	}

	/**
	 * Gets a categorydto list with organismos loaded from Arargon Opendata
	 * @return CategoryDTO list with organismos loaded from Arargon Opendata
	 */
	public static List<CategoryDTO> getOrganismosCategoryDTOList(String idLegislaturaFilter) {
		_log.info("Getting organizations of legislature " + idLegislaturaFilter);
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		try {
			//Read the Organismos JSON from OpenData
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=159&formato=json&name=Organigrama%20del%20Gobierno%20de%20Arag%C3%B3n&nameRes=Entidades");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String organismosJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				organismosJSONArrayString = organismosJSONArrayString + line;
			}
			//JSON processing
			if (!organismosJSONArrayString.isEmpty()) {
				JSONArray organismosJSONArray = JSONFactoryUtil.createJSONArray(organismosJSONArrayString);
				//Iteration of the obtained results (the first entry contains the headers)
				for (int i = 1; i < organismosJSONArray.length(); i++) {
					//"ID_ENTIDAD", "ID_ENTIDAD_PADRE", "ID_LEGISLATURA", "NIVEL", "ORDEN", "NOMBRE", "OBSERVACIONES", "DEPENDENCIA_DIRECTA", "COD_SIU", "EDIFICIO", "DIRECCION", "CP", "LOCALIDAD", "PROVINCIA", "TELEFONO", "COOR_X", "COOR_Y", "EMAIL", "RNUM"
					JSONArray organismoJSONArray = organismosJSONArray.getJSONArray(i);
					//Get organismos of the last legislature 
					if (organismoOriginDataIsValid(organismoJSONArray, idLegislaturaFilter)) {
						CategoryDTO categoryDTO = new CategoryDTO();
						//Id
						String id = String.valueOf(organismoJSONArray.get(0));
						categoryDTO.setId(id);
						//Title
						String title = (String)organismoJSONArray.get(5);
						Map<Locale, String> titleMap = new HashMap<Locale, String>();
						titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
						titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
						categoryDTO.setTitleMap(titleMap);
						//Name
						categoryDTO.setName(CategoriesImporterUtil.getCategoryName(id, title));
						//Description
						String description = organismoJSONArray.getString(6);
						if (description != null && !description.trim().isEmpty()) {
							Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
							descriptionMap.put(LocaleUtil.fromLanguageId("es_ES"), description);
							descriptionMap.put(LocaleUtil.fromLanguageId("en_EN"), description);
							categoryDTO.setDescriptionMap(descriptionMap);	
						}
						//Parent Id
						String parentId = String.valueOf(organismoJSONArray.get(1));
						categoryDTO.setParentId(parentId);
						//OpenData Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, id);
						//Ei2a Id
						String ei2aId = "ORG" + String.format("%07d", Integer.valueOf(id));
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID, ei2aId);
						//Id legislatura
						String idLegislatura = organismoJSONArray.getString(2);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ID_LEGISLATURA, idLegislatura);
						//Cod siu
						String codSiu = organismoJSONArray.getString(8);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU, codSiu);
						//Edificio
						String edificio = organismoJSONArray.getString(9);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO, edificio);
						//Direccion
						String direccion = organismoJSONArray.getString(10);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION, direccion);
						//CP
						String cp = organismoJSONArray.getString(11);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP, cp);
						//Localidad
						String localidad = organismoJSONArray.getString(12);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD, localidad);
						//Provincia
						String provincia = organismoJSONArray.getString(13);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA, provincia);
						//Telefono
						String telefono = organismoJSONArray.getString(14);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO, telefono);
						//Coordenada X
						String coorX = organismoJSONArray.getString(15);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_X, coorX);
						//Coordenada Y
						String coorY = organismoJSONArray.getString(16);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_Y, coorY);
						//Email
						String email = organismoJSONArray.getString(17);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL, email);
						//WebPageTitle
						String webPageTitle = organismoJSONArray.getString(18);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_WEB_PAGE_TITLE, webPageTitle);
						//WebPage
						String webPage = organismoJSONArray.getString(19);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_WEB_PAGE, webPage);
						//Add category to list
						categoriesDTOList.add(categoryDTO);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			_log.error("There was an error getting the list of organismos CategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return categoriesDTOList;
	}
	
	/**
	 * Checks if the obtained organismo JsonArray is valid
	 * @param organismoJSONArray Organismo JsonArray
	 * @return True if the obtained organismo JsonArray is valid
	 */
	private static boolean organismoOriginDataIsValid(JSONArray organismoJSONArray, String idLegislaturaFilter) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();
		//"ID_ENTIDAD", "ID_ENTIDAD_PADRE", "ID_LEGISLATURA", "NIVEL", "ORDEN", "NOMBRE", "OBSERVACIONES", "DEPENDENCIA_DIRECTA", "COD_SIU", "EDIFICIO", "DIRECCION", "CP", "LOCALIDAD", "PROVINCIA", "TELEFONO", "COOR_X", "COOR_Y", "RNUM"
		if (organismoJSONArray != null && organismoJSONArray.length() > 0) {
			//ID_ENTIDAD is mandatory
			String idEntidad = String.valueOf(organismoJSONArray.get(0));
			if (idEntidad == null || idEntidad.trim().isEmpty() || idEntidad.equals("null")) {
				errorCauseList.add("FIELD ID_ENTIDAD IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//ID_LEGISLATURA filter
			if (idLegislaturaFilter != null && !idLegislaturaFilter.isEmpty()) {
				String idLegislatura = String.valueOf(organismoJSONArray.get(2));
				if (!idLegislatura.equals(idLegislaturaFilter)) {
					errorCauseList.add("FIELD ID_LEGISLATURA MUST BE " + idLegislaturaFilter);
					result = Boolean.FALSE;
				}
			}
			//NOMBRE is mandatory
			String nombre = String.valueOf(organismoJSONArray.get(2));
			if (nombre == null || nombre.trim().isEmpty() || nombre.equals("null")) {
				errorCauseList.add("FIELD NOMBRE IS MANDATORY IN POSITION 5");
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
			_log.error(organismoJSONArray.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}
	
	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(OrganismosUtil.class);
	
}
