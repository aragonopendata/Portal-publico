package es.aragon.base.crawler.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		service = ReCrawlRootPagesSchedulerListener.class
	)
public class ReCrawlRootPagesSchedulerListener extends BaseMessageListener {
	private static final Log _log = LogFactoryUtil.getLog(ReCrawlRootPagesSchedulerListener.class);
	//private static final String _DEFAULT_CRON_EXPRESSION = "0 0/30 * 1/1 * ? *"; //cada 30 minuto
	private static final String _DEFAULT_CRON_EXPRESSION = "0 0 1 ? * SAT *"; //cada dia a las 6.00 A.M.
	private TriggerFactory _triggerFactory;
	private SchedulerEntryImpl _schedulerEntryImpl = null;
	private volatile boolean _initialized;
	private SchedulerEngineHelper _schedulerEngineHelper;
	
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("INICIO PROCESO RE CRAWLING");
		new SchedulerTaskReCrawl().run();
		_log.info("FINALIZACION PROCESO RE CRAWLING");
	}
	
	@Activate	
	@Modified
	protected void activate(Map<String,Object> properties) throws SchedulerException {
	    String cronExpression = _DEFAULT_CRON_EXPRESSION;
	    String listenerClass = getClass().getName();
	    Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);
	    _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
	    _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.PERSISTED);

	    if (_initialized) {
	      deactivate();
	    }

	    _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	    _initialized = true;
	}
	@Deactivate
	protected void deactivate() {
		if (_initialized) {
			try {
				_schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
			} catch (SchedulerException se) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to unschedule trigger", se);
				}
			}
			_schedulerEngineHelper.unregister(this);
		}
	    _initialized = false;
	}
	protected StorageType getStorageType() {
		if(_schedulerEntryImpl instanceof StorageTypeAware) {
			return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    return StorageType.PERSISTED;
	}
	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	    _triggerFactory = triggerFactory;
	}
	
	@Reference(unbind = "-")
	public void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		this._schedulerEngineHelper = schedulerEngineHelper;
	}




}
