<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@page import="com.liferay.portal.kernel.webserver.WebServerServletTokenUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.DateUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.URLEncoder"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="java.util.List"%>
<%@page import="es.aragon.base.social_network.sb.model.SocialNetwork"%>
<%@page import="es.aragon.base.social_network.sb.service.SocialNetworkLocalService"%>
<%@page import="es.aragon.base.social_network.web.portlet.constants.SocialNetworkWebPortletKeys"%>
<%@page import="es.aragon.base.social_network.web.portlet.portlet.SocialNetworkWebPortlet"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	SocialNetworkLocalService _socialNetworkLocalService = (SocialNetworkLocalService)renderRequest.getAttribute(SocialNetworkLocalService.class.getName());
%>