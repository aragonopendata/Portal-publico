package es.aragon.base.content_ratings.util;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

public class ContentRatingsUtil {

	/**
	 * Gets a journal article identifier from a '/-/' type url
	 * @param groupId Group identifier
	 * @param url Journal article detail url
	 * @return Journal article identifier
	 */
	public static long getJournalArticleClassPKByUrl(long groupId, String url) {
		long classPK = 0;
		if (url != null && !url.trim().isEmpty()) {
			String journalArticleFriendlyURL = "";
			if (url.contains("/-/")) {
				journalArticleFriendlyURL = url.substring(url.indexOf("/-/") + 3, url.length());
			}
			if (journalArticleFriendlyURL != null && !journalArticleFriendlyURL.isEmpty()) {
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticleByUrlTitle(groupId, journalArticleFriendlyURL, WorkflowConstants.STATUS_APPROVED);
				if (journalArticle != null) {
					classPK = journalArticle.getResourcePrimKey();
				}
			}
		}
		return classPK;
	}
	
}