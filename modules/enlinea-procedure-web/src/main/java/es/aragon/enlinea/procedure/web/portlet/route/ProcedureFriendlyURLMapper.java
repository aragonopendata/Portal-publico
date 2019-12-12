package es.aragon.enlinea.procedure.web.portlet.route;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

import es.aragon.enlinea.procedure.web.constants.EnlineaProcedurePortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
		"javax.portlet.name=" + EnlineaProcedurePortletKeys.ENLINEA_PROCEDURE_WEB_PORTLET
	},
	service = FriendlyURLMapper.class
)
public class ProcedureFriendlyURLMapper extends DefaultFriendlyURLMapper {
	
	@Override
	public String getMapping() {
		return MAPPING;
	}
	
	public static final String MAPPING = "tramite";

}
