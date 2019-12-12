<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.petra.string.StringPool"%>

<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Content content = (Content) row.getObject();

String userGroupName = StringPool.BLANK;
if (content.getUserGroupId() > 0) {
	UserGroup userGroup = UserGroupLocalServiceUtil.fetchUserGroup(content.getUserGroupId());
	if (userGroup != null) {
		userGroupName = userGroup.getName();
	}
}
%>

<%=userGroupName%>