package es.aragon.base.crawler.admin.crawler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.service.PageLocalService;
import es.aragon.base.crawler.service.RootPageLocalService;
import es.aragon.base.crawler.service.util.PageIndexer;

public class CrawlerIndexer extends Thread {
	
	private static final Log log = LogFactoryUtil.getLog(CrawlerIndexer.class);
	
	private Page page;
	private boolean index;
	private PageLocalService pageLocalService;
	private RootPageLocalService rootPageLocalService;
	
	public CrawlerIndexer(Page page, boolean index, PageLocalService pageLocalService, RootPageLocalService rootPageLocalService) {
		this.page = page;
		this.index = index;
		this.pageLocalService = pageLocalService;
		this.rootPageLocalService = rootPageLocalService;
	}
	
	@Override
	public void run() {
		
		try {
			if (index) {
				// page wasn't indexed but now need to index
				if (page.getStatus() == CrawlerPortletKeys.PAGE_STATUS_CRAWLED_NO_INDEX
						|| page.getStatus() == CrawlerPortletKeys.PAGE_STATUS_CRAWLED_INDEXING) {
					PageIndexer pageIndexer = new PageIndexer(pageLocalService, rootPageLocalService);
					pageIndexer.reindexPageAndChilds(page);
				}
			}
			else {
				// page was indexed but now need to remove that index
				if (page.getStatus() == CrawlerPortletKeys.PAGE_STATUS_CRAWLED_INDEXED) {
					PageIndexer pageIndexer = new PageIndexer(pageLocalService, rootPageLocalService);
					pageIndexer.doDeletePageDocumentAndChilds(page);
				}
			}
		} catch (Exception e) {
			log.error("Error indexing page with ID '" + page.getPageId() + "':", e);
		}
	}
}
