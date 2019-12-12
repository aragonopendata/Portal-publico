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

package es.aragon.base.aragon_most_visited_pages.service.impl;

import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;
import es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl;
import es.aragon.base.aragon_most_visited_pages.service.base.MostVisitedPageLocalServiceBaseImpl;

/**
 * The implementation of the most visited page local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPageLocalServiceBaseImpl
 * @see es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalServiceUtil
 */
public class MostVisitedPageLocalServiceImpl extends MostVisitedPageLocalServiceBaseImpl {
	
	@Override
	public void addMostVisitedPage(String path, int visits, String title, int position) {
		final MostVisitedPage mostVisitedPage = new MostVisitedPageImpl();
		mostVisitedPage.setMostVisitedPageId(counterLocalService.increment());
		mostVisitedPage.setPath(path);
		mostVisitedPage.setVisits(visits);
		mostVisitedPage.setTitle(title);
		mostVisitedPage.setPosition(position);
		mostVisitedPage.setVisible(true);
		addMostVisitedPage(mostVisitedPage);
	}
	
}