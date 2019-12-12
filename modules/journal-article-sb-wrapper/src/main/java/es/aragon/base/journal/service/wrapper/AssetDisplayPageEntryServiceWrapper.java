package es.aragon.base.journal.service.wrapper;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalServiceUtil;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalServiceWrapper;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ClassNameServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
	immediate = true,
	property = {
		"service.ranking:Integer=100"
	},
	service = ServiceWrapper.class
)
public class AssetDisplayPageEntryServiceWrapper extends AssetDisplayPageEntryLocalServiceWrapper {

	/**
	 * Log of the class
	 */
	private static final Log log = LogFactoryUtil.getLog(AssetDisplayPageEntryServiceWrapper.class.getName());
	
	/**
	 * Empty constructor
	 */
	public AssetDisplayPageEntryServiceWrapper() {
		super(null);
	}
	
	/**
	 * Constructor with parameters
	 * @param assetDisplayPageEntryLocalService AssetDisplayPageEntry local service instance
	 */
	public AssetDisplayPageEntryServiceWrapper(AssetDisplayPageEntryLocalService assetDisplayPageEntryLocalService) {
		super(assetDisplayPageEntryLocalService);
	}

	@Override
	public AssetDisplayPageEntry addAssetDisplayPageEntry(long userId, long groupId, long classNameId, long classPK, long layoutPageTemplateEntryId, int type, ServiceContext serviceContext) throws PortalException {
		//Get journal article ClassName
		ClassName className = ClassNameServiceUtil.fetchByClassNameId(classNameId);
		log.debug("AssetDisplayPageEntryServiceWrapper addAssetDisplayPageEntry - className: " + className);
		// If it's a journal article, set displayPage to specific to avoid generating pages for every asset
		if (className != null && className.getClassName().contains("JournalArticle")) {
			log.debug("AssetDisplayPageEntryServiceWrapper addAssetDisplayPageEntry - isJournalArticle: true");
			type = AssetDisplayPageConstants.TYPE_SPECIFIC;
			layoutPageTemplateEntryId = 0;
			changeJournalArticlesLayoutUuid(groupId, classPK);
		} else {
			log.warn("AssetDisplayPageEntryServiceWrapper was invoked from next classNameId: ["+classNameId+"]");
		}
		return super.addAssetDisplayPageEntry(userId, groupId, classNameId, classPK, layoutPageTemplateEntryId, type, serviceContext);
	}

	@Override
	public AssetDisplayPageEntry updateAssetDisplayPageEntry(long assetDisplayPageEntryId, long layoutPageTemplateEntryId, int type) throws PortalException {
		AssetDisplayPageEntry assetDisplayPageEntry = AssetDisplayPageEntryLocalServiceUtil.getAssetDisplayPageEntry(assetDisplayPageEntryId);
		//Get journal article ClassName
		ClassName className = ClassNameServiceUtil.fetchByClassNameId(assetDisplayPageEntry.getClassNameId());
		// If it's a journal article, set displayPage to specific to avoid generating pages for every asset
		if (className!=null && className.getClassName().contains("JournalArticle")) {
			type = AssetDisplayPageConstants.TYPE_SPECIFIC;
			layoutPageTemplateEntryId = 0;
			changeJournalArticlesLayoutUuid(assetDisplayPageEntry.getGroupId(), assetDisplayPageEntry.getClassPK());
		} else {
			log.warn("AssetDisplayPageEntryServiceWrapper was invoked from next classNameId: ["+assetDisplayPageEntry.getClassNameId()+"]");
		}
		return super.updateAssetDisplayPageEntry(assetDisplayPageEntryId, layoutPageTemplateEntryId, type);
	}

	/*
	 * PORQUE TENEMOS QUE HACER ESTE DELETE?
	@Override
	public void deleteAssetDisplayPageEntry(long groupId, long classNameId, long classPK) throws PortalException {
		//Get journal article ClassName
		ClassName className = ClassNameServiceUtil.fetchClassName(JournalArticle.class.getName());
		//If it's a journal article, set displayPage to specific to avoid generating pages for every asset
		if (className.getClassNameId() != classNameId) {
			super.deleteAssetDisplayPageEntry(groupId, classNameId, classPK);
		} else {
			changeJournalArticlesLayoutUuid(groupId, classPK);
		}
	}
	*/
	
