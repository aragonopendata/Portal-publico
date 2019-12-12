package es.aragon.base.category_related_sections.portlet.configuration;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

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

import es.aragon.base.category_related_sections.constants.AragonCategoryRelatedSectionsPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
    configurationPid = "es.aragon.base.category_related_sections.portlet.configuration.AragonCategoryRelatedSectionsPortletConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + AragonCategoryRelatedSectionsPortletKeys.CATEGORY_RELATED_SECTIONS_PORTLET_NAME
	},
	service = ConfigurationAction.class
)
public class AragonCategoryRelatedSectionsPortletConfigurationAction extends DefaultConfigurationAction {

	private volatile AragonCategoryRelatedSectionsPortletConfiguration searchWebPortletConfiguration;
	
	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;
	
	@Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {	
		long groupId = ParamUtil.getLong(actionRequest, "categoriesGroupId");
		StringBuilder presentFilters = new StringBuilder();
		StringBuilder documentFilters = new StringBuilder();
        List<AssetVocabulary> vocabularies = assetVocabularyLocalService.getGroupsVocabularies(
        		new long[] {groupId}, AssetEntry.class.getName());
        for(AssetVocabulary vocabulary : vocabularies) {
        	String presentVocabularyFilters = ParamUtil.getString(actionRequest, "presentFilters_" + vocabulary.getVocabularyId());
        	if(!presentVocabularyFilters.isEmpty()) {
				if(presentFilters.length() > 0) {
					presentFilters.append(",");
				}
				presentFilters.append(presentVocabularyFilters);
			}
        }
       	String documentVocabularyFilters = ParamUtil.getString(actionRequest, "documentFilters");
    	if(!documentVocabularyFilters.isEmpty()) {
			if(documentFilters.length() > 0) {
				documentFilters.append(",");
			}
			documentFilters.append(documentVocabularyFilters);
		}

        PortletPreferences preferences = actionRequest.getPreferences();
        preferences.setValue("presentFilters", presentFilters.toString());
        preferences.setValue("documentFilters", documentFilters.toString());
        preferences.store();
        
        super.processAction(portletConfig, actionRequest, actionResponse);
    }
	
	@Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(AragonCategoryRelatedSectionsPortletConfiguration.class.getName(), searchWebPortletConfiguration);
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }
	
	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
		searchWebPortletConfiguration = ConfigurableUtil.createConfigurable(AragonCategoryRelatedSectionsPortletConfiguration.class, properties);
    }
}
