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

package es.aragon.base.crawler.model;

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
 * This class is a wrapper for {@link Page}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Page
 * @generated
 */
@ProviderType
public class PageWrapper implements Page, ModelWrapper<Page> {
	public PageWrapper(Page page) {
		_page = page;
	}

	@Override
	public Class<?> getModelClass() {
		return Page.class;
	}

	@Override
	public String getModelClassName() {
		return Page.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pageId", getPageId());
		attributes.put("title", getTitle());
		attributes.put("contentType", getContentType());
		attributes.put("metaDescription", getMetaDescription());
		attributes.put("metaKeywords", getMetaKeywords());
		attributes.put("content", getContent());
		attributes.put("url", getUrl());
		attributes.put("categoryIds", getCategoryIds());
		attributes.put("rootPageId", getRootPageId());
		attributes.put("parentPageId", getParentPageId());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long pageId = (Long)attributes.get("pageId");

		if (pageId != null) {
			setPageId(pageId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String contentType = (String)attributes.get("contentType");

		if (contentType != null) {
			setContentType(contentType);
		}

		String metaDescription = (String)attributes.get("metaDescription");

		if (metaDescription != null) {
			setMetaDescription(metaDescription);
		}

		String metaKeywords = (String)attributes.get("metaKeywords");

		if (metaKeywords != null) {
			setMetaKeywords(metaKeywords);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String categoryIds = (String)attributes.get("categoryIds");

		if (categoryIds != null) {
			setCategoryIds(categoryIds);
		}

		Long rootPageId = (Long)attributes.get("rootPageId");

		if (rootPageId != null) {
			setRootPageId(rootPageId);
		}

		Long parentPageId = (Long)attributes.get("parentPageId");

		if (parentPageId != null) {
			setParentPageId(parentPageId);
		}

		Long status = (Long)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new PageWrapper((Page)_page.clone());
	}

	@Override
	public int compareTo(Page page) {
		return _page.compareTo(page);
	}

	/**
	* Returns the category IDs of this page.
	*
	* @return the category IDs of this page
	*/
	@Override
	public String getCategoryIds() {
		return _page.getCategoryIds();
	}

	/**
	* Returns the content of this page.
	*
	* @return the content of this page
	*/
	@Override
	public String getContent() {
		return _page.getContent();
	}

	/**
	* Returns the content type of this page.
	*
	* @return the content type of this page
	*/
	@Override
	public String getContentType() {
		return _page.getContentType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _page.getExpandoBridge();
	}

	/**
	* Returns the meta description of this page.
	*
	* @return the meta description of this page
	*/
	@Override
	public String getMetaDescription() {
		return _page.getMetaDescription();
	}

	/**
	* Returns the meta keywords of this page.
	*
	* @return the meta keywords of this page
	*/
	@Override
	public String getMetaKeywords() {
		return _page.getMetaKeywords();
	}

	/**
	* Returns the page ID of this page.
	*
	* @return the page ID of this page
	*/
	@Override
	public long getPageId() {
		return _page.getPageId();
	}

	/**
	* Returns the parent page ID of this page.
	*
	* @return the parent page ID of this page
	*/
	@Override
	public long getParentPageId() {
		return _page.getParentPageId();
	}

	/**
	* Returns the primary key of this page.
	*
	* @return the primary key of this page
	*/
	@Override
	public long getPrimaryKey() {
		return _page.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _page.getPrimaryKeyObj();
	}

	/**
	* Returns the root page ID of this page.
	*
	* @return the root page ID of this page
	*/
	@Override
	public long getRootPageId() {
		return _page.getRootPageId();
	}

	/**
	* Returns the status of this page.
	*
	* @return the status of this page
	*/
	@Override
	public long getStatus() {
		return _page.getStatus();
	}

	/**
	* Returns the title of this page.
	*
	* @return the title of this page
	*/
	@Override
	public String getTitle() {
		return _page.getTitle();
	}

	/**
	* Returns the url of this page.
	*
	* @return the url of this page
	*/
	@Override
	public String getUrl() {
		return _page.getUrl();
	}

	/**
	* Returns the uuid of this page.
	*
	* @return the uuid of this page
	*/
	@Override
	public String getUuid() {
		return _page.getUuid();
	}

	@Override
	public int hashCode() {
		return _page.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _page.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _page.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _page.isNew();
	}

	@Override
	public void persist() {
		_page.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_page.setCachedModel(cachedModel);
	}

	/**
	* Sets the category IDs of this page.
	*
	* @param categoryIds the category IDs of this page
	*/
	@Override
	public void setCategoryIds(String categoryIds) {
		_page.setCategoryIds(categoryIds);
	}

	@Override
	public void setCategoryIds(String categoryIds,
		es.aragon.base.crawler.service.PageLocalService pageLocalService) {
		_page.setCategoryIds(categoryIds, pageLocalService);
	}

	/**
	* Sets the content of this page.
	*
	* @param content the content of this page
	*/
	@Override
	public void setContent(String content) {
		_page.setContent(content);
	}

	/**
	* Sets the content type of this page.
	*
	* @param contentType the content type of this page
	*/
	@Override
	public void setContentType(String contentType) {
		_page.setContentType(contentType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_page.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_page.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_page.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the meta description of this page.
	*
	* @param metaDescription the meta description of this page
	*/
	@Override
	public void setMetaDescription(String metaDescription) {
		_page.setMetaDescription(metaDescription);
	}

	/**
	* Sets the meta keywords of this page.
	*
	* @param metaKeywords the meta keywords of this page
	*/
	@Override
	public void setMetaKeywords(String metaKeywords) {
		_page.setMetaKeywords(metaKeywords);
	}

	@Override
	public void setNew(boolean n) {
		_page.setNew(n);
	}

	/**
	* Sets the page ID of this page.
	*
	* @param pageId the page ID of this page
	*/
	@Override
	public void setPageId(long pageId) {
		_page.setPageId(pageId);
	}

	/**
	* Sets the parent page ID of this page.
	*
	* @param parentPageId the parent page ID of this page
	*/
	@Override
	public void setParentPageId(long parentPageId) {
		_page.setParentPageId(parentPageId);
	}

	/**
	* Sets the primary key of this page.
	*
	* @param primaryKey the primary key of this page
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_page.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_page.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the root page ID of this page.
	*
	* @param rootPageId the root page ID of this page
	*/
	@Override
	public void setRootPageId(long rootPageId) {
		_page.setRootPageId(rootPageId);
	}

	/**
	* Sets the status of this page.
	*
	* @param status the status of this page
	*/
	@Override
	public void setStatus(long status) {
		_page.setStatus(status);
	}

	/**
	* Sets the title of this page.
	*
	* @param title the title of this page
	*/
	@Override
	public void setTitle(String title) {
		_page.setTitle(title);
	}

	/**
	* Sets the url of this page.
	*
	* @param url the url of this page
	*/
	@Override
	public void setUrl(String url) {
		_page.setUrl(url);
	}

	/**
	* Sets the uuid of this page.
	*
	* @param uuid the uuid of this page
	*/
	@Override
	public void setUuid(String uuid) {
		_page.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Page> toCacheModel() {
		return _page.toCacheModel();
	}

	@Override
	public Page toEscapedModel() {
		return new PageWrapper(_page.toEscapedModel());
	}

	@Override
	public String toString() {
		return _page.toString();
	}

	@Override
	public Page toUnescapedModel() {
		return new PageWrapper(_page.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _page.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageWrapper)) {
			return false;
		}

		PageWrapper pageWrapper = (PageWrapper)obj;

		if (Objects.equals(_page, pageWrapper._page)) {
			return true;
		}

		return false;
	}

	@Override
	public Page getWrappedModel() {
		return _page;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _page.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _page.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_page.resetOriginalValues();
	}

	private final Page _page;
}