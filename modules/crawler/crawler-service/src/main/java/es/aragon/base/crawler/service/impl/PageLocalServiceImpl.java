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

package es.aragon.base.crawler.service.impl;

import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import es.aragon.base.crawler.constants.PageConstants;
import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.service.base.PageLocalServiceBaseImpl;

/**
 * The implementation of the page local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.crawler.service.PageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PageLocalServiceBaseImpl
 * @see es.aragon.base.crawler.service.PageLocalServiceUtil
 */
public class PageLocalServiceImpl extends PageLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link es.aragon.base.crawler.service.PageLocalServiceUtil} to access the page local service.
	 */
	
    private static Log log = LogFactoryUtil.getLog(PageLocalServiceImpl.class);
	
    @Override
    public Page addPage(Page page) {
    	// we must to validate that there are only utf8 available characters
    	if(page!=null && Validator.isNotNull(page.getContent())) {
    		String content = page.getContent().replaceAll("[^\\u0000-\\uFFFF]", "");
    		page.setContent(content);
    	}
    	if(page!=null && Validator.isNotNull(page.getTitle())) {
    		String title = page.getTitle().replaceAll("[^\\u0000-\\uFFFF]", "");
    		page.setTitle(title);
    	}
    	return super.addPage(page);
    }
    
    public Page createPage() {
    	return this.createPage(counterLocalService.increment(Page.class.getName()));
    }
    
	public Page addPage(Page page, long userId, long companyId, long groupId) {

		// check if rootPageId exist
		Long rootPageId = page.getRootPageId();
		if (Validator.isNotNull(rootPageId)) {
			try {
				rootPageLocalService.getRootPage(rootPageId);
			} catch (PortalException e) {
				log.error("Selected rootPage with ID '" + rootPageId + "' doesn't exist:", e);
				return null;
			}
		}
		
		// check if parentPageId exist
		Long parentPageId = page.getParentPageId();
		if (Validator.isNotNull(parentPageId)) {
			try {
				pageLocalService.getPage(parentPageId);
			} catch (PortalException e) {
				log.error("Selected parentPage with ID '" + parentPageId + "' doesn't exist:", e);
				return null;
			}
		}
		
		// check if categoryIds exists
		String categoryIds = page.getCategoryIds();
		if (Validator.isNotNull(categoryIds)) {
			long[] longCategoryIds = StringUtil.split(categoryIds, 0L);
			for (long categoryId : longCategoryIds) {
				if (Validator.isNull(categoryId) || Validator.isNull(assetCategoryLocalService.fetchAssetCategory(categoryId))) {
					log.error("Category with ID '" + categoryIds + "' doesn't exist");
					return null;
				}
			}
		}
		
		// save page in db
		Page newPage = addPage(page);
		
		// Add permission resources
		boolean portletActions = false;
		boolean addGroupPermissions = true;
		boolean addGuestPermissions = true;
		try {
			resourceLocalService.addResources(companyId, groupId, userId, Page.class.getName(),
					newPage.getPageId(), portletActions, addGroupPermissions, addGuestPermissions);
		} catch (PortalException e) {
			log.error("Error adding resource permissions", e);
		}

		return newPage;
	}
	
	public List<Page> getPagesByKeywords(long companyId, String keywords, int start, int end, OrderByComparator<Page> orderByComparator) {

//		DynamicQuery pageQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("companyId", companyId));
		DynamicQuery pageQuery = dynamicQuery();
		
		if (Validator.isNotNull(keywords)) {
			Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
//			disjunctionQuery.add(RestrictionsFactoryUtil.like("alias", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("url", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("categoryIds", "%" + keywords + "%"));
			pageQuery.add(disjunctionQuery);
		}

		return pageLocalService.dynamicQuery(pageQuery, start, end, orderByComparator);
	}
	
	public long getPagesCountByKeywords(long companyId, String keywords) {

//		DynamicQuery pageQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("companyId", companyId));
		DynamicQuery pageQuery = dynamicQuery();
		
		if (Validator.isNotNull(keywords)) {
			Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
//			disjunctionQuery.add(RestrictionsFactoryUtil.like("alias", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("url", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("categoryIds", "%" + keywords + "%"));
			pageQuery.add(disjunctionQuery);
		}

		return pageLocalService.dynamicQueryCount(pageQuery);
	}
	
	public List<Page> getPagesByKeywordsByRootPage(long companyId, String keywords, int start, int end, OrderByComparator<Page> orderByComparator, long rootPageId) {

//		DynamicQuery pageQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("companyId", companyId));
		DynamicQuery pageQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("rootPageId", rootPageId));
		
		if (Validator.isNotNull(keywords)) {
			Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
			// disjunctionQuery.add(RestrictionsFactoryUtil.like("alias", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("url", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("categoryIds", "%" + keywords + "%"));
			pageQuery.add(disjunctionQuery);
		}

		return pageLocalService.dynamicQuery(pageQuery, start, end, orderByComparator);
	}
	
	public long getPagesCountByKeywordsByRootPage(long companyId, String keywords, long rootPageId) {

//		DynamicQuery pageQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("companyId", companyId));
		DynamicQuery pageQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("rootPageId", rootPageId));

		if (Validator.isNotNull(keywords)) {
			Disjunction disjunctionQuery = RestrictionsFactoryUtil.disjunction();
//			disjunctionQuery.add(RestrictionsFactoryUtil.like("alias", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("url", "%" + keywords + "%"));
			disjunctionQuery.add(RestrictionsFactoryUtil.like("categoryIds", "%" + keywords + "%"));
			pageQuery.add(disjunctionQuery);
		}

		return pageLocalService.dynamicQueryCount(pageQuery);
	}
	
	public List<Page> getPagesByRootPageId(long rootPageId) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("rootPageId", rootPageId));
		return pageLocalService.dynamicQuery(dynamicQuery);
	}
	
	public List<Page> getChildPages(long parentPageId) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("parentPageId", parentPageId));
		return pageLocalService.dynamicQuery(dynamicQuery);
	}
	
	@Override
	public Page deletePage(Page page) {
		
		// first, change page estatus
		page.setStatus(PageConstants.PAGE_STATUS_CRAWLED_DELETING);
		page = this.updatePage(page);
		
		// then, delete childpages
		List<Page> childPages = getChildPages(page.getPageId());
		if (childPages != null && !childPages.isEmpty()) {
			for (Page childPage : childPages) {
				deletePage(childPage);
			}
		}
		
		// If is a rootPage's page delete it's rootPage too
		if (Validator.isNull(page.getParentPageId())) {
			long rootPageId = rootPageLocalService.getRootPageId(page.getPageId());
			try {
				rootPageLocalService.deleteRootPage(rootPageId);
			} catch (PortalException e) {
				log.error("Error deleting root page with ID '" + rootPageId + "':", e);
			}
		}
		
		return super.deletePage(page);
	}
	
	public long getParentPageStatus(Page page) {
		
		long status = page.getStatus();
		if (status == PageConstants.PAGE_STATUS_CRAWLED_INDEXED) {
			List<Page> childPages = getChildPages(page.getPageId());
			if (childPages != null && !childPages.isEmpty()) {
				for (Page childPage : childPages) {
					if (childPage.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_INDEXING) {
						status = PageConstants.PAGE_STATUS_CRAWLED_INDEXING;
						break;
					}
				}
			}
		}
		else if (status == PageConstants.PAGE_STATUS_CRAWLED_NO_INDEX) {
			List<Page> childPages = getChildPages(page.getPageId());
			if (childPages != null && !childPages.isEmpty()) {
				for (Page childPage : childPages) {
					if (childPage.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_UN_INDEXING
							|| childPage.getStatus() == PageConstants.PAGE_STATUS_CRAWLED_INDEXED) {
						status = PageConstants.PAGE_STATUS_CRAWLED_UN_INDEXING;
						break;
					}
				}
			}
		}
		
		return status;
	}
	
	public void deleteChildPages(Page page, boolean deletePage) {
		
		List<Page> childPages = getChildPages(page.getPageId());
		if (childPages != null && !childPages.isEmpty()) {
			for (Page childPage : childPages) {
				deleteChildPages(childPage, true);
			}
		}
		
		if (deletePage) {
			deletePage(page);
		}
	}
}