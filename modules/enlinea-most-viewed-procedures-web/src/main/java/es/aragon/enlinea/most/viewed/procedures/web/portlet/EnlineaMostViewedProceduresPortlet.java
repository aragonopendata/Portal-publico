package es.aragon.enlinea.most.viewed.procedures.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import es.aragon.enlinea.most.viewed.procedures.web.constants.EnlineaMostViewedProceduresPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.enlinea",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EnlineaMostViewedProceduresPortletKeys.ENLINEA_MOST_VIEWED_PROCEDURES_WEB_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EnlineaMostViewedProceduresPortlet extends MVCPortlet {
}