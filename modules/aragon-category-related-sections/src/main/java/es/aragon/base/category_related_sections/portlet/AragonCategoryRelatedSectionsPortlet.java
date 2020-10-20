package es.aragon.base.category_related_sections.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.ui.JournalArticleTag;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.category_related_sections.constants.AragonCategoryRelatedSectionsPortletKeys;
import es.aragon.base.category_related_sections.portlet.configuration.AragonCategoryRelatedSectionsPortletConfiguration;
import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;

/**
 * @author pfalcon
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AragonCategoryRelatedSectionsPortletKeys.CATEGORY_RELATED_SECTIONS_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonCategoryRelatedSectionsPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		long originCategoryId = -1;
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Layout currentLayout = themeDisplay.getLayout();
		String relatedCategoryExpando = "related-category-id";
		if (currentLayout.getExpandoBridge().hasAttribute(relatedCategoryExpando)) {
			originCategoryId = currentLayout.getExpandoBridge().getAttribute(relatedCategoryExpando, false) != null ? GetterUtil.getLong(currentLayout.getExpandoBridge().getAttribute(relatedCategoryExpando, false), -1) : -1;
    	}
		String originCategoryName = StringPool.BLANK;
		String proceduresURL = StringPool.BLANK;
		String presentURL = StringPool.BLANK;
		String openDataURL = StringPool.BLANK;
		if (originCategoryId != -1) {
			AssetCategory originCategory = assetCategoryLocalService.fetchAssetCategory(originCategoryId);
			if (Validator.isNotNull(originCategory)) {
				// Get proceduresURL & presentURL & documentsURL
				String searcherExpando = "searcher-url";
				if (currentLayout.getGroup().getExpandoBridge().hasAttribute(searcherExpando)) {
					String searcherURL = currentLayout.getGroup().getExpandoBridge().getAttribute(searcherExpando, false) != null ? String.valueOf(currentLayout.getGroup().getExpandoBridge().getAttribute(searcherExpando, false)) : StringPool.BLANK;
					Layout layout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getLayout().getGroupId(), false, searcherURL);
					if (Validator.isNotNull(layout)) {
						ClassName className = classNameLocalService.fetchClassName("es.aragon.enlinea.db.connection.dao.Procedure");
						if(Validator.isNotNull(className)) {
							proceduresURL = getSearcherURLToProcedures(renderRequest, layout.getPlid(), originCategoryId, className.getClassNameId()).toString();
						}
						PortletPreferences portletPreferences = renderRequest.getPreferences();
						long[] presentFilters = StringUtil.split(portletPreferences.getValue("presentFilters", String.valueOf(configuration.presentFilters())), 0L);
						presentURL = getSearcherURL(renderRequest, layout.getPlid(), originCategoryId, presentFilters).toString();
						openDataURL = freemarkerUtilities.getOpenDataURL(Long.toString(originCategoryId));
					}
				}
				originCategoryName = originCategory.getTitle(themeDisplay.getLocale());
			}
		}
		renderRequest.setAttribute("originCategoryName", originCategoryName);
		renderRequest.setAttribute("proceduresURL", proceduresURL);
		renderRequest.setAttribute("presentURL", presentURL);
		renderRequest.setAttribute("openDataURL", openDataURL);
		renderRequest.setAttribute(AragonCategoryRelatedSectionsPortletConfiguration.class.getName(), configuration);
		super.render(renderRequest, renderResponse);
	}
	
	private String getCategoryURL(AssetCategory originCategory) {
		String proceduresURL = StringPool.BLANK;
		CustomCategoryProperty aliasProperty = CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(originCategory.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALIAS);
		if(aliasProperty != null) {
			proceduresURL = proceduresURL + StringPool.SLASH + aliasProperty.getText();
		}
		AssetCategory parentCategory = originCategory.getParentCategory();
		if(parentCategory != null) {
			proceduresURL = getCategoryURL(parentCategory) + proceduresURL;
		}
		return proceduresURL;
	}
	
	private PortletURL getSearcherURLToProcedures(RenderRequest renderRequest, long plid, long categoryId, long type) {
		PortletURL portletURL =  PortletURLFactoryUtil.create(renderRequest, "es_aragon_base_search_web_SearchWebPortlet", plid, PortletRequest.RENDER_PHASE);
    	try {
    		portletURL.setWindowState(LiferayWindowState.NORMAL);
		} catch (WindowStateException e) {
			// Unable to set window state
		}
    	try {
    		portletURL.setPortletMode(LiferayPortletMode.VIEW);
		} catch (PortletModeException e) {
			// Unable to set portlet mode
		}
    	portletURL.setParameter("searchType", String.valueOf(type));
    	portletURL.setParameter("selectedCategories", String.valueOf(categoryId));
    	portletURL.setParameter("currentPage", "0");
    	return portletURL;
	}
	
	private PortletURL getSearcherURL(RenderRequest renderRequest, long plid, long relatedCategoryId, long[] filters) {
		PortletURL portletURL =  PortletURLFactoryUtil.create(renderRequest, "es_aragon_base_search_web_SearchWebPortlet", plid, PortletRequest.RENDER_PHASE);
		ClassName journalArticleClassName = ClassNameLocalServiceUtil.fetchClassName(JournalArticle.class.getName());
    	try {
    		portletURL.setWindowState(LiferayWindowState.NORMAL);
		} catch (WindowStateException e) {
			// Unable to set window state
		}
    	try {
    		portletURL.setPortletMode(LiferayPortletMode.VIEW);
		} catch (PortletModeException e) {
			// Unable to set portlet mode
		}
    	StringBuilder selectedCategories = new StringBuilder(String.valueOf(relatedCategoryId));
    	for(long filter : filters) {
    		selectedCategories.append("-" + filter);
    	}
    	portletURL.setParameter("searchType", String.valueOf(journalArticleClassName.getClassNameId()));
    	portletURL.setParameter("selectedCategories", selectedCategories.toString());
    	portletURL.setParameter("currentPage", "0");
    	return portletURL;
	}
	
	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
		configuration = ConfigurableUtil.createConfigurable(AragonCategoryRelatedSectionsPortletConfiguration.class, properties);
	}
	
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	
	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;
	
	@Reference
	private ClassNameLocalService classNameLocalService;
	
	@Reference
	private LayoutLocalService layoutLocalService;
	
	@Reference
	private FreemarkerUtilities freemarkerUtilities;
	
	private volatile AragonCategoryRelatedSectionsPortletConfiguration configuration;
	
}