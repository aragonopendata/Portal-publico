<%@ page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %>
<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="com.liferay.asset.kernel.model.AssetEntry" %>
<%@ page import="com.liferay.asset.kernel.model.AssetVocabulary" %>
<%@ page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.liferay.dynamic.data.mapping.model.DDMStructure" %>
<%@ page import="com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil" %>
<%@ page import="com.liferay.journal.model.JournalArticle" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.model.ClassName" %>
<%@ page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %>
<%@ page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.KeyValuePair" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>

<%@ include file="init.jsp" %>

<%
long[] selectedAssetTypesArray = StringUtil.split(selectedAssetTypes, 0L);
// Left asset type list
List<KeyValuePair> selectedAssetTypeList = new ArrayList<KeyValuePair>();
for(long selectedAssetType : selectedAssetTypesArray) {
	ClassName className = ClassNameLocalServiceUtil.fetchClassName(selectedAssetType);
	if(Validator.isNotNull(className)) {
		String classNameResource = ResourceActionsUtil.getModelResource(themeDisplay.getLocale(), className.getValue());
		selectedAssetTypeList.add(new KeyValuePair(String.valueOf(selectedAssetType), HtmlUtil.escape(LanguageUtil.get(request,"filters."+className.getValue()))));
	}
}

Arrays.sort(selectedAssetTypesArray);

// Right asset type list
long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(themeDisplay.getCompanyId(), true);
List<KeyValuePair> availableAssetTypeList = new ArrayList<KeyValuePair>();
for(long classNameId : classNameIds) {
	if(Arrays.binarySearch(selectedAssetTypesArray, classNameId) < 0) {
		ClassName className = ClassNameLocalServiceUtil.fetchClassName(classNameId);
		if(Validator.isNotNull(className)) {
			String classNameResource = ResourceActionsUtil.getModelResource(themeDisplay.getLocale(), className.getValue());
			availableAssetTypeList.add(new KeyValuePair(String.valueOf(classNameId), HtmlUtil.escape(classNameResource)));
		}
	}
}
ClassName assetCategoryClassName = ClassNameLocalServiceUtil.fetchClassName(AssetCategory.class.getName());
if(Validator.isNotNull(assetCategoryClassName)) {
	String classNameResource = ResourceActionsUtil.getModelResource(themeDisplay.getLocale(), assetCategoryClassName.getValue());
	if(Arrays.binarySearch(selectedAssetTypesArray, assetCategoryClassName.getClassNameId()) < 0) {
		availableAssetTypeList.add(new KeyValuePair(String.valueOf(assetCategoryClassName.getClassNameId()), HtmlUtil.escape(classNameResource)));
	}
}

String[] selectedStructuresArray = StringUtil.split(selectedStructures);
long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);

//Left structures list
List<KeyValuePair> selectedStructuresList = new ArrayList<KeyValuePair>();
for(String selectedStructure : selectedStructuresArray) {
	DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(themeDisplay.getLayout().getGroupId(), classNameId, selectedStructure);
	if(Validator.isNotNull(ddmStructure)) {
		selectedStructuresList.add(new KeyValuePair(ddmStructure.getStructureKey(), HtmlUtil.escape(ddmStructure.getName(themeDisplay.getLocale()))));
	}
}

Arrays.sort(selectedStructuresArray);

// Right vocabularies list

List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.getStructures(themeDisplay.getLayout().getGroupId(), classNameId);
List<KeyValuePair> availableStructuresList = new ArrayList<KeyValuePair>();
for(DDMStructure ddmStructure : ddmStructures) {
	if(Arrays.binarySearch(selectedStructuresArray, ddmStructure.getStructureKey()) < 0) {
		availableStructuresList.add(new KeyValuePair(ddmStructure.getStructureKey(), HtmlUtil.escape(ddmStructure.getName(themeDisplay.getLocale()))));
	}
}

if(Validator.isNull(selectedVocabularies) || selectedVocabularies.isEmpty()) {
	AssetVocabulary topics = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getLayout().getGroupId(), LanguageUtil.get(request, "configuration.topics"));
	AssetVocabulary organisms = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getLayout().getGroupId(), LanguageUtil.get(request, "configuration.organism"));
	if(Validator.isNotNull(topics)) {
		selectedVocabularies = "" + topics.getVocabularyId(); 
	}
	if(Validator.isNotNull(organisms)) {
		if(selectedVocabularies.isEmpty()) {
			selectedVocabularies = "" + organisms.getVocabularyId(); 
		} else {
			selectedVocabularies += "," + organisms.getVocabularyId(); 
		}
	}
}



