<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>
<%-- START - Custom imports  --%>
<%@page import="es.aragon.base.web_content_display_action_fragment.util.WebContentDisplayActionFragmentUtil"%>
<%@page import="com.liferay.journal.constants.JournalPortletKeys"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.PortletRequest"%>
<%-- END - Custom imports --%>

<%@ include file="/dynamic_include/init.jsp" %>

<%JournalArticle article = journalContentDisplayContext.getArticle();%>

<%-- START - Custom permissions check --%>
<%
boolean customCanViewEditButton = Boolean.FALSE;
boolean customCanViewInFolderButton = Boolean.FALSE;
boolean customCanViewAssetEntryActions = Boolean.FALSE;
if (permissionChecker.isGroupMember(themeDisplay.getScopeGroupId())) {
	boolean userHasJournalFolderViewPermission = WebContentDisplayActionFragmentUtil.userHasJournalFolderViewPermission(themeDisplay.getUserId(), article, themeDisplay.getPermissionChecker());	
	if (userHasJournalFolderViewPermission) {
		customCanViewEditButton = Boolean.TRUE;
		customCanViewInFolderButton = Boolean.TRUE;
	}
}
if (permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())) {
	customCanViewEditButton = Boolean.TRUE;
	customCanViewInFolderButton = Boolean.TRUE;
}
if (permissionChecker.isOmniadmin()) {
	customCanViewEditButton = Boolean.TRUE;
	customCanViewInFolderButton = Boolean.TRUE;
	customCanViewAssetEntryActions = Boolean.TRUE;
}
%>
<%-- END - Custom permissions check --%>

<div class="visible-interaction">
	<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="web-content-options" showWhenSingleIcon="<%= true %>">
		<%-- START - Sets condition for showing the edit option --%>
		<%--<c:if test="<%= journalContentDisplayContext.isShowEditArticleIcon() %>">--%>
		<c:if test="<%= customCanViewEditButton && journalContentDisplayContext.isShowEditArticleIcon() %>">
		<%-- END - Sets condition for showing the edit option --%>
			<%
			JournalArticle latestArticle = journalContentDisplayContext.getLatestArticle();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("destroyOnHide", true);
			data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
			data.put("title", HtmlUtil.escape(latestArticle.getTitle(locale)));
			%>
			<liferay-ui:icon data="<%= data %>" id="editWebContentIcon" message="edit-web-content" url="<%= journalContentDisplayContext.getURLEdit() %>" useDialog="<%= true %>"/>
		</c:if>
		<%-- START - Adds condition for showing the actions menu --%>
		<c:if test="<%= customCanViewAssetEntryActions %>">
		<%-- END - Adds condition for showing the actions menu --%>
			<c:if test="<%= journalContentDisplayContext.isShowEditTemplateIcon() %>">
				<%
				DDMTemplate ddmTemplate = journalContentDisplayContext.getDDMTemplate();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("destroyOnHide", true);
				data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
				data.put("title", HtmlUtil.escape(ddmTemplate.getName(locale)));
				%>
				<liferay-ui:icon data="<%= data %>" id="editTemplateIcon" message="edit-template" url="<%= journalContentDisplayContext.getURLEditTemplate() %>" useDialog="<%= true %>"/>
			</c:if>
			<c:if test="<%= JournalArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL modelResource="<%= JournalArticle.class.getName() %>" modelResourceDescription="<%= HtmlUtil.escape(article.getTitle(locale)) %>" resourcePrimKey="<%= String.valueOf(article.getResourcePrimKey()) %>" var="permissionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>"/>
				<liferay-ui:icon message="permissions" method="get" url="<%= permissionsURL %>" useDialog="<%= true %>"/>
			</c:if>
		<%-- START - Adds condition for showing the actions menu --%>
		</c:if>
		<%-- END - Adds condition for showing the actions menu --%>
		<%-- START - Custom view in folder button  --%>
		<c:if test="<%=customCanViewInFolderButton%>">
			<%
			PortletRequest portletRequest = (PortletRequest) request.getAttribute("javax.portlet.request");
			PortletURL goToArticleUrl = PortalUtil.getControlPanelPortletURL(
			PortalUtil.getLiferayPortletRequest(portletRequest), JournalPortletKeys.JOURNAL, PortletRequest.RENDER_PHASE);
			goToArticleUrl.setWindowState(LiferayWindowState.MAXIMIZED);
			goToArticleUrl.setPortletMode(LiferayPortletMode.VIEW);
			goToArticleUrl.setParameter("folderId", String.valueOf(article.getFolderId()));
			goToArticleUrl.setParameter("groupId", String.valueOf(article.getGroupId()));
			%>
			<liferay-ui:icon message="aragon-go-to-article" url="<%=goToArticleUrl.toString()%>" target="_blank"/>
		</c:if>
		<%-- END - Custom view in folder button  --%>
	</liferay-ui:icon-menu>
</div>