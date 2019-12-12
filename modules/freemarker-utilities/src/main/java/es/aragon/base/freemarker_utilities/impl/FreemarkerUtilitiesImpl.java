package es.aragon.base.freemarker_utilities.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.ExistsFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;
import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;

@Component(
	immediate = true, 
	service = FreemarkerUtilities.class
)
public class FreemarkerUtilitiesImpl implements FreemarkerUtilities {
	
	@Override
	public String removeUrlParameters(String url) {
		if (url != null && !url.isEmpty()) {
			if (url.contains(StringPool.QUESTION)) {
				url = url.substring(0, url.indexOf(StringPool.QUESTION));
			}
		}
		return url;
	}
	
	@Override
	public String getArticleFullURL(long resourcePrimaryKey, Locale locale){
		String result = StringPool.BLANK;
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(resourcePrimaryKey);
		if (Validator.isNotNull(journalArticle)) {
			result = getArticleFullURL(journalArticle, locale);
		}
		return result;
	}
	
	@Override
	public String getArticleFullURL(AssetEntry assetEntry, Locale locale) {
		String result = StringPool.BLANK;
		long classPK = assetEntry.getClassPK();
		JournalArticle JournalArticle = _journalArticleLocalService.fetchLatestArticle(classPK);
		if (Validator.isNotNull(JournalArticle)){
			result = getArticleFullURL(JournalArticle, locale);
		}
		return result;
	}
	
