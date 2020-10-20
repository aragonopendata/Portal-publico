<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%@ page import="es.aragon.base.search.web.portlet.configuration.SearchWebPortletConfiguration" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
	SearchWebPortletConfiguration configuration = (SearchWebPortletConfiguration) GetterUtil.getObject(renderRequest.getAttribute(SearchWebPortletConfiguration.class.getName()));
	String helpMessage = portletPreferences.getValue("helpMessage", String.valueOf(configuration.helpMessage()));
	String defaultDisplayElements = portletPreferences.getValue("defaultDisplayElements", String.valueOf(configuration.defaultDisplayElements()));
	boolean allWordsMustMatch = GetterUtil.getBoolean(portletPreferences.getValue("allWordsMustMatch", String.valueOf(configuration.allWordsMustMatch())), Boolean.FALSE) ;
	String defaultFilters = portletPreferences.getValue("defaultFilters", String.valueOf(configuration.defaultFilters()));
	String selectedAssetTypes = portletPreferences.getValue("selectedAssetTypes", String.valueOf(configuration.selectedAssetTypes()));
	String selectedStructures = portletPreferences.getValue("selectedStructures", String.valueOf(configuration.selectedStructures()));
	String selectedStructuresInPage = portletPreferences.getValue("selectedStructuresInPage", String.valueOf(configuration.selectedStructuresInPage()));
	String selectedVocabularies = portletPreferences.getValue("selectedVocabularies", String.valueOf(configuration.selectedVocabularies()));
	String facetedVocabularies = portletPreferences.getValue("facetedVocabularies", String.valueOf(configuration.facetedVocabularies()));
	String checkedAssetType = portletPreferences.getValue("checkedAssetType", String.valueOf(configuration.checkedAssetType()));
	boolean checkedExternalSearch = GetterUtil.getBoolean(portletPreferences.getValue("checkedExternalSearch", String.valueOf(configuration.checkedExternalSearch())), Boolean.FALSE);
	boolean checkedViewExternalSearchFilter = GetterUtil.getBoolean(portletPreferences.getValue("checkedViewExternalSearchFilter", String.valueOf(configuration.checkedViewExternalSearchFilter())), Boolean.FALSE);
%>