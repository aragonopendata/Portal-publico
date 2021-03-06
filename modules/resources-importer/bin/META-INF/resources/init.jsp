<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/expando" prefix="liferay-expando" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/soy" prefix="soy" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/trash" prefix="liferay-trash" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@page import="java.util.Date"%>
<%@page import="com.liferay.dynamic.data.mapping.service.DDMStructureLocalService"%>
<%@page import="com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalService"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.dynamic.data.mapping.model.DDMTemplate"%>
<%@page import="com.liferay.dynamic.data.mapping.model.DDMStructure"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="es.aragon.base.resources.importer.util.TemplateUtil"%>
<%@page import="es.aragon.base.resources.importer.util.StructureUtil"%>
<%@page import="es.aragon.base.resources.importer.util.ApplicationDisplayTemplateUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	DDMTemplateLocalService _ddmTemplateLocalService = (DDMTemplateLocalService)renderRequest.getAttribute(DDMTemplateLocalService.class.getName());
	DDMStructureLocalService _ddmStructureLocalService = (DDMStructureLocalService)renderRequest.getAttribute(DDMStructureLocalService.class.getName());
%>