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

package es.aragon.base.crawler.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for RootPage. This utility wraps
 * {@link es.aragon.base.crawler.service.impl.RootPageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RootPageLocalService
 * @see es.aragon.base.crawler.service.base.RootPageLocalServiceBaseImpl
 * @see es.aragon.base.crawler.service.impl.RootPageLocalServiceImpl
 * @generated
 */
@ProviderType
public class RootPageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.crawler.service.impl.RootPageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the root page to the database. Also notifies the appropriate model listeners.
	*
	* @param rootPage the root page
	* @return the root page that was added
	*/
	public static es.aragon.base.crawler.model.RootPage addRootPage(
		es.aragon.base.crawler.model.RootPage rootPage) {
		return getService().addRootPage(rootPage);
	}

	public static es.aragon.base.crawler.model.RootPage createRootPage() {
		return getService().createRootPage();
	}

	/**
	* Creates a new root page with the primary key. Does not add the root page to the database.
	*
	* @param rootPageId the primary key for the new root page
	* @return the new root page
	*/
	public static es.aragon.base.crawler.model.RootPage createRootPage(
		long rootPageId) {
		return getService().createRootPage(rootPageId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the root page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page that was removed
	* @throws PortalException if a root page with the primary key could not be found
	*/
	public static es.aragon.base.crawler.model.RootPage deleteRootPage(
		long rootPageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRootPage(rootPageId);
	}

	/**
	* Deletes the root page from the database. Also notifies the appropriate model listeners.
	*
	* @param rootPage the root page
	* @return the root page that was removed
	*/
	public static es.aragon.base.crawler.model.RootPage deleteRootPage(
		es.aragon.base.crawler.model.RootPage rootPage) {
		return getService().deleteRootPage(rootPage);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.crawler.model.RootPage fetchRootPage(
		long rootPageId) {
		return getService().fetchRootPage(rootPageId);
	}

	/**
	* Returns the root page matching the UUID and group.
	*
	* @param uuid the root page's UUID
	* @param groupId the primary key of the group
	* @return the matching root page, or <code>null</code> if a matching root page could not be found
	*/
	public static es.aragon.base.crawler.model.RootPage fetchRootPageByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchRootPageByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static String getAlias(long pageId) {
		return getService().getAlias(pageId);
	}

	public static java.util.Date getCrawledDate(long pageId) {
		return getService().getCrawledDate(pageId);
	}

	public static String getInclusionRules(long pageId) {
		return getService().getInclusionRules(pageId);
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
	* Returns the root page with the primary key.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page
	* @throws PortalException if a root page with the primary key could not be found
	*/
	public static es.aragon.base.crawler.model.RootPage getRootPage(
		long rootPageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRootPage(rootPageId);
	}

	public static es.aragon.base.crawler.model.RootPage getRootPageByPageId(
		long pageId) {
		return getService().getRootPageByPageId(pageId);
	}

	/**
	* Returns the root page matching the UUID and group.
	*
	* @param uuid the root page's UUID
	* @param groupId the primary key of the group
	* @return the matching root page
	* @throws PortalException if a matching root page could not be found
	*/
	public static es.aragon.base.crawler.model.RootPage getRootPageByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRootPageByUuidAndGroupId(uuid, groupId);
	}

	public static long getRootPageId(long pageId) {
		return getService().getRootPageId(pageId);
	}

	/**
	* Returns a range of all the root pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @return the range of root pages
	*/
	public static java.util.List<es.aragon.base.crawler.model.RootPage> getRootPages(
		int start, int end) {
		return getService().getRootPages(start, end);
	}

	public static java.util.List<es.aragon.base.crawler.model.RootPage> getRootPages(
		long companyId) {
		return getService().getRootPages(companyId);
	}

	public static java.util.List<es.aragon.base.crawler.model.RootPage> getRootPagesByScheduledCrawl(
		boolean scheduledCrawl) {
		return getService().getRootPagesByScheduledCrawl(scheduledCrawl);
	}

	/**
	* Returns all the root pages matching the UUID and company.
	*
	* @param uuid the UUID of the root pages
	* @param companyId the primary key of the company
	* @return the matching root pages, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.crawler.model.RootPage> getRootPagesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getRootPagesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of root pages matching the UUID and company.
	*
	* @param uuid the UUID of the root pages
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching root pages, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.crawler.model.RootPage> getRootPagesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.RootPage> orderByComparator) {
		return getService()
				   .getRootPagesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of root pages.
	*
	* @return the number of root pages
	*/
	public static int getRootPagesCount() {
		return getService().getRootPagesCount();
	}

	/**
	* Updates the root page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rootPage the root page
	* @return the root page that was updated
	*/
	public static es.aragon.base.crawler.model.RootPage updateRootPage(
		es.aragon.base.crawler.model.RootPage rootPage) {
		return getService().updateRootPage(rootPage);
	}

	public static RootPageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RootPageLocalService, RootPageLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RootPageLocalService.class);

		ServiceTracker<RootPageLocalService, RootPageLocalService> serviceTracker =
			new ServiceTracker<RootPageLocalService, RootPageLocalService>(bundle.getBundleContext(),
				RootPageLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}