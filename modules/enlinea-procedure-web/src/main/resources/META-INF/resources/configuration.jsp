<%@ include file="init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
    <aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />
    <liferay-frontend:edit-form-body>
   		<liferay-frontend:fieldset-group>
   			<liferay-frontend:fieldset label="configuration.fieldset.presentation-place" collapsible="<%= true %>" collapsed="<%= false %>">
	   			<aui:fieldset>
		   			<aui:field-wrapper cssClass="lfr-input-text-container" label="configuration.label.presential">
						<liferay-ui:input-localized
							name="presential"
							type="editor"
							xml="<%= presential %>" />
					</aui:field-wrapper>
				</aui:fieldset>
				<aui:fieldset>
		   			<aui:field-wrapper cssClass="lfr-input-text-container" label="configuration.label.presentialAndOnline">
						<liferay-ui:input-localized
							name="presentialAndOnline"
							type="editor"
							xml="<%= presentialAndOnline %>" />
					</aui:field-wrapper>
				</aui:fieldset>
				<aui:fieldset>
		   			<aui:field-wrapper cssClass="lfr-input-text-container" label="configuration.label.online">
						<liferay-ui:input-localized
							name="online"
							type="editor"
							xml="<%= online %>" />
					</aui:field-wrapper>
				</aui:fieldset>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="configuration.fieldset.more-information" collapsible="<%= true %>" collapsed="<%= false %>">
				<aui:fieldset>
		   			<aui:field-wrapper cssClass="lfr-input-text-container" label="configuration.tooltip.procedureid-variable">
						<liferay-ui:input-localized
							name="moreInformation"
							type="editor"
							xml="<%= moreInformation %>" />
					</aui:field-wrapper>
				</aui:fieldset>
			</liferay-frontend:fieldset>
   		</liferay-frontend:fieldset-group>
   	</liferay-frontend:edit-form-body>
   	<liferay-frontend:edit-form-footer>
   		<aui:button type="submit"></aui:button>
       	<aui:button type="cancel"></aui:button>
   	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>