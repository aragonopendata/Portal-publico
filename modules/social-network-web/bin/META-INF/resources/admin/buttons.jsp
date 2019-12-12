<%@ include file="/init.jsp" %>

<%
    final ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    final SocialNetwork socialNetwork = (SocialNetwork) row.getObject();
%>

<portlet:actionURL name="deleteSocialNetwork" var="deleteSocialNetworkActionURL">
    <portlet:param name="socialNetworkId" value="<%=String.valueOf(socialNetwork.getSocialNetworkId())%>"/>
</portlet:actionURL>

<portlet:actionURL name="goEditForm" var="goEditFormActionURL">
    <portlet:param name="socialNetworkId" value="<%=String.valueOf(socialNetwork.getSocialNetworkId())%>"/>
</portlet:actionURL>
 
<liferay-ui:icon-menu>
 	<liferay-ui:icon image="edit" message="social-network-web.edit-button" url="<%=goEditFormActionURL%>"/>
    <liferay-ui:icon image="delete" message="social-network-web.delete-button" url="<%=deleteSocialNetworkActionURL%>"/>
</liferay-ui:icon-menu>