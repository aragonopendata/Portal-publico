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

package es.aragon.base.categories_custom_properties.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.categories_custom_properties.service.http.CustomCategoryPropertyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.categories_custom_properties.service.http.CustomCategoryPropertyServiceSoap
 * @generated
 */
@ProviderType
public class CustomCategoryPropertySoap implements Serializable {
	public static CustomCategoryPropertySoap toSoapModel(
		CustomCategoryProperty model) {
		CustomCategoryPropertySoap soapModel = new CustomCategoryPropertySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCustomCategoryPropertyId(model.getCustomCategoryPropertyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setVocabularyId(model.getVocabularyId());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setKey(model.getKey());
		soapModel.setText(model.getText());

		return soapModel;
	}

	public static CustomCategoryPropertySoap[] toSoapModels(
		CustomCategoryProperty[] models) {
		CustomCategoryPropertySoap[] soapModels = new CustomCategoryPropertySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CustomCategoryPropertySoap[][] toSoapModels(
		CustomCategoryProperty[][] models) {
		CustomCategoryPropertySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CustomCategoryPropertySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CustomCategoryPropertySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CustomCategoryPropertySoap[] toSoapModels(
		List<CustomCategoryProperty> models) {
		List<CustomCategoryPropertySoap> soapModels = new ArrayList<CustomCategoryPropertySoap>(models.size());

		for (CustomCategoryProperty model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CustomCategoryPropertySoap[soapModels.size()]);
	}

	public CustomCategoryPropertySoap() {
	}

	public long getPrimaryKey() {
		return _CustomCategoryPropertyId;
	}

	public void setPrimaryKey(long pk) {
		setCustomCategoryPropertyId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCustomCategoryPropertyId() {
		return _CustomCategoryPropertyId;
	}

	public void setCustomCategoryPropertyId(long CustomCategoryPropertyId) {
		_CustomCategoryPropertyId = CustomCategoryPropertyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	private String _uuid;
	private long _CustomCategoryPropertyId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _vocabularyId;
	private long _categoryId;
	private String _key;
	private String _text;
}