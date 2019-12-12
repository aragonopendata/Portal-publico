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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;

/**
 * The persistence interface for the import category registry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see es.aragon.base.categories_importer.service.persistence.impl.ImportCategoryRegistryPersistenceImpl
 * @see ImportCategoryRegistryUtil
 * @generated
 */
@ProviderType
public interface ImportCategoryRegistryPersistence extends BasePersistence<ImportCategoryRegistry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportCategoryRegistryUtil} to access the import category registry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the import category registries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import category registries
	*/
	public java.util.List<ImportCategoryRegistry> findByUuid(String uuid);

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
	public java.util.List<ImportCategoryRegistry> findByUuid(String uuid,
		int start, int end);

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
	public java.util.List<ImportCategoryRegistry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

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
	public java.util.List<ImportCategoryRegistry> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the first import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the last import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the last import category registry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where uuid = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public ImportCategoryRegistry[] findByUuid_PrevAndNext(
		long importCategoryRegistryId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Removes all the import category registries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of import category registries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import category registries
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the import category registries where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the matching import category registries
	*/
	public java.util.List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId);

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
	public java.util.List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end);

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
	public java.util.List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

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
	public java.util.List<ImportCategoryRegistry> findByVocabularyId(
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByVocabularyId_First(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByVocabularyId_First(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByVocabularyId_Last(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByVocabularyId_Last(long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where vocabularyId = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param vocabularyId the vocabulary ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public ImportCategoryRegistry[] findByVocabularyId_PrevAndNext(
		long importCategoryRegistryId, long vocabularyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Removes all the import category registries where vocabularyId = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	*/
	public void removeByVocabularyId(long vocabularyId);

	/**
	* Returns the number of import category registries where vocabularyId = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @return the number of matching import category registries
	*/
	public int countByVocabularyId(long vocabularyId);

	/**
	* Returns the import category registry where categoryId = &#63; or throws a {@link NoSuchImportCategoryRegistryException} if it could not be found.
	*
	* @param categoryId the category ID
	* @return the matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByCategoryId(long categoryId)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the import category registry where categoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param categoryId the category ID
	* @return the matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByCategoryId(long categoryId);

	/**
	* Returns the import category registry where categoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param categoryId the category ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByCategoryId(long categoryId,
		boolean retrieveFromCache);

	/**
	* Removes the import category registry where categoryId = &#63; from the database.
	*
	* @param categoryId the category ID
	* @return the import category registry that was removed
	*/
	public ImportCategoryRegistry removeByCategoryId(long categoryId)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the number of import category registries where categoryId = &#63;.
	*
	* @param categoryId the category ID
	* @return the number of matching import category registries
	*/
	public int countByCategoryId(long categoryId);

	/**
	* Returns all the import category registries where type = &#63;.
	*
	* @param type the type
	* @return the matching import category registries
	*/
	public java.util.List<ImportCategoryRegistry> findByType(String type);

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
	public java.util.List<ImportCategoryRegistry> findByType(String type,
		int start, int end);

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
	public java.util.List<ImportCategoryRegistry> findByType(String type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

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
	public java.util.List<ImportCategoryRegistry> findByType(String type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByType_First(String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the first import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByType_First(String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the last import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByType_Last(String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the last import category registry in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByType_Last(String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the import category registries before and after the current import category registry in the ordered set where type = &#63;.
	*
	* @param importCategoryRegistryId the primary key of the current import category registry
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public ImportCategoryRegistry[] findByType_PrevAndNext(
		long importCategoryRegistryId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Removes all the import category registries where type = &#63; from the database.
	*
	* @param type the type
	*/
	public void removeByType(String type);

	/**
	* Returns the number of import category registries where type = &#63;.
	*
	* @param type the type
	* @return the number of matching import category registries
	*/
	public int countByType(String type);

	/**
	* Returns all the import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @return the matching import category registries
	*/
	public java.util.List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type);

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
	public java.util.List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end);

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
	public java.util.List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

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
	public java.util.List<ImportCategoryRegistry> findByVocabularyIdType(
		long vocabularyId, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByVocabularyIdType_First(
		long vocabularyId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the first import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByVocabularyIdType_First(
		long vocabularyId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry
	* @throws NoSuchImportCategoryRegistryException if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry findByVocabularyIdType_Last(
		long vocabularyId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the last import category registry in the ordered set where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import category registry, or <code>null</code> if a matching import category registry could not be found
	*/
	public ImportCategoryRegistry fetchByVocabularyIdType_Last(
		long vocabularyId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

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
	public ImportCategoryRegistry[] findByVocabularyIdType_PrevAndNext(
		long importCategoryRegistryId, long vocabularyId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Removes all the import category registries where vocabularyId = &#63; and type = &#63; from the database.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	*/
	public void removeByVocabularyIdType(long vocabularyId, String type);

	/**
	* Returns the number of import category registries where vocabularyId = &#63; and type = &#63;.
	*
	* @param vocabularyId the vocabulary ID
	* @param type the type
	* @return the number of matching import category registries
	*/
	public int countByVocabularyIdType(long vocabularyId, String type);

	/**
	* Caches the import category registry in the entity cache if it is enabled.
	*
	* @param importCategoryRegistry the import category registry
	*/
	public void cacheResult(ImportCategoryRegistry importCategoryRegistry);

	/**
	* Caches the import category registries in the entity cache if it is enabled.
	*
	* @param importCategoryRegistries the import category registries
	*/
	public void cacheResult(
		java.util.List<ImportCategoryRegistry> importCategoryRegistries);

	/**
	* Creates a new import category registry with the primary key. Does not add the import category registry to the database.
	*
	* @param importCategoryRegistryId the primary key for the new import category registry
	* @return the new import category registry
	*/
	public ImportCategoryRegistry create(long importCategoryRegistryId);

	/**
	* Removes the import category registry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry that was removed
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public ImportCategoryRegistry remove(long importCategoryRegistryId)
		throws NoSuchImportCategoryRegistryException;

	public ImportCategoryRegistry updateImpl(
		ImportCategoryRegistry importCategoryRegistry);

	/**
	* Returns the import category registry with the primary key or throws a {@link NoSuchImportCategoryRegistryException} if it could not be found.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry
	* @throws NoSuchImportCategoryRegistryException if a import category registry with the primary key could not be found
	*/
	public ImportCategoryRegistry findByPrimaryKey(
		long importCategoryRegistryId)
		throws NoSuchImportCategoryRegistryException;

	/**
	* Returns the import category registry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param importCategoryRegistryId the primary key of the import category registry
	* @return the import category registry, or <code>null</code> if a import category registry with the primary key could not be found
	*/
	public ImportCategoryRegistry fetchByPrimaryKey(
		long importCategoryRegistryId);

	@Override
	public java.util.Map<java.io.Serializable, ImportCategoryRegistry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the import category registries.
	*
	* @return the import category registries
	*/
	public java.util.List<ImportCategoryRegistry> findAll();

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
	public java.util.List<ImportCategoryRegistry> findAll(int start, int end);

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
	public java.util.List<ImportCategoryRegistry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator);

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
	public java.util.List<ImportCategoryRegistry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportCategoryRegistry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the import category registries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of import category registries.
	*
	* @return the number of import category registries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}