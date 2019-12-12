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
 * Provides a wrapper for {@link ContentMetadataLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadataLocalService
 * @generated
 */
@ProviderType
public class ContentMetadataLocalServiceWrapper
	implements ContentMetadataLocalService,
		ServiceWrapper<ContentMetadataLocalService> {
	public ContentMetadataLocalServiceWrapper(
		ContentMetadataLocalService contentMetadataLocalService) {
		_contentMetadataLocalService = contentMetadataLocalService;
	}

	/**
	* Adds the content metadata to the database. Also notifies the appropriate model listeners.
	*
	* @param contentMetadata the content metadata
	* @return the content metadata that was added
	*/
	@Override
	public es.aragon.base.migration.model.ContentMetadata addContentMetadata(
		es.aragon.base.migration.model.ContentMetadata contentMetadata) {
		return _contentMetadataLocalService.addContentMetadata(contentMetadata);
	}

	@Override
	public es.aragon.base.migration.model.ContentMetadata addContentMetadata(
		long contentId, String primaryBody, String primaryImage,
		String primaryVideo, String secondaryBody, String imagesGalleryTitle,
		String imagesGalleryImages, String linksListTitle, String linksListLinks) {
		return _contentMetadataLocalService.addContentMetadata(contentId,
			primaryBody, primaryImage, primaryVideo, secondaryBody,
			imagesGalleryTitle, imagesGalleryImages, linksListTitle,
			linksListLinks);
	}

	/**
	* Creates a new content metadata with the primary key. Does not add the content metadata to the database.
	*
	* @param contentMetadataId the primary key for the new content metadata
	* @return the new content metadata
	*/
	@Override
	public es.aragon.base.migration.model.ContentMetadata createContentMetadata(
		long contentMetadataId) {
		return _contentMetadataLocalService.createContentMetadata(contentMetadataId);
	}

	/**
	* Deletes the content metadata from the database. Also notifies the appropriate model listeners.
	*
	* @param contentMetadata the content metadata
	* @return the content metadata that was removed
	*/
	@Override
	public es.aragon.base.migration.model.ContentMetadata deleteContentMetadata(
		es.aragon.base.migration.model.ContentMetadata contentMetadata) {
		return _contentMetadataLocalService.deleteContentMetadata(contentMetadata);
	}

	/**
	* Deletes the content metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata that was removed
	* @throws PortalException if a content metadata with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentMetadata deleteContentMetadata(
		long contentMetadataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentMetadataLocalService.deleteContentMetadata(contentMetadataId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentMetadataLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contentMetadataLocalService.dynamicQuery();
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
		return _contentMetadataLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentMetadataLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentMetadataLocalService.dynamicQuery(dynamicQuery, start,
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
		return _contentMetadataLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contentMetadataLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.migration.model.ContentMetadata fetchByContentId(
		long contentId) {
		return _contentMetadataLocalService.fetchByContentId(contentId);
	}

	@Override
	public es.aragon.base.migration.model.ContentMetadata fetchContentMetadata(
		long contentMetadataId) {
		return _contentMetadataLocalService.fetchContentMetadata(contentMetadataId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contentMetadataLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the content metadata with the primary key.
	*
	* @param contentMetadataId the primary key of the content metadata
	* @return the content metadata
	* @throws PortalException if a content metadata with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentMetadata getContentMetadata(
		long contentMetadataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentMetadataLocalService.getContentMetadata(contentMetadataId);
	}

	/**
	* Returns a range of all the content metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content metadatas
	* @param end the upper bound of the range of content metadatas (not inclusive)
	* @return the range of content metadatas
	*/
	@Override
	public java.util.List<es.aragon.base.migration.model.ContentMetadata> getContentMetadatas(
		int start, int end) {
		return _contentMetadataLocalService.getContentMetadatas(start, end);
	}

	/**
	* Returns the number of content metadatas.
	*
	* @return the number of content metadatas
	*/
	@Override
	public int getContentMetadatasCount() {
		return _contentMetadataLocalService.getContentMetadatasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contentMetadataLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentMetadataLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentMetadataLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the content metadata in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contentMetadata the content metadata
	* @return the content metadata that was updated
	*/
	@Override
	public es.aragon.base.migration.model.ContentMetadata updateContentMetadata(
		es.aragon.base.migration.model.ContentMetadata contentMetadata) {
		return _contentMetadataLocalService.updateContentMetadata(contentMetadata);
	}

	@Override
	public ContentMetadataLocalService getWrappedService() {
		return _contentMetadataLocalService;
	}

	@Override
	public void setWrappedService(
		ContentMetadataLocalService contentMetadataLocalService) {
		_contentMetadataLocalService = contentMetadataLocalService;
	}

	private ContentMetadataLocalService _contentMetadataLocalService;
}