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
 * Provides the local service utility for ContentImage. This utility wraps
 * {@link es.aragon.base.migration.service.impl.ContentImageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContentImageLocalService
 * @see es.aragon.base.migration.service.base.ContentImageLocalServiceBaseImpl
 * @see es.aragon.base.migration.service.impl.ContentImageLocalServiceImpl
 * @generated
 */
@ProviderType
public class ContentImageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.migration.service.impl.ContentImageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the content image to the database. Also notifies the appropriate model listeners.
	*
	* @param contentImage the content image
	* @return the content image that was added
	*/
	public static es.aragon.base.migration.model.ContentImage addContentImage(
		es.aragon.base.migration.model.ContentImage contentImage) {
		return getService().addContentImage(contentImage);
	}

	/**
	* Creates a new content image with the primary key. Does not add the content image to the database.
	*
	* @param contentImageId the primary key for the new content image
	* @return the new content image
	*/
	public static es.aragon.base.migration.model.ContentImage createContentImage(
		long contentImageId) {
		return getService().createContentImage(contentImageId);
	}

	/**
	* Deletes the content image from the database. Also notifies the appropriate model listeners.
	*
	* @param contentImage the content image
	* @return the content image that was removed
	*/
	public static es.aragon.base.migration.model.ContentImage deleteContentImage(
		es.aragon.base.migration.model.ContentImage contentImage) {
		return getService().deleteContentImage(contentImage);
	}

	/**
	* Deletes the content image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image that was removed
	* @throws PortalException if a content image with the primary key could not be found
	*/
	public static es.aragon.base.migration.model.ContentImage deleteContentImage(
		long contentImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteContentImage(contentImageId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.ContentImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.migration.model.ContentImage fetchContentImage(
		long contentImageId) {
		return getService().fetchContentImage(contentImageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the content image with the primary key.
	*
	* @param contentImageId the primary key of the content image
	* @return the content image
	* @throws PortalException if a content image with the primary key could not be found
	*/
	public static es.aragon.base.migration.model.ContentImage getContentImage(
		long contentImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContentImage(contentImageId);
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
	public static java.util.List<es.aragon.base.migration.model.ContentImage> getContentImages(
		int start, int end) {
		return getService().getContentImages(start, end);
	}

	/**
	* Returns the number of content images.
	*
	* @return the number of content images
	*/
	public static int getContentImagesCount() {
		return getService().getContentImagesCount();
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
	* Updates the content image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contentImage the content image
	* @return the content image that was updated
	*/
	public static es.aragon.base.migration.model.ContentImage updateContentImage(
		es.aragon.base.migration.model.ContentImage contentImage) {
		return getService().updateContentImage(contentImage);
	}

	public static ContentImageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentImageLocalService, ContentImageLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentImageLocalService.class);

		ServiceTracker<ContentImageLocalService, ContentImageLocalService> serviceTracker =
			new ServiceTracker<ContentImageLocalService, ContentImageLocalService>(bundle.getBundleContext(),
				ContentImageLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}