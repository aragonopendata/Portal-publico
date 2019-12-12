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

package es.aragon.base.migration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContentUrlService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrlService
 * @generated
 */
@ProviderType
public class ContentUrlServiceWrapper implements ContentUrlService,
	ServiceWrapper<ContentUrlService> {
	public ContentUrlServiceWrapper(ContentUrlService contentUrlService) {
		_contentUrlService = contentUrlService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentUrlService.getOSGiServiceIdentifier();
	}

	@Override
	public ContentUrlService getWrappedService() {
		return _contentUrlService;
	}

	@Override
	public void setWrappedService(ContentUrlService contentUrlService) {
		_contentUrlService = contentUrlService;
	}

	private ContentUrlService _contentUrlService;
}