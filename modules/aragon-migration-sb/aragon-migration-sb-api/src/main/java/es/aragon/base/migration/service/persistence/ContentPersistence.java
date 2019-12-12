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

import es.aragon.base.migration.exception.NoSuchContentException;
import es.aragon.base.migration.model.Content;

/**
 * The persistence interface for the content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.ContentPersistenceImpl
 * @see ContentUtil
 * @generated
 */
@ProviderType
public interface ContentPersistence extends BasePersistence<Content> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentUtil} to access the content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the contents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching contents
	*/
	public java.util.List<Content> findByUuid(String uuid);

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
	public java.util.List<Content> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Content> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

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
	public java.util.List<Content> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the first content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the last content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the last content in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the contents before and after the current content in the ordered set where uuid = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content[] findByUuid_PrevAndNext(long contentId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Removes all the contents where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of contents where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching contents
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the contents where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @return the matching contents
	*/
	public java.util.List<Content> findByAssignedUserId(long assignedUserId);

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
	public java.util.List<Content> findByAssignedUserId(long assignedUserId,
		int start, int end);

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
	public java.util.List<Content> findByAssignedUserId(long assignedUserId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

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
	public java.util.List<Content> findByAssignedUserId(long assignedUserId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByAssignedUserId_First(long assignedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the first content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByAssignedUserId_First(long assignedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the last content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByAssignedUserId_Last(long assignedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the last content in the ordered set where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByAssignedUserId_Last(long assignedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the contents before and after the current content in the ordered set where assignedUserId = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param assignedUserId the assigned user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content[] findByAssignedUserId_PrevAndNext(long contentId,
		long assignedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Removes all the contents where assignedUserId = &#63; from the database.
	*
	* @param assignedUserId the assigned user ID
	*/
	public void removeByAssignedUserId(long assignedUserId);

	/**
	* Returns the number of contents where assignedUserId = &#63;.
	*
	* @param assignedUserId the assigned user ID
	* @return the number of matching contents
	*/
	public int countByAssignedUserId(long assignedUserId);

	/**
	* Returns all the contents where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @return the matching contents
	*/
	public java.util.List<Content> findByLastModifiedUserId(
		long lastModifiedUserId);

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
	public java.util.List<Content> findByLastModifiedUserId(
		long lastModifiedUserId, int start, int end);

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
	public java.util.List<Content> findByLastModifiedUserId(
		long lastModifiedUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

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
	public java.util.List<Content> findByLastModifiedUserId(
		long lastModifiedUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByLastModifiedUserId_First(long lastModifiedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the first content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByLastModifiedUserId_First(long lastModifiedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the last content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByLastModifiedUserId_Last(long lastModifiedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the last content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByLastModifiedUserId_Last(long lastModifiedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the contents before and after the current content in the ordered set where lastModifiedUserId = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param lastModifiedUserId the last modified user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content[] findByLastModifiedUserId_PrevAndNext(long contentId,
		long lastModifiedUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Removes all the contents where lastModifiedUserId = &#63; from the database.
	*
	* @param lastModifiedUserId the last modified user ID
	*/
	public void removeByLastModifiedUserId(long lastModifiedUserId);

	/**
	* Returns the number of contents where lastModifiedUserId = &#63;.
	*
	* @param lastModifiedUserId the last modified user ID
	* @return the number of matching contents
	*/
	public int countByLastModifiedUserId(long lastModifiedUserId);

	/**
	* Returns all the contents where areaId = &#63;.
	*
	* @param areaId the area ID
	* @return the matching contents
	*/
	public java.util.List<Content> findByAreaId(long areaId);

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
	public java.util.List<Content> findByAreaId(long areaId, int start, int end);

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
	public java.util.List<Content> findByAreaId(long areaId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

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
	public java.util.List<Content> findByAreaId(long areaId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByAreaId_First(long areaId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the first content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByAreaId_First(long areaId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the last content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByAreaId_Last(long areaId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the last content in the ordered set where areaId = &#63;.
	*
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByAreaId_Last(long areaId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the contents before and after the current content in the ordered set where areaId = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param areaId the area ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content[] findByAreaId_PrevAndNext(long contentId, long areaId,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Removes all the contents where areaId = &#63; from the database.
	*
	* @param areaId the area ID
	*/
	public void removeByAreaId(long areaId);

	/**
	* Returns the number of contents where areaId = &#63;.
	*
	* @param areaId the area ID
	* @return the number of matching contents
	*/
	public int countByAreaId(long areaId);

	/**
	* Returns all the contents where url = &#63;.
	*
	* @param url the url
	* @return the matching contents
	*/
	public java.util.List<Content> findByUrl(String url);

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
	public java.util.List<Content> findByUrl(String url, int start, int end);

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
	public java.util.List<Content> findByUrl(String url, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

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
	public java.util.List<Content> findByUrl(String url, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByUrl_First(String url,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the first content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByUrl_First(String url,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the last content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content
	* @throws NoSuchContentException if a matching content could not be found
	*/
	public Content findByUrl_Last(String url,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Returns the last content in the ordered set where url = &#63;.
	*
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching content, or <code>null</code> if a matching content could not be found
	*/
	public Content fetchByUrl_Last(String url,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

	/**
	* Returns the contents before and after the current content in the ordered set where url = &#63;.
	*
	* @param contentId the primary key of the current content
	* @param url the url
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content[] findByUrl_PrevAndNext(long contentId, String url,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException;

	/**
	* Removes all the contents where url = &#63; from the database.
	*
	* @param url the url
	*/
	public void removeByUrl(String url);

	/**
	* Returns the number of contents where url = &#63;.
	*
	* @param url the url
	* @return the number of matching contents
	*/
	public int countByUrl(String url);

	/**
	* Caches the content in the entity cache if it is enabled.
	*
	* @param content the content
	*/
	public void cacheResult(Content content);

	/**
	* Caches the contents in the entity cache if it is enabled.
	*
	* @param contents the contents
	*/
	public void cacheResult(java.util.List<Content> contents);

	/**
	* Creates a new content with the primary key. Does not add the content to the database.
	*
	* @param contentId the primary key for the new content
	* @return the new content
	*/
	public Content create(long contentId);

	/**
	* Removes the content with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentId the primary key of the content
	* @return the content that was removed
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content remove(long contentId) throws NoSuchContentException;

	public Content updateImpl(Content content);

	/**
	* Returns the content with the primary key or throws a {@link NoSuchContentException} if it could not be found.
	*
	* @param contentId the primary key of the content
	* @return the content
	* @throws NoSuchContentException if a content with the primary key could not be found
	*/
	public Content findByPrimaryKey(long contentId)
		throws NoSuchContentException;

	/**
	* Returns the content with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentId the primary key of the content
	* @return the content, or <code>null</code> if a content with the primary key could not be found
	*/
	public Content fetchByPrimaryKey(long contentId);

	@Override
	public java.util.Map<java.io.Serializable, Content> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the contents.
	*
	* @return the contents
	*/
	public java.util.List<Content> findAll();

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
	public java.util.List<Content> findAll(int start, int end);

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
	public java.util.List<Content> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator);

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
	public java.util.List<Content> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the contents from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of contents.
	*
	* @return the number of contents
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}