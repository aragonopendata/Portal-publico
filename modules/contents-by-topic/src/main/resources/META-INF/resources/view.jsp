<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoBridge"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.portal.kernel.cache.PortalCache"%>
<%@page import="com.liferay.portal.kernel.cache.SingleVMPoolUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="es.aragon.base.contents_by_topic.util.ContentsByTopicUtil"%>
<%@page import="es.aragon.base.freemarker_utilities.api.FreemarkerUtilities"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%
//Gets the layout expando category
ExpandoBridge expandoBridge = themeDisplay.getLayout().getExpandoBridge();
long layoutExpandoCategoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), 0);
%>
<c:if test="<%=layoutExpandoCategoryId > 0%>">
	<%AssetCategory layoutExpandoCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(layoutExpandoCategoryId);%>
	<c:if test="<%=layoutExpandoCategory != null%>">
		<%
		//Get cache registry
		String cacheRegistryKey = "contents-by-category-" + layoutExpandoCategoryId + "-" + portletDisplay.getInstanceId(); 
		PortalCache categoriesNavigationPortalCache = SingleVMPoolUtil.getPortalCache("categories-navigation-cache");
		String cachedContent = null;
		%>
		<c:if test="<%=cacheEnabled%>">
			<%
			if (Validator.isNotNull(categoriesNavigationPortalCache)) {
				cachedContent = (String) categoriesNavigationPortalCache.get(cacheRegistryKey);
			}
			%>
		</c:if>
		<c:choose>
			<c:when test="<%=cachedContent != null && !cachedContent.isEmpty() %>">
				<%=cachedContent%>
			</c:when>
			<c:otherwise>
				<liferay-util:buffer var="cachedContentBuffer">
					<%
					//FreemarkerUtilities instance
					FreemarkerUtilities freemarkerUtilities = (FreemarkerUtilities) renderRequest.getAttribute("freemarkerUtilities");
					//Gets the configured structures
					long[] structureIds = null;
					if (Validator.isNotNull(selectedStructures)) {
						structureIds = StringUtil.split(selectedStructures, 0L);
					}
					//Must contain category ids
					List<Long> mustContainCategoryIds = new ArrayList<Long>();
					mustContainCategoryIds.add(layoutExpandoCategoryId);
					//Must not contain category ids
					List<Long> mustNotContainCategoryIds = new ArrayList<Long>();
					//Loop over configured category ids and add to the must contain or must not contain list
					long[] allConfiguredCategoryIds = StringUtil.split(selectedCategoryIds, 0L);
					if (allConfiguredCategoryIds != null && allConfiguredCategoryIds.length > 0) {
						for (long allConfiguredCategoryId : allConfiguredCategoryIds) {
							if (inclusionCategories) {
								mustContainCategoryIds.add(allConfiguredCategoryId);
							} else {
								mustNotContainCategoryIds.add(allConfiguredCategoryId);
							}
						}
					}
					//Gets the journal article list with the configurated params
					List<JournalArticle> journalArticleList = ContentsByTopicUtil.getFilteredJournalArticles(request, themeDisplay.getScopeGroupId(), structureIds, mustContainCategoryIds, mustNotContainCategoryIds, resultsNumber, orderByCol, orderByType);
					%>
					<c:if test="<%=journalArticleList != null && !journalArticleList.isEmpty()%>">
						<!-- Listado de contenidos -->
						<c:if test="<%=selectedTemplate == 1%>">
							<h2 id="titContenidoInterior" class="oculto">Contenidos de <%=layoutExpandoCategory.getTitle(locale)%></h2> 
							<section id="articulos-tema" role="region" class="articulos-tema u-padding-bottom-2" aria-labelledby="titContenidoInterior">
								<ul class="listado">
									<%for (JournalArticle journalArticle : journalArticleList) {%>
										<li class="listado__item">
											<a href="<%=freemarkerUtilities.getArticleFullURL(journalArticle, locale)%>" class="link">
												<p class="title"><%=journalArticle.getTitle(locale)%></p>
											</a>
											<div class="info">
												<p><%=HtmlUtil.stripHtml(journalArticle.getDescription(locale))%></p>
											</div>
										</li>
									<%}%>					
								</ul>
							</section>
						</c:if>
						<!-- Listado de noticias de actualidad -->
						<c:if test="<%=selectedTemplate == 2%>">
							<section id="news-organismos" class="news-organismos u-padding-bottom-5" role="region" aria-labelledby="titNoticias">
								<h2 id="titNoticias" class="oculto">Noticias de <%=layoutExpandoCategory.getTitle(locale)%></h2>
								<div class="last-news-module">
									<c:if test="<%=journalArticleList != null && !journalArticleList.isEmpty()%>">
										<ul class="last-news-module__listado">
											<%for (JournalArticle journalArticle : journalArticleList) {%>
												<li class="col-12 col-sm-12 col-md-6 item">
													<div class="head-news">
														<a href="<%=freemarkerUtilities.getArticleFullURL(journalArticle, locale) %>" class="img-title-container">	
															<h3 class="title"><%= journalArticle.getTitle(locale)%></h3>
															<div class="image">
																<img src="<%= freemarkerUtilities.getArticleImage(themeDisplay.getScopeGroupId(), journalArticle.getArticleId(), locale)%>" alt="<%=freemarkerUtilities.getArticleImageAlt(themeDisplay.getScopeGroupId(), journalArticle.getArticleId(), locale) %>" class="image">
															</div>
														</a>
														<p>
															<span class="date"><%=freemarkerUtilities.getDisplayDate(journalArticle, LanguageUtil.get(locale, "aragon.short-date-format"))%></span>
														</p>
														<%List <String> categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale);%>
														<c:if test="<%=categoryList != null && !categoryList.isEmpty()%>">
															<ul class="categories">
																<%for (String category : categoryList) {%>
																	<li class="categories__item"><span class="name"><%=category %></span></li>
																<%}%>
															</ul>
														</c:if>
													</div>								
												</li>
											<%}%>	
										</ul>
									</c:if>
								</div>
							</section>
							<c:if test="<%=selectedContentTypeId > 0%>">
								<%String newsSearchURL = freemarkerUtilities.getAssetCategoryURL(request, selectedContentTypeId, true);%>
								<c:if test="<%=newsSearchURL != null && !newsSearchURL.isEmpty()%>">
									<section class="u-padding-bottom-6 ">
										<div class="btn-module">
											<p class="text-center">
												<a href="<%=newsSearchURL%>" class="u-btn u-btn-red btn-access-news"><strong><liferay-ui:message key="contents_by_topic.view-button-watch-more"></liferay-ui:message></strong></a>
											</p>
										</div>
									</section>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
				</liferay-util:buffer>
				<%=cachedContentBuffer%>
				<%-- Create cache registry --%>
				<c:if test="<%=cacheEnabled%>">
					<c:if test="<%=Validator.isNotNull(categoriesNavigationPortalCache)%>">
						<%categoriesNavigationPortalCache.put(cacheRegistryKey, cachedContentBuffer, 7200);%>
					</c:if>
				</c:if>
			</c:otherwise>
		</c:choose>
		<%-- JSON LD --%>
		<%AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(layoutExpandoCategory.getVocabularyId());%>
		<c:if test="<%=assetVocabulary != null && (assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES) || assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_EN))%>">
			<script type="application/ld+json" id="customJsonLdData"></script>
			<script>
				$( document ).ready(function() {
					var jsonLdArray = [];
					$("#articulos-tema, #news-organismos").find($("li")).each(function() {
					    var jsonLd = {};
						jsonLd["@context"] = {"dct": "http://purl.org/dc/terms/"};
						jsonLd["@id"] = "<%=themeDisplay.getPortalURL()%>" + $(this).find("a").first().attr('href');
						jsonLd["dct:subject"] = {"@id": "<%=themeDisplay.getPortalURL() + themeDisplay.getURLCurrent()%>"};
						jsonLdArray.push(jsonLd);
					});
					
					$("#customJsonLdData").text(JSON.stringify(jsonLdArray));
				});
			</script>
		</c:if>
	</c:if>
</c:if>
