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

package es.aragon.base.categories_importer.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImportCategoryRegistryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryLocalService
 * @generated
 */
@ProviderType
public class ImportCategoryRegistryLocalServiceWrapper
	implements ImportCategoryRegistryLocalService,
		ServiceWrapper<ImportCategoryRegistryLocalService> {
	public ImportCategoryRegistryLocalServiceWrapper(
		ImportCategoryRegistryLocalService importCategoryRegistryLocalService) {
		_importCategoryRegistryLocalService = importCategoryRegistryLocalService;
	}

	/**
	* Adds the import category registry to the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was added
	*/
	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry addImportCategoryRegistry(
		es.aragon.base.categories_importer.model.ImportCategoryRegistry importCategoryRegistry) {
		return _importCategoryRegistryLocalService.addImportCategoryRegistry(importCategoryRegistry);
	}

	@Override
	public void addImportCategoryRegistry(long categoryId, long vocabularyId,
		String type, String comment) {
		_importCategoryRegistryLocalService.addImportCategoryRegistry(categoryId,
			vocabularyId, type, comment);
	}

	@Override
	public int countByTypeId(String type) {
		return _importCategoryRegistryLocalService.countByTypeId(type);
	}

	@Override
	public int countByVocabularyId(long vocabularyId) {
		return _importCategoryRegistryLocalService.countByVocabularyId(vocabularyId);
	}

	@Override
	public int countByVocabularyIdType(long vocabularyId, String type) {
		return _importCategoryRegistryLocalService.countByVocabularyIdType(vocabularyId,
			type);
	}

	/**
	* Creates a new import category registry with the primary key. Does not add the import category registry to the database.
	*
	* @param importCategoryRegistryId the primary key for the new import category registry
	* @return the new import category registry
	*/
	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry createImportCategoryRegistry(
		long importCategoryRegistryId) {
		return _importCategoryRegistryLocalService.createImportCategoryRegistry(importCategoryRegistryId);
	}

	/**
	* Deletes the import category registry from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was removed
	*/
	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry deleteImportCategoryRegistry(
		es.aragon.base.categories_importer.model.ImportCategoryRegistry importCategoryRegistry) {
		return _importCategoryRegistryLocalService.deleteImportCategoryRegistry(importCategoryRegistry);
	}

	/**
	* Deletes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry that was removed
	* @throws PortalException if a import category registry with the primary key could not be found
	*/
	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry deleteImportCategoryRegistry(
		long importCategoryRegistryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importCategoryRegistryLocalService.deleteImportCategoryRegistry(importCategoryRegistryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importCategoryRegistryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _importCategoryRegistryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _importCategoryRegistryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _importCategoryRegistryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _importCategoryRegistryLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _importCategoryRegistryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _importCategoryRegistryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry fetchImportCategoryRegistry(
		long importCategoryRegistryId) {
		return _importCategoryRegistryLocalService.fetchImportCategoryRegistry(importCategoryRegistryId);
	}

	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry findByCategoryId(
		long categoryId)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return _importCategoryRegistryLocalService.findByCategoryId(categoryId);
	}

	@Override
	public java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> findByType(
		String type, int start, int end) {
		return _importCategoryRegistryLocalService.findByType(type, start, end);
	}

	@Override
	public java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end) {
		return _importCategoryRegistryLocalService.findByVocabularyId(vocabularyId,
			start, end);
	}

	@Override
	public java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end) {
		return _importCategoryRegistryLocalService.findByVocabularyIdType(vocabularyId,
			type, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _importCategoryRegistryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the import category registries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link es.aragon.base.categories_importer.model.impl.ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @return the range of import category registries
	*/
	@Override
	public java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> getImportCategoryRegistries(
		int start, int end) {
		return _importCategoryRegistryLocalService.getImportCategoryRegistries(start,
			end);
	}

	/**
	* Returns the number of import category registries.
	*
	* @return the number of import category registries
	*/
	@Override
	public int getImportCategoryRegistriesCount() {
		return _importCategoryRegistryLocalService.getImportCategoryRegistriesCount();
	}

	/**
	* Returns the import category registry with the primary key.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry
	* @throws PortalException if a import category registry with the primary key could not be found
	*/
	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry getImportCategoryRegistry(
		long importCategoryRegistryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importCategoryRegistryLocalService.getImportCategoryRegistry(importCategoryRegistryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _importCategoryRegistryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _importCategoryRegistryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importCategoryRegistryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the import category registry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was updated
	*/
	@Override
	public es.aragon.base.categories_importer.model.ImportCategoryRegistry updateImportCategoryRegistry(
		es.aragon.base.categories_importer.model.ImportCategoryRegistry importCategoryRegistry) {
		return _importCategoryRegistryLocalService.updateImportCategoryRegistry(importCategoryRegistry);
	}

	@Override
	public ImportCategoryRegistryLocalService getWrappedService() {
		return _importCategoryRegistryLocalService;
	}

	@Override
	public void setWrappedService(
		ImportCategoryRegistryLocalService importCategoryRegistryLocalService) {
		_importCategoryRegistryLocalService = importCategoryRegistryLocalService;
	}

	private ImportCategoryRegistryLocalService _importCategoryRegistryLocalService;
}