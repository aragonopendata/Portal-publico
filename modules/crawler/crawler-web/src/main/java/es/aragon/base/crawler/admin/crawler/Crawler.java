package es.aragon.base.crawler.admin.crawler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.vdurmont.emoji.EmojiParser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.util.CrawlerUtil;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.PageLocalService;
import es.aragon.base.crawler.service.RootPageLocalService;

/**
 * 
 * @author Mikel Jorge
 *
 */
public class Crawler extends Thread {

	private static Log log = LogFactoryUtil.getLog(Crawler.class);
	
	// init URL to start crawling. Need to be berified before.
	private String initURL;
	
	// origin URL used to check if current URLs are in the same web page
	private String originURL;
	
	// crawler limit depth, starting by 1.
	private int limitDepth;
	
	// crawler founded links and their depth
	private Map<String, Integer> allLinks;
	
	// crawler's links to search into and their depth
	private Map<String, Entry<Integer, Long>> linksToCrawl;
	
	// page local service. Used to create pages in DB
	private PageLocalService pageLocalService;
	private RootPageLocalService rootPageLocalService;
	
	// root page
	private RootPage rootPage;
	
	// root page's pageId
	private long pageId;
	
	// status (1 indexed / 0 no indexed)
	private long status;
	
	// themedisplay used to add pages
	private ThemeDisplay themeDisplay;
	
	// selected category Ids
	private String categoryIds;
	
	// is re-crawl (set page categories)
	private boolean reCrawl;
	
	// previous pages
	private List<Page> previousPages;
	
	/**
	 * Method used to create a Crawler with these params:
	 * @param initURL: init url to point when crawling begins. If is null won't do nothing when init() method is called.
	 * @param limitDepth: limit depth to crawl, counting initURL as first level. If is null or less than 1, it'll be set as Crawler.DEFAULT_DEPTH_LIMIT.
	 */
	public Crawler(RootPage rootPage, String initURL, String categoryIds, Integer limitDepth, long status, PageLocalService pageLocalService, RootPageLocalService rootPageLocalService, ThemeDisplay themeDisplay) {
		
		this.initURL = initURL;
		this.pageLocalService = pageLocalService;
		this.rootPage = rootPage;
		this.status = status;
		this.limitDepth = limitDepth;
		this.pageId = 0L;
		this.themeDisplay = themeDisplay;
		this.categoryIds = categoryIds;
		this.rootPageLocalService = rootPageLocalService;
		this.reCrawl = false;
		this.previousPages = new ArrayList<>();
		
		if (Validator.isNotNull(initURL)) {
			String subString = initURL.substring(initURL.indexOf("//") + 2);
			if (subString.indexOf('/') != -1) {
				this.originURL = subString.substring(0, subString.indexOf('/'));
			}
			else {
				this.originURL = subString;
			}
			
			allLinks = new HashMap<>();
			allLinks.put(initURL, 1);
			linksToCrawl = new HashMap<>();
		}
	}
	
	/**
	 * Class used to start crawling from the initURL param until all url's are crawled or configured depth is reached
	 * @throws IOException 
	 */
	public long init() throws IOException {
		
		crawlLink(this.initURL, 1, 0L);
		return this.pageId;
	}
	
	public void initReCrawling(List<Page> previousPages) throws IOException {

			// set reIndex
			this.reCrawl = true;
			
			// set previous pages
			this.previousPages = previousPages;
			
			
        	// fetch the HTML code
			Document document = Jsoup.connect(this.initURL).get();
			
			// parse the HTML to extract links to other URLs and include them to the lists
			Elements linksOnPage = document.select("a[href]");
			
			includeNewLinksToCrawl(linksOnPage, 2, rootPage.getPageId());

	}
	
	@Override
	public void run() {
		// while exists links to crawl, continue crawling
		while(!linksToCrawl.isEmpty() && linksToCrawl.size() > 0) {
			// get next element to crawl
			Map.Entry<String, Entry<Integer, Long>> entry = linksToCrawl.entrySet().iterator().next();
			String link = entry.getKey();
			Integer depth = entry.getValue().getKey();
			Long parentPageId = entry.getValue().getValue();
			
			log.info("remaining " + linksToCrawl.size() + " - [" + depth + "]-" + link);
			
			try {
				crawlLink(link, depth, parentPageId);
			} catch (IOException e) {
				log.error("Error connecting to '" + this.initURL + "':", e);
			}
			
			// remove crawled element
			linksToCrawl.remove(link);
		}
		
		// update new pages to match previous existing pages categories
		if(reCrawl) {
			List<Page> currentPages = pageLocalService.getPagesByRootPageId(rootPage.getRootPageId());

			for(Page currentPage : currentPages) {
				for(Page previousPage : previousPages) {
					if(currentPage.getUrl().equals(previousPage.getUrl())) {
						Set<Integer> currentCategories = new HashSet<>();
						currentCategories.addAll(Arrays.asList(Arrays.stream(currentPage.getCategoryIds().split(","))
								.map(Object::toString)
								.map(Integer::valueOf)
								.toArray(Integer[]::new)));
						
						if(Validator.isNotNull(previousPage.getCategoryIds()) && !previousPage.getCategoryIds().equals("")) {
							currentCategories.addAll(Arrays.asList(Arrays.stream(previousPage.getCategoryIds().split(","))
									.map(Object::toString)
									.map(Integer::valueOf)
									.toArray(Integer[]::new)));
						}

						currentPage.setCategoryIds(currentCategories.stream()
							                        .map(i->Integer.toString(i))
							                        .collect(Collectors.joining(",")));
						
						pageLocalService.updatePage(currentPage);
						log.info("Page updated: " + currentPage.getTitle());
					}
				}
			}
			
			// set update date
			rootPage.setCrawledDate(new Date());
			rootPageLocalService.updateRootPage(rootPage);
			
			try {
				Page auxRootPage = pageLocalService.getPage(rootPage.getPageId());
				auxRootPage.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLED_INDEXED);
			} catch (Exception e) {
				log.error("Cannot set reCrawled page status to: 'crawled");
			}
		}
		
