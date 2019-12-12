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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Rating service. Represents a row in the &quot;EAB_CR_Rating&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see RatingModel
 * @see es.aragon.base.content_ratings.model.impl.RatingImpl
 * @see es.aragon.base.content_ratings.model.impl.RatingModelImpl
 * @generated
 */
@ImplementationClassName("es.aragon.base.content_ratings.model.impl.RatingImpl")
@ProviderType
public interface Rating extends RatingModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link es.aragon.base.content_ratings.model.impl.RatingImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Rating, Long> RATING_ID_ACCESSOR = new Accessor<Rating, Long>() {
			@Override
			public Long get(Rating rating) {
				return rating.getRatingId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Rating> getTypeClass() {
				return Rating.class;
			}
		};
}