package es.aragon.base.portal.utilities.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;

public class AragonPortalUtilitiesContentUtils {
	
	private AragonPortalUtilitiesContentUtils() {
		
	}

	/**
	 * @param groupId
	 * @param folderId
	 * @param portalURL
	 * @return
	 * @throws PortalException
	 */
	public static AragonPortalUtilitiesMapHierarchy<JournalArticle> getWebContentMapData(long groupId, long folderId, String portalURL) throws PortalException {	
		AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap = new AragonPortalUtilitiesMapHierarchy<>();
		Set<JournalArticle> folderJournalArticles = getAllArticlesFolderAndSubFolders(folderId, groupId);

		setCategoriesData(dataMap, folderJournalArticles, portalURL);
				
		/*for(JournalArticle journalArticle : allJournalArticles) {
			if(journalArticle.getStatus() == WorkflowConstants.STATUS_APPROVED &&
					JournalArticleLocalServiceUtil.isLatestVersion(journalArticle.getGroupId(), journalArticle.getArticleId(), journalArticle.getVersion())) {
				for(JournalArticle matched : allMatches(Pattern.compile("href=\\\".*?\\/-\\/(.*?)\\\""), journalArticle.getContent(), journalArticle.getGroupId())){
					chekMatchedJournalArticle(dataMap, journalArticle, matched);
				}
				
				for(JournalArticle matched : allMatches(Pattern.compile("\\[CDATA\\[.*\\/-\\/(.*)\\]\\]"), journalArticle.getContent(), journalArticle.getGroupId())){
					chekMatchedJournalArticle(dataMap, journalArticle, matched);
				}
			}
		}			
		*/
		return dataMap;
	}
	
	/**
	 * @param groupId
	 * @param folderId
	 * @param portalURL
	 * @return
	 * @throws PortalException
	 */
	public static AragonPortalUtilitiesMapHierarchy<DLFileEntry> getDlFileEntryMapData(long groupId, long folderId, String portalURL) throws PortalException {	
		AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap = new AragonPortalUtilitiesMapHierarchy<>();
		List<JournalArticle> allJournalArticles = JournalArticleLocalServiceUtil.getArticles();
		
		Set<String> noMappedDlFileEntries = new TreeSet<>();
		
		Set<DLFileEntry> dlFileEntries = new TreeSet<>();
		getAllDocumentsFolderAndSubFolders(folderId, groupId, dlFileEntries);
		
		Set<String> uuidAllDlFileEntries = new HashSet<>();
		for(DLFileEntry loopDlFileEntry : dlFileEntries) {
			uuidAllDlFileEntries.add(loopDlFileEntry.getUuid());
		}
		
		for(DLFileEntry dlFileEntry : dlFileEntries) {
			noMappedDlFileEntries.add(dlFileEntry.getUuid());
		}
		
		for(JournalArticle journalArticle : allJournalArticles) {
			if(journalArticle.getStatus() == WorkflowConstants.STATUS_APPROVED &&
					JournalArticleLocalServiceUtil.isLatestVersion(journalArticle.getGroupId(), journalArticle.getArticleId(), journalArticle.getVersion())) {
				for(DLFileEntry matched : allMatchesDlFileEntry(Pattern.compile("href=\\\".*?\\/.+\\/(.*?)(\\?.*)?(\\#.*)?\\\""), journalArticle.getContent(), journalArticle.getGroupId(), false)){
					if(Validator.isNotNull(matched) && uuidAllDlFileEntries.contains(matched.getUuid())) {
						buildMatchedDlFileEntry(dataMap, journalArticle, portalURL, matched, noMappedDlFileEntries);
					}
				}
				
				for(DLFileEntry matched : allMatchesDlFileEntry(Pattern.compile("\\[CDATA\\[.*\\/-\\/(.*)\\]\\]"), journalArticle.getContent(), journalArticle.getGroupId(), false)){
					if(Validator.isNotNull(matched) && uuidAllDlFileEntries.contains(matched.getUuid())) {
						buildMatchedDlFileEntry(dataMap, journalArticle, portalURL, matched, noMappedDlFileEntries);
					}
				}
				
				for(DLFileEntry matched : allMatchesDlFileEntry(Pattern.compile("\\[CDATA\\[(\\{.*\\\"type\\\":\\\"document\\\".*\\})\\]\\]"), journalArticle.getContent(), journalArticle.getGroupId(), true)){
					if(Validator.isNotNull(matched) && uuidAllDlFileEntries.contains(matched.getUuid())) {
						buildMatchedDlFileEntry(dataMap, journalArticle, portalURL, matched, noMappedDlFileEntries);
					}
				}
			}
		}
		
		if(!noMappedDlFileEntries.isEmpty()) {
			AragonPortalUtilitiesMapHierarchy<DLFileEntry> noReferenceds = dataMap.putIfNotExistsHierachyAndReturn("NoMapped", "");
			for(DLFileEntry dlFileEntry : dlFileEntries) {
				if(noMappedDlFileEntries.contains(dlFileEntry.getUuid())) {
					noReferenceds.putIfNotExistsLinkedData(dlFileEntry);
				}
			}
		}
		return dataMap;
	}
	
