package es.aragon.base.search.web.dto;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Mikel Jorge
 *
 */
public class SearchDTO {

	private SearchContext searchContext;
	private String searchText;
	private Date fromDate;
	private Date toDate;
	private String[] facets;
	private ThemeDisplay themeDisplay;
	private long[] selectedCategories;
	private long[] selectedAssetTypes;
	private long[] selectedPortalsToSearch;
	private String[] selectedStructures;
	private List<Long> selectedPortalToSearchList;

	
	public long[] getSelectedCategories() {
		return selectedCategories;
	}
	public void setSelectedCategories(long[] selectedCategories) {
		this.selectedCategories = selectedCategories;
	}
	public long[] getSelectedAssetTypes() {
		return selectedAssetTypes;
	}
	public void setSelectedAssetTypes(long[] selectedAssetTypes) {
		this.selectedAssetTypes = selectedAssetTypes;
	}
	public String[] getSelectedStructures() {
		return selectedStructures;
	}
	public void setSelectedStructures(String[] selectedStructures) {
		this.selectedStructures = selectedStructures;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public SearchContext getSearchContext() {
		return searchContext;
	}
	public void setSearchContext(SearchContext searchContext) {
		this.searchContext = searchContext;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String[] getFacets() {
		return facets;
	}
	public void setFacets(String[] facets) {
		this.facets = facets;
	}
	public ThemeDisplay getThemeDisplay() {
		return themeDisplay;
	}
	public void setThemeDisplay(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
	}
	public long[] getSelectedPortalsToSearch() {
		return this.selectedPortalsToSearch;
	}
	public void setSelectedPortalsToSearch(long[] selectedPortalsToSearch) {
		this.selectedPortalsToSearch = selectedPortalsToSearch;
	}
	public void setSelectedPortalsToSearch(List<Long> selectedPortalToSearchList) {
		this.selectedPortalToSearchList = selectedPortalToSearchList;
	}
}
