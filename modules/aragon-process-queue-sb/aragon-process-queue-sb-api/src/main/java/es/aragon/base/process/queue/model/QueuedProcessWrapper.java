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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link QueuedProcess}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcess
 * @generated
 */
@ProviderType
public class QueuedProcessWrapper implements QueuedProcess,
	ModelWrapper<QueuedProcess> {
	public QueuedProcessWrapper(QueuedProcess queuedProcess) {
		_queuedProcess = queuedProcess;
	}

	@Override
	public Class<?> getModelClass() {
		return QueuedProcess.class;
	}

	@Override
	public String getModelClassName() {
		return QueuedProcess.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("queuedProcessId", getQueuedProcessId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("executionStartedDate", getExecutionStartedDate());
		attributes.put("executionFinishedDate", getExecutionFinishedDate());
		attributes.put("processName", getProcessName());
		attributes.put("jsonParameters", getJsonParameters());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long queuedProcessId = (Long)attributes.get("queuedProcessId");

		if (queuedProcessId != null) {
			setQueuedProcessId(queuedProcessId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date executionStartedDate = (Date)attributes.get("executionStartedDate");

		if (executionStartedDate != null) {
			setExecutionStartedDate(executionStartedDate);
		}

		Date executionFinishedDate = (Date)attributes.get(
				"executionFinishedDate");

		if (executionFinishedDate != null) {
			setExecutionFinishedDate(executionFinishedDate);
		}

		String processName = (String)attributes.get("processName");

		if (processName != null) {
			setProcessName(processName);
		}

		String jsonParameters = (String)attributes.get("jsonParameters");

		if (jsonParameters != null) {
			setJsonParameters(jsonParameters);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new QueuedProcessWrapper((QueuedProcess)_queuedProcess.clone());
	}

	@Override
	public int compareTo(QueuedProcess queuedProcess) {
		return _queuedProcess.compareTo(queuedProcess);
	}

	/**
	* Returns the company ID of this queued process.
	*
	* @return the company ID of this queued process
	*/
	@Override
	public long getCompanyId() {
		return _queuedProcess.getCompanyId();
	}

	/**
	* Returns the create date of this queued process.
	*
	* @return the create date of this queued process
	*/
	@Override
	public Date getCreateDate() {
		return _queuedProcess.getCreateDate();
	}

	/**
	* Returns the execution finished date of this queued process.
	*
	* @return the execution finished date of this queued process
	*/
	@Override
	public Date getExecutionFinishedDate() {
		return _queuedProcess.getExecutionFinishedDate();
	}

	/**
	* Returns the execution started date of this queued process.
	*
	* @return the execution started date of this queued process
	*/
	@Override
	public Date getExecutionStartedDate() {
		return _queuedProcess.getExecutionStartedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _queuedProcess.getExpandoBridge();
	}

	/**
	* Returns the group ID of this queued process.
	*
	* @return the group ID of this queued process
	*/
	@Override
	public long getGroupId() {
		return _queuedProcess.getGroupId();
	}

	/**
	* Returns the json parameters of this queued process.
	*
	* @return the json parameters of this queued process
	*/
	@Override
	public String getJsonParameters() {
		return _queuedProcess.getJsonParameters();
	}

	/**
	* Returns the primary key of this queued process.
	*
	* @return the primary key of this queued process
	*/
	@Override
	public long getPrimaryKey() {
		return _queuedProcess.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _queuedProcess.getPrimaryKeyObj();
	}

	/**
	* Returns the process name of this queued process.
	*
	* @return the process name of this queued process
	*/
	@Override
	public String getProcessName() {
		return _queuedProcess.getProcessName();
	}

	/**
	* Returns the queued process ID of this queued process.
	*
	* @return the queued process ID of this queued process
	*/
	@Override
	public long getQueuedProcessId() {
		return _queuedProcess.getQueuedProcessId();
	}

	/**
	* Returns the status of this queued process.
	*
	* @return the status of this queued process
	*/
	@Override
	public int getStatus() {
		return _queuedProcess.getStatus();
	}

	/**
	* Returns the user ID of this queued process.
	*
	* @return the user ID of this queued process
	*/
	@Override
	public long getUserId() {
		return _queuedProcess.getUserId();
	}

	/**
	* Returns the user name of this queued process.
	*
	* @return the user name of this queued process
	*/
	@Override
	public String getUserName() {
		return _queuedProcess.getUserName();
	}

	/**
	* Returns the user uuid of this queued process.
	*
	* @return the user uuid of this queued process
	*/
	@Override
	public String getUserUuid() {
		return _queuedProcess.getUserUuid();
	}

	/**
	* Returns the uuid of this queued process.
	*
	* @return the uuid of this queued process
	*/
	@Override
	public String getUuid() {
		return _queuedProcess.getUuid();
	}

	@Override
	public int hashCode() {
		return _queuedProcess.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _queuedProcess.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _queuedProcess.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _queuedProcess.isNew();
	}

	@Override
	public void persist() {
		_queuedProcess.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_queuedProcess.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this queued process.
	*
	* @param companyId the company ID of this queued process
	*/
	@Override
	public void setCompanyId(long companyId) {
		_queuedProcess.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this queued process.
	*
	* @param createDate the create date of this queued process
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_queuedProcess.setCreateDate(createDate);
	}

	/**
	* Sets the execution finished date of this queued process.
	*
	* @param executionFinishedDate the execution finished date of this queued process
	*/
	@Override
	public void setExecutionFinishedDate(Date executionFinishedDate) {
		_queuedProcess.setExecutionFinishedDate(executionFinishedDate);
	}

	/**
	* Sets the execution started date of this queued process.
	*
	* @param executionStartedDate the execution started date of this queued process
	*/
	@Override
	public void setExecutionStartedDate(Date executionStartedDate) {
		_queuedProcess.setExecutionStartedDate(executionStartedDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_queuedProcess.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_queuedProcess.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_queuedProcess.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this queued process.
	*
	* @param groupId the group ID of this queued process
	*/
	@Override
	public void setGroupId(long groupId) {
		_queuedProcess.setGroupId(groupId);
	}

	/**
	* Sets the json parameters of this queued process.
	*
	* @param jsonParameters the json parameters of this queued process
	*/
	@Override
	public void setJsonParameters(String jsonParameters) {
		_queuedProcess.setJsonParameters(jsonParameters);
	}

	@Override
	public void setNew(boolean n) {
		_queuedProcess.setNew(n);
	}

	/**
	* Sets the primary key of this queued process.
	*
	* @param primaryKey the primary key of this queued process
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_queuedProcess.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_queuedProcess.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process name of this queued process.
	*
	* @param processName the process name of this queued process
	*/
	@Override
	public void setProcessName(String processName) {
		_queuedProcess.setProcessName(processName);
	}

	/**
	* Sets the queued process ID of this queued process.
	*
	* @param queuedProcessId the queued process ID of this queued process
	*/
	@Override
	public void setQueuedProcessId(long queuedProcessId) {
		_queuedProcess.setQueuedProcessId(queuedProcessId);
	}

	/**
	* Sets the status of this queued process.
	*
	* @param status the status of this queued process
	*/
	@Override
	public void setStatus(int status) {
		_queuedProcess.setStatus(status);
	}

	/**
	* Sets the user ID of this queued process.
	*
	* @param userId the user ID of this queued process
	*/
	@Override
	public void setUserId(long userId) {
		_queuedProcess.setUserId(userId);
	}

	/**
	* Sets the user name of this queued process.
	*
	* @param userName the user name of this queued process
	*/
	@Override
	public void setUserName(String userName) {
		_queuedProcess.setUserName(userName);
	}

	/**
	* Sets the user uuid of this queued process.
	*
	* @param userUuid the user uuid of this queued process
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_queuedProcess.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this queued process.
	*
	* @param uuid the uuid of this queued process
	*/
	@Override
	public void setUuid(String uuid) {
		_queuedProcess.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<QueuedProcess> toCacheModel() {
		return _queuedProcess.toCacheModel();
	}

	@Override
	public QueuedProcess toEscapedModel() {
		return new QueuedProcessWrapper(_queuedProcess.toEscapedModel());
	}

	@Override
	public String toString() {
		return _queuedProcess.toString();
	}

	@Override
	public QueuedProcess toUnescapedModel() {
		return new QueuedProcessWrapper(_queuedProcess.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _queuedProcess.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QueuedProcessWrapper)) {
			return false;
		}

		QueuedProcessWrapper queuedProcessWrapper = (QueuedProcessWrapper)obj;

		if (Objects.equals(_queuedProcess, queuedProcessWrapper._queuedProcess)) {
			return true;
		}

		return false;
	}

	@Override
	public QueuedProcess getWrappedModel() {
		return _queuedProcess;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _queuedProcess.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _queuedProcess.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_queuedProcess.resetOriginalValues();
	}

	private final QueuedProcess _queuedProcess;
}