package es.aragon.enlinea.admin.web.search;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.db.connection.api.EnlineaDBService;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
@Component(
    immediate = true,
    service = Indexer.class
)
public class ProcedureIndexer extends BaseIndexer<Procedure> {
	
	public ProcedureIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}
	
	@Override
	public String getClassName() {
		return Procedure.class.getName();
	}
	
	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {
		super.postProcessSearchQuery(searchQuery, fullQueryBooleanFilter, searchContext);
	}
	
	@Override
	protected void doDelete(Procedure procedure) throws Exception {
		deleteDocument(procedure.getCompanyId(), procedure.getProcedureId());
	}
	
	@Override
	protected Document doGetDocument(Procedure procedure) {
		Document document = newDocument();
		document.addUID(Procedure.class.getName(), procedure.getProcedureId());
		document.addKeyword(Field.ENTRY_CLASS_NAME, Procedure.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, procedure.getProcedureId());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, procedure.getCategoryIds().stream().mapToLong(l -> l).toArray());
		document.addKeyword(Field.ASSET_CATEGORY_TITLES, procedure.getCategoryTitles().stream().toArray(String[]::new));
		document.addKeyword(Field.COMPANY_ID, procedure.getCompanyId());
		document.addKeyword(Field.GROUP_ID, procedure.getGroupId());
		document.addDate(Field.MODIFIED_DATE, procedure.getModifiedDate());
		document.addKeyword(Field.USER_ID, procedure.getUserId());
		document.addKeyword(Field.USER_NAME, procedure.getUserName(), true);
		document.addText(Field.TITLE, procedure.getName());
		document.addText(Field.CONTENT, procedure.getContent());
		document.addText(Field.DESCRIPTION, procedure.getDescription());
		document.addKeyword("procedureId", procedure.getProcedureId());
		document.addTextSortable("name", procedure.getName());
		document.addText("keywords", procedure.getKeywords());
		document.addText("description", procedure.getDescription());
		document.addText("applicant", procedure.getApplicant());
		document.addText("requirementsAndObservations", procedure.getRequirementsAndObservations());
		document.addText("documentation", procedure.getDocumentation());
		document.addText("normative", procedure.getNormative());
		document.addDateSortable("fromDate", procedure.getFromDate());
		document.addDateSortable("toDate", procedure.getToDate());
		document.addText("resolutionTime", procedure.getResolutionTime());
		document.addNumber("inLevel", procedure.getInLevel());
		document.addText("onlineURL", procedure.getOnlineURL());
		document.addText("responsibleDepartment", procedure.getResponsibleDepartment());
		document.addText("responsibleDepartmentURL", procedure.getResponsibleDepartmentURL());
		document.addKeyword("friendlyURL", procedure.getFriendlyURL());
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(Procedure.class.getName(), procedure.getProcedureId());
		long viewCount = 0;
		if(Validator.isNotNull(assetEntry)) {
			viewCount = assetEntry.getViewCount();
		}
		document.addKeywordSortable("viewCount", String.valueOf(viewCount));
		document.addKeyword("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));
		List<Long> roles = new ArrayList<>();
		Role guestRole = roleLocalService.fetchRole(procedure.getCompanyId(), RoleConstants.GUEST);
		if(Validator.isNotNull(guestRole)) {
			roles.add(guestRole.getRoleId());
		}
		Role ownerRole = roleLocalService.fetchRole(procedure.getCompanyId(), RoleConstants.OWNER);
		if(Validator.isNotNull(ownerRole)) {
			roles.add(ownerRole.getRoleId());
		}
		document.addKeyword(Field.ROLE_ID, roles.stream().mapToLong(l -> l).toArray());
		return document;
	}
	
	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		return createSummary(document);
	}
	
	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Procedure procedure = enlineaDBService.getProcedure(null, classPK);
		if(Validator.isNotNull(procedure)) {
			doReindex(procedure);
		}
	}
	
	@Override
	protected void doReindex(String[] ids) throws Exception {
		log.info("Started procedures reindex process");
		List<Procedure> procedures = enlineaDBService.getProceduresForIndex();
		if(!procedures.isEmpty()) {
			doDeleteAll();
			List<Document> documents = new ArrayList<>();
			for(Procedure procedure : procedures) {
				documents.add(doGetDocument(procedure));
			}
			IndexWriterHelperUtil.updateDocuments(getSearchEngineId(), procedures.get(0).getCompanyId(), documents, true);
			try {
				long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
				long groupId = groupLocalService.getFriendlyURLGroup(companyId, GroupConstants.CONTROL_PANEL_FRIENDLY_URL).getGroupId();
				long plid = layoutLocalService.getDefaultPlid(groupId);
				PortletPreferences preferences = portletPreferencesLocalService.getPreferences(companyId, 0, PortletKeys.PREFS_OWNER_TYPE_LAYOUT, plid, EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET);
				SimpleDateFormat df = new SimpleDateFormat("HH:mm, dd MMMM yyyy", LocaleUtil.fromLanguageId("es_ES"));
				preferences.setValue("lastProceduresReindex", df.format(new Date()));
				preferences.store();
			} catch(PortalException e) {
				log.error("Error updating the procedures reindex date", e);
			}
			log.info("Finished procedures reindex process");
		} else {
			log.error("No procedures to reindex");
		}
	}
	
	@Override
	protected void doReindex(Procedure procedure) throws SearchException {
		IndexWriterHelperUtil.updateDocument(
				getSearchEngineId(),
				procedure.getCompanyId(),
				doGetDocument(procedure),
				true
		);
	}

	public void doDeleteAll() {
		try {
			long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
			long groupId = groupLocalService.getFriendlyURLGroup(companyId, GroupConstants.CONTROL_PANEL_FRIENDLY_URL).getGroupId();
			long userId = groupLocalService.getFriendlyURLGroup(companyId, GroupConstants.CONTROL_PANEL_FRIENDLY_URL).getCreatorUserId();
			SearchContext searchContext = SearchContextFactory.getInstance(
					new long[0], new String[0], new HashMap<>(), 
					companyId, StringPool.BLANK, null, LocaleUtil.getDefault(),
					groupId, TimeZoneUtil.getDefault(), userId);
			BooleanQuery booleanQuery = new BooleanQueryImpl();
			booleanQuery.addRequiredTerm("entryClassName", Procedure.class.getName());
			try {
				Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
				while(hits.getLength() > 0) {
					for(Document doc : hits.getDocs()) {
						deleteDocument(doc, companyId);
					}
					hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
				}
			} catch(SearchException e) {
				log.error("Error searching procedures for delete");	
			}
		} catch(PortalException e) {
			log.error("Error obtaining parameters for deleting all procedures", e);
		}
	}
	
	private void deleteDocument(Document doc, long companyId) {
		try {
			delete(GetterUtil.getLong(doc.get(Field.COMPANY_ID), companyId), doc.getUID());
		} catch (SearchException e) {
			log.error("Error deleting procedure with id " + doc.get("procedureId"));
		}
	}
	
	private static Log log = LogFactoryUtil.getLog(ProcedureIndexer.class);
	
	@Reference
	private AssetEntryLocalService assetEntryLocalService;
	@Reference
	private CompanyLocalService companyLocalService;
	@Reference
	private GroupLocalService groupLocalService;
	@Reference
	private EnlineaDBService enlineaDBService;
	@Reference
	private LayoutLocalService layoutLocalService;
	@Reference
	private PortletPreferencesLocalService portletPreferencesLocalService;
	@Reference
	private RoleLocalService roleLocalService;

}
