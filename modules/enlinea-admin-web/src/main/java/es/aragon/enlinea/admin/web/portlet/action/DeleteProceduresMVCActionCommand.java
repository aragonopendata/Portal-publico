package es.aragon.enlinea.admin.web.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.admin.web.search.ProcedureIndexer;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET,
		"mvc.command.name=" + EnlineaAdminPortletKeys.ACTION_DELETE_PROCEDURES
	},
	service = MVCActionCommand.class
)
public class DeleteProceduresMVCActionCommand extends BaseMVCActionCommand {
	
	private static Log log = LogFactoryUtil.getLog(ReindexProceduresMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		log.info("Started procedure delete process");
		ProcedureIndexer indexer = (ProcedureIndexer) IndexerRegistryUtil.getIndexer(Procedure.class);
		indexer.doDeleteAll();
		log.info("Finished procedure delete process");
	}

}
