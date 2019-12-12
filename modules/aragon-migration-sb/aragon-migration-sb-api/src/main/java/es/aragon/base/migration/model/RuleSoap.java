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
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.RuleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.RuleServiceSoap
 * @generated
 */
@ProviderType
public class RuleSoap implements Serializable {
	public static RuleSoap toSoapModel(Rule model) {
		RuleSoap soapModel = new RuleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRuleId(model.getRuleId());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setUserGroupId(model.getUserGroupId());
		soapModel.setJournalFolderId(model.getJournalFolderId());
		soapModel.setAssignedUserId(model.getAssignedUserId());
		soapModel.setUrl(model.getUrl());
		soapModel.setTags(model.getTags());
		soapModel.setActionId(model.getActionId());
		soapModel.setAvailableUserGroupId(model.getAvailableUserGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static RuleSoap[] toSoapModels(Rule[] models) {
		RuleSoap[] soapModels = new RuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RuleSoap[][] toSoapModels(Rule[][] models) {
		RuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RuleSoap[] toSoapModels(List<Rule> models) {
		List<RuleSoap> soapModels = new ArrayList<RuleSoap>(models.size());

		for (Rule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RuleSoap[soapModels.size()]);
	}

	public RuleSoap() {
	}

	public long getPrimaryKey() {
		return _ruleId;
	}

	public void setPrimaryKey(long pk) {
		setRuleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRuleId() {
		return _ruleId;
	}

	public void setRuleId(long ruleId) {
		_ruleId = ruleId;
	}

	public int getTypeId() {
		return _typeId;
	}

	public void setTypeId(int typeId) {
		_typeId = typeId;
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

	public long getAssignedUserId() {
		return _assignedUserId;
	}

	public void setAssignedUserId(long assignedUserId) {
		_assignedUserId = assignedUserId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getTags() {
		return _tags;
	}

	public void setTags(String tags) {
		_tags = tags;
	}

	public long getActionId() {
		return _actionId;
	}

	public void setActionId(long actionId) {
		_actionId = actionId;
	}

	public String getAvailableUserGroupId() {
		return _availableUserGroupId;
	}

	public void setAvailableUserGroupId(String availableUserGroupId) {
		_availableUserGroupId = availableUserGroupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private String _uuid;
	private long _ruleId;
	private int _typeId;
	private long _userGroupId;
	private long _journalFolderId;
	private long _assignedUserId;
	private String _url;
	private String _tags;
	private long _actionId;
	private String _availableUserGroupId;
	private long _userId;
	private Date _modifiedDate;
}