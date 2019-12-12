<%@page import="es.aragon.base.crawler.model.Page"%>
<%@page import="es.aragon.base.crawler.service.PageLocalServiceUtil"%>
<%@page import="es.aragon.base.search.web.adapter.HitAdapter"%>
<%@page import="es.aragon.base.crawler.model.RootPage"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.ClassName"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.KeyValuePair" %>
<%@page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@page import="com.liferay.portal.kernel.util.Validator" %>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<%@include file="init.jsp"%>


<liferay-portlet:renderURL var="searchOrFilterURL">
	<liferay-portlet:param name="searchText" value="SEARCHED_TEXT"/>
	<liferay-portlet:param name="currentPage" value="0"/>
	<liferay-portlet:param name="selectedCategories" value="SELECTED_CATEGORIES"/>
	<liferay-portlet:param name="selectedDateRange" value="SELECTED_DATE_RANGE"/>
	<liferay-portlet:param name="searchType" value="SELECTED_SEARCH_TYPE"/>
	<liferay-portlet:param name="selectedPortalsToSearch" value="SELECTED_PORTALS_TO_SEARCH"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="changePageURL">
	<liferay-portlet:param name="searchText" value="${searchText}"/>
	<liferay-portlet:param name="currentPage" value="CURRENT_PAGE"/>
	<liferay-portlet:param name="selectedCategories" value="${selectedCategories}"/>
	<liferay-portlet:param name="selectedDateRange" value="${selectedDateRange}"/>
	<liferay-portlet:param name="searchType" value="${searchType}"/>
	<liferay-portlet:param name="selectedPortalsToSearch" value="${selectedPortalsToSearch}"/>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="cleanFiltersURL">
	<liferay-portlet:param name="searchText" value=""/>
	<liferay-portlet:param name="currentPage" value="0"/>
	<liferay-portlet:param name="selectedCategories" value=""/>
	<liferay-portlet:param name="selectedDateRange" value=""/>
	<liferay-portlet:param name="searchType" value="any"/>
</liferay-portlet:renderURL>

