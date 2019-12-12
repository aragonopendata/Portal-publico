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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.crawler.model.Page;

import java.util.List;

/**
 * Provides the remote service interface for Page. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PageServiceUtil
 * @see es.aragon.base.crawler.service.base.PageServiceBaseImpl
 * @see es.aragon.base.crawler.service.impl.PageServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=eab_crawler", "json.web.service.context.path=Page"}, service = PageService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PageService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PageServiceUtil} to access the page remote service. Add custom service methods to {@link es.aragon.base.crawler.service.impl.PageServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Page deletePage(long pageId, long groupId) throws AuthException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Page> getPagesByKeywords(long companyId, String keywords,
		int start, int end, OrderByComparator<Page> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Page> getPagesByKeywordsByRootPage(long companyId,
		String keywords, int start, int end,
		OrderByComparator<Page> orderByComparator, long rootPageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getPagesCountByKeywords(long companyId, String keywords);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getPagesCountByKeywordsByRootPage(long companyId,
		String keywords, long rootPageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getParentPageStatus(Page page);
}