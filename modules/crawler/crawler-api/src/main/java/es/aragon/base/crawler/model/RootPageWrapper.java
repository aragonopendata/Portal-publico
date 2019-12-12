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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link RootPage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RootPage
 * @generated
 */
@ProviderType
public class RootPageWrapper implements RootPage, ModelWrapper<RootPage> {
	public RootPageWrapper(RootPage rootPage) {
		_rootPage = rootPage;
	}

	@Override
	public Class<?> getModelClass() {
		return RootPage.class;
	}

	@Override
	public String getModelClassName() {
		return RootPage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("rootPageId", getRootPageId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("crawledDate", getCrawledDate());
		attributes.put("alias", getAlias());
		attributes.put("inclusionRules", getInclusionRules());
		attributes.put("pageId", getPageId());
		attributes.put("scheduledCrawl", isScheduledCrawl());
		attributes.put("depth", getDepth());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long rootPageId = (Long)attributes.get("rootPageId");

		if (rootPageId != null) {
			setRootPageId(rootPageId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date crawledDate = (Date)attributes.get("crawledDate");

		if (crawledDate != null) {
			setCrawledDate(crawledDate);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		String inclusionRules = (String)attributes.get("inclusionRules");

		if (inclusionRules != null) {
			setInclusionRules(inclusionRules);
		}

		Long pageId = (Long)attributes.get("pageId");

		if (pageId != null) {
			setPageId(pageId);
		}

		Boolean scheduledCrawl = (Boolean)attributes.get("scheduledCrawl");

		if (scheduledCrawl != null) {
			setScheduledCrawl(scheduledCrawl);
		}

		Integer depth = (Integer)attributes.get("depth");

		if (depth != null) {
			setDepth(depth);
		}
	}

	@Override
	public Object clone() {
		return new RootPageWrapper((RootPage)_rootPage.clone());
	}

	@Override
	public int compareTo(RootPage rootPage) {
		return _rootPage.compareTo(rootPage);
	}

	/**
	* Returns the alias of this root page.
	*
	* @return the alias of this root page
	*/
	@Override
	public String getAlias() {
		return _rootPage.getAlias();
	}

	/**
	* Returns the company ID of this root page.
	*
	* @return the company ID of this root page
	*/
	@Override
	public long getCompanyId() {
		return _rootPage.getCompanyId();
	}

	/**
	* Returns the crawled date of this root page.
	*
	* @return the crawled date of this root page
	*/
	@Override
	public Date getCrawledDate() {
		return _rootPage.getCrawledDate();
	}

	/**
	* Returns the depth of this root page.
	*
	* @return the depth of this root page
	*/
	@Override
	public int getDepth() {
		return _rootPage.getDepth();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rootPage.getExpandoBridge();
	}

	/**
	* Returns the group ID of this root page.
	*
	* @return the group ID of this root page
	*/
	@Override
	public long getGroupId() {
		return _rootPage.getGroupId();
	}

	/**
	* Returns the inclusion rules of this root page.
	*
	* @return the inclusion rules of this root page
	*/
	@Override
	public String getInclusionRules() {
		return _rootPage.getInclusionRules();
	}

	/**
	* Returns the page ID of this root page.
	*
	* @return the page ID of this root page
	*/
	@Override
	public long getPageId() {
		return _rootPage.getPageId();
	}

	/**
	* Returns the primary key of this root page.
	*
	* @return the primary key of this root page
	*/
	@Override
	public long getPrimaryKey() {
		return _rootPage.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rootPage.getPrimaryKeyObj();
	}

	/**
	* Returns the root page ID of this root page.
	*
	* @return the root page ID of this root page
	*/
	@Override
	public long getRootPageId() {
		return _rootPage.getRootPageId();
	}

	/**
	* Returns the scheduled crawl of this root page.
	*
	* @return the scheduled crawl of this root page
	*/
	@Override
	public boolean getScheduledCrawl() {
		return _rootPage.getScheduledCrawl();
	}

	/**
	* Returns the uuid of this root page.
	*
	* @return the uuid of this root page
	*/
	@Override
	public String getUuid() {
		return _rootPage.getUuid();
	}

	@Override
	public int hashCode() {
		return _rootPage.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rootPage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rootPage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rootPage.isNew();
	}

	/**
	* Returns <code>true</code> if this root page is scheduled crawl.
	*
	* @return <code>true</code> if this root page is scheduled crawl; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduledCrawl() {
		return _rootPage.isScheduledCrawl();
	}

	@Override
	public void persist() {
		_rootPage.persist();
	}

	/**
	* Sets the alias of this root page.
	*
	* @param alias the alias of this root page
	*/
	@Override
	public void setAlias(String alias) {
		_rootPage.setAlias(alias);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rootPage.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this root page.
	*
	* @param companyId the company ID of this root page
	*/
	@Override
	public void setCompanyId(long companyId) {
		_rootPage.setCompanyId(companyId);
	}

	/**
	* Sets the crawled date of this root page.
	*
	* @param crawledDate the crawled date of this root page
	*/
	@Override
	public void setCrawledDate(Date crawledDate) {
		_rootPage.setCrawledDate(crawledDate);
	}

	/**
	* Sets the depth of this root page.
	*
	* @param depth the depth of this root page
	*/
	@Override
	public void setDepth(int depth) {
		_rootPage.setDepth(depth);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rootPage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rootPage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rootPage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this root page.
	*
	* @param groupId the group ID of this root page
	*/
	@Override
	public void setGroupId(long groupId) {
		_rootPage.setGroupId(groupId);
	}

	/**
	* Sets the inclusion rules of this root page.
	*
	* @param inclusionRules the inclusion rules of this root page
	*/
	@Override
	public void setInclusionRules(String inclusionRules) {
		_rootPage.setInclusionRules(inclusionRules);
	}

	@Override
	public void setNew(boolean n) {
		_rootPage.setNew(n);
	}

	/**
	* Sets the page ID of this root page.
	*
	* @param pageId the page ID of this root page
	*/
	@Override
	public void setPageId(long pageId) {
		_rootPage.setPageId(pageId);
	}

	/**
	* Sets the primary key of this root page.
	*
	* @param primaryKey the primary key of this root page
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_rootPage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rootPage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the root page ID of this root page.
	*
	* @param rootPageId the root page ID of this root page
	*/
	@Override
	public void setRootPageId(long rootPageId) {
		_rootPage.setRootPageId(rootPageId);
	}

	/**
	* Sets whether this root page is scheduled crawl.
	*
	* @param scheduledCrawl the scheduled crawl of this root page
	*/
	@Override
	public void setScheduledCrawl(boolean scheduledCrawl) {
		_rootPage.setScheduledCrawl(scheduledCrawl);
	}

	/**
	* Sets the uuid of this root page.
	*
	* @param uuid the uuid of this root page
	*/
	@Override
	public void setUuid(String uuid) {
		_rootPage.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RootPage> toCacheModel() {
		return _rootPage.toCacheModel();
	}

	@Override
	public RootPage toEscapedModel() {
		return new RootPageWrapper(_rootPage.toEscapedModel());
	}

	@Override
	public String toString() {
		return _rootPage.toString();
	}

	@Override
	public RootPage toUnescapedModel() {
		return new RootPageWrapper(_rootPage.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _rootPage.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RootPageWrapper)) {
			return false;
		}

		RootPageWrapper rootPageWrapper = (RootPageWrapper)obj;

		if (Objects.equals(_rootPage, rootPageWrapper._rootPage)) {
			return true;
		}

		return false;
	}

	@Override
	public RootPage getWrappedModel() {
		return _rootPage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rootPage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rootPage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rootPage.resetOriginalValues();
	}

	private final RootPage _rootPage;
}