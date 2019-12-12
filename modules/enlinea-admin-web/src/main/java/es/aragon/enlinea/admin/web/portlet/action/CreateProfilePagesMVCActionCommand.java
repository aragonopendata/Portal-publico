package es.aragon.enlinea.admin.web.portlet.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;
import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;
import es.aragon.enlinea.admin.web.portlet.util.LayoutUtil;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET,
		"mvc.command.name=" + EnlineaAdminPortletKeys.ACTION_CREATE_PROFILE_PAGES
	},
	service = MVCActionCommand.class
)
public class CreateProfilePagesMVCActionCommand extends BaseMVCActionCommand {
	
	private static Log log = LogFactoryUtil.getLog(CreateProfilePagesMVCActionCommand.class);
	
	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	@Reference
	private LayoutLocalService layoutLocalService;
	@Reference
	private LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService;
	@Reference
	private CustomCategoryPropertyLocalService customCategoryPropertyLocalService;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		hideDefaultErrorMessage(actionRequest);
		long level1PageTemplate = ParamUtil.getLong(actionRequest, "level1PageTemplate", -1);
		long level23PageTemplate = ParamUtil.getLong(actionRequest, "level23PageTemplate", -1);
		if(level1PageTemplate != -1 && level23PageTemplate != -1) {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			AssetVocabulary assetVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Perfiles");
			PortletPreferences preferences = actionRequest.getPreferences();
			if(Validator.isNotNull(assetVocabulary)) {
				new LayoutUtil(assetCategoryLocalService, customCategoryPropertyLocalService, layoutLocalService, layoutPageTemplateEntryLocalService).
				createLayouts(actionRequest, preferences, assetVocabulary, themeDisplay, level1PageTemplate, level23PageTemplate);
			} else {
				SessionErrors.add(actionRequest, "action.error.no-profile-vocabulary");
			}
		} else {
			SessionErrors.add(actionRequest, "action.error.empty-page-templates");
		}
		try {
			sendRedirect(actionRequest, actionResponse);
		} catch (IOException e) {
			log.error("Error redirecting in create pages command");
		}
	}

}
