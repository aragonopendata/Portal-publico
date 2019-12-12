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
 * This class is a wrapper for {@link ContentUrl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrl
 * @generated
 */
@ProviderType
public class ContentUrlWrapper implements ContentUrl, ModelWrapper<ContentUrl> {
	public ContentUrlWrapper(ContentUrl contentUrl) {
		_contentUrl = contentUrl;
	}

	@Override
	public Class<?> getModelClass() {
		return ContentUrl.class;
	}

	@Override
	public String getModelClassName() {
		return ContentUrl.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contentUrlId", getContentUrlId());
		attributes.put("contentOriginId", getContentOriginId());
		attributes.put("contentDestinationId", getContentDestinationId());
		attributes.put("urlOrigin", getUrlOrigin());
		attributes.put("urlDestination", getUrlDestination());
		attributes.put("className", getClassName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contentUrlId = (Long)attributes.get("contentUrlId");

		if (contentUrlId != null) {
			setContentUrlId(contentUrlId);
		}

		Long contentOriginId = (Long)attributes.get("contentOriginId");

		if (contentOriginId != null) {
			setContentOriginId(contentOriginId);
		}

		Long contentDestinationId = (Long)attributes.get("contentDestinationId");

		if (contentDestinationId != null) {
			setContentDestinationId(contentDestinationId);
		}

		String urlOrigin = (String)attributes.get("urlOrigin");

		if (urlOrigin != null) {
			setUrlOrigin(urlOrigin);
		}

		String urlDestination = (String)attributes.get("urlDestination");

		if (urlDestination != null) {
			setUrlDestination(urlDestination);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}
	}

	@Override
	public Object clone() {
		return new ContentUrlWrapper((ContentUrl)_contentUrl.clone());
	}

	@Override
	public int compareTo(ContentUrl contentUrl) {
		return _contentUrl.compareTo(contentUrl);
	}

	/**
	* Returns the class name of this content url.
	*
	* @return the class name of this content url
	*/
	@Override
	public String getClassName() {
		return _contentUrl.getClassName();
	}

	/**
	* Returns the content destination ID of this content url.
	*
	* @return the content destination ID of this content url
	*/
	@Override
	public long getContentDestinationId() {
		return _contentUrl.getContentDestinationId();
	}

	/**
	* Returns the content origin ID of this content url.
	*
	* @return the content origin ID of this content url
	*/
	@Override
	public long getContentOriginId() {
		return _contentUrl.getContentOriginId();
	}

	/**
	* Returns the content url ID of this content url.
	*
	* @return the content url ID of this content url
	*/
	@Override
	public long getContentUrlId() {
		return _contentUrl.getContentUrlId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contentUrl.getExpandoBridge();
	}

	/**
	* Returns the primary key of this content url.
	*
	* @return the primary key of this content url
	*/
	@Override
	public long getPrimaryKey() {
		return _contentUrl.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentUrl.getPrimaryKeyObj();
	}

	/**
	* Returns the url destination of this content url.
	*
	* @return the url destination of this content url
	*/
	@Override
	public String getUrlDestination() {
		return _contentUrl.getUrlDestination();
	}

	/**
	* Returns the url origin of this content url.
	*
	* @return the url origin of this content url
	*/
	@Override
	public String getUrlOrigin() {
		return _contentUrl.getUrlOrigin();
	}

	/**
	* Returns the uuid of this content url.
	*
	* @return the uuid of this content url
	*/
	@Override
	public String getUuid() {
		return _contentUrl.getUuid();
	}

	@Override
	public int hashCode() {
		return _contentUrl.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _contentUrl.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contentUrl.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contentUrl.isNew();
	}

	@Override
	public void persist() {
		_contentUrl.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contentUrl.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this content url.
	*
	* @param className the class name of this content url
	*/
	@Override
	public void setClassName(String className) {
		_contentUrl.setClassName(className);
	}

	/**
	* Sets the content destination ID of this content url.
	*
	* @param contentDestinationId the content destination ID of this content url
	*/
	@Override
	public void setContentDestinationId(long contentDestinationId) {
		_contentUrl.setContentDestinationId(contentDestinationId);
	}

	/**
	* Sets the content origin ID of this content url.
	*
	* @param contentOriginId the content origin ID of this content url
	*/
	@Override
	public void setContentOriginId(long contentOriginId) {
		_contentUrl.setContentOriginId(contentOriginId);
	}

	/**
	* Sets the content url ID of this content url.
	*
	* @param contentUrlId the content url ID of this content url
	*/
	@Override
	public void setContentUrlId(long contentUrlId) {
		_contentUrl.setContentUrlId(contentUrlId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contentUrl.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contentUrl.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contentUrl.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_contentUrl.setNew(n);
	}

	/**
	* Sets the primary key of this content url.
	*
	* @param primaryKey the primary key of this content url
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contentUrl.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contentUrl.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the url destination of this content url.
	*
	* @param urlDestination the url destination of this content url
	*/
	@Override
	public void setUrlDestination(String urlDestination) {
		_contentUrl.setUrlDestination(urlDestination);
	}

	/**
	* Sets the url origin of this content url.
	*
	* @param urlOrigin the url origin of this content url
	*/
	@Override
	public void setUrlOrigin(String urlOrigin) {
		_contentUrl.setUrlOrigin(urlOrigin);
	}

	/**
	* Sets the uuid of this content url.
	*
	* @param uuid the uuid of this content url
	*/
	@Override
	public void setUuid(String uuid) {
		_contentUrl.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContentUrl> toCacheModel() {
		return _contentUrl.toCacheModel();
	}

	@Override
	public ContentUrl toEscapedModel() {
		return new ContentUrlWrapper(_contentUrl.toEscapedModel());
	}

	@Override
	public String toString() {
		return _contentUrl.toString();
	}

	@Override
	public ContentUrl toUnescapedModel() {
		return new ContentUrlWrapper(_contentUrl.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _contentUrl.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentUrlWrapper)) {
			return false;
		}

		ContentUrlWrapper contentUrlWrapper = (ContentUrlWrapper)obj;

		if (Objects.equals(_contentUrl, contentUrlWrapper._contentUrl)) {
			return true;
		}

		return false;
	}

	@Override
	public ContentUrl getWrappedModel() {
		return _contentUrl;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contentUrl.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contentUrl.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contentUrl.resetOriginalValues();
	}

	private final ContentUrl _contentUrl;
}