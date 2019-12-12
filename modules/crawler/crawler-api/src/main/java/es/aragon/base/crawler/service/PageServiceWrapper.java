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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PageService}.
 *
 * @author Brian Wing Shun Chan
 * @see PageService
 * @generated
 */
@ProviderType
public class PageServiceWrapper implements PageService,
	ServiceWrapper<PageService> {
	public PageServiceWrapper(PageService pageService) {
		_pageService = pageService;
	}

	@Override
	public es.aragon.base.crawler.model.Page deletePage(long pageId,
		long groupId)
		throws com.liferay.portal.kernel.security.auth.AuthException {
		return _pageService.deletePage(pageId, groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _pageService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<es.aragon.base.crawler.model.Page> getPagesByKeywords(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.Page> orderByComparator) {
		return _pageService.getPagesByKeywords(companyId, keywords, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<es.aragon.base.crawler.model.Page> getPagesByKeywordsByRootPage(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.Page> orderByComparator,
		long rootPageId) {
		return _pageService.getPagesByKeywordsByRootPage(companyId, keywords,
			start, end, orderByComparator, rootPageId);
	}

	@Override
	public long getPagesCountByKeywords(long companyId, String keywords) {
		return _pageService.getPagesCountByKeywords(companyId, keywords);
	}

	@Override
	public long getPagesCountByKeywordsByRootPage(long companyId,
		String keywords, long rootPageId) {
		return _pageService.getPagesCountByKeywordsByRootPage(companyId,
			keywords, rootPageId);
	}

	@Override
	public long getParentPageStatus(es.aragon.base.crawler.model.Page page) {
		return _pageService.getParentPageStatus(page);
	}

	@Override
	public PageService getWrappedService() {
		return _pageService;
	}

	@Override
	public void setWrappedService(PageService pageService) {
		_pageService = pageService;
	}

	private PageService _pageService;
}