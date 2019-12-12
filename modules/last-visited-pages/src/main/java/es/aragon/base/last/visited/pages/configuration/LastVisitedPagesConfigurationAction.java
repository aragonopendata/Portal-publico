package es.aragon.base.last.visited.pages.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.last.visited.pages.constants.LastVisitedPagesPortletKeys;

@Component(
	immediate = true,
	property = (
		"javax.portlet.name=" + LastVisitedPagesPortletKeys.LAST_VISITED_PAGES_PORTLET
	),
	service = ConfigurationAction.class
)
public class LastVisitedPagesConfigurationAction extends DefaultConfigurationAction{

}
