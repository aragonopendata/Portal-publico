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

package es.aragon.base.social_network.sb.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SocialNetwork}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetwork
 * @generated
 */
@ProviderType
public class SocialNetworkWrapper implements SocialNetwork,
	ModelWrapper<SocialNetwork> {
	public SocialNetworkWrapper(SocialNetwork socialNetwork) {
		_socialNetwork = socialNetwork;
	}

	@Override
	public Class<?> getModelClass() {
		return SocialNetwork.class;
	}

	@Override
	public String getModelClassName() {
		return SocialNetwork.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("socialNetworkId", getSocialNetworkId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("url", getUrl());
		attributes.put("imageId", getImageId());
		attributes.put("extraParameters", getExtraParameters());
		attributes.put("alt", getAlt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long socialNetworkId = (Long)attributes.get("socialNetworkId");

		if (socialNetworkId != null) {
			setSocialNetworkId(socialNetworkId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String extraParameters = (String)attributes.get("extraParameters");

		if (extraParameters != null) {
			setExtraParameters(extraParameters);
		}

		String alt = (String)attributes.get("alt");

		if (alt != null) {
			setAlt(alt);
		}
	}

	@Override
	public Object clone() {
		return new SocialNetworkWrapper((SocialNetwork)_socialNetwork.clone());
	}

	@Override
	public int compareTo(SocialNetwork socialNetwork) {
		return _socialNetwork.compareTo(socialNetwork);
	}

	/**
	* Returns the alt of this social network.
	*
	* @return the alt of this social network
	*/
	@Override
	public String getAlt() {
		return _socialNetwork.getAlt();
	}

	/**
	* Returns the company ID of this social network.
	*
	* @return the company ID of this social network
	*/
	@Override
	public long getCompanyId() {
		return _socialNetwork.getCompanyId();
	}

	/**
	* Returns the create date of this social network.
	*
	* @return the create date of this social network
	*/
	@Override
	public Date getCreateDate() {
		return _socialNetwork.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _socialNetwork.getExpandoBridge();
	}

	/**
	* Returns the extra parameters of this social network.
	*
	* @return the extra parameters of this social network
	*/
	@Override
	public String getExtraParameters() {
		return _socialNetwork.getExtraParameters();
	}

	/**
	* Returns the group ID of this social network.
	*
	* @return the group ID of this social network
	*/
	@Override
	public long getGroupId() {
		return _socialNetwork.getGroupId();
	}

	/**
	* Returns the image ID of this social network.
	*
	* @return the image ID of this social network
	*/
	@Override
	public long getImageId() {
		return _socialNetwork.getImageId();
	}

	/**
	* Returns the modified date of this social network.
	*
	* @return the modified date of this social network
	*/
	@Override
	public Date getModifiedDate() {
		return _socialNetwork.getModifiedDate();
	}

	/**
	* Returns the primary key of this social network.
	*
	* @return the primary key of this social network
	*/
	@Override
	public long getPrimaryKey() {
		return _socialNetwork.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _socialNetwork.getPrimaryKeyObj();
	}

	/**
	* Returns the social network ID of this social network.
	*
	* @return the social network ID of this social network
	*/
	@Override
	public long getSocialNetworkId() {
		return _socialNetwork.getSocialNetworkId();
	}

	/**
	* Returns the url of this social network.
	*
	* @return the url of this social network
	*/
	@Override
	public String getUrl() {
		return _socialNetwork.getUrl();
	}

	/**
	* Returns the user ID of this social network.
	*
	* @return the user ID of this social network
	*/
	@Override
	public long getUserId() {
		return _socialNetwork.getUserId();
	}

	/**
	* Returns the user name of this social network.
	*
	* @return the user name of this social network
	*/
	@Override
	public String getUserName() {
		return _socialNetwork.getUserName();
	}

	/**
	* Returns the user uuid of this social network.
	*
	* @return the user uuid of this social network
	*/
	@Override
	public String getUserUuid() {
		return _socialNetwork.getUserUuid();
	}

	/**
	* Returns the uuid of this social network.
	*
	* @return the uuid of this social network
	*/
	@Override
	public String getUuid() {
		return _socialNetwork.getUuid();
	}

	@Override
	public int hashCode() {
		return _socialNetwork.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _socialNetwork.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _socialNetwork.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _socialNetwork.isNew();
	}

	@Override
	public void persist() {
		_socialNetwork.persist();
	}

	/**
	* Sets the alt of this social network.
	*
	* @param alt the alt of this social network
	*/
	@Override
	public void setAlt(String alt) {
		_socialNetwork.setAlt(alt);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_socialNetwork.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this social network.
	*
	* @param companyId the company ID of this social network
	*/
	@Override
	public void setCompanyId(long companyId) {
		_socialNetwork.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this social network.
	*
	* @param createDate the create date of this social network
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_socialNetwork.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_socialNetwork.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_socialNetwork.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_socialNetwork.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra parameters of this social network.
	*
	* @param extraParameters the extra parameters of this social network
	*/
	@Override
	public void setExtraParameters(String extraParameters) {
		_socialNetwork.setExtraParameters(extraParameters);
	}

	/**
	* Sets the group ID of this social network.
	*
	* @param groupId the group ID of this social network
	*/
	@Override
	public void setGroupId(long groupId) {
		_socialNetwork.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this social network.
	*
	* @param imageId the image ID of this social network
	*/
	@Override
	public void setImageId(long imageId) {
		_socialNetwork.setImageId(imageId);
	}

	/**
	* Sets the modified date of this social network.
	*
	* @param modifiedDate the modified date of this social network
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_socialNetwork.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_socialNetwork.setNew(n);
	}

	/**
	* Sets the primary key of this social network.
	*
	* @param primaryKey the primary key of this social network
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_socialNetwork.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_socialNetwork.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the social network ID of this social network.
	*
	* @param socialNetworkId the social network ID of this social network
	*/
	@Override
	public void setSocialNetworkId(long socialNetworkId) {
		_socialNetwork.setSocialNetworkId(socialNetworkId);
	}

	/**
	* Sets the url of this social network.
	*
	* @param url the url of this social network
	*/
	@Override
	public void setUrl(String url) {
		_socialNetwork.setUrl(url);
	}

	/**
	* Sets the user ID of this social network.
	*
	* @param userId the user ID of this social network
	*/
	@Override
	public void setUserId(long userId) {
		_socialNetwork.setUserId(userId);
	}

	/**
	* Sets the user name of this social network.
	*
	* @param userName the user name of this social network
	*/
	@Override
	public void setUserName(String userName) {
		_socialNetwork.setUserName(userName);
	}

	/**
	* Sets the user uuid of this social network.
	*
	* @param userUuid the user uuid of this social network
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_socialNetwork.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this social network.
	*
	* @param uuid the uuid of this social network
	*/
	@Override
	public void setUuid(String uuid) {
		_socialNetwork.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SocialNetwork> toCacheModel() {
		return _socialNetwork.toCacheModel();
	}

	@Override
	public SocialNetwork toEscapedModel() {
		return new SocialNetworkWrapper(_socialNetwork.toEscapedModel());
	}

	@Override
	public String toString() {
		return _socialNetwork.toString();
	}

	@Override
	public SocialNetwork toUnescapedModel() {
		return new SocialNetworkWrapper(_socialNetwork.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _socialNetwork.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SocialNetworkWrapper)) {
			return false;
		}

		SocialNetworkWrapper socialNetworkWrapper = (SocialNetworkWrapper)obj;

		if (Objects.equals(_socialNetwork, socialNetworkWrapper._socialNetwork)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _socialNetwork.getStagedModelType();
	}

	@Override
	public SocialNetwork getWrappedModel() {
		return _socialNetwork;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _socialNetwork.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _socialNetwork.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_socialNetwork.resetOriginalValues();
	}

	private final SocialNetwork _socialNetwork;
}