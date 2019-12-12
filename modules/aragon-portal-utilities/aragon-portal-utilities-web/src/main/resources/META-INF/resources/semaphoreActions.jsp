
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys"%>
<%@ include file="/init.jsp"%>

<c:set var="semaphore" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />

<liferay-ui:icon-menu direction="right-side" icon="<%= StringPool.BLANK %>" markupView="lexicon"
	message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">


	<portlet:actionURL name="<%=AragonPortalUtilitiesPortletKeys.ROUTE_EDIT_SEMAPHORE%>" var="editPlusSemaphore">
		<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
		<portlet:param name="semaphoreId" value="${semaphore.semaphoreId}" />
		<portlet:param name="value" value="1" />
	</portlet:actionURL>

	<liferay-ui:icon message="Max. usuarios +1" url="${editPlusSemaphore}" />
	
	<c:if test="${not (semaphore.maxUsers eq 1)}" >
		<portlet:actionURL name="<%=AragonPortalUtilitiesPortletKeys.ROUTE_EDIT_SEMAPHORE%>" var="editMinusSemaphore">
			<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
			<portlet:param name="semaphoreId" value="${semaphore.semaphoreId}" />
			<portlet:param name="value" value="-1" />
		</portlet:actionURL>
		
		<liferay-ui:icon message="Max. usuarios -1" url="${editMinusSemaphore}" />
	
	</c:if>

	<portlet:actionURL name="<%=AragonPortalUtilitiesPortletKeys.ROUTE_CLEAN_SEMAPHORE%>" var="cleanSemaphore">
		<portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
		<portlet:param name="semaphoreId" value="${semaphore.semaphoreId}" />
	</portlet:actionURL>

	<liferay-ui:icon message="Resetear semáforo a 0 usuarios" url="${cleanSemaphore}" />

</liferay-ui:icon-menu>