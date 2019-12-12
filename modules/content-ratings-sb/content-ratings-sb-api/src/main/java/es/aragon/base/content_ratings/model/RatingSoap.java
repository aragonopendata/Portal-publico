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

package es.aragon.base.content_ratings.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.content_ratings.service.http.RatingServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.content_ratings.service.http.RatingServiceSoap
 * @generated
 */
@ProviderType
public class RatingSoap implements Serializable {
	public static RatingSoap toSoapModel(Rating model) {
		RatingSoap soapModel = new RatingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRatingId(model.getRatingId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setScore(model.getScore());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static RatingSoap[] toSoapModels(Rating[] models) {
		RatingSoap[] soapModels = new RatingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RatingSoap[][] toSoapModels(Rating[][] models) {
		RatingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RatingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RatingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RatingSoap[] toSoapModels(List<Rating> models) {
		List<RatingSoap> soapModels = new ArrayList<RatingSoap>(models.size());

		for (Rating model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RatingSoap[soapModels.size()]);
	}

	public RatingSoap() {
	}

	public long getPrimaryKey() {
		return _ratingId;
	}

	public void setPrimaryKey(long pk) {
		setRatingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRatingId() {
		return _ratingId;
	}

	public void setRatingId(long ratingId) {
		_ratingId = ratingId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public double getScore() {
		return _score;
	}

	public void setScore(double score) {
		_score = score;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	private String _uuid;
	private long _ratingId;
	private Date _createDate;
	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private double _score;
	private String _comment;
}