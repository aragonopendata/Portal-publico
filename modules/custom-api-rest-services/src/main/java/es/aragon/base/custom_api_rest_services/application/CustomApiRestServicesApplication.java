package es.aragon.base.custom_api_rest_services.application;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.semaphore.model.Semaphore;
import es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil;

/**
 * @author migarcia
 * Create API REST to dowload csv and json with information journalArticle from URL
 * CSV-local: http://localhost:8080/o/custom-api-rest-services/contents/list-urls.csv?cod_siu=ORG11335&tema=305108&agregador=si&desde=20190710&hasta=20190717
 * JSON-local: http://localhost:8080/o/custom-api-rest-services/contents/list-urls.json
 */

@ApplicationPath("/contents") 
@Component(immediate = true, service = Application.class)
public class CustomApiRestServicesApplication extends Application {
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	/**
	 * Create file csv with journal articles status = 0; groupId= 20127; template= contenido-final
	 * @param organization optional value. Looking for by name organization
	 * @param cod_siu optional value. Looking for by cod_siu organization
	 * @param topic optional value. Looking for by name topic
	 * @param visibility optional value. Value: Si o No
	 * @param fromDate optional value. Format ddMMYYY
	 * @param toDate optional value. Formay ddMMYYY
	 * @return string
	 */
	@GET
	@Path("/list-urls.csv")
	@Produces("text/csv; charset=UTF-8")
	public Response getJournalArticle(
			@QueryParam("organismo") String organization,
			@QueryParam("cod_siu") String cod_siu,
			@QueryParam("tema") String topic,
			@QueryParam("agregador") String visibility,
			@QueryParam("desde") String fromDate,
			@QueryParam("hasta") String toDate
			){

		Semaphore semaphore = SemaphoreLocalServiceUtil.addSemaphore(GROUP_ID, "custom-api-rest-services", 3);
		if(SemaphoreLocalServiceUtil.semaphoreWait(semaphore)) {
			try {
				_log.info("INICIANDO PROCESO DE GENERACION DE CSV...");
				long start = System.currentTimeMillis();
				StringBundler sb = new StringBundler();
				//Print header title
				sb.append(Arrays.stream(COLUMN_NAMES).map(this::getCSVFormattedValue).collect(Collectors.joining(CSV_SEPARATOR)));
				
				sb.append(CharPool.NEW_LINE);
				
				//Get list ids asset entries with applied filters
				List<String> listAssetEntryIds = getJournalArticles(organization, cod_siu, topic,visibility, fromDate, toDate);
				
				for (String articleId : listAssetEntryIds ) {
					try {
						JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(GROUP_ID, articleId, 0);
						buildJournalArticleInfo(organization, cod_siu, topic, visibility, sb, journalArticle);
					} catch (PortalException e) {
						_log.error("NO SE HA ENCONTRADO EL JOURNAL", e);
					}
		
					sb.setIndex(sb.index() - 1);
					sb.append(CharPool.NEW_LINE);
				}
				long end = System.currentTimeMillis();
				_log.info("FIN DE CREACION DEL FICHERO CSV");
				_log.info("TIEMPO TOTAL PARA GENERARLO: "+ (end - start));
				return Response.ok(sb.toString()).build();
			} finally {
				SemaphoreLocalServiceUtil.semaphoreSignal(semaphore);
			}
		}
		return Response.serverError().status(503).entity("Server is busy, try it later").build();
	}
	/**
	 * @param organization
	 * @param cod_siu
	 * @param topic
	 * @param visibility
	 * @param sb
	 * @param journalArticle
	 */
	private void buildJournalArticleInfo(String organization, String cod_siu, String topic, String visibility,
			StringBundler sb, JournalArticle journalArticle) {
		try {
			LinkedList<String>	listInformation = getListInformation(journalArticle, organization, cod_siu, topic, visibility);
			if(listInformation!=null) {
				for (int i = 0; i < listInformation.size(); i++) {
					sb.append(getCSVFormattedValue(String.valueOf(listInformation.get(i)))).append(CSV_SEPARATOR);
				}
			}
		} catch (PortalException e) {
			_log.error("ERROR AL GENERAR LA LISTA CON INFORMACION DEL JOURNAL", e);
		}
	}
	
