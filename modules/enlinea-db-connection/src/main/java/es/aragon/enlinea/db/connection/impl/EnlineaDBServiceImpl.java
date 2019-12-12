package es.aragon.enlinea.db.connection.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;
import es.aragon.enlinea.db.connection.api.EnlineaDBService;
import es.aragon.enlinea.db.connection.constants.SQLQueryConstants;
import es.aragon.enlinea.db.connection.dao.Document;
import es.aragon.enlinea.db.connection.dao.Procedure;
import es.aragon.enlinea.db.connection.util.ProcedureUtil;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true, 
	service = EnlineaDBService.class
)
public class EnlineaDBServiceImpl implements EnlineaDBService {
	
	private static final Log log = LogFactoryUtil.getLog(EnlineaDBServiceImpl.class);
	private static final Configuration configuration = ConfigurationFactoryUtil.getConfiguration(EnlineaDBServiceImpl.class.getClassLoader(), "portlet");
	
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;
	@Reference
	private CompanyLocalService companyLocalService;
	@Reference
	private GroupLocalService groupLocalService;
	@Reference
	private UserLocalService userLocalService;
	@Reference
	private CustomCategoryPropertyLocalService customCategoryPropertyLocalService;

	public List<Procedure> getProceduresForIndex() {
		HashMap<Long, Procedure> proceduresHM = new LinkedHashMap<>();
		Connection connection = connect();
		if(connection != null) {
			long companyId = 0;
			long groupId = 0;
			User user = null;
			try {
				companyId = companyLocalService.getCompanyByWebId(configuration.get("default.company")).getCompanyId();
				groupId = groupLocalService.getFriendlyURLGroup(companyId, configuration.get("default.group")).getGroupId();
				user = userLocalService.getDefaultUser(companyId);
			} catch (PortalException e) {
				log.error("Error obtaining companyId, groupId or userId", e);
			}
			if(companyId != 0 && groupId != 0 && Validator.isNotNull(user)) {
				loadProcedures(connection, companyId, groupId, user, proceduresHM);
				loadTopics(connection, groupId, proceduresHM);
				loadProfiles(connection, groupId, proceduresHM);
				loadOrganizations(connection, groupId, proceduresHM);
				loadTerms(groupId, user, proceduresHM);
			}
		}
		return new ArrayList<>(proceduresHM.values());
	}
	
