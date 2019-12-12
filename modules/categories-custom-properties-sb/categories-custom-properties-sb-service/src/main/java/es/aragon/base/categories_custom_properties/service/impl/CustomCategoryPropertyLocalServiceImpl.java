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

package es.aragon.base.categories_custom_properties.service.impl;

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.base.CustomCategoryPropertyLocalServiceBaseImpl;

/**
 * The implementation of the custom category property local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyLocalServiceBaseImpl
 * @see es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil
 */
public class CustomCategoryPropertyLocalServiceImpl extends CustomCategoryPropertyLocalServiceBaseImpl {
	
	@Override
	public CustomCategoryProperty addCustomCategoryProperty(long groupId, long companyId, long userId, String userName, Date createDate, Date modifiedDate, long vocabularyId, long categoryId, String key, String text) {
		long customCategoryPropertyId = counterLocalService.increment(CustomCategoryProperty.class.getName());
		CustomCategoryProperty customCategoryProperty = customCategoryPropertyPersistence.create(customCategoryPropertyId);
		customCategoryProperty.setGroupId(groupId);
		customCategoryProperty.setCompanyId(companyId);
		customCategoryProperty.setUserId(userId);
		customCategoryProperty.setUserName(userName);
		customCategoryProperty.setCreateDate(createDate);
		customCategoryProperty.setModifiedDate(modifiedDate);
		customCategoryProperty.setVocabularyId(vocabularyId);
		customCategoryProperty.setCategoryId(categoryId);
		customCategoryProperty.setKey(key);
		customCategoryProperty.setText(text);
		return customCategoryPropertyPersistence.update(customCategoryProperty);
	}
	
	@Override
	public List<CustomCategoryProperty> findByCategoryId(long categoryId) {
		return customCategoryPropertyPersistence.findByCategoryId(categoryId);
	}
	
	@Override
	public List<CustomCategoryProperty> findByCategoryId(long categoryId, int start, int end, OrderByComparator<CustomCategoryProperty> orderByComparator, boolean retrieveFromCache) {
		return customCategoryPropertyPersistence.findByCategoryId(categoryId, start, end, orderByComparator, retrieveFromCache);
	}
	
	@Override
	public CustomCategoryProperty fetchByCategoryIdAndKey(long categoryId, String key) {
		return customCategoryPropertyPersistence.fetchByCategoryIdAndKey(categoryId, key);
	}
	
	@Override
	public List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(long vocabularyId, String key, String text) {
		return customCategoryPropertyPersistence.findByVocabularyIdAndKeyAndText(vocabularyId, key, text);
	}	
}