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

package es.aragon.base.crawler.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Page service. Represents a row in the &quot;EAB_CRAWLER_Page&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PageModel
 * @see es.aragon.base.crawler.model.impl.PageImpl
 * @see es.aragon.base.crawler.model.impl.PageModelImpl
 * @generated
 */
@ImplementationClassName("es.aragon.base.crawler.model.impl.PageImpl")
@ProviderType
public interface Page extends PageModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link es.aragon.base.crawler.model.impl.PageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Page, Long> PAGE_ID_ACCESSOR = new Accessor<Page, Long>() {
			@Override
			public Long get(Page page) {
				return page.getPageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Page> getTypeClass() {
				return Page.class;
			}
		};

	public void setCategoryIds(String categoryIds,
		es.aragon.base.crawler.service.PageLocalService pageLocalService);
}