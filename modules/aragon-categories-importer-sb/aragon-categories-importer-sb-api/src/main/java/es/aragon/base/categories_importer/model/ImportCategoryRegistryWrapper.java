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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ImportCategoryRegistry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistry
 * @generated
 */
@ProviderType
public class ImportCategoryRegistryWrapper implements ImportCategoryRegistry,
	ModelWrapper<ImportCategoryRegistry> {
	public ImportCategoryRegistryWrapper(
		ImportCategoryRegistry importCategoryRegistry) {
		_importCategoryRegistry = importCategoryRegistry;
	}

	@Override
	public Class<?> getModelClass() {
		return ImportCategoryRegistry.class;
	}

	@Override
	public String getModelClassName() {
		return ImportCategoryRegistry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("importCategoryRegistryId", getImportCategoryRegistryId());
		attributes.put("vocabularyId", getVocabularyId());
		attributes.put("categoryId", getCategoryId());
		attributes.put("importDate", getImportDate());
		attributes.put("type", getType());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long importCategoryRegistryId = (Long)attributes.get(
				"importCategoryRegistryId");

		if (importCategoryRegistryId != null) {
			setImportCategoryRegistryId(importCategoryRegistryId);
		}

		Long vocabularyId = (Long)attributes.get("vocabularyId");

		if (vocabularyId != null) {
			setVocabularyId(vocabularyId);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		Date importDate = (Date)attributes.get("importDate");

		if (importDate != null) {
			setImportDate(importDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	@Override
	public Object clone() {
		return new ImportCategoryRegistryWrapper((ImportCategoryRegistry)_importCategoryRegistry.clone());
	}

	@Override
	public int compareTo(ImportCategoryRegistry importCategoryRegistry) {
		return _importCategoryRegistry.compareTo(importCategoryRegistry);
	}

	/**
	* Returns the category ID of this import category registry.
	*
	* @return the category ID of this import category registry
	*/
	@Override
	public long getCategoryId() {
		return _importCategoryRegistry.getCategoryId();
	}

	/**
	* Returns the comment of this import category registry.
	*
	* @return the comment of this import category registry
	*/
	@Override
	public String getComment() {
		return _importCategoryRegistry.getComment();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _importCategoryRegistry.getExpandoBridge();
	}

	/**
	* Returns the import category registry ID of this import category registry.
	*
	* @return the import category registry ID of this import category registry
	*/
	@Override
	public long getImportCategoryRegistryId() {
		return _importCategoryRegistry.getImportCategoryRegistryId();
	}

	/**
	* Returns the import date of this import category registry.
	*
	* @return the import date of this import category registry
	*/
	@Override
	public Date getImportDate() {
		return _importCategoryRegistry.getImportDate();
	}

	/**
	* Returns the primary key of this import category registry.
	*
	* @return the primary key of this import category registry
	*/
	@Override
	public long getPrimaryKey() {
		return _importCategoryRegistry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _importCategoryRegistry.getPrimaryKeyObj();
	}

	/**
	* Returns the type of this import category registry.
	*
	* @return the type of this import category registry
	*/
	@Override
	public String getType() {
		return _importCategoryRegistry.getType();
	}

	/**
	* Returns the uuid of this import category registry.
	*
	* @return the uuid of this import category registry
	*/
	@Override
	public String getUuid() {
		return _importCategoryRegistry.getUuid();
	}

	/**
	* Returns the vocabulary ID of this import category registry.
	*
	* @return the vocabulary ID of this import category registry
	*/
	@Override
	public long getVocabularyId() {
		return _importCategoryRegistry.getVocabularyId();
	}

	@Override
	public int hashCode() {
		return _importCategoryRegistry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _importCategoryRegistry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _importCategoryRegistry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _importCategoryRegistry.isNew();
	}

	@Override
	public void persist() {
		_importCategoryRegistry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_importCategoryRegistry.setCachedModel(cachedModel);
	}

	/**
	* Sets the category ID of this import category registry.
	*
	* @param categoryId the category ID of this import category registry
	*/
	@Override
	public void setCategoryId(long categoryId) {
		_importCategoryRegistry.setCategoryId(categoryId);
	}

	/**
	* Sets the comment of this import category registry.
	*
	* @param comment the comment of this import category registry
	*/
	@Override
	public void setComment(String comment) {
		_importCategoryRegistry.setComment(comment);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_importCategoryRegistry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_importCategoryRegistry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_importCategoryRegistry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the import category registry ID of this import category registry.
	*
	* @param importCategoryRegistryId the import category registry ID of this import category registry
	*/
	@Override
	public void setImportCategoryRegistryId(long importCategoryRegistryId) {
		_importCategoryRegistry.setImportCategoryRegistryId(importCategoryRegistryId);
	}

	/**
	* Sets the import date of this import category registry.
	*
	* @param importDate the import date of this import category registry
	*/
	@Override
	public void setImportDate(Date importDate) {
		_importCategoryRegistry.setImportDate(importDate);
	}

	@Override
	public void setNew(boolean n) {
		_importCategoryRegistry.setNew(n);
	}

	/**
	* Sets the primary key of this import category registry.
	*
	* @param primaryKey the primary key of this import category registry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_importCategoryRegistry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_importCategoryRegistry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this import category registry.
	*
	* @param type the type of this import category registry
	*/
	@Override
	public void setType(String type) {
		_importCategoryRegistry.setType(type);
	}

	/**
	* Sets the uuid of this import category registry.
	*
	* @param uuid the uuid of this import category registry
	*/
	@Override
	public void setUuid(String uuid) {
		_importCategoryRegistry.setUuid(uuid);
	}

	/**
	* Sets the vocabulary ID of this import category registry.
	*
	* @param vocabularyId the vocabulary ID of this import category registry
	*/
	@Override
	public void setVocabularyId(long vocabularyId) {
		_importCategoryRegistry.setVocabularyId(vocabularyId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImportCategoryRegistry> toCacheModel() {
		return _importCategoryRegistry.toCacheModel();
	}

	@Override
	public ImportCategoryRegistry toEscapedModel() {
		return new ImportCategoryRegistryWrapper(_importCategoryRegistry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _importCategoryRegistry.toString();
	}

	@Override
	public ImportCategoryRegistry toUnescapedModel() {
		return new ImportCategoryRegistryWrapper(_importCategoryRegistry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _importCategoryRegistry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportCategoryRegistryWrapper)) {
			return false;
		}

		ImportCategoryRegistryWrapper importCategoryRegistryWrapper = (ImportCategoryRegistryWrapper)obj;

		if (Objects.equals(_importCategoryRegistry,
					importCategoryRegistryWrapper._importCategoryRegistry)) {
			return true;
		}

		return false;
	}

	@Override
	public ImportCategoryRegistry getWrappedModel() {
		return _importCategoryRegistry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _importCategoryRegistry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _importCategoryRegistry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_importCategoryRegistry.resetOriginalValues();
	}

	private final ImportCategoryRegistry _importCategoryRegistry;
}