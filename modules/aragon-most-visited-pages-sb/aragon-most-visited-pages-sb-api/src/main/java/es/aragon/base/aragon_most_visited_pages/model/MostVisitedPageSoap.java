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

package es.aragon.base.aragon_most_visited_pages.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.aragon_most_visited_pages.service.http.MostVisitedPageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.aragon_most_visited_pages.service.http.MostVisitedPageServiceSoap
 * @generated
 */
@ProviderType
public class MostVisitedPageSoap implements Serializable {
	public static MostVisitedPageSoap toSoapModel(MostVisitedPage model) {
		MostVisitedPageSoap soapModel = new MostVisitedPageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMostVisitedPageId(model.getMostVisitedPageId());
		soapModel.setPath(model.getPath());
		soapModel.setVisits(model.getVisits());
		soapModel.setTitle(model.getTitle());
		soapModel.setPosition(model.getPosition());
		soapModel.setVisible(model.isVisible());

		return soapModel;
	}

	public static MostVisitedPageSoap[] toSoapModels(MostVisitedPage[] models) {
		MostVisitedPageSoap[] soapModels = new MostVisitedPageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MostVisitedPageSoap[][] toSoapModels(
		MostVisitedPage[][] models) {
		MostVisitedPageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MostVisitedPageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MostVisitedPageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MostVisitedPageSoap[] toSoapModels(
		List<MostVisitedPage> models) {
		List<MostVisitedPageSoap> soapModels = new ArrayList<MostVisitedPageSoap>(models.size());

		for (MostVisitedPage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MostVisitedPageSoap[soapModels.size()]);
	}

	public MostVisitedPageSoap() {
	}

	public long getPrimaryKey() {
		return _mostVisitedPageId;
	}

	public void setPrimaryKey(long pk) {
		setMostVisitedPageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMostVisitedPageId() {
		return _mostVisitedPageId;
	}

	public void setMostVisitedPageId(long mostVisitedPageId) {
		_mostVisitedPageId = mostVisitedPageId;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
	}

	public int getVisits() {
		return _visits;
	}

	public void setVisits(int visits) {
		_visits = visits;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public boolean getVisible() {
		return _visible;
	}

	public boolean isVisible() {
		return _visible;
	}

	public void setVisible(boolean visible) {
		_visible = visible;
	}

	private String _uuid;
	private long _mostVisitedPageId;
	private String _path;
	private int _visits;
	private String _title;
	private int _position;
	private boolean _visible;
}