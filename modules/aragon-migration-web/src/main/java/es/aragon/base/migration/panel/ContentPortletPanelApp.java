package es.aragon.base.migration.panel;

import com.liferay.application.list.BaseJSPPanelCategory;
import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ContentPortletPanelApp extends  BasePanelApp{

	@Override
	public String getPortletId() {
		return AragonMigrationPortletKeys.CONTENT_PORTLET_KEY;
	}
	
	
	@Override
	@Reference(
		target = "(javax.portlet.name=" + AragonMigrationPortletKeys.CONTENT_PORTLET_KEY + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
	

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return AragonMigrationPortletKeys.CONTENT_PORTLET_KEY;
	}
	
}
