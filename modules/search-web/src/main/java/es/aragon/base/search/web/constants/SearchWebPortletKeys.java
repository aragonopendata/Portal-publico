package es.aragon.base.search.web.constants;

/**
 * @author Mikel Jorge
 */
public class SearchWebPortletKeys {
	
	private SearchWebPortletKeys() {}

	public static final String SEARCH_WEB = "es_aragon_base_search_web_SearchWebPortlet";
	
	// used to build hit's url if doesn't exist any layout with it
	public static final String LIFERAY_SEARCH_PORTLET = "com_liferay_portal_search_web_portlet_SearchPortlet";
	
	// configuration default values
	public static final String DEFAULT_SHOWED_RESULTS = "20";
	
	// SearchFactory constants
	public static final String SEARCH_FACTORY_DEFAULT_FACET = "default";
	
}