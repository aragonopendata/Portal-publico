<%@ include file="init.jsp" %>

<%
	List<LayoutPageTemplateEntry> globalLayoutPageTemplateEntries = LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(
			themeDisplay.getCompanyGroupId(), LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE, WorkflowConstants.STATUS_APPROVED, -1, -1, null);
	List<LayoutPageTemplateEntry> siteLayoutPageTemplateEntries = LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(
			themeDisplay.getScopeGroupId(), LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE, WorkflowConstants.STATUS_APPROVED, -1, -1, null);
	PortletPreferences preferences = renderRequest.getPreferences();
	long level1TopicPageTemplate =  GetterUtil.getLong(preferences.getValue("level1TopicPageTemplate", "-1"), -1);
	long level23TopicPageTemplate = GetterUtil.getLong(preferences.getValue("level23TopicPageTemplate", "-1"), -1);
	long level1ProfilePageTemplate =  GetterUtil.getLong(preferences.getValue("level1ProfilePageTemplate", "-1"), -1);
	long level23ProfilePageTemplate = GetterUtil.getLong(preferences.getValue("level23ProfilePageTemplate", "-1"), -1);
	String lastTopicPageGeneration = preferences.getValue("lastTopicPageGeneration", "");
	String lastProfilePageGeneration = preferences.getValue("lastProfilePageGeneration", "");
%>

<liferay-ui:error key="action.error.empty-page-templates" message="action.error.empty-page-templates" />
<liferay-ui:error key="action.error.no-topic-vocabulary" message="action.error.no-topic-vocabulary" />
<liferay-ui:error key="action.error.no-categories" message="action.error.no-categories" />
<liferay-ui:error key="action.error.no-procedure-url-defined" message="action.error.no-procedure-url-defined" />
<liferay-ui:error key="action.error.defined-procedure-url-not-found" message="action.error.defined-procedure-url-not-found" />

<portlet:actionURL name="<%= EnlineaAdminPortletKeys.ACTION_CREATE_TOPIC_PAGES %>" var="createTopicPagesURL">
	<portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>"/>
</portlet:actionURL>

<portlet:actionURL name="<%= EnlineaAdminPortletKeys.ACTION_CREATE_PROFILE_PAGES %>" var="createProfilePagesURL">
	<portlet:param name="redirect" value="<%= themeDisplay.getURLCurrent() %>"/>
</portlet:actionURL>

<liferay-frontend:edit-form action="<%= createTopicPagesURL %>" method="post" name="fm">
	<liferay-frontend:fieldset label="page-creation-topic" collapsed="<%= false %>" collapsible="<%= false %>">
		<aui:select label="page-template-for-level-1-pages" name="level1PageTemplate" helpMessage="page-template-for-level-1-pages-help-message">
			<aui:option value="-1"><liferay-ui:message key="select-page-template" /></aui:option>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : globalLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level1TopicPageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %> (<liferay-ui:message key="global" />)
				</aui:option>
			<%
				}
			%>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : siteLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level1TopicPageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %>
				</aui:option>
			<%
				}
			%>
		</aui:select>
		<aui:select label="page-template-for-level-2-3-pages" name="level23PageTemplate" helpMessage="page-template-for-level-2-3-pages-help-message">
			<aui:option value="-1"><liferay-ui:message key="select-page-template" /></aui:option>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : globalLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level23TopicPageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %> (<liferay-ui:message key="global" />)
				</aui:option>
			<%
				}
			%>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : siteLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level23TopicPageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %>
				</aui:option>
			<%
				}
			%>
		</aui:select>
		<p><strong><liferay-ui:message key="last-page-generation" />:</strong> <%= lastTopicPageGeneration %></p>
		<aui:button cssClass="btn btn-primary" type="submit" value="create-pages" />
	</liferay-frontend:fieldset>
</liferay-frontend:edit-form>

<liferay-frontend:edit-form action="<%= createProfilePagesURL %>" method="post" name="fm">
	<liferay-frontend:fieldset label="page-creation-profile" collapsed="<%= false %>" collapsible="<%= false %>">
		<aui:select label="page-template-for-level-1-pages" name="level1PageTemplate" helpMessage="page-template-for-level-1-pages-help-message">
			<aui:option value="-1"><liferay-ui:message key="select-page-template" /></aui:option>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : globalLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level1ProfilePageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %> (<liferay-ui:message key="global" />)
				</aui:option>
			<%
				}
			%>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : siteLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level1ProfilePageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %>
				</aui:option>
			<%
				}
			%>
		</aui:select>
		<aui:select label="page-template-for-level-2-3-pages" name="level23PageTemplate" helpMessage="page-template-for-level-2-3-pages-help-message">
			<aui:option value="-1"><liferay-ui:message key="select-page-template" /></aui:option>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : globalLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level23ProfilePageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %> (<liferay-ui:message key="global" />)
				</aui:option>
			<%
				}
			%>
			<%
				for (LayoutPageTemplateEntry layoutPageTemplateEntry : siteLayoutPageTemplateEntries) {
			%>
				<aui:option value="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() %>"
					selected="<%= layoutPageTemplateEntry.getLayoutPageTemplateEntryId() == level23ProfilePageTemplate %>">
					<%= layoutPageTemplateEntry.getName() %>
				</aui:option>
			<%
				}
			%>
		</aui:select>
		<p><strong><liferay-ui:message key="last-page-generation" />:</strong> <%= lastProfilePageGeneration %></p>
		<aui:button cssClass="btn btn-primary" type="submit" value="create-pages" />
	</liferay-frontend:fieldset>
</liferay-frontend:edit-form>