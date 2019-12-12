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

package es.aragon.base.social_network.sb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.social_network.sb.model.SocialNetwork;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the social network service. This utility wraps {@link es.aragon.base.social_network.sb.service.persistence.impl.SocialNetworkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetworkPersistence
 * @see es.aragon.base.social_network.sb.service.persistence.impl.SocialNetworkPersistenceImpl
 * @generated
 */
@ProviderType
public class SocialNetworkUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SocialNetwork socialNetwork) {
		getPersistence().clearCache(socialNetwork);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SocialNetwork> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SocialNetwork> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SocialNetwork> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SocialNetwork update(SocialNetwork socialNetwork) {
		return getPersistence().update(socialNetwork);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SocialNetwork update(SocialNetwork socialNetwork,
		ServiceContext serviceContext) {
		return getPersistence().update(socialNetwork, serviceContext);
	}

	/**
	* Returns all the social networks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching social networks
	*/
	public static List<SocialNetwork> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the social networks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @return the range of matching social networks
	*/
	public static List<SocialNetwork> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the social networks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByUuid(String uuid, int start,
		int end, OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the social networks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByUuid(String uuid, int start,
		int end, OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByUuid_First(String uuid,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByUuid_First(String uuid,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByUuid_Last(String uuid,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByUuid_Last(String uuid,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the social networks before and after the current social network in the ordered set where uuid = &#63;.
	*
	* @param socialNetworkId the primary key of the current social network
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public static SocialNetwork[] findByUuid_PrevAndNext(long socialNetworkId,
		String uuid, OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByUuid_PrevAndNext(socialNetworkId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the social networks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of social networks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching social networks
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the social network where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSocialNetworkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByUUID_G(String uuid, long groupId)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the social network where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the social network where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the social network where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the social network that was removed
	*/
	public static SocialNetwork removeByUUID_G(String uuid, long groupId)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of social networks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching social networks
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the social networks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching social networks
	*/
	public static List<SocialNetwork> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the social networks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @return the range of matching social networks
	*/
	public static List<SocialNetwork> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the social networks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the social networks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the social networks before and after the current social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param socialNetworkId the primary key of the current social network
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public static SocialNetwork[] findByUuid_C_PrevAndNext(
		long socialNetworkId, String uuid, long companyId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(socialNetworkId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the social networks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of social networks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching social networks
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the social networks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching social networks
	*/
	public static List<SocialNetwork> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the social networks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @return the range of matching social networks
	*/
	public static List<SocialNetwork> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the social networks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the social networks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByCompanyId_First(long companyId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByCompanyId_First(long companyId,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByCompanyId_Last(long companyId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByCompanyId_Last(long companyId,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the social networks before and after the current social network in the ordered set where companyId = &#63;.
	*
	* @param socialNetworkId the primary key of the current social network
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public static SocialNetwork[] findByCompanyId_PrevAndNext(
		long socialNetworkId, long companyId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(socialNetworkId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the social networks where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of social networks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching social networks
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the social networks where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching social networks
	*/
	public static List<SocialNetwork> findByCompanyIdGroupId(long companyId,
		long groupId) {
		return getPersistence().findByCompanyIdGroupId(companyId, groupId);
	}

	/**
	* Returns a range of all the social networks where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @return the range of matching social networks
	*/
	public static List<SocialNetwork> findByCompanyIdGroupId(long companyId,
		long groupId, int start, int end) {
		return getPersistence()
				   .findByCompanyIdGroupId(companyId, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the social networks where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByCompanyIdGroupId(long companyId,
		long groupId, int start, int end,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .findByCompanyIdGroupId(companyId, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the social networks where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching social networks
	*/
	public static List<SocialNetwork> findByCompanyIdGroupId(long companyId,
		long groupId, int start, int end,
		OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyIdGroupId(companyId, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByCompanyIdGroupId_First(long companyId,
		long groupId, OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByCompanyIdGroupId_First(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the first social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByCompanyIdGroupId_First(long companyId,
		long groupId, OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyIdGroupId_First(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public static SocialNetwork findByCompanyIdGroupId_Last(long companyId,
		long groupId, OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByCompanyIdGroupId_Last(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public static SocialNetwork fetchByCompanyIdGroupId_Last(long companyId,
		long groupId, OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyIdGroupId_Last(companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the social networks before and after the current social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param socialNetworkId the primary key of the current social network
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public static SocialNetwork[] findByCompanyIdGroupId_PrevAndNext(
		long socialNetworkId, long companyId, long groupId,
		OrderByComparator<SocialNetwork> orderByComparator)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence()
				   .findByCompanyIdGroupId_PrevAndNext(socialNetworkId,
			companyId, groupId, orderByComparator);
	}

	/**
	* Removes all the social networks where companyId = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public static void removeByCompanyIdGroupId(long companyId, long groupId) {
		getPersistence().removeByCompanyIdGroupId(companyId, groupId);
	}

	/**
	* Returns the number of social networks where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching social networks
	*/
	public static int countByCompanyIdGroupId(long companyId, long groupId) {
		return getPersistence().countByCompanyIdGroupId(companyId, groupId);
	}

	/**
	* Caches the social network in the entity cache if it is enabled.
	*
	* @param socialNetwork the social network
	*/
	public static void cacheResult(SocialNetwork socialNetwork) {
		getPersistence().cacheResult(socialNetwork);
	}

	/**
	* Caches the social networks in the entity cache if it is enabled.
	*
	* @param socialNetworks the social networks
	*/
	public static void cacheResult(List<SocialNetwork> socialNetworks) {
		getPersistence().cacheResult(socialNetworks);
	}

	/**
	* Creates a new social network with the primary key. Does not add the social network to the database.
	*
	* @param socialNetworkId the primary key for the new social network
	* @return the new social network
	*/
	public static SocialNetwork create(long socialNetworkId) {
		return getPersistence().create(socialNetworkId);
	}

	/**
	* Removes the social network with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network that was removed
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public static SocialNetwork remove(long socialNetworkId)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence().remove(socialNetworkId);
	}

	public static SocialNetwork updateImpl(SocialNetwork socialNetwork) {
		return getPersistence().updateImpl(socialNetwork);
	}

	/**
	* Returns the social network with the primary key or throws a {@link NoSuchSocialNetworkException} if it could not be found.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public static SocialNetwork findByPrimaryKey(long socialNetworkId)
		throws es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException {
		return getPersistence().findByPrimaryKey(socialNetworkId);
	}

	/**
	* Returns the social network with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network, or <code>null</code> if a social network with the primary key could not be found
	*/
	public static SocialNetwork fetchByPrimaryKey(long socialNetworkId) {
		return getPersistence().fetchByPrimaryKey(socialNetworkId);
	}

	public static java.util.Map<java.io.Serializable, SocialNetwork> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the social networks.
	*
	* @return the social networks
	*/
	public static List<SocialNetwork> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the social networks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @return the range of social networks
	*/
	public static List<SocialNetwork> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the social networks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social networks
	*/
	public static List<SocialNetwork> findAll(int start, int end,
		OrderByComparator<SocialNetwork> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the social networks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SocialNetworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social networks
	* @param end the upper bound of the range of social networks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of social networks
	*/
	public static List<SocialNetwork> findAll(int start, int end,
		OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the social networks from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of social networks.
	*
	* @return the number of social networks
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SocialNetworkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SocialNetworkPersistence, SocialNetworkPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SocialNetworkPersistence.class);

		ServiceTracker<SocialNetworkPersistence, SocialNetworkPersistence> serviceTracker =
			new ServiceTracker<SocialNetworkPersistence, SocialNetworkPersistence>(bundle.getBundleContext(),
				SocialNetworkPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}