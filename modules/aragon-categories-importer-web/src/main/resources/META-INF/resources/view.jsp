<%@page import="es.aragon.base.categories_importer.util.assetcategory.ExpandoUtil"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoBridge"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants"%>
<%@page import="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"%>
<%@page import="com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.DisplayTerms"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="es.aragon.base.categories_importer.model.ImportCategoryRegistry"%>
<%@page import="es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalServiceUtil"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%
//Date format
Format simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//Get parameters
String menuId = ParamUtil.getString(request, "menuId", AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
String type = ParamUtil.getString(request, "type", "deprecated");
//Set view title
renderResponse.setTitle("Importador de categorias - " + menuId);
//Gets the current tab vocabulary
AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), menuId);
%>
<!-- Vocabulary tabs -->
<nav class="navbar navbar-collapse-absolute navbar-expand-md navbar-underline navigation-bar navigation-bar-secondary">
	<div class="container-fluid container-fluid-max-xl">
		<div class="navbar-collapse collapse" ref="content">
			<div class="container-fluid container-fluid-max-xl">
				<ul class="navbar-nav">
					<portlet:renderURL var="organismosRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES%>"/>
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=organismosRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES%></a>
					</li>
					<portlet:renderURL var="temasRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES%>" /> 
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=temasRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES%></a>
					</li>					
					<portlet:renderURL var="municipiosRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES%>" />
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=municipiosRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES%></a>
					</li>
					<portlet:renderURL var="tiposDocumentoRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES%>" />
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=tiposDocumentoRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES%></a>
					</li>
					<portlet:renderURL var="perfilesRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES%>" /> 
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=perfilesRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_PROFILES_ES%></a>
					</li>
					<portlet:renderURL var="tramitesRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_ES%>" /> 
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=tramitesRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_PROCEDURES_ES%></a>
					</li>
					<portlet:renderURL var="personasRenderURL">
					    <portlet:param name="mvcPath" value="/view.jsp"/>
					    <portlet:param name="menuId" value="<%=AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES%>" /> 
					</portlet:renderURL>
					<li class="nav-item" data-nav-item-index="2" data-onclick="null">
						<a href="<%=personasRenderURL%>" class='nav-link <%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES) ? "active" : "" %>'><%=AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES%></a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>

