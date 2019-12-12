<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"%>
<%@page import="es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil"%>

<%@ include file="init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
if (redirect != null && !redirect.isEmpty()) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);
}

long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId", -1);
long customCategoryPropertyId = ParamUtil.getLong(request, "customCategoryPropertyId", -1);

String key = StringPool.BLANK;
String value = StringPool.BLANK;

if (customCategoryPropertyId == -1) {
	renderResponse.setTitle("Nueva propiedad");
} else {
	CustomCategoryProperty customCategoryProperty = CustomCategoryPropertyLocalServiceUtil.fetchCustomCategoryProperty(customCategoryPropertyId);
	if (customCategoryProperty != null) {
		renderResponse.setTitle("Editar " + customCategoryProperty.getKey());
		key = customCategoryProperty.getKey();
		value = customCategoryProperty.getText();
	}
}
%>

<portlet:actionURL name="saveProperty" var="savePropertyActionURL">
	<portlet:param name="assetCategoryId" value="<%=String.valueOf(assetCategoryId)%>"/>
	<portlet:param name="customCategoryPropertyId" value="<%=String.valueOf(customCategoryPropertyId)%>"/>
	<portlet:param name="redirect" value="<%=redirect%>"/>
</portlet:actionURL>
<liferay-frontend:edit-form method="post" action="<%=savePropertyActionURL%>">
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset label="details">
				<aui:input type="text" name="key" label="key" placeholder="key" value="<%=key%>">
					<aui:validator name="required"/>
					<aui:validator name="maxLength">75</aui:validator>
				</aui:input>
				<aui:input type="textarea" name="value" label="value" placeholder="value" value="<%=value%>">
					<aui:validator name="required"/>
				</aui:input>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" value="save"></aui:button>
		<c:if test="<%=redirect != null && !redirect.isEmpty()%>">
			<aui:button type="cancel" href="<%=redirect%>"></aui:button>
		</c:if>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>