		// if no need to index only change rootPage's page status
		if (this.status == CrawlerPortletKeys.PAGE_STATUS_CRAWLING_NO_INDEX) {
			try {
				Page page = pageLocalService.getPage(this.rootPage.getPageId());
				page.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLED_NO_INDEX);
				pageLocalService.updatePage(page);
			} catch (PortalException e) {
				log.error("Error updating rootPage's page status. Page with ID '" + this.rootPage.getPageId() + "' not found:", e);
			}
		}
		// need to index
		else {
			try {
				Page page = pageLocalService.getPage(this.rootPage.getPageId());
				
				// change page status
				page.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLED_INDEXING);
				pageLocalService.updatePage(page);
				
				// index
				CrawlerIndexer crawlerIndexer = new CrawlerIndexer(page, true, pageLocalService, rootPageLocalService);
				crawlerIndexer.start();

			} catch (PortalException e) {
				log.error("Error getting rootPage's page to start indexing. Page with ID '" + this.rootPage.getPageId() + "' not found:", e);
			}
		}
	}
	
	private void crawlLink(String link, Integer depth, long parentPageId) throws IOException{
		
		final String CONTENT = "content";
		
        try {
        	// fetch the HTML code
			Document document = Jsoup.connect(link).get();
			
			// parse current HTML and save it in a page
			Page page = pageLocalService.createPage();
			
			// basic data
			page.setUrl(link);
			
			// set parentPageId and scopePageId
			if (depth == 1) {
				page.setRootPageId(0L);
			}
			else {
				page.setRootPageId(this.rootPage.getRootPageId());
			}
			page.setParentPageId(parentPageId);
			
			// set selected categoryIds
			page.setCategoryIds(categoryIds);
			
			// Crawled data
			// page title
			page.setTitle(document.title());
			
			// JSOUP only can work with plain text content types
			page.setContentType("text/html");
			
			// page meta's description
			Elements metaDescription = document.select("meta[name=description]");
			if (metaDescription != null) {
				Charset charset = StandardCharsets.UTF_8;
				page.setMetaDescription(charset.decode(charset.encode(EmojiParser.removeAllEmojis(metaDescription.attr(CONTENT)))).toString());
			}
			
			// page meta's keywords
			Elements metaKeywords = document.select("meta[name=keywords]");
			if (metaKeywords != null) {
				page.setMetaKeywords(metaKeywords.attr(CONTENT));
			}
			
			// page's content
			page.setContent(getContent(document.body()));
			
			// page's status
			if (depth == 1) {
				if (this.status == CrawlerPortletKeys.PAGE_STATUS_CRAWLING_NO_INDEX) {
					page.setStatus(this.status);
				}
				else {
					page.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX);
				}
			}
			else {
				if (this.status == CrawlerPortletKeys.PAGE_STATUS_CRAWLING_NO_INDEX) {
					page.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLED_NO_INDEX);
				}
				else {
					page.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLED_INDEXING);
				}
			}
			
			// save new page in database
			
			page = pageLocalService.addPage(page, themeDisplay.getUserId(), themeDisplay.getCompanyId(), themeDisplay.getSiteGroupId());
			
			// parse the HTML to extract links to other URLs and include them to the lists
			Elements linksOnPage = document.select("a[href]");
			includeNewLinksToCrawl(linksOnPage, depth + 1, page.getPageId());
			
			if (depth == 1) {
				this.pageId = page.getPageId();
			}
			
		}catch(UnsupportedMimeTypeException un) {
			log.error("Error with the type link");
		}
	}
	
	private String getContent(Element body) {
		String content = "";
			if (body!=null && Validator.isNotNull(this.rootPage.getInclusionRules())) {
				Elements elements = body.select(this.rootPage.getInclusionRules());
				content = elements.text();
			}
			else {
				if (body != null) {
					content = body.text();
				}
			}
		return content;
	}
	
	private void includeNewLinksToCrawl(Elements linksOnPage, Integer depth, long parentPageId) {
		
		// check if the depth is not higher than selected limit
		if (this.limitDepth >= depth && linksOnPage != null) {
			for (Element pageLink : linksOnPage) {
	            String link = pageLink.attr("abs:href");
	            
	            // if the link has not been included yet include it
	            if (Validator.isNotNull(link)
	            		&& CrawlerUtil.isValidURL(link)
	            		&& !allLinks.containsKey(link)
	            		&& link.startsWith("https://" +this.originURL)) {

	            	allLinks.put(link, depth);
	            	linksToCrawl.put(link, new SimpleEntry(depth, parentPageId));
	            }
	        }
		}
	}
	
	public String getInitURL() {
		return initURL;
	}
	public void setInitURL(String initURL) {
		this.initURL = initURL;
	}
	public int getLimitDepth() {
		return limitDepth;
	}
	public void setLimitDepth(int depth) {
		this.limitDepth = depth;
	}
}
