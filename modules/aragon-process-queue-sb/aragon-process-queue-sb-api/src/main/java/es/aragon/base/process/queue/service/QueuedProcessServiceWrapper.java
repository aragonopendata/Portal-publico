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

package es.aragon.base.process.queue.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link QueuedProcessService}.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessService
 * @generated
 */
@ProviderType
public class QueuedProcessServiceWrapper implements QueuedProcessService,
	ServiceWrapper<QueuedProcessService> {
	public QueuedProcessServiceWrapper(
		QueuedProcessService queuedProcessService) {
		_queuedProcessService = queuedProcessService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _queuedProcessService.getOSGiServiceIdentifier();
	}

	@Override
	public QueuedProcessService getWrappedService() {
		return _queuedProcessService;
	}

	@Override
	public void setWrappedService(QueuedProcessService queuedProcessService) {
		_queuedProcessService = queuedProcessService;
	}

	private QueuedProcessService _queuedProcessService;
}