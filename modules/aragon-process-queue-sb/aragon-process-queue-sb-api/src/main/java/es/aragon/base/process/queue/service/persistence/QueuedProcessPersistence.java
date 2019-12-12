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

package es.aragon.base.process.queue.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.process.queue.exception.NoSuchQueuedProcessException;
import es.aragon.base.process.queue.model.QueuedProcess;

/**
 * The persistence interface for the queued process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.process.queue.service.persistence.impl.QueuedProcessPersistenceImpl
 * @see QueuedProcessUtil
 * @generated
 */
@ProviderType
public interface QueuedProcessPersistence extends BasePersistence<QueuedProcess> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QueuedProcessUtil} to access the queued process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the queued processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid(String uuid);

	/**
	* Returns a range of all the queued processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @return the range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the queued processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns an ordered range of all the queued processes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the first queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the last queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the last queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the queued processes before and after the current queued process in the ordered set where uuid = &#63;.
	*
	* @param queuedProcessId the primary key of the current queued process
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public QueuedProcess[] findByUuid_PrevAndNext(long queuedProcessId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Removes all the queued processes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of queued processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching queued processes
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the queued process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchQueuedProcessException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByUUID_G(String uuid, long groupId)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the queued process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the queued process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the queued process where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the queued process that was removed
	*/
	public QueuedProcess removeByUUID_G(String uuid, long groupId)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the number of queued processes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching queued processes
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the queued processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the queued processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @return the range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the queued processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns an ordered range of all the queued processes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the first queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the last queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the last queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the queued processes before and after the current queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param queuedProcessId the primary key of the current queued process
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public QueuedProcess[] findByUuid_C_PrevAndNext(long queuedProcessId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Removes all the queued processes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of queued processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching queued processes
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the queued processes where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @return the matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupId(
		long groupId, String processName);

	/**
	* Returns a range of all the queued processes where groupId = &#63; and processName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @return the range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupId(
		long groupId, String processName, int start, int end);

	/**
	* Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupId(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupId(
		long groupId, String processName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByProcessNameGroupId_First(long groupId,
		String processName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByProcessNameGroupId_First(long groupId,
		String processName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByProcessNameGroupId_Last(long groupId,
		String processName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByProcessNameGroupId_Last(long groupId,
		String processName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the queued processes before and after the current queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param queuedProcessId the primary key of the current queued process
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public QueuedProcess[] findByProcessNameGroupId_PrevAndNext(
		long queuedProcessId, long groupId, String processName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Removes all the queued processes where groupId = &#63; and processName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processName the process name
	*/
	public void removeByProcessNameGroupId(long groupId, String processName);

	/**
	* Returns the number of queued processes where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @return the number of matching queued processes
	*/
	public int countByProcessNameGroupId(long groupId, String processName);

	/**
	* Returns all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @return the matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status);

	/**
	* Returns a range of all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @return the range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status, int start, int end);

	/**
	* Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching queued processes
	*/
	public java.util.List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByProcessNameGroupIdStatus_First(long groupId,
		String processName, int status,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByProcessNameGroupIdStatus_First(long groupId,
		String processName, int status,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public QueuedProcess findByProcessNameGroupIdStatus_Last(long groupId,
		String processName, int status,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public QueuedProcess fetchByProcessNameGroupIdStatus_Last(long groupId,
		String processName, int status,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns the queued processes before and after the current queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param queuedProcessId the primary key of the current queued process
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public QueuedProcess[] findByProcessNameGroupIdStatus_PrevAndNext(
		long queuedProcessId, long groupId, String processName, int status,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException;

	/**
	* Removes all the queued processes where groupId = &#63; and processName = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	*/
	public void removeByProcessNameGroupIdStatus(long groupId,
		String processName, int status);

	/**
	* Returns the number of queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @return the number of matching queued processes
	*/
	public int countByProcessNameGroupIdStatus(long groupId,
		String processName, int status);

	/**
	* Caches the queued process in the entity cache if it is enabled.
	*
	* @param queuedProcess the queued process
	*/
	public void cacheResult(QueuedProcess queuedProcess);

	/**
	* Caches the queued processes in the entity cache if it is enabled.
	*
	* @param queuedProcesses the queued processes
	*/
	public void cacheResult(java.util.List<QueuedProcess> queuedProcesses);

	/**
	* Creates a new queued process with the primary key. Does not add the queued process to the database.
	*
	* @param queuedProcessId the primary key for the new queued process
	* @return the new queued process
	*/
	public QueuedProcess create(long queuedProcessId);

	/**
	* Removes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process that was removed
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public QueuedProcess remove(long queuedProcessId)
		throws NoSuchQueuedProcessException;

	public QueuedProcess updateImpl(QueuedProcess queuedProcess);

	/**
	* Returns the queued process with the primary key or throws a {@link NoSuchQueuedProcessException} if it could not be found.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public QueuedProcess findByPrimaryKey(long queuedProcessId)
		throws NoSuchQueuedProcessException;

	/**
	* Returns the queued process with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process, or <code>null</code> if a queued process with the primary key could not be found
	*/
	public QueuedProcess fetchByPrimaryKey(long queuedProcessId);

	@Override
	public java.util.Map<java.io.Serializable, QueuedProcess> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the queued processes.
	*
	* @return the queued processes
	*/
	public java.util.List<QueuedProcess> findAll();

	/**
	* Returns a range of all the queued processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @return the range of queued processes
	*/
	public java.util.List<QueuedProcess> findAll(int start, int end);

	/**
	* Returns an ordered range of all the queued processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of queued processes
	*/
	public java.util.List<QueuedProcess> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator);

	/**
	* Returns an ordered range of all the queued processes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of queued processes
	* @param end the upper bound of the range of queued processes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of queued processes
	*/
	public java.util.List<QueuedProcess> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the queued processes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of queued processes.
	*
	* @return the number of queued processes
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}