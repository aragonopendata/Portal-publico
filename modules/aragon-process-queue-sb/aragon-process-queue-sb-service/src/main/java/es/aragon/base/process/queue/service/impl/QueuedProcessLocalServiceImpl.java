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

package es.aragon.base.process.queue.service.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import es.aragon.base.process.queue.model.QueuedProcess;
import es.aragon.base.process.queue.service.base.QueuedProcessLocalServiceBaseImpl;

/**
 * The implementation of the queued process local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.process.queue.service.QueuedProcessLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessLocalServiceBaseImpl
 * @see es.aragon.base.process.queue.service.QueuedProcessLocalServiceUtil
 */
public class QueuedProcessLocalServiceImpl
	extends QueuedProcessLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link es.aragon.base.process.queue.service.QueuedProcessLocalServiceUtil} to access the queued process local service.
	 */
	
	/**
	 * @param groupId
	 * @param companyId
	 * @param user
	 * @param processName
	 * @param jsonParameters - String
	 * @return new QueuedProcess
	 */
	public QueuedProcess addQueuedProcess(long groupId, long companyId, User user, String processName, String jsonParameters) {
		long queuedProcessId = counterLocalService.increment(QueuedProcess.class.getName());
		QueuedProcess queuedProcess = queuedProcessPersistence.create(queuedProcessId);
		
		queuedProcess.setGroupId(groupId);
		queuedProcess.setCompanyId(companyId);
		queuedProcess.setUserId(user.getUserId());
		queuedProcess.setUserName(user.getScreenName());
		queuedProcess.setCreateDate(new Date());
		queuedProcess.setExecutionStartedDate(new Date());
		queuedProcess.setExecutionFinishedDate(new Date());
		
		queuedProcess.setProcessName(processName);
		queuedProcess.setJsonParameters(jsonParameters);
		queuedProcess.setStatus(2);
		
		return queuedProcessPersistence.update(queuedProcess);
	}
	
	/**
	 * @param groupId
	 * @param companyId
	 * @param user
	 * @param processName
	 * @param jsonParameters - JsonObject
	 * @return new QueuedProcess
	 */
	public QueuedProcess addQueuedProcess(long groupId, long companyId, User user, String processName, JSONObject jsonParameters) {
		return addQueuedProcess(groupId, companyId, user, processName, jsonParameters.toJSONString());
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @return
	 */
	public List<QueuedProcess> fetchPendingQueuedProcess(long groupId, String processName) {
		return fetchPendingQueuedProcess(groupId, processName, -1, -1, null);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @param orderByComparator
	 * @return
	 */
	public List<QueuedProcess> fetchPendingQueuedProcess(long groupId, String processName, OrderByComparator<QueuedProcess> orderByComparator) {
		return fetchPendingQueuedProcess(groupId, processName, -1, -1, orderByComparator);
	}

	/**
	 * @param groupId
	 * @param processName
	 * @param start
	 * @param end
	 * @param orderByComparator
	 * @return
	 */
	public List<QueuedProcess> fetchPendingQueuedProcess(long groupId, String processName, int start, int end, OrderByComparator<QueuedProcess> orderByComparator) {
		return queuedProcessPersistence.findByProcessNameGroupIdStatus(groupId, processName, 2, start, end, orderByComparator);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @return
	 */
	public int fetchPendingQueuedProcessCount(long groupId, String processName) {
		return queuedProcessPersistence.countByProcessNameGroupIdStatus(groupId, processName, 2);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @return
	 */
	public List<QueuedProcess> fetchExecutingQueuedProcess(long groupId, String processName) {
		return fetchExecutingQueuedProcess(groupId, processName, -1, -1, null);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @param orderByComparator
	 * @return
	 */
	public List<QueuedProcess> fetchExecutingQueuedProcess(long groupId, String processName, OrderByComparator<QueuedProcess> orderByComparator) {
		return fetchExecutingQueuedProcess(groupId, processName, -1, -1, orderByComparator);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @param start
	 * @param end
	 * @param orderByComparator
	 * @return
	 */
	public List<QueuedProcess> fetchExecutingQueuedProcess(long groupId, String processName, int start, int end, OrderByComparator<QueuedProcess> orderByComparator) {
		return queuedProcessPersistence.findByProcessNameGroupIdStatus(groupId, processName, 1, start, end, orderByComparator);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @return
	 */
	public int fetchExecutingQueuedProcessCount(long groupId, String processName) {
		return queuedProcessPersistence.countByProcessNameGroupIdStatus(groupId, processName, 1);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @param start
	 * @param end
	 * @param orderByComparator
	 * @return
	 */
	public List<QueuedProcess> fetchAllQueuedProcess(long groupId, String processName, int start, int end, OrderByComparator<QueuedProcess> orderByComparator) {
		return queuedProcessPersistence.findByProcessNameGroupId(groupId, processName, start, end, orderByComparator);
	}
	
	/**
	 * @param groupId
	 * @param processName
	 * @return
	 */
	public int fetchAllQueuedProcessCount(long groupId, String processName) {
		return queuedProcessPersistence.countByProcessNameGroupId(groupId, processName);
	}
}