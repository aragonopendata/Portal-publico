<%@page import="es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys"%>
<%@page import="es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil"%>
<%@page import="es.aragon.base.semaphore.model.Semaphore"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesSearchUrlUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@ include file="/init.jsp" %>

<%
	String url = ParamUtil.getString(request,"url", "");
	renderResponse.setTitle("Utilidades de portal - " + menuId);

	Semaphore semaphore = SemaphoreLocalServiceUtil.addSemaphore(themeDisplay.getScopeGroupId(), AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES, AragonPortalUtilitiesPortletKeys.MAX_SEMAPHORE_USERS);
%>

<liferay-portlet:renderURL varImpl="searchUrl">
	<portlet:param name="mvcPath" value="/view.jsp" />
	<portlet:param name="menuId" value="<%=menuId%>" />
</liferay-portlet:renderURL>

<div class="sheet sheet-lg padding-top" id="searchUrl-div">

	<%
		Boolean disabled = semaphore.getCurrentUsers() >= semaphore.getMaxUsers();
		if(	disabled ) {
	%>
		<div class="alert alert-danger" id="<portlet:namespace/>userLimit">Hay demasiadas tareas en curso</div>
	<%
		}
	%>	
	<liferay-frontend:fieldset label="aragon-portal-utilities.form.label.webContent" collapsed="<%=false%>" collapsible="<%=false%>">
		<aui:form method="post" action="<%=searchUrl%>" name="form2">
			<aui:input name="url" type="text" label="aragon-portal-utilities.url-input.label.searchUrl" value="<%=url%>"/>
			<aui:button name="publishButtonweb" type="submit" value="aragon-portal-utilities.form.submit.search" disabled="<%=disabled%>" />
		</aui:form>
	</liferay-frontend:fieldset>
	<br>

	<liferay-portlet:renderURL varImpl="iteratorUrlFinderURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="menuId" value="<%=menuId%>" />
		<portlet:param name="url" value="<%=url%>" />

	</liferay-portlet:renderURL>
	<liferay-ui:search-container iteratorURL="<%=iteratorUrlFinderURL%>" emptyResultsMessage="aragon-portal-utilities.container-empty-results-message.search"  delta="50" deltaConfigurable="false">
		<liferay-ui-search-container-results>
			<%
				List<String> results = AragonPortalUtilitiesSearchUrlUtil.findReferencesInAllJournalArticleContents(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), url, searchContainer.getStart(), searchContainer.getEnd());
				searchContainer.setResults(results);
				searchContainer.setTotal(AragonPortalUtilitiesSearchUrlUtil.findReferencesInAllJournalArticleContentsCount(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), url, results));
			%>
		</liferay-ui-search-container-results>
		<liferay-ui:search-container-row className="java.lang.String" modelVar="value">
			<liferay-ui:search-container-column-text name="Contenidos desde donde esta siendo referenciado" target="_blank" value="<%=themeDisplay.getPortalURL() + "/-/" + value %>" href="<%=themeDisplay.getPortalURL() + "/-/" + value %>"/>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
	</liferay-ui:search-container>
	<br>
</div>
