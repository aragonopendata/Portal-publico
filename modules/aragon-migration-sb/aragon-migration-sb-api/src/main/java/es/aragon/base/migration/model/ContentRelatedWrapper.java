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

package es.aragon.base.migration.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ContentRelated}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentRelated
 * @generated
 */
@ProviderType
public class ContentRelatedWrapper implements ContentRelated,
	ModelWrapper<ContentRelated> {
	public ContentRelatedWrapper(ContentRelated contentRelated) {
		_contentRelated = contentRelated;
	}

	@Override
	public Class<?> getModelClass() {
		return ContentRelated.class;
	}

	@Override
	public String getModelClassName() {
		return ContentRelated.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contentRelatedId", getContentRelatedId());
		attributes.put("contentParentId", getContentParentId());
		attributes.put("contentLinkedId", getContentLinkedId());
		attributes.put("url", getUrl());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contentRelatedId = (Long)attributes.get("contentRelatedId");

		if (contentRelatedId != null) {
			setContentRelatedId(contentRelatedId);
		}

		Long contentParentId = (Long)attributes.get("contentParentId");

		if (contentParentId != null) {
			setContentParentId(contentParentId);
		}

		Long contentLinkedId = (Long)attributes.get("contentLinkedId");

		if (contentLinkedId != null) {
			setContentLinkedId(contentLinkedId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}
	}

	@Override
	public Object clone() {
		return new ContentRelatedWrapper((ContentRelated)_contentRelated.clone());
	}

	@Override
	public int compareTo(ContentRelated contentRelated) {
		return _contentRelated.compareTo(contentRelated);
	}

	/**
	* Returns the content linked ID of this content related.
	*
	* @return the content linked ID of this content related
	*/
	@Override
	public long getContentLinkedId() {
		return _contentRelated.getContentLinkedId();
	}

	/**
	* Returns the content parent ID of this content related.
	*
	* @return the content parent ID of this content related
	*/
	@Override
	public long getContentParentId() {
		return _contentRelated.getContentParentId();
	}

	/**
	* Returns the content related ID of this content related.
	*
	* @return the content related ID of this content related
	*/
	@Override
	public long getContentRelatedId() {
		return _contentRelated.getContentRelatedId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contentRelated.getExpandoBridge();
	}

	/**
	* Returns the primary key of this content related.
	*
	* @return the primary key of this content related
	*/
	@Override
	public long getPrimaryKey() {
		return _contentRelated.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentRelated.getPrimaryKeyObj();
	}

	/**
	* Returns the url of this content related.
	*
	* @return the url of this content related
	*/
	@Override
	public String getUrl() {
		return _contentRelated.getUrl();
	}

	/**
	* Returns the uuid of this content related.
	*
	* @return the uuid of this content related
	*/
	@Override
	public String getUuid() {
		return _contentRelated.getUuid();
	}

	@Override
	public int hashCode() {
		return _contentRelated.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _contentRelated.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contentRelated.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contentRelated.isNew();
	}

	@Override
	public void persist() {
		_contentRelated.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contentRelated.setCachedModel(cachedModel);
	}

	/**
	* Sets the content linked ID of this content related.
	*
	* @param contentLinkedId the content linked ID of this content related
	*/
	@Override
	public void setContentLinkedId(long contentLinkedId) {
		_contentRelated.setContentLinkedId(contentLinkedId);
	}

	/**
	* Sets the content parent ID of this content related.
	*
	* @param contentParentId the content parent ID of this content related
	*/
	@Override
	public void setContentParentId(long contentParentId) {
		_contentRelated.setContentParentId(contentParentId);
	}

	/**
	* Sets the content related ID of this content related.
	*
	* @param contentRelatedId the content related ID of this content related
	*/
	@Override
	public void setContentRelatedId(long contentRelatedId) {
		_contentRelated.setContentRelatedId(contentRelatedId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contentRelated.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contentRelated.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contentRelated.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_contentRelated.setNew(n);
	}

	/**
	* Sets the primary key of this content related.
	*
	* @param primaryKey the primary key of this content related
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contentRelated.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contentRelated.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the url of this content related.
	*
	* @param url the url of this content related
	*/
	@Override
	public void setUrl(String url) {
		_contentRelated.setUrl(url);
	}

	/**
	* Sets the uuid of this content related.
	*
	* @param uuid the uuid of this content related
	*/
	@Override
	public void setUuid(String uuid) {
		_contentRelated.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContentRelated> toCacheModel() {
		return _contentRelated.toCacheModel();
	}

	@Override
	public ContentRelated toEscapedModel() {
		return new ContentRelatedWrapper(_contentRelated.toEscapedModel());
	}

	@Override
	public String toString() {
		return _contentRelated.toString();
	}

	@Override
	public ContentRelated toUnescapedModel() {
		return new ContentRelatedWrapper(_contentRelated.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _contentRelated.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentRelatedWrapper)) {
			return false;
		}

		ContentRelatedWrapper contentRelatedWrapper = (ContentRelatedWrapper)obj;

		if (Objects.equals(_contentRelated,
					contentRelatedWrapper._contentRelated)) {
			return true;
		}

		return false;
	}

	@Override
	public ContentRelated getWrappedModel() {
		return _contentRelated;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contentRelated.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contentRelated.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contentRelated.resetOriginalValues();
	}

	private final ContentRelated _contentRelated;
}