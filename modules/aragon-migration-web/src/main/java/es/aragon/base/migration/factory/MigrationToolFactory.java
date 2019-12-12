package es.aragon.base.migration.factory;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalContentSearch;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;
import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.model.ContentMetadata;
import es.aragon.base.migration.model.ContentRelated;
import es.aragon.base.migration.model.ContentUrl;
import es.aragon.base.migration.service.ContentRelatedLocalService;
import es.aragon.base.migration.service.ContentUrlLocalService;
import es.aragon.base.migration.service.ContentUrlLocalServiceUtil;
import es.aragon.base.migration.util.MigrationUtil;

public class MigrationToolFactory {

	public static JournalArticle migrateContent(long groupId, long companyId, long userId,
			Content content, ContentMetadata contentMetadata, DDMStructure ddmStructure, DDMTemplate ddmTemplate,
			ServiceContext serviceContext, String portalUrl, Locale defaultLocale,
			JournalArticleLocalService _journalArticleLocalService, JournalFolderLocalService _journalFolderLocalService, 
			RoleLocalService _roleLocalService, DLFolderLocalService _dlFolderLocalService, DLAppLocalService _dlAppLocalService,
			DLFileEntryLocalService _dlFileEntryLocalService, AssetCategoryLocalService _assetCategoryLocalService,
			AssetTagLocalService _assetTagLocalService, AssetVocabularyLocalService _assetVocabularyLocalService,
			ContentUrlLocalService _contentUrlLocalService, LayoutLocalService _layoutLocalService,
			FreemarkerUtilities _freemarkerUtilities, CounterLocalService _counterLocalService) {
		
		JournalArticle journalArticle = null;
		String title = content.getTitle();
		String description = content.getShortExcerpt();
		Date publicationDate = new Date();
		String siteDefaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);
		// Titulo del contenido
		Map<Locale,String> titleMap = new HashMap<Locale,String>();
		titleMap.put(LocaleUtil.SPAIN, title);
		// Descripcion del contenido
		Map<Locale,String> descriptionMap = new HashMap<Locale,String>();
		descriptionMap.put(LocaleUtil.SPAIN, MigrationUtil.unescapeHTML(description.replaceAll("\\<.*?>",""), false));
		//Categorias del contenido
		List<Long> categoriesIds = new ArrayList<Long>();
		String categoryIdsString = content.getTags();
		if (Validator.isNotNull(categoryIdsString) && categoryIdsString.length() > 0) {
			String[] categoryIdsArray = categoryIdsString.split(StringPool.COMMA);
			for (String categoryIdStr : categoryIdsArray) {
				categoriesIds.add(Long.valueOf(categoryIdStr));
			}
		}
		//Si el contenido esta configurado como no visible, se le asigna la categoria "No visible"
		if (content.getVisibility() == 0) {
			long noVisibleCategoryId = getNoVisibleCategoryId(groupId);
			if (noVisibleCategoryId != 0 && !categoriesIds.contains(noVisibleCategoryId)) {
				categoriesIds.add(noVisibleCategoryId);
			}
		}
		long classNameId = 0;
		long classPK = 0;
		double version = 1.0;
		String layoutUuid = null;
		String smallImageURL = null;
		String articleURL = null;
		String articleId = "";
		boolean autoArticleId = true;
		boolean indexable = true;
		boolean smallImage = false;
		
		File smallImageFile = null;
		Map<String, byte[]> images = null;
		
		// Fecha de publicacion
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(publicationDate);
		int displayDateMonth = calendar.get(Calendar.MONTH);
		int displayDateDay = calendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = calendar.get(Calendar.YEAR);
		int displayDateHour = calendar.get(Calendar.HOUR_OF_DAY);
		int displayDateMinute = calendar.get(Calendar.MINUTE);
		
