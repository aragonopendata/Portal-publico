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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Rating}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Rating
 * @generated
 */
@ProviderType
public class RatingWrapper implements Rating, ModelWrapper<Rating> {
	public RatingWrapper(Rating rating) {
		_rating = rating;
	}

	@Override
	public Class<?> getModelClass() {
		return Rating.class;
	}

	@Override
	public String getModelClassName() {
		return Rating.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ratingId", getRatingId());
		attributes.put("createDate", getCreateDate());
		attributes.put("companyId", getCompanyId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("score", getScore());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ratingId = (Long)attributes.get("ratingId");

		if (ratingId != null) {
			setRatingId(ratingId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Double score = (Double)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	@Override
	public Object clone() {
		return new RatingWrapper((Rating)_rating.clone());
	}

	@Override
	public int compareTo(Rating rating) {
		return _rating.compareTo(rating);
	}

	/**
	* Returns the fully qualified class name of this rating.
	*
	* @return the fully qualified class name of this rating
	*/
	@Override
	public String getClassName() {
		return _rating.getClassName();
	}

	/**
	* Returns the class name ID of this rating.
	*
	* @return the class name ID of this rating
	*/
	@Override
	public long getClassNameId() {
		return _rating.getClassNameId();
	}

	/**
	* Returns the class pk of this rating.
	*
	* @return the class pk of this rating
	*/
	@Override
	public long getClassPK() {
		return _rating.getClassPK();
	}

	/**
	* Returns the comment of this rating.
	*
	* @return the comment of this rating
	*/
	@Override
	public String getComment() {
		return _rating.getComment();
	}

	/**
	* Returns the company ID of this rating.
	*
	* @return the company ID of this rating
	*/
	@Override
	public long getCompanyId() {
		return _rating.getCompanyId();
	}

	/**
	* Returns the create date of this rating.
	*
	* @return the create date of this rating
	*/
	@Override
	public Date getCreateDate() {
		return _rating.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rating.getExpandoBridge();
	}

	/**
	* Returns the primary key of this rating.
	*
	* @return the primary key of this rating
	*/
	@Override
	public long getPrimaryKey() {
		return _rating.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rating.getPrimaryKeyObj();
	}

	/**
	* Returns the rating ID of this rating.
	*
	* @return the rating ID of this rating
	*/
	@Override
	public long getRatingId() {
		return _rating.getRatingId();
	}

	/**
	* Returns the score of this rating.
	*
	* @return the score of this rating
	*/
	@Override
	public double getScore() {
		return _rating.getScore();
	}

	/**
	* Returns the uuid of this rating.
	*
	* @return the uuid of this rating
	*/
	@Override
	public String getUuid() {
		return _rating.getUuid();
	}

	@Override
	public int hashCode() {
		return _rating.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rating.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rating.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rating.isNew();
	}

	@Override
	public void persist() {
		_rating.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rating.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_rating.setClassName(className);
	}

	/**
	* Sets the class name ID of this rating.
	*
	* @param classNameId the class name ID of this rating
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_rating.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this rating.
	*
	* @param classPK the class pk of this rating
	*/
	@Override
	public void setClassPK(long classPK) {
		_rating.setClassPK(classPK);
	}

	/**
	* Sets the comment of this rating.
	*
	* @param comment the comment of this rating
	*/
	@Override
	public void setComment(String comment) {
		_rating.setComment(comment);
	}

	/**
	* Sets the company ID of this rating.
	*
	* @param companyId the company ID of this rating
	*/
	@Override
	public void setCompanyId(long companyId) {
		_rating.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this rating.
	*
	* @param createDate the create date of this rating
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_rating.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rating.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rating.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rating.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_rating.setNew(n);
	}

	/**
	* Sets the primary key of this rating.
	*
	* @param primaryKey the primary key of this rating
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_rating.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rating.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rating ID of this rating.
	*
	* @param ratingId the rating ID of this rating
	*/
	@Override
	public void setRatingId(long ratingId) {
		_rating.setRatingId(ratingId);
	}

	/**
	* Sets the score of this rating.
	*
	* @param score the score of this rating
	*/
	@Override
	public void setScore(double score) {
		_rating.setScore(score);
	}

	/**
	* Sets the uuid of this rating.
	*
	* @param uuid the uuid of this rating
	*/
	@Override
	public void setUuid(String uuid) {
		_rating.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Rating> toCacheModel() {
		return _rating.toCacheModel();
	}

	@Override
	public Rating toEscapedModel() {
		return new RatingWrapper(_rating.toEscapedModel());
	}

	@Override
	public String toString() {
		return _rating.toString();
	}

	@Override
	public Rating toUnescapedModel() {
		return new RatingWrapper(_rating.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _rating.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RatingWrapper)) {
			return false;
		}

		RatingWrapper ratingWrapper = (RatingWrapper)obj;

		if (Objects.equals(_rating, ratingWrapper._rating)) {
			return true;
		}

		return false;
	}

	@Override
	public Rating getWrappedModel() {
		return _rating;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rating.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rating.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rating.resetOriginalValues();
	}

	private final Rating _rating;
}