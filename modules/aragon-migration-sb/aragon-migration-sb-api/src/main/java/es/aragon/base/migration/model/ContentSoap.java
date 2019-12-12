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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.ContentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.ContentServiceSoap
 * @generated
 */
@ProviderType
public class ContentSoap implements Serializable {
	public static ContentSoap toSoapModel(Content model) {
		ContentSoap soapModel = new ContentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContentId(model.getContentId());
		soapModel.setTitle(model.getTitle());
		soapModel.setUrl(model.getUrl());
		soapModel.setContentType(model.getContentType());
		soapModel.setShortExcerpt(model.getShortExcerpt());
		soapModel.setExcerpt(model.getExcerpt());
		soapModel.setOriginalBodyHash(model.getOriginalBodyHash());
		soapModel.setOriginalBody(model.getOriginalBody());
		soapModel.setAreaId(model.getAreaId());
		soapModel.setActionId(model.getActionId());
		soapModel.setStatusId(model.getStatusId());
		soapModel.setTemplateId(model.getTemplateId());
		soapModel.setTags(model.getTags());
		soapModel.setLastModifiedUserId(model.getLastModifiedUserId());
		soapModel.setAssignedUserId(model.getAssignedUserId());
		soapModel.setUserGroupId(model.getUserGroupId());
		soapModel.setJournalFolderId(model.getJournalFolderId());
		soapModel.setDateCreated(model.getDateCreated());
		soapModel.setDateModified(model.getDateModified());
		soapModel.setComments(model.getComments());
		soapModel.setVisibility(model.getVisibility());

		return soapModel;
	}

	public static ContentSoap[] toSoapModels(Content[] models) {
		ContentSoap[] soapModels = new ContentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentSoap[][] toSoapModels(Content[][] models) {
		ContentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentSoap[] toSoapModels(List<Content> models) {
		List<ContentSoap> soapModels = new ArrayList<ContentSoap>(models.size());

		for (Content model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentSoap[soapModels.size()]);
	}

	public ContentSoap() {
	}

	public long getPrimaryKey() {
		return _contentId;
	}

	public void setPrimaryKey(long pk) {
		setContentId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getContentType() {
		return _contentType;
	}

	public void setContentType(String contentType) {
		_contentType = contentType;
	}

	public String getShortExcerpt() {
		return _shortExcerpt;
	}

	public void setShortExcerpt(String shortExcerpt) {
		_shortExcerpt = shortExcerpt;
	}

	public String getExcerpt() {
		return _excerpt;
	}

	public void setExcerpt(String excerpt) {
		_excerpt = excerpt;
	}

	public String getOriginalBodyHash() {
		return _originalBodyHash;
	}

	public void setOriginalBodyHash(String originalBodyHash) {
		_originalBodyHash = originalBodyHash;
	}

	public String getOriginalBody() {
		return _originalBody;
	}

	public void setOriginalBody(String originalBody) {
		_originalBody = originalBody;
	}

	public long getAreaId() {
		return _areaId;
	}

	public void setAreaId(long areaId) {
		_areaId = areaId;
	}

	public long getActionId() {
		return _actionId;
	}

	public void setActionId(long actionId) {
		_actionId = actionId;
	}

	public long getStatusId() {
		return _statusId;
	}

	public void setStatusId(long statusId) {
		_statusId = statusId;
	}

	public long getTemplateId() {
		return _templateId;
	}

	public void setTemplateId(long templateId) {
		_templateId = templateId;
	}

	public String getTags() {
		return _tags;
	}

	public void setTags(String tags) {
		_tags = tags;
	}

	public long getLastModifiedUserId() {
		return _lastModifiedUserId;
	}

	public void setLastModifiedUserId(long lastModifiedUserId) {
		_lastModifiedUserId = lastModifiedUserId;
	}

	public long getAssignedUserId() {
		return _assignedUserId;
	}

	public void setAssignedUserId(long assignedUserId) {
		_assignedUserId = assignedUserId;
	}

	public long getUserGroupId() {
		return _userGroupId;
	}

	public void setUserGroupId(long userGroupId) {
		_userGroupId = userGroupId;
	}

	public long getJournalFolderId() {
		return _journalFolderId;
	}

	public void setJournalFolderId(long journalFolderId) {
		_journalFolderId = journalFolderId;
	}

	public Date getDateCreated() {
		return _dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		_dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return _dateModified;
	}

	public void setDateModified(Date dateModified) {
		_dateModified = dateModified;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	private String _uuid;
	private long _contentId;
	private String _title;
	private String _url;
	private String _contentType;
	private String _shortExcerpt;
	private String _excerpt;
	private String _originalBodyHash;
	private String _originalBody;
	private long _areaId;
	private long _actionId;
	private long _statusId;
	private long _templateId;
	private String _tags;
	private long _lastModifiedUserId;
	private long _assignedUserId;
	private long _userGroupId;
	private long _journalFolderId;
	private Date _dateCreated;
	private Date _dateModified;
	private String _comments;
	private int _visibility;
}