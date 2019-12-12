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
 * This class is a wrapper for {@link Rule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Rule
 * @generated
 */
@ProviderType
public class RuleWrapper implements Rule, ModelWrapper<Rule> {
	public RuleWrapper(Rule rule) {
		_rule = rule;
	}

	@Override
	public Class<?> getModelClass() {
		return Rule.class;
	}

	@Override
	public String getModelClassName() {
		return Rule.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ruleId", getRuleId());
		attributes.put("typeId", getTypeId());
		attributes.put("userGroupId", getUserGroupId());
		attributes.put("journalFolderId", getJournalFolderId());
		attributes.put("assignedUserId", getAssignedUserId());
		attributes.put("url", getUrl());
		attributes.put("tags", getTags());
		attributes.put("actionId", getActionId());
		attributes.put("availableUserGroupId", getAvailableUserGroupId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ruleId = (Long)attributes.get("ruleId");

		if (ruleId != null) {
			setRuleId(ruleId);
		}

		Integer typeId = (Integer)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long userGroupId = (Long)attributes.get("userGroupId");

		if (userGroupId != null) {
			setUserGroupId(userGroupId);
		}

		Long journalFolderId = (Long)attributes.get("journalFolderId");

		if (journalFolderId != null) {
			setJournalFolderId(journalFolderId);
		}

		Long assignedUserId = (Long)attributes.get("assignedUserId");

		if (assignedUserId != null) {
			setAssignedUserId(assignedUserId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String tags = (String)attributes.get("tags");

		if (tags != null) {
			setTags(tags);
		}

		Long actionId = (Long)attributes.get("actionId");

		if (actionId != null) {
			setActionId(actionId);
		}

		String availableUserGroupId = (String)attributes.get(
				"availableUserGroupId");

		if (availableUserGroupId != null) {
			setAvailableUserGroupId(availableUserGroupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public Object clone() {
		return new RuleWrapper((Rule)_rule.clone());
	}

	@Override
	public int compareTo(Rule rule) {
		return _rule.compareTo(rule);
	}

	/**
	* Returns the action ID of this rule.
	*
	* @return the action ID of this rule
	*/
	@Override
	public long getActionId() {
		return _rule.getActionId();
	}

	/**
	* Returns the assigned user ID of this rule.
	*
	* @return the assigned user ID of this rule
	*/
	@Override
	public long getAssignedUserId() {
		return _rule.getAssignedUserId();
	}

	/**
	* Returns the assigned user uuid of this rule.
	*
	* @return the assigned user uuid of this rule
	*/
	@Override
	public String getAssignedUserUuid() {
		return _rule.getAssignedUserUuid();
	}

	/**
	* Returns the available user group ID of this rule.
	*
	* @return the available user group ID of this rule
	*/
	@Override
	public String getAvailableUserGroupId() {
		return _rule.getAvailableUserGroupId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rule.getExpandoBridge();
	}

	/**
	* Returns the journal folder ID of this rule.
	*
	* @return the journal folder ID of this rule
	*/
	@Override
	public long getJournalFolderId() {
		return _rule.getJournalFolderId();
	}

	/**
	* Returns the modified date of this rule.
	*
	* @return the modified date of this rule
	*/
	@Override
	public Date getModifiedDate() {
		return _rule.getModifiedDate();
	}

	/**
	* Returns the primary key of this rule.
	*
	* @return the primary key of this rule
	*/
	@Override
	public long getPrimaryKey() {
		return _rule.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rule.getPrimaryKeyObj();
	}

	/**
	* Returns the rule ID of this rule.
	*
	* @return the rule ID of this rule
	*/
	@Override
	public long getRuleId() {
		return _rule.getRuleId();
	}

	/**
	* Returns the tags of this rule.
	*
	* @return the tags of this rule
	*/
	@Override
	public String getTags() {
		return _rule.getTags();
	}

	/**
	* Returns the type ID of this rule.
	*
	* @return the type ID of this rule
	*/
	@Override
	public int getTypeId() {
		return _rule.getTypeId();
	}

	/**
	* Returns the url of this rule.
	*
	* @return the url of this rule
	*/
	@Override
	public String getUrl() {
		return _rule.getUrl();
	}

	/**
	* Returns the user group ID of this rule.
	*
	* @return the user group ID of this rule
	*/
	@Override
	public long getUserGroupId() {
		return _rule.getUserGroupId();
	}

	/**
	* Returns the user ID of this rule.
	*
	* @return the user ID of this rule
	*/
	@Override
	public long getUserId() {
		return _rule.getUserId();
	}

	/**
	* Returns the user uuid of this rule.
	*
	* @return the user uuid of this rule
	*/
	@Override
	public String getUserUuid() {
		return _rule.getUserUuid();
	}

	/**
	* Returns the uuid of this rule.
	*
	* @return the uuid of this rule
	*/
	@Override
	public String getUuid() {
		return _rule.getUuid();
	}

	@Override
	public int hashCode() {
		return _rule.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rule.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rule.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rule.isNew();
	}

	@Override
	public void persist() {
		_rule.persist();
	}

	/**
	* Sets the action ID of this rule.
	*
	* @param actionId the action ID of this rule
	*/
	@Override
	public void setActionId(long actionId) {
		_rule.setActionId(actionId);
	}

	/**
	* Sets the assigned user ID of this rule.
	*
	* @param assignedUserId the assigned user ID of this rule
	*/
	@Override
	public void setAssignedUserId(long assignedUserId) {
		_rule.setAssignedUserId(assignedUserId);
	}

	/**
	* Sets the assigned user uuid of this rule.
	*
	* @param assignedUserUuid the assigned user uuid of this rule
	*/
	@Override
	public void setAssignedUserUuid(String assignedUserUuid) {
		_rule.setAssignedUserUuid(assignedUserUuid);
	}

	/**
	* Sets the available user group ID of this rule.
	*
	* @param availableUserGroupId the available user group ID of this rule
	*/
	@Override
	public void setAvailableUserGroupId(String availableUserGroupId) {
		_rule.setAvailableUserGroupId(availableUserGroupId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rule.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rule.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rule.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rule.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the journal folder ID of this rule.
	*
	* @param journalFolderId the journal folder ID of this rule
	*/
	@Override
	public void setJournalFolderId(long journalFolderId) {
		_rule.setJournalFolderId(journalFolderId);
	}

	/**
	* Sets the modified date of this rule.
	*
	* @param modifiedDate the modified date of this rule
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_rule.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_rule.setNew(n);
	}

	/**
	* Sets the primary key of this rule.
	*
	* @param primaryKey the primary key of this rule
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_rule.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rule.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rule ID of this rule.
	*
	* @param ruleId the rule ID of this rule
	*/
	@Override
	public void setRuleId(long ruleId) {
		_rule.setRuleId(ruleId);
	}

	/**
	* Sets the tags of this rule.
	*
	* @param tags the tags of this rule
	*/
	@Override
	public void setTags(String tags) {
		_rule.setTags(tags);
	}

	/**
	* Sets the type ID of this rule.
	*
	* @param typeId the type ID of this rule
	*/
	@Override
	public void setTypeId(int typeId) {
		_rule.setTypeId(typeId);
	}

	/**
	* Sets the url of this rule.
	*
	* @param url the url of this rule
	*/
	@Override
	public void setUrl(String url) {
		_rule.setUrl(url);
	}

	/**
	* Sets the user group ID of this rule.
	*
	* @param userGroupId the user group ID of this rule
	*/
	@Override
	public void setUserGroupId(long userGroupId) {
		_rule.setUserGroupId(userGroupId);
	}

	/**
	* Sets the user ID of this rule.
	*
	* @param userId the user ID of this rule
	*/
	@Override
	public void setUserId(long userId) {
		_rule.setUserId(userId);
	}

	/**
	* Sets the user uuid of this rule.
	*
	* @param userUuid the user uuid of this rule
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_rule.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this rule.
	*
	* @param uuid the uuid of this rule
	*/
	@Override
	public void setUuid(String uuid) {
		_rule.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Rule> toCacheModel() {
		return _rule.toCacheModel();
	}

	@Override
	public Rule toEscapedModel() {
		return new RuleWrapper(_rule.toEscapedModel());
	}

	@Override
	public String toString() {
		return _rule.toString();
	}

	@Override
	public Rule toUnescapedModel() {
		return new RuleWrapper(_rule.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _rule.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RuleWrapper)) {
			return false;
		}

		RuleWrapper ruleWrapper = (RuleWrapper)obj;

		if (Objects.equals(_rule, ruleWrapper._rule)) {
			return true;
		}

		return false;
	}

	@Override
	public Rule getWrappedModel() {
		return _rule;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rule.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rule.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rule.resetOriginalValues();
	}

	private final Rule _rule;
}