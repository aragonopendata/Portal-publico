<%@ include file="init.jsp" %>

<%
	String lastProceduresReindex = renderRequest.getPreferences().getValue("lastProceduresReindex", "");
	String lastDocumentsReindex = renderRequest.getPreferences().getValue("lastDocumentsReindex", "");
%>

<portlet:actionURL name="<%= EnlineaAdminPortletKeys.ACTION_REINDEX_PROCEDURES %>" var="reindexProceduresURL">
	<portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>"/>
</portlet:actionURL>

<portlet:actionURL name="<%= EnlineaAdminPortletKeys.ACTION_REINDEX_DOCUMENTS %>" var="reindexDocumentsURL">
	<portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>"/>
</portlet:actionURL>

<liferay-frontend:edit-form action="<%= reindexProceduresURL %>" method="post" name="fm">
	<liferay-frontend:fieldset label="procedures" collapsed="<%= false %>" collapsible="<%= false %>">
		<p><strong><liferay-ui:message key="procedures-in-oracle" />:</strong> ${proceduresCount}</p>
		<p><strong><liferay-ui:message key="procedures-in-elastic" />:</strong> ${elasticProceduresCount}</p>
		<p><strong><liferay-ui:message key="last-procedures-reindex" />:</strong> <%= lastProceduresReindex %></p>
		<aui:button cssClass="btn btn-primary" type="submit" value="reindex-procedures" />
	</liferay-frontend:fieldset>
</liferay-frontend:edit-form>

<liferay-frontend:edit-form action="<%= reindexDocumentsURL %>" method="post" name="fm">
	<liferay-frontend:fieldset label="documents" collapsed="<%= false %>" collapsible="<%= false %>">
		<p><strong><liferay-ui:message key="documents-in-oracle" />:</strong> ${documentsCount}</p>
		<p><strong><liferay-ui:message key="documents-in-elastic" />:</strong> ${elasticDocumentsCount}</p>
		<p><strong><liferay-ui:message key="last-documents-reindex" />:</strong> <%= lastDocumentsReindex %></p>
		<aui:button cssClass="btn btn-primary" type="submit" value="reindex-documents" />
	</liferay-frontend:fieldset>
</liferay-frontend:edit-form>