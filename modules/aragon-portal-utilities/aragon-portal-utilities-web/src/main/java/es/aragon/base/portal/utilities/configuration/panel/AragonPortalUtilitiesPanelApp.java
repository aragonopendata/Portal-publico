package es.aragon.base.portal.utilities.configuration.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;

/**
 * 
 * @author jrambla
 *
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=500",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
	},
	service = PanelApp.class
)
public class AragonPortalUtilitiesPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES;
	}
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES + ")",
		unbind = "-"
	)	
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
	
}
