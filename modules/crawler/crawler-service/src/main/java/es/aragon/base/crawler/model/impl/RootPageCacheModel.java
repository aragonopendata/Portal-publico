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

import es.aragon.base.crawler.model.RootPage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RootPage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RootPage
 * @generated
 */
@ProviderType
public class RootPageCacheModel implements CacheModel<RootPage>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RootPageCacheModel)) {
			return false;
		}

		RootPageCacheModel rootPageCacheModel = (RootPageCacheModel)obj;

		if (rootPageId == rootPageCacheModel.rootPageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rootPageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", rootPageId=");
		sb.append(rootPageId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", crawledDate=");
		sb.append(crawledDate);
		sb.append(", alias=");
		sb.append(alias);
		sb.append(", inclusionRules=");
		sb.append(inclusionRules);
		sb.append(", pageId=");
		sb.append(pageId);
		sb.append(", scheduledCrawl=");
		sb.append(scheduledCrawl);
		sb.append(", depth=");
		sb.append(depth);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RootPage toEntityModel() {
		RootPageImpl rootPageImpl = new RootPageImpl();

		if (uuid == null) {
			rootPageImpl.setUuid("");
		}
		else {
			rootPageImpl.setUuid(uuid);
		}

		rootPageImpl.setRootPageId(rootPageId);
		rootPageImpl.setGroupId(groupId);
		rootPageImpl.setCompanyId(companyId);

		if (crawledDate == Long.MIN_VALUE) {
			rootPageImpl.setCrawledDate(null);
		}
		else {
			rootPageImpl.setCrawledDate(new Date(crawledDate));
		}

		if (alias == null) {
			rootPageImpl.setAlias("");
		}
		else {
			rootPageImpl.setAlias(alias);
		}

		if (inclusionRules == null) {
			rootPageImpl.setInclusionRules("");
		}
		else {
			rootPageImpl.setInclusionRules(inclusionRules);
		}

		rootPageImpl.setPageId(pageId);
		rootPageImpl.setScheduledCrawl(scheduledCrawl);
		rootPageImpl.setDepth(depth);

		rootPageImpl.resetOriginalValues();

		return rootPageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		rootPageId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		crawledDate = objectInput.readLong();
		alias = objectInput.readUTF();
		inclusionRules = objectInput.readUTF();

		pageId = objectInput.readLong();

		scheduledCrawl = objectInput.readBoolean();

		depth = objectInput.readInt();
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

		objectOutput.writeLong(rootPageId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(crawledDate);

		if (alias == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(alias);
		}

		if (inclusionRules == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(inclusionRules);
		}

		objectOutput.writeLong(pageId);

		objectOutput.writeBoolean(scheduledCrawl);

		objectOutput.writeInt(depth);
	}

	public String uuid;
	public long rootPageId;
	public long groupId;
	public long companyId;
	public long crawledDate;
	public String alias;
	public String inclusionRules;
	public long pageId;
	public boolean scheduledCrawl;
	public int depth;
}