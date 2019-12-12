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

import es.aragon.base.migration.model.ContentRelated;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContentRelated in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContentRelated
 * @generated
 */
@ProviderType
public class ContentRelatedCacheModel implements CacheModel<ContentRelated>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentRelatedCacheModel)) {
			return false;
		}

		ContentRelatedCacheModel contentRelatedCacheModel = (ContentRelatedCacheModel)obj;

		if (contentRelatedId == contentRelatedCacheModel.contentRelatedId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contentRelatedId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentRelatedId=");
		sb.append(contentRelatedId);
		sb.append(", contentParentId=");
		sb.append(contentParentId);
		sb.append(", contentLinkedId=");
		sb.append(contentLinkedId);
		sb.append(", url=");
		sb.append(url);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContentRelated toEntityModel() {
		ContentRelatedImpl contentRelatedImpl = new ContentRelatedImpl();

		if (uuid == null) {
			contentRelatedImpl.setUuid("");
		}
		else {
			contentRelatedImpl.setUuid(uuid);
		}

		contentRelatedImpl.setContentRelatedId(contentRelatedId);
		contentRelatedImpl.setContentParentId(contentParentId);
		contentRelatedImpl.setContentLinkedId(contentLinkedId);

		if (url == null) {
			contentRelatedImpl.setUrl("");
		}
		else {
			contentRelatedImpl.setUrl(url);
		}

		contentRelatedImpl.resetOriginalValues();

		return contentRelatedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		contentRelatedId = objectInput.readLong();

		contentParentId = objectInput.readLong();

		contentLinkedId = objectInput.readLong();
		url = objectInput.readUTF();
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

		objectOutput.writeLong(contentRelatedId);

		objectOutput.writeLong(contentParentId);

		objectOutput.writeLong(contentLinkedId);

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}
	}

	public String uuid;
	public long contentRelatedId;
	public long contentParentId;
	public long contentLinkedId;
	public String url;
}