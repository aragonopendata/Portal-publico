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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CustomCategoryPropertyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyLocalService
 * @generated
 */
@ProviderType
public class CustomCategoryPropertyLocalServiceWrapper
	implements CustomCategoryPropertyLocalService,
		ServiceWrapper<CustomCategoryPropertyLocalService> {
	public CustomCategoryPropertyLocalServiceWrapper(
		CustomCategoryPropertyLocalService customCategoryPropertyLocalService) {
		_customCategoryPropertyLocalService = customCategoryPropertyLocalService;
	}

	/**
	* Adds the custom category property to the database. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was added
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty addCustomCategoryProperty(
		es.aragon.base.categories_custom_properties.model.CustomCategoryProperty customCategoryProperty) {
		return _customCategoryPropertyLocalService.addCustomCategoryProperty(customCategoryProperty);
	}

	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty addCustomCategoryProperty(
		long groupId, long companyId, long userId, String userName,
		java.util.Date createDate, java.util.Date modifiedDate,
		long vocabularyId, long categoryId, String key, String text) {
		return _customCategoryPropertyLocalService.addCustomCategoryProperty(groupId,
			companyId, userId, userName, createDate, modifiedDate,
			vocabularyId, categoryId, key, text);
	}

	/**
	* Creates a new custom category property with the primary key. Does not add the custom category property to the database.
	*
	* @param CustomCategoryPropertyId the primary key for the new custom category property
	* @return the new custom category property
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty createCustomCategoryProperty(
		long CustomCategoryPropertyId) {
		return _customCategoryPropertyLocalService.createCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* Deletes the custom category property from the database. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was removed
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty deleteCustomCategoryProperty(
		es.aragon.base.categories_custom_properties.model.CustomCategoryProperty customCategoryProperty) {
		return _customCategoryPropertyLocalService.deleteCustomCategoryProperty(customCategoryProperty);
	}

	/**
	* Deletes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property that was removed
	* @throws PortalException if a custom category property with the primary key could not be found
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty deleteCustomCategoryProperty(
		long CustomCategoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customCategoryPropertyLocalService.deleteCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customCategoryPropertyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _customCategoryPropertyLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _customCategoryPropertyLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _customCategoryPropertyLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _customCategoryPropertyLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _customCategoryPropertyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _customCategoryPropertyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty fetchByCategoryIdAndKey(
		long categoryId, String key) {
		return _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(categoryId,
			key);
	}

	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty fetchCustomCategoryProperty(
		long CustomCategoryPropertyId) {
		return _customCategoryPropertyLocalService.fetchCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* Returns the custom category property matching the UUID and group.
	*
	* @param uuid the custom category property's UUID
	* @param groupId the primary key of the group
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty fetchCustomCategoryPropertyByUuidAndGroupId(
		String uuid, long groupId) {
		return _customCategoryPropertyLocalService.fetchCustomCategoryPropertyByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> findByCategoryId(
		long categoryId) {
		return _customCategoryPropertyLocalService.findByCategoryId(categoryId);
	}

	@Override
	public java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> findByCategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		return _customCategoryPropertyLocalService.findByCategoryId(categoryId,
			start, end, orderByComparator, retrieveFromCache);
	}

	@Override
	public java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text) {
		return _customCategoryPropertyLocalService.findByVocabularyIdAndKeyAndText(vocabularyId,
			key, text);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _customCategoryPropertyLocalService.getActionableDynamicQuery();
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
	@Override
	public java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> getCustomCategoryProperties(
		int start, int end) {
		return _customCategoryPropertyLocalService.getCustomCategoryProperties(start,
			end);
	}

	/**
	* Returns all the custom category properties matching the UUID and company.
	*
	* @param uuid the UUID of the custom category properties
	* @param companyId the primary key of the company
	* @return the matching custom category properties, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> getCustomCategoryPropertiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _customCategoryPropertyLocalService.getCustomCategoryPropertiesByUuidAndCompanyId(uuid,
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
	@Override
	public java.util.List<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> getCustomCategoryPropertiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.categories_custom_properties.model.CustomCategoryProperty> orderByComparator) {
		return _customCategoryPropertyLocalService.getCustomCategoryPropertiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of custom category properties.
	*
	* @return the number of custom category properties
	*/
	@Override
	public int getCustomCategoryPropertiesCount() {
		return _customCategoryPropertyLocalService.getCustomCategoryPropertiesCount();
	}

	/**
	* Returns the custom category property with the primary key.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property
	* @throws PortalException if a custom category property with the primary key could not be found
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty getCustomCategoryProperty(
		long CustomCategoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customCategoryPropertyLocalService.getCustomCategoryProperty(CustomCategoryPropertyId);
	}

	/**
	* Returns the custom category property matching the UUID and group.
	*
	* @param uuid the custom category property's UUID
	* @param groupId the primary key of the group
	* @return the matching custom category property
	* @throws PortalException if a matching custom category property could not be found
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty getCustomCategoryPropertyByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customCategoryPropertyLocalService.getCustomCategoryPropertyByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _customCategoryPropertyLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _customCategoryPropertyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _customCategoryPropertyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customCategoryPropertyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the custom category property in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was updated
	*/
	@Override
	public es.aragon.base.categories_custom_properties.model.CustomCategoryProperty updateCustomCategoryProperty(
		es.aragon.base.categories_custom_properties.model.CustomCategoryProperty customCategoryProperty) {
		return _customCategoryPropertyLocalService.updateCustomCategoryProperty(customCategoryProperty);
	}

	@Override
	public CustomCategoryPropertyLocalService getWrappedService() {
		return _customCategoryPropertyLocalService;
	}

	@Override
	public void setWrappedService(
		CustomCategoryPropertyLocalService customCategoryPropertyLocalService) {
		_customCategoryPropertyLocalService = customCategoryPropertyLocalService;
	}

	private CustomCategoryPropertyLocalService _customCategoryPropertyLocalService;
}