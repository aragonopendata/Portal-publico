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

import es.aragon.base.migration.model.Content;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the content service. This utility wraps {@link es.aragon.base.migration.service.persistence.impl.ContentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentPersistence
 * @see es.aragon.base.migration.service.persistence.impl.ContentPersistenceImpl
 * @generated
 */
@ProviderType
public class ContentUtil {
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
	public static void clearCache(Content content) {
		getPersistence().clearCache(content);
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
	public static List<Content> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Content> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Content> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Content update(Content content) {
		return getPersistence().update(content);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Content update(Content content, ServiceContext serviceContext) {
		return getPersistence().update(content, serviceContext);
	}

	/**
	* Returns all the contents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching contents
	*/
	public static List<Content> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the contents where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of matching contents
	*/
	public static List<Content> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the contents where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByUuid(String uuid, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contents where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByUuid(String uuid, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByUuid_First(String uuid,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByUuid_First(String uuid,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByUuid_Last(String uuid,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByUuid_Last(String uuid,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the contents before and after the current content in the ordered set where uuid = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content[] findByUuid_PrevAndNext(long contentId, String uuid,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contentId, uuid, orderByComparator);
	}

	/**
	* Removes all the contents where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of contents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching contents
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the contents where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @return the matching contents
	*/
	public static List<Content> findByAssignedUserId(long assignedUserId) {
		return getPersistence().findByAssignedUserId(assignedUserId);
	}

	/**
	* Returns a range of all the contents where assignedUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assignedUserId the assigned user ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of matching contents
	*/
	public static List<Content> findByAssignedUserId(long assignedUserId,
		int start, int end) {
		return getPersistence().findByAssignedUserId(assignedUserId, start, end);
	}

	/**
	* Returns an ordered range of all the contents where assignedUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assignedUserId the assigned user ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByAssignedUserId(long assignedUserId,
		int start, int end, OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .findByAssignedUserId(assignedUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the contents where assignedUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assignedUserId the assigned user ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByAssignedUserId(long assignedUserId,
		int start, int end, OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssignedUserId(assignedUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByAssignedUserId_First(long assignedUserId,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByAssignedUserId_First(assignedUserId, orderByComparator);
	}

	/**
	* Returns the first content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByAssignedUserId_First(long assignedUserId,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .fetchByAssignedUserId_First(assignedUserId,
			orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByAssignedUserId_Last(long assignedUserId,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByAssignedUserId_Last(assignedUserId, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByAssignedUserId_Last(long assignedUserId,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .fetchByAssignedUserId_Last(assignedUserId, orderByComparator);
	}

	/**
	* Returns the contents before and after the current content in the ordered set where assignedUserId = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content[] findByAssignedUserId_PrevAndNext(long contentId,
		long assignedUserId, OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByAssignedUserId_PrevAndNext(contentId, assignedUserId,
			orderByComparator);
	}

	/**
	* Removes all the contents where assignedUserId = &#63; from the database.
	*
	* @param assignedUserId the assigned user ID
	*/
	public static void removeByAssignedUserId(long assignedUserId) {
		getPersistence().removeByAssignedUserId(assignedUserId);
	}

	/**
	* Returns the number of contents where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @return the number of matching contents
	*/
	public static int countByAssignedUserId(long assignedUserId) {
		return getPersistence().countByAssignedUserId(assignedUserId);
	}

	/**
	* Returns all the contents where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @return the matching contents
	*/
	public static List<Content> findByLastModifiedUserId(
		long lastModifiedUserId) {
		return getPersistence().findByLastModifiedUserId(lastModifiedUserId);
	}

	/**
	* Returns a range of all the contents where lastModifiedUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastModifiedUserId the last modified user ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of matching contents
	*/
	public static List<Content> findByLastModifiedUserId(
		long lastModifiedUserId, int start, int end) {
		return getPersistence()
				   .findByLastModifiedUserId(lastModifiedUserId, start, end);
	}

	/**
	* Returns an ordered range of all the contents where lastModifiedUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastModifiedUserId the last modified user ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByLastModifiedUserId(
		long lastModifiedUserId, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .findByLastModifiedUserId(lastModifiedUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the contents where lastModifiedUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastModifiedUserId the last modified user ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByLastModifiedUserId(
		long lastModifiedUserId, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByLastModifiedUserId(lastModifiedUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByLastModifiedUserId_First(
		long lastModifiedUserId, OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByLastModifiedUserId_First(lastModifiedUserId,
			orderByComparator);
	}

	/**
	* Returns the first content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByLastModifiedUserId_First(
		long lastModifiedUserId, OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .fetchByLastModifiedUserId_First(lastModifiedUserId,
			orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByLastModifiedUserId_Last(
		long lastModifiedUserId, OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByLastModifiedUserId_Last(lastModifiedUserId,
			orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByLastModifiedUserId_Last(
		long lastModifiedUserId, OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .fetchByLastModifiedUserId_Last(lastModifiedUserId,
			orderByComparator);
	}

	/**
	* Returns the contents before and after the current content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content[] findByLastModifiedUserId_PrevAndNext(
		long contentId, long lastModifiedUserId,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByLastModifiedUserId_PrevAndNext(contentId,
			lastModifiedUserId, orderByComparator);
	}

	/**
	* Removes all the contents where lastModifiedUserId = &#63; from the database.
	*
	* @param lastModifiedUserId the last modified user ID
	*/
	public static void removeByLastModifiedUserId(long lastModifiedUserId) {
		getPersistence().removeByLastModifiedUserId(lastModifiedUserId);
	}

	/**
	* Returns the number of contents where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @return the number of matching contents
	*/
	public static int countByLastModifiedUserId(long lastModifiedUserId) {
		return getPersistence().countByLastModifiedUserId(lastModifiedUserId);
	}

	/**
	* Returns all the contents where areaId = &#63;.
	*
	* @param areaId the area ID
	* @return the matching contents
	*/
	public static List<Content> findByAreaId(long areaId) {
		return getPersistence().findByAreaId(areaId);
	}

	/**
	* Returns a range of all the contents where areaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param areaId the area ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of matching contents
	*/
	public static List<Content> findByAreaId(long areaId, int start, int end) {
		return getPersistence().findByAreaId(areaId, start, end);
	}

	/**
	* Returns an ordered range of all the contents where areaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param areaId the area ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByAreaId(long areaId, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence()
				   .findByAreaId(areaId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contents where areaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param areaId the area ID
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByAreaId(long areaId, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByAreaId(areaId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByAreaId_First(long areaId,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByAreaId_First(areaId, orderByComparator);
	}

	/**
	* Returns the first content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByAreaId_First(long areaId,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().fetchByAreaId_First(areaId, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByAreaId_Last(long areaId,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByAreaId_Last(areaId, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByAreaId_Last(long areaId,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().fetchByAreaId_Last(areaId, orderByComparator);
	}

	/**
	* Returns the contents before and after the current content in the ordered set where areaId = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content[] findByAreaId_PrevAndNext(long contentId,
		long areaId, OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByAreaId_PrevAndNext(contentId, areaId,
			orderByComparator);
	}

	/**
	* Removes all the contents where areaId = &#63; from the database.
	*
	* @param areaId the area ID
	*/
	public static void removeByAreaId(long areaId) {
		getPersistence().removeByAreaId(areaId);
	}

	/**
	* Returns the number of contents where areaId = &#63;.
	*
	* @param areaId the area ID
	* @return the number of matching contents
	*/
	public static int countByAreaId(long areaId) {
		return getPersistence().countByAreaId(areaId);
	}

	/**
	* Returns all the contents where url = &#63;.
	*
	* @param url the url
	* @return the matching contents
	*/
	public static List<Content> findByUrl(String url) {
		return getPersistence().findByUrl(url);
	}

	/**
	* Returns a range of all the contents where url = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param url the url
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of matching contents
	*/
	public static List<Content> findByUrl(String url, int start, int end) {
		return getPersistence().findByUrl(url, start, end);
	}

	/**
	* Returns an ordered range of all the contents where url = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param url the url
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByUrl(String url, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().findByUrl(url, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contents where url = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param url the url
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contents
	*/
	public static List<Content> findByUrl(String url, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUrl(url, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByUrl_First(String url,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByUrl_First(url, orderByComparator);
	}

	/**
	* Returns the first content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByUrl_First(String url,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().fetchByUrl_First(url, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public static Content findByUrl_Last(String url,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByUrl_Last(url, orderByComparator);
	}

	/**
	* Returns the last content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public static Content fetchByUrl_Last(String url,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().fetchByUrl_Last(url, orderByComparator);
	}

	/**
	* Returns the contents before and after the current content in the ordered set where url = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content[] findByUrl_PrevAndNext(long contentId, String url,
		OrderByComparator<Content> orderByComparator)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence()
				   .findByUrl_PrevAndNext(contentId, url, orderByComparator);
	}

	/**
	* Removes all the contents where url = &#63; from the database.
	*
	* @param url the url
	*/
	public static void removeByUrl(String url) {
		getPersistence().removeByUrl(url);
	}

	/**
	* Returns the number of contents where url = &#63;.
	*
	* @param url the url
	* @return the number of matching contents
	*/
	public static int countByUrl(String url) {
		return getPersistence().countByUrl(url);
	}

	/**
	* Caches the content in the entity cache if it is enabled.
	*
	* @param content the content
	*/
	public static void cacheResult(Content content) {
		getPersistence().cacheResult(content);
	}

	/**
	* Caches the contents in the entity cache if it is enabled.
	*
	* @param contents the contents
	*/
	public static void cacheResult(List<Content> contents) {
		getPersistence().cacheResult(contents);
	}

	/**
	* Creates a new content with the primary key. Does not add the content to the database.
	*
	* @param contentId the primary key for the new content
	* @return the new content
	*/
	public static Content create(long contentId) {
		return getPersistence().create(contentId);
	}

	/**
	* Removes the content with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentId the primary key of the content
	* @return the content that was removed
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content remove(long contentId)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().remove(contentId);
	}

	public static Content updateImpl(Content content) {
		return getPersistence().updateImpl(content);
	}

	/**
	* Returns the content with the primary key or throws a {@link NoSuchContentException} if it could not be found.
	*
	* @param contentId the primary key of the content
	* @return the content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public static Content findByPrimaryKey(long contentId)
		throws es.aragon.base.migration.exception.NoSuchContentException {
		return getPersistence().findByPrimaryKey(contentId);
	}

	/**
	* Returns the content with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentId the primary key of the content
	* @return the content, or <code>null</code> if a content with the primary key could not be found
	*/
	public static Content fetchByPrimaryKey(long contentId) {
		return getPersistence().fetchByPrimaryKey(contentId);
	}

	public static java.util.Map<java.io.Serializable, Content> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contents.
	*
	* @return the contents
	*/
	public static List<Content> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the contents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of contents
	*/
	public static List<Content> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the contents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contents
	*/
	public static List<Content> findAll(int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contents
	*/
	public static List<Content> findAll(int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contents from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contents.
	*
	* @return the number of contents
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentPersistence, ContentPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentPersistence.class);

		ServiceTracker<ContentPersistence, ContentPersistence> serviceTracker = new ServiceTracker<ContentPersistence, ContentPersistence>(bundle.getBundleContext(),
				ContentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}