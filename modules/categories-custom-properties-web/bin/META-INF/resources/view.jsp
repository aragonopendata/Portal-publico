<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="es.aragon.base.categories_custom_properties.model.CustomCategoryProperty"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategoryConstants"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.OrderFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%
long vocabularyId = ParamUtil.getLong(request, "vocabularyId", -1);
long parentCategoryId = ParamUtil.getLong(request, "parentCategoryId", -1);
String search = ParamUtil.getString(request, "search", "");
%>
<div class="container-fluid-1280 main-content-body">
		<!-- Caja buscador -->
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid container-fluid-max-xl">
				<portlet:renderURL var="textSearchRenderURL">
					<portlet:param name="mvcPath" value="/view.jsp"/>
					<portlet:param name="vocabularyId" value="<%=String.valueOf(vocabularyId)%>"/>
					<portlet:param name="parentCategoryId" value="<%=String.valueOf(parentCategoryId)%>"/>
				</portlet:renderURL>
				<aui:form method="post" action="<%=textSearchRenderURL%>">
					<div class="input-group">
						<div class="input-group-item">
						<input name='<portlet:namespace/>search' aria-label="Search" class="form-control input-group-inset input-group-inset-after" placeholder='<%=LanguageUtil.get(request,"categories.custom.properties.web.search-placeholder") %>' ref="search" type="text" value="<%=search%>">
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
		<br>
		<!-- Fin Caja buscador -->
	<c:if test="<%=vocabularyId == -1 && Validator.isNull(search)%>">
	
		<!-- VISTA DE VOCABULARIO-->
		<ul aria-label="Categorías" class="breadcrumb breadcrumb-horizontal" role="navigation">
			<li class="active">Vocabularios</li>
		</ul>
		<div class="form-group input-text-wrapper">
		</div>
		<liferay-ui:search-container emptyResultsMessage="No se han encontrado vocabularios" deltaConfigurable="<%=false%>">
			<liferay-ui-search-container-results>
				<%
				List<AssetVocabulary> assetVocabularies = AssetVocabularyLocalServiceUtil.getGroupVocabularies(themeDisplay.getScopeGroupId()); 
				searchContainer.setTotal(assetVocabularies.size());
				searchContainer.setResults(assetVocabularies);
				%>
			</liferay-ui-search-container-results>
			<liferay-ui:search-container-row className="com.liferay.asset.kernel.model.AssetVocabulary" keyProperty="vocabularyId" modelVar="vocabulary" >
				<portlet:renderURL var="vocabularyRenderURL">
					<portlet:param name="mvcPath" value="/view.jsp"/>
					<portlet:param name="vocabularyId" value="<%=String.valueOf(vocabulary.getVocabularyId())%>"/>
					<portlet:param name="parentCategoryId" value="<%=String.valueOf(AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)%>"/>
				</portlet:renderURL>
				<liferay-ui:search-container-column-text name="name" property="name" href="<%=vocabularyRenderURL%>"/>
				<liferay-ui:search-container-column-text name="description" value="<%=vocabulary.getDescription(locale)%>"/>
				<liferay-ui:search-container-column-text name="categories" value="<%=String.valueOf(vocabulary.getCategoriesCount())%>"/>
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator paginate="false"/>
		</liferay-ui:search-container>
	</c:if>
	<c:if test="<%=(vocabularyId != -1 && parentCategoryId != -1) || (vocabularyId == -1 && Validator.isNotNull(search))%>">

		<!-- VISTA DE CATEGORIAS -->

		<%
		AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(vocabularyId);
		AssetCategory parentAssetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(parentCategoryId);
		%>
		<c:if test="<%=assetVocabulary != null%>">
			<ul aria-label="Categorías" class="breadcrumb breadcrumb-horizontal" role="navigation">
				<portlet:renderURL var="rootRenderURL">
					<portlet:param name="mvcPath" value="/view.jsp"/>
				</portlet:renderURL>
				<li>
					<a href="<%=rootRenderURL%>">Vocabularios</a>
				</li>
				<c:choose>
					<c:when test="<%=parentCategoryId > 0%>">
						<portlet:renderURL var="vocabularyRenderURL">
							<portlet:param name="mvcPath" value="/view.jsp"/>
							<portlet:param name="vocabularyId" value="<%=String.valueOf(vocabularyId)%>"/>
							<portlet:param name="parentCategoryId" value="0"/>
						</portlet:renderURL>
						<li>
							<a href="<%=vocabularyRenderURL%>"><%=assetVocabulary.getTitle(locale)%></a>
						</li>
					</c:when>
					<c:otherwise>
						<li><%=assetVocabulary.getTitle(locale)%></li>
						<%renderResponse.setTitle(assetVocabulary.getTitle(locale));%>
					</c:otherwise>
				</c:choose>
				<c:if test="<%=parentAssetCategory != null%>">
					<%List<AssetCategory> ancestorCategories = parentAssetCategory.getAncestors();%>
					<c:if test="<%=ancestorCategories != null && !ancestorCategories.isEmpty()%>">
						<%for (AssetCategory ancestorCategory : ancestorCategories) {%>
							<portlet:renderURL var="categoryRenderURL">
								<portlet:param name="mvcPath" value="/view.jsp"/>
								<portlet:param name="vocabularyId" value="<%=String.valueOf(vocabularyId)%>"/>
								<portlet:param name="parentCategoryId" value="<%=String.valueOf(ancestorCategory.getCategoryId())%>"/>
							</portlet:renderURL>
							<li>
								<a href="<%=categoryRenderURL%>"><%=ancestorCategory.getTitle(locale)%></a>
							</li>
						<%}%>
					</c:if>
					<li class="active"><%=parentAssetCategory.getTitle(locale)%></li>
					<%renderResponse.setTitle(parentAssetCategory.getTitle(locale));%>
				</c:if>
			</ul>
		</c:if>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="mvcPath" value="/view.jsp"  />
			<portlet:param name="vocabularyId" value="<%=String.valueOf(vocabularyId)%>"/>
			<portlet:param name="parentCategoryId" value="<%=String.valueOf(parentCategoryId)%>"/>
			<portlet:param name="search" value="<%=search%>"/>
		</liferay-portlet:renderURL>
		<liferay-ui:search-container iteratorURL="<%=iteratorURL%>" emptyResultsMessage="No se han encontrado categorias">
			<liferay-ui-search-container-results>
				<%
				Long contentsCountLong = (Long)request.getAttribute("totalResults");
				List<AssetCategory> contentsList = (List<AssetCategory>)request.getAttribute("listResults");
				searchContainer.setResults(contentsList);
				searchContainer.setTotal(contentsCountLong.intValue());
				
				%>
			</liferay-ui-search-container-results>
			<liferay-ui:search-container-row className="com.liferay.asset.kernel.model.AssetCategory" keyProperty="categoryId" modelVar="category" >
				<portlet:renderURL var="categoryRenderURL">
					<portlet:param name="mvcPath" value="/view.jsp"/>
					<portlet:param name="vocabularyId" value="<%=String.valueOf(category.getVocabularyId())%>"/>
					<portlet:param name="parentCategoryId" value="<%=String.valueOf(category.getCategoryId())%>"/>
					<portlet:param name="search" value="<%=search%>"/>
				</portlet:renderURL>
				<liferay-ui:search-container-column-text name="name" value="<%=category.getTitle(locale)%>" href="<%=categoryRenderURL%>"/>
				<liferay-ui:search-container-column-text name="description" value="<%=category.getDescription(locale)%>"/>
				<liferay-ui:search-container-column-text name="subcategories" value="<%=String.valueOf(AssetCategoryLocalServiceUtil.getChildCategoriesCount(category.getCategoryId()))%>"/>
				<liferay-ui:search-container-column-jsp align="right" path="/category_actions.jsp" />
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator/>
		</liferay-ui:search-container>
	</c:if>
</div>
