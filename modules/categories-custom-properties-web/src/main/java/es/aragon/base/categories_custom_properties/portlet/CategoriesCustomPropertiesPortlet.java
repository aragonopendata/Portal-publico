package es.aragon.base.categories_custom_properties.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_custom_properties.constants.CategoriesCustomPropertiesPortletKeys;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;

/**
 * @author pfalcon
 * Categories custom properties administration portlet class 
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CategoriesCustomPropertiesPortletKeys.CATEGORIES_CUSTOM_PROPERTIES_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CategoriesCustomPropertiesPortlet extends MVCPortlet {
	
	/**
	 * Adds or updates a CategoriesCustomProperty with the given parameters
	 * @param actionRequest Action request
	 * @param actionResponse Action response
	 */
	public void saveProperty(ActionRequest actionRequest, ActionResponse actionResponse) {
		try {
			//Current date
			Date nowDate = new Date();
			//Request parameters
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			//Custom parameters
			long assetCategoryId = ParamUtil.getLong(actionRequest, "assetCategoryId", -1);
			String key = ParamUtil.getString(actionRequest, "key", StringPool.BLANK);
			String value = ParamUtil.getString(actionRequest, "value", StringPool.BLANK);
			//Checks the category
			if (assetCategoryId != -1) {
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(assetCategoryId);
				if (assetCategory != null) {
					long customCategoryPropertyId = ParamUtil.getLong(actionRequest, "customCategoryPropertyId", -1);
					//Create
					if (customCategoryPropertyId == -1) {
						//Create property
						_log.info("CREATING PROPERTY " + key + "=" + value + " IN " + assetCategory.getName() + " CATEGORY");
						_customCategoryPropertyLocalService.addCustomCategoryProperty(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), themeDisplay.getUserId(), themeDisplay.getUser().getScreenName(), nowDate, nowDate, assetCategory.getVocabularyId(), assetCategory.getCategoryId(), key, value);
					}
					//Update
					else {
						//Update property
						_log.info("UPDATING PROPERTY " + key + "=" + value + " IN " + assetCategory.getName() + " CATEGORY");
						CustomCategoryProperty customCategoryProperty = _customCategoryPropertyLocalService.fetchCustomCategoryProperty(customCategoryPropertyId);
						if (customCategoryProperty != null) {
							customCategoryProperty.setKey(key);
							customCategoryProperty.setText(value);
							_customCategoryPropertyLocalService.updateCustomCategoryProperty(customCategoryProperty);
						} else {
							SessionErrors.add(actionRequest, "category-not-found-error");
							_log.error("CUSTOMCATEGORYPROPERTY WITH ID " + customCategoryPropertyId + " DOES NOT EXISTS");
						}
					}
				} else {
					SessionErrors.add(actionRequest, "category-not-found-error");
					_log.error("ASSETCATEGORY WITH ID " + assetCategoryId + " DOES NOT EXISTS");
				}
			} else {
				SessionErrors.add(actionRequest, "invalid-category-id-error");
				_log.error("ASSETCATEGORY ID " + assetCategoryId + " IS NOT A VALID ID");
			}
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "add-property-general-error");
			_log.error("There was an error saving the property: " + e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a CategoriesCustomProperty with the given identifier
	 * @param actionRequest Action request
	 * @param actionResponse Action response
	 */
	public void deleteProperty(ActionRequest actionRequest, ActionResponse actionResponse) {
		try {
			//Custom parameters
			long customCategoryPropertyId = ParamUtil.getLong(actionRequest, "customCategoryPropertyId", 0);
			//Delete field
			_customCategoryPropertyLocalService.deleteCustomCategoryProperty(customCategoryPropertyId);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "delete-property-general-error");
			_log.error("There was an error deleting the property: " + e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//Paginacion
		int delta = ParamUtil.getInteger(renderRequest, "delta", 10);
		int cur = ParamUtil.getInteger(renderRequest, "cur", 1);
		int start = delta * (cur - 1);
		int end = delta * cur;
		
		//Filtrado
		String search = ParamUtil.getString(renderRequest, "search", StringPool.BLANK);	
		long vocabularyId = ParamUtil.getLong(renderRequest, "vocabularyId", -1);
		long parentCategoryId= ParamUtil.getLong(renderRequest, "parentCategoryId", -1);
		long groupId = themeDisplay.getScopeGroupId();
		//Consulta de resultados
		long totalResults = getContentsSize(search);
		List<AssetCategory> listResults = getContents(search, vocabularyId, parentCategoryId,groupId, start, end);
		
		//Envio de resultados a la vista
		renderRequest.setAttribute("totalResults", totalResults);
		renderRequest.setAttribute("listResults", listResults);
		
		super.render(renderRequest, renderResponse);
	}
	private long getContentsSize(String search) {
		long total = 0;
		try {
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class);
			if (Validator.isNotNull(search)) {
				dynamicQuery.add(RestrictionsFactoryUtil.or(
					RestrictionsFactoryUtil.ilike("title", "%"+search+"%"),
					RestrictionsFactoryUtil.ilike("description", "%"+search+"%")
				));
			}
			Order orderBy = OrderFactoryUtil.asc("title");
			dynamicQuery.addOrder(orderBy);
			total = AssetCategoryLocalServiceUtil.dynamicQueryCount(dynamicQuery);
		} catch (Exception e) {
			_log.error(e, e);
		}
		return total;
	}
	private List<AssetCategory> getContents(String search,long vocabularyId, long parentCategoryId, long groupId,int start, int end) {
		List<AssetCategory> results = new ArrayList<AssetCategory>();
		try {
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class);
			dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
			if(vocabularyId!=-1) {
				dynamicQuery.add(RestrictionsFactoryUtil.eq("vocabularyId", vocabularyId));
			}
			if(Validator.isNull(search)) {
				dynamicQuery.add(RestrictionsFactoryUtil.eq("parentCategoryId", parentCategoryId));
			}
			dynamicQuery.add(RestrictionsFactoryUtil.or(
						RestrictionsFactoryUtil.ilike("title", "%"+search+"%"),
						RestrictionsFactoryUtil.ilike("description", "%"+search+"%")));
			
			
			Order orderBy = OrderFactoryUtil.asc("title");
			dynamicQuery.addOrder(orderBy);
			results = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
		} catch (Exception e) {
			_log.error(e, e);
		}
		return results;
	}
	
	/**
	 * AssetCategory local service
	 */
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	
	/**
	 * CustomCategoryProperty local service
	 */
	@Reference
	private CustomCategoryPropertyLocalService _customCategoryPropertyLocalService;
	
	/**
	 * Log of the class
	 */
	private final Log _log = LogFactoryUtil.getLog(CategoriesCustomPropertiesPortlet.class);
	
}