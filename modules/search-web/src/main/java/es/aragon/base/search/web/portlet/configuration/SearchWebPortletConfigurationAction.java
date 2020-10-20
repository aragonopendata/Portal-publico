package es.aragon.base.search.web.portlet.configuration;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.search.web.constants.SearchWebPortletKeys;

@Component(
    configurationPid = "es.aragon.base.search.web.portlet.configuration.SearchWebPortletConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + SearchWebPortletKeys.SEARCH_WEB
	},
	service = ConfigurationAction.class
)
public class SearchWebPortletConfigurationAction extends DefaultConfigurationAction {

	private volatile SearchWebPortletConfiguration searchWebPortletConfiguration;
	
	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;
	
	@Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		//Theme display
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//Get parameters
		String helpMessage = ParamUtil.getString(actionRequest, "helpMessage");
        String defaultDisplayElements = ParamUtil.getString(actionRequest, "defaultDisplayElements");
        String allWordsMustMatch = ParamUtil.getString(actionRequest, "allWordsMustMatch");
        String checkedExternalSearch = ParamUtil.getString(actionRequest, "checkedExternalSearch");
        String checkedViewExternalSearchFilter = ParamUtil.getString(actionRequest, "checkedViewExternalSearchFilter");
        String defaultFilters = "";
        List<AssetVocabulary> vocabularies = _assetVocabularyLocalService.getGroupsVocabularies(new long[] {themeDisplay.getLayout().getGroupId()}, AssetEntry.class.getName());
        for (AssetVocabulary vocabulary : vocabularies) {
        	String vocabularyFilters = ParamUtil.getString(actionRequest, "defaultFilters_" + vocabulary.getVocabularyId());
        	if (!vocabularyFilters.isEmpty()) {
				if (!defaultFilters.isEmpty()) {
					vocabularyFilters = ", " + vocabularyFilters;
				}
				defaultFilters += vocabularyFilters;
			}
        }
        String selectedAssetTypes = ParamUtil.getString(actionRequest, "selectedAssetTypes");
        String selectedStructures = ParamUtil.getString(actionRequest, "selectedStructures");
        String selectedStructuresInPage = ParamUtil.getString(actionRequest, "selectedStructuresInPage");
        String selectedVocabularies = ParamUtil.getString(actionRequest, "selectedVocabularies");
        String facetedVocabularies = ParamUtil.getString(actionRequest, "facetedVocabularies");
        String checkedAssetType = ParamUtil.getString(actionRequest, "checkedAssetType");
        //Store preferences values
        PortletPreferences preferences = actionRequest.getPreferences();
        preferences.setValue("helpMessage", helpMessage);
        preferences.setValue("defaultDisplayElements", defaultDisplayElements);
        preferences.setValue("allWordsMustMatch", allWordsMustMatch);
        preferences.setValue("checkedExternalSearch", checkedExternalSearch);
        preferences.setValue("checkedViewExternalSearchFilter", checkedViewExternalSearchFilter);
        preferences.setValue("defaultFilters", defaultFilters);
        preferences.setValue("selectedAssetTypes", selectedAssetTypes);
        preferences.setValue("selectedStructures", selectedStructures);
        preferences.setValue("selectedStructuresInPage", selectedStructuresInPage);
        preferences.setValue("selectedVocabularies", selectedVocabularies);
        preferences.setValue("facetedVocabularies", facetedVocabularies);
        preferences.setValue("checkedAssetType", checkedAssetType);
        preferences.store();
        //Invoke super method
        super.processAction(portletConfig, actionRequest, actionResponse);
    }
	
	@Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setAttribute(SearchWebPortletConfiguration.class.getName(), searchWebPortletConfiguration);
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }
	
	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
		searchWebPortletConfiguration = ConfigurableUtil.createConfigurable(SearchWebPortletConfiguration.class, properties);
    }
}
