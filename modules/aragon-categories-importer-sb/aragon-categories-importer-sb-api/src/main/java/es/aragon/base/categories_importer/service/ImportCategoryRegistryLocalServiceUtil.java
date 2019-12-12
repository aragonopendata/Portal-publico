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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ImportCategoryRegistry. This utility wraps
 * {@link es.aragon.base.categories_importer.service.impl.ImportCategoryRegistryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryLocalService
 * @see es.aragon.base.categories_importer.service.base.ImportCategoryRegistryLocalServiceBaseImpl
 * @see es.aragon.base.categories_importer.service.impl.ImportCategoryRegistryLocalServiceImpl
 * @generated
 */
@ProviderType
public class ImportCategoryRegistryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link es.aragon.base.categories_importer.service.impl.ImportCategoryRegistryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the import category registry to the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was added
	*/
	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry addImportCategoryRegistry(
		es.aragon.base.categories_importer.model.ImportCategoryRegistry importCategoryRegistry) {
		return getService().addImportCategoryRegistry(importCategoryRegistry);
	}

	public static void addImportCategoryRegistry(long categoryId,
		long vocabularyId, String type, String comment) {
		getService()
			.addImportCategoryRegistry(categoryId, vocabularyId, type, comment);
	}

	public static int countByTypeId(String type) {
		return getService().countByTypeId(type);
	}

	public static int countByVocabularyId(long vocabularyId) {
		return getService().countByVocabularyId(vocabularyId);
	}

	public static int countByVocabularyIdType(long vocabularyId, String type) {
		return getService().countByVocabularyIdType(vocabularyId, type);
	}

	/**
	* Creates a new import category registry with the primary key. Does not add the import category registry to the database.
	*
	* @param importCategoryRegistryId the primary key for the new import category registry
	* @return the new import category registry
	*/
	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry createImportCategoryRegistry(
		long importCategoryRegistryId) {
		return getService()
				   .createImportCategoryRegistry(importCategoryRegistryId);
	}

	/**
	* Deletes the import category registry from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was removed
	*/
	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry deleteImportCategoryRegistry(
		es.aragon.base.categories_importer.model.ImportCategoryRegistry importCategoryRegistry) {
		return getService().deleteImportCategoryRegistry(importCategoryRegistry);
	}

	/**
	* Deletes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry that was removed
	* @throws PortalException if a import category registry with the primary key could not be found
	*/
	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry deleteImportCategoryRegistry(
		long importCategoryRegistryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteImportCategoryRegistry(importCategoryRegistryId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry fetchImportCategoryRegistry(
		long importCategoryRegistryId) {
		return getService().fetchImportCategoryRegistry(importCategoryRegistryId);
	}

	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry findByCategoryId(
		long categoryId)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getService().findByCategoryId(categoryId);
	}

	public static java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> findByType(
		String type, int start, int end) {
		return getService().findByType(type, start, end);
	}

	public static java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end) {
		return getService().findByVocabularyId(vocabularyId, start, end);
	}

	public static java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end) {
		return getService()
				   .findByVocabularyIdType(vocabularyId, type, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	public static java.util.List<es.aragon.base.categories_importer.model.ImportCategoryRegistry> getImportCategoryRegistries(
		int start, int end) {
		return getService().getImportCategoryRegistries(start, end);
	}

	/**
	* Returns the number of import category registries.
	*
	* @return the number of import category registries
	*/
	public static int getImportCategoryRegistriesCount() {
		return getService().getImportCategoryRegistriesCount();
	}

	/**
	* Returns the import category registry with the primary key.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry
	* @throws PortalException if a import category registry with the primary key could not be found
	*/
	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry getImportCategoryRegistry(
		long importCategoryRegistryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImportCategoryRegistry(importCategoryRegistryId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the import category registry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was updated
	*/
	public static es.aragon.base.categories_importer.model.ImportCategoryRegistry updateImportCategoryRegistry(
		es.aragon.base.categories_importer.model.ImportCategoryRegistry importCategoryRegistry) {
		return getService().updateImportCategoryRegistry(importCategoryRegistry);
	}

	public static ImportCategoryRegistryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportCategoryRegistryLocalService, ImportCategoryRegistryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ImportCategoryRegistryLocalService.class);

		ServiceTracker<ImportCategoryRegistryLocalService, ImportCategoryRegistryLocalService> serviceTracker =
			new ServiceTracker<ImportCategoryRegistryLocalService, ImportCategoryRegistryLocalService>(bundle.getBundleContext(),
				ImportCategoryRegistryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}