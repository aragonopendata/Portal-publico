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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Content}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Content
 * @generated
 */
@ProviderType
public class ContentWrapper implements Content, ModelWrapper<Content> {
	public ContentWrapper(Content content) {
		_content = content;
	}

	@Override
	public Class<?> getModelClass() {
		return Content.class;
	}

	@Override
	public String getModelClassName() {
		return Content.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contentId", getContentId());
		attributes.put("title", getTitle());
		attributes.put("url", getUrl());
		attributes.put("contentType", getContentType());
		attributes.put("shortExcerpt", getShortExcerpt());
		attributes.put("excerpt", getExcerpt());
		attributes.put("originalBodyHash", getOriginalBodyHash());
		attributes.put("originalBody", getOriginalBody());
		attributes.put("areaId", getAreaId());
		attributes.put("actionId", getActionId());
		attributes.put("statusId", getStatusId());
		attributes.put("templateId", getTemplateId());
		attributes.put("tags", getTags());
		attributes.put("lastModifiedUserId", getLastModifiedUserId());
		attributes.put("assignedUserId", getAssignedUserId());
		attributes.put("userGroupId", getUserGroupId());
		attributes.put("journalFolderId", getJournalFolderId());
		attributes.put("dateCreated", getDateCreated());
		attributes.put("dateModified", getDateModified());
		attributes.put("comments", getComments());
		attributes.put("visibility", getVisibility());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contentId = (Long)attributes.get("contentId");

		if (contentId != null) {
			setContentId(contentId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String contentType = (String)attributes.get("contentType");

		if (contentType != null) {
			setContentType(contentType);
		}

		String shortExcerpt = (String)attributes.get("shortExcerpt");

		if (shortExcerpt != null) {
			setShortExcerpt(shortExcerpt);
		}

		String excerpt = (String)attributes.get("excerpt");

		if (excerpt != null) {
			setExcerpt(excerpt);
		}

		String originalBodyHash = (String)attributes.get("originalBodyHash");

		if (originalBodyHash != null) {
			setOriginalBodyHash(originalBodyHash);
		}

		String originalBody = (String)attributes.get("originalBody");

		if (originalBody != null) {
			setOriginalBody(originalBody);
		}

		Long areaId = (Long)attributes.get("areaId");

		if (areaId != null) {
			setAreaId(areaId);
		}

		Long actionId = (Long)attributes.get("actionId");

		if (actionId != null) {
			setActionId(actionId);
		}

		Long statusId = (Long)attributes.get("statusId");

		if (statusId != null) {
			setStatusId(statusId);
		}

		Long templateId = (Long)attributes.get("templateId");

		if (templateId != null) {
			setTemplateId(templateId);
		}

		String tags = (String)attributes.get("tags");

		if (tags != null) {
			setTags(tags);
		}

		Long lastModifiedUserId = (Long)attributes.get("lastModifiedUserId");

		if (lastModifiedUserId != null) {
			setLastModifiedUserId(lastModifiedUserId);
		}

		Long assignedUserId = (Long)attributes.get("assignedUserId");

		if (assignedUserId != null) {
			setAssignedUserId(assignedUserId);
		}

		Long userGroupId = (Long)attributes.get("userGroupId");

		if (userGroupId != null) {
			setUserGroupId(userGroupId);
		}

		Long journalFolderId = (Long)attributes.get("journalFolderId");

		if (journalFolderId != null) {
			setJournalFolderId(journalFolderId);
		}

		Date dateCreated = (Date)attributes.get("dateCreated");

		if (dateCreated != null) {
			setDateCreated(dateCreated);
		}

		Date dateModified = (Date)attributes.get("dateModified");

		if (dateModified != null) {
			setDateModified(dateModified);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}
	}

	@Override
	public Object clone() {
		return new ContentWrapper((Content)_content.clone());
	}

	@Override
	public int compareTo(Content content) {
		return _content.compareTo(content);
	}

	/**
	* Returns the action ID of this content.
	*
	* @return the action ID of this content
	*/
	@Override
	public long getActionId() {
		return _content.getActionId();
	}

	/**
	* Returns the area ID of this content.
	*
	* @return the area ID of this content
	*/
	@Override
	public long getAreaId() {
		return _content.getAreaId();
	}

	/**
	* Returns the assigned user ID of this content.
	*
	* @return the assigned user ID of this content
	*/
	@Override
	public long getAssignedUserId() {
		return _content.getAssignedUserId();
	}

	/**
	* Returns the assigned user uuid of this content.
	*
	* @return the assigned user uuid of this content
	*/
	@Override
	public String getAssignedUserUuid() {
		return _content.getAssignedUserUuid();
	}

	/**
	* Returns the comments of this content.
	*
	* @return the comments of this content
	*/
	@Override
	public String getComments() {
		return _content.getComments();
	}

	/**
	* Returns the content ID of this content.
	*
	* @return the content ID of this content
	*/
	@Override
	public long getContentId() {
		return _content.getContentId();
	}

	/**
	* Returns the content type of this content.
	*
	* @return the content type of this content
	*/
	@Override
	public String getContentType() {
		return _content.getContentType();
	}

	/**
	* Returns the date created of this content.
	*
	* @return the date created of this content
	*/
	@Override
	public Date getDateCreated() {
		return _content.getDateCreated();
	}

	/**
	* Returns the date modified of this content.
	*
	* @return the date modified of this content
	*/
	@Override
	public Date getDateModified() {
		return _content.getDateModified();
	}

	/**
	* Returns the excerpt of this content.
	*
	* @return the excerpt of this content
	*/
	@Override
	public String getExcerpt() {
		return _content.getExcerpt();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _content.getExpandoBridge();
	}

	/**
	* Returns the journal folder ID of this content.
	*
	* @return the journal folder ID of this content
	*/
	@Override
	public long getJournalFolderId() {
		return _content.getJournalFolderId();
	}

	/**
	* Returns the last modified user ID of this content.
	*
	* @return the last modified user ID of this content
	*/
	@Override
	public long getLastModifiedUserId() {
		return _content.getLastModifiedUserId();
	}

	/**
	* Returns the last modified user uuid of this content.
	*
	* @return the last modified user uuid of this content
	*/
	@Override
	public String getLastModifiedUserUuid() {
		return _content.getLastModifiedUserUuid();
	}

	/**
	* Returns the original body of this content.
	*
	* @return the original body of this content
	*/
	@Override
	public String getOriginalBody() {
		return _content.getOriginalBody();
	}

	/**
	* Returns the original body hash of this content.
	*
	* @return the original body hash of this content
	*/
	@Override
	public String getOriginalBodyHash() {
		return _content.getOriginalBodyHash();
	}

	/**
	* Returns the primary key of this content.
	*
	* @return the primary key of this content
	*/
	@Override
	public long getPrimaryKey() {
		return _content.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _content.getPrimaryKeyObj();
	}

	/**
	* Returns the short excerpt of this content.
	*
	* @return the short excerpt of this content
	*/
	@Override
	public String getShortExcerpt() {
		return _content.getShortExcerpt();
	}

	/**
	* Returns the status ID of this content.
	*
	* @return the status ID of this content
	*/
	@Override
	public long getStatusId() {
		return _content.getStatusId();
	}

	/**
	* Returns the tags of this content.
	*
	* @return the tags of this content
	*/
	@Override
	public String getTags() {
		return _content.getTags();
	}

	/**
	* Returns the template ID of this content.
	*
	* @return the template ID of this content
	*/
	@Override
	public long getTemplateId() {
		return _content.getTemplateId();
	}

	/**
	* Returns the title of this content.
	*
	* @return the title of this content
	*/
	@Override
	public String getTitle() {
		return _content.getTitle();
	}

	/**
	* Returns the url of this content.
	*
	* @return the url of this content
	*/
	@Override
	public String getUrl() {
		return _content.getUrl();
	}

	/**
	* Returns the user group ID of this content.
	*
	* @return the user group ID of this content
	*/
	@Override
	public long getUserGroupId() {
		return _content.getUserGroupId();
	}

	/**
	* Returns the uuid of this content.
	*
	* @return the uuid of this content
	*/
	@Override
	public String getUuid() {
		return _content.getUuid();
	}

	/**
	* Returns the visibility of this content.
	*
	* @return the visibility of this content
	*/
	@Override
	public int getVisibility() {
		return _content.getVisibility();
	}

	@Override
	public int hashCode() {
		return _content.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _content.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _content.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _content.isNew();
	}

	@Override
	public void persist() {
		_content.persist();
	}

	/**
	* Sets the action ID of this content.
	*
	* @param actionId the action ID of this content
	*/
	@Override
	public void setActionId(long actionId) {
		_content.setActionId(actionId);
	}

	/**
	* Sets the area ID of this content.
	*
	* @param areaId the area ID of this content
	*/
	@Override
	public void setAreaId(long areaId) {
		_content.setAreaId(areaId);
	}

	/**
	* Sets the assigned user ID of this content.
	*
	* @param assignedUserId the assigned user ID of this content
	*/
	@Override
	public void setAssignedUserId(long assignedUserId) {
		_content.setAssignedUserId(assignedUserId);
	}

	/**
	* Sets the assigned user uuid of this content.
	*
	* @param assignedUserUuid the assigned user uuid of this content
	*/
	@Override
	public void setAssignedUserUuid(String assignedUserUuid) {
		_content.setAssignedUserUuid(assignedUserUuid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_content.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this content.
	*
	* @param comments the comments of this content
	*/
	@Override
	public void setComments(String comments) {
		_content.setComments(comments);
	}

	/**
	* Sets the content ID of this content.
	*
	* @param contentId the content ID of this content
	*/
	@Override
	public void setContentId(long contentId) {
		_content.setContentId(contentId);
	}

	/**
	* Sets the content type of this content.
	*
	* @param contentType the content type of this content
	*/
	@Override
	public void setContentType(String contentType) {
		_content.setContentType(contentType);
	}

	/**
	* Sets the date created of this content.
	*
	* @param dateCreated the date created of this content
	*/
	@Override
	public void setDateCreated(Date dateCreated) {
		_content.setDateCreated(dateCreated);
	}

	/**
	* Sets the date modified of this content.
	*
	* @param dateModified the date modified of this content
	*/
	@Override
	public void setDateModified(Date dateModified) {
		_content.setDateModified(dateModified);
	}

	/**
	* Sets the excerpt of this content.
	*
	* @param excerpt the excerpt of this content
	*/
	@Override
	public void setExcerpt(String excerpt) {
		_content.setExcerpt(excerpt);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_content.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_content.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_content.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the journal folder ID of this content.
	*
	* @param journalFolderId the journal folder ID of this content
	*/
	@Override
	public void setJournalFolderId(long journalFolderId) {
		_content.setJournalFolderId(journalFolderId);
	}

	/**
	* Sets the last modified user ID of this content.
	*
	* @param lastModifiedUserId the last modified user ID of this content
	*/
	@Override
	public void setLastModifiedUserId(long lastModifiedUserId) {
		_content.setLastModifiedUserId(lastModifiedUserId);
	}

	/**
	* Sets the last modified user uuid of this content.
	*
	* @param lastModifiedUserUuid the last modified user uuid of this content
	*/
	@Override
	public void setLastModifiedUserUuid(String lastModifiedUserUuid) {
		_content.setLastModifiedUserUuid(lastModifiedUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_content.setNew(n);
	}

	/**
	* Sets the original body of this content.
	*
	* @param originalBody the original body of this content
	*/
	@Override
	public void setOriginalBody(String originalBody) {
		_content.setOriginalBody(originalBody);
	}

	/**
	* Sets the original body hash of this content.
	*
	* @param originalBodyHash the original body hash of this content
	*/
	@Override
	public void setOriginalBodyHash(String originalBodyHash) {
		_content.setOriginalBodyHash(originalBodyHash);
	}

	/**
	* Sets the primary key of this content.
	*
	* @param primaryKey the primary key of this content
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_content.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_content.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the short excerpt of this content.
	*
	* @param shortExcerpt the short excerpt of this content
	*/
	@Override
	public void setShortExcerpt(String shortExcerpt) {
		_content.setShortExcerpt(shortExcerpt);
	}

	/**
	* Sets the status ID of this content.
	*
	* @param statusId the status ID of this content
	*/
	@Override
	public void setStatusId(long statusId) {
		_content.setStatusId(statusId);
	}

	/**
	* Sets the tags of this content.
	*
	* @param tags the tags of this content
	*/
	@Override
	public void setTags(String tags) {
		_content.setTags(tags);
	}

	/**
	* Sets the template ID of this content.
	*
	* @param templateId the template ID of this content
	*/
	@Override
	public void setTemplateId(long templateId) {
		_content.setTemplateId(templateId);
	}

	/**
	* Sets the title of this content.
	*
	* @param title the title of this content
	*/
	@Override
	public void setTitle(String title) {
		_content.setTitle(title);
	}

	/**
	* Sets the url of this content.
	*
	* @param url the url of this content
	*/
	@Override
	public void setUrl(String url) {
		_content.setUrl(url);
	}

	/**
	* Sets the user group ID of this content.
	*
	* @param userGroupId the user group ID of this content
	*/
	@Override
	public void setUserGroupId(long userGroupId) {
		_content.setUserGroupId(userGroupId);
	}

	/**
	* Sets the uuid of this content.
	*
	* @param uuid the uuid of this content
	*/
	@Override
	public void setUuid(String uuid) {
		_content.setUuid(uuid);
	}

	/**
	* Sets the visibility of this content.
	*
	* @param visibility the visibility of this content
	*/
	@Override
	public void setVisibility(int visibility) {
		_content.setVisibility(visibility);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Content> toCacheModel() {
		return _content.toCacheModel();
	}

	@Override
	public Content toEscapedModel() {
		return new ContentWrapper(_content.toEscapedModel());
	}

	@Override
	public String toString() {
		return _content.toString();
	}

	@Override
	public Content toUnescapedModel() {
		return new ContentWrapper(_content.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _content.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentWrapper)) {
			return false;
		}

		ContentWrapper contentWrapper = (ContentWrapper)obj;

		if (Objects.equals(_content, contentWrapper._content)) {
			return true;
		}

		return false;
	}

	@Override
	public Content getWrappedModel() {
		return _content;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _content.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _content.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_content.resetOriginalValues();
	}

	private final Content _content;
}