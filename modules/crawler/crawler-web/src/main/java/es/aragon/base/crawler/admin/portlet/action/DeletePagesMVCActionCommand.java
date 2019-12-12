package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.service.PageService;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CrawlerPortletKeys.CRAWLER_ADMIN,
		"mvc.command.name=" + MVCCommandNames.DELETE_PAGES
	},
	service = MVCActionCommand.class
)
public class DeletePagesMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	protected PageService pageService;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Get page's id from request.
		long[] pageIds = StringUtil.split(ParamUtil.getString(actionRequest, "deletePagesIds"), 0L);
		
		for (long pageId : pageIds) {
			pageService.deletePage(pageId, themeDisplay.getSiteGroupId());
		}
	}

}
