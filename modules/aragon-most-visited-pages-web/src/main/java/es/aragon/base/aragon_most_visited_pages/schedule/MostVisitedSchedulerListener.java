package es.aragon.base.aragon_most_visited_pages.schedule;


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
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		service = MostVisitedSchedulerListener.class
	)
public class MostVisitedSchedulerListener extends BaseMessageListener  {
	private static final Log _log = LogFactoryUtil.getLog(MostVisitedSchedulerListener.class);
	private static final String _DEFAULT_CRON_EXPRESSION = "0 0 2 ? * MON"; //Cada lunes a las 4:00 A.M.
	private TriggerFactory _triggerFactory;
	private SchedulerEntryImpl _schedulerEntryImpl = null;
	private volatile boolean _initialized;
	private SchedulerEngineHelper _schedulerEngineHelper;

	
	
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("INICIADO PROCESO DE IMPORTACION A BBDD PAGINAS MAS VISITADAS ");
		new SchedulerTask().run();
		_log.info("PROCESO DE IMPORTACION FINALIZADO DE PAGINAS MAS VISITADAS");
	}
	
	@Activate	
	@Modified
	protected void activate(Map<String,Object> properties) throws SchedulerException {
	    String cronExpression = GetterUtil.getString(properties.get("cron.expression"), _DEFAULT_CRON_EXPRESSION);
	    
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


