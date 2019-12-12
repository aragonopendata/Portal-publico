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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for CustomCategoryProperty. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyLocalServiceUtil
 * @see es.aragon.base.categories_custom_properties.service.base.CustomCategoryPropertyLocalServiceBaseImpl
 * @see es.aragon.base.categories_custom_properties.service.impl.CustomCategoryPropertyLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CustomCategoryPropertyLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomCategoryPropertyLocalServiceUtil} to access the custom category property local service. Add custom service methods to {@link es.aragon.base.categories_custom_properties.service.impl.CustomCategoryPropertyLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the custom category property to the database. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CustomCategoryProperty addCustomCategoryProperty(
		CustomCategoryProperty customCategoryProperty);

	public CustomCategoryProperty addCustomCategoryProperty(long groupId,
		long companyId, long userId, String userName, Date createDate,
		Date modifiedDate, long vocabularyId, long categoryId, String key,
		String text);

	/**
	* Creates a new custom category property with the primary key. Does not add the custom category property to the database.
	*
	* @param CustomCategoryPropertyId the primary key for the new custom category property
	* @return the new custom category property
	*/
	@Transactional(enabled = false)
	public CustomCategoryProperty createCustomCategoryProperty(
		long CustomCategoryPropertyId);

	/**
	* Deletes the custom category property from the database. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CustomCategoryProperty deleteCustomCategoryProperty(
		CustomCategoryProperty customCategoryProperty);

	/**
	* Deletes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property that was removed
	* @throws PortalException if a custom category property with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CustomCategoryProperty deleteCustomCategoryProperty(
		long CustomCategoryPropertyId) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomCategoryProperty fetchByCategoryIdAndKey(long categoryId,
		String key);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomCategoryProperty fetchCustomCategoryProperty(
		long CustomCategoryPropertyId);

	/**
	* Returns the custom category property matching the UUID and group.
	*
	* @param uuid the custom category property's UUID
	* @param groupId the primary key of the group
	* @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomCategoryProperty fetchCustomCategoryPropertyByUuidAndGroupId(
		String uuid, long groupId);

	public List<CustomCategoryProperty> findByCategoryId(long categoryId);

	public List<CustomCategoryProperty> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache);

	public List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CustomCategoryProperty> getCustomCategoryProperties(int start,
		int end);

	/**
	* Returns all the custom category properties matching the UUID and company.
	*
	* @param uuid the UUID of the custom category properties
	* @param companyId the primary key of the company
	* @return the matching custom category properties, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CustomCategoryProperty> getCustomCategoryPropertiesByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CustomCategoryProperty> getCustomCategoryPropertiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator);

	/**
	* Returns the number of custom category properties.
	*
	* @return the number of custom category properties
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomCategoryPropertiesCount();

	/**
	* Returns the custom category property with the primary key.
	*
	* @param CustomCategoryPropertyId the primary key of the custom category property
	* @return the custom category property
	* @throws PortalException if a custom category property with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomCategoryProperty getCustomCategoryProperty(
		long CustomCategoryPropertyId) throws PortalException;

	/**
	* Returns the custom category property matching the UUID and group.
	*
	* @param uuid the custom category property's UUID
	* @param groupId the primary key of the group
	* @return the matching custom category property
	* @throws PortalException if a matching custom category property could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomCategoryProperty getCustomCategoryPropertyByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the custom category property in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customCategoryProperty the custom category property
	* @return the custom category property that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CustomCategoryProperty updateCustomCategoryProperty(
		CustomCategoryProperty customCategoryProperty);
}