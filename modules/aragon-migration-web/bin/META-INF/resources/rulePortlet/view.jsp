<%@page import="com.liferay.journal.model.JournalFolder"%>
<%@page import="com.liferay.journal.service.JournalFolderLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.security.permission.PermissionChecker"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.migration.model.Rule"%>
<%@page import="es.aragon.base.migration.portlet.RulePortlet"%>
<%@page import="es.aragon.base.migration.service.RuleLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>

<%@ include file="init.jsp" %>
<%
int typeId = ParamUtil.getInteger(request, "typeId", 1);
long[] userGroupIds = user.getUserGroupIds();
List<Rule> rulesList = RuleLocalServiceUtil.getRulesByTypeId(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
String url = themeDisplay.getURLCurrent();
List <Rule> listSearch = new ArrayList<Rule>();
List<Rule> userRulesList = new ArrayList<Rule>();
String search = ParamUtil.getString(request, "search", StringPool.BLANK);

if( Validator.isNotNull(search) && search != StringPool.BLANK){
	for ( Rule rule : rulesList){
		if( rule.getUrl().toLowerCase().contains(search.toLowerCase())){
			listSearch.add(rule);
		}
	}
}else{
	listSearch = rulesList;
}
if (!permissionChecker.isOmniadmin()) {
	for (Rule auxRule : listSearch){
		String availableGroupsOfRule = auxRule.getAvailableUserGroupId();
		for ( long userGroupId : userGroupIds) {
			if (Validator.isNotNull(availableGroupsOfRule) && availableGroupsOfRule.contains(StringPool.OPEN_CURLY_BRACE+userGroupId+StringPool.CLOSE_CURLY_BRACE)) {
				userRulesList.add(auxRule);
				break;
			}else{
				if (Validator.isNull(availableGroupsOfRule)){
					userRulesList.add(auxRule);
					break;
				}
			}
		}
	}
}else{
	userRulesList = listSearch;
}

%>
<nav class="navbar navbar-collapse-absolute navbar-expand-md navbar-underline navigation-bar navigation-bar-secondary">
	<div class="container-fluid container-fluid-max-xl">
		<div class="navbar-collapse collapse" ref="content">
			<div class="container-fluid container-fluid-max-xl">
				<ul class="navbar-nav">
					<portlet:renderURL var="viewCategoryRulesRenderURL">
					    <portlet:param name="mvcPath" value="/rulePortlet/view.jsp"/>
						<portlet:param name="typeId" value="1" />
						<portlet:param name="search" value="<%=search%>"/> 
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="0" data-onclick="null">
						<a class='nav-link <%=typeId == 1 ? "active" : "" %>' href="<%=viewCategoryRulesRenderURL%>">
							<span class="navbar-text-truncate"><%=LanguageUtil.get(request, "categories") %></span>
						</a>
					</li>
					<portlet:renderURL var="viewJournalFolderRulesRenderURL">
					    <portlet:param name="mvcPath" value="/rulePortlet/view.jsp"/>
						<portlet:param name="typeId" value="3" />
						<portlet:param name="search" value="<%=search%>"/> 
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="3" data-onclick="null">
						<a class='nav-link <%=typeId == 3 ? "active" : "" %>' href="<%=viewJournalFolderRulesRenderURL%>">
							<span class="navbar-text-truncate"><liferay-ui:message key="aragon.migration.contentportlet.journal-folders"/></span>
						</a>
					</li>
					<portlet:renderURL var="viewMigrableContentRulesRenderURL">
					    <portlet:param name="mvcPath" value="/rulePortlet/view.jsp"/>
						<portlet:param name="typeId" value="2" /> 
						<portlet:param name="search" value="<%=search%>"/>
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a class='nav-link <%=typeId == 2 ? "active" : "" %>' href="<%=viewMigrableContentRulesRenderURL%>">
							<span class="navbar-text-truncate"><liferay-ui:message key="aragon.migration.contentportlet.migrable-content"/></span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>
<!-- Barra de acciones -->
<nav class="management-bar management-bar-light navbar navbar-expand-md">
	<div class="container-fluid container-fluid-max-xl">
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid container-fluid-max-xl">
				<portlet:renderURL var="textSearchRenderURL">
					<portlet:param name="mvcPath" value="/rulePortlet/view.jsp"/>
					<portlet:param name="typeId" value="<%=String.valueOf(typeId)%>"/>
				</portlet:renderURL>
				<aui:form method="post" action="<%=textSearchRenderURL%>">
					<div class="input-group">
						<div class="input-group-item">
							<input name='<portlet:namespace/>search' aria-label="Search" class="form-control input-group-inset input-group-inset-after" placeholder='<%=LanguageUtil.get(request, "aragon.migration.contentportlet.search-placeholder")%>' ref="search" type="text" value="<%=search%>">
							<span class="input-group-inset-item input-group-inset-item-after">
								<button class="btn btn-unstyled" aria-label="search" type="submit">
									<svg aria-hidden="true" class="lexicon-icon lexicon-icon-search" focusable="false" viewBox="0 0 512 512">
										<title>search</title>
										<path class="lexicon-icon-outline" d="M503.254 467.861l-133.645-133.645c27.671-35.13 44.344-79.327 44.344-127.415 0-113.784-92.578-206.362-206.362-206.362s-206.362 92.578-206.362 206.362 92.578 206.362 206.362 206.362c47.268 0 90.735-16.146 125.572-42.969l133.851 133.851c5.002 5.002 11.554 7.488 18.106 7.488s13.104-2.486 18.106-7.488c10.004-10.003 10.004-26.209 0.029-36.183zM52.446 206.801c0-85.558 69.616-155.173 155.173-155.173s155.174 69.616 155.174 155.173-69.616 155.173-155.173 155.173-155.173-69.616-155.173-155.173z"></path>
									</svg>
								</button>
							</span>
						</div>
					</div>
				</aui:form>
			</div>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item">
				<div class="dropdown">
					<portlet:renderURL var="addRuleRenderURL">
					    <portlet:param name="mvcPath" value="/rulePortlet/edit.jsp"></portlet:param>
					    <portlet:param name="typeId" value="<%=String.valueOf(typeId)%>"/>
					    <portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/>
					</portlet:renderURL>
					<button onclick="window.location.href='<%=addRuleRenderURL%>'" aria-expanded="false" aria-haspopup="true" aria-label="toggle" class="dropdown-toggle btn nav-btn nav-btn-monospaced btn-primary" data-onclick="toggle" ref="triggerButton" type="button" data-title="Nuevo" title="Nuevo">
						<svg aria-hidden="true" class="lexicon-icon lexicon-icon-plus" focusable="false" viewBox="0 0 512 512">
							<title>plus</title>
							<path class="lexicon-icon-outline" d="M479.82 224.002h-192.41v-191.91c0-17.6-14.4-32-32-32v0c-17.6 0-32 14.4-32 32v191.91h-191.41c-17.6 0-32 14.4-32 32v0c0 17.6 14.4 32 32 32h191.41v191.91c0 17.6 14.4 32 32 32v0c17.6 0 32-14.4 32-32v-191.909h192.41c17.6 0 32-14.4 32-32v0c0-17.6-14.4-32-32-32z"></path>
						</svg>
					</button>
				</div>
			</li>
		</ul>
	</div>
</nav>
<%
PortletURL searchContainerIteratorURL = renderResponse.createRenderURL();
searchContainerIteratorURL.setParameter("mvcPath", "/rulePortlet/view.jsp");
%>
<portlet:renderURL var="currentTopicURL">
    <portlet:param name="mvcPath" value="/rulePortlet/view.jsp"/>
	<portlet:param name="typeId" value="<%=String.valueOf(typeId)%>" />
</portlet:renderURL>
<div class="container-fluid-1280 main-content-body">
	<liferay-ui:search-container emptyResultsMessage="No se han encontrado reglas" iteratorURL="<%=searchContainerIteratorURL%>">
		<%
		searchContainer.setResults(userRulesList);
		searchContainer.setTotal(userRulesList.size());
		%>
		<liferay-ui:search-container-row className="es.aragon.base.migration.model.Rule" modelVar="rule">
			<portlet:renderURL var="editRuleRenderURL">
			    <portlet:param name="mvcPath" value="/rulePortlet/edit.jsp"/>
				<portlet:param name="ruleId" value="<%=String.valueOf(rule.getRuleId())%>" />
			    <portlet:param name="backURL" value="<%=currentTopicURL%>"/>				
			</portlet:renderURL>
			<portlet:actionURL name="deleteRule" var="deleteRuleActionURL">
				<portlet:param name="ruleId" value="<%=String.valueOf(rule.getRuleId())%>" />
				<portlet:param name="redirect" value="<%=currentTopicURL%>"/> 
			</portlet:actionURL>
			<portlet:actionURL name="revertRule" var="revertRuleActionURL">
				<portlet:param name="ruleId" value="<%=String.valueOf(rule.getRuleId())%>" />
				<portlet:param name="redirect" value="<%=currentTopicURL%>"/> 
			</portlet:actionURL>
			<portlet:actionURL name="applyRule" var="applyRuleActionURL">
				<portlet:param name="ruleId" value="<%=String.valueOf(rule.getRuleId())%>" /> 
				<portlet:param name="redirect" value="<%=currentTopicURL%>"/>
			</portlet:actionURL>
			<%
				String ruleStr = rule.getUrl();
				String migrable="";
				if(ruleStr==null){
					ruleStr = "";
				}
				ruleStr = ruleStr.length() > 100? ruleStr.substring(0,100) + "..." : ruleStr;
				long actionId = rule.getActionId();
				if(actionId==0){
					migrable ="No";
				}else{
					migrable ="Si";
				}
				
			%>
			<liferay-ui:search-container-column-text name="URL" href="<%=editRuleRenderURL%>" value="<%=ruleStr%>" title="<%=rule.getUrl()%>" />
			<c:if test="<%=typeId == 1%>">
				<liferay-ui:search-container-column-jsp name="aragon.migration.contentportlet.clasification" path="/rulePortlet/columns/ruleCategoriesColumn.jsp"></liferay-ui:search-container-column-jsp>
			</c:if>
			<c:if test="<%=typeId == 2%>">
				<liferay-ui:search-container-column-text name="Migration" value="<%=migrable%>"/>
			</c:if>
			<c:if test="<%=typeId == 3%>">
				<%
				String folderName = StringPool.BLANK;
				
				JournalFolder folder = JournalFolderLocalServiceUtil.fetchJournalFolder(rule.getJournalFolderId());
				
				if (folder!= null) {
					folderName = folder.getName();
				} else {
					folderName = "No encontrado";
				}
				%>
				<liferay-ui:search-container-column-text name="folder" href="<%=editRuleRenderURL%>" value="<%=folderName%>"/>
			</c:if>
			<liferay-ui:search-container-column-text align="center" name="actions">
				<liferay-ui:icon-menu>
					<liferay-ui:icon image="edit" url="<%=editRuleRenderURL%>" />
					<c:if test="<%=permissionChecker.isOmniadmin()%>">
						<liferay-ui:icon image="checked" message="aragon.migration.contentportlet.apply-rule-button" url="<%=applyRuleActionURL%>" />
						<liferay-ui:icon image="delete" url="<%=deleteRuleActionURL%>" />
					</c:if>
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator paginate="<%=false%>"/>
	</liferay-ui:search-container>
	<c:if test="<%=userRulesList != null && !userRulesList.isEmpty() && permissionChecker.isOmniadmin() && user.getFullName().contains("Test")%>">
		<portlet:actionURL name="appyAllRules" var="applyAllRulestURL">
			<portlet:param name="typeId" value="<%=String.valueOf(typeId)%>"/>
			<portlet:param name="redirect" value="<%=currentTopicURL%>"/>
		</portlet:actionURL>
		<aui:button-row>
			<a class="btn btn-default" href="<%=applyAllRulestURL.toString()%>"><%=LanguageUtil.get(request, "aragon.migration.contentportlet.apply-all-rules-button")%></a>
		</aui:button-row>
	</c:if>
</div>
