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
 * Provides the remote service utility for Page. This utility wraps
 * {@link es.aragon.base.crawler.service.impl.PageServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PageService
 * @see es.aragon.base.crawler.service.base.PageServiceBaseImpl
 * @see es.aragon.base.crawler.service.impl.PageServiceImpl
 * @generated
 */
@ProviderType
public class PageServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.crawler.service.impl.PageServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static es.aragon.base.crawler.model.Page deletePage(long pageId,
		long groupId)
		throws com.liferay.portal.kernel.security.auth.AuthException {
		return getService().deletePage(pageId, groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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

	public static PageService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PageService, PageService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PageService.class);

		ServiceTracker<PageService, PageService> serviceTracker = new ServiceTracker<PageService, PageService>(bundle.getBundleContext(),
				PageService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}