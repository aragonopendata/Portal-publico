package es.aragon.base.crawler.admin.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.service.PageLocalService;

public class CrawlerUtil {

	private CrawlerUtil () {}
	
	public static boolean isValidURL(String stringURL) {
		try {
			new URL(stringURL);
			return true;
		}
		catch (MalformedURLException e) {
			return false;
	    }
	}
	
	public static int pageDepth(long pageId, PageLocalService pageLocalService) {
		return getDepth(pageId, pageLocalService, 1);
	}
	
	public static int getDepth(long pageId, PageLocalService pageLocalService, int actualDepth) {
		int depth = actualDepth;
		
		List<Page> childrenPages = pageLocalService.getChildPages(pageId);
		if (childrenPages != null && !childrenPages.isEmpty()) {
			for (Page page : childrenPages) {
				int newDepth = getDepth(page.getPageId(), pageLocalService, actualDepth + 1);
				
				if (newDepth > depth) {
					depth = newDepth;
				}
			}
		}
		
		return depth;
	}
}