	/**
	 * Create file json with journal articles status = 0; groupId= 20127; template= contenido-final
	 * @param organization optional value. Looking for by name organization
	 * @param cod_siu optional value. Looking for by cod_siu organization
	 * @param topic optional value. Looking for by name topic
	 * @param visibility optional value. Value: Si o No
	 * @param fromDate optional value. Format ddMMYYY
	 * @param toDate optional value. Formay ddMMYYY
	 * @return string
	 */
	@GET
	@Path("/list-urls.json")
	@Produces("{application/json}")
	public Response getJournalArticleJson(
			@QueryParam("organismo") String organization,
			@QueryParam("cod_siu") String cod_siu,
			@QueryParam("tema") String topic,
			@QueryParam("agregador") String visibility,
			@QueryParam("desde") String fromDate,
			@QueryParam("hasta") String toDate
			){
		Semaphore semaphore = SemaphoreLocalServiceUtil.addSemaphore(GROUP_ID, "custom-api-rest-services", 3);
		if(SemaphoreLocalServiceUtil.semaphoreWait(semaphore)) {
			try {
				_log.info("INICIANDO PROCESO DE GENERACION DE JSON...");
				long start = System.currentTimeMillis();
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
				JSONArray jsonArray1 = JSONFactoryUtil.createJSONArray();
				for (int i = 0; i < COLUMN_NAMES.length; i++) {
					jsonArray1.put(COLUMN_NAMES[i]);
				}
				jsonArray.put(jsonArray1);
				try {
					List<String> listArticleId = getJournalArticles(organization, cod_siu, topic, visibility, fromDate, toDate);
					if(!listArticleId.isEmpty()) {
						for ( String articleId : listArticleId ) {
							JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(GROUP_ID, articleId, 0);
							if (Validator.isNotNull(journalArticle)){
								JSONArray jsonArray2 = JSONFactoryUtil.createJSONArray();
								LinkedList<String> listInformation = getListInformation(journalArticle, organization, cod_siu, topic, visibility);
								if(listInformation!=null) {
									for (int i = 0; i < listInformation.size(); i++) {
										jsonArray2.put(listInformation.get(i));
									}
									jsonArray.put(jsonArray2);
								}
							}
						}
					}
				}catch (Exception e) {
					_log.error("ERROR CREATE FILE JSON " + e.toString(), e);
				}
				long end = System.currentTimeMillis();
				long total = end - start;
				_log.info("FIN DE CREACION DEL FICHERO JSON");
				_log.info("TIEMPO TOTAL PARA GENERARLO: "+total);
				return Response.ok(jsonArray.toJSONString()).build();
			} finally {
				SemaphoreLocalServiceUtil.semaphoreSignal(semaphore);
			}
		}
		return Response.serverError().status(503).entity("Server is busy, try it later").build();
	}

