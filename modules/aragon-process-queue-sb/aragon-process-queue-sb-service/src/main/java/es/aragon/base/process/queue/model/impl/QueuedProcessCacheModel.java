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

package es.aragon.base.process.queue.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.process.queue.model.QueuedProcess;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing QueuedProcess in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcess
 * @generated
 */
@ProviderType
public class QueuedProcessCacheModel implements CacheModel<QueuedProcess>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QueuedProcessCacheModel)) {
			return false;
		}

		QueuedProcessCacheModel queuedProcessCacheModel = (QueuedProcessCacheModel)obj;

		if (queuedProcessId == queuedProcessCacheModel.queuedProcessId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, queuedProcessId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", queuedProcessId=");
		sb.append(queuedProcessId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", executionStartedDate=");
		sb.append(executionStartedDate);
		sb.append(", executionFinishedDate=");
		sb.append(executionFinishedDate);
		sb.append(", processName=");
		sb.append(processName);
		sb.append(", jsonParameters=");
		sb.append(jsonParameters);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public QueuedProcess toEntityModel() {
		QueuedProcessImpl queuedProcessImpl = new QueuedProcessImpl();

		if (uuid == null) {
			queuedProcessImpl.setUuid("");
		}
		else {
			queuedProcessImpl.setUuid(uuid);
		}

		queuedProcessImpl.setQueuedProcessId(queuedProcessId);
		queuedProcessImpl.setGroupId(groupId);
		queuedProcessImpl.setCompanyId(companyId);
		queuedProcessImpl.setUserId(userId);

		if (userName == null) {
			queuedProcessImpl.setUserName("");
		}
		else {
			queuedProcessImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			queuedProcessImpl.setCreateDate(null);
		}
		else {
			queuedProcessImpl.setCreateDate(new Date(createDate));
		}

		if (executionStartedDate == Long.MIN_VALUE) {
			queuedProcessImpl.setExecutionStartedDate(null);
		}
		else {
			queuedProcessImpl.setExecutionStartedDate(new Date(
					executionStartedDate));
		}

		if (executionFinishedDate == Long.MIN_VALUE) {
			queuedProcessImpl.setExecutionFinishedDate(null);
		}
		else {
			queuedProcessImpl.setExecutionFinishedDate(new Date(
					executionFinishedDate));
		}

		if (processName == null) {
			queuedProcessImpl.setProcessName("");
		}
		else {
			queuedProcessImpl.setProcessName(processName);
		}

		if (jsonParameters == null) {
			queuedProcessImpl.setJsonParameters("");
		}
		else {
			queuedProcessImpl.setJsonParameters(jsonParameters);
		}

		queuedProcessImpl.setStatus(status);

		queuedProcessImpl.resetOriginalValues();

		return queuedProcessImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		queuedProcessId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		executionStartedDate = objectInput.readLong();
		executionFinishedDate = objectInput.readLong();
		processName = objectInput.readUTF();
		jsonParameters = objectInput.readUTF();

		status = objectInput.readInt();
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

		objectOutput.writeLong(queuedProcessId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(executionStartedDate);
		objectOutput.writeLong(executionFinishedDate);

		if (processName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(processName);
		}

		if (jsonParameters == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jsonParameters);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long queuedProcessId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long executionStartedDate;
	public long executionFinishedDate;
	public String processName;
	public String jsonParameters;
	public int status;
}