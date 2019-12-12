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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.social_network.sb.service.http.SocialNetworkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.social_network.sb.service.http.SocialNetworkServiceSoap
 * @generated
 */
@ProviderType
public class SocialNetworkSoap implements Serializable {
	public static SocialNetworkSoap toSoapModel(SocialNetwork model) {
		SocialNetworkSoap soapModel = new SocialNetworkSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSocialNetworkId(model.getSocialNetworkId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUrl(model.getUrl());
		soapModel.setImageId(model.getImageId());
		soapModel.setExtraParameters(model.getExtraParameters());
		soapModel.setAlt(model.getAlt());

		return soapModel;
	}

	public static SocialNetworkSoap[] toSoapModels(SocialNetwork[] models) {
		SocialNetworkSoap[] soapModels = new SocialNetworkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SocialNetworkSoap[][] toSoapModels(SocialNetwork[][] models) {
		SocialNetworkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SocialNetworkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SocialNetworkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SocialNetworkSoap[] toSoapModels(List<SocialNetwork> models) {
		List<SocialNetworkSoap> soapModels = new ArrayList<SocialNetworkSoap>(models.size());

		for (SocialNetwork model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SocialNetworkSoap[soapModels.size()]);
	}

	public SocialNetworkSoap() {
	}

	public long getPrimaryKey() {
		return _socialNetworkId;
	}

	public void setPrimaryKey(long pk) {
		setSocialNetworkId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSocialNetworkId() {
		return _socialNetworkId;
	}

	public void setSocialNetworkId(long socialNetworkId) {
		_socialNetworkId = socialNetworkId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public String getExtraParameters() {
		return _extraParameters;
	}

	public void setExtraParameters(String extraParameters) {
		_extraParameters = extraParameters;
	}

	public String getAlt() {
		return _alt;
	}

	public void setAlt(String alt) {
		_alt = alt;
	}

	private String _uuid;
	private long _socialNetworkId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _url;
	private long _imageId;
	private String _extraParameters;
	private String _alt;
}