	@Override
	public String getArticleFullURL(JournalArticle journalArticle, Locale locale){
		String result = StringPool.BLANK;
		if (Validator.isNotNull(journalArticle)) {
			try {
				result = "/-/" + journalArticle.getUrlTitle(locale);
			} catch (Exception e) {
				_log.error("There was an error getting the journal article (articleId" + journalArticle.getArticleId() + ") url title: " + e.toString());
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public String getLayoutFullURL(long layoutId, long groupId, Locale locale){
		String result = StringPool.BLANK;
		Layout layout = _layoutLocalService.fetchLayout(groupId, false, layoutId);
		if (Validator.isNotNull(layout)) {
			result = getLayoutFullURL(layout, groupId, locale);
		}
		return result;
	}
	
	@Override
	public String getLayoutFullURL(Layout layout, long groupId, Locale locale){
		String groupFriendlyURL = StringPool.BLANK;
		Group group = _groupLocalService.fetchGroup(groupId);
		if (group != null) {
			if (layout.isPrivateLayout()) {
				groupFriendlyURL = "/group" + group.getFriendlyURL();
			}
		}
		String result = groupFriendlyURL + layout.getFriendlyURL(locale);
		return result;
	}

	@Override
	public String getArticleImage(long groupId, String articleId, Locale locale) {
		String imgSrc = StringPool.BLANK;
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(groupId, articleId, WorkflowConstants.STATUS_APPROVED);
		if (Validator.isNotNull(journalArticle)){
			long fileEntryId = 0;
			//Si tiene campo imagen se recoge su url
			String imagenFileEntryIdStr = getArticleImageMetadata(journalArticle, "imagen", "fileEntryId", locale);
			if (Validator.isNotNull(imagenFileEntryIdStr) && !imagenFileEntryIdStr.isEmpty()) {
				fileEntryId = Long.valueOf(imagenFileEntryIdStr);
			}
			//Si tiene campo imagen se recoge su url
			String imagenPrincipalFileEntryIdStr = getArticleImageMetadata(journalArticle, "imagen_principal", "fileEntryId", locale);
			if (Validator.isNotNull(imagenPrincipalFileEntryIdStr) && !imagenPrincipalFileEntryIdStr.isEmpty()) {
				fileEntryId = Long.valueOf(imagenPrincipalFileEntryIdStr);
			}
			//Se obtiene el fileEntry
			if (fileEntryId > 0) {
				DLFileEntry dlFileEntry = _dlFileEntryLocalService.fetchDLFileEntry(fileEntryId);
				if (Validator.isNotNull(dlFileEntry)) {
					imgSrc = "/documents/" + journalArticle.getGroupId() + "/" + dlFileEntry.getFolderId() + "/" + dlFileEntry.getName() + "/" + dlFileEntry.getUuid() + "?imageThumbnail=1";
				}
			}
		}
		return imgSrc;
	}
	
	@Override
	public String getArticleImage(long groupId, AssetEntry assetEntry, Locale locale) {
		String imgSrc = StringPool.BLANK;
		long classPK = assetEntry.getClassPK();
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(classPK);
		if(Validator.isNotNull(journalArticle)){
			imgSrc = getArticleImage(groupId, journalArticle.getArticleId(), locale);
		}
		return imgSrc;
	}
	
	@Override
	public String getArticleImageAlt(long groupId, AssetEntry assetEntry, Locale locale) {
		String imageAlt = StringPool.BLANK;
		long classPK = assetEntry.getClassPK();
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(classPK);
		if(Validator.isNotNull(journalArticle)){
			imageAlt = getArticleImageAlt(groupId, journalArticle.getArticleId(), locale);
		}
		return imageAlt;
	}
	
	@Override
	public String getArticleImageAlt(long groupId, String articleId, Locale locale) {
		String imageAlt = StringPool.BLANK;
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(groupId, articleId, WorkflowConstants.STATUS_APPROVED);
		if (Validator.isNotNull(journalArticle)){
			imageAlt = getArticleImageMetadata(journalArticle, "imagen", "alt", locale);
		}
		return imageAlt;
	}
	
	public String getArticleImageMetadata(JournalArticle article, String fieldName, String metadataName, Locale locale) {
		String imageDetadata = StringPool.BLANK;
		try {
			String content = article.getContentByLocale(LocaleUtil.toLanguageId(locale));
			Document document = null;
			try {
				document = SAXReaderUtil.read(new StringReader(content));
			} catch(Exception de) {
				de.printStackTrace();
			}
			if (Validator.isNotNull(document)) {
				Node fieldContent = document.selectSingleNode("//*/dynamic-element[@name='" + fieldName + "']/dynamic-content");
				if(fieldContent != null) {
					Pattern p = Pattern.compile("\"([^\"]+)\":\"([^\"]+)");
					Matcher m = p.matcher(fieldContent.getText());
					while (m.find()) {
						if(metadataName.equals(m.group(1))){
							return m.group(2);
						}
					}
				}
			}
		} catch(Exception se) {
			se.printStackTrace();
		}
		return imageDetadata;
	}
	
	public String getArticleFieldValue(JournalArticle article, String fieldName, Locale locale) {
		
		String fieldValue = StringPool.BLANK;
		try {
			String content = article.getContentByLocale(LocaleUtil.toLanguageId(locale));
			Document document = null;
			try {
				document = SAXReaderUtil.read(new StringReader(content));
			} catch(Exception de) {
				de.printStackTrace();
			}
			if (Validator.isNotNull(document)) {
				Node fieldContent = document.selectSingleNode("//*/dynamic-element[@name='" + fieldName + "']/dynamic-content");
				fieldValue = fieldContent.getText();
			}
		} catch(Exception se) {
			se.printStackTrace();
		}
		return fieldValue;
	}

	@Override
	public List<String> getArticleCategoriesList(AssetEntry assetEntry, Locale locale) {
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(assetEntry.getClassPK());
		return getArticleCategoriesList(journalArticle, locale);
	}
	
	@Override
	public List<String> getArticleCategoriesList(long groupId, String journalArticleId, Locale locale) {
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(groupId, journalArticleId, WorkflowConstants.STATUS_APPROVED);
		return getArticleCategoriesList(journalArticle, locale);
	}
	
	@Override
	public List<String> getArticleCategoriesList(JournalArticle journalArticle, Locale locale) {
		List<String> result = new ArrayList<String>();
		if (Validator.isNotNull(journalArticle)){
			List<AssetCategory> assetCategoryList = _assetCategoryLocalService.getCategories(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
			if (assetCategoryList != null && !assetCategoryList.isEmpty()) {
				// Solo mostraremos las categorias de los vocabularios Temas y Organismos
				AssetVocabulary vocabularyTemas = _assetVocabularyLocalService.fetchGroupVocabulary(journalArticle.getGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
				AssetVocabulary vocabularyOrganismos = _assetVocabularyLocalService.fetchGroupVocabulary(journalArticle.getGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
				for (AssetCategory assetCategory : assetCategoryList) {
					if (Validator.isNotNull(vocabularyTemas) && assetCategory.getVocabularyId() == vocabularyTemas.getVocabularyId()) {
						result.add(assetCategory.getTitle(locale));
					} else if (Validator.isNotNull(vocabularyOrganismos) && assetCategory.getVocabularyId() == vocabularyOrganismos.getVocabularyId()) {
						result.add(assetCategory.getTitle(locale));
					}
					
				}
			}
		}
		return result;
	}
	
	@Override
	public List<AssetCategory> getArticleCategories(JournalArticle journalArticle, Locale locale) {
		List<AssetCategory> result = new ArrayList<AssetCategory>();
		if (Validator.isNotNull(journalArticle)) {
			List<AssetCategory> assetCategoryList = _assetCategoryLocalService.getCategories(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
			if (assetCategoryList != null && !assetCategoryList.isEmpty()) {
				// Solo mostraremos las categorias de los vocabularios Temas y Organismos
				AssetVocabulary vocabularyTemas = _assetVocabularyLocalService.fetchGroupVocabulary(journalArticle.getGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
				AssetVocabulary vocabularyOrganismos = _assetVocabularyLocalService.fetchGroupVocabulary(journalArticle.getGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
				for (AssetCategory assetCategory : assetCategoryList) {
					if (Validator.isNotNull(vocabularyTemas) && assetCategory.getVocabularyId() == vocabularyTemas.getVocabularyId()) {
						result.add(assetCategory);
					} else if (Validator.isNotNull(vocabularyOrganismos) && assetCategory.getVocabularyId() == vocabularyOrganismos.getVocabularyId()) {
						result.add(assetCategory);
					}
					
				}
			}
		}
		return result;
	}
	
	@Override
	public List<AssetCategory> filterVocabularyCategoriesFromArticleCategories(long groupId, String vocabularyName, List<AssetCategory> articleCategories) {
		AssetVocabulary vocabulary = _assetVocabularyLocalService.fetchGroupVocabulary(groupId, vocabularyName);
		List<AssetCategory> vocabularyCategories = new ArrayList<AssetCategory>();
		if (Validator.isNotNull(articleCategories)) {
			for (AssetCategory assetCategory : articleCategories) {
				if (assetCategory.getVocabularyId() == vocabulary.getVocabularyId()) {
					vocabularyCategories.add(assetCategory);
				}
			}
		}
		return vocabularyCategories;
	}
	
	@Override
	public String getDisplayDate(AssetEntry assetEntry, String format) {
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(assetEntry.getClassPK());
		return getDisplayDate(journalArticle, format);
	}
	
	@Override
	public List<JSONObject> getH2Elements(String[] sourceElements) {
		List<JSONObject> result = new ArrayList<JSONObject>();
		if (Validator.isNotNull(sourceElements)) {
			int i = 1;
			for (String sourceElement : sourceElements) {
				if (Validator.isNotNull(sourceElement)) {
					org.jsoup.nodes.Document sourceElementDocument = Jsoup.parse(sourceElement);
					Elements h2Elements = sourceElementDocument.getElementsByTag("h2");
					if (h2Elements != null && !h2Elements.isEmpty()) {
						for (Element h2Element : h2Elements) {
							try{
								if (!h2Element.text().isEmpty()){
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("title", h2Element.html());
									jsonObject.put("href", "#anchor" + i);
									result.add(jsonObject);
									i++;
								}
							}catch (NullPointerException e) {
			    				_log.error("There was an error checking empty h2 elements: " + e.toString());
							}
						}
					}
				}
			}
		}
		return result;
		
	}
	
	@Override
	public List<String> addAnchorIds(String[] sourceElements) {
		List<String> result = new ArrayList<String>();
		if (Validator.isNotNull(sourceElements)) {
			int i = 1;
			for (String sourceElement : sourceElements) {
				if (Validator.isNotNull(sourceElement)) {
					org.jsoup.nodes.Document sourceElementDocument = Jsoup.parse(sourceElement, StringPool.BLANK, Parser.xmlParser());
					Elements h2Elements = sourceElementDocument.getElementsByTag("h2");
					if (h2Elements != null && !h2Elements.isEmpty()) {
						for (Element h2Element : h2Elements) {
							h2Element.attr("id", "anchor" + i);
							i++;
						}
					}
					result.add(sourceElementDocument.html());
				}
			}
		}
		return result;
	}
	
	@Override
	public String getDisplayDate(JournalArticle journalArticle, String format) {
		String dateString = StringPool.BLANK;
		DateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		if (Validator.isNotNull(journalArticle)) {
			dateString = dateFormat.format(journalArticle.getDisplayDate());
		}
		return dateString;
	}
	
	@Override
	public List<String[]> getLastProcedures(HttpServletRequest request, long groupId) {
		//Este metodo nos devuelve los ultimos tramites de enlinea
		List<String[]> procedures = new ArrayList<>();
		try {
			String portletId = "es_aragon_enlinea_procedure_web_portlet_EnlineaProcedurePortlet";
			long plid = PortalUtil.getPlidFromPortletId(groupId, portletId);
			SearchContext searchContext = SearchContextFactory.getInstance(request);
			searchContext.setStart(0);
			searchContext.setEnd(5);
			searchContext.setSorts(new Sort("toDate_sortable", Sort.LONG_TYPE, false));
			searchContext.setScoresThreshold(20);
			BooleanQuery booleanQuery = new BooleanQueryImpl();
			BooleanFilter filter = new BooleanFilter();
			filter.addRequiredTerm("entryClassName", "es.aragon.enlinea.db.connection.dao.Procedure");
			Calendar calendar = Calendar.getInstance();
			Date dateFrom = calendar.getTime();
			calendar.add(Calendar.DATE, 30);
			Date dateTo = calendar.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			RangeTermFilter fromDateFilter = new RangeTermFilter("toDate", true, true, df.format(dateFrom), df.format(dateTo));
			ExistsFilter fromDateExists = new ExistsFilter("toDate");
			filter.add(fromDateFilter, BooleanClauseOccur.MUST);
			filter.add(fromDateExists, BooleanClauseOccur.MUST);
			booleanQuery.setPreBooleanFilter(filter);
			try {
				Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
				if(Validator.isNotNull(hits) && hits.getLength() > 0) {
					for(com.liferay.portal.kernel.search.Document doc : hits.getDocs()) {
						String name = GetterUtil.getString(doc.get("name"), StringPool.BLANK);
						String friendlyURL = GetterUtil.getString(doc.get("friendlyURL"), StringPool.BLANK);
						if(!name.isEmpty() && !friendlyURL.isEmpty()) {
							try {
								PortletURL renderUrl =  PortletURLFactoryUtil.create(request, portletId, plid, PortletRequest.RENDER_PHASE);
								renderUrl.setWindowState(LiferayWindowState.MAXIMIZED);
								renderUrl.setPortletMode(LiferayPortletMode.VIEW);
								renderUrl.setParameter("friendlyURL", friendlyURL);
								
								String[] item = new String[2];
								item[0] = renderUrl.toString();
								item[1] = name;
								procedures.add(item);
							} catch (WindowStateException e) {
								// Unable to set window state
							} catch (PortletModeException e) {
								// Unable to set portlet mode
							}
						}
					}
				}
			} catch (SearchException e) {
				_log.error("Error searching last procedures in elastic", e);
			}
		} catch (PortalException e) {
			_log.error("Error getting default plid of portlet es_aragon_enlinea_procedure_web_portlet_EnlineaProcedurePortlet");
		}
		
		return procedures;
	}
	
	@Override
	public FileEntry getFileEntryByURL(String url) {
		FileEntry result = null;
		String[] parts= url.split("/");
		String uuid= parts[5];
		int posicionInterrogante = uuid.indexOf(StringPool.QUESTION);
		if (uuid.contains(StringPool.QUESTION)) {
			uuid = uuid.substring(0, posicionInterrogante);
		}
		long groupId = Long.parseLong(parts[2]);
		try {
			result = DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getFileTypeAndSizeString(FileEntry fileEntry) {
		String result = StringPool.BLANK;
		String udsMedida = StringPool.BLANK;
		if (fileEntry != null) {
			double fileSize = fileEntry.getSize() / 1024 ;
			udsMedida = "KB";
			if( fileSize > 1024) {
				fileSize = fileSize / 1024;
				fileSize = Math.round(fileSize * 100.0) / 100.0;
				udsMedida = "MB";
			}
			result = StringPool.OPEN_PARENTHESIS + fileEntry.getExtension().toUpperCase() + StringPool.COMMA + StringPool.SPACE + fileSize + udsMedida + StringPool.CLOSE_PARENTHESIS;
		}
		return result;
	}
	
	@Override
	public String getFileEntryMimeTypeByURL(String url) {
		String result = StringPool.BLANK;
		String[] parts= url.split("/");
		String uuid= parts[5];
		int posicionInterrogante = uuid.indexOf(StringPool.QUESTION);
		if (uuid.contains(StringPool.QUESTION)) {
			uuid = uuid.substring(0, posicionInterrogante);
		}
		long groupId = Long.parseLong(parts[2]);
		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId);
			result = fileEntry.getMimeType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getImageURL(String url) {
		int lastIndex = url.lastIndexOf('/');
		if(lastIndex != -1) {
			return url.substring(0, lastIndex);
		} else {
			return url;
		}
	}
	
	@Override
	public Map<String, String> getChildLayoutsLinksMap(Layout layout){
		Map<String, String> mapEnlaces = new HashMap<String, String>();
		List<Layout> childLayouts = layout.getChildren();
		for (Layout childLayout : childLayouts) {
			mapEnlaces.put(childLayout.getName(), childLayout.getFriendlyURL());
		}
        return sortMapByKey(mapEnlaces);
    }
    
	/**
	 * Returns an ordered map by key
	 * @param map Unordered map
	 * @return Ordered map by key
	 */
    private static HashMap<String, String> sortMapByKey(Map<String, String> map) {
    	HashMap<String, String> result = new LinkedHashMap<String, String>();
    	//Create a list from elements of HashMap
        List<Map.Entry<String, String>> hashmapList = new LinkedList<Map.Entry<String, String>>(map.entrySet());
        //Sort the list
        Collections.sort(hashmapList, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> map1, Map.Entry<String, String> map2) {
                return (map1.getKey()).compareTo(map2.getKey());
            }
        });
        //Put data from sorted list to hashmap
        for (Map.Entry<String, String> currentMap : hashmapList) {
        	result.put(currentMap.getKey(), currentMap.getValue());
        }
        return result;
	}
    
    @Override
    public String getSearcherUrl(Group group) {
    	String url = StringPool.BLANK;
    	String expandoAttribute = "searcher-url";
    	if (group != null) {
    		if (group.getExpandoBridge().hasAttribute(expandoAttribute)) {
    			url = group.getExpandoBridge().getAttribute(expandoAttribute, false) != null ? String.valueOf(group.getExpandoBridge().getAttribute(expandoAttribute, false)) : url;
    		} else {
    			try {
    				group.getExpandoBridge().addAttribute(expandoAttribute);
    			} catch (PortalException e) {
    				_log.error("There was an error adding the expando attribute: " + e.toString());
    			}
    		}
    	}
    	return url;
    }
    
    @Override
    public String getGroupExpandoAttributeValue(long groupId, String expandoAttributeName) {
    	String value = StringPool.BLANK;
    	Group group = _groupLocalService.fetchGroup(groupId);
    	if (group != null) {
    		if (group.getExpandoBridge().hasAttribute(expandoAttributeName)) {
    			value = GetterUtil.getString(group.getExpandoBridge().getAttribute(expandoAttributeName, false), StringPool.BLANK);
    		}
    	}
    	return value;
    }
    
    @Override
    public String getAssetCategoryURL(HttpServletRequest request, long assetCategoryId, boolean addCurrentPageCategory) {
    	String url = StringPool.BLANK;
    	//Get themedisplay from request
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	//Gets all the pages whose related-category-id expando has the value of the category received by parameters
    	DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class, PortalClassLoaderUtil.getClassLoader())
    			.add(
    					PropertyFactoryUtil.forName("columnId").in(
    							DynamicQueryFactoryUtil.forClass(ExpandoColumn.class, PortalClassLoaderUtil.getClassLoader())
    							.add(PropertyFactoryUtil.forName("name").eq("related-category-id"))
    							.setProjection(ProjectionFactoryUtil.property("columnId"))
    							)
    					)
    			.add(PropertyFactoryUtil.forName("data").eq(String.valueOf(assetCategoryId)));
		List<ExpandoValue> expandoValues = ExpandoValueLocalServiceUtil.dynamicQuery(dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		if (!expandoValues.isEmpty()) {
			for (ExpandoValue expandoValue : expandoValues) {
				long plid = expandoValue.getClassPK();
				Layout layout = LayoutLocalServiceUtil.fetchLayout(plid);
				if (Validator.isNotNull(layout)) {
					url = layout.getFriendlyURL();
				}
			}
		}
		//If category layout doesnt exists, the url will redirect to the filtered searcher
		if (url.isEmpty()) {
			List<Long> assetCategoryIds = new ArrayList<>();
			assetCategoryIds.add(assetCategoryId);
			//If addCurrentPageCategory is true, the current layout category will be added in the url
			if (addCurrentPageCategory) {
				long originCategoryId = -1;
				Layout currentLayout = themeDisplay.getLayout();
				if (currentLayout != null) {
					String relatedCategoryExpando = "related-category-id";
					if (currentLayout.getExpandoBridge().hasAttribute(relatedCategoryExpando)) {
						originCategoryId = currentLayout.getExpandoBridge().getAttribute(relatedCategoryExpando, false) != null ? GetterUtil.getLong(currentLayout.getExpandoBridge().getAttribute(relatedCategoryExpando, false), -1) : -1;
						if (originCategoryId > -1) {
							assetCategoryIds.add(originCategoryId);
						}
					}
				}
			}
			url = getSearcherRenderURL(request, assetCategoryIds);
		}
		return url;
	}
    
    private String getSearcherRenderURL(HttpServletRequest request, List<Long> assetCategoryIds) {
    	String url = StringPool.BLANK;
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	String searcherURL = getSearcherUrl(themeDisplay.getLayout().getGroup());
    	if (!searcherURL.isEmpty()) {
    		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getLayout().getGroupId(), false, searcherURL);
        	long plid = 0;
    		if (Validator.isNotNull(layout)) {
    			plid = layout.getPlid();
    			PortletURL renderUrl =  PortletURLFactoryUtil.create(request, "es_aragon_base_search_web_SearchWebPortlet", plid, PortletRequest.RENDER_PHASE);
    	    	try {
    				renderUrl.setWindowState(LiferayWindowState.NORMAL);
    			} catch (WindowStateException e) {
    				// Unable to set window state
    			}
    	    	try {
    	    		renderUrl.setPortletMode(LiferayPortletMode.VIEW);
    			} catch (PortletModeException e) {
    				// Unable to set portlet mode
    			}
    	    	StringBuffer selectedCategories = new StringBuffer(StringPool.BLANK);
    	    	if (assetCategoryIds != null && !assetCategoryIds.isEmpty()) {
    	    		for (int i = 0; i < assetCategoryIds.size(); i++) {
    	    			if (i > 0) {
    	    				selectedCategories.append(StringPool.MINUS);
    	    			}
    	    			selectedCategories.append(assetCategoryIds.get(i));
    	    		}
    	    	}
    	    	renderUrl.setParameter("selectedCategories", selectedCategories.toString());
    	    	renderUrl.setParameter("currentPage", "0");
    	    	renderUrl.setParameter("searchType", "any");
    			url = renderUrl.toString();
    		}
    	}
    	return url;
	}
	
	@Override
	public Locale getLocale(String localeId) {
		return Locale.US;
	}
	
	public boolean isJournalPage(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String currentURL = themeDisplay.getURLCurrent();
		if(currentURL.startsWith("/en/-/")) {
			return true;
		} else if (currentURL.startsWith("/en/web/guest/-/")) {
			return true;
		}
		return false;
	}

	@Override
	public String getTranslatedContentUrl(String urlOrigin, long groupId, Locale locale) {
    	String result = urlOrigin;
    	String expandoAttribute = "searcher-url";
    	String urlBuscador = StringPool.BLANK;
    	try {
    		Group group = _groupLocalService.getGroup(groupId);
        	if (group != null) {
        		if (group.getExpandoBridge().hasAttribute(expandoAttribute)) {
        			urlBuscador = String.valueOf(group.getExpandoBridge().getAttribute(expandoAttribute, false));
        		}
        	}
	    	result = HttpUtil.decodeURL(result);
	    	String redirect = HttpUtil.getParameter(result, "redirect", false);
	    	if(redirect.contains("/-/") && !redirect.contains(urlBuscador)){
	    		int posicion = redirect.indexOf("/-/") + 3;
	    		String currentTitleJournal = redirect.substring(posicion);
	    		JournalArticle journalArticle = _journalArticleLocalService.fetchArticleByUrlTitle(groupId, currentTitleJournal);
	    		if (Validator.isNotNull(journalArticle)) {
		    		String newRedirect = journalArticle.getUrlTitle(locale);
		    		if ( Validator.isNotNull(newRedirect)) {
		    			result = HttpUtil.removeParameter(result, "redirect");
		    			newRedirect = StringPool.SLASH + StringPool.MINUS + StringPool.SLASH + newRedirect;
		    			result = HttpUtil.addParameter(result, "redirect", newRedirect);	
		    		}
		    	}	    	
	    	}
    	}catch(Exception e) {
    		_log.error("Not found url translate: "+e.toString());
    	}
    	return result;
    }
    
	@Override
	public String getReadSpeakerURL(String originElementId, String currentURL, Locale locale) {
		String customerId = "4976";
		String language = locale.toString().toLowerCase();
		String url = HtmlUtil.escapeURL(currentURL);
		return "//app-eu.readspeaker.com/cgi-bin/rsent?customerid=" + customerId + "&amp;lang=" + language + "&amp;readid=" + originElementId + "&amp;url=" + url;
	}
	
	@Override
	public String getOpenDataURL(String categories) {
		String[] categoriesId = categories.split(",");
		List<String> topics = new ArrayList<>();
		
		List<String> organisms = new ArrayList<>();
		
		for(String categoryId : categoriesId) {
			switch (checkTopicOrganism(categoryId)) {
				case TOPIC: // is topic
					topics.add(categoryId);
					break;
				case ORGANISM: // is organism
					organisms.add(categoryId);
					break;
				default:
					break;
			}
		}
		
		return buildOpenDataURL(topics, organisms);
	}

	@Override
	public String getOpenDataURLFromJournal(JournalArticle journalArticle) {
		List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getCategories(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
		if(!assetCategories.isEmpty()) {
			return getOpenDataURL(assetCategories.stream().map(AssetCategory::getCategoryId).map(i -> Long.toString(i)).collect(Collectors.joining(",")));
		} else {
			return OPENDATA_URL;
		}
	}
	
	/**
	 * @param categoryId
	 * @return
	 */
	private int checkTopicOrganism(String categoryId) {
		AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(Long.parseLong(categoryId));
		if(Validator.isNull(assetCategory)) {
			return NOT_FOUND;
		}
		
		AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(assetCategory.getVocabularyId());
		if(Validator.isNull(assetVocabulary)) {
			return NOT_FOUND;
		}
		
		if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_EN) ||
				assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES)) {
			return TOPIC;
		}
		
		if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_EN) ||
				assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
			return ORGANISM;
		}
		
		return NOT_FOUND;
	}
	
	private String buildOpenDataURL(List<String> topics, List<String> organisms) {
		String initURL = OPENDATA_URL + "/busqueda/siu";
		String organismURL = null;
		String topicsURL = null;
		// build organisms
		if(!organisms.isEmpty()) {
			organismURL = "";
			for(String organismId : organisms) {
				CustomCategoryProperty customCategoryProperty = 
						_customCategoryPropertyLocalService.fetchByCategoryIdAndKey(Long.parseLong(organismId), "COD_SIU");
				if(Validator.isNotNull(customCategoryProperty) && !customCategoryProperty.getText().equalsIgnoreCase("")) {
					if(organismURL.equalsIgnoreCase("")) {
						organismURL = "org=";
					}
					organismURL = organismURL.concat(customCategoryProperty.getText()).concat("+");
				}
			}
		}
		
		// build topics
		if(!topics.isEmpty()) {
			topicsURL = "";
			for(String topicId : topics) {
				CustomCategoryProperty customCategoryProperty = 
						_customCategoryPropertyLocalService.fetchByCategoryIdAndKey(Long.parseLong(topicId), "ALIAS");
				if(Validator.isNotNull(customCategoryProperty) && !customCategoryProperty.getText().equalsIgnoreCase("")) {
					if(topicsURL.equalsIgnoreCase("")) {
						topicsURL = "tema=";
					}
					topicsURL = topicsURL.concat(customCategoryProperty.getText()).concat("+");
				}
			}
		}
		
		
		if(Validator.isNotNull(organismURL) && !organismURL.equalsIgnoreCase("")) {
			initURL = initURL.concat("?").concat(organismURL.substring(0, organismURL.length() - 1));
		}
		
		if(Validator.isNotNull(topicsURL) && !topicsURL.equalsIgnoreCase("")) {
			if(organisms.isEmpty()) {
				initURL = initURL.concat("?");
			} else {
				initURL = initURL.concat("&");
			}
			initURL = initURL.concat(topicsURL.substring(0, topicsURL.length() - 1));
		}
		
		if(initURL.equalsIgnoreCase(OPENDATA_URL + "/busqueda/siu")) {
			initURL = OPENDATA_URL;
		}
		return initURL;
	}
	
	@Override
	public void incrementViewCounter(long resourcePrimaryKey, long userId) {
		AssetEntryLocalServiceUtil.incrementViewCounter(userId, JournalArticle.class.getName(), resourcePrimaryKey, 1);
	}
	
	
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	
	@Reference
	private AssetEntryLocalService _assetEntryLocalService;
	
	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;
	
	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;
	
	@Reference
	private GroupLocalService _groupLocalService;
	
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	
	@Reference
	private JournalContentSearchLocalService _journalContentSearchLocalService;
	
	@Reference
	private LayoutLocalService _layoutLocalService;
	
	@Reference
	private CustomCategoryPropertyLocalService _customCategoryPropertyLocalService;
	
	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(FreemarkerUtilities.class);
	
	private static final int NOT_FOUND = -1;
	private static final int TOPIC = 0;
	private static final int ORGANISM = 1;
	private static final String OPENDATA_URL = "https://opendata.aragon.es/datos/catalogo";
}