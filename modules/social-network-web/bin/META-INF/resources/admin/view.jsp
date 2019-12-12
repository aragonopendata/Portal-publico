<%@ include file="/init.jsp" %>

<style>
	.img-circle {
    	border-radius: 50%;
	}
</style>

<%
PortletURL redirectionUrl = renderResponse.createRenderURL();
redirectionUrl.setParameter("mvcPath", "/admin/view.jsp");
%>

<liferay-ui:success key="social-network-web.saved-successfully-message" message="social-network-web.saved-successfully-message" />
<liferay-ui:success key="social-network-web.updated-successfully-updated-message" message="social-network-web.updated-successfully-updated-message" />
<liferay-ui:success key="social-network-web.removed-successfully-message" message="social-network-web.removed-successfully-message" />
<liferay-ui:error key="social-network-web.incorrect-extra-parameters-message" message="social-network-web.incorrect-extra-parameters-message"/>

<jsp:useBean id="socialNetworkList" type="java.util.List<es.aragon.base.social_network.sb.model.SocialNetwork>" scope="request"/>

<portlet:renderURL var="addSocialNetwork">
	<portlet:param name="mvcPath" value="/admin/edit_social_network.jsp"/>
	<portlet:param name="backURL" value="<%= themeDisplay.getURLCurrent() %>"></portlet:param>
</portlet:renderURL>

<div class="container-fluid-1280 main-content-body">

	<liferay-ui:search-container emptyResultsMessage="social-network-web.empty-results-message" iteratorURL="<%=redirectionUrl%>">
		 
		<liferay-ui:search-container-results results="${socialNetworkList}"/>
		 
		 <liferay-ui:search-container-row className="SocialNetwork" modelVar="socialNetwork" keyProperty="socialNetworkId" > 
		     
		     <%
		     String dateFormat = DateUtil.getDate(socialNetwork.getModifiedDate(), "dd/MM/yyyy HH:mm:ss", locale, themeDisplay.getCompany().getTimeZone());
		     long imageId = socialNetwork.getImageId();
		     %>
		     
       		 <liferay-ui:search-container-column-text name="social-network-web.companyid-column" property="companyId"/>
       		 <liferay-ui:search-container-column-text name="social-network-web.groupid-column" property="groupId"/>
        	 <liferay-ui:search-container-column-text name="social-network-web.user-column" value="<%=socialNetwork.getUserName()%>"/>
		     <liferay-ui:search-container-column-text name="social-network-web.modified-date-column" value="<%=dateFormat%>"/>
		     <liferay-ui:search-container-column-text name="social-network-web.extra-parameters-column" property="extraParameters"/>
		     <liferay-ui:search-container-column-text name="social-network-web.url-column" value="<%=socialNetwork.getUrl() %>"/>
		     <liferay-ui:search-container-column-text name="social-network-web.alt-column" value="<%=socialNetwork.getAlt() %>"/>
		     <liferay-ui:search-container-column-text name="social-network-web.image-column">
     		 	<img class="img-circle" id="<%=imageId%>" src="<%=themeDisplay.getPathImage()%>/template?img_id=<%=imageId%>&t=<%=WebServerServletTokenUtil.getToken(imageId) %>" width="40" height="40"/>
		     </liferay-ui:search-container-column-text>  
		     <liferay-ui:search-container-column-jsp name="social-network-web.actions-column" path="/admin/buttons.jsp"/>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator displayStyle="list" markupView="lexicon"/>
	
	</liferay-ui:search-container>
	
	<liferay-frontend:add-menu >
 		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "social-network-web.add-button") %>' url="<%=addSocialNetwork%>" />
	</liferay-frontend:add-menu>
</div>