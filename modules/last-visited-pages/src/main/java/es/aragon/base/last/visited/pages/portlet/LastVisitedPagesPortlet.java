package es.aragon.base.last.visited.pages.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


import org.osgi.service.component.annotations.Component;

import es.aragon.base.last.visited.pages.constants.LastVisitedPagesPortletKeys;

/**
 * @author Alex
 */
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.aragon",
			"com.liferay.portlet.instanceable=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.init-param.config-template=/configuration.jsp",
			"javax.portlet.name=" + LastVisitedPagesPortletKeys.LAST_VISITED_PAGES_PORTLET,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class LastVisitedPagesPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		LinkedHashMap<String, String> lastVisitedPageMap = new LinkedHashMap();
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String valueCookie = CookieKeys.getCookie(themeDisplay.getRequest(), COOKIE_NAME);
		if(Validator.isNotNull(valueCookie) && valueCookie != "") {
			valueCookie= URLDecoder.decode(valueCookie, "UTF-8");
			String[] cookiesArray = valueCookie.split(";");
			for (int i = 0; i < cookiesArray.length; i++) {
		        String cookieEntry = cookiesArray[i];
		        while (cookieEntry.charAt(0) == ' ') {
		            cookieEntry = cookieEntry.substring(1);
		        }
		        if (cookieEntry.indexOf(COOKIE_NAME + "=") == 0) {
		        	valueCookie = cookieEntry.substring((COOKIE_NAME + "=").length(), cookieEntry.length());
		        }
		    }
			//Add entries list to the view
			if (valueCookie != "") {
				String[] visitedPagesList = valueCookie.split("\\|");
				String cookieSeparator = "[COOKIE_SEPARATOR]";
				for (int i = 0; i < visitedPagesList.length; i++) {
					String visitedPage = visitedPagesList[i];
					if (visitedPage != null) {
						visitedPage = visitedPage.trim();
						int separator = visitedPage.indexOf(cookieSeparator);
			            String visitedPageTitle = visitedPage.substring(0, separator);
			            String visitedPageURL = visitedPage.substring(separator + cookieSeparator.length(), visitedPage.length());
			            if (visitedPageURL.length() > 0 && visitedPageTitle.length() > 0 && !visitedPageURL.contains("?") && !visitedPageURL.contains("<")) {
			            	lastVisitedPageMap.put(visitedPageTitle, visitedPageURL);
		    			}
					}
				}
			}
		}
		renderRequest.setAttribute("listLastVisitedPage", lastVisitedPageMap);
		super.render(renderRequest, renderResponse);
	}
	final String COOKIE_NAME = "LAST_VISITED_PAGES";
}