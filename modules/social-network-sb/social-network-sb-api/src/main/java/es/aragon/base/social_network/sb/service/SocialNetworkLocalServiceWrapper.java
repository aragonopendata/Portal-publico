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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SocialNetworkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetworkLocalService
 * @generated
 */
@ProviderType
public class SocialNetworkLocalServiceWrapper
	implements SocialNetworkLocalService,
		ServiceWrapper<SocialNetworkLocalService> {
	public SocialNetworkLocalServiceWrapper(
		SocialNetworkLocalService socialNetworkLocalService) {
		_socialNetworkLocalService = socialNetworkLocalService;
	}

	/**
	* Adds the social network to the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was added
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork addSocialNetwork(
		es.aragon.base.social_network.sb.model.SocialNetwork socialNetwork) {
		return _socialNetworkLocalService.addSocialNetwork(socialNetwork);
	}

	/**
	* Creates a new social network with the primary key. Does not add the social network to the database.
	*
	* @param socialNetworkId the primary key for the new social network
	* @return the new social network
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork createSocialNetwork(
		long socialNetworkId) {
		return _socialNetworkLocalService.createSocialNetwork(socialNetworkId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialNetworkLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the social network with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network that was removed
	* @throws PortalException if a social network with the primary key could not be found
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork deleteSocialNetwork(
		long socialNetworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialNetworkLocalService.deleteSocialNetwork(socialNetworkId);
	}

	/**
	* Deletes the social network from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was removed
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork deleteSocialNetwork(
		es.aragon.base.social_network.sb.model.SocialNetwork socialNetwork) {
		return _socialNetworkLocalService.deleteSocialNetwork(socialNetwork);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _socialNetworkLocalService.dynamicQuery();
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
		return _socialNetworkLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _socialNetworkLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _socialNetworkLocalService.dynamicQuery(dynamicQuery, start,
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
		return _socialNetworkLocalService.dynamicQueryCount(dynamicQuery);
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
		return _socialNetworkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork fetchSocialNetwork(
		long socialNetworkId) {
		return _socialNetworkLocalService.fetchSocialNetwork(socialNetworkId);
	}

	/**
	* Returns the social network matching the UUID and group.
	*
	* @param uuid the social network's UUID
	* @param groupId the primary key of the group
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork fetchSocialNetworkByUuidAndGroupId(
		String uuid, long groupId) {
		return _socialNetworkLocalService.fetchSocialNetworkByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _socialNetworkLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _socialNetworkLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _socialNetworkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _socialNetworkLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialNetworkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the social network with the primary key.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network
	* @throws PortalException if a social network with the primary key could not be found
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork getSocialNetwork(
		long socialNetworkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialNetworkLocalService.getSocialNetwork(socialNetworkId);
	}

	/**
	* Returns the social network matching the UUID and group.
	*
	* @param uuid the social network's UUID
	* @param groupId the primary key of the group
	* @return the matching social network
	* @throws PortalException if a matching social network could not be found
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork getSocialNetworkByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialNetworkLocalService.getSocialNetworkByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworks(
		int start, int end) {
		return _socialNetworkLocalService.getSocialNetworks(start, end);
	}

	@Override
	public java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByCompanyId(
		long companyId) {
		return _socialNetworkLocalService.getSocialNetworksByCompanyId(companyId);
	}

	@Override
	public java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByCompanyIdAndGroupId(
		long companyId, long groupId) {
		return _socialNetworkLocalService.getSocialNetworksByCompanyIdAndGroupId(companyId,
			groupId);
	}

	/**
	* Returns all the social networks matching the UUID and company.
	*
	* @param uuid the UUID of the social networks
	* @param companyId the primary key of the company
	* @return the matching social networks, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByUuidAndCompanyId(
		String uuid, long companyId) {
		return _socialNetworkLocalService.getSocialNetworksByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork> getSocialNetworksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.social_network.sb.model.SocialNetwork> orderByComparator) {
		return _socialNetworkLocalService.getSocialNetworksByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of social networks.
	*
	* @return the number of social networks
	*/
	@Override
	public int getSocialNetworksCount() {
		return _socialNetworkLocalService.getSocialNetworksCount();
	}

	/**
	* Updates the social network in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was updated
	*/
	@Override
	public es.aragon.base.social_network.sb.model.SocialNetwork updateSocialNetwork(
		es.aragon.base.social_network.sb.model.SocialNetwork socialNetwork) {
		return _socialNetworkLocalService.updateSocialNetwork(socialNetwork);
	}

	@Override
	public SocialNetworkLocalService getWrappedService() {
		return _socialNetworkLocalService;
	}

	@Override
	public void setWrappedService(
		SocialNetworkLocalService socialNetworkLocalService) {
		_socialNetworkLocalService = socialNetworkLocalService;
	}

	private SocialNetworkLocalService _socialNetworkLocalService;
}