package es.aragon.base.related.categories.factory;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.SimpleFacetFactory;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;



public class RelatedCategoriesFactory {
	private static Log log = LogFactoryUtil.getLog(RelatedCategoriesFactory.class);
	
	public static Map<String, Facet> search(SearchContext searchContext, ThemeDisplay themeDisplay, long categoryId) {
		setFacets(searchContext);
		Map<String, Facet> searchedFacets= new HashMap<>();
		try {
			BooleanQuery booleanQuery = getBooleanQuery(searchContext, themeDisplay, categoryId);
			IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			
			// used to save searched facets. If it's not done, then in portlet will be null
			searchedFacets = searchContext.getFacets();
		} catch (SearchException e) {
			log.error("Error searching: ", e);
		}
		
		return searchedFacets;
	}
	
	private static void setFacets(SearchContext searchContext) {
		
		FacetConfiguration facetConfiguration = new FacetConfiguration();
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("maxTerms", 200);

		facetConfiguration.setDataJSONObject(jsonObject);
		
		facetConfiguration.setFieldName("assetCategoryIds");
		facetConfiguration.setLabel("Label");
		facetConfiguration.setOrder("OrderHitsDesc");
		facetConfiguration.setStatic(false);
		facetConfiguration.setWeight(1.0);
					
		SimpleFacetFactory simpleFacetFactory = new SimpleFacetFactory();
		Facet facet = simpleFacetFactory.newInstance(searchContext);
		facet.setFacetConfiguration(facetConfiguration);
		searchContext.addFacet(facet);
	}
	
	private static BooleanQuery getBooleanQuery(SearchContext searchContext, ThemeDisplay themeDisplay,long categoryId  ) {
		
		BooleanQuery fullQuery = new BooleanQueryImpl();
		
		// prepare query configuration
		QueryConfig queryConfig = new QueryConfig();
        queryConfig.setHitsProcessingEnabled(true);
        queryConfig.setCollatedSpellCheckResultEnabled(true);
        queryConfig.setQueryIndexingEnabled(true);
        
        // set query configuration
        fullQuery.setQueryConfig(queryConfig);

        // add selected filters to the query
        addFiltersClausure(fullQuery,themeDisplay, categoryId);

		return fullQuery;
	}
private static BooleanQuery addFiltersClausure(BooleanQuery fullQuery, ThemeDisplay themeDisplay, long categoryId ) {

		// add entry filters
		
		BooleanFilter filter = new BooleanFilter();
		filter.addRequiredTerm("entryClassName", JournalArticle.class.getName());
		filter.addRequiredTerm("latest", true);
		
		// add group filter
		filter.addTerm("groupId", String.valueOf(themeDisplay.getScopeGroupId()), BooleanClauseOccur.MUST);
		
		//add category filters
		
		filter.addTerm("assetCategoryIds", String.valueOf(categoryId), BooleanClauseOccur.MUST);

		fullQuery.setPreBooleanFilter(filter);
		return fullQuery;
	}
	public static SearchContext getSearchContext(RenderRequest renderRequest) {

		HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
	    SearchContext searchContext = SearchContextFactory.getInstance(httpRequest);
	    
	    // used to display results in diferent pages
		searchContext.setStart(0);
		searchContext.setEnd(0);
		
		return searchContext;
	}
	
}
