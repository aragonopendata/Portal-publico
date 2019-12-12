package es.aragon.base.categories_custom_properties.configuration.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_custom_properties.constants.CategoriesCustomPropertiesPortletKeys;

@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=302",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CATEGORIZATION
	},
	service = PanelApp.class
)
public class CategoriesCustomPropertiesPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CategoriesCustomPropertiesPortletKeys.CATEGORIES_CUSTOM_PROPERTIES_PORTLET_NAME;
	}
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + CategoriesCustomPropertiesPortletKeys.CATEGORIES_CUSTOM_PROPERTIES_PORTLET_NAME + ")",
		unbind = "-"
	)	
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}
