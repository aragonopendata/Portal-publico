package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.admin.crawler.Crawler;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.PageLocalService;
import es.aragon.base.crawler.service.PageLocalServiceUtil;
import es.aragon.base.crawler.service.RootPageLocalService;
import es.aragon.base.crawler.service.RootPageLocalServiceUtil;

/**
 * 
 * @author JRambla
 *
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CrawlerPortletKeys.CRAWLER_ADMIN,
		"mvc.command.name=" + MVCCommandNames.RECRAWL_PAGE
	},
	service = MVCActionCommand.class
)
public class ReCrawlPageMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	protected PageLocalService pageLocalService;
	
	@Reference
	protected AssetVocabularyLocalService assetVocabularyLocalService;
	
	@Reference
	protected RootPageLocalService rootPageLocalService;
	
	private static final Log _log = LogFactoryUtil.getLog(ReCrawlPageMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Get parameters from the request.
		long pageId = ParamUtil.getLong(actionRequest, "pageId");
		
		// Get existing page
		Page page = null;
		if (Validator.isNotNull(pageId)) {
			page = pageLocalService.fetchPage(pageId);
		}
		
		// Get Root page
		RootPage rootPage = null;
		if(Validator.isNotNull(page)) {
			rootPage = rootPageLocalService.getRootPageByPageId(pageId);
		}
		
		if(Validator.isNotNull(page) && Validator.isNotNull(rootPage))	 {
			// set status to "crawling"
			Page auxRootPage = pageLocalService.getPage(rootPage.getPageId());
			long prevStatus = auxRootPage.getStatus();
			auxRootPage.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX);
			pageLocalService.updatePage(auxRootPage);
			
			// get depth
			int depth = rootPage.getDepth();
			
			// get previous page list for later update
			List<Page> previousPages = pageLocalService.getPagesByRootPageId(rootPage.getRootPageId());
			
			// reCrawl root page
			Crawler crawler = new Crawler(rootPage,
					page.getUrl(),
					page.getCategoryIds(),
					depth,
					CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX,
					pageLocalService,
					rootPageLocalService,
					themeDisplay);
			
			try {
				// launch reCrawling init method
				crawler.initReCrawling(previousPages);
			
				// delete actual crawled pages if it's going to reCrawl
//				pageLocalService.deleteChildPages(page, false);
				for(Page prevPage : previousPages) {
					pageLocalService.deletePage(prevPage);
				}
				
				// launch crawling in other thread
				crawler.start(); 
				
			} catch (IOException e) {
				_log.error("Error connecting to '" + page.getUrl() + "':", e);
				SessionErrors.add(actionRequest, "cannot-connect");
				auxRootPage.setStatus(prevStatus);
				pageLocalService.updatePage(auxRootPage);
			}
		}
	}
}