long[] selectedVocabulariesArray = StringUtil.split(selectedVocabularies, 0L);
long[] facetedVocabulariesArray = StringUtil.split(facetedVocabularies, 0L);

// Left vocabularies list
List<KeyValuePair> selectedVocabulariesList = new ArrayList<KeyValuePair>();
for(long selectedVocabulary : selectedVocabulariesArray) {
	AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(selectedVocabulary);
	String titleVocabulary = HtmlUtil.escape(vocabulary.getTitle(themeDisplay.getLocale()));
	String titleVocabularyModified = titleVocabulary.substring(0, 1).toUpperCase() + titleVocabulary.substring(1).toLowerCase();
	if(Validator.isNotNull(vocabulary)) {
		selectedVocabulariesList.add(new KeyValuePair(String.valueOf(vocabulary.getVocabularyId()), titleVocabularyModified));
	}
}

Arrays.sort(selectedVocabulariesArray);
Arrays.sort(facetedVocabulariesArray);

// Right vocabularies list
List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getGroupVocabularies(themeDisplay.getLayout().getGroupId());
List<KeyValuePair> availableVocabulariesList = new ArrayList<KeyValuePair>();
for (AssetVocabulary vocabulary : vocabularies) {
	if (Arrays.binarySearch(selectedVocabulariesArray, vocabulary.getVocabularyId()) < 0) {
		availableVocabulariesList.add(new KeyValuePair(String.valueOf(vocabulary.getVocabularyId()), HtmlUtil.escape(vocabulary.getTitle(themeDisplay.getLocale()))));
	}
}
%>
    
<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
    <aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />
    <liferay-frontend:edit-form-body>
   		<liferay-frontend:fieldset-group>
   			<liferay-frontend:fieldset label="configuration.label.presentation" collapsible="<%=true%>" collapsed="<%=false%>">
   				<label><strong><liferay-ui:message key="configuration.help-message" /></strong></label>
				<aui:input label="" name="helpMessage" type="text" value="<%= helpMessage %>" />
				<label><strong><liferay-ui:message key="configuration.showed-elements" /></strong></label>
				<aui:input label="" name="defaultDisplayElements" type="number" value="<%= defaultDisplayElements %>" />
   			</liferay-frontend:fieldset>
   			<liferay-frontend:fieldset label="configuration.label.filters" collapsible="<%=true%>">
				<aui:input type="checkbox" label="configuration.label.external-portal-search-available" name="checkedExternalSearch" checked="<%=checkedExternalSearch%>" />
				<aui:input type="checkbox" label="configuration.label.enable-external-portal-search-filter" name="checkedViewExternalSearchFilter" checked="<%=checkedViewExternalSearchFilter%>" />
				<aui:input type="checkbox" label="configuration.label.all-words-must-match" name="allWordsMustMatch" checked="<%=allWordsMustMatch%>" />
   				<label><strong><liferay-ui:message key="configuration.categories-to-filter" /></strong></label>
				<liferay-asset:asset-categories-selector hiddenInput="defaultFilters" categoryIds="<%= defaultFilters %>" className="<%=AssetEntry.class.getName()%>" />
				<br>
				<label><strong><liferay-ui:message key="configuration.showed-vocabularies" /></strong></label>
				<aui:input name="selectedVocabularies" type="hidden" value="<%= selectedVocabularies %>" />
				<liferay-ui:input-move-boxes
					cssClass="autoHeight"
					leftBoxName="currentVocabularyIds"
					leftList="<%= selectedVocabulariesList %>"
					leftTitle="selected"
					leftReorder="true"
					rightBoxName="availableVocabularyIds"
					rightList="<%= availableVocabulariesList %>"
					rightTitle="available"
				/>
				<label><strong><liferay-ui:message key="configuration.faceted-vocabularies" /></strong></label>
				<aui:input name="facetedVocabularies" type="hidden" value="<%= facetedVocabularies %>" />
				<div id="facetedVocabulariesDiv">
					<c:forEach items="<%= selectedVocabulariesList %>" var="vocabulary">
						<c:set var="vocabularyId" value="${vocabulary.getKey()}" scope="page" />
						<% boolean faceted = Arrays.binarySearch(facetedVocabulariesArray, Long.parseLong(String.valueOf(pageContext.getAttribute("vocabularyId")))) >= 0 ? true : false; %>
						<aui:input label="${vocabulary.getValue()}" name="f${vocabulary.getKey()}" data-id="${vocabulary.getKey()}" type="toggle-switch" value="<%= faceted %>" />
					</c:forEach>
				</div>	
   			</liferay-frontend:fieldset>
   			<liferay-frontend:fieldset label="configuration.label.content-type" collapsible="<%=true%>">
   				<label><strong><liferay-ui:message key="configuration.selected-asset-types" /></strong></label>
				<aui:input name="selectedAssetTypes" type="hidden" value="<%= selectedAssetTypes %>" />
				<liferay-ui:input-move-boxes
					cssClass="autoHeight"
					leftBoxName="currentAssetTypeIds"
					leftList="<%= selectedAssetTypeList %>"
					leftTitle="selected"
					leftReorder="true"
					rightBoxName="availableAssetTypeIds"
					rightList="<%= availableAssetTypeList %>"
					rightTitle="available"
				/>
				<% 		
				boolean checked = false;
				if (!checkedAssetType.equals("false")){
					checked =true;
				}
				%>
				<aui:input type="checkbox" label="<%=LanguageUtil.get(request, "configuration.show-asset-type")%>" name="checkedAssetType" checked="<%=checked %>" />
				
				<label><strong><liferay-ui:message key="configuration.selected-structures" /></strong></label>
				<aui:input name="selectedStructures" type="hidden" value="<%= selectedStructures %>" />
				<liferay-ui:input-move-boxes
					cssClass="autoHeight"
					leftBoxName="currentStructureIds"
					leftList="<%= selectedStructuresList %>"
					leftTitle="selected"
					leftReorder="true"
					rightBoxName="availableStructureIds"
					rightList="<%= availableStructuresList %>"
					rightTitle="available"
				/>
   			</liferay-frontend:fieldset>
   		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
   	<liferay-frontend:edit-form-footer>
   		<aui:button type="submit"></aui:button>
       	<aui:button type="cancel"></aui:button>
   	</liferay-frontend:edit-form-footer>  
