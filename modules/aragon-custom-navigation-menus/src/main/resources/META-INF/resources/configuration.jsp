<%@page import="com.liferay.portal.kernel.util.Constants"%>

<%@ include file="init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>" var="configurationActionURL"/>
<liferay-portlet:renderURL portletConfiguration="<%=true%>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%=configurationActionURL%>" method="post" name="fm">
	<aui:input name="<%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>"/>
	<aui:input name="redirect" type="hidden" value="<%=configurationRenderURL%>"/>
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset collapsible="<%= false %>" label="Configuracion principal">
				<aui:select name="preferences--elementsFilter--" label="Mostrar">
					<aui:option value="same_level_pages" selected="<%=elementsFilter.equals("same_level_pages") ? true : false %>">Paginas del mismo nivel que la actual</aui:option>
					<aui:option value="child_pages" selected="<%=elementsFilter.equals("child_pages") ? true : false %>">Paginas hijas de la actual</aui:option>
				</aui:select>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit"/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>