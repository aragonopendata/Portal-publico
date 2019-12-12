<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.dynamic.data.mapping.model.DDMStructure"%>
<%@page import="com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"%>
<%@page import="com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.model.LayoutPrototype"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.KeyValuePair"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<%@include file="init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

//Get groupId to get site's vocabularies
long groupId = 0;
Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getSiteGroupId());
if (group.getType() == 0) {
	LayoutPrototype layoutPrototype = LayoutPrototypeLocalServiceUtil.getLayoutPrototype(Long.valueOf(group.getGroupKey()));
	List<LayoutPageTemplateEntry> layoutPageTemplateEntries = LayoutPageTemplateEntryLocalServiceUtil.getLayoutPageTemplateEntries(-1, -1);
	for (LayoutPageTemplateEntry layoutPageTemplateEntry : layoutPageTemplateEntries) {
		if (layoutPageTemplateEntry.getLayoutPrototypeId() == Long.valueOf(group.getGroupKey())) {
			groupId = layoutPageTemplateEntry.getGroupId();
			break;
		}
	}
} else {
	groupId = group.getGroupId();
}
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />
<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
		   	<liferay-frontend:fieldset label="other" collapsible="<%=true%>" collapsed="<%=true%>" >
				<aui:input type="checkbox" name="preferences--cacheEnabled--" label="Cache activada" checked="<%=cacheEnabled%>"/>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="templates">
				<aui:select name="preferences--selectedTemplate--" label="metadata.MSOffice.TEMPLATE">
				    <aui:option value="1" selected="<%=selectedTemplate == 1 ? true : false %>"><liferay-ui:message key="contents_by_topic.config.select-layout-option-list"></liferay-ui:message></aui:option>
				    <aui:option value="2" selected="<%=selectedTemplate == 2 ? true : false %>"><liferay-ui:message key="contents_by_topic.config.select-layout-option-news"></liferay-ui:message></aui:option>
				</aui:select>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset collapsible="<%= false %>" label="contents_by_topic.config.label-preferences">
				<aui:select name="preferences--orderByCol--" label="contents_by_topic.config.order-by">
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_COL_PUBLISH_DATE%>" selected="<%=orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_PUBLISH_DATE) ? true : false%>" label="publish-date"/>
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_COL_CREATE_DATE%>" selected="<%=orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_CREATE_DATE) ? true : false%>" label="create-date"/>
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_COL_MODIFIED_DATE%>" selected="<%=orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_MODIFIED_DATE) ? true : false%>" label="modified-date"/>
					<aui:option value="viewCount" selected="<%=orderByCol.equals("viewCount") ? true : false%>" label="Visitas"/>
					<aui:option value="title" selected="<%=orderByCol.equals("title") ? true : false%>" label="contents_by_topic.config.order-by-title"/>							
				</aui:select>
				<aui:select name="preferences--orderByType--" label="contents_by_topic.config.order-by-type">
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_TYPE_DESC%>" selected="<%=orderByType.equals(AragonUtilitiesConstant.ORDER_BY_TYPE_DESC) ? true : false%>" label="contents_by_topic.config.order-by-type.option.desc"/>
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_TYPE_ASC%>" selected="<%=orderByType.equals(AragonUtilitiesConstant.ORDER_BY_TYPE_ASC) ? true : false%>" label="contents_by_topic.config.order-by-type.option.asc"/>
				</aui:select>
				<aui:input name="preferences--resultsNumber--" label="contents_by_topic.config.number-content-max" value="<%=resultsNumber%>">
					<aui:validator name="required" />
					<aui:validator name="number"></aui:validator>
				</aui:input>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="contents_by_topic.config.searcher-filters">
				<%
				AssetVocabulary contentTypeVocabulary = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(groupId, "Tipos de Documento");
				List<AssetCategory> contentTypeAssetCategories = new ArrayList<>();
				if (Validator.isNotNull(contentTypeVocabulary)) {
					contentTypeAssetCategories = AssetCategoryLocalServiceUtil.getVocabularyCategories(contentTypeVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				}
				%>
				<c:if test="<%=!contentTypeAssetCategories.isEmpty()%>">
					<aui:select name="preferences--selectedContentTypeId--" label="contents_by_topic.config.selected-content-type" helpMessage="Tipo de documento por el que se filtrará en el buscador al clicar en el boton de 'Ver mas'">
						<aui:option value="" selected="<%=selectedContentTypeId == 0 ? true : false%>"></aui:option>
						<%for (AssetCategory category : contentTypeAssetCategories) {%>
							<aui:option value="<%=category.getCategoryId()%>" selected="<%=selectedContentTypeId == category.getCategoryId() ? true : false%>" label="<%=category.getTitle(themeDisplay.getLocale())%>"/>
						<%}%>
					</aui:select>
				</c:if>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="contents_by_topic.config.content-type" collapsible="<%=true%>" collapsed="<%=true%>" >
				<%
				long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);
				//Structures lists for move boxes
				List<KeyValuePair> availableStructuresList = new ArrayList<KeyValuePair>();
				List<KeyValuePair> selectedStructuresList = new ArrayList<KeyValuePair>();
				//Selected structures
				for (String selectedStructure : selectedStructuresArray) {
					DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(Long.parseLong(selectedStructure));
					if (Validator.isNotNull(ddmStructure)) {
						selectedStructuresList.add(new KeyValuePair(String.valueOf(ddmStructure.getStructureId()), HtmlUtil.escape(ddmStructure.getName(themeDisplay.getLocale()))));
					}
				}
				Arrays.sort(selectedStructuresArray);
				//Available structures
				List<DDMStructure> ddmStructures = new ArrayList<>();
				List<Group> groupsList = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				if (groupsList != null && !groupsList.isEmpty()){
					for (Group currentGroup : groupsList){
						List<DDMStructure> groupStructuresList = DDMStructureLocalServiceUtil.getStructures(currentGroup.getGroupId(), ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						if (groupStructuresList != null && !groupStructuresList.isEmpty()) {
							ddmStructures.addAll(groupStructuresList);
						}
					}
				}
				for (DDMStructure ddmStructure : ddmStructures) {
					if (Arrays.binarySearch(selectedStructuresArray, String.valueOf(ddmStructure.getStructureId())) < 0) {
						availableStructuresList.add(new KeyValuePair(String.valueOf(ddmStructure.getStructureId()), HtmlUtil.escape(ddmStructure.getName(themeDisplay.getLocale()))));
					}
				}
				%>
				<label><strong><liferay-ui:message key="contents_by_topic.config.selected-structures"/></strong></label>
				<aui:input name="preferences--selectedStructures--" type="hidden" value="<%=selectedStructures%>"/>
				<liferay-ui:input-move-boxes cssClass="autoHeight" leftBoxName="currentStructureIds" leftList="<%= selectedStructuresList %>" leftTitle="selected" leftReorder="true" rightBoxName="availableStructureIds" rightList="<%= availableStructuresList %>" rightTitle="available"/>
   			</liferay-frontend:fieldset>
   			<liferay-frontend:fieldset label="contents_by_topic.config.filter-by-categories" collapsible="<%=true%>" collapsed="<%=true%>" >
   				<aui:select name="preferences--inclusionCategories--" label="">
					<aui:option value="true" selected="<%=inclusionCategories == true%>" label="contents_by_topic.config.filter-by-categories.include"/>
					<aui:option value="false" selected="<%=inclusionCategories == false%>" label="contents_by_topic.config.filter-by-categories.exclude"/>
				</aui:select>
				<aui:input type="hidden" name="categoriesGroupId" value="<%=groupId%>" />
				<liferay-asset:asset-categories-selector groupIds="<%=new long[]{groupId}%>" hiddenInput="categoryIds" categoryIds="<%= selectedCategoryIds %>" className="<%= JournalArticle.class.getName() %>" />
				</br>
   			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button name="saveButton" type="submit" />
		<aui:button href="<%= redirect %>" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>
<script>
	$('#<portlet:namespace />fm').submit(function(event) {
		event.preventDefault();
		var currentStructureIds = $("#<portlet:namespace />currentStructureIds > option");
		var selectedStructures = "";
		for (var i = 0; i < currentStructureIds.length; i++) {
			if (selectedStructures == "") {
				selectedStructures = currentStructureIds[i].value;
			} else {
				selectedStructures = selectedStructures + "," + currentStructureIds[i].value;
			}
		}
		$("#<portlet:namespace/>selectedStructures").val(selectedStructures);
		$(this).unbind('submit').submit(); 
	});
</script>