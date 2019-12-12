<%@ include file="/init.jsp" %>

<style>
.custom-truncate-text {
	max-width: 250px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	display: block;
}
</style>
<clay:management-toolbar
	actionDropdownItems="${pageManagementToolbarDisplayContext.getActionDropdownItems()}"
	clearResultsURL="${pageManagementToolbarDisplayContext.getSearchActionURL()}"
	componentId="pagesManagementToolbar"
	creationMenu="${pageManagementToolbarDisplayContext.getCreationMenu()}"
	disabled="${pagesCount eq 0}"
	filterDropdownItems="${pageManagementToolbarDisplayContext.getFilterDropdownItems()}"
	itemsTotal="${pagesCount}"
	searchActionURL="${pageManagementToolbarDisplayContext.getSearchActionURL()}"
	searchContainerId="pageEntries"
	searchFormName="searchFm"		
	showInfoButton="false"
	sortingOrder="${pageManagementToolbarDisplayContext.getOrderByType()}"
	sortingURL="${pageManagementToolbarDisplayContext.getSortingURL()}"
/>

<portlet:renderURL var="viewPagesURL">
	<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.VIEW_PAGES %>" />
	<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
</portlet:renderURL>

<div class="crawler-pages container-fluid-1280">

	<c:if test="${not (rootPageId eq 0)}">
		<h4><a href="${viewPagesURL}"><liferay-ui:message key="es.aragon.base.crawler.all-pages" /></a> - ${pageAlias}</h4>
	</c:if>
	<liferay-ui:error key="cannot-connect" message="No se ha podido conectar con la página."/>
	<aui:form action="${portletURL.toString()}" method="get" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="${viewPagesURL}" />
		<aui:input name="deletePagesIds" type="hidden" />

		<liferay-ui:search-container 
			emptyResultsMessage="es.aragon.base.crawler.table.empty-results"
			id="pageEntries"
			iteratorURL="${customInteratorURL}" 
			total="${pagesCount}"
			rowChecker="<%= new EmptyOnClickRowChecker(renderResponse) %>">
	
			<liferay-ui:search-container-results results="${pages}" />

			<liferay-ui:search-container-row
				className="es.aragon.base.crawler.model.Page"
				keyProperty="pageId"
				modelVar="crawledPage">

				<%@ include file="/page_search_columns.jspf" %>
	
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator displayStyle="${pageManagementToolbarDisplayContext.getDisplayStyle()}" markupView="lexicon" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	var deletePages = function() {
		if(confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-pages" />')) {
			var form = document.getElementById('<portlet:namespace />fm');

			if(form) {
				form.setAttribute('method', 'post');
				var deletePagesIds = form.querySelector('#<portlet:namespace />deletePagesIds');
				if(deletePagesIds) {
					deletePagesIds.setAttribute('value', Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));
				}

				submitForm(form, '<portlet:actionURL name="/crawler/pages/delete" />');
			}
		}
	};

	var ACTIONS = {
		'deletePages': deletePages
	};

	Liferay.componentReady('pagesManagementToolbar').then(
		function(managementToolbar) {
			managementToolbar.on(
				'actionItemClicked',
				function(event) {
					var itemData = event.data.item.data;

					if (itemData && itemData.action && ACTIONS[itemData.action]) {
						ACTIONS[itemData.action]();
					}
				}
			);
		}
	);
</aui:script>