	/**
	 * Formatted csv with double quote
	 * @param information receive from journal
	 * @return string formatted
	 */
	protected String getCSVFormattedValue(String value) {
		StringBundler sb = new StringBundler(3);
		sb.append(CharPool.QUOTE);
		sb.append(StringUtil.replace(value, CharPool.QUOTE, StringPool.DOUBLE_QUOTE));
		sb.append(CharPool.QUOTE);
		return sb.toString();
	}
	/**
	 * Filter journal article by max version, structure and status approved
	 * @param groupId
	 * @return list Ids journal
	 */
	public List<String> getJournalArticles(String organization, String cod_siu, String topic, String visibility, String fromDate, String toDate) {
		List<String> listJournalArticleId = new ArrayList<>();
		//String otherFilter = StringPool.BLANK;
		String filterDate = StringPool.BLANK;
		//Escape any code that can inyect something
		if(Validator.isNotNull(organization)) {
			organization = organization.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit} ]", "").trim();
		}
		if(Validator.isNotNull(cod_siu)) {
			cod_siu = cod_siu.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit} ]", "").trim();
		}
		if(Validator.isNotNull(topic)) {
			topic = topic.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit} ]", "").trim();
		}
		if(Validator.isNotNull(visibility)) {
			visibility = visibility.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit} ]", "").trim();
		}
		if(Validator.isNotNull(fromDate)) {
			fromDate = fromDate.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit} ]", "").trim();
			fromDate = formattedDate(fromDate, "00:00:00");
		}
		if(Validator.isNotNull(toDate)) {
			toDate = toDate.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit} ]", "").trim();
			toDate = formattedDate(toDate, "23:59:59");
		}
		try {
			_log.info("LEYENDO JOURNALS...");
			Connection mySQLConnection = DataAccess.getConnection();
			Statement stmt = mySQLConnection.createStatement();
			DDMStructure ddmStructure = fetchStructureByName(AragonUtilitiesConstant.STRUCTURE_NAME_CONTENIDO_FINAL, GROUP_ID);
			//long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class.getName());
			String ddmStructureKey = ddmStructure.getStructureKey();
			/*if (Validator.isNotNull(organization)) {
				
				AssetVocabulary vocabularyOrganization = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
				DynamicQuery query = AssetCategoryLocalServiceUtil.dynamicQuery();;
				query.add(RestrictionsFactoryUtil.eq("groupId", GROUP_ID));
				query.add(RestrictionsFactoryUtil.eq("vocabularyId", vocabularyOrganization.getVocabularyId()));
				query.add(RestrictionsFactoryUtil.ilike("title", "%"+organization+"%"));
				List<AssetCategory> results = AssetCategoryLocalServiceUtil.dynamicQuery(query, -1, -1);
				//filterDate = filterDate+ " AND assetcategory.title in ( "+results+") ";
				String categoryIds = StringPool.BLANK;
				for(AssetCategory categoryTitle : results ){
					categoryIds =categoryIds + categoryTitle.getCategoryId();
				}
				otherFilter = otherFilter+" assetCategoryId in (" + categoryIds + ") AND ";

				//otherFilter = "assetCategoryId = " + organization + " AND";
				 
			}*/
			/*if (Validator.isNotNull(cod_siu)) {
				//filterDate = " AND eab_ccp_customcategoryproperty.key_ LIKE 'COD_SIU' AND eab_ccp_customcategoryproperty.text_ LIKE '" + cod_siu + "' ";
			}*/
			/*if(Validator.isNotNull(topic)) {
				AssetVocabulary vocabularyTopic = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
				DynamicQuery query = AssetCategoryLocalServiceUtil.dynamicQuery();;
				query.add(RestrictionsFactoryUtil.eq("groupId", GROUP_ID));
				query.add(RestrictionsFactoryUtil.eq("vocabularyId", vocabularyTopic.getVocabularyId()));
				query.add(RestrictionsFactoryUtil.ilike("title", "%"+topic+"%"));
				List<AssetCategory> results = AssetCategoryLocalServiceUtil.dynamicQuery(query, -1, -1);
				for(AssetCategory categoryTitle : results ){
					//filterDate = filterDate+ " AND assetcategory.title like "+categoryTitle.getTitle(LocaleUtil.SPAIN)+" ";
					//otherFilter = otherFilter+" assetCategoryId = " + categoryTitle + " AND ";
				}
				//otherFilter = otherFilter+" assetCategoryId = " + categoryTitle + " AND ";
			}*/
			/*if(Validator.isNotNull(visibility)) {
				AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_VISIBILITY_IN_NAVIGATION_ES);
				AssetCategory assetCatetegoryNotVisibility = AssetCategoryLocalServiceUtil.fetchCategory(GROUP_ID, 0, "No visible" , vocabulary.getVocabularyId());
				if(visibility.equals("no")) {
					otherFilter = otherFilter + "assetCategoryId = " + assetCatetegoryNotVisibility.getCategoryId() + " AND";
				} else if(visibility.equals("si")) {
					otherFilter = otherFilter + "acr1.assetEntryId not in (SELECT acr2.assetEntryId FROM assetentryassetcategoryrel acr2 WHERE acr2.assetCategoryId ="+assetCatetegoryNotVisibility.getCategoryId()+") AND";
				}
			}*/
			if(Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
				filterDate = " AND statusDate >= '"+fromDate+"' AND statusDate <= '"+toDate+"' ";
			} else if(Validator.isNotNull(fromDate)) {
				filterDate = " AND  statusDate >= '"+fromDate+"' ";
			} else if(Validator.isNotNull(toDate)) {
				filterDate = " AND statusDate <= '"+toDate+"' ";
			}
			String query = "SELECT articleId " 
					+ "FROM journalarticle "
					+ "WHERE groupId = " + GROUP_ID + " "
						+ "AND status = " + WorkflowConstants.STATUS_APPROVED + " "
						+ "AND DDMStructureKey = " + ddmStructureKey + " "
						+ filterDate
						+ "AND (articleId,version) IN "
							+ "( SELECT articleId, MAX(version) "
							+ "FROM journalarticle "
							+ "GROUP BY articleId "
						+ ");";
			_log.info("sql: " + query);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				if(!listJournalArticleId.contains(resultSet.getString("articleId"))) {
					listJournalArticleId.add(resultSet.getString("articleId"));
				}
			}
			_log.info("total results: "+listJournalArticleId.size());
			stmt.close();
			mySQLConnection.close();
			_log.info("...FIN DE LA LECTURA DE JOURNALS");
		} catch (Exception e) {
			_log.error("NO SE HA PODIDO ESTABLECER LA CONEXION SQL O HAY UN ERROR EN LA CONSULTA", e);
		}
		return listJournalArticleId;
	}
	/**
	 * Get structure by name
	 * @param name of the structure that we are looking for 
	 * @param groupId
	 * @return object structure
	 */
	public DDMStructure fetchStructureByName(String name, long groupId) {
		List<DDMStructure> ddmStructures = _ddmStructureLocalService.getStructures(groupId);
		try {
			Locale locale = PortalUtil.getSiteDefaultLocale(groupId);
			if(Validator.isNotNull(ddmStructures)) {
				for(DDMStructure ddmStructure : ddmStructures) {
					if(name.equalsIgnoreCase(ddmStructure.getName(locale))) {
						return ddmStructure;
					}
				}
			}
		} catch (Exception e) {
			_log.error(e.toString());
		}
		return null;
	}
	/**
	 * Create string formatted with information from journal
	 * @param category with which the journal is categorized
	 * @param categories value information
	 * @param actual count current category number
	 * @param numMax maxium number categories that appear
	 * @return String formatted with categories
	 */
	private String formattedInformation(AssetCategory assetCategory, String categories,int actual, int numMax) {
		if (actual <= numMax ) {
			if (categories.equals(StringPool.BLANK )) {
				categories = assetCategory.getTitle(LocaleUtil.SPAIN);
			}else {
				categories =categories + StringPool.COMMA + assetCategory.getTitle(LocaleUtil.SPAIN);
			}
		}
		return categories;
	}
	/**
	 * Get information and create list with all information by vocabulary and journal
	 * @param journalArticle to get information
	 * @param visibility 
	 * @param topic 
	 * @param cod_siu2 
	 * @param organization 
	 * @return list with information by journal
	 * @throws PortalException
	 */
	private LinkedList<String> getListInformation(JournalArticle journalArticle, String organization, String cod_siu2, String topic, String visibility) throws PortalException{
		//Get vocabularies
		AssetVocabulary vocabularyTopic = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
		AssetVocabulary vocabularyProfile = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES);
		AssetVocabulary vocabularyProcedures = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_ES);
		AssetVocabulary vocabularyDocumentType = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES);
		AssetVocabulary vocabularyMunicipalities = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES);
		AssetVocabulary vocabularyOrganization = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
		AssetVocabulary vocabularyVisibility = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(GROUP_ID, AragonUtilitiesConstant.VOCABULARY_NAME_VISIBILITY_IN_NAVIGATION_ES);
		
		//Declaration variables
		String categoriesOrganizationsJournal = StringPool.BLANK;
		String categoriesTopicsJournal = StringPool.BLANK;
		String categoriesProfilesJournal = StringPool.BLANK;
		String categoriesProceduresJournal = StringPool.BLANK;
		String categoriesDocumentsTypeJournal = StringPool.BLANK;
		String categoriesMunicipalitiesJournal = StringPool.BLANK;
		String categoriesVisibilityJournal = "SI";
		String cod_siu = StringPool.BLANK;
		String datePublishString ="";
		String url = "";
		String title = "";
		String description = "";
		String dateCreateString="";
		String dateModifyString="";
		Date createDate = journalArticle.getCreateDate();
		Date statusDate = journalArticle.getStatusDate();
		Date displayDate = journalArticle.getDisplayDate();
		LinkedList<String> listInformation = new LinkedList<>();
		try {
			//Create journal's information
			url = "www.aragon.es/-/"+String.valueOf(journalArticle.getUrlTitle(LocaleUtil.SPAIN));
			title =  removeHtmlTags(String.valueOf(journalArticle.getTitle()));
			description = HtmlUtil.stripHtml(removeHtmlTags(String.valueOf(journalArticle.getDescription())));
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        format.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
	        if (Validator.isNotNull(createDate)) {
	        	dateCreateString=format.format(createDate);
	        }
	        if( Validator.isNotNull(statusDate)) {
	        	dateModifyString = format.format(statusDate);
	        }
	        if(Validator.isNotNull(displayDate)) {
	        	datePublishString = format.format(displayDate);
	        }
		} catch (Exception e) {
			_log.error("ERROR AL RECOGER LA INFORMACION DEL JOURNAL", e);
		}
		//Get journal's category
		List<AssetCategory> journalCategories = _assetCategoryLocalService.getCategories(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
		if(Validator.isNotNull(journalCategories)) {
			int nMunicipalities = 0, nProfiles= 0, nTopics = 0, nProcedures = 0, nDocumentType = 0, nOrganization = 0;
			for (AssetCategory assetcategory :  journalCategories) {
				//Topic
				if(Validator.isNotNull(vocabularyTopic)) {
					if(assetcategory.getVocabularyId() == vocabularyTopic.getVocabularyId()) {
						nTopics++;
						categoriesTopicsJournal = formattedInformation(assetcategory, categoriesTopicsJournal,nTopics, 4);
					}
				}
				//Profile
				if(Validator.isNotNull(vocabularyProfile)) {
					if(assetcategory.getVocabularyId() == vocabularyProfile.getVocabularyId()) {
						nProfiles++;
						categoriesProfilesJournal = formattedInformation(assetcategory, categoriesProfilesJournal,nProfiles, 2);
					}
				}
				//Procedures
				if(Validator.isNotNull(vocabularyProcedures)) {
					if(assetcategory.getVocabularyId() == vocabularyProcedures.getVocabularyId()) {
						nProcedures++;
						categoriesProceduresJournal = formattedInformation(assetcategory, categoriesProceduresJournal,nProcedures,4);
					}
				}
				//Documents-type
				if(Validator.isNotNull(vocabularyDocumentType)) {
					if(assetcategory.getVocabularyId() == vocabularyDocumentType.getVocabularyId()) {
						nDocumentType++;
						categoriesDocumentsTypeJournal = formattedInformation(assetcategory, categoriesDocumentsTypeJournal,nDocumentType,1);
					}
				}
				//Municipalities
				if(Validator.isNotNull(vocabularyMunicipalities)) {
					if(assetcategory.getVocabularyId() == vocabularyMunicipalities.getVocabularyId()) {
						nMunicipalities++;
						categoriesMunicipalitiesJournal = formattedInformation(assetcategory, categoriesMunicipalitiesJournal,nMunicipalities,2);
					}
				}
				//Visibility
				if(Validator.isNotNull(vocabularyVisibility)) {
					if(assetcategory.getVocabularyId() == vocabularyVisibility.getVocabularyId()) {
						categoriesVisibilityJournal = "NO";
					}
				}
				//Organization
				if(Validator.isNotNull(vocabularyOrganization)) {
					if(assetcategory.getVocabularyId() == vocabularyOrganization.getVocabularyId()) {
						nOrganization++;
						categoriesOrganizationsJournal = formattedInformation(assetcategory, categoriesOrganizationsJournal,nOrganization,2);
						//COD_SIU
						if(nOrganization <= 2 ) {
							CustomCategoryProperty customCategoryProperty = CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(assetcategory.getCategoryId(), "COD_SIU");
							if(Validator.isNotNull(customCategoryProperty)) {
								if (cod_siu.equals(StringPool.BLANK)) {
									cod_siu = customCategoryProperty.getText();
								}else {
									cod_siu = cod_siu+StringPool.COMMA + customCategoryProperty.getText();
								}
							}
						}
					}
				}
			}
		}
		//create linkList with journal's information
		
		if(Validator.isNotNull(cod_siu2)) {
			if(!cod_siu.contains(cod_siu2)) {
				return null;
			}
		}
		
		if(Validator.isNotNull(topic)) {
			if(!categoriesTopicsJournal.toLowerCase().contains(topic.toLowerCase())) {
				return null;
			}
		}
		
		if(Validator.isNotNull(organization)) {
			if(!categoriesOrganizationsJournal.toLowerCase().contains(organization.toLowerCase())) {
				return null;
			}
		}
		
		if(Validator.isNotNull(visibility)) {
			if(!categoriesVisibilityJournal.toLowerCase().contains(visibility.toLowerCase())) {
				return null;
			}
		}
		
		listInformation.add(title);
		listInformation.add(description);
		listInformation.add(url);
		listInformation.add(dateCreateString);
		listInformation.add(dateModifyString);
		listInformation.add(datePublishString);
		listInformation.add(categoriesTopicsJournal);
		listInformation.add(categoriesOrganizationsJournal);
		listInformation.add(cod_siu);
		listInformation.add(categoriesProfilesJournal);
		listInformation.add(categoriesProceduresJournal);
		listInformation.add(categoriesDocumentsTypeJournal);
		listInformation.add(categoriesMunicipalitiesJournal);
		listInformation.add(categoriesVisibilityJournal);
		
		return listInformation;
		
	}
	/**
	 * Remove html tag from title
	 * @param html
	 * @return
	 */
	public static String removeHtmlTags(String html){
		html = HtmlUtil.getHtml().extractText(html);
		return html;
	}
	/**
	 * Formatted date
	 * @param date
	 * @param hour
	 * @return
	 */
	public String formattedDate(String date, String hour) {
		if(Validator.isNull(date) || date.length()!=8) {
			return null;
		}
		String year = date.substring(0,4);
		String month = date.substring(4,6);
		String day = date.substring(6);
		date = year+StringPool.DASH + month + StringPool.DASH + day + StringPool.SPACE + hour;
		return date;
	}
	
	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(CustomApiRestServicesApplication.class);
	protected static final String[] COLUMN_NAMES = { "Titulo", "Resumen", "URL","Fecha creacion", "Fecha de publicacion","Fecha de publicacion programada","Temas", "Organismos", "COD_SIU", "Perfiles", "Tramites", "Tipos de documento", "Lugar", "Agregador"};
	public static final String CSV_SEPARATOR = ";";
	public static final long GROUP_ID = 20127;

}