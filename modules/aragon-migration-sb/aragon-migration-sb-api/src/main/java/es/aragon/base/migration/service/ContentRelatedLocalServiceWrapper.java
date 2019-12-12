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
 * Provides a wrapper for {@link ContentRelatedLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentRelatedLocalService
 * @generated
 */
@ProviderType
public class ContentRelatedLocalServiceWrapper
	implements ContentRelatedLocalService,
		ServiceWrapper<ContentRelatedLocalService> {
	public ContentRelatedLocalServiceWrapper(
		ContentRelatedLocalService contentRelatedLocalService) {
		_contentRelatedLocalService = contentRelatedLocalService;
	}

	/**
	* Adds the content related to the database. Also notifies the appropriate model listeners.
	*
	* @param contentRelated the content related
	* @return the content related that was added
	*/
	@Override
	public es.aragon.base.migration.model.ContentRelated addContentRelated(
		es.aragon.base.migration.model.ContentRelated contentRelated) {
		return _contentRelatedLocalService.addContentRelated(contentRelated);
	}

	/**
	* Creates a new content related with the primary key. Does not add the content related to the database.
	*
	* @param contentRelatedId the primary key for the new content related
	* @return the new content related
	*/
	@Override
	public es.aragon.base.migration.model.ContentRelated createContentRelated(
		long contentRelatedId) {
		return _contentRelatedLocalService.createContentRelated(contentRelatedId);
	}

	/**
	* Deletes the content related from the database. Also notifies the appropriate model listeners.
	*
	* @param contentRelated the content related
	* @return the content related that was removed
	*/
	@Override
	public es.aragon.base.migration.model.ContentRelated deleteContentRelated(
		es.aragon.base.migration.model.ContentRelated contentRelated) {
		return _contentRelatedLocalService.deleteContentRelated(contentRelated);
	}

	/**
	* Deletes the content related with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related that was removed
	* @throws PortalException if a content related with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentRelated deleteContentRelated(
		long contentRelatedId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentRelatedLocalService.deleteContentRelated(contentRelatedId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentRelatedLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contentRelatedLocalService.dynamicQuery();
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
		return _contentRelatedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentRelatedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentRelatedLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _contentRelatedLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contentRelatedLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.migration.model.ContentRelated fetchContentRelated(
		long contentRelatedId) {
		return _contentRelatedLocalService.fetchContentRelated(contentRelatedId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contentRelatedLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the content related with the primary key.
	*
	* @param contentRelatedId the primary key of the content related
	* @return the content related
	* @throws PortalException if a content related with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentRelated getContentRelated(
		long contentRelatedId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentRelatedLocalService.getContentRelated(contentRelatedId);
	}

	/**
	* Returns a range of all the content relateds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content relateds
	* @param end the upper bound of the range of content relateds (not inclusive)
	* @return the range of content relateds
	*/
	@Override
	public java.util.List<es.aragon.base.migration.model.ContentRelated> getContentRelateds(
		int start, int end) {
		return _contentRelatedLocalService.getContentRelateds(start, end);
	}

	/**
	* Returns the number of content relateds.
	*
	* @return the number of content relateds
	*/
	@Override
	public int getContentRelatedsCount() {
		return _contentRelatedLocalService.getContentRelatedsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contentRelatedLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentRelatedLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentRelatedLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the content related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contentRelated the content related
	* @return the content related that was updated
	*/
	@Override
	public es.aragon.base.migration.model.ContentRelated updateContentRelated(
		es.aragon.base.migration.model.ContentRelated contentRelated) {
		return _contentRelatedLocalService.updateContentRelated(contentRelated);
	}

	@Override
	public ContentRelatedLocalService getWrappedService() {
		return _contentRelatedLocalService;
	}

	@Override
	public void setWrappedService(
		ContentRelatedLocalService contentRelatedLocalService) {
		_contentRelatedLocalService = contentRelatedLocalService;
	}

	private ContentRelatedLocalService _contentRelatedLocalService;
}