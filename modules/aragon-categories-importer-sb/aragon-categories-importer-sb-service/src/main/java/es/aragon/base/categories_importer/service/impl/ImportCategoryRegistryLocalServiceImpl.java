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

package es.aragon.base.categories_importer.service.impl;

import java.util.Date;
import java.util.List;

import es.aragon.base.categories_importer.exception.NoSuchImportCategoryRegistryException;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;
import es.aragon.base.categories_importer.service.base.ImportCategoryRegistryLocalServiceBaseImpl;

/**
 * The implementation of the import category registry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImportCategoryRegistryLocalServiceBaseImpl
 * @see es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalServiceUtil
 */
public class ImportCategoryRegistryLocalServiceImpl	extends ImportCategoryRegistryLocalServiceBaseImpl {
	
	public void addImportCategoryRegistry(long categoryId, long vocabularyId, String type, String comment) {
		long importCategoryRegistryId = counterLocalService.increment(ImportCategoryRegistry.class.getName());
		ImportCategoryRegistry importCategoryRegistry = importCategoryRegistryPersistence.create(importCategoryRegistryId);
		importCategoryRegistry.setCategoryId(categoryId);
		importCategoryRegistry.setVocabularyId(vocabularyId);
		importCategoryRegistry.setType(type);
		importCategoryRegistry.setComment(comment);
		importCategoryRegistry.setImportDate(new Date());
		importCategoryRegistryPersistence.update(importCategoryRegistry);
	}
	
	public List<ImportCategoryRegistry> findByVocabularyId(long vocabularyId, int start,int end){
		return importCategoryRegistryPersistence.findByVocabularyId(vocabularyId, start, end);
	}
	
	public int countByVocabularyId(long vocabularyId){
		return importCategoryRegistryPersistence.countByVocabularyId(vocabularyId);
	}
	
	public ImportCategoryRegistry findByCategoryId(long categoryId) throws NoSuchImportCategoryRegistryException{
		return importCategoryRegistryPersistence.findByCategoryId(categoryId);
	}
	
	public List<ImportCategoryRegistry> findByType(String type, int start, int end){
		return importCategoryRegistryPersistence.findByType(type,start, end);
	}
	
	public int countByTypeId(String type){
		return importCategoryRegistryPersistence.countByType(type);		
	}
	
	public List<ImportCategoryRegistry> findByVocabularyIdType(long vocabularyId, String type, int start, int end){
		return importCategoryRegistryPersistence.findByVocabularyIdType(vocabularyId, type, start, end);
	}
	
	public int countByVocabularyIdType(long vocabularyId, String type){
		return importCategoryRegistryPersistence.countByVocabularyIdType(vocabularyId,type);
	}
	
}