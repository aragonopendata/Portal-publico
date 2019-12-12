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

package es.aragon.base.migration.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.migration.model.ContentUrl;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the content url service. This utility wraps {@link es.aragon.base.migration.service.persistence.impl.ContentUrlPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrlPersistence
 * @see es.aragon.base.migration.service.persistence.impl.ContentUrlPersistenceImpl
 * @generated
 */
@ProviderType
public class ContentUrlUtil {
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
	public static void clearCache(ContentUrl contentUrl) {
		getPersistence().clearCache(contentUrl);
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
	public static List<ContentUrl> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContentUrl> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContentUrl> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContentUrl> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContentUrl update(ContentUrl contentUrl) {
		return getPersistence().update(contentUrl);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContentUrl update(ContentUrl contentUrl,
		ServiceContext serviceContext) {
		return getPersistence().update(contentUrl, serviceContext);
	}

	/**
	* Returns all the content urls where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content urls
	*/
	public static List<ContentUrl> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the content urls where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @return the range of matching content urls
	*/
	public static List<ContentUrl> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the content urls where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching content urls
	*/
	public static List<ContentUrl> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentUrl> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the content urls where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching content urls
	*/
	public static List<ContentUrl> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentUrl> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content url
	* @throws NoSuchContentUrlException if a matching content url could not be found
	*/
	public static ContentUrl findByUuid_First(String uuid,
		OrderByComparator<ContentUrl> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public static ContentUrl fetchByUuid_First(String uuid,
		OrderByComparator<ContentUrl> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content url
	* @throws NoSuchContentUrlException if a matching content url could not be found
	*/
	public static ContentUrl findByUuid_Last(String uuid,
		OrderByComparator<ContentUrl> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public static ContentUrl fetchByUuid_Last(String uuid,
		OrderByComparator<ContentUrl> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the content urls before and after the current content url in the ordered set where uuid = &#63;.
	*
	* @param contentUrlId the primary key of the current content url
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content url
	* @throws NoSuchContentUrlException if a content url with the primary key could not be found
	*/
	public static ContentUrl[] findByUuid_PrevAndNext(long contentUrlId,
		String uuid, OrderByComparator<ContentUrl> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contentUrlId, uuid, orderByComparator);
	}

	/**
	* Removes all the content urls where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of content urls where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content urls
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the content url where contentOriginId = &#63; or throws a {@link NoSuchContentUrlException} if it could not be found.
	*
	* @param contentOriginId the content origin ID
	* @return the matching content url
	* @throws NoSuchContentUrlException if a matching content url could not be found
	*/
	public static ContentUrl findByContentOriginId(long contentOriginId)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence().findByContentOriginId(contentOriginId);
	}

	/**
	* Returns the content url where contentOriginId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contentOriginId the content origin ID
	* @return the matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public static ContentUrl fetchByContentOriginId(long contentOriginId) {
		return getPersistence().fetchByContentOriginId(contentOriginId);
	}

	/**
	* Returns the content url where contentOriginId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contentOriginId the content origin ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public static ContentUrl fetchByContentOriginId(long contentOriginId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByContentOriginId(contentOriginId, retrieveFromCache);
	}

	/**
	* Removes the content url where contentOriginId = &#63; from the database.
	*
	* @param contentOriginId the content origin ID
	* @return the content url that was removed
	*/
	public static ContentUrl removeByContentOriginId(long contentOriginId)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence().removeByContentOriginId(contentOriginId);
	}

	/**
	* Returns the number of content urls where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @return the number of matching content urls
	*/
	public static int countByContentOriginId(long contentOriginId) {
		return getPersistence().countByContentOriginId(contentOriginId);
	}

	/**
	* Caches the content url in the entity cache if it is enabled.
	*
	* @param contentUrl the content url
	*/
	public static void cacheResult(ContentUrl contentUrl) {
		getPersistence().cacheResult(contentUrl);
	}

	/**
	* Caches the content urls in the entity cache if it is enabled.
	*
	* @param contentUrls the content urls
	*/
	public static void cacheResult(List<ContentUrl> contentUrls) {
		getPersistence().cacheResult(contentUrls);
	}

	/**
	* Creates a new content url with the primary key. Does not add the content url to the database.
	*
	* @param contentUrlId the primary key for the new content url
	* @return the new content url
	*/
	public static ContentUrl create(long contentUrlId) {
		return getPersistence().create(contentUrlId);
	}

	/**
	* Removes the content url with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url that was removed
	* @throws NoSuchContentUrlException if a content url with the primary key could not be found
	*/
	public static ContentUrl remove(long contentUrlId)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence().remove(contentUrlId);
	}

	public static ContentUrl updateImpl(ContentUrl contentUrl) {
		return getPersistence().updateImpl(contentUrl);
	}

	/**
	* Returns the content url with the primary key or throws a {@link NoSuchContentUrlException} if it could not be found.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url
	* @throws NoSuchContentUrlException if a content url with the primary key could not be found
	*/
	public static ContentUrl findByPrimaryKey(long contentUrlId)
		throws es.aragon.base.migration.exception.NoSuchContentUrlException {
		return getPersistence().findByPrimaryKey(contentUrlId);
	}

	/**
	* Returns the content url with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url, or <code>null</code> if a content url with the primary key could not be found
	*/
	public static ContentUrl fetchByPrimaryKey(long contentUrlId) {
		return getPersistence().fetchByPrimaryKey(contentUrlId);
	}

	public static java.util.Map<java.io.Serializable, ContentUrl> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the content urls.
	*
	* @return the content urls
	*/
	public static List<ContentUrl> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the content urls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @return the range of content urls
	*/
	public static List<ContentUrl> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the content urls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of content urls
	*/
	public static List<ContentUrl> findAll(int start, int end,
		OrderByComparator<ContentUrl> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the content urls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of content urls
	*/
	public static List<ContentUrl> findAll(int start, int end,
		OrderByComparator<ContentUrl> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the content urls from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of content urls.
	*
	* @return the number of content urls
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContentUrlPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentUrlPersistence, ContentUrlPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentUrlPersistence.class);

		ServiceTracker<ContentUrlPersistence, ContentUrlPersistence> serviceTracker =
			new ServiceTracker<ContentUrlPersistence, ContentUrlPersistence>(bundle.getBundleContext(),
				ContentUrlPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}