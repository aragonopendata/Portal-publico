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

package es.aragon.base.crawler.model.impl;

import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.service.PageLocalService;

/**
 * The extended model implementation for the Page service. Represents a row in the &quot;EAB_CRAWLER_Page&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.crawler.model.Page} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PageImpl extends PageBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a page model instance should use the {@link es.aragon.base.crawler.model.Page} interface instead.
	 */
	public PageImpl() {
	}
	
	public void setCategoryIds(String categoryIds, PageLocalService pageLocalService) {
		
		List<Page> childPages = pageLocalService.getChildPages(this.getPageId());
		if (childPages != null && !childPages.isEmpty()) {
			
			// get deleted categories
			String deletedCategoryIds = getDeletedCategoryIds(categoryIds);
			
			for (Page childPage : childPages) {
				String childPageCategoryIds = childPage.getCategoryIds();
				if (Validator.isNotNull(childPageCategoryIds)) {
					
					childPageCategoryIds = removeDeletedCategories(childPageCategoryIds, deletedCategoryIds);
					childPageCategoryIds = addNewCategories(childPageCategoryIds, categoryIds);
					
					childPage.setCategoryIds(childPageCategoryIds, pageLocalService);
				}
				else {
					childPage.setCategoryIds(categoryIds, pageLocalService);
				}
				pageLocalService.updatePage(childPage);
			}
		}
		
		this.setCategoryIds(categoryIds);
	}
	
	private String getDeletedCategoryIds(String categoryIds) {
		StringBuilder deletedCategoryIds = new StringBuilder();
		String[] oldCategoryIds = getCategoryIds().split(",");
		for (String oldCategoryId : oldCategoryIds) {
			if (!categoryIds.contains(oldCategoryId)) {
				if (Validator.isNotNull(deletedCategoryIds.toString())) {
					deletedCategoryIds.append(",");
				}
				deletedCategoryIds.append(oldCategoryId);
			}
		}
		
		return deletedCategoryIds.toString();
	}
	
	private String removeDeletedCategories(String childPageCategoryIds, String deletedCategoryIds) {
		if (Validator.isNotNull(deletedCategoryIds)) {
			String[] splitedDeletedCategoryIds = deletedCategoryIds.split(",");
			
			for (String deletedCategoryId : splitedDeletedCategoryIds) {
				// if it's middle or last element
				if (Validator.isNotNull(deletedCategoryId) && childPageCategoryIds.contains("," + deletedCategoryId)) {
					childPageCategoryIds = childPageCategoryIds.replace("," + deletedCategoryId, "");
				} // if it's the first element
				else if (Validator.isNotNull(deletedCategoryId) && childPageCategoryIds.contains(deletedCategoryId + ",")) {
					childPageCategoryIds = childPageCategoryIds.replace(deletedCategoryId + ",", "");
				} // if it's the unique element
				else if (Validator.isNotNull(deletedCategoryId) && childPageCategoryIds.contains(deletedCategoryId)) {
					childPageCategoryIds = childPageCategoryIds.replace(deletedCategoryId, "");
				}
			}
		}
		
		return childPageCategoryIds;
	}
	
	private String addNewCategories(String childPageCategoryIds, String categoryIds) {
		String[] splitedCategoryIds = categoryIds.split(",");
		for (String categoryId : splitedCategoryIds) {
			if (!childPageCategoryIds.contains(categoryId)) {
				childPageCategoryIds = "," + categoryId;
			}
		}
		
		return childPageCategoryIds;
	}
}