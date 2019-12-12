<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.model.JournalFolder"%>
<%@page import="com.liferay.journal.model.JournalFolderConstants"%>
<%@page import="com.liferay.journal.service.JournalFolderServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="es.aragon.base.migration.model.Rule"%>
<%@page import="es.aragon.base.migration.service.RuleLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>

<%@ include file="init.jsp" %>
<%
/* AssetVocabulary vocabularyVisibility = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_VISIBILITY_ES);
AssetVocabulary vocabularyVisibilityInNavigation = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_VISIBILITY_IN_NAVIGATION_ES);
long idVocabularyVisibility = 0;
long idvocabularyVisibilityInNavigation = 0;
if( Validator.isNotNull(vocabularyVisibility) && Validator.isNotNull(vocabularyVisibility)){
	idVocabularyVisibility = vocabularyVisibility.getVocabularyId();
	idvocabularyVisibilityInNavigation = vocabularyVisibilityInNavigation.getVocabularyId();
} */
String backURL = ParamUtil.getString(request, "backURL");
portletDisplay.setURLBack(backURL);
portletDisplay.setShowBackIcon(true);

long ruleId = ParamUtil.getLong(request, "ruleId", 0);
int typeId = ParamUtil.getInteger(request, "typeId", 0);
long userGroupId = 0;
String availableGroupId = StringPool.BLANK;
long journalFolderId = 0;
String url = StringPool.BLANK;
String tags = StringPool.BLANK;
long actionId= 0;

if (ruleId > 0) {
	Rule rule = RuleLocalServiceUtil.fetchRule(ruleId);
	if (rule != null) {
		typeId = rule.getTypeId();
		userGroupId = rule.getUserGroupId();
		url = rule.getUrl();
		tags = rule.getTags();
		journalFolderId = rule.getJournalFolderId();
		actionId = rule.getActionId();
	}
}
%>

<portlet:actionURL name="saveRule" var="saveRuleActionURL">
	<portlet:param name="redirect" value="<%=backURL%>"/>
</portlet:actionURL>
<liferay-frontend:edit-form action="<%=saveRuleActionURL%>" method="post" name="fm">
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset label="aragon.migration.contentportlet.general-config">
				<aui:input type="hidden" name="typeId" value="<%=typeId%>"/>
				<aui:input type="hidden" name="ruleId" value="<%=ruleId%>"/>
				<aui:input name="url" label="URL" value="<%=url%>" required="true"></aui:input>
			</liferay-frontend:fieldset>
			<c:if test="<%=typeId == 1%>">
				<liferay-frontend:fieldset label="aragon.migration.contentportlet.clasification">
				    <liferay-asset:asset-categories-selector hiddenInput="tags" categoryIds="<%=tags%>" className="<%=JournalArticle.class.getName()%>"/>
				</liferay-frontend:fieldset>
			</c:if>
			<c:if test="<%=typeId == 2%>">
				<liferay-frontend:fieldset label="aragon.migration.contentportlet.migrable-content">
				    <aui:select name="actionId" label="Migrar">
				    	<aui:option label="no" value="0" selected="<%=actionId == 0 ? true : false%>"></aui:option>
				    	<aui:option label="yes" value="1" selected="<%=actionId == 1 ? true : false%>"></aui:option>
				    </aui:select>
				</liferay-frontend:fieldset>
			</c:if>

			<c:if test="<%=typeId == 3%>">
				<liferay-frontend:fieldset label="aragon.migration.contentportlet.journal-folders">
					<aui:select label="aragon.migration.contentportlet.journal-folders" name="journalFolderId">
						<aui:option value="" selected="<%=journalFolderId == 0 ? true : false %>" label="select"></aui:option>
						<%List<JournalFolder> journalFolders = JournalFolderServiceUtil.getFolders(themeDisplay.getScopeGroupId(), JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);%>
						<c:if test="<%=journalFolders != null && !journalFolders.isEmpty()%>">
							<%
							for (JournalFolder journalFolder : journalFolders) {
								if(permissionChecker.hasPermission(journalFolder.getGroupId(), journalFolder.getModelClassName(), journalFolder.getFolderId(), ActionKeys.VIEW)) {
							%>
								<aui:option value="<%=journalFolder.getFolderId()%>" selected="<%=journalFolderId == journalFolder.getFolderId() ? true : false %>" label="<%=journalFolder.getName()%>"></aui:option>
							<%
								}
							}%>
						</c:if>
						<aui:validator name="required"></aui:validator>
					</aui:select>
				</liferay-frontend:fieldset>
			</c:if>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" value="save"></aui:button>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<%-- <script>
var idVisibility = "<%=idVocabularyVisibility%>"; 
var idVisibilityInNavigation ="<%=idvocabularyVisibilityInNavigation%>";

window.onload = function() {
	var idVisibilitySelector= "_es_aragon_base_migration_RulePortlet_assetCategoriesSelector_"+idVisibility;
	var idVisibilityLabel= "_es_aragon_base_migration_RulePortlet_assetCategoriesLabel_"+idVisibility;
	var idVisibilityInNavigationSelector = "_es_aragon_base_migration_RulePortlet_assetCategoriesSelector_"+idVisibilityInNavigation;
	var idVisibilityInNavigationLabel = "_es_aragon_base_migration_RulePortlet_assetCategoriesLabel_"+idVisibilityInNavigation;
	console.log("label navigation " + idVisibilityInNavigationLabel);
	console.log("label selector " + idVisibilityInNavigationSelector);
	document.getElementById(idVisibilitySelector).style.display = "none";
	document.getElementById(idVisibilityLabel).style.display = "none";
	document.getElementById(idVisibilityInNavigationSelector).style.display = "none";
	document.getElementById(idVisibilityInNavigationLabel).style.display = "none";
	};
</script> --%>

<%-- Hide visibility vocabularies --%>
<%
AssetVocabulary visibilidad = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Visibilidad");
AssetVocabulary visible = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Visible en navegacion");
long visibilidadId = 0;
long visibleId = 0;
if (visibilidad != null) {
	visibilidadId = visibilidad.getVocabularyId();
}
if (visible != null) {
	visibleId = visible.getVocabularyId();
}
%>
<script>
	$( document ).ready(function() {
		if (<%=visibilidadId != 0%>) {
			var visibilidad = $("#_es_aragon_base_migration_RulePortlet_assetCategoriesLabel_" + "<%=visibilidadId %>")[0];
			if(visibilidad != null) {
				$(visibilidad).parent().hide();
			}
		}
		if (<%=visibleId != 0%>) {
			var visible = $("#_es_aragon_base_migration_RulePortlet_assetCategoriesLabel_" + "<%=visibleId %>")[0];
			if (visible != null) {
				$(visible).parent().hide();
			}
		}
	});
</script>