<div class="main-buscador">
	<div class="container u-container-mobile-0">
   		<div class="abrir-filtro-contenedor">
   			<button class="u-btn u-btn-white js-abrir-filtro-buscador hidden-sm hidden-md hidden-lg" aria-controls="buscadorAvanzado" aria-expanded="false">
   				<strong><liferay-ui:message key="button.advanced.search"/></strong>
   			</button>
   		</div>
        <section class="resultados">
        	<div class="info-resultados">
           		<p class="info-resultados__txt-numero-cadena">
           			<liferay-ui:message key="applied-filters.total.results" arguments="${totalResults}"/>
	        		<c:if test="${searchText != null && searchText != ''}">
					    <liferay-ui:message key="applied-filters.total.results.about"/> <strong id="aboutSearchedTextInfo">${searchText}</strong>
					</c:if>
           		</p>
           		<%-- <c:if test="${not empty selectedCategoriesArray}"> --%>
	           		<button href="#cat-aplicadas" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="cat-aplicadas" class="info-resultados__btn">
	           			<liferay-ui:message key="applied-filters.show.results.about" />
	           		</button>
           		<%-- </c:if> --%>
        	</div>
        	<div class="resultados-buscador">
        		<c:if test="${not empty selectedCategoriesArray or searchType!='any'}">
        			<ul class="resultados-buscador__listado-cat col-xs-12 collapse" id="cat-aplicadas">
        				<c:forEach items="${selectedCategoriesArray}" var="selectedCategory">
        					<c:set var="category" value="${assetCategoryLocalService.fetchAssetCategory(selectedCategory)}" />
	        				<c:if test="${not empty category}">
			              		<li class="level">
			                 		<p class="level-link js-level-link">${category.getTitle(themeDisplay.getLocale())}</p>
			                 		<button class="remove-filter js-remove-filter" aria-label="Eliminar filtro ${category.getTitle(themeDisplay.getLocale())}" onClick="javascript:onClickRemoveFilter(${selectedCategory})">
			                       		<span></span>
			                       		<span></span>
			                 		</button>
			              		</li>
		              		</c:if>
		              	</c:forEach>
	              		<% 
						long[] selectedAssetTypesList = StringUtil.split(selectedAssetTypes, 0L);
						for (long selectedAssetType : selectedAssetTypesList){
							ClassName className = ClassNameLocalServiceUtil.fetchClassName(selectedAssetType);
							if (Validator.isNotNull(className)) {
								String classNameResource = ResourceActionsUtil.getModelResource(themeDisplay.getLocale(), className.getValue());
						%>
								<c:set var="selectedAssetType" value="<%=selectedAssetType %>" />
								<c:if test="${selectedAssetTypesArray.contains(selectedAssetType)}">
									<li class="level">
				                 		<p class="level-link js-level-link"><%=LanguageUtil.get(request,"filters."+className.getValue()) %></p>
				                 		<button class="remove-filter js-remove-filter" aria-label="Eliminar filtro <%=classNameResource %>" onClick="javascript:onClickRemoveAsset(${selectedAssetType})">
				                       		<span></span>
				                       		<span></span>
				                 		</button>
				              		</li>
								</c:if>
						<%
							}
						}
						%>
						
				
	           		</ul>
        		</c:if>
        	</div>
     	</section>                     
        <div class="row">
        	<!-- Columna filtrado -->
        	<h2 id="idFiltros" class="oculto">Filtro de resultados</h2> 
           	<section class="filtrado-buscador hidden-xs col-xs-12 col-md-5 col-lg-4" aria-labelledby="idFiltros" role="region" id="buscadorAvanzado">
                <div class="position-relative">
                	<section class="filtrado">
						<button tabindex="0" class="close-btn js-filtrado-close-btn hidden-sm hidden-md hidden-lg">
							<p class="oculto">Cerrar</p>
							<span></span>
							<span></span>
							<span></span>
						</button>
                		<button class="btn-borrar js-btn-borrar u-btn u-btn-red float-right" onClick="javascript:onClickCleanFilters()">
                			<strong><liferay-ui:message key="filters.button.delete"/></strong>
                		</button>
	                    <div class="clearfix"></div>
	                    <div class="filtrado-container">
	                    	<form action="javascript:void(0)" method="get" id="<portlet:namespace/>search_fm" name="<portlet:namespace/>search_fm" onsubmit="onClickSearchOrFilter()">
		                    	<p class="intro">
		                    		<c:choose>
										<c:when test="<%= helpMessage != null && !helpMessage.isEmpty() %>">
											<%=helpMessage%>
										</c:when>
										<c:otherwise>
											<liferay-ui:message key="configuration.default-help-message" />
										</c:otherwise>
									</c:choose>
		                    	</p>
		                       	<div class="search-module">
		                       		<div class="input-group">
		                       			<div class="search-input-container">
		                       				<input type="search" placeholder='<liferay-ui:message key="filters.placeholder.search"/>' class="search-input" title='<liferay-ui:message key="filters.search"/>' id="searchText" maxlength="400" value="${searchText}">
		                                </div>
		                                <button class="search-btn" type="submit">
		                                	<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-lupa.svg" class="lupa" alt='<liferay-ui:message key="filters.search"/>'>
		                                	<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-lupa-negative.svg" class="lupa-focus" alt='<liferay-ui:message key="filters.search"/>'>
		                                </button>
		                          	</div>
		                       	</div>
		                       	<div class="date">
		                          	<fieldset>
			                          	<legend class="date__title"><liferay-ui:message key="publish-date" />:</legend>
			                          	<div class="row">
			                            	<div class="date-container col-xs-12">
			                            		<label for="textDesde" class="date__input-label"><liferay-ui:message key="filters.from"/></label>
			                            		<p id="formatoDesde" class="oculto"><liferay-ui:message key="filters.date-format"/></p>
			                                   	<input id="textDesde" type="text" class="date__input-date form-input" placeholder="DD/MM/YYYY" value="${fromDate}" aria-describedby="formatoDesde">
												<a role="button" href="javascript:void(0)" id="fechaDesdeDateIcon" class="accCalendar datePicker" aria-expanded="false">
													<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-calendar.svg" alt="Calendario fecha desde" class="icon-calendar-custom">
												</a>
			                                </div>
			                                <div class="date-container col-xs-12">
			                                   	<label for="textHasta" class="date__input-label"><liferay-ui:message key="filters.to"/></label>
			                                   	<p id="formatoHasta" class="oculto"><liferay-ui:message key="filters.date-format"/></p>
			                                   	<input id="textHasta" type="text" class="date__input-date form-input" placeholder="DD/MM/YYYY" value="${toDate}" aria-describedby="formatoHasta">
			                                	<a role="button" href="javascript:void(0)" id="fechaHastaDateIcon" class="accCalendar datePicker" aria-expanded="false">
													<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-calendar.svg" alt="Calendario fecha hasta" class="icon-calendar-custom">
												</a>
			                                </div>
			                          	</div>
									</fieldset>
		                       	</div>
		                       	<c:if test="${not empty selectedVocabulariesArray}">
		                       		<!-- Categories facets -->
		                       		<c:forEach items="${selectedVocabulariesArray}" var="selectedVocabularyId">
		                       			<c:set var="vocabulary" value="${assetVocabularyLocalService.fetchAssetVocabulary(selectedVocabularyId)}" />
		                       			<c:if test="${not empty vocabulary}">
		                       			<c:set var="vocabularyTitle" value="${fn:toUpperCase(fn:substring(vocabulary.getTitle(themeDisplay.getLocale()), 0, 1))}${fn:toLowerCase(fn:substring(vocabulary.getTitle(themeDisplay.getLocale()), 1,fn:length(vocabulary.getTitle(themeDisplay.getLocale()))))}" />
		                       				<c:choose>
				                       			<c:when test="${facetedVocabularies.get(selectedVocabularyId) != null}">
				                       				<c:if test="${not empty facetedVocabularies.get(selectedVocabularyId)}">
				                       					<div class="categoria">
				                       					<a href="#vocabulary_${vocabulary.getVocabularyId()}"  class="categoria__title arrow-open js-arrow-open" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="vocabulary_${vocabulary.getVocabularyId()}" aria-label="Mostrar ${vocabulary.getName()}" data-label-closed="Mostrar ${vocabulary.getName()}" data-label-opened="Ocultar ${vocabulary.getName()}">${vocabularyTitle}</a>
															<fieldset>
																<legend class="oculto">Filtro de ${vocabulary.getTitle(themeDisplay.getLocale())}</legend>
																<ul class="categoria__listado listado collapse u-padding-left-0" id="vocabulary_${vocabulary.getVocabularyId()}">
																	<c:forEach items="${facetedVocabularies.get(selectedVocabularyId)}" var="facetedCategory">
																		<li class="listado__item check-item">
																			<div class="cont-item js-check-item">
																				<input type="checkbox" class="check-item__check" id="c${facetedCategory.key.getCategoryId()}" data-id="${facetedCategory.key.getCategoryId()}" ${selectedCategoriesArray.contains(facetedCategory.key.getCategoryId()) ? "checked" : ""}>
																				<label class="check-item__label" for="c${facetedCategory.key.getCategoryId()}">
																					${facetedCategory.key.getTitle(themeDisplay.getLocale())}
																					<c:if test="${facetedCategory.value != 0}">
																						(${facetedCategory.value})
																					</c:if>
																				</label>
																			</div>
																		</li>
																	</c:forEach>
																</ul>
															</fieldset>
														</div>
				                       				</c:if>
				                       			</c:when>
				                       			<c:otherwise>
													<c:set var="rootCategories" value="${assetCategoryLocalService.getVocabularyRootCategories(vocabulary.getVocabularyId(), -1, -1, null)}" />				                       				
													<c:if test="${not empty rootCategories}">
														<div class="categoria">
															<a href="#vocabulary_${vocabulary.getVocabularyId()}" id="panel_v_${vocabulary.getVocabularyId()}" onclick="refreshChildCategoriesFilter('${vocabulary.getVocabularyId()}', 0);" class="categoria__title arrow-open js-arrow-open collapsed" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="vocabulary_${vocabulary.getVocabularyId()}" aria-label="Mostrar ${vocabulary.getName()}" data-label-closed="Mostrar ${vocabulary.getName()}" data-label-opened="Ocultar ${vocabulary.getName()}">${vocabularyTitle}</a>
															<fieldset>
																<legend class="oculto" id="elementId">Filtro de ${vocabulary.getName()}</legend>
																<div id="vocabulary_${vocabulary.getVocabularyId()}" class="collapse"><div class="loading-animation"></div></div>
															</fieldset>
														</div>
													</c:if>
				                       			</c:otherwise>
				                       		</c:choose>
				                       	</c:if>
				                	</c:forEach>
		                       	</c:if>
		                       	<c:if test="<%=checkedAssetType.equals("true")%>">
		                       		<!-- Content type facet --> 
			                       	<div class="categoria">                       		
			                       		<a href="#tiposContenido" class="categoria__title arrow-open js-arrow-open" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="tiposContenido" aria-label="Mostrar tipos de contenido" data-label-closed="Mostrar tipos de contenido" data-label-opened="Ocultar tipos de contenido"><%=LanguageUtil.get(request, "label.document-type")%></a>
			                       		<fieldset>
											<legend class="oculto">Filtro de contenido</legend>
											<ul class="categoria__listado listado collapse u-padding-left-0" id="tiposContenido">
												<% 
												long[] selectedAssetTypesList = StringUtil.split(selectedAssetTypes, 0L);
												for (long selectedAssetType : selectedAssetTypesList) {
													ClassName className = ClassNameLocalServiceUtil.fetchClassName(selectedAssetType);
												%>
													<c:if test="<%=Validator.isNotNull(className)%>">	
														<%String classNameResource = ResourceActionsUtil.getModelResource(themeDisplay.getLocale(), className.getValue());%>
														<li class="listado__item check-item">
															<div class="cont-item js-check-item">
																<c:set var="selectedAssetType" value="<%=selectedAssetType %>" />
																<input type="checkbox" class="check-item__check check-asset-type" id="asset<%=selectedAssetType%>" data-id="<%=selectedAssetType%>" ${selectedAssetTypesArray.contains(selectedAssetType) ? "checked" : ""}>
																<label class="check-item__label" for="asset<%=selectedAssetType%>"> <%=LanguageUtil.get(request,"filters."+className.getValue()) %></label>
															</div>
														</li>
													</c:if>
												<%
												}
												%>
											</ul>
										</fieldset>
			                        </div>
		                        </c:if>
		                        <c:if test="<%=checkedViewExternalSearchFilter%>">
		                        	<c:if test="${not empty externalPortals}">
			                       		<!-- filter to search in external portals crawled results --> 
				                       	<div class="categoria portal-externo">                       		
				                       		<a href="#buscarEn" class="categoria__title arrow-open js-arrow-open" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="buscarEn" aria-label="Mostrar buscar en" data-label-closed="Mostrar buscar en" data-label-opened="Ocultar buscar en"><%=LanguageUtil.get(request, "label.search-in")%></a>
				                       		<fieldset>
												<legend class="oculto">Filtro para buscar en portales externos</legend>
												<ul class="categoria__listado listado collapse u-padding-left-0" id="buscarEn">
													 <c:forEach items="${externalPortals}" var="rootPage"> 
														<li class="listado__item check-item">
															<div class="cont-item js-check-item">
																<input type="checkbox" class="check-item__check check-portal-search" id="portal_${rootPage.getRootPageId()}" data-id="${rootPage.getRootPageId()}" ${selectedPortalToSearchList.contains(rootPage.getRootPageId()) ? "checked" : ""}>
																<label class="check-item__label" for="portal_${rootPage.getRootPageId()}">${rootPage.getAlias()}</label>
															</div>
														</li>
													 </c:forEach> 												
												</ul>
											</fieldset>
				                        </div>
			                        </c:if>
			                    </c:if>
		                       	<div class="btn-filtrar-container text-center">
		                          	<button class="btn-filtrar js-btn-buscar u-btn u-btn-red" type="submit">
		                          		<strong><liferay-ui:message key="filters.button.filter"/></strong>
		                          	</button>
		                          	<button class="btn-borrar js-btn-buscar u-btn u-btn-red-border" type="button" onClick="javascript:onClickCleanFilters()">
		                          		<strong><liferay-ui:message key="filters.button.delete"/></strong>
		                          	</button>
		                       	</div>
		                	</form>
	                    </div>
	            	</section>
	            </div>             
           	</section>
			<!-- Columna resultados -->
           	<section class="info-buscador col-xs-12 col-md-7 col-lg-8">
           		<c:choose>
					<c:when test="${not empty results}">
						<h2 id="idResultados" class="oculto">Listado de resultados</h2> 
	            		<section class="u-padding-bottom-6" aria-labelledby="idResultados" role="region">
	            			<div class="list-news-module">		
								<ul class="listado">
									<c:forEach items="${results}" var="hitAdapter">
										<li class="col-12 listado__item">
											<c:set var="articleURL" value="${hitAdapter.getURL()}"/>
											<c:if test="${articleURL == '' && hitAdapter.getAssetEntryId() != 0}">
												<liferay-portlet:renderURL var="journalArticleURL" portletName="${liferaySearchPortlet}" windowState="${liferayWindowState}">
													<liferay-portlet:param name="mvcPath" value="/view_content.jsp" />
													<liferay-portlet:param name="type" value="content" />
													<liferay-portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
													<liferay-portlet:param name="assetEntryId" value="${hitAdapter.getAssetEntryId()}" />
												</liferay-portlet:renderURL>
												<c:set var="articleURL" value="${journalArticleURL}"/>
											</c:if>
											<%String linkTitle = ""; %>
											<c:if test="${hitAdapter.getTarget() == '_blank'}">
												<%linkTitle = "title='Se abre en ventana nueva'";%>
											</c:if>
											<a href="${articleURL}" class="link" target="${hitAdapter.getTarget()}" <%=linkTitle%>>
				            					<span class="title">${hitAdapter.getTitle()}</span>
				            				</a>
				            				<c:choose>
				            					<c:when test = "${fn:contains(hitAdapter.getTitle(), themeDisplay.getPortalURL())}">
				            						<p><span class="date">${hitAdapter.getDisplayDate()}</span></p>
				            					</c:when>
				            					<c:otherwise>
												<%
													String dateAndRootPageValue = "";

													HitAdapter adapter = (HitAdapter) pageContext.getAttribute("hitAdapter");
													if(adapter != null) {
														dateAndRootPageValue = adapter.getDisplayDate();
													}
													if(adapter != null && adapter.getClassName().equals(Page.class.getName())) {
														String[] urlSplited = adapter.getURL().split("/");
														if(urlSplited.length >= 3) {
															dateAndRootPageValue = dateAndRootPageValue.concat(" - Resultado de ").concat(urlSplited[0].concat("/").concat("/").concat(urlSplited[2]));
														}
													}
													
												%>
				            						<p><span class="date"><%=dateAndRootPageValue %> </span></p>
				            					</c:otherwise>
			            					</c:choose>
			                                <c:if test="${not empty hitAdapter.getCategories()}">
				                                <ul class="categories">
				                                	<c:forEach items="${hitAdapter.getCategories()}" var="category">
					                                	<li class="categories__item"><span class="name">${category}</span></li>
				                                    </c:forEach>
				                                </ul>
			                                </c:if>
			                        	</li>
			                        	
									</c:forEach>
								</ul>
			                   	<h3 class="oculto"><liferay-ui:message key="pagination.more.results" /></h3>
			                   	<p class="info-paginacion"><liferay-ui:message key="pagination.current.page" arguments="${currentPage + 1}" /> <liferay-ui:message key="pagination.total.pages" arguments="${totalPages}" /></p>
			                    <ul class="pagination">
			                    	<c:if test = "${currentPage - 1 >= 0}">
								    	<li class="prev"><a href="#" onclick="javascript:onClickChangePage(${currentPage - 1})"><span class="oculto"><liferay-ui:message key="pagination.page" /> </span><span><liferay-ui:message key="pagination.previous" /></span><span class="oculto"> <liferay-ui:message key="pagination.info" /></span></a></li>
								    </c:if>
								    <c:if test = "${currentPage - 2 >= 0}">
								    	<li><a href="#" onclick="javascript:onClickChangePage(${currentPage - 2})"><span class="oculto"><liferay-ui:message key="pagination.page" /> </span><span>${currentPage - 1}</span><span class="oculto"> <liferay-ui:message key="pagination.info" /></span></a></li>
								    </c:if>
								    <c:if test = "${currentPage - 1 >= 0}">
								    	<li><a href="#" onclick="javascript:onClickChangePage(${currentPage - 1})"><span class="oculto"><liferay-ui:message key="pagination.page" /> </span><span>${currentPage}</span><span class="oculto"> <liferay-ui:message key="pagination.info" /></span></a></li>
								    </c:if>
			                        <li class="active"><span class="oculto"><liferay-ui:message key="pagination.page" /> </span><span aria-current="page"><strong>${currentPage + 1}</strong></span><span  class="oculto"> <liferay-ui:message key="pagination.info" /></span></li>
			                        <c:if test = "${currentPage + 1 < totalPages}">
								    	<li><a href="#" onclick="javascript:onClickChangePage(${currentPage + 1})"><span class="oculto"><liferay-ui:message key="pagination.page" /> </span><span>${currentPage + 2}</span><span class="oculto"> <liferay-ui:message key="pagination.info" /></span></a></li>
								    </c:if>
								    <c:if test = "${currentPage + 2 < totalPages}">
								    	<li class="points"><span>...</span></li>
								    </c:if>
			                        <c:if test = "${currentPage + 1 < totalPages}">
								    	<li class="next"><a href="#" onclick="javascript:onClickChangePage(${currentPage + 1})"><span class="oculto"><liferay-ui:message key="pagination.page" /> </span><span><liferay-ui:message key="pagination.next" /></span><span class="oculto"> <liferay-ui:message key="pagination.info" /></span></a></li>
								    </c:if>
			                    </ul>
			               	</div>
          				</section>
          			</c:when>
					<c:otherwise>
						<section class="no-result">
	                     	<p class="col-xs-12"><liferay-ui:message key="results.no.results" /></p>
	                	</section>
					</c:otherwise>
				</c:choose> 
        	</section>
        </div>
    </div>
