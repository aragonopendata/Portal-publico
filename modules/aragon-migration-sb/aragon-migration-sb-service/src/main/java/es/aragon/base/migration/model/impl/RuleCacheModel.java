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

package es.aragon.base.migration.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.migration.model.Rule;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Rule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Rule
 * @generated
 */
@ProviderType
public class RuleCacheModel implements CacheModel<Rule>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RuleCacheModel)) {
			return false;
		}

		RuleCacheModel ruleCacheModel = (RuleCacheModel)obj;

		if (ruleId == ruleCacheModel.ruleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ruleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ruleId=");
		sb.append(ruleId);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", userGroupId=");
		sb.append(userGroupId);
		sb.append(", journalFolderId=");
		sb.append(journalFolderId);
		sb.append(", assignedUserId=");
		sb.append(assignedUserId);
		sb.append(", url=");
		sb.append(url);
		sb.append(", tags=");
		sb.append(tags);
		sb.append(", actionId=");
		sb.append(actionId);
		sb.append(", availableUserGroupId=");
		sb.append(availableUserGroupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Rule toEntityModel() {
		RuleImpl ruleImpl = new RuleImpl();

		if (uuid == null) {
			ruleImpl.setUuid("");
		}
		else {
			ruleImpl.setUuid(uuid);
		}

		ruleImpl.setRuleId(ruleId);
		ruleImpl.setTypeId(typeId);
		ruleImpl.setUserGroupId(userGroupId);
		ruleImpl.setJournalFolderId(journalFolderId);
		ruleImpl.setAssignedUserId(assignedUserId);

		if (url == null) {
			ruleImpl.setUrl("");
		}
		else {
			ruleImpl.setUrl(url);
		}

		if (tags == null) {
			ruleImpl.setTags("");
		}
		else {
			ruleImpl.setTags(tags);
		}

		ruleImpl.setActionId(actionId);

		if (availableUserGroupId == null) {
			ruleImpl.setAvailableUserGroupId("");
		}
		else {
			ruleImpl.setAvailableUserGroupId(availableUserGroupId);
		}

		ruleImpl.setUserId(userId);

		if (modifiedDate == Long.MIN_VALUE) {
			ruleImpl.setModifiedDate(null);
		}
		else {
			ruleImpl.setModifiedDate(new Date(modifiedDate));
		}

		ruleImpl.resetOriginalValues();

		return ruleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		ruleId = objectInput.readLong();

		typeId = objectInput.readInt();

		userGroupId = objectInput.readLong();

		journalFolderId = objectInput.readLong();

		assignedUserId = objectInput.readLong();
		url = objectInput.readUTF();
		tags = objectInput.readUTF();

		actionId = objectInput.readLong();
		availableUserGroupId = objectInput.readUTF();

		userId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
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

		objectOutput.writeLong(ruleId);

		objectOutput.writeInt(typeId);

		objectOutput.writeLong(userGroupId);

		objectOutput.writeLong(journalFolderId);

		objectOutput.writeLong(assignedUserId);

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (tags == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tags);
		}

		objectOutput.writeLong(actionId);

		if (availableUserGroupId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(availableUserGroupId);
		}

		objectOutput.writeLong(userId);
		objectOutput.writeLong(modifiedDate);
	}

	public String uuid;
	public long ruleId;
	public int typeId;
	public long userGroupId;
	public long journalFolderId;
	public long assignedUserId;
	public String url;
	public String tags;
	public long actionId;
	public String availableUserGroupId;
	public long userId;
	public long modifiedDate;
}