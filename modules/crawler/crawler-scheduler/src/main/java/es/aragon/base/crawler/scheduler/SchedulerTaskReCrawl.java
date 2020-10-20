package es.aragon.base.crawler.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.List;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.crawler.Crawler;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.PageLocalServiceUtil;
import es.aragon.base.crawler.service.RootPageLocalServiceUtil;

public class SchedulerTaskReCrawl {
	private static final Log _log = LogFactoryUtil.getLog(SchedulerTaskReCrawl.class);
	public void run(){
		
		// get all Root pages to crawl
		List<RootPage> rootPages = RootPageLocalServiceUtil.getRootPagesByScheduledCrawl(true);
		
		// build themeDisplay
		ThemeDisplay themeDisplay = new ThemeDisplay();
		
		long currentCompanyId = PortalUtil.getDefaultCompanyId();
		Role adminRole = RoleLocalServiceUtil.fetchRole(currentCompanyId, "Administrator");
		
		// fetch user
		User user = null;
		if(Validator.isNotNull(adminRole)) {
			List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
			if(adminUsers != null && !adminUsers.isEmpty()) {
				user = adminUsers.get(0);
			}
		}
		
		// fetch current company (default company)
		Company company = CompanyLocalServiceUtil.fetchCompany(currentCompanyId);
		
		// fetch Site id (groupId)
		Group group = null;
		if(Validator.isNotNull(company)) {
			group = GroupLocalServiceUtil.fetchGroup(company.getCompanyId(), "Guest");
		}
		
		// build themeDisplay
		try {
			themeDisplay.setUser(user);
			themeDisplay.setCompany(company);
			themeDisplay.setSiteGroupId(group.getGroupId());
		} catch(Exception e) {
			themeDisplay = null;
		}
		
		// if themeDisplay sucessfully build
		if(Validator.isNotNull(user) && Validator.isNotNull(company) && Validator.isNotNull(themeDisplay)) {

			for(RootPage rootPage : rootPages) {
				
				Page auxRootPage = PageLocalServiceUtil.fetchPage(rootPage.getPageId());
				if(Validator.isNotNull(auxRootPage) && auxRootPage.getStatus() == 5) {
					_log.info("RECRAWLEANDO: " + rootPage.getAlias());
					// Set current page status to "CRAWLING"
					long prevStatus = auxRootPage.getStatus();
					auxRootPage.setStatus(CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX);
					PageLocalServiceUtil.updatePage(auxRootPage);
					
					Page page = PageLocalServiceUtil.fetchPage(rootPage.getPageId());
					
					int depth = rootPage.getDepth();
					
					List<Page> previousPages = PageLocalServiceUtil.getPagesByRootPageId(rootPage.getRootPageId());
					
										
					Crawler crawler = new Crawler(rootPage,
							page.getUrl(),
							page.getCategoryIds(),
							depth,
							CrawlerPortletKeys.PAGE_STATUS_CRAWLING_INDEX,
							PageLocalServiceUtil.getService(),
							RootPageLocalServiceUtil.getService(),
							themeDisplay);
					
					// launch reCrawling init method
					try {
						crawler.initReCrawling(previousPages);
						
						for(Page prevPage : previousPages) {
							PageLocalServiceUtil.deletePage(prevPage);
						}
						
						crawler.start();
					} catch (IOException e) {
						_log.error("Error connecting to '" + page.getUrl() + "':", e);
						auxRootPage.setStatus(prevStatus);
						PageLocalServiceUtil.updatePage(auxRootPage);
					}
					

				} else {
					_log.info("NO SE HA PODIDO RECRAWLEAR: " + rootPage.getAlias());
				}
			}
		} else {
			_log.info("NO SE HA PODIDO INICIAR EL PROCESO DE RECRAWLING");
		}
		
	}
}
