package es.aragon.base.aragon_custom_navigation_menus.portlet;

import es.aragon.base.aragon_custom_navigation_menus.constants.AragonCustomNavigationMenusPortletKeys;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + AragonCustomNavigationMenusPortletKeys.ARAGON_CUSTOM_NAVIGATION_MENUS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonCustomNavigationMenusPortlet extends MVCPortlet{
	



}