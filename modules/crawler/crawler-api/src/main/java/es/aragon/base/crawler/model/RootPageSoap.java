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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.crawler.service.http.RootPageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.crawler.service.http.RootPageServiceSoap
 * @generated
 */
@ProviderType
public class RootPageSoap implements Serializable {
	public static RootPageSoap toSoapModel(RootPage model) {
		RootPageSoap soapModel = new RootPageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRootPageId(model.getRootPageId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCrawledDate(model.getCrawledDate());
		soapModel.setAlias(model.getAlias());
		soapModel.setInclusionRules(model.getInclusionRules());
		soapModel.setPageId(model.getPageId());
		soapModel.setScheduledCrawl(model.isScheduledCrawl());
		soapModel.setDepth(model.getDepth());

		return soapModel;
	}

	public static RootPageSoap[] toSoapModels(RootPage[] models) {
		RootPageSoap[] soapModels = new RootPageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RootPageSoap[][] toSoapModels(RootPage[][] models) {
		RootPageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RootPageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RootPageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RootPageSoap[] toSoapModels(List<RootPage> models) {
		List<RootPageSoap> soapModels = new ArrayList<RootPageSoap>(models.size());

		for (RootPage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RootPageSoap[soapModels.size()]);
	}

	public RootPageSoap() {
	}

	public long getPrimaryKey() {
		return _rootPageId;
	}

	public void setPrimaryKey(long pk) {
		setRootPageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRootPageId() {
		return _rootPageId;
	}

	public void setRootPageId(long rootPageId) {
		_rootPageId = rootPageId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCrawledDate() {
		return _crawledDate;
	}

	public void setCrawledDate(Date crawledDate) {
		_crawledDate = crawledDate;
	}

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String alias) {
		_alias = alias;
	}

	public String getInclusionRules() {
		return _inclusionRules;
	}

	public void setInclusionRules(String inclusionRules) {
		_inclusionRules = inclusionRules;
	}

	public long getPageId() {
		return _pageId;
	}

	public void setPageId(long pageId) {
		_pageId = pageId;
	}

	public boolean getScheduledCrawl() {
		return _scheduledCrawl;
	}

	public boolean isScheduledCrawl() {
		return _scheduledCrawl;
	}

	public void setScheduledCrawl(boolean scheduledCrawl) {
		_scheduledCrawl = scheduledCrawl;
	}

	public int getDepth() {
		return _depth;
	}

	public void setDepth(int depth) {
		_depth = depth;
	}

	private String _uuid;
	private long _rootPageId;
	private long _groupId;
	private long _companyId;
	private Date _crawledDate;
	private String _alias;
	private String _inclusionRules;
	private long _pageId;
	private boolean _scheduledCrawl;
	private int _depth;
}