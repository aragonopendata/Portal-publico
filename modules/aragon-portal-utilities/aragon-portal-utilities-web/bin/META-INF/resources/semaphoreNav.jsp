<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker"%>
<%@page import="es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil"%>
<%@page import="es.aragon.base.semaphore.model.Semaphore"%>
<%@page import="java.util.Date"%>
<%@page import="es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesDocumentUtils"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils"%>
<%@ include file="/init.jsp" %>

<%
	renderResponse.setTitle("Utilidades de portal - " + menuId);
%>
<style>
.status-ok {
	color: forestgreen;
	font-weight: bold;
	text-align: center;
}

.status-nok {
	color: red;
	font-weight: bold;
	text-align: center;
}

.max-users {
	font-weight: bold;
	text-align: center;
}
</style>

<liferay-portlet:renderURL varImpl="itertatorURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
	<portlet:param name="menuId" value="<%=menuId%>" />
</liferay-portlet:renderURL>
<div class="sheet sheet-lg padding-top" id="semaphores">
	<liferay-ui:search-container iteratorURL="<%=itertatorURL%>" emptyResultsMessage="aragon-portal-utilities.container-empty-results-message.semaphore"  delta="5" deltaConfigurable="true">
		<liferay-ui-search-container-results>
			<%
				List<Semaphore> results = SemaphoreLocalServiceUtil.getSemaphores(themeDisplay.getScopeGroupId(), searchContainer.getStart(), searchContainer.getEnd());
	
				searchContainer.setResults(results);
				searchContainer.setTotal(SemaphoreLocalServiceUtil.getSemaphoresCount(themeDisplay.getScopeGroupId()));
			%>
		</liferay-ui-search-container-results>
		<liferay-ui:search-container-row className="es.aragon.base.semaphore.model.Semaphore" keyProperty="semaphoreId" modelVar="semaphore" >
			<liferay-ui:search-container-column-text name="Identificador" property="semaphoreId"/>
			<liferay-ui:search-container-column-text name="Nombre del servicio" property="serviceName"/>
			<liferay-ui:search-container-column-text name="Maximo de usuarios" property="maxUsers" cssClass="max-users"/>
			<%
				String cssClass = "status-ok";
				Double percent = (semaphore.getCurrentUsers() * 1.0) / semaphore.getMaxUsers();
				if(percent >= 0.5) {
					cssClass = "status-nok";
				}
			%>
			<liferay-ui:search-container-column-text name="Usuarios actualmente" property="currentUsers" cssClass="<%=cssClass%>"/>
			<liferay-ui:search-container-column-jsp name="Acciones" path="/semaphoreActions.jsp" />
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
	</liferay-ui:search-container>
	<br>
</div>

