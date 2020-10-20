package es.aragon.base.search.web.factory;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.facet.DateRangeFacet;
import com.liferay.portal.kernel.search.facet.DateRangeFacetFactory;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.SimpleFacetFactory;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;
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
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import es.aragon.base.crawler.model.Page;
import es.aragon.base.search.web.dto.SearchDTO;

/**
 * @author Mikel Jorge
 * @author Miguel Juaniz
 * @author Asier Guillo
 */
public class SearchFactory {

	private static Log log = LogFactoryUtil.getLog(SearchFactory.class);
	
	private SearchFactory() {}
	
	/**
	 * Method used to search and return results to search portlet
	 * @return
	 */
	public static Hits search(SearchContext searchContext, SearchDTO searchDTO, boolean allWordsMustMatch) {
		
		Hits hits = null;
		
		setFacets(searchContext, searchDTO);
		
		try {
			BooleanQuery booleanQuery = getBooleanQuery(searchContext, searchDTO, allWordsMustMatch);
			hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			
			// used to save searched facets. If it's not done, then in portlet will be null
			Map<String, Facet> searchedFacets = searchContext.getFacets();
			searchContext.setAttribute("searchedFacets", (Serializable) searchedFacets);
		} catch (SearchException e) {
			log.error("Error searching: ", e);
		}
		
		return hits;
	}
	
	private static void setFacets(SearchContext searchContext, SearchDTO searchDTO) {
		
		for (String facetString : searchDTO.getFacets()) {
			if (facetString=="createDate") {
				setCreateDateFacet(searchContext, searchDTO, facetString);
			} else {
				FacetConfiguration facetConfiguration = new FacetConfiguration();
				facetConfiguration.setFieldName(facetString);
				facetConfiguration.setLabel("Label");
				facetConfiguration.setOrder("OrderHitsDesc");
				facetConfiguration.setStatic(false);
				facetConfiguration.setWeight(1.0);
				//Set the default limit of 10 facets
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put("maxTerms", 1000);
				facetConfiguration.setDataJSONObject(jsonObject);
				SimpleFacetFactory simpleFacetFactory = new SimpleFacetFactory();
				Facet facet = simpleFacetFactory.newInstance(searchContext);
				facet.setFacetConfiguration(facetConfiguration);
				searchContext.addFacet(facet);
			}
		}
	}
	
	private static void setCreateDateFacet(SearchContext searchContext, SearchDTO searchDTO, String facetString) {
		
		JSONArray array = JSONFactoryUtil.createJSONArray();
		for (Entry<String, Date> range : dateRanges().entrySet()) {
			if(range.getKey().equals("customRange") && searchDTO.getFromDate() != null && searchDTO.getToDate() != null) {
				JSONObject range1 = JSONFactoryUtil.createJSONObject();
				range1.put("label","customDate");
				range1.put("range","["+ searchDTO.getFromDate().getTime() +" TO " + searchDTO.getToDate().getTime() +"]");
				array.put(range1);
			}else {
				String to = String.valueOf(System.currentTimeMillis());
				String from = String.valueOf(range.getValue().getTime());
				JSONObject range1 = JSONFactoryUtil.createJSONObject();
				range1.put("label","customDate");
				range1.put("range","["+ from +" TO " + to +"]");
				array.put(range1);
			}
		}
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("ranges", array);
		jsonObject.put("format","epoch_millis");
		
		FacetConfiguration facetConfiguration = new FacetConfiguration();
		facetConfiguration.setDataJSONObject(jsonObject);
		facetConfiguration.setClassName(DateRangeFacet.class.getName());
		facetConfiguration.setFieldName(facetString);
		facetConfiguration.setLabel("CustomRange");
		facetConfiguration.setOrder("OrderHitsDesc");
		facetConfiguration.setStatic(false);
		facetConfiguration.setWeight(1.0);
		
		DateRangeFacetFactory dateRangeFacetFactory = new DateRangeFacetFactory();
		Facet facet = dateRangeFacetFactory.newInstance(searchContext);
		facet.setFacetConfiguration(facetConfiguration);
		searchContext.addFacet(facet);
	}
	
	private static Map<String, Date> dateRanges() {
		Map<String,Date> ranges = new HashMap<>();
		
		// Code used to add others facets than customRange
		Calendar today = Calendar.getInstance();
		
		today.add(Calendar.HOUR, -1);
		Date oneHourAfter = today.getTime();
		ranges.put("oneHourAfter",oneHourAfter);
		
		today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, -1);
		Date oneDayAfter = today.getTime();
		ranges.put("oneDayAfter",oneDayAfter);
		
