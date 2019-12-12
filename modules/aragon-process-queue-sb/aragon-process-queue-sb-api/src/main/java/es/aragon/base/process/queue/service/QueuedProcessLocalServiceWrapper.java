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

package es.aragon.base.process.queue.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link QueuedProcessLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessLocalService
 * @generated
 */
@ProviderType
public class QueuedProcessLocalServiceWrapper
	implements QueuedProcessLocalService,
		ServiceWrapper<QueuedProcessLocalService> {
	public QueuedProcessLocalServiceWrapper(
		QueuedProcessLocalService queuedProcessLocalService) {
		_queuedProcessLocalService = queuedProcessLocalService;
	}

	/**
	* @param groupId
	* @param companyId
	* @param user
	* @param processName
	* @param jsonParameters - JsonObject
	* @return new QueuedProcess
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess addQueuedProcess(
		long groupId, long companyId,
		com.liferay.portal.kernel.model.User user, String processName,
		com.liferay.portal.kernel.json.JSONObject jsonParameters) {
		return _queuedProcessLocalService.addQueuedProcess(groupId, companyId,
			user, processName, jsonParameters);
	}

	/**
	* @param groupId
	* @param companyId
	* @param user
	* @param processName
	* @param jsonParameters - String
	* @return new QueuedProcess
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess addQueuedProcess(
		long groupId, long companyId,
		com.liferay.portal.kernel.model.User user, String processName,
		String jsonParameters) {
		return _queuedProcessLocalService.addQueuedProcess(groupId, companyId,
			user, processName, jsonParameters);
	}

	/**
	* Adds the queued process to the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was added
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess addQueuedProcess(
		es.aragon.base.process.queue.model.QueuedProcess queuedProcess) {
		return _queuedProcessLocalService.addQueuedProcess(queuedProcess);
	}

	/**
	* Creates a new queued process with the primary key. Does not add the queued process to the database.
	*
	* @param queuedProcessId the primary key for the new queued process
	* @return the new queued process
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess createQueuedProcess(
		long queuedProcessId) {
		return _queuedProcessLocalService.createQueuedProcess(queuedProcessId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _queuedProcessLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process that was removed
	* @throws PortalException if a queued process with the primary key could not be found
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess deleteQueuedProcess(
		long queuedProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _queuedProcessLocalService.deleteQueuedProcess(queuedProcessId);
	}

	/**
	* Deletes the queued process from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was removed
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess deleteQueuedProcess(
		es.aragon.base.process.queue.model.QueuedProcess queuedProcess) {
		return _queuedProcessLocalService.deleteQueuedProcess(queuedProcess);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _queuedProcessLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _queuedProcessLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _queuedProcessLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _queuedProcessLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _queuedProcessLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _queuedProcessLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchAllQueuedProcess(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return _queuedProcessLocalService.fetchAllQueuedProcess(groupId,
			processName, start, end, orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Override
	public int fetchAllQueuedProcessCount(long groupId, String processName) {
		return _queuedProcessLocalService.fetchAllQueuedProcessCount(groupId,
			processName);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchExecutingQueuedProcess(
		long groupId, String processName) {
		return _queuedProcessLocalService.fetchExecutingQueuedProcess(groupId,
			processName);
	}

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchExecutingQueuedProcess(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return _queuedProcessLocalService.fetchExecutingQueuedProcess(groupId,
			processName, start, end, orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @param orderByComparator
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchExecutingQueuedProcess(
		long groupId, String processName,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return _queuedProcessLocalService.fetchExecutingQueuedProcess(groupId,
			processName, orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Override
	public int fetchExecutingQueuedProcessCount(long groupId, String processName) {
		return _queuedProcessLocalService.fetchExecutingQueuedProcessCount(groupId,
			processName);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchPendingQueuedProcess(
		long groupId, String processName) {
		return _queuedProcessLocalService.fetchPendingQueuedProcess(groupId,
			processName);
	}

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchPendingQueuedProcess(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return _queuedProcessLocalService.fetchPendingQueuedProcess(groupId,
			processName, start, end, orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @param orderByComparator
	* @return
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchPendingQueuedProcess(
		long groupId, String processName,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return _queuedProcessLocalService.fetchPendingQueuedProcess(groupId,
			processName, orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Override
	public int fetchPendingQueuedProcessCount(long groupId, String processName) {
		return _queuedProcessLocalService.fetchPendingQueuedProcessCount(groupId,
			processName);
	}

	@Override
	public es.aragon.base.process.queue.model.QueuedProcess fetchQueuedProcess(
		long queuedProcessId) {
		return _queuedProcessLocalService.fetchQueuedProcess(queuedProcessId);
	}

	/**
	* Returns the queued process matching the UUID and group.
	*
	* @param uuid the queued process's UUID
	* @param groupId the primary key of the group
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess fetchQueuedProcessByUuidAndGroupId(
		String uuid, long groupId) {
		return _queuedProcessLocalService.fetchQueuedProcessByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _queuedProcessLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _queuedProcessLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _queuedProcessLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _queuedProcessLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the queued process with the primary key.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process
	* @throws PortalException if a queued process with the primary key could not be found
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess getQueuedProcess(
		long queuedProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _queuedProcessLocalService.getQueuedProcess(queuedProcessId);
	}

	/**
	* Returns the queued process matching the UUID and group.
	*
	* @param uuid the queued process's UUID
	* @param groupId the primary key of the group
	* @return the matching queued process
	* @throws PortalException if a matching queued process could not be found
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess getQueuedProcessByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _queuedProcessLocalService.getQueuedProcessByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the queued processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @return the range of queued processes
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> getQueuedProcesses(
		int start, int end) {
		return _queuedProcessLocalService.getQueuedProcesses(start, end);
	}

	/**
	* Returns all the queued processes matching the UUID and company.
	*
	* @param uuid the UUID of the queued processes
	* @param companyId the primary key of the company
	* @return the matching queued processes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> getQueuedProcessesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _queuedProcessLocalService.getQueuedProcessesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of queued processes matching the UUID and company.
	*
	* @param uuid the UUID of the queued processes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching queued processes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<es.aragon.base.process.queue.model.QueuedProcess> getQueuedProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return _queuedProcessLocalService.getQueuedProcessesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of queued processes.
	*
	* @return the number of queued processes
	*/
	@Override
	public int getQueuedProcessesCount() {
		return _queuedProcessLocalService.getQueuedProcessesCount();
	}

	/**
	* Updates the queued process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was updated
	*/
	@Override
	public es.aragon.base.process.queue.model.QueuedProcess updateQueuedProcess(
		es.aragon.base.process.queue.model.QueuedProcess queuedProcess) {
		return _queuedProcessLocalService.updateQueuedProcess(queuedProcess);
	}

	@Override
	public QueuedProcessLocalService getWrappedService() {
		return _queuedProcessLocalService;
	}

	@Override
	public void setWrappedService(
		QueuedProcessLocalService queuedProcessLocalService) {
		_queuedProcessLocalService = queuedProcessLocalService;
	}

	private QueuedProcessLocalService _queuedProcessLocalService;
}