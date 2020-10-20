package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.model.Page;
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
		"mvc.command.name=" + MVCCommandNames.DELETE_PAGE
	},
	service = MVCActionCommand.class
)
public class DeletePageMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	protected PageLocalService pageLocalService;
	
	@Reference
	protected RootPageLocalService rootPageLocalService;
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
				
		// Get page id from request.
		long pageId = ParamUtil.getLong(actionRequest, "pageId");
		
		if (Validator.isNotNull(pageId)) {
			Page page = pageLocalService.fetchPage(pageId);
			if(Validator.isNotNull(page)) {
				if(Validator.isNull(page.getParentPageId())) {
					List<Page> childPages = 
							pageLocalService.getPagesByRootPageId(
									rootPageLocalService.getRootPageByPageId(page.getPageId()).getRootPageId());
					for(Page childPage : childPages) {
						pageLocalService.deletePage(childPage);
					}
				} else {
					List<Page> childPages = pageLocalService.getChildPages(pageId);
					for(Page childPage : childPages) {
						pageLocalService.deletePage(childPage);
					}
				}

				pageLocalService.deletePage(page);
			}
		}
	}
}
