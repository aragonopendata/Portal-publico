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

package es.aragon.base.migration.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.ContentRelatedServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.ContentRelatedServiceSoap
 * @generated
 */
@ProviderType
public class ContentRelatedSoap implements Serializable {
	public static ContentRelatedSoap toSoapModel(ContentRelated model) {
		ContentRelatedSoap soapModel = new ContentRelatedSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContentRelatedId(model.getContentRelatedId());
		soapModel.setContentParentId(model.getContentParentId());
		soapModel.setContentLinkedId(model.getContentLinkedId());
		soapModel.setUrl(model.getUrl());

		return soapModel;
	}

	public static ContentRelatedSoap[] toSoapModels(ContentRelated[] models) {
		ContentRelatedSoap[] soapModels = new ContentRelatedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentRelatedSoap[][] toSoapModels(ContentRelated[][] models) {
		ContentRelatedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentRelatedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentRelatedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentRelatedSoap[] toSoapModels(List<ContentRelated> models) {
		List<ContentRelatedSoap> soapModels = new ArrayList<ContentRelatedSoap>(models.size());

		for (ContentRelated model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentRelatedSoap[soapModels.size()]);
	}

	public ContentRelatedSoap() {
	}

	public long getPrimaryKey() {
		return _contentRelatedId;
	}

	public void setPrimaryKey(long pk) {
		setContentRelatedId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContentRelatedId() {
		return _contentRelatedId;
	}

	public void setContentRelatedId(long contentRelatedId) {
		_contentRelatedId = contentRelatedId;
	}

	public long getContentParentId() {
		return _contentParentId;
	}

	public void setContentParentId(long contentParentId) {
		_contentParentId = contentParentId;
	}

	public long getContentLinkedId() {
		return _contentLinkedId;
	}

	public void setContentLinkedId(long contentLinkedId) {
		_contentLinkedId = contentLinkedId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private String _uuid;
	private long _contentRelatedId;
	private long _contentParentId;
	private long _contentLinkedId;
	private String _url;
}