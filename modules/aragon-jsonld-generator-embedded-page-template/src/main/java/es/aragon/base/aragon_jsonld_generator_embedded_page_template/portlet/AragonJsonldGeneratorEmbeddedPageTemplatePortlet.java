package es.aragon.base.aragon_jsonld_generator_embedded_page_template.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_jsonld_generator_embedded_page_template.constants.AragonJsonldGeneratorEmbeddedPageTemplatePortletKeys;
import es.aragon.base.jsonld_generator.api.JSONLDGenerator;


/**
 * @author migarcia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AragonJsonldGeneratorEmbeddedPageTemplatePortletKeys.AragonJsonldGeneratorEmbeddedPageTemplate,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonJsonldGeneratorEmbeddedPageTemplatePortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String jsonLD = StringPool.BLANK;
		//Get category expando from current layout
		Layout currentLayout = themeDisplay.getLayout();
		if(Validator.isNotNull(currentLayout)) {
			ExpandoBridge expandoBridge = currentLayout.getExpandoBridge();
			long categoryId = -1;
			if(Validator.isNotNull(expandoBridge)) {
				if(expandoBridge.hasAttribute("related-category-id")) {
					categoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), -1);
				}
				if(expandoBridge.hasAttribute("topic-category-id") && categoryId <= 0) {
					categoryId = GetterUtil.getLong(expandoBridge.getAttribute("topic-category-id", Boolean.FALSE), -1);
				}
				if(categoryId > 0) {
					AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
					if (Validator.isNotNull(category)) {
						AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(category.getVocabularyId());
						if (Validator.isNotNull(vocabulary)) {
							jsonLD = jsonLDGenerator.getVocabularyFromJsonLD(vocabulary, category, LocaleUtil.SPAIN, themeDisplay);
						}
					}
				}
			}else {
				_log.info("Not found expando expandoBridge");
			}
		}else {
			_log.info("Not found layout");
		}
		renderRequest.setAttribute("jsonLD", jsonLD);
		
		super.render(renderRequest, renderResponse);
	}
	
    @Reference
    private JSONLDGenerator jsonLDGenerator;
    private static Log _log = LogFactoryUtil.getLog(AragonJsonldGeneratorEmbeddedPageTemplatePortlet.class);
}