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

package es.aragon.base.migration.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the ContentImage service. Represents a row in the &quot;EAB_MG_ContentImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link es.aragon.base.migration.model.impl.ContentImageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link es.aragon.base.migration.model.impl.ContentImageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentImage
 * @see es.aragon.base.migration.model.impl.ContentImageImpl
 * @see es.aragon.base.migration.model.impl.ContentImageModelImpl
 * @generated
 */
@ProviderType
public interface ContentImageModel extends BaseModel<ContentImage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a content image model instance should use the {@link ContentImage} interface instead.
	 */

	/**
	 * Returns the primary key of this content image.
	 *
	 * @return the primary key of this content image
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this content image.
	 *
	 * @param primaryKey the primary key of this content image
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this content image.
	 *
	 * @return the uuid of this content image
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this content image.
	 *
	 * @param uuid the uuid of this content image
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the content image ID of this content image.
	 *
	 * @return the content image ID of this content image
	 */
	public long getContentImageId();

	/**
	 * Sets the content image ID of this content image.
	 *
	 * @param contentImageId the content image ID of this content image
	 */
	public void setContentImageId(long contentImageId);

	/**
	 * Returns the content origin ID of this content image.
	 *
	 * @return the content origin ID of this content image
	 */
	public long getContentOriginId();

	/**
	 * Sets the content origin ID of this content image.
	 *
	 * @param contentOriginId the content origin ID of this content image
	 */
	public void setContentOriginId(long contentOriginId);

	/**
	 * Returns the url of this content image.
	 *
	 * @return the url of this content image
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this content image.
	 *
	 * @param url the url of this content image
	 */
	public void setUrl(String url);

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
	public int compareTo(ContentImage contentImage);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ContentImage> toCacheModel();

	@Override
	public ContentImage toEscapedModel();

	@Override
	public ContentImage toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}