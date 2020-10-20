package es.aragon.base.organization_map.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.organization_map.constants.Organization_mapPortletKeys;

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
		"javax.portlet.name=" + Organization_mapPortletKeys.Organization_map,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OrganizationMapPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		//Get theme display
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//Get category expando from current layout
		Layout currentLayout = themeDisplay.getLayout();
		ExpandoBridge expandoBridge = currentLayout.getExpandoBridge();
		long relatedCategoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), -1);
		AssetCategory titleCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(relatedCategoryId);
		List <CustomCategoryProperty> currentLayoutCustomCategoryPropertyList = CustomCategoryPropertyLocalServiceUtil.findByCategoryId(relatedCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null, true);
		if (Validator.isNotNull(currentLayoutCustomCategoryPropertyList)) {
			for (CustomCategoryProperty categoryPropery : currentLayoutCustomCategoryPropertyList ) {
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_X)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_X, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_Y)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_Y, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_WEB_PAGE)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_WEB_PAGE, categoryPropery.getText());
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_WEB_PAGE_TITLE)) {
					renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_WEB_PAGE_TITLE, categoryPropery.getText());
				}
			}
			
			try {
				Locale defaultSiteLocale = PortalUtil.getSiteDefaultLocale(themeDisplay.getScopeGroupId());
				if( Validator.isNotNull(defaultSiteLocale) && Validator.isNotNull(titleCategory)) {
				renderRequest.setAttribute("TITLE", titleCategory.getTitle(defaultSiteLocale));
				}
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			renderRequest.setAttribute("categoryId", relatedCategoryId);
		}
		super.render(renderRequest, renderResponse);
	}
	
	
}