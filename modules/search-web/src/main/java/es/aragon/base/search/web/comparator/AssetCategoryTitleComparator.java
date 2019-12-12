package es.aragon.base.search.web.comparator;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

public class AssetCategoryTitleComparator extends OrderByComparator<AssetCategory> {
	
	public static final String ORDER_BY_ASC = "title ASC";
	public static final String ORDER_BY_DESC = "title DESC";
	
	public AssetCategoryTitleComparator() {
		this(true);
	}

	public AssetCategoryTitleComparator(boolean asc) {
		orderByAsc = asc;
	}

	@Override
	public int compare(AssetCategory assetCategory1, AssetCategory assetCategory2) {
		int value = assetCategory1.getTitle(LocaleUtil.SPAIN).compareTo(assetCategory2.getTitle(LocaleUtil.SPAIN));
		if (orderByAsc) {
			return value;
		} else {
			return -value;
		}
	}
	
	@Override
	public String getOrderBy() {
		if (orderByAsc) {
			return ORDER_BY_ASC;
		} else {
			return ORDER_BY_DESC;
		}
	}
	
	private boolean orderByAsc;

}
