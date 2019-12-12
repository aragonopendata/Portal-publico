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

package es.aragon.base.content_ratings.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.aragon.base.content_ratings.model.Rating;
import es.aragon.base.content_ratings.service.base.RatingLocalServiceBaseImpl;
import es.aragon.base.content_ratings.service.persistence.RatingUtil;

/**
 * The implementation of the rating local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.content_ratings.service.RatingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RatingLocalServiceBaseImpl
 * @see es.aragon.base.content_ratings.service.RatingLocalServiceUtil
 */
public class RatingLocalServiceImpl extends RatingLocalServiceBaseImpl {

	@Override
	public Rating addRating(long companyId, long classNameId, long classPK, double score) {
		long ratingId = counterLocalService.increment(Rating.class.getName());
		Rating rating = RatingUtil.create(ratingId);
		rating.setCreateDate(new Date());
		rating.setCompanyId(companyId);
		rating.setClassNameId(classNameId);
		rating.setClassPK(classPK);
		rating.setScore(score);
		return RatingUtil.update(rating);
	}
	
	@Override
	public Rating addComment(long companyId, long classNameId, long classPK, String comment) {
		long ratingId = counterLocalService.increment(Rating.class.getName());
		Rating rating = RatingUtil.create(ratingId);
		rating.setCreateDate(new Date());
		rating.setCompanyId(companyId);
		rating.setClassNameId(classNameId);
		rating.setClassPK(classPK);
		rating.setComment(comment);
		return RatingUtil.update(rating);
	}
	
	@Override
	public int totalRatings(long companyId, long classNameId, long classPK) {
		DynamicQuery dynamicQuery = super.dynamicQuery();
		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");
		Property classNameIdProperty = PropertyFactoryUtil.forName("classNameId");
		Property classPKProperty = PropertyFactoryUtil.forName("classPK");
		Property scoreProperty = PropertyFactoryUtil.forName("score");
		dynamicQuery.add(companyIdProperty.eq(companyId));
		dynamicQuery.add(classNameIdProperty.eq(classNameId));
		dynamicQuery.add(classPKProperty.eq(classPK));
		dynamicQuery.add(scoreProperty.gt(0));
		return (int) super.dynamicQueryCount(dynamicQuery);
	}
	
	@Override
	public double averageScore(long companyId, long classNameId, long classPK) {
		DynamicQuery dynamicQuery = super.dynamicQuery();
		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");
		Property classNameIdProperty = PropertyFactoryUtil.forName("classNameId");
		Property classPKProperty = PropertyFactoryUtil.forName("classPK");
		Property scoreProperty = PropertyFactoryUtil.forName("score");
		dynamicQuery.add(companyIdProperty.eq(companyId));
		dynamicQuery.add(classNameIdProperty.eq(classNameId));
		dynamicQuery.add(classPKProperty.eq(classPK));
		dynamicQuery.add(scoreProperty.gt(0));
		List<Rating> ratings = new ArrayList<Rating>(super.dynamicQuery(dynamicQuery));
		if(ratings.size() != 0) {
			double totalScore = 0;
			for(Rating rating : ratings) {
				totalScore += rating.getScore();
			}
			return totalScore/ratings.size(); 
		} else {
			return 0;
		}
	}
	
	@Override
	public List<String> getComments(long companyId, long classNameId, long classPK) {
		DynamicQuery dynamicQuery = super.dynamicQuery();
		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");
		Property classNameIdProperty = PropertyFactoryUtil.forName("classNameId");
		Property classPKProperty = PropertyFactoryUtil.forName("classPK");
		Property commentProperty = PropertyFactoryUtil.forName("comment");
		dynamicQuery.add(companyIdProperty.eq(companyId));
		dynamicQuery.add(classNameIdProperty.eq(classNameId));
		dynamicQuery.add(classPKProperty.eq(classPK));
		dynamicQuery.add(commentProperty.isNotNull());
		dynamicQuery.add(commentProperty.isNotEmpty());
		List<Rating> ratings = new ArrayList<Rating>(super.dynamicQuery(dynamicQuery));
		if(ratings.size() != 0) {
			List<String> comments = new ArrayList<String>();
			for(Rating rating : ratings) {
				comments.add(rating.getComment());
			}
			return comments;
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Override
	public List<Rating> findByClassNameIdClassPK(long classNameId, long classPK, int start, int end, OrderByComparator<Rating> orderByComparator){
		return ratingPersistence.findByClassNameIdClassPK(classNameId, classPK, start, end, orderByComparator);
	}
	
}