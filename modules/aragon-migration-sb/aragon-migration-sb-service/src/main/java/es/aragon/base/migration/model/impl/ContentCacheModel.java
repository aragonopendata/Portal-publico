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

import es.aragon.base.migration.model.Content;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Content in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Content
 * @generated
 */
@ProviderType
public class ContentCacheModel implements CacheModel<Content>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentCacheModel)) {
			return false;
		}

		ContentCacheModel contentCacheModel = (ContentCacheModel)obj;

		if (contentId == contentCacheModel.contentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentId=");
		sb.append(contentId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", url=");
		sb.append(url);
		sb.append(", contentType=");
		sb.append(contentType);
		sb.append(", shortExcerpt=");
		sb.append(shortExcerpt);
		sb.append(", excerpt=");
		sb.append(excerpt);
		sb.append(", originalBodyHash=");
		sb.append(originalBodyHash);
		sb.append(", originalBody=");
		sb.append(originalBody);
		sb.append(", areaId=");
		sb.append(areaId);
		sb.append(", actionId=");
		sb.append(actionId);
		sb.append(", statusId=");
		sb.append(statusId);
		sb.append(", templateId=");
		sb.append(templateId);
		sb.append(", tags=");
		sb.append(tags);
		sb.append(", lastModifiedUserId=");
		sb.append(lastModifiedUserId);
		sb.append(", assignedUserId=");
		sb.append(assignedUserId);
		sb.append(", userGroupId=");
		sb.append(userGroupId);
		sb.append(", journalFolderId=");
		sb.append(journalFolderId);
		sb.append(", dateCreated=");
		sb.append(dateCreated);
		sb.append(", dateModified=");
		sb.append(dateModified);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", visibility=");
		sb.append(visibility);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Content toEntityModel() {
		ContentImpl contentImpl = new ContentImpl();

		if (uuid == null) {
			contentImpl.setUuid("");
		}
		else {
			contentImpl.setUuid(uuid);
		}

		contentImpl.setContentId(contentId);

		if (title == null) {
			contentImpl.setTitle("");
		}
		else {
			contentImpl.setTitle(title);
		}

		if (url == null) {
			contentImpl.setUrl("");
		}
		else {
			contentImpl.setUrl(url);
		}

		if (contentType == null) {
			contentImpl.setContentType("");
		}
		else {
			contentImpl.setContentType(contentType);
		}

		if (shortExcerpt == null) {
			contentImpl.setShortExcerpt("");
		}
		else {
			contentImpl.setShortExcerpt(shortExcerpt);
		}

		if (excerpt == null) {
			contentImpl.setExcerpt("");
		}
		else {
			contentImpl.setExcerpt(excerpt);
		}

		if (originalBodyHash == null) {
			contentImpl.setOriginalBodyHash("");
		}
		else {
			contentImpl.setOriginalBodyHash(originalBodyHash);
		}

		if (originalBody == null) {
			contentImpl.setOriginalBody("");
		}
		else {
			contentImpl.setOriginalBody(originalBody);
		}

		contentImpl.setAreaId(areaId);
		contentImpl.setActionId(actionId);
		contentImpl.setStatusId(statusId);
		contentImpl.setTemplateId(templateId);

		if (tags == null) {
			contentImpl.setTags("");
		}
		else {
			contentImpl.setTags(tags);
		}

		contentImpl.setLastModifiedUserId(lastModifiedUserId);
		contentImpl.setAssignedUserId(assignedUserId);
		contentImpl.setUserGroupId(userGroupId);
		contentImpl.setJournalFolderId(journalFolderId);

		if (dateCreated == Long.MIN_VALUE) {
			contentImpl.setDateCreated(null);
		}
		else {
			contentImpl.setDateCreated(new Date(dateCreated));
		}

		if (dateModified == Long.MIN_VALUE) {
			contentImpl.setDateModified(null);
		}
		else {
			contentImpl.setDateModified(new Date(dateModified));
		}

		if (comments == null) {
			contentImpl.setComments("");
		}
		else {
			contentImpl.setComments(comments);
		}

		contentImpl.setVisibility(visibility);

		contentImpl.resetOriginalValues();

		return contentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		contentId = objectInput.readLong();
		title = objectInput.readUTF();
		url = objectInput.readUTF();
		contentType = objectInput.readUTF();
		shortExcerpt = objectInput.readUTF();
		excerpt = objectInput.readUTF();
		originalBodyHash = objectInput.readUTF();
		originalBody = objectInput.readUTF();

		areaId = objectInput.readLong();

		actionId = objectInput.readLong();

		statusId = objectInput.readLong();

		templateId = objectInput.readLong();
		tags = objectInput.readUTF();

		lastModifiedUserId = objectInput.readLong();

		assignedUserId = objectInput.readLong();

		userGroupId = objectInput.readLong();

		journalFolderId = objectInput.readLong();
		dateCreated = objectInput.readLong();
		dateModified = objectInput.readLong();
		comments = objectInput.readUTF();

		visibility = objectInput.readInt();
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

		objectOutput.writeLong(contentId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (contentType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contentType);
		}

		if (shortExcerpt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(shortExcerpt);
		}

		if (excerpt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(excerpt);
		}

		if (originalBodyHash == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(originalBodyHash);
		}

		if (originalBody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(originalBody);
		}

		objectOutput.writeLong(areaId);

		objectOutput.writeLong(actionId);

		objectOutput.writeLong(statusId);

		objectOutput.writeLong(templateId);

		if (tags == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tags);
		}

		objectOutput.writeLong(lastModifiedUserId);

		objectOutput.writeLong(assignedUserId);

		objectOutput.writeLong(userGroupId);

		objectOutput.writeLong(journalFolderId);
		objectOutput.writeLong(dateCreated);
		objectOutput.writeLong(dateModified);

		if (comments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comments);
		}

		objectOutput.writeInt(visibility);
	}

	public String uuid;
	public long contentId;
	public String title;
	public String url;
	public String contentType;
	public String shortExcerpt;
	public String excerpt;
	public String originalBodyHash;
	public String originalBody;
	public long areaId;
	public long actionId;
	public long statusId;
	public long templateId;
	public String tags;
	public long lastModifiedUserId;
	public long assignedUserId;
	public long userGroupId;
	public long journalFolderId;
	public long dateCreated;
	public long dateModified;
	public String comments;
	public int visibility;
}