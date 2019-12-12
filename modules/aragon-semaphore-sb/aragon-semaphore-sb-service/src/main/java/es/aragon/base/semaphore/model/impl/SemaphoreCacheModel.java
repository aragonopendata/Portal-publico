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

package es.aragon.base.semaphore.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.semaphore.model.Semaphore;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Semaphore in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Semaphore
 * @generated
 */
@ProviderType
public class SemaphoreCacheModel implements CacheModel<Semaphore>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SemaphoreCacheModel)) {
			return false;
		}

		SemaphoreCacheModel semaphoreCacheModel = (SemaphoreCacheModel)obj;

		if (semaphoreId == semaphoreCacheModel.semaphoreId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, semaphoreId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", semaphoreId=");
		sb.append(semaphoreId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", currentUsers=");
		sb.append(currentUsers);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Semaphore toEntityModel() {
		SemaphoreImpl semaphoreImpl = new SemaphoreImpl();

		if (uuid == null) {
			semaphoreImpl.setUuid("");
		}
		else {
			semaphoreImpl.setUuid(uuid);
		}

		semaphoreImpl.setSemaphoreId(semaphoreId);
		semaphoreImpl.setGroupId(groupId);

		if (serviceName == null) {
			semaphoreImpl.setServiceName("");
		}
		else {
			semaphoreImpl.setServiceName(serviceName);
		}

		semaphoreImpl.setMaxUsers(maxUsers);
		semaphoreImpl.setCurrentUsers(currentUsers);

		semaphoreImpl.resetOriginalValues();

		return semaphoreImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		semaphoreId = objectInput.readLong();

		groupId = objectInput.readLong();
		serviceName = objectInput.readUTF();

		maxUsers = objectInput.readLong();

		currentUsers = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(semaphoreId);

		objectOutput.writeLong(groupId);

		if (serviceName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serviceName);
		}

		objectOutput.writeLong(maxUsers);

		objectOutput.writeLong(currentUsers);
	}

	public String uuid;
	public long semaphoreId;
	public long groupId;
	public String serviceName;
	public long maxUsers;
	public long currentUsers;
}