		// Fecha de expiracion
		int expirationDateMonth = Calendar.getInstance().get(Calendar.MONTH);
		int expirationDateDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = Calendar.getInstance().get(Calendar.YEAR);
		int expirationDateHour = Calendar.getInstance().get(Calendar.HOUR);
		int expirationDateMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int expirationDateAmPm = 0;
		boolean neverExpire = true;
		
		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}
		
		// Fecha de revision
		int reviewDateMonth = Calendar.getInstance().get(Calendar.MONTH);
		int reviewDateDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int reviewDateYear = Calendar.getInstance().get(Calendar.YEAR);
		int reviewDateHour = Calendar.getInstance().get(Calendar.HOUR);
		int reviewDateMinute = Calendar.getInstance().get(Calendar.MINUTE);
		int reviewDateAmPm = 0;
		boolean neverReview = true;
		
		if (reviewDateAmPm == Calendar.PM) {
			reviewDateHour += 12;
		}
		
		long journalFolderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;
        //long dlFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
        String folderName = DEFAULT_FOLDER_NAME;
        
        if(Validator.isNotNull(content.getJournalFolderId())) {
        	JournalFolder journalFolder = _journalFolderLocalService.fetchJournalFolder(content.getJournalFolderId());
        	if(Validator.isNotNull(journalFolder)) {
        		folderName = journalFolder.getName() + StringPool.SLASH + "Vignette";
        	}
        }
        
    	// Carpeta en la que se almacenara el contenido
		JournalFolder journalFolder = createJournalFolder(userId, companyId, groupId, folderName, serviceContext, 
				_journalFolderLocalService, _roleLocalService);
		
		if(Validator.isNotNull(journalFolder)){
			journalFolderId = journalFolder.getFolderId();
		}
		
		// Se asignan las categorias al articulo (si las tuviese)
		assignCategoriesToArticle(categoriesIds, serviceContext, _assetCategoryLocalService);
		
		// Se asignan las etiquetas al articulo (si las tuviese)
		//assignTagsToArticle(tagsIds, serviceContext, _assetTagLocalService);
		
		// Se genera el contenido xml del articulo a partir del contenido html
		String xmlContent = MigrationUtil.generateXMLContent(ddmStructure, contentMetadata, content, siteDefaultLanguageId);
		
		ContentUrl contentUrl = _contentUrlLocalService.fetchByContentOriginId(content.getContentId());
		
		// Esta primera validacion mira que el contenido no haya sido borrado previamente
		if(Validator.isNotNull(contentUrl)) {
			journalArticle = _journalArticleLocalService.fetchArticle(contentUrl.getContentDestinationId());
			if(journalArticle==null) {
				_contentUrlLocalService.deleteContentUrl(contentUrl);
				contentUrl = null;
            } else {
                try {
                    
                    journalArticle = _journalArticleLocalService.moveArticleToTrash(userId, journalArticle);
                    List<JournalArticle> allJournalVersions = _journalArticleLocalService.getArticles(groupId, journalArticle.getArticleId());
                    for (JournalArticle journalVersion : allJournalVersions) {
                        _journalArticleLocalService.deleteArticle(groupId, journalVersion.getArticleId(), journalVersion.getVersion(), null,null);
                    }
                    
                    _log.info("BORRADO ARTICULO "+contentUrl.getContentDestinationId());
                } catch (Exception e) {
                    _log.error("IMPOSIBLE BORRAR EL ARTICULO "+contentUrl.getContentDestinationId());
                }
                _contentUrlLocalService.deleteContentUrl(contentUrl);
                contentUrl = null;
            }
		}
		
		if(Validator.isNotNull(contentUrl)) {
			
			// Si el contenido ya esta migrado, se actualiza
			journalArticle = _journalArticleLocalService.fetchArticle(contentUrl.getContentDestinationId());
			if(Validator.isNotNull(journalArticle)) {
				journalArticle = _journalArticleLocalService.fetchLatestArticle(journalArticle.getResourcePrimKey());
				try {
					if(journalFolderId != journalArticle.getFolderId()) {
						// Si el articulo tiene un folderId diferente en la actualizacion, se mueve a la nueva carpeta
						journalArticle = _journalArticleLocalService.moveArticle(groupId, journalArticle.getArticleId(), journalFolderId, serviceContext);
					}
					journalArticle = _journalArticleLocalService.updateArticle(userId, groupId, journalFolderId, journalArticle.getArticleId(), 
							journalArticle.getVersion(), titleMap, descriptionMap, xmlContent, ddmStructure.getStructureKey(),
							ddmTemplate.getTemplateKey(), journalArticle.getLayoutUuid(), displayDateMonth, displayDateDay, displayDateYear, 
							displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear, 
							expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, 
							reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, 
							smallImageFile, images, articleURL, serviceContext);
					
					contentUrl.setContentDestinationId(journalArticle.getId());
					_contentUrlLocalService.updateContentUrl(contentUrl);
					
					//_log.info("CONTENIDO " + title + " ACTUALIZADO CORRECTAMENTE.");
					
				} catch (Exception e) {
					_log.error("NO SE HA PODIDO ACTUALIZAR EL CONTENIDO" + title + ".");
					e.printStackTrace();
				}
			}else {
				_log.info("NO SE HA PODIDO ACTUALIZAR EL CONTENIDO" + title + ".");
			}
			
		} else {
			// Si el contenido aun no se ha migrado, se crea
			try {
				journalArticle = _journalArticleLocalService.addArticle(userId, groupId, journalFolderId, classNameId, 
					classPK, articleId, autoArticleId, version, titleMap, descriptionMap, xmlContent, ddmStructure.getStructureKey(), 
					ddmTemplate.getTemplateKey(), layoutUuid, displayDateMonth, displayDateDay, displayDateYear, displayDateHour, displayDateMinute, 
					expirationDateMonth, expirationDateDay, expirationDateYear, expirationDateHour, expirationDateMinute, 
					neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute, 
					neverReview, indexable, smallImage, smallImageURL, smallImageFile, images, articleURL, serviceContext);
				
				// Se crea una nueva entrada con la url origen y destino y los ids de los contenidos origen y destino
				
	            String urlDestination = _freemarkerUtilities.getArticleFullURL(journalArticle, defaultLocale);

				urlDestination = portalUrl + urlDestination;
				
				long contentUrlId = _counterLocalService.increment(ContentUrl.class.getName());
				contentUrl = _contentUrlLocalService.createContentUrl(contentUrlId);
				contentUrl.setContentOriginId(content.getContentId());
				contentUrl.setContentDestinationId(journalArticle.getId());
				contentUrl.setUrlOrigin(content.getUrl());
				contentUrl.setUrlDestination(urlDestination);
				contentUrl.setClassName(JournalArticle.class.getName());
				_contentUrlLocalService.addContentUrl(contentUrl);
				
				//_log.info("CONTENIDO " + title + " MIGRADO CORRECTAMENTE.");
				
			} catch (PortalException e) {
				_log.error("NO SE HA PODIDO CREAR EL CONTENIDO" + title + ". ", e);
			}
		}
		
		return journalArticle;
	}
	
	public static JournalFolder createJournalFolder(long userId, long companyId, long groupId, String treePath,
			ServiceContext serviceContext, JournalFolderLocalService _journalFolderLocalService, 
			RoleLocalService _roleLocalService){
		
		String description = StringPool.BLANK;
		long parentFolderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		JournalFolder journalFolder = null;
		
		// Creamos la estructura arbol de carpetas en la seccion de contenido web
		String[] treePathArray = treePath.split(StringPool.SLASH);
		for (int i = 0; i < treePathArray.length; i++) {
			String folderName = MigrationUtil.removeSpecialCharacters(treePathArray[i]);
			try {
				JournalFolder folder =  null;
				journalFolder = _journalFolderLocalService.fetchFolder(groupId, parentFolderId, folderName);
				if(Validator.isNull(journalFolder)){
					ModelPermissions modelPermissions = MigrationUtil.getModelPermissions(companyId, _roleLocalService);
					serviceContext.setModelPermissions(modelPermissions);
					
					folder = _journalFolderLocalService.addFolder(userId, groupId, parentFolderId, 
							folderName, description, serviceContext);
					
					journalFolder = _journalFolderLocalService.fetchJournalFolder(folder.getFolderId());
				}
				parentFolderId = journalFolder.getFolderId();
				
			} catch (Exception e) {
				_log.error(e);
			}
		}
		return journalFolder;
	}
	
	public static void migrateDocument(long userId, long groupId, long companyId, String treePath,
			Content content, String portalUrl, ServiceContext serviceContext, DLFolderLocalService _dlFolderLocalService, 
			DLAppLocalService _dlAppLocalService, RoleLocalService _roleLocalService,
			DLFileEntryLocalService _dlFileEntryLocalService, ContentUrlLocalService _contentUrlLocalService,
			CounterLocalService _counterLocalService, AssetCategoryLocalService _assetCategoryLocalService) {
		
		String originUrl = content.getUrl();
		originUrl = originUrl.replace(StringPool.SPACE, "%20");
		
		// Si la url no contiene www, se lo agregamos a la url
		if(!originUrl.matches("^(https?)://www.*$")){
			originUrl = MigrationUtil.modifyUrl(originUrl);
		}
		
		// Se asignan las categorias al documento (si las tuviese)
		assignCategoriesToDocument(content, serviceContext, _assetCategoryLocalService);
		
		FileEntry fileEntry = null;
		ContentUrl contentUrl = _contentUrlLocalService.fetchByContentOriginId(content.getContentId());
		
		if(Validator.isNotNull(contentUrl)) {
			// Si el contenido ya esta migrado, se actualiza
		    try {
		        long fileEntryId = contentUrl.getContentDestinationId();
				boolean majorVersion = true;
				DLFileEntry dlFileEntry = _dlFileEntryLocalService.fetchDLFileEntry(fileEntryId);
				if(Validator.isNotNull(dlFileEntry)) {
					if(dlFileEntry.getSize()<0){
						originUrl = originUrl.replaceAll("www.aragon.es", "gobierno.aragon.es");
						_log.info("Leyendo: "+originUrl);
						URL fileUrl = new URL(originUrl);
						URLConnection connection = null;
				    	connection = fileUrl.openConnection();
				        if(connection instanceof HttpURLConnection) {
				            ((HttpURLConnection)connection).setRequestMethod("HEAD");
				        }
				        InputStream inputStream = MigrationUtil.getInputStreamFromURL(fileUrl);
				        int fileSize = connection.getContentLength();
				        
						if(Validator.isNotNull(inputStream)) {
							
							fileEntry = _dlAppLocalService.updateFileEntry(userId, fileEntryId, dlFileEntry.getTitle(), dlFileEntry.getMimeType(), 
									dlFileEntry.getTitle(), dlFileEntry.getDescription(), null, majorVersion, inputStream, fileSize, serviceContext);
							
							if(fileEntry!=null) {
								String newDocumentUrl = portalUrl + "/documents/" + groupId + "/" + fileEntry.getFolderId() + "/" + 
										fileEntry.getFileName() + "/" + fileEntry.getUuid();
								_log.info("Nueva URL: "+newDocumentUrl);
							}
							
							_log.info("Vieja URL: "+contentUrl.getUrlDestination());
							_log.info("DOCUMENTO " + fileEntry.getTitle() + " ACTUALIZADO CORRECTAMENTE.");
						}
						
					}
					
				}
				
		    } catch (Exception e) {
		        _log.error("ERROR AL ACTUALIZAR EL DOCUMENTO " + content.getContentId(), e);
		    }
		}
	}
	
	public static DLFolder createDLFolderArticle(long userId, long groupId, long companyId, String treePath,
			ServiceContext serviceContext, DLFolderLocalService _DLFolderLocalService, 
			DLAppLocalService _dlAppLocalService, RoleLocalService _roleLocalService){
		
		long repositoryId = groupId;
		String description = StringPool.BLANK;
		long parentDLFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		DLFolder dlFolderArticle = null;
		
		// Creamos la estructura arbol de carpetas en documents and media
		String[] treePathArray = treePath.split(StringPool.SLASH);
		for (int i = 0; i < treePathArray.length; i++) {
			String folderName = MigrationUtil.removeSpecialCharacters(treePathArray[i]);
			try {
				Folder folder =  null;
				dlFolderArticle = _DLFolderLocalService.fetchFolder(groupId, parentDLFolderId, folderName);
				if(Validator.isNull(dlFolderArticle)){
					
					ModelPermissions modelPermissions = MigrationUtil.getModelPermissions(companyId, _roleLocalService);
					serviceContext.setModelPermissions(modelPermissions);
					
					folder = _dlAppLocalService.addFolder(userId, repositoryId, parentDLFolderId, 
							folderName, description, serviceContext);
					dlFolderArticle = _DLFolderLocalService.fetchDLFolder(folder.getFolderId());
				}
				parentDLFolderId = dlFolderArticle.getFolderId();
				
			} catch (Exception e) {
				_log.error(e);
			}
		}
		return dlFolderArticle;
	}
	
	public static FileEntry uploadDocument(URL originUrl, long dlFolderId, long groupId, long userId,
			Content content, ServiceContext serviceContext, DLAppLocalService _dlAppLocalService, 
			DLFileEntryLocalService _dlFileEntryLocalService) {
		
		FileEntry fileEntry = null;
		URLConnection connection = null;
	    try {
	    	connection = originUrl.openConnection();
	        if(connection instanceof HttpURLConnection) {
	            ((HttpURLConnection)connection).setRequestMethod("HEAD");
	        }
	        InputStream inputStream = MigrationUtil.getInputStreamFromURL(originUrl);
	        int fileSize = connection.getContentLength();
	        
			if(Validator.isNotNull(inputStream)) {
				String description = content.getShortExcerpt();
				String contentType = connection.getContentType();
				String fileName = MigrationUtil.decodeUTF8FromURL(FilenameUtils.getName(originUrl.getPath()));
				fileName = MigrationUtil.removeSpecialCharacters(fileName);
				
				// Si ya existe un documento con el mismo titulo, concatenamos el timestamp
				DLFileEntry dlFileEntry = _dlFileEntryLocalService.fetchFileEntry(groupId, dlFolderId, fileName);
				if(Validator.isNotNull(dlFileEntry)) {
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
					String timestampStr = sdf.format(timestamp);
					fileName = FilenameUtils.getBaseName(fileName) + "-" + timestampStr + "." + FilenameUtils.getExtension(fileName);
				}
				
				fileEntry = _dlAppLocalService.addFileEntry(userId, groupId, dlFolderId, fileName, contentType, 
						fileName, description, null, inputStream, fileSize, serviceContext);
			}
			
	    } catch (IOException | PortalException e) {
	        _log.error("ERROR AL CREAR EL DOCUMENTO " + content.getContentId(), e);
	        
	    }
	    return fileEntry;
	}
	
	public static void assignCategoriesToArticle(List<Long> articleCategoryIds, ServiceContext serviceContext,
			AssetCategoryLocalService _assetCategoryLocalService) {
		
		if(articleCategoryIds.size() > 0) {
			Long[] categoryIds = articleCategoryIds.toArray(new Long[articleCategoryIds.size()]);
			serviceContext.setAssetCategoryIds(MigrationUtil.toPrimitive(categoryIds));
		}
	}
	
	public static void assignTagsToArticle(List<Long> assetTagsIds, ServiceContext serviceContext,
			AssetTagLocalService _assetTagLocalService) {
		
		List<String> articleTagNames = new ArrayList<String>();
		
		if(Validator.isNotNull(assetTagsIds)) {
			for(Long assetTagId : assetTagsIds) {
				AssetTag assetTag = _assetTagLocalService.fetchAssetTag(assetTagId);
				if(Validator.isNotNull(assetTag)) {
					articleTagNames.add(assetTag.getName());
				}
			}
			if(articleTagNames.size() > 0) {
				String[] assetTagNames = articleTagNames.toArray(new String[articleTagNames.size()]);
				serviceContext.setAssetTagNames(assetTagNames);
			}
		}
	}
	
	public static String getOrganismoCategoryNameFirstLevel(List<Long> categoriesIds, AssetCategoryLocalService _assetCategoryLocalService,
			AssetVocabularyLocalService _assetVocabularyLocalService) {
		String name = StringPool.BLANK;
		if(Validator.isNotNull(categoriesIds) && categoriesIds.size() > 0) {
			long categoryId = categoriesIds.get(0);
			AssetCategory assetCategory = _assetCategoryLocalService.fetchCategory(categoryId);
			if(Validator.isNotNull(assetCategory)) {
				AssetVocabulary assetVocabulary = _assetVocabularyLocalService.fetchAssetVocabulary(assetCategory.getVocabularyId());
				if(Validator.isNotNull(assetVocabulary) && "Organismos".equalsIgnoreCase(assetVocabulary.getName())) {
					while(assetCategory.getParentCategoryId() != AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
						assetCategory = _assetCategoryLocalService.fetchAssetCategory(assetCategory.getParentCategoryId());
					}
					name = assetCategory.getName();
				}
			}
		}
		return name;
	}
	
	public static String getArticleFullURL(String articleId, Locale locale, LayoutLocalService _layoutLocalService,
			JournalContentSearchLocalService _journalContentSearchLocalService){
		String fullUrl = StringPool.BLANK;
		List<JournalContentSearch> journalContentSearchList = _journalContentSearchLocalService.getArticleContentSearches(articleId);
		if (Validator.isNotNull(journalContentSearchList) && journalContentSearchList.size() > 0) {
			JournalContentSearch journalContentSearch = journalContentSearchList.get(0);
			Layout layout = _layoutLocalService.fetchLayout(journalContentSearch.getGroupId(), journalContentSearch.getPrivateLayout(), journalContentSearch.getLayoutId());
			String groupFriendlyURL = StringPool.BLANK;
			if (layout.isPrivateLayout()) {
				groupFriendlyURL = "/group" + layout.getGroup().getFriendlyURL();
			}
			fullUrl = groupFriendlyURL + layout.getFriendlyURL(locale);
		}
		return fullUrl;
	}
	
	public static void updateJournalArticleReferences(ContentRelatedLocalService _contentRelatedLocalService,
			ContentUrlLocalService _contentUrlLocalService, JournalArticleLocalService _journalArticleLocalService) {
		
		List<ContentRelated> contentsToProcess = _contentRelatedLocalService.getContentRelateds(-1, -1);
		if(contentsToProcess!=null && contentsToProcess.size()>0) {
			for(ContentRelated contentToProcess : contentsToProcess) {
				
				long mainContentId = contentToProcess.getContentParentId();
				long relatedContentId = contentToProcess.getContentLinkedId();
				
				//Content mainContent = _contentLocalService.fetchContent(mainContentId);
				//Content relatedContent = _contentLocalService.fetchContent(relatedContentId);
				ContentUrl mainContentURL = _contentUrlLocalService.fetchByContentOriginId(mainContentId);
				ContentUrl relatedContentURL = _contentUrlLocalService.fetchByContentOriginId(relatedContentId);
				
				if(mainContentURL!=null && relatedContentURL!=null) {
					JournalArticle mainContentJournalArticle = _journalArticleLocalService.fetchArticle(mainContentURL.getContentDestinationId());
					if(mainContentJournalArticle!=null) {
						String journalContent = mainContentJournalArticle.getContent();
						
						String urlToReplace = contentToProcess.getUrl();
						String urlDestination = relatedContentURL.getUrlDestination();
						
						//System.out.println("Reemplazar esta url: ["+urlToReplace+"]");
						//System.out.println("Por esta otra url:   ["+urlDestination+"]");
						//System.out.println("En este cuerpo:\n"+journalContent);
						//System.out.println(journalContent);
						
						//journalContent = journalContent.replaceAll("\n", "");
						//System.out.println(journalContent.indexOf(urlToReplace));
						if(journalContent.indexOf(urlToReplace)!=-1) {
							
							String parte1 = journalContent.substring(0, journalContent.indexOf(urlToReplace));
							String parte2 = journalContent.substring(journalContent.indexOf(urlToReplace)+urlToReplace.length());
							String finalJournalContent = parte1+urlDestination+parte2;
							
							//System.out.println("URL actualizada para el contenido que se encuenta en: ["+mainContentURL.getUrlDestination()+"]");
							//System.out.println("Modificada la URL:\n["+urlToReplace+"]\nPor la URL:\n["+urlDestination+"]");
							
							mainContentJournalArticle.setContent(finalJournalContent);
							try {
								_journalArticleLocalService.updateJournalArticle(mainContentJournalArticle);
							} catch (Exception e) {
								_log.error(e,e);
							}
						} else {
							//System.out.println("No se ha encontrado la URL para el contenido: ["+mainContentURL.getUrlDestination()+"]");
							//System.out.println("La URL que no se ha encontrado ha sido:\n["+urlToReplace+"]\nPor la URL:\n["+urlDestination+"]");
						}
					}
				}
			}
		}
	}
	
	public static void assignCategoriesToDocument(Content content, ServiceContext serviceContext, 
			AssetCategoryLocalService _assetCategoryLocalService) {
		
		List<Long> categoriesIds = new ArrayList<Long>();
		String categoryIdsString = content.getTags();
		if(Validator.isNotNull(categoryIdsString) && categoryIdsString.length() > 0) {
			String[] categoryIdsArray = categoryIdsString.split(StringPool.COMMA);
			for(String categoryIdStr : categoryIdsArray) {
				categoriesIds.add(Long.valueOf(categoryIdStr));
			}
		}
		if(categoriesIds.size() > 0) {
			Long[] categoryIds = categoriesIds.toArray(new Long[categoriesIds.size()]);
			serviceContext.setAssetCategoryIds(MigrationUtil.toPrimitive(categoryIds));
		}
	}
	
	public static String normalizeString(String string) {
	    string = Normalizer.normalize(string, Normalizer.Form.NFD);
	    string = string.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return string.toLowerCase();
	}
	
	public static long getNoVisibleCategoryId(long groupId) {
		if (NO_VISIBLE_CATEGORY_ID == 0) {
			//Agregar categoria no visible si el content esta marcado como visible = 0
			try {				
				//Get the Visible en navegacion category
				List<AssetVocabulary> assetVocabularyList = AssetVocabularyLocalServiceUtil.getGroupVocabularies(groupId);
				if (assetVocabularyList != null && !assetVocabularyList.isEmpty()) {
					for (AssetVocabulary assetVocabulary : assetVocabularyList) {
						if (normalizeString(assetVocabulary.getName()).equals(normalizeString("Visible en navegacion"))) {
							List<AssetCategory> vocabularyAssetCategories = AssetCategoryLocalServiceUtil.getVocabularyCategories(assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
							if (vocabularyAssetCategories != null && !vocabularyAssetCategories.isEmpty()) {
								for (AssetCategory vocabularyAssetCategory : vocabularyAssetCategories) {
									if (normalizeString(vocabularyAssetCategory.getName()).equals(normalizeString("No visible"))) {	
										NO_VISIBLE_CATEGORY_ID = vocabularyAssetCategory.getCategoryId();
									}
								}
							}
							
						}
					}
				}
			} catch (Exception e) {
				_log.error("There was an error getting the visibility category: " + e.toString());
			}
		}
		return NO_VISIBLE_CATEGORY_ID;
	}
	
	private static final String DEFAULT_FOLDER_NAME = "Contenidos migrados";
	private static final Log _log = LogFactoryUtil.getLog(MigrationToolFactory.class.getName());
	private static long NO_VISIBLE_CATEGORY_ID = 0;
}
