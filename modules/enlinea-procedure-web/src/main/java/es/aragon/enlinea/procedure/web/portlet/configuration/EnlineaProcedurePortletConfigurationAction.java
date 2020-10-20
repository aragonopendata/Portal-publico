package es.aragon.enlinea.procedure.web.portlet.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import es.aragon.enlinea.procedure.web.constants.EnlineaProcedurePortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
    configurationPid = "es.veci.login.web.portlet.configuration.LoginWebPortletConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaProcedurePortletKeys.ENLINEA_PROCEDURE_WEB_PORTLET
	},
	service = ConfigurationAction.class
)
public class EnlineaProcedurePortletConfigurationAction extends DefaultConfigurationAction {
	
	private volatile EnlineaProcedurePortletConfiguration enlineaProcedurePortletConfiguration;
	
	@Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		PortletPreferences preferences = actionRequest.getPreferences();
        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, "presential");
        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, "presentialAndOnline");
        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, "online");
        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, "moreInformation");
        preferences.store();
        super.processAction(portletConfig, actionRequest, actionResponse);
    }
	
	@Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setAttribute(EnlineaProcedurePortletConfiguration.class.getName(), enlineaProcedurePortletConfiguration);
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }
	
	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
		enlineaProcedurePortletConfiguration = ConfigurableUtil.createConfigurable(EnlineaProcedurePortletConfiguration.class, properties);
    }

}
