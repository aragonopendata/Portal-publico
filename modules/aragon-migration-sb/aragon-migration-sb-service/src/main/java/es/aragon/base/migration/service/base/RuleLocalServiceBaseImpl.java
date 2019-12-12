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

package es.aragon.base.migration.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.migration.model.Rule;
import es.aragon.base.migration.service.RuleLocalService;
import es.aragon.base.migration.service.persistence.AreaPersistence;
import es.aragon.base.migration.service.persistence.ContentImagePersistence;
import es.aragon.base.migration.service.persistence.ContentMetadataPersistence;
import es.aragon.base.migration.service.persistence.ContentPersistence;
import es.aragon.base.migration.service.persistence.ContentRelatedPersistence;
import es.aragon.base.migration.service.persistence.ContentUrlPersistence;
import es.aragon.base.migration.service.persistence.RulePersistence;
import es.aragon.base.migration.service.persistence.TemplatePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the rule local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link es.aragon.base.migration.service.impl.RuleLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.impl.RuleLocalServiceImpl
 * @see es.aragon.base.migration.service.RuleLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class RuleLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements RuleLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link es.aragon.base.migration.service.RuleLocalServiceUtil} to access the rule local service.
	 */

	/**
	 * Adds the rule to the database. Also notifies the appropriate model listeners.
	 *
	 * @param rule the rule
	 * @return the rule that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Rule addRule(Rule rule) {
		rule.setNew(true);

		return rulePersistence.update(rule);
	}

	/**
	 * Creates a new rule with the primary key. Does not add the rule to the database.
	 *
	 * @param ruleId the primary key for the new rule
	 * @return the new rule
	 */
	@Override
	@Transactional(enabled = false)
	public Rule createRule(long ruleId) {
		return rulePersistence.create(ruleId);
	}

	/**
	 * Deletes the rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleId the primary key of the rule
	 * @return the rule that was removed
	 * @throws PortalException if a rule with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Rule deleteRule(long ruleId) throws PortalException {
		return rulePersistence.remove(ruleId);
	}

	/**
	 * Deletes the rule from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rule the rule
	 * @return the rule that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Rule deleteRule(Rule rule) {
		return rulePersistence.remove(rule);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Rule.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return rulePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return rulePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return rulePersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return rulePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return rulePersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Rule fetchRule(long ruleId) {
		return rulePersistence.fetchByPrimaryKey(ruleId);
	}

	/**
	 * Returns the rule with the primary key.
	 *
	 * @param ruleId the primary key of the rule
	 * @return the rule
	 * @throws PortalException if a rule with the primary key could not be found
	 */
	@Override
	public Rule getRule(long ruleId) throws PortalException {
		return rulePersistence.findByPrimaryKey(ruleId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ruleLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Rule.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ruleId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(ruleLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Rule.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("ruleId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(ruleLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Rule.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ruleId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return ruleLocalService.deleteRule((Rule)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return rulePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.migration.model.impl.RuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @return the range of rules
	 */
	@Override
	public List<Rule> getRules(int start, int end) {
		return rulePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of rules.
	 *
	 * @return the number of rules
	 */
	@Override
	public int getRulesCount() {
		return rulePersistence.countAll();
	}

	/**
	 * Updates the rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param rule the rule
	 * @return the rule that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Rule updateRule(Rule rule) {
		return rulePersistence.update(rule);
	}

	/**
	 * Returns the area local service.
	 *
	 * @return the area local service
	 */
	public es.aragon.base.migration.service.AreaLocalService getAreaLocalService() {
		return areaLocalService;
	}

	/**
	 * Sets the area local service.
	 *
	 * @param areaLocalService the area local service
	 */
	public void setAreaLocalService(
		es.aragon.base.migration.service.AreaLocalService areaLocalService) {
		this.areaLocalService = areaLocalService;
	}

	/**
	 * Returns the area persistence.
	 *
	 * @return the area persistence
	 */
	public AreaPersistence getAreaPersistence() {
		return areaPersistence;
	}

	/**
	 * Sets the area persistence.
	 *
	 * @param areaPersistence the area persistence
	 */
	public void setAreaPersistence(AreaPersistence areaPersistence) {
		this.areaPersistence = areaPersistence;
	}

	/**
	 * Returns the content local service.
	 *
	 * @return the content local service
	 */
	public es.aragon.base.migration.service.ContentLocalService getContentLocalService() {
		return contentLocalService;
	}

	/**
	 * Sets the content local service.
	 *
	 * @param contentLocalService the content local service
	 */
	public void setContentLocalService(
		es.aragon.base.migration.service.ContentLocalService contentLocalService) {
		this.contentLocalService = contentLocalService;
	}

	/**
	 * Returns the content persistence.
	 *
	 * @return the content persistence
	 */
	public ContentPersistence getContentPersistence() {
		return contentPersistence;
	}

	/**
	 * Sets the content persistence.
	 *
	 * @param contentPersistence the content persistence
	 */
	public void setContentPersistence(ContentPersistence contentPersistence) {
		this.contentPersistence = contentPersistence;
	}

	/**
	 * Returns the content image local service.
	 *
	 * @return the content image local service
	 */
	public es.aragon.base.migration.service.ContentImageLocalService getContentImageLocalService() {
		return contentImageLocalService;
	}

	/**
	 * Sets the content image local service.
	 *
	 * @param contentImageLocalService the content image local service
	 */
	public void setContentImageLocalService(
		es.aragon.base.migration.service.ContentImageLocalService contentImageLocalService) {
		this.contentImageLocalService = contentImageLocalService;
	}

	/**
	 * Returns the content image persistence.
	 *
	 * @return the content image persistence
	 */
	public ContentImagePersistence getContentImagePersistence() {
		return contentImagePersistence;
	}

	/**
	 * Sets the content image persistence.
	 *
	 * @param contentImagePersistence the content image persistence
	 */
	public void setContentImagePersistence(
		ContentImagePersistence contentImagePersistence) {
		this.contentImagePersistence = contentImagePersistence;
	}

	/**
	 * Returns the content metadata local service.
	 *
	 * @return the content metadata local service
	 */
	public es.aragon.base.migration.service.ContentMetadataLocalService getContentMetadataLocalService() {
		return contentMetadataLocalService;
	}

	/**
	 * Sets the content metadata local service.
	 *
	 * @param contentMetadataLocalService the content metadata local service
	 */
	public void setContentMetadataLocalService(
		es.aragon.base.migration.service.ContentMetadataLocalService contentMetadataLocalService) {
		this.contentMetadataLocalService = contentMetadataLocalService;
	}

	/**
	 * Returns the content metadata persistence.
	 *
	 * @return the content metadata persistence
	 */
	public ContentMetadataPersistence getContentMetadataPersistence() {
		return contentMetadataPersistence;
	}

	/**
	 * Sets the content metadata persistence.
	 *
	 * @param contentMetadataPersistence the content metadata persistence
	 */
	public void setContentMetadataPersistence(
		ContentMetadataPersistence contentMetadataPersistence) {
		this.contentMetadataPersistence = contentMetadataPersistence;
	}

	/**
	 * Returns the content related local service.
	 *
	 * @return the content related local service
	 */
	public es.aragon.base.migration.service.ContentRelatedLocalService getContentRelatedLocalService() {
		return contentRelatedLocalService;
	}

	/**
	 * Sets the content related local service.
	 *
	 * @param contentRelatedLocalService the content related local service
	 */
	public void setContentRelatedLocalService(
		es.aragon.base.migration.service.ContentRelatedLocalService contentRelatedLocalService) {
		this.contentRelatedLocalService = contentRelatedLocalService;
	}

	/**
	 * Returns the content related persistence.
	 *
	 * @return the content related persistence
	 */
	public ContentRelatedPersistence getContentRelatedPersistence() {
		return contentRelatedPersistence;
	}

	/**
	 * Sets the content related persistence.
	 *
	 * @param contentRelatedPersistence the content related persistence
	 */
	public void setContentRelatedPersistence(
		ContentRelatedPersistence contentRelatedPersistence) {
		this.contentRelatedPersistence = contentRelatedPersistence;
	}

	/**
	 * Returns the content url local service.
	 *
	 * @return the content url local service
	 */
	public es.aragon.base.migration.service.ContentUrlLocalService getContentUrlLocalService() {
		return contentUrlLocalService;
	}

	/**
	 * Sets the content url local service.
	 *
	 * @param contentUrlLocalService the content url local service
	 */
	public void setContentUrlLocalService(
		es.aragon.base.migration.service.ContentUrlLocalService contentUrlLocalService) {
		this.contentUrlLocalService = contentUrlLocalService;
	}

	/**
	 * Returns the content url persistence.
	 *
	 * @return the content url persistence
	 */
	public ContentUrlPersistence getContentUrlPersistence() {
		return contentUrlPersistence;
	}

	/**
	 * Sets the content url persistence.
	 *
	 * @param contentUrlPersistence the content url persistence
	 */
	public void setContentUrlPersistence(
		ContentUrlPersistence contentUrlPersistence) {
		this.contentUrlPersistence = contentUrlPersistence;
	}

	/**
	 * Returns the rule local service.
	 *
	 * @return the rule local service
	 */
	public RuleLocalService getRuleLocalService() {
		return ruleLocalService;
	}

	/**
	 * Sets the rule local service.
	 *
	 * @param ruleLocalService the rule local service
	 */
	public void setRuleLocalService(RuleLocalService ruleLocalService) {
		this.ruleLocalService = ruleLocalService;
	}

	/**
	 * Returns the rule persistence.
	 *
	 * @return the rule persistence
	 */
	public RulePersistence getRulePersistence() {
		return rulePersistence;
	}

	/**
	 * Sets the rule persistence.
	 *
	 * @param rulePersistence the rule persistence
	 */
	public void setRulePersistence(RulePersistence rulePersistence) {
		this.rulePersistence = rulePersistence;
	}

	/**
	 * Returns the template local service.
	 *
	 * @return the template local service
	 */
	public es.aragon.base.migration.service.TemplateLocalService getTemplateLocalService() {
		return templateLocalService;
	}

	/**
	 * Sets the template local service.
	 *
	 * @param templateLocalService the template local service
	 */
	public void setTemplateLocalService(
		es.aragon.base.migration.service.TemplateLocalService templateLocalService) {
		this.templateLocalService = templateLocalService;
	}

	/**
	 * Returns the template persistence.
	 *
	 * @return the template persistence
	 */
	public TemplatePersistence getTemplatePersistence() {
		return templatePersistence;
	}

	/**
	 * Sets the template persistence.
	 *
	 * @param templatePersistence the template persistence
	 */
	public void setTemplatePersistence(TemplatePersistence templatePersistence) {
		this.templatePersistence = templatePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("es.aragon.base.migration.model.Rule",
			ruleLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"es.aragon.base.migration.model.Rule");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RuleLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Rule.class;
	}

	protected String getModelClassName() {
		return Rule.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = rulePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = es.aragon.base.migration.service.AreaLocalService.class)
	protected es.aragon.base.migration.service.AreaLocalService areaLocalService;
	@BeanReference(type = AreaPersistence.class)
	protected AreaPersistence areaPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentLocalService.class)
	protected es.aragon.base.migration.service.ContentLocalService contentLocalService;
	@BeanReference(type = ContentPersistence.class)
	protected ContentPersistence contentPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentImageLocalService.class)
	protected es.aragon.base.migration.service.ContentImageLocalService contentImageLocalService;
	@BeanReference(type = ContentImagePersistence.class)
	protected ContentImagePersistence contentImagePersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentMetadataLocalService.class)
	protected es.aragon.base.migration.service.ContentMetadataLocalService contentMetadataLocalService;
	@BeanReference(type = ContentMetadataPersistence.class)
	protected ContentMetadataPersistence contentMetadataPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentRelatedLocalService.class)
	protected es.aragon.base.migration.service.ContentRelatedLocalService contentRelatedLocalService;
	@BeanReference(type = ContentRelatedPersistence.class)
	protected ContentRelatedPersistence contentRelatedPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentUrlLocalService.class)
	protected es.aragon.base.migration.service.ContentUrlLocalService contentUrlLocalService;
	@BeanReference(type = ContentUrlPersistence.class)
	protected ContentUrlPersistence contentUrlPersistence;
	@BeanReference(type = RuleLocalService.class)
	protected RuleLocalService ruleLocalService;
	@BeanReference(type = RulePersistence.class)
	protected RulePersistence rulePersistence;
	@BeanReference(type = es.aragon.base.migration.service.TemplateLocalService.class)
	protected es.aragon.base.migration.service.TemplateLocalService templateLocalService;
	@BeanReference(type = TemplatePersistence.class)
	protected TemplatePersistence templatePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}