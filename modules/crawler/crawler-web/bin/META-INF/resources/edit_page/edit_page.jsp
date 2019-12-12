<%-- Page editing view. --%>

<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ include file="edit_page_init.jsp"%>

<%-- Generate add / edit action URL and set title. --%>
<%
	Page pageToEdit = (Page) GetterUtil.getObject(renderRequest.getAttribute("pageToEdit"));
	
	RootPage rootPageToEdit = (RootPage) GetterUtil.getObject(renderRequest.getAttribute("rootPageToEdit"));
	String url = ParamUtil.getString(request, "redirect");
%>
<portlet:actionURL var="editPageActionURL" name="<%=MVCCommandNames.EDIT_PAGE %>">
	<portlet:param name="redirect" value="<%=url %>" />
</portlet:actionURL>

<portlet:actionURL var="addPageActionURL" name="<%=MVCCommandNames.ADD_PAGE %>">
	<portlet:param name="redirect" value="<%=url %>" />
</portlet:actionURL>

<%
String pageActionURL = StringPool.BLANK;
if (pageToEdit != null) {
	pageActionURL = editPageActionURL;
} else {
	pageActionURL = addPageActionURL;
}
%>


<div class="container-fluid-1280">
	<liferay-frontend:edit-form action="<%=pageActionURL%>"name="fm">
		<liferay-frontend:edit-form-body>
			<liferay-frontend:fieldset-group>
			
				<liferay-frontend:fieldset label="es.aragon.base.crawler.edit.title.config.page" collapsed="<%= false %>" collapsible="<%= true %>">
					
					<c:if test="<%= pageToEdit != null%>">
						<aui:input name="pageId" value="<%= pageToEdit.getPageId()%>" type="hidden" />
						<aui:input name="es.aragon.base.crawler.edit.page-id" value="<%= pageToEdit.getPageId()%>" type="number" disabled="true" />
					</c:if>
					
					<c:choose>
						<%-- New rootPage --%>
						<c:when test="<%= pageToEdit == null%>">
							<%-- Alias --%>
							<aui:input name="es.aragon.base.crawler.edit.alias" type="text">
								<aui:validator name="required" />
							</aui:input>
							<%-- Status --%>
							<aui:input name="es.aragon.base.crawler.edit.status" value="true" type="hidden" />
							<%-- URL --%>
							<aui:input name="es.aragon.base.crawler.edit.url" type="text">
								<aui:validator name="required" />
							</aui:input>
							<%-- Depth --%>
							<aui:input name="es.aragon.base.crawler.edit.depth" type="number">
								<aui:validator name="required" />
								<aui:validator name="min">1</aui:validator>
							</aui:input>
							<%-- Auto crawl --%>
							<aui:input name="es.aragon.base.crawler.edit.auto-crawl" type="toggle-switch"/>
							<%-- Inclusion rules --%>
							<aui:input name="es.aragon.base.crawler.edit.inclusion-rules" type="textarea" />
						</c:when>
						
						
						<%-- editing rootPage --%>
						<c:when test="<%= rootPageToEdit != null %>">
							<%-- Alias --%>
							<aui:input name="es.aragon.base.crawler.edit.alias" value="<%= rootPageToEdit.getAlias() %>" type="text">
								<aui:validator name="required" />
							</aui:input>
							<%-- Status --%>
							<aui:input name="es.aragon.base.crawler.edit.status" value="true" type="hidden" />
							<%-- URL --%>
							<aui:input name="es.aragon.base.crawler.edit.url" value="<%= pageToEdit.getUrl() %>" type="text" disabled="true" />
							<%-- Depth --%>
							<aui:input name="es.aragon.base.crawler.edit.depth" value="<%= rootPageToEdit.getDepth() %>" type="number" />
							<%-- Auto crawl --%>
							<aui:input name="es.aragon.base.crawler.edit.auto-crawl" value="<%= rootPageToEdit.isScheduledCrawl() %>" type="toggle-switch"/>
							<%-- Inclusion rules --%>
							<aui:input name="es.aragon.base.crawler.edit.inclusion-rules" value="<%= rootPageToEdit.getInclusionRules() %>" type="textarea" />
							<%-- Crawled date --%>
							<aui:input name="es.aragon.base.crawler.edit.crawled-date" value="<%= rootPageToEdit.getCrawledDate() %>" type="text" disabled="true" />
						</c:when>
						
						
						<%-- Editing subPage --%>
						<c:otherwise>
							<%-- Status --%>
							<aui:input name="es.aragon.base.crawler.edit.status" value="${status}" type="checkbox" />
							<%-- URL --%>
							<aui:input name="es.aragon.base.crawler.edit.url" value="<%= pageToEdit.getUrl() %>" type="text" disabled="true" />
						</c:otherwise>
					</c:choose>
				</liferay-frontend:fieldset>
				
				<%-- Crawled page information --%>
				<c:if test="<%= pageToEdit != null %>">
					<liferay-frontend:fieldset label="es.aragon.base.crawler.edit.title.crawled-data" collapsed="<%= true %>" collapsible="<%= true %>">
						<aui:input name="es.aragon.base.crawler.edit.title" value="<%= pageToEdit.getTitle() %>" type="text" disabled="true" />
						<aui:input name="es.aragon.base.crawler.edit.meta-description" value="<%= pageToEdit.getMetaDescription() %>" type="textarea" disabled="true" />
						<aui:input name="es.aragon.base.crawler.edit.meta-keywords" value="<%= pageToEdit.getMetaKeywords() %>" type="textarea" disabled="true" />
						<aui:input name="es.aragon.base.crawler.edit.content" value="<%= pageToEdit.getContent() %>" type="textarea" disabled="true" />
					</liferay-frontend:fieldset>
				</c:if>
				
				<%-- Categorization --%>
				<liferay-frontend:fieldset label="es.aragon.base.crawler.edit.title.categorization" collapsed="<%= true %>" collapsible="<%= true %>">
					<liferay-asset:asset-categories-selector hiddenInput="categoryIds" categoryIds="${pageCategoryIds}" className="${categoriesClassName}"/>
					</br>
				</liferay-frontend:fieldset>
			</liferay-frontend:fieldset-group>
		</liferay-frontend:edit-form-body>
		<liferay-frontend:edit-form-footer>
			<aui:button cssClass="btn btn-primary" type="submit" />
			<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel" />
		</liferay-frontend:edit-form-footer>
	</liferay-frontend:edit-form>
</div>