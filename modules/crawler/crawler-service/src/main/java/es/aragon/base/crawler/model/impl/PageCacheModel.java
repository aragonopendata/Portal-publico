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

package es.aragon.base.crawler.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.crawler.model.Page;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Page in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Page
 * @generated
 */
@ProviderType
public class PageCacheModel implements CacheModel<Page>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageCacheModel)) {
			return false;
		}

		PageCacheModel pageCacheModel = (PageCacheModel)obj;

		if (pageId == pageCacheModel.pageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, pageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", pageId=");
		sb.append(pageId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", contentType=");
		sb.append(contentType);
		sb.append(", metaDescription=");
		sb.append(metaDescription);
		sb.append(", metaKeywords=");
		sb.append(metaKeywords);
		sb.append(", content=");
		sb.append(content);
		sb.append(", url=");
		sb.append(url);
		sb.append(", categoryIds=");
		sb.append(categoryIds);
		sb.append(", rootPageId=");
		sb.append(rootPageId);
		sb.append(", parentPageId=");
		sb.append(parentPageId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Page toEntityModel() {
		PageImpl pageImpl = new PageImpl();

		if (uuid == null) {
			pageImpl.setUuid("");
		}
		else {
			pageImpl.setUuid(uuid);
		}

		pageImpl.setPageId(pageId);

		if (title == null) {
			pageImpl.setTitle("");
		}
		else {
			pageImpl.setTitle(title);
		}

		if (contentType == null) {
			pageImpl.setContentType("");
		}
		else {
			pageImpl.setContentType(contentType);
		}

		if (metaDescription == null) {
			pageImpl.setMetaDescription("");
		}
		else {
			pageImpl.setMetaDescription(metaDescription);
		}

		if (metaKeywords == null) {
			pageImpl.setMetaKeywords("");
		}
		else {
			pageImpl.setMetaKeywords(metaKeywords);
		}

		if (content == null) {
			pageImpl.setContent("");
		}
		else {
			pageImpl.setContent(content);
		}

		if (url == null) {
			pageImpl.setUrl("");
		}
		else {
			pageImpl.setUrl(url);
		}

		if (categoryIds == null) {
			pageImpl.setCategoryIds("");
		}
		else {
			pageImpl.setCategoryIds(categoryIds);
		}

		pageImpl.setRootPageId(rootPageId);
		pageImpl.setParentPageId(parentPageId);
		pageImpl.setStatus(status);

		pageImpl.resetOriginalValues();

		return pageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		pageId = objectInput.readLong();
		title = objectInput.readUTF();
		contentType = objectInput.readUTF();
		metaDescription = objectInput.readUTF();
		metaKeywords = objectInput.readUTF();
		content = objectInput.readUTF();
		url = objectInput.readUTF();
		categoryIds = objectInput.readUTF();

		rootPageId = objectInput.readLong();

		parentPageId = objectInput.readLong();

		status = objectInput.readLong();
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

		objectOutput.writeLong(pageId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (contentType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contentType);
		}

		if (metaDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaDescription);
		}

		if (metaKeywords == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(metaKeywords);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (categoryIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(categoryIds);
		}

		objectOutput.writeLong(rootPageId);

		objectOutput.writeLong(parentPageId);

		objectOutput.writeLong(status);
	}

	public String uuid;
	public long pageId;
	public String title;
	public String contentType;
	public String metaDescription;
	public String metaKeywords;
	public String content;
	public String url;
	public String categoryIds;
	public long rootPageId;
	public long parentPageId;
	public long status;
}