<%@page import="com.liferay.portal.kernel.dao.search.DisplayTerms"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="java.util.List"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesDocumentUtils"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesContentUtils"%>

<%@ include file="init.jsp" %>

<%
	String menuId = ParamUtil.getString(request, "menuId", "Mapa web contenidos web");
	Boolean isAdmin = permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId()) || permissionChecker.isOmniadmin();
%>

<%@ include file="/navBar.jsp" %>

<c:if test="<%=menuId.equals("Mapa web contenidos web")%>">
	<%@ include file="/webContentNav.jsp" %>
</c:if>

<c:if test="<%=menuId.equals("Mapa web documentos y multimendia")%>">
	<%@ include file="/dlFilesNav.jsp" %>
</c:if>

<c:if test="<%=isAdmin %>">
	<c:if test="<%=menuId.equals("Historial de usuarios") %>">
		<%@ include file="/userLogNav.jsp" %>
	</c:if>
</c:if>

<c:if test="<%=menuId.equals("Buscador de URL's") %>">
	<%@ include file="/urlFinderNav.jsp" %>
</c:if>

<c:if test="<%=isAdmin %>">
	<c:if test="<%=menuId.equals("Configuracion") %>">
		<%@ include file="/semaphoreNav.jsp" %>
	</c:if>
</c:if>


<script type="text/javascript">

	function buildOptions(id, folderArray, depth) {
		var prefix = '';
		for(var count = 0; count < depth; count ++) {
			prefix += '----';
	    }

		for(var iterator in folderArray) {
			$('#<portlet:namespace/>' + id).append($('<option>', { 
				value: folderArray[iterator].id,
				text : prefix + folderArray[iterator].name 
			}));
			buildOptions(id, folderArray[iterator].nested, depth + 1);
	    }
	}
</script>