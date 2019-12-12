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

package es.aragon.base.categories_custom_properties.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;

/**
 * The persistence interface for the custom category property service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.categories_custom_properties.service.persistence.impl.CustomCategoryPropertyPersistenceImpl
 * @see CustomCategoryPropertyUtil
 * @generated
 */
@ProviderType
public interface CustomCategoryPropertyPersistence extends BasePersistence<CustomCategoryProperty> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomCategoryPropertyUtil} to access the custom category property persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the custom category properties where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid(String uuid);

	/**
	* Returns a range of all the custom category properties where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @return the range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the custom category properties where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns an ordered range of all the custom category properties where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the custom category properties before and after the current custom category property in the ordered set where uuid = &#63;.
	*
	* @param CustomCategoryPropertyId the primary key of the current custom category property
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty[] findByUuid_PrevAndNext(
		long CustomCategoryPropertyId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Removes all the custom category properties where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of custom category properties where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching custom category properties
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the custom category property where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByUUID_G(String uuid, long groupId)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the custom category property where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the custom category property where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the custom category property where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the custom category property that was removed
	*/
	public CustomCategoryProperty removeByUUID_G(String uuid, long groupId)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the number of custom category properties where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching custom category properties
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @return the range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns an ordered range of all the custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the custom category properties before and after the current custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param CustomCategoryPropertyId the primary key of the current custom category property
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty[] findByUuid_C_PrevAndNext(
		long CustomCategoryPropertyId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Removes all the custom category properties where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching custom category properties
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the custom category properties where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByCategoryId(
		long categoryId);

	/**
	* Returns a range of all the custom category properties where categoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryId the category ID
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @return the range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end);

	/**
	* Returns an ordered range of all the custom category properties where categoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryId the category ID
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns an ordered range of all the custom category properties where categoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryId the category ID
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByCategoryId_First(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the first custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByCategoryId_First(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the last custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByCategoryId_Last(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the last custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByCategoryId_Last(long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the custom category properties before and after the current custom category property in the ordered set where categoryId = &#63;.
	*
	* @param CustomCategoryPropertyId the primary key of the current custom category property
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty[] findByCategoryId_PrevAndNext(
		long CustomCategoryPropertyId, long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Removes all the custom category properties where categoryId = &#63; from the database.
	*
	* @param categoryId the category ID
	*/
	public void removeByCategoryId(long categoryId);

	/**
	* Returns the number of custom category properties where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the number of matching custom category properties
	*/
	public int countByCategoryId(long categoryId);

	/**
	* Returns the custom category property where categoryId = &#63; and key = &#63; or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByCategoryIdAndKey(long categoryId,
		String key) throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the custom category property where categoryId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByCategoryIdAndKey(long categoryId,
		String key);

	/**
	* Returns the custom category property where categoryId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param categoryId the category ID
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByCategoryIdAndKey(long categoryId,
		String key, boolean retrieveFromCache);

	/**
	* Removes the custom category property where categoryId = &#63; and key = &#63; from the database.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the custom category property that was removed
	*/
	public CustomCategoryProperty removeByCategoryIdAndKey(long categoryId,
		String key) throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the number of custom category properties where categoryId = &#63; and key = &#63;.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the number of matching custom category properties
	*/
	public int countByCategoryIdAndKey(long categoryId, String key);

	/**
	* Returns all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @return the matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text);

	/**
	* Returns a range of all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @return the range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end);

	/**
	* Returns an ordered range of all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns an ordered range of all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByVocabularyIdAndKeyAndText_First(
		long vocabularyId, String key, String text,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the first custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByVocabularyIdAndKeyAndText_First(
		long vocabularyId, String key, String text,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the last custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public CustomCategoryProperty findByVocabularyIdAndKeyAndText_Last(
		long vocabularyId, String key, String text,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the last custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public CustomCategoryProperty fetchByVocabularyIdAndKeyAndText_Last(
		long vocabularyId, String key, String text,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the custom category properties before and after the current custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param CustomCategoryPropertyId the primary key of the current custom category property
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty[] findByVocabularyIdAndKeyAndText_PrevAndNext(
		long CustomCategoryPropertyId, long vocabularyId, String key,
		String text,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Removes all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	*/
	public void removeByVocabularyIdAndKeyAndText(long vocabularyId,
		String key, String text);

	/**
	* Returns the number of custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @return the number of matching custom category properties
	*/
	public int countByVocabularyIdAndKeyAndText(long vocabularyId, String key,
		String text);

	/**
	* Caches the custom category property in the entity cache if it is enabled.
	*
	* @param customCategoryProperty the custom category property
	*/
	public void cacheResult(CustomCategoryProperty customCategoryProperty);

	/**
	* Caches the custom category properties in the entity cache if it is enabled.
	*
	* @param customCategoryProperties the custom category properties
	*/
	public void cacheResult(
		java.util.List<CustomCategoryProperty> customCategoryProperties);

	/**
	* Creates a new custom category property with the primary key. Does not add the custom category property to the database.
	*
	* @param CustomCategoryPropertyId the primary key for the new custom category property
	* @return the new custom category property
	*/
	public CustomCategoryProperty create(long CustomCategoryPropertyId);

	/**
	* Removes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property that was removed
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty remove(long CustomCategoryPropertyId)
		throws NoSuchCustomCategoryPropertyException;

	public CustomCategoryProperty updateImpl(
		CustomCategoryProperty customCategoryProperty);

	/**
	* Returns the custom category property with the primary key or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty findByPrimaryKey(
		long CustomCategoryPropertyId)
		throws NoSuchCustomCategoryPropertyException;

	/**
	* Returns the custom category property with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property, or <code>null</code> if a custom category property with the primary key could not be found
	*/
	public CustomCategoryProperty fetchByPrimaryKey(
		long CustomCategoryPropertyId);

	@Override
	public java.util.Map<java.io.Serializable, CustomCategoryProperty> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the custom category properties.
	*
	* @return the custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findAll();

	/**
	* Returns a range of all the custom category properties.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @return the range of custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findAll(int start, int end);

	/**
	* Returns an ordered range of all the custom category properties.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns an ordered range of all the custom category properties.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of custom category properties
	*/
	public java.util.List<CustomCategoryProperty> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the custom category properties from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of custom category properties.
	*
	* @return the number of custom category properties
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}