package es.aragon.base.person_detail_web.portlet;

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
import es.aragon.base.person_detail_web.constants.PersonDetailWebPortletKeys;

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
		"javax.portlet.name=" + PersonDetailWebPortletKeys.PagePeople,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PersonDetailWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		//Get theme display
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		// get categoryId from expando
				long relatedCategoryId = 0;
				Layout currentLayout = themeDisplay.getLayout();
				ExpandoBridge expandoBridge = currentLayout.getExpandoBridge();
				relatedCategoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), -1);
				List <CustomCategoryProperty> currentLayoutCustomCategoryPropertyList = CustomCategoryPropertyLocalServiceUtil.findByCategoryId(relatedCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null, true);
				AssetCategory titleCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(relatedCategoryId);
				if (Validator.isNotNull(currentLayoutCustomCategoryPropertyList)) {
					for (CustomCategoryProperty categoryPropery : currentLayoutCustomCategoryPropertyList ) {
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CARGO)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CARGO, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_BIOGRAFIA)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_BIOGRAFIA, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_PATH)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_PATH, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_NOMBRE)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_NOMBRE, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD, categoryPropery.getText());
						}			
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA, categoryPropery.getText());
						}	
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO, categoryPropery.getText());
						}
						if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL)) {
							renderRequest.setAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL, categoryPropery.getText());
						}						
					}
				}	
				
				try {
					Locale defaultSiteLocale = PortalUtil.getSiteDefaultLocale(themeDisplay.getScopeGroupId());
					renderRequest.setAttribute("TITLE", titleCategory.getTitle(defaultSiteLocale));
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				super.render(renderRequest, renderResponse);
			}
}