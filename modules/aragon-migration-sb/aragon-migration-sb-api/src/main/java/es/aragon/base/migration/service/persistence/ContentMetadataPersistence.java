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

import es.aragon.base.migration.exception.NoSuchContentMetadataException;
import es.aragon.base.migration.model.ContentMetadata;

/**
 * The persistence interface for the content metadata service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.ContentMetadataPersistenceImpl
 * @see ContentMetadataUtil
 * @generated
 */
@ProviderType
public interface ContentMetadataPersistence extends BasePersistence<ContentMetadata> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentMetadataUtil} to access the content metadata persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the content metadatas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content metadatas
	*/
	public java.util.List<ContentMetadata> findByUuid(String uuid);

	/**
	* Returns a range of all the content metadatas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @return the range of matching content metadatas
	*/
	public java.util.List<ContentMetadata> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the content metadatas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching content metadatas
	*/
	public java.util.List<ContentMetadata> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator);

	/**
	* Returns an ordered range of all the content metadatas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching content metadatas
	*/
	public java.util.List<ContentMetadata> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content metadata
	* @throws NoSuchContentMetadataException if a matching content metadata could not be found
	*/
	public ContentMetadata findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator)
		throws NoSuchContentMetadataException;

	/**
	* Returns the first content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public ContentMetadata fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator);

	/**
	* Returns the last content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content metadata
	* @throws NoSuchContentMetadataException if a matching content metadata could not be found
	*/
	public ContentMetadata findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator)
		throws NoSuchContentMetadataException;

	/**
	* Returns the last content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public ContentMetadata fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator);

	/**
	* Returns the content metadatas before and after the current content metadata in the ordered set where uuid = &#63;.
	*
	* @param contentMetadataId the primary key of the current content metadata
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content metadata
	* @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	*/
	public ContentMetadata[] findByUuid_PrevAndNext(long contentMetadataId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator)
		throws NoSuchContentMetadataException;

	/**
	* Removes all the content metadatas where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of content metadatas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content metadatas
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the content metadata where contentId = &#63; or throws a {@link NoSuchContentMetadataException} if it could not be found.
	*
	* @param contentId the content ID
	* @return the matching content metadata
	* @throws NoSuchContentMetadataException if a matching content metadata could not be found
	*/
	public ContentMetadata findByContentId(long contentId)
		throws NoSuchContentMetadataException;

	/**
	* Returns the content metadata where contentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contentId the content ID
	* @return the matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public ContentMetadata fetchByContentId(long contentId);

	/**
	* Returns the content metadata where contentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contentId the content ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public ContentMetadata fetchByContentId(long contentId,
		boolean retrieveFromCache);

	/**
	* Removes the content metadata where contentId = &#63; from the database.
	*
	* @param contentId the content ID
	* @return the content metadata that was removed
	*/
	public ContentMetadata removeByContentId(long contentId)
		throws NoSuchContentMetadataException;

	/**
	* Returns the number of content metadatas where contentId = &#63;.
	*
	* @param contentId the content ID
	* @return the number of matching content metadatas
	*/
	public int countByContentId(long contentId);

	/**
	* Caches the content metadata in the entity cache if it is enabled.
	*
	* @param contentMetadata the content metadata
	*/
	public void cacheResult(ContentMetadata contentMetadata);

	/**
	* Caches the content metadatas in the entity cache if it is enabled.
	*
	* @param contentMetadatas the content metadatas
	*/
	public void cacheResult(java.util.List<ContentMetadata> contentMetadatas);

	/**
	* Creates a new content metadata with the primary key. Does not add the content metadata to the database.
	*
	* @param contentMetadataId the primary key for the new content metadata
	* @return the new content metadata
	*/
	public ContentMetadata create(long contentMetadataId);

	/**
	* Removes the content metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata that was removed
	* @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	*/
	public ContentMetadata remove(long contentMetadataId)
		throws NoSuchContentMetadataException;

	public ContentMetadata updateImpl(ContentMetadata contentMetadata);

	/**
	* Returns the content metadata with the primary key or throws a {@link NoSuchContentMetadataException} if it could not be found.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata
	* @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	*/
	public ContentMetadata findByPrimaryKey(long contentMetadataId)
		throws NoSuchContentMetadataException;

	/**
	* Returns the content metadata with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata, or <code>null</code> if a content metadata with the primary key could not be found
	*/
	public ContentMetadata fetchByPrimaryKey(long contentMetadataId);

	@Override
	public java.util.Map<java.io.Serializable, ContentMetadata> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the content metadatas.
	*
	* @return the content metadatas
	*/
	public java.util.List<ContentMetadata> findAll();

	/**
	* Returns a range of all the content metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @return the range of content metadatas
	*/
	public java.util.List<ContentMetadata> findAll(int start, int end);

	/**
	* Returns an ordered range of all the content metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of content metadatas
	*/
	public java.util.List<ContentMetadata> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator);

	/**
	* Returns an ordered range of all the content metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of content metadatas
	*/
	public java.util.List<ContentMetadata> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContentMetadata> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the content metadatas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of content metadatas.
	*
	* @return the number of content metadatas
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}