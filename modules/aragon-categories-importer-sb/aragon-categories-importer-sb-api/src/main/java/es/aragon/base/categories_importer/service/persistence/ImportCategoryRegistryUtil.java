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

package es.aragon.base.categories_importer.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.aragon.base.categories_importer.model.ImportCategoryRegistry;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the import category registry service. This utility wraps {@link es.aragon.base.categories_importer.service.persistence.impl.ImportCategoryRegistryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryPersistence
 * @see es.aragon.base.categories_importer.service.persistence.impl.ImportCategoryRegistryPersistenceImpl
 * @generated
 */
@ProviderType
public class ImportCategoryRegistryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ImportCategoryRegistry importCategoryRegistry) {
		getPersistence().clearCache(importCategoryRegistry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ImportCategoryRegistry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImportCategoryRegistry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImportCategoryRegistry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImportCategoryRegistry update(
		ImportCategoryRegistry importCategoryRegistry) {
		return getPersistence().update(importCategoryRegistry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImportCategoryRegistry update(
		ImportCategoryRegistry importCategoryRegistry,
		ServiceContext serviceContext) {
		return getPersistence().update(importCategoryRegistry, serviceContext);
	}

	/**
	* Returns all the import category registries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the import category registries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @return the range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the import category registries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import category registries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByUuid_First(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByUuid_First(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByUuid_Last(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByUuid_Last(String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where uuid = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry[] findByUuid_PrevAndNext(
		long importCategoryRegistryId, String uuid,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(importCategoryRegistryId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the import category registries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of import category registries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import category registries
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the import category registries where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId) {
		return getPersistence().findByVocabularyId(vocabularyId);
	}

	/**
	* Returns a range of all the import category registries where vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @return the range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end) {
		return getPersistence().findByVocabularyId(vocabularyId, start, end);
	}

	/**
	* Returns an ordered range of all the import category registries where vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .findByVocabularyId(vocabularyId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the import category registries where vocabularyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByVocabularyId(vocabularyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByVocabularyId_First(
		long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByVocabularyId_First(vocabularyId, orderByComparator);
	}

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByVocabularyId_First(
		long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyId_First(vocabularyId, orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByVocabularyId_Last(
		long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByVocabularyId_Last(vocabularyId, orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByVocabularyId_Last(
		long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyId_Last(vocabularyId, orderByComparator);
	}

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry[] findByVocabularyId_PrevAndNext(
		long importCategoryRegistryId, long vocabularyId,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByVocabularyId_PrevAndNext(importCategoryRegistryId,
			vocabularyId, orderByComparator);
	}

	/**
	* Removes all the import category registries where vocabularyId = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	*/
	public static void removeByVocabularyId(long vocabularyId) {
		getPersistence().removeByVocabularyId(vocabularyId);
	}

	/**
	* Returns the number of import category registries where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the number of matching import category registries
	*/
	public static int countByVocabularyId(long vocabularyId) {
		return getPersistence().countByVocabularyId(vocabularyId);
	}

	/**
	* Returns the import category registry where categoryId = &#63; or throws a {@link NoSuchImportCategoryRegistryException} if it could not be found.
	*
	* @param categoryId the category ID
	* @return the matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByCategoryId(long categoryId)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().findByCategoryId(categoryId);
	}

	/**
	* Returns the import category registry where categoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param categoryId the category ID
	* @return the matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByCategoryId(long categoryId) {
		return getPersistence().fetchByCategoryId(categoryId);
	}

	/**
	* Returns the import category registry where categoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param categoryId the category ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByCategoryId(long categoryId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByCategoryId(categoryId, retrieveFromCache);
	}

	/**
	* Removes the import category registry where categoryId = &#63; from the database.
	*
	* @param categoryId the category ID
	* @return the import category registry that was removed
	*/
	public static ImportCategoryRegistry removeByCategoryId(long categoryId)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().removeByCategoryId(categoryId);
	}

	/**
	* Returns the number of import category registries where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the number of matching import category registries
	*/
	public static int countByCategoryId(long categoryId) {
		return getPersistence().countByCategoryId(categoryId);
	}

	/**
	* Returns all the import category registries where type = &#63;.
	*
	* @param type the type
	* @return the matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByType(String type) {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the import category registries where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @return the range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByType(String type,
		int start, int end) {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the import category registries where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByType(String type,
		int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import category registries where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByType(String type,
		int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByType(type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByType_First(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByType_First(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByType_Last(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByType_Last(String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where type = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry[] findByType_PrevAndNext(
		long importCategoryRegistryId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByType_PrevAndNext(importCategoryRegistryId, type,
			orderByComparator);
	}

	/**
	* Removes all the import category registries where type = &#63; from the database.
	*
	* @param type the type
	*/
	public static void removeByType(String type) {
		getPersistence().removeByType(type);
	}

	/**
	* Returns the number of import category registries where type = &#63;.
	*
	* @param type the type
	* @return the number of matching import category registries
	*/
	public static int countByType(String type) {
		return getPersistence().countByType(type);
	}

	/**
	* Returns all the import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @return the matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type) {
		return getPersistence().findByVocabularyIdType(vocabularyId, type);
	}

	/**
	* Returns a range of all the import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @return the range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end) {
		return getPersistence()
				   .findByVocabularyIdType(vocabularyId, type, start, end);
	}

	/**
	* Returns an ordered range of all the import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .findByVocabularyIdType(vocabularyId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import category registries
	*/
	public static List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByVocabularyIdType(vocabularyId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByVocabularyIdType_First(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByVocabularyIdType_First(vocabularyId, type,
			orderByComparator);
	}

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByVocabularyIdType_First(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyIdType_First(vocabularyId, type,
			orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry findByVocabularyIdType_Last(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByVocabularyIdType_Last(vocabularyId, type,
			orderByComparator);
	}

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public static ImportCategoryRegistry fetchByVocabularyIdType_Last(
		long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence()
				   .fetchByVocabularyIdType_Last(vocabularyId, type,
			orderByComparator);
	}

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry[] findByVocabularyIdType_PrevAndNext(
		long importCategoryRegistryId, long vocabularyId, String type,
		OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence()
				   .findByVocabularyIdType_PrevAndNext(importCategoryRegistryId,
			vocabularyId, type, orderByComparator);
	}

	/**
	* Removes all the import category registries where vocabularyId = &#63; and type = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	*/
	public static void removeByVocabularyIdType(long vocabularyId, String type) {
		getPersistence().removeByVocabularyIdType(vocabularyId, type);
	}

	/**
	* Returns the number of import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @return the number of matching import category registries
	*/
	public static int countByVocabularyIdType(long vocabularyId, String type) {
		return getPersistence().countByVocabularyIdType(vocabularyId, type);
	}

	/**
	* Caches the import category registry in the entity cache if it is enabled.
	*
	* @param importCategoryRegistry the import category registry
	*/
	public static void cacheResult(
		ImportCategoryRegistry importCategoryRegistry) {
		getPersistence().cacheResult(importCategoryRegistry);
	}

	/**
	* Caches the import category registries in the entity cache if it is enabled.
	*
	* @param importCategoryRegistries the import category registries
	*/
	public static void cacheResult(
		List<ImportCategoryRegistry> importCategoryRegistries) {
		getPersistence().cacheResult(importCategoryRegistries);
	}

	/**
	* Creates a new import category registry with the primary key. Does not add the import category registry to the database.
	*
	* @param importCategoryRegistryId the primary key for the new import category registry
	* @return the new import category registry
	*/
	public static ImportCategoryRegistry create(long importCategoryRegistryId) {
		return getPersistence().create(importCategoryRegistryId);
	}

	/**
	* Removes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry that was removed
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry remove(long importCategoryRegistryId)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().remove(importCategoryRegistryId);
	}

	public static ImportCategoryRegistry updateImpl(
		ImportCategoryRegistry importCategoryRegistry) {
		return getPersistence().updateImpl(importCategoryRegistry);
	}

	/**
	* Returns the import category registry with the primary key or throws a {@link NoSuchImportCategoryRegistryException} if it could not be found.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry findByPrimaryKey(
		long importCategoryRegistryId)
		throws es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException {
		return getPersistence().findByPrimaryKey(importCategoryRegistryId);
	}

	/**
	* Returns the import category registry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry, or <code>null</code> if a import category registry with the primary key could not be found
	*/
	public static ImportCategoryRegistry fetchByPrimaryKey(
		long importCategoryRegistryId) {
		return getPersistence().fetchByPrimaryKey(importCategoryRegistryId);
	}

	public static java.util.Map<java.io.Serializable, ImportCategoryRegistry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the import category registries.
	*
	* @return the import category registries
	*/
	public static List<ImportCategoryRegistry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the import category registries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @return the range of import category registries
	*/
	public static List<ImportCategoryRegistry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the import category registries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of import category registries
	*/
	public static List<ImportCategoryRegistry> findAll(int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import category registries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportCategoryRegistryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import category registries
	* @param end the upper bound of the range of import category registries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of import category registries
	*/
	public static List<ImportCategoryRegistry> findAll(int start, int end,
		OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the import category registries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of import category registries.
	*
	* @return the number of import category registries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImportCategoryRegistryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportCategoryRegistryPersistence, ImportCategoryRegistryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ImportCategoryRegistryPersistence.class);

		ServiceTracker<ImportCategoryRegistryPersistence, ImportCategoryRegistryPersistence> serviceTracker =
			new ServiceTracker<ImportCategoryRegistryPersistence, ImportCategoryRegistryPersistence>(bundle.getBundleContext(),
				ImportCategoryRegistryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}