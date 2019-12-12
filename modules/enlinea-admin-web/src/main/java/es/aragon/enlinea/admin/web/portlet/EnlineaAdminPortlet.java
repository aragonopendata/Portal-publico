package es.aragon.enlinea.admin.web.portlet;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EnlineaAdminPortlet extends MVCPortlet {
	
	private static Log log = LogFactoryUtil.getLog(EnlineaAdminPortlet.class);
	
	@Activate
    public void activate() {
		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(PortalUtil.getDefaultCompanyId(), Group.class.getName());
		if(!expandoBridge.hasAttribute("procedure-url")) {
			try {
				expandoBridge.addAttribute("procedure-url", false);
			} catch (PortalException e) {
				log.error("Error creating procedure-url expando");
			}
		}
    }
	
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
		// Necessary for waiting to the portal to be initialized
	}
		
}