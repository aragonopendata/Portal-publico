<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="es.aragon.base.related_contents.constants.RelatedContentsConstants"%>
<%@page import="java.util.Arrays"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
//ORIGIN
String originType = GetterUtil.getString(portletPreferences.getValue("originType", null), "web_content_url");
String layoutColumnId = GetterUtil.getString(portletPreferences.getValue("layoutColumnId", null), "column-1");

//RESULTS FILTER
String groupFilterType = GetterUtil.getString(portletPreferences.getValue("groupFilterType", null), "current");
long[] groupIds = GetterUtil.getLongValues(portletPreferences.getValues("groupIds", null), new long[0]);
String structureFilterType = GetterUtil.getString(portletPreferences.getValue("structureFilterType", null), "sameAsOrigin");
long[] structureIds = GetterUtil.getLongValues(portletPreferences.getValues("structureIds", null), new long[0]);
String categoryFilterType = GetterUtil.getString(portletPreferences.getValue("categoryFilterType", null), "any");

//DISPLAY PREFERENCES
String orderByCol = GetterUtil.getString(portletPreferences.getValue("orderByCol", null), "publishDate");
String orderByType = GetterUtil.getString(portletPreferences.getValue("orderByType", null), "desc");
int resultsNumber =  GetterUtil.getInteger(portletPreferences.getValue("resultsNumber", null), 3);
String templateKey = GetterUtil.getString(portletPreferences.getValue("templateKey", null), "NOTICIA_LISTADO_SIMPLE");

%>