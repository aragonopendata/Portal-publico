package es.aragon.base.search.web.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.portlet.MimeResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.crawler.model.Page;
import es.aragon.base.crawler.model.RootPage;
import es.aragon.base.crawler.service.RootPageLocalService;
import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;
import es.aragon.base.search.web.adapter.HitAdapter;
import es.aragon.base.search.web.comparator.AssetCategoryTitleComparator;
import es.aragon.base.search.web.constants.SearchWebPortletKeys;
import es.aragon.base.search.web.dto.SearchDTO;
import es.aragon.base.search.web.factory.SearchFactory;
import es.aragon.base.search.web.portlet.configuration.SearchWebPortletConfiguration;

/**
 * @author Mikel Jorge
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + SearchWebPortletKeys.SEARCH_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SearchWebPortlet extends MVCPortlet {

	/**
	 * Log of the class
	 */
	private static Log log = LogFactoryUtil.getLog(SearchWebPortlet.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final String SNIPPET_LOCALIZED_TITLE = "snippet_localized_title";
	
	private static final String PAGE_CLASS_NAME_VALUE = "es.aragon.base.crawler.model.Page";
	
	private static final int ELASTIC_MAX_RESULTS = 10000;
	
	private volatile SearchWebPortletConfiguration configuration;

	// Facets to use
	private static final String[] FACETS = {"assetCategoryIds"};
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Get preferences
		PortletPreferences portletPreferences = renderRequest.getPreferences();
		int displayedElements = Integer.parseInt(portletPreferences.getValue("defaultDisplayElements", String.valueOf(configuration.defaultDisplayElements())));
		boolean allWordsMustMatch = GetterUtil.getBoolean(portletPreferences.getValue("allWordsMustMatch", null), Boolean.FALSE);
		long[] defaultFilters = StringUtil.split(portletPreferences.getValue("defaultFilters", String.valueOf(configuration.defaultFilters())), 0L);
		long[] selectedAssetTypes = StringUtil.split(portletPreferences.getValue("selectedAssetTypes", String.valueOf(configuration.selectedAssetTypes())), 0L);
		String[] selectedStructures = StringUtil.split(portletPreferences.getValue("selectedStructures", String.valueOf(configuration.selectedStructures())));
		long[] selectedVocabularies = StringUtil.split(portletPreferences.getValue("selectedVocabularies", String.valueOf(configuration.selectedVocabularies())), 0L);
		long[] facetedVocabularies = StringUtil.split(portletPreferences.getValue("facetedVocabularies", String.valueOf(configuration.facetedVocabularies())), 0L);

		String checkedAssetType = portletPreferences.getValue("checkedAssetType", String.valueOf(configuration.checkedAssetType()));
		//Get parameters
		String searchText = ParamUtil.getString(renderRequest, "searchText", StringPool.BLANK);
		String htmlFriendlySearchedText = StringPool.BLANK;
		int currentPage = ParamUtil.getInteger(renderRequest, "currentPage", 0);
		String selectedCategories = ParamUtil.getString(renderRequest, "selectedCategories", StringPool.BLANK);
		String selectedDateRange = ParamUtil.getString(renderRequest, "selectedDateRange", StringPool.BLANK);
		String selectedPortalsToSearch = ParamUtil.getString(renderRequest, "selectedPortalsToSearch", StringPool.BLANK);
		ClassName journalArticleClassName = ClassNameLocalServiceUtil.fetchClassName(JournalArticle.class.getName());
		ClassName assetCategoryClassName = ClassNameLocalServiceUtil.fetchClassName(AssetCategory.class.getName());
		ClassName pageClassName = ClassNameLocalServiceUtil.fetchClassName(PAGE_CLASS_NAME_VALUE);
		ClassName classNameProcedure = ClassNameLocalServiceUtil.fetchClassName("es.aragon.enlinea.db.connection.dao.Procedure");
		//Search default by journal article and organizations.
		String searchType = ParamUtil.getString(renderRequest, "searchType", "any");
		if( searchType.equals("any")){
			searchType = String.valueOf(journalArticleClassName.getClassNameId()) + StringPool.DASH + String.valueOf(assetCategoryClassName.getClassNameId());
		}
		if ( searchType.contains(String.valueOf(journalArticleClassName.getClassNameId())) && !searchType.contains(String.valueOf(assetCategoryClassName.getClassNameId()))){
			searchType = searchType + StringPool.DASH + String.valueOf(assetCategoryClassName.getClassNameId());
		}		
		
		//Treat searchText
		if (Validator.isNotNull(searchText) && !searchText.isEmpty()) {
			searchText = searchText.replaceAll("0x2f", ""); // remove / , is missplacing url on pagination
			searchText = searchText.replaceAll("0x5c", "\\\\");
			searchText = searchText.replaceAll("0x2e", ".");
			htmlFriendlySearchedText = HtmlUtil.escape(searchText);
			searchText = cleanString(searchText);
			searchText = searchText.replaceAll("[^a-zA-Z0-9ñ\\- ]","");
		}
		// Treat selectedCategories
		long[] selectedCategoriesArray = StringUtil.split(selectedCategories, "-", 0L);
		long[] selectedPortalsToSearchArray = StringUtil.split(selectedPortalsToSearch, "-", 0L);
		// add external crawled portals and check the external urls the first time
		List<RootPage> rootPages = rootPageLocalService.getRootPages(themeDisplay.getCompanyId());
		List <Long> selectedPortalToSearchList = new ArrayList<>();
		selectedPortalToSearchList = Arrays.stream(selectedPortalsToSearchArray).boxed().collect(Collectors.toList());
		if(!themeDisplay.getURLCurrent().contains("portal/0") && Validator.isNotNull(selectedPortalToSearchList) && selectedPortalToSearchList.size()==0) {
			for ( RootPage rootPage: rootPages) {
				selectedPortalToSearchList.add(rootPage.getRootPageId());	
			}
			selectedPortalsToSearch = selectedPortalToSearchList.stream().map(i -> Long.toString(i)).collect(Collectors.joining(","));
			if(!(selectedPortalsToSearch == null || selectedPortalsToSearch.equals(StringPool.BLANK))) {
				selectedPortalsToSearchArray = Arrays.stream(selectedPortalsToSearch.split(",")).mapToLong(Long::parseLong).toArray();
			}
			
		}


		long[] selectedAssetTypesArray = StringUtil.split(searchType, "-", 0L);
		List<Long>  selectedAssetTypesArrayList  = new ArrayList<>();
		for ( long selectedAssetType: selectedAssetTypes) {
			selectedAssetTypesArrayList.add(selectedAssetType);
		}
		//Get id AssetCategory entry to default search
		if(Validator.isNotNull(assetCategoryClassName)) {
			selectedAssetTypesArrayList.add(assetCategoryClassName.getClassNameId());
		}
		
		//Parse List<Long> to long[]
		selectedAssetTypes = selectedAssetTypesArrayList.stream().mapToLong(l -> l).toArray();
		if(!(selectedAssetTypesArray.length == 1 && selectedAssetTypesArray[0]==0)) {
			selectedAssetTypes = selectedAssetTypesArray;
		}
		//Get id pageClassName if external portal search is availabled
		if(Validator.isNotNull(selectedPortalsToSearch)) {
			if(Validator.isNotNull(pageClassName)) {
				selectedAssetTypes = ArrayUtil.append(selectedAssetTypes, pageClassName.getClassNameId());
			}
		}
		//Treat procedure search defaults	
		AssetVocabulary termsVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Plazos");
		if(Validator.isNotNull(termsVocabulary)) {
			AssetCategory inTerm = assetCategoryLocalService.fetchCategory(
					themeDisplay.getScopeGroupId(), 0L, "En plazo", termsVocabulary.getVocabularyId());
			AssetCategory outOfTime = assetCategoryLocalService.fetchCategory(
					themeDisplay.getScopeGroupId(), 0L, "Fuera de plazo", termsVocabulary.getVocabularyId());
			if(Validator.isNotNull(inTerm) && Validator.isNotNull(outOfTime)) {
				List<Long> selectedAssetTypesList = Arrays.stream(selectedAssetTypesArray).boxed().collect(Collectors.toList());
				List<Long> selectedCategoriesList = Arrays.stream(selectedCategoriesArray).boxed().collect(Collectors.toList());
				if(selectedAssetTypesList.contains(classNameProcedure.getClassNameId())) {
					List<Long> selectedVocabulariesList = Arrays.stream(selectedVocabularies).boxed().collect(Collectors.toList());
					if(!selectedVocabulariesList.contains(termsVocabulary.getVocabularyId())) {
						selectedVocabulariesList.add(termsVocabulary.getVocabularyId());
						selectedVocabularies = selectedVocabulariesList.stream().mapToLong(l -> l).toArray();
					}
					if(selectedAssetTypesList.size() == 1) {
						if(!selectedCategoriesList.contains(inTerm.getCategoryId()) &&
								!selectedCategoriesList.contains(outOfTime.getCategoryId())) {
							selectedCategoriesList.add(inTerm.getCategoryId());
							selectedCategoriesList.add(outOfTime.getCategoryId());
							selectedCategoriesArray = selectedCategoriesList.stream().mapToLong(l -> l).toArray();
						}
					}
				} else {
					if(selectedCategoriesList.contains(inTerm.getCategoryId())) {
						selectedCategoriesList.remove(inTerm.getCategoryId());
					}
					if(selectedCategoriesList.contains(outOfTime.getCategoryId())) {
						selectedCategoriesList.remove(outOfTime.getCategoryId());
					}
					selectedCategoriesArray = selectedCategoriesList.stream().mapToLong(l -> l).toArray();
				}
			}
		}
		//Treat selectedDateRange
		String[] selectedDateRangeArray = StringUtil.split(selectedDateRange, "-");
		Date fromDate = null;
		Date toDate = null;
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		if (selectedDateRangeArray.length == 1) {
			try {
				fromDate = df.parse(selectedDateRangeArray[0]);
				Calendar c = Calendar.getInstance();
		        c.setTime(fromDate);
		        c.add(Calendar.DATE, 1);
		        toDate = c.getTime();
			} catch (ParseException e) {
				// la fecha no tiene el formato adecuado
			}
		} else if(selectedDateRangeArray.length == 2) {
			try {
				toDate = df.parse(selectedDateRangeArray[1]);
				fromDate = df.parse(selectedDateRangeArray[0]);
			} catch (ParseException e) {
				if (Validator.isNotNull(toDate)) {
					Calendar c = Calendar.getInstance();
			        c.setTime(toDate);
			        c.add(Calendar.DATE, -1);
			        fromDate = c.getTime();
				}
			}
		}
		//Set hour of toDate (23:59:59)
		if (Validator.isNotNull(toDate)) {
			Calendar c = Calendar.getInstance();
			c.setTime(toDate);
	        c.set(Calendar.HOUR_OF_DAY, 23);
	        c.set(Calendar.MINUTE, 59);
	        c.set(Calendar.SECOND, 59);
	        toDate = c.getTime();
		}
        //Configure the searchDTO
		SearchContext searchContext = SearchFactory.getSearchContext(renderRequest, displayedElements, currentPage, searchText, themeDisplay.getLocale());
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setSearchContext(searchContext);
		searchDTO.setThemeDisplay(themeDisplay);
		searchDTO.setSearchText(searchText);
		searchDTO.setFromDate(fromDate);
		searchDTO.setToDate(toDate);
		searchDTO.setFacets(FACETS);
		searchDTO.setSelectedCategories(mergeCategoryFilters(defaultFilters, selectedCategoriesArray));
		searchDTO.setSelectedPortalsToSearch(selectedPortalsToSearchArray);
		searchDTO.setSelectedStructures(selectedStructures);
		searchDTO.setSelectedAssetTypes(selectedAssetTypes);
		searchDTO.setSelectedPortalsToSearch(selectedPortalToSearchList);
		//Search for hits
		Hits hits = SearchFactory.search(searchContext, searchDTO, allWordsMustMatch);
		//Change hits structure to show them as results
		List<HitAdapter> hitAdapterList = getHitAdapters(hits, themeDisplay);
		//Calculate total pages
		int totalPages = (int) Math.ceil((double) hits.getLength() / displayedElements);
		if(totalPages > ELASTIC_MAX_RESULTS / displayedElements) {
			totalPages = ELASTIC_MAX_RESULTS / displayedElements;
		}
		
		int urlPage = currentPage;
		if(currentPage >= totalPages) {
			currentPage = totalPages - 1;
		}
		
		//Get category facets
		Map<String, Facet> searchedFacets = (Map<String, Facet>) searchContext.getAttribute("searchedFacets");
		
		//Add search params
		renderRequest.setAttribute("searchText", htmlFriendlySearchedText);
		renderRequest.setAttribute("currentPage", currentPage);
		renderRequest.setAttribute("selectedDateRange", selectedDateRange);
		renderRequest.setAttribute("selectedCategories", selectedCategories);
		renderRequest.setAttribute("searchType", searchType);
		renderRequest.setAttribute("journalArticleClassNameId", String.valueOf(journalArticleClassName.getClassNameId()));
		
		//Add dates strings fro datepickers
		DateFormat attrDF = new SimpleDateFormat("dd/MM/yyyy");
		String fromDateStr = null;
		String toDateStr = null;
		if (Validator.isNotNull(fromDate)) {
			fromDateStr = attrDF.format(fromDate);
		}
		if (Validator.isNotNull(toDate)) {
			toDateStr = attrDF.format(toDate);
		}
		renderRequest.setAttribute("fromDate", fromDateStr);
		renderRequest.setAttribute("toDate", toDateStr);
		
		// add results
		renderRequest.setAttribute("totalResults", hitAdapterList.isEmpty() ? 0 : hits.getLength());
		renderRequest.setAttribute("results", hitAdapterList);
		
		//add faceted assetType
		renderRequest.setAttribute("selectedAssetTypesArray", getLongArrayList(selectedAssetTypesArray));
		
		// add vocabularies
		renderRequest.setAttribute("assetCategoryLocalService", assetCategoryLocalService);
		renderRequest.setAttribute("assetVocabularyLocalService", assetVocabularyLocalService);
		renderRequest.setAttribute("selectedVocabulariesArray", getLongArrayList(selectedVocabularies));
		// add faceted categories
		renderRequest.setAttribute("facetedVocabularies", getCategoryFacets(searchedFacets, facetedVocabularies));
		// add selected categories
		renderRequest.setAttribute("selectedCategoriesArray", getLongArrayList(selectedCategoriesArray));
		renderRequest.setAttribute("checkedAssetType", checkedAssetType);
		// add pagination
		renderRequest.setAttribute("totalPages", totalPages);
		
		// add necessary values
		renderRequest.setAttribute("liferaySearchPortlet", SearchWebPortletKeys.LIFERAY_SEARCH_PORTLET);
		renderRequest.setAttribute("liferayWindowState", LiferayWindowState.MAXIMIZED.toString());
		
		// add configuration
		renderRequest.setAttribute(SearchWebPortletConfiguration.class.getName(), configuration);
		renderRequest.setAttribute("externalPortals", rootPages);
		renderRequest.setAttribute("selectedPortalsToSearch", selectedPortalsToSearch.replace(",", "-"));
		renderRequest.setAttribute("selectedPortalToSearchList", selectedPortalToSearchList);
		
		// add SEO pagination links
		boolean emptyParameters = searchText.isEmpty() && selectedCategories.isEmpty() && selectedDateRange.isEmpty();
		addSEOLinks(renderResponse, urlPage, currentPage, totalPages, emptyParameters, themeDisplay);

		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException,PortletException {
		long parentCategoryId = ParamUtil.getLong(resourceRequest, "parentCategoryId");
		long vocabularyId = ParamUtil.getLong(resourceRequest, "vocabularyId");
		List<AssetCategory> assetCategoriesList = AssetCategoryLocalServiceUtil.getVocabularyCategories(parentCategoryId, vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetCategoryTitleComparator());
		//JSON result object
		JSONArray categoriesJSONArray = JSONFactoryUtil.createJSONArray();
		JSONObject categoryJSONObject = null;
		for (AssetCategory assetCategory : assetCategoriesList) {
			categoryJSONObject = JSONFactoryUtil.createJSONObject();
			categoryJSONObject.put("title", assetCategory.getTitle(LocaleUtil.SPAIN));
			categoryJSONObject.put("categoryId", assetCategory.getCategoryId());
			int childCategoriesCount = AssetCategoryLocalServiceUtil.getChildCategoriesCount(assetCategory.getCategoryId());
			categoryJSONObject.put("childCategoriesCount", childCategoriesCount);
			if (childCategoriesCount > 0) {
				categoryJSONObject.put("hasChildren", true);
			} else {
				categoryJSONObject.put("hasChildren", false);
			}
			categoriesJSONArray.put(categoryJSONObject);
		}		
		resourceResponse.setContentType("text/html");
		PrintWriter writer = resourceResponse.getWriter();
		writer.print(categoriesJSONArray.toString());
		writer.flush();
		writer.close();
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	private List<HitAdapter> getHitAdapters(Hits hits, ThemeDisplay themeDisplay) {
		List<HitAdapter> hitAdapterList = new ArrayList<>();
		for (Document document : hits.getDocs()) {
			String className = document.get("entryClassName");
			long classPK = Long.parseLong(document.get("entryClassPK"));
			if (className.equalsIgnoreCase(JournalArticle.class.getName())) {
				// Journal Article element
				HitAdapter hitAdapter = getHitAdapterFromJournalArticle(classPK, document, themeDisplay);
				if (hitAdapter != null) {
					hitAdapterList.add(hitAdapter);
				}
			} else if (className.equalsIgnoreCase(DLFileEntry.class.getName())) {
				// Documents & Media element
				HitAdapter hitAdapter = getHitAdapterFromDLFileEntry(classPK, document, themeDisplay);
				if (hitAdapter != null) {
					hitAdapterList.add(hitAdapter);
				}
			} else if (className.equalsIgnoreCase(AssetCategory.class.getName())) {
				// AssetCategory
				HitAdapter hitAdapter = getHitAdapterFromAssetCategory(classPK, document, themeDisplay);
				if (hitAdapter != null) {
					hitAdapterList.add(hitAdapter);
				}
			} else if (className.equalsIgnoreCase(PAGE_CLASS_NAME_VALUE)) {
				// AssetCategory
				HitAdapter hitAdapter = getHitAdapterFromExternalPortalPage(classPK, document, themeDisplay);
				if (hitAdapter != null) {
					hitAdapterList.add(hitAdapter);
				}
			} else if (className.equalsIgnoreCase("es.aragon.enlinea.db.connection.dao.Procedure")) { 				// Procedure
				HitAdapter hitAdapter = getHitAdapterFromProcedure(classPK, document, themeDisplay);
				if (hitAdapter != null) {
					hitAdapterList.add(hitAdapter);
				}			
			} else {
				// Asset entry
				HitAdapter hitAdapter = getHitAdapterFromAssetEntry(className, classPK, document, themeDisplay);
				hitAdapterList.add(hitAdapter);
			}
		}
		
		return hitAdapterList;
	}
	
	private HitAdapter getHitAdapterFromJournalArticle(long classPK, Document document, ThemeDisplay themeDisplay) {
		HitAdapter hitAdapter = null;
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(classPK);
		if (journalArticle != null) {
			Locale locale = themeDisplay.getLocale();
			String snippetLocalizedTitle = document.get(locale, SNIPPET_LOCALIZED_TITLE);
			if (Validator.isNull(snippetLocalizedTitle)) {
				snippetLocalizedTitle = journalArticle.getTitle(locale);
			}
			String articleFullURL = _freemarkerUtilities.getArticleFullURL(journalArticle, locale);
			long assetEntryId = getAssetEntryId(journalArticle.getModelClassName(), classPK);
			List<String> categories = getCategories(journalArticle.getGroupId(), JournalArticle.class.getName(), journalArticle.getResourcePrimKey(), locale); 
			hitAdapter = new HitAdapter(snippetLocalizedTitle, journalArticle.getDisplayDate(), articleFullURL, assetEntryId, StringPool.BLANK, categories, JournalArticle.class.getName());
		}
		return hitAdapter;
	}
	
	private HitAdapter getHitAdapterFromDLFileEntry(long classPK, Document document, ThemeDisplay themeDisplay) {
		HitAdapter hitAdapter = null;
		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(classPK);
		if (dlFileEntry != null) {
			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dlFileEntry.getFileEntryId());
				String previewURL = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK);
				String snippetLocalizedTitle = document.get(themeDisplay.getLocale(), SNIPPET_LOCALIZED_TITLE);
				String sizeAndType = _freemarkerUtilities.getFileTypeAndSizeString(fileEntry);
				if (Validator.isNull(snippetLocalizedTitle)) {
					snippetLocalizedTitle = dlFileEntry.getTitle()+StringPool.SPACE+sizeAndType;
				}
				hitAdapter = new HitAdapter(
					snippetLocalizedTitle,
					dlFileEntry.getCreateDate(),
					previewURL,
					getAssetEntryId(dlFileEntry.getModelClassName(), classPK),
					"_blank",
					getCategories(dlFileEntry.getGroupId(), FileEntry.class.getName(), dlFileEntry.getPrimaryKey(), themeDisplay.getLocale()),
					DLFileEntry.class.getName());
			} catch (PortalException e) {
				log.error("Error getting fileEntry URL", e);
			}
		}
		return hitAdapter;
	}

	private HitAdapter getHitAdapterFromExternalPortalPage(long classPK, Document document, ThemeDisplay themeDisplay) {
		HitAdapter hitAdapter = null;
		if(Validator.isNotNull(document)) {
			Locale locale = themeDisplay.getLocale();
			String snippetLocalizedTitle = document.get(locale, SNIPPET_LOCALIZED_TITLE);
			if (Validator.isNull(snippetLocalizedTitle)) {
				snippetLocalizedTitle = document.get(Field.TITLE);
			}
			Date modifiedDate = new Date();
			try {
				modifiedDate = sdf.parse(document.get(themeDisplay.getLocale(), "modified"));
			} catch (ParseException e) {
				// la fecha no tiene el formato adecuado
			}
			List<String> categories = new ArrayList<String>();
			try {
				if(Validator.isNotNull(document.getValues(Field.ASSET_CATEGORY_IDS))) {
					// Solo mostraremos las categorias de los vocabularios Temas y Organismos
					AssetVocabulary vocabularyTemas = assetVocabularyLocalService.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Temas");
					AssetVocabulary vocabularyOrganismos = assetVocabularyLocalService.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Organismos");
					for(String categoryStr : document.getValues(Field.ASSET_CATEGORY_IDS)) {
						if(Validator.isNotNull(categoryStr)) {
							AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(Long.parseLong(categoryStr));
							if((Validator.isNotNull(vocabularyTemas) && assetCategory.getVocabularyId() == vocabularyTemas.getVocabularyId()) ||
								(Validator.isNotNull(vocabularyOrganismos) && assetCategory.getVocabularyId() == vocabularyOrganismos.getVocabularyId())){
								categories.add(assetCategory.getTitle(locale));
							}
						}
					}
				}
			} catch (Exception e) {
				log.error(e, e);
			}
			hitAdapter = new HitAdapter(
				snippetLocalizedTitle,
				modifiedDate,
				document.get(Field.URL),
				0,
				"",
				categories,
				Page.class.getName());
		}
		return hitAdapter;
	}
	
	private HitAdapter getHitAdapterFromAssetCategory(long classPK, Document document, ThemeDisplay themeDisplay) {
		HitAdapter hitAdapter = null;
		AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(classPK);
		if(Validator.isNotNull(assetCategory)) {
			String snippetLocalizedTitle = document.get(themeDisplay.getLocale(), SNIPPET_LOCALIZED_TITLE);
			if (Validator.isNull(snippetLocalizedTitle)) {
				snippetLocalizedTitle = assetCategory.getTitle(themeDisplay.getLocale());
			}
			hitAdapter = new HitAdapter(
				snippetLocalizedTitle,
				assetCategory.getCreateDate(),
				getAssetCategoryURL(classPK),
				getAssetEntryId(assetCategory.getModelClassName(), classPK),
				"",
				new ArrayList<String>(),
				AssetCategory.class.getName());
		}
		return hitAdapter;
	}
	
	private HitAdapter getHitAdapterFromProcedure(long classPK, Document document, ThemeDisplay themeDisplay) {
		String title = GetterUtil.getString(document.get("name"), StringPool.BLANK);
		long groupId = GetterUtil.getLong(document.get("groupId"), 0);
		String friendlyURL = GetterUtil.getString(document.get("friendlyURL"), StringPool.BLANK);
		long[] categoryIds = GetterUtil.getLongValues(document.getValues(Field.ASSET_CATEGORY_IDS), new long[] {});
		return new HitAdapter(
				title,
				null,
				getProcedureURL(themeDisplay, friendlyURL),
				getAssetEntryId("es.aragon.enlinea.db.connection.dao.Procedure", classPK),
				StringPool.BLANK,
				getCategoriesFromIds(categoryIds, groupId, themeDisplay.getLocale()),
				"es.aragon.enlinea.db.connection.dao.Procedure");
	}
	
	private HitAdapter getHitAdapterFromAssetEntry(String className, long classPK, Document document, ThemeDisplay themeDisplay) {
		HitAdapter hitAdapter = null;
		Locale locale = themeDisplay.getLocale();
		String snippetLocalizedTitle = document.get(locale, SNIPPET_LOCALIZED_TITLE);
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(className, classPK);
		if(Validator.isNotNull(assetEntry)) {
			if(Validator.isNull(snippetLocalizedTitle)) {
				snippetLocalizedTitle = assetEntry.getTitle(locale);
			}
			hitAdapter = new HitAdapter(
				snippetLocalizedTitle,
				assetEntry.getModifiedDate(),
				"",
				assetEntry.getEntryId(),
				"",
				getCategories(assetEntry.getGroupId(), className, classPK, locale),
				AssetEntry.class.getName());
		} else {
			if(Validator.isNull(snippetLocalizedTitle)) {
				snippetLocalizedTitle = document.get(locale, "title");
			}
			Date modifiedDate = new Date();
			try {
				modifiedDate = sdf.parse(document.get(locale, "modified"));
			} catch (ParseException e) {
				// la fecha no tiene el formato adecuado
			}
			hitAdapter = new HitAdapter(
				snippetLocalizedTitle,
				modifiedDate,
				"",
				0,
				"",
				getCategories(themeDisplay.getLayout().getGroupId(), className, classPK, locale),
				AssetEntry.class.getName());
		}
		return hitAdapter;
	}
	
	private Map<Long, List<Entry<AssetCategory, Integer>>> getCategoryFacets(Map<String, Facet> searchedFacets, long[] facetedVocabularies) {
		Map<Long, List<Entry<AssetCategory, Integer>>> facets = new HashMap<>();
		for(long facetedVocabulary : facetedVocabularies) {
			facets.put(facetedVocabulary, new ArrayList<>());
		}
		Arrays.sort(facetedVocabularies);
		Facet assetCategoryIds = searchedFacets.get(FACETS[0]);
		List<TermCollector> collectors = assetCategoryIds.getFacetCollector().getTermCollectors();
		for(TermCollector termCollector : collectors) {
			if(Validator.isNotNull(termCollector.getTerm())) {
				AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(Long.parseLong(termCollector.getTerm()));
				if(Validator.isNotNull(assetCategory) && Arrays.binarySearch(facetedVocabularies, assetCategory.getVocabularyId()) >= 0) {
					List<Entry<AssetCategory, Integer>> vocabularyFacets = facets.get(assetCategory.getVocabularyId());
					if(Validator.isNull(vocabularyFacets)) {
						vocabularyFacets = new ArrayList<>();
					}
					vocabularyFacets.add(new AbstractMap.SimpleEntry<AssetCategory, Integer>(assetCategory, termCollector.getFrequency()));
					facets.put(assetCategory.getVocabularyId(), vocabularyFacets);
				}
			}
		}
		return facets;
	}
	
	public static String cleanString(String str) {
	    String clean = null;
	    if (str != null) {
	        clean = Normalizer.normalize(str, Normalizer.Form.NFD);
	        clean = clean.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
	        clean = Normalizer.normalize(clean, Normalizer.Form.NFC);
	    }
	    return clean;
	}
	
	public long getAssetEntryId(String className, long classPK) {
		long entryId = 0;
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(className, classPK);
		if(assetEntry!=null){
			entryId = assetEntry.getEntryId();
		}
		return entryId;
	}
	
	public String getAssetCategoryURL(long assetCategoryId) {
		String url = "";
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
		if(!expandoValues.isEmpty()) {
			for (ExpandoValue expandoValue : expandoValues) {
				long plid = expandoValue.getClassPK();
				Layout layout = LayoutLocalServiceUtil.fetchLayout(plid);
				if(Validator.isNotNull(layout)) {
					url = layout.getFriendlyURL();
				}
			}
		}
		return url;
	}
	
	public String getProcedureURL(ThemeDisplay themeDisplay, String friendlyURL) {
		String portletId = "es_aragon_enlinea_procedure_web_portlet_EnlineaProcedurePortlet";
	    try {
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), portletId);
			PortletURL procedureURL =  PortletURLFactoryUtil.create(themeDisplay.getRequest(), portletId, plid, PortletRequest.RENDER_PHASE);
	    	try {
	    		procedureURL.setPortletMode(LiferayPortletMode.VIEW);
			} catch (PortletModeException e) {
				// Unable to set portlet mode
			}
			procedureURL.setParameter("friendlyURL", friendlyURL);
			return procedureURL.toString();
		} catch (PortalException e) {
			log.error("Error getting default plid of portlet " + portletId);
		}
	    return StringPool.BLANK;
	}
	
	public List<String> getCategories(long groupId, String className, long classPK, Locale locale) {
		List<String> categories = new ArrayList<>();
		List<AssetCategory> assetCategories = assetCategoryLocalService.getCategories(className, classPK);
		if (assetCategories != null && !assetCategories.isEmpty()) {
			// Solo mostraremos las categorias de los vocabularios Temas y Organismos
			AssetVocabulary vocabularyTemas = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Temas");
			AssetVocabulary vocabularyOrganismos = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Organismos");
			for(AssetCategory assetCategory : assetCategories) {
				if((Validator.isNotNull(vocabularyTemas) && assetCategory.getVocabularyId() == vocabularyTemas.getVocabularyId()) ||
					(Validator.isNotNull(vocabularyOrganismos) && assetCategory.getVocabularyId() == vocabularyOrganismos.getVocabularyId())){
					categories.add(assetCategory.getTitle(locale));
				}
			}
		}
		return categories;
	}
	
	public List<String> getCategoriesFromIds(long[] categoryIds, long groupId, Locale locale) {
		List<String> categories = new ArrayList<>();
		if(categoryIds.length > 0) {
			// Solo mostraremos las categorias de los vocabularios Temas y Organismos
			AssetVocabulary vocabularyTemas = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Temas");
			AssetVocabulary vocabularyOrganismos = assetVocabularyLocalService.fetchGroupVocabulary(groupId, "Organismos");
			for(long categoryId : categoryIds) {
				AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(categoryId);
				if(Validator.isNotNull(assetCategory) &&
					((Validator.isNotNull(vocabularyTemas) && assetCategory.getVocabularyId() == vocabularyTemas.getVocabularyId()) ||
					(Validator.isNotNull(vocabularyOrganismos) && assetCategory.getVocabularyId() == vocabularyOrganismos.getVocabularyId()))){
					categories.add(assetCategory.getTitle(locale));
				}
			}
		}
		return categories;
	}
	
	private long[] mergeCategoryFilters(long[] defaultFilters, long[] selectedCategories) {
		ArrayList<Long> categoryFilters = new ArrayList<>();
		for(long defaultFilter : defaultFilters) {
			if(defaultFilter != 0) {
				categoryFilters.add(defaultFilter);
			}
		}
		for(long selectedCategory : selectedCategories) {
			if(selectedCategory != 0) {
				categoryFilters.add(selectedCategory);
			}
		}
		
		categoryFilters = new ArrayList<>(new HashSet<>(categoryFilters));
		long[] longArray = new long[categoryFilters.size()];
		for (int i = 0; i < categoryFilters.size(); i++) {
		    longArray[i] = categoryFilters.get(i);
		}
		return longArray;
	}
	
	private List<Long> getLongArrayList(long[] array) {
		List<Long> arraylist = new ArrayList<>();
		for(long value : array) {
			arraylist.add(value);
		}
		return arraylist;
	}
	
	public void addSEOLinks(RenderResponse renderResponse, int urlPage, int currentPage, 
			int totalPages, boolean emptyParameters, ThemeDisplay themeDisplay) {
		String page = "/page/";
		
		String actual = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent();
		String canonicalURL = actual.contains("/-/") ? actual.substring(0, actual.indexOf("/-/")) : actual;
		//Remove canonical from searcher
		org.w3c.dom.Element canonical = renderResponse.createElement("link");
		canonical.setAttribute("rel", "canonical");
		canonical.setAttribute("href", canonicalURL);
		renderResponse.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, canonical);
		
		if(currentPage - 1 >= 0) {
			String prevURL = actual.replace(page + urlPage, page + (currentPage-1));
			if(emptyParameters && currentPage == 1) {
				prevURL = canonicalURL;
			}
			org.w3c.dom.Element prev = renderResponse.createElement("link");
			prev.setAttribute("rel", "prev");
			prev.setAttribute("href", prevURL);
			renderResponse.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, prev);
		}
		if(currentPage + 1 < totalPages) {
			String nextURL = actual.contains("/-/") ? actual.replace(page + urlPage, page + (currentPage+1)) : actual + "/-/search/page/" + (currentPage+1);
			org.w3c.dom.Element next = renderResponse.createElement("link");
			next.setAttribute("rel", "next");
			next.setAttribute("href", nextURL);
			renderResponse.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, next);
		}
	}

	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
		configuration = ConfigurableUtil.createConfigurable(SearchWebPortletConfiguration.class, properties);
	}
	
	@Reference
	private JournalContentSearchLocalService journalContentSearchLocalService;
	
	@Reference
	private LayoutLocalService layoutLocalService;
	
	@Reference
	private AssetEntryLocalService assetEntryLocalService;
	
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	
	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;
	
	@Reference
	private FreemarkerUtilities _freemarkerUtilities;
	
	@Reference
	private RootPageLocalService rootPageLocalService;
	
}