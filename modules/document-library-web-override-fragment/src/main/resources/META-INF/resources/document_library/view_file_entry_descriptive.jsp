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

<%@page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.liferay.portal.kernel.repository.model.Folder"%>
<%@page import="com.liferay.taglib.ui.BreadcrumbTag"%>
<%@ include file="/document_library/init.jsp" %>
<%@page import="es.aragon.base.document_library_web_override_fragment.util.DocumentLibraryNavigationDocumentUtil"%>

<%
DocumentLibraryNavigationDocumentUtil documentLibraryAragonUtil = new  DocumentLibraryNavigationDocumentUtil();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object result = row.getObject();

FileEntry fileEntry = null;
FileShortcut fileShortcut = null;

if (result instanceof AssetEntry) {
	AssetEntry assetEntry = (AssetEntry)result;

	if (assetEntry.getClassName().equals(DLFileEntryConstants.getClassName())) {
		fileEntry = DLAppLocalServiceUtil.getFileEntry(assetEntry.getClassPK());

		fileEntry = fileEntry.toEscapedModel();
	}
	else {
		fileShortcut = DLAppLocalServiceUtil.getFileShortcut(assetEntry.getClassPK());

		fileShortcut = fileShortcut.toEscapedModel();
	}
}
else if (result instanceof FileEntry) {
	fileEntry = (FileEntry)result;
}
else if (result instanceof FileShortcut) {
	fileShortcut = (FileShortcut)result;

	fileShortcut = fileShortcut.toEscapedModel();

	fileEntry = DLAppLocalServiceUtil.getFileEntry(fileShortcut.getToFileEntryId());
}

fileEntry = fileEntry.toEscapedModel();

FileVersion latestFileVersion = fileEntry.getFileVersion();

if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
	latestFileVersion = fileEntry.getLatestFileVersion();
}

Date modifiedDate = latestFileVersion.getModifiedDate();

String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - modifiedDate.getTime(), true);

PortletURL rowURL = liferayPortletResponse.createRenderURL();

rowURL.setParameter("mvcRenderCommandName", "/document_library/view_file_entry");
rowURL.setParameter("redirect", HttpUtil.removeParameter(currentURL, liferayPortletResponse.getNamespace() + "ajax"));
rowURL.setParameter("fileEntryId", String.valueOf(fileEntry.getFileEntryId()));
%>

<h5 class="text-default">
	<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(latestFileVersion.getUserName()), modifiedDateDescription} %>" key="x-modified-x-ago" />
</h5>
<%--INICIO AGREGAMOS LA RUTA EN LA QUE SE ENCUENTRA CADA DOCUEMENTO --%>

<%
String pathFullocument="";
DLFileEntry dlFile = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntry.getFileEntryId());
if(dlFile != null){
	pathFullocument = documentLibraryAragonUtil.getFullPath2(dlFile);
}
%>
<h4><%=pathFullocument %></h4>
<%--  FIN DE LA MODIFICACI�N --%>
<h4>
	<aui:a href="<%= rowURL.toString() %>">
		<%= latestFileVersion.getTitle() %>
	</aui:a>

	<c:if test="<%= fileEntry.hasLock() || fileEntry.isCheckedOut() %>">
		<span>
			<aui:icon cssClass="icon-monospaced" image="lock" markupView="lexicon" message="locked" />
		</span>
	</c:if>
</h4>

<span class="h5 text-default">
	<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= latestFileVersion.getStatus() %>" />
</span>

<c:if test="<%= latestFileVersion.getModel() instanceof DLFileVersion %>">

	<%
	DLFileVersion latestDLFileVersion = (DLFileVersion)latestFileVersion.getModel();

	DLFileEntryType dlFileEntryType = latestDLFileVersion.getDLFileEntryType();
	%>

	<span class="h5 text-default">
		<%= HtmlUtil.escape(dlFileEntryType.getName(locale)) %>
	</span>
</c:if>