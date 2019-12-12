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

package es.aragon.base.social_network.sb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.social_network.sb.model.SocialNetwork;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SocialNetwork in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetwork
 * @generated
 */
@ProviderType
public class SocialNetworkCacheModel implements CacheModel<SocialNetwork>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SocialNetworkCacheModel)) {
			return false;
		}

		SocialNetworkCacheModel socialNetworkCacheModel = (SocialNetworkCacheModel)obj;

		if (socialNetworkId == socialNetworkCacheModel.socialNetworkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, socialNetworkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", socialNetworkId=");
		sb.append(socialNetworkId);
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
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", url=");
		sb.append(url);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", extraParameters=");
		sb.append(extraParameters);
		sb.append(", alt=");
		sb.append(alt);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SocialNetwork toEntityModel() {
		SocialNetworkImpl socialNetworkImpl = new SocialNetworkImpl();

		if (uuid == null) {
			socialNetworkImpl.setUuid("");
		}
		else {
			socialNetworkImpl.setUuid(uuid);
		}

		socialNetworkImpl.setSocialNetworkId(socialNetworkId);
		socialNetworkImpl.setGroupId(groupId);
		socialNetworkImpl.setCompanyId(companyId);
		socialNetworkImpl.setUserId(userId);

		if (userName == null) {
			socialNetworkImpl.setUserName("");
		}
		else {
			socialNetworkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			socialNetworkImpl.setCreateDate(null);
		}
		else {
			socialNetworkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			socialNetworkImpl.setModifiedDate(null);
		}
		else {
			socialNetworkImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (url == null) {
			socialNetworkImpl.setUrl("");
		}
		else {
			socialNetworkImpl.setUrl(url);
		}

		socialNetworkImpl.setImageId(imageId);

		if (extraParameters == null) {
			socialNetworkImpl.setExtraParameters("");
		}
		else {
			socialNetworkImpl.setExtraParameters(extraParameters);
		}

		if (alt == null) {
			socialNetworkImpl.setAlt("");
		}
		else {
			socialNetworkImpl.setAlt(alt);
		}

		socialNetworkImpl.resetOriginalValues();

		return socialNetworkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		socialNetworkId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		url = objectInput.readUTF();

		imageId = objectInput.readLong();
		extraParameters = objectInput.readUTF();
		alt = objectInput.readUTF();
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

		objectOutput.writeLong(socialNetworkId);

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
		objectOutput.writeLong(modifiedDate);

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeLong(imageId);

		if (extraParameters == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraParameters);
		}

		if (alt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(alt);
		}
	}

	public String uuid;
	public long socialNetworkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String url;
	public long imageId;
	public String extraParameters;
	public String alt;
}