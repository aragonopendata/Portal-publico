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

<%-- Inicio --%>
<%-- Import para poder utilizar el metodo escape de la clase HtmlUtil --%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%-- Fin --%>
<%@ include file="/journal_article/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.journal.taglib#/journal_article/page.jsp#pre" />

<%
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute("liferay-journal:journal-article:articleDisplay");
boolean showTitle = GetterUtil.getBoolean((String)request.getAttribute("liferay-journal:journal-article:showTitle"));
%>

<%--Inicio --%>
<%
//**** Escapamos el texto (articleDisplay.getTitle()) para que sea seguro utilizarlo en un contexto HTML ****//
String articleDisplayTitleEncoded = HtmlUtil.escape(articleDisplay.getTitle());
//**** Utilizamos esta cadena codificada para pasarla al atributo del siguiente div *************************//
%>
<div class="clearfix journal-content-article" data-analytics-asset-id="<%= articleDisplay.getArticleId() %>" data-analytics-asset-title="<%= articleDisplayTitleEncoded %>" data-analytics-asset-type="web-content">
<%-- Fin --%>	
	<c:if test="<%= showTitle %>">
		<%= articleDisplay.getTitle() %>
	</c:if>

	<%= articleDisplay.getContent() %>
</div>

<liferay-util:dynamic-include key="com.liferay.journal.taglib#/journal_article/page.jsp#post" />