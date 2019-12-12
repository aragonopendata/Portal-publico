package es.aragon.base.contents_by_topic.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;

/**
 * Contents by topic utilities class
 * @author pfalcon
 */
public class ContentsByTopicUtil {
	
	/**
	 * Returns a list of journal article filtered by the given parameters
	 * @param request Original http request
	 * @param groupId Group identifier
	 * @param classTypeIds Class type identifiers
	 * @param mustContainCategoryIds Categories ids that must contain the results
	 * @param mustNotContainCategoryIds Categories ids that must not contain the results
	 * @param size Results list max size
	 * @param orderByCol Column name to order
	 * @param orderByType Order type
	 * @return List of journal article filtered by the given parameters
	 */
	public static List<JournalArticle> getFilteredJournalArticles(HttpServletRequest request, long groupId, long[] classTypeIds, List<Long> mustContainCategoryIds, List<Long> mustNotContainCategoryIds, int size, String orderByCol, String orderByType) {
        _log.debug("START FILTERS");
        _log.debug("groupId: " + groupId);
        _log.debug("classTypeIds: " + (classTypeIds != null ? Arrays.toString(classTypeIds) : "null"));
        _log.debug("mustContainCategoryIds: " + (mustContainCategoryIds != null ? mustContainCategoryIds.toString() : "null"));
        _log.debug("mustNotContainCategoryIds: " + (mustNotContainCategoryIds != null ? mustNotContainCategoryIds.toString() : "null"));
        _log.debug("size: " + size);
        _log.debug("orderByCol: " + orderByCol);
        _log.debug("orderByType: " + orderByType);
        _log.debug("END FILTERS");
		List<JournalArticle> result = new ArrayList<>();
		try {
			//Gets search context
			SearchContext searchContext = SearchContextFactory.getInstance(request);
			//Principal query
			BooleanQuery fullQuery = new BooleanQueryImpl();
			//Sets query configuration
			QueryConfig queryConfig = new QueryConfig();
	        queryConfig.setHitsProcessingEnabled(true);
	        queryConfig.setCollatedSpellCheckResultEnabled(true);
	        queryConfig.setQueryIndexingEnabled(true);
	        //Adds config to the query
	        fullQuery.setQueryConfig(queryConfig);
	        //Adds filters to the query
	        fullQuery.setPreBooleanFilter(getJournalQueryFilters(groupId, classTypeIds, mustContainCategoryIds, mustNotContainCategoryIds));
			//Adds results size list
	        if (size > 0) {
				searchContext.setEnd(size);
	        }
			//Configures order
			String orderField = Field.PUBLISH_DATE + StringPool.UNDERLINE + Field.SORTABLE_FIELD_SUFFIX;
			if (orderByCol != null && !orderByCol.isEmpty()) {
				if (orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_MODIFIED_DATE)) {
					orderField = Field.MODIFIED_DATE + StringPool.UNDERLINE + Field.SORTABLE_FIELD_SUFFIX;
				}
				if (orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_CREATE_DATE)) {
					orderField = Field.CREATE_DATE + StringPool.UNDERLINE + Field.SORTABLE_FIELD_SUFFIX;
				}
			}
			boolean reversedOrder = Boolean.TRUE;
			if (orderByType != null && !orderByType.isEmpty()) {
				if (orderByType.equals(AragonUtilitiesConstant.ORDER_BY_TYPE_ASC)) {
					reversedOrder = Boolean.FALSE;
				}
			}
			Sort sort = new Sort(orderField, Sort.LONG_TYPE, reversedOrder);
			searchContext.setSorts(sort);
	        //Gets search results
			Hits hits = IndexSearcherHelperUtil.search(searchContext, fullQuery);
			//Get asset entries
			List<AssetEntry> assetEntryList = new ArrayList<AssetEntry>();
			Document[] documents = hits.getDocs();
			for (Document document : documents) {
				Field entryClassPKField = document.getField("entryClassPK");
				if (entryClassPKField != null) {
					long resourcePrimKey = GetterUtil.getLong(entryClassPKField.getValue(), 0);
					if (resourcePrimKey > 0) {
						String className = GetterUtil.getString(document.get("entryClassName"), StringPool.BLANK);
						long classPK = GetterUtil.getLong(document.get("entryClassPK"), 0);
						AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(className, classPK);
						if (assetEntry != null) {
							assetEntryList.add(assetEntry);
						}
					}
				}
			}
			//Sort assets by viewCount
			if (orderByCol.equals("viewCount") ) {
				if (reversedOrder) { 
					Collections.sort(assetEntryList, new Comparator<AssetEntry>() {
				        @Override public int compare(AssetEntry a1, AssetEntry a2) {
				            return a2.getViewCount() - a1.getViewCount();
				        }
				    });
				} else {
					Collections.sort(assetEntryList, new Comparator<AssetEntry>() {
				        @Override public int compare(AssetEntry a1, AssetEntry a2) {
				            return a1.getViewCount() - a2.getViewCount();
				        }
				    });
				}
			}
			//Get journal articles from asset entries
			for (AssetEntry assetEntry : assetEntryList) {
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(assetEntry.getClassPK(), WorkflowConstants.STATUS_APPROVED);
				if (journalArticle != null) {
					result.add(journalArticle);
				}				
			}			
			//Sort journals by title
			if (orderByCol.equals("title")) {
				if (reversedOrder) {
					Collections.sort(result, new Comparator<JournalArticle>() {
					    @Override
					    public int compare(JournalArticle o1, JournalArticle o2) {
					        Collator esCollator = Collator.getInstance(LocaleUtil.SPAIN);
					        return esCollator.compare(o2.getTitle(LocaleUtil.SPAIN), o1.getTitle(LocaleUtil.SPAIN));
					    }
					});
				} else {
					Collections.sort(result, new Comparator<JournalArticle>() {
					    @Override
					    public int compare(JournalArticle o1, JournalArticle o2) {
					        Collator esCollator = Collator.getInstance(LocaleUtil.SPAIN);
					        return esCollator.compare(o1.getTitle(LocaleUtil.SPAIN), o2.getTitle(LocaleUtil.SPAIN));
					    }
					});	
				}
			}
		} catch (Exception e) {
			_log.error("There was an error searching the journalArticles: " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Returns a boolean filter with the given parameters
	 * @param groupId Group identifier
	 * @param classTypeIds Class type identifiers
	 * @param mustContainCategoryIds Categories ids that must contain the results
	 * @param mustNotContainCategoryIds Categories ids that must not contain the results
	 * @return Boolean filter with the given parameters
	 */
	private static BooleanFilter getJournalQueryFilters(long groupId, long[] classTypeIds, List<Long> mustContainCategoryIds, List<Long> mustNotContainCategoryIds) {
		BooleanFilter journalFilter = new BooleanFilter();
        //Adds groupId clause to the journal filter
        journalFilter.addTerm("groupId", String.valueOf(groupId), BooleanClauseOccur.MUST);
        //Adds journal article class clause to the journal filter
        journalFilter.addRequiredTerm("entryClassName", JournalArticle.class.getName());
        //Adds status approved clause to the journal filter
        journalFilter.addTerm("status", String.valueOf(WorkflowConstants.STATUS_APPROVED), BooleanClauseOccur.MUST);
        //Adds latest version clause to the journal filter
        journalFilter.addRequiredTerm("head", true);
        //Structures filter
        if (classTypeIds != null && classTypeIds.length > 0) {
			BooleanFilter classTypeFilter = new BooleanFilter();
			for (long classTypeId : classTypeIds) {
				classTypeFilter.addTerm("classTypeId", String.valueOf(classTypeId), BooleanClauseOccur.SHOULD);
			}
			//Adds class type filter to the journal filter
			journalFilter.add(classTypeFilter, BooleanClauseOccur.MUST);
		}
        //Must contains and must not contains categories filter
        if ((mustContainCategoryIds != null && !mustContainCategoryIds.isEmpty()) || (mustNotContainCategoryIds != null && !mustNotContainCategoryIds.isEmpty())) {
        	BooleanFilter categoryFilter = new BooleanFilter();
        	//Must contain categories
        	if (mustContainCategoryIds != null && !mustContainCategoryIds.isEmpty()) {
	        	for (long categoryId : mustContainCategoryIds) {
					categoryFilter.addTerm("assetCategoryIds", String.valueOf(categoryId), BooleanClauseOccur.MUST);
				}
        	}
        	//Must not contain categories
        	if (mustNotContainCategoryIds != null && !mustNotContainCategoryIds.isEmpty()) {
	        	for (long categoryId : mustNotContainCategoryIds) {
					categoryFilter.addTerm("assetCategoryIds", String.valueOf(categoryId), BooleanClauseOccur.MUST_NOT);
				}
        	}
        	//Adds category filter to the journal filter
        	journalFilter.add(categoryFilter, BooleanClauseOccur.MUST);
		}
        //Return
        return journalFilter;
	}
	
	/**
	 * Log of the class
	 */
	private final static Log _log = LogFactoryUtil.getLog(ContentsByTopicUtil.class);
	
}
