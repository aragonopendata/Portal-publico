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

package es.aragon.base.process.queue.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.process.queue.service.http.QueuedProcessServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.process.queue.service.http.QueuedProcessServiceSoap
 * @generated
 */
@ProviderType
public class QueuedProcessSoap implements Serializable {
	public static QueuedProcessSoap toSoapModel(QueuedProcess model) {
		QueuedProcessSoap soapModel = new QueuedProcessSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setQueuedProcessId(model.getQueuedProcessId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setExecutionStartedDate(model.getExecutionStartedDate());
		soapModel.setExecutionFinishedDate(model.getExecutionFinishedDate());
		soapModel.setProcessName(model.getProcessName());
		soapModel.setJsonParameters(model.getJsonParameters());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static QueuedProcessSoap[] toSoapModels(QueuedProcess[] models) {
		QueuedProcessSoap[] soapModels = new QueuedProcessSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static QueuedProcessSoap[][] toSoapModels(QueuedProcess[][] models) {
		QueuedProcessSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new QueuedProcessSoap[models.length][models[0].length];
		}
		else {
			soapModels = new QueuedProcessSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static QueuedProcessSoap[] toSoapModels(List<QueuedProcess> models) {
		List<QueuedProcessSoap> soapModels = new ArrayList<QueuedProcessSoap>(models.size());

		for (QueuedProcess model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new QueuedProcessSoap[soapModels.size()]);
	}

	public QueuedProcessSoap() {
	}

	public long getPrimaryKey() {
		return _queuedProcessId;
	}

	public void setPrimaryKey(long pk) {
		setQueuedProcessId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getQueuedProcessId() {
		return _queuedProcessId;
	}

	public void setQueuedProcessId(long queuedProcessId) {
		_queuedProcessId = queuedProcessId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getExecutionStartedDate() {
		return _executionStartedDate;
	}

	public void setExecutionStartedDate(Date executionStartedDate) {
		_executionStartedDate = executionStartedDate;
	}

	public Date getExecutionFinishedDate() {
		return _executionFinishedDate;
	}

	public void setExecutionFinishedDate(Date executionFinishedDate) {
		_executionFinishedDate = executionFinishedDate;
	}

	public String getProcessName() {
		return _processName;
	}

	public void setProcessName(String processName) {
		_processName = processName;
	}

	public String getJsonParameters() {
		return _jsonParameters;
	}

	public void setJsonParameters(String jsonParameters) {
		_jsonParameters = jsonParameters;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _queuedProcessId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _executionStartedDate;
	private Date _executionFinishedDate;
	private String _processName;
	private String _jsonParameters;
	private int _status;
}