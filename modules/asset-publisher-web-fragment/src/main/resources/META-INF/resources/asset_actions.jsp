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
<%@page import="es.aragon.base.asset_publisher_web_fragment.util.AssetPublisherWebFragmentUtil"%>
<%@page import="com.liferay.journal.constants.JournalPortletKeys"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="javax.portlet.PortletURL"%>
<%-- END - Custom imports --%>

<%@ include file="/init.jsp" %>

<%
boolean showIconLabel = ((Boolean)request.getAttribute("view.jsp-showIconLabel")).booleanValue();
AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");
boolean showEditURL = ParamUtil.getBoolean(request, "showEditURL", true);
PortletURL editPortletURL = null;
if (showEditURL && assetRenderer.hasEditPermission(permissionChecker)) {
	PortletURL redirectURL = liferayPortletResponse.createLiferayPortletURL(plid, portletDisplay.getId(), PortletRequest.RENDER_PHASE, false);
	redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp");
	String fullContentRedirect = (String)request.getAttribute("view.jsp-fullContentRedirect");
	if (fullContentRedirect != null) {
		redirectURL.setParameter("redirect", fullContentRedirect);
	} else {
		redirectURL.setParameter("redirect", currentURL);
	}
	redirectURL.setWindowState(LiferayWindowState.POP_UP);
	editPortletURL = assetRenderer.getURLEdit(liferayPortletRequest, liferayPortletResponse, LiferayWindowState.POP_UP, redirectURL);
}
List<AssetEntryAction> assetEntryActions = assetPublisherDisplayContext.getAssetEntryActions(assetEntry.getClassName());
%>

<%-- START - Custom permissions check --%>
<%
boolean customCanViewEditButton = Boolean.FALSE;
boolean customCanViewAssetEntryActions = Boolean.FALSE;
boolean customCanViewInFolderButton = Boolean.FALSE;
if (permissionChecker.isGroupMember(themeDisplay.getScopeGroupId())) {
	boolean userHasJournalFolderViewPermission = AssetPublisherWebFragmentUtil.userHasJournalFolderViewPermission(themeDisplay.getUserId(), assetEntry, themeDisplay.getPermissionChecker());	
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
	customCanViewAssetEntryActions = Boolean.TRUE;
	customCanViewInFolderButton = Boolean.TRUE;
}
%>
<%-- END - Custom permissions check --%>
<%-- START - Sets condition for showing the actions menu --%>
<%--<c:if test="<%= (editPortletURL != null) || ListUtil.isNotEmpty(assetEntryActions) %>">--%>
<c:if test="<%= (customCanViewEditButton || customCanViewInFolderButton || customCanViewAssetEntryActions) && (editPortletURL != null) || ListUtil.isNotEmpty(assetEntryActions) %>">
<%-- END - Sets condition for showing the actions menu --%>
	<div class="pull-right">
		<liferay-ui:icon-menu cssClass="visible-interaction" direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
			<%-- START - Sets condition for showing the edit option --%>
			<%-- <c:if test="<%= editPortletURL != null %>"> --%>
			<c:if test="<%= customCanViewEditButton && editPortletURL != null %>">
			<%-- END - Sets condition for showing the edit option --%>
				<%
				editPortletURL.setParameter("hideDefaultSuccessMessage", Boolean.TRUE.toString());
				editPortletURL.setParameter("showHeader", Boolean.FALSE.toString());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("destroyOnHide", true);
				data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
				data.put("title", LanguageUtil.format(request, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale)), false));
				%>
				<liferay-ui:icon data="<%= data %>" message='<%= showIconLabel ? LanguageUtil.format(request, "edit-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(assetRenderer.getTitle(locale))}, false) : LanguageUtil.format(request, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale)), false) %>' method="get" url="<%= editPortletURL.toString() %>" useDialog="<%= true %>"/>
			</c:if>
			<%-- START - Sets condition for showing the actions menu --%>
			<%--<c:if test="<%= ListUtil.isNotEmpty(assetEntryActions) %>">--%>
			<c:if test="<%= customCanViewAssetEntryActions && ListUtil.isNotEmpty(assetEntryActions) %>">
			<%-- END - Sets condition for showing the actions menu --%>
				<%
				for (AssetEntryAction assetEntryAction : assetEntryActions) {
					if (!assetEntryAction.hasPermission(permissionChecker, assetRenderer)) {
						continue;
					}
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("destroyOnHide", true);
					data.put("title", assetEntryAction.getDialogTitle(locale));
				%>
					<liferay-ui:icon data="<%= data %>" message="<%= assetEntryAction.getMessage(locale) %>" method="get" url="<%= assetEntryAction.getDialogURL(request, assetRenderer) %>" useDialog="<%= true %>"/>
				<%
				}
				%>
			</c:if>
			<%-- START - Custom view in folder button  --%>
			<c:if test="<%=customCanViewInFolderButton%>">
				<%
				//Go to article url
				PortletURL goToArticleUrl = null;
				if (assetEntry.getClassName().equals("com.liferay.journal.model.JournalArticle")) {
					JournalArticle article = JournalArticleLocalServiceUtil.fetchLatestArticle(assetEntry.getClassPK(), 0);
					if (article != null){
						goToArticleUrl = PortalUtil.getControlPanelPortletURL(liferayPortletRequest, "com_liferay_journal_web_portlet_JournalPortlet", "RENDER_PHASE");
						goToArticleUrl.setWindowState(LiferayWindowState.MAXIMIZED);
						goToArticleUrl.setPortletMode(LiferayPortletMode.VIEW);
						goToArticleUrl.setParameter("folderId", String.valueOf(article.getFolderId()));
						goToArticleUrl.setParameter("groupId", String.valueOf(article.getGroupId()));
					}
				}
				%>
				<c:if test="<%=goToArticleUrl != null%>">
					<liferay-ui:icon message="aragon-go-to-article" url="<%=goToArticleUrl.toString()%>" target="_blank"/>
				</c:if>
			</c:if>
			<%-- END - Custom view in folder button  --%>
		</liferay-ui:icon-menu>
	</div>
</c:if>