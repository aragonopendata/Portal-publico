package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.admin.crawler.Crawler;
import es.aragon.base.crawler.admin.crawler.CrawlerIndexer;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.PageLocalService;
import es.aragon.base.crawler.service.RootPageLocalService;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + CrawlerPortletKeys.CRAWLER_ADMIN,
			"mvc.command.name=" + MVCCommandNames.EDIT_PAGE
		},
		service = MVCActionCommand.class
	)
public class EditPageMVCActionCommand extends BaseMVCActionCommand {

	private static final Log log = LogFactoryUtil.getLog(EditPageMVCActionCommand.class);
	
	private static final boolean DISABLE_RECRAWLING_AFTER_EDIT = false;
	
	@Reference
	protected PageLocalService pageLocalService;
	
	@Reference
	protected AssetVocabularyLocalService assetVocabularyLocalService;
	
	@Reference
	protected RootPageLocalService rootPageLocalService;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Get parameters from the request.
		long pageId = ParamUtil.getLong(actionRequest, "pageId");
		String alias = ParamUtil.getString(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_ALIAS);
		int depth = ParamUtil.getInteger(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_DEPTH);
		String inclusionRules = ParamUtil.getString(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_INCLUSION_RULES);
		boolean status = ParamUtil.getBoolean(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_STATUS);
		boolean scheduledCrawl = ParamUtil.getBoolean(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_SCHEDULED_CRAWL);
		String redirect = actionRequest.getParameter("redirect");
		
		// get selected category IDs
		StringBuilder categoryIds = new StringBuilder();
		long[] groupIds = {themeDisplay.getSiteGroupId()};
		List<AssetVocabulary> vocabularies = assetVocabularyLocalService.getGroupsVocabularies(groupIds, JournalArticle.class.getName());
		for (AssetVocabulary vocabulary : vocabularies) {
			String vocabularySelectedCategories = ParamUtil.getString(actionRequest, "categoryIds_" + vocabulary.getVocabularyId(), "");
			if (Validator.isNotNull(vocabularySelectedCategories)) {
				if (Validator.isNotNull(categoryIds.toString())) {
					categoryIds.append(",");
				}
				categoryIds.append(vocabularySelectedCategories);
			}
		}
		
		// update rootPage
		if (Validator.isNotNull(alias)) {
			// get rootPage
			RootPage rootPage = rootPageLocalService.getRootPageByPageId(pageId);
			
			boolean reCrawl = updateRootPage(rootPage, alias, depth, inclusionRules, scheduledCrawl);
			
			try {
				Page page = pageLocalService.getPage(pageId);
				
				// update categoryIds if has changed
				if (page.getCategoryIds().compareTo(categoryIds.toString()) != 0) {
					page.setCategoryIds(categoryIds.toString());
					page = pageLocalService.updatePage(page);
				}

				if (reCrawl) {
					List<Page> previousPages = pageLocalService.getPagesByRootPageId(rootPage.getRootPageId());
					
					// delete actual crawled pages if it's going to reCrawl
					pageLocalService.deleteChildPages(page, false);

					// reCrawl root page
					Crawler crawler = new Crawler(rootPage,
							page.getUrl(),
							categoryIds.toString(),
							depth,
							(status) ? CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX : CrawlerPortletKeys.PAGE_STATUS_CRAWLING_NO_INDEX,
							pageLocalService,
							rootPageLocalService,
							themeDisplay);
					
					// launch reCrawling init method
					crawler.initReCrawling(previousPages);
					
					// launch crawling in other thread
					crawler.start();
				}
			} catch (PortalException e) {
				log.error("Error updating page with ID '" + pageId + "':", e);
			}
		}
		else {
			// update page
			try {
				Page page = pageLocalService.getPage(pageId);
				
				// index or delete index
				CrawlerIndexer crawlerIndexer = new CrawlerIndexer(page, status, pageLocalService, rootPageLocalService);
				crawlerIndexer.start();
				
				// get the page again to work with it's last state, depends on indexing time
				page = pageLocalService.getPage(pageId);
				page.setCategoryIds(categoryIds.toString(), pageLocalService);
				pageLocalService.updatePage(page);
				
			} catch (PortalException e) {
				log.error("Error updating page with ID '" + pageId + "':", e);
			}
		}
		if (redirect != null && !redirect.trim().isEmpty()) {
			actionResponse.sendRedirect(redirect);	
		}
	}
	
	private boolean updateRootPage(RootPage rootPage, String alias, int depth, String inclusionRules, boolean scheduledCrawl) {
		
		boolean pageUpdated = false;
		boolean reCrawling = false;
		
		// check alias
		if (rootPage.getAlias().compareTo(alias) != 0) {
			rootPage.setAlias(alias);
			pageUpdated = true;
		}
		
		// check depth
		if (rootPage.getDepth() != depth) {
			rootPage.setAlias(alias);
			rootPage.setDepth(depth);
			pageUpdated = true;
			reCrawling = true;
		}
		
		// check inclusionRules
		if (rootPage.getInclusionRules().compareTo(inclusionRules) != 0) {
			rootPage.setInclusionRules(inclusionRules);
			pageUpdated = true;
			reCrawling = true;
		}
		
		// check scheduleCrawl 
		if(rootPage.isScheduledCrawl() != scheduledCrawl) {
			rootPage.setScheduledCrawl(scheduledCrawl);
			pageUpdated = true;
		}
		
		// update page only if it's necesary
		if (pageUpdated) {
			rootPageLocalService.updateRootPage(rootPage);
		}
		
		return reCrawling && DISABLE_RECRAWLING_AFTER_EDIT;
	}
}
