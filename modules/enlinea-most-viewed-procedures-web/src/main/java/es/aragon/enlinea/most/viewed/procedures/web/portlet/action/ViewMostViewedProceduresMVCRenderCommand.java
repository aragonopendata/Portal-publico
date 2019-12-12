package es.aragon.enlinea.most.viewed.procedures.web.portlet.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
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
import es.aragon.enlinea.most.viewed.procedures.web.constants.EnlineaMostViewedProceduresPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaMostViewedProceduresPortletKeys.ENLINEA_MOST_VIEWED_PROCEDURES_WEB_PORTLET,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class ViewMostViewedProceduresMVCRenderCommand implements MVCRenderCommand {
	
	private static Log log = LogFactoryUtil.getLog(ViewMostViewedProceduresMVCRenderCommand.class);
	private final static String SEARCHER_EXPANDO = "searcher-url";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Layout layout = themeDisplay.getLayout(); 
		String topicName = StringPool.BLANK;
		String topicalityURL = StringPool.BLANK;
		String documentsURL = StringPool.BLANK;
		List<Procedure> procedures = new ArrayList<>();
		if(layout.getExpandoBridge().hasAttribute("topic-category-id")) {
			long categoryId = GetterUtil.getLong(layout.getExpandoBridge().getAttribute("topic-category-id", false), 0);
			if(categoryId != 0) {
				HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
				procedures = enlineaDBService.getProceduresByTopic(httpRequest, categoryId, 5);
				AssetCategory category = assetCategoryLocalService.fetchAssetCategory(categoryId);
				if(Validator.isNotNull(category)) {
					topicName = category.getTitle(themeDisplay.getLocale());
					if(layout.getGroup().getExpandoBridge().hasAttribute(SEARCHER_EXPANDO)) {
						String searcherURL = StringPool.BLANK;
						if(Validator.isNotNull(layout.getGroup().getExpandoBridge().getAttribute(SEARCHER_EXPANDO, false))) {
							searcherURL = String.valueOf(layout.getGroup().getExpandoBridge().getAttribute(SEARCHER_EXPANDO, false));
						}
						if(!searcherURL.isEmpty()) {
							Layout searcherLayout = layoutLocalService.fetchLayoutByFriendlyURL(layout.getGroupId(), false, searcherURL);
							if (Validator.isNotNull(searcherLayout)) {
								PortletPreferences portletPreferences = renderRequest.getPreferences();
								long[] presentFilters = StringUtil.split(portletPreferences.getValue("presentFilters", ""), 0L);
								long[] documentFilters = StringUtil.split(portletPreferences.getValue("documentFilters", ""), 0L);
								topicalityURL = getSearcherURL(renderRequest, searcherLayout.getPlid(), categoryId, presentFilters).toString();
								documentsURL = getSearcherURLToDocuments(renderRequest, searcherLayout.getPlid(), categoryId, documentFilters).toString();
							}
						}
					}
				}
			}
		}
		String portletId = "es_aragon_enlinea_procedure_web_portlet_EnlineaProcedurePortlet";
	    try {
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), portletId);
			renderRequest.setAttribute("portletNameProcedureWeb", portletId);
			renderRequest.setAttribute("plidProcedureWeb", plid);
		} catch (PortalException e) {
			log.error("Error getting default plid of portlet " + portletId);
		}
		renderRequest.setAttribute("procedures", procedures);
		renderRequest.setAttribute("topicName", topicName);
		renderRequest.setAttribute("topicalityURL", topicalityURL);
		renderRequest.setAttribute("documentsURL", documentsURL);
		return "/view.jsp";
	}
	
	private PortletURL getSearcherURL(RenderRequest renderRequest, long plid, long relatedCategoryId, long[] filters) {
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
    	StringBuilder selectedCategories = new StringBuilder(String.valueOf(relatedCategoryId));
    	for(long filter : filters) {
    		selectedCategories.append("-" + filter);
    	}
    	portletURL.setParameter("searchType", "any");
    	portletURL.setParameter("selectedCategories", selectedCategories.toString());
    	portletURL.setParameter("currentPage", "0");
    	return portletURL;
	}
	
	private PortletURL getSearcherURLToDocuments(RenderRequest renderRequest, long plid, long relatedCategoryId, long[] filters) {
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
    	StringBuilder selectedCategories = new StringBuilder(String.valueOf(relatedCategoryId));
    	for(long filter : filters) {
    		portletURL.setParameter("searchType", String.valueOf(filter));
    	}
    	portletURL.setParameter("selectedCategories", selectedCategories.toString());
    	portletURL.setParameter("currentPage", "0");
    	return portletURL;
	}
	
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	
	@Reference
	private EnlineaDBService enlineaDBService;
	
	@Reference
	private LayoutLocalService layoutLocalService;

}
