package es.aragon.enlinea.admin.web.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.ServletContext;

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
	property = "javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET,
	service = AssetRendererFactory.class
)
public class ProcedureAssetRendererFactory extends BaseAssetRendererFactory<Procedure> {
	
	public ProcedureAssetRendererFactory() {
		setClassName(Procedure.class.getName());
		setLinkable(true);
		setPortletId(EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Procedure> getAssetRenderer(long classPK, int type) throws PortalException {
		ProcedureAssetRenderer procedureAssetRenderer =
			new ProcedureAssetRenderer(classPK, this);
		procedureAssetRenderer.setAssetRendererType(type);
		procedureAssetRenderer.setServletContext(servletContext);
		return procedureAssetRenderer;
	}
	
	@Override
	public String getType() {
		return "procedure";
	}
	
	public EnlineaDBService getEnlineaDBService() {
		return enlineaDBService;
	}
	
	@Reference(target = "(osgi.web.symbolicname=es.aragon.enlinea.admin.web)")
	private ServletContext servletContext;
	
	@Reference
	private EnlineaDBService enlineaDBService;

}
