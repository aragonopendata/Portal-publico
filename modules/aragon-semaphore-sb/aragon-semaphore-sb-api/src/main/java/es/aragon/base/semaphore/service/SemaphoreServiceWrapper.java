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

package es.aragon.base.semaphore.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SemaphoreService}.
 *
 * @author Brian Wing Shun Chan
 * @see SemaphoreService
 * @generated
 */
@ProviderType
public class SemaphoreServiceWrapper implements SemaphoreService,
	ServiceWrapper<SemaphoreService> {
	public SemaphoreServiceWrapper(SemaphoreService semaphoreService) {
		_semaphoreService = semaphoreService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _semaphoreService.getOSGiServiceIdentifier();
	}

	@Override
	public SemaphoreService getWrappedService() {
		return _semaphoreService;
	}

	@Override
	public void setWrappedService(SemaphoreService semaphoreService) {
		_semaphoreService = semaphoreService;
	}

	private SemaphoreService _semaphoreService;
}