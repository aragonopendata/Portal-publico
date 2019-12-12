package es.aragon.base.journal.service.wrapper;

import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ClassNameServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.journal.service.util.JournalArticleServiceWrapperUtil;

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
public class JournalArticleServiceWrapper extends JournalArticleLocalServiceWrapper {

	public JournalArticleServiceWrapper() {
		super(null);
	}
	
	public JournalArticleServiceWrapper(JournalArticleLocalService journalArticleLocalService) {
		super(journalArticleLocalService);
	}
	
	@Override
	public JournalArticle addArticle(long userId, long groupId, long folderId, long classNameId, long classPK,
			String articleId, boolean autoArticleId, double version, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> friendlyURLMap, String content,
			String ddmStructureKey, String ddmTemplateKey, String layoutUuid, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear, int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay, int reviewDateYear, int reviewDateHour,
			int reviewDateMinute, boolean neverReview, boolean indexable, boolean smallImage, String smallImageURL,
			File smallImageFile, Map<String, byte[]> images, String articleURL, ServiceContext serviceContext)
			throws PortalException {
		//**** Eliminamos etiquetas html para que sea seguro utilizarlo en un contexto HTML en titulo y descripcion ****//
			for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			    Locale key = entry.getKey();
			    String value = JournalArticleServiceWrapperUtil.removeHtmlTags(entry.getValue());
			    titleMap.put(key, value);
			}
			for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			    Locale key = entry.getKey();
			    String value = JournalArticleServiceWrapperUtil.removeHtmlTags(entry.getValue());
			    descriptionMap.put(key, HtmlUtil.stripHtml(value));
			}
		//**** ****************************************************************************** ****//
		//Invoke the super method
		JournalArticle journalArticle = super.addArticle(userId, groupId, folderId, classNameId, classPK, articleId, autoArticleId, version, titleMap,
				descriptionMap, friendlyURLMap, content, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay,
				reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL,
				smallImageFile, images, articleURL, serviceContext);
		//Set the display page entry invoking the wrapper
		_assetDisplayPageEntryLocalService.addAssetDisplayPageEntry(userId, groupId, ClassNameServiceUtil.fetchClassName(JournalArticle.class.getName()).getClassNameId(), journalArticle.getResourcePrimKey(), 0, 0, serviceContext);
		//Return
		return journalArticle;
	}

	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Map<Locale, String> friendlyURLMap,
			String content, String ddmStructureKey, String ddmTemplateKey, String layoutUuid, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay, int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire, int reviewDateMonth, int reviewDateDay, int reviewDateYear,
			int reviewDateHour, int reviewDateMinute, boolean neverReview, boolean indexable, boolean smallImage,
			String smallImageURL, File smallImageFile, Map<String, byte[]> images, String articleURL,
			ServiceContext serviceContext) throws PortalException {
		//**** Eliminamos etiquetas html para que sea seguro utilizarlo en un contexto HTML en titulo y descripcion****//
			for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			    Locale key = entry.getKey();
			    String value = JournalArticleServiceWrapperUtil.removeHtmlTags(entry.getValue());
			    titleMap.put(key, value);
			}
			for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			    Locale key = entry.getKey();
			    String value = JournalArticleServiceWrapperUtil.removeHtmlTags(entry.getValue());
			    descriptionMap.put(key, HtmlUtil.stripHtml(value));
			}
			//**** ****************************************************************************** ****//
		return super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, friendlyURLMap,
				content, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear,
				reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile, images,
				articleURL, serviceContext);
	}
	
 	@Override
 	public JournalArticle copyArticle(long userId, long groupId, String oldArticleId, String newArticleId, boolean autoArticleId, double version) throws PortalException {
 		//Invoke the super method
 		JournalArticle journalArticle = super.copyArticle(userId, groupId, oldArticleId, newArticleId, autoArticleId, version);
 		//Set the display page entry invoking the wrapper
 		_assetDisplayPageEntryLocalService.addAssetDisplayPageEntry(userId, groupId, ClassNameServiceUtil.fetchClassName(JournalArticle.class.getName()).getClassNameId(), journalArticle.getResourcePrimKey(), 0, 0, new ServiceContext());
 		//Return
 		return journalArticle;
 	}
	
	/**
	 * Journal article local service
	 */
	@Reference
	JournalArticleLocalService _journalArticleLocalService;
	
	/**
	 * Asset display page entry local service
	 */
	@Reference
	AssetDisplayPageEntryLocalService _assetDisplayPageEntryLocalService;
	
	/**
	 * Allows to find the appropriate service that is nullifying in the implementation.
	 * @param journalArticleLocalService Journal article local service
	 */
	@Reference(unbind = "-")
	private void serviceSetter(JournalArticleLocalService journalArticleLocalService) {
	    setWrappedService(journalArticleLocalService);
	}
	
}