package es.aragon.base.related.categories.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;
import es.aragon.base.related.categories.constants.RelatedCategoriesPortletKeys;
import es.aragon.base.related.categories.factory.RelatedCategoriesFactory;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;


/**
 * @author migarcia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RelatedCategoriesPortletKeys.RelatedCategories,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RelatedCategoriesPortlet extends MVCPortlet {
private static final String[] FACETS = {"assetCategoryIds"};
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		SearchContext searchContext = RelatedCategoriesFactory.getSearchContext(renderRequest);
		PortletPreferences portletPreferences = renderRequest.getPreferences();
		// get categoryId from expando
		long relatedCategoryId = 0;
		Layout currentLayout = themeDisplay.getLayout();
		ExpandoBridge expandoBridge = currentLayout.getExpandoBridge();
		relatedCategoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), -1);
		
		// get category facets
		Map<String, Facet> searchedFacets =  RelatedCategoriesFactory.search(searchContext, themeDisplay,relatedCategoryId );
		List<AssetCategory> listCategoriesFacets = getCategoryFacets(searchedFacets);
		// get checked vocabualry view
		String checkedVocabulariesIdString = GetterUtil.getString(portletPreferences.getValue("checkedVocabulary", null));
		String[] checkedVocabulariesIdsArray = checkedVocabulariesIdString.split(",");
		//Classifier map of categories according to vocabulary 
		Map<Long, List<AssetCategory>> vocabulariesFacets = new HashMap<>();
     	long key = 0;
     	List <AssetCategory> listValue = new ArrayList<>();
    	if (checkedVocabulariesIdsArray != null && checkedVocabulariesIdsArray.length > 0){
    		for (String checkedVocabularyId : checkedVocabulariesIdsArray) {
    			for (AssetCategory category : listCategoriesFacets) {
	    			if(category.getVocabularyId() == GetterUtil.getLong(checkedVocabularyId, -1) && (category.getCategoryId() != relatedCategoryId)) {
	    				listValue.add(category);
	    				key = GetterUtil.getLong(checkedVocabularyId, -1);
	    			}
	    			
        		}
    			vocabulariesFacets.put(key, listValue);
    		}
    	}  	
		renderRequest.setAttribute("checkedVocabulariesIdString", checkedVocabulariesIdString);
		renderRequest.setAttribute("selectedCategories", String.valueOf(relatedCategoryId));
		renderRequest.setAttribute("vocabulariesFacets", vocabulariesFacets);
		renderRequest.setAttribute("listCategoriesFacets", listCategoriesFacets);
		renderRequest.setAttribute("freemarkerUtilities", _freemarkerUtilities);
		
		super.render(renderRequest, renderResponse);
	}
	private List<AssetCategory> getCategoryFacets(Map<String, Facet> searchedFacets) {
		List<AssetCategory> listCategoriesFacets = new ArrayList<AssetCategory>();
		Facet assetCategoryIds = searchedFacets.get(FACETS[0]);
		List<TermCollector> collectors = (List<TermCollector>) assetCategoryIds.getFacetCollector().getTermCollectors();
		for(TermCollector termCollector : collectors) {
			AssetCategory assetCategory = _assetCategoryLocalService.fetchAssetCategory(Long.parseLong(termCollector.getTerm()));
			if(Validator.isNotNull(assetCategory)) {
				listCategoriesFacets.add(assetCategory);
				
			}
		}
		return listCategoriesFacets;
	}

	@Reference
	FreemarkerUtilities _freemarkerUtilities;
	
}