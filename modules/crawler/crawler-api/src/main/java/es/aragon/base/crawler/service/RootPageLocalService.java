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

package es.aragon.base.crawler.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.crawler.model.RootPage;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for RootPage. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see RootPageLocalServiceUtil
 * @see es.aragon.base.crawler.service.base.RootPageLocalServiceBaseImpl
 * @see es.aragon.base.crawler.service.impl.RootPageLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RootPageLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RootPageLocalServiceUtil} to access the root page local service. Add custom service methods to {@link es.aragon.base.crawler.service.impl.RootPageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the root page to the database. Also notifies the appropriate model listeners.
	*
	* @param rootPage the root page
	* @return the root page that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public RootPage addRootPage(RootPage rootPage);

	public RootPage createRootPage();

	/**
	* Creates a new root page with the primary key. Does not add the root page to the database.
	*
	* @param rootPageId the primary key for the new root page
	* @return the new root page
	*/
	@Transactional(enabled = false)
	public RootPage createRootPage(long rootPageId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the root page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page that was removed
	* @throws PortalException if a root page with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public RootPage deleteRootPage(long rootPageId) throws PortalException;

	/**
	* Deletes the root page from the database. Also notifies the appropriate model listeners.
	*
	* @param rootPage the root page
	* @return the root page that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public RootPage deleteRootPage(RootPage rootPage);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RootPage fetchRootPage(long rootPageId);

	/**
	* Returns the root page matching the UUID and group.
	*
	* @param uuid the root page's UUID
	* @param groupId the primary key of the group
	* @return the matching root page, or <code>null</code> if a matching root page could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RootPage fetchRootPageByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getAlias(long pageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Date getCrawledDate(long pageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getInclusionRules(long pageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the root page with the primary key.
	*
	* @param rootPageId the primary key of the root page
	* @return the root page
	* @throws PortalException if a root page with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RootPage getRootPage(long rootPageId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RootPage getRootPageByPageId(long pageId);

	/**
	* Returns the root page matching the UUID and group.
	*
	* @param uuid the root page's UUID
	* @param groupId the primary key of the group
	* @return the matching root page
	* @throws PortalException if a matching root page could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RootPage getRootPageByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getRootPageId(long pageId);

	/**
	* Returns a range of all the root pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.crawler.model.impl.RootPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @return the range of root pages
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RootPage> getRootPages(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RootPage> getRootPages(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RootPage> getRootPagesByScheduledCrawl(boolean scheduledCrawl);

	/**
	* Returns all the root pages matching the UUID and company.
	*
	* @param uuid the UUID of the root pages
	* @param companyId the primary key of the company
	* @return the matching root pages, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RootPage> getRootPagesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of root pages matching the UUID and company.
	*
	* @param uuid the UUID of the root pages
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of root pages
	* @param end the upper bound of the range of root pages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching root pages, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RootPage> getRootPagesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<RootPage> orderByComparator);

	/**
	* Returns the number of root pages.
	*
	* @return the number of root pages
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRootPagesCount();

	/**
	* Updates the root page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rootPage the root page
	* @return the root page that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public RootPage updateRootPage(RootPage rootPage);
}