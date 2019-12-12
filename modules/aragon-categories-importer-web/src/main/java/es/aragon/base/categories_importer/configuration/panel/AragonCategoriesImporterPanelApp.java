package es.aragon.base.categories_importer.configuration.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_importer.constants.AragonCategoriesImporterPortletKeys;

/**
 * @author pfalcon
 * Panel app for Aragon categories importer
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=301",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CATEGORIZATION
	},
	service = PanelApp.class
)
public class AragonCategoriesImporterPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return AragonCategoriesImporterPortletKeys.ARAGON_CATEGORIES_IMPORTER_PORTLET_NAME;
	}
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + AragonCategoriesImporterPortletKeys.ARAGON_CATEGORIES_IMPORTER_PORTLET_NAME + ")",
		unbind = "-"
	)	
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
	
}
