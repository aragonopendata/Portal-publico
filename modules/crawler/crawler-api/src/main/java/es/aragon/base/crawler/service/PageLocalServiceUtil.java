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
 * Provides the local service utility for Page. This utility wraps
 * {@link es.aragon.base.crawler.service.impl.PageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PageLocalService
 * @see es.aragon.base.crawler.service.base.PageLocalServiceBaseImpl
 * @see es.aragon.base.crawler.service.impl.PageLocalServiceImpl
 * @generated
 */
@ProviderType
public class PageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.crawler.service.impl.PageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the page to the database. Also notifies the appropriate model listeners.
	*
	* @param page the page
	* @return the page that was added
	*/
	public static es.aragon.base.crawler.model.Page addPage(
		es.aragon.base.crawler.model.Page page) {
		return getService().addPage(page);
	}

	public static es.aragon.base.crawler.model.Page addPage(
		es.aragon.base.crawler.model.Page page, long userId, long companyId,
		long groupId) {
		return getService().addPage(page, userId, companyId, groupId);
	}

	public static es.aragon.base.crawler.model.Page createPage() {
		return getService().createPage();
	}

	/**
	* Creates a new page with the primary key. Does not add the page to the database.
	*
	* @param pageId the primary key for the new page
	* @return the new page
	*/
	public static es.aragon.base.crawler.model.Page createPage(long pageId) {
		return getService().createPage(pageId);
	}

	public static void deleteChildPages(
		es.aragon.base.crawler.model.Page page, boolean deletePage) {
		getService().deleteChildPages(page, deletePage);
	}

	/**
	* Deletes the page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pageId the primary key of the page
	* @return the page that was removed
	* @throws PortalException if a page with the primary key could not be found
	*/
	public static es.aragon.base.crawler.model.Page deletePage(long pageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePage(pageId);
	}

	/**
	* Deletes the page from the database. Also notifies the appropriate model listeners.
	*
	* @param page the page
	* @return the page that was removed
	*/
	public static es.aragon.base.crawler.model.Page deletePage(
		es.aragon.base.crawler.model.Page page) {
		return getService().deletePage(page);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.crawler.model.Page fetchPage(long pageId) {
		return getService().fetchPage(pageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<es.aragon.base.crawler.model.Page> getChildPages(
		long parentPageId) {
		return getService().getChildPages(parentPageId);
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

	/**
	* Returns the page with the primary key.
	*
	* @param pageId the primary key of the page
	* @return the page
	* @throws PortalException if a page with the primary key could not be found
	*/
	public static es.aragon.base.crawler.model.Page getPage(long pageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPage(pageId);
	}

	/**
	* Returns a range of all the pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.PageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of pages
	* @param end the upper bound of the range of pages (not inclusive)
	* @return the range of pages
	*/
	public static java.util.List<es.aragon.base.crawler.model.Page> getPages(
		int start, int end) {
		return getService().getPages(start, end);
	}

	public static java.util.List<es.aragon.base.crawler.model.Page> getPagesByKeywords(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.Page> orderByComparator) {
		return getService()
				   .getPagesByKeywords(companyId, keywords, start, end,
			orderByComparator);
	}

	public static java.util.List<es.aragon.base.crawler.model.Page> getPagesByKeywordsByRootPage(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.Page> orderByComparator,
		long rootPageId) {
		return getService()
				   .getPagesByKeywordsByRootPage(companyId, keywords, start,
			end, orderByComparator, rootPageId);
	}

	public static java.util.List<es.aragon.base.crawler.model.Page> getPagesByRootPageId(
		long rootPageId) {
		return getService().getPagesByRootPageId(rootPageId);
	}

	/**
	* Returns the number of pages.
	*
	* @return the number of pages
	*/
	public static int getPagesCount() {
		return getService().getPagesCount();
	}

	public static long getPagesCountByKeywords(long companyId, String keywords) {
		return getService().getPagesCountByKeywords(companyId, keywords);
	}

	public static long getPagesCountByKeywordsByRootPage(long companyId,
		String keywords, long rootPageId) {
		return getService()
				   .getPagesCountByKeywordsByRootPage(companyId, keywords,
			rootPageId);
	}

	public static long getParentPageStatus(
		es.aragon.base.crawler.model.Page page) {
		return getService().getParentPageStatus(page);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param page the page
	* @return the page that was updated
	*/
	public static es.aragon.base.crawler.model.Page updatePage(
		es.aragon.base.crawler.model.Page page) {
		return getService().updatePage(page);
	}

	public static PageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PageLocalService, PageLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PageLocalService.class);

		ServiceTracker<PageLocalService, PageLocalService> serviceTracker = new ServiceTracker<PageLocalService, PageLocalService>(bundle.getBundleContext(),
				PageLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}