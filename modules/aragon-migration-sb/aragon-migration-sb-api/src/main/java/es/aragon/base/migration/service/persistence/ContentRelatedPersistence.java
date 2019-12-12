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

import es.aragon.base.migration.exception.NoSuchContentRelatedException;
import es.aragon.base.migration.model.ContentRelated;

/**
 * The persistence interface for the content related service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.ContentRelatedPersistenceImpl
 * @see ContentRelatedUtil
 * @generated
 */
@ProviderType
public interface ContentRelatedPersistence extends BasePersistence<ContentRelated> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentRelatedUtil} to access the content related persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the content relateds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content relateds
	*/
	public java.util.List<ContentRelated> findByUuid(String uuid);

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
	public java.util.List<ContentRelated> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ContentRelated> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

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
	public java.util.List<ContentRelated> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public ContentRelated findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException;

	/**
	* Returns the first content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public ContentRelated fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

	/**
	* Returns the last content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public ContentRelated findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException;

	/**
	* Returns the last content related in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public ContentRelated fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

	/**
	* Returns the content relateds before and after the current content related in the ordered set where uuid = &#63;.
	*
	* @param contentRelatedId the primary key of the current content related
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content related
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public ContentRelated[] findByUuid_PrevAndNext(long contentRelatedId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException;

	/**
	* Removes all the content relateds where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of content relateds where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content relateds
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the content relateds where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @return the matching content relateds
	*/
	public java.util.List<ContentRelated> findByContentParentId(
		long contentParentId);

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
	public java.util.List<ContentRelated> findByContentParentId(
		long contentParentId, int start, int end);

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
	public java.util.List<ContentRelated> findByContentParentId(
		long contentParentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

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
	public java.util.List<ContentRelated> findByContentParentId(
		long contentParentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public ContentRelated findByContentParentId_First(long contentParentId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException;

	/**
	* Returns the first content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public ContentRelated fetchByContentParentId_First(long contentParentId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

	/**
	* Returns the last content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related
	* @throws NoSuchContentRelatedException if a matching content related could not be found
	*/
	public ContentRelated findByContentParentId_Last(long contentParentId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException;

	/**
	* Returns the last content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content related, or <code>null</code> if a matching content related could not be found
	*/
	public ContentRelated fetchByContentParentId_Last(long contentParentId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

	/**
	* Returns the content relateds before and after the current content related in the ordered set where contentParentId = &#63;.
	*
	* @param contentRelatedId the primary key of the current content related
	* @param contentParentId the content parent ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content related
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public ContentRelated[] findByContentParentId_PrevAndNext(
		long contentRelatedId, long contentParentId,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException;

	/**
	* Removes all the content relateds where contentParentId = &#63; from the database.
	*
	* @param contentParentId the content parent ID
	*/
	public void removeByContentParentId(long contentParentId);

	/**
	* Returns the number of content relateds where contentParentId = &#63;.
	*
	* @param contentParentId the content parent ID
	* @return the number of matching content relateds
	*/
	public int countByContentParentId(long contentParentId);

	/**
	* Caches the content related in the entity cache if it is enabled.
	*
	* @param contentRelated the content related
	*/
	public void cacheResult(ContentRelated contentRelated);

	/**
	* Caches the content relateds in the entity cache if it is enabled.
	*
	* @param contentRelateds the content relateds
	*/
	public void cacheResult(java.util.List<ContentRelated> contentRelateds);

	/**
	* Creates a new content related with the primary key. Does not add the content related to the database.
	*
	* @param contentRelatedId the primary key for the new content related
	* @return the new content related
	*/
	public ContentRelated create(long contentRelatedId);

	/**
	* Removes the content related with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related that was removed
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public ContentRelated remove(long contentRelatedId)
		throws NoSuchContentRelatedException;

	public ContentRelated updateImpl(ContentRelated contentRelated);

	/**
	* Returns the content related with the primary key or throws a {@link NoSuchContentRelatedException} if it could not be found.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related
	* @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	*/
	public ContentRelated findByPrimaryKey(long contentRelatedId)
		throws NoSuchContentRelatedException;

	/**
	* Returns the content related with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related, or <code>null</code> if a content related with the primary key could not be found
	*/
	public ContentRelated fetchByPrimaryKey(long contentRelatedId);

	@Override
	public java.util.Map<java.io.Serializable, ContentRelated> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the content relateds.
	*
	* @return the content relateds
	*/
	public java.util.List<ContentRelated> findAll();

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
	public java.util.List<ContentRelated> findAll(int start, int end);

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
	public java.util.List<ContentRelated> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator);

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
	public java.util.List<ContentRelated> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the content relateds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of content relateds.
	*
	* @return the number of content relateds
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}