<!-- Filters -->
<nav class="management-bar management-bar-light navbar navbar-expand-md">
	<div class="container-fluid container-fluid-max-xl">
		<ul class="navbar-nav">
			<c:if test="<%=Validator.isNotNull(vocabulary)%>">
				<!-- Status -->
				<span class="dropdown nav-item"><liferay-ui:message key="aragon-categories-importer-web.filter.category-status"></liferay-ui:message></span>
				<li class="dropdown nav-item">
					<div class="dropdown">
						<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownAreaButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="navbar-breakpoint-down-d-none">
								<c:if test='<%=type.equals("any")%>'>
									<span><liferay-ui:message key="aragon-categories-importer-web.option.default-value-import-date"></liferay-ui:message></span>
								</c:if>
								<c:if test='<%=type.equals("update")%>'>
									<span><liferay-ui:message key="aragon-categories-importer-web.option.updated-category-status"></liferay-ui:message></span>
								</c:if>
								<c:if test='<%=type.equals("create")%>'>
									<span><liferay-ui:message key="aragon-categories-importer-web.option.created-category-status"></liferay-ui:message></span>
								</c:if>
								<c:if test='<%=type.equals("deprecated")%>'>
									<span><liferay-ui:message key="aragon-categories-importer-web.option.deprecated-category-status"></liferay-ui:message></span>
								</c:if>
								<svg aria-hidden="true" class="lexicon-icon lexicon-icon-caret-bottom inline-item inline-item-after" focusable="false" viewBox="0 0 512 512">
									<title><liferay-ui:message key="aragon-categories-importer-web.button-span-svg-caret-bottom"></liferay-ui:message></title>
									<path class="lexicon-icon-outline" d="M103.5,204.3l136.1,136.1c9,9,23.7,9,32.7,0l136.1-136.1c14.6-14.6,4.3-39.5-16.4-39.5H119.9C99.2,164.8,88.9,189.7,103.5,204.3L103.5,204.3z"></path>
								</svg>
							</span>
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownAreaButton">
							<portlet:renderURL var="cleanTypeFilterRenderURL">
								<portlet:param name="mvcPath" value="/view.jsp"/>
								<portlet:param name="type" value="any"/>
								<portlet:param name="menuId" value="<%=menuId%>"/>
							</portlet:renderURL>
							<a class='dropdown-item <%=type.equals("any") ? " active" : "" %>' href="<%=cleanTypeFilterRenderURL%>"><liferay-ui:message key="aragon-categories-importer-web.option.default-value-import-date"></liferay-ui:message></a>
							<portlet:renderURL var="createFilterRenderURL">
								<portlet:param name="mvcPath" value="/view.jsp"/>
								<portlet:param name="type" value="create"/>
								<portlet:param name="menuId" value="<%=menuId%>"/>
							</portlet:renderURL>
							<a class='dropdown-item' href="<%=createFilterRenderURL%>"><liferay-ui:message key="aragon-categories-importer-web.option.created-category-status"></liferay-ui:message></a>							
							<portlet:renderURL var="updateFilterRenderURL">
								<portlet:param name="mvcPath" value="/view.jsp"/>
								<portlet:param name="type" value="update"/>
								<portlet:param name="menuId" value="<%=menuId%>"/>
							</portlet:renderURL>
							<a class='dropdown-item' href="<%=updateFilterRenderURL%>"><liferay-ui:message key="aragon-categories-importer-web.option.updated-category-status"></liferay-ui:message></a>
							<portlet:renderURL var="deprecatedFilterRenderURL">
								<portlet:param name="mvcPath" value="/view.jsp"/>
								<portlet:param name="type" value="deprecated"/>
								<portlet:param name="menuId" value="<%=menuId%>"/>
							</portlet:renderURL>
							<a class='dropdown-item' href="<%=deprecatedFilterRenderURL%>"><liferay-ui:message key="aragon-categories-importer-web.option.deprecated-category-status"></liferay-ui:message></a>
						</div>
					</div>
				</li>
			</c:if>	
		</ul>
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid container-fluid-max-xl">
			</div>
		</div>
		<!-- Actions -->
		<ul class="navbar-nav">
			<!-- Clean options-->
			<li class="dropdown nav-item">
				<div class="dropdown">
					<button class="dropdown-toggle btn nav-link btn-unstyled" id="dropdownAreaButtonTrash" aria-label="trash" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-title='<%=LanguageUtil.get(request, "aragon-categories-importer-web.button.trash-helpmessage")%>' title='<%=LanguageUtil.get(request, "aragon-categories-importer-web.button.trash-helpmessage")%>'>
						<span class="navbar-breakpoint-down-d-none">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-trash" focusable="false" viewBox="0 0 512 512">
								<title><liferay-ui:message key="aragon-categories-importer-web.label.trash-tittle"></liferay-ui:message></title>
								<path class="lexicon-icon-outline" d="M64.4,440.7c0,39.3,31.9,71.3,71.3,71.3h240.6c39.3,0,71.3-31.9,71.3-71.3v-312H64.4V440.7z M128.2,192.6h255.5v231.7c0,13.1-10.7,23.8-23.8,23.8H152c-13.1,0-23.8-10.7-23.8-23.8V192.6z"></path>
								<polygon class="lexicon-icon-outline" points="351.8,32.9 351.8,0 160.2,0 160.2,32.9 64.4,32.9 64.4,96.1 447.6,96.1 447.6,32.9 "></polygon>
								<rect class="lexicon-icon-outline" x="287.9" y="223.6" width="63.9" height="191.6"></rect>
								<rect class="lexicon-icon-outline" x="160.2" y="223.6" width="63.9" height="191.6"></rect>
							</svg>
						</span>
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownAreaButtonTrash" style="left: -200px;">
						<!-- Reset current vocabulary historic -->
						<portlet:actionURL name="resetHistoricalCategories" var="resetHistoricalCategoriesByVocabularyActionURL">
							<portlet:param name="menuId" value="<%=menuId%>"/>
						</portlet:actionURL>
						<aui:form method="post" action="<%=resetHistoricalCategoriesByVocabularyActionURL%>">
								<button class='dropdown-item' type="submit"><liferay-ui:message key="aragon-categories-importer-web.button-option-delete-from"></liferay-ui:message> <%=menuId %></button>
						</aui:form>
						<!-- Reset all vocabularies historic -->
						<portlet:actionURL name="resetHistoricalCategories" var="resetHistoricalCategoriesAllActionURL">
						</portlet:actionURL>
						<aui:form method="post" action="<%=resetHistoricalCategoriesAllActionURL%>">
							<button type="submit" class='dropdown-item' ><liferay-ui:message key="aragon-categories-importer-web.button-option-delete-all"></liferay-ui:message></button>
						</aui:form>
						<c:if test="<%=vocabulary != null%>">
							<!-- Delete unused old categories -->
							<portlet:actionURL var="deleteUnusedOldCategoriesURL" name="deleteUnusedOldCategories">
								<portlet:param name="vocabularyId" value="<%=String.valueOf(vocabulary.getVocabularyId())%>"/>
								<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
							</portlet:actionURL>
							<aui:form method="post" action="<%=deleteUnusedOldCategoriesURL%>">
								<button type="submit" class='dropdown-item' ><liferay-ui:message key="aragon-categories-importer-web.button-option-delete-unused-categories"></liferay-ui:message></button>
							</aui:form>
						</c:if>
					</div>
				</div>
			</li>
			<!-- Synchronize options -->
			<li class="dropdown nav-item">
				<div class="dropdown">
					<button class="dropdown-toggle btn nav-link btn-unstyled" type="button" id="dropdownAreaButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="navbar-breakpoint-down-d-none">
							<svg class="lexicon-icon lexicon-icon-ellipsis-v" focusable="false" role="img" title="" viewBox="0 0 512 512"><title>ellipsis-v</title>
								<path class="lexicon-icon-outline" d="M319 255.5c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
								<path class="lexicon-icon-outline" d="M319 448c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
								<path class="lexicon-icon-outline" d="M319 64c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
							</svg>
						</span>
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownAreaButton" style="left: -200px;">
						<!-- Load current vocabulary categories -->
						<portlet:actionURL name="loadCategories" var="loadSelectCategoriesActionURL">
							<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
							<portlet:param name="vocabularyToImport" value="<%=menuId%>"/>
							<portlet:param name="allCategories" value="false"/>
						</portlet:actionURL>
						<aui:form method="post" action="<%=loadSelectCategoriesActionURL%>">
							<button class='dropdown-item' type="submit"><liferay-ui:message key="aragon-categories-importer-web.button-option-import-from"></liferay-ui:message> <%=menuId %></button>
						</aui:form>
						<!-- Load all categories -->
						<portlet:actionURL name="loadCategories" var="loadAllCategoriesActionURL">
							<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
							<portlet:param name="vocabularyToImport" value="<%=menuId%>"/>
							<portlet:param name="allCategories" value="true"/>
						</portlet:actionURL>
						<aui:form method="post" action="<%=loadAllCategoriesActionURL%>">
							<button type="submit" class='dropdown-item' ><liferay-ui:message key="aragon-categories-importer-web.button-option-import-all"></liferay-ui:message></button>
						</aui:form>
					</div>
				</div>
			</li>
		</ul>
	</div>
