package es.aragon.base.aragon_custom_navigation_menus.portlet.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.aragon_custom_navigation_menus.constants.AragonCustomNavigationMenusPortletKeys;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AragonCustomNavigationMenusPortletKeys.ARAGON_CUSTOM_NAVIGATION_MENUS
		},
		service = ConfigurationAction.class
	)
public class AragonCustomNavigationMenusPortletConfigurationAction extends DefaultConfigurationAction {

}
