package es.aragon.base.portal.utilities.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;

import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;

/**
 * @author jrambla
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonPortalUtilitiesPortlet extends MVCPortlet {
	
}