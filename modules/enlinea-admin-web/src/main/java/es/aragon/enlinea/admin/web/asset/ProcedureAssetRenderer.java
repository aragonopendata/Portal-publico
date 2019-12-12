package es.aragon.enlinea.admin.web.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
public class ProcedureAssetRenderer extends BaseJSPAssetRenderer<Procedure> {
	
	public ProcedureAssetRenderer(long procedureId, 
			ProcedureAssetRendererFactory procedureAssetRendererFactory) {
		this.procedureId = procedureId;
		this.procedure = null;
		this.procedureAssetRendererFactory = procedureAssetRendererFactory;
	}
	
	@Override
	public Procedure getAssetObject() {
		return procedure;
	}
	
	@Override
	public String getClassName() {
		return Procedure.class.getName();
	}
	
	@Override
	public long getClassPK() {
		return procedure.getProcedureId();
	}
	
	@Override
	public long getGroupId() {
		return procedure.getGroupId();
	}

	@Override
	public long getUserId() {
		return procedure.getUserId();
	}

	@Override
	public String getUserName() {
		return procedure.getUserName();
	}

	@Override
	public String getUuid() {
		return "0";
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		return HtmlUtil.stripHtml(StringUtil.shorten(procedure.getName(), 200));
	}

	@Override
	public String getTitle(Locale locale) {
		return procedure.getName();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		return null;
	}
	
	@Override
	public boolean include(HttpServletRequest request, 
			HttpServletResponse response, String template) throws Exception {
		procedure = 
				this.procedureAssetRendererFactory.getEnlineaDBService().getProcedure(request, procedureId);
		request.setAttribute("procedure", procedure);
		return super.include(request, response, template);
	}
	
	private long procedureId;
	private Procedure procedure;
	private ProcedureAssetRendererFactory procedureAssetRendererFactory;

}
