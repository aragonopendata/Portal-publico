<%@page import="javax.portlet.RenderRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>

<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="portletRedirectURL">
    <portlet:param name="mvcPath" value="/view.jsp"/>
</portlet:renderURL>

<div class="sheet sheet-lg">
	<liferay-frontend:fieldset label="aragon-script-utilities.label.update-urls" collapsed="<%=false%>" collapsible="<%=false%>">
	
		<!-- Upload excel -->
		<portlet:actionURL var="uploadDocumentURL" name="uploadDocument"></portlet:actionURL>
		<label class="control-label">
			<liferay-ui:message key="aragon-script-utilities.label.information" />
		</label>
		<liferay-ui:icon-help message="aragon-script-utilities.label.help-information"/>
		<form action="<%=uploadDocumentURL%>" method="post" enctype="multipart/form-data">
			<input type="file" name="uploadedFile"><br/><br/>
			<div class="button-holder">
				<aui:button class="btn select-button btn-default" type="submit" value="aragon-script-utilities.label.publish"></aui:button>
			</div>
		</form>
		
		<!-- Replace urls -->
		<portlet:actionURL name="updateRedirectUrl" var="updateRedirectURL">
			<portlet:param name="fileName" value="${fileName}"/>
			<portlet:param name="urlExcel" value="${urlExcel}"/>
		</portlet:actionURL>
		<aui:form method="post" action="<%=updateRedirectURL%>" enctype="multipart/form-data">
			<div class="button-holder">
				<aui:button class="btn select-button btn-default" type="submit" value="aragon-script-utilities.label.update"></aui:button>
			</div>
		</aui:form>
		
		<!-- Dowload excel -->
		<c:if test="${not empty urlExcel}">
			<a href="${urlExcel}"><liferay-ui:message key="aragon-script-utilities.label.download" /></a>
			<br/>
		</c:if>
	</liferay-frontend:fieldset>
	<br/>
</div>
