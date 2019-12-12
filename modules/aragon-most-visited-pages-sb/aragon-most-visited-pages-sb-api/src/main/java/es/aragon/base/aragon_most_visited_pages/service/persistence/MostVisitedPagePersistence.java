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

package es.aragon.base.aragon_most_visited_pages.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException;
import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;

/**
 * The persistence interface for the most visited page service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.aragon_most_visited_pages.service.persistence.impl.MostVisitedPagePersistenceImpl
 * @see MostVisitedPageUtil
 * @generated
 */
@ProviderType
public interface MostVisitedPagePersistence extends BasePersistence<MostVisitedPage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MostVisitedPageUtil} to access the most visited page persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the most visited pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching most visited pages
	*/
	public java.util.List<MostVisitedPage> findByUuid(String uuid);

	/**
	* Returns a range of all the most visited pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @return the range of matching most visited pages
	*/
	public java.util.List<MostVisitedPage> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the most visited pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching most visited pages
	*/
	public java.util.List<MostVisitedPage> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator);

	/**
	* Returns an ordered range of all the most visited pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching most visited pages
	*/
	public java.util.List<MostVisitedPage> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching most visited page
	* @throws NoSuchMostVisitedPageException if a matching most visited page could not be found
	*/
	public MostVisitedPage findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator)
		throws NoSuchMostVisitedPageException;

	/**
	* Returns the first most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching most visited page, or <code>null</code> if a matching most visited page could not be found
	*/
	public MostVisitedPage fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator);

	/**
	* Returns the last most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching most visited page
	* @throws NoSuchMostVisitedPageException if a matching most visited page could not be found
	*/
	public MostVisitedPage findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator)
		throws NoSuchMostVisitedPageException;

	/**
	* Returns the last most visited page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching most visited page, or <code>null</code> if a matching most visited page could not be found
	*/
	public MostVisitedPage fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator);

	/**
	* Returns the most visited pages before and after the current most visited page in the ordered set where uuid = &#63;.
	*
	* @param mostVisitedPageId the primary key of the current most visited page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next most visited page
	* @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	*/
	public MostVisitedPage[] findByUuid_PrevAndNext(long mostVisitedPageId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator)
		throws NoSuchMostVisitedPageException;

	/**
	* Removes all the most visited pages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of most visited pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching most visited pages
	*/
	public int countByUuid(String uuid);

	/**
	* Caches the most visited page in the entity cache if it is enabled.
	*
	* @param mostVisitedPage the most visited page
	*/
	public void cacheResult(MostVisitedPage mostVisitedPage);

	/**
	* Caches the most visited pages in the entity cache if it is enabled.
	*
	* @param mostVisitedPages the most visited pages
	*/
	public void cacheResult(java.util.List<MostVisitedPage> mostVisitedPages);

	/**
	* Creates a new most visited page with the primary key. Does not add the most visited page to the database.
	*
	* @param mostVisitedPageId the primary key for the new most visited page
	* @return the new most visited page
	*/
	public MostVisitedPage create(long mostVisitedPageId);

	/**
	* Removes the most visited page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page that was removed
	* @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	*/
	public MostVisitedPage remove(long mostVisitedPageId)
		throws NoSuchMostVisitedPageException;

	public MostVisitedPage updateImpl(MostVisitedPage mostVisitedPage);

	/**
	* Returns the most visited page with the primary key or throws a {@link NoSuchMostVisitedPageException} if it could not be found.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page
	* @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	*/
	public MostVisitedPage findByPrimaryKey(long mostVisitedPageId)
		throws NoSuchMostVisitedPageException;

	/**
	* Returns the most visited page with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page, or <code>null</code> if a most visited page with the primary key could not be found
	*/
	public MostVisitedPage fetchByPrimaryKey(long mostVisitedPageId);

	@Override
	public java.util.Map<java.io.Serializable, MostVisitedPage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the most visited pages.
	*
	* @return the most visited pages
	*/
	public java.util.List<MostVisitedPage> findAll();

	/**
	* Returns a range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @return the range of most visited pages
	*/
	public java.util.List<MostVisitedPage> findAll(int start, int end);

	/**
	* Returns an ordered range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of most visited pages
	*/
	public java.util.List<MostVisitedPage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator);

	/**
	* Returns an ordered range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of most visited pages
	*/
	public java.util.List<MostVisitedPage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MostVisitedPage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the most visited pages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of most visited pages.
	*
	* @return the number of most visited pages
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}