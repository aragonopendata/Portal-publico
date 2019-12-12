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
 * This class is a wrapper for {@link Template}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Template
 * @generated
 */
@ProviderType
public class TemplateWrapper implements Template, ModelWrapper<Template> {
	public TemplateWrapper(Template template) {
		_template = template;
	}

	@Override
	public Class<?> getModelClass() {
		return Template.class;
	}

	@Override
	public String getModelClassName() {
		return Template.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("templateId", getTemplateId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long templateId = (Long)attributes.get("templateId");

		if (templateId != null) {
			setTemplateId(templateId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new TemplateWrapper((Template)_template.clone());
	}

	@Override
	public int compareTo(Template template) {
		return _template.compareTo(template);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _template.getExpandoBridge();
	}

	/**
	* Returns the name of this template.
	*
	* @return the name of this template
	*/
	@Override
	public String getName() {
		return _template.getName();
	}

	/**
	* Returns the primary key of this template.
	*
	* @return the primary key of this template
	*/
	@Override
	public long getPrimaryKey() {
		return _template.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _template.getPrimaryKeyObj();
	}

	/**
	* Returns the template ID of this template.
	*
	* @return the template ID of this template
	*/
	@Override
	public long getTemplateId() {
		return _template.getTemplateId();
	}

	/**
	* Returns the uuid of this template.
	*
	* @return the uuid of this template
	*/
	@Override
	public String getUuid() {
		return _template.getUuid();
	}

	@Override
	public int hashCode() {
		return _template.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _template.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _template.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _template.isNew();
	}

	@Override
	public void persist() {
		_template.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_template.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_template.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_template.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_template.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the name of this template.
	*
	* @param name the name of this template
	*/
	@Override
	public void setName(String name) {
		_template.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_template.setNew(n);
	}

	/**
	* Sets the primary key of this template.
	*
	* @param primaryKey the primary key of this template
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_template.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_template.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the template ID of this template.
	*
	* @param templateId the template ID of this template
	*/
	@Override
	public void setTemplateId(long templateId) {
		_template.setTemplateId(templateId);
	}

	/**
	* Sets the uuid of this template.
	*
	* @param uuid the uuid of this template
	*/
	@Override
	public void setUuid(String uuid) {
		_template.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Template> toCacheModel() {
		return _template.toCacheModel();
	}

	@Override
	public Template toEscapedModel() {
		return new TemplateWrapper(_template.toEscapedModel());
	}

	@Override
	public String toString() {
		return _template.toString();
	}

	@Override
	public Template toUnescapedModel() {
		return new TemplateWrapper(_template.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _template.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TemplateWrapper)) {
			return false;
		}

		TemplateWrapper templateWrapper = (TemplateWrapper)obj;

		if (Objects.equals(_template, templateWrapper._template)) {
			return true;
		}

		return false;
	}

	@Override
	public Template getWrappedModel() {
		return _template;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _template.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _template.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_template.resetOriginalValues();
	}

	private final Template _template;
}