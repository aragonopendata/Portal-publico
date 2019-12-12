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

package es.aragon.base.semaphore.service;

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

import es.aragon.base.semaphore.exception.NoSuchSemaphoreException;
import es.aragon.base.semaphore.model.Semaphore;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Semaphore. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SemaphoreLocalServiceUtil
 * @see es.aragon.base.semaphore.service.base.SemaphoreLocalServiceBaseImpl
 * @see es.aragon.base.semaphore.service.impl.SemaphoreLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SemaphoreLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SemaphoreLocalServiceUtil} to access the semaphore local service. Add custom service methods to {@link es.aragon.base.semaphore.service.impl.SemaphoreLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Semaphore addSemaphore(long groupId, String serviceName,
		long maxUsers);

	/**
	* Adds the semaphore to the database. Also notifies the appropriate model listeners.
	*
	* @param semaphore the semaphore
	* @return the semaphore that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Semaphore addSemaphore(Semaphore semaphore);

	/**
	* Creates a new semaphore with the primary key. Does not add the semaphore to the database.
	*
	* @param semaphoreId the primary key for the new semaphore
	* @return the new semaphore
	*/
	@Transactional(enabled = false)
	public Semaphore createSemaphore(long semaphoreId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the semaphore with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore that was removed
	* @throws PortalException if a semaphore with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Semaphore deleteSemaphore(long semaphoreId)
		throws PortalException;

	/**
	* Deletes the semaphore from the database. Also notifies the appropriate model listeners.
	*
	* @param semaphore the semaphore
	* @return the semaphore that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Semaphore deleteSemaphore(Semaphore semaphore);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.semaphore.model.impl.SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.semaphore.model.impl.SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Semaphore fetchSemaphore(long semaphoreId);

	/**
	* Returns the semaphore matching the UUID and group.
	*
	* @param uuid the semaphore's UUID
	* @param groupId the primary key of the group
	* @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Semaphore fetchSemaphoreByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

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
	* Returns the semaphore with the primary key.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore
	* @throws PortalException if a semaphore with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Semaphore getSemaphore(long semaphoreId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Semaphore getSemaphore(long groupId, String serviceName)
		throws NoSuchSemaphoreException;

	/**
	* Returns the semaphore matching the UUID and group.
	*
	* @param uuid the semaphore's UUID
	* @param groupId the primary key of the group
	* @return the matching semaphore
	* @throws PortalException if a matching semaphore could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Semaphore getSemaphoreByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the semaphores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.semaphore.model.impl.SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of semaphores
	* @param end the upper bound of the range of semaphores (not inclusive)
	* @return the range of semaphores
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Semaphore> getSemaphores(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Semaphore> getSemaphores(long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Semaphore> getSemaphores(long groupId, int start, int end,
		OrderByComparator<Semaphore> orderByComparator);

	/**
	* Returns the number of semaphores.
	*
	* @return the number of semaphores
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSemaphoresCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSemaphoresCount(long groupId);

	public Boolean semaphoreSignal(long groupId, String serviceName);

	public Boolean semaphoreSignal(Semaphore inputSemaphore);

	public Boolean semaphoreWait(long groupId, String serviceName);

	public Boolean semaphoreWait(Semaphore inputSemaphore);

	/**
	* Updates the semaphore in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param semaphore the semaphore
	* @return the semaphore that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Semaphore updateSemaphore(Semaphore semaphore);
}