package es.aragon.base.migration.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.migration.constants.AragonMigrationPortletKeys;

@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION
	},
	service = PanelApp.class
)
public class MigratedContentPortletPanelApp  extends BasePanelApp{

	@Override
	public String getPortletId() {
		return AragonMigrationPortletKeys.MIGRATED_CONTENT_PORTLET_KEY;
	}
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + AragonMigrationPortletKeys.MIGRATED_CONTENT_PORTLET_KEY + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
	
}