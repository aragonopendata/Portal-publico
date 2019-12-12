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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MostVisitedPage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPage
 * @generated
 */
@ProviderType
public class MostVisitedPageWrapper implements MostVisitedPage,
	ModelWrapper<MostVisitedPage> {
	public MostVisitedPageWrapper(MostVisitedPage mostVisitedPage) {
		_mostVisitedPage = mostVisitedPage;
	}

	@Override
	public Class<?> getModelClass() {
		return MostVisitedPage.class;
	}

	@Override
	public String getModelClassName() {
		return MostVisitedPage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("mostVisitedPageId", getMostVisitedPageId());
		attributes.put("path", getPath());
		attributes.put("visits", getVisits());
		attributes.put("title", getTitle());
		attributes.put("position", getPosition());
		attributes.put("visible", isVisible());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long mostVisitedPageId = (Long)attributes.get("mostVisitedPageId");

		if (mostVisitedPageId != null) {
			setMostVisitedPageId(mostVisitedPageId);
		}

		String path = (String)attributes.get("path");

		if (path != null) {
			setPath(path);
		}

		Integer visits = (Integer)attributes.get("visits");

		if (visits != null) {
			setVisits(visits);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}

		Boolean visible = (Boolean)attributes.get("visible");

		if (visible != null) {
			setVisible(visible);
		}
	}

	@Override
	public Object clone() {
		return new MostVisitedPageWrapper((MostVisitedPage)_mostVisitedPage.clone());
	}

	@Override
	public int compareTo(MostVisitedPage mostVisitedPage) {
		return _mostVisitedPage.compareTo(mostVisitedPage);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mostVisitedPage.getExpandoBridge();
	}

	/**
	* Returns the most visited page ID of this most visited page.
	*
	* @return the most visited page ID of this most visited page
	*/
	@Override
	public long getMostVisitedPageId() {
		return _mostVisitedPage.getMostVisitedPageId();
	}

	/**
	* Returns the path of this most visited page.
	*
	* @return the path of this most visited page
	*/
	@Override
	public String getPath() {
		return _mostVisitedPage.getPath();
	}

	/**
	* Returns the position of this most visited page.
	*
	* @return the position of this most visited page
	*/
	@Override
	public int getPosition() {
		return _mostVisitedPage.getPosition();
	}

	/**
	* Returns the primary key of this most visited page.
	*
	* @return the primary key of this most visited page
	*/
	@Override
	public long getPrimaryKey() {
		return _mostVisitedPage.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mostVisitedPage.getPrimaryKeyObj();
	}

	/**
	* Returns the title of this most visited page.
	*
	* @return the title of this most visited page
	*/
	@Override
	public String getTitle() {
		return _mostVisitedPage.getTitle();
	}

	/**
	* Returns the uuid of this most visited page.
	*
	* @return the uuid of this most visited page
	*/
	@Override
	public String getUuid() {
		return _mostVisitedPage.getUuid();
	}

	/**
	* Returns the visible of this most visited page.
	*
	* @return the visible of this most visited page
	*/
	@Override
	public boolean getVisible() {
		return _mostVisitedPage.getVisible();
	}

	/**
	* Returns the visits of this most visited page.
	*
	* @return the visits of this most visited page
	*/
	@Override
	public int getVisits() {
		return _mostVisitedPage.getVisits();
	}

	@Override
	public int hashCode() {
		return _mostVisitedPage.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mostVisitedPage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mostVisitedPage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mostVisitedPage.isNew();
	}

	/**
	* Returns <code>true</code> if this most visited page is visible.
	*
	* @return <code>true</code> if this most visited page is visible; <code>false</code> otherwise
	*/
	@Override
	public boolean isVisible() {
		return _mostVisitedPage.isVisible();
	}

	@Override
	public void persist() {
		_mostVisitedPage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mostVisitedPage.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mostVisitedPage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mostVisitedPage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mostVisitedPage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the most visited page ID of this most visited page.
	*
	* @param mostVisitedPageId the most visited page ID of this most visited page
	*/
	@Override
	public void setMostVisitedPageId(long mostVisitedPageId) {
		_mostVisitedPage.setMostVisitedPageId(mostVisitedPageId);
	}

	@Override
	public void setNew(boolean n) {
		_mostVisitedPage.setNew(n);
	}

	/**
	* Sets the path of this most visited page.
	*
	* @param path the path of this most visited page
	*/
	@Override
	public void setPath(String path) {
		_mostVisitedPage.setPath(path);
	}

	/**
	* Sets the position of this most visited page.
	*
	* @param position the position of this most visited page
	*/
	@Override
	public void setPosition(int position) {
		_mostVisitedPage.setPosition(position);
	}

	/**
	* Sets the primary key of this most visited page.
	*
	* @param primaryKey the primary key of this most visited page
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_mostVisitedPage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mostVisitedPage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the title of this most visited page.
	*
	* @param title the title of this most visited page
	*/
	@Override
	public void setTitle(String title) {
		_mostVisitedPage.setTitle(title);
	}

	/**
	* Sets the uuid of this most visited page.
	*
	* @param uuid the uuid of this most visited page
	*/
	@Override
	public void setUuid(String uuid) {
		_mostVisitedPage.setUuid(uuid);
	}

	/**
	* Sets whether this most visited page is visible.
	*
	* @param visible the visible of this most visited page
	*/
	@Override
	public void setVisible(boolean visible) {
		_mostVisitedPage.setVisible(visible);
	}

	/**
	* Sets the visits of this most visited page.
	*
	* @param visits the visits of this most visited page
	*/
	@Override
	public void setVisits(int visits) {
		_mostVisitedPage.setVisits(visits);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MostVisitedPage> toCacheModel() {
		return _mostVisitedPage.toCacheModel();
	}

	@Override
	public MostVisitedPage toEscapedModel() {
		return new MostVisitedPageWrapper(_mostVisitedPage.toEscapedModel());
	}

	@Override
	public String toString() {
		return _mostVisitedPage.toString();
	}

	@Override
	public MostVisitedPage toUnescapedModel() {
		return new MostVisitedPageWrapper(_mostVisitedPage.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _mostVisitedPage.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MostVisitedPageWrapper)) {
			return false;
		}

		MostVisitedPageWrapper mostVisitedPageWrapper = (MostVisitedPageWrapper)obj;

		if (Objects.equals(_mostVisitedPage,
					mostVisitedPageWrapper._mostVisitedPage)) {
			return true;
		}

		return false;
	}

	@Override
	public MostVisitedPage getWrappedModel() {
		return _mostVisitedPage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mostVisitedPage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mostVisitedPage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mostVisitedPage.resetOriginalValues();
	}

	private final MostVisitedPage _mostVisitedPage;
}