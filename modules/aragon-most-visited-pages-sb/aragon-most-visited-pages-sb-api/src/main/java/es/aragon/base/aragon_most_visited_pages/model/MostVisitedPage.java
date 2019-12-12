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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MostVisitedPage service. Represents a row in the &quot;EAB_AMVP_MostVisitedPage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPageModel
 * @see es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl
 * @see es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl
 * @generated
 */
@ImplementationClassName("es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl")
@ProviderType
public interface MostVisitedPage extends MostVisitedPageModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MostVisitedPage, Long> MOST_VISITED_PAGE_ID_ACCESSOR =
		new Accessor<MostVisitedPage, Long>() {
			@Override
			public Long get(MostVisitedPage mostVisitedPage) {
				return mostVisitedPage.getMostVisitedPageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MostVisitedPage> getTypeClass() {
				return MostVisitedPage.class;
			}
		};
}