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

package es.aragon.base.categories_importer.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.categories_importer.model.ImportCategoryRegistry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImportCategoryRegistry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistry
 * @generated
 */
@ProviderType
public class ImportCategoryRegistryCacheModel implements CacheModel<ImportCategoryRegistry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportCategoryRegistryCacheModel)) {
			return false;
		}

		ImportCategoryRegistryCacheModel importCategoryRegistryCacheModel = (ImportCategoryRegistryCacheModel)obj;

		if (importCategoryRegistryId == importCategoryRegistryCacheModel.importCategoryRegistryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, importCategoryRegistryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", importCategoryRegistryId=");
		sb.append(importCategoryRegistryId);
		sb.append(", vocabularyId=");
		sb.append(vocabularyId);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", importDate=");
		sb.append(importDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImportCategoryRegistry toEntityModel() {
		ImportCategoryRegistryImpl importCategoryRegistryImpl = new ImportCategoryRegistryImpl();

		if (uuid == null) {
			importCategoryRegistryImpl.setUuid("");
		}
		else {
			importCategoryRegistryImpl.setUuid(uuid);
		}

		importCategoryRegistryImpl.setImportCategoryRegistryId(importCategoryRegistryId);
		importCategoryRegistryImpl.setVocabularyId(vocabularyId);
		importCategoryRegistryImpl.setCategoryId(categoryId);

		if (importDate == Long.MIN_VALUE) {
			importCategoryRegistryImpl.setImportDate(null);
		}
		else {
			importCategoryRegistryImpl.setImportDate(new Date(importDate));
		}

		if (type == null) {
			importCategoryRegistryImpl.setType("");
		}
		else {
			importCategoryRegistryImpl.setType(type);
		}

		if (comment == null) {
			importCategoryRegistryImpl.setComment("");
		}
		else {
			importCategoryRegistryImpl.setComment(comment);
		}

		importCategoryRegistryImpl.resetOriginalValues();

		return importCategoryRegistryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		importCategoryRegistryId = objectInput.readLong();

		vocabularyId = objectInput.readLong();

		categoryId = objectInput.readLong();
		importDate = objectInput.readLong();
		type = objectInput.readUTF();
		comment = objectInput.readUTF();
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

		objectOutput.writeLong(importCategoryRegistryId);

		objectOutput.writeLong(vocabularyId);

		objectOutput.writeLong(categoryId);
		objectOutput.writeLong(importDate);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}
	}

	public String uuid;
	public long importCategoryRegistryId;
	public long vocabularyId;
	public long categoryId;
	public long importDate;
	public String type;
	public String comment;
}