</nav>
<!-- Information process creation layouts and load categories-->
<% 
ExpandoBridge expandoBridgeVocabulary = vocabulary.getExpandoBridge();
String statusProcessClassCreateLayout= StringPool.BLANK;
String statusCreateLayout = StringPool.BLANK;
if (Validator.isNotNull(expandoBridgeVocabulary)){
	statusCreateLayout = GetterUtil.getString(expandoBridgeVocabulary.getAttribute("create-layout-status-process", Boolean.FALSE), statusCreateLayout);
	if (statusCreateLayout.contains("Generando")){
		statusProcessClassCreateLayout = "alert alert-danger";
	}else{
		statusProcessClassCreateLayout = "alert alert-success";
	}
	if(!statusCreateLayout.equals(StringPool.BLANK) && Validator.isNotNull(statusCreateLayout)){
	%>
	<div class="<%=statusProcessClassCreateLayout%>" role="alert"><%=statusCreateLayout %></div>
	<%
	}
}
String statusProcessClassLoadCategories= StringPool.BLANK;
String statusLoadCategories = StringPool.BLANK;
if (Validator.isNotNull(expandoBridgeVocabulary)){
	statusLoadCategories = GetterUtil.getString(expandoBridgeVocabulary.getAttribute("load-categories-status-process", Boolean.FALSE), statusLoadCategories);
	if (statusLoadCategories.contains("Importando")){
		statusProcessClassLoadCategories = "alert alert-danger";
	}else{
		statusProcessClassLoadCategories = "alert alert-success";
	}
	if(!statusLoadCategories.equals(StringPool.BLANK) && Validator.isNotNull(statusLoadCategories)){
	%>
	<div class="<%=statusProcessClassLoadCategories%>" role="alert"><%=statusLoadCategories %></div>
	<%
	}
}
%>

