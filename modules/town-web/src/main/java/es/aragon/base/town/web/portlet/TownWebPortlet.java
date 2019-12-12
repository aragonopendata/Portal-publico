package es.aragon.base.town.web.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.town.web.constants.TownWebPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TownWebPortletKeys.TOWN_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TownWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Layout layout = themeDisplay.getLayout();
		long relatedCategoryId = GetterUtil.getLong(layout.getExpandoBridge().getAttribute("related-category-id", Boolean.FALSE), 0);
		AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(relatedCategoryId);
		if (Validator.isNotNull(category)) {
			renderRequest.setAttribute("townName", category.getTitle(themeDisplay.getLocale()));
			List<CustomCategoryProperty> currentLayoutCustomCategoryPropertyList = CustomCategoryPropertyLocalServiceUtil.findByCategoryId(relatedCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null, true);
			if (Validator.isNotNull(currentLayoutCustomCategoryPropertyList)) {
				for (CustomCategoryProperty categoryPropery : currentLayoutCustomCategoryPropertyList ) {
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALCALDE)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_ALCALDE, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COMARCA)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COMARCA, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIPUTACION)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIPUTACION, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_SUPERFICIE)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_SUPERFICIE, categoryPropery.getText());
					}
					if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_HABITANTES)) {
						renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_HABITANTES, categoryPropery.getText());
					}
				}
			}
		}
		super.render(renderRequest, renderResponse);
	}
	
}