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

package es.aragon.base.categories_importer.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ImportCategoryRegistry service. Represents a row in the &quot;EAB_CI_ImportCategoryRegistry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryModel
 * @see es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryImpl
 * @see es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryModelImpl
 * @generated
 */
@ImplementationClassName("es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryImpl")
@ProviderType
public interface ImportCategoryRegistry extends ImportCategoryRegistryModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ImportCategoryRegistry, Long> IMPORT_CATEGORY_REGISTRY_ID_ACCESSOR =
		new Accessor<ImportCategoryRegistry, Long>() {
			@Override
			public Long get(ImportCategoryRegistry importCategoryRegistry) {
				return importCategoryRegistry.getImportCategoryRegistryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImportCategoryRegistry> getTypeClass() {
				return ImportCategoryRegistry.class;
			}
		};
}