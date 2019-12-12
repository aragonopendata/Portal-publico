package es.aragon.base.resources.importer.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.resources.importer.constants.ResourcesImporterPortletKeys;

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
public class ResourcesImporterPanelApp extends BasePanelApp{

	@Override
	public String getPortletId() {
		return ResourcesImporterPortletKeys.ResourcesImporter;
	}
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + ResourcesImporterPortletKeys.ResourcesImporter + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
}
