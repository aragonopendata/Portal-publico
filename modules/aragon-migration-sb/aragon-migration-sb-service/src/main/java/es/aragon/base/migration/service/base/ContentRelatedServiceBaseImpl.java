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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.migration.model.ContentRelated;
import es.aragon.base.migration.service.ContentRelatedService;
import es.aragon.base.migration.service.persistence.AreaPersistence;
import es.aragon.base.migration.service.persistence.ContentImagePersistence;
import es.aragon.base.migration.service.persistence.ContentMetadataPersistence;
import es.aragon.base.migration.service.persistence.ContentPersistence;
import es.aragon.base.migration.service.persistence.ContentRelatedPersistence;
import es.aragon.base.migration.service.persistence.ContentUrlPersistence;
import es.aragon.base.migration.service.persistence.RulePersistence;
import es.aragon.base.migration.service.persistence.TemplatePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the content related remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link es.aragon.base.migration.service.impl.ContentRelatedServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.impl.ContentRelatedServiceImpl
 * @see es.aragon.base.migration.service.ContentRelatedServiceUtil
 * @generated
 */
public abstract class ContentRelatedServiceBaseImpl extends BaseServiceImpl
	implements ContentRelatedService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link es.aragon.base.migration.service.ContentRelatedServiceUtil} to access the content related remote service.
	 */

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
	 * Returns the area remote service.
	 *
	 * @return the area remote service
	 */
	public es.aragon.base.migration.service.AreaService getAreaService() {
		return areaService;
	}

	/**
	 * Sets the area remote service.
	 *
	 * @param areaService the area remote service
	 */
	public void setAreaService(
		es.aragon.base.migration.service.AreaService areaService) {
		this.areaService = areaService;
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
	 * Returns the content remote service.
	 *
	 * @return the content remote service
	 */
	public es.aragon.base.migration.service.ContentService getContentService() {
		return contentService;
	}

	/**
	 * Sets the content remote service.
	 *
	 * @param contentService the content remote service
	 */
	public void setContentService(
		es.aragon.base.migration.service.ContentService contentService) {
		this.contentService = contentService;
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
	 * Returns the content image remote service.
	 *
	 * @return the content image remote service
	 */
	public es.aragon.base.migration.service.ContentImageService getContentImageService() {
		return contentImageService;
	}

	/**
	 * Sets the content image remote service.
	 *
	 * @param contentImageService the content image remote service
	 */
	public void setContentImageService(
		es.aragon.base.migration.service.ContentImageService contentImageService) {
		this.contentImageService = contentImageService;
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
	 * Returns the content metadata remote service.
	 *
	 * @return the content metadata remote service
	 */
	public es.aragon.base.migration.service.ContentMetadataService getContentMetadataService() {
		return contentMetadataService;
	}

	/**
	 * Sets the content metadata remote service.
	 *
	 * @param contentMetadataService the content metadata remote service
	 */
	public void setContentMetadataService(
		es.aragon.base.migration.service.ContentMetadataService contentMetadataService) {
		this.contentMetadataService = contentMetadataService;
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
	 * Returns the content related remote service.
	 *
	 * @return the content related remote service
	 */
	public ContentRelatedService getContentRelatedService() {
		return contentRelatedService;
	}

	/**
	 * Sets the content related remote service.
	 *
	 * @param contentRelatedService the content related remote service
	 */
	public void setContentRelatedService(
		ContentRelatedService contentRelatedService) {
		this.contentRelatedService = contentRelatedService;
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
	 * Returns the content url remote service.
	 *
	 * @return the content url remote service
	 */
	public es.aragon.base.migration.service.ContentUrlService getContentUrlService() {
		return contentUrlService;
	}

	/**
	 * Sets the content url remote service.
	 *
	 * @param contentUrlService the content url remote service
	 */
	public void setContentUrlService(
		es.aragon.base.migration.service.ContentUrlService contentUrlService) {
		this.contentUrlService = contentUrlService;
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
	public es.aragon.base.migration.service.RuleLocalService getRuleLocalService() {
		return ruleLocalService;
	}

	/**
	 * Sets the rule local service.
	 *
	 * @param ruleLocalService the rule local service
	 */
	public void setRuleLocalService(
		es.aragon.base.migration.service.RuleLocalService ruleLocalService) {
		this.ruleLocalService = ruleLocalService;
	}

	/**
	 * Returns the rule remote service.
	 *
	 * @return the rule remote service
	 */
	public es.aragon.base.migration.service.RuleService getRuleService() {
		return ruleService;
	}

	/**
	 * Sets the rule remote service.
	 *
	 * @param ruleService the rule remote service
	 */
	public void setRuleService(
		es.aragon.base.migration.service.RuleService ruleService) {
		this.ruleService = ruleService;
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
	 * Returns the template remote service.
	 *
	 * @return the template remote service
	 */
	public es.aragon.base.migration.service.TemplateService getTemplateService() {
		return templateService;
	}

	/**
	 * Sets the template remote service.
	 *
	 * @param templateService the template remote service
	 */
	public void setTemplateService(
		es.aragon.base.migration.service.TemplateService templateService) {
		this.templateService = templateService;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
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
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ContentRelatedService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ContentRelated.class;
	}

	protected String getModelClassName() {
		return ContentRelated.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = contentRelatedPersistence.getDataSource();

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
	@BeanReference(type = es.aragon.base.migration.service.AreaService.class)
	protected es.aragon.base.migration.service.AreaService areaService;
	@BeanReference(type = AreaPersistence.class)
	protected AreaPersistence areaPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentLocalService.class)
	protected es.aragon.base.migration.service.ContentLocalService contentLocalService;
	@BeanReference(type = es.aragon.base.migration.service.ContentService.class)
	protected es.aragon.base.migration.service.ContentService contentService;
	@BeanReference(type = ContentPersistence.class)
	protected ContentPersistence contentPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentImageLocalService.class)
	protected es.aragon.base.migration.service.ContentImageLocalService contentImageLocalService;
	@BeanReference(type = es.aragon.base.migration.service.ContentImageService.class)
	protected es.aragon.base.migration.service.ContentImageService contentImageService;
	@BeanReference(type = ContentImagePersistence.class)
	protected ContentImagePersistence contentImagePersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentMetadataLocalService.class)
	protected es.aragon.base.migration.service.ContentMetadataLocalService contentMetadataLocalService;
	@BeanReference(type = es.aragon.base.migration.service.ContentMetadataService.class)
	protected es.aragon.base.migration.service.ContentMetadataService contentMetadataService;
	@BeanReference(type = ContentMetadataPersistence.class)
	protected ContentMetadataPersistence contentMetadataPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentRelatedLocalService.class)
	protected es.aragon.base.migration.service.ContentRelatedLocalService contentRelatedLocalService;
	@BeanReference(type = ContentRelatedService.class)
	protected ContentRelatedService contentRelatedService;
	@BeanReference(type = ContentRelatedPersistence.class)
	protected ContentRelatedPersistence contentRelatedPersistence;
	@BeanReference(type = es.aragon.base.migration.service.ContentUrlLocalService.class)
	protected es.aragon.base.migration.service.ContentUrlLocalService contentUrlLocalService;
	@BeanReference(type = es.aragon.base.migration.service.ContentUrlService.class)
	protected es.aragon.base.migration.service.ContentUrlService contentUrlService;
	@BeanReference(type = ContentUrlPersistence.class)
	protected ContentUrlPersistence contentUrlPersistence;
	@BeanReference(type = es.aragon.base.migration.service.RuleLocalService.class)
	protected es.aragon.base.migration.service.RuleLocalService ruleLocalService;
	@BeanReference(type = es.aragon.base.migration.service.RuleService.class)
	protected es.aragon.base.migration.service.RuleService ruleService;
	@BeanReference(type = RulePersistence.class)
	protected RulePersistence rulePersistence;
	@BeanReference(type = es.aragon.base.migration.service.TemplateLocalService.class)
	protected es.aragon.base.migration.service.TemplateLocalService templateLocalService;
	@BeanReference(type = es.aragon.base.migration.service.TemplateService.class)
	protected es.aragon.base.migration.service.TemplateService templateService;
	@BeanReference(type = TemplatePersistence.class)
	protected TemplatePersistence templatePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}