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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Rule service. Represents a row in the &quot;EAB_MG_Rule&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link es.aragon.base.migration.model.impl.RuleModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link es.aragon.base.migration.model.impl.RuleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Rule
 * @see es.aragon.base.migration.model.impl.RuleImpl
 * @see es.aragon.base.migration.model.impl.RuleModelImpl
 * @generated
 */
@ProviderType
public interface RuleModel extends BaseModel<Rule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a rule model instance should use the {@link Rule} interface instead.
	 */

	/**
	 * Returns the primary key of this rule.
	 *
	 * @return the primary key of this rule
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this rule.
	 *
	 * @param primaryKey the primary key of this rule
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this rule.
	 *
	 * @return the uuid of this rule
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this rule.
	 *
	 * @param uuid the uuid of this rule
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the rule ID of this rule.
	 *
	 * @return the rule ID of this rule
	 */
	public long getRuleId();

	/**
	 * Sets the rule ID of this rule.
	 *
	 * @param ruleId the rule ID of this rule
	 */
	public void setRuleId(long ruleId);

	/**
	 * Returns the type ID of this rule.
	 *
	 * @return the type ID of this rule
	 */
	public int getTypeId();

	/**
	 * Sets the type ID of this rule.
	 *
	 * @param typeId the type ID of this rule
	 */
	public void setTypeId(int typeId);

	/**
	 * Returns the user group ID of this rule.
	 *
	 * @return the user group ID of this rule
	 */
	public long getUserGroupId();

	/**
	 * Sets the user group ID of this rule.
	 *
	 * @param userGroupId the user group ID of this rule
	 */
	public void setUserGroupId(long userGroupId);

	/**
	 * Returns the journal folder ID of this rule.
	 *
	 * @return the journal folder ID of this rule
	 */
	public long getJournalFolderId();

	/**
	 * Sets the journal folder ID of this rule.
	 *
	 * @param journalFolderId the journal folder ID of this rule
	 */
	public void setJournalFolderId(long journalFolderId);

	/**
	 * Returns the assigned user ID of this rule.
	 *
	 * @return the assigned user ID of this rule
	 */
	public long getAssignedUserId();

	/**
	 * Sets the assigned user ID of this rule.
	 *
	 * @param assignedUserId the assigned user ID of this rule
	 */
	public void setAssignedUserId(long assignedUserId);

	/**
	 * Returns the assigned user uuid of this rule.
	 *
	 * @return the assigned user uuid of this rule
	 */
	public String getAssignedUserUuid();

	/**
	 * Sets the assigned user uuid of this rule.
	 *
	 * @param assignedUserUuid the assigned user uuid of this rule
	 */
	public void setAssignedUserUuid(String assignedUserUuid);

	/**
	 * Returns the url of this rule.
	 *
	 * @return the url of this rule
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this rule.
	 *
	 * @param url the url of this rule
	 */
	public void setUrl(String url);

	/**
	 * Returns the tags of this rule.
	 *
	 * @return the tags of this rule
	 */
	@AutoEscape
	public String getTags();

	/**
	 * Sets the tags of this rule.
	 *
	 * @param tags the tags of this rule
	 */
	public void setTags(String tags);

	/**
	 * Returns the action ID of this rule.
	 *
	 * @return the action ID of this rule
	 */
	public long getActionId();

	/**
	 * Sets the action ID of this rule.
	 *
	 * @param actionId the action ID of this rule
	 */
	public void setActionId(long actionId);

	/**
	 * Returns the available user group ID of this rule.
	 *
	 * @return the available user group ID of this rule
	 */
	@AutoEscape
	public String getAvailableUserGroupId();

	/**
	 * Sets the available user group ID of this rule.
	 *
	 * @param availableUserGroupId the available user group ID of this rule
	 */
	public void setAvailableUserGroupId(String availableUserGroupId);

	/**
	 * Returns the user ID of this rule.
	 *
	 * @return the user ID of this rule
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this rule.
	 *
	 * @param userId the user ID of this rule
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this rule.
	 *
	 * @return the user uuid of this rule
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this rule.
	 *
	 * @param userUuid the user uuid of this rule
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the modified date of this rule.
	 *
	 * @return the modified date of this rule
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this rule.
	 *
	 * @param modifiedDate the modified date of this rule
	 */
	public void setModifiedDate(Date modifiedDate);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Rule rule);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Rule> toCacheModel();

	@Override
	public Rule toEscapedModel();

	@Override
	public Rule toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}