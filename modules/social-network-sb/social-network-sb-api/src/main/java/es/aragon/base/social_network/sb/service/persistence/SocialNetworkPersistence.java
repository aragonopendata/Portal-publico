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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.social_network.sb.exception.NoSuchSocialNetworkException;
import es.aragon.base.social_network.sb.model.SocialNetwork;

/**
 * The persistence interface for the social network service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.social_network.sb.service.persistence.impl.SocialNetworkPersistenceImpl
 * @see SocialNetworkUtil
 * @generated
 */
@ProviderType
public interface SocialNetworkPersistence extends BasePersistence<SocialNetwork> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialNetworkUtil} to access the social network persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the social networks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching social networks
	*/
	public java.util.List<SocialNetwork> findByUuid(String uuid);

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
	public java.util.List<SocialNetwork> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<SocialNetwork> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public java.util.List<SocialNetwork> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the first social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the last social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the last social network in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the social networks before and after the current social network in the ordered set where uuid = &#63;.
	*
	* @param socialNetworkId the primary key of the current social network
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public SocialNetwork[] findByUuid_PrevAndNext(long socialNetworkId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Removes all the social networks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of social networks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching social networks
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the social network where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSocialNetworkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByUUID_G(String uuid, long groupId)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the social network where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the social network where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the social network where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the social network that was removed
	*/
	public SocialNetwork removeByUUID_G(String uuid, long groupId)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the number of social networks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching social networks
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the social networks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching social networks
	*/
	public java.util.List<SocialNetwork> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<SocialNetwork> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<SocialNetwork> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public java.util.List<SocialNetwork> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the first social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the last social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the last social network in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public SocialNetwork[] findByUuid_C_PrevAndNext(long socialNetworkId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Removes all the social networks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of social networks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching social networks
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the social networks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching social networks
	*/
	public java.util.List<SocialNetwork> findByCompanyId(long companyId);

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
	public java.util.List<SocialNetwork> findByCompanyId(long companyId,
		int start, int end);

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
	public java.util.List<SocialNetwork> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public java.util.List<SocialNetwork> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the first social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the last social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the last social network in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the social networks before and after the current social network in the ordered set where companyId = &#63;.
	*
	* @param socialNetworkId the primary key of the current social network
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public SocialNetwork[] findByCompanyId_PrevAndNext(long socialNetworkId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Removes all the social networks where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of social networks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching social networks
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the social networks where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching social networks
	*/
	public java.util.List<SocialNetwork> findByCompanyIdGroupId(
		long companyId, long groupId);

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
	public java.util.List<SocialNetwork> findByCompanyIdGroupId(
		long companyId, long groupId, int start, int end);

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
	public java.util.List<SocialNetwork> findByCompanyIdGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public java.util.List<SocialNetwork> findByCompanyIdGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByCompanyIdGroupId_First(long companyId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the first social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByCompanyIdGroupId_First(long companyId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

	/**
	* Returns the last social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network
	* @throws NoSuchSocialNetworkException if a matching social network could not be found
	*/
	public SocialNetwork findByCompanyIdGroupId_Last(long companyId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the last social network in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social network, or <code>null</code> if a matching social network could not be found
	*/
	public SocialNetwork fetchByCompanyIdGroupId_Last(long companyId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public SocialNetwork[] findByCompanyIdGroupId_PrevAndNext(
		long socialNetworkId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator)
		throws NoSuchSocialNetworkException;

	/**
	* Removes all the social networks where companyId = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public void removeByCompanyIdGroupId(long companyId, long groupId);

	/**
	* Returns the number of social networks where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching social networks
	*/
	public int countByCompanyIdGroupId(long companyId, long groupId);

	/**
	* Caches the social network in the entity cache if it is enabled.
	*
	* @param socialNetwork the social network
	*/
	public void cacheResult(SocialNetwork socialNetwork);

	/**
	* Caches the social networks in the entity cache if it is enabled.
	*
	* @param socialNetworks the social networks
	*/
	public void cacheResult(java.util.List<SocialNetwork> socialNetworks);

	/**
	* Creates a new social network with the primary key. Does not add the social network to the database.
	*
	* @param socialNetworkId the primary key for the new social network
	* @return the new social network
	*/
	public SocialNetwork create(long socialNetworkId);

	/**
	* Removes the social network with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network that was removed
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public SocialNetwork remove(long socialNetworkId)
		throws NoSuchSocialNetworkException;

	public SocialNetwork updateImpl(SocialNetwork socialNetwork);

	/**
	* Returns the social network with the primary key or throws a {@link NoSuchSocialNetworkException} if it could not be found.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network
	* @throws NoSuchSocialNetworkException if a social network with the primary key could not be found
	*/
	public SocialNetwork findByPrimaryKey(long socialNetworkId)
		throws NoSuchSocialNetworkException;

	/**
	* Returns the social network with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param socialNetworkId the primary key of the social network
	* @return the social network, or <code>null</code> if a social network with the primary key could not be found
	*/
	public SocialNetwork fetchByPrimaryKey(long socialNetworkId);

	@Override
	public java.util.Map<java.io.Serializable, SocialNetwork> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the social networks.
	*
	* @return the social networks
	*/
	public java.util.List<SocialNetwork> findAll();

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
	public java.util.List<SocialNetwork> findAll(int start, int end);

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
	public java.util.List<SocialNetwork> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator);

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
	public java.util.List<SocialNetwork> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SocialNetwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the social networks from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of social networks.
	*
	* @return the number of social networks
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}