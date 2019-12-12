package es.aragon.base.journal_web_overridde_fragment.util;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.service.JournalFolderServiceUtil;
import com.liferay.journal.util.comparator.ArticleCreateDateComparator;
import com.liferay.journal.util.comparator.ArticleDisplayDateComparator;
import com.liferay.journal.util.comparator.ArticleIDComparator;
import com.liferay.journal.util.comparator.ArticleModifiedDateComparator;
import com.liferay.journal.util.comparator.ArticleReviewDateComparator;
import com.liferay.journal.util.comparator.ArticleTitleComparator;
import com.liferay.journal.util.comparator.ArticleVersionComparator;
import com.liferay.journal.util.comparator.FolderArticleArticleIdComparator;
import com.liferay.journal.util.comparator.FolderArticleDisplayDateComparator;
import com.liferay.journal.util.comparator.FolderArticleModifiedDateComparator;
import com.liferay.journal.util.comparator.FolderArticleTitleComparator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

public class JournalWebOverrideFragmentUtil {
	
	public JournalWebOverrideFragmentUtil() {
		
	}
	/**
	 * Recursive function to create Map with all the folders that a user has permissions to view. KEY: is folderId, and VALUE: is folder name
	 *  You can she this method when you need move content from one folder to another one
	 * @param themeDisplay
	 * @param idFolderParent
	 * @return
	 */
	public Map<String, String> getJournalFolderSelectorOptions(ThemeDisplay themeDisplay, long idFolderParent, Map<String, String> listFolderNameMap) {
		List <JournalFolder> journalFoldersList = JournalFolderLocalServiceUtil.getFolders(themeDisplay.getScopeGroupId(), idFolderParent);
		if (journalFoldersList != null && !journalFoldersList.isEmpty()) {
			String spaceDoubleDash = StringPool.BLANK;
			String name = "";
			for (JournalFolder journalFolder : journalFoldersList) {
				try {
					int numFolderAncestor = journalFolder.getAncestorFolderIds().size();
					for (int i = 0; i < numFolderAncestor; i++) {
						spaceDoubleDash = spaceDoubleDash +StringPool.DOUBLE_DASH;
					}
					if( themeDisplay.getPermissionChecker().hasPermission(journalFolder.getGroupId(), journalFolder.getModelClassName(), journalFolder.getFolderId(), ActionKeys.VIEW)){
						name = spaceDoubleDash+StringPool.SPACE+journalFolder.getName();
						listFolderNameMap.put(String.valueOf(journalFolder.getFolderId()), name);
						getJournalFolderSelectorOptions(themeDisplay, journalFolder.getFolderId(), listFolderNameMap);
					}
					spaceDoubleDash = StringPool.BLANK;	
				} catch (PortalException e) {
					e.printStackTrace();
				}		
			}
		}
		return listFolderNameMap;
	}
	
