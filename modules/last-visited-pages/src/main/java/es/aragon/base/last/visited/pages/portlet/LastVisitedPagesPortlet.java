package es.aragon.base.last.visited.pages.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

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
}