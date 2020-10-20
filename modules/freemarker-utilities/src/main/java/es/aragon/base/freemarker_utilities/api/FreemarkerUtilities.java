package es.aragon.base.freemarker_utilities.api;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FreemarkerUtilities {
	
	public String removeUrlParameters(String url);
	
	public String getArticleFullURL(long resourcePrimaryKey, Locale locale);

	public String getArticleFullURL(AssetEntry assetEntry, Locale locale);
	
	public String getArticleFullURL(JournalArticle journalArticle, Locale locale);
	
	public String getLayoutFullURL(long layoutId, long groupId, Locale locale);
	
	public String getLayoutFullURL(Layout layout, long groupId, Locale locale);
	
	public String getArticleImage(long groupId, String articleId, Locale locale);
	
	public String getArticleImage(long groupId, AssetEntry assetEntry, Locale locale);
	
	public String getArticleImageAlt(long groupId, AssetEntry assetEntry, Locale locale);
	
	public String getArticleImageAlt(long groupId, String articleId, Locale locale);
	
	public List<String> getArticleCategoriesList(AssetEntry assetEntry, Locale locale);
	
	public List<String> getArticleCategoriesList(long groupId, String journalArticleId, Locale locale);
	
	public List<String> getArticleCategoriesList(JournalArticle article, Locale locale);
	
	public List<AssetCategory> getArticleCategories(JournalArticle journalArticle, Locale locale);
	
	public List<AssetCategory> filterVocabularyCategoriesFromArticleCategories(long groupId, String vocabularyName, List<AssetCategory> articleCategories);
	
	public List<JSONObject> getH2Elements(String[] sourceElements);
	
	public List<String> addAnchorIds(String[] sourceElements);
	
	public String getDisplayDate(AssetEntry assetEntry, String format);
	
	public String getDisplayDate(JournalArticle journalArticle, String format);
	
	public Map<String, String> getChildLayoutsLinksMap(Layout layout);
	
	public String getSearcherUrl(Group group);
	
    public String getProcedureClassNameId();
	
	public String getGroupExpandoAttributeValue(long groupId, String expandoAttributeName);

	public List<String[]> getLastProcedures(HttpServletRequest request, long groupId);
	
	public FileEntry getFileEntryByURL(String url);
	
	public String getFileTypeAndSizeString(FileEntry fileEntry);

	public String getFileEntryMimeTypeByURL(String url);
	
	public String getImageURL(String url);
	
	public String getAssetCategoryURL(HttpServletRequest request, long assetCategoryId, boolean addCurrentPageCategory);

	public String getTranslatedContentUrl(String urlOrigin, long groupId, Locale locale);

	public Locale getLocale(String localeId);
	
	public String getOpenDataURL(String categories);
	
	public String getOpenDataURLFromJournal(JournalArticle journalArticle);
	
	public void incrementViewCounter(long resourcePrimaryKey, long userId);

	public String getCustomTitleProcedures(AssetCategory categoryProcedure, Locale locale);

	public String getArticleCategoriesNotices(JournalArticle journalArticle, Locale locale);

	public String getLastPublishDate(JournalArticle journalArticle, String format);

	public String getGoogleAnalyticsId(ThemeDisplay themeDisplay);
	
	/**
	 * Returns a theme property value from given layout. Searchs the value in ancestor layouts if is not defined in the given one
	 * @param layout Layout
	 * @param propertyName Property name
	 * @return Theme property value from given layout
	 */
	public String getThemeLayoutPropertyValue(Layout layout, String propertyName);

	public boolean viewCoronavirusMenu(long groupId, JournalArticle journalArticle, Locale locale, String vocabularyName,
			String categoryTitle);
	
	public boolean isTranslated(ThemeDisplay themeDisplay, Locale locale);

	public List<JSONObject> getQuestionAnswerTemplate(String[] codeHtml);

	public Map<String, Map<String, String>> getMunicipalitiesLinksMap(long groupId, HttpServletRequest request, Locale locale,
			String nameVocabulary);

}