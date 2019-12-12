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
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_importer.dto.CategoryDTO;

/**
 * @author pfalcon
 * Temas categories import utilities class
 */
public class TiposDocumentoUtil {
	
	/**
	 * Imports all the document types in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
	 * @param serviceContext Service context
	 */
	public static void importTiposDocumento(long groupId, long userId, ServiceContext serviceContext) {
		//List to store the modified tipos de documento in the process
		List<Long> modifiedTiposDocumentosIds = new ArrayList<Long>();
		//Add vocabulary if does not exists 
		Map<Locale, String> tiposDocumentoTitleMap = new HashMap<Locale, String>();
		tiposDocumentoTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES);
		tiposDocumentoTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_EN);
		AssetVocabulary tiposDocumentoVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, tiposDocumentoTitleMap, tiposDocumentoTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(tiposDocumentoVocabulary.getCompanyId(), tiposDocumentoVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(tiposDocumentoVocabulary.getCompanyId(), tiposDocumentoVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(tiposDocumentoVocabulary.getCompanyId(), tiposDocumentoVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(tiposDocumentoVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(tiposDocumentoVocabulary.getCompanyId(), tiposDocumentoVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(tiposDocumentoVocabulary.getVocabularyId());
		//Get all tipos de documento from opendata
		List<CategoryDTO> fullOpenDataTiposDocumentoCategoryDTOList = getOpenDataTiposDocumentoCategoryDTOList();
		if (Validator.isNotNull(fullOpenDataTiposDocumentoCategoryDTOList)) {
			//Load categories in the current group
			for (CategoryDTO rootTipoDocumentoCategoryDTO : fullOpenDataTiposDocumentoCategoryDTOList) {	
				CategoriesImporterUtil.addOrGetChildCategory(modifiedTiposDocumentosIds, rootTipoDocumentoCategoryDTO, userId, groupId, serviceContext, tiposDocumentoVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullOpenDataTiposDocumentoCategoryDTOList);
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(tiposDocumentoVocabulary.getVocabularyId(), modifiedTiposDocumentosIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(tiposDocumentoVocabulary.getVocabularyId());
		}
	}
	
	/**
	 * Gets a categorydto list with tipos de documento loaded from Arargon Opendata
	 * @return CategoryDTO list with tipos de documento loaded from Arargon Opendata
	 */
	public static List<CategoryDTO> getOpenDataTiposDocumentoCategoryDTOList() {
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		try {
			//Read the Tipos de documento excel from portalempleado
			_log.info("GETTING DATA FROM PORTALEMPLEADO");
			URL portalEmpleadoUrl = new URL("http://portalempleado.aragon.es/pls/portal/url/ITEM/4E9871E4480532E4E05400144FF956C9");
			URLConnection urlConnection = portalEmpleadoUrl.openConnection();
			Workbook workbook = new HSSFWorkbook(urlConnection.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			int tipoDocumentoCont = 0;
			//Iteration of the obtained results (the first entry contains the headers)
			while (rowIterator.hasNext()) {
				//Tipo de Documento, Valor metadato, Aclaración
				Row row = rowIterator.next();
				Cell nameCell = row.getCell(1);
				Cell titleCell = row.getCell(0);
				Cell descriptionCell = row.getCell(2);
				if (tipoDocumentoCont > 0 && nameCell != null && titleCell != null) {
					CategoryDTO categoryDTO = new CategoryDTO();
					//Name
					String name = CategoriesImporterUtil.getCategoryName(StringPool.BLANK, row.getCell(1).getStringCellValue());
					categoryDTO.setName(name);
					//Title
					String title = row.getCell(0).getStringCellValue();
					Map<Locale, String> titleMap = new HashMap<Locale, String>();
					titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
					titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
					categoryDTO.setTitleMap(titleMap);
					//Description
					if (descriptionCell != null) {
						String description = descriptionCell.getStringCellValue();
						if (description != null && !description.isEmpty()) {
							Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
							descriptionMap.put(LocaleUtil.fromLanguageId("es_ES"), description);
							descriptionMap.put(LocaleUtil.fromLanguageId("en_EN"), description);
							categoryDTO.setDescriptionMap(descriptionMap);
						}
					}
					//Add category to list
					categoriesDTOList.add(categoryDTO);
				}
				tipoDocumentoCont++;
			}
			workbook.close();
			//Read the Tipos de documento JSON from OpenData
			_log.info("GETTING DATA FROM OPENDATA");
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=264&formato=json&nameRes=Listado%20de%20tipos%20documentales");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String tiposDocumentoJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				tiposDocumentoJSONArrayString = tiposDocumentoJSONArrayString + line;
			}
			//JSON processing
			if (!tiposDocumentoJSONArrayString.isEmpty()) {
				JSONArray tipoDocumentoJSONArray = JSONFactoryUtil.createJSONArray(tiposDocumentoJSONArrayString);
				//Iteration of the obtained results (the first entry contains the headers)
				for (int i = 1; i < tipoDocumentoJSONArray.length(); i++) {
					//"DOCUMENT_TYPE_ID", "METADATA_MODEL_ID", "NAME", "DESCRIPTION", "DOCUMENTUM_TYPE_ID", "HIGHLIGHT", "CLONED", "RNUM"
					JSONArray typeDocumentJSONArray = tipoDocumentoJSONArray.getJSONArray(i);
					if (typeDocumentOriginDataIsValid(typeDocumentJSONArray)) {
						CategoryDTO categoryDTO = new CategoryDTO();
						//Id
						String id = String.valueOf(typeDocumentJSONArray.get(0));
						categoryDTO.setId(id);
						//Name
						String name = CategoriesImporterUtil.getCategoryName(StringPool.BLANK, typeDocumentJSONArray.getString(2));
						categoryDTO.setName(name);
						//Title
						String title = (String)typeDocumentJSONArray.get(3);
						Map<Locale, String> titleMap = new HashMap<Locale, String>();
						titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
						titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
						categoryDTO.setTitleMap(titleMap);
						//OpenData Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, id);
						//Ei2a Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID, id);
						//Alias
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS, typeDocumentJSONArray.getString(2));
						//Add category to list
						categoriesDTOList.add(categoryDTO);
					}
				}				
			}
			in.close();
		} catch (Exception e) {
			_log.error("There was an error getting the list of tipos de documento CategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return categoriesDTOList;
	}
	
	/**
	 * Checks if the obtained tipoDocumento JsonArray is valid
	 * @param tipoDocumentoJSONArray TipoDocumento JsonArray
	 * @return True if the obtained tipoDocumento JsonArray is valid
	 */
	private static boolean typeDocumentOriginDataIsValid(JSONArray tipoDocumentoJSONArray) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();
		//"DOCUMENT_TYPE_ID", "METADATA_MODEL_ID", "NAME", "DESCRIPTION", "DOCUMENTUM_TYPE_ID", "HIGHLIGHT", "CLONED", "RNUM"
		if (tipoDocumentoJSONArray != null && tipoDocumentoJSONArray.length() > 0) {
			//DOCUMENT_TYPE_ID is mandatory
			String idDocumentType = String.valueOf(tipoDocumentoJSONArray.get(0));
			if (idDocumentType == null || idDocumentType.trim().isEmpty() || idDocumentType.equals("null")) {
				errorCauseList.add("FIELD DOCUMENT_TYPE_ID IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//NOMBRE is mandatory
			String nombre = String.valueOf(tipoDocumentoJSONArray.get(2));
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
			_log.error(tipoDocumentoJSONArray.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}

	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(TemasUtil.class);

}
