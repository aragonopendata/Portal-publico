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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the QueuedProcess service. Represents a row in the &quot;EAB_PQ_QueuedProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link es.aragon.base.process.queue.model.impl.QueuedProcessImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcess
 * @see es.aragon.base.process.queue.model.impl.QueuedProcessImpl
 * @see es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl
 * @generated
 */
@ProviderType
public interface QueuedProcessModel extends BaseModel<QueuedProcess>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a queued process model instance should use the {@link QueuedProcess} interface instead.
	 */

	/**
	 * Returns the primary key of this queued process.
	 *
	 * @return the primary key of this queued process
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this queued process.
	 *
	 * @param primaryKey the primary key of this queued process
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this queued process.
	 *
	 * @return the uuid of this queued process
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this queued process.
	 *
	 * @param uuid the uuid of this queued process
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the queued process ID of this queued process.
	 *
	 * @return the queued process ID of this queued process
	 */
	public long getQueuedProcessId();

	/**
	 * Sets the queued process ID of this queued process.
	 *
	 * @param queuedProcessId the queued process ID of this queued process
	 */
	public void setQueuedProcessId(long queuedProcessId);

	/**
	 * Returns the group ID of this queued process.
	 *
	 * @return the group ID of this queued process
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this queued process.
	 *
	 * @param groupId the group ID of this queued process
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this queued process.
	 *
	 * @return the company ID of this queued process
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this queued process.
	 *
	 * @param companyId the company ID of this queued process
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this queued process.
	 *
	 * @return the user ID of this queued process
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this queued process.
	 *
	 * @param userId the user ID of this queued process
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this queued process.
	 *
	 * @return the user uuid of this queued process
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this queued process.
	 *
	 * @param userUuid the user uuid of this queued process
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this queued process.
	 *
	 * @return the user name of this queued process
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this queued process.
	 *
	 * @param userName the user name of this queued process
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this queued process.
	 *
	 * @return the create date of this queued process
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this queued process.
	 *
	 * @param createDate the create date of this queued process
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the execution started date of this queued process.
	 *
	 * @return the execution started date of this queued process
	 */
	public Date getExecutionStartedDate();

	/**
	 * Sets the execution started date of this queued process.
	 *
	 * @param executionStartedDate the execution started date of this queued process
	 */
	public void setExecutionStartedDate(Date executionStartedDate);

	/**
	 * Returns the execution finished date of this queued process.
	 *
	 * @return the execution finished date of this queued process
	 */
	public Date getExecutionFinishedDate();

	/**
	 * Sets the execution finished date of this queued process.
	 *
	 * @param executionFinishedDate the execution finished date of this queued process
	 */
	public void setExecutionFinishedDate(Date executionFinishedDate);

	/**
	 * Returns the process name of this queued process.
	 *
	 * @return the process name of this queued process
	 */
	@AutoEscape
	public String getProcessName();

	/**
	 * Sets the process name of this queued process.
	 *
	 * @param processName the process name of this queued process
	 */
	public void setProcessName(String processName);

	/**
	 * Returns the json parameters of this queued process.
	 *
	 * @return the json parameters of this queued process
	 */
	@AutoEscape
	public String getJsonParameters();

	/**
	 * Sets the json parameters of this queued process.
	 *
	 * @param jsonParameters the json parameters of this queued process
	 */
	public void setJsonParameters(String jsonParameters);

	/**
	 * Returns the status of this queued process.
	 *
	 * @return the status of this queued process
	 */
	public int getStatus();

	/**
	 * Sets the status of this queued process.
	 *
	 * @param status the status of this queued process
	 */
	public void setStatus(int status);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(QueuedProcess queuedProcess);

	@Override
	public int hashCode();

	@Override
	public CacheModel<QueuedProcess> toCacheModel();

	@Override
	public QueuedProcess toEscapedModel();

	@Override
	public QueuedProcess toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}