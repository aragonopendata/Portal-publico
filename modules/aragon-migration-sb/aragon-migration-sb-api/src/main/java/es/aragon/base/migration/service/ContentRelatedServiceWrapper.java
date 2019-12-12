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
 * Provides a wrapper for {@link ContentRelatedService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContentRelatedService
 * @generated
 */
@ProviderType
public class ContentRelatedServiceWrapper implements ContentRelatedService,
	ServiceWrapper<ContentRelatedService> {
	public ContentRelatedServiceWrapper(
		ContentRelatedService contentRelatedService) {
		_contentRelatedService = contentRelatedService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentRelatedService.getOSGiServiceIdentifier();
	}

	@Override
	public ContentRelatedService getWrappedService() {
		return _contentRelatedService;
	}

	@Override
	public void setWrappedService(ContentRelatedService contentRelatedService) {
		_contentRelatedService = contentRelatedService;
	}

	private ContentRelatedService _contentRelatedService;
}