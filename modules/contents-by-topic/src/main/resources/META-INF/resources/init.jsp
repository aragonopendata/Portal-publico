<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
//Gets portlet preferences
int selectedTemplate = GetterUtil.getInteger(portletPreferences.getValue("selectedTemplate", null), 1);
String orderByCol = GetterUtil.getString(portletPreferences.getValue("orderByCol", null), "publishDate");
String orderByType = GetterUtil.getString(portletPreferences.getValue("orderByType", null), "desc");
int resultsNumber = GetterUtil.getInteger(portletPreferences.getValue("resultsNumber", null), 2) ;
long selectedContentTypeId = GetterUtil.getLong(portletPreferences.getValue("selectedContentTypeId", StringPool.BLANK));
String selectedStructures = portletPreferences.getValue("selectedStructures", StringPool.BLANK);
String[] selectedStructuresArray = StringUtil.split(selectedStructures);
boolean inclusionCategories = GetterUtil.getBoolean(portletPreferences.getValue("inclusionCategories", null), Boolean.TRUE);
String selectedCategoryIds = portletPreferences.getValue("selectedCategoryIds", StringPool.BLANK);
boolean cacheEnabled = GetterUtil.getBoolean(portletPreferences.getValue("cacheEnabled", null), Boolean.TRUE);
%>