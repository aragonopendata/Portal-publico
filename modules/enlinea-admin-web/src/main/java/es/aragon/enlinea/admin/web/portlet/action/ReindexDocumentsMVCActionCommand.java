package es.aragon.enlinea.admin.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.admin.web.search.DocumentIndexer;
import es.aragon.enlinea.db.connection.dao.Document;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET,
		"mvc.command.name=" + EnlineaAdminPortletKeys.ACTION_REINDEX_DOCUMENTS
	},
	service = MVCActionCommand.class
)
public class ReindexDocumentsMVCActionCommand extends BaseMVCActionCommand {
	
	private static Log log = LogFactoryUtil.getLog(ReindexDocumentsMVCActionCommand.class);
	
	@Reference
	private CompanyLocalService companyLocalService;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		try {
			long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
			DocumentIndexer indexer = (DocumentIndexer) IndexerRegistryUtil.getIndexer(Document.class);
			indexer.reindex(new String[] { String.valueOf(companyId) });
		} catch(PortalException e) {
			log.error("Error reindexing documents", e);
		}
	}

}
