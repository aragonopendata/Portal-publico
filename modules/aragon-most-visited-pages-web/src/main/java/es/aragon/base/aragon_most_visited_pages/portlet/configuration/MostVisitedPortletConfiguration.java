package es.aragon.base.aragon_most_visited_pages.portlet.configuration;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.ProcessAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_most_visited_pages.constants.AragonMostVisitedPagesWebPortletKeys;
import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;
import es.aragon.base.aragon_most_visited_pages.schedule.MostVisitedSchedulerListener;
import es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalService;
import es.aragon.base.aragon_most_visited_pages.util.MostVisitedPagesUtil;

@Component(
	immediate = true, 
	property = (
		"javax.portlet.name=" + AragonMostVisitedPagesWebPortletKeys.ARAGON_MOST_VISITED_PAGES_WEB_PORTLET_NAME
	),
	service = ConfigurationAction.class
)
public class MostVisitedPortletConfiguration extends DefaultConfigurationAction {
	
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		httpServletRequest.setAttribute("mostVisitedPageLocalService", _mostVisitedPageLocalService);
		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}
	
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		List<MostVisitedPage> pages = _mostVisitedPageLocalService.getMostVisitedPages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		int count=0;
		String variable = ParamUtil.getString(actionRequest, "variable");
		Log _log = LogFactoryUtil.getLog(MostVisitedSchedulerListener.class);
	
		if(variable.equals("variable")) {
			_log.info("INICIADO PROCESO DE IMPORTACION A BBDD DE PAGINAS MAS VISITADAS");
			MostVisitedPagesUtil.addBBDD();
			_log.info("FINALIZADO PROCESO DE IMPORTACION DE PAGINAS MAS VISITADAS");
			
		}else {
		
			if (pages != null && !pages.isEmpty()) {
				for (MostVisitedPage mvp : pages) {
					
					count++;
					Boolean visible = ParamUtil.getBoolean(actionRequest, "check"+mvp.getMostVisitedPageId(), true);
				
					String title = ParamUtil.getString(actionRequest, "label"+mvp.getMostVisitedPageId(), "");
					
					mvp.setVisible(visible);
					mvp.setTitle(title);
					_mostVisitedPageLocalService.updateMostVisitedPage(mvp);
				}
			}
			
		}
		
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
	
	
	
	@Reference
	private MostVisitedPageLocalService _mostVisitedPageLocalService;
	
}
