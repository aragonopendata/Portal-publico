package es.aragon.base.related_contents.portlet.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.related_contents.constants.RelatedContentsConstants;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + RelatedContentsConstants.RELATED_CONTENTS_PORTLET_NAME
	},
	service = ConfigurationAction.class
)
public class RelatedContentsPortletConfigurationAction extends DefaultConfigurationAction {
	
}
