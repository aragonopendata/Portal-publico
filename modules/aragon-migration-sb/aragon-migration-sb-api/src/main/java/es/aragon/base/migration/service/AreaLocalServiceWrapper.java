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

package es.aragon.base.migration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AreaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AreaLocalService
 * @generated
 */
@ProviderType
public class AreaLocalServiceWrapper implements AreaLocalService,
	ServiceWrapper<AreaLocalService> {
	public AreaLocalServiceWrapper(AreaLocalService areaLocalService) {
		_areaLocalService = areaLocalService;
	}

	/**
	* Adds the area to the database. Also notifies the appropriate model listeners.
	*
	* @param area the area
	* @return the area that was added
	*/
	@Override
	public es.aragon.base.migration.model.Area addArea(
		es.aragon.base.migration.model.Area area) {
		return _areaLocalService.addArea(area);
	}

	/**
	* Creates a new area with the primary key. Does not add the area to the database.
	*
	* @param areaId the primary key for the new area
	* @return the new area
	*/
	@Override
	public es.aragon.base.migration.model.Area createArea(long areaId) {
		return _areaLocalService.createArea(areaId);
	}

	/**
	* Deletes the area from the database. Also notifies the appropriate model listeners.
	*
	* @param area the area
	* @return the area that was removed
	*/
	@Override
	public es.aragon.base.migration.model.Area deleteArea(
		es.aragon.base.migration.model.Area area) {
		return _areaLocalService.deleteArea(area);
	}

	/**
	* Deletes the area with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param areaId the primary key of the area
	* @return the area that was removed
	* @throws PortalException if a area with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.Area deleteArea(long areaId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _areaLocalService.deleteArea(areaId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _areaLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _areaLocalService.dynamicQuery();
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
		return _areaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _areaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _areaLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _areaLocalService.dynamicQueryCount(dynamicQuery);
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
		return _areaLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public es.aragon.base.migration.model.Area fetchArea(long areaId) {
		return _areaLocalService.fetchArea(areaId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _areaLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the area with the primary key.
	*
	* @param areaId the primary key of the area
	* @return the area
	* @throws PortalException if a area with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.Area getArea(long areaId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _areaLocalService.getArea(areaId);
	}

	/**
	* Returns a range of all the areas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.AreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of areas
	* @param end the upper bound of the range of areas (not inclusive)
	* @return the range of areas
	*/
	@Override
	public java.util.List<es.aragon.base.migration.model.Area> getAreas(
		int start, int end) {
		return _areaLocalService.getAreas(start, end);
	}

	/**
	* Returns the number of areas.
	*
	* @return the number of areas
	*/
	@Override
	public int getAreasCount() {
		return _areaLocalService.getAreasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _areaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _areaLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _areaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param area the area
	* @return the area that was updated
	*/
	@Override
	public es.aragon.base.migration.model.Area updateArea(
		es.aragon.base.migration.model.Area area) {
		return _areaLocalService.updateArea(area);
	}

	@Override
	public AreaLocalService getWrappedService() {
		return _areaLocalService;
	}

	@Override
	public void setWrappedService(AreaLocalService areaLocalService) {
		_areaLocalService = areaLocalService;
	}

	private AreaLocalService _areaLocalService;
}