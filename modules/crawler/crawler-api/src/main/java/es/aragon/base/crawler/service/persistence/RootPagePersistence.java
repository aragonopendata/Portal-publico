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

package es.aragon.base.crawler.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.crawler.exception.NoSuchRootPageException;
import es.aragon.base.crawler.model.RootPage;

/**
 * The persistence interface for the root page service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.crawler.service.persistence.impl.RootPagePersistenceImpl
 * @see RootPageUtil
 * @generated
 */
@ProviderType
public interface RootPagePersistence extends BasePersistence<RootPage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RootPageUtil} to access the root page persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the root pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching root pages
	*/
	public java.util.List<RootPage> findByUuid(String uuid);

	/**
	* Returns a range of all the root pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @return the range of matching root pages
	*/
	public java.util.List<RootPage> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the root pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching root pages
	*/
	public java.util.List<RootPage> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns an ordered range of all the root pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching root pages
	*/
	public java.util.List<RootPage> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Returns the first root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns the last root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Returns the last root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns the root pages before and after the current root page in the ordered set where uuid = &#63;.
	*
	* @param rootPageId the primary key of the current root page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next root page
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public RootPage[] findByUuid_PrevAndNext(long rootPageId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Removes all the root pages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of root pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching root pages
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the root page where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRootPageException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByUUID_G(String uuid, long groupId)
		throws NoSuchRootPageException;

	/**
	* Returns the root page where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the root page where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the root page where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the root page that was removed
	*/
	public RootPage removeByUUID_G(String uuid, long groupId)
		throws NoSuchRootPageException;

	/**
	* Returns the number of root pages where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching root pages
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the root pages where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching root pages
	*/
	public java.util.List<RootPage> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the root pages where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @return the range of matching root pages
	*/
	public java.util.List<RootPage> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the root pages where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching root pages
	*/
	public java.util.List<RootPage> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns an ordered range of all the root pages where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching root pages
	*/
	public java.util.List<RootPage> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Returns the first root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns the last root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Returns the last root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns the root pages before and after the current root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param rootPageId the primary key of the current root page
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next root page
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public RootPage[] findByUuid_C_PrevAndNext(long rootPageId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Removes all the root pages where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of root pages where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching root pages
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the root pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the matching root pages
	*/
	public java.util.List<RootPage> findByRootPageId(long rootPageId);

	/**
	* Returns a range of all the root pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @return the range of matching root pages
	*/
	public java.util.List<RootPage> findByRootPageId(long rootPageId,
		int start, int end);

	/**
	* Returns an ordered range of all the root pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching root pages
	*/
	public java.util.List<RootPage> findByRootPageId(long rootPageId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns an ordered range of all the root pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching root pages
	*/
	public java.util.List<RootPage> findByRootPageId(long rootPageId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByRootPageId_First(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Returns the first root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByRootPageId_First(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns the last root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public RootPage findByRootPageId_Last(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator)
		throws NoSuchRootPageException;

	/**
	* Returns the last root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public RootPage fetchByRootPageId_Last(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Removes all the root pages where rootPageId = &#63; from the database.
	*
	* @param rootPageId the root page ID
	*/
	public void removeByRootPageId(long rootPageId);

	/**
	* Returns the number of root pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the number of matching root pages
	*/
	public int countByRootPageId(long rootPageId);

	/**
	* Caches the root page in the entity cache if it is enabled.
	*
	* @param rootPage the root page
	*/
	public void cacheResult(RootPage rootPage);

	/**
	* Caches the root pages in the entity cache if it is enabled.
	*
	* @param rootPages the root pages
	*/
	public void cacheResult(java.util.List<RootPage> rootPages);

	/**
	* Creates a new root page with the primary key. Does not add the root page to the database.
	*
	* @param rootPageId the primary key for the new root page
	* @return the new root page
	*/
	public RootPage create(long rootPageId);

	/**
	* Removes the root page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page that was removed
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public RootPage remove(long rootPageId) throws NoSuchRootPageException;

	public RootPage updateImpl(RootPage rootPage);

	/**
	* Returns the root page with the primary key or throws a {@link NoSuchRootPageException} if it could not be found.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public RootPage findByPrimaryKey(long rootPageId)
		throws NoSuchRootPageException;

	/**
	* Returns the root page with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page, or <code>null</code> if a root page with the primary key could not be found
	*/
	public RootPage fetchByPrimaryKey(long rootPageId);

	@Override
	public java.util.Map<java.io.Serializable, RootPage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the root pages.
	*
	* @return the root pages
	*/
	public java.util.List<RootPage> findAll();

	/**
	* Returns a range of all the root pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @return the range of root pages
	*/
	public java.util.List<RootPage> findAll(int start, int end);

	/**
	* Returns an ordered range of all the root pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of root pages
	*/
	public java.util.List<RootPage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns an ordered range of all the root pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of root pages
	*/
	public java.util.List<RootPage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RootPage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the root pages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of root pages.
	*
	* @return the number of root pages
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}