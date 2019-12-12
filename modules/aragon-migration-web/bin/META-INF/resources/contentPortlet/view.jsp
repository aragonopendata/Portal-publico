<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.model.JournalFolder"%>
<%@page import="com.liferay.journal.model.JournalFolderConstants"%>
<%@page import="com.liferay.journal.service.JournalFolderLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="es.aragon.base.migration.model.Area"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="es.aragon.base.migration.model.ContentUrl"%>
<%@page import="es.aragon.base.migration.service.AreaLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.service.ContentLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.service.ContentUrlLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.util.AreaUtil"%>
<%@page import="es.aragon.base.migration.util.ContentPreviewUtil"%>
<%@page import="es.aragon.base.migration.util.MigrationUtil"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>

<%@ include file="init.jsp" %>

<%
long areaId = ParamUtil.getLong(request, "areaId", 0);
String typeName = ParamUtil.getString(request, "typeName", StringPool.BLANK);
long userGroupId = 0;
long journalFolderId = ParamUtil.getLong(renderRequest, "journalFolderId", 0);
if (permissionChecker.isOmniadmin()) {
	userGroupId = ParamUtil.getLong(renderRequest, "userGroupId", 0);
} else {
	long[] userGroupIds = themeDisplay.getUser().getUserGroupIds();
	if (userGroupIds != null && userGroupIds.length > 0) {
		userGroupId = userGroupIds[0];
		
	}
}
long assignedUserId = ParamUtil.getLong(request, "assignedUserId", 0);
long statusId = ParamUtil.getLong(request, "statusId", 0);
long actionId = ParamUtil.getLong(request, "actionId", -1);
int visibility = ParamUtil.getInteger(request, "visibility", -1);
String search = ParamUtil.getString(request, "search", StringPool.BLANK);
%>
<nav class="management-bar management-bar-light navbar navbar-expand-md">
	<div class="container-fluid container-fluid-max-xl">
		<!-- FILTROS -->
		<ul class="navbar-nav">
			<!-- Area -->
			<portlet:renderURL var="resetAreaFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="0"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
				<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
				<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<li class="dropdown nav-item">
				<div class="dropdown">
					<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownAreaButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="navbar-breakpoint-down-d-none">
							<%=LanguageUtil.get(request, "aragon.migration.contentportlet.area")%>
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
								<title>caret-bottom</title>
								<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
							</svg>
						</span>
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownAreaButton">
						<a class='dropdown-item <%=areaId == 0 ? " active" : "" %>' href="<%=resetAreaFilterRenderURL%>">Cualquiera</a>
						<%
						List<Area> areasList;
						if(themeDisplay.getPermissionChecker().isOmniadmin()) {
							areasList = AreaLocalServiceUtil.getAreas(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						} else {
							areasList = AreaUtil.getUserAreas(themeDisplay.getUser().getUserGroupIds());
						}
						%>
						<c:if test="<%=areasList != null && !areasList.isEmpty()%>">
							<%for (Area area : areasList) {%>
								<portlet:renderURL var="areaFilterRenderURL">
									<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
									<portlet:param name="areaId" value="<%=String.valueOf(area.getAreaId())%>"/>
									<portlet:param name="typeName" value="<%=typeName%>"/>
									<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
									<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
									<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
									<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
									<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
									<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
									<portlet:param name="search" value="<%=search%>"/>
								</portlet:renderURL>
								<a class='dropdown-item <%=area.getAreaId() == areaId ? " active" : "" %>' href="<%=areaFilterRenderURL%>"><%=area.getName()%></a>
							<%}%>
						</c:if>
					</div>
				</div>
			</li>
			<!-- Carpetas de contenido web -->
			<portlet:renderURL var="resetJournalFolderFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="0"/>
				<portlet:param name="journalFolderId" value="0"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>							
				<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<portlet:renderURL var="NoJournalFolderFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="0"/>
				<portlet:param name="journalFolderId" value="-1"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>							
				<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<li class="dropdown nav-item">
				<div class="dropdown">
					<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownJournalFolderButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="navbar-breakpoint-down-d-none">
							<liferay-ui:message key="aragon.migration.contentportlet.folder"/>
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
								<title>caret-bottom</title>
								<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
							</svg>
						</span>
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownJournalFolderButton">
							<a class='dropdown-item <%=journalFolderId == 0 ? " active" : "" %>' href="<%=resetJournalFolderFilterRenderURL%>">Cualquiera</a>
							<a class='dropdown-item <%=journalFolderId == -1 ? " active" : "" %>' href="<%=NoJournalFolderFilterRenderURL%>">Sin carpeta</a>
						<%List<JournalFolder> journalFolders = JournalFolderLocalServiceUtil.getFolders(themeDisplay.getScopeGroupId(), JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);%>
						<c:if test="<%=journalFolders != null && !journalFolders.isEmpty()%>">
							<%
							for (JournalFolder journalFolder : journalFolders) {
								if(permissionChecker.hasPermission(journalFolder.getGroupId(), journalFolder.getModelClassName(), journalFolder.getFolderId(), ActionKeys.VIEW)) {
							%>
									<portlet:renderURL var="JournalFolderFilterRenderURL">
										<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
										<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
										<portlet:param name="typeName" value="<%=typeName%>"/>
										<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
										<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolder.getFolderId())%>"/>
										<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
										<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
										<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
										<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
										<portlet:param name="search" value="<%=search%>"/>
									</portlet:renderURL>
									<a class="dropdown-item <%=journalFolder.getFolderId() == journalFolderId ? " active" : "" %>" href="<%=JournalFolderFilterRenderURL%>"><%=journalFolder.getName()%></a>
							<%
								}
							}
							%>
						</c:if>
					</div>
				</div>
			</li>
			<!-- Asignado a -->
			<portlet:renderURL var="resetAsignadoFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
				<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
				<portlet:param name="assignedUserId" value="0"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
				<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<!-- Revisado -->
			<portlet:renderURL var="resetRevisadoFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
				<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="0"/>
				<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<!-- Migrar -->
			<portlet:renderURL var="resetMigrarFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
				<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
				<portlet:param name="actionId" value="-1"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<li class="dropdown nav-item">
				<div class="dropdown">
					<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownMigrarButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="navbar-breakpoint-down-d-none">
							<%=LanguageUtil.get(request, "aragon.migration.contentportlet.migrate")%>
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
								<title>caret-bottom</title>
								<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
							</svg>
						</span>
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMigrarButton">
						<a class="dropdown-item <%=actionId == -1 ? " active" : "" %>" href="<%=resetMigrarFilterRenderURL%>">Cualquiera</a>
						<portlet:renderURL var="noMigrarFilterRenderURL">
							<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
							<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
							<portlet:param name="typeName" value="<%=typeName%>"/>
							<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
							<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
							<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
							<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
							<portlet:param name="actionId" value="0"/>
							<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
							<portlet:param name="search" value="<%=search%>"/>
						</portlet:renderURL>
						<a class="dropdown-item <%=actionId == 0 ? " active" : "" %>" href="<%=noMigrarFilterRenderURL%>"><%=LanguageUtil.get(request, "no") %></a>
						<portlet:renderURL var="migrarFilterRenderURL">
							<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
							<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
							<portlet:param name="typeName" value="<%=typeName%>"/>
							<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
							<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
							<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
							<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
							<portlet:param name="actionId" value="1"/>
							<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
							<portlet:param name="search" value="<%=search%>"/>
						</portlet:renderURL>
						<a class="dropdown-item <%=actionId == 1 ? " active" : "" %>" href="<%=migrarFilterRenderURL%>"><%=LanguageUtil.get(request, "yes")%></a>
						<portlet:renderURL var="enEsperaMigrarFilterRenderURL">
							<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
							<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
							<portlet:param name="typeName" value="<%=typeName%>"/>
							<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
							<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
							<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
							<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
							<portlet:param name="actionId" value="2"/>
							<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
							<portlet:param name="search" value="<%=search%>"/>
						</portlet:renderURL>
						<a class="dropdown-item <%=actionId == 2 ? " active" : "" %>" href="<%=enEsperaMigrarFilterRenderURL%>">En espera</a>
					</div>
				</div>
			</li>
			<portlet:renderURL var="resetVisibilityFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
				<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
				<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
				<portlet:param name="visibility" value="-1"/>
				<portlet:param name="search" value="<%=search%>"/>
			</portlet:renderURL>
			<li class="dropdown nav-item">
				<div class="dropdown">
					<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownMigrarButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="navbar-breakpoint-down-d-none">
							<%=LanguageUtil.get(request, "aragon.migration.contentportlet.visibility")%>
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
								<title>caret-bottom</title>
								<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
							</svg>
						</span>
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMigrarButton">
						<a class="dropdown-item <%=visibility == -1 ? " active" : "" %>" href="<%=resetVisibilityFilterRenderURL%>">Cualquiera</a>
						<portlet:renderURL var="noVisibilityFilterRenderURL">
							<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
							<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
							<portlet:param name="typeName" value="<%=typeName%>"/>
							<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
							<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
							<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
							<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
							<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
							<portlet:param name="visibility" value="0"/>
							<portlet:param name="search" value="<%=search%>"/>
						</portlet:renderURL>
						<a class="dropdown-item <%=visibility == 0 ? " active" : "" %>" href="<%=noVisibilityFilterRenderURL%>"><%=LanguageUtil.get(request, "no") %></a>
						<portlet:renderURL var="yesVisibilityFilterRenderURL">
							<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
							<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
							<portlet:param name="typeName" value="<%=typeName%>"/>
							<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
							<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
							<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
							<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
							<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
							<portlet:param name="visibility" value="1"/>
							<portlet:param name="search" value="<%=search%>"/>
						</portlet:renderURL>
						<a class="dropdown-item <%=visibility == 1 ? " active" : "" %>" href="<%=yesVisibilityFilterRenderURL%>"><%=LanguageUtil.get(request, "yes")%></a>
					</div>
				</div>
			</li>
		</ul>
		<!-- CAJA BUSCADOR -->
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid container-fluid-max-xl">
				<portlet:renderURL var="textSearchRenderURL">
					<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
					<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
					<portlet:param name="typeName" value="<%=typeName%>"/>
					<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
					<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
					<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
					<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
					<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
					<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
					<portlet:param name="search" value="VAR_SEARCH"/>
				</portlet:renderURL>
				<aui:form method="post" name="searchForm" action="<%=textSearchRenderURL%>" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "submitSearchForm();" %>'>
					<div class="input-group">
						<div class="input-group-item">
						<input id="<portlet:namespace/>searchInput" name='<portlet:namespace/>search' aria-label="Search" class="form-control input-group-inset input-group-inset-after" placeholder='<%=LanguageUtil.get(request, "aragon.migration.contentportlet.search-placeholder")%>' ref="search" type="text" value="<%=search%>">
						<span class="input-group-inset-item input-group-inset-item-after">
							<button class="btn btn-unstyled" aria-label="search" type="submit">
								<svg aria-hidden="true" class="lexicon-icon lexicon-icon-search" focusable="false" viewBox="0 0 512 512">
									<title>search</title>
									<path class="lexicon-icon-outline" d="M503.254 467.861l-133.645-133.645c27.671-35.13 44.344-79.327 44.344-127.415 0-113.784-92.578-206.362-206.362-206.362s-206.362 92.578-206.362 206.362 92.578 206.362 206.362 206.362c47.268 0 90.735-16.146 125.572-42.969l133.851 133.851c5.002 5.002 11.554 7.488 18.106 7.488s13.104-2.486 18.106-7.488c10.004-10.003 10.004-26.209 0.029-36.183zM52.446 206.801c0-85.558 69.616-155.173 155.173-155.173s155.174 69.616 155.174 155.173-69.616 155.173-155.173 155.173-155.173-69.616-155.173-155.173z"></path>
								</svg>
							</button>
						</span>
						</div>
					</div>
				</aui:form>
			</div>
		</div>
		<!-- RESET FILTROS -->
		<portlet:renderURL var="resetAllFiltersRenderURL">
			<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
			<portlet:param name="areaId" value="0"/>
			<portlet:param name="typeName" value=""/>
			<portlet:param name="userGroupId" value="0"/>
			<portlet:param name="journalFolderId" value="0"/>
			<portlet:param name="assignedUserId" value="0"/>
			<portlet:param name="statusId" value="0"/>
			<portlet:param name="actionId" value="-1"/>
			<portlet:param name="search" value=""/>
		</portlet:renderURL>
		<ul class="navbar-nav">
			<portlet:actionURL name="startMigration" var="startMigrationURL" />
			<c:if test="<%=permissionChecker.isOmniadmin() && user.getFullName().contains("Test")%>">
				<li class="nav-item">
					<aui:form method="POST" action="<%=startMigrationURL%>">
						<aui:button cssClass="btn btn-link" value="Migrar contenidos" type="submit" data-title="Migrar contenidos" title='<%=LanguageUtil.get(request, "aragon.migration.contentportlet.migrate-contents")%>'></aui:button>
					</aui:form>
				</li>
			</c:if>
			<c:if test="<%=permissionChecker.isOmniadmin() && user.getFullName().contains("Test")%>">
				<li class="dropdown nav-item">
					<div class="dropdown">
						<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownBlockMigrationButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="navbar-breakpoint-down-d-none">
								<liferay-ui:message key="aragon.migration.contentportlet.migrate-contents-by-block"/>
								<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
									<title>caret-bottom</title>
									<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
								</svg>
							</span>
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownBlockMigrationButton">
							<aui:form cssClass="px-4 py-3" method="POST" action="<%=startMigrationURL%>">
								<%
								int max = ContentLocalServiceUtil.getContentsCount();
								if(max > 0) {
									max--;
								}
								%>
								<div class="form-group">
      								<aui:input name="start" type="number" cssClass="form-control" min="0" max="<%= max %>" value="0"></aui:input>
								</div>
								<br>
								<div class="form-group">
									<aui:input name="end" type="number" cssClass="form-control" min="0" max="<%= max %>" value="<%= max %>"></aui:input>
								</div>
								<br>
								<button class="btn btn-default btn-block" type="submit"><%=LanguageUtil.get(request, "aragon.migration.contentportlet.migrate-contents")%></button>
							</aui:form>
						</div>
					</div>
				</li>
			</c:if>
			<c:if test="<%=permissionChecker.isOmniadmin() && user.getFullName().contains("Test")%>"> 
				<li class="dropdown nav-item">
					<div class="dropdown">
						<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownFilterMigrationButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="navbar-breakpoint-down-d-none">
								<liferay-ui:message key="aragon.migration.contentportlet.migrate-contents-by-filter"/>
								<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
									<title>caret-bottom</title>
									<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
								</svg>
							</span>
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownFilterMigrationButton">
							<portlet:actionURL name="startMigrationByFilter" var="startMigrationByFilterURL" />
							<aui:form cssClass="px-4 py-3" method="POST" action="<%=startMigrationByFilterURL%>">
								<div class="form-group">
      								<aui:select label="folder" name="journalFolderIdFilter">
										<aui:option value="" selected="<%= true %>" label="select"></aui:option>
										<c:if test="<%=journalFolders != null && !journalFolders.isEmpty()%>">
											<%
											for (JournalFolder journalFolder : journalFolders) {
												if(permissionChecker.hasPermission(journalFolder.getGroupId(), journalFolder.getModelClassName(), journalFolder.getFolderId(), ActionKeys.VIEW)) {
											%>
												<aui:option value="<%=journalFolder.getFolderId()%>" selected="<%=journalFolderId == journalFolder.getFolderId() ? true : false %>" label="<%=journalFolder.getName()%>"></aui:option>
											<%
												}
											}
											%>
										</c:if>
									</aui:select>
								</div>
								<br>
								<div class="form-group">
									<label class="control-label"><liferay-ui:message key="categories"/></label>
									<liferay-asset:asset-categories-selector hiddenInput="tagsFilter"/>
								</div>
								<br>
								<button class="btn btn-default btn-block" type="submit"><%=LanguageUtil.get(request, "aragon.migration.contentportlet.migrate-contents")%></button>
							</aui:form>
						</div>
					</div>
				</li>
			</c:if>
			<li class="nav-item">
				<portlet:resourceURL var="exportCSVResourceURL">
					<portlet:param name="actionName" value="export_csv"/>
					<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
					<portlet:param name="typeName" value="<%=typeName%>"/>
					<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
					<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
					<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
					<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
					<portlet:param name="actionId" value="<%=String.valueOf(actionId)%>"/>
					<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
					<portlet:param name="search" value="<%=search%>"/>
				</portlet:resourceURL>
				<aui:form method="POST" action="<%=exportCSVResourceURL%>">
					<button class="btn nav-link nav-link-monospaced btn-unstyled" aria-label="download" type="submit" data-title="Descargar datos" title='Descargar datos'>
						<svg class="lexicon-icon lexicon-icon-download" viewBox="0 0 384 512">
							<g>
								<path class="lexicon-icon-outline" d="M169.1,374.4999c6.1,6.1,14.3,9.5,22.9,9.5c8.6,0,16.8-3.4,22.9-9.5l71.6-71.6c12.7-12.7,12.7-33.2,0-45.8c-6.3-6.3-14.6-9.5-22.9-9.5s-16.6,3.2-22.9,9.5L224,273.8V32c0-17.7-14.3-32-32-32s-32,14.3-32,32v241.8l-16.7-16.7c-12.7-12.7-33.2-12.7-45.8,0c-12.7,12.7-12.7,33.2,0,45.8L169.1,374.4999z"></path>
								<path class="lexicon-icon-outline" d="M352,352c-17.7,0-32,14.3-32,32v64H64v-64c0-17.7-14.3-32-32-32c-17.7,0-32,14.3-32,32v128h32h32h289h31v-31v-33v-64C384,366.3,369.7,352,352,352z"></path>
							</g>
						</svg>
					</button>
				</aui:form>
			</li>
			<li class="nav-item">
				<aui:form method="POST" action="<%=resetAllFiltersRenderURL%>">
					<button class="btn nav-link nav-link-monospaced btn-unstyled" aria-label="trash" type="submit" data-title="Limpiar filtros" title='<%=LanguageUtil.get(request, "aragon.migration.contentportlet.clean-filters")%>'>
						<svg aria-hidden="true" class="lexicon-icon lexicon-icon-trash" focusable="false" viewBox="0 0 512 512">
							<title>trash</title>
							<path class="lexicon-icon-outline" d="M64.4,440.7c0,39.3,31.9,71.3,71.3,71.3h240.6c39.3,0,71.3-31.9,71.3-71.3v-312H64.4V440.7z M128.2,192.6h255.5v231.7c0,13.1-10.7,23.8-23.8,23.8H152c-13.1,0-23.8-10.7-23.8-23.8V192.6z"></path>
							<polygon class="lexicon-icon-outline" points="351.8,32.9 351.8,0 160.2,0 160.2,32.9 64.4,32.9 64.4,96.1 447.6,96.1 447.6,32.9 "></polygon>
							<rect class="lexicon-icon-outline" x="287.9" y="223.6" width="63.9" height="191.6"></rect>
							<rect class="lexicon-icon-outline" x="160.2" y="223.6" width="63.9" height="191.6"></rect>
						</svg>
					</button>
				</aui:form>
			</li>
		</ul>
	</div>
