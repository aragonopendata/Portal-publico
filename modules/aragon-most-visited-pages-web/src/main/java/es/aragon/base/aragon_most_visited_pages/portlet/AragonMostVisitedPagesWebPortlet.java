package es.aragon.base.aragon_most_visited_pages.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_most_visited_pages.constants.AragonMostVisitedPagesWebPortletKeys;
import es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalService;
import es.aragon.base.aragon_most_visited_pages.util.MostVisitedPagesUtil;

/**
 * @author migarcia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + AragonMostVisitedPagesWebPortletKeys.ARAGON_MOST_VISITED_PAGES_WEB_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonMostVisitedPagesWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		renderRequest.setAttribute("mostVisitedPageLocalService", _mostVisitedPageLocalService);
		super.render(renderRequest, renderResponse);
	}

	@ProcessAction(name = "addMostVisitedPage")
	public void addMostVisitedPages(ActionRequest actionRequest, ActionResponse actionResponse) {
		MostVisitedPagesUtil.addBBDD();
	}
			
	
	@Reference
	private MostVisitedPageLocalService _mostVisitedPageLocalService;
	
}
