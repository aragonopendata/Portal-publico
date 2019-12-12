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

import es.aragon.base.migration.model.ContentUrl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContentUrl in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrl
 * @generated
 */
@ProviderType
public class ContentUrlCacheModel implements CacheModel<ContentUrl>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentUrlCacheModel)) {
			return false;
		}

		ContentUrlCacheModel contentUrlCacheModel = (ContentUrlCacheModel)obj;

		if (contentUrlId == contentUrlCacheModel.contentUrlId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contentUrlId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentUrlId=");
		sb.append(contentUrlId);
		sb.append(", contentOriginId=");
		sb.append(contentOriginId);
		sb.append(", contentDestinationId=");
		sb.append(contentDestinationId);
		sb.append(", urlOrigin=");
		sb.append(urlOrigin);
		sb.append(", urlDestination=");
		sb.append(urlDestination);
		sb.append(", className=");
		sb.append(className);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContentUrl toEntityModel() {
		ContentUrlImpl contentUrlImpl = new ContentUrlImpl();

		if (uuid == null) {
			contentUrlImpl.setUuid("");
		}
		else {
			contentUrlImpl.setUuid(uuid);
		}

		contentUrlImpl.setContentUrlId(contentUrlId);
		contentUrlImpl.setContentOriginId(contentOriginId);
		contentUrlImpl.setContentDestinationId(contentDestinationId);

		if (urlOrigin == null) {
			contentUrlImpl.setUrlOrigin("");
		}
		else {
			contentUrlImpl.setUrlOrigin(urlOrigin);
		}

		if (urlDestination == null) {
			contentUrlImpl.setUrlDestination("");
		}
		else {
			contentUrlImpl.setUrlDestination(urlDestination);
		}

		if (className == null) {
			contentUrlImpl.setClassName("");
		}
		else {
			contentUrlImpl.setClassName(className);
		}

		contentUrlImpl.resetOriginalValues();

		return contentUrlImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		contentUrlId = objectInput.readLong();

		contentOriginId = objectInput.readLong();

		contentDestinationId = objectInput.readLong();
		urlOrigin = objectInput.readUTF();
		urlDestination = objectInput.readUTF();
		className = objectInput.readUTF();
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

		objectOutput.writeLong(contentUrlId);

		objectOutput.writeLong(contentOriginId);

		objectOutput.writeLong(contentDestinationId);

		if (urlOrigin == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlOrigin);
		}

		if (urlDestination == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlDestination);
		}

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}
	}

	public String uuid;
	public long contentUrlId;
	public long contentOriginId;
	public long contentDestinationId;
	public String urlOrigin;
	public String urlDestination;
	public String className;
}