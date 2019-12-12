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

package es.aragon.base.migration.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.migration.exception.NoSuchRuleException;
import es.aragon.base.migration.model.Rule;

/**
 * The persistence interface for the rule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.persistence.impl.RulePersistenceImpl
 * @see RuleUtil
 * @generated
 */
@ProviderType
public interface RulePersistence extends BasePersistence<Rule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RuleUtil} to access the rule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the rules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching rules
	*/
	public java.util.List<Rule> findByUuid(String uuid);

	/**
	* Returns a range of all the rules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @return the range of matching rules
	*/
	public java.util.List<Rule> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the rules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rules
	*/
	public java.util.List<Rule> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns an ordered range of all the rules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rules
	*/
	public java.util.List<Rule> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rule
	* @throws NoSuchRuleException if a matching rule could not be found
	*/
	public Rule findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator)
		throws NoSuchRuleException;

	/**
	* Returns the first rule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rule, or <code>null</code> if a matching rule could not be found
	*/
	public Rule fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns the last rule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rule
	* @throws NoSuchRuleException if a matching rule could not be found
	*/
	public Rule findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator)
		throws NoSuchRuleException;

	/**
	* Returns the last rule in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rule, or <code>null</code> if a matching rule could not be found
	*/
	public Rule fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns the rules before and after the current rule in the ordered set where uuid = &#63;.
	*
	* @param ruleId the primary key of the current rule
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rule
	* @throws NoSuchRuleException if a rule with the primary key could not be found
	*/
	public Rule[] findByUuid_PrevAndNext(long ruleId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator)
		throws NoSuchRuleException;

	/**
	* Removes all the rules where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of rules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching rules
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the rules where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the matching rules
	*/
	public java.util.List<Rule> findByTypeId(int typeId);

	/**
	* Returns a range of all the rules where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @return the range of matching rules
	*/
	public java.util.List<Rule> findByTypeId(int typeId, int start, int end);

	/**
	* Returns an ordered range of all the rules where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rules
	*/
	public java.util.List<Rule> findByTypeId(int typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns an ordered range of all the rules where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rules
	*/
	public java.util.List<Rule> findByTypeId(int typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rule in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rule
	* @throws NoSuchRuleException if a matching rule could not be found
	*/
	public Rule findByTypeId_First(int typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator)
		throws NoSuchRuleException;

	/**
	* Returns the first rule in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rule, or <code>null</code> if a matching rule could not be found
	*/
	public Rule fetchByTypeId_First(int typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns the last rule in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rule
	* @throws NoSuchRuleException if a matching rule could not be found
	*/
	public Rule findByTypeId_Last(int typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator)
		throws NoSuchRuleException;

	/**
	* Returns the last rule in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rule, or <code>null</code> if a matching rule could not be found
	*/
	public Rule fetchByTypeId_Last(int typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns the rules before and after the current rule in the ordered set where typeId = &#63;.
	*
	* @param ruleId the primary key of the current rule
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rule
	* @throws NoSuchRuleException if a rule with the primary key could not be found
	*/
	public Rule[] findByTypeId_PrevAndNext(long ruleId, int typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator)
		throws NoSuchRuleException;

	/**
	* Removes all the rules where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	*/
	public void removeByTypeId(int typeId);

	/**
	* Returns the number of rules where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching rules
	*/
	public int countByTypeId(int typeId);

	/**
	* Caches the rule in the entity cache if it is enabled.
	*
	* @param rule the rule
	*/
	public void cacheResult(Rule rule);

	/**
	* Caches the rules in the entity cache if it is enabled.
	*
	* @param rules the rules
	*/
	public void cacheResult(java.util.List<Rule> rules);

	/**
	* Creates a new rule with the primary key. Does not add the rule to the database.
	*
	* @param ruleId the primary key for the new rule
	* @return the new rule
	*/
	public Rule create(long ruleId);

	/**
	* Removes the rule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ruleId the primary key of the rule
	* @return the rule that was removed
	* @throws NoSuchRuleException if a rule with the primary key could not be found
	*/
	public Rule remove(long ruleId) throws NoSuchRuleException;

	public Rule updateImpl(Rule rule);

	/**
	* Returns the rule with the primary key or throws a {@link NoSuchRuleException} if it could not be found.
	*
	* @param ruleId the primary key of the rule
	* @return the rule
	* @throws NoSuchRuleException if a rule with the primary key could not be found
	*/
	public Rule findByPrimaryKey(long ruleId) throws NoSuchRuleException;

	/**
	* Returns the rule with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ruleId the primary key of the rule
	* @return the rule, or <code>null</code> if a rule with the primary key could not be found
	*/
	public Rule fetchByPrimaryKey(long ruleId);

	@Override
	public java.util.Map<java.io.Serializable, Rule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rules.
	*
	* @return the rules
	*/
	public java.util.List<Rule> findAll();

	/**
	* Returns a range of all the rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @return the range of rules
	*/
	public java.util.List<Rule> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rules
	*/
	public java.util.List<Rule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator);

	/**
	* Returns an ordered range of all the rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rules
	* @param end the upper bound of the range of rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rules
	*/
	public java.util.List<Rule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Rule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rules from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rules.
	*
	* @return the number of rules
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}