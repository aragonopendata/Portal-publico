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

package es.aragon.base.aragon_most_visited_pages.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MostVisitedPage. This utility wraps
 * {@link es.aragon.base.aragon_most_visited_pages.service.impl.MostVisitedPageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPageLocalService
 * @see es.aragon.base.aragon_most_visited_pages.service.base.MostVisitedPageLocalServiceBaseImpl
 * @see es.aragon.base.aragon_most_visited_pages.service.impl.MostVisitedPageLocalServiceImpl
 * @generated
 */
@ProviderType
public class MostVisitedPageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.aragon_most_visited_pages.service.impl.MostVisitedPageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the most visited page to the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPage the most visited page
	* @return the most visited page that was added
	*/
	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage addMostVisitedPage(
		es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage mostVisitedPage) {
		return getService().addMostVisitedPage(mostVisitedPage);
	}

	public static void addMostVisitedPage(String path, int visits,
		String title, int position) {
		getService().addMostVisitedPage(path, visits, title, position);
	}

	/**
	* Creates a new most visited page with the primary key. Does not add the most visited page to the database.
	*
	* @param mostVisitedPageId the primary key for the new most visited page
	* @return the new most visited page
	*/
	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage createMostVisitedPage(
		long mostVisitedPageId) {
		return getService().createMostVisitedPage(mostVisitedPageId);
	}

	/**
	* Deletes the most visited page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page that was removed
	* @throws PortalException if a most visited page with the primary key could not be found
	*/
	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage deleteMostVisitedPage(
		long mostVisitedPageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMostVisitedPage(mostVisitedPageId);
	}

	/**
	* Deletes the most visited page from the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPage the most visited page
	* @return the most visited page that was removed
	*/
	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage deleteMostVisitedPage(
		es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage mostVisitedPage) {
		return getService().deleteMostVisitedPage(mostVisitedPage);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage fetchMostVisitedPage(
		long mostVisitedPageId) {
		return getService().fetchMostVisitedPage(mostVisitedPageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the most visited page with the primary key.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page
	* @throws PortalException if a most visited page with the primary key could not be found
	*/
	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage getMostVisitedPage(
		long mostVisitedPageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMostVisitedPage(mostVisitedPageId);
	}

	/**
	* Returns a range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @return the range of most visited pages
	*/
	public static java.util.List<es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage> getMostVisitedPages(
		int start, int end) {
		return getService().getMostVisitedPages(start, end);
	}

	/**
	* Returns the number of most visited pages.
	*
	* @return the number of most visited pages
	*/
	public static int getMostVisitedPagesCount() {
		return getService().getMostVisitedPagesCount();
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
	* Updates the most visited page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPage the most visited page
	* @return the most visited page that was updated
	*/
	public static es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage updateMostVisitedPage(
		es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage mostVisitedPage) {
		return getService().updateMostVisitedPage(mostVisitedPage);
	}

	public static MostVisitedPageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MostVisitedPageLocalService, MostVisitedPageLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MostVisitedPageLocalService.class);

		ServiceTracker<MostVisitedPageLocalService, MostVisitedPageLocalService> serviceTracker =
			new ServiceTracker<MostVisitedPageLocalService, MostVisitedPageLocalService>(bundle.getBundleContext(),
				MostVisitedPageLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}