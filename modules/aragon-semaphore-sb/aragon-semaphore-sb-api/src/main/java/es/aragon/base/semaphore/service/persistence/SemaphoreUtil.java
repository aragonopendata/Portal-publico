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

package es.aragon.base.semaphore.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.semaphore.model.Semaphore;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the semaphore service. This utility wraps {@link es.aragon.base.semaphore.service.persistence.impl.SemaphorePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SemaphorePersistence
 * @see es.aragon.base.semaphore.service.persistence.impl.SemaphorePersistenceImpl
 * @generated
 */
@ProviderType
public class SemaphoreUtil {
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
	public static void clearCache(Semaphore semaphore) {
		getPersistence().clearCache(semaphore);
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
	public static List<Semaphore> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Semaphore> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Semaphore> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Semaphore update(Semaphore semaphore) {
		return getPersistence().update(semaphore);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Semaphore update(Semaphore semaphore,
		ServiceContext serviceContext) {
		return getPersistence().update(semaphore, serviceContext);
	}

	/**
	* Returns all the semaphores where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching semaphores
	*/
	public static List<Semaphore> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the semaphores where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @return the range of matching semaphores
	*/
	public static List<Semaphore> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the semaphores where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching semaphores
	*/
	public static List<Semaphore> findByUuid(String uuid, int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the semaphores where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching semaphores
	*/
	public static List<Semaphore> findByUuid(String uuid, int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByUuid_First(String uuid,
		OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByUuid_First(String uuid,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByUuid_Last(String uuid,
		OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByUuid_Last(String uuid,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the semaphores before and after the current semaphore in the ordered set where uuid = &#63;.
	*
	* @param semaphoreId the primary key of the current semaphore
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public static Semaphore[] findByUuid_PrevAndNext(long semaphoreId,
		String uuid, OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence()
				   .findByUuid_PrevAndNext(semaphoreId, uuid, orderByComparator);
	}

	/**
	* Removes all the semaphores where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of semaphores where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching semaphores
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the semaphore where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSemaphoreException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByUUID_G(String uuid, long groupId)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the semaphore where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the semaphore where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the semaphore where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the semaphore that was removed
	*/
	public static Semaphore removeByUUID_G(String uuid, long groupId)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of semaphores where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching semaphores
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @return the matching semaphores
	*/
	public static List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName) {
		return getPersistence().findByServiceNameGroupId(groupId, serviceName);
	}

	/**
	* Returns a range of all the semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @return the range of matching semaphores
	*/
	public static List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end) {
		return getPersistence()
				   .findByServiceNameGroupId(groupId, serviceName, start, end);
	}

	/**
	* Returns an ordered range of all the semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching semaphores
	*/
	public static List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence()
				   .findByServiceNameGroupId(groupId, serviceName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching semaphores
	*/
	public static List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByServiceNameGroupId(groupId, serviceName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByServiceNameGroupId_First(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence()
				   .findByServiceNameGroupId_First(groupId, serviceName,
			orderByComparator);
	}

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByServiceNameGroupId_First(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence()
				   .fetchByServiceNameGroupId_First(groupId, serviceName,
			orderByComparator);
	}

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByServiceNameGroupId_Last(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence()
				   .findByServiceNameGroupId_Last(groupId, serviceName,
			orderByComparator);
	}

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByServiceNameGroupId_Last(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence()
				   .fetchByServiceNameGroupId_Last(groupId, serviceName,
			orderByComparator);
	}

	/**
	* Returns the semaphores before and after the current semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param semaphoreId the primary key of the current semaphore
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public static Semaphore[] findByServiceNameGroupId_PrevAndNext(
		long semaphoreId, long groupId, String serviceName,
		OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence()
				   .findByServiceNameGroupId_PrevAndNext(semaphoreId, groupId,
			serviceName, orderByComparator);
	}

	/**
	* Removes all the semaphores where groupId = &#63; and serviceName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	*/
	public static void removeByServiceNameGroupId(long groupId,
		String serviceName) {
		getPersistence().removeByServiceNameGroupId(groupId, serviceName);
	}

	/**
	* Returns the number of semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @return the number of matching semaphores
	*/
	public static int countByServiceNameGroupId(long groupId, String serviceName) {
		return getPersistence().countByServiceNameGroupId(groupId, serviceName);
	}

	/**
	* Returns all the semaphores where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching semaphores
	*/
	public static List<Semaphore> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the semaphores where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @return the range of matching semaphores
	*/
	public static List<Semaphore> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the semaphores where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching semaphores
	*/
	public static List<Semaphore> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the semaphores where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching semaphores
	*/
	public static List<Semaphore> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByGroupId_First(long groupId,
		OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByGroupId_First(long groupId,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public static Semaphore findByGroupId_Last(long groupId,
		OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static Semaphore fetchByGroupId_Last(long groupId,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the semaphores before and after the current semaphore in the ordered set where groupId = &#63;.
	*
	* @param semaphoreId the primary key of the current semaphore
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public static Semaphore[] findByGroupId_PrevAndNext(long semaphoreId,
		long groupId, OrderByComparator<Semaphore> orderByComparator)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(semaphoreId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the semaphores where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of semaphores where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching semaphores
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the semaphore in the entity cache if it is enabled.
	*
	* @param semaphore the semaphore
	*/
	public static void cacheResult(Semaphore semaphore) {
		getPersistence().cacheResult(semaphore);
	}

	/**
	* Caches the semaphores in the entity cache if it is enabled.
	*
	* @param semaphores the semaphores
	*/
	public static void cacheResult(List<Semaphore> semaphores) {
		getPersistence().cacheResult(semaphores);
	}

	/**
	* Creates a new semaphore with the primary key. Does not add the semaphore to the database.
	*
	* @param semaphoreId the primary key for the new semaphore
	* @return the new semaphore
	*/
	public static Semaphore create(long semaphoreId) {
		return getPersistence().create(semaphoreId);
	}

	/**
	* Removes the semaphore with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore that was removed
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public static Semaphore remove(long semaphoreId)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().remove(semaphoreId);
	}

	public static Semaphore updateImpl(Semaphore semaphore) {
		return getPersistence().updateImpl(semaphore);
	}

	/**
	* Returns the semaphore with the primary key or throws a {@link NoSuchSemaphoreException} if it could not be found.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public static Semaphore findByPrimaryKey(long semaphoreId)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getPersistence().findByPrimaryKey(semaphoreId);
	}

	/**
	* Returns the semaphore with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore, or <code>null</code> if a semaphore with the primary key could not be found
	*/
	public static Semaphore fetchByPrimaryKey(long semaphoreId) {
		return getPersistence().fetchByPrimaryKey(semaphoreId);
	}

	public static java.util.Map<java.io.Serializable, Semaphore> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the semaphores.
	*
	* @return the semaphores
	*/
	public static List<Semaphore> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the semaphores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @return the range of semaphores
	*/
	public static List<Semaphore> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the semaphores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of semaphores
	*/
	public static List<Semaphore> findAll(int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the semaphores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of semaphores
	*/
	public static List<Semaphore> findAll(int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the semaphores from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of semaphores.
	*
	* @return the number of semaphores
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SemaphorePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SemaphorePersistence, SemaphorePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SemaphorePersistence.class);

		ServiceTracker<SemaphorePersistence, SemaphorePersistence> serviceTracker =
			new ServiceTracker<SemaphorePersistence, SemaphorePersistence>(bundle.getBundleContext(),
				SemaphorePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}