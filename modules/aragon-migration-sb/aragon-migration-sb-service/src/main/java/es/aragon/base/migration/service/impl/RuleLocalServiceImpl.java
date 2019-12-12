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

package es.aragon.base.migration.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import es.aragon.base.migration.exception.RuleUrlException;
import es.aragon.base.migration.model.Rule;
import es.aragon.base.migration.service.base.RuleLocalServiceBaseImpl;

/**
 * The implementation of the rule local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.migration.service.RuleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author pfalcon
 * @see RuleLocalServiceBaseImpl
 * @see es.aragon.base.migration.service.RuleLocalServiceUtil
 */
public class RuleLocalServiceImpl extends RuleLocalServiceBaseImpl {

	public Rule addRule(int typeId, long userGroupId, long journalFolderId, long assignedUserId, long userId, String url, String tags,String availableUserGroupId, long actionId) throws PortalException {
		validate(url);
		long ruleId = counterLocalService.increment(Rule.class.getName());
		Rule rule = rulePersistence.create(ruleId);
		rule.setTypeId(typeId);
		rule.setUserGroupId(userGroupId);
		rule.setAssignedUserId(assignedUserId);
		rule.setUrl(url);
		rule.setTags(tags);
		rule.setJournalFolderId(journalFolderId);
		rule.setAvailableUserGroupId(availableUserGroupId);
		rule.setActionId(actionId);
		Date modifiedDate = new Date();
		rule.setModifiedDate(modifiedDate);
		rule.setUserId(userId);
		rulePersistence.update(rule);
		return rule;
	}

	@Override
	public void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new RuleUrlException();
		}
	}
	
	@Override
	public List<Rule> getRulesByTypeId(int typeId, int start, int end, OrderByComparator<Rule> orderByComparator){
		return rulePersistence.findByTypeId(typeId, start, end, orderByComparator);
	}
	
}