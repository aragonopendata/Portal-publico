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

package es.aragon.base.crawler.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.service.base.PageServiceBaseImpl;
import es.aragon.base.crawler.service.permission.PagePermissionChecker;

/**
 * The implementation of the page remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.crawler.service.PageService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PageServiceBaseImpl
 * @see es.aragon.base.crawler.service.PageServiceUtil
 */
public class PageServiceImpl extends PageServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link es.aragon.base.crawler.service.PageServiceUtil} to access the page remote service.
	 */

	private static final Log log = LogFactoryUtil.getLog(PageServiceImpl.class);
	
	@ServiceReference(type = es.aragon.base.crawler.service.permission.PagePermissionChecker.class)
	private PagePermissionChecker pagePermissionChecker;
	
	public List<Page> getPagesByKeywords(long companyId, String keywords, int start, int end, OrderByComparator<Page> orderByComparator) {
		return pageLocalService.getPagesByKeywords(companyId, keywords, start, end, orderByComparator);
	}
	
	public long getPagesCountByKeywords(long companyId, String keywords) {
		return pageLocalService.getPagesCountByKeywords(companyId, keywords);
	}
	
	public List<Page> getPagesByKeywordsByRootPage(long companyId, String keywords, int start, int end, OrderByComparator<Page> orderByComparator, long rootPageId) {
		return pageLocalService.getPagesByKeywordsByRootPage(companyId, keywords, start, end, orderByComparator, rootPageId);
	}
	
	public long getPagesCountByKeywordsByRootPage(long companyId, String keywords, long rootPageId) {
		return pageLocalService.getPagesCountByKeywordsByRootPage(companyId, keywords, rootPageId);
	}
	
	public long getParentPageStatus(Page page) {
		return pageLocalService.getParentPageStatus(page);
	}
	
	public Page deletePage(long pageId, long groupId) throws AuthException {
		
		Page page = null;
		try {
			page = pageLocalService.getPage(pageId);
			// Check permissions
			pagePermissionChecker.check(getPermissionChecker(), groupId, page.getPageId(), ActionKeys.DELETE);
			page = pageLocalService.deletePage(page);
		} catch (PrincipalException e) {
			log.error("Error checking permissions to remove the page:", e);
		} catch (PortalException e) {
			log.error("Error getting page to remove:", e);
		}
		return page;
	}
}