	/**
	 * @param dataMap
	 * @param journalArticle
	 * @param matched
	 * @param noMapped
	 */
	private static void buildMatchedDlFileEntry(AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap, 
		JournalArticle journalArticle, String portalURL, DLFileEntry matched, Set<String> noMapped) {
		List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getCategories(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
		Set<AssetCategory> topics = new HashSet<>();
		Set<AssetCategory> organisms = new HashSet<>();
		Boolean hasVisible = false;

		for(AssetCategory assetCategory : assetCategories) {
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(assetCategory.getVocabularyId());
			if(Validator.isNotNull(assetVocabulary)) {
				hasVisible = hasVisible || assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_VISIBILITY_IN_NAVIGATION_ES);
				if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES) ||
						assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_EN)) {
					topics.add(assetCategory);
				} else if (assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES) ||
						assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_EN)) {
					organisms.add(assetCategory);
				}
			}
		}
		buildTopicsDataDlFileEntry(dataMap, topics, journalArticle, portalURL, matched);
		buildOrganismDataDlFileEntry(dataMap, organisms, hasVisible, journalArticle, portalURL, matched);
		
		if(!topics.isEmpty() || (!hasVisible && !organisms.isEmpty())) {
			noMapped.remove(matched.getUuid());
		}
	}
	
	/**
	 * @param dataMap
	 * @param organisms
	 * @param hasVisible
	 * @param journalArticle
	 * @param portalURL
	 * @param matched
	 */
	private static void buildOrganismDataDlFileEntry(AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap,
			Set<AssetCategory> organisms, Boolean hasVisible, JournalArticle journalArticle, String portalURL,
			DLFileEntry matched) {
		if(!hasVisible && !organisms.isEmpty()) {
			AragonPortalUtilitiesMapHierarchy<DLFileEntry> startPoint = dataMap.putIfNotExistsHierachyAndReturn("Inicio", portalURL);
			startPoint = startPoint.putIfNotExistsHierachyAndReturn("Organismos", "organismos");
			
			AragonPortalUtilitiesMapHierarchy<DLFileEntry> nextChild;
			for(AssetCategory assetCategory : organisms) {
				nextChild = startPoint;
				List<AssetCategory> assetCategories = new ArrayList<>();
				AssetCategory parentAssetCategory = assetCategory;
				while(parentAssetCategory != null) {
					assetCategories.add(parentAssetCategory);
					parentAssetCategory = parentAssetCategory.getParentCategory();
				}
				
				Collections.reverse(assetCategories);
				
				for(AssetCategory assetCategory2 : assetCategories) {
					nextChild = nextChild.putIfNotExistsHierachyAndReturn(assetCategory2.getTitle(LocaleUtil.getDefault()), assetCategory2);
				}
				
				nextChild = nextChild.putIfNotExistsHierachyAndReturn(journalArticle.getUrlTitle(), journalArticle);
				
				nextChild.putIfNotExistsLinkedData(matched);		
			}
		}
		
	}

	/**
	 * @param dataMap
	 * @param topics
	 * @param journalArticle
	 * @param portalURL
	 * @param matched
	 */
	private static void buildTopicsDataDlFileEntry(AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap,
			Set<AssetCategory> topics, JournalArticle journalArticle, String portalURL, DLFileEntry matched) {
		if(!topics.isEmpty()) {
			AragonPortalUtilitiesMapHierarchy<DLFileEntry> startPoint = dataMap.putIfNotExistsHierachyAndReturn("Inicio", portalURL);
			startPoint = startPoint.putIfNotExistsHierachyAndReturn("Temas", "temas");
			
			AragonPortalUtilitiesMapHierarchy<DLFileEntry> nextChild;
			for(AssetCategory assetCategory : topics) {
				nextChild = startPoint;
				List<AssetCategory> assetCategories = new ArrayList<>();
				AssetCategory parentAssetCategory = assetCategory;
				while(parentAssetCategory != null) {
					assetCategories.add(parentAssetCategory);
					parentAssetCategory = parentAssetCategory.getParentCategory();
				}
				
				Collections.reverse(assetCategories);
				
				for(AssetCategory assetCategory2 : assetCategories) {
					List<CustomCategoryProperty> customCategoryProperties = 
							CustomCategoryPropertyLocalServiceUtil.findByCategoryId(assetCategory2.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null, false);
					
					String alias = "";
					for(CustomCategoryProperty customCategoryProperty : customCategoryProperties) {
						if(customCategoryProperty.getKey().equalsIgnoreCase("ALIAS")) {
							alias = customCategoryProperty.getText();
						}
					}
					nextChild = nextChild.putIfNotExistsHierachyAndReturn(alias, assetCategory2);
				}

				nextChild = nextChild.putIfNotExistsHierachyAndReturn(journalArticle.getUrlTitle(), journalArticle);
				
				nextChild.putIfNotExistsLinkedData(matched);
			}
		}
		
	}

	/**
	 * @param fatherFolderId
	 * @param groupId
	 * @param set
	 * @throws PortalException
	 */
	private static void getAllDocumentsFolderAndSubFolders(long fatherFolderId, long groupId, Set<DLFileEntry> set) throws PortalException { 

		List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(groupId, fatherFolderId, false);
		List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.getFileEntries(groupId, fatherFolderId, 0, -1,-1, null);
		set.addAll(dlFileEntries);
		
		for(DLFolder dlFolder : dlFolders) {
			getAllDocumentsFolderAndSubFolders(dlFolder.getFolderId(), groupId, set);
		}
	}
	
	/**
	 * @param dataMap
	 * @param journalSet
	 */
	private static void setCategoriesData(AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap, Set<JournalArticle> journalSet, String portalURL) {
		AragonPortalUtilitiesMapHierarchy<JournalArticle> noReferenceds = dataMap.putIfNotExistsHierachyAndReturn("NoMapped", "");

		for(JournalArticle journalArticle : journalSet) {
			List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getCategories(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
			Set<AssetCategory> topics = new HashSet<>();
			Set<AssetCategory> organisms = new HashSet<>();
			Boolean hasVisible = false;

			for(AssetCategory assetCategory : assetCategories) {
				AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(assetCategory.getVocabularyId());
				if(Validator.isNotNull(assetVocabulary)) {
					hasVisible = hasVisible || assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_VISIBILITY_IN_NAVIGATION_ES);
					if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES) ||
							assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_EN)) {
						topics.add(assetCategory);
					} else if (assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES) ||
							assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_EN)) {
						organisms.add(assetCategory);
					}
				}
			}
			buildTopicsData(dataMap, topics, journalArticle, portalURL);
			buildOrganismData(dataMap, organisms, hasVisible, journalArticle, portalURL);
			if(topics.isEmpty() && (organisms.isEmpty() || hasVisible)) {
				noReferenceds.putIfNotExistsLinkedData(journalArticle);
			}
		}
		
		if(dataMap.getHierachy().get("NoMapped").getLinkedData().isEmpty()) {
			dataMap.getHierachy().remove("NoMapped");
		}
	}
	
	/**
	 * @param dataMap
	 * @param categoriesSet
	 * @param journalArticle
	 * @param portalURL
	 */
	private static void buildTopicsData(AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap, Set<AssetCategory> categoriesSet,
			JournalArticle journalArticle, String portalURL) {
		if(!categoriesSet.isEmpty()) {
			AragonPortalUtilitiesMapHierarchy<JournalArticle> startPoint = dataMap.putIfNotExistsHierachyAndReturn("Inicio", portalURL);
			startPoint = startPoint.putIfNotExistsHierachyAndReturn("Temas", "temas");
			
			AragonPortalUtilitiesMapHierarchy<JournalArticle> nextChild;
			for(AssetCategory assetCategory : categoriesSet) {
				nextChild = startPoint;
				List<AssetCategory> assetCategories = new ArrayList<>();
				AssetCategory parentAssetCategory = assetCategory;
				while(parentAssetCategory != null) {
					assetCategories.add(parentAssetCategory);
					parentAssetCategory = parentAssetCategory.getParentCategory();
				}
				
				Collections.reverse(assetCategories);
				
				for(AssetCategory assetCategory2 : assetCategories) {
					List<CustomCategoryProperty> customCategoryProperties = 
							CustomCategoryPropertyLocalServiceUtil.findByCategoryId(assetCategory2.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null, false);
					
					String alias = "";
					for(CustomCategoryProperty customCategoryProperty : customCategoryProperties) {
						if(customCategoryProperty.getKey().equalsIgnoreCase("ALIAS")) {
							alias = customCategoryProperty.getText();
						}
					}
					nextChild = nextChild.putIfNotExistsHierachyAndReturn(alias, assetCategory2);
				}
				nextChild.putIfNotExistsLinkedData(journalArticle);
			}
		}
	}
	
	/**
	 * @param dataMap
	 * @param categoriesSet
	 * @param hasVisible
	 * @param journalArticle
	 * @param portalURL
	 */
	private static void buildOrganismData(AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap, Set<AssetCategory> categoriesSet,
			Boolean hasVisible, JournalArticle journalArticle, String portalURL) {
		if(!hasVisible && !categoriesSet.isEmpty()) {
			AragonPortalUtilitiesMapHierarchy<JournalArticle> startPoint = dataMap.putIfNotExistsHierachyAndReturn("Inicio", portalURL);
			startPoint = startPoint.putIfNotExistsHierachyAndReturn("Organismos", "organismos");
			
			AragonPortalUtilitiesMapHierarchy<JournalArticle> nextChild;
			for(AssetCategory assetCategory : categoriesSet) {
				nextChild = startPoint;
				List<AssetCategory> assetCategories = new ArrayList<>();
				AssetCategory parentAssetCategory = assetCategory;
				while(parentAssetCategory != null) {
					assetCategories.add(parentAssetCategory);
					parentAssetCategory = parentAssetCategory.getParentCategory();
				}
				
				Collections.reverse(assetCategories);
				
				for(AssetCategory assetCategory2 : assetCategories) {
					nextChild = nextChild.putIfNotExistsHierachyAndReturn(assetCategory2.getTitle(LocaleUtil.getDefault()), assetCategory2);
				}
				nextChild.putIfNotExistsLinkedData(journalArticle);
			}
		}
	}
	
	/**
	 * @param folderId
	 * @param currentPath
	 * @return
	 * @throws PortalException
	 */
	public static String getJournalFolderPathName(long folderId, String currentPath) throws PortalException {
		JournalFolder journalFolder = JournalFolderLocalServiceUtil.getFolder(folderId);
		if(journalFolder != null) {
			 currentPath = "/" + journalFolder.getName().concat(currentPath);
			
			if(journalFolder.getParentFolderId() != 0) {
				currentPath = getJournalFolderPathName(journalFolder.getParentFolderId(), currentPath);
			}
		}
		
		return currentPath;
	}
	
	/**
	 * @param folderId
	 * @param currentPath
	 * @return
	 * @throws PortalException
	 */
	public static String getDlFolderPathName(long folderId, String currentPath) throws PortalException {
		DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(folderId);
		if(dlFolder != null) {
			 currentPath = "/" + dlFolder.getName().concat(currentPath);
			
			if(dlFolder.getParentFolderId() != 0) {
				currentPath = getDlFolderPathName(dlFolder.getParentFolderId(), currentPath);
			}
		}
		
		return currentPath;
	}
	
	/**
	 * @param dataMap
	 * @param folderId
	 * @param folderNamePath
	 * @param portalURL
	 * @return
	 * @throws IOException
	 */
	public static String buildHtmlfromDataMapWebContent(AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap, long folderId, String folderNamePath, String portalURL) throws IOException {
		String[] allPaths = AragonPortalUtilitiesContentUtils.getBundleEntries(AragonPortalUtilitiesPortletKeys.TEMPLATES_DIRECTORY_NAME, "plantilla.html", false);
		InputStream inputStream = AragonPortalUtilitiesContentUtils.class.getResourceAsStream(allPaths[0]);
		Scanner scanner = new Scanner(inputStream);
		scanner.useDelimiter("\\A");
		String content = scanner.hasNext() ? scanner.next() : "";
		
		scanner.close();
		inputStream.close();
		
		content = content.replace(REPLACE_FOLDER_TITLE, folderNamePath);
		
		content = content.replace(REPLACE_TITLE, "Mapa web contenidos - " + folderNamePath);
		
		content = content.replace(REPLACE_LIST, buildListDataWebContent(dataMap, portalURL, ""));
		
		return content;
	}
	
	/**
	 * @param dataMap
	 * @param folderId
	 * @param folderNamePath
	 * @param portalURL
	 * @return
	 * @throws IOException
	 */
	public static String buildHtmlfromDataMapDlFileEntry(AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap, long folderId, String folderNamePath, String portalURL) throws IOException {
		String[] allPaths = AragonPortalUtilitiesContentUtils.getBundleEntries(AragonPortalUtilitiesPortletKeys.TEMPLATES_DIRECTORY_NAME, "plantilla.html", false);
		InputStream inputStream = AragonPortalUtilitiesContentUtils.class.getResourceAsStream(allPaths[0]);
		Scanner scanner = new Scanner(inputStream);
		scanner.useDelimiter("\\A");
		String content = scanner.hasNext() ? scanner.next() : "";
		
		scanner.close();
		inputStream.close();
		
		content = content.replace(REPLACE_FOLDER_TITLE, folderNamePath);
		
		content = content.replace(REPLACE_TITLE, "Mapa web contenidos - " + folderNamePath);
		
		content = content.replace(REPLACE_LIST, buildListDataDlFileEntry(dataMap, portalURL, ""));
		
		return content;
	}
	
	/**
	 * @param dataMap
	 * @param portalURL
	 * @param path
	 * @return
	 */
	private static String buildListDataWebContent(AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap, String portalURL, String path) {
		String html = "";
		for(Entry<String, AragonPortalUtilitiesMapHierarchy<JournalArticle>> entry : dataMap.getHierachy().entrySet()) {
			long idCounter = CounterLocalServiceUtil.increment(AragonPortalUtilitiesContentUtils.class.getName());
			if(entry.getKey().equals(HOME_NAME)) {
				html = html.concat(buildHomeWebContent(portalURL, entry, idCounter));
			} else if(entry.getKey().equals(NO_MAPPED_CATEGORY)) {
				html = html.concat(buildNoMappedJournals(portalURL, entry, idCounter));
			} else if(entry.getKey().equals(TOPICS_NAME)) {
				html = html.concat(buildTopicsWebContent(portalURL, path, entry, idCounter));
			} else if(entry.getKey().equals(ORGANISMS_NAME)) {
				html = html.concat(buildOrganismsWebContent(portalURL, path, entry, idCounter));
			} else {
				html = html.concat(buildOtherWebContent(portalURL, path, entry, idCounter));
			}
		}
		return html;
	}
	
	/**
	 * @param dataMap
	 * @param portalURL
	 * @param path
	 * @return
	 */
	private static String buildListDataDlFileEntry(AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap, String portalURL, String path) {
		String html = "";
		for(Entry<String, AragonPortalUtilitiesMapHierarchy<DLFileEntry>> entry : dataMap.getHierachy().entrySet()) {
			long idCounter = CounterLocalServiceUtil.increment(AragonPortalUtilitiesContentUtils.class.getName());
			if(entry.getKey().equals(HOME_NAME)) {
				html = html.concat(buildHomeDlFileEntry(portalURL, entry, idCounter));
			} else if(entry.getKey().equals(NO_MAPPED_CATEGORY)) {
				html = html.concat(buildNoMappedDlFileEntry(portalURL, entry, idCounter));
			} else if(entry.getKey().equals(TOPICS_NAME)) {
				html = html.concat(buildTopicsDlFileEntry(portalURL, path, entry, idCounter));
			} else if(entry.getKey().equals(ORGANISMS_NAME)) {
				html = html.concat(buildOrganismsDlFileEntry(portalURL, path, entry, idCounter));
			} else {
				html = html.concat(buildOtherDlFileEntry(portalURL, path, entry, idCounter));
			}
		}
		return html;
	}

	/**
	 * @param portalURL
	 * @param path
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildOtherWebContent(String portalURL, String path,
			Entry<String, AragonPortalUtilitiesMapHierarchy<JournalArticle>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;

		if(entry.getValue().getNode() instanceof AssetCategory) {
			html = FULL_TEMPLATE;
			html = html.replace(REPLACE_TITLE_URL, path + "/" + FriendlyURLNormalizerUtil.normalize(entry.getKey()));
			html = html.replace(REPLACE_TITLE, "<b>" + ((AssetCategory) entry.getValue().getNode()).getTitle(LocaleUtil.getDefault()) + "</b>");
			html = html.replace(REPLACE_ID, Long.toString(idCounter));
			html = html.concat(buildListDataWebContent(entry.getValue(), portalURL, path + "/" + FriendlyURLNormalizerUtil.normalize(entry.getKey())));
			
			String finalHtml = "";
			for(Entry<String, JournalArticle> journalEntry : entry.getValue().getLinkedData().entrySet()) {
				finalHtml = END_TEMPLATE;
				finalHtml = finalHtml.replace(REPLACE_TITLE, journalEntry.getValue().getTitle(LocaleUtil.getDefault()));
				finalHtml = finalHtml.replace(REPLACE_TITLE_URL, portalURL + "/-/" + journalEntry.getValue().getUrlTitle());
				html = html.concat(finalHtml);
			}
			html = html.concat(FULL_TEMPLATE_END);
		}
		return html;
	}
	
	/**
	 * @param portalURL
	 * @param path
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildOtherDlFileEntry(String portalURL, String path,
			Entry<String, AragonPortalUtilitiesMapHierarchy<DLFileEntry>> entry, long idCounter) {
		String html = "";
		if(entry.getValue().getNode() instanceof AssetCategory) {
			html = FULL_TEMPLATE;
			html = html.replace(REPLACE_TITLE_URL, path + "/" + FriendlyURLNormalizerUtil.normalize(entry.getKey()));
			html = html.replace(REPLACE_TITLE, "<b>" + ((AssetCategory) entry.getValue().getNode()).getTitle(LocaleUtil.getDefault()) + "</b>");
			html = html.replace(REPLACE_ID, Long.toString(idCounter));
			html = html.concat(buildListDataDlFileEntry(entry.getValue(), portalURL, path + "/" + FriendlyURLNormalizerUtil.normalize(entry.getKey())));
			html = html.concat(FULL_TEMPLATE_END);
			
		} else if(entry.getValue().getNode() instanceof JournalArticle && !entry.getValue().getLinkedData().isEmpty())	{
			html = FULL_TEMPLATE;
			html = html.replace(REPLACE_TITLE_URL, portalURL  + "/-/" + ((JournalArticle) entry.getValue().getNode()).getUrlTitle());
			html = html.replace(REPLACE_TITLE, "<i>" + ((JournalArticle) entry.getValue().getNode()).getTitle(LocaleUtil.getDefault()) + "</i>");
			html = html.replace(REPLACE_ID, Long.toString(idCounter));
			
			String finalHtml = "";
			for(Entry<String, DLFileEntry> dlFileEntryEntry : entry.getValue().getLinkedData().entrySet()) {
				finalHtml = END_TEMPLATE;
				finalHtml = finalHtml.replace(REPLACE_TITLE, dlFileEntryEntry.getValue().getFileName());
				finalHtml = finalHtml.replace(REPLACE_TITLE_URL, buildDlFileEntryUrl(portalURL, dlFileEntryEntry.getValue(), "/"));
				html = html.concat(finalHtml);
			}
			html = html.concat(FULL_TEMPLATE_END);
			
		}
		return html;
	}
	
	/**
	 * @param portalURL
	 * @param path
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildOrganismsWebContent(String portalURL, String path,
			Entry<String, AragonPortalUtilitiesMapHierarchy<JournalArticle>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, path + "/" + entry.getValue().getNode().toString());
		html = html.replace(REPLACE_TITLE, "<b>Organismos</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.concat(buildListDataWebContent(entry.getValue(), portalURL, path + "/" + entry.getValue().getNode().toString()));
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}
	
	/**
	 * @param portalURL
	 * @param path
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildOrganismsDlFileEntry(String portalURL, String path,
			Entry<String, AragonPortalUtilitiesMapHierarchy<DLFileEntry>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, path + "/" + entry.getValue().getNode().toString());
		html = html.replace(REPLACE_TITLE, "<b>Organismos</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.concat(buildListDataDlFileEntry(entry.getValue(), portalURL, path + "/" + entry.getValue().getNode().toString()));
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}

	/**
	 * @param portalURL
	 * @param path
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildTopicsWebContent(String portalURL, String path,
			Entry<String, AragonPortalUtilitiesMapHierarchy<JournalArticle>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, path + "/" + entry.getValue().getNode().toString());
		html = html.replace(REPLACE_TITLE, "<b>Temas</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.concat(buildListDataWebContent(entry.getValue(), portalURL, path + "/" + entry.getValue().getNode().toString()));
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}
	
	/**
	 * @param portalURL
	 * @param path
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildTopicsDlFileEntry(String portalURL, String path,
			Entry<String, AragonPortalUtilitiesMapHierarchy<DLFileEntry>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, path + "/" + entry.getValue().getNode().toString());
		html = html.replace(REPLACE_TITLE, "<b>Temas</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.concat(buildListDataDlFileEntry(entry.getValue(), portalURL, path + "/" + entry.getValue().getNode().toString()));
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}

	/**
	 * @param portalURL
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildHomeWebContent(String portalURL, Entry<String, AragonPortalUtilitiesMapHierarchy<JournalArticle>> entry,
			long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, entry.getValue().getNode().toString());
		html = html.replace(REPLACE_TITLE, "<b>Inicio</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.concat(buildListDataWebContent(entry.getValue(), portalURL, portalURL));
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}
	
	/**
	 * @param portalURL
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildHomeDlFileEntry(String portalURL, Entry<String, AragonPortalUtilitiesMapHierarchy<DLFileEntry>> entry,
			long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, entry.getValue().getNode().toString());
		html = html.replace(REPLACE_TITLE, "<b>Inicio</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.concat(buildListDataDlFileEntry(entry.getValue(), portalURL, portalURL));
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}

	/**
	 * @param portalURL
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildNoMappedJournals(String portalURL,
			Entry<String, AragonPortalUtilitiesMapHierarchy<JournalArticle>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, "#");
		html = html.replace(REPLACE_TITLE, "<b>No referenciados</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.replace("target=\"_blank\"", "");
		String finalHtml = "";
		for(Entry<String, JournalArticle> journalEntry : entry.getValue().getLinkedData().entrySet()) {
			finalHtml = END_TEMPLATE;
			finalHtml = finalHtml.replace(REPLACE_TITLE, journalEntry.getValue().getTitle(LocaleUtil.getDefault()));
			finalHtml = finalHtml.replace(REPLACE_TITLE_URL, portalURL + "/-/" + journalEntry.getValue().getUrlTitle());
			html = html.concat(finalHtml);
		}
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}
	
	/**
	 * @param portalURL
	 * @param entry
	 * @param idCounter
	 * @return
	 */
	private static String buildNoMappedDlFileEntry(String portalURL,
			Entry<String, AragonPortalUtilitiesMapHierarchy<DLFileEntry>> entry, long idCounter) {
		String html;
		html = FULL_TEMPLATE;
		html = html.replace(REPLACE_TITLE_URL, "#");
		html = html.replace(REPLACE_TITLE, "<b>No referenciados</b>");
		html = html.replace(REPLACE_ID, Long.toString(idCounter));
		html = html.replace("target=\"_blank\"", "");
		String finalHtml = "";
		for(Entry<String, DLFileEntry> dlFileEntryEntry : entry.getValue().getLinkedData().entrySet()) {
			finalHtml = END_TEMPLATE;
			finalHtml = finalHtml.replace(REPLACE_TITLE, dlFileEntryEntry.getValue().getFileName());
			finalHtml = finalHtml.replace(REPLACE_TITLE_URL, buildDlFileEntryUrl(portalURL, dlFileEntryEntry.getValue(), "/"));
			html = html.concat(finalHtml);
		}
		html = html.concat(FULL_TEMPLATE_END);
		return html;
	}
	
	
	/**
	 * @param themeDisplay
	 * @param dlFileEntry
	 * @return
	 */
	private static String buildDlFileEntryUrl(String portalURL, DLFileEntry dlFileEntry, String spliter) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(portalURL).append(spliter);
	
		stringBuilder.append("documents").append(spliter);
		stringBuilder.append(dlFileEntry.getGroupId()).append(spliter);
		stringBuilder.append(dlFileEntry.getFolderId()).append(spliter);
		stringBuilder.append(dlFileEntry.getFileEntryId()).append(spliter);
		stringBuilder.append(dlFileEntry.getUuid());
		
		return stringBuilder.toString();
	}
	
	/*------------------*/

	public static String getJournalFolderJson(long groupId) {
		JSONArray rootJSONArray = JSONFactoryUtil.createJSONArray();
		JSONObject rootJSONObject = JSONFactoryUtil.createJSONObject();
		
		rootJSONObject.put("name", "Todas las carpetas");
		rootJSONObject.put("id", 0L);
		
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		List<JournalFolder> journalFolders = JournalFolderLocalServiceUtil.getFolders(groupId, 0L);
		
		for(JournalFolder journalFolder : journalFolders) {
			if(!journalFolder.isInTrash() ) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				
				jsonObject.put("name", journalFolder.getName());
				jsonObject.put("id", journalFolder.getFolderId());
				jsonObject.put("nested", getJournalFolderJson(groupId, journalFolder.getFolderId()));
				
				jsonArray.put(jsonObject);	
			}	
		}
		
		rootJSONObject.put("nested", jsonArray);
		rootJSONArray.put(rootJSONObject);

		
		return rootJSONArray.toJSONString();
	}
	
	private static JSONArray getJournalFolderJson(long groupId, long parentFolderId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		List<JournalFolder> journalFolders = JournalFolderLocalServiceUtil.getFolders(groupId, parentFolderId);

		for(JournalFolder journalFolder : journalFolders) {
			if(!journalFolder.isInTrash() ) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				
				jsonObject.put("name", journalFolder.getName());
				jsonObject.put("id", journalFolder.getFolderId());
				jsonObject.put("nested", getJournalFolderJson(groupId, journalFolder.getFolderId()));
				
				jsonArray.put(jsonObject);
			}
		}
		
		return jsonArray;
	}
		
	static List<Long> getValidVocabularies() {
		List<Long> validVocabularies = new ArrayList<>();
		List<AssetVocabulary> allVocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		
		for(AssetVocabulary assetVocabulary : allVocabularies) {
			if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_EN) ||
					assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES) ||
					assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_EN) ||
					assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES)) {
				
				validVocabularies.add(assetVocabulary.getVocabularyId());
			}
		}
		
		return validVocabularies;
	}
	
	private static Set<JournalArticle> getAllArticlesFolderAndSubFolders(long fatherFolderId, long groupId) throws PortalException { 
		Set<JournalArticle> returnValue = new TreeSet<>();
		
		getAllArticlesFolderAndSubFolders(fatherFolderId, groupId, returnValue);
		
		return returnValue;
	}
	
	private static void getAllArticlesFolderAndSubFolders(long parentFolderId, long groupId, Set<JournalArticle> set) throws PortalException {
		
		List<JournalArticle> allArticles = JournalArticleLocalServiceUtil.getArticles(groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED, -1,-1);
		Set<String> auxSet = new HashSet<>();
		
		for(JournalArticle journalArticle : allArticles) {
			JournalArticle lastApro = 
					JournalArticleLocalServiceUtil.getLatestArticle(
							journalArticle.getGroupId(), 
							journalArticle.getArticleId(), 
							WorkflowConstants.STATUS_APPROVED);
			
			// Only if strucuture is "Contenido final"
			if(lastApro.getDDMStructure().getName(LocaleUtil.getDefault()).equalsIgnoreCase(AragonUtilitiesConstant.STRUCTURE_NAME_CONTENIDO_FINAL)) {
				auxSet.add(lastApro.getUrlTitle());
			}
		}
		
		for(String urlNoDup : auxSet) {
			JournalArticle article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(groupId, urlNoDup);
			if(article != null) {
				set.add(article);
			}
		}

		List<JournalFolder> journalFolders = JournalFolderLocalServiceUtil.getFolders(groupId, parentFolderId);
		for(JournalFolder journalFolder : journalFolders) {
			getAllArticlesFolderAndSubFolders(journalFolder.getFolderId(), groupId, set);
		}
	}
	
	public static Iterable<DLFileEntry> allMatchesDlFileEntry(final Pattern p, final CharSequence input, long groupId, boolean expectedJSON) {
		return new Iterable<DLFileEntry>() {
			public Iterator<DLFileEntry> iterator() {
				return new Iterator<DLFileEntry>() {
					// Use a matcher internally.
					final Matcher matcher = p.matcher(input);
					// Keep a match around that supports any interleaving of hasNext/next calls.
					MatchResult pending;

					public boolean hasNext() {
						// Lazily fill pending, and avoid calling find() multiple times if the
						// clients call hasNext() repeatedly before sampling via next().
						if (pending == null && matcher.find()) {
							pending = matcher.toMatchResult();
						}
						return pending != null;
					}

					public DLFileEntry next() {
						// Fill pending if necessary (as when clients call next() without
						// checking hasNext()), throw if not possible.
						if (!hasNext()) {
							throw new NoSuchElementException();
						}
						// Consume pending so next call to hasNext() does a find().
						MatchResult next = pending;
						pending = null;
						
						// Clear all end tags matched in regex
						if(!expectedJSON) {
							return DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(next.group(1).replaceAll("\\<\\/.+\\>", ""), groupId);
						} else {
							try {
								JSONObject jsonObject = JSONFactoryUtil.createJSONObject(next.group(1));
								return DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(jsonObject.getString("uuid"), groupId);
							} catch (JSONException e) {
								return null;
							}
						}
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
	public static Iterable<JournalArticle> allMatches(final Pattern p, final CharSequence input, long groupId) {
		return new Iterable<JournalArticle>() {
			public Iterator<JournalArticle> iterator() {
				return new Iterator<JournalArticle>() {
					// Use a matcher internally.
					final Matcher matcher = p.matcher(input);
					// Keep a match around that supports any interleaving of hasNext/next calls.
					MatchResult pending;

					public boolean hasNext() {
						// Lazily fill pending, and avoid calling find() multiple times if the
						// clients call hasNext() repeatedly before sampling via next().
						if (pending == null && matcher.find()) {
							pending = matcher.toMatchResult();
						}
						return pending != null;
					}

					public JournalArticle next() {
						// Fill pending if necessary (as when clients call next() without
						// checking hasNext()), throw if not possible.
						if (!hasNext()) {
							throw new NoSuchElementException();
						}
						// Consume pending so next call to hasNext() does a find().
						MatchResult next = pending;
						pending = null;
						
						// Clear all end tags matched in regex

						return JournalArticleLocalServiceUtil.fetchArticleByUrlTitle(groupId, next.group(1).replaceAll("\\<\\/.+\\>", "").split("[#|\\?]")[0]);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
    public static String[] getBundleEntries (String path, String filePattern, boolean recurse) {
        Bundle bundle = FrameworkUtil.getBundle(AragonPortalUtilitiesContentUtils.class);
        Enumeration<URL> entriesEnum = bundle.findEntries(path, filePattern, recurse);
        String [] paths = new String[Collections.list(entriesEnum).size()];
        entriesEnum = bundle.findEntries(path, filePattern, recurse);
        int i = 0;
        if (entriesEnum != null) {
            while (entriesEnum.hasMoreElements()) {
            	 paths[i] = entriesEnum.nextElement().getPath();
            	 i++;
            }
        }
        return paths;
    }
    
	private static final String NO_MAPPED_CATEGORY = "NoMapped";
	private static final String HOME_NAME = "Inicio";
	private static final String TOPICS_NAME = "Temas";
	private static final String ORGANISMS_NAME = "Organismos";
    private static final String FULL_TEMPLATE = "<li class=\"col-12 listado__item\"><a class=\"link\" href=\"${title_url}\" target=\"_blank\"><span class=\"title\">${title}</span></a><div id=\"collapse_${id}\" class=\"collapse show\"><ul class=\"listado\">";	
    private static final String FULL_TEMPLATE_END = "</ul></div></li>";
	private static final String END_TEMPLATE = "<li class=\"col-12 listado__item\"><a class=\"link\" href=\"${title_url}\" target=\"_blank\"><span class=\"title\">${title}</span></a></li>";
	
	private static final String REPLACE_TITLE = "${title}";
	private static final String REPLACE_TITLE_URL = "${title_url}";
	private static final String REPLACE_LIST = "${list}";
	private static final String REPLACE_ID = "${id}";
	private static final String REPLACE_FOLDER_TITLE = "${folder_title}";
}