<c:choose>
	<c:when test="<%=Validator.isNotNull(vocabulary)%>">
		<div class="sheet sheet-lg">
			<%long vocabularyId = vocabulary.getVocabularyId();%>
			<c:if test="<%=menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES) || menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES) || menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES) || menuId.equals(AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES)%>">
				<!-- Pages creation form -->
				<liferay-frontend:fieldset label="aragon-categories-importer-web.label.pages-creation" collapsed="<%=false%>" collapsible="<%=false%>">
					<portlet:actionURL name="createVocabularyLayouts" var="createVocabularyLayoutsActionURL">
						<portlet:param name="assetVocabularyId" value="<%=String.valueOf(vocabularyId)%>"/>
					</portlet:actionURL>
					<aui:form method="post" action="<%=createVocabularyLayoutsActionURL%>">
						<%
						List<LayoutPageTemplateEntry> globalLayoutPageTemplateEntries = LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(themeDisplay.getCompanyGroupId(), LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
						List<LayoutPageTemplateEntry> groupLayoutPageTemplateEntries = LayoutPageTemplateEntryServiceUtil.getLayoutPageTemplateEntries(themeDisplay.getScopeGroupId(), LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);	
						%>
						<aui:select label="aragon-categories-importer-web.label.page-layout " name="layoutPageTemplateEntryId" helpMessage="aragon-categories-importer-web.label.page-layout-helpmessage">
							<aui:option value="-1"><liferay-ui:message key="aragon-categories-importer-web.select.page-layout-default-value"></liferay-ui:message></aui:option>
							<%for (LayoutPageTemplateEntry layoutPageTemplateEntry : globalLayoutPageTemplateEntries) {%>
								<aui:option value="<%=layoutPageTemplateEntry.getLayoutPageTemplateEntryId()%>"><%=layoutPageTemplateEntry.getName()%><liferay-ui:message key="aragon-categories-importer-web.select-layout-global-string"></liferay-ui:message></aui:option>
							<%}%>
							<%for (LayoutPageTemplateEntry layoutPageTemplateEntry : groupLayoutPageTemplateEntries) {%>
								<aui:option value="<%=layoutPageTemplateEntry.getLayoutPageTemplateEntryId()%>"><%=layoutPageTemplateEntry.getName()%></aui:option>
							<%}%>
						</aui:select>
						<aui:button type="submit" value="aragon-categories-importer-web.button.create-pages"></aui:button>
					</aui:form>
					<br/>
					<portlet:resourceURL var="downloadVocabularyRedirectMapActionURL">
						<portlet:param name="assetVocabularyId" value="<%=String.valueOf(vocabularyId)%>"/>
					</portlet:resourceURL>
					<aui:form method="post" action="<%=downloadVocabularyRedirectMapActionURL%>">
						<%
						String oldURLMap = ExpandoUtil.getOldUrlValue(themeDisplay.getCompanyId(), vocabularyId);
						String newURLMap = ExpandoUtil.getNewUrlValue(themeDisplay.getCompanyId(), vocabularyId);
						if(Validator.isNotNull(oldURLMap) && Validator.isNotNull(newURLMap)){
						%>
							<aui:button type="submit" value="aragon-categories-importer-web.button.redirection-pages"></aui:button>
						<%
						}
						%>
					</aui:form>
					<br/>
				</liferay-frontend:fieldset>
			</c:if>
			<liferay-frontend:fieldset label="aragon-categories-importer-web.label.historical-import" collapsed="<%=false%>" collapsible="<%=false%>">
				<!-- Historic -->
				<%
				String orderByCol = ParamUtil.getString(request, "orderByCol", "Categoria");
				String orderByType= ParamUtil.getString(request, "orderByType", "desc");
				OrderByComparator comparator = OrderByComparatorFactoryUtil.create("ImportCategoryRegistry", orderByCol, "asc".equals(orderByType)); 
				%>
				<liferay-portlet:renderURL varImpl="iteratorURL">
					<portlet:param name="mvcPath" value="/view.jsp"  />
					<portlet:param name="type" value="<%=type%>"/>
					<portlet:param name="menuId" value="<%=menuId%>"/>
				</liferay-portlet:renderURL>
				<liferay-ui:search-container iteratorURL="<%=iteratorURL%>" emptyResultsMessage="aragon-categories-importer-web.container-empty-results-message" delta="20" displayTerms="<%=new DisplayTerms(renderRequest) %>" orderByCol="<%=orderByCol %>" orderByType="<%=orderByType%>" orderByComparator="<%=comparator%>">
					<liferay-ui-search-container-results>
						<c:choose>
							<c:when test='<%=type.equals("any")%>'>
								<%
								searchContainer.setResults(ImportCategoryRegistryLocalServiceUtil.findByVocabularyId(vocabularyId, searchContainer.getStart(), searchContainer.getEnd()));
								searchContainer.setTotal(ImportCategoryRegistryLocalServiceUtil.countByVocabularyId(vocabularyId));
								%>
							</c:when>
							<c:otherwise>
								<%
								searchContainer.setResults(ImportCategoryRegistryLocalServiceUtil.findByVocabularyIdType(vocabularyId, type, searchContainer.getStart(), searchContainer.getEnd()));
								searchContainer.setTotal(ImportCategoryRegistryLocalServiceUtil.countByVocabularyIdType(vocabularyId, type));
								%>
							</c:otherwise>
						</c:choose>
					</liferay-ui-search-container-results>
					<liferay-ui:search-container-row className="es.aragon.base.categories_importer.model.ImportCategoryRegistry" keyProperty ="ImportCategoryRegistryId" modelVar="registry" >
						<%
						String categoryTitle = " --> Ruta no localizada (" + registry.getCategoryId() + ")";
						String categoryPath = " --> Ruta no localizada (" + registry.getCategoryId() + ")";
						AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchCategory(registry.getCategoryId());
						if (assetCategory != null) {
							categoryTitle = assetCategory.getTitle(locale) + categoryTitle;
							try {
								categoryPath = assetCategory.getPath(locale);
								categoryTitle = assetCategory.getTitle(locale) + " > " + categoryPath;
							} catch(Exception e) { 
								
							}
						}
						Date categoryImportDate = registry.getImportDate();
						String categoryImportDateString = StringPool.BLANK;
						if (categoryImportDate != null) {
							categoryImportDateString = simpleDateFormat.format(categoryImportDate);
						}
						String statusProcessClass= StringPool.BLANK;
						String status = LanguageUtil.get(request, "aragon-categories-importer-web.container-text-value-unprocessed");
						ExpandoBridge expandoBridge = assetCategory.getExpandoBridge();
						if (Validator.isNotNull(expandoBridge)){
							statusProcessClass = GetterUtil.getString(expandoBridge.getAttribute("import-category-status-process", Boolean.FALSE), status);
							if (statusProcessClass.contains("-")){
								String[] split = statusProcessClass.split("-");
								status = split[1];
							}
						}
						%>
						<liferay-ui:search-container-column-text name="aragon-categories-importer-web.container-column-text-category" value="<%=categoryTitle%>"/>
						<liferay-ui:search-container-column-text name="date" value="<%=categoryImportDateString%>"/>
						<liferay-ui:search-container-column-text name="aragon-categories-importer-web.container-column-text-status" property="type"/>		
						<liferay-ui:search-container-column-text align="center" name="aragon-categories-importer-web.container-column-text-actions">
							<c:if test='<%=registry.getType().equals("deprecated")%>'>
								<liferay-ui:icon-menu showWhenSingleIcon="true">
									<portlet:renderURL var="reassignCategoryRenderURL">
										<portlet:param name="mvcPath" value="/reassign-category.jsp"/>
										<portlet:param name="importCategoryRegistryId" value="<%=String.valueOf(registry.getImportCategoryRegistryId())%>"/>
										<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/>
									</portlet:renderURL>
									<liferay-ui:icon image="edit" url="<%=reassignCategoryRenderURL%>" message="aragon-categories-importer-web.icon-message-reassignToContents"/>
									<portlet:actionURL var="deleteCategoryURL" name="deleteCategory">
										<portlet:param name="importCategoryRegistryId" value="<%=String.valueOf(registry.getImportCategoryRegistryId())%>"/>
										<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
									</portlet:actionURL>
									<liferay-ui:icon image="delete" url="<%=deleteCategoryURL%>" message="aragon-categories-importer-web.icon-message-delete-category"/>
								</liferay-ui:icon-menu>
							</c:if>
						</liferay-ui:search-container-column-text>
						<c:if test='<%=registry.getType().equals("deprecated")%>'>
							<liferay-ui:search-container-column-text cssClass="<%=statusProcessClass %>" name="aragon-categories-importer-web.container-column-text-process" value="<%=status %>"/>
						</c:if>
					</liferay-ui:search-container-row>
					<liferay-ui:search-iterator/>
				</liferay-ui:search-container>
				<br>
			</liferay-frontend:fieldset>
		</div>
	</c:when>
	<c:otherwise>
		<!-- No vocabulary exists message -->
		<div class="container-fluid container-fluid-max-xl">
			<div class="alert alert-info">
				<liferay-ui:message key="aragon-categories-importer-web.alert.nonexist-vocabulary"></liferay-ui:message>
			</div>
		</div>
	</c:otherwise>	
</c:choose>
<style>
.status-Reasignacion{
	color:green;
}
.status-Eliminada{
	color:green;
}
.status-Reasignando\.\.\.{
	color:red;
}
.status-Eliminando\.\.\.{
	color:red;
}
.status-Finalizado{
	text-align:center;
	text-transform: uppercase;
	color:green;
}
.status-Generando{
	text-align:center;
	text-transform: uppercase;
	color:red;
}
.status-Importando{
	text-align:center;
	text-transform: uppercase;
	color:green;
}
</style>