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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Semaphore. This utility wraps
 * {@link es.aragon.base.semaphore.service.impl.SemaphoreLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SemaphoreLocalService
 * @see es.aragon.base.semaphore.service.base.SemaphoreLocalServiceBaseImpl
 * @see es.aragon.base.semaphore.service.impl.SemaphoreLocalServiceImpl
 * @generated
 */
@ProviderType
public class SemaphoreLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.semaphore.service.impl.SemaphoreLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static es.aragon.base.semaphore.model.Semaphore addSemaphore(
		long groupId, String serviceName, long maxUsers) {
		return getService().addSemaphore(groupId, serviceName, maxUsers);
	}

	/**
	* Adds the semaphore to the database. Also notifies the appropriate model listeners.
	*
	* @param semaphore the semaphore
	* @return the semaphore that was added
	*/
	public static es.aragon.base.semaphore.model.Semaphore addSemaphore(
		es.aragon.base.semaphore.model.Semaphore semaphore) {
		return getService().addSemaphore(semaphore);
	}

	/**
	* Creates a new semaphore with the primary key. Does not add the semaphore to the database.
	*
	* @param semaphoreId the primary key for the new semaphore
	* @return the new semaphore
	*/
	public static es.aragon.base.semaphore.model.Semaphore createSemaphore(
		long semaphoreId) {
		return getService().createSemaphore(semaphoreId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the semaphore with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore that was removed
	* @throws PortalException if a semaphore with the primary key could not be found
	*/
	public static es.aragon.base.semaphore.model.Semaphore deleteSemaphore(
		long semaphoreId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSemaphore(semaphoreId);
	}

	/**
	* Deletes the semaphore from the database. Also notifies the appropriate model listeners.
	*
	* @param semaphore the semaphore
	* @return the semaphore that was removed
	*/
	public static es.aragon.base.semaphore.model.Semaphore deleteSemaphore(
		es.aragon.base.semaphore.model.Semaphore semaphore) {
		return getService().deleteSemaphore(semaphore);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static es.aragon.base.semaphore.model.Semaphore fetchSemaphore(
		long semaphoreId) {
		return getService().fetchSemaphore(semaphoreId);
	}

	/**
	* Returns the semaphore matching the UUID and group.
	*
	* @param uuid the semaphore's UUID
	* @param groupId the primary key of the group
	* @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	*/
	public static es.aragon.base.semaphore.model.Semaphore fetchSemaphoreByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchSemaphoreByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the semaphore with the primary key.
	*
	* @param semaphoreId the primary key of the semaphore
	* @return the semaphore
	* @throws PortalException if a semaphore with the primary key could not be found
	*/
	public static es.aragon.base.semaphore.model.Semaphore getSemaphore(
		long semaphoreId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSemaphore(semaphoreId);
	}

	public static es.aragon.base.semaphore.model.Semaphore getSemaphore(
		long groupId, String serviceName)
		throws es.aragon.base.semaphore.exception.NoSuchSemaphoreException {
		return getService().getSemaphore(groupId, serviceName);
	}

	/**
	* Returns the semaphore matching the UUID and group.
	*
	* @param uuid the semaphore's UUID
	* @param groupId the primary key of the group
	* @return the matching semaphore
	* @throws PortalException if a matching semaphore could not be found
	*/
	public static es.aragon.base.semaphore.model.Semaphore getSemaphoreByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSemaphoreByUuidAndGroupId(uuid, groupId);
	}

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
	public static java.util.List<es.aragon.base.semaphore.model.Semaphore> getSemaphores(
		int start, int end) {
		return getService().getSemaphores(start, end);
	}

	public static java.util.List<es.aragon.base.semaphore.model.Semaphore> getSemaphores(
		long groupId, int start, int end) {
		return getService().getSemaphores(groupId, start, end);
	}

	public static java.util.List<es.aragon.base.semaphore.model.Semaphore> getSemaphores(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.semaphore.model.Semaphore> orderByComparator) {
		return getService().getSemaphores(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the number of semaphores.
	*
	* @return the number of semaphores
	*/
	public static int getSemaphoresCount() {
		return getService().getSemaphoresCount();
	}

	public static int getSemaphoresCount(long groupId) {
		return getService().getSemaphoresCount(groupId);
	}

	public static Boolean semaphoreSignal(long groupId, String serviceName) {
		return getService().semaphoreSignal(groupId, serviceName);
	}

	public static Boolean semaphoreSignal(
		es.aragon.base.semaphore.model.Semaphore inputSemaphore) {
		return getService().semaphoreSignal(inputSemaphore);
	}

	public static Boolean semaphoreWait(long groupId, String serviceName) {
		return getService().semaphoreWait(groupId, serviceName);
	}

	public static Boolean semaphoreWait(
		es.aragon.base.semaphore.model.Semaphore inputSemaphore) {
		return getService().semaphoreWait(inputSemaphore);
	}

	/**
	* Updates the semaphore in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param semaphore the semaphore
	* @return the semaphore that was updated
	*/
	public static es.aragon.base.semaphore.model.Semaphore updateSemaphore(
		es.aragon.base.semaphore.model.Semaphore semaphore) {
		return getService().updateSemaphore(semaphore);
	}

	public static SemaphoreLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SemaphoreLocalService, SemaphoreLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SemaphoreLocalService.class);

		ServiceTracker<SemaphoreLocalService, SemaphoreLocalService> serviceTracker =
			new ServiceTracker<SemaphoreLocalService, SemaphoreLocalService>(bundle.getBundleContext(),
				SemaphoreLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}