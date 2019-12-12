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

import es.aragon.base.migration.exception.NoSuchAreaException;
import es.aragon.base.migration.model.Area;

/**
 * The persistence interface for the area service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.AreaPersistenceImpl
 * @see AreaUtil
 * @generated
 */
@ProviderType
public interface AreaPersistence extends BasePersistence<Area> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AreaUtil} to access the area persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the areas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching areas
	*/
	public java.util.List<Area> findByUuid(String uuid);

	/**
	* Returns a range of all the areas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @return the range of matching areas
	*/
	public java.util.List<Area> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the areas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching areas
	*/
	public java.util.List<Area> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator);

	/**
	* Returns an ordered range of all the areas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching areas
	*/
	public java.util.List<Area> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first area in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching area
	* @throws NoSuchAreaException if a matching area could not be found
	*/
	public Area findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator)
		throws NoSuchAreaException;

	/**
	* Returns the first area in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching area, or <code>null</code> if a matching area could not be found
	*/
	public Area fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator);

	/**
	* Returns the last area in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching area
	* @throws NoSuchAreaException if a matching area could not be found
	*/
	public Area findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator)
		throws NoSuchAreaException;

	/**
	* Returns the last area in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching area, or <code>null</code> if a matching area could not be found
	*/
	public Area fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator);

	/**
	* Returns the areas before and after the current area in the ordered set where uuid = &#63;.
	*
	* @param areaId the primary key of the current area
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next area
	* @throws NoSuchAreaException if a area with the primary key could not be found
	*/
	public Area[] findByUuid_PrevAndNext(long areaId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator)
		throws NoSuchAreaException;

	/**
	* Removes all the areas where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of areas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching areas
	*/
	public int countByUuid(String uuid);

	/**
	* Caches the area in the entity cache if it is enabled.
	*
	* @param area the area
	*/
	public void cacheResult(Area area);

	/**
	* Caches the areas in the entity cache if it is enabled.
	*
	* @param areas the areas
	*/
	public void cacheResult(java.util.List<Area> areas);

	/**
	* Creates a new area with the primary key. Does not add the area to the database.
	*
	* @param areaId the primary key for the new area
	* @return the new area
	*/
	public Area create(long areaId);

	/**
	* Removes the area with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param areaId the primary key of the area
	* @return the area that was removed
	* @throws NoSuchAreaException if a area with the primary key could not be found
	*/
	public Area remove(long areaId) throws NoSuchAreaException;

	public Area updateImpl(Area area);

	/**
	* Returns the area with the primary key or throws a {@link NoSuchAreaException} if it could not be found.
	*
	* @param areaId the primary key of the area
	* @return the area
	* @throws NoSuchAreaException if a area with the primary key could not be found
	*/
	public Area findByPrimaryKey(long areaId) throws NoSuchAreaException;

	/**
	* Returns the area with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param areaId the primary key of the area
	* @return the area, or <code>null</code> if a area with the primary key could not be found
	*/
	public Area fetchByPrimaryKey(long areaId);

	@Override
	public java.util.Map<java.io.Serializable, Area> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the areas.
	*
	* @return the areas
	*/
	public java.util.List<Area> findAll();

	/**
	* Returns a range of all the areas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @return the range of areas
	*/
	public java.util.List<Area> findAll(int start, int end);

	/**
	* Returns an ordered range of all the areas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of areas
	*/
	public java.util.List<Area> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator);

	/**
	* Returns an ordered range of all the areas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of areas
	*/
	public java.util.List<Area> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Area> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the areas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of areas.
	*
	* @return the number of areas
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}