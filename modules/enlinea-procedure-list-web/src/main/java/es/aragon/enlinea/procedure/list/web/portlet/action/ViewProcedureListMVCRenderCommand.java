package es.aragon.enlinea.procedure.list.web.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.db.connection.api.EnlineaDBService;
import es.aragon.enlinea.db.connection.dao.Procedure;
import es.aragon.enlinea.procedure.list.web.constants.EnlineaProcedureListPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaProcedureListPortletKeys.ENLINEA_PROCEDURE_LIST_WEB_PORTLET,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class ViewProcedureListMVCRenderCommand implements MVCRenderCommand {
	
	private static Log log = LogFactoryUtil.getLog(ViewProcedureListMVCRenderCommand.class);
	private final static String SEARCHER_EXPANDO = "searcher-url";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Layout layout = themeDisplay.getLayout();
		long topicCategoryId = 0;
		if(layout.getExpandoBridge().hasAttribute("topic-category-id")) {
			topicCategoryId = GetterUtil.getLong(layout.getExpandoBridge().getAttribute("topic-category-id", false), 0);
		}
		List<Procedure> procedures = new ArrayList<>();
		if(topicCategoryId != 0) {
			HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
			procedures = enlineaDBService.getProceduresByTopic(httpRequest, topicCategoryId, 10);
			if(!procedures.isEmpty()) {
				String portletId = "es_aragon_enlinea_procedure_web_portlet_EnlineaProcedurePortlet";
			    try {
					long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), portletId);
					renderRequest.setAttribute("portletNameProcedureWeb", portletId);
					renderRequest.setAttribute("plidProcedureWeb", plid);
				} catch (PortalException e) {
					log.error("Error getting default plid of portlet " + portletId);
				}
			    String viewMoreURL = StringPool.BLANK;
				if(layout.getGroup().getExpandoBridge().hasAttribute(SEARCHER_EXPANDO)) {
					String searcherURL = StringPool.BLANK;
					if(Validator.isNotNull(layout.getGroup().getExpandoBridge().getAttribute(SEARCHER_EXPANDO, false))) {
						searcherURL = String.valueOf(layout.getGroup().getExpandoBridge().getAttribute(SEARCHER_EXPANDO, false));
					}
					if(!searcherURL.isEmpty()) {
						Layout searcherLayout = layoutLocalService.fetchLayoutByFriendlyURL(layout.getGroupId(), false, searcherURL);
						if (Validator.isNotNull(searcherLayout)) {
							ClassName className = classNameLocalService.fetchClassName(Procedure.class.getName());
							if(Validator.isNull(className)) {
								className = classNameLocalService.addClassName(Procedure.class.getName());
							}
							if(Validator.isNotNull(className)) {
								viewMoreURL = getSearcherURL(renderRequest, searcherLayout.getPlid(), topicCategoryId, className.getClassNameId()).toString();
							}
						}
					}
				}
				renderRequest.setAttribute("viewMoreURL", viewMoreURL);
			} else {
				log.error("No procedures with category " + topicCategoryId);
			}
		} else {
			log.error("Layout " + layout.getName("es_ES", true) + " has not enlinea-category-id");
		}
		renderRequest.setAttribute("procedures", procedures);
		return "/view.jsp";
	}
	
	private PortletURL getSearcherURL(RenderRequest renderRequest, long plid, long categoryId, long type) {
		PortletURL portletURL =  PortletURLFactoryUtil.create(renderRequest, "es_aragon_base_search_web_SearchWebPortlet", plid, PortletRequest.RENDER_PHASE);
    	try {
    		portletURL.setWindowState(LiferayWindowState.NORMAL);
		} catch (WindowStateException e) {
			// Unable to set window state
		}
    	try {
    		portletURL.setPortletMode(LiferayPortletMode.VIEW);
		} catch (PortletModeException e) {
			// Unable to set portlet mode
		}
    	portletURL.setParameter("searchType", String.valueOf(type));
    	portletURL.setParameter("selectedCategories", String.valueOf(categoryId));
    	portletURL.setParameter("currentPage", "0");
    	return portletURL;
	}
	
	@Reference
	private ClassNameLocalService classNameLocalService;
	
	@Reference
	private EnlineaDBService enlineaDBService;
	
	@Reference
	private LayoutLocalService layoutLocalService;

}
