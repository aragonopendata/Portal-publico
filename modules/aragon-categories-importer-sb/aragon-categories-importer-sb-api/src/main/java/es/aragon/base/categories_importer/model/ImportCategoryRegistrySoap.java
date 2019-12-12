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

package es.aragon.base.categories_importer.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.categories_importer.service.http.ImportCategoryRegistryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.categories_importer.service.http.ImportCategoryRegistryServiceSoap
 * @generated
 */
@ProviderType
public class ImportCategoryRegistrySoap implements Serializable {
	public static ImportCategoryRegistrySoap toSoapModel(
		ImportCategoryRegistry model) {
		ImportCategoryRegistrySoap soapModel = new ImportCategoryRegistrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setImportCategoryRegistryId(model.getImportCategoryRegistryId());
		soapModel.setVocabularyId(model.getVocabularyId());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setImportDate(model.getImportDate());
		soapModel.setType(model.getType());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static ImportCategoryRegistrySoap[] toSoapModels(
		ImportCategoryRegistry[] models) {
		ImportCategoryRegistrySoap[] soapModels = new ImportCategoryRegistrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImportCategoryRegistrySoap[][] toSoapModels(
		ImportCategoryRegistry[][] models) {
		ImportCategoryRegistrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImportCategoryRegistrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImportCategoryRegistrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImportCategoryRegistrySoap[] toSoapModels(
		List<ImportCategoryRegistry> models) {
		List<ImportCategoryRegistrySoap> soapModels = new ArrayList<ImportCategoryRegistrySoap>(models.size());

		for (ImportCategoryRegistry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImportCategoryRegistrySoap[soapModels.size()]);
	}

	public ImportCategoryRegistrySoap() {
	}

	public long getPrimaryKey() {
		return _importCategoryRegistryId;
	}

	public void setPrimaryKey(long pk) {
		setImportCategoryRegistryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getImportCategoryRegistryId() {
		return _importCategoryRegistryId;
	}

	public void setImportCategoryRegistryId(long importCategoryRegistryId) {
		_importCategoryRegistryId = importCategoryRegistryId;
	}

	public long getVocabularyId() {
		return _vocabularyId;
	}

	public void setVocabularyId(long vocabularyId) {
		_vocabularyId = vocabularyId;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public Date getImportDate() {
		return _importDate;
	}

	public void setImportDate(Date importDate) {
		_importDate = importDate;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	private String _uuid;
	private long _importCategoryRegistryId;
	private long _vocabularyId;
	private long _categoryId;
	private Date _importDate;
	private String _type;
	private String _comment;
}