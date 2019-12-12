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
 * Provides a wrapper for {@link ContentUrlLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrlLocalService
 * @generated
 */
@ProviderType
public class ContentUrlLocalServiceWrapper implements ContentUrlLocalService,
	ServiceWrapper<ContentUrlLocalService> {
	public ContentUrlLocalServiceWrapper(
		ContentUrlLocalService contentUrlLocalService) {
		_contentUrlLocalService = contentUrlLocalService;
	}

	/**
	* Adds the content url to the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrl the content url
	* @return the content url that was added
	*/
	@Override
	public es.aragon.base.migration.model.ContentUrl addContentUrl(
		es.aragon.base.migration.model.ContentUrl contentUrl) {
		return _contentUrlLocalService.addContentUrl(contentUrl);
	}

	/**
	* Creates a new content url with the primary key. Does not add the content url to the database.
	*
	* @param contentUrlId the primary key for the new content url
	* @return the new content url
	*/
	@Override
	public es.aragon.base.migration.model.ContentUrl createContentUrl(
		long contentUrlId) {
		return _contentUrlLocalService.createContentUrl(contentUrlId);
	}

	/**
	* Deletes the content url from the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrl the content url
	* @return the content url that was removed
	*/
	@Override
	public es.aragon.base.migration.model.ContentUrl deleteContentUrl(
		es.aragon.base.migration.model.ContentUrl contentUrl) {
		return _contentUrlLocalService.deleteContentUrl(contentUrl);
	}

	/**
	* Deletes the content url with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url that was removed
	* @throws PortalException if a content url with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentUrl deleteContentUrl(
		long contentUrlId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentUrlLocalService.deleteContentUrl(contentUrlId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentUrlLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contentUrlLocalService.dynamicQuery();
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
		return _contentUrlLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentUrlLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contentUrlLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _contentUrlLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contentUrlLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.migration.model.ContentUrl fetchByContentOriginId(
		long contentOriginId) {
		return _contentUrlLocalService.fetchByContentOriginId(contentOriginId);
	}

	@Override
	public es.aragon.base.migration.model.ContentUrl fetchContentUrl(
		long contentUrlId) {
		return _contentUrlLocalService.fetchContentUrl(contentUrlId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contentUrlLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the content url with the primary key.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url
	* @throws PortalException if a content url with the primary key could not be found
	*/
	@Override
	public es.aragon.base.migration.model.ContentUrl getContentUrl(
		long contentUrlId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentUrlLocalService.getContentUrl(contentUrlId);
	}

	/**
	* Returns a range of all the content urls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of content urls
	* @param end the upper bound of the range of content urls (not inclusive)
	* @return the range of content urls
	*/
	@Override
	public java.util.List<es.aragon.base.migration.model.ContentUrl> getContentUrls(
		int start, int end) {
		return _contentUrlLocalService.getContentUrls(start, end);
	}

	/**
	* Returns the number of content urls.
	*
	* @return the number of content urls
	*/
	@Override
	public int getContentUrlsCount() {
		return _contentUrlLocalService.getContentUrlsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contentUrlLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentUrlLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contentUrlLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the content url in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contentUrl the content url
	* @return the content url that was updated
	*/
	@Override
	public es.aragon.base.migration.model.ContentUrl updateContentUrl(
		es.aragon.base.migration.model.ContentUrl contentUrl) {
		return _contentUrlLocalService.updateContentUrl(contentUrl);
	}

	@Override
	public ContentUrlLocalService getWrappedService() {
		return _contentUrlLocalService;
	}

	@Override
	public void setWrappedService(ContentUrlLocalService contentUrlLocalService) {
		_contentUrlLocalService = contentUrlLocalService;
	}

	private ContentUrlLocalService _contentUrlLocalService;
}