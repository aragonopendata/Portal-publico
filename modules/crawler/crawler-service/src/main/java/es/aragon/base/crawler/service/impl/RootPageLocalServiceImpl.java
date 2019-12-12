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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.base.RootPageLocalServiceBaseImpl;

/**
 * The implementation of the root page local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.crawler.service.RootPageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RootPageLocalServiceBaseImpl
 * @see es.aragon.base.crawler.service.RootPageLocalServiceUtil
 */
public class RootPageLocalServiceImpl extends RootPageLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link es.aragon.base.crawler.service.RootPageLocalServiceUtil} to access the root page local service.
	 */
	
	private static Log log = LogFactoryUtil.getLog(RootPageLocalServiceImpl.class);
	
	public RootPage createRootPage() {
    	return this.createRootPage(counterLocalService.increment(RootPage.class.getName()));
    }
	
	@Override
	public RootPage addRootPage(RootPage rootPage) {
		// check if pageId exist
		Long pageId = rootPage.getPageId();
		if (Validator.isNotNull(pageId)) {
			try {
				pageLocalService.getPage(pageId);
			} catch (PortalException e) {
				log.error("Selected page with ID '" + pageId + "' doesn't exist:", e);
				return null;
			}
		}
		
		return super.addRootPage(rootPage);
	}
	
	public RootPage getRootPageByPageId(long pageId) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("pageId", pageId));
		List<RootPage> rootPages = rootPageLocalService.dynamicQuery(dynamicQuery);
		RootPage rootPage = null;
		if (rootPages != null && rootPages.size() == 1) {
			rootPage = rootPages.get(0);
		} else {
			if (rootPages != null && rootPages.size() > 1) {
				log.error("Founded " + rootPages.size() + " rootPages with pageId '" + pageId + "'");
			}
		}
		return rootPage;
	}
	
	public List<RootPage> getRootPages(long companyId) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("companyId", companyId));
		return rootPageLocalService.dynamicQuery(dynamicQuery);
	}
	
	public String getAlias(long pageId) {
		
		RootPage rootPage = getRootPageByPageId(pageId);
		String alias = "";
		if (rootPage != null) {
			alias = rootPage.getAlias();
		}
		return alias;
	}
	
	public Date getCrawledDate(long pageId) {
		RootPage rootPage = getRootPageByPageId(pageId);
		Date crawledDate = null;
		if (rootPage != null) {
			crawledDate = rootPage.getCrawledDate();
		}
		return crawledDate;
	}
	
	public String getInclusionRules(long pageId) {
		RootPage rootPage = getRootPageByPageId(pageId);
		String inclusionRules = "";
		if (rootPage != null) {
			inclusionRules = rootPage.getInclusionRules();
		}
		return inclusionRules;
	}
	
	public long getRootPageId(long pageId) {
		RootPage rootPage = getRootPageByPageId(pageId);
		long rootPageId = 0;
		if (rootPage != null) {
			rootPageId = rootPage.getRootPageId();
		}
		return rootPageId;
	}
	
	public List<RootPage> getRootPagesByScheduledCrawl(boolean scheduledCrawl) {
		DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("scheduledCrawl", scheduledCrawl));
		return rootPageLocalService.dynamicQuery(dynamicQuery);
	}
}