	private void loadProcedures(Connection connection, long companyId, long groupId, User user, HashMap<Long, Procedure> proceduresHM) {
		String fields = StringUtil.merge(SQLQueryConstants.PROCEDURE_FIELDS);
		String departmentFields = StringUtil.merge(SQLQueryConstants.PROCEDURE_DEPARTMENT_FIELDS);
		String joinProcDepartment = "P.IDDEPARTAMENTORESPONSABLE = D.CID AND P.ID_ENTIDAD = D.IDENTIDAD";
		Set<String> friendlyURLs = new HashSet<>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery("SELECT " + fields + ", "+ departmentFields + " FROM " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_PROC P LEFT JOIN " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_DEPARTAMENTOS D ON " + joinProcDepartment);
			while(resultSet.next()) {
				Procedure procedure = ProcedureUtil.parseProcedureFromDB(resultSet, companyId, groupId, user, friendlyURLs);
				if(Validator.isNotNull(procedure)) {
					SearchContext searchContext = SearchContextFactory.getInstance(
							new long[0], new String[0], new HashMap<>(), 
							companyId, StringPool.BLANK, null, user.getLocale(),
							groupId, user.getTimeZone(), user.getUserId());
					Procedure previousVersion = getProcedure(searchContext, procedure.getProcedureId());
					if(Validator.isNotNull(previousVersion)) {
						procedure.setFriendlyURL(previousVersion.getFriendlyURL());
					}
					proceduresHM.put(procedure.getProcedureId(), procedure);
					friendlyURLs.add(procedure.getFriendlyURL());
				}
			}
		} catch (SQLException e) {
			log.error("Error executing getProceduresForIndex query", e);
		}
	}
	
	private void loadTopics(Connection connection, long groupId, HashMap<Long, Procedure> proceduresHM) {
		AssetVocabulary assetVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Temas");
		if(Validator.isNotNull(assetVocabulary)) {
			try {
				ResultSet resultSet = connection.createStatement().executeQuery(
						"SELECT ID_TEMA, SIGNATURA FROM " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_PROC_TEMA");
				while(resultSet.next()) {
					long procedureId = getProcedureId(resultSet);
					if(procedureId != 0) {
						Procedure procedure = proceduresHM.get(procedureId);
						if(Validator.isNotNull(procedure)) {
							long categoryId = findCategoryId(resultSet, assetVocabulary.getVocabularyId(), "OPENDATA_ID", "ID_TEMA");
							if(categoryId != 0) {
								AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(categoryId);
								if(Validator.isNotNull(assetCategory)) {
									if(!procedure.containsCategoryId(categoryId)) {
										procedure.addCategoryId(categoryId);
										procedure.addCategoryTitle(assetCategory.getTitle("es_ES", true));
									}
									for(AssetCategory category : getCategoryAncestors(assetCategory)) {
										if(!procedure.containsCategoryId(category.getCategoryId())) {
											procedure.addCategoryId(category.getCategoryId());
											procedure.addCategoryTitle(category.getTitle("es_ES", true));
										}
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				log.error("Error executing getTopics query", e);
			}
		} else {
			log.error("Vocabulary temas doesnt exist");
		}
	}
	
	private void loadProfiles(Connection connection, long groupId, HashMap<Long, Procedure> proceduresHM) {
		AssetVocabulary assetVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Perfiles");
		if(Validator.isNotNull(assetVocabulary)) {
			try {
				ResultSet resultSet = connection.createStatement().executeQuery(
						"SELECT ID_COLECTIVO, SIGNATURA FROM " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_PROC_COLECTIVO");
				while(resultSet.next()) {
					long procedureId = getProcedureId(resultSet);
					if(procedureId != 0) {
						Procedure procedure = proceduresHM.get(procedureId);
						if(Validator.isNotNull(procedure)) {
							long categoryId = findCategoryId(resultSet, assetVocabulary.getVocabularyId(), "OPENDATA_ID", "ID_COLECTIVO");
							if(categoryId != 0) {
								AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(categoryId);
								if(Validator.isNotNull(assetCategory)) {
									if(!procedure.containsCategoryId(categoryId)) {
										procedure.addCategoryId(categoryId);
										procedure.addCategoryTitle(assetCategory.getTitle("es_ES", true));
									}
									for(AssetCategory category : getCategoryAncestors(assetCategory)) {
										if(!procedure.containsCategoryId(category.getCategoryId())) {
											procedure.addCategoryId(category.getCategoryId());
											procedure.addCategoryTitle(category.getTitle("es_ES", true));
										}
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				log.error("Error executing getProfiles query", e);
			}
		} else {
			log.error("Vocabulary perfiles doesnt exist");
		}
	}
	
	private void loadOrganizations(Connection connection, long groupId, HashMap<Long, Procedure> proceduresHM) {
		AssetVocabulary assetVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Organismos");
		if(Validator.isNotNull(assetVocabulary)) {
			for(Procedure procedure : proceduresHM.values()) {
				String department = procedure.getResponsibleDepartment();
				if(Validator.isNotNull(department) && !department.isEmpty()) {
					DynamicQuery dynamicQuery = assetCategoryLocalService.dynamicQuery()
							.add(RestrictionsFactoryUtil.like("name", "%" + department))
							.add(RestrictionsFactoryUtil.eq("groupId", groupId))
							.add(RestrictionsFactoryUtil.eq("parentCategoryId", 0L));
					List<AssetCategory> assetCategories = assetCategoryLocalService.dynamicQuery(dynamicQuery);
					if(assetCategories.size() > 1) {
						log.info("Found more than one category in organismos matching " + department);
						log.info("Please check categories of organismos from procedure " + procedure.getName());
					}
					for(AssetCategory assetCategory : assetCategories) {
						if(!procedure.containsCategoryId(assetCategory.getCategoryId())) {
							procedure.addCategoryId(assetCategory.getCategoryId());
							procedure.addCategoryTitle(assetCategory.getTitle("es_ES", true));
						}
					}
				}
			}
		} else {
			log.error("Vocabulary organismos doesnt exist");
		}
	}
	
	private void loadTerms(long groupId, User user, HashMap<Long, Procedure> proceduresHM) {
		AssetVocabulary assetVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Plazos");
		if(Validator.isNull(assetVocabulary)) {
			assetVocabulary = createTermsVocabulary(groupId, user);
		}
		if(Validator.isNotNull(assetVocabulary)) {
			AssetCategory inTerm = assetCategoryLocalService.fetchCategory(
					groupId, 0L, "En plazo", assetVocabulary.getVocabularyId());
			AssetCategory outOfTime = assetCategoryLocalService.fetchCategory(
					groupId, 0L, "Fuera de plazo", assetVocabulary.getVocabularyId());
			if(Validator.isNotNull(inTerm) && Validator.isNotNull(outOfTime)) {
				for(Procedure procedure : proceduresHM.values()) {
					if(procedure.isInTerm()) {
						procedure.addCategoryId(inTerm.getCategoryId());
						procedure.addCategoryTitle(inTerm.getTitle("es_ES", true));
					} else {
						procedure.addCategoryId(outOfTime.getCategoryId());
						procedure.addCategoryTitle(outOfTime.getTitle("es_ES", true));
					}
				}
			} else {
				log.error("Terms vocabulary categories doesnt exist");
			}
		} else {
			log.error("Vocabulary terms doesnt exist");
		}
	}
	
	private AssetVocabulary createTermsVocabulary(long groupId, User user) {
		Map<Locale, String> titleMap = new HashMap<>();
		titleMap.put(LocaleUtil.SPAIN, "Plazos");
		titleMap.put(LocaleUtil.US, "Terms");
		try {
			AssetVocabulary assetVocabulary = assetVocabularyLocalService.addVocabulary(
					user.getUserId(), groupId, "Plazos", titleMap, titleMap, StringPool.BLANK, new ServiceContext());
			log.info("Terms vocabulary created");
			addTermCategories(assetVocabulary, user, groupId);
			return assetVocabulary;
		} catch(PortalException e) {
			log.error("Error creating terms vocabulary");
		}
		return null;
	}
	
	private void addTermCategories(AssetVocabulary assetVocabulary, User user, long groupId) {
		Map<Locale, String> inTermTitleMap = new HashMap<>();
		inTermTitleMap.put(LocaleUtil.SPAIN, "En plazo");
		inTermTitleMap.put(LocaleUtil.US, "In term");
		Map<Locale, String> outOfTimeTitleMap = new HashMap<>();
		outOfTimeTitleMap.put(LocaleUtil.SPAIN, "Fuera de plazo");
		outOfTimeTitleMap.put(LocaleUtil.US, "Out of time");
		try {
			assetCategoryLocalService.addCategory(
					user.getUserId(), groupId, 0L, inTermTitleMap, inTermTitleMap, 
					assetVocabulary.getVocabularyId(), new String[0], new ServiceContext());
			assetCategoryLocalService.addCategory(
					user.getUserId(), groupId, 0L, outOfTimeTitleMap, outOfTimeTitleMap, 
					assetVocabulary.getVocabularyId(), new String[0], new ServiceContext());
		} catch (PortalException e) {
			log.error("Error adding categories to terms vocabulary");
		}
	}
	
	private List<AssetCategory> getCategoryAncestors(AssetCategory assetCategory) {
		List<AssetCategory> assetCategories = new ArrayList<>();
		try {
			assetCategories = assetCategory.getAncestors();
		} catch (PortalException e) {
			log.error("Error getting " + assetCategory.getCategoryId() + " category ancestors", e);
		}
		return assetCategories;
	}
	
	private long getProcedureId(ResultSet resultSet) {
		try {
			return resultSet.getLong("SIGNATURA");
		} catch (SQLException e) {
			log.error("Error parsing procedureId", e);
		}
		return 0;
	}
	
	private long findCategoryId(ResultSet resultSet, long vocabularyId, String key, String column) {
		try {
			String text = resultSet.getString(column);
			List<CustomCategoryProperty> customCategoryProperties = 
					customCategoryPropertyLocalService.findByVocabularyIdAndKeyAndText(vocabularyId, key, text);
			if(!customCategoryProperties.isEmpty()) {
				return customCategoryProperties.get(0).getCategoryId();
			} else {
				log.info("categoryId from vocabulary " + vocabularyId + " with key " + key + " and text " + text + " not found");
			}
		} catch (SQLException e) {
			log.error("Error parsing " + column + " information", e);
		}
		return 0;
	}
	
	public List<Document> getDocumentsForIndex() {
		List<Document> documents = new ArrayList<>();
		Connection connection = connect();
		String documentFields = StringUtil.merge(SQLQueryConstants.PROCEDURE_DOCUMENT_FIELDS);
		if(connection != null) {
			try {
				ResultSet resultSet = connection.createStatement().executeQuery(
						"SELECT " + documentFields + " FROM " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_PROC_DOCUMENTO D");
				long companyId = companyLocalService.getCompanyByWebId(configuration.get("default.company")).getCompanyId();
				long groupId = groupLocalService.getFriendlyURLGroup(companyId, configuration.get("default.group")).getGroupId();
				while(resultSet.next()) {
					Document document = ProcedureUtil.parseDocumentForIndex(resultSet, companyId, groupId);
					if(Validator.isNotNull(document)) {
						documents.add(document);
					}
				}
			} catch (SQLException e) {
				log.error("Error executing getDocumentsForIndex query", e);
			} catch (PortalException e) {
				log.error("Error obtaining companyId or groupId", e);
			}
		}
		return documents;
	}
	
	public String getProceduresCount() {
		String proceduresCount = StringPool.BLANK;
		Connection connection = connect();
		if(connection != null) {
			try {
				ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_PROC P");
				while(resultSet.next()) {
					proceduresCount = resultSet.getString(1);
				}
			} catch (SQLException e) {
				log.error("Error executing getProceduresCount query", e);
				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", getClass());
				proceduresCount = LanguageUtil.get(resourceBundle, "error.error-executing-query");
			}
		} else {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", getClass());
			proceduresCount = LanguageUtil.get(resourceBundle, "error.unable-to-connect");
		}
		return proceduresCount;
	}
	
	public String getDocumentsCount() {
		String documentsCount = StringPool.BLANK;
		Connection connection = connect();
		if(connection != null) {
			try {
				ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM " + configuration.get("db.scheme") + ".DESFOR_V_SEDE_PROC_DOCUMENTO D");
				while(resultSet.next()) {
					documentsCount = resultSet.getString(1);
				}
			} catch (SQLException e) {
				log.error("Error executing getDocumentsCount query", e);
				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", getClass());
				documentsCount = LanguageUtil.get(resourceBundle, "error.error-executing-query");
			}
		} else {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", getClass());
			documentsCount = LanguageUtil.get(resourceBundle, "error.unable-to-connect");
		}
		return documentsCount;
	}
	
	public Procedure getProcedure(HttpServletRequest httpRequest, long procedureId) {
		SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
		Procedure procedure = getProcedure(searchContext, procedureId);
		if(Validator.isNotNull(procedure)) {
			procedure.setDocuments(getDocumentsByProcedure(httpRequest, procedureId));
		}
		return procedure;
	}
	
	private Procedure getProcedure(SearchContext searchContext, long procedureId) {
		searchContext.setStart(0);
		searchContext.setEnd(1);
		BooleanQuery booleanQuery = new BooleanQueryImpl();
		BooleanFilter filter = new BooleanFilter();
		filter.addRequiredTerm("procedureId", String.valueOf(procedureId));
		filter.addRequiredTerm("entryClassName", Procedure.class.getName());
		booleanQuery.setPreBooleanFilter(filter);
		try {
			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			if(Validator.isNotNull(hits) && hits.getLength() > 0) {
				return ProcedureUtil.parseProcedureFromES(hits.getDocs()[0]);
			} else {
				log.error("No procedure exists with procedureId " + procedureId);
			}
		} catch (SearchException e) {
			log.error("Error getting procedure by ES", e);
		}
		return null;
	}
	
	public Procedure getProcedure(HttpServletRequest httpRequest, String friendlyURL) {
		SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
		searchContext.setStart(0);
		searchContext.setEnd(1);
		BooleanQuery booleanQuery = new BooleanQueryImpl();
		BooleanFilter filter = new BooleanFilter();
		filter.addRequiredTerm("entryClassName", Procedure.class.getName());
		booleanQuery.setPreBooleanFilter(filter);
		booleanQuery.addRequiredTerm("friendlyURL", friendlyURL);
		try {
			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			if(Validator.isNotNull(hits) && hits.getLength() > 0) {
				Procedure procedure = ProcedureUtil.parseProcedureFromES(hits.getDocs()[0]);
				if(procedure.getFriendlyURL().equals(friendlyURL)) {
					procedure.setDocuments(getDocumentsByProcedure(httpRequest, procedure.getProcedureId()));
					return procedure;
				} else {
					log.error("No procedure exists with friendlyURL " + friendlyURL);
				}
			} else {
				log.error("No procedure exists with friendlyURL " + friendlyURL);
			}
		} catch (SearchException e) {
			log.error("Error getting procedure by ES", e);
		}
		return null;
	}
	
	public List<Procedure> getProceduresByTopic(HttpServletRequest httpRequest, long topicCategoryId, int size) {
		List<Procedure> procedures = new ArrayList<>();
		SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
		searchContext.setStart(0);
		searchContext.setEnd(size);
		searchContext.setSorts(new Sort("viewCount_String_sortable", true), new Sort("name_String_sortable", false));
		searchContext.setScoresThreshold(20);
		BooleanQuery booleanQuery = new BooleanQueryImpl();
		BooleanFilter filter = new BooleanFilter();
		filter.addRequiredTerm("entryClassName", Procedure.class.getName());
		filter.addRequiredTerm("assetCategoryIds", String.valueOf(topicCategoryId));
		booleanQuery.setPreBooleanFilter(filter);
		try {
			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			if(Validator.isNotNull(hits) && hits.getLength() > 0) {
				for(com.liferay.portal.kernel.search.Document doc : hits.getDocs()) {
					Procedure procedure = ProcedureUtil.parseProcedureFromES(doc);
					procedure.setDocuments(getDocumentsByProcedure(httpRequest, procedure.getProcedureId()));
					procedures.add(procedure);
				}
			}
		} catch (SearchException e) {
			log.error("Error searching procedures by topic in elastic", e);
		}
		return procedures;
	}
	
	public List<Procedure> getRelatedProcedures(HttpServletRequest httpRequest, long procedureId, String keywords) {
		List<Procedure> procedures = new ArrayList<>();
		SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
		searchContext.setStart(0);
		searchContext.setEnd(5);
		searchContext.setSorts(new Sort("_score", Sort.SCORE_TYPE, true), new Sort("name_String_sortable", false));
		searchContext.setScoresThreshold(20);
		BooleanQuery booleanQuery = new BooleanQueryImpl();
		if(Validator.isNotNull(keywords) && !keywords.isEmpty()) {
			booleanQuery.addRequiredTerm("name", keywords);
		}
		BooleanFilter filter = new BooleanFilter();
		filter.addTerm("procedureId", String.valueOf(procedureId), BooleanClauseOccur.MUST_NOT);
		filter.addRequiredTerm("entryClassName", Procedure.class.getName());
		booleanQuery.setPreBooleanFilter(filter);
		try {
			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			if(Validator.isNotNull(hits)) {
				for(com.liferay.portal.kernel.search.Document document : hits.getDocs()) {
					Procedure procedure = new Procedure();
					procedure.setProcedureId(GetterUtil.getLong(document.get("procedureId"), 0));
					procedure.setName(GetterUtil.getString(document.get("name"), ""));
					procedures.add(procedure);
				}
			}
		} catch (SearchException e) {
			log.error("Error searching related procedures", e);
		}
		
		return procedures;
	}
	
	private List<Document> getDocumentsByProcedure(HttpServletRequest httpRequest, long procedureId) {
		List<Document> documents = new ArrayList<>();
		SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
		searchContext.setStart(0);
		searchContext.setEnd(10);
		searchContext.setSorts(new Sort("order_Number_sortable", false), new Sort("name_String_sortable", false));
		searchContext.setScoresThreshold(20);
		BooleanQuery booleanQuery = new BooleanQueryImpl();
		BooleanFilter filter = new BooleanFilter();
		filter.addRequiredTerm("procedureId", String.valueOf(procedureId));
		filter.addRequiredTerm("entryClassName", Document.class.getName());
		booleanQuery.setPreBooleanFilter(filter);
		try {
			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			for(com.liferay.portal.kernel.search.Document doc : hits.getDocs()) {
				Document document = ProcedureUtil.parseDocumentFromES(doc);
				documents.add(document);
			}
		} catch (SearchException e) {
			log.error("Error searching documents in elastic", e);
		}
		return documents;
	}
	
	private Connection connect() {
		Connection connection = null;
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
        	log.error("NO HA SIDO POSIBLE CARGAR EL DRIVER DE ORACLE", e);
            return connection;
        }
		String connectionURL = configuration.get("db.connection");
    	String user = configuration.get("db.user");
    	String pass = configuration.get("db.pass");
    	if(Validator.isNotNull(connectionURL) && Validator.isNotNull(user) && Validator.isNotNull(pass)) {
    		try {
    			connection = DriverManager.getConnection(connectionURL, user, pass);
    		} catch (SQLException e) {
    			log.error("Error opening connection with enlinea database (Oracle)", e);
    		}
    	}
    	return connection;
	}
	
}
