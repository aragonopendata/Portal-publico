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

package es.aragon.base.migration.service.impl;

import es.aragon.base.migration.model.ContentMetadata;
import es.aragon.base.migration.service.base.ContentMetadataLocalServiceBaseImpl;

/**
 * The implementation of the content metadata local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.migration.service.ContentMetadataLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentMetadataLocalServiceBaseImpl
 * @see es.aragon.base.migration.service.ContentMetadataLocalServiceUtil
 */
public class ContentMetadataLocalServiceImpl extends ContentMetadataLocalServiceBaseImpl {
	
	public ContentMetadata fetchByContentId(long contentId) {
		return contentMetadataPersistence.fetchByContentId(contentId);
	}
	
	@Override
	public ContentMetadata addContentMetadata(long contentId, String primaryBody, String primaryImage, String primaryVideo, String secondaryBody, String imagesGalleryTitle, String imagesGalleryImages, String linksListTitle, String linksListLinks) {
		long contentMetadataId = counterLocalService.increment(ContentMetadata.class.getName());
		ContentMetadata contentMetadata = contentMetadataPersistence.create(contentMetadataId);
		contentMetadata.setContentId(contentId);
		contentMetadata.setPrimaryBody(primaryBody);
		contentMetadata.setPrimaryImage(primaryImage);
		contentMetadata.setPrimaryVideo(primaryVideo);
		contentMetadata.setSecondaryBody(secondaryBody);
		contentMetadata.setImagesGalleryTitle(imagesGalleryTitle);
		contentMetadata.setImagesGalleryImages(imagesGalleryImages);
		contentMetadata.setLinksListTitle(linksListTitle);
		contentMetadata.setLinksListLinks(linksListLinks);		
		return contentMetadataPersistence.update(contentMetadata);
	}
	
}