/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package es.aragon.base.semaphore.service.impl;

import com.liferay.document.library.kernel.util.comparator.RepositoryModelModifiedDateComparator;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import es.aragon.base.semaphore.exception.NoSuchSemaphoreException;
import es.aragon.base.semaphore.model.Semaphore;
import es.aragon.base.semaphore.service.base.SemaphoreLocalServiceBaseImpl;

/**
 * The implementation of the semaphore local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.semaphore.service.SemaphoreLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SemaphoreLocalServiceBaseImpl
 * @see es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil
 */
public class SemaphoreLocalServiceImpl extends SemaphoreLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil} to access the semaphore local service.
	 */
	
	public Boolean semaphoreWait(long groupId, String serviceName) {
		Semaphore semaphore = semaphorePersistence.fetchByServiceNameGroupId_First(groupId, serviceName, null);

		return semaphoreWait(semaphore);
	}
	
	public Boolean semaphoreWait(Semaphore inputSemaphore) {
		Semaphore semaphore = semaphorePersistence.fetchByServiceNameGroupId_First(inputSemaphore.getGroupId(), inputSemaphore.getServiceName(), null);
		if(Validator.isNotNull(semaphore)) {
			if((semaphore.getCurrentUsers() + 1 ) <= semaphore.getMaxUsers()) {
				semaphore.setCurrentUsers(semaphore.getCurrentUsers() + 1);
				semaphoreLocalService.updateSemaphore(semaphore);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public Boolean semaphoreSignal(long groupId, String serviceName) {
		Semaphore semaphore = semaphorePersistence.fetchByServiceNameGroupId_First(groupId, serviceName, null);
		
		return semaphoreSignal(semaphore);
	}
	
	public Boolean semaphoreSignal(Semaphore inputSemaphore) {
		Semaphore semaphore = semaphorePersistence.fetchByServiceNameGroupId_First(inputSemaphore.getGroupId(), inputSemaphore.getServiceName(), null);
		if(Validator.isNotNull(semaphore)) {
			if((semaphore.getCurrentUsers() - 1 ) >= 0) {
				semaphore.setCurrentUsers(semaphore.getCurrentUsers() - 1);
				semaphoreLocalService.updateSemaphore(semaphore);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public Semaphore addSemaphore(long groupId, String serviceName, long maxUsers) {
		Semaphore semaphore = semaphorePersistence.fetchByServiceNameGroupId_First(groupId, serviceName, null);
		if(Validator.isNotNull(semaphore)) {
			return semaphore;
		} else {
			long semaphoreId = counterLocalService.increment(Semaphore.class.getName());
			semaphore = semaphorePersistence.create(semaphoreId);
			semaphore.setGroupId(groupId);
			semaphore.setServiceName(serviceName);
			semaphore.setMaxUsers(maxUsers);
			semaphore.setCurrentUsers(0);
			return semaphorePersistence.update(semaphore);
		}
	}
	
	public Semaphore getSemaphore(long groupId, String serviceName) throws NoSuchSemaphoreException {
		return semaphorePersistence.findByServiceNameGroupId_First(groupId, serviceName, null);
	}
	
	public int getSemaphoresCount(long groupId) {
		return semaphorePersistence.countByGroupId(groupId);
	}
	
	public List<Semaphore> getSemaphores(long groupId, int start, int end) {
		return getSemaphores(groupId, start, end, null);
	}
	
	public List<Semaphore> getSemaphores(long groupId, int start, int end, OrderByComparator<Semaphore> orderByComparator) {
		return semaphorePersistence.findByGroupId(groupId, start, end, orderByComparator);
	}
}