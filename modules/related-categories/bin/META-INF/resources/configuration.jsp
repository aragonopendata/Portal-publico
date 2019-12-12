<%-- //<%@page import="javax.swing.text.html.HTMLDocument$HTMLReader.IsindexAction"%> --%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.util.KeyValuePair"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>

<%@include file="init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

//Preferences
String []vocabularyIdsList = vocabularyIdsString.split(",");
List<Long> vocabularyIds =  new ArrayList<Long>();
if(vocabularyIdsList != null && vocabularyIdsList.length > 0 ){	
	for (int i = 0; i < vocabularyIdsList.length; i++) {
		Long vocabularyId = GetterUtil.getLong(vocabularyIdsList[i], - 1);
		if(vocabularyId != -1){
			vocabularyIds.add(Long.valueOf(vocabularyIdsList[i]));
		}	
	}
}
%>


<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<aui:input type="text" name="preferences--vocabularyIds--"/>
				<% 
				List<KeyValuePair> availableVocabularyIds = new ArrayList<KeyValuePair>();
				List<KeyValuePair> selectedVocabularyIds = new ArrayList<KeyValuePair>();
				
				//Gets selected vocabularies from preferences
				if (vocabularyIds != null && !vocabularyIds.isEmpty()){
	 	 	 		for (long auxVocabularyId : vocabularyIds){
	 	 	 			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getVocabulary(auxVocabularyId);
	 	 	 			selectedVocabularyIds.add(new KeyValuePair(String.valueOf(auxVocabularyId), assetVocabulary.getName()));	
	 	 	 		}
	 	 		}
				
				//Gets non selected vocabularies from preferences
				List<Group> groupsList = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				for (Group group : groupsList ) {
			   		List<AssetVocabulary> groupVocabulariesList = AssetVocabularyLocalServiceUtil.getGroupVocabularies(group.getGroupId());
			    	for (AssetVocabulary assetVocabulary : groupVocabulariesList ) {	
			    		boolean isSelected = Boolean.FALSE;
			    		for (KeyValuePair selected : selectedVocabularyIds) {
			    			if(assetVocabulary.getVocabularyId() == Long.valueOf(selected.getKey())){
			    				isSelected = Boolean.TRUE;
			    				selected.setValue(selected.getValue() +  "(" + group.getName() + ")");
			    			}
			    		}
			    		if (!isSelected) {
			    			availableVocabularyIds.add(new KeyValuePair(String.valueOf(assetVocabulary.getVocabularyId()), assetVocabulary.getName() + "(" + group.getName() + ")"));
			    		}
			    	}    	
				}
				%>
				<liferay-ui:input-move-boxes 
					leftBoxName="selectedVocabularyIds"
					leftList="<%=selectedVocabularyIds %>"
					leftTitle="selected"
					leftReorder="true"
					leftOnChange="true" 
					rightBoxName="availableVocabularyIds" 
					rightList="<%=availableVocabularyIds %>"
					rightTitle="available"/>
			</liferay-frontend:fieldset>	
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button name="saveButton" type="submit" />
		<aui:button href="<%= redirect %>" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>
<aui:script use="liferay-util-list-fields">
	A.one('#<portlet:namespace/>fm').on('submit', function(event) {
		//Moveboxes de sitios web
		var selectedGroups = Liferay.Util.listSelect('#<portlet:namespace/>selectedVocabularyIds');
		A.one('#<portlet:namespace/>vocabularyIds').val(selectedGroups);
		//Submit del formulario
		submitForm('#<portlet:namespace/>fm');
	});
</aui:script>