	public SearchContainer getSearchContainer(HttpServletRequest request, LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, PortletURL portletURL, 
			boolean journalArticlesSearchWithIndex, boolean showVersions) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);
		
		// get orderBy column
		String orderByCol = ParamUtil.getString(request, "orderByCol");
		if (Validator.isNull(orderByCol)) {
			orderByCol = portalPreferences.getValue(JournalPortletKeys.JOURNAL, "order-by-col", "modified-date");
		}
		_orderByCol = orderByCol;
		
		// get navigation
		String navigation = ParamUtil.getString(request, "navigation", "all");
		_navigation = navigation;
		
		boolean isNavigationMine = Objects.equals(navigation, "mine");
		boolean isNavigationRecent = Objects.equals(navigation, "recent");
		
		// get orderBy type
		String orderByType = ParamUtil.getString(request, "orderByType");
		_orderByType = orderByType;
		if (isNavigationMine) {
			//orderByType = "asc";
		}
		if (Validator.isNull(orderByType)) {
			//orderByType = portalPreferences.getValue(JournalPortletKeys.JOURNAL, "order-by-type", "asc");
		}
		
		// get status
		int defaultStatus = WorkflowConstants.STATUS_APPROVED;
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		if (permissionChecker.isContentReviewer(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId()) || isNavigationMine) {
			defaultStatus = WorkflowConstants.STATUS_ANY;
		}
		int status = ParamUtil.getInteger(request, "status", defaultStatus);
		_status = status;
		
		// get ddmStructure
		String ddmStructureKey = ParamUtil.getString(request, "ddmStructureKey");
		_ddmStructureKey = ddmStructureKey;
		
		// get ddmTemplate
		String ddmTemplateKey = ParamUtil.getString(request, "ddmTemplateKey");
		
		// get keywords
		String keywords = ParamUtil.getString(request, "keywords");
		
		// get folder
		JournalFolder journalFolder = (JournalFolder) request.getAttribute(WebKeys.JOURNAL_FOLDER);
		if (journalFolder == null) {
			long folderId = ParamUtil.getLong(request, "folderId");
			journalFolder = JournalFolderLocalServiceUtil.fetchFolder(folderId);
		}
		long folderId = BeanParamUtil.getLong(journalFolder, request, "folderId", JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		_folderId = folderId;
		List<Long> userSearchFolderIds = new ArrayList<Long>();
		if(folderId==0) {
			long groupId = themeDisplay.getScopeGroupId();
			List<JournalFolder> journalFolders = JournalFolderLocalServiceUtil.getFolders(groupId);
			for(JournalFolder journalFolderAux : journalFolders) {
				if(hasPermision(themeDisplay, journalFolderAux)) {
					userSearchFolderIds.add(journalFolderAux.getFolderId());
				}
			}
		}
		
		OrderByComparator<JournalArticle> orderByComparator = getArticleOrderByComparator(orderByCol, orderByType);

		SearchContainer articleSearchContainer = new SearchContainer(liferayPortletRequest, getPortletURL(request, portletURL), null, null);
		
		articleSearchContainer.setOrderByCol(orderByCol);
		articleSearchContainer.setOrderByComparator(orderByComparator);
		articleSearchContainer.setOrderByType(orderByCol);

		List<Long> folderIds = new ArrayList<Long>();
		if (folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			folderIds.add(folderId);
		} else {
			folderIds = userSearchFolderIds;
		}

		if(isNavigationMine || isNavigationRecent || Validator.isNotNull(ddmStructureKey) || Validator.isNotNull(ddmTemplateKey) || Validator.isNotNull(keywords)) {
			if (Validator.isNull(ddmStructureKey) && !isNavigationRecent && journalArticlesSearchWithIndex) {
				boolean orderByAsc = false;
	
				if (Objects.equals(orderByType, "asc")) {
					orderByAsc = true;
				}
	
				Sort sort = null;
	
				if (Objects.equals(orderByCol, "display-date")) {
					sort = new Sort("displayDate", Sort.LONG_TYPE, orderByAsc);
				}
				else if (Objects.equals(orderByCol, "id")) {
					sort = new Sort(Field.getSortableFieldName(Field.ARTICLE_ID), Sort.STRING_TYPE, !orderByAsc);
				}
				else if (Objects.equals(orderByCol, "modified-date")) {
					sort = new Sort(Field.MODIFIED_DATE, Sort.LONG_TYPE, orderByAsc);
				}
				else if (Objects.equals(orderByCol, "title")) {
					sort = new Sort("title", Sort.STRING_TYPE, !orderByAsc);
				}
	
				LinkedHashMap<String, Object> params = new LinkedHashMap<>();
	
				params.put("expandoAttributes", keywords);
	
				Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);
	
				SearchContext searchContext = buildSearchContext(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
					folderIds, JournalArticleConstants.CLASSNAME_ID_DEFAULT,
					ddmStructureKey, ddmTemplateKey, keywords,
					params, articleSearchContainer.getStart(),
					articleSearchContainer.getEnd(), sort, status, showVersions, 
					isNavigationMine, themeDisplay.getUserId());
				
				Hits hits = indexer.search(searchContext);
	
				int total = hits.getLength();
	
				articleSearchContainer.setTotal(total);
	
				List results = new ArrayList<>();
	
				Document[] documents = hits.getDocs();
	
				for (Document document : documents) {
					String className = document.get(Field.ENTRY_CLASS_NAME);
					long classPK = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));
	
					if (className.equals(JournalArticle.class.getName())) {
						JournalArticle article = null;
	
						if (!showVersions) {
							article = JournalArticleLocalServiceUtil.fetchLatestArticle(classPK, WorkflowConstants.STATUS_ANY,false);
						}
						else {
							String articleId = document.get(Field.ARTICLE_ID);
							long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));
							double version = GetterUtil.getDouble(document.get(Field.VERSION));
							article = JournalArticleLocalServiceUtil.fetchArticle(groupId, articleId, version);
						}
	
						results.add(article);
					}
					else if (className.equals(JournalFolder.class.getName())) {
						JournalFolder folder = JournalFolderLocalServiceUtil.getFolder(classPK);
						results.add(folder);
					}
				}
	
				articleSearchContainer.setResults(results);
			} else {
				int total = JournalArticleServiceUtil.searchCount(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
					folderIds, JournalArticleConstants.CLASSNAME_ID_DEFAULT,
					keywords, -1.0, ddmStructureKey,
					ddmTemplateKey, null, null, status, null);
	
				articleSearchContainer.setTotal(total);
	
				List results = JournalArticleServiceUtil.search(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
					folderIds, JournalArticleConstants.CLASSNAME_ID_DEFAULT,
					keywords, -1.0, ddmStructureKey,
					ddmTemplateKey, null, null, status, null,
					articleSearchContainer.getStart(),
					articleSearchContainer.getEnd(),
					articleSearchContainer.getOrderByComparator());
	
				articleSearchContainer.setResults(results);
			}
		} else {
			int total = JournalFolderServiceUtil.getFoldersAndArticlesCount(themeDisplay.getScopeGroupId(), 0, folderId, status);

			articleSearchContainer.setTotal(total);

			OrderByComparator<Object> folderOrderByComparator = null;

			boolean orderByAsc = false;

			if (Objects.equals(orderByType, "asc")) {
				orderByAsc = true;
			}

			if (Objects.equals(orderByCol, "display-date")) {
				folderOrderByComparator = new FolderArticleDisplayDateComparator(orderByAsc);
			}
			else if (Objects.equals(orderByCol, "id")) {
				folderOrderByComparator = new FolderArticleArticleIdComparator(orderByAsc);
			}
			else if (Objects.equals(orderByCol, "modified-date")) {
				folderOrderByComparator =
					new FolderArticleModifiedDateComparator(orderByAsc);
			}
			else if (Objects.equals(orderByCol, "title")) {
				folderOrderByComparator = new FolderArticleTitleComparator(orderByAsc);
			}

			List results = JournalFolderServiceUtil.getFoldersAndArticles(
				themeDisplay.getScopeGroupId(), 0, folderId, status,
				themeDisplay.getLocale(), articleSearchContainer.getStart(),
				articleSearchContainer.getEnd(), folderOrderByComparator);

			articleSearchContainer.setResults(results);
		}
		
		return articleSearchContainer;
	}
	
	private boolean hasPermision(ThemeDisplay themeDisplay, JournalFolder journalFolder) {
		try {
			if(journalFolder!=null && journalFolder.getFolderId()!=0) {
				JournalFolder auxJournalFolder = journalFolder;
				while(auxJournalFolder!=null && auxJournalFolder.getFolderId()!=0) {
					if(themeDisplay.getPermissionChecker().hasPermission(auxJournalFolder.getGroupId(), auxJournalFolder.getModelClassName(), auxJournalFolder.getFolderId(), ActionKeys.VIEW)){
						auxJournalFolder = auxJournalFolder.getParentFolder();
					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return true;
	}
	
	protected SearchContext buildSearchContext(
			long companyId, long groupId, List<Long> folderIds, long classNameId,
			String ddmStructureKey, String ddmTemplateKey, String keywords,
			LinkedHashMap<String, Object> params, int start, int end, Sort sort, int status,
			boolean showVersions, boolean isNavigationMine, long userId) {

		String articleId = null;
		String title = null;
		String description = null;
		String content = null;
		boolean andOperator = false;
		
		Map<String, Serializable> attributes = new HashMap<>();
		
		if(isNavigationMine) {
			attributes.put(Field.USER_ID, userId);
		}

		if (Validator.isNotNull(keywords)) {
			articleId = keywords;
			title = keywords;
			description = keywords;
			content = keywords;
			
			attributes.put(Field.ARTICLE_ID, articleId);
			attributes.put(Field.TITLE, title);
			attributes.put(Field.CONTENT, content);
			attributes.put(Field.DESCRIPTION, description);
		}
		else {
			andOperator = true;
		}

		if (params != null && Validator.isNotNull(keywords)) {
			params.put("keywords", keywords);
		}

		SearchContext searchContext = new SearchContext();

		searchContext.setAndSearch(andOperator);
		
		attributes.put(Field.CLASS_NAME_ID, classNameId);
		attributes.put(Field.STATUS, status);
		
		if(Validator.isNotNull(ddmStructureKey)) {
			attributes.put("ddmStructureKey", ddmStructureKey);
		}
		
		if(Validator.isNotNull(ddmTemplateKey)) {
			attributes.put("ddmTemplateKey", ddmTemplateKey);
		}
		
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setEnd(end);
		searchContext.setFolderIds(folderIds);
		searchContext.setGroupIds(new long[] {groupId});
		searchContext.setIncludeDiscussions(GetterUtil.getBoolean(params.get("includeDiscussions"), true));

		if (params != null && Validator.isNotNull(keywords)) {
			keywords = (String)params.remove("keywords");
			if (Validator.isNotNull(keywords)) {
				searchContext.setKeywords(keywords);
			}
		}

		searchContext.setAttribute("head", !showVersions);
		searchContext.setAttribute("latest", !showVersions);
		searchContext.setAttribute("params", params);

		if (!showVersions) {
			searchContext.setAttribute("showNonindexable", Boolean.TRUE);
		}

		searchContext.setEnd(end);
		searchContext.setFolderIds(folderIds);
		searchContext.setStart(start);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		searchContext.setStart(start);

		return searchContext;
	}
	
	public PortletURL getPortletURL(HttpServletRequest request, PortletURL portletURL) {

		String navigation = ParamUtil.getString(request, "navigation");

		if (Validator.isNotNull(navigation)) {
			portletURL.setParameter(
				"navigation", HtmlUtil.escapeJS(_navigation));
		}

		portletURL.setParameter("folderId", String.valueOf(_folderId));

		if (Validator.isNotNull(_ddmStructureKey)) {
			portletURL.setParameter("ddmStructureKey", _ddmStructureKey);
		}

		String status = ParamUtil.getString(request, "status");

		if (Validator.isNotNull(status)) {
			portletURL.setParameter("status", String.valueOf(_status));
		}

		String delta = ParamUtil.getString(request, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(request, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		String displayStyle = ParamUtil.getString(request, "displayStyle");

		if (Validator.isNotNull(displayStyle)) {
			portletURL.setParameter("displayStyle", displayStyle);
		}

		String keywords = ParamUtil.getString(request, "keywords");

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		String orderByCol = _orderByCol;

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = _orderByType;

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		boolean showEditActions = ParamUtil.getBoolean(request, "showEditActions", true);
		if (!showEditActions) {
			portletURL.setParameter("showEditActions", String.valueOf(showEditActions));
		}

		String tabs1 = ParamUtil.getString(request, "tabs1");;

		if (Validator.isNotNull(tabs1)) {
			portletURL.setParameter("tabs1", tabs1);
		}

		return portletURL;
	}

	public OrderByComparator<JournalArticle> getArticleOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<JournalArticle> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new ArticleCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("display-date")) {
			orderByComparator = new ArticleDisplayDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("id")) {
			orderByComparator = new ArticleIDComparator(orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new ArticleModifiedDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("review-date")) {
			orderByComparator = new ArticleReviewDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("title")) {
			orderByComparator = new ArticleTitleComparator(orderByAsc);
		}
		else if (orderByCol.equals("version")) {
			orderByComparator = new ArticleVersionComparator(orderByAsc);
		}

		return orderByComparator;
	}
	
	private String _orderByType;
	private String _ddmStructureKey;
	private int _status;
	private String _orderByCol;
	private String _navigation;
	private long _folderId;
	
	private final Log log = LogFactoryUtil.getLog(JournalWebOverrideFragmentUtil.class);

}