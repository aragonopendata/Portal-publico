package es.aragon.base.script_utilities.configuration.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.script_utilities.constants.AragonScriptUtilitiesPortletKeys;


/**
 * @author migarcia
 * Panel app for Aragon Script Utilities
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=201",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION
	},
	service = PanelApp.class
)
public class AragonScriptUtilitiesPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return AragonScriptUtilitiesPortletKeys.ARAGON_SCRIPT_UTILITIES_PORTLET_NAME;
	}
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + AragonScriptUtilitiesPortletKeys.ARAGON_SCRIPT_UTILITIES_PORTLET_NAME + ")",
		unbind = "-"
	)	
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
	
}
