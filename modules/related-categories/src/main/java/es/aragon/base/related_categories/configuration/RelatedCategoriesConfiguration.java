package es.aragon.base.related_categories.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import es.aragon.base.related.categories.constants.RelatedCategoriesPortletKeys;


@Component(
		immediate = true, 
		property = (
			"javax.portlet.name=" + RelatedCategoriesPortletKeys.RelatedCategories
		),
		service = ConfigurationAction.class
	)
	public class RelatedCategoriesConfiguration extends DefaultConfigurationAction {
	
	
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
	
		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}
	
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		super.processAction(portletConfig, actionRequest, actionResponse);
	
	}

}
