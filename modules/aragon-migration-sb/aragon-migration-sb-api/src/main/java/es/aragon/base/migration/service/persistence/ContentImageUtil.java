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

import es.aragon.base.migration.model.ContentImage;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the content image service. This utility wraps {@link es.aragon.base.migration.service.persistence.impl.ContentImagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentImagePersistence
 * @see es.aragon.base.migration.service.persistence.impl.ContentImagePersistenceImpl
 * @generated
 */
@ProviderType
public class ContentImageUtil {
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
	public static void clearCache(ContentImage contentImage) {
		getPersistence().clearCache(contentImage);
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
	public static List<ContentImage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContentImage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContentImage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContentImage update(ContentImage contentImage) {
		return getPersistence().update(contentImage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContentImage update(ContentImage contentImage,
		ServiceContext serviceContext) {
		return getPersistence().update(contentImage, serviceContext);
	}

	/**
	* Returns all the content images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content images
	*/
	public static List<ContentImage> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the content images where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @return the range of matching content images
	*/
	public static List<ContentImage> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the content images where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching content images
	*/
	public static List<ContentImage> findByUuid(String uuid, int start,
		int end, OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the content images where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching content images
	*/
	public static List<ContentImage> findByUuid(String uuid, int start,
		int end, OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public static ContentImage findByUuid_First(String uuid,
		OrderByComparator<ContentImage> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public static ContentImage fetchByUuid_First(String uuid,
		OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public static ContentImage findByUuid_Last(String uuid,
		OrderByComparator<ContentImage> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public static ContentImage fetchByUuid_Last(String uuid,
		OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the content images before and after the current content image in the ordered set where uuid = &#63;.
	*
	* @param contentImageId the primary key of the current content image
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content image
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public static ContentImage[] findByUuid_PrevAndNext(long contentImageId,
		String uuid, OrderByComparator<ContentImage> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contentImageId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the content images where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of content images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content images
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the content images where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @return the matching content images
	*/
	public static List<ContentImage> findByContentOriginId(long contentOriginId) {
		return getPersistence().findByContentOriginId(contentOriginId);
	}

	/**
	* Returns a range of all the content images where contentOriginId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentOriginId the content origin ID
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @return the range of matching content images
	*/
	public static List<ContentImage> findByContentOriginId(
		long contentOriginId, int start, int end) {
		return getPersistence()
				   .findByContentOriginId(contentOriginId, start, end);
	}

	/**
	* Returns an ordered range of all the content images where contentOriginId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentOriginId the content origin ID
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching content images
	*/
	public static List<ContentImage> findByContentOriginId(
		long contentOriginId, int start, int end,
		OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence()
				   .findByContentOriginId(contentOriginId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the content images where contentOriginId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentOriginId the content origin ID
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching content images
	*/
	public static List<ContentImage> findByContentOriginId(
		long contentOriginId, int start, int end,
		OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByContentOriginId(contentOriginId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public static ContentImage findByContentOriginId_First(
		long contentOriginId, OrderByComparator<ContentImage> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence()
				   .findByContentOriginId_First(contentOriginId,
			orderByComparator);
	}

	/**
	* Returns the first content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public static ContentImage fetchByContentOriginId_First(
		long contentOriginId, OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence()
				   .fetchByContentOriginId_First(contentOriginId,
			orderByComparator);
	}

	/**
	* Returns the last content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public static ContentImage findByContentOriginId_Last(
		long contentOriginId, OrderByComparator<ContentImage> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence()
				   .findByContentOriginId_Last(contentOriginId,
			orderByComparator);
	}

	/**
	* Returns the last content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public static ContentImage fetchByContentOriginId_Last(
		long contentOriginId, OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence()
				   .fetchByContentOriginId_Last(contentOriginId,
			orderByComparator);
	}

	/**
	* Returns the content images before and after the current content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentImageId the primary key of the current content image
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content image
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public static ContentImage[] findByContentOriginId_PrevAndNext(
		long contentImageId, long contentOriginId,
		OrderByComparator<ContentImage> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence()
				   .findByContentOriginId_PrevAndNext(contentImageId,
			contentOriginId, orderByComparator);
	}

	/**
	* Removes all the content images where contentOriginId = &#63; from the database.
	*
	* @param contentOriginId the content origin ID
	*/
	public static void removeByContentOriginId(long contentOriginId) {
		getPersistence().removeByContentOriginId(contentOriginId);
	}

	/**
	* Returns the number of content images where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @return the number of matching content images
	*/
	public static int countByContentOriginId(long contentOriginId) {
		return getPersistence().countByContentOriginId(contentOriginId);
	}

	/**
	* Caches the content image in the entity cache if it is enabled.
	*
	* @param contentImage the content image
	*/
	public static void cacheResult(ContentImage contentImage) {
		getPersistence().cacheResult(contentImage);
	}

	/**
	* Caches the content images in the entity cache if it is enabled.
	*
	* @param contentImages the content images
	*/
	public static void cacheResult(List<ContentImage> contentImages) {
		getPersistence().cacheResult(contentImages);
	}

	/**
	* Creates a new content image with the primary key. Does not add the content image to the database.
	*
	* @param contentImageId the primary key for the new content image
	* @return the new content image
	*/
	public static ContentImage create(long contentImageId) {
		return getPersistence().create(contentImageId);
	}

	/**
	* Removes the content image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image that was removed
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public static ContentImage remove(long contentImageId)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence().remove(contentImageId);
	}

	public static ContentImage updateImpl(ContentImage contentImage) {
		return getPersistence().updateImpl(contentImage);
	}

	/**
	* Returns the content image with the primary key or throws a {@link NoSuchContentImageException} if it could not be found.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public static ContentImage findByPrimaryKey(long contentImageId)
		throws es.aragon.base.migration.exception.NoSuchContentImageException {
		return getPersistence().findByPrimaryKey(contentImageId);
	}

	/**
	* Returns the content image with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image, or <code>null</code> if a content image with the primary key could not be found
	*/
	public static ContentImage fetchByPrimaryKey(long contentImageId) {
		return getPersistence().fetchByPrimaryKey(contentImageId);
	}

	public static java.util.Map<java.io.Serializable, ContentImage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the content images.
	*
	* @return the content images
	*/
	public static List<ContentImage> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the content images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @return the range of content images
	*/
	public static List<ContentImage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the content images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of content images
	*/
	public static List<ContentImage> findAll(int start, int end,
		OrderByComparator<ContentImage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the content images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of content images
	*/
	public static List<ContentImage> findAll(int start, int end,
		OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the content images from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of content images.
	*
	* @return the number of content images
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContentImagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentImagePersistence, ContentImagePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentImagePersistence.class);

		ServiceTracker<ContentImagePersistence, ContentImagePersistence> serviceTracker =
			new ServiceTracker<ContentImagePersistence, ContentImagePersistence>(bundle.getBundleContext(),
				ContentImagePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}