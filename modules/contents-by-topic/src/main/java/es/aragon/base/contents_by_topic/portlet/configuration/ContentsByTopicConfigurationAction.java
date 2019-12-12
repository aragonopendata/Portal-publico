package es.aragon.base.contents_by_topic.portlet.configuration;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.contents_by_topic.constants.ContentsByTopicConstants;

/**
 * Contents by topic configuration action class
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentsByTopicConstants.PORTLET_NAME
	},
	service = ConfigurationAction.class
)
public class ContentsByTopicConfigurationAction extends DefaultConfigurationAction {
	
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		//Gets selected category IDs
		StringBuilder categoryIds = new StringBuilder();
		long[] groupIds = {ParamUtil.getLong(actionRequest, "categoriesGroupId")};
		List<AssetVocabulary> vocabularies = assetVocabularyLocalService.getGroupsVocabularies(groupIds, JournalArticle.class.getName());
		for (AssetVocabulary vocabulary : vocabularies) {
			String vocabularySelectedCategories = ParamUtil.getString(actionRequest, "categoryIds_" + vocabulary.getVocabularyId(), "");
			if (Validator.isNotNull(vocabularySelectedCategories)) {
				if (Validator.isNotNull(categoryIds.toString())) {
					categoryIds.append(",");
				}
				categoryIds.append(vocabularySelectedCategories);
			}
		}
		String selectedCategoryIds = categoryIds.toString();
		PortletPreferences preferences = actionRequest.getPreferences();
        preferences.setValue("selectedCategoryIds", selectedCategoryIds);
        preferences.store();
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
	
	@Reference
	protected AssetVocabularyLocalService assetVocabularyLocalService;
	
}