</liferay-frontend:edit-form>

<script>
	$('#<portlet:namespace />fm').submit(function(event) {
		event.preventDefault();
		
		var currentAssetTypeIds = $("#<portlet:namespace />currentAssetTypeIds > option");
		var selectedAssetTypes = "";
		for(var i = 0; i < currentAssetTypeIds.length; i++) {
			if(selectedAssetTypes == "") {
				selectedAssetTypes = currentAssetTypeIds[i].value;
			} else {
				selectedAssetTypes = selectedAssetTypes + "," + currentAssetTypeIds[i].value;
			}
		}
		$("#<portlet:namespace/>selectedAssetTypes").val(selectedAssetTypes);
		
		var currentStructureIds = $("#<portlet:namespace />currentStructureIds > option");
		var selectedStructures = "";
		for(var i = 0; i < currentStructureIds.length; i++) {
			if(selectedStructures == "") {
				selectedStructures = currentStructureIds[i].value;
			} else {
				selectedStructures = selectedStructures + "," + currentStructureIds[i].value;
			}
		}
		$("#<portlet:namespace/>selectedStructures").val(selectedStructures);
		
		var currentVocabularyIds = $("#<portlet:namespace />currentVocabularyIds > option");
		var selectedVocabularies = "";
		for(var i = 0; i < currentVocabularyIds.length; i++) {
			if(selectedVocabularies == "") {
				selectedVocabularies = currentVocabularyIds[i].value;
			} else {
				selectedVocabularies = selectedVocabularies + "," + currentVocabularyIds[i].value;
			}
		}
		$("#<portlet:namespace/>selectedVocabularies").val(selectedVocabularies);
		
		var facetedVocabulariesChecks = $("#facetedVocabulariesDiv > div > label > input");
		var facetedVocabularies = "";
		for(var i = 0; i < facetedVocabulariesChecks.length; i++) {
			if(facetedVocabulariesChecks[i].checked) {
				if(facetedVocabularies == "") {
					facetedVocabularies = facetedVocabulariesChecks[i].dataset.id;
				} else {
					facetedVocabularies = facetedVocabularies + "," + facetedVocabulariesChecks[i].dataset.id;
				}
			}
		}
		$("#<portlet:namespace/>facetedVocabularies").val(facetedVocabularies);
		
		
		$(this).unbind('submit').submit(); 
	});
</script>