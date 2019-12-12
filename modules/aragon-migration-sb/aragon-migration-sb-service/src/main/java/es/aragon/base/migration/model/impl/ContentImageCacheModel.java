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

import es.aragon.base.migration.model.ContentImage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContentImage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContentImage
 * @generated
 */
@ProviderType
public class ContentImageCacheModel implements CacheModel<ContentImage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentImageCacheModel)) {
			return false;
		}

		ContentImageCacheModel contentImageCacheModel = (ContentImageCacheModel)obj;

		if (contentImageId == contentImageCacheModel.contentImageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contentImageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentImageId=");
		sb.append(contentImageId);
		sb.append(", contentOriginId=");
		sb.append(contentOriginId);
		sb.append(", url=");
		sb.append(url);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContentImage toEntityModel() {
		ContentImageImpl contentImageImpl = new ContentImageImpl();

		if (uuid == null) {
			contentImageImpl.setUuid("");
		}
		else {
			contentImageImpl.setUuid(uuid);
		}

		contentImageImpl.setContentImageId(contentImageId);
		contentImageImpl.setContentOriginId(contentOriginId);

		if (url == null) {
			contentImageImpl.setUrl("");
		}
		else {
			contentImageImpl.setUrl(url);
		}

		contentImageImpl.resetOriginalValues();

		return contentImageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		contentImageId = objectInput.readLong();

		contentOriginId = objectInput.readLong();
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

		objectOutput.writeLong(contentImageId);

		objectOutput.writeLong(contentOriginId);

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}
	}

	public String uuid;
	public long contentImageId;
	public long contentOriginId;
	public String url;
}