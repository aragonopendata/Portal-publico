package es.aragon.base.aragon.calendar.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * Calendar portlet utils class
 * @author Hiberus Digital Business
 * @author Maria Isabel Garcia
 */
public class CalendarPortletUtils {
	
	private CalendarPortletUtils() {}
	
	/**
	 * Transform input Event collection into JSONArray with desired info 
	 * 
	 * @param input {@link Event} {@link Collection}
	 * @param timeZone {@link TimeZone} desired date timeZone
	 * @return {@link JSONArray}
	 */
	public static JSONArray getEventsJSONArray(Collection<JournalArticle> collectionEvent, TimeZone timeZone, HttpServletRequest request) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		for(JournalArticle event : collectionEvent) {
			
			JSONObject jsonObj = getEventJSONObject(event, timeZone, request);
			if (Validator.isNotNull(jsonObj)) {
				jsonArray.put(getEventJSONObject(event, timeZone, request));
			}
		}
		return jsonArray;
	}
	
	/**
	 * Transform input Event into JSONObject with desired info 
	 * 
	 * @param input {@link Event}
	 * @param timeZone {@link TimeZone} desired date timeZone
	 * @return {@link JSONObject}
	 */
	public static JSONObject getEventJSONObject(JournalArticle input, TimeZone timeZone, HttpServletRequest request) {
		JSONObject jsonObject = null;
		Date startDateEvent = input.getReviewDate();
		Date endDateEvent = input.getExpirationDate();
		if (Validator.isNotNull(startDateEvent) && Validator.isNotNull(endDateEvent)) {
			jsonObject = JSONFactoryUtil.createJSONObject();
			Calendar calendar = Calendar.getInstance(timeZone);
			calendar.setTime(startDateEvent);
			//Set year info
			jsonObject.put("year", calendar.get(Calendar.YEAR));
			jsonObject.put("yearSh", calendar.get(Calendar.YEAR) % 100);
			//Set month info
			jsonObject.put("month", calendar.get(Calendar.MONTH));
			//Set day info
			jsonObject.put("day", calendar.get(Calendar.DAY_OF_MONTH));
			//Set title info
			String title = input.getTitle();
			if (title.contains("\"")) {
				title = title.replace("\"", "-");
			}
			jsonObject.put("title", title);
			Calendar calendarEndDate = Calendar.getInstance(timeZone);
			calendarEndDate.setTime(endDateEvent);
			int firtsWeek =  calendar.get(Calendar.WEEK_OF_YEAR);
			int endWeek = calendarEndDate.get(Calendar.WEEK_OF_YEAR);
			int totalDaysWeek = (endWeek-firtsWeek)+1;
			int[] numberWeekEvent = new int[totalDaysWeek];
			for (int i = 0; i < totalDaysWeek; i++) {
				numberWeekEvent[i] = firtsWeek;
				firtsWeek+=1;
			}
			jsonObject.put("week", numberWeekEvent);
			//Set url
			try {
				String url = "/-/" + input.getUrlTitle(request.getLocale());
				jsonObject.put("url", url);
			} catch (PortalException e) {
				e.toString();
			}
			SimpleDateFormat fulldateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fulldateFormat.setTimeZone(timeZone);

			SimpleDateFormat monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH);
			SimpleDateFormat weekDayName = new SimpleDateFormat("EEEE", Locale.ENGLISH);

			SimpleDateFormat hourMinuteFormat = new SimpleDateFormat("HH:mm");
			hourMinuteFormat.setTimeZone(timeZone);
			//Set startDate info
			jsonObject.put("startDate", fulldateFormat.format(input.getReviewDate()));
			//Set endDate info
			jsonObject.put("endDate", fulldateFormat.format(input.getExpirationDate()));
			//Set startHour info
			jsonObject.put("startHour", hourMinuteFormat.format(input.getReviewDate()));
			//Set endHour info
			jsonObject.put("endHour", hourMinuteFormat.format(input.getReviewDate()));
			//Get localizations
			jsonObject.put("monthName", LanguageUtil.get(
					request, "es.aragon.calendar.month.".concat(
							monthName.format(input.getReviewDate()).toLowerCase())));
			jsonObject.put("monthNameSh", LanguageUtil.get(
					request, "es.aragon.calendar.month-short.".concat(
							monthName.format(input.getReviewDate()).toLowerCase())));
			jsonObject.put("weekday-name", LanguageUtil.get(
					request, "es.aragon.calendar.day-of-week.".concat(
							weekDayName.format(input.getReviewDate()).toLowerCase())));
		}
		return jsonObject;
	}
	
	/**
	 * Transform {@link LocalDateTime} in {@link java.util.Date} object
	 * 
	 * @param localDateTime - {@link LocalDateTime} object to be transformed 
	 * @return {@link Date} transformed object
	 */
	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime, String zoneId) {
		return Date.from(localDateTime.atZone(ZoneId.of(zoneId))
				.toInstant());
	}
	
	public static String getLocaleFromBundle(Locale locale, String key) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, CalendarPortletUtils.class);
		return LanguageUtil.get(resourceBundle, key);
	}
	
	/**
	 * Transform {@link LocalDateTime} in {@link java.util.Date} object
	 * 
	 * @param localDateTime - {@link LocalDateTime} object to be transformed 
	 * @return {@link Date} transformed object
	 */
	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		return convertLocalDateTimeToDate(localDateTime, ZoneId.systemDefault().getId());
	}
	
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
	public static List<JournalArticle> getFilteredJournalArticles(HttpServletRequest request, long groupId, long classTypeIds, List<Long> mustContainCategoryIds, List<Long> mustNotContainCategoryIds, int size, Date startEvent, Date endEvent) {
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
	        fullQuery.setPreBooleanFilter(getJournalQueryFilters(groupId, classTypeIds, mustContainCategoryIds, mustNotContainCategoryIds, startEvent, endEvent));
			//Adds results size list
			searchContext.setEnd(100);
			//Configures order
			Sort sort = new Sort("reviewDate_sortable", Sort.LONG_TYPE, Boolean.FALSE);
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
			int count = 0;
			//Get journal articles from asset entries
			for (AssetEntry assetEntry : assetEntryList) {
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(assetEntry.getClassPK(), WorkflowConstants.STATUS_APPROVED);
				if (journalArticle != null && count <= size) {
					result.add(journalArticle);
					count++;
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
	private static BooleanFilter getJournalQueryFilters(long groupId, long classTypeIds, List<Long> mustContainCategoryIds, List<Long> mustNotContainCategoryIds, Date startDate, Date endDate) {
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
        if (classTypeIds != 0) {
			BooleanFilter classTypeFilter = new BooleanFilter();
			classTypeFilter.addTerm("classTypeId", String.valueOf(classTypeIds), BooleanClauseOccur.SHOULD);
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
        //Date filter
//        BooleanFilter dateFilter = new BooleanFilter();
//		if (startDate != null) {
//			RangeTermFilter rangeTermFilterStart = new RangeTermFilter("expirationDate", true, true,String.valueOf(Long.MIN_VALUE)
//			,String.valueOf(startDate.getTime()));
//			dateFilter.add(rangeTermFilterStart, BooleanClauseOccur.MUST);
//		}
//		if (endDate != null) {
//			RangeTermFilter rangeTermFilterEnd = new RangeTermFilter("expirationDate_sortable", true, true,
//					String.valueOf(endDate.getTime()), String.valueOf(Long.MAX_VALUE));
//			dateFilter.add(rangeTermFilterEnd, BooleanClauseOccur.MUST);
//			System.out.println("finish end date:" + endDate.toString());
//		}
		//journalFilter.add(dateFilter, BooleanClauseOccur.MUST);
        
        //Return
        return journalFilter;
	}
	/**
	 * Get structure by name
	 * @param name of the structure that we are looking for 
	 * @param groupId
	 * @return object structure
	 */
	public static DDMStructure fetchStructureByName(String name, long groupId) {
		List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.getStructures(groupId);
		try {
			Locale locale = PortalUtil.getSiteDefaultLocale(groupId);
			if(Validator.isNotNull(ddmStructures)) {
				for(DDMStructure ddmStructure : ddmStructures) {
					if(name.equalsIgnoreCase(ddmStructure.getName(locale))) {
						return ddmStructure;
					}
				}
			}
		} catch (Exception e) {
			_log.error(e.toString());
		}
		return null;
	}
	/**
	 * Log of the class
	 */
	private final static Log _log = LogFactoryUtil.getLog(CalendarPortletUtils.class);

}