	/**
	 * Changes the display page of the given journal article
	 * @param classPK JournalArticle classPK
	 * @param groupId Group identifier
	 */
	private void changeJournalArticlesLayoutUuid(long groupId, long classPK) {
		log.debug("Temporal LOG START" );
		Group group = null;
		try {
			group = GroupLocalServiceUtil.getGroup(groupId);
		} catch (PortalException e) {
			log.error("Group '" + groupId + "' does not exist.");
		}
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(classPK);
		if (journalArticle != null && group != null) {
			log.debug("JournalArticle with articleId " + journalArticle.getArticleId() + " found");
			//Get expando column 'JOURNALS_PAGE'
			String friendlyURL = getGroupExpandoValue(group, "JOURNALS_PAGE");
			log.debug("JOURNALS_PAGE value: " + friendlyURL);
			if (Validator.isNotNull(friendlyURL)) {
				//Get layout with configured friendlyURL
				Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, false, friendlyURL);
				if (Validator.isNotNull(layout)) {
					log.debug("JOURNALS_PAGE layout (" + friendlyURL + ") found");
					//Check if layout has an AssetPublisher
					String typeSettings = layout.getTypeSettings();
					if (typeSettings.contains("com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet")) {
						log.debug("JOURNALS_PAGE layout contains an assetpublisher portlet");
						//Check if the AssetPublisher is configured as layout's default asset publisher
						if (typeSettings.contains("default-asset-publisher-portlet-id")) {
							log.debug("Assetpublisher is configured as default viewer of the page");
							//Update article and set it's layoutUuid
							journalArticle.setLayoutUuid(layout.getUuid());
							log.debug("JournalArticle layoutUuid setted to " + layout.getUuid());
							JournalArticleLocalServiceUtil.updateJournalArticle(journalArticle);
							log.debug("JournalArticle updated");
							//Update assetEntry and set it's layoutUuid too
							long classNameId = ClassNameLocalServiceUtil.fetchClassName(JournalArticle.class.getName()).getClassNameId();
							AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(classNameId, classPK);
							if (assetEntry != null) {
								log.debug("JournalArticle AssetEntry found");
								assetEntry.setLayoutUuid(layout.getUuid());
								log.debug("JournalArticle AssetEntry layoutUuid setted to " + layout.getUuid());
								AssetEntryLocalServiceUtil.updateAssetEntry(assetEntry);
								log.debug("JournalArticle AssetEntry updated");
							} else {
								log.error("Journal article asset entry not found");
							}
						} else {
							log.error("Layout with friendlyURL '" + friendlyURL + "' has an AssetPublisher but it has not been configured to be layout's default asset publisher.");
						}
					} else {
						log.error("Configured friendlyURL's layout don't have any AssetPublisher. Insert it on the layout.");
					}
				} else {
					log.error("Layout with friendlyURL '" + friendlyURL + "' does not exist. Change Site's 'JOURNALS_PAGE' expando column's value.");
				}
			} else {
				log.error("Configured friendly URL is empty");
			}
		} else {
			log.error("Journal article is null");
		}
		log.debug("Temporal LOG END" );
	}
	
	/**
	 * Gets a group expando value
	 * @param group Group
	 * @param key Name of the expando column
	 * @return Group expando value
	 */
	private String getGroupExpandoValue(Group group, String key) {
		String result = null;
		try {
			ExpandoBridge expandoBridge = group.getExpandoBridge();
			if (expandoBridge != null) {
				result = (String) expandoBridge.getAttribute(key, Boolean.FALSE);	
			}
		} catch (Exception e) {
			log.error("There was an error getting the group expando value (" + key + "): " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Allows to find the appropriate service that is nullifying in the implementation.
	 * @param assetDisplayPageEntryLocalService Asset display page local service
	 */
	@Reference(unbind = "-")
	private void serviceSetter(AssetDisplayPageEntryLocalService assetDisplayPageEntryLocalService) {
	    setWrappedService(assetDisplayPageEntryLocalService);
	}
	
}
