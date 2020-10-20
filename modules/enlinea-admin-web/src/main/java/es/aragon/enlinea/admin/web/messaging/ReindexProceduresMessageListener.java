package es.aragon.enlinea.admin.web.messaging;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.admin.web.search.DocumentIndexer;
import es.aragon.enlinea.admin.web.search.ProcedureIndexer;
import es.aragon.enlinea.db.connection.dao.Document;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	service = ReindexProceduresMessageListener.class
)
public class ReindexProceduresMessageListener extends BaseMessageListener {
	
	private static Log log = LogFactoryUtil.getLog(ReindexProceduresMessageListener.class);
	
	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Started procedures reindex process");
		try {
			long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
			ProcedureIndexer indexer = (ProcedureIndexer) IndexerRegistryUtil.getIndexer(Procedure.class);
			indexer.reindex(new String[] { String.valueOf(companyId) });
		} catch(PortalException e) {
			log.error("Error reindexing procedures", e);
		}
		log.info("Finished procedures reindex process");
		
		log.info("Started documents reindex process");
		try {
			long companyId = companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId();
			DocumentIndexer indexer = (DocumentIndexer) IndexerRegistryUtil.getIndexer(Document.class);
			indexer.reindex(new String[] { String.valueOf(companyId) });
		} catch(PortalException e) {
			log.error("Error reindexing documents", e);
		}
		log.info("Finished documents reindex process");
	}
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();
		
		Trigger trigger = triggerFactory.createTrigger(
			className, className, new Date(), null, CRON_EXPRESSION);
		
		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}
	
	@Deactivate
	protected void deactivate() {
		schedulerEngineHelper.unregister(this);
	}
	
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
		// Necessary for waiting to the portal to be initialized
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		this.schedulerEngineHelper = schedulerEngineHelper;
	}
	
	private static final String CRON_EXPRESSION = "0 0 2,8,10,12 ? * *"; // Everyday at 4, 8, 10 and 12 (UTC+0)
	
	private SchedulerEngineHelper schedulerEngineHelper;

	@Reference
	private CompanyLocalService companyLocalService;
	@Reference
	private TriggerFactory triggerFactory;

}
