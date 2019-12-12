<%-- 
	Actions menu for a single page.
--%>

<%@ include file="/init.jsp"%>

<c:set var="crawledPage" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />

<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon"
	message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">

	<%-- View action. --%>
	<c:if test="${rootPageId eq 0}">
		<c:if test="${pagePermissionChecker.contains(permissionChecker, scopeGroupId, crawledPage.pageId, 'VIEW' )}">
			<portlet:renderURL var="viewPageURL">
				<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.VIEW_PAGE%>" />
				<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
				<portlet:param name="rootPageId" value="${rootPageService.getRootPageId(crawledPage.pageId)}" />
				<portlet:param name="rootPageAlias" value="${rootPageService.getAlias(crawledPage.pageId)}" />
			</portlet:renderURL>

			<liferay-ui:icon message="view" url="${viewPageURL}" />
		</c:if>
	</c:if>

	<%-- Edit action. --%>
	<c:if test="${pagePermissionChecker.contains(permissionChecker, scopeGroupId, crawledPage.pageId, 'UPDATE' )}">
		<portlet:renderURL var="editPageURL">
			<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.EDIT_PAGE %>" />
			<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
			<portlet:param name="pageId" value="${crawledPage.pageId}" />
		</portlet:renderURL>

		<liferay-ui:icon message="edit" url="${editPageURL}" />
	</c:if>
	
	<%-- Re-crawl action. --%>
	<c:if test="${rootPageId eq 0}">
		<c:if test="${pagePermissionChecker.contains(permissionChecker, scopeGroupId, crawledPage.pageId, 'UPDATE')}">
	
			<portlet:actionURL name="<%=MVCCommandNames.RECRAWL_PAGE %>" var="recrawlPageURL">
				<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.RECRAWL_PAGE %>" />
				<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
				<portlet:param name="pageId" value="${crawledPage.pageId}" />
			</portlet:actionURL>
	
			<liferay-ui:icon message="Re-crawl" url="${recrawlPageURL}" />
		</c:if>
	</c:if>
	
	<%-- Delete action. --%>
	<c:if test="${pagePermissionChecker.contains(permissionChecker, scopeGroupId, crawledPage.pageId, 'DELETE')}">

		<portlet:actionURL name="<%=MVCCommandNames.DELETE_PAGE %>" var="deletePageURL">
			<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.DELETE_PAGE %>" />
			<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
			<portlet:param name="pageId" value="${crawledPage.pageId}" />
		</portlet:actionURL>

		<liferay-ui:icon message="delete" url="${deletePageURL}" />
	</c:if>
</liferay-ui:icon-menu>