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
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.TemplateServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.TemplateServiceSoap
 * @generated
 */
@ProviderType
public class TemplateSoap implements Serializable {
	public static TemplateSoap toSoapModel(Template model) {
		TemplateSoap soapModel = new TemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTemplateId(model.getTemplateId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static TemplateSoap[] toSoapModels(Template[] models) {
		TemplateSoap[] soapModels = new TemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TemplateSoap[][] toSoapModels(Template[][] models) {
		TemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TemplateSoap[] toSoapModels(List<Template> models) {
		List<TemplateSoap> soapModels = new ArrayList<TemplateSoap>(models.size());

		for (Template model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TemplateSoap[soapModels.size()]);
	}

	public TemplateSoap() {
	}

	public long getPrimaryKey() {
		return _templateId;
	}

	public void setPrimaryKey(long pk) {
		setTemplateId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTemplateId() {
		return _templateId;
	}

	public void setTemplateId(long templateId) {
		_templateId = templateId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _uuid;
	private long _templateId;
	private String _name;
}