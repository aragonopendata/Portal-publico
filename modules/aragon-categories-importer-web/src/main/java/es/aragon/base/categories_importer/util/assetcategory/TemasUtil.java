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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_importer.dto.CategoryDTO;
import es.aragon.base.categories_importer.dto.Ei2aCategoryDTO;

/**
 * @author pfalcon
 * Temas categories import utilities class
 */
public class TemasUtil {
	
	/**
	 * Imports all the topics in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
	 * @param serviceContext Service context
	 */
	public static void importTemas(long groupId, long userId, ServiceContext serviceContext) {
		//List to store the modified temas in the process
		List<Long> modifiedTemasIds = new ArrayList<Long>();
		//Add vocabulary if does not exists
		Map<Locale, String> temasTitleMap = new HashMap<Locale, String>();
		temasTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
		temasTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_EN);
		AssetVocabulary temasVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, temasTitleMap, temasTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(temasVocabulary.getCompanyId(), temasVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(temasVocabulary.getCompanyId(), temasVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(temasVocabulary.getCompanyId(), temasVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(temasVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(temasVocabulary.getCompanyId(), temasVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(temasVocabulary.getVocabularyId());
		//Get all temas from opendata
		List<CategoryDTO> fullOpendataTemasCategoryDTOList = getOpenDataTemasCategoryDTOList();
		if (Validator.isNotNull(fullOpendataTemasCategoryDTOList)) {
			//Load categories in the current group
			List <CategoryDTO> rootTemasCategoryDTOList = CategoriesImporterUtil.getChildCategoryDTOList("0", fullOpendataTemasCategoryDTOList);
			if (Validator.isNotNull(rootTemasCategoryDTOList)) {
				for (CategoryDTO rootTemaCategoryDTO : rootTemasCategoryDTOList) {
					CategoriesImporterUtil.addOrGetChildCategory(modifiedTemasIds, rootTemaCategoryDTO, userId, groupId, serviceContext, temasVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullOpendataTemasCategoryDTOList);
				}
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(temasVocabulary.getVocabularyId(), modifiedTemasIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(temasVocabulary.getVocabularyId());
		}
	}

	/**
	 * Gets a categorydto list with temas loaded from Arargon Opendata
	 * @return CategoryDTO list with temas loaded from Arargon Opendata
	 */
	public static List<CategoryDTO> getOpenDataTemasCategoryDTOList() {
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		try {
			//Read the Temas JSON from OpenData
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=161&formato=json&name=DESFOR-Sede-Tema&nameRes=Temas");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String temasJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				temasJSONArrayString = temasJSONArrayString + line;
			}
			//JSON processing
			if (!temasJSONArrayString.isEmpty()) {
				JSONArray temasJSONArray = JSONFactoryUtil.createJSONArray(temasJSONArrayString);
				//Iteration of the obtained results (the first entry contains the headers)
				for (int i = 1; i < temasJSONArray.length(); i++) {
					//"ID_TEMA", "ALIAS", "NOMBRE", "DESCRIPCION", "NIVEL", "ID_PADRE", "RNUM"
					JSONArray temaJSONArray = temasJSONArray.getJSONArray(i);
					if(temaOriginDataIsValid(temaJSONArray)) {
						CategoryDTO categoryDTO = new CategoryDTO();
						//Id
						String id = String.valueOf(temaJSONArray.get(0));
						categoryDTO.setId(id);
						//OpenData Id
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_OPENDATA_ID, id);
						//Alias
						String alias = (String)temaJSONArray.get(1);
						categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS, alias);
						//Title
						String title = (String)temaJSONArray.get(2);
						Map<Locale, String> titleMap = new HashMap<Locale, String>();
						titleMap.put(LocaleUtil.fromLanguageId("es_ES"), title);
						titleMap.put(LocaleUtil.fromLanguageId("en_EN"), title);
						categoryDTO.setTitleMap(titleMap);
						//Name
						categoryDTO.setName(CategoriesImporterUtil.getCategoryName(id, title));
						//Description
						String description = temaJSONArray.getString(3);
						if (description != null && !description.trim().isEmpty()) {
							Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
							descriptionMap.put(LocaleUtil.fromLanguageId("es_ES"), description);
							descriptionMap.put(LocaleUtil.fromLanguageId("en_EN"), description);
							categoryDTO.setDescriptionMap(descriptionMap);	
						}
						//Parent Id
						String parentId = String.valueOf(temaJSONArray.get(5));
						categoryDTO.setParentId(parentId);
						//Add category to list
						categoriesDTOList.add(categoryDTO);
					}
				}
			}
			in.close();
			//Get all temas from ei2a
			List<Ei2aCategoryDTO> fullEi2aTemasCategoryDTOList = getEi2aTemasDTOList();
			//Add hierarchy title from opendata and ei2a
			for (CategoryDTO categoryDTO : categoriesDTOList) {
				String openDataHierarchyTitles = addOpenDataHierarchyTitle(categoryDTO, categoriesDTOList, StringPool.BLANK);
				//Obtaining the ids of all ei2a categories which have the same title as given
				List<String> ei2aCategoryIds = getPossibleEi2aCategoryIds(categoryDTO.getTitleMap().get(LocaleUtil.fromLanguageId("es_ES")), fullEi2aTemasCategoryDTOList);
				if (ei2aCategoryIds != null && !ei2aCategoryIds.isEmpty()) {
					for (String ei2aCategoryId : ei2aCategoryIds) {
						String ei2aHierarchyTitles = addEi2aHierarchyTitle(ei2aCategoryId, fullEi2aTemasCategoryDTOList, StringPool.BLANK);
						//If a category has been found in the ei2a with the same name and same hierarchical tree as in the opendata, the id of the ei2a is stored
						if (openDataHierarchyTitles.replace(" ", "").equalsIgnoreCase(ei2aHierarchyTitles.replace(" ", ""))) {
							categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID, ei2aCategoryId);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error("There was an error getting the list of temas CategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return categoriesDTOList;
	}

	/**
	 * Gets a Ei2aCategoryDTO list with temas loaded from ei2a
	 * @return Ei2aCategoryDTO list with temas loaded from ei2a
	 */
	private static List<Ei2aCategoryDTO> getEi2aTemasDTOList() {
		List<Ei2aCategoryDTO> ei2aTemasDTOList = new ArrayList<Ei2aCategoryDTO>();
		try {
			//Obtained file from opendata
			URL xmlURL = new URL("https://opendata.aragon.es/def/ei2a/CategorizationOntology.owl");
			HttpURLConnection connection = (HttpURLConnection) xmlURL.openConnection();
			InputStream inputStream = connection.getInputStream();
			//XML processing
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(inputStream);
			//Iteration of category elements
			NodeList categoryNodes = document.getElementsByTagName("owl:Class");
			for (int i = 0; i < categoryNodes.getLength(); i++) {
				Element categoryElement = (Element)categoryNodes.item(i);
				//Identifier
				String identifier = categoryElement.getAttribute("rdf:about");
				//Spanish title
				String title = StringPool.BLANK;
				NodeList labelNodes = categoryElement.getElementsByTagName("rdfs:label");
				for (int j = 0; j < labelNodes.getLength(); j++) {
					Element labelElement = (Element)labelNodes.item(j);
					String languageId = labelElement.getAttribute("xml:lang");
					if (languageId.equals("es")) {
						title = labelElement.getTextContent();
					}
				}
				//Parent category
				String parentIdentifier = StringPool.BLANK;
				NodeList parentCategoryNodes = categoryElement.getElementsByTagName("rdfs:subClassOf");
				if (parentCategoryNodes != null && parentCategoryNodes.getLength() > 0) {
					Element parentCategoryElement = (Element)parentCategoryNodes.item(0);
					parentIdentifier = parentCategoryElement.getAttribute("rdf:resource");
				}
				//Creation of DTO and insertion to the list
				Ei2aCategoryDTO ei2aCategoryDTO = new Ei2aCategoryDTO();
				ei2aCategoryDTO.setEi2aIdentifier(identifier.replaceAll("http://opendata.aragon.es/def/ei2a/categorization#", ""));
				ei2aCategoryDTO.setTitle(title);
				ei2aCategoryDTO.setEi2aParentIdentifier(parentIdentifier.replaceAll("http://opendata.aragon.es/def/ei2a/categorization#", ""));
				ei2aTemasDTOList.add(ei2aCategoryDTO);
			}
		} catch (Exception e) {
			_log.error("There was an error getting the list of temas Ei2aCategoryDTO: " + e.toString());
			e.printStackTrace();
		}
		return ei2aTemasDTOList;
	}

	/**
	 * Recursive function that returns a String with all the hierarchy tree of the given category. Values are separated by semicolons
	 * @param categoryDTO Data transfer object loaded from opendata
	 * @param fullOpendataCategoryDTOList Full list with all categories loaded from opendata.
	 * @param opendataHierarchyTitles Previous value of the hierarchy tree
	 * @return String with all the hierarchy tree of the given category
	 */
	private static String addOpenDataHierarchyTitle(CategoryDTO categoryDTO, List<CategoryDTO> fullOpendataCategoryDTOList, String opendataHierarchyTitles) {
		if (!categoryDTO.getId().equals("0")) {
			opendataHierarchyTitles = opendataHierarchyTitles + categoryDTO.getTitleMap().get(LocaleUtil.fromLanguageId("es_ES")) + ";";
			for (CategoryDTO auxCategoryDTO : fullOpendataCategoryDTOList) {
				if (auxCategoryDTO.getId().equals(categoryDTO.getParentId())) {
					opendataHierarchyTitles = addOpenDataHierarchyTitle(auxCategoryDTO, fullOpendataCategoryDTOList, opendataHierarchyTitles);
				}
			}
		}
		return opendataHierarchyTitles;
	}

	/**
	 * Returns a list loaded with all category ids from ei2a whose title matches the specified by parameters 
	 * @param title Title to search in ei2a
	 * @param fullEi2aTemasCategoryDTOList Full list of temas loaded from ei2a
	 * @return List loaded with all category ids from ei2a whose title matches the specified by parameters 
	 */
	private static List<String> getPossibleEi2aCategoryIds(String title, List<Ei2aCategoryDTO> fullEi2aTemasCategoryDTOList) {
		List<String> possibleEi2aCategoryIds = new ArrayList<String>();
		if (fullEi2aTemasCategoryDTOList!= null && !fullEi2aTemasCategoryDTOList.isEmpty()) {
			for (Ei2aCategoryDTO ei2aCategoryDTO : fullEi2aTemasCategoryDTOList) {
				possibleEi2aCategoryIds.add(ei2aCategoryDTO.getEi2aIdentifier());
			}
		}
		return possibleEi2aCategoryIds;
	}

	/**
	 * Returns a String with the category hierarchy tree from ei2a system. Values are separated by semicolon
	 * @param categoryId
	 * @param fullEi2aTemasCategoryDTOList
	 * @param ei2aHierarchyTitles
	 * @return String with the category hierarchy tree from ei2a system
	 */
	private static String addEi2aHierarchyTitle(String categoryId, List<Ei2aCategoryDTO> fullEi2aTemasCategoryDTOList, String ei2aHierarchyTitles) {
		if (!categoryId.isEmpty() && !categoryId.equals("GeneralCategorization")) {
			for (Ei2aCategoryDTO ei2aCategoryDTO : fullEi2aTemasCategoryDTOList) {
				if (ei2aCategoryDTO.getEi2aIdentifier().equals(categoryId)) {
					ei2aHierarchyTitles = ei2aHierarchyTitles + ei2aCategoryDTO.getTitle() + ";";
					ei2aHierarchyTitles = addEi2aHierarchyTitle(ei2aCategoryDTO.getEi2aParentIdentifier(), fullEi2aTemasCategoryDTOList, ei2aHierarchyTitles);
				}
			}
		}
		return ei2aHierarchyTitles;
	}
	/**
	 * Checks if the obtained tema JsonArray is valid
	 * @param temaJSONArray Tema JsonArray
	 * @return True if the obtained tema JsonArray is valid
	 */
	private static boolean temaOriginDataIsValid(JSONArray temaJSONArray) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();
		//"ID_TEMA", "ALIAS", "NOMBRE", "DESCRIPCION", "NIVEL", "ID_PADRE", "RNUM"
		if (temaJSONArray != null && temaJSONArray.length() > 0) {
			//ID_TEMA is mandatory
			String idTema = String.valueOf(temaJSONArray.get(0));
			if (idTema == null || idTema.trim().isEmpty() || idTema.equals("null")) {
				errorCauseList.add("FIELD ID_TEMA IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//NOMBRE is mandatory
			String nombre = String.valueOf(temaJSONArray.get(2));
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
			_log.error(temaJSONArray.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}

	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(TemasUtil.class);

}
