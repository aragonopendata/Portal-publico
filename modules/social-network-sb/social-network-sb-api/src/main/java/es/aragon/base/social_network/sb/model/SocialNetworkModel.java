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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SocialNetwork service. Represents a row in the &quot;EAB_SN_SocialNetwork&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link es.aragon.base.social_network.sb.model.impl.SocialNetworkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialNetwork
 * @see es.aragon.base.social_network.sb.model.impl.SocialNetworkImpl
 * @see es.aragon.base.social_network.sb.model.impl.SocialNetworkModelImpl
 * @generated
 */
@ProviderType
public interface SocialNetworkModel extends BaseModel<SocialNetwork>,
	GroupedModel, ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social network model instance should use the {@link SocialNetwork} interface instead.
	 */

	/**
	 * Returns the primary key of this social network.
	 *
	 * @return the primary key of this social network
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social network.
	 *
	 * @param primaryKey the primary key of this social network
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this social network.
	 *
	 * @return the uuid of this social network
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this social network.
	 *
	 * @param uuid the uuid of this social network
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the social network ID of this social network.
	 *
	 * @return the social network ID of this social network
	 */
	public long getSocialNetworkId();

	/**
	 * Sets the social network ID of this social network.
	 *
	 * @param socialNetworkId the social network ID of this social network
	 */
	public void setSocialNetworkId(long socialNetworkId);

	/**
	 * Returns the group ID of this social network.
	 *
	 * @return the group ID of this social network
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this social network.
	 *
	 * @param groupId the group ID of this social network
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social network.
	 *
	 * @return the company ID of this social network
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this social network.
	 *
	 * @param companyId the company ID of this social network
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this social network.
	 *
	 * @return the user ID of this social network
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this social network.
	 *
	 * @param userId the user ID of this social network
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this social network.
	 *
	 * @return the user uuid of this social network
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this social network.
	 *
	 * @param userUuid the user uuid of this social network
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this social network.
	 *
	 * @return the user name of this social network
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this social network.
	 *
	 * @param userName the user name of this social network
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this social network.
	 *
	 * @return the create date of this social network
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this social network.
	 *
	 * @param createDate the create date of this social network
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this social network.
	 *
	 * @return the modified date of this social network
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this social network.
	 *
	 * @param modifiedDate the modified date of this social network
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the url of this social network.
	 *
	 * @return the url of this social network
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this social network.
	 *
	 * @param url the url of this social network
	 */
	public void setUrl(String url);

	/**
	 * Returns the image ID of this social network.
	 *
	 * @return the image ID of this social network
	 */
	public long getImageId();

	/**
	 * Sets the image ID of this social network.
	 *
	 * @param imageId the image ID of this social network
	 */
	public void setImageId(long imageId);

	/**
	 * Returns the extra parameters of this social network.
	 *
	 * @return the extra parameters of this social network
	 */
	@AutoEscape
	public String getExtraParameters();

	/**
	 * Sets the extra parameters of this social network.
	 *
	 * @param extraParameters the extra parameters of this social network
	 */
	public void setExtraParameters(String extraParameters);

	/**
	 * Returns the alt of this social network.
	 *
	 * @return the alt of this social network
	 */
	@AutoEscape
	public String getAlt();

	/**
	 * Sets the alt of this social network.
	 *
	 * @param alt the alt of this social network
	 */
	public void setAlt(String alt);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(SocialNetwork socialNetwork);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SocialNetwork> toCacheModel();

	@Override
	public SocialNetwork toEscapedModel();

	@Override
	public SocialNetwork toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}