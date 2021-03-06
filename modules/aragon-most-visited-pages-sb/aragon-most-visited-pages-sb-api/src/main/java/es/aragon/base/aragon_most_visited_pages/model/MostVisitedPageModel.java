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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the MostVisitedPage service. Represents a row in the &quot;EAB_AMVP_MostVisitedPage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPage
 * @see es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl
 * @see es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl
 * @generated
 */
@ProviderType
public interface MostVisitedPageModel extends BaseModel<MostVisitedPage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a most visited page model instance should use the {@link MostVisitedPage} interface instead.
	 */

	/**
	 * Returns the primary key of this most visited page.
	 *
	 * @return the primary key of this most visited page
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this most visited page.
	 *
	 * @param primaryKey the primary key of this most visited page
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this most visited page.
	 *
	 * @return the uuid of this most visited page
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this most visited page.
	 *
	 * @param uuid the uuid of this most visited page
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the most visited page ID of this most visited page.
	 *
	 * @return the most visited page ID of this most visited page
	 */
	public long getMostVisitedPageId();

	/**
	 * Sets the most visited page ID of this most visited page.
	 *
	 * @param mostVisitedPageId the most visited page ID of this most visited page
	 */
	public void setMostVisitedPageId(long mostVisitedPageId);

	/**
	 * Returns the path of this most visited page.
	 *
	 * @return the path of this most visited page
	 */
	@AutoEscape
	public String getPath();

	/**
	 * Sets the path of this most visited page.
	 *
	 * @param path the path of this most visited page
	 */
	public void setPath(String path);

	/**
	 * Returns the visits of this most visited page.
	 *
	 * @return the visits of this most visited page
	 */
	public int getVisits();

	/**
	 * Sets the visits of this most visited page.
	 *
	 * @param visits the visits of this most visited page
	 */
	public void setVisits(int visits);

	/**
	 * Returns the title of this most visited page.
	 *
	 * @return the title of this most visited page
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this most visited page.
	 *
	 * @param title the title of this most visited page
	 */
	public void setTitle(String title);

	/**
	 * Returns the position of this most visited page.
	 *
	 * @return the position of this most visited page
	 */
	public int getPosition();

	/**
	 * Sets the position of this most visited page.
	 *
	 * @param position the position of this most visited page
	 */
	public void setPosition(int position);

	/**
	 * Returns the visible of this most visited page.
	 *
	 * @return the visible of this most visited page
	 */
	public boolean getVisible();

	/**
	 * Returns <code>true</code> if this most visited page is visible.
	 *
	 * @return <code>true</code> if this most visited page is visible; <code>false</code> otherwise
	 */
	public boolean isVisible();

	/**
	 * Sets whether this most visited page is visible.
	 *
	 * @param visible the visible of this most visited page
	 */
	public void setVisible(boolean visible);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(MostVisitedPage mostVisitedPage);

	@Override
	public int hashCode();

	@Override
	public CacheModel<MostVisitedPage> toCacheModel();

	@Override
	public MostVisitedPage toEscapedModel();

	@Override
	public MostVisitedPage toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}