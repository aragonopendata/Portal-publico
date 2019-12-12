<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>

<%@include file="init.jsp"%>

<%
ResultRow row = (ResultRow) request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");
AssetCategory assetCategory = (AssetCategory) row.getObject();
%>

<liferay-ui:icon-menu showWhenSingleIcon="true">
	<portlet:renderURL var="listCategoryPropertiesRenderURL">
		<portlet:param name="mvcPath" value="/list_category_properties.jsp" />
		<portlet:param name="assetCategoryId" value="<%=String.valueOf(assetCategory.getCategoryId()) %>" />
		<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="edit" url="<%=listCategoryPropertiesRenderURL.toString()%>" />
</liferay-ui:icon-menu>