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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the custom category property service. This utility wraps {@link es.aragon.base.categories_custom_properties.service.persistence.impl.CustomCategoryPropertyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyPersistence
 * @see es.aragon.base.categories_custom_properties.service.persistence.impl.CustomCategoryPropertyPersistenceImpl
 * @generated
 */
@ProviderType
public class CustomCategoryPropertyUtil {
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
	public static void clearCache(CustomCategoryProperty customCategoryProperty) {
		getPersistence().clearCache(customCategoryProperty);
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
	public static List<CustomCategoryProperty> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CustomCategoryProperty> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CustomCategoryProperty> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CustomCategoryProperty update(
		CustomCategoryProperty customCategoryProperty) {
		return getPersistence().update(customCategoryProperty);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CustomCategoryProperty update(
		CustomCategoryProperty customCategoryProperty,
		ServiceContext serviceContext) {
		return getPersistence().update(customCategoryProperty, serviceContext);
	}

	/**
	* Returns all the custom category properties where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching custom category properties
	*/
	public static List<CustomCategoryProperty> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<CustomCategoryProperty> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<CustomCategoryProperty> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<CustomCategoryProperty> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByUuid_First(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByUuid_First(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByUuid_Last(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByUuid_Last(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the custom category properties before and after the current custom category property in the ordered set where uuid = &#63;.
	*
	* @param CustomCategoryPropertyId the primary key of the current custom category property
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public static CustomCategoryProperty[] findByUuid_PrevAndNext(
		long CustomCategoryPropertyId, String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByUuid_PrevAndNext(CustomCategoryPropertyId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the custom category properties where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of custom category properties where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching custom category properties
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the custom category property where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByUUID_G(String uuid, long groupId)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the custom category property where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the custom category property where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the custom category property where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the custom category property that was removed
	*/
	public static CustomCategoryProperty removeByUUID_G(String uuid,
		long groupId)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of custom category properties where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching custom category properties
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching custom category properties
	*/
	public static List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static CustomCategoryProperty[] findByUuid_C_PrevAndNext(
		long CustomCategoryPropertyId, String uuid, long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(CustomCategoryPropertyId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the custom category properties where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of custom category properties where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching custom category properties
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the custom category properties where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the matching custom category properties
	*/
	public static List<CustomCategoryProperty> findByCategoryId(long categoryId) {
		return getPersistence().findByCategoryId(categoryId);
	}

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
	public static List<CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end) {
		return getPersistence().findByCategoryId(categoryId, start, end);
	}

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
	public static List<CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .findByCategoryId(categoryId, start, end, orderByComparator);
	}

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
	public static List<CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCategoryId(categoryId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByCategoryId_First(
		long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByCategoryId_First(categoryId, orderByComparator);
	}

	/**
	* Returns the first custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByCategoryId_First(
		long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .fetchByCategoryId_First(categoryId, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByCategoryId_Last(
		long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByCategoryId_Last(categoryId, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByCategoryId_Last(
		long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .fetchByCategoryId_Last(categoryId, orderByComparator);
	}

	/**
	* Returns the custom category properties before and after the current custom category property in the ordered set where categoryId = &#63;.
	*
	* @param CustomCategoryPropertyId the primary key of the current custom category property
	* @param categoryId the category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public static CustomCategoryProperty[] findByCategoryId_PrevAndNext(
		long CustomCategoryPropertyId, long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByCategoryId_PrevAndNext(CustomCategoryPropertyId,
			categoryId, orderByComparator);
	}

	/**
	* Removes all the custom category properties where categoryId = &#63; from the database.
	*
	* @param categoryId the category ID
	*/
	public static void removeByCategoryId(long categoryId) {
		getPersistence().removeByCategoryId(categoryId);
	}

	/**
	* Returns the number of custom category properties where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the number of matching custom category properties
	*/
	public static int countByCategoryId(long categoryId) {
		return getPersistence().countByCategoryId(categoryId);
	}

	/**
	* Returns the custom category property where categoryId = &#63; and key = &#63; or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the matching custom category property
	* @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty findByCategoryIdAndKey(
		long categoryId, String key)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().findByCategoryIdAndKey(categoryId, key);
	}

	/**
	* Returns the custom category property where categoryId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByCategoryIdAndKey(
		long categoryId, String key) {
		return getPersistence().fetchByCategoryIdAndKey(categoryId, key);
	}

	/**
	* Returns the custom category property where categoryId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param categoryId the category ID
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByCategoryIdAndKey(
		long categoryId, String key, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCategoryIdAndKey(categoryId, key, retrieveFromCache);
	}

	/**
	* Removes the custom category property where categoryId = &#63; and key = &#63; from the database.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the custom category property that was removed
	*/
	public static CustomCategoryProperty removeByCategoryIdAndKey(
		long categoryId, String key)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().removeByCategoryIdAndKey(categoryId, key);
	}

	/**
	* Returns the number of custom category properties where categoryId = &#63; and key = &#63;.
	*
	* @param categoryId the category ID
	* @param key the key
	* @return the number of matching custom category properties
	*/
	public static int countByCategoryIdAndKey(long categoryId, String key) {
		return getPersistence().countByCategoryIdAndKey(categoryId, key);
	}

	/**
	* Returns all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @return the matching custom category properties
	*/
	public static List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text) {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText(vocabularyId, key, text);
	}

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
	public static List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end) {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText(vocabularyId, key, text,
			start, end);
	}

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
	public static List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText(vocabularyId, key, text,
			start, end, orderByComparator);
	}

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
	public static List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText(vocabularyId, key, text,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static CustomCategoryProperty findByVocabularyIdAndKeyAndText_First(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText_First(vocabularyId, key,
			text, orderByComparator);
	}

	/**
	* Returns the first custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByVocabularyIdAndKeyAndText_First(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyIdAndKeyAndText_First(vocabularyId, key,
			text, orderByComparator);
	}

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
	public static CustomCategoryProperty findByVocabularyIdAndKeyAndText_Last(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText_Last(vocabularyId, key,
			text, orderByComparator);
	}

	/**
	* Returns the last custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static CustomCategoryProperty fetchByVocabularyIdAndKeyAndText_Last(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyIdAndKeyAndText_Last(vocabularyId, key,
			text, orderByComparator);
	}

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
	public static CustomCategoryProperty[] findByVocabularyIdAndKeyAndText_PrevAndNext(
		long CustomCategoryPropertyId, long vocabularyId, String key,
		String text, OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence()
				   .findByVocabularyIdAndKeyAndText_PrevAndNext(CustomCategoryPropertyId,
			vocabularyId, key, text, orderByComparator);
	}

	/**
	* Removes all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	*/
	public static void removeByVocabularyIdAndKeyAndText(long vocabularyId,
		String key, String text) {
		getPersistence()
			.removeByVocabularyIdAndKeyAndText(vocabularyId, key, text);
	}

	/**
	* Returns the number of custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param key the key
	* @param text the text
	* @return the number of matching custom category properties
	*/
	public static int countByVocabularyIdAndKeyAndText(long vocabularyId,
		String key, String text) {
		return getPersistence()
				   .countByVocabularyIdAndKeyAndText(vocabularyId, key, text);
	}

	/**
	* Caches the custom category property in the entity cache if it is enabled.
	*
	* @param customCategoryProperty the custom category property
	*/
	public static void cacheResult(
		CustomCategoryProperty customCategoryProperty) {
		getPersistence().cacheResult(customCategoryProperty);
	}

	/**
	* Caches the custom category properties in the entity cache if it is enabled.
	*
	* @param customCategoryProperties the custom category properties
	*/
	public static void cacheResult(
		List<CustomCategoryProperty> customCategoryProperties) {
		getPersistence().cacheResult(customCategoryProperties);
	}

	/**
	* Creates a new custom category property with the primary key. Does not add the custom category property to the database.
	*
	* @param CustomCategoryPropertyId the primary key for the new custom category property
	* @return the new custom category property
	*/
	public static CustomCategoryProperty create(long CustomCategoryPropertyId) {
		return getPersistence().create(CustomCategoryPropertyId);
	}

	/**
	* Removes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property that was removed
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public static CustomCategoryProperty remove(long CustomCategoryPropertyId)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().remove(CustomCategoryPropertyId);
	}

	public static CustomCategoryProperty updateImpl(
		CustomCategoryProperty customCategoryProperty) {
		return getPersistence().updateImpl(customCategoryProperty);
	}

	/**
	* Returns the custom category property with the primary key or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property
	* @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	*/
	public static CustomCategoryProperty findByPrimaryKey(
		long CustomCategoryPropertyId)
		throws es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException {
		return getPersistence().findByPrimaryKey(CustomCategoryPropertyId);
	}

	/**
	* Returns the custom category property with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property, or <code>null</code> if a custom category property with the primary key could not be found
	*/
	public static CustomCategoryProperty fetchByPrimaryKey(
		long CustomCategoryPropertyId) {
		return getPersistence().fetchByPrimaryKey(CustomCategoryPropertyId);
	}

	public static java.util.Map<java.io.Serializable, CustomCategoryProperty> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the custom category properties.
	*
	* @return the custom category properties
	*/
	public static List<CustomCategoryProperty> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CustomCategoryProperty> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CustomCategoryProperty> findAll(int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CustomCategoryProperty> findAll(int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the custom category properties from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of custom category properties.
	*
	* @return the number of custom category properties
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CustomCategoryPropertyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomCategoryPropertyPersistence, CustomCategoryPropertyPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CustomCategoryPropertyPersistence.class);

		ServiceTracker<CustomCategoryPropertyPersistence, CustomCategoryPropertyPersistence> serviceTracker =
			new ServiceTracker<CustomCategoryPropertyPersistence, CustomCategoryPropertyPersistence>(bundle.getBundleContext(),
				CustomCategoryPropertyPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}