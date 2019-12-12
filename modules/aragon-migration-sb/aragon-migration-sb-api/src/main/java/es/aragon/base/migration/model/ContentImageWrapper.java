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
 * This class is a wrapper for {@link ContentImage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentImage
 * @generated
 */
@ProviderType
public class ContentImageWrapper implements ContentImage,
	ModelWrapper<ContentImage> {
	public ContentImageWrapper(ContentImage contentImage) {
		_contentImage = contentImage;
	}

	@Override
	public Class<?> getModelClass() {
		return ContentImage.class;
	}

	@Override
	public String getModelClassName() {
		return ContentImage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contentImageId", getContentImageId());
		attributes.put("contentOriginId", getContentOriginId());
		attributes.put("url", getUrl());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contentImageId = (Long)attributes.get("contentImageId");

		if (contentImageId != null) {
			setContentImageId(contentImageId);
		}

		Long contentOriginId = (Long)attributes.get("contentOriginId");

		if (contentOriginId != null) {
			setContentOriginId(contentOriginId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}
	}

	@Override
	public Object clone() {
		return new ContentImageWrapper((ContentImage)_contentImage.clone());
	}

	@Override
	public int compareTo(ContentImage contentImage) {
		return _contentImage.compareTo(contentImage);
	}

	/**
	* Returns the content image ID of this content image.
	*
	* @return the content image ID of this content image
	*/
	@Override
	public long getContentImageId() {
		return _contentImage.getContentImageId();
	}

	/**
	* Returns the content origin ID of this content image.
	*
	* @return the content origin ID of this content image
	*/
	@Override
	public long getContentOriginId() {
		return _contentImage.getContentOriginId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contentImage.getExpandoBridge();
	}

	/**
	* Returns the primary key of this content image.
	*
	* @return the primary key of this content image
	*/
	@Override
	public long getPrimaryKey() {
		return _contentImage.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentImage.getPrimaryKeyObj();
	}

	/**
	* Returns the url of this content image.
	*
	* @return the url of this content image
	*/
	@Override
	public String getUrl() {
		return _contentImage.getUrl();
	}

	/**
	* Returns the uuid of this content image.
	*
	* @return the uuid of this content image
	*/
	@Override
	public String getUuid() {
		return _contentImage.getUuid();
	}

	@Override
	public int hashCode() {
		return _contentImage.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _contentImage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contentImage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contentImage.isNew();
	}

	@Override
	public void persist() {
		_contentImage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contentImage.setCachedModel(cachedModel);
	}

	/**
	* Sets the content image ID of this content image.
	*
	* @param contentImageId the content image ID of this content image
	*/
	@Override
	public void setContentImageId(long contentImageId) {
		_contentImage.setContentImageId(contentImageId);
	}

	/**
	* Sets the content origin ID of this content image.
	*
	* @param contentOriginId the content origin ID of this content image
	*/
	@Override
	public void setContentOriginId(long contentOriginId) {
		_contentImage.setContentOriginId(contentOriginId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contentImage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contentImage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contentImage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_contentImage.setNew(n);
	}

	/**
	* Sets the primary key of this content image.
	*
	* @param primaryKey the primary key of this content image
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contentImage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contentImage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the url of this content image.
	*
	* @param url the url of this content image
	*/
	@Override
	public void setUrl(String url) {
		_contentImage.setUrl(url);
	}

	/**
	* Sets the uuid of this content image.
	*
	* @param uuid the uuid of this content image
	*/
	@Override
	public void setUuid(String uuid) {
		_contentImage.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContentImage> toCacheModel() {
		return _contentImage.toCacheModel();
	}

	@Override
	public ContentImage toEscapedModel() {
		return new ContentImageWrapper(_contentImage.toEscapedModel());
	}

	@Override
	public String toString() {
		return _contentImage.toString();
	}

	@Override
	public ContentImage toUnescapedModel() {
		return new ContentImageWrapper(_contentImage.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _contentImage.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentImageWrapper)) {
			return false;
		}

		ContentImageWrapper contentImageWrapper = (ContentImageWrapper)obj;

		if (Objects.equals(_contentImage, contentImageWrapper._contentImage)) {
			return true;
		}

		return false;
	}

	@Override
	public ContentImage getWrappedModel() {
		return _contentImage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contentImage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contentImage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contentImage.resetOriginalValues();
	}

	private final ContentImage _contentImage;
}