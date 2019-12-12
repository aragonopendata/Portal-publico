package es.aragon.base.asset_category_model_listener;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;
import es.aragon.base.categories_importer.model.ImportCategoryRegistry;
import es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalService;
import es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalServiceUtil;

@Component(
    immediate = true,
    service = ModelListener.class
)
public class AssetCategoryModelListener extends BaseModelListener<AssetCategory>{

	@Override
	public void onAfterRemove(AssetCategory model) throws ModelListenerException {
		//Remove import category registries
		List<ImportCategoryRegistry> listImportCategoryregistry = getListCategoryId(model.getCategoryId());
		if ( Validator.isNotNull(listImportCategoryregistry)) {
			for ( ImportCategoryRegistry importRegistry : listImportCategoryregistry) {
				_importerCategoriesRegistryLocalService.deleteImportCategoryRegistry(importRegistry);
			}
			_log.debug("Delete category from historical registry with name: " + model.getTitle(LocaleUtil.SPAIN) + " and id: " + model.getCategoryId());
		}
		//Remove custom category properties
		List <CustomCategoryProperty> listCategoryProperties = _importerCustomCategoryPropertyLocalService.findByCategoryId(model.getCategoryId());
		if( Validator.isNotNull(listCategoryProperties)) {
			for ( CustomCategoryProperty categoryProperties : listCategoryProperties) {
				_importerCustomCategoryPropertyLocalService.deleteCustomCategoryProperty(categoryProperties);
			}
			_log.debug("Delete category from custom properties with name: " + model.getTitle(LocaleUtil.SPAIN) + " and id: " + model.getCategoryId());
		}
		//Remove associated layouts
		List <Layout>  listLayouts =  getLayoutByCategoryId(model.getCategoryId());
		if( Validator.isNotNull(listLayouts)) {
			for ( Layout layout : listLayouts) {
				_log.debug("Delete layout with name: " + layout.getName());
				try {
					_layoutLocalService.deleteLayout(layout);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		super.onAfterRemove(model);
	}
		
	public static List<ImportCategoryRegistry> getListCategoryId (long categoryId) {
		List<ImportCategoryRegistry> results = new ArrayList<>();
		DynamicQuery dynamicQuery = ImportCategoryRegistryLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryId",categoryId));
		results = ImportCategoryRegistryLocalServiceUtil.dynamicQuery(dynamicQuery);
		return results;
	}
	
	public static List <Layout> getLayoutByCategoryId (long categoryId) {
		List <Layout> listLayout = null;
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class, PortalClassLoaderUtil.getClassLoader())
	        .add(
                PropertyFactoryUtil.forName("columnId").in(
            		DynamicQueryFactoryUtil.forClass(ExpandoColumn.class, PortalClassLoaderUtil.getClassLoader())
	                    .add(PropertyFactoryUtil.forName("name").eq("related-category-id"))
	                    .setProjection(ProjectionFactoryUtil.property("columnId"))
        		)
    		)
	        .add(PropertyFactoryUtil.forName("data").eq(String.valueOf(categoryId)));
		List<ExpandoValue> expandoValues = ExpandoValueLocalServiceUtil.dynamicQuery(dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		long plid = 0;
		if (!expandoValues.isEmpty()) {
			listLayout = new ArrayList<>();
			for ( ExpandoValue expandoValue :  expandoValues) {
				plid = expandoValue.getClassPK();
				listLayout.add(LayoutLocalServiceUtil.fetchLayout(plid));
			}
		}
		return listLayout;
	}
		
	@Reference
	private ImportCategoryRegistryLocalService _importerCategoriesRegistryLocalService;
	
	@Reference
	private CustomCategoryPropertyLocalService _importerCustomCategoryPropertyLocalService;
	
	@Reference
	private LayoutLocalService _layoutLocalService;
	
	/**
	 * Log of the class
	 */
	private final Log _log = LogFactoryUtil.getLog(AssetCategoryModelListener.class);
}
	
	


	

