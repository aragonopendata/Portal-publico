<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>

<%@ page import="es.aragon.base.crawler.admin.constants.MVCCommandNames" %>
<%@ page import="es.aragon.base.crawler.model.Page" %>
<%@page import="es.aragon.base.crawler.model.RootPage"%>
<%@page import="es.aragon.base.crawler.service.RootPageLocalServiceUtil"%>
<%@page import="es.aragon.base.crawler.service.PageLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />
