package es.aragon.base.crawler.admin.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;
import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.admin.display.context.PageManagementToolbarDisplayContext;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.service.PageService;
import es.aragon.base.crawler.service.RootPageService;
import es.aragon.base.crawler.service.permission.PagePermissionChecker;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + CrawlerPortletKeys.CRAWLER_ADMIN,
			"mvc.command.name=/",
			"mvc.command.name=" + MVCCommandNames.VIEW_PAGES,
			"mvc.command.name=" + MVCCommandNames.VIEW_PAGE,
			"mvc.command.name=" + MVCCommandNames.DELETE_PAGE,
			"mvc.command.name=" + MVCCommandNames.DELETE_PAGES
		},
		service = MVCRenderCommand.class
	)
public class ViewPagesMVCRenderCommand implements MVCRenderCommand {

	@Reference
	protected PageService pageService;
	
	@Reference
	protected RootPageService rootPageService;
	
	@Reference
	private Portal portal;
	
	@Reference
	protected PagePermissionChecker pagePermissionChecker;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// get parameters
		long rootPageId = ParamUtil.getLong(renderRequest, "rootPageId", 0);
		String rootPageAlias = ParamUtil.getString(renderRequest, "rootPageAlias", "");
		
		// Add page list related attributes.
		addPageListAttributes(renderRequest, rootPageId);

		// Add Clay management toolbar related attributes.
		addManagementToolbarAttributes(renderRequest, renderResponse);

		PortletURL customInteratorURL = renderResponse.createRenderURL();
		customInteratorURL.setParameter("rootPageId", String.valueOf(rootPageId));
		customInteratorURL.setParameter("rootPageAlias", rootPageAlias);
		
		// Add permission checker
		renderRequest.setAttribute("pagePermissionChecker", pagePermissionChecker);
		
		renderRequest.setAttribute("locale", themeDisplay.getLocale());
		renderRequest.setAttribute("customInteratorURL", customInteratorURL);
		renderRequest.setAttribute("rootPageId", rootPageId);
		renderRequest.setAttribute("pageAlias", rootPageAlias);
		renderRequest.setAttribute("rootPageService", rootPageService);
		renderRequest.setAttribute("pageService", pageService);
		return "/view.jsp";
	}
	
	private void addPageListAttributes(RenderRequest renderRequest, long rootPageId) {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR);
		int delta = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM, SearchContainer.DEFAULT_DELTA);
		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		// Get sorting options.
		// Notice that this doesn't really sort on title because the field is
		// stored in XML
		// In real world this search would be integrated to the search engine to
		// get localized
		// sort options.
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "pageId");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");

		// Create comparator
		OrderByComparator<Page> comp = OrderByComparatorFactoryUtil.create("Page", orderByCol, !("asc").equals(orderByType));

		// Get keywords.
		// Notice that cleaning keywords is not implemented.
		String keywords = ParamUtil.getString(renderRequest, "keywords");
		
		// Call the service to get the list of pages.
		List<Page> pages = pageService.getPagesByKeywordsByRootPage(themeDisplay.getCompanyId(), keywords, start, end, comp, rootPageId);

		// Set request attributes.
		renderRequest.setAttribute("pages", pages);
		renderRequest.setAttribute("pagesCount", pageService.getPagesCountByKeywordsByRootPage(themeDisplay.getCompanyId(), keywords, rootPageId));
	}
	
	private void addManagementToolbarAttributes(RenderRequest renderRequest, RenderResponse renderResponse) {

		LiferayPortletRequest liferayPortletRequest = portal.getLiferayPortletRequest(renderRequest);
		LiferayPortletResponse liferayPortletResponse = portal.getLiferayPortletResponse(renderResponse);

		PageManagementToolbarDisplayContext pageManagementToolbarDisplayContext = new PageManagementToolbarDisplayContext(
				liferayPortletRequest, liferayPortletResponse,
				portal.getHttpServletRequest(renderRequest),
				pagePermissionChecker);

		renderRequest.setAttribute("pageManagementToolbarDisplayContext", pageManagementToolbarDisplayContext);
	}
}
