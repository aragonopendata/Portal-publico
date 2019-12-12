package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
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
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.admin.crawler.Crawler;
import es.aragon.base.crawler.admin.util.CrawlerUtil;
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
		"mvc.command.name=" + MVCCommandNames.ADD_PAGE
	},
	service = MVCActionCommand.class
)
public class AddPageMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	private PageLocalService pageLocalService;
	
	@Reference
	private RootPageLocalService rootPageLocalService;
	
	@Reference
	protected AssetVocabularyLocalService assetVocabularyLocalService;
	
	private static final Log _log = LogFactoryUtil.getLog(AddPageMVCActionCommand.class);

	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Get parameters from the request.
		String alias = ParamUtil.getString(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_ALIAS);
		String url = ParamUtil.getString(actionRequest, CrawlerPortletKeys.PAGE_ADMIN_URL);
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

		RootPage rootPage = rootPageLocalService.createRootPage();
		rootPage.setGroupId(themeDisplay.getSiteGroupId());
		rootPage.setCompanyId(themeDisplay.getCompanyId());
		rootPage.setAlias(alias);
		rootPage.setDepth(depth);
		rootPage.setInclusionRules(inclusionRules);
		rootPage.setCrawledDate(new Date());
		rootPage.setScheduledCrawl(scheduledCrawl);
		
		rootPage = rootPageLocalService.addRootPage(rootPage);
		
		// check if url has http://, if not, add it
		if (Validator.isNotNull(url) && !url.startsWith("http")) {
			url = "http://" + url;
		}
		
		if (Validator.isNotNull(url) && CrawlerUtil.isValidURL(url)) {
			Crawler crawler = new Crawler(rootPage,
					url,
					categoryIds.toString(),
					depth,
					(status) ? CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX : CrawlerPortletKeys.PAGE_STATUS_CRAWLING_NO_INDEX,
					pageLocalService,
					rootPageLocalService,
					themeDisplay);
			
			// launch crawler init method to crawl init URL and create it's page
			try {
				long pageId = crawler.init();
				
				rootPage.setPageId(pageId);
				rootPageLocalService.updateRootPage(rootPage);
				
				// launch crawling in other thread
				crawler.start();
			} catch (IOException e) {
				rootPageLocalService.deleteRootPage(rootPage);
				_log.error("Error connecting to '" + url + "':", e);
				SessionErrors.add(actionRequest, "cannot-connect");
			}
		}
		if (redirect != null && !redirect.trim().isEmpty()) {
			actionResponse.sendRedirect(redirect);	
		}
	}
}
