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

import es.aragon.base.migration.model.ContentMetadata;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the content metadata service. This utility wraps {@link es.aragon.base.migration.service.persistence.impl.ContentMetadataPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadataPersistence
 * @see es.aragon.base.migration.service.persistence.impl.ContentMetadataPersistenceImpl
 * @generated
 */
@ProviderType
public class ContentMetadataUtil {
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
	public static void clearCache(ContentMetadata contentMetadata) {
		getPersistence().clearCache(contentMetadata);
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
	public static List<ContentMetadata> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContentMetadata> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContentMetadata> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContentMetadata update(ContentMetadata contentMetadata) {
		return getPersistence().update(contentMetadata);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContentMetadata update(ContentMetadata contentMetadata,
		ServiceContext serviceContext) {
		return getPersistence().update(contentMetadata, serviceContext);
	}

	/**
	* Returns all the content metadatas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching content metadatas
	*/
	public static List<ContentMetadata> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ContentMetadata> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ContentMetadata> findByUuid(String uuid, int start,
		int end, OrderByComparator<ContentMetadata> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ContentMetadata> findByUuid(String uuid, int start,
		int end, OrderByComparator<ContentMetadata> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content metadata
	* @throws NoSuchContentMetadataException if a matching content metadata could not be found
	*/
	public static ContentMetadata findByUuid_First(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public static ContentMetadata fetchByUuid_First(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content metadata
	* @throws NoSuchContentMetadataException if a matching content metadata could not be found
	*/
	public static ContentMetadata findByUuid_Last(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last content metadata in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public static ContentMetadata fetchByUuid_Last(String uuid,
		OrderByComparator<ContentMetadata> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the content metadatas before and after the current content metadata in the ordered set where uuid = &#63;.
	*
	* @param contentMetadataId the primary key of the current content metadata
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content metadata
	* @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	*/
	public static ContentMetadata[] findByUuid_PrevAndNext(
		long contentMetadataId, String uuid,
		OrderByComparator<ContentMetadata> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contentMetadataId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the content metadatas where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of content metadatas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching content metadatas
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the content metadata where contentId = &#63; or throws a {@link NoSuchContentMetadataException} if it could not be found.
	*
	* @param contentId the content ID
	* @return the matching content metadata
	* @throws NoSuchContentMetadataException if a matching content metadata could not be found
	*/
	public static ContentMetadata findByContentId(long contentId)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence().findByContentId(contentId);
	}

	/**
	* Returns the content metadata where contentId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contentId the content ID
	* @return the matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public static ContentMetadata fetchByContentId(long contentId) {
		return getPersistence().fetchByContentId(contentId);
	}

	/**
	* Returns the content metadata where contentId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contentId the content ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching content metadata, or <code>null</code> if a matching content metadata could not be found
	*/
	public static ContentMetadata fetchByContentId(long contentId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByContentId(contentId, retrieveFromCache);
	}

	/**
	* Removes the content metadata where contentId = &#63; from the database.
	*
	* @param contentId the content ID
	* @return the content metadata that was removed
	*/
	public static ContentMetadata removeByContentId(long contentId)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence().removeByContentId(contentId);
	}

	/**
	* Returns the number of content metadatas where contentId = &#63;.
	*
	* @param contentId the content ID
	* @return the number of matching content metadatas
	*/
	public static int countByContentId(long contentId) {
		return getPersistence().countByContentId(contentId);
	}

	/**
	* Caches the content metadata in the entity cache if it is enabled.
	*
	* @param contentMetadata the content metadata
	*/
	public static void cacheResult(ContentMetadata contentMetadata) {
		getPersistence().cacheResult(contentMetadata);
	}

	/**
	* Caches the content metadatas in the entity cache if it is enabled.
	*
	* @param contentMetadatas the content metadatas
	*/
	public static void cacheResult(List<ContentMetadata> contentMetadatas) {
		getPersistence().cacheResult(contentMetadatas);
	}

	/**
	* Creates a new content metadata with the primary key. Does not add the content metadata to the database.
	*
	* @param contentMetadataId the primary key for the new content metadata
	* @return the new content metadata
	*/
	public static ContentMetadata create(long contentMetadataId) {
		return getPersistence().create(contentMetadataId);
	}

	/**
	* Removes the content metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata that was removed
	* @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	*/
	public static ContentMetadata remove(long contentMetadataId)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence().remove(contentMetadataId);
	}

	public static ContentMetadata updateImpl(ContentMetadata contentMetadata) {
		return getPersistence().updateImpl(contentMetadata);
	}

	/**
	* Returns the content metadata with the primary key or throws a {@link NoSuchContentMetadataException} if it could not be found.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata
	* @throws NoSuchContentMetadataException if a content metadata with the primary key could not be found
	*/
	public static ContentMetadata findByPrimaryKey(long contentMetadataId)
		throws es.aragon.base.migration.exception.NoSuchContentMetadataException {
		return getPersistence().findByPrimaryKey(contentMetadataId);
	}

	/**
	* Returns the content metadata with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata, or <code>null</code> if a content metadata with the primary key could not be found
	*/
	public static ContentMetadata fetchByPrimaryKey(long contentMetadataId) {
		return getPersistence().fetchByPrimaryKey(contentMetadataId);
	}

	public static java.util.Map<java.io.Serializable, ContentMetadata> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the content metadatas.
	*
	* @return the content metadatas
	*/
	public static List<ContentMetadata> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ContentMetadata> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ContentMetadata> findAll(int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ContentMetadata> findAll(int start, int end,
		OrderByComparator<ContentMetadata> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the content metadatas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of content metadatas.
	*
	* @return the number of content metadatas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContentMetadataPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentMetadataPersistence, ContentMetadataPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentMetadataPersistence.class);

		ServiceTracker<ContentMetadataPersistence, ContentMetadataPersistence> serviceTracker =
			new ServiceTracker<ContentMetadataPersistence, ContentMetadataPersistence>(bundle.getBundleContext(),
				ContentMetadataPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}