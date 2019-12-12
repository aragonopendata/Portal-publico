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

package es.aragon.base.categories_custom_properties.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CustomCategoryProperty in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryProperty
 * @generated
 */
@ProviderType
public class CustomCategoryPropertyCacheModel implements CacheModel<CustomCategoryProperty>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomCategoryPropertyCacheModel)) {
			return false;
		}

		CustomCategoryPropertyCacheModel customCategoryPropertyCacheModel = (CustomCategoryPropertyCacheModel)obj;

		if (CustomCategoryPropertyId == customCategoryPropertyCacheModel.CustomCategoryPropertyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CustomCategoryPropertyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CustomCategoryPropertyId=");
		sb.append(CustomCategoryPropertyId);
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
		sb.append(", vocabularyId=");
		sb.append(vocabularyId);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", text=");
		sb.append(text);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CustomCategoryProperty toEntityModel() {
		CustomCategoryPropertyImpl customCategoryPropertyImpl = new CustomCategoryPropertyImpl();

		if (uuid == null) {
			customCategoryPropertyImpl.setUuid("");
		}
		else {
			customCategoryPropertyImpl.setUuid(uuid);
		}

		customCategoryPropertyImpl.setCustomCategoryPropertyId(CustomCategoryPropertyId);
		customCategoryPropertyImpl.setGroupId(groupId);
		customCategoryPropertyImpl.setCompanyId(companyId);
		customCategoryPropertyImpl.setUserId(userId);

		if (userName == null) {
			customCategoryPropertyImpl.setUserName("");
		}
		else {
			customCategoryPropertyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			customCategoryPropertyImpl.setCreateDate(null);
		}
		else {
			customCategoryPropertyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			customCategoryPropertyImpl.setModifiedDate(null);
		}
		else {
			customCategoryPropertyImpl.setModifiedDate(new Date(modifiedDate));
		}

		customCategoryPropertyImpl.setVocabularyId(vocabularyId);
		customCategoryPropertyImpl.setCategoryId(categoryId);

		if (key == null) {
			customCategoryPropertyImpl.setKey("");
		}
		else {
			customCategoryPropertyImpl.setKey(key);
		}

		if (text == null) {
			customCategoryPropertyImpl.setText("");
		}
		else {
			customCategoryPropertyImpl.setText(text);
		}

		customCategoryPropertyImpl.resetOriginalValues();

		return customCategoryPropertyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CustomCategoryPropertyId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		vocabularyId = objectInput.readLong();

		categoryId = objectInput.readLong();
		key = objectInput.readUTF();
		text = objectInput.readUTF();
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

		objectOutput.writeLong(CustomCategoryPropertyId);

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

		objectOutput.writeLong(vocabularyId);

		objectOutput.writeLong(categoryId);

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (text == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(text);
		}
	}

	public String uuid;
	public long CustomCategoryPropertyId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long vocabularyId;
	public long categoryId;
	public String key;
	public String text;
}