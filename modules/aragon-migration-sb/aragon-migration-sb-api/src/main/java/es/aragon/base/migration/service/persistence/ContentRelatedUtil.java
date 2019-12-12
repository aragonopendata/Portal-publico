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

import es.aragon.base.migration.model.ContentRelated;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the content related service. This utility wraps {@link es.aragon.base.migration.service.persistence.impl.ContentRelatedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentRelatedPersistence
 * @see es.aragon.base.migration.service.persistence.impl.ContentRelatedPersistenceImpl
 * @generated
 */
@ProviderType
public class ContentRelatedUtil {
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
	public static void clearCache(ContentRelated contentRelated) {
		getPersistence().clearCache(contentRelated);
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
	public static List<ContentRelated> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContentRelated> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContentRelated> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContentRelated update(ContentRelated contentRelated) {
		return getPersistence().update(contentRelated);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContentRelated update(ContentRelated contentRelated,
		ServiceContext serviceContext) {
		return getPersistence().update(contentRelated, serviceContext);
	}

	/**
	* Returns all the content relateds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content relateds
	*/
	public static List<ContentRelated> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the content relateds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @return the range of matching content relateds
	*/
	public static List<ContentRelated> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the content relateds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching content relateds
	*/
	public static List<ContentRelated> findByUuid(String uuid, int start,
		int end, OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the content relateds where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching content relateds
	*/
	public static List<ContentRelated> findByUuid(String uuid, int start,
		int end, OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public static ContentRelated findByUuid_First(String uuid,
		OrderByComparator<ContentRelated> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public static ContentRelated fetchByUuid_First(String uuid,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public static ContentRelated findByUuid_Last(String uuid,
		OrderByComparator<ContentRelated> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public static ContentRelated fetchByUuid_Last(String uuid,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the content relateds before and after the current content related in the ordered set where uuid = &#63;.
	*
	* @param contentRelatedId the primary key of the current content related
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content related
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public static ContentRelated[] findByUuid_PrevAndNext(
		long contentRelatedId, String uuid,
		OrderByComparator<ContentRelated> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contentRelatedId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the content relateds where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of content relateds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content relateds
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the content relateds where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @return the matching content relateds
	*/
	public static List<ContentRelated> findByContentParentId(
		long contentParentId) {
		return getPersistence().findByContentParentId(contentParentId);
	}

	/**
	* Returns a range of all the content relateds where contentParentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentParentId the content parent ID
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @return the range of matching content relateds
	*/
	public static List<ContentRelated> findByContentParentId(
		long contentParentId, int start, int end) {
		return getPersistence()
				   .findByContentParentId(contentParentId, start, end);
	}

	/**
	* Returns an ordered range of all the content relateds where contentParentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentParentId the content parent ID
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching content relateds
	*/
	public static List<ContentRelated> findByContentParentId(
		long contentParentId, int start, int end,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence()
				   .findByContentParentId(contentParentId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the content relateds where contentParentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentParentId the content parent ID
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching content relateds
	*/
	public static List<ContentRelated> findByContentParentId(
		long contentParentId, int start, int end,
		OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByContentParentId(contentParentId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public static ContentRelated findByContentParentId_First(
		long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence()
				   .findByContentParentId_First(contentParentId,
			orderByComparator);
	}

	/**
	* Returns the first content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public static ContentRelated fetchByContentParentId_First(
		long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence()
				   .fetchByContentParentId_First(contentParentId,
			orderByComparator);
	}

	/**
	* Returns the last content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public static ContentRelated findByContentParentId_Last(
		long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence()
				   .findByContentParentId_Last(contentParentId,
			orderByComparator);
	}

	/**
	* Returns the last content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public static ContentRelated fetchByContentParentId_Last(
		long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence()
				   .fetchByContentParentId_Last(contentParentId,
			orderByComparator);
	}

	/**
	* Returns the content relateds before and after the current content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentRelatedId the primary key of the current content related
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content related
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public static ContentRelated[] findByContentParentId_PrevAndNext(
		long contentRelatedId, long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence()
				   .findByContentParentId_PrevAndNext(contentRelatedId,
			contentParentId, orderByComparator);
	}

	/**
	* Removes all the content relateds where contentParentId = &#63; from the database.
	*
	* @param contentParentId the content parent ID
	*/
	public static void removeByContentParentId(long contentParentId) {
		getPersistence().removeByContentParentId(contentParentId);
	}

	/**
	* Returns the number of content relateds where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @return the number of matching content relateds
	*/
	public static int countByContentParentId(long contentParentId) {
		return getPersistence().countByContentParentId(contentParentId);
	}

	/**
	* Caches the content related in the entity cache if it is enabled.
	*
	* @param contentRelated the content related
	*/
	public static void cacheResult(ContentRelated contentRelated) {
		getPersistence().cacheResult(contentRelated);
	}

	/**
	* Caches the content relateds in the entity cache if it is enabled.
	*
	* @param contentRelateds the content relateds
	*/
	public static void cacheResult(List<ContentRelated> contentRelateds) {
		getPersistence().cacheResult(contentRelateds);
	}

	/**
	* Creates a new content related with the primary key. Does not add the content related to the database.
	*
	* @param contentRelatedId the primary key for the new content related
	* @return the new content related
	*/
	public static ContentRelated create(long contentRelatedId) {
		return getPersistence().create(contentRelatedId);
	}

	/**
	* Removes the content related with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related that was removed
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public static ContentRelated remove(long contentRelatedId)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence().remove(contentRelatedId);
	}

	public static ContentRelated updateImpl(ContentRelated contentRelated) {
		return getPersistence().updateImpl(contentRelated);
	}

	/**
	* Returns the content related with the primary key or throws a {@link NoSuchContentRelatedException} if it could not be found.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public static ContentRelated findByPrimaryKey(long contentRelatedId)
		throws es.aragon.base.migration.exception.NoSuchContentRelatedException {
		return getPersistence().findByPrimaryKey(contentRelatedId);
	}

	/**
	* Returns the content related with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related, or <code>null</code> if a content related with the primary key could not be found
	*/
	public static ContentRelated fetchByPrimaryKey(long contentRelatedId) {
		return getPersistence().fetchByPrimaryKey(contentRelatedId);
	}

	public static java.util.Map<java.io.Serializable, ContentRelated> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the content relateds.
	*
	* @return the content relateds
	*/
	public static List<ContentRelated> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the content relateds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @return the range of content relateds
	*/
	public static List<ContentRelated> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the content relateds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of content relateds
	*/
	public static List<ContentRelated> findAll(int start, int end,
		OrderByComparator<ContentRelated> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the content relateds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of content relateds
	*/
	public static List<ContentRelated> findAll(int start, int end,
		OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the content relateds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of content relateds.
	*
	* @return the number of content relateds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContentRelatedPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentRelatedPersistence, ContentRelatedPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentRelatedPersistence.class);

		ServiceTracker<ContentRelatedPersistence, ContentRelatedPersistence> serviceTracker =
			new ServiceTracker<ContentRelatedPersistence, ContentRelatedPersistence>(bundle.getBundleContext(),
				ContentRelatedPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}