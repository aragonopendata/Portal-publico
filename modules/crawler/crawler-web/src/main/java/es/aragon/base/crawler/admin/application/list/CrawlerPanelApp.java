package es.aragon.base.crawler.admin.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.admin.constants.CrawlerPortletKeys;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=200",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION
	},
	service = PanelApp.class
)
public class CrawlerPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CrawlerPortletKeys.CRAWLER_ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CrawlerPortletKeys.CRAWLER_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
}
