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

package es.aragon.base.aragon_most_visited_pages.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the most visited page service. This utility wraps {@link es.aragon.base.aragon_most_visited_pages.service.persistence.impl.MostVisitedPagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPagePersistence
 * @see es.aragon.base.aragon_most_visited_pages.service.persistence.impl.MostVisitedPagePersistenceImpl
 * @generated
 */
@ProviderType
public class MostVisitedPageUtil {
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
	public static void clearCache(MostVisitedPage mostVisitedPage) {
		getPersistence().clearCache(mostVisitedPage);
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
	public static List<MostVisitedPage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MostVisitedPage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MostVisitedPage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MostVisitedPage update(MostVisitedPage mostVisitedPage) {
		return getPersistence().update(mostVisitedPage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MostVisitedPage update(MostVisitedPage mostVisitedPage,
		ServiceContext serviceContext) {
		return getPersistence().update(mostVisitedPage, serviceContext);
	}

	/**
	* Returns all the most visited pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching most visited pages
	*/
	public static List<MostVisitedPage> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the most visited pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @return the range of matching most visited pages
	*/
	public static List<MostVisitedPage> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the most visited pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching most visited pages
	*/
	public static List<MostVisitedPage> findByUuid(String uuid, int start,
		int end, OrderByComparator<MostVisitedPage> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the most visited pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching most visited pages
	*/
	public static List<MostVisitedPage> findByUuid(String uuid, int start,
		int end, OrderByComparator<MostVisitedPage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching most visited page
	* @throws NoSuchMostVisitedPageException if a matching most visited page could not be found
	*/
	public static MostVisitedPage findByUuid_First(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator)
		throws es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching most visited page, or <code>null</code> if a matching most visited page could not be found
	*/
	public static MostVisitedPage fetchByUuid_First(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching most visited page
	* @throws NoSuchMostVisitedPageException if a matching most visited page could not be found
	*/
	public static MostVisitedPage findByUuid_Last(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator)
		throws es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching most visited page, or <code>null</code> if a matching most visited page could not be found
	*/
	public static MostVisitedPage fetchByUuid_Last(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the most visited pages before and after the current most visited page in the ordered set where uuid = &#63;.
	*
	* @param mostVisitedPageId the primary key of the current most visited page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next most visited page
	* @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	*/
	public static MostVisitedPage[] findByUuid_PrevAndNext(
		long mostVisitedPageId, String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator)
		throws es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException {
		return getPersistence()
				   .findByUuid_PrevAndNext(mostVisitedPageId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the most visited pages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of most visited pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching most visited pages
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the most visited page in the entity cache if it is enabled.
	*
	* @param mostVisitedPage the most visited page
	*/
	public static void cacheResult(MostVisitedPage mostVisitedPage) {
		getPersistence().cacheResult(mostVisitedPage);
	}

	/**
	* Caches the most visited pages in the entity cache if it is enabled.
	*
	* @param mostVisitedPages the most visited pages
	*/
	public static void cacheResult(List<MostVisitedPage> mostVisitedPages) {
		getPersistence().cacheResult(mostVisitedPages);
	}

	/**
	* Creates a new most visited page with the primary key. Does not add the most visited page to the database.
	*
	* @param mostVisitedPageId the primary key for the new most visited page
	* @return the new most visited page
	*/
	public static MostVisitedPage create(long mostVisitedPageId) {
		return getPersistence().create(mostVisitedPageId);
	}

	/**
	* Removes the most visited page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page that was removed
	* @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	*/
	public static MostVisitedPage remove(long mostVisitedPageId)
		throws es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException {
		return getPersistence().remove(mostVisitedPageId);
	}

	public static MostVisitedPage updateImpl(MostVisitedPage mostVisitedPage) {
		return getPersistence().updateImpl(mostVisitedPage);
	}

	/**
	* Returns the most visited page with the primary key or throws a {@link NoSuchMostVisitedPageException} if it could not be found.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page
	* @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	*/
	public static MostVisitedPage findByPrimaryKey(long mostVisitedPageId)
		throws es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException {
		return getPersistence().findByPrimaryKey(mostVisitedPageId);
	}

	/**
	* Returns the most visited page with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page, or <code>null</code> if a most visited page with the primary key could not be found
	*/
	public static MostVisitedPage fetchByPrimaryKey(long mostVisitedPageId) {
		return getPersistence().fetchByPrimaryKey(mostVisitedPageId);
	}

	public static java.util.Map<java.io.Serializable, MostVisitedPage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the most visited pages.
	*
	* @return the most visited pages
	*/
	public static List<MostVisitedPage> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @return the range of most visited pages
	*/
	public static List<MostVisitedPage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of most visited pages
	*/
	public static List<MostVisitedPage> findAll(int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of most visited pages
	*/
	public static List<MostVisitedPage> findAll(int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the most visited pages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of most visited pages.
	*
	* @return the number of most visited pages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MostVisitedPagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MostVisitedPagePersistence, MostVisitedPagePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MostVisitedPagePersistence.class);

		ServiceTracker<MostVisitedPagePersistence, MostVisitedPagePersistence> serviceTracker =
			new ServiceTracker<MostVisitedPagePersistence, MostVisitedPagePersistence>(bundle.getBundleContext(),
				MostVisitedPagePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}