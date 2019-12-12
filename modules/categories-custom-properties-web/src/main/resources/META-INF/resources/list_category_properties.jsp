<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"%>
<%@page import="es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
if (redirect != null && !redirect.isEmpty()) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);
}
long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId", -1);
%>
<c:if test="<%=assetCategoryId > -1%>">
	<%AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(assetCategoryId);%>
	<c:if test="<%=assetCategory != null%>">
		<%
		renderResponse.setTitle("Propiedades de " + assetCategory.getTitle(locale));
		%>
		<nav class="management-bar management-bar-light navbar navbar-expand-md">
			<div class="container-fluid container-fluid-max-xl">
				<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
					<div class="container-fluid container-fluid-max-xl"></div>
				</div>
				<ul class="navbar-nav">
					<li class="nav-item">
						<div class="dropdown">
							<portlet:renderURL var="addPropertyRenderURL">
								<portlet:param name="mvcPath" value="/save_property.jsp"/>
								<portlet:param name="assetCategoryId" value="<%=String.valueOf(assetCategoryId)%>"/>
								<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
							</portlet:renderURL>
							<button onclick="window.location.href='<%=addPropertyRenderURL%>'" aria-expanded="false" aria-haspopup="true" aria-label="toggle" class="dropdown-toggle btn nav-btn nav-btn-monospaced btn-primary" data-onclick="toggle" ref="triggerButton" type="button" data-title="Nuevo" title="Nuevo">
								<svg aria-hidden="true" class="lexicon-icon lexicon-icon-plus" focusable="false" viewBox="0 0 512 512">
									<title>plus</title>
									<path class="lexicon-icon-outline" d="M479.82 224.002h-192.41v-191.91c0-17.6-14.4-32-32-32v0c-17.6 0-32 14.4-32 32v191.91h-191.41c-17.6 0-32 14.4-32 32v0c0 17.6 14.4 32 32 32h191.41v191.91c0 17.6 14.4 32 32 32v0c17.6 0 32-14.4 32-32v-191.909h192.41c17.6 0 32-14.4 32-32v0c0-17.6-14.4-32-32-32z"></path>
								</svg>
							</button>
						</div>
					</li>
				</ul>
			</div>
		</nav>
	</c:if>
</c:if>
<div class="container-fluid-1280 main-content-body">
	<liferay-ui:search-container emptyResultsMessage="No se han encontrado propiedades para la categoria" deltaConfigurable="<%=false%>">
		<liferay-ui-search-container-results>
			<%
			List<CustomCategoryProperty> customCategoryProperties = CustomCategoryPropertyLocalServiceUtil.findByCategoryId(assetCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null, true); 
			searchContainer.setTotal(customCategoryProperties.size());
			searchContainer.setResults(customCategoryProperties);
			%>
		</liferay-ui-search-container-results>
		<liferay-ui:search-container-row className="es.aragon.base.categories_custom_properties.model.CustomCategoryProperty" keyProperty="customCategoryPropertyId" modelVar="customCategoryProperty" >
			<liferay-ui:search-container-column-text name="key" property="key"/>
			<liferay-ui:search-container-column-text name="value" property="text"/>
			<liferay-ui:search-container-column-jsp align="right" path="/property_actions.jsp" />
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator paginate="false"/>
	</liferay-ui:search-container>
</div>