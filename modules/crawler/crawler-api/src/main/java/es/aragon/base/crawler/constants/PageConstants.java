package es.aragon.base.crawler.constants;

/**
 * 
 * @author Mikel Jorge
 *
 */
public class PageConstants {

	private PageConstants() {}
	
	public static final String RESOURCE_NAME = "es.aragon.base.crawler.model.Page";

	public static final String SERVICE_NAME = "es.aragon.base.crawler";
	
	public static final long PAGE_STATUS_CRAWLING_NO_INDEX = 0;
	public static final long PAGE_STATUS_CRAWLED_NO_INDEX = 1;
	public static final long PAGE_STATUS_CRAWLING_INDEX = 2;
	public static final long PAGE_STATUS_CRAWLED_INDEXING = 3;
	public static final long PAGE_STATUS_CRAWLED_UN_INDEXING = 4;
	public static final long PAGE_STATUS_CRAWLED_INDEXED = 5;
	public static final long PAGE_STATUS_CRAWLED_DELETING = 6;
}
