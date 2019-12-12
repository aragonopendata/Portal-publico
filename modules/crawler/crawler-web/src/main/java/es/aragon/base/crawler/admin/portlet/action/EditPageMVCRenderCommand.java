package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
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
	service = MVCRenderCommand.class
)
public class EditPageMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(EditPageMVCRenderCommand.class);
	
	@Reference
	protected PageLocalService pageLocalService;
	
	@Reference
	protected RootPageLocalService rootPageLocalService;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		// Set back icon visible.
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		portletDisplay.setShowBackIcon(true);
		String redirect = renderRequest.getParameter("redirect");
		portletDisplay.setURLBack(redirect);
		
		Page pageToEdit = null;
		RootPage rootPageToEdit = null;
		long pageId = ParamUtil.getLong(renderRequest, "pageId", 0);
		if (pageId != 0) {
			try {
				pageToEdit = pageLocalService.getPage(pageId);
				if (Validator.isNull(pageToEdit.getParentPageId())) {
					long rootPageId = rootPageLocalService.getRootPageId(pageId);
					rootPageToEdit = rootPageLocalService.getRootPage(rootPageId);
				}
			} catch (PortalException e) {
				log.error("Page widh Id '" + pageId + "' doesn't exist:", e);
			}
		}
		
		String pageCategoryIds = "";
		if (pageToEdit != null) {
			pageCategoryIds = pageToEdit.getCategoryIds();
			
			renderRequest.setAttribute("status", (pageToEdit.getStatus() >= 2) ? Boolean.TRUE : Boolean.FALSE);
		}
		renderRequest.setAttribute("categoriesClassName", JournalArticle.class.getName());
		renderRequest.setAttribute("pageCategoryIds", pageCategoryIds);
		renderRequest.setAttribute("pageToEdit", pageToEdit);
		renderRequest.setAttribute("rootPageToEdit", rootPageToEdit);
		renderRequest.setAttribute("pageClass", Page.class);
		
		return "/edit_page/edit_page.jsp";
	}
}
