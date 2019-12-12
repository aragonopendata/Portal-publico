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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ImportCategoryRegistry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryLocalServiceUtil
 * @see es.aragon.base.categories_importer.service.base.ImportCategoryRegistryLocalServiceBaseImpl
 * @see es.aragon.base.categories_importer.service.impl.ImportCategoryRegistryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ImportCategoryRegistryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportCategoryRegistryLocalServiceUtil} to access the import category registry local service. Add custom service methods to {@link es.aragon.base.categories_importer.service.impl.ImportCategoryRegistryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the import category registry to the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ImportCategoryRegistry addImportCategoryRegistry(
		ImportCategoryRegistry importCategoryRegistry);

	public void addImportCategoryRegistry(long categoryId, long vocabularyId,
		String type, String comment);

	public int countByTypeId(String type);

	public int countByVocabularyId(long vocabularyId);

	public int countByVocabularyIdType(long vocabularyId, String type);

	/**
	* Creates a new import category registry with the primary key. Does not add the import category registry to the database.
	*
	* @param importCategoryRegistryId the primary key for the new import category registry
	* @return the new import category registry
	*/
	@Transactional(enabled = false)
	public ImportCategoryRegistry createImportCategoryRegistry(
		long importCategoryRegistryId);

	/**
	* Deletes the import category registry from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ImportCategoryRegistry deleteImportCategoryRegistry(
		ImportCategoryRegistry importCategoryRegistry);

	/**
	* Deletes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry that was removed
	* @throws PortalException if a import category registry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ImportCategoryRegistry deleteImportCategoryRegistry(
		long importCategoryRegistryId) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportCategoryRegistry fetchImportCategoryRegistry(
		long importCategoryRegistryId);

	public ImportCategoryRegistry findByCategoryId(long categoryId)
		throws NoSuchImportCategoryRegistryException;

	public List<ImportCategoryRegistry> findByType(String type, int start,
		int end);

	public List<ImportCategoryRegistry> findByVocabularyId(long vocabularyId,
		int start, int end);

	public List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportCategoryRegistry> getImportCategoryRegistries(int start,
		int end);

	/**
	* Returns the number of import category registries.
	*
	* @return the number of import category registries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getImportCategoryRegistriesCount();

	/**
	* Returns the import category registry with the primary key.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry
	* @throws PortalException if a import category registry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportCategoryRegistry getImportCategoryRegistry(
		long importCategoryRegistryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the import category registry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistry the import category registry
	* @return the import category registry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ImportCategoryRegistry updateImportCategoryRegistry(
		ImportCategoryRegistry importCategoryRegistry);
}