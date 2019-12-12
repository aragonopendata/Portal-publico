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

import es.aragon.base.crawler.model.Page;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the page service. This utility wraps {@link es.aragon.base.crawler.service.persistence.impl.PagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PagePersistence
 * @see es.aragon.base.crawler.service.persistence.impl.PagePersistenceImpl
 * @generated
 */
@ProviderType
public class PageUtil {
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
	public static void clearCache(Page page) {
		getPersistence().clearCache(page);
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
	public static List<Page> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Page> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Page> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Page> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Page update(Page page) {
		return getPersistence().update(page);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Page update(Page page, ServiceContext serviceContext) {
		return getPersistence().update(page, serviceContext);
	}

	/**
	* Returns all the pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching pages
	*/
	public static List<Page> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages
	*/
	public static List<Page> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages
	*/
	public static List<Page> findByUuid(String uuid, int start, int end,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching pages
	*/
	public static List<Page> findByUuid(String uuid, int start, int end,
		OrderByComparator<Page> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public static Page findByUuid_First(String uuid,
		OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page, or <code>null</code> if a matching page could not be found
	*/
	public static Page fetchByUuid_First(String uuid,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public static Page findByUuid_Last(String uuid,
		OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page, or <code>null</code> if a matching page could not be found
	*/
	public static Page fetchByUuid_Last(String uuid,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the pages before and after the current page in the ordered set where uuid = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public static Page[] findByUuid_PrevAndNext(long pageId, String uuid,
		OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence()
				   .findByUuid_PrevAndNext(pageId, uuid, orderByComparator);
	}

	/**
	* Returns all the pages that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching pages that the user has permission to view
	*/
	public static List<Page> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	* Returns a range of all the pages that the user has permission to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages that the user has permission to view
	*/
	public static List<Page> filterFindByUuid(String uuid, int start, int end) {
		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the pages that the user has permissions to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages that the user has permission to view
	*/
	public static List<Page> filterFindByUuid(String uuid, int start, int end,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence()
				   .filterFindByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the pages before and after the current page in the ordered set of pages that the user has permission to view where uuid = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public static Page[] filterFindByUuid_PrevAndNext(long pageId, String uuid,
		OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence()
				   .filterFindByUuid_PrevAndNext(pageId, uuid, orderByComparator);
	}

	/**
	* Removes all the pages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching pages
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of pages that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching pages that the user has permission to view
	*/
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	* Returns all the pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the matching pages
	*/
	public static List<Page> findByRootPageId(long rootPageId) {
		return getPersistence().findByRootPageId(rootPageId);
	}

	/**
	* Returns a range of all the pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages
	*/
	public static List<Page> findByRootPageId(long rootPageId, int start,
		int end) {
		return getPersistence().findByRootPageId(rootPageId, start, end);
	}

	/**
	* Returns an ordered range of all the pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages
	*/
	public static List<Page> findByRootPageId(long rootPageId, int start,
		int end, OrderByComparator<Page> orderByComparator) {
		return getPersistence()
				   .findByRootPageId(rootPageId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching pages
	*/
	public static List<Page> findByRootPageId(long rootPageId, int start,
		int end, OrderByComparator<Page> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRootPageId(rootPageId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public static Page findByRootPageId_First(long rootPageId,
		OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence()
				   .findByRootPageId_First(rootPageId, orderByComparator);
	}

	/**
	* Returns the first page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page, or <code>null</code> if a matching page could not be found
	*/
	public static Page fetchByRootPageId_First(long rootPageId,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence()
				   .fetchByRootPageId_First(rootPageId, orderByComparator);
	}

	/**
	* Returns the last page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public static Page findByRootPageId_Last(long rootPageId,
		OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence()
				   .findByRootPageId_Last(rootPageId, orderByComparator);
	}

	/**
	* Returns the last page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page, or <code>null</code> if a matching page could not be found
	*/
	public static Page fetchByRootPageId_Last(long rootPageId,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence()
				   .fetchByRootPageId_Last(rootPageId, orderByComparator);
	}

	/**
	* Returns the pages before and after the current page in the ordered set where rootPageId = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public static Page[] findByRootPageId_PrevAndNext(long pageId,
		long rootPageId, OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence()
				   .findByRootPageId_PrevAndNext(pageId, rootPageId,
			orderByComparator);
	}

	/**
	* Returns all the pages that the user has permission to view where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the matching pages that the user has permission to view
	*/
	public static List<Page> filterFindByRootPageId(long rootPageId) {
		return getPersistence().filterFindByRootPageId(rootPageId);
	}

	/**
	* Returns a range of all the pages that the user has permission to view where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages that the user has permission to view
	*/
	public static List<Page> filterFindByRootPageId(long rootPageId, int start,
		int end) {
		return getPersistence().filterFindByRootPageId(rootPageId, start, end);
	}

	/**
	* Returns an ordered range of all the pages that the user has permissions to view where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages that the user has permission to view
	*/
	public static List<Page> filterFindByRootPageId(long rootPageId, int start,
		int end, OrderByComparator<Page> orderByComparator) {
		return getPersistence()
				   .filterFindByRootPageId(rootPageId, start, end,
			orderByComparator);
	}

	/**
	* Returns the pages before and after the current page in the ordered set of pages that the user has permission to view where rootPageId = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public static Page[] filterFindByRootPageId_PrevAndNext(long pageId,
		long rootPageId, OrderByComparator<Page> orderByComparator)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence()
				   .filterFindByRootPageId_PrevAndNext(pageId, rootPageId,
			orderByComparator);
	}

	/**
	* Removes all the pages where rootPageId = &#63; from the database.
	*
	* @param rootPageId the root page ID
	*/
	public static void removeByRootPageId(long rootPageId) {
		getPersistence().removeByRootPageId(rootPageId);
	}

	/**
	* Returns the number of pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the number of matching pages
	*/
	public static int countByRootPageId(long rootPageId) {
		return getPersistence().countByRootPageId(rootPageId);
	}

	/**
	* Returns the number of pages that the user has permission to view where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the number of matching pages that the user has permission to view
	*/
	public static int filterCountByRootPageId(long rootPageId) {
		return getPersistence().filterCountByRootPageId(rootPageId);
	}

	/**
	* Caches the page in the entity cache if it is enabled.
	*
	* @param page the page
	*/
	public static void cacheResult(Page page) {
		getPersistence().cacheResult(page);
	}

	/**
	* Caches the pages in the entity cache if it is enabled.
	*
	* @param pages the pages
	*/
	public static void cacheResult(List<Page> pages) {
		getPersistence().cacheResult(pages);
	}

	/**
	* Creates a new page with the primary key. Does not add the page to the database.
	*
	* @param pageId the primary key for the new page
	* @return the new page
	*/
	public static Page create(long pageId) {
		return getPersistence().create(pageId);
	}

	/**
	* Removes the page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pageId the primary key of the page
	* @return the page that was removed
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public static Page remove(long pageId)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence().remove(pageId);
	}

	public static Page updateImpl(Page page) {
		return getPersistence().updateImpl(page);
	}

	/**
	* Returns the page with the primary key or throws a {@link NoSuchPageException} if it could not be found.
	*
	* @param pageId the primary key of the page
	* @return the page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public static Page findByPrimaryKey(long pageId)
		throws es.aragon.base.crawler.exception.NoSuchPageException {
		return getPersistence().findByPrimaryKey(pageId);
	}

	/**
	* Returns the page with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pageId the primary key of the page
	* @return the page, or <code>null</code> if a page with the primary key could not be found
	*/
	public static Page fetchByPrimaryKey(long pageId) {
		return getPersistence().fetchByPrimaryKey(pageId);
	}

	public static java.util.Map<java.io.Serializable, Page> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the pages.
	*
	* @return the pages
	*/
	public static List<Page> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of pages
	*/
	public static List<Page> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of pages
	*/
	public static List<Page> findAll(int start, int end,
		OrderByComparator<Page> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of pages
	*/
	public static List<Page> findAll(int start, int end,
		OrderByComparator<Page> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the pages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of pages.
	*
	* @return the number of pages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PagePersistence, PagePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PagePersistence.class);

		ServiceTracker<PagePersistence, PagePersistence> serviceTracker = new ServiceTracker<PagePersistence, PagePersistence>(bundle.getBundleContext(),
				PagePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}