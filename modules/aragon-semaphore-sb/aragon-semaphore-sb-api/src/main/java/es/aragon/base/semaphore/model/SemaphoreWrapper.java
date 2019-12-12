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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Semaphore}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Semaphore
 * @generated
 */
@ProviderType
public class SemaphoreWrapper implements Semaphore, ModelWrapper<Semaphore> {
	public SemaphoreWrapper(Semaphore semaphore) {
		_semaphore = semaphore;
	}

	@Override
	public Class<?> getModelClass() {
		return Semaphore.class;
	}

	@Override
	public String getModelClassName() {
		return Semaphore.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("semaphoreId", getSemaphoreId());
		attributes.put("groupId", getGroupId());
		attributes.put("serviceName", getServiceName());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("currentUsers", getCurrentUsers());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long semaphoreId = (Long)attributes.get("semaphoreId");

		if (semaphoreId != null) {
			setSemaphoreId(semaphoreId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Long currentUsers = (Long)attributes.get("currentUsers");

		if (currentUsers != null) {
			setCurrentUsers(currentUsers);
		}
	}

	@Override
	public Object clone() {
		return new SemaphoreWrapper((Semaphore)_semaphore.clone());
	}

	@Override
	public int compareTo(Semaphore semaphore) {
		return _semaphore.compareTo(semaphore);
	}

	/**
	* Returns the current users of this semaphore.
	*
	* @return the current users of this semaphore
	*/
	@Override
	public long getCurrentUsers() {
		return _semaphore.getCurrentUsers();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _semaphore.getExpandoBridge();
	}

	/**
	* Returns the group ID of this semaphore.
	*
	* @return the group ID of this semaphore
	*/
	@Override
	public long getGroupId() {
		return _semaphore.getGroupId();
	}

	/**
	* Returns the max users of this semaphore.
	*
	* @return the max users of this semaphore
	*/
	@Override
	public long getMaxUsers() {
		return _semaphore.getMaxUsers();
	}

	/**
	* Returns the primary key of this semaphore.
	*
	* @return the primary key of this semaphore
	*/
	@Override
	public long getPrimaryKey() {
		return _semaphore.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _semaphore.getPrimaryKeyObj();
	}

	/**
	* Returns the semaphore ID of this semaphore.
	*
	* @return the semaphore ID of this semaphore
	*/
	@Override
	public long getSemaphoreId() {
		return _semaphore.getSemaphoreId();
	}

	/**
	* Returns the service name of this semaphore.
	*
	* @return the service name of this semaphore
	*/
	@Override
	public String getServiceName() {
		return _semaphore.getServiceName();
	}

	/**
	* Returns the uuid of this semaphore.
	*
	* @return the uuid of this semaphore
	*/
	@Override
	public String getUuid() {
		return _semaphore.getUuid();
	}

	@Override
	public int hashCode() {
		return _semaphore.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _semaphore.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _semaphore.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _semaphore.isNew();
	}

	@Override
	public void persist() {
		_semaphore.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_semaphore.setCachedModel(cachedModel);
	}

	/**
	* Sets the current users of this semaphore.
	*
	* @param currentUsers the current users of this semaphore
	*/
	@Override
	public void setCurrentUsers(long currentUsers) {
		_semaphore.setCurrentUsers(currentUsers);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_semaphore.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_semaphore.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_semaphore.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this semaphore.
	*
	* @param groupId the group ID of this semaphore
	*/
	@Override
	public void setGroupId(long groupId) {
		_semaphore.setGroupId(groupId);
	}

	/**
	* Sets the max users of this semaphore.
	*
	* @param maxUsers the max users of this semaphore
	*/
	@Override
	public void setMaxUsers(long maxUsers) {
		_semaphore.setMaxUsers(maxUsers);
	}

	@Override
	public void setNew(boolean n) {
		_semaphore.setNew(n);
	}

	/**
	* Sets the primary key of this semaphore.
	*
	* @param primaryKey the primary key of this semaphore
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_semaphore.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_semaphore.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the semaphore ID of this semaphore.
	*
	* @param semaphoreId the semaphore ID of this semaphore
	*/
	@Override
	public void setSemaphoreId(long semaphoreId) {
		_semaphore.setSemaphoreId(semaphoreId);
	}

	/**
	* Sets the service name of this semaphore.
	*
	* @param serviceName the service name of this semaphore
	*/
	@Override
	public void setServiceName(String serviceName) {
		_semaphore.setServiceName(serviceName);
	}

	/**
	* Sets the uuid of this semaphore.
	*
	* @param uuid the uuid of this semaphore
	*/
	@Override
	public void setUuid(String uuid) {
		_semaphore.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Semaphore> toCacheModel() {
		return _semaphore.toCacheModel();
	}

	@Override
	public Semaphore toEscapedModel() {
		return new SemaphoreWrapper(_semaphore.toEscapedModel());
	}

	@Override
	public String toString() {
		return _semaphore.toString();
	}

	@Override
	public Semaphore toUnescapedModel() {
		return new SemaphoreWrapper(_semaphore.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _semaphore.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SemaphoreWrapper)) {
			return false;
		}

		SemaphoreWrapper semaphoreWrapper = (SemaphoreWrapper)obj;

		if (Objects.equals(_semaphore, semaphoreWrapper._semaphore)) {
			return true;
		}

		return false;
	}

	@Override
	public Semaphore getWrappedModel() {
		return _semaphore;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _semaphore.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _semaphore.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_semaphore.resetOriginalValues();
	}

	private final Semaphore _semaphore;
}