package es.aragon.base.categories_importer.util.assetcategory;

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
	public static void importTramites(long groupId, long userId, ServiceContext serviceContext) {
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
		List<CategoryDTO> fullEnlineaTramitesCategoryDTOList = getEnlineaTramitesCategoryDTOList();
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
	public static List<CategoryDTO> getEnlineaTramitesCategoryDTOList() {
		List<CategoryDTO> categoriesDTOList = new ArrayList<CategoryDTO>();
		//Create JSON with data from enlinea
		JSONArray tramitesJSONArray = getEnlineaTramitesJSONArray();
		if (tramitesJSONArray != null) {
			for (int i = 0; i < tramitesJSONArray.length(); i++) {
				JSONObject tramiteJSONObject = tramitesJSONArray.getJSONObject(i);
				if (tramiteOriginDataIsValid(tramiteJSONObject)) {
					CategoryDTO categoryDTO = new CategoryDTO();
					//Signatura
					String signatura = tramiteJSONObject.getString("signatura");
					categoryDTO.addCustomProperty(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_SIGNATURA, signatura);
					//Title
					String title = tramiteJSONObject.getString("denominacion");
					Map<Locale, String> titleMap = new HashMap<Locale, String>();
					titleMap.put(LocaleUtil.fromLanguageId("es_ES"), signatura + StringPool.UNDERLINE + title);
					titleMap.put(LocaleUtil.fromLanguageId("en_EN"), signatura + StringPool.UNDERLINE + title);
					categoryDTO.setTitleMap(titleMap);
					//Alias
					String alias = tramiteJSONObject.getString("alias");
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
	private static JSONArray getEnlineaTramitesJSONArray() {
		JSONArray enlineaTramites = JSONFactoryUtil.createJSONArray();
		try {
			boolean isLastPage = Boolean.FALSE;
			int start = 0;
			while (!isLastPage) {
				JSONArray enlineaPage = getEnlineaTramitesPageJSONArray(start);
				for (int i = 0; i < enlineaPage.length(); i++) {
					JSONObject tramiteJSON = enlineaPage.getJSONObject(i);
					enlineaTramites.put(tramiteJSON);
				}
				if (enlineaPage == null || enlineaPage.length() < 20) {
					isLastPage = Boolean.TRUE;
				}
				start = start + 20;
			}
		} catch (Exception e) {
			_log.error("There was an error getting the enlinea procedures JSONArray: " + e.toString());
			e.printStackTrace();
		}
		return enlineaTramites;
	}

	/**
	 * Returns a JSONArray with the tramites fount in one page of Enlinea
	 * @param start Position of the first page tramite
	 * @return JSONArray with the tramites fount in one page of Enlinea
	 * @throws Exception Throwed when an error occurs
	 */
	private static JSONArray getEnlineaTramitesPageJSONArray(int start) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://enlinea.aragon.es/web/sede/busqueda?p_p_id=buscador_WAR_sedeportlet&p_p_lifecycle=2&p_p_state=maximized&p_p_mode=view&p_p_resource_id=getMoreResults&p_p_cacheability=cacheLevelPage");
		//Parameters
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("_buscador_WAR_sedeportlet_searchData", "{\"keywords\":\"\",\"filter-temas\":[],\"filter-tipos\":[],\"filter-departamentos\":[],\"filter-colectivos\":[],\"filter-plazos\":{\"opts\":[],\"start\":\"\",\"end\":\"\"}}"));
		urlParameters.add(new BasicNameValuePair("_buscador_WAR_sedeportlet_searchOrder", ","));
		urlParameters.add(new BasicNameValuePair("_buscador_WAR_sedeportlet_searchOrderChanged", "false"));
		urlParameters.add(new BasicNameValuePair("_buscador_WAR_sedeportlet_searchType", "avanzado"));
		urlParameters.add(new BasicNameValuePair("_buscador_WAR_sedeportlet_start", String.valueOf(start)));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		//Request
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = StringPool.BLANK;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(result.toString());
		JSONArray procs = jsonObject.getJSONArray("procs");
		return procs;
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
			String title = tramiteJSONObject.getString("denominacion");
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
