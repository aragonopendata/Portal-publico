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

package es.aragon.base.migration.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.migration.model.ContentMetadata;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContentMetadata in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadata
 * @generated
 */
@ProviderType
public class ContentMetadataCacheModel implements CacheModel<ContentMetadata>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentMetadataCacheModel)) {
			return false;
		}

		ContentMetadataCacheModel contentMetadataCacheModel = (ContentMetadataCacheModel)obj;

		if (contentMetadataId == contentMetadataCacheModel.contentMetadataId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contentMetadataId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentMetadataId=");
		sb.append(contentMetadataId);
		sb.append(", contentId=");
		sb.append(contentId);
		sb.append(", primaryBody=");
		sb.append(primaryBody);
		sb.append(", primaryImage=");
		sb.append(primaryImage);
		sb.append(", primaryVideo=");
		sb.append(primaryVideo);
		sb.append(", secondaryBody=");
		sb.append(secondaryBody);
		sb.append(", imagesGalleryTitle=");
		sb.append(imagesGalleryTitle);
		sb.append(", imagesGalleryImages=");
		sb.append(imagesGalleryImages);
		sb.append(", linksListTitle=");
		sb.append(linksListTitle);
		sb.append(", linksListLinks=");
		sb.append(linksListLinks);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContentMetadata toEntityModel() {
		ContentMetadataImpl contentMetadataImpl = new ContentMetadataImpl();

		if (uuid == null) {
			contentMetadataImpl.setUuid("");
		}
		else {
			contentMetadataImpl.setUuid(uuid);
		}

		contentMetadataImpl.setContentMetadataId(contentMetadataId);
		contentMetadataImpl.setContentId(contentId);

		if (primaryBody == null) {
			contentMetadataImpl.setPrimaryBody("");
		}
		else {
			contentMetadataImpl.setPrimaryBody(primaryBody);
		}

		if (primaryImage == null) {
			contentMetadataImpl.setPrimaryImage("");
		}
		else {
			contentMetadataImpl.setPrimaryImage(primaryImage);
		}

		if (primaryVideo == null) {
			contentMetadataImpl.setPrimaryVideo("");
		}
		else {
			contentMetadataImpl.setPrimaryVideo(primaryVideo);
		}

		if (secondaryBody == null) {
			contentMetadataImpl.setSecondaryBody("");
		}
		else {
			contentMetadataImpl.setSecondaryBody(secondaryBody);
		}

		if (imagesGalleryTitle == null) {
			contentMetadataImpl.setImagesGalleryTitle("");
		}
		else {
			contentMetadataImpl.setImagesGalleryTitle(imagesGalleryTitle);
		}

		if (imagesGalleryImages == null) {
			contentMetadataImpl.setImagesGalleryImages("");
		}
		else {
			contentMetadataImpl.setImagesGalleryImages(imagesGalleryImages);
		}

		if (linksListTitle == null) {
			contentMetadataImpl.setLinksListTitle("");
		}
		else {
			contentMetadataImpl.setLinksListTitle(linksListTitle);
		}

		if (linksListLinks == null) {
			contentMetadataImpl.setLinksListLinks("");
		}
		else {
			contentMetadataImpl.setLinksListLinks(linksListLinks);
		}

		contentMetadataImpl.resetOriginalValues();

		return contentMetadataImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		contentMetadataId = objectInput.readLong();

		contentId = objectInput.readLong();
		primaryBody = objectInput.readUTF();
		primaryImage = objectInput.readUTF();
		primaryVideo = objectInput.readUTF();
		secondaryBody = objectInput.readUTF();
		imagesGalleryTitle = objectInput.readUTF();
		imagesGalleryImages = objectInput.readUTF();
		linksListTitle = objectInput.readUTF();
		linksListLinks = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(contentMetadataId);

		objectOutput.writeLong(contentId);

		if (primaryBody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(primaryBody);
		}

		if (primaryImage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(primaryImage);
		}

		if (primaryVideo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(primaryVideo);
		}

		if (secondaryBody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(secondaryBody);
		}

		if (imagesGalleryTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagesGalleryTitle);
		}

		if (imagesGalleryImages == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagesGalleryImages);
		}

		if (linksListTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linksListTitle);
		}

		if (linksListLinks == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linksListLinks);
		}
	}

	public String uuid;
	public long contentMetadataId;
	public long contentId;
	public String primaryBody;
	public String primaryImage;
	public String primaryVideo;
	public String secondaryBody;
	public String imagesGalleryTitle;
	public String imagesGalleryImages;
	public String linksListTitle;
	public String linksListLinks;
}