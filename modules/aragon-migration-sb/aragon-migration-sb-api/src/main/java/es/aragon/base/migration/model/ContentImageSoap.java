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
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.ContentImageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.ContentImageServiceSoap
 * @generated
 */
@ProviderType
public class ContentImageSoap implements Serializable {
	public static ContentImageSoap toSoapModel(ContentImage model) {
		ContentImageSoap soapModel = new ContentImageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContentImageId(model.getContentImageId());
		soapModel.setContentOriginId(model.getContentOriginId());
		soapModel.setUrl(model.getUrl());

		return soapModel;
	}

	public static ContentImageSoap[] toSoapModels(ContentImage[] models) {
		ContentImageSoap[] soapModels = new ContentImageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentImageSoap[][] toSoapModels(ContentImage[][] models) {
		ContentImageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentImageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentImageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentImageSoap[] toSoapModels(List<ContentImage> models) {
		List<ContentImageSoap> soapModels = new ArrayList<ContentImageSoap>(models.size());

		for (ContentImage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentImageSoap[soapModels.size()]);
	}

	public ContentImageSoap() {
	}

	public long getPrimaryKey() {
		return _contentImageId;
	}

	public void setPrimaryKey(long pk) {
		setContentImageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContentImageId() {
		return _contentImageId;
	}

	public void setContentImageId(long contentImageId) {
		_contentImageId = contentImageId;
	}

	public long getContentOriginId() {
		return _contentOriginId;
	}

	public void setContentOriginId(long contentOriginId) {
		_contentOriginId = contentOriginId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private String _uuid;
	private long _contentImageId;
	private long _contentOriginId;
	private String _url;
}