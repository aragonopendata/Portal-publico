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
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.ContentUrlServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.ContentUrlServiceSoap
 * @generated
 */
@ProviderType
public class ContentUrlSoap implements Serializable {
	public static ContentUrlSoap toSoapModel(ContentUrl model) {
		ContentUrlSoap soapModel = new ContentUrlSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContentUrlId(model.getContentUrlId());
		soapModel.setContentOriginId(model.getContentOriginId());
		soapModel.setContentDestinationId(model.getContentDestinationId());
		soapModel.setUrlOrigin(model.getUrlOrigin());
		soapModel.setUrlDestination(model.getUrlDestination());
		soapModel.setClassName(model.getClassName());

		return soapModel;
	}

	public static ContentUrlSoap[] toSoapModels(ContentUrl[] models) {
		ContentUrlSoap[] soapModels = new ContentUrlSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentUrlSoap[][] toSoapModels(ContentUrl[][] models) {
		ContentUrlSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentUrlSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentUrlSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentUrlSoap[] toSoapModels(List<ContentUrl> models) {
		List<ContentUrlSoap> soapModels = new ArrayList<ContentUrlSoap>(models.size());

		for (ContentUrl model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentUrlSoap[soapModels.size()]);
	}

	public ContentUrlSoap() {
	}

	public long getPrimaryKey() {
		return _contentUrlId;
	}

	public void setPrimaryKey(long pk) {
		setContentUrlId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContentUrlId() {
		return _contentUrlId;
	}

	public void setContentUrlId(long contentUrlId) {
		_contentUrlId = contentUrlId;
	}

	public long getContentOriginId() {
		return _contentOriginId;
	}

	public void setContentOriginId(long contentOriginId) {
		_contentOriginId = contentOriginId;
	}

	public long getContentDestinationId() {
		return _contentDestinationId;
	}

	public void setContentDestinationId(long contentDestinationId) {
		_contentDestinationId = contentDestinationId;
	}

	public String getUrlOrigin() {
		return _urlOrigin;
	}

	public void setUrlOrigin(String urlOrigin) {
		_urlOrigin = urlOrigin;
	}

	public String getUrlDestination() {
		return _urlDestination;
	}

	public void setUrlDestination(String urlDestination) {
		_urlDestination = urlDestination;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	private String _uuid;
	private long _contentUrlId;
	private long _contentOriginId;
	private long _contentDestinationId;
	private String _urlOrigin;
	private String _urlDestination;
	private String _className;
}