</div>
<portlet:resourceURL var="resourceURL"/>
<%
List<Long> selectedCategoriesIds = (List<Long>) renderRequest.getAttribute("selectedCategoriesArray");
String selectedCategoriesIdsString = StringPool.BLANK;
if (selectedCategoriesIds != null && !selectedCategoriesIds.isEmpty()) {
	for (int i = 0; i < selectedCategoriesIds.size(); i++) {
		if (i > 0) {
			selectedCategoriesIdsString = selectedCategoriesIdsString + StringPool.COMMA;
		}
		selectedCategoriesIdsString = selectedCategoriesIdsString + selectedCategoriesIds.get(i);
	}
}

%>
<script>

	/* Analytics function , event to show search data */
	if (typeof ga === "function"){
		$(document).ready(function() {
			var searchedText = $("#searchText")[0].value;
		    if(searchedText != null && searchedText != ""){
		    	var pathname = window.location.pathname;
		    	ga('send', 'event', 'search_results', searchedText , ${totalResults} + " resultados con la url "+  pathname);		    
		    }
		});
	}

	var originalSelectedCategories = '<%=selectedCategoriesIdsString%>'.split(',');

 	/**
	* Refresh the asset categories filter component
	*/
	function refreshChildCategoriesFilter(vocabularyId, parentCategoryId) {
		var hasDivElements = 0;
		if(parentCategoryId==0){
			var clickedElement = $("#panel_v_"+vocabularyId);
			hasDivElements = $(clickedElement).parent().find("#vocabulary_"+vocabularyId).find("li").length;
		} else {
			var clickedElement = $("#panel_c_"+parentCategoryId);
			hasDivElements = $(clickedElement).parent().find("#"+parentCategoryId+"-listado").find("li").length;
		}
		if(hasDivElements==0){
			AUI().use('aui-io-request', function(A){
				A.io.request('<%=resourceURL.toString()%>', {
					method: 'post',
					dataType: 'json',
						data: {
						<portlet:namespace/>vocabularyId: vocabularyId,
						<portlet:namespace/>parentCategoryId: parentCategoryId
					},
					on: {
						success: function(data) {
							var categoriesArray = this.get('responseData');	 
							var categoryElementsHTML = '';
							for (var i = 0; i < categoriesArray.length; i++) {
								var category = categoriesArray[i];
								var checked = "";
								for (var j = 0; j < originalSelectedCategories.length; j++) { 
									if (originalSelectedCategories[j] == category.categoryId) {
										checked = "checked=true";
									}
								}
								var categoryHTML = '';
								categoryHTML += '<li class="listado__item check-item">';
								categoryHTML += '	<div class="cont-item js-check-item">';
								categoryHTML += '		<input type="checkbox" class="check-item__check" id="c' + category.categoryId + '" data-id="' + category.categoryId + '" ' + checked + '>';
								categoryHTML += '		<label class="check-item__label" for="c' + category.categoryId + '">' + category.title + '</label>';
								categoryHTML += '	</div>';
								if (category.hasChildren == true) {
									categoryHTML += '<a class="arrow-open js-arrow-open" id="panel_c_'+category.categoryId+'" onclick="refreshChildCategoriesFilter(' + vocabularyId + ', ' + category.categoryId + ');" href="#' + category.categoryId + '-listado" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="' + category.categoryId + '-listado" aria-label="Mostrar opciones del tema ' + category.title + '" data-label-closed="Mostrar opciones del tema ' + category.title + '" data-label-opened="Ocultar opciones del tema ' + category.title + '"></a> ';
									categoryHTML += '<div id="'+category.categoryId+'-listado" class="collapse"><div class="loading-animation collapse"></div></div>';
								}
								categoryHTML += '</li>';
								categoryElementsHTML = categoryElementsHTML + categoryHTML;
							}
							
							if (parentCategoryId == 0) {
								$('#vocabulary_'+vocabularyId).remove();
								categoryElementsHTML = '<ul class="categoria__listado listado collapse show u-padding-left-0" id="vocabulary_'+vocabularyId+'">'+categoryElementsHTML+'</ul>';
								$(clickedElement).parent().find("fieldset").append(categoryElementsHTML);
							} else {
								$('#'+parentCategoryId+'-listado').remove();
								categoryElementsHTML = '<ul class="listado collapse show u-padding-left-1" id="'+parentCategoryId+'-listado">'+categoryElementsHTML+'</ul>';
								var categoryPanelElement = document.getElementById("panel_c_" + parentCategoryId);
								if (categoryPanelElement != null) {
									categoryPanelElement.parentElement.appendChild(createElementFromHTML(categoryElementsHTML), 'afterend');
								}
							}
						}
					}
				});
			});
		}
	}
 	/**
 	*	Deploy external urls component when loading the page
 	*/
 	 $(document).ready(function($) {
		if($('input[class$= "check-portal-search"]').is(':checked')){
			$('a[href^= "#buscarEn"]').click();	
		}
	});
	/**
	* Prevents the standard onsubmit function
	*/
	$("#<portlet:namespace/>search_fm").on('submit', function(event) {
		event.preventDefault();
	});
	
	/**
	* Redirects the browser to a search results page with the selected filters
	*/
	function onClickSearchOrFilter() {
		/**Gets original searchURL*/
		var searchOrFilterURL = "<%=searchOrFilterURL%>";
		/**Gets searched text*/
		var searchedText = $("#searchText")[0].value;
		/**Adds search into the search url parameters*/
		if (searchedText != null && searchedText != "") {
			searchedText = searchedText.replace(/\//g, "0x2f");
		    searchedText = searchedText.replace(/\\/g, "0x5c");
		    searchedText = searchedText.replace(/\./g, "0x2e");
		    searchedText = searchedText.replace(/[!'()*]/g, "");
		    searchedText = encodeURIComponent(searchedText);
			searchOrFilterURL = searchOrFilterURL.replace("SEARCHED_TEXT", searchedText);
		} else {
			searchOrFilterURL = searchOrFilterURL.replace("/q/SEARCHED_TEXT", "");
		}
		/**Adds selected categories and facets to the URL*/
		searchOrFilterURL = getSelectedCategoriesAndFacets(searchOrFilterURL);
		/**Adds selected date range to the URL*/
		searchOrFilterURL = getSelectedDateRange(searchOrFilterURL);

		/**Redirects to the searchURL*/
		window.location.href = searchOrFilterURL;
	}
	
	function onClickChangePage(selectedPage) {
		var changePageURL = "<%=changePageURL%>";
		
		/** change selected page*/
		changePageURL = changePageURL.replace("CURRENT_PAGE", selectedPage);
		
		/**document.<portlet:namespace />search_fm.action = changePageURL;*/
		/**submitForm('#<portlet:namespace/>search_fm');*/
		window.location.href = changePageURL;
	}
	
	function getSelectedCategoriesAndFacets(originURL) {
		//Variables initialization
		var selectedAssetTypes = "";
		var selectedCategories = "";
		var selectedPortalsToSearch = "";
		//Add previous non loaded by ajax categories applyed to filter
		for (var i = 0; i < originalSelectedCategories.length; i++) {
			if (document.getElementById("c" + originalSelectedCategories[i]) == null) {
				if (selectedCategories == "") {
					selectedCategories = originalSelectedCategories[i];
				} else {
					selectedCategories = selectedCategories + "-" + originalSelectedCategories[i];
				}
			}
		}
		//Get checked inputs
		var checks = $("input.check-item__check");
		for (var i = 0; i < checks.length; i++) {
			var check = checks[i];
			if($(check).hasClass("check-asset-type")){
				if (check.checked) {
					if (selectedAssetTypes == "") {
						selectedAssetTypes = check.dataset.id;
					} else {
						selectedAssetTypes = selectedAssetTypes + "-" + check.dataset.id;
					}
				}
			} else if ($(check).hasClass("check-portal-search")) {
				if (check.checked) {
					if (selectedPortalsToSearch == "") {
						selectedPortalsToSearch = check.dataset.id;
					} else {
						selectedPortalsToSearch = selectedPortalsToSearch + "-" + check.dataset.id;
					}
				}
			} else {
				if (check.checked) {
					if (selectedCategories == "") {
						selectedCategories = check.dataset.id;
					} else {
						selectedCategories = selectedCategories + "-" + check.dataset.id;
					}
				}
			}
		}
		
		var modifiedURL;
		
		if (selectedAssetTypes != "") {
			modifiedURL = originURL.replace("SELECTED_SEARCH_TYPE", selectedAssetTypes);
		}
		else {
			modifiedURL = originURL.replace("SELECTED_SEARCH_TYPE", "any");
		}
		
		if (selectedPortalsToSearch != "") {
			modifiedURL = modifiedURL.replace("SELECTED_PORTALS_TO_SEARCH", selectedPortalsToSearch);
		}
		else {
			modifiedURL = modifiedURL.replace("/portal/SELECTED_PORTALS_TO_SEARCH", "/portal/0");
		}
		
		if (selectedCategories != "") {
			modifiedURL = modifiedURL.replace("SELECTED_CATEGORIES", selectedCategories);
		}
		else {
			modifiedURL = modifiedURL.replace("/filter/SELECTED_CATEGORIES", "");
		}
		return modifiedURL;
	}
	
	function getSelectedDateRange(originURL) {
		var fromInputValue = $("#textDesde").val();
		var fromDate = fromInputValue.replace(/\//g, "");
		var toInputValue = $("#textHasta").val();
		var toDate   = toInputValue.replace(/\//g, "");
		var selectedDateRange;
		var modifiedURL;
		if (fromDate != "" || toDate != "") {
			selectedDateRange = fromDate + "-" + toDate;
			modifiedURL = originURL.replace("SELECTED_DATE_RANGE", selectedDateRange);
		} else {
			modifiedURL = originURL.replace("/date/SELECTED_DATE_RANGE", "");
		}
		return modifiedURL;
	}
	
	function onClickCleanFilters() {
		var cleanFiltersURL = "<%=cleanFiltersURL%>";
		window.location.href = cleanFiltersURL;
	}
	
	function onClickRemoveFilter(categoryId) {
		var categoryCheck = $("#c" + categoryId);
		if (categoryCheck[0] != null && categoryCheck[0].checked) {
			categoryCheck[0].checked = false;
		} else {
			//Delete category from original selected categories array
			var modifiedOriginalSelectedCategories = [];
			for (var i = 0; i < originalSelectedCategories.length; i++) {
				if (originalSelectedCategories[i] != categoryId) {
					modifiedOriginalSelectedCategories.push(originalSelectedCategories[i]);
				}
			}
			originalSelectedCategories = modifiedOriginalSelectedCategories;
		}
		onClickSearchOrFilter();
	}
	
	function onClickRemoveAsset(assetId){
		var assetCheck = $("#asset" + assetId);
		if(assetCheck[0] != null && assetCheck[0].checked) {
			assetCheck[0].checked = false;
		}
		onClickSearchOrFilter();
	}
	
	/**
	 * Returns a DOM element from the given HTML fragment
	 * @param html HTML fragment
	 * @returns A DOM element with the given HTML
	 */
	 function createElementFromHTML(html) {
		var div = document.createElement("div");
		div.innerHTML = html.trim();
		return div.firstChild;
	};

</script>

<!-- Includes calendar generator js -->
<script src="<%=themeDisplay.getPathThemeJavaScript()%>/calendar_generator.js"></script>
