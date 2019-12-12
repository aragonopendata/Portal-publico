<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.expando.kernel.model.ExpandoBridge"%>
<%@page import="com.liferay.portal.kernel.exception.LayoutNameException"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>

<%@ include file="init.jsp" %>

<%
Layout currentLayout = themeDisplay.getLayout();
ExpandoBridge expandoBridge = currentLayout.getExpandoBridge();
long relatedCategoryId = GetterUtil.getLong(expandoBridge.getAttribute("related-category-id", Boolean.FALSE), -1);
%>
<c:if test="<%=relatedCategoryId > 0%>">
	<%
	AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(relatedCategoryId);
	%>
	<c:if test="<%=category != null%>">
		<%
		AssetVocabulary vocabulary = null;
		long vocabularyId = category.getVocabularyId();
		if (vocabularyId > 0) {
			vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(vocabularyId);
		}
		%>
		<c:if test='<%=elementsFilter.equals("same_level_pages")%>'>
			<%
			//Get layout hierarchy tree
			Layout grandParentLayout = null;
			Layout parentLayout = null;
			List<Layout> currentLayoutBrothers = new ArrayList<Layout>();
			long parentLayoutId = currentLayout.getParentLayoutId();
			if (parentLayoutId != 0) {
				parentLayout = LayoutLocalServiceUtil.fetchLayout(currentLayout.getGroupId(), currentLayout.getPrivateLayout(), parentLayoutId);
				if (parentLayout != null) {
					long grandParentLayoutId = parentLayout.getParentLayoutId();
					grandParentLayout = LayoutLocalServiceUtil.fetchLayout(parentLayout.getGroupId(), parentLayout.getPrivateLayout(), grandParentLayoutId);
					currentLayoutBrothers = parentLayout.getChildren(permissionChecker);
				}
			}
			//Order results by name
			Collections.sort(currentLayoutBrothers, new Comparator<Layout>() {
			    @Override
			    public int compare(Layout o1, Layout o2) {
			        return o1.getName().compareTo(o2.getName());
			    }
			});
			//Section title
			String sectionTitle = "";
			if (vocabulary != null) {
				if (grandParentLayout != null) {
					sectionTitle = LanguageUtil.format(request, "category-related-sections.child-pages.section-title", new Object[]{vocabulary.getTitle(locale), parentLayout.getName(locale)});
				} else {
					sectionTitle = LanguageUtil.format(request, "category-related-sections.first-level-pages.section-title", new Object[]{vocabulary.getTitle(locale)});
				}
			}
			%>
			<h2 id="titMenuSecundario" class="oculto"><%=sectionTitle%></h2> 
			<nav class="nav-menu-tema" role="navigation" aria-labelledby="titMenuSecundario">
				<c:if test="<%=grandParentLayout != null%>">
					<a class="title" href="<%=parentLayout.getFriendlyURL(locale)%>" title="<%=LanguageUtil.get(request, "back")%>"><%=parentLayout.getName(locale)%></a>
				</c:if>
				<ul class="listado collapse" id="menu-section">
					<%for (Layout currentLayoutBrother : currentLayoutBrothers) {%>
						<li class="listado__item">
							<c:choose>
								<c:when test="<%=currentLayoutBrother.getLayoutId() == currentLayout.getLayoutId()%>">
									<a class='link-1 active' href="<%=currentLayoutBrother.getFriendlyURL(locale)%>" aria-current="page">
										<strong><%=currentLayoutBrother.getName(locale)%></strong>
									</a>
								</c:when>
								<c:otherwise>
									<a class='link-1' href="<%=currentLayoutBrother.getFriendlyURL(locale)%>">
										<span><%=currentLayoutBrother.getName(locale)%></span>
									</a>
								</c:otherwise>
							</c:choose>
						</li>
					<%}%>
				</ul>
			</nav>
		</c:if>
		<c:if test='<%=elementsFilter.equals("child_pages")%>'>
			<%
			//Get child layouts
			List<Layout> currentLayoutChildren = currentLayout.getChildren(permissionChecker);
			//Order results by name
			Collections.sort(currentLayoutChildren, new Comparator<Layout>() {
			    @Override
			    public int compare(Layout o1, Layout o2) {
			        return o1.getName().compareTo(o2.getName());
			    }
			});
			//Section title
			String sectionTitle = "";
			if (vocabulary != null) {
				sectionTitle = LanguageUtil.format(request, "category-related-sections.child-pages.section-title", new Object[]{vocabulary.getTitle(locale), category.getTitle(locale)});
			}
			%>
			<c:if test="<%=category != null%>">
				<h2 id="titSubentradasInterior" class="oculto"><%=sectionTitle%></h2>
				<section role="region" class="articulos-tema" aria-labelledby="titSubentradasInterior">
					<ul class="listado">
						<%for (Layout currentLayoutChild : currentLayoutChildren) {%>
							<li class="listado__item">
								<a href="<%=currentLayoutChild.getFriendlyURL(locale)%>" class="link">
									<p class="title"><%=currentLayoutChild.getName(locale)%></p>
								</a>
								<%String descriptionCategory = currentLayoutChild.getDescription();%>
								<c:if test="<%=descriptionCategory != null && !descriptionCategory.trim().isEmpty()%>">
									<div class="info">
										<p><%=descriptionCategory %></p>
									</div>
								</c:if>
							</li>
						<%}%>
					</ul>
				</section>
			</c:if>
		</c:if>
	</c:if>
</c:if>
<c:if test="<%=relatedCategoryId <= 0%>">
	<%
	List<Layout> currentLayoutChildren = currentLayout.getChildren(permissionChecker);
	//Order results by name
	Collections.sort(currentLayoutChildren, new Comparator<Layout>() {
	    @Override
	    public int compare(Layout o1, Layout o2) {
	        return o1.getName().compareTo(o2.getName());
	    }
	});
	%>
	<section role="region" class="articulos-tema" aria-labelledby="titSubentradasInterior">
		<div class="container">
			<ul class="listado">
				<%for (Layout currentLayoutChild : currentLayoutChildren) {%>
					<li class="listado__item">
						<a href="<%=currentLayoutChild.getFriendlyURL(locale)%>" class="link">
							<p class="title"><%=currentLayoutChild.getName(locale)%></p>
						</a>
						<%String descriptionCategory = currentLayoutChild.getDescription();%>
						<c:if test="<%=descriptionCategory != null && !descriptionCategory.trim().isEmpty()%>">
							<div class="info">
								<p><%=descriptionCategory %></p>
							</div>
						</c:if>
					</li>
				<%}%>
			</ul>
		</div>
	</section>
</c:if>