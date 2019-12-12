<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>

<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Content content = (Content) row.getObject();

String migrarLabel = StringPool.BLANK;
if (content.getActionId() == 0) {
	migrarLabel = LanguageUtil.get(request, "no");
}
if (content.getActionId() == 1) {
	migrarLabel = LanguageUtil.get(request, "yes");
}
if (content.getActionId() == 2) {
	migrarLabel = "En espera";
}
%>
<%=migrarLabel%>