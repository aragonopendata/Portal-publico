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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ContentMetadata}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadata
 * @generated
 */
@ProviderType
public class ContentMetadataWrapper implements ContentMetadata,
	ModelWrapper<ContentMetadata> {
	public ContentMetadataWrapper(ContentMetadata contentMetadata) {
		_contentMetadata = contentMetadata;
	}

	@Override
	public Class<?> getModelClass() {
		return ContentMetadata.class;
	}

	@Override
	public String getModelClassName() {
		return ContentMetadata.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contentMetadataId", getContentMetadataId());
		attributes.put("contentId", getContentId());
		attributes.put("primaryBody", getPrimaryBody());
		attributes.put("primaryImage", getPrimaryImage());
		attributes.put("primaryVideo", getPrimaryVideo());
		attributes.put("secondaryBody", getSecondaryBody());
		attributes.put("imagesGalleryTitle", getImagesGalleryTitle());
		attributes.put("imagesGalleryImages", getImagesGalleryImages());
		attributes.put("linksListTitle", getLinksListTitle());
		attributes.put("linksListLinks", getLinksListLinks());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contentMetadataId = (Long)attributes.get("contentMetadataId");

		if (contentMetadataId != null) {
			setContentMetadataId(contentMetadataId);
		}

		Long contentId = (Long)attributes.get("contentId");

		if (contentId != null) {
			setContentId(contentId);
		}

		String primaryBody = (String)attributes.get("primaryBody");

		if (primaryBody != null) {
			setPrimaryBody(primaryBody);
		}

		String primaryImage = (String)attributes.get("primaryImage");

		if (primaryImage != null) {
			setPrimaryImage(primaryImage);
		}

		String primaryVideo = (String)attributes.get("primaryVideo");

		if (primaryVideo != null) {
			setPrimaryVideo(primaryVideo);
		}

		String secondaryBody = (String)attributes.get("secondaryBody");

		if (secondaryBody != null) {
			setSecondaryBody(secondaryBody);
		}

		String imagesGalleryTitle = (String)attributes.get("imagesGalleryTitle");

		if (imagesGalleryTitle != null) {
			setImagesGalleryTitle(imagesGalleryTitle);
		}

		String imagesGalleryImages = (String)attributes.get(
				"imagesGalleryImages");

		if (imagesGalleryImages != null) {
			setImagesGalleryImages(imagesGalleryImages);
		}

		String linksListTitle = (String)attributes.get("linksListTitle");

		if (linksListTitle != null) {
			setLinksListTitle(linksListTitle);
		}

		String linksListLinks = (String)attributes.get("linksListLinks");

		if (linksListLinks != null) {
			setLinksListLinks(linksListLinks);
		}
	}

	@Override
	public Object clone() {
		return new ContentMetadataWrapper((ContentMetadata)_contentMetadata.clone());
	}

	@Override
	public int compareTo(ContentMetadata contentMetadata) {
		return _contentMetadata.compareTo(contentMetadata);
	}

	/**
	* Returns the content ID of this content metadata.
	*
	* @return the content ID of this content metadata
	*/
	@Override
	public long getContentId() {
		return _contentMetadata.getContentId();
	}

	/**
	* Returns the content metadata ID of this content metadata.
	*
	* @return the content metadata ID of this content metadata
	*/
	@Override
	public long getContentMetadataId() {
		return _contentMetadata.getContentMetadataId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contentMetadata.getExpandoBridge();
	}

	/**
	* Returns the images gallery images of this content metadata.
	*
	* @return the images gallery images of this content metadata
	*/
	@Override
	public String getImagesGalleryImages() {
		return _contentMetadata.getImagesGalleryImages();
	}

	/**
	* Returns the images gallery title of this content metadata.
	*
	* @return the images gallery title of this content metadata
	*/
	@Override
	public String getImagesGalleryTitle() {
		return _contentMetadata.getImagesGalleryTitle();
	}

	/**
	* Returns the links list links of this content metadata.
	*
	* @return the links list links of this content metadata
	*/
	@Override
	public String getLinksListLinks() {
		return _contentMetadata.getLinksListLinks();
	}

	/**
	* Returns the links list title of this content metadata.
	*
	* @return the links list title of this content metadata
	*/
	@Override
	public String getLinksListTitle() {
		return _contentMetadata.getLinksListTitle();
	}

	/**
	* Returns the primary body of this content metadata.
	*
	* @return the primary body of this content metadata
	*/
	@Override
	public String getPrimaryBody() {
		return _contentMetadata.getPrimaryBody();
	}

	/**
	* Returns the primary image of this content metadata.
	*
	* @return the primary image of this content metadata
	*/
	@Override
	public String getPrimaryImage() {
		return _contentMetadata.getPrimaryImage();
	}

	/**
	* Returns the primary key of this content metadata.
	*
	* @return the primary key of this content metadata
	*/
	@Override
	public long getPrimaryKey() {
		return _contentMetadata.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentMetadata.getPrimaryKeyObj();
	}

	/**
	* Returns the primary video of this content metadata.
	*
	* @return the primary video of this content metadata
	*/
	@Override
	public String getPrimaryVideo() {
		return _contentMetadata.getPrimaryVideo();
	}

	/**
	* Returns the secondary body of this content metadata.
	*
	* @return the secondary body of this content metadata
	*/
	@Override
	public String getSecondaryBody() {
		return _contentMetadata.getSecondaryBody();
	}

	/**
	* Returns the uuid of this content metadata.
	*
	* @return the uuid of this content metadata
	*/
	@Override
	public String getUuid() {
		return _contentMetadata.getUuid();
	}

	@Override
	public int hashCode() {
		return _contentMetadata.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _contentMetadata.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contentMetadata.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contentMetadata.isNew();
	}

	@Override
	public void persist() {
		_contentMetadata.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contentMetadata.setCachedModel(cachedModel);
	}

	/**
	* Sets the content ID of this content metadata.
	*
	* @param contentId the content ID of this content metadata
	*/
	@Override
	public void setContentId(long contentId) {
		_contentMetadata.setContentId(contentId);
	}

	/**
	* Sets the content metadata ID of this content metadata.
	*
	* @param contentMetadataId the content metadata ID of this content metadata
	*/
	@Override
	public void setContentMetadataId(long contentMetadataId) {
		_contentMetadata.setContentMetadataId(contentMetadataId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contentMetadata.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contentMetadata.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contentMetadata.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the images gallery images of this content metadata.
	*
	* @param imagesGalleryImages the images gallery images of this content metadata
	*/
	@Override
	public void setImagesGalleryImages(String imagesGalleryImages) {
		_contentMetadata.setImagesGalleryImages(imagesGalleryImages);
	}

	/**
	* Sets the images gallery title of this content metadata.
	*
	* @param imagesGalleryTitle the images gallery title of this content metadata
	*/
	@Override
	public void setImagesGalleryTitle(String imagesGalleryTitle) {
		_contentMetadata.setImagesGalleryTitle(imagesGalleryTitle);
	}

	/**
	* Sets the links list links of this content metadata.
	*
	* @param linksListLinks the links list links of this content metadata
	*/
	@Override
	public void setLinksListLinks(String linksListLinks) {
		_contentMetadata.setLinksListLinks(linksListLinks);
	}

	/**
	* Sets the links list title of this content metadata.
	*
	* @param linksListTitle the links list title of this content metadata
	*/
	@Override
	public void setLinksListTitle(String linksListTitle) {
		_contentMetadata.setLinksListTitle(linksListTitle);
	}

	@Override
	public void setNew(boolean n) {
		_contentMetadata.setNew(n);
	}

	/**
	* Sets the primary body of this content metadata.
	*
	* @param primaryBody the primary body of this content metadata
	*/
	@Override
	public void setPrimaryBody(String primaryBody) {
		_contentMetadata.setPrimaryBody(primaryBody);
	}

	/**
	* Sets the primary image of this content metadata.
	*
	* @param primaryImage the primary image of this content metadata
	*/
	@Override
	public void setPrimaryImage(String primaryImage) {
		_contentMetadata.setPrimaryImage(primaryImage);
	}

	/**
	* Sets the primary key of this content metadata.
	*
	* @param primaryKey the primary key of this content metadata
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contentMetadata.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contentMetadata.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the primary video of this content metadata.
	*
	* @param primaryVideo the primary video of this content metadata
	*/
	@Override
	public void setPrimaryVideo(String primaryVideo) {
		_contentMetadata.setPrimaryVideo(primaryVideo);
	}

	/**
	* Sets the secondary body of this content metadata.
	*
	* @param secondaryBody the secondary body of this content metadata
	*/
	@Override
	public void setSecondaryBody(String secondaryBody) {
		_contentMetadata.setSecondaryBody(secondaryBody);
	}

	/**
	* Sets the uuid of this content metadata.
	*
	* @param uuid the uuid of this content metadata
	*/
	@Override
	public void setUuid(String uuid) {
		_contentMetadata.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContentMetadata> toCacheModel() {
		return _contentMetadata.toCacheModel();
	}

	@Override
	public ContentMetadata toEscapedModel() {
		return new ContentMetadataWrapper(_contentMetadata.toEscapedModel());
	}

	@Override
	public String toString() {
		return _contentMetadata.toString();
	}

	@Override
	public ContentMetadata toUnescapedModel() {
		return new ContentMetadataWrapper(_contentMetadata.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _contentMetadata.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentMetadataWrapper)) {
			return false;
		}

		ContentMetadataWrapper contentMetadataWrapper = (ContentMetadataWrapper)obj;

		if (Objects.equals(_contentMetadata,
					contentMetadataWrapper._contentMetadata)) {
			return true;
		}

		return false;
	}

	@Override
	public ContentMetadata getWrappedModel() {
		return _contentMetadata;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contentMetadata.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contentMetadata.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contentMetadata.resetOriginalValues();
	}

	private final ContentMetadata _contentMetadata;
}