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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MostVisitedPageService}.
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPageService
 * @generated
 */
@ProviderType
public class MostVisitedPageServiceWrapper implements MostVisitedPageService,
	ServiceWrapper<MostVisitedPageService> {
	public MostVisitedPageServiceWrapper(
		MostVisitedPageService mostVisitedPageService) {
		_mostVisitedPageService = mostVisitedPageService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _mostVisitedPageService.getOSGiServiceIdentifier();
	}

	@Override
	public MostVisitedPageService getWrappedService() {
		return _mostVisitedPageService;
	}

	@Override
	public void setWrappedService(MostVisitedPageService mostVisitedPageService) {
		_mostVisitedPageService = mostVisitedPageService;
	}

	private MostVisitedPageService _mostVisitedPageService;
}