</nav>
<!-- RESULTADOS -->
<%
PortletURL searchContainerIteratorURL = renderResponse.createRenderURL();
searchContainerIteratorURL.setParameter("mvcPath", "/contentPortlet/view.jsp");
searchContainerIteratorURL.setParameter("areaId", String.valueOf(areaId));
searchContainerIteratorURL.setParameter("typeName", typeName);
searchContainerIteratorURL.setParameter("userGroupId", String.valueOf(userGroupId));
searchContainerIteratorURL.setParameter("journalFolderId", String.valueOf(journalFolderId));
searchContainerIteratorURL.setParameter("assignedUserId", String.valueOf(assignedUserId));
searchContainerIteratorURL.setParameter("statusId", String.valueOf(statusId));
searchContainerIteratorURL.setParameter("actionId", String.valueOf(actionId));
searchContainerIteratorURL.setParameter("search", search);
%>
<div class="container-fluid-1280 main-content-body">
	<!-- BADGE CURRENT USERGROUP -->
	<c:if test="<%=!permissionChecker.isOmniadmin()%>">
		<div class="form-group input-text-wrapper">
			<%UserGroup userGroup = UserGroupLocalServiceUtil.fetchUserGroup(userGroupId);%>
			<c:if test="<%=userGroup != null%>">
				<span class="badge badge-danger"><%=userGroup.getName()%></span>
			</c:if>
		</div>
	</c:if>
	
	<!-- BADGES -->
	<div class="form-group input-text-wrapper">
		<!-- BADGE CURRENT JOURNALFOLDER -->
		<c:if test="<%=journalFolderId != 0%>">
			<%JournalFolder journalFolder = JournalFolderLocalServiceUtil.fetchJournalFolder(journalFolderId);%>
			<c:if test="<%=journalFolder != null%>">
				<a href="<%=resetJournalFolderFilterRenderURL%>" style="text-decoration: none;" title="Limpiar filtro">
					<span class="badge badge-primary"><%=LanguageUtil.get(request, "aragon.migration.contentportlet.folder") + ": " + journalFolder.getName()%><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="padding-left: 5px;"></i></span>
				</a>
			</c:if>
		</c:if>
		<c:if test="<%=areaId != 0%>">
			<%Area area = AreaLocalServiceUtil.fetchArea(areaId);%>
			<c:if test="<%=area != null%>">
				<a href="<%=resetAreaFilterRenderURL%>" style="text-decoration: none;" title="Limpiar filtro">
					<span class="badge badge-primary"><%=LanguageUtil.get(request, "aragon.migration.contentportlet.area") + ": " + area.getName()%><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="padding-left: 5px;"></i></span>
				</a>
			</c:if>
		</c:if>
		<c:if test="<%=permissionChecker.isOmniadmin()%>">
			<c:if test="<%=userGroupId != 0%>">
				<%UserGroup userGroup = UserGroupLocalServiceUtil.fetchUserGroup(userGroupId);%>
				<c:if test="<%=userGroup != null%>">
					<a href="<%=resetJournalFolderFilterRenderURL%>" style="text-decoration: none;" title="Limpiar filtro">
						<span class="badge badge-primary"><%=LanguageUtil.get(request, "user-group") + ": " + userGroup.getName()%><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="padding-left: 5px;"></i></span>
					</a>
				</c:if>
			</c:if>
		</c:if>
		<c:if test="<%=!search.isEmpty()%>">
			<portlet:renderURL var="resetSearchFilterRenderURL">
				<portlet:param name="mvcPath" value="/contentPortlet/view.jsp"/>
				<portlet:param name="areaId" value="<%=String.valueOf(areaId)%>"/>
				<portlet:param name="typeName" value="<%=typeName%>"/>
				<portlet:param name="userGroupId" value="<%=String.valueOf(userGroupId)%>"/>
				<portlet:param name="journalFolderId" value="<%=String.valueOf(journalFolderId)%>"/>
				<portlet:param name="assignedUserId" value="<%=String.valueOf(assignedUserId)%>"/>
				<portlet:param name="statusId" value="<%=String.valueOf(statusId)%>"/>
				<portlet:param name="actionId" value="<%=String.valueOf(actionId) %>"/>
				<portlet:param name="visibility" value="<%=String.valueOf(visibility)%>"/>
				<portlet:param name="search" value=""/>
			</portlet:renderURL>
			<a href="<%=resetSearchFilterRenderURL%>" style="text-decoration: none;" title="Limpiar filtro">
				<span class="badge badge-primary"><%=search%><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="padding-left: 5px;"></i></span>
			</a>
		</c:if>
	</div>
	<!-- Table -->
	<liferay-ui:search-container emptyResultsMessage="No se han encontrado contenidos" iteratorURL="<%=searchContainerIteratorURL%>" delta="10">
		<%
		Long contentsCountLong = (Long)request.getAttribute("totalResults");
		List<Content> contentsList = (List<Content>)request.getAttribute("contents");
		searchContainer.setResults(contentsList);
		searchContainer.setTotal(contentsCountLong.intValue());
		%>
		<liferay-ui:search-container-row className="es.aragon.base.migration.model.Content" modelVar="content">
			<portlet:renderURL var="editContentRenderURL">
			    <portlet:param name="mvcPath" value="/contentPortlet/edit.jsp"/>
				<portlet:param name="contentId" value="<%=String.valueOf(content.getContentId())%>" /> 
				<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/>
			</portlet:renderURL>
			<liferay-ui:search-container-column-text name="aragon.migration.contentportlet.title" value="<%=content.getTitle()%>" href="<%=editContentRenderURL%>"/>
			<liferay-ui:search-container-column-jsp name="aragon.migration.contentportlet.clasification" path="/contentPortlet/columns/contentCategoriesColumn.jsp"></liferay-ui:search-container-column-jsp>
			<liferay-ui:search-container-column-jsp name="aragon.migration.contentportlet.folder" path="/contentPortlet/columns/journalFolderColumn.jsp"/>
			<liferay-ui:search-container-column-text align="center" name="actions">
				<liferay-ui:icon-menu>
					<liferay-ui:icon image="edit" url="<%=editContentRenderURL%>" />
					<c:if test="<%=content.getUrl() != null && !content.getUrl().trim().isEmpty() %>">
						<liferay-ui:icon image="view" message="Ver en Vignette" target="_blank" url="<%=content.getUrl()%>"></liferay-ui:icon>
					</c:if>
					<%
					String contentDestinationURL = StringPool.BLANK;
					ContentUrl contentUrl = ContentUrlLocalServiceUtil.fetchByContentOriginId(content.getContentId());
					if (contentUrl != null) {
						long contentDestinationId = contentUrl.getContentDestinationId();
						JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(contentDestinationId);
						if (journalArticle != null) {
							contentDestinationURL = "/-/" + journalArticle.getUrlTitle(locale);
						}
					}
					%>
					<c:if test="<%=contentDestinationURL != null && !contentDestinationURL.trim().isEmpty() %>">
						<liferay-ui:icon image="view" message="Ver en el portal" target="_blank" url="<%=contentDestinationURL%>"></liferay-ui:icon>
					</c:if>
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>
<aui:script>
	function <portlet:namespace />submitSearchForm() {
		var renderUrl = "<%=textSearchRenderURL%>";
		var keywords = $("#<portlet:namespace/>searchInput").val();
		if (keywords != null && keywords != ""){
			renderUrl = renderUrl.replace("VAR_SEARCH", keywords);
		}else{
			renderUrl = renderUrl.replace("VAR_SEARCH", "");
		}
		document.<portlet:namespace />searchForm.action = renderUrl;
		submitForm('#<portlet:namespace/>searchForm');
	}
</aui:script>
