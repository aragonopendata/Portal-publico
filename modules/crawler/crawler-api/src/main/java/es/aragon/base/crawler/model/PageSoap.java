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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.crawler.service.http.PageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.crawler.service.http.PageServiceSoap
 * @generated
 */
@ProviderType
public class PageSoap implements Serializable {
	public static PageSoap toSoapModel(Page model) {
		PageSoap soapModel = new PageSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPageId(model.getPageId());
		soapModel.setTitle(model.getTitle());
		soapModel.setContentType(model.getContentType());
		soapModel.setMetaDescription(model.getMetaDescription());
		soapModel.setMetaKeywords(model.getMetaKeywords());
		soapModel.setContent(model.getContent());
		soapModel.setUrl(model.getUrl());
		soapModel.setCategoryIds(model.getCategoryIds());
		soapModel.setRootPageId(model.getRootPageId());
		soapModel.setParentPageId(model.getParentPageId());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static PageSoap[] toSoapModels(Page[] models) {
		PageSoap[] soapModels = new PageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PageSoap[][] toSoapModels(Page[][] models) {
		PageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PageSoap[] toSoapModels(List<Page> models) {
		List<PageSoap> soapModels = new ArrayList<PageSoap>(models.size());

		for (Page model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PageSoap[soapModels.size()]);
	}

	public PageSoap() {
	}

	public long getPrimaryKey() {
		return _pageId;
	}

	public void setPrimaryKey(long pk) {
		setPageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPageId() {
		return _pageId;
	}

	public void setPageId(long pageId) {
		_pageId = pageId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getContentType() {
		return _contentType;
	}

	public void setContentType(String contentType) {
		_contentType = contentType;
	}

	public String getMetaDescription() {
		return _metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		_metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return _metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		_metaKeywords = metaKeywords;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getCategoryIds() {
		return _categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		_categoryIds = categoryIds;
	}

	public long getRootPageId() {
		return _rootPageId;
	}

	public void setRootPageId(long rootPageId) {
		_rootPageId = rootPageId;
	}

	public long getParentPageId() {
		return _parentPageId;
	}

	public void setParentPageId(long parentPageId) {
		_parentPageId = parentPageId;
	}

	public long getStatus() {
		return _status;
	}

	public void setStatus(long status) {
		_status = status;
	}

	private String _uuid;
	private long _pageId;
	private String _title;
	private String _contentType;
	private String _metaDescription;
	private String _metaKeywords;
	private String _content;
	private String _url;
	private String _categoryIds;
	private long _rootPageId;
	private long _parentPageId;
	private long _status;
}