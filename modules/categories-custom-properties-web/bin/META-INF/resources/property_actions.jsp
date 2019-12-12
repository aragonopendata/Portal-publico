<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"%>

<%@include file="init.jsp"%>

<%
ResultRow row = (ResultRow) request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");
CustomCategoryProperty customCategoryProperty = (CustomCategoryProperty) row.getObject();
%>

<liferay-ui:icon-menu showWhenSingleIcon="true">
	<portlet:renderURL var="editPropertyRenderURL">
		<portlet:param name="mvcPath" value="/save_property.jsp"/>
		<portlet:param name="assetCategoryId" value="<%=String.valueOf(customCategoryProperty.getCategoryId()) %>" />
		<portlet:param name="customCategoryPropertyId" value="<%=String.valueOf(customCategoryProperty.getCustomCategoryPropertyId()) %>" />
		<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="edit" url="<%=editPropertyRenderURL.toString()%>" />
	<portlet:actionURL name="deleteProperty" var="deletePropertyActionURL">
		<portlet:param name="customCategoryPropertyId" value="<%=String.valueOf(customCategoryProperty.getCustomCategoryPropertyId()) %>" />
		<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
	</portlet:actionURL>
	<liferay-ui:icon image="delete" message="delete" url="<%=deletePropertyActionURL.toString()%>" />
</liferay-ui:icon-menu>