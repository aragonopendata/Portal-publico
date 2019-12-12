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
 * Provides a wrapper for {@link RootPageService}.
 *
 * @author Brian Wing Shun Chan
 * @see RootPageService
 * @generated
 */
@ProviderType
public class RootPageServiceWrapper implements RootPageService,
	ServiceWrapper<RootPageService> {
	public RootPageServiceWrapper(RootPageService rootPageService) {
		_rootPageService = rootPageService;
	}

	@Override
	public String getAlias(long pageId) {
		return _rootPageService.getAlias(pageId);
	}

	@Override
	public java.util.Date getCrawledDate(long pageId) {
		return _rootPageService.getCrawledDate(pageId);
	}

	@Override
	public String getInclusionRules(long pageId) {
		return _rootPageService.getInclusionRules(pageId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _rootPageService.getOSGiServiceIdentifier();
	}

	@Override
	public long getRootPageId(long pageId) {
		return _rootPageService.getRootPageId(pageId);
	}

	@Override
	public RootPageService getWrappedService() {
		return _rootPageService;
	}

	@Override
	public void setWrappedService(RootPageService rootPageService) {
		_rootPageService = rootPageService;
	}

	private RootPageService _rootPageService;
}