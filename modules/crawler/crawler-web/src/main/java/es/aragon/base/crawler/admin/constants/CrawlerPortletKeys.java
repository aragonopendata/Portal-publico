package es.aragon.base.crawler.admin.constants;

/**
 * @author Mikel Jorge
 */
public class CrawlerPortletKeys {

	private CrawlerPortletKeys() {}
	
	public static final String CRAWLER_ADMIN = "es_aragon_base_crawler_web_CrawlerAdminPortlet";

	public static final String PAGE_ADMIN_ALIAS = "es.aragon.base.crawler.edit.alias";
	public static final String PAGE_ADMIN_URL = "es.aragon.base.crawler.edit.url";
	public static final String PAGE_ADMIN_DEPTH = "es.aragon.base.crawler.edit.depth";
	public static final String PAGE_ADMIN_INCLUSION_RULES = "es.aragon.base.crawler.edit.inclusion-rules";
	public static final String PAGE_ADMIN_STATUS = "es.aragon.base.crawler.edit.status";
	public static final String PAGE_ADMIN_SCHEDULED_CRAWL = "es.aragon.base.crawler.edit.auto-crawl";
	
	public static final long PAGE_STATUS_CRAWLING_NO_INDEX = 0;
	public static final long PAGE_STATUS_CRAWLED_NO_INDEX = 1;
	public static final long PAGE_STATUS_CRAWLING_INDEX = 2;
	public static final long PAGE_STATUS_CRAWLED_INDEXING = 3;
	public static final long PAGE_STATUS_CRAWLED_UN_INDEXING = 4;
	public static final long PAGE_STATUS_CRAWLED_INDEXED = 5;
	
	
	
}