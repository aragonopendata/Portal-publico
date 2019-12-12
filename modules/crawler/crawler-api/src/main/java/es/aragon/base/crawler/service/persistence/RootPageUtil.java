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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.crawler.model.RootPage;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the root page service. This utility wraps {@link es.aragon.base.crawler.service.persistence.impl.RootPagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RootPagePersistence
 * @see es.aragon.base.crawler.service.persistence.impl.RootPagePersistenceImpl
 * @generated
 */
@ProviderType
public class RootPageUtil {
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
	public static void clearCache(RootPage rootPage) {
		getPersistence().clearCache(rootPage);
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
	public static List<RootPage> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RootPage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RootPage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RootPage update(RootPage rootPage) {
		return getPersistence().update(rootPage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RootPage update(RootPage rootPage,
		ServiceContext serviceContext) {
		return getPersistence().update(rootPage, serviceContext);
	}

	/**
	* Returns all the root pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching root pages
	*/
	public static List<RootPage> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<RootPage> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<RootPage> findByUuid(String uuid, int start, int end,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<RootPage> findByUuid(String uuid, int start, int end,
		OrderByComparator<RootPage> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByUuid_First(String uuid,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByUuid_First(String uuid,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByUuid_Last(String uuid,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last root page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByUuid_Last(String uuid,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the root pages before and after the current root page in the ordered set where uuid = &#63;.
	*
	* @param rootPageId the primary key of the current root page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next root page
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public static RootPage[] findByUuid_PrevAndNext(long rootPageId,
		String uuid, OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence()
				   .findByUuid_PrevAndNext(rootPageId, uuid, orderByComparator);
	}

	/**
	* Removes all the root pages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of root pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching root pages
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the root page where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRootPageException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByUUID_G(String uuid, long groupId)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the root page where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the root page where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the root page where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the root page that was removed
	*/
	public static RootPage removeByUUID_G(String uuid, long groupId)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of root pages where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching root pages
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the root pages where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching root pages
	*/
	public static List<RootPage> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<RootPage> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<RootPage> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<RootPage> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<RootPage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last root page in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static RootPage[] findByUuid_C_PrevAndNext(long rootPageId,
		String uuid, long companyId,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(rootPageId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the root pages where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of root pages where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching root pages
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the root pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the matching root pages
	*/
	public static List<RootPage> findByRootPageId(long rootPageId) {
		return getPersistence().findByRootPageId(rootPageId);
	}

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
	public static List<RootPage> findByRootPageId(long rootPageId, int start,
		int end) {
		return getPersistence().findByRootPageId(rootPageId, start, end);
	}

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
	public static List<RootPage> findByRootPageId(long rootPageId, int start,
		int end, OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .findByRootPageId(rootPageId, start, end, orderByComparator);
	}

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
	public static List<RootPage> findByRootPageId(long rootPageId, int start,
		int end, OrderByComparator<RootPage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRootPageId(rootPageId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByRootPageId_First(long rootPageId,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence()
				   .findByRootPageId_First(rootPageId, orderByComparator);
	}

	/**
	* Returns the first root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByRootPageId_First(long rootPageId,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .fetchByRootPageId_First(rootPageId, orderByComparator);
	}

	/**
	* Returns the last root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page
	* @throws NoSuchRootPageException if a matching root page could not be found
	*/
	public static RootPage findByRootPageId_Last(long rootPageId,
		OrderByComparator<RootPage> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence()
				   .findByRootPageId_Last(rootPageId, orderByComparator);
	}

	/**
	* Returns the last root page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static RootPage fetchByRootPageId_Last(long rootPageId,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence()
				   .fetchByRootPageId_Last(rootPageId, orderByComparator);
	}

	/**
	* Removes all the root pages where rootPageId = &#63; from the database.
	*
	* @param rootPageId the root page ID
	*/
	public static void removeByRootPageId(long rootPageId) {
		getPersistence().removeByRootPageId(rootPageId);
	}

	/**
	* Returns the number of root pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the number of matching root pages
	*/
	public static int countByRootPageId(long rootPageId) {
		return getPersistence().countByRootPageId(rootPageId);
	}

	/**
	* Caches the root page in the entity cache if it is enabled.
	*
	* @param rootPage the root page
	*/
	public static void cacheResult(RootPage rootPage) {
		getPersistence().cacheResult(rootPage);
	}

	/**
	* Caches the root pages in the entity cache if it is enabled.
	*
	* @param rootPages the root pages
	*/
	public static void cacheResult(List<RootPage> rootPages) {
		getPersistence().cacheResult(rootPages);
	}

	/**
	* Creates a new root page with the primary key. Does not add the root page to the database.
	*
	* @param rootPageId the primary key for the new root page
	* @return the new root page
	*/
	public static RootPage create(long rootPageId) {
		return getPersistence().create(rootPageId);
	}

	/**
	* Removes the root page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page that was removed
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public static RootPage remove(long rootPageId)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence().remove(rootPageId);
	}

	public static RootPage updateImpl(RootPage rootPage) {
		return getPersistence().updateImpl(rootPage);
	}

	/**
	* Returns the root page with the primary key or throws a {@link NoSuchRootPageException} if it could not be found.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page
	* @throws NoSuchRootPageException if a root page with the primary key could not be found
	*/
	public static RootPage findByPrimaryKey(long rootPageId)
		throws es.aragon.base.crawler.exception.NoSuchRootPageException {
		return getPersistence().findByPrimaryKey(rootPageId);
	}

	/**
	* Returns the root page with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page, or <code>null</code> if a root page with the primary key could not be found
	*/
	public static RootPage fetchByPrimaryKey(long rootPageId) {
		return getPersistence().fetchByPrimaryKey(rootPageId);
	}

	public static java.util.Map<java.io.Serializable, RootPage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the root pages.
	*
	* @return the root pages
	*/
	public static List<RootPage> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<RootPage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<RootPage> findAll(int start, int end,
		OrderByComparator<RootPage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<RootPage> findAll(int start, int end,
		OrderByComparator<RootPage> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the root pages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of root pages.
	*
	* @return the number of root pages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RootPagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RootPagePersistence, RootPagePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RootPagePersistence.class);

		ServiceTracker<RootPagePersistence, RootPagePersistence> serviceTracker = new ServiceTracker<RootPagePersistence, RootPagePersistence>(bundle.getBundleContext(),
				RootPagePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}