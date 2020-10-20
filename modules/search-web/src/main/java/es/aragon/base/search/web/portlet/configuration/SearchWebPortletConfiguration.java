package es.aragon.base.search.web.portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;
import es.aragon.base.search.web.constants.SearchWebPortletKeys;

@Meta.OCD(id = "es.aragon.base.search.web.portlet.configuration.SearchWebPortletConfiguration")
public interface SearchWebPortletConfiguration {
	
	@Meta.AD(required = false, deflt = "")
	public String helpMessage();

	@Meta.AD(required = false, deflt = SearchWebPortletKeys.DEFAULT_SHOWED_RESULTS)
    public String defaultDisplayElements();
	
	@Meta.AD(required = false)
	public String allWordsMustMatch();
	
	@Meta.AD(required = false)
	public String checkedExternalSearch();
	
	@Meta.AD(required = false)
	public String checkedViewExternalSearchFilter();
	
	@Meta.AD(required = false)
	public String defaultFilters();
	
	@Meta.AD(required = false)
	public String selectedAssetTypes();
	
	@Meta.AD(required = false)
	public String selectedStructures();
	
	@Meta.AD(required = false)
	public String selectedStructuresInPage();
	
	@Meta.AD(required = false)
	public String selectedVocabularies();
	
	@Meta.AD(required = false)
	public String facetedVocabularies();

	@Meta.AD(required = false)
	public String checkedAssetType();
	
	
}
