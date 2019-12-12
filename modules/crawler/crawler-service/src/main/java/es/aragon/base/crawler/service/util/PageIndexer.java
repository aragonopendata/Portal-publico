package es.aragon.base.crawler.service.util;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.constants.PageConstants;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.PageLocalService;
import es.aragon.base.crawler.service.RootPageLocalService;

@Component(
    immediate = true,
    service = Indexer.class
)
public class PageIndexer extends BaseIndexer<Page> {

	@Reference
	private PageLocalService pageLocalService;
	
	@Reference
	private RootPageLocalService rootPageLocalService;
	
	public static final String CLASS_NAME = Page.class.getName();
	
	public PageIndexer() {
	    setDefaultSelectedFieldNames(
	    		Field.COMPANY_ID,
	    		Field.GROUP_ID,
	    		Field.GROUP_ROLE_ID,
	    		Field.ROLE_ID,
	    		Field.ENTRY_CLASS_NAME,
	    		Field.ENTRY_CLASS_PK,
	    		Field.TITLE,
	    		Field.CONTENT,
	    		Field.URL,
	    		Field.ASSET_CATEGORY_IDS,
	    		Field.MODIFIED_DATE,
	    		Field.STATUS);
	    setFilterSearch(true);
	}
	
	public PageIndexer(PageLocalService pageLocalService, RootPageLocalService rootPageLocalService) {
	    setDefaultSelectedFieldNames(
	    		Field.COMPANY_ID,
	    		Field.GROUP_ID,
	    		Field.GROUP_ROLE_ID,
	    		Field.ROLE_ID,
	    		Field.ENTRY_CLASS_NAME,
	    		Field.ENTRY_CLASS_PK,
	    		Field.TITLE,
	    		Field.CONTENT,
	    		Field.URL,
	    		Field.ASSET_CATEGORY_IDS,
	    		Field.MODIFIED_DATE,
	    		Field.STATUS);
	    setFilterSearch(true);
	    
	    if (Validator.isNull(this.pageLocalService)) {
	    	this.pageLocalService = pageLocalService;
	    }
	    if (Validator.isNull(this.rootPageLocalService)) {
	    	this.rootPageLocalService = rootPageLocalService;
	    }
	}
	
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Page page) throws Exception {
		
		RootPage rootPage;
		if (Validator.isNotNull(page.getParentPageId())) {
			rootPage = rootPageLocalService.getRootPage(page.getRootPageId());
		}
		else {
			rootPage = rootPageLocalService.getRootPageByPageId(page.getPageId());
		}
		deleteDocument(rootPage.getCompanyId(), page.getPageId());
	}
	
	public void doDeletePageDocumentAndChilds(Page page) throws Exception {
		
		page.setStatus(PageConstants.PAGE_STATUS_CRAWLED_UN_INDEXING);
		
		pageLocalService.updatePage(page);
		List<Page> childPages = pageLocalService.getChildPages(page.getPageId());
		if (childPages != null && !childPages.isEmpty()) {
			for (Page childPage : childPages) {
				doDeletePageDocumentAndChilds(childPage);
			}
		}

		doDelete(page);
		page.setStatus(PageConstants.PAGE_STATUS_CRAWLED_NO_INDEX);
		pageLocalService.updatePage(page);
	}

	@Override
	protected Document doGetDocument(Page page) throws Exception {

		// get root page
		RootPage rootPage;
		if (Validator.isNotNull(page.getParentPageId())) {
			rootPage = rootPageLocalService.getRootPage(page.getRootPageId());
		}
		else {
			rootPage = rootPageLocalService.getRootPageByPageId(page.getPageId());
		}
		
		// create document
		Document document = getBaseModelDocument(CLASS_NAME, page);
		
		document.addKeyword(Field.COMPANY_ID, rootPage.getCompanyId());
		document.addKeyword(Field.GROUP_ID, rootPage.getGroupId());
		document.addDate(Field.MODIFIED_DATE, rootPage.getCrawledDate());
		
		// add portal alias to make facets
		document.addKeyword("portalAlias", rootPage.getAlias());
		document.addKeyword("rootPageId", rootPage.getRootPageId());
		
		// add guest role
		Role guestUserRole = RoleLocalServiceUtil.getRole(rootPage.getCompanyId(), RoleConstants.GUEST);
		document.addKeyword(Field.ROLE_ID, guestUserRole.getRoleId());
		document.addKeyword(Field.GROUP_ROLE_ID, guestUserRole.getRoleId());
		
		document.addKeyword(Field.ENTRY_CLASS_NAME, Page.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, page.getPageId());
		
		document.addKeyword(Field.TITLE, page.getTitle());
		document.addKeyword(Field.CONTENT, page.getContent());
		document.addKeyword(Field.URL, page.getUrl());
		
		if(Validator.isNotNull(page.getCategoryIds())) {
			long[] longCategoryIds = StringUtil.split(page.getCategoryIds(), 0L);
			document.addKeyword(Field.ASSET_CATEGORY_IDS, longCategoryIds);
		}
		
		document.addKeyword(Field.STATUS, 0);
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		return getSummary(document, snippet, portletRequest, portletResponse);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Page page = pageLocalService.getPage(classPK);
	    doReindex(page);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		for(String id : ids) {
			List<RootPage> rootPages = rootPageLocalService.getRootPages(Long.valueOf(id));
			for(RootPage rootPage : rootPages) {
				Page page = pageLocalService.getPage(rootPage.getPageId());
				if (page != null &&
						(page.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_INDEXING
							|| page.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_INDEXED)) {
					reindexPageAndChilds(page);
				}
			}
		}
	}
	
	public void reindexPageAndChilds(Page page) throws Exception {
		
		page.setStatus(PageConstants.PAGE_STATUS_CRAWLED_INDEXING);
		pageLocalService.updatePage(page);
		
		List<Page> childPages = pageLocalService.getChildPages(page.getPageId());
		if (childPages != null && !childPages.isEmpty()) {
			for (Page childPage : childPages) {
				reindexPageAndChilds(childPage);
			}
		}
		
		doReindex(page);
		page.setStatus(PageConstants.PAGE_STATUS_CRAWLED_INDEXED);
		pageLocalService.updatePage(page);
	}

	@Override
	protected void doReindex(Page page) throws Exception {

		// only reindex when is crawled and need to be indexed or when is already indexed
		if (page.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_INDEXING || page.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_INDEXED) {
			// get root page
			RootPage rootPage;
			if (Validator.isNotNull(page.getParentPageId())) {
				rootPage = rootPageLocalService.getRootPage(page.getRootPageId());
			}
			else {
				rootPage = rootPageLocalService.getRootPageByPageId(page.getPageId());
			}
			
		    IndexWriterHelperUtil.updateDocument(
					getSearchEngineId(),
					rootPage.getCompanyId(),
					doGetDocument(page),
					isCommitImmediately()
			);
		}
	}
}
