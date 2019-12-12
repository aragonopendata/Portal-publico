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

import es.aragon.base.migration.exception.NoSuchContentUrlException;
import es.aragon.base.migration.model.ContentUrl;

/**
 * The persistence interface for the content url service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.ContentUrlPersistenceImpl
 * @see ContentUrlUtil
 * @generated
 */
@ProviderType
public interface ContentUrlPersistence extends BasePersistence<ContentUrl> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentUrlUtil} to access the content url persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the content urls where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content urls
	*/
	public java.util.List<ContentUrl> findByUuid(String uuid);

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
	public java.util.List<ContentUrl> findByUuid(String uuid, int start, int end);

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
	public java.util.List<ContentUrl> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator);

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
	public java.util.List<ContentUrl> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content url
	* @throws NoSuchContentUrlException if a matching content url could not be found
	*/
	public ContentUrl findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator)
		throws NoSuchContentUrlException;

	/**
	* Returns the first content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public ContentUrl fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator);

	/**
	* Returns the last content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content url
	* @throws NoSuchContentUrlException if a matching content url could not be found
	*/
	public ContentUrl findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator)
		throws NoSuchContentUrlException;

	/**
	* Returns the last content url in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public ContentUrl fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator);

	/**
	* Returns the content urls before and after the current content url in the ordered set where uuid = &#63;.
	*
	* @param contentUrlId the primary key of the current content url
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content url
	* @throws NoSuchContentUrlException if a content url with the primary key could not be found
	*/
	public ContentUrl[] findByUuid_PrevAndNext(long contentUrlId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator)
		throws NoSuchContentUrlException;

	/**
	* Removes all the content urls where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of content urls where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content urls
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the content url where contentOriginId = &#63; or throws a {@link NoSuchContentUrlException} if it could not be found.
	*
	* @param contentOriginId the content origin ID
	* @return the matching content url
	* @throws NoSuchContentUrlException if a matching content url could not be found
	*/
	public ContentUrl findByContentOriginId(long contentOriginId)
		throws NoSuchContentUrlException;

	/**
	* Returns the content url where contentOriginId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contentOriginId the content origin ID
	* @return the matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public ContentUrl fetchByContentOriginId(long contentOriginId);

	/**
	* Returns the content url where contentOriginId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contentOriginId the content origin ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching content url, or <code>null</code> if a matching content url could not be found
	*/
	public ContentUrl fetchByContentOriginId(long contentOriginId,
		boolean retrieveFromCache);

	/**
	* Removes the content url where contentOriginId = &#63; from the database.
	*
	* @param contentOriginId the content origin ID
	* @return the content url that was removed
	*/
	public ContentUrl removeByContentOriginId(long contentOriginId)
		throws NoSuchContentUrlException;

	/**
	* Returns the number of content urls where contentOriginId = &#63;.
	*
	* @param contentOriginId the content origin ID
	* @return the number of matching content urls
	*/
	public int countByContentOriginId(long contentOriginId);

	/**
	* Caches the content url in the entity cache if it is enabled.
	*
	* @param contentUrl the content url
	*/
	public void cacheResult(ContentUrl contentUrl);

	/**
	* Caches the content urls in the entity cache if it is enabled.
	*
	* @param contentUrls the content urls
	*/
	public void cacheResult(java.util.List<ContentUrl> contentUrls);

	/**
	* Creates a new content url with the primary key. Does not add the content url to the database.
	*
	* @param contentUrlId the primary key for the new content url
	* @return the new content url
	*/
	public ContentUrl create(long contentUrlId);

	/**
	* Removes the content url with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url that was removed
	* @throws NoSuchContentUrlException if a content url with the primary key could not be found
	*/
	public ContentUrl remove(long contentUrlId)
		throws NoSuchContentUrlException;

	public ContentUrl updateImpl(ContentUrl contentUrl);

	/**
	* Returns the content url with the primary key or throws a {@link NoSuchContentUrlException} if it could not be found.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url
	* @throws NoSuchContentUrlException if a content url with the primary key could not be found
	*/
	public ContentUrl findByPrimaryKey(long contentUrlId)
		throws NoSuchContentUrlException;

	/**
	* Returns the content url with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url, or <code>null</code> if a content url with the primary key could not be found
	*/
	public ContentUrl fetchByPrimaryKey(long contentUrlId);

	@Override
	public java.util.Map<java.io.Serializable, ContentUrl> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the content urls.
	*
	* @return the content urls
	*/
	public java.util.List<ContentUrl> findAll();

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
	public java.util.List<ContentUrl> findAll(int start, int end);

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
	public java.util.List<ContentUrl> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator);

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
	public java.util.List<ContentUrl> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentUrl> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the content urls from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of content urls.
	*
	* @return the number of content urls
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}