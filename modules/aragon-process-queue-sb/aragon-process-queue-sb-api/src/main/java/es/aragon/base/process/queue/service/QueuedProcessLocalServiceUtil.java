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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for QueuedProcess. This utility wraps
 * {@link es.aragon.base.process.queue.service.impl.QueuedProcessLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessLocalService
 * @see es.aragon.base.process.queue.service.base.QueuedProcessLocalServiceBaseImpl
 * @see es.aragon.base.process.queue.service.impl.QueuedProcessLocalServiceImpl
 * @generated
 */
@ProviderType
public class QueuedProcessLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.process.queue.service.impl.QueuedProcessLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* @param groupId
	* @param companyId
	* @param user
	* @param processName
	* @param jsonParameters - JsonObject
	* @return new QueuedProcess
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess addQueuedProcess(
		long groupId, long companyId,
		com.liferay.portal.kernel.model.User user, String processName,
		com.liferay.portal.kernel.json.JSONObject jsonParameters) {
		return getService()
				   .addQueuedProcess(groupId, companyId, user, processName,
			jsonParameters);
	}

	/**
	* @param groupId
	* @param companyId
	* @param user
	* @param processName
	* @param jsonParameters - String
	* @return new QueuedProcess
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess addQueuedProcess(
		long groupId, long companyId,
		com.liferay.portal.kernel.model.User user, String processName,
		String jsonParameters) {
		return getService()
				   .addQueuedProcess(groupId, companyId, user, processName,
			jsonParameters);
	}

	/**
	* Adds the queued process to the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was added
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess addQueuedProcess(
		es.aragon.base.process.queue.model.QueuedProcess queuedProcess) {
		return getService().addQueuedProcess(queuedProcess);
	}

	/**
	* Creates a new queued process with the primary key. Does not add the queued process to the database.
	*
	* @param queuedProcessId the primary key for the new queued process
	* @return the new queued process
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess createQueuedProcess(
		long queuedProcessId) {
		return getService().createQueuedProcess(queuedProcessId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process that was removed
	* @throws PortalException if a queued process with the primary key could not be found
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess deleteQueuedProcess(
		long queuedProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteQueuedProcess(queuedProcessId);
	}

	/**
	* Deletes the queued process from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was removed
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess deleteQueuedProcess(
		es.aragon.base.process.queue.model.QueuedProcess queuedProcess) {
		return getService().deleteQueuedProcess(queuedProcess);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchAllQueuedProcess(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return getService()
				   .fetchAllQueuedProcess(groupId, processName, start, end,
			orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	public static int fetchAllQueuedProcessCount(long groupId,
		String processName) {
		return getService().fetchAllQueuedProcessCount(groupId, processName);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchExecutingQueuedProcess(
		long groupId, String processName) {
		return getService().fetchExecutingQueuedProcess(groupId, processName);
	}

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchExecutingQueuedProcess(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return getService()
				   .fetchExecutingQueuedProcess(groupId, processName, start,
			end, orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @param orderByComparator
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchExecutingQueuedProcess(
		long groupId, String processName,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return getService()
				   .fetchExecutingQueuedProcess(groupId, processName,
			orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	public static int fetchExecutingQueuedProcessCount(long groupId,
		String processName) {
		return getService()
				   .fetchExecutingQueuedProcessCount(groupId, processName);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchPendingQueuedProcess(
		long groupId, String processName) {
		return getService().fetchPendingQueuedProcess(groupId, processName);
	}

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchPendingQueuedProcess(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return getService()
				   .fetchPendingQueuedProcess(groupId, processName, start, end,
			orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @param orderByComparator
	* @return
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> fetchPendingQueuedProcess(
		long groupId, String processName,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return getService()
				   .fetchPendingQueuedProcess(groupId, processName,
			orderByComparator);
	}

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	public static int fetchPendingQueuedProcessCount(long groupId,
		String processName) {
		return getService().fetchPendingQueuedProcessCount(groupId, processName);
	}

	public static es.aragon.base.process.queue.model.QueuedProcess fetchQueuedProcess(
		long queuedProcessId) {
		return getService().fetchQueuedProcess(queuedProcessId);
	}

	/**
	* Returns the queued process matching the UUID and group.
	*
	* @param uuid the queued process's UUID
	* @param groupId the primary key of the group
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess fetchQueuedProcessByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchQueuedProcessByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the queued process with the primary key.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process
	* @throws PortalException if a queued process with the primary key could not be found
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess getQueuedProcess(
		long queuedProcessId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getQueuedProcess(queuedProcessId);
	}

	/**
	* Returns the queued process matching the UUID and group.
	*
	* @param uuid the queued process's UUID
	* @param groupId the primary key of the group
	* @return the matching queued process
	* @throws PortalException if a matching queued process could not be found
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess getQueuedProcessByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getQueuedProcessByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> getQueuedProcesses(
		int start, int end) {
		return getService().getQueuedProcesses(start, end);
	}

	/**
	* Returns all the queued processes matching the UUID and company.
	*
	* @param uuid the UUID of the queued processes
	* @param companyId the primary key of the company
	* @return the matching queued processes, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> getQueuedProcessesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getQueuedProcessesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<es.aragon.base.process.queue.model.QueuedProcess> getQueuedProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.process.queue.model.QueuedProcess> orderByComparator) {
		return getService()
				   .getQueuedProcessesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of queued processes.
	*
	* @return the number of queued processes
	*/
	public static int getQueuedProcessesCount() {
		return getService().getQueuedProcessesCount();
	}

	/**
	* Updates the queued process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was updated
	*/
	public static es.aragon.base.process.queue.model.QueuedProcess updateQueuedProcess(
		es.aragon.base.process.queue.model.QueuedProcess queuedProcess) {
		return getService().updateQueuedProcess(queuedProcess);
	}

	public static QueuedProcessLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<QueuedProcessLocalService, QueuedProcessLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(QueuedProcessLocalService.class);

		ServiceTracker<QueuedProcessLocalService, QueuedProcessLocalService> serviceTracker =
			new ServiceTracker<QueuedProcessLocalService, QueuedProcessLocalService>(bundle.getBundleContext(),
				QueuedProcessLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}