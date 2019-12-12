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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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

import es.aragon.base.social_network.sb.model.SocialNetwork;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SocialNetwork. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetworkLocalServiceUtil
 * @see es.aragon.base.social_network.sb.service.base.SocialNetworkLocalServiceBaseImpl
 * @see es.aragon.base.social_network.sb.service.impl.SocialNetworkLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SocialNetworkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialNetworkLocalServiceUtil} to access the social network local service. Add custom service methods to {@link es.aragon.base.social_network.sb.service.impl.SocialNetworkLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the social network to the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SocialNetwork addSocialNetwork(SocialNetwork socialNetwork);

	/**
	* Creates a new social network with the primary key. Does not add the social network to the database.
	*
	* @param socialNetworkId the primary key for the new social network
	* @return the new social network
	*/
	@Transactional(enabled = false)
	public SocialNetwork createSocialNetwork(long socialNetworkId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the social network with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network that was removed
	* @throws PortalException if a social network with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SocialNetwork deleteSocialNetwork(long socialNetworkId)
		throws PortalException;

	/**
	* Deletes the social network from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SocialNetwork deleteSocialNetwork(SocialNetwork socialNetwork);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public SocialNetwork fetchSocialNetwork(long socialNetworkId);

	/**
	* Returns the social network matching the UUID and group.
	*
	* @param uuid the social network's UUID
	* @param groupId the primary key of the group
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialNetwork fetchSocialNetworkByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Returns the social network with the primary key.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network
	* @throws PortalException if a social network with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialNetwork getSocialNetwork(long socialNetworkId)
		throws PortalException;

	/**
	* Returns the social network matching the UUID and group.
	*
	* @param uuid the social network's UUID
	* @param groupId the primary key of the group
	* @return the matching social network
	* @throws PortalException if a matching social network could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialNetwork getSocialNetworkByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialNetwork> getSocialNetworks(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialNetwork> getSocialNetworksByCompanyId(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialNetwork> getSocialNetworksByCompanyIdAndGroupId(
		long companyId, long groupId);

	/**
	* Returns all the social networks matching the UUID and company.
	*
	* @param uuid the UUID of the social networks
	* @param companyId the primary key of the company
	* @return the matching social networks, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialNetwork> getSocialNetworksByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialNetwork> getSocialNetworksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the number of social networks.
	*
	* @return the number of social networks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialNetworksCount();

	/**
	* Updates the social network in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialNetwork the social network
	* @return the social network that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SocialNetwork updateSocialNetwork(SocialNetwork socialNetwork);
}