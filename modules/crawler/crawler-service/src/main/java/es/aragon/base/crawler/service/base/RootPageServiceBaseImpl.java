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

package es.aragon.base.crawler.service.base;

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

import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.RootPageService;
import es.aragon.base.crawler.service.persistence.PagePersistence;
import es.aragon.base.crawler.service.persistence.RootPagePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the root page remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link es.aragon.base.crawler.service.impl.RootPageServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.crawler.service.impl.RootPageServiceImpl
 * @see es.aragon.base.crawler.service.RootPageServiceUtil
 * @generated
 */
public abstract class RootPageServiceBaseImpl extends BaseServiceImpl
	implements RootPageService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link es.aragon.base.crawler.service.RootPageServiceUtil} to access the root page remote service.
	 */

	/**
	 * Returns the page local service.
	 *
	 * @return the page local service
	 */
	public es.aragon.base.crawler.service.PageLocalService getPageLocalService() {
		return pageLocalService;
	}

	/**
	 * Sets the page local service.
	 *
	 * @param pageLocalService the page local service
	 */
	public void setPageLocalService(
		es.aragon.base.crawler.service.PageLocalService pageLocalService) {
		this.pageLocalService = pageLocalService;
	}

	/**
	 * Returns the page remote service.
	 *
	 * @return the page remote service
	 */
	public es.aragon.base.crawler.service.PageService getPageService() {
		return pageService;
	}

	/**
	 * Sets the page remote service.
	 *
	 * @param pageService the page remote service
	 */
	public void setPageService(
		es.aragon.base.crawler.service.PageService pageService) {
		this.pageService = pageService;
	}

	/**
	 * Returns the page persistence.
	 *
	 * @return the page persistence
	 */
	public PagePersistence getPagePersistence() {
		return pagePersistence;
	}

	/**
	 * Sets the page persistence.
	 *
	 * @param pagePersistence the page persistence
	 */
	public void setPagePersistence(PagePersistence pagePersistence) {
		this.pagePersistence = pagePersistence;
	}

	/**
	 * Returns the root page local service.
	 *
	 * @return the root page local service
	 */
	public es.aragon.base.crawler.service.RootPageLocalService getRootPageLocalService() {
		return rootPageLocalService;
	}

	/**
	 * Sets the root page local service.
	 *
	 * @param rootPageLocalService the root page local service
	 */
	public void setRootPageLocalService(
		es.aragon.base.crawler.service.RootPageLocalService rootPageLocalService) {
		this.rootPageLocalService = rootPageLocalService;
	}

	/**
	 * Returns the root page remote service.
	 *
	 * @return the root page remote service
	 */
	public RootPageService getRootPageService() {
		return rootPageService;
	}

	/**
	 * Sets the root page remote service.
	 *
	 * @param rootPageService the root page remote service
	 */
	public void setRootPageService(RootPageService rootPageService) {
		this.rootPageService = rootPageService;
	}

	/**
	 * Returns the root page persistence.
	 *
	 * @return the root page persistence
	 */
	public RootPagePersistence getRootPagePersistence() {
		return rootPagePersistence;
	}

	/**
	 * Sets the root page persistence.
	 *
	 * @param rootPagePersistence the root page persistence
	 */
	public void setRootPagePersistence(RootPagePersistence rootPagePersistence) {
		this.rootPagePersistence = rootPagePersistence;
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
		return RootPageService.class.getName();
	}

	protected Class<?> getModelClass() {
		return RootPage.class;
	}

	protected String getModelClassName() {
		return RootPage.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = rootPagePersistence.getDataSource();

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

	@BeanReference(type = es.aragon.base.crawler.service.PageLocalService.class)
	protected es.aragon.base.crawler.service.PageLocalService pageLocalService;
	@BeanReference(type = es.aragon.base.crawler.service.PageService.class)
	protected es.aragon.base.crawler.service.PageService pageService;
	@BeanReference(type = PagePersistence.class)
	protected PagePersistence pagePersistence;
	@BeanReference(type = es.aragon.base.crawler.service.RootPageLocalService.class)
	protected es.aragon.base.crawler.service.RootPageLocalService rootPageLocalService;
	@BeanReference(type = RootPageService.class)
	protected RootPageService rootPageService;
	@BeanReference(type = RootPagePersistence.class)
	protected RootPagePersistence rootPagePersistence;
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