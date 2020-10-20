package es.aragon.enlinea.admin.web.portlet;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.db.connection.api.EnlineaDBService;
import es.aragon.enlinea.db.connection.dao.Procedure;

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
	private static String[] columnNames = { "Signatura", "Denominacion", "FriendlyURL Actual", "FriendlyURL Nueva"};
	
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
		// Necessary for waiting to the portal to be initialized
	}
	
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
	
	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		
		Map<Procedure, String> report = enlineaDBService.getProcedureReport();
		try {
			StringBundler sb = new StringBundler();
			for (String columnName : columnNames) {
			sb.append(getCSVFormattedValue(columnName));
			sb.append(",");
			}
			sb.setIndex(sb.index() - 1);
			sb.append(CharPool.NEW_LINE);
			for(Map.Entry<Procedure, String> entry : report.entrySet()) {
				sb.append(getCSVFormattedValue(String.valueOf(entry.getKey().getProcedureId())));
				sb.append(",");
				sb.append(getCSVFormattedValue(String.valueOf(entry.getKey().getName())));
				sb.append(",");
				sb.append(getCSVFormattedValue(String.valueOf(entry.getValue())));
				sb.append(",");
				sb.append(getCSVFormattedValue(String.valueOf(entry.getKey().getFriendlyURL())));
				sb.append(",");
				sb.setIndex(sb.index() - 1);
				sb.append(CharPool.NEW_LINE);
			}
			String fileName = "procedureReport.csv";
			byte[] bytes = sb.toString().getBytes();
			PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, bytes,  ContentTypes.TEXT_PLAIN_UTF8);
		} catch(IOException e) {
			e.printStackTrace();
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	protected String getCSVFormattedValue(String value) {
		StringBundler sb = new StringBundler(3);
		sb.append(CharPool.QUOTE);
		sb.append(StringUtil.replace(value, CharPool.QUOTE, StringPool.DOUBLE_QUOTE));
		sb.append(CharPool.QUOTE);
		return sb.toString();
	}
	
	@Reference
	private EnlineaDBService enlineaDBService;
		
}