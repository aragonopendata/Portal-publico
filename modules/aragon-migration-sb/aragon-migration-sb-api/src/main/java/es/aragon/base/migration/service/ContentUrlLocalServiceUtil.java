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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ContentUrl. This utility wraps
 * {@link es.aragon.base.migration.service.impl.ContentUrlLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrlLocalService
 * @see es.aragon.base.migration.service.base.ContentUrlLocalServiceBaseImpl
 * @see es.aragon.base.migration.service.impl.ContentUrlLocalServiceImpl
 * @generated
 */
@ProviderType
public class ContentUrlLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.migration.service.impl.ContentUrlLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the content url to the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrl the content url
	* @return the content url that was added
	*/
	public static es.aragon.base.migration.model.ContentUrl addContentUrl(
		es.aragon.base.migration.model.ContentUrl contentUrl) {
		return getService().addContentUrl(contentUrl);
	}

	/**
	* Creates a new content url with the primary key. Does not add the content url to the database.
	*
	* @param contentUrlId the primary key for the new content url
	* @return the new content url
	*/
	public static es.aragon.base.migration.model.ContentUrl createContentUrl(
		long contentUrlId) {
		return getService().createContentUrl(contentUrlId);
	}

	/**
	* Deletes the content url from the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrl the content url
	* @return the content url that was removed
	*/
	public static es.aragon.base.migration.model.ContentUrl deleteContentUrl(
		es.aragon.base.migration.model.ContentUrl contentUrl) {
		return getService().deleteContentUrl(contentUrl);
	}

	/**
	* Deletes the content url with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url that was removed
	* @throws PortalException if a content url with the primary key could not be found
	*/
	public static es.aragon.base.migration.model.ContentUrl deleteContentUrl(
		long contentUrlId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteContentUrl(contentUrlId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.migration.model.ContentUrl fetchByContentOriginId(
		long contentOriginId) {
		return getService().fetchByContentOriginId(contentOriginId);
	}

	public static es.aragon.base.migration.model.ContentUrl fetchContentUrl(
		long contentUrlId) {
		return getService().fetchContentUrl(contentUrlId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the content url with the primary key.
	*
	* @param contentUrlId the primary key of the content url
	* @return the content url
	* @throws PortalException if a content url with the primary key could not be found
	*/
	public static es.aragon.base.migration.model.ContentUrl getContentUrl(
		long contentUrlId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContentUrl(contentUrlId);
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
	public static java.util.List<es.aragon.base.migration.model.ContentUrl> getContentUrls(
		int start, int end) {
		return getService().getContentUrls(start, end);
	}

	/**
	* Returns the number of content urls.
	*
	* @return the number of content urls
	*/
	public static int getContentUrlsCount() {
		return getService().getContentUrlsCount();
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
	* Updates the content url in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contentUrl the content url
	* @return the content url that was updated
	*/
	public static es.aragon.base.migration.model.ContentUrl updateContentUrl(
		es.aragon.base.migration.model.ContentUrl contentUrl) {
		return getService().updateContentUrl(contentUrl);
	}

	public static ContentUrlLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentUrlLocalService, ContentUrlLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentUrlLocalService.class);

		ServiceTracker<ContentUrlLocalService, ContentUrlLocalService> serviceTracker =
			new ServiceTracker<ContentUrlLocalService, ContentUrlLocalService>(bundle.getBundleContext(),
				ContentUrlLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}