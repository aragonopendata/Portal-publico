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

package es.aragon.base.content_ratings.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.content_ratings.model.Rating;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Rating in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Rating
 * @generated
 */
@ProviderType
public class RatingCacheModel implements CacheModel<Rating>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RatingCacheModel)) {
			return false;
		}

		RatingCacheModel ratingCacheModel = (RatingCacheModel)obj;

		if (ratingId == ratingCacheModel.ratingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ratingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ratingId=");
		sb.append(ratingId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", score=");
		sb.append(score);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Rating toEntityModel() {
		RatingImpl ratingImpl = new RatingImpl();

		if (uuid == null) {
			ratingImpl.setUuid("");
		}
		else {
			ratingImpl.setUuid(uuid);
		}

		ratingImpl.setRatingId(ratingId);

		if (createDate == Long.MIN_VALUE) {
			ratingImpl.setCreateDate(null);
		}
		else {
			ratingImpl.setCreateDate(new Date(createDate));
		}

		ratingImpl.setCompanyId(companyId);
		ratingImpl.setClassNameId(classNameId);
		ratingImpl.setClassPK(classPK);
		ratingImpl.setScore(score);

		if (comment == null) {
			ratingImpl.setComment("");
		}
		else {
			ratingImpl.setComment(comment);
		}

		ratingImpl.resetOriginalValues();

		return ratingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		ratingId = objectInput.readLong();
		createDate = objectInput.readLong();

		companyId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		score = objectInput.readDouble();
		comment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(ratingId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeDouble(score);

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}
	}

	public String uuid;
	public long ratingId;
	public long createDate;
	public long companyId;
	public long classNameId;
	public long classPK;
	public double score;
	public String comment;
}