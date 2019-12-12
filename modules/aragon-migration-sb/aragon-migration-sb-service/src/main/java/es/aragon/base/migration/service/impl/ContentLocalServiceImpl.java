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

package es.aragon.base.migration.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import es.aragon.base.migration.exception.ContentTitleException;
import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.service.base.ContentLocalServiceBaseImpl;

/**
 * The implementation of the content local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.migration.service.ContentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author pfalcon
 * @author aguillo
 * @see ContentLocalServiceBaseImpl
 * @see es.aragon.base.migration.service.ContentLocalServiceUtil
 */
public class ContentLocalServiceImpl extends ContentLocalServiceBaseImpl {

	@Override
	public void validate(String title) throws PortalException {
		if (Validator.isNull(title)) {
			throw new ContentTitleException();
		}
	}
	
	public List<Content>getContentsByAssignedUserId(long assignedUserId) {
		return contentPersistence.findByAssignedUserId(assignedUserId);
	}
	
	public List<Content> getContentsByLastModifiedUserId(long lastModifiedUserId) {
		return contentPersistence.findByLastModifiedUserId(lastModifiedUserId);
	}
	
	public List<Content> getContentsByFolderIdAndTags(long journalFolderId, long[] tags) {
		DynamicQuery dynamicQuery = super.dynamicQuery();
		if(journalFolderId != 0) {
			Property journalFolderIdProperty = PropertyFactoryUtil.forName("journalFolderId");
			dynamicQuery.add(journalFolderIdProperty.eq(journalFolderId));
		}
		for(long tag : tags) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("tags", "%" + tag + "%"));
		}
		return new ArrayList<Content>(super.dynamicQuery(dynamicQuery));
	}
	
}