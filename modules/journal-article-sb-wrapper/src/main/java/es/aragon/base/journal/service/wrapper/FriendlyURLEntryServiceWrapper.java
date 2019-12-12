package es.aragon.base.journal.service.wrapper;

import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalServiceWrapper;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/**
 * @author anunez
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
			"service.ranking:Integer=100"
	},
	service = ServiceWrapper.class
)
public class FriendlyURLEntryServiceWrapper extends FriendlyURLEntryLocalServiceWrapper{

	public FriendlyURLEntryServiceWrapper() {
		super(null);
	}
	
	public FriendlyURLEntryServiceWrapper(FriendlyURLEntryLocalService friendlyURLEntryLocalService) {
		super(friendlyURLEntryLocalService);
	}
	
	@Override
	public String getUniqueUrlTitle(long groupId, long classNameId, long classPK, String urlTitle) {
		int urlTitleMaxLength = ModelHintsUtil.getMaxLength(JournalArticle.class.getName(), "urlTitle");
		if (urlTitle.length() > urlTitleMaxLength) {
			urlTitle = urlTitle.substring(0, 50);
		}
		urlTitle = normalizeUrl(urlTitle);
		return super.getUniqueUrlTitle(groupId, classNameId, classPK, urlTitle);
	}
	
	@Override
	public FriendlyURLEntry addFriendlyURLEntry(long groupId, long classNameId, long classPK, Map<String, String> urlTitleMap, ServiceContext serviceContext) throws PortalException {
		Map<String, String> formattedUrlTitleMap = normalizeUrlMap(urlTitleMap);
		return super.addFriendlyURLEntry(groupId, classNameId, classPK, formattedUrlTitleMap, serviceContext);
	}
	
	/**
	 * Returns a full map of normalized URL titles
	 * @param friendlyURLMap Friendly URL maps
	 * @return A full map of normalized URL titles
	 */
	private Map<String, String> normalizeUrlMap(Map<String, String> friendlyURLMap) {
		Map<String, String> result = new HashMap<>();
		if (friendlyURLMap != null && !friendlyURLMap.isEmpty()) {
			for (Map.Entry<String, String> entry : friendlyURLMap.entrySet()) {
				String url = normalizeUrl(entry.getValue());
				result.put(entry.getKey(), url);
			}
		}
		return result;
	}
	
	/**
	 * Normalizes a friendly URL
	 * @param url Original friendlyURL
	 * @return Normalized friendlyURL
	 */
	private String normalizeUrl(String url) {
		String result = FriendlyURLNormalizerUtil.normalize(url);
		if (!result.equals(url)) {
			_log.info("Url " + url + " normalized to " + result);
		}
		return result;
	}
	
	/**
	 * Log of the class
	 */
	private Log _log = LogFactoryUtil.getLog(FriendlyURLEntryServiceWrapper.class);
	
	/**
	 * Allows to find the appropriate service that is nullifying in the implementation.
	 * @param friendlyURLEntryLocalService Friendly url entry local service
	 */
	@Reference(unbind = "-")
	private void serviceSetter(FriendlyURLEntryLocalService friendlyURLEntryLocalService) {
	    setWrappedService(friendlyURLEntryLocalService);
	}
	
}
