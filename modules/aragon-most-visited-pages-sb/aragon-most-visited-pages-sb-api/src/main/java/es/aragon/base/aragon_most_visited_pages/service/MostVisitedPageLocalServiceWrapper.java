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

package es.aragon.base.aragon_most_visited_pages.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MostVisitedPageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPageLocalService
 * @generated
 */
@ProviderType
public class MostVisitedPageLocalServiceWrapper
	implements MostVisitedPageLocalService,
		ServiceWrapper<MostVisitedPageLocalService> {
	public MostVisitedPageLocalServiceWrapper(
		MostVisitedPageLocalService mostVisitedPageLocalService) {
		_mostVisitedPageLocalService = mostVisitedPageLocalService;
	}

	/**
	* Adds the most visited page to the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPage the most visited page
	* @return the most visited page that was added
	*/
	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage addMostVisitedPage(
		es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage mostVisitedPage) {
		return _mostVisitedPageLocalService.addMostVisitedPage(mostVisitedPage);
	}

	@Override
	public void addMostVisitedPage(String path, int visits, String title,
		int position) {
		_mostVisitedPageLocalService.addMostVisitedPage(path, visits, title,
			position);
	}

	/**
	* Creates a new most visited page with the primary key. Does not add the most visited page to the database.
	*
	* @param mostVisitedPageId the primary key for the new most visited page
	* @return the new most visited page
	*/
	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage createMostVisitedPage(
		long mostVisitedPageId) {
		return _mostVisitedPageLocalService.createMostVisitedPage(mostVisitedPageId);
	}

	/**
	* Deletes the most visited page with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page that was removed
	* @throws PortalException if a most visited page with the primary key could not be found
	*/
	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage deleteMostVisitedPage(
		long mostVisitedPageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mostVisitedPageLocalService.deleteMostVisitedPage(mostVisitedPageId);
	}

	/**
	* Deletes the most visited page from the database. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPage the most visited page
	* @return the most visited page that was removed
	*/
	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage deleteMostVisitedPage(
		es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage mostVisitedPage) {
		return _mostVisitedPageLocalService.deleteMostVisitedPage(mostVisitedPage);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mostVisitedPageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mostVisitedPageLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _mostVisitedPageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _mostVisitedPageLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _mostVisitedPageLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _mostVisitedPageLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _mostVisitedPageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage fetchMostVisitedPage(
		long mostVisitedPageId) {
		return _mostVisitedPageLocalService.fetchMostVisitedPage(mostVisitedPageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mostVisitedPageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mostVisitedPageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the most visited page with the primary key.
	*
	* @param mostVisitedPageId the primary key of the most visited page
	* @return the most visited page
	* @throws PortalException if a most visited page with the primary key could not be found
	*/
	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage getMostVisitedPage(
		long mostVisitedPageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mostVisitedPageLocalService.getMostVisitedPage(mostVisitedPageId);
	}

	/**
	* Returns a range of all the most visited pages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of most visited pages
	* @param end the upper bound of the range of most visited pages (not inclusive)
	* @return the range of most visited pages
	*/
	@Override
	public java.util.List<es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage> getMostVisitedPages(
		int start, int end) {
		return _mostVisitedPageLocalService.getMostVisitedPages(start, end);
	}

	/**
	* Returns the number of most visited pages.
	*
	* @return the number of most visited pages
	*/
	@Override
	public int getMostVisitedPagesCount() {
		return _mostVisitedPageLocalService.getMostVisitedPagesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _mostVisitedPageLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mostVisitedPageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the most visited page in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mostVisitedPage the most visited page
	* @return the most visited page that was updated
	*/
	@Override
	public es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage updateMostVisitedPage(
		es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage mostVisitedPage) {
		return _mostVisitedPageLocalService.updateMostVisitedPage(mostVisitedPage);
	}

	@Override
	public MostVisitedPageLocalService getWrappedService() {
		return _mostVisitedPageLocalService;
	}

	@Override
	public void setWrappedService(
		MostVisitedPageLocalService mostVisitedPageLocalService) {
		_mostVisitedPageLocalService = mostVisitedPageLocalService;
	}

	private MostVisitedPageLocalService _mostVisitedPageLocalService;
}