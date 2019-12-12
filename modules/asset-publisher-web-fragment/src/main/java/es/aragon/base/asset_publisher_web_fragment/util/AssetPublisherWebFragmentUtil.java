package es.aragon.base.asset_publisher_web_fragment.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.List;

public class AssetPublisherWebFragmentUtil {

	/**
	 * Check if the user has entry view permission checking his folder and all his ancestors.
	 * @param userId User identifier
	 * @param assetEntry Asset entry
	 * @param permissionChecker Permission checker
	 * @return True if user has view permission for the asset and all his ancestor folders.
	 */
	public static boolean userHasJournalFolderViewPermission(long userId, AssetEntry assetEntry, PermissionChecker permissionChecker) {
		boolean result = Boolean.FALSE;
		try {
			if (assetEntry != null) {
				if (assetEntry.getClassName().equals("com.liferay.journal.model.JournalArticle")) {
					JournalArticle article = JournalArticleLocalServiceUtil.fetchLatestArticle(assetEntry.getClassPK(), 0);
					if (article != null){
						article.getFolderId();
						JournalFolder journalFolder = JournalFolderLocalServiceUtil.fetchFolder(article.getFolderId());
						if (journalFolder != null) {
							//Check folder view permission
							result = permissionChecker.hasPermission(journalFolder.getGroupId(), JournalFolder.class.getName(), journalFolder.getFolderId(), ActionKeys.VIEW);
							//Check ancestor folder view permission
							List<JournalFolder> ancestorJournalFolders = journalFolder.getAncestors();
							if (ancestorJournalFolders != null && !ancestorJournalFolders.isEmpty()) {
								for (JournalFolder ancestorFolder : ancestorJournalFolders) {
									result = result && permissionChecker.hasPermission(ancestorFolder.getGroupId(), JournalFolder.class.getName(), ancestorFolder.getFolderId(), ActionKeys.VIEW);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error("There was an error checking the folder permissions: " + e.toString());
		}
		return result;
	}
	
	/**
	 * Log of the class
	 */
	private final static Log _log = LogFactoryUtil.getLog(AssetPublisherWebFragmentUtil.class);

}
