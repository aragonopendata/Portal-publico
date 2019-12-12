<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.DateFormat"%>
<%@page import="es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesContentUtils"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@ include file="/init.jsp" %>

<%
	DLFolder webContentDLFolder = AragonPortalUtilitiesCommonUtils.fetchFolder(AragonPortalUtilitiesCommonUtils.WEB_CONTENT_FOLDER_PATH, themeDisplay.getScopeGroupId());
	renderResponse.setTitle("Utilidades de portal - " + menuId);
%>

<portlet:actionURL name="<%= AragonPortalUtilitiesPortletKeys.ROUTE_WEB_CONTENT %>" var="webContentUrl" />
<div class="sheet sheet-lg padding-top" id="webContent-div">
	<liferay-ui:error key="service-is-not-avaliable" message="Hay demasiadas tareas en curso"/>

	<liferay-ui:success key="success-request" message="aragon-portal-utilities.success-created"/>

	<%
		if(webContentDLFolder == null) {
	%>
		<div class="alert alert-danger" id="<portlet:namespace/>webFolderError"><liferay-ui:message key="aragon-portal-utilities.missing-folder.webContent"/></div>
	<%
		}
	%>
	
	<div class="alert alert-info" id="alert-info-queued"><liferay-ui:message key="aragon-portal-utilities.queued.task.info"/></div>
	
	<liferay-frontend:fieldset label="aragon-portal-utilities.form.label.webContent" collapsed="<%=false%>" collapsible="<%=false%>">
		<aui:form method="post" action="<%=webContentUrl%>" name="form1">
			<aui:select id="selectorwebContent" name="folderIdToMap" label="aragon-portal-utilities.folder-selector.label.webContent"/>
			<aui:input name="menuId" type="hidden" value="<%=menuId%>"/>
			<aui:button name="publishButtonweb" type="submit" value="aragon-portal-utilities.form.submit.web-map" />
		</aui:form>
	</liferay-frontend:fieldset>
	<br>
	
	<liferay-portlet:renderURL varImpl="iteratorWebContentURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="menuId" value="<%=menuId%>" />
	</liferay-portlet:renderURL>
	<liferay-ui:search-container iteratorURL="<%=iteratorWebContentURL%>" emptyResultsMessage="aragon-portal-utilities.container-empty-results-message.web-map" delta="5" deltaConfigurable="true">
		<liferay-ui-search-container-results>
			<%
				List<DLFileEntry> results = AragonPortalUtilitiesCommonUtils.getWebContentMaps(themeDisplay.getScopeGroupId(), searchContainer.getStart(), searchContainer.getEnd());

				searchContainer.setResults(results);
				searchContainer.setTotal(AragonPortalUtilitiesCommonUtils.getWebContentMapsCount(themeDisplay.getScopeGroupId()));
			%>
		</liferay-ui-search-container-results>
		<liferay-ui:search-container-row className="com.liferay.document.library.kernel.model.DLFileEntry" keyProperty="fileEntryId" modelVar="fileEntry" >
			<%
				String href = themeDisplay.getPortalURL() + "/documents/" + fileEntry.getGroupId() + "/"+ fileEntry.getFolderId() + "/" + fileEntry.getFileName();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));	
			
				String date = dateFormat.format(fileEntry.getCreateDate());
			%>
			<liferay-ui:search-container-column-text name="Mapas generados" property="fileName" href="<%=href%>"/>
			<liferay-ui:search-container-column-text name="Fecha de creación" value="<%=date%>"/>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
	</liferay-ui:search-container>
	<br>
</div>

<script type="text/javascript">
	function getJournalFolderData() {
		return <%=AragonPortalUtilitiesContentUtils.getJournalFolderJson(themeDisplay.getScopeGroupId())%>;
	}
	
	$(document).ready(function() {
		buildOptions('selectorwebContent', getJournalFolderData(), 0);	
	<%if(webContentDLFolder != null) { %>
		
		$('#<portlet:namespace/>selectorwebContent').change(function() {
	        $("#<portlet:namespace/>publishButtonweb").attr("disabled", false);
	        $("#<portlet:namespace/>publishButtonweb").removeAttr("style");
		});
		
	<% } else {%>
    	$("#<portlet:namespace/>publishButtonweb").attr("disabled", true);
   	<% } %>
	});
</script>