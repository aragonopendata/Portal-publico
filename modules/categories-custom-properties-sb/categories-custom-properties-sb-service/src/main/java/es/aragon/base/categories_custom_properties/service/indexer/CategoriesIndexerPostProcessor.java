package es.aragon.base.categories_custom_properties.service.indexer;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;

import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;

@Component(
		immediate = true,
		property = {
			"indexer.class.name=com.liferay.asset.kernel.model.AssetCategory",
		},
		service = IndexerPostProcessor.class
	)
public class CategoriesIndexerPostProcessor implements IndexerPostProcessor {
	
	@Reference
	CustomCategoryPropertyLocalService customCategoryPropertyLocalService;

	@Override
	public void postProcessContextBooleanFilter(BooleanFilter booleanFilter, SearchContext searchContext)
			throws Exception {
		// No es necesario sobreescribirlo
	}

	@Override
	public void postProcessContextQuery(BooleanQuery contextQuery, SearchContext searchContext) throws Exception {
		// No es necesario sobreescribirlo
	}

	@Override
	public void postProcessDocument(Document document, Object obj) throws Exception {
		AssetCategory assetCategory = (AssetCategory) obj;
		List<CustomCategoryProperty> customCategoryProperties = customCategoryPropertyLocalService.findByCategoryId(assetCategory.getCategoryId());
		if(!customCategoryProperties.isEmpty()) {
			StringBuilder content = new StringBuilder();
			for(CustomCategoryProperty customCategoryProperty : customCategoryProperties) {
				content.append(customCategoryProperty.getText() + " ");
			}
			document.addKeyword(Field.CONTENT, content.toString());
		}
	}

	@Override
	public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchContext) throws Exception {
		// No es necesario sobreescribirlo
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext) throws Exception {
		// No es necesario sobreescribirlo
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, SearchContext searchContext) throws Exception {
		// No es necesario sobreescribirlo
	}

	@Override
	public void postProcessSummary(Summary summary, Document document, Locale locale, String snippet) {
		// No es necesario sobreescribirlo
	}

}
