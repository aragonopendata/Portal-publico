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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.model.CustomCategoryPropertyModel;
import es.aragon.base.categories_custom_properties.model.CustomCategoryPropertySoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CustomCategoryProperty service. Represents a row in the &quot;EAB_CCP_CustomCategoryProperty&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CustomCategoryPropertyModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CustomCategoryPropertyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyImpl
 * @see CustomCategoryProperty
 * @see CustomCategoryPropertyModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CustomCategoryPropertyModelImpl extends BaseModelImpl<CustomCategoryProperty>
	implements CustomCategoryPropertyModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a custom category property model instance should use the {@link CustomCategoryProperty} interface instead.
	 */
	public static final String TABLE_NAME = "EAB_CCP_CustomCategoryProperty";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "CustomCategoryPropertyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "vocabularyId", Types.BIGINT },
			{ "categoryId", Types.BIGINT },
			{ "key_", Types.VARCHAR },
			{ "text_", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CustomCategoryPropertyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("vocabularyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("categoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("text_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table EAB_CCP_CustomCategoryProperty (uuid_ VARCHAR(75) null,CustomCategoryPropertyId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,vocabularyId LONG,categoryId LONG,key_ VARCHAR(75) null,text_ STRING null)";
	public static final String TABLE_SQL_DROP = "drop table EAB_CCP_CustomCategoryProperty";
	public static final String ORDER_BY_JPQL = " ORDER BY customCategoryProperty.key ASC";
	public static final String ORDER_BY_SQL = " ORDER BY EAB_CCP_CustomCategoryProperty.key_ ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(es.aragon.base.categories_custom_properties.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(es.aragon.base.categories_custom_properties.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(es.aragon.base.categories_custom_properties.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"),
			true);
	public static final long CATEGORYID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long KEY_COLUMN_BITMASK = 8L;
	public static final long TEXT_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;
	public static final long VOCABULARYID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CustomCategoryProperty toModel(
		CustomCategoryPropertySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CustomCategoryProperty model = new CustomCategoryPropertyImpl();

		model.setUuid(soapModel.getUuid());
		model.setCustomCategoryPropertyId(soapModel.getCustomCategoryPropertyId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setVocabularyId(soapModel.getVocabularyId());
		model.setCategoryId(soapModel.getCategoryId());
		model.setKey(soapModel.getKey());
		model.setText(soapModel.getText());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CustomCategoryProperty> toModels(
		CustomCategoryPropertySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CustomCategoryProperty> models = new ArrayList<CustomCategoryProperty>(soapModels.length);

		for (CustomCategoryPropertySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(es.aragon.base.categories_custom_properties.service.util.ServiceProps.get(
				"lock.expiration.time.es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"));

	public CustomCategoryPropertyModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CustomCategoryPropertyId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCustomCategoryPropertyId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CustomCategoryPropertyId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCustomCategoryPropertyId() {
		return _CustomCategoryPropertyId;
	}

	@Override
	public void setCustomCategoryPropertyId(long CustomCategoryPropertyId) {
		_CustomCategoryPropertyId = CustomCategoryPropertyId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getVocabularyId() {
		return _vocabularyId;
	}

	@Override
	public void setVocabularyId(long vocabularyId) {
		_columnBitmask |= VOCABULARYID_COLUMN_BITMASK;

		if (!_setOriginalVocabularyId) {
			_setOriginalVocabularyId = true;

			_originalVocabularyId = _vocabularyId;
		}

		_vocabularyId = vocabularyId;
	}

	public long getOriginalVocabularyId() {
		return _originalVocabularyId;
	}

	@JSON
	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_columnBitmask |= CATEGORYID_COLUMN_BITMASK;

		if (!_setOriginalCategoryId) {
			_setOriginalCategoryId = true;

			_originalCategoryId = _categoryId;
		}

		_categoryId = categoryId;
	}

	public long getOriginalCategoryId() {
		return _originalCategoryId;
	}

	@JSON
	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		_columnBitmask = -1L;

		if (_originalKey == null) {
			_originalKey = _key;
		}

		_key = key;
	}

	public String getOriginalKey() {
		return GetterUtil.getString(_originalKey);
	}

	@JSON
	@Override
	public String getText() {
		if (_text == null) {
			return "";
		}
		else {
			return _text;
		}
	}

	@Override
	public void setText(String text) {
		_columnBitmask |= TEXT_COLUMN_BITMASK;

		if (_originalText == null) {
			_originalText = _text;
		}

		_text = text;
	}

	public String getOriginalText() {
		return GetterUtil.getString(_originalText);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				CustomCategoryProperty.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CustomCategoryProperty.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CustomCategoryProperty toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CustomCategoryProperty)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CustomCategoryPropertyImpl customCategoryPropertyImpl = new CustomCategoryPropertyImpl();

		customCategoryPropertyImpl.setUuid(getUuid());
		customCategoryPropertyImpl.setCustomCategoryPropertyId(getCustomCategoryPropertyId());
		customCategoryPropertyImpl.setGroupId(getGroupId());
		customCategoryPropertyImpl.setCompanyId(getCompanyId());
		customCategoryPropertyImpl.setUserId(getUserId());
		customCategoryPropertyImpl.setUserName(getUserName());
		customCategoryPropertyImpl.setCreateDate(getCreateDate());
		customCategoryPropertyImpl.setModifiedDate(getModifiedDate());
		customCategoryPropertyImpl.setVocabularyId(getVocabularyId());
		customCategoryPropertyImpl.setCategoryId(getCategoryId());
		customCategoryPropertyImpl.setKey(getKey());
		customCategoryPropertyImpl.setText(getText());

		customCategoryPropertyImpl.resetOriginalValues();

		return customCategoryPropertyImpl;
	}

	@Override
	public int compareTo(CustomCategoryProperty customCategoryProperty) {
		int value = 0;

		value = getKey().compareTo(customCategoryProperty.getKey());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomCategoryProperty)) {
			return false;
		}

		CustomCategoryProperty customCategoryProperty = (CustomCategoryProperty)obj;

		long primaryKey = customCategoryProperty.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CustomCategoryPropertyModelImpl customCategoryPropertyModelImpl = this;

		customCategoryPropertyModelImpl._originalUuid = customCategoryPropertyModelImpl._uuid;

		customCategoryPropertyModelImpl._originalGroupId = customCategoryPropertyModelImpl._groupId;

		customCategoryPropertyModelImpl._setOriginalGroupId = false;

		customCategoryPropertyModelImpl._originalCompanyId = customCategoryPropertyModelImpl._companyId;

		customCategoryPropertyModelImpl._setOriginalCompanyId = false;

		customCategoryPropertyModelImpl._setModifiedDate = false;

		customCategoryPropertyModelImpl._originalVocabularyId = customCategoryPropertyModelImpl._vocabularyId;

		customCategoryPropertyModelImpl._setOriginalVocabularyId = false;

		customCategoryPropertyModelImpl._originalCategoryId = customCategoryPropertyModelImpl._categoryId;

		customCategoryPropertyModelImpl._setOriginalCategoryId = false;

		customCategoryPropertyModelImpl._originalKey = customCategoryPropertyModelImpl._key;

		customCategoryPropertyModelImpl._originalText = customCategoryPropertyModelImpl._text;

		customCategoryPropertyModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CustomCategoryProperty> toCacheModel() {
		CustomCategoryPropertyCacheModel customCategoryPropertyCacheModel = new CustomCategoryPropertyCacheModel();

		customCategoryPropertyCacheModel.uuid = getUuid();

		String uuid = customCategoryPropertyCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			customCategoryPropertyCacheModel.uuid = null;
		}

		customCategoryPropertyCacheModel.CustomCategoryPropertyId = getCustomCategoryPropertyId();

		customCategoryPropertyCacheModel.groupId = getGroupId();

		customCategoryPropertyCacheModel.companyId = getCompanyId();

		customCategoryPropertyCacheModel.userId = getUserId();

		customCategoryPropertyCacheModel.userName = getUserName();

		String userName = customCategoryPropertyCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			customCategoryPropertyCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			customCategoryPropertyCacheModel.createDate = createDate.getTime();
		}
		else {
			customCategoryPropertyCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			customCategoryPropertyCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			customCategoryPropertyCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		customCategoryPropertyCacheModel.vocabularyId = getVocabularyId();

		customCategoryPropertyCacheModel.categoryId = getCategoryId();

		customCategoryPropertyCacheModel.key = getKey();

		String key = customCategoryPropertyCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			customCategoryPropertyCacheModel.key = null;
		}

		customCategoryPropertyCacheModel.text = getText();

		String text = customCategoryPropertyCacheModel.text;

		if ((text != null) && (text.length() == 0)) {
			customCategoryPropertyCacheModel.text = null;
		}

		return customCategoryPropertyCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", CustomCategoryPropertyId=");
		sb.append(getCustomCategoryPropertyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", vocabularyId=");
		sb.append(getVocabularyId());
		sb.append(", categoryId=");
		sb.append(getCategoryId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", text=");
		sb.append(getText());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append(
			"es.aragon.base.categories_custom_properties.model.CustomCategoryProperty");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CustomCategoryPropertyId</column-name><column-value><![CDATA[");
		sb.append(getCustomCategoryPropertyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>vocabularyId</column-name><column-value><![CDATA[");
		sb.append(getVocabularyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>categoryId</column-name><column-value><![CDATA[");
		sb.append(getCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>text</column-name><column-value><![CDATA[");
		sb.append(getText());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CustomCategoryProperty.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CustomCategoryProperty.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _CustomCategoryPropertyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _vocabularyId;
	private long _originalVocabularyId;
	private boolean _setOriginalVocabularyId;
	private long _categoryId;
	private long _originalCategoryId;
	private boolean _setOriginalCategoryId;
	private String _key;
	private String _originalKey;
	private String _text;
	private String _originalText;
	private long _columnBitmask;
	private CustomCategoryProperty _escapedModel;
}