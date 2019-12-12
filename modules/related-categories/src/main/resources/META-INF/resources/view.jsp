<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoBridge"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.liferay.portal.kernel.util.KeyValuePair"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="es.aragon.base.freemarker_utilities.api.FreemarkerUtilities"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%
//VOCABULARY LIST FROM CONFIGURATION JSP
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
// FILTER ONLY CATEGORIES THAT WE WANT

List<AssetCategory>  auxListCategoriesFacets = (List<AssetCategory>)renderRequest.getAttribute("listCategoriesFacets");
List<AssetCategory> listCategoriesFacets = new ArrayList<AssetCategory>();
Layout currentLayout = themeDisplay.getLayout();
ExpandoBridge expandoBridge = currentLayout.getExpandoBridge();
long relatedCategoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), -1);

for(Long vocabularyId : vocabularyIds){
	for(AssetCategory auxlistcategoryfacet: auxListCategoriesFacets ){
		if(vocabularyId == auxlistcategoryfacet.getVocabularyId() && auxlistcategoryfacet.getCategoryId()!= relatedCategoryId ){
			listCategoriesFacets.add(auxlistcategoryfacet);
		}
	}
}

//WE GET SELECTED VOCABULARIES

List<KeyValuePair> selectedVocabularyIds = new ArrayList<KeyValuePair>();
if (vocabularyIds != null && !vocabularyIds.isEmpty()){
	for (long auxVocabularyId : vocabularyIds){
		AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getVocabulary(auxVocabularyId);
		selectedVocabularyIds.add(new KeyValuePair(String.valueOf(auxVocabularyId), assetVocabulary.getName()));	
	}
}


FreemarkerUtilities freemarkerUtilities = (FreemarkerUtilities) renderRequest.getAttribute("freemarkerUtilities");
long currentCategoryId = GetterUtil.getLong(renderRequest.getAttribute("selectedCategories"), 0);
Locale defaultSiteLocale = PortalUtil.getSiteDefaultLocale(themeDisplay.getScopeGroupId());
int numCol = vocabularyIdsList.length;
HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);

//SHOW ONLY THE VOCABULARIES THAT HAVE CATEGORIES ASSOCIATED
List<String> filteredVocabularyIdList = new ArrayList<String>();
for(int i = 0; i < vocabularyIdsList.length; i++){
	boolean correct = false;
	for(AssetCategory assetcategory : listCategoriesFacets){
		if(vocabularyIdsList[i].contains(String.valueOf(assetcategory.getVocabularyId()))){
			correct = true;
		}
	}
	if(correct == true){
		filteredVocabularyIdList.add(vocabularyIdsList[i]);
	}
}
%>

<c:if test="<%=listCategoriesFacets != null && !listCategoriesFacets.isEmpty()%>">		
	<section class="u-padding-bottom-6">
	     <nav class="asociados" aria-label="<%=LanguageUtil.get(request, "es.aragon.related_categories.view.label.linked.menus")%>" role="navigation">
	     	 <div class="container">
	        	<ul class="asociados__listado">
	        	<%
	        	for (KeyValuePair vocabulary : selectedVocabularyIds ) {
					if (vocabularyIdsList != null && vocabularyIdsList.length > 0) {
						for (String checkedVocabularyId : filteredVocabularyIdList) {	
							if (checkedVocabularyId.equals(String.valueOf(vocabulary.getKey())) ) {
								String cssClass = "item";
								if (numCol==3) {
									cssClass = cssClass + " col-xs-12 col-md-4";
								} else {
									cssClass = cssClass + " col-sm-6 col-md-3";
								}
									%>
									<li class="<%=cssClass%>">
										<p class="title"><liferay-ui:message key="es.aragon.related_categories.view.span.related" arguments="<%=vocabulary.getValue()%>"/>
										<ul class="submenu-listado">
											<%for (AssetCategory category : listCategoriesFacets) {%>
												<c:if test="<%=(category.getVocabularyId() == Long.valueOf(vocabulary.getKey())) %>">
													<li class="submenu-listado__item">
														<a href="<%=freemarkerUtilities.getAssetCategoryURL(request, category.getCategoryId(), true)%>" class="link"><%=category.getTitle(defaultSiteLocale) %></a>
													</li>
												</c:if>
											<%}%>
										</ul>
									</li>					
								<% 
							 }
						 }
					  }
				} %>
	        	</ul> 
	        </div>
	    </nav>
	    <div class="asociados__ver-menos text-center">
	        <div class="link js-link">
	            <button><span class="text"><%=LanguageUtil.get(request, "es.aragon.related_categories.view.span.viewmore")%></span><span class="oculto"><%=LanguageUtil.get(request, "es.aragon.related_categories.view.label.linked.menus")%></span></button>
	        </div>
	    </div>
	</section>
</c:if>



