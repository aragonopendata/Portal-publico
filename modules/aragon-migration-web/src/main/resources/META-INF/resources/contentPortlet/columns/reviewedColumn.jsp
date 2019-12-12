<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>

<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Content content = (Content) row.getObject();

String revisadoLabel = LanguageUtil.get(request, "no");
if (content.getStatusId() == 2) {
	revisadoLabel = LanguageUtil.get(request, "yes");
}
%>

<%=revisadoLabel%>

<c:if test="<%=Validator.isNotNull(content.getComments())%>">
	<liferay-ui:icon-help message="<%=content.getComments()%>"/>
</c:if>