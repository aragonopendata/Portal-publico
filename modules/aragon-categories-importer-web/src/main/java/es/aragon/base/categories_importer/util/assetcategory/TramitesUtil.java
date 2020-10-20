package es.aragon.base.categories_importer.util.assetcategory;

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_importer.dto.CategoryDTO;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author pfalcon
 * Tramites categories import utilities class
 */
public class TramitesUtil {
	
	/**
	 * Imports all the procedures in asset categories system
	 * @param groupId Group identifier
	 * @param userId User identifier
	 * @param serviceContext Service context
	 */
	public static void importTramites(long companyId, long groupId, long userId, ServiceContext serviceContext) {
		//List to store the modified tramites in the process
		List<Long> modifiedTramitesIds = new ArrayList<Long>();		
		//Add vocabulary if does not exists
		Map<Locale, String> tramitesTitleMap = new HashMap<Locale, String>();
		tramitesTitleMap.put(LocaleUtil.SPAIN, AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_ES);
		tramitesTitleMap.put(LocaleUtil.US, AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_EN);
		AssetVocabulary tramitesVocabulary = CategoriesImporterUtil.addOrGetVocabulary(userId, groupId, serviceContext, tramitesTitleMap, tramitesTitleMap);
		// First, clean expandoo values
		ExpandoUtil.setNewUrlValue(tramitesVocabulary.getCompanyId(), tramitesVocabulary.getVocabularyId(), "");
		ExpandoUtil.setOldUrlValue(tramitesVocabulary.getCompanyId(), tramitesVocabulary.getVocabularyId(), "");
		ExpandoUtil.setCategoryMapValue(tramitesVocabulary.getCompanyId(), tramitesVocabulary.getVocabularyId(), "");
		// then, load old url map
		Locale siteDefaultLocale = LocaleUtil.fromLanguageId("es_ES");
		String vocabularyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(tramitesVocabulary.getTitle(siteDefaultLocale));
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, Boolean.FALSE, vocabularyURL);
		if(layout!=null) {
			ExpandoUtil.addOldUrlLayoutsValue(tramitesVocabulary.getCompanyId(), tramitesVocabulary.getVocabularyId(), layout);
		}
		//Delete previous import historic registry
		CategoriesImporterUtil.cleanImportCategoryRegistry(tramitesVocabulary.getVocabularyId());
		//Get all tramites from enlinea
		List<CategoryDTO> fullEnlineaTramitesCategoryDTOList = getEnlineaTramitesCategoryDTOList(companyId,groupId, userId);
		if (Validator.isNotNull(fullEnlineaTramitesCategoryDTOList)) {
			//Load categories in the current group
			for (CategoryDTO categoryDTO : fullEnlineaTramitesCategoryDTOList) {
				CategoriesImporterUtil.addOrGetChildCategory(modifiedTramitesIds, categoryDTO, userId, groupId, serviceContext, tramitesVocabulary, AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, fullEnlineaTramitesCategoryDTOList);
			}
			//Register deprecated categories
			CategoriesImporterUtil.registerDeprecatedCategories(tramitesVocabulary.getVocabularyId(), modifiedTramitesIds);
			//Reindex categories
			CategoriesImporterUtil.reindexCategories(tramitesVocabulary.getVocabularyId());
		}
	}

	/**
	 * Gets a categorydto list with tramites loaded from Enlinea
	 * @return CategoryDTO list with tramites loaded from Enlinea
	 */
	public static List<CategoryDTO> getEnlineaTramitesCategoryDTOList(long companyId, long groupId, long userId) {
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		//Create JSON with data from enlinea
		JSONArray tramitesJSONArray = getEnlineaTramitesJSONArray(companyId, groupId, userId);
		if (tramitesJSONArray != null) {
			for (int i = 0; i < tramitesJSONArray.length(); i++) {
				JSONObject tramiteJSONObject = tramitesJSONArray.getJSONObject(i);
				if (tramiteOriginDataIsValid(tramiteJSONObject)) {
					CategoryDTO categoryDTO = new CategoryDTO();
					//Signatura
					String signatura = tramiteJSONObject.getString("signatura");
					categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_SIGNATURA, signatura);
					//Title
					String title = tramiteJSONObject.getString("title");
					Map<Locale, String> titleMap = new HashMap<Locale, String>();
					titleMap.put(LocaleUtil.fromLanguageId("es_ES"), signatura + StringPool.UNDERLINE + title);
					titleMap.put(LocaleUtil.fromLanguageId("en_EN"), signatura + StringPool.UNDERLINE + title);
					categoryDTO.setTitleMap(titleMap);
					//Alias
					String alias = tramiteJSONObject.getString("friendlyURL");
					categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS, alias);
					//Name 
					String name = CategoriesImporterUtil.getCategoryName(signatura, title);
					categoryDTO.setName(name);
					//Add DTO to list
					categoriesDTOList.add(categoryDTO);
				}
			}
		}
		return categoriesDTOList;
	}

	/**
	 * Returns a JSONArray with all tramites loaded from Enlinea
	 * @return JSONArray with all tramites loaded from Enlinea
	 */
	private static JSONArray getEnlineaTramitesJSONArray(long companyId, long groupId, long userId) {
		JSONArray enlineaTramites = JSONFactoryUtil.createJSONArray();
		try {
			SearchContext searchContext = SearchContextFactory.getInstance(
					new long[0], new String[0], new HashMap<>(), 
					companyId, StringPool.BLANK, null, LocaleUtil.getDefault(),
					groupId, TimeZoneUtil.getDefault(), userId);
			BooleanQuery booleanQuery = new BooleanQueryImpl();
			booleanQuery.addRequiredTerm("entryClassName", Procedure.class.getName());
			try {
				Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
				int totalResult = hits.getLength();
				int numDocs = hits.getDocs().length;
				int totalDocs = numDocs;
				while(totalDocs < totalResult) {
					for(Document doc : hits.getDocs()) {
						JSONObject tramiteJSONObject = JSONFactoryUtil.createJSONObject();
						tramiteJSONObject.put("title", doc.get("name"));
						tramiteJSONObject.put("signatura", doc.get("procedureId"));
						tramiteJSONObject.put("friendlyURL", doc.get("friendlyURL"));
						enlineaTramites.put(tramiteJSONObject);
					}
					searchContext.setStart(totalDocs);
					searchContext.setEnd(totalResult);
					hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
					totalDocs += numDocs;
				}
				if (totalDocs >= totalResult) {
					for(Document doc : hits.getDocs()) {
						JSONObject tramiteJSONObject = JSONFactoryUtil.createJSONObject();
						tramiteJSONObject.put("title", doc.get("name"));
						tramiteJSONObject.put("signatura", doc.get("procedureId"));
						tramiteJSONObject.put("friendlyURL", doc.get("friendlyURL"));
						enlineaTramites.put(tramiteJSONObject);
					}
				}
			} catch(SearchException e) {
				_log.error("Error searching procedures for delete");	
			}
		} catch (Exception e) {
			_log.error("There was an error getting the enlinea procedures JSONArray: " + e.toString());
			e.printStackTrace();
		}
		return enlineaTramites;
	}

	/**
	 * Checks if the obtained tramite JsonArray is valid
	 * @param tramiteJSONArray Tramite JsonArray
	 * @return True if the obtained tramite JsonArray is valid
	 */
	private static boolean tramiteOriginDataIsValid(JSONObject tramiteJSONObject) {
		Boolean result = Boolean.TRUE;
		List<String> errorCauseList = new ArrayList<String>();		
		if (tramiteJSONObject != null && tramiteJSONObject.length() > 0) {
			//TITLE is mandatory
			String title = tramiteJSONObject.getString("title");
			if (title == null || title.trim().isEmpty() || title.equals("null")) {
				errorCauseList.add("FIELD TITLE IS MANDATORY IN POSITION 0");
				result = Boolean.FALSE;
			}
			//SIGNATURA is mandatory
			String signatura = tramiteJSONObject.getString("signatura");
			if (signatura == null || signatura.trim().isEmpty() || signatura.equals("null")) {
				errorCauseList.add("FIELD SIGNATURA IS MANDATORY IN POSITION 2");
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
			_log.error(tramiteJSONObject.toJSONString() + " is not valid " + errorCauseSb.toString());
		}
		return result;
	}

	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(TramitesUtil.class);

}
