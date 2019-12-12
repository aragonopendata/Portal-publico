package es.aragon.base.migration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;

public class ContentPreviewUtil {
	
	public static final String PREVIEW_URL_BASE = "/migration-preview";
	
	public static String getPreviewURL(long groupId, boolean privateLayout, String friendlyURL) {
		String previewURL = StringPool.BLANK;
		try {
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);
			if (group != null) {
				Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, privateLayout, friendlyURL);
				if (layout != null) {
					if (privateLayout) {
						previewURL = "/group";
					} else {
						previewURL = "/web";
					}
					previewURL = previewURL + group.getFriendlyURL() + friendlyURL;
				} else {
					_log.error((privateLayout ? "Private" : "Public") + " layout with friendly URL " + friendlyURL + " not found in group " + groupId);
				}
			} else {
				_log.error("Group with id " + groupId + " not found");
			}
		} catch (Exception e) {
			_log.error("There was an error getting the preview URL: " + e.toString());
			e.printStackTrace();
		}
		return previewURL;
	}
	
	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(ContentPreviewUtil.class);

}
