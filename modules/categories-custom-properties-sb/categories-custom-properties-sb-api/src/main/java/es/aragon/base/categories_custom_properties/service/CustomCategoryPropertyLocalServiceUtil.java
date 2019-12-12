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

package es.aragon.base.categories_custom_properties.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CustomCategoryProperty. This utility wraps
 * {@link es.aragon.base.categories_custom_properties.service.impl.CustomCategoryPropertyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyLocalService
 * @see es.aragon.base.categories_custom_properties.service.base.CustomCategoryPropertyLocalServiceBaseImpl
 * @see es.aragon.base.categories_custom_properties.service.impl.CustomCategoryPropertyLocalServiceImpl
 * @generated
 */
@ProviderType
public class CustomCategoryPropertyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.categories_custom_properties.service.impl.CustomCategoryPropertyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the custom category property to the database. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was added
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty addCustomCategoryProperty(
		es.aragon.base.categories_custom_properties.model.CustomCategoryProperty customCategoryProperty) {
		return getService().addCustomCategoryProperty(customCategoryProperty);
	}

	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty addCustomCategoryProperty(
		long groupId, long companyId, long userId, String userName,
		java.util.Date createDate, java.util.Date modifiedDate,
		long vocabularyId, long categoryId, String key, String text) {
		return getService()
				   .addCustomCategoryProperty(groupId, companyId, userId,
			userName, createDate, modifiedDate, vocabularyId, categoryId, key,
			text);
	}

	/**
	* Creates a new custom category property with the primary key. Does not add the custom category property to the database.
	*
	* @param CustomCategoryPropertyId the primary key for the new custom category property
	* @return the new custom category property
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty createCustomCategoryProperty(
		long CustomCategoryPropertyId) {
		return getService()
				   .createCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* Deletes the custom category property from the database. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was removed
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty deleteCustomCategoryProperty(
		es.aragon.base.categories_custom_properties.model.CustomCategoryProperty customCategoryProperty) {
		return getService().deleteCustomCategoryProperty(customCategoryProperty);
	}

	/**
	* Deletes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property that was removed
	* @throws PortalException if a custom category property with the primary key could not be found
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty deleteCustomCategoryProperty(
		long CustomCategoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.categories_custom_properties.model.impl.CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.categories_custom_properties.model.impl.CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty fetchByCategoryIdAndKey(
		long categoryId, String key) {
		return getService().fetchByCategoryIdAndKey(categoryId, key);
	}

	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty fetchCustomCategoryProperty(
		long CustomCategoryPropertyId) {
		return getService().fetchCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* Returns the custom category property matching the UUID and group.
	*
	* @param uuid the custom category property's UUID
	* @param groupId the primary key of the group
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty fetchCustomCategoryPropertyByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchCustomCategoryPropertyByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> findByCategoryId(
		long categoryId) {
		return getService().findByCategoryId(categoryId);
	}

	public static java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return getService()
				   .findByCategoryId(categoryId, start, end, orderByComparator,
			retrieveFromCache);
	}

	public static java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text) {
		return getService()
				   .findByVocabularyIdAndKeyAndText(vocabularyId, key, text);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the custom category properties.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.categories_custom_properties.model.impl.CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @return the range of custom category properties
	*/
	public static java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> getCustomCategoryProperties(
		int start, int end) {
		return getService().getCustomCategoryProperties(start, end);
	}

	/**
	* Returns all the custom category properties matching the UUID and company.
	*
	* @param uuid the UUID of the custom category properties
	* @param companyId the primary key of the company
	* @return the matching custom category properties, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> getCustomCategoryPropertiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getCustomCategoryPropertiesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of custom category properties matching the UUID and company.
	*
	* @param uuid the UUID of the custom category properties
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of custom category properties
	* @param end the upper bound of the range of custom category properties (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching custom category properties, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> getCustomCategoryPropertiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> orderByComparator) {
		return getService()
				   .getCustomCategoryPropertiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of custom category properties.
	*
	* @return the number of custom category properties
	*/
	public static int getCustomCategoryPropertiesCount() {
		return getService().getCustomCategoryPropertiesCount();
	}

	/**
	* Returns the custom category property with the primary key.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property
	* @throws PortalException if a custom category property with the primary key could not be found
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty getCustomCategoryProperty(
		long CustomCategoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* Returns the custom category property matching the UUID and group.
	*
	* @param uuid the custom category property's UUID
	* @param groupId the primary key of the group
	* @return the matching custom category property
	* @throws PortalException if a matching custom category property could not be found
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty getCustomCategoryPropertyByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCustomCategoryPropertyByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the custom category property in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was updated
	*/
	public static es.aragon.base.categories_custom_properties.model.CustomCategoryProperty updateCustomCategoryProperty(
		es.aragon.base.categories_custom_properties.model.CustomCategoryProperty customCategoryProperty) {
		return getService().updateCustomCategoryProperty(customCategoryProperty);
	}

	public static CustomCategoryPropertyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomCategoryPropertyLocalService, CustomCategoryPropertyLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CustomCategoryPropertyLocalService.class);

		ServiceTracker<CustomCategoryPropertyLocalService, CustomCategoryPropertyLocalService> serviceTracker =
			new ServiceTracker<CustomCategoryPropertyLocalService, CustomCategoryPropertyLocalService>(bundle.getBundleContext(),
				CustomCategoryPropertyLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}