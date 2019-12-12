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

package es.aragon.base.social_network.sb.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SocialNetwork. This utility wraps
 * {@link es.aragon.base.social_network.sb.service.impl.SocialNetworkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetworkLocalService
 * @see es.aragon.base.social_network.sb.service.base.SocialNetworkLocalServiceBaseImpl
 * @see es.aragon.base.social_network.sb.service.impl.SocialNetworkLocalServiceImpl
 * @generated
 */
@ProviderType
public class SocialNetworkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.social_network.sb.service.impl.SocialNetworkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the social network to the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was added
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork addSocialNetwork(
		es.aragon.base.social_network.sb.model.SocialNetwork socialNetwork) {
		return getService().addSocialNetwork(socialNetwork);
	}

	/**
	* Creates a new social network with the primary key. Does not add the social network to the database.
	*
	* @param socialNetworkId the primary key for the new social network
	* @return the new social network
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork createSocialNetwork(
		long socialNetworkId) {
		return getService().createSocialNetwork(socialNetworkId);
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
	* Deletes the social network with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network that was removed
	* @throws PortalException if a social network with the primary key could not be found
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork deleteSocialNetwork(
		long socialNetworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSocialNetwork(socialNetworkId);
	}

	/**
	* Deletes the social network from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was removed
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork deleteSocialNetwork(
		es.aragon.base.social_network.sb.model.SocialNetwork socialNetwork) {
		return getService().deleteSocialNetwork(socialNetwork);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static es.aragon.base.social_network.sb.model.SocialNetwork fetchSocialNetwork(
		long socialNetworkId) {
		return getService().fetchSocialNetwork(socialNetworkId);
	}

	/**
	* Returns the social network matching the UUID and group.
	*
	* @param uuid the social network's UUID
	* @param groupId the primary key of the group
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork fetchSocialNetworkByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchSocialNetworkByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	* Returns the social network with the primary key.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network
	* @throws PortalException if a social network with the primary key could not be found
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork getSocialNetwork(
		long socialNetworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSocialNetwork(socialNetworkId);
	}

	/**
	* Returns the social network matching the UUID and group.
	*
	* @param uuid the social network's UUID
	* @param groupId the primary key of the group
	* @return the matching social network
	* @throws PortalException if a matching social network could not be found
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork getSocialNetworkByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSocialNetworkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the social networks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @return the range of social networks
	*/
	public static java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworks(
		int start, int end) {
		return getService().getSocialNetworks(start, end);
	}

	public static java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByCompanyId(
		long companyId) {
		return getService().getSocialNetworksByCompanyId(companyId);
	}

	public static java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByCompanyIdAndGroupId(
		long companyId, long groupId) {
		return getService()
				   .getSocialNetworksByCompanyIdAndGroupId(companyId, groupId);
	}

	/**
	* Returns all the social networks matching the UUID and company.
	*
	* @param uuid the UUID of the social networks
	* @param companyId the primary key of the company
	* @return the matching social networks, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getSocialNetworksByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of social networks matching the UUID and company.
	*
	* @param uuid the UUID of the social networks
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching social networks, or an empty list if no matches were found
	*/
	public static java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.social_network.sb.model.SocialNetwork> orderByComparator) {
		return getService()
				   .getSocialNetworksByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of social networks.
	*
	* @return the number of social networks
	*/
	public static int getSocialNetworksCount() {
		return getService().getSocialNetworksCount();
	}

	/**
	* Updates the social network in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was updated
	*/
	public static es.aragon.base.social_network.sb.model.SocialNetwork updateSocialNetwork(
		es.aragon.base.social_network.sb.model.SocialNetwork socialNetwork) {
		return getService().updateSocialNetwork(socialNetwork);
	}

	public static SocialNetworkLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SocialNetworkLocalService, SocialNetworkLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SocialNetworkLocalService.class);

		ServiceTracker<SocialNetworkLocalService, SocialNetworkLocalService> serviceTracker =
			new ServiceTracker<SocialNetworkLocalService, SocialNetworkLocalService>(bundle.getBundleContext(),
				SocialNetworkLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}