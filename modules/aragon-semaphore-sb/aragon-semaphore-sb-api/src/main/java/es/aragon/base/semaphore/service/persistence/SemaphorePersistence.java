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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.semaphore.exception.NoSuchSemaphoreException;
import es.aragon.base.semaphore.model.Semaphore;

/**
 * The persistence interface for the semaphore service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.semaphore.service.persistence.impl.SemaphorePersistenceImpl
 * @see SemaphoreUtil
 * @generated
 */
@ProviderType
public interface SemaphorePersistence extends BasePersistence<Semaphore> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SemaphoreUtil} to access the semaphore persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the semaphores where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching semaphores
	*/
	public java.util.List<Semaphore> findByUuid(String uuid);

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
	public java.util.List<Semaphore> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Semaphore> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

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
	public java.util.List<Semaphore> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Returns the first semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

	/**
	* Returns the last semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Returns the last semaphore in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

	/**
	* Returns the semaphores before and after the current semaphore in the ordered set where uuid = &#63;.
	*
	* @param semaphoreId the primary key of the current semaphore
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public Semaphore[] findByUuid_PrevAndNext(long semaphoreId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Removes all the semaphores where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of semaphores where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching semaphores
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the semaphore where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSemaphoreException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByUUID_G(String uuid, long groupId)
		throws NoSuchSemaphoreException;

	/**
	* Returns the semaphore where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the semaphore where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the semaphore where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the semaphore that was removed
	*/
	public Semaphore removeByUUID_G(String uuid, long groupId)
		throws NoSuchSemaphoreException;

	/**
	* Returns the number of semaphores where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching semaphores
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @return the matching semaphores
	*/
	public java.util.List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName);

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
	public java.util.List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end);

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
	public java.util.List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

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
	public java.util.List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByServiceNameGroupId_First(long groupId,
		String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByServiceNameGroupId_First(long groupId,
		String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByServiceNameGroupId_Last(long groupId,
		String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByServiceNameGroupId_Last(long groupId,
		String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

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
	public Semaphore[] findByServiceNameGroupId_PrevAndNext(long semaphoreId,
		long groupId, String serviceName,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Removes all the semaphores where groupId = &#63; and serviceName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	*/
	public void removeByServiceNameGroupId(long groupId, String serviceName);

	/**
	* Returns the number of semaphores where groupId = &#63; and serviceName = &#63;.
	*
	* @param groupId the group ID
	* @param serviceName the service name
	* @return the number of matching semaphores
	*/
	public int countByServiceNameGroupId(long groupId, String serviceName);

	/**
	* Returns all the semaphores where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching semaphores
	*/
	public java.util.List<Semaphore> findByGroupId(long groupId);

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
	public java.util.List<Semaphore> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Semaphore> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

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
	public java.util.List<Semaphore> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Returns the first semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore
	* @throws NoSuchSemaphoreException if a matching semaphore could not be found
	*/
	public Semaphore findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Returns the last semaphore in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public Semaphore fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

	/**
	* Returns the semaphores before and after the current semaphore in the ordered set where groupId = &#63;.
	*
	* @param semaphoreId the primary key of the current semaphore
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public Semaphore[] findByGroupId_PrevAndNext(long semaphoreId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException;

	/**
	* Removes all the semaphores where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of semaphores where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching semaphores
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the semaphore in the entity cache if it is enabled.
	*
	* @param semaphore the semaphore
	*/
	public void cacheResult(Semaphore semaphore);

	/**
	* Caches the semaphores in the entity cache if it is enabled.
	*
	* @param semaphores the semaphores
	*/
	public void cacheResult(java.util.List<Semaphore> semaphores);

	/**
	* Creates a new semaphore with the primary key. Does not add the semaphore to the database.
	*
	* @param semaphoreId the primary key for the new semaphore
	* @return the new semaphore
	*/
	public Semaphore create(long semaphoreId);

	/**
	* Removes the semaphore with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore that was removed
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public Semaphore remove(long semaphoreId) throws NoSuchSemaphoreException;

	public Semaphore updateImpl(Semaphore semaphore);

	/**
	* Returns the semaphore with the primary key or throws a {@link NoSuchSemaphoreException} if it could not be found.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore
	* @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	*/
	public Semaphore findByPrimaryKey(long semaphoreId)
		throws NoSuchSemaphoreException;

	/**
	* Returns the semaphore with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore, or <code>null</code> if a semaphore with the primary key could not be found
	*/
	public Semaphore fetchByPrimaryKey(long semaphoreId);

	@Override
	public java.util.Map<java.io.Serializable, Semaphore> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the semaphores.
	*
	* @return the semaphores
	*/
	public java.util.List<Semaphore> findAll();

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
	public java.util.List<Semaphore> findAll(int start, int end);

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
	public java.util.List<Semaphore> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator);

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
	public java.util.List<Semaphore> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the semaphores from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of semaphores.
	*
	* @return the number of semaphores
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}