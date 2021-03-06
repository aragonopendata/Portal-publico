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
 * The base model interface for the ContentMetadata service. Represents a row in the &quot;EAB_MG_ContentMetadata&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link es.aragon.base.migration.model.impl.ContentMetadataModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link es.aragon.base.migration.model.impl.ContentMetadataImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadata
 * @see es.aragon.base.migration.model.impl.ContentMetadataImpl
 * @see es.aragon.base.migration.model.impl.ContentMetadataModelImpl
 * @generated
 */
@ProviderType
public interface ContentMetadataModel extends BaseModel<ContentMetadata> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a content metadata model instance should use the {@link ContentMetadata} interface instead.
	 */

	/**
	 * Returns the primary key of this content metadata.
	 *
	 * @return the primary key of this content metadata
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this content metadata.
	 *
	 * @param primaryKey the primary key of this content metadata
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this content metadata.
	 *
	 * @return the uuid of this content metadata
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this content metadata.
	 *
	 * @param uuid the uuid of this content metadata
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the content metadata ID of this content metadata.
	 *
	 * @return the content metadata ID of this content metadata
	 */
	public long getContentMetadataId();

	/**
	 * Sets the content metadata ID of this content metadata.
	 *
	 * @param contentMetadataId the content metadata ID of this content metadata
	 */
	public void setContentMetadataId(long contentMetadataId);

	/**
	 * Returns the content ID of this content metadata.
	 *
	 * @return the content ID of this content metadata
	 */
	public long getContentId();

	/**
	 * Sets the content ID of this content metadata.
	 *
	 * @param contentId the content ID of this content metadata
	 */
	public void setContentId(long contentId);

	/**
	 * Returns the primary body of this content metadata.
	 *
	 * @return the primary body of this content metadata
	 */
	@AutoEscape
	public String getPrimaryBody();

	/**
	 * Sets the primary body of this content metadata.
	 *
	 * @param primaryBody the primary body of this content metadata
	 */
	public void setPrimaryBody(String primaryBody);

	/**
	 * Returns the primary image of this content metadata.
	 *
	 * @return the primary image of this content metadata
	 */
	@AutoEscape
	public String getPrimaryImage();

	/**
	 * Sets the primary image of this content metadata.
	 *
	 * @param primaryImage the primary image of this content metadata
	 */
	public void setPrimaryImage(String primaryImage);

	/**
	 * Returns the primary video of this content metadata.
	 *
	 * @return the primary video of this content metadata
	 */
	@AutoEscape
	public String getPrimaryVideo();

	/**
	 * Sets the primary video of this content metadata.
	 *
	 * @param primaryVideo the primary video of this content metadata
	 */
	public void setPrimaryVideo(String primaryVideo);

	/**
	 * Returns the secondary body of this content metadata.
	 *
	 * @return the secondary body of this content metadata
	 */
	@AutoEscape
	public String getSecondaryBody();

	/**
	 * Sets the secondary body of this content metadata.
	 *
	 * @param secondaryBody the secondary body of this content metadata
	 */
	public void setSecondaryBody(String secondaryBody);

	/**
	 * Returns the images gallery title of this content metadata.
	 *
	 * @return the images gallery title of this content metadata
	 */
	@AutoEscape
	public String getImagesGalleryTitle();

	/**
	 * Sets the images gallery title of this content metadata.
	 *
	 * @param imagesGalleryTitle the images gallery title of this content metadata
	 */
	public void setImagesGalleryTitle(String imagesGalleryTitle);

	/**
	 * Returns the images gallery images of this content metadata.
	 *
	 * @return the images gallery images of this content metadata
	 */
	@AutoEscape
	public String getImagesGalleryImages();

	/**
	 * Sets the images gallery images of this content metadata.
	 *
	 * @param imagesGalleryImages the images gallery images of this content metadata
	 */
	public void setImagesGalleryImages(String imagesGalleryImages);

	/**
	 * Returns the links list title of this content metadata.
	 *
	 * @return the links list title of this content metadata
	 */
	@AutoEscape
	public String getLinksListTitle();

	/**
	 * Sets the links list title of this content metadata.
	 *
	 * @param linksListTitle the links list title of this content metadata
	 */
	public void setLinksListTitle(String linksListTitle);

	/**
	 * Returns the links list links of this content metadata.
	 *
	 * @return the links list links of this content metadata
	 */
	@AutoEscape
	public String getLinksListLinks();

	/**
	 * Sets the links list links of this content metadata.
	 *
	 * @param linksListLinks the links list links of this content metadata
	 */
	public void setLinksListLinks(String linksListLinks);

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
	public int compareTo(ContentMetadata contentMetadata);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ContentMetadata> toCacheModel();

	@Override
	public ContentMetadata toEscapedModel();

	@Override
	public ContentMetadata toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}