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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.process.queue.model.QueuedProcess;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for QueuedProcess. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessLocalServiceUtil
 * @see es.aragon.base.process.queue.service.base.QueuedProcessLocalServiceBaseImpl
 * @see es.aragon.base.process.queue.service.impl.QueuedProcessLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface QueuedProcessLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QueuedProcessLocalServiceUtil} to access the queued process local service. Add custom service methods to {@link es.aragon.base.process.queue.service.impl.QueuedProcessLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* @param groupId
	* @param companyId
	* @param user
	* @param processName
	* @param jsonParameters - JsonObject
	* @return new QueuedProcess
	*/
	public QueuedProcess addQueuedProcess(long groupId, long companyId,
		User user, String processName, JSONObject jsonParameters);

	/**
	* @param groupId
	* @param companyId
	* @param user
	* @param processName
	* @param jsonParameters - String
	* @return new QueuedProcess
	*/
	public QueuedProcess addQueuedProcess(long groupId, long companyId,
		User user, String processName, String jsonParameters);

	/**
	* Adds the queued process to the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public QueuedProcess addQueuedProcess(QueuedProcess queuedProcess);

	/**
	* Creates a new queued process with the primary key. Does not add the queued process to the database.
	*
	* @param queuedProcessId the primary key for the new queued process
	* @return the new queued process
	*/
	@Transactional(enabled = false)
	public QueuedProcess createQueuedProcess(long queuedProcessId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process that was removed
	* @throws PortalException if a queued process with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public QueuedProcess deleteQueuedProcess(long queuedProcessId)
		throws PortalException;

	/**
	* Deletes the queued process from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public QueuedProcess deleteQueuedProcess(QueuedProcess queuedProcess);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchAllQueuedProcess(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int fetchAllQueuedProcessCount(long groupId, String processName);

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchExecutingQueuedProcess(long groupId,
		String processName);

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchExecutingQueuedProcess(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* @param groupId
	* @param processName
	* @param orderByComparator
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchExecutingQueuedProcess(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int fetchExecutingQueuedProcessCount(long groupId, String processName);

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchPendingQueuedProcess(long groupId,
		String processName);

	/**
	* @param groupId
	* @param processName
	* @param start
	* @param end
	* @param orderByComparator
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchPendingQueuedProcess(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* @param groupId
	* @param processName
	* @param orderByComparator
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> fetchPendingQueuedProcess(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* @param groupId
	* @param processName
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int fetchPendingQueuedProcessCount(long groupId, String processName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QueuedProcess fetchQueuedProcess(long queuedProcessId);

	/**
	* Returns the queued process matching the UUID and group.
	*
	* @param uuid the queued process's UUID
	* @param groupId the primary key of the group
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QueuedProcess fetchQueuedProcessByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the queued process with the primary key.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process
	* @throws PortalException if a queued process with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QueuedProcess getQueuedProcess(long queuedProcessId)
		throws PortalException;

	/**
	* Returns the queued process matching the UUID and group.
	*
	* @param uuid the queued process's UUID
	* @param groupId the primary key of the group
	* @return the matching queued process
	* @throws PortalException if a matching queued process could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QueuedProcess getQueuedProcessByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> getQueuedProcesses(int start, int end);

	/**
	* Returns all the queued processes matching the UUID and company.
	*
	* @param uuid the UUID of the queued processes
	* @param companyId the primary key of the company
	* @return the matching queued processes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> getQueuedProcessesByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QueuedProcess> getQueuedProcessesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the number of queued processes.
	*
	* @return the number of queued processes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getQueuedProcessesCount();

	/**
	* Updates the queued process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param queuedProcess the queued process
	* @return the queued process that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public QueuedProcess updateQueuedProcess(QueuedProcess queuedProcess);
}