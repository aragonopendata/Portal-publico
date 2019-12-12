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

import java.util.Date;

import es.aragon.base.crawler.service.base.RootPageServiceBaseImpl;

/**
 * The implementation of the root page remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.crawler.service.RootPageService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RootPageServiceBaseImpl
 * @see es.aragon.base.crawler.service.RootPageServiceUtil
 */
public class RootPageServiceImpl extends RootPageServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link es.aragon.base.crawler.service.RootPageServiceUtil} to access the root page remote service.
	 */
	
	public String getAlias(long pageId) {
		return rootPageLocalService.getAlias(pageId);
	}
	
	public Date getCrawledDate(long pageId) {
		return rootPageLocalService.getCrawledDate(pageId);
	}
	
	public String getInclusionRules(long pageId) {
		return rootPageLocalService.getInclusionRules(pageId);
	}
	
	public long getRootPageId(long pageId) {
		return rootPageLocalService.getRootPageId(pageId);
	}
}