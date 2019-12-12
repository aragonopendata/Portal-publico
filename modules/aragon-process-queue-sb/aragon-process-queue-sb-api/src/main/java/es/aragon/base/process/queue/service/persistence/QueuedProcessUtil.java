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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.process.queue.model.QueuedProcess;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the queued process service. This utility wraps {@link es.aragon.base.process.queue.service.persistence.impl.QueuedProcessPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessPersistence
 * @see es.aragon.base.process.queue.service.persistence.impl.QueuedProcessPersistenceImpl
 * @generated
 */
@ProviderType
public class QueuedProcessUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(QueuedProcess queuedProcess) {
		getPersistence().clearCache(queuedProcess);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<QueuedProcess> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<QueuedProcess> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<QueuedProcess> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static QueuedProcess update(QueuedProcess queuedProcess) {
		return getPersistence().update(queuedProcess);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static QueuedProcess update(QueuedProcess queuedProcess,
		ServiceContext serviceContext) {
		return getPersistence().update(queuedProcess, serviceContext);
	}

	/**
	* Returns all the queued processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching queued processes
	*/
	public static List<QueuedProcess> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<QueuedProcess> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<QueuedProcess> findByUuid(String uuid, int start,
		int end, OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<QueuedProcess> findByUuid(String uuid, int start,
		int end, OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByUuid_First(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByUuid_First(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByUuid_Last(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByUuid_Last(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the queued processes before and after the current queued process in the ordered set where uuid = &#63;.
	*
	* @param queuedProcessId the primary key of the current queued process
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public static QueuedProcess[] findByUuid_PrevAndNext(long queuedProcessId,
		String uuid, OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByUuid_PrevAndNext(queuedProcessId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the queued processes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of queued processes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching queued processes
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the queued process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchQueuedProcessException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByUUID_G(String uuid, long groupId)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the queued process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the queued process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the queued process where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the queued process that was removed
	*/
	public static QueuedProcess removeByUUID_G(String uuid, long groupId)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of queued processes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching queued processes
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the queued processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching queued processes
	*/
	public static List<QueuedProcess> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<QueuedProcess> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<QueuedProcess> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<QueuedProcess> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static QueuedProcess[] findByUuid_C_PrevAndNext(
		long queuedProcessId, String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(queuedProcessId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the queued processes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of queued processes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching queued processes
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the queued processes where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @return the matching queued processes
	*/
	public static List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName) {
		return getPersistence().findByProcessNameGroupId(groupId, processName);
	}

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
	public static List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName, int start, int end) {
		return getPersistence()
				   .findByProcessNameGroupId(groupId, processName, start, end);
	}

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
	public static List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .findByProcessNameGroupId(groupId, processName, start, end,
			orderByComparator);
	}

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
	public static List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProcessNameGroupId(groupId, processName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByProcessNameGroupId_First(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByProcessNameGroupId_First(groupId, processName,
			orderByComparator);
	}

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByProcessNameGroupId_First(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .fetchByProcessNameGroupId_First(groupId, processName,
			orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process
	* @throws NoSuchQueuedProcessException if a matching queued process could not be found
	*/
	public static QueuedProcess findByProcessNameGroupId_Last(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByProcessNameGroupId_Last(groupId, processName,
			orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByProcessNameGroupId_Last(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .fetchByProcessNameGroupId_Last(groupId, processName,
			orderByComparator);
	}

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
	public static QueuedProcess[] findByProcessNameGroupId_PrevAndNext(
		long queuedProcessId, long groupId, String processName,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByProcessNameGroupId_PrevAndNext(queuedProcessId,
			groupId, processName, orderByComparator);
	}

	/**
	* Removes all the queued processes where groupId = &#63; and processName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processName the process name
	*/
	public static void removeByProcessNameGroupId(long groupId,
		String processName) {
		getPersistence().removeByProcessNameGroupId(groupId, processName);
	}

	/**
	* Returns the number of queued processes where groupId = &#63; and processName = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @return the number of matching queued processes
	*/
	public static int countByProcessNameGroupId(long groupId, String processName) {
		return getPersistence().countByProcessNameGroupId(groupId, processName);
	}

	/**
	* Returns all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @return the matching queued processes
	*/
	public static List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status) {
		return getPersistence()
				   .findByProcessNameGroupIdStatus(groupId, processName, status);
	}

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
	public static List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status, int start, int end) {
		return getPersistence()
				   .findByProcessNameGroupIdStatus(groupId, processName,
			status, start, end);
	}

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
	public static List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .findByProcessNameGroupIdStatus(groupId, processName,
			status, start, end, orderByComparator);
	}

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
	public static List<QueuedProcess> findByProcessNameGroupIdStatus(
		long groupId, String processName, int status, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProcessNameGroupIdStatus(groupId, processName,
			status, start, end, orderByComparator, retrieveFromCache);
	}

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
	public static QueuedProcess findByProcessNameGroupIdStatus_First(
		long groupId, String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByProcessNameGroupIdStatus_First(groupId, processName,
			status, orderByComparator);
	}

	/**
	* Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByProcessNameGroupIdStatus_First(
		long groupId, String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .fetchByProcessNameGroupIdStatus_First(groupId, processName,
			status, orderByComparator);
	}

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
	public static QueuedProcess findByProcessNameGroupIdStatus_Last(
		long groupId, String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByProcessNameGroupIdStatus_Last(groupId, processName,
			status, orderByComparator);
	}

	/**
	* Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	*/
	public static QueuedProcess fetchByProcessNameGroupIdStatus_Last(
		long groupId, String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence()
				   .fetchByProcessNameGroupIdStatus_Last(groupId, processName,
			status, orderByComparator);
	}

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
	public static QueuedProcess[] findByProcessNameGroupIdStatus_PrevAndNext(
		long queuedProcessId, long groupId, String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence()
				   .findByProcessNameGroupIdStatus_PrevAndNext(queuedProcessId,
			groupId, processName, status, orderByComparator);
	}

	/**
	* Removes all the queued processes where groupId = &#63; and processName = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	*/
	public static void removeByProcessNameGroupIdStatus(long groupId,
		String processName, int status) {
		getPersistence()
			.removeByProcessNameGroupIdStatus(groupId, processName, status);
	}

	/**
	* Returns the number of queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param processName the process name
	* @param status the status
	* @return the number of matching queued processes
	*/
	public static int countByProcessNameGroupIdStatus(long groupId,
		String processName, int status) {
		return getPersistence()
				   .countByProcessNameGroupIdStatus(groupId, processName, status);
	}

	/**
	* Caches the queued process in the entity cache if it is enabled.
	*
	* @param queuedProcess the queued process
	*/
	public static void cacheResult(QueuedProcess queuedProcess) {
		getPersistence().cacheResult(queuedProcess);
	}

	/**
	* Caches the queued processes in the entity cache if it is enabled.
	*
	* @param queuedProcesses the queued processes
	*/
	public static void cacheResult(List<QueuedProcess> queuedProcesses) {
		getPersistence().cacheResult(queuedProcesses);
	}

	/**
	* Creates a new queued process with the primary key. Does not add the queued process to the database.
	*
	* @param queuedProcessId the primary key for the new queued process
	* @return the new queued process
	*/
	public static QueuedProcess create(long queuedProcessId) {
		return getPersistence().create(queuedProcessId);
	}

	/**
	* Removes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process that was removed
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public static QueuedProcess remove(long queuedProcessId)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence().remove(queuedProcessId);
	}

	public static QueuedProcess updateImpl(QueuedProcess queuedProcess) {
		return getPersistence().updateImpl(queuedProcess);
	}

	/**
	* Returns the queued process with the primary key or throws a {@link NoSuchQueuedProcessException} if it could not be found.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process
	* @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	*/
	public static QueuedProcess findByPrimaryKey(long queuedProcessId)
		throws es.aragon.base.process.queue.exception.NoSuchQueuedProcessException {
		return getPersistence().findByPrimaryKey(queuedProcessId);
	}

	/**
	* Returns the queued process with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param queuedProcessId the primary key of the queued process
	* @return the queued process, or <code>null</code> if a queued process with the primary key could not be found
	*/
	public static QueuedProcess fetchByPrimaryKey(long queuedProcessId) {
		return getPersistence().fetchByPrimaryKey(queuedProcessId);
	}

	public static java.util.Map<java.io.Serializable, QueuedProcess> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the queued processes.
	*
	* @return the queued processes
	*/
	public static List<QueuedProcess> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<QueuedProcess> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<QueuedProcess> findAll(int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<QueuedProcess> findAll(int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the queued processes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of queued processes.
	*
	* @return the number of queued processes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static QueuedProcessPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<QueuedProcessPersistence, QueuedProcessPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(QueuedProcessPersistence.class);

		ServiceTracker<QueuedProcessPersistence, QueuedProcessPersistence> serviceTracker =
			new ServiceTracker<QueuedProcessPersistence, QueuedProcessPersistence>(bundle.getBundleContext(),
				QueuedProcessPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}