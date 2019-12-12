<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesDocumentUtils"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils"%>
<%@ include file="/init.jsp" %>

<%	
	DLFolder userLogDLFolder = AragonPortalUtilitiesCommonUtils.fetchFolder(AragonPortalUtilitiesCommonUtils.USER_LOG_FOLDER_PATH, themeDisplay.getScopeGroupId());
	renderResponse.setTitle("Utilidades de portal - " + menuId);
 %>

<portlet:actionURL name="<%= AragonPortalUtilitiesPortletKeys.ROUTE_USER_LOG %>" var="userLogUrl" />
<div class="sheet sheet-lg padding-top" id="dlFile-div">
	<liferay-ui:error key="service-is-not-avaliable" message="Hay demasiadas tareas en curso"/>

	<%
		if(userLogDLFolder == null) {
	%>
		<div class="alert alert-danger" id="<portlet:namespace/>documentError"><liferay-ui:message key="aragon-portal-utilities.missing-folder.user-log"/></div>
	<%
		}
	%>
	<liferay-frontend:fieldset label="aragon-portal-utilities.form.label.dlFileEntry" collapsed="<%=false%>" collapsible="<%=false%>">
		<aui:form method="post" action="<%=userLogUrl%>" name="form2">
			<aui:input name="menuId" type="hidden" value="<%=menuId%>" />
			<label for="<portlet:namespace/>fromDate"><liferay-ui:message key="aragon-portal-utilities.date-picker.userLog.from"/></label>
			<liferay-ui:input-date name="fromDate" lastEnabledDate="<%= new Date() %>" yearValue="2018" monthValue="9"/>
			<label for="<portlet:namespace/>toDate"><liferay-ui:message key="aragon-portal-utilities.date-picker.userLog.to"/></label>
			<liferay-ui:input-date name="toDate" lastEnabledDate="<%= new Date() %>" yearValue="2019" monthValue="9"/>
			<br>
			<aui:button name="publishButtondl" type="submit" value="aragon-portal-utilities.form.submit.user-log" />
		</aui:form>
	</liferay-frontend:fieldset>
	<br>

	<liferay-portlet:renderURL varImpl="iteratorUserLogURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="menuId" value="<%=menuId%>" />
	</liferay-portlet:renderURL>
	<liferay-ui:search-container iteratorURL="<%=iteratorUserLogURL%>" emptyResultsMessage="aragon-portal-utilities.container-empty-results-message.user-log"  delta="5" deltaConfigurable="true">
		<liferay-ui-search-container-results>
			<%
				List<DLFileEntry> results = AragonPortalUtilitiesCommonUtils.getUserLogs(themeDisplay.getScopeGroupId(), searchContainer.getStart(), searchContainer.getEnd());
	
				searchContainer.setResults(results);
				searchContainer.setTotal(AragonPortalUtilitiesCommonUtils.getUserLogsCount(themeDisplay.getScopeGroupId()));
			%>
		</liferay-ui-search-container-results>
		<liferay-ui:search-container-row className="com.liferay.document.library.kernel.model.DLFileEntry" keyProperty="fileEntryId" modelVar="fileEntry" >
			<%
				String href = themeDisplay.getPortalURL() + "/documents/" + fileEntry.getGroupId() + "/"+ fileEntry.getFolderId() + "/" + fileEntry.getFileName();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));	
		
			String date = dateFormat.format(fileEntry.getCreateDate());
			%>
			<liferay-ui:search-container-column-text name="Informes generados" property="fileName" href="<%=href%>"/>
			<liferay-ui:search-container-column-text name="Fecha de creación" value="<%=date%>"/>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
	</liferay-ui:search-container>
	<br>
</div>

<script type="text/javascript">	
	$(document).ready(function() {
		<%if(userLogDLFolder != null) { %>
	
			$('#<portlet:namespace/>selectordlFile').change(function() {
		        $("#<portlet:namespace/>publishButtondl").attr("disabled", false);
		        $("#<portlet:namespace/>publishButtondl").removeAttr("style");
			});
			
		<% } else {%>
	    	$("#<portlet:namespace/>publishButtondl" ).attr("disabled", true);
	   	<% } %>
	});
</script>