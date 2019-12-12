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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link es.aragon.base.migration.service.http.ContentMetadataServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.migration.service.http.ContentMetadataServiceSoap
 * @generated
 */
@ProviderType
public class ContentMetadataSoap implements Serializable {
	public static ContentMetadataSoap toSoapModel(ContentMetadata model) {
		ContentMetadataSoap soapModel = new ContentMetadataSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContentMetadataId(model.getContentMetadataId());
		soapModel.setContentId(model.getContentId());
		soapModel.setPrimaryBody(model.getPrimaryBody());
		soapModel.setPrimaryImage(model.getPrimaryImage());
		soapModel.setPrimaryVideo(model.getPrimaryVideo());
		soapModel.setSecondaryBody(model.getSecondaryBody());
		soapModel.setImagesGalleryTitle(model.getImagesGalleryTitle());
		soapModel.setImagesGalleryImages(model.getImagesGalleryImages());
		soapModel.setLinksListTitle(model.getLinksListTitle());
		soapModel.setLinksListLinks(model.getLinksListLinks());

		return soapModel;
	}

	public static ContentMetadataSoap[] toSoapModels(ContentMetadata[] models) {
		ContentMetadataSoap[] soapModels = new ContentMetadataSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentMetadataSoap[][] toSoapModels(
		ContentMetadata[][] models) {
		ContentMetadataSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentMetadataSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentMetadataSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentMetadataSoap[] toSoapModels(
		List<ContentMetadata> models) {
		List<ContentMetadataSoap> soapModels = new ArrayList<ContentMetadataSoap>(models.size());

		for (ContentMetadata model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentMetadataSoap[soapModels.size()]);
	}

	public ContentMetadataSoap() {
	}

	public long getPrimaryKey() {
		return _contentMetadataId;
	}

	public void setPrimaryKey(long pk) {
		setContentMetadataId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContentMetadataId() {
		return _contentMetadataId;
	}

	public void setContentMetadataId(long contentMetadataId) {
		_contentMetadataId = contentMetadataId;
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public String getPrimaryBody() {
		return _primaryBody;
	}

	public void setPrimaryBody(String primaryBody) {
		_primaryBody = primaryBody;
	}

	public String getPrimaryImage() {
		return _primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		_primaryImage = primaryImage;
	}

	public String getPrimaryVideo() {
		return _primaryVideo;
	}

	public void setPrimaryVideo(String primaryVideo) {
		_primaryVideo = primaryVideo;
	}

	public String getSecondaryBody() {
		return _secondaryBody;
	}

	public void setSecondaryBody(String secondaryBody) {
		_secondaryBody = secondaryBody;
	}

	public String getImagesGalleryTitle() {
		return _imagesGalleryTitle;
	}

	public void setImagesGalleryTitle(String imagesGalleryTitle) {
		_imagesGalleryTitle = imagesGalleryTitle;
	}

	public String getImagesGalleryImages() {
		return _imagesGalleryImages;
	}

	public void setImagesGalleryImages(String imagesGalleryImages) {
		_imagesGalleryImages = imagesGalleryImages;
	}

	public String getLinksListTitle() {
		return _linksListTitle;
	}

	public void setLinksListTitle(String linksListTitle) {
		_linksListTitle = linksListTitle;
	}

	public String getLinksListLinks() {
		return _linksListLinks;
	}

	public void setLinksListLinks(String linksListLinks) {
		_linksListLinks = linksListLinks;
	}

	private String _uuid;
	private long _contentMetadataId;
	private long _contentId;
	private String _primaryBody;
	private String _primaryImage;
	private String _primaryVideo;
	private String _secondaryBody;
	private String _imagesGalleryTitle;
	private String _imagesGalleryImages;
	private String _linksListTitle;
	private String _linksListLinks;
}