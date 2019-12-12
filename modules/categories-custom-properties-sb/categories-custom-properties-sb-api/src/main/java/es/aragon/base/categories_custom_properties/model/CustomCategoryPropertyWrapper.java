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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CustomCategoryProperty}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryProperty
 * @generated
 */
@ProviderType
public class CustomCategoryPropertyWrapper implements CustomCategoryProperty,
	ModelWrapper<CustomCategoryProperty> {
	public CustomCategoryPropertyWrapper(
		CustomCategoryProperty customCategoryProperty) {
		_customCategoryProperty = customCategoryProperty;
	}

	@Override
	public Class<?> getModelClass() {
		return CustomCategoryProperty.class;
	}

	@Override
	public String getModelClassName() {
		return CustomCategoryProperty.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("CustomCategoryPropertyId", getCustomCategoryPropertyId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("vocabularyId", getVocabularyId());
		attributes.put("categoryId", getCategoryId());
		attributes.put("key", getKey());
		attributes.put("text", getText());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long CustomCategoryPropertyId = (Long)attributes.get(
				"CustomCategoryPropertyId");

		if (CustomCategoryPropertyId != null) {
			setCustomCategoryPropertyId(CustomCategoryPropertyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long vocabularyId = (Long)attributes.get("vocabularyId");

		if (vocabularyId != null) {
			setVocabularyId(vocabularyId);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}
	}

	@Override
	public Object clone() {
		return new CustomCategoryPropertyWrapper((CustomCategoryProperty)_customCategoryProperty.clone());
	}

	@Override
	public int compareTo(CustomCategoryProperty customCategoryProperty) {
		return _customCategoryProperty.compareTo(customCategoryProperty);
	}

	/**
	* Returns the category ID of this custom category property.
	*
	* @return the category ID of this custom category property
	*/
	@Override
	public long getCategoryId() {
		return _customCategoryProperty.getCategoryId();
	}

	/**
	* Returns the company ID of this custom category property.
	*
	* @return the company ID of this custom category property
	*/
	@Override
	public long getCompanyId() {
		return _customCategoryProperty.getCompanyId();
	}

	/**
	* Returns the create date of this custom category property.
	*
	* @return the create date of this custom category property
	*/
	@Override
	public Date getCreateDate() {
		return _customCategoryProperty.getCreateDate();
	}

	/**
	* Returns the custom category property ID of this custom category property.
	*
	* @return the custom category property ID of this custom category property
	*/
	@Override
	public long getCustomCategoryPropertyId() {
		return _customCategoryProperty.getCustomCategoryPropertyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _customCategoryProperty.getExpandoBridge();
	}

	/**
	* Returns the group ID of this custom category property.
	*
	* @return the group ID of this custom category property
	*/
	@Override
	public long getGroupId() {
		return _customCategoryProperty.getGroupId();
	}

	/**
	* Returns the key of this custom category property.
	*
	* @return the key of this custom category property
	*/
	@Override
	public String getKey() {
		return _customCategoryProperty.getKey();
	}

	/**
	* Returns the modified date of this custom category property.
	*
	* @return the modified date of this custom category property
	*/
	@Override
	public Date getModifiedDate() {
		return _customCategoryProperty.getModifiedDate();
	}

	/**
	* Returns the primary key of this custom category property.
	*
	* @return the primary key of this custom category property
	*/
	@Override
	public long getPrimaryKey() {
		return _customCategoryProperty.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customCategoryProperty.getPrimaryKeyObj();
	}

	/**
	* Returns the text of this custom category property.
	*
	* @return the text of this custom category property
	*/
	@Override
	public String getText() {
		return _customCategoryProperty.getText();
	}

	/**
	* Returns the user ID of this custom category property.
	*
	* @return the user ID of this custom category property
	*/
	@Override
	public long getUserId() {
		return _customCategoryProperty.getUserId();
	}

	/**
	* Returns the user name of this custom category property.
	*
	* @return the user name of this custom category property
	*/
	@Override
	public String getUserName() {
		return _customCategoryProperty.getUserName();
	}

	/**
	* Returns the user uuid of this custom category property.
	*
	* @return the user uuid of this custom category property
	*/
	@Override
	public String getUserUuid() {
		return _customCategoryProperty.getUserUuid();
	}

	/**
	* Returns the uuid of this custom category property.
	*
	* @return the uuid of this custom category property
	*/
	@Override
	public String getUuid() {
		return _customCategoryProperty.getUuid();
	}

	/**
	* Returns the vocabulary ID of this custom category property.
	*
	* @return the vocabulary ID of this custom category property
	*/
	@Override
	public long getVocabularyId() {
		return _customCategoryProperty.getVocabularyId();
	}

	@Override
	public int hashCode() {
		return _customCategoryProperty.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _customCategoryProperty.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _customCategoryProperty.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _customCategoryProperty.isNew();
	}

	@Override
	public void persist() {
		_customCategoryProperty.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_customCategoryProperty.setCachedModel(cachedModel);
	}

	/**
	* Sets the category ID of this custom category property.
	*
	* @param categoryId the category ID of this custom category property
	*/
	@Override
	public void setCategoryId(long categoryId) {
		_customCategoryProperty.setCategoryId(categoryId);
	}

	/**
	* Sets the company ID of this custom category property.
	*
	* @param companyId the company ID of this custom category property
	*/
	@Override
	public void setCompanyId(long companyId) {
		_customCategoryProperty.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this custom category property.
	*
	* @param createDate the create date of this custom category property
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_customCategoryProperty.setCreateDate(createDate);
	}

	/**
	* Sets the custom category property ID of this custom category property.
	*
	* @param CustomCategoryPropertyId the custom category property ID of this custom category property
	*/
	@Override
	public void setCustomCategoryPropertyId(long CustomCategoryPropertyId) {
		_customCategoryProperty.setCustomCategoryPropertyId(CustomCategoryPropertyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_customCategoryProperty.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_customCategoryProperty.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_customCategoryProperty.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this custom category property.
	*
	* @param groupId the group ID of this custom category property
	*/
	@Override
	public void setGroupId(long groupId) {
		_customCategoryProperty.setGroupId(groupId);
	}

	/**
	* Sets the key of this custom category property.
	*
	* @param key the key of this custom category property
	*/
	@Override
	public void setKey(String key) {
		_customCategoryProperty.setKey(key);
	}

	/**
	* Sets the modified date of this custom category property.
	*
	* @param modifiedDate the modified date of this custom category property
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_customCategoryProperty.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_customCategoryProperty.setNew(n);
	}

	/**
	* Sets the primary key of this custom category property.
	*
	* @param primaryKey the primary key of this custom category property
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_customCategoryProperty.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_customCategoryProperty.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the text of this custom category property.
	*
	* @param text the text of this custom category property
	*/
	@Override
	public void setText(String text) {
		_customCategoryProperty.setText(text);
	}

	/**
	* Sets the user ID of this custom category property.
	*
	* @param userId the user ID of this custom category property
	*/
	@Override
	public void setUserId(long userId) {
		_customCategoryProperty.setUserId(userId);
	}

	/**
	* Sets the user name of this custom category property.
	*
	* @param userName the user name of this custom category property
	*/
	@Override
	public void setUserName(String userName) {
		_customCategoryProperty.setUserName(userName);
	}

	/**
	* Sets the user uuid of this custom category property.
	*
	* @param userUuid the user uuid of this custom category property
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_customCategoryProperty.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this custom category property.
	*
	* @param uuid the uuid of this custom category property
	*/
	@Override
	public void setUuid(String uuid) {
		_customCategoryProperty.setUuid(uuid);
	}

	/**
	* Sets the vocabulary ID of this custom category property.
	*
	* @param vocabularyId the vocabulary ID of this custom category property
	*/
	@Override
	public void setVocabularyId(long vocabularyId) {
		_customCategoryProperty.setVocabularyId(vocabularyId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CustomCategoryProperty> toCacheModel() {
		return _customCategoryProperty.toCacheModel();
	}

	@Override
	public CustomCategoryProperty toEscapedModel() {
		return new CustomCategoryPropertyWrapper(_customCategoryProperty.toEscapedModel());
	}

	@Override
	public String toString() {
		return _customCategoryProperty.toString();
	}

	@Override
	public CustomCategoryProperty toUnescapedModel() {
		return new CustomCategoryPropertyWrapper(_customCategoryProperty.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _customCategoryProperty.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomCategoryPropertyWrapper)) {
			return false;
		}

		CustomCategoryPropertyWrapper customCategoryPropertyWrapper = (CustomCategoryPropertyWrapper)obj;

		if (Objects.equals(_customCategoryProperty,
					customCategoryPropertyWrapper._customCategoryProperty)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _customCategoryProperty.getStagedModelType();
	}

	@Override
	public CustomCategoryProperty getWrappedModel() {
		return _customCategoryProperty;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _customCategoryProperty.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _customCategoryProperty.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_customCategoryProperty.resetOriginalValues();
	}

	private final CustomCategoryProperty _customCategoryProperty;
}