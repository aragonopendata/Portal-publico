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

package es.aragon.base.content_ratings.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RatingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RatingLocalService
 * @generated
 */
@ProviderType
public class RatingLocalServiceWrapper implements RatingLocalService,
	ServiceWrapper<RatingLocalService> {
	public RatingLocalServiceWrapper(RatingLocalService ratingLocalService) {
		_ratingLocalService = ratingLocalService;
	}

	@Override
	public es.aragon.base.content_ratings.model.Rating addComment(
		long companyId, long classNameId, long classPK, String comment) {
		return _ratingLocalService.addComment(companyId, classNameId, classPK,
			comment);
	}

	@Override
	public es.aragon.base.content_ratings.model.Rating addRating(
		long companyId, long classNameId, long classPK, double score) {
		return _ratingLocalService.addRating(companyId, classNameId, classPK,
			score);
	}

	/**
	* Adds the rating to the database. Also notifies the appropriate model listeners.
	*
	* @param rating the rating
	* @return the rating that was added
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating addRating(
		es.aragon.base.content_ratings.model.Rating rating) {
		return _ratingLocalService.addRating(rating);
	}

	@Override
	public double averageScore(long companyId, long classNameId, long classPK) {
		return _ratingLocalService.averageScore(companyId, classNameId, classPK);
	}

	/**
	* Creates a new rating with the primary key. Does not add the rating to the database.
	*
	* @param ratingId the primary key for the new rating
	* @return the new rating
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating createRating(
		long ratingId) {
		return _ratingLocalService.createRating(ratingId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rating with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ratingId the primary key of the rating
	* @return the rating that was removed
	* @throws PortalException if a rating with the primary key could not be found
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating deleteRating(
		long ratingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingLocalService.deleteRating(ratingId);
	}

	/**
	* Deletes the rating from the database. Also notifies the appropriate model listeners.
	*
	* @param rating the rating
	* @return the rating that was removed
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating deleteRating(
		es.aragon.base.content_ratings.model.Rating rating) {
		return _ratingLocalService.deleteRating(rating);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ratingLocalService.dynamicQuery();
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
		return _ratingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.content_ratings.model.impl.RatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ratingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.content_ratings.model.impl.RatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ratingLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _ratingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ratingLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public es.aragon.base.content_ratings.model.Rating fetchRating(
		long ratingId) {
		return _ratingLocalService.fetchRating(ratingId);
	}

	/**
	* Returns the rating with the matching UUID and company.
	*
	* @param uuid the rating's UUID
	* @param companyId the primary key of the company
	* @return the matching rating, or <code>null</code> if a matching rating could not be found
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating fetchRatingByUuidAndCompanyId(
		String uuid, long companyId) {
		return _ratingLocalService.fetchRatingByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public java.util.List<es.aragon.base.content_ratings.model.Rating> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.content_ratings.model.Rating> orderByComparator) {
		return _ratingLocalService.findByClassNameIdClassPK(classNameId,
			classPK, start, end, orderByComparator);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ratingLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<String> getComments(long companyId, long classNameId,
		long classPK) {
		return _ratingLocalService.getComments(companyId, classNameId, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ratingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _ratingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rating with the primary key.
	*
	* @param ratingId the primary key of the rating
	* @return the rating
	* @throws PortalException if a rating with the primary key could not be found
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating getRating(long ratingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingLocalService.getRating(ratingId);
	}

	/**
	* Returns the rating with the matching UUID and company.
	*
	* @param uuid the rating's UUID
	* @param companyId the primary key of the company
	* @return the matching rating
	* @throws PortalException if a matching rating could not be found
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating getRatingByUuidAndCompanyId(
		String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingLocalService.getRatingByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of all the ratings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.content_ratings.model.impl.RatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ratings
	* @param end the upper bound of the range of ratings (not inclusive)
	* @return the range of ratings
	*/
	@Override
	public java.util.List<es.aragon.base.content_ratings.model.Rating> getRatings(
		int start, int end) {
		return _ratingLocalService.getRatings(start, end);
	}

	/**
	* Returns the number of ratings.
	*
	* @return the number of ratings
	*/
	@Override
	public int getRatingsCount() {
		return _ratingLocalService.getRatingsCount();
	}

	@Override
	public int totalRatings(long companyId, long classNameId, long classPK) {
		return _ratingLocalService.totalRatings(companyId, classNameId, classPK);
	}

	/**
	* Updates the rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rating the rating
	* @return the rating that was updated
	*/
	@Override
	public es.aragon.base.content_ratings.model.Rating updateRating(
		es.aragon.base.content_ratings.model.Rating rating) {
		return _ratingLocalService.updateRating(rating);
	}

	@Override
	public RatingLocalService getWrappedService() {
		return _ratingLocalService;
	}

	@Override
	public void setWrappedService(RatingLocalService ratingLocalService) {
		_ratingLocalService = ratingLocalService;
	}

	private RatingLocalService _ratingLocalService;
}