package es.aragon.enlinea.admin.web.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;

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
import es.aragon.enlinea.db.connection.dao.Document;

/**
 * @author Asier Guillo
 */
@Component(
    immediate = true,
    service = Indexer.class
)
public class DocumentIndexer extends BaseIndexer<Document> {
	
	public DocumentIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}
	
	@Override
	public String getClassName() {
		return Document.class.getName();
	}
	
	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {
		super.postProcessSearchQuery(searchQuery, fullQueryBooleanFilter, searchContext);
	}
	
	@Override
	protected void doDelete(Document document) throws Exception {
		deleteDocument(document.getCompanyId(), document.getUrl());
	}
	
	@Override
	protected com.liferay.portal.kernel.search.Document doGetDocument(Document doc) {
		com.liferay.portal.kernel.search.Document document = newDocument();
		document.addUID(Document.class.getName(), doc.getUrl());
		document.addKeyword(Field.ENTRY_CLASS_NAME, Document.class.getName());
		document.addKeyword("procedureId", doc.getProcedureId());
		document.addKeyword(Field.COMPANY_ID, doc.getCompanyId());
		document.addKeyword(Field.GROUP_ID, doc.getGroupId());
		document.addDate(Field.MODIFIED_DATE, new Date());
		document.addTextSortable("name", doc.getName());
		document.addText("url", doc.getUrl());
		document.addNumberSortable("order", doc.getOrder());
		return document;
	}
	
	@Override
	protected Summary doGetSummary(com.liferay.portal.kernel.search.Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		return createSummary(document);
	}
	
	
	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void doReindex(String[] ids) throws Exception {
		log.info("Started documents reindex process");
		List<Document> procedureDocuments = enlineaDBService.getDocumentsForIndex();
		if(!procedureDocuments.isEmpty()) {
			doDeleteAll();
			List<com.liferay.portal.kernel.search.Document> documents = new ArrayList<>();
			for(Document procedureDocument : procedureDocuments) {
				documents.add(doGetDocument(procedureDocument));
			}
			IndexWriterHelperUtil.updateDocuments(getSearchEngineId(), procedureDocuments.get(0).getCompanyId(), documents, true);
			try {
				long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
				long groupId = groupLocalService.getFriendlyURLGroup(companyId, GroupConstants.CONTROL_PANEL_FRIENDLY_URL).getGroupId();
				long plid = layoutLocalService.getDefaultPlid(groupId);
				PortletPreferences preferences = portletPreferencesLocalService.getPreferences(companyId, 0, PortletKeys.PREFS_OWNER_TYPE_LAYOUT, plid, EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET);
				SimpleDateFormat df = new SimpleDateFormat("HH:mm, dd MMMM yyyy", LocaleUtil.fromLanguageId("es_ES"));
				preferences.setValue("lastDocumentsReindex", df.format(new Date()));
				preferences.store();
				
			} catch(PortalException e) {
				log.error("Error updating the documents reindex date", e);
			}
			log.info("Finished documents reindex process");
		} else {
			log.error("No documents to reindex");
		}
	}
	
	@Override
	protected void doReindex(Document document) throws Exception {
		IndexWriterHelperUtil.updateDocument(
				getSearchEngineId(),
				document.getCompanyId(),
				doGetDocument(document),
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
			booleanQuery.addRequiredTerm("entryClassName", Document.class.getName());
			try {
				Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
				while(hits.getLength() > 0) {
					for(com.liferay.portal.kernel.search.Document doc : hits.getDocs()) {
						deleteDocument(doc, companyId);
					}
					hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
				}
			} catch(SearchException e) {
				log.error("Error searching documents for delete");	
			}
		} catch(PortalException e) {
			log.error("Error obtaining parameters for deleting all documents", e);
		}
	}
	
	private void deleteDocument(com.liferay.portal.kernel.search.Document doc, long companyId) {
		try {
			delete(GetterUtil.getLong(doc.get(Field.COMPANY_ID), companyId), doc.getUID());
		} catch (SearchException e) {
			log.error("Error deleting document with uid " + doc.getUID());
		}
	}
	
	private static Log log = LogFactoryUtil.getLog(DocumentIndexer.class);
	
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

}
