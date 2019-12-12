package es.aragon.base.social_network.web.portlet.application.list;

import es.aragon.base.social_network.web.portlet.constants.SocialNetworkWebPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alex
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION
	},
	service = PanelApp.class
)
public class SocialNetworkWebPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return SocialNetworkWebPortletKeys.SOCIAL_NETWORK_ADMIN_PORTLET;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + SocialNetworkWebPortletKeys.SOCIAL_NETWORK_ADMIN_PORTLET + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}