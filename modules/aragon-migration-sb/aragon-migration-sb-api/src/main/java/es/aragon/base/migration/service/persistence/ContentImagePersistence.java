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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.migration.exception.NoSuchContentImageException;
import es.aragon.base.migration.model.ContentImage;

/**
 * The persistence interface for the content image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.ContentImagePersistenceImpl
 * @see ContentImageUtil
 * @generated
 */
@ProviderType
public interface ContentImagePersistence extends BasePersistence<ContentImage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentImageUtil} to access the content image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the content images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content images
	*/
	public java.util.List<ContentImage> findByUuid(String uuid);

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
	public java.util.List<ContentImage> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ContentImage> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

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
	public java.util.List<ContentImage> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public ContentImage findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException;

	/**
	* Returns the first content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public ContentImage fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

	/**
	* Returns the last content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public ContentImage findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException;

	/**
	* Returns the last content image in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public ContentImage fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

	/**
	* Returns the content images before and after the current content image in the ordered set where uuid = &#63;.
	*
	* @param contentImageId the primary key of the current content image
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content image
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public ContentImage[] findByUuid_PrevAndNext(long contentImageId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException;

	/**
	* Removes all the content images where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of content images where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content images
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the content images where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @return the matching content images
	*/
	public java.util.List<ContentImage> findByContentOriginId(
		long contentOriginId);

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
	public java.util.List<ContentImage> findByContentOriginId(
		long contentOriginId, int start, int end);

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
	public java.util.List<ContentImage> findByContentOriginId(
		long contentOriginId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

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
	public java.util.List<ContentImage> findByContentOriginId(
		long contentOriginId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public ContentImage findByContentOriginId_First(long contentOriginId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException;

	/**
	* Returns the first content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public ContentImage fetchByContentOriginId_First(long contentOriginId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

	/**
	* Returns the last content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image
	* @throws NoSuchContentImageException if a matching content image could not be found
	*/
	public ContentImage findByContentOriginId_Last(long contentOriginId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException;

	/**
	* Returns the last content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content image, or <code>null</code> if a matching content image could not be found
	*/
	public ContentImage fetchByContentOriginId_Last(long contentOriginId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

	/**
	* Returns the content images before and after the current content image in the ordered set where contentOriginId = &#63;.
	*
	* @param contentImageId the primary key of the current content image
	* @param contentOriginId the content origin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content image
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public ContentImage[] findByContentOriginId_PrevAndNext(
		long contentImageId, long contentOriginId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator)
		throws NoSuchContentImageException;

	/**
	* Removes all the content images where contentOriginId = &#63; from the database.
	*
	* @param contentOriginId the content origin ID
	*/
	public void removeByContentOriginId(long contentOriginId);

	/**
	* Returns the number of content images where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @return the number of matching content images
	*/
	public int countByContentOriginId(long contentOriginId);

	/**
	* Caches the content image in the entity cache if it is enabled.
	*
	* @param contentImage the content image
	*/
	public void cacheResult(ContentImage contentImage);

	/**
	* Caches the content images in the entity cache if it is enabled.
	*
	* @param contentImages the content images
	*/
	public void cacheResult(java.util.List<ContentImage> contentImages);

	/**
	* Creates a new content image with the primary key. Does not add the content image to the database.
	*
	* @param contentImageId the primary key for the new content image
	* @return the new content image
	*/
	public ContentImage create(long contentImageId);

	/**
	* Removes the content image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image that was removed
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public ContentImage remove(long contentImageId)
		throws NoSuchContentImageException;

	public ContentImage updateImpl(ContentImage contentImage);

	/**
	* Returns the content image with the primary key or throws a {@link NoSuchContentImageException} if it could not be found.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image
	* @throws NoSuchContentImageException if a content image with the primary key could not be found
	*/
	public ContentImage findByPrimaryKey(long contentImageId)
		throws NoSuchContentImageException;

	/**
	* Returns the content image with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image, or <code>null</code> if a content image with the primary key could not be found
	*/
	public ContentImage fetchByPrimaryKey(long contentImageId);

	@Override
	public java.util.Map<java.io.Serializable, ContentImage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the content images.
	*
	* @return the content images
	*/
	public java.util.List<ContentImage> findAll();

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
	public java.util.List<ContentImage> findAll(int start, int end);

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
	public java.util.List<ContentImage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator);

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
	public java.util.List<ContentImage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentImage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the content images from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of content images.
	*
	* @return the number of content images
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}