package es.aragon.base.portal.utilities.scheduler;

import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;

public class StorageTypeAwareSchedulerEntryImpl extends SchedulerEntryImpl implements SchedulerEntry, StorageTypeAware{

	/**
	 * StorageTypeAwareSchedulerEntryImpl: Constructor for the class.
	 * @param schedulerEntry
	 */
	public StorageTypeAwareSchedulerEntryImpl(final SchedulerEntryImpl schedulerEntry) {
		super(schedulerEntry.getEventListenerClass(), schedulerEntry.getTrigger());
		_schedulerEntry = schedulerEntry;
		_storageType = StorageType.PERSISTED;
	}

	/**
	 * StorageTypeAwareSchedulerEntryImpl: Constructor for the class.
	 * @param schedulerEntry
	 * @param storageType
	 */
	public StorageTypeAwareSchedulerEntryImpl(final SchedulerEntryImpl schedulerEntry, final StorageType storageType) {
		super(schedulerEntry.getEventListenerClass(), schedulerEntry.getTrigger());
		_schedulerEntry = schedulerEntry;
		_storageType = storageType;
	}

	@Override
	public String getDescription() {
		return _schedulerEntry.getDescription();
	}

	@Override
	public String getEventListenerClass() {
		return _schedulerEntry.getEventListenerClass();
	}

	@Override
	public StorageType getStorageType() {
		return _storageType;
	}

	@Override
	public Trigger getTrigger() {
		return _schedulerEntry.getTrigger();
	}

	private SchedulerEntryImpl _schedulerEntry;
	private StorageType _storageType;
	private static final long serialVersionUID = 1L;
}
