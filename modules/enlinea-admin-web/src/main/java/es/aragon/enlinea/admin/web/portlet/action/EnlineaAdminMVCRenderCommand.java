package es.aragon.enlinea.admin.web.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.db.connection.api.EnlineaDBService;
import es.aragon.enlinea.db.connection.dao.Document;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class EnlineaAdminMVCRenderCommand implements MVCRenderCommand {
	
	private static Log log = LogFactoryUtil.getLog(EnlineaAdminMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		String navigation = ParamUtil.getString(renderRequest, "navigation");
		if(navigation.equals("procedures")) {
			String proceduresCount = enlineaDBService.getProceduresCount();
			renderRequest.setAttribute("proceduresCount", proceduresCount);
			String documentsCount = enlineaDBService.getDocumentsCount();
			renderRequest.setAttribute("documentsCount", documentsCount);
			
			HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
			SearchContext searchContext = SearchContextFactory.getInstance(request);
			BooleanFilter filterProcedures = new BooleanFilter();
			filterProcedures.addRequiredTerm("entryClassName", Procedure.class.getName());
			BooleanQuery procedureQuery = new BooleanQueryImpl();
			procedureQuery.setPreBooleanFilter(filterProcedures);
			String elasticProceduresCount = StringPool.BLANK;
			try {
				elasticProceduresCount = String.valueOf(IndexSearcherHelperUtil.search(searchContext, procedureQuery).getLength());
			} catch (SearchException e) {
				log.error("Error obtaining procedures count from elastic");
				elasticProceduresCount = LanguageUtil.get(request, "error.error-executing-elastic-query");
			}
			renderRequest.setAttribute("elasticProceduresCount", elasticProceduresCount);
			
			BooleanFilter filterDocuments = new BooleanFilter();
			filterDocuments.addRequiredTerm("entryClassName", Document.class.getName());
			BooleanQuery documentQuery = new BooleanQueryImpl();
			documentQuery.setPreBooleanFilter(filterDocuments);
			String elasticDocumentsCount = StringPool.BLANK;
			try {
				elasticDocumentsCount = String.valueOf(IndexSearcherHelperUtil.search(searchContext, documentQuery).getLength());
			} catch (SearchException e) {
				log.error("Error obtaining document count from elastic");
				elasticDocumentsCount = LanguageUtil.get(request, "error.error-executing-elastic-query");
			}
			renderRequest.setAttribute("elasticDocumentsCount", elasticDocumentsCount);
		}
		return "/view.jsp";
	}
	
	@Reference
	private EnlineaDBService enlineaDBService;

}
