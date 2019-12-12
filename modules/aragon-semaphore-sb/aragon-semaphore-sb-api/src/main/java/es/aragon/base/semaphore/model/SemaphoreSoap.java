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

package es.aragon.base.semaphore.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.semaphore.service.http.SemaphoreServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.semaphore.service.http.SemaphoreServiceSoap
 * @generated
 */
@ProviderType
public class SemaphoreSoap implements Serializable {
	public static SemaphoreSoap toSoapModel(Semaphore model) {
		SemaphoreSoap soapModel = new SemaphoreSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSemaphoreId(model.getSemaphoreId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setCurrentUsers(model.getCurrentUsers());

		return soapModel;
	}

	public static SemaphoreSoap[] toSoapModels(Semaphore[] models) {
		SemaphoreSoap[] soapModels = new SemaphoreSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SemaphoreSoap[][] toSoapModels(Semaphore[][] models) {
		SemaphoreSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SemaphoreSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SemaphoreSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SemaphoreSoap[] toSoapModels(List<Semaphore> models) {
		List<SemaphoreSoap> soapModels = new ArrayList<SemaphoreSoap>(models.size());

		for (Semaphore model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SemaphoreSoap[soapModels.size()]);
	}

	public SemaphoreSoap() {
	}

	public long getPrimaryKey() {
		return _semaphoreId;
	}

	public void setPrimaryKey(long pk) {
		setSemaphoreId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSemaphoreId() {
		return _semaphoreId;
	}

	public void setSemaphoreId(long semaphoreId) {
		_semaphoreId = semaphoreId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public long getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		_maxUsers = maxUsers;
	}

	public long getCurrentUsers() {
		return _currentUsers;
	}

	public void setCurrentUsers(long currentUsers) {
		_currentUsers = currentUsers;
	}

	private String _uuid;
	private long _semaphoreId;
	private long _groupId;
	private String _serviceName;
	private long _maxUsers;
	private long _currentUsers;
}