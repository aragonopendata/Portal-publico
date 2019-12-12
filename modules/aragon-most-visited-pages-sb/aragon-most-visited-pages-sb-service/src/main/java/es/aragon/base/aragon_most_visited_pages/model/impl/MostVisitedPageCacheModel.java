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

package es.aragon.base.aragon_most_visited_pages.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MostVisitedPage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPage
 * @generated
 */
@ProviderType
public class MostVisitedPageCacheModel implements CacheModel<MostVisitedPage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MostVisitedPageCacheModel)) {
			return false;
		}

		MostVisitedPageCacheModel mostVisitedPageCacheModel = (MostVisitedPageCacheModel)obj;

		if (mostVisitedPageId == mostVisitedPageCacheModel.mostVisitedPageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mostVisitedPageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", mostVisitedPageId=");
		sb.append(mostVisitedPageId);
		sb.append(", path=");
		sb.append(path);
		sb.append(", visits=");
		sb.append(visits);
		sb.append(", title=");
		sb.append(title);
		sb.append(", position=");
		sb.append(position);
		sb.append(", visible=");
		sb.append(visible);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MostVisitedPage toEntityModel() {
		MostVisitedPageImpl mostVisitedPageImpl = new MostVisitedPageImpl();

		if (uuid == null) {
			mostVisitedPageImpl.setUuid("");
		}
		else {
			mostVisitedPageImpl.setUuid(uuid);
		}

		mostVisitedPageImpl.setMostVisitedPageId(mostVisitedPageId);

		if (path == null) {
			mostVisitedPageImpl.setPath("");
		}
		else {
			mostVisitedPageImpl.setPath(path);
		}

		mostVisitedPageImpl.setVisits(visits);

		if (title == null) {
			mostVisitedPageImpl.setTitle("");
		}
		else {
			mostVisitedPageImpl.setTitle(title);
		}

		mostVisitedPageImpl.setPosition(position);
		mostVisitedPageImpl.setVisible(visible);

		mostVisitedPageImpl.resetOriginalValues();

		return mostVisitedPageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		mostVisitedPageId = objectInput.readLong();
		path = objectInput.readUTF();

		visits = objectInput.readInt();
		title = objectInput.readUTF();

		position = objectInput.readInt();

		visible = objectInput.readBoolean();
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

		objectOutput.writeLong(mostVisitedPageId);

		if (path == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(path);
		}

		objectOutput.writeInt(visits);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(position);

		objectOutput.writeBoolean(visible);
	}

	public String uuid;
	public long mostVisitedPageId;
	public String path;
	public int visits;
	public String title;
	public int position;
	public boolean visible;
}