		today = Calendar.getInstance();
		today.add(Calendar.WEEK_OF_MONTH, -1);
		Date oneWeekAfter = today.getTime();
		ranges.put("oneWeekAfter",oneWeekAfter);
		
		today = Calendar.getInstance();
		today.add(Calendar.MONTH, -1);
		Date oneMonthAfter = today.getTime();
		ranges.put("oneMonthAfter",oneMonthAfter);
		
		today = Calendar.getInstance();
		today.add(Calendar.YEAR, -1);
		Date oneYearAfter = today.getTime();
		ranges.put("oneYearAfter",oneYearAfter);
		
		ranges.put("customRange",new Date());
		
		return ranges;
	}
	
	private static BooleanQuery getBooleanQuery(SearchContext searchContext, SearchDTO searchDTO, boolean allWordsMustMatch) {
		BooleanQuery fullQuery = new BooleanQueryImpl();
		//Prepare query configuration
		QueryConfig queryConfig = new QueryConfig();
        queryConfig.setHitsProcessingEnabled(true);
        queryConfig.setCollatedSpellCheckResultEnabled(true);
        queryConfig.setQueryIndexingEnabled(true);
        //Set query configuration
        fullQuery.setQueryConfig(queryConfig);
        //Searched text
        String searchedText = searchDTO.getSearchText();
		//If "all words must match" configuration is enabled, the searched text is splitted and all his words must appear in the found results
        if (allWordsMustMatch) {
        	String[] searchedTextWords = searchedText.split(StringPool.SPACE);
        	for (String searchedTextWord : searchedTextWords) {
        		BooleanQuery fullTextBooleanQuery = new BooleanQueryImpl();
            	Map<String, String> fields = (Map<String, String>) searchContext.getAttribute("fields");
            	for (Map.Entry<String, String> field : fields.entrySet()) {
            		BooleanQuery booleanQuery = new BooleanQueryImpl();
        			if (field.getKey().contains("title")) {
        				booleanQuery.setBoost(100.0f);
        			}
        			booleanQuery.addRequiredTerm(field.getKey(), searchedTextWord);
        			//Adds the boolean query to the full query
        			try {
        				fullTextBooleanQuery.add(booleanQuery, BooleanClauseOccur.SHOULD);		
        			} catch (ParseException e) {
        				log.error("Error adding boolean query to full query: ", e);
        			}    			
            	}
    			//Adds the boolean query to the full query
    			try {
    				fullQuery.add(fullTextBooleanQuery, BooleanClauseOccur.MUST);		
    			} catch (ParseException e) {
    				log.error("Error adding boolean query to full query: ", e);
    			} 
        	}
        }
        //If all words must match configuration is disabled, any of the words searched must appear in the found results
        else {
        	//Get fields to search for
        	Map<String, String> fields = (Map<String, String>) searchContext.getAttribute("fields");
        	for (Map.Entry<String, String> field : fields.entrySet()) {
        		BooleanQuery booleanQuery = new BooleanQueryImpl();
    			if (field.getKey().contains("title")) {
    				booleanQuery.setBoost(100.0f);
    			}
    			booleanQuery.addRequiredTerm(field.getKey(), searchedText);
    			//Adds the boolean query to the full query
    			try {
    				fullQuery.add(booleanQuery, BooleanClauseOccur.SHOULD);		
    			} catch (ParseException e) {
    				log.error("Error adding boolean query to full query: ", e);
    			}    			
        	}
        }
        /*
        //Get fields to search for
		Map<String, String> fields = (Map<String, String>) searchContext.getAttribute("fields");
		for (Map.Entry<String, String> field : fields.entrySet()) {
			BooleanQuery booleanQuery = new BooleanQueryImpl();
			if (field.getKey().contains("title")) {
				booleanQuery.setBoost(100.0f);
			}
			//If all words must match configuration is enabled, the searched text is splitted and all his words must appear in the found results
			if (allWordsMustMatch) {
				String[] fieldValueWords = GetterUtil.getString(field.getValue(), StringPool.BLANK).split(StringPool.SPACE);
				for (String fieldValueWord : fieldValueWords) {
					booleanQuery.addRequiredTerm(field.getKey(), fieldValueWord);
				}
			}
			//If all words must match configuration is disabled, any of the words searched must appear in the found results
			else {
				booleanQuery.addRequiredTerm(field.getKey(), (String) field.getValue());
			}
			//Adds the boolean query to the full query
			try {
				fullQuery.add(booleanQuery, BooleanClauseOccur.SHOULD);		
			} catch (ParseException e) {
				log.error("Error adding boolean query to full query: ", e);
			}
		}
		*/
        //Add selected filters to the query
        addFiltersClausure(fullQuery, searchDTO);
		return fullQuery;
	}
	
	private static BooleanQuery addFiltersClausure(BooleanQuery fullQuery, SearchDTO searchDTO) {
		
		BooleanFilter filter = new BooleanFilter();
		
		// add entry filters
		getEntryFilters(filter, searchDTO);
		
		// add group filter
		filter.addTerm("groupId", String.valueOf(searchDTO.getThemeDisplay().getSiteGroupId()), BooleanClauseOccur.MUST);

		// add role filter
		long[] userRoles = getUserRoles(searchDTO.getThemeDisplay());
		if(userRoles.length > 0) {
			BooleanFilter rolesFilter = new BooleanFilter();
			for (long role : userRoles) {
				rolesFilter.addTerm("roleId", String.valueOf(role));
			}
			filter.add(rolesFilter, BooleanClauseOccur.MUST);
		}
		
		// add date filter
		if (searchDTO.getFromDate() != null && searchDTO.getToDate() != null) {
			RangeTermFilter rangeTermFilter = new RangeTermFilter("displayDate_sortable", true, true,
			String.valueOf(searchDTO.getFromDate().getTime()), String.valueOf(searchDTO.getToDate().getTime()));
			filter.add(rangeTermFilter, BooleanClauseOccur.MUST);
		}
		//Add category filters
		long[] selectedCategoryIds = searchDTO.getSelectedCategories();
		//Group selected categories by vocabulary and add his children to the query
		Map<Long, List<Long>> selectedVocabulariesCategoryIdsMap = new HashMap<>();
		if (selectedCategoryIds != null && selectedCategoryIds.length > 0) {
			for (long selectedCategoryId : selectedCategoryIds) {
				AssetCategory selectedCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(selectedCategoryId);
				if (selectedCategory != null) {
					long vocabularyId = selectedCategory.getVocabularyId();
					List<Long> selectedVocabularyCategories = new ArrayList<>();
					//If the vocabulary is already on the map, its category list is retrieved
					if (selectedVocabulariesCategoryIdsMap.containsKey(vocabularyId)) {
						selectedVocabularyCategories = selectedVocabulariesCategoryIdsMap.get(vocabularyId);
					}
					//Adds the category and all his children to the vocabulary categories list
					long[] selectedCategoryAndChildrenIds = getCategoryAndChildrenIds(selectedCategoryId);
					if (selectedCategoryAndChildrenIds != null && selectedCategoryAndChildrenIds.length > 0) {
						for (long selectedCategoryAndChildrenId : selectedCategoryAndChildrenIds) {
							if (!selectedVocabularyCategories.contains(selectedCategoryAndChildrenId)) {
								selectedVocabularyCategories.add(selectedCategoryAndChildrenId);
							}
						}
					}
					selectedVocabulariesCategoryIdsMap.put(vocabularyId, selectedVocabularyCategories);
				}
			}
		}
		for (Map.Entry<Long, List<Long>> entry : selectedVocabulariesCategoryIdsMap.entrySet()) {
			BooleanFilter categoryFilter = new BooleanFilter();
			List<Long> vocabularyCategoriesIdsList = entry.getValue();
			if (vocabularyCategoriesIdsList != null && !vocabularyCategoriesIdsList.isEmpty()) {
				for (long vocabularyCategoryId : vocabularyCategoriesIdsList) {
					categoryFilter.addTerm("assetCategoryIds", String.valueOf(vocabularyCategoryId), BooleanClauseOccur.SHOULD);
				}
			}
			filter.add(categoryFilter, BooleanClauseOccur.MUST);
        }
		
		//Add filters to the main query
		fullQuery.setPreBooleanFilter(filter);
		
		//Return
		return fullQuery;
	}
	
	private static long[] getCategoryAndChildrenIds(long selectedCategory) {
		Set<Long> categories = new HashSet<>();
		AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(selectedCategory);
		if (assetCategory != null) {
			categories.add(selectedCategory);
			getChilds(selectedCategory, categories);
		}
		return categories.stream().mapToLong(l -> l).toArray();
	}
	
	private static void getChilds(long selectedCategory, Set<Long> categories) {
		for(AssetCategory childCategory : AssetCategoryLocalServiceUtil.getChildCategories(selectedCategory)) {
			categories.add(childCategory.getCategoryId());
			getChilds(childCategory.getCategoryId(), categories);
		}
	}
	
	/**
	 * Adds preconfigured entries filters
	 * @param filter Original filter where entry filters will be added
	 * @param searchDTO Search data transfer object
	 */
	private static void getEntryFilters(BooleanFilter filter, SearchDTO searchDTO) {
		for (long selectedAssetType : searchDTO.getSelectedAssetTypes()) {
			ClassName className = ClassNameLocalServiceUtil.fetchClassName(selectedAssetType);
			if (Validator.isNotNull(className)) {
				if (className.getValue().equals(JournalArticle.class.getName())) {
					BooleanFilter journalFilter = new BooleanFilter();
					journalFilter.addRequiredTerm("entryClassName", className.getValue());
					//Only includes last published versions
					journalFilter.addRequiredTerm("head", true);
					//Only includes approved entries
					journalFilter.addRequiredTerm("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));
					//Adds structure filter
					if (searchDTO.getSelectedStructures().length > 0) {
						journalFilter.add(getStructureFilters(searchDTO), BooleanClauseOccur.MUST);
					}
					//Adds journal filter to the global filter
					filter.add(journalFilter, BooleanClauseOccur.SHOULD);
				} else if (className.getValue().equals(DLFileEntry.class.getName())) {
					BooleanFilter fileEntryFilter = new BooleanFilter();
					fileEntryFilter.addRequiredTerm("entryClassName", className.getValue());
					//Excludes images
					fileEntryFilter.addTerm("mimeType", "image_png", BooleanClauseOccur.MUST_NOT);
					fileEntryFilter.addTerm("mimeType", "image_jpeg", BooleanClauseOccur.MUST_NOT);
					fileEntryFilter.addTerm("mimeType", "image_tiff", BooleanClauseOccur.MUST_NOT);
					fileEntryFilter.addTerm("mimeType", "image_svg+xml", BooleanClauseOccur.MUST_NOT);
					fileEntryFilter.addTerm("mimeType", "image_vnd.microsoft.icon", BooleanClauseOccur.MUST_NOT);
					fileEntryFilter.addTerm("mimeType", "image_gif", BooleanClauseOccur.MUST_NOT);
					fileEntryFilter.addTerm("mimeType", "image_webp", BooleanClauseOccur.MUST_NOT);
					//Only includes approved entries
					fileEntryFilter.addRequiredTerm("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));
					//Adds fileEntry filter to the global filter
					filter.add(fileEntryFilter, BooleanClauseOccur.SHOULD);
				} else if (className.getValue().equals(AssetCategory.class.getName())) {
					BooleanFilter assetCategoryFilter = new BooleanFilter();
					//Adds only organismos categories
					AssetVocabulary organismosVocabulary = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(searchDTO.getThemeDisplay().getSiteGroupId(), "Organismos");
					AssetVocabulary municipalitiesVocabulary = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(searchDTO.getThemeDisplay().getSiteGroupId(), "Municipios");
					if (Validator.isNotNull(organismosVocabulary) && Validator.isNotNull(municipalitiesVocabulary)) {
						assetCategoryFilter.addRequiredTerm("entryClassName", className.getValue());
						assetCategoryFilter.addTerm("assetVocabularyId", String.valueOf(organismosVocabulary.getVocabularyId()), BooleanClauseOccur.SHOULD);	
						assetCategoryFilter.addTerm("assetVocabularyId", String.valueOf(municipalitiesVocabulary.getVocabularyId()), BooleanClauseOccur.SHOULD);	
					}
					//Adds assetCategory filter to the global filter
					filter.add(assetCategoryFilter, BooleanClauseOccur.SHOULD);
				} else if (className.getValue().contentEquals(Page.class.getName())){
					BooleanFilter externalPortals = new BooleanFilter();
					externalPortals.addRequiredTerm("entryClassName", className.getValue());
					//Only includes approved entries
					externalPortals.addRequiredTerm("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));	
					
					//Add extenal portal filter search
					long[] selectedPortalsToSearch = searchDTO.getSelectedPortalsToSearch();
					if(selectedPortalsToSearch!=null && selectedPortalsToSearch.length>0) {
						BooleanFilter portalsToSearchFilter = new BooleanFilter();
						for (long selectedPortalToSearch : selectedPortalsToSearch) {
							portalsToSearchFilter.addTerm("rootPageId", String.valueOf(selectedPortalToSearch), BooleanClauseOccur.SHOULD);
						}
						externalPortals.add(portalsToSearchFilter, BooleanClauseOccur.MUST);
					}
					//Adds other filter to the global filter
					filter.add(externalPortals, BooleanClauseOccur.SHOULD);
				} else {
					BooleanFilter otherFilter = new BooleanFilter();
					otherFilter.addRequiredTerm("entryClassName", className.getValue());
					//Only includes approved entries
					otherFilter.addRequiredTerm("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));	
					//Adds other filter to the global filter
					filter.add(otherFilter, BooleanClauseOccur.SHOULD);
				}
			}
		}
	}
	
	private static BooleanFilter getStructureFilters(SearchDTO searchDTO) {
		BooleanFilter structureFilter = new BooleanFilter();
		for(String selectedStructure : searchDTO.getSelectedStructures()) {
			structureFilter.addTerm("ddmStructureKey", selectedStructure, BooleanClauseOccur.SHOULD);
		}
		return structureFilter;
	}
	
	private static long[] getUserRoles(ThemeDisplay themeDisplay) {
		
		long[] userRoles = themeDisplay.getUser().getRoleIds();

		try {
			Role guestRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "Guest");
			if (userRoles.length > 1 || (userRoles.length == 1 && userRoles[0] != guestRole.getRoleId())) {
				// add guest role
				long roleId = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.GUEST).getRoleId();
				userRoles = Arrays.copyOf(userRoles, userRoles.length+1);
				userRoles[userRoles.length-1] = roleId;
				// add owner role
				roleId = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), RoleConstants.OWNER).getRoleId();
				userRoles = Arrays.copyOf(userRoles, userRoles.length+1);
				userRoles[userRoles.length-1] = roleId;
			}
		} catch (PortalException e) {
			log.error("Error adding role filter:" , e);
		}
		
		return userRoles;
	}
	
	public static SearchContext getSearchContext(RenderRequest renderRequest, int displayedElements, int currentPage, String searchText, Locale locale) {

		HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
	    SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
	    
	    // used to display results in diferent pages
		searchContext.setStart(currentPage * displayedElements);
		searchContext.setEnd(currentPage * displayedElements + displayedElements);
		
		// used to prepare query
	    HashMap<String, String> fields = new HashMap<>();
	    ArrayList<String> notLocalizedFieldsArrayList = new ArrayList<>();
	    if (Validator.isNotNull(searchText)) {
	    	fields.put(Field.TITLE, searchText);
	    	fields.put(Field.CONTENT, searchText);
	    	fields.put(Field.DESCRIPTION, searchText);
	    	fields.put(Field.ASSET_CATEGORY_TITLES, searchText);
	    	fields.put(Field.ASSET_TAG_NAMES, searchText);
	    	fields.put("ddmContent", searchText);
	    	notLocalizedFieldsArrayList.addAll(fields.keySet());
	    	fields.put("extension", searchText);
	    	if(Validator.isNotNull(locale)) {
	    		fields.put(Field.getLocalizedName(locale, Field.TITLE), searchText);
		    	fields.put(Field.getLocalizedName(locale, Field.CONTENT), searchText);
		    	fields.put(Field.getLocalizedName(locale, Field.DESCRIPTION), searchText);
		    	fields.put(Field.getLocalizedName(locale, Field.ASSET_CATEGORY_TITLES), searchText);
		    	fields.put(Field.getLocalizedName(locale, Field.ASSET_TAG_NAMES), searchText);
		    	fields.put(Field.getLocalizedName(locale, "ddmContent"), searchText);
	    	}
	    }
		searchContext.setAttribute("fields", fields);
		
		// add highlighted
		QueryConfig queryConfig = searchContext.getQueryConfig();
		queryConfig.setHighlightEnabled(true);

		// set highlightedFields
		String[] highlightedFields = notLocalizedFieldsArrayList.toArray(new String[notLocalizedFieldsArrayList.size()]);
        queryConfig.setHighlightFieldNames(highlightedFields);
		
		// sort
		Sort sort;
		if(Validator.isNull(searchText) && searchText.isEmpty()) {
			sort = new Sort(Field.DISPLAY_DATE + "_sortable", Sort.LONG_TYPE, true);
		} else {
			sort = new Sort("_score", Sort.SCORE_TYPE, true);
		}
		searchContext.setSorts(sort);
		searchContext.setScoresThreshold(20);
		
		return searchContext;
	}
}
