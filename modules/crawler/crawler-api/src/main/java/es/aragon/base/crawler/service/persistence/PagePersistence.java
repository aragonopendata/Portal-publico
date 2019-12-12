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

package es.aragon.base.crawler.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.crawler.exception.NoSuchPageException;
import es.aragon.base.crawler.model.Page;

/**
 * The persistence interface for the page service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.crawler.service.persistence.impl.PagePersistenceImpl
 * @see PageUtil
 * @generated
 */
@ProviderType
public interface PagePersistence extends BasePersistence<Page> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PageUtil} to access the page persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching pages
	*/
	public java.util.List<Page> findByUuid(String uuid);

	/**
	* Returns a range of all the pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages
	*/
	public java.util.List<Page> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages
	*/
	public java.util.List<Page> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns an ordered range of all the pages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching pages
	*/
	public java.util.List<Page> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public Page findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Returns the first page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page, or <code>null</code> if a matching page could not be found
	*/
	public Page fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns the last page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public Page findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Returns the last page in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page, or <code>null</code> if a matching page could not be found
	*/
	public Page fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns the pages before and after the current page in the ordered set where uuid = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public Page[] findByUuid_PrevAndNext(long pageId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Returns all the pages that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching pages that the user has permission to view
	*/
	public java.util.List<Page> filterFindByUuid(String uuid);

	/**
	* Returns a range of all the pages that the user has permission to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages that the user has permission to view
	*/
	public java.util.List<Page> filterFindByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the pages that the user has permissions to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages that the user has permission to view
	*/
	public java.util.List<Page> filterFindByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns the pages before and after the current page in the ordered set of pages that the user has permission to view where uuid = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public Page[] filterFindByUuid_PrevAndNext(long pageId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Removes all the pages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of pages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching pages
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the number of pages that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching pages that the user has permission to view
	*/
	public int filterCountByUuid(String uuid);

	/**
	* Returns all the pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the matching pages
	*/
	public java.util.List<Page> findByRootPageId(long rootPageId);

	/**
	* Returns a range of all the pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages
	*/
	public java.util.List<Page> findByRootPageId(long rootPageId, int start,
		int end);

	/**
	* Returns an ordered range of all the pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages
	*/
	public java.util.List<Page> findByRootPageId(long rootPageId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns an ordered range of all the pages where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching pages
	*/
	public java.util.List<Page> findByRootPageId(long rootPageId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public Page findByRootPageId_First(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Returns the first page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching page, or <code>null</code> if a matching page could not be found
	*/
	public Page fetchByRootPageId_First(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns the last page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page
	* @throws NoSuchPageException if a matching page could not be found
	*/
	public Page findByRootPageId_Last(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Returns the last page in the ordered set where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching page, or <code>null</code> if a matching page could not be found
	*/
	public Page fetchByRootPageId_Last(long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns the pages before and after the current page in the ordered set where rootPageId = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public Page[] findByRootPageId_PrevAndNext(long pageId, long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Returns all the pages that the user has permission to view where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the matching pages that the user has permission to view
	*/
	public java.util.List<Page> filterFindByRootPageId(long rootPageId);

	/**
	* Returns a range of all the pages that the user has permission to view where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of matching pages that the user has permission to view
	*/
	public java.util.List<Page> filterFindByRootPageId(long rootPageId,
		int start, int end);

	/**
	* Returns an ordered range of all the pages that the user has permissions to view where rootPageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rootPageId the root page ID
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching pages that the user has permission to view
	*/
	public java.util.List<Page> filterFindByRootPageId(long rootPageId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns the pages before and after the current page in the ordered set of pages that the user has permission to view where rootPageId = &#63;.
	*
	* @param pageId the primary key of the current page
	* @param rootPageId the root page ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public Page[] filterFindByRootPageId_PrevAndNext(long pageId,
		long rootPageId,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator)
		throws NoSuchPageException;

	/**
	* Removes all the pages where rootPageId = &#63; from the database.
	*
	* @param rootPageId the root page ID
	*/
	public void removeByRootPageId(long rootPageId);

	/**
	* Returns the number of pages where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the number of matching pages
	*/
	public int countByRootPageId(long rootPageId);

	/**
	* Returns the number of pages that the user has permission to view where rootPageId = &#63;.
	*
	* @param rootPageId the root page ID
	* @return the number of matching pages that the user has permission to view
	*/
	public int filterCountByRootPageId(long rootPageId);

	/**
	* Caches the page in the entity cache if it is enabled.
	*
	* @param page the page
	*/
	public void cacheResult(Page page);

	/**
	* Caches the pages in the entity cache if it is enabled.
	*
	* @param pages the pages
	*/
	public void cacheResult(java.util.List<Page> pages);

	/**
	* Creates a new page with the primary key. Does not add the page to the database.
	*
	* @param pageId the primary key for the new page
	* @return the new page
	*/
	public Page create(long pageId);

	/**
	* Removes the page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pageId the primary key of the page
	* @return the page that was removed
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public Page remove(long pageId) throws NoSuchPageException;

	public Page updateImpl(Page page);

	/**
	* Returns the page with the primary key or throws a {@link NoSuchPageException} if it could not be found.
	*
	* @param pageId the primary key of the page
	* @return the page
	* @throws NoSuchPageException if a page with the primary key could not be found
	*/
	public Page findByPrimaryKey(long pageId) throws NoSuchPageException;

	/**
	* Returns the page with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pageId the primary key of the page
	* @return the page, or <code>null</code> if a page with the primary key could not be found
	*/
	public Page fetchByPrimaryKey(long pageId);

	@Override
	public java.util.Map<java.io.Serializable, Page> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the pages.
	*
	* @return the pages
	*/
	public java.util.List<Page> findAll();

	/**
	* Returns a range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of pages
	*/
	public java.util.List<Page> findAll(int start, int end);

	/**
	* Returns an ordered range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of pages
	*/
	public java.util.List<Page> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator);

	/**
	* Returns an ordered range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of pages
	*/
	public java.util.List<Page> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Page> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the pages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of pages.
	*
	* @return the number of pages
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}