<%@page import="com.liferay.journal.model.JournalFolder"%>
<%@page import="com.liferay.journal.service.JournalFolderLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.petra.string.StringPool"%>

<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Content content = (Content) row.getObject();

String folderName = StringPool.BLANK;
if (content.getJournalFolderId() > 0) {
	JournalFolder journalFolder = JournalFolderLocalServiceUtil.fetchJournalFolder(content.getJournalFolderId());
	if (journalFolder != null) {
		folderName = journalFolder.getName();
	}
}
%>

<%=folderName%>