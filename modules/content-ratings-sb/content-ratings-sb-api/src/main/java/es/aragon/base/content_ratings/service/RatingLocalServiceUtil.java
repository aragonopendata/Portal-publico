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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Rating. This utility wraps
 * {@link es.aragon.base.content_ratings.service.impl.RatingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RatingLocalService
 * @see es.aragon.base.content_ratings.service.base.RatingLocalServiceBaseImpl
 * @see es.aragon.base.content_ratings.service.impl.RatingLocalServiceImpl
 * @generated
 */
@ProviderType
public class RatingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.content_ratings.service.impl.RatingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static es.aragon.base.content_ratings.model.Rating addComment(
		long companyId, long classNameId, long classPK, String comment) {
		return getService().addComment(companyId, classNameId, classPK, comment);
	}

	public static es.aragon.base.content_ratings.model.Rating addRating(
		long companyId, long classNameId, long classPK, double score) {
		return getService().addRating(companyId, classNameId, classPK, score);
	}

	/**
	* Adds the rating to the database. Also notifies the appropriate model listeners.
	*
	* @param rating the rating
	* @return the rating that was added
	*/
	public static es.aragon.base.content_ratings.model.Rating addRating(
		es.aragon.base.content_ratings.model.Rating rating) {
		return getService().addRating(rating);
	}

	public static double averageScore(long companyId, long classNameId,
		long classPK) {
		return getService().averageScore(companyId, classNameId, classPK);
	}

	/**
	* Creates a new rating with the primary key. Does not add the rating to the database.
	*
	* @param ratingId the primary key for the new rating
	* @return the new rating
	*/
	public static es.aragon.base.content_ratings.model.Rating createRating(
		long ratingId) {
		return getService().createRating(ratingId);
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
	* Deletes the rating with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ratingId the primary key of the rating
	* @return the rating that was removed
	* @throws PortalException if a rating with the primary key could not be found
	*/
	public static es.aragon.base.content_ratings.model.Rating deleteRating(
		long ratingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRating(ratingId);
	}

	/**
	* Deletes the rating from the database. Also notifies the appropriate model listeners.
	*
	* @param rating the rating
	* @return the rating that was removed
	*/
	public static es.aragon.base.content_ratings.model.Rating deleteRating(
		es.aragon.base.content_ratings.model.Rating rating) {
		return getService().deleteRating(rating);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.content_ratings.model.impl.RatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.content_ratings.model.impl.RatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.content_ratings.model.Rating fetchRating(
		long ratingId) {
		return getService().fetchRating(ratingId);
	}

	/**
	* Returns the rating with the matching UUID and company.
	*
	* @param uuid the rating's UUID
	* @param companyId the primary key of the company
	* @return the matching rating, or <code>null</code> if a matching rating could not be found
	*/
	public static es.aragon.base.content_ratings.model.Rating fetchRatingByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().fetchRatingByUuidAndCompanyId(uuid, companyId);
	}

	public static java.util.List<es.aragon.base.content_ratings.model.Rating> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.content_ratings.model.Rating> orderByComparator) {
		return getService()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<String> getComments(long companyId,
		long classNameId, long classPK) {
		return getService().getComments(companyId, classNameId, classPK);
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
	* Returns the rating with the primary key.
	*
	* @param ratingId the primary key of the rating
	* @return the rating
	* @throws PortalException if a rating with the primary key could not be found
	*/
	public static es.aragon.base.content_ratings.model.Rating getRating(
		long ratingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRating(ratingId);
	}

	/**
	* Returns the rating with the matching UUID and company.
	*
	* @param uuid the rating's UUID
	* @param companyId the primary key of the company
	* @return the matching rating
	* @throws PortalException if a matching rating could not be found
	*/
	public static es.aragon.base.content_ratings.model.Rating getRatingByUuidAndCompanyId(
		String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRatingByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<es.aragon.base.content_ratings.model.Rating> getRatings(
		int start, int end) {
		return getService().getRatings(start, end);
	}

	/**
	* Returns the number of ratings.
	*
	* @return the number of ratings
	*/
	public static int getRatingsCount() {
		return getService().getRatingsCount();
	}

	public static int totalRatings(long companyId, long classNameId,
		long classPK) {
		return getService().totalRatings(companyId, classNameId, classPK);
	}

	/**
	* Updates the rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rating the rating
	* @return the rating that was updated
	*/
	public static es.aragon.base.content_ratings.model.Rating updateRating(
		es.aragon.base.content_ratings.model.Rating rating) {
		return getService().updateRating(rating);
	}

	public static RatingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RatingLocalService, RatingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RatingLocalService.class);

		ServiceTracker<RatingLocalService, RatingLocalService> serviceTracker = new ServiceTracker<RatingLocalService, RatingLocalService>(bundle.getBundleContext(),
				RatingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}