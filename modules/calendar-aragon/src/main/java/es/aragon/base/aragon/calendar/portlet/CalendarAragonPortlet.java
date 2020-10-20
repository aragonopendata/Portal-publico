package es.aragon.base.aragon.calendar.portlet;

import es.aragon.base.aragon.calendar.constants.CalendarConstants;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author migarcia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.config-template=/calendar/config.jsp",
		"javax.portlet.init-param.view-template=/calendar/view.jsp",
		"javax.portlet.name=" + CalendarConstants.CALENDAR_PORTLET_KEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CalendarAragonPortlet extends MVCPortlet {
}