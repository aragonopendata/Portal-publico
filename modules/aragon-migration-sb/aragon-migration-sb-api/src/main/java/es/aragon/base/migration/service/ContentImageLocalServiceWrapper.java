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
 * Provides a wrapper for {@link ContentImageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentImageLocalService
 * @generated
 */
@ProviderType
public class ContentImageLocalServiceWrapper implements ContentImageLocalService,
	ServiceWrapper<ContentImageLocalService> {
	public ContentImageLocalServiceWrapper(
		ContentImageLocalService contentImageLocalService) {
		_contentImageLocalService = contentImageLocalService;
	}

	/**
	* Adds the content image to the database. Also notifies the appropriate model listeners.
	*
	* @param contentImage the content image
	* @return the content image that was added
	*/
	@Override
	public es.aragon.base.migration.model.ContentImage addContentImage(
		es.aragon.base.migration.model.ContentImage contentImage) {
		return _contentImageLocalService.addContentImage(contentImage);
	}

	/**
	* Creates a new content image with the primary key. Does not add the content image to the database.
	*
	* @param contentImageId the primary key for the new content image
	* @return the new content image
	*/
	@Override
	public es.aragon.base.migration.model.ContentImage createContentImage(
		long contentImageId) {
		return _contentImageLocalService.createContentImage(contentImageId);
	}

	/**
	* Deletes the content image from the database. Also notifies the appropriate model listeners.
	*
	* @param contentImage the content image
	* @return the content image that was removed
	*/
	@Override
	public es.aragon.base.migration.model.ContentImage deleteContentImage(
		es.aragon.base.migration.model.ContentImage contentImage) {
		return _contentImageLocalService.deleteContentImage(contentImage);
	}

	/**
	* Deletes the content image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image that was removed
	* @throws PortalException if a content image with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentImage deleteContentImage(
		long contentImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentImageLocalService.deleteContentImage(contentImageId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentImageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contentImageLocalService.dynamicQuery();
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
		return _contentImageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentImageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentImageLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _contentImageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contentImageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.migration.model.ContentImage fetchContentImage(
		long contentImageId) {
		return _contentImageLocalService.fetchContentImage(contentImageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contentImageLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the content image with the primary key.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image
	* @throws PortalException if a content image with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentImage getContentImage(
		long contentImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentImageLocalService.getContentImage(contentImageId);
	}

	/**
	* Returns a range of all the content images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content images
	* @param end the upper bound of the range of content images (not inclusive)
	* @return the range of content images
	*/
	@Override
	public java.util.List<es.aragon.base.migration.model.ContentImage> getContentImages(
		int start, int end) {
		return _contentImageLocalService.getContentImages(start, end);
	}

	/**
	* Returns the number of content images.
	*
	* @return the number of content images
	*/
	@Override
	public int getContentImagesCount() {
		return _contentImageLocalService.getContentImagesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contentImageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentImageLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentImageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the content image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contentImage the content image
	* @return the content image that was updated
	*/
	@Override
	public es.aragon.base.migration.model.ContentImage updateContentImage(
		es.aragon.base.migration.model.ContentImage contentImage) {
		return _contentImageLocalService.updateContentImage(contentImage);
	}

	@Override
	public ContentImageLocalService getWrappedService() {
		return _contentImageLocalService;
	}

	@Override
	public void setWrappedService(
		ContentImageLocalService contentImageLocalService) {
		_contentImageLocalService = contentImageLocalService;
	}

	private ContentImageLocalService _contentImageLocalService;
}