<%@ include file="/init.jsp" %>


<%
long socialNetworkId = ParamUtil.getLong(request, "socialNetworkId", 0L);

String action = SocialNetworkWebPortletKeys.ADD_SOCIAL_NETWORK;
long imageId = 0;

SocialNetwork socialNetwork = null;
if(socialNetworkId > 0) {
	socialNetwork = _socialNetworkLocalService.fetchSocialNetwork(socialNetworkId);
	action = SocialNetworkWebPortletKeys.UPDATE_SOCIAL_NETWORK;
	imageId = socialNetwork.getImageId();
}

PortletURL socialNetworkActionUrl = renderResponse.createActionURL();
socialNetworkActionUrl.setParameter("javax.portlet.action", action);

PortletURL backPortletURL = renderResponse.createRenderURL();
backPortletURL.setParameter("mvcPath", "/admin/view.jsp");
portletDisplay.setURLBack(backPortletURL.toString());
portletDisplay.setShowBackIcon(true);

%>

<portlet:renderURL portletMode="view" var="backURL" />

<div class="container-fluid-1280 main-content-body">

	<liferay-frontend:edit-form enctype="multipart/form-data" action="<%=socialNetworkActionUrl %>" method="post" name="fm">
		 <liferay-frontend:edit-form-body>
		 	<liferay-frontend:fieldset-group>
		 		<liferay-frontend:fieldset>
				 	<aui:model-context model="<%=SocialNetwork.class %>" bean="<%=socialNetwork%>"/>
				 	<aui:input name="socialNetworkId" type="hidden" value="<%=socialNetworkId%>"/>
				 	<aui:input name="url" type="text" required="true" label="social-network-web.url-column" />
				 	<aui:input name="alt" type="text" label="social-network-web.alt-column" />
				 	<aui:input name="extraParameters" label="social-network-web.extra-parameters-column" type="text" helpMessage="social-network-web.extra-parameters-help"/>
		 			<aui:input class="form-group form-group-inline input-text-wrapper" name="smallFile" type="file" required="true" label="social-network-web.image-column" cssClass="lfr-journal-small-image-value" inlineField="<%= true %>"/>
				</liferay-frontend:fieldset>
			</liferay-frontend:fieldset-group>
		</liferay-frontend:edit-form-body>	
			
		<liferay-frontend:edit-form-footer>		
	 		<aui:button cssClass="btn-lg" type="submit" />
	 		<aui:button cssClass="btn-lg" type="cancel" href="<%= backPortletURL.toString() %>" />
 		</liferay-frontend:edit-form-footer>
	 		
 		<br><br><br><br>
	 		
 		<aui:fieldset cssClass="definition-of-terms email-user-add terms" label="definition-of-terms">
			<liferay-util:include page="/admin/variables_definition.jsp" servletContext="<%= application %>" />
	 	</aui:fieldset> 

	</liferay-frontend:edit-form>
</div>