<%@page import="es.aragon.base.aragon_most_visited_pages.util.MostVisitedPagesUtil"%>
<%@page import="es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage"%>
<%@page import="es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalService"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>

<%@include file="init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>
<liferay-portlet:actionURL name="addBBDD" var="addURL"/>
<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />
			<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
			<aui:input name="variable" type="hidden" value="variable" />
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
			<aui:button name="refresh" type="submit" value="Actualizar datos" />
		</liferay-frontend:edit-form>
<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="variable" type="hidden" value="" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
		<!--  
			<liferay-frontend:fieldset>
				<aui:select name="preferences--selectedCount--" label="most-visited.results">
					<%--for (int i = 1; i <= 10; i++) { --%>
						<aui:option value="<%--=i--%>" selected="<%--=selectedCount == i--%>"><%--=i--%></aui:option>
					<%--}--%>
				</aui:select>
			</liferay-frontend:fieldset>
			-->
			<% 
			MostVisitedPageLocalService mostVisitedPageLocalService = (MostVisitedPageLocalService)renderRequest.getAttribute("mostVisitedPageLocalService");
			List<MostVisitedPage> pages = mostVisitedPageLocalService.getMostVisitedPages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (pages != null && pages.size() > 0) {
				for (MostVisitedPage mvp : pages) {
					String checkName = "check" + mvp.getMostVisitedPageId();
					String labelName = "label" + mvp.getMostVisitedPageId();
					String helpMessg = "URL: http://www.aragon.es" + mvp.getPath();
					
					
			%>
					<aui:input name="<%=checkName %>" type="checkbox" value="<%=mvp.getVisible()%>" label="most-visited.visible" />
					<aui:input name="<%=labelName %>" type="text" label="most-visited.title" value="<%=mvp.getTitle()%>" helpMessage="<%=helpMessg%>"/>
			<%
					
				}
			}
			%>
			
			<aui:input name="selectedDefaultFilterStructures" type="text" />
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button name="saveButton" type="submit"  />
		<aui:button href="<%= redirect %>" type="cancel" />
			
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>


		
