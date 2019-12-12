<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="es.aragon.base.migration.model.ContentUrl"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>

<%@ include file="init.jsp" %>

<%String search = ParamUtil.getString(request, "search", StringPool.BLANK);%>
<nav class="management-bar management-bar-light navbar navbar-expand-md">
	<div class="container-fluid container-fluid-max-xl">	
		<!-- CAJA BUSCADOR -->
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid container-fluid-max-xl">
				<portlet:renderURL var="textSearchRenderURL">
					<portlet:param name="mvcPath" value="/migratedContentPortlet/view.jsp"/>
				</portlet:renderURL>
				<aui:form method="post" action="<%=textSearchRenderURL%>">
					<div class="input-group">
						<div class="input-group-item">
						<input name='<portlet:namespace/>search' aria-label="Search" class="form-control input-group-inset input-group-inset-after" placeholder='<%=LanguageUtil.get(request, "aragon.migration.contentportlet.search-placeholder")%>' ref="search" type="text" value="<%=search%>">
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
		<!-- BOTON EXPORTAR EXCEL-->
		<li class="nav-item">
			<portlet:resourceURL var="exportMigratedContentURL1">
				<portlet:param name="exportType" value="0"/>
			</portlet:resourceURL>
			<aui:form method="POST" action="<%=exportMigratedContentURL1%>">
				<button class="btn nav-link nav-link-monospaced btn-unstyled" aria-label="download" type="submit" data-title="Exportar a excel" title='Descargar datos'>
					<svg class="lexicon-icon lexicon-icon-download" viewBox="0 0 384 512">
						<g>
							<path class="lexicon-icon-outline" d="M169.1,374.4999c6.1,6.1,14.3,9.5,22.9,9.5c8.6,0,16.8-3.4,22.9-9.5l71.6-71.6c12.7-12.7,12.7-33.2,0-45.8c-6.3-6.3-14.6-9.5-22.9-9.5s-16.6,3.2-22.9,9.5L224,273.8V32c0-17.7-14.3-32-32-32s-32,14.3-32,32v241.8l-16.7-16.7c-12.7-12.7-33.2-12.7-45.8,0c-12.7,12.7-12.7,33.2,0,45.8L169.1,374.4999z"></path>
							<path class="lexicon-icon-outline" d="M352,352c-17.7,0-32,14.3-32,32v64H64v-64c0-17.7-14.3-32-32-32c-17.7,0-32,14.3-32,32v128h32h32h289h31v-31v-33v-64C384,366.3,369.7,352,352,352z"></path>
						</g>
					</svg>
				</button>
			</aui:form>
		</li>
		<!-- BOTON EXPORTAR REDIRECCIONES -->
		<li class="nav-item">
			<portlet:resourceURL var="exportMigratedContentURL2">
				<portlet:param name="exportType" value="1"/>
			</portlet:resourceURL>
			<aui:form method="POST" action="<%=exportMigratedContentURL2%>">
				<button class="btn nav-link nav-link-monospaced btn-unstyled" aria-label="Exportar redirecciones" type="submit" data-title="Exportar redirecciones" title='Exportar redirecciones'>
					<svg class="lexicon-icon lexicon-icon-download" viewBox="0 0 384 512">
						<g>
							<path class="lexicon-icon-outline" d="M169.1,374.4999c6.1,6.1,14.3,9.5,22.9,9.5c8.6,0,16.8-3.4,22.9-9.5l71.6-71.6c12.7-12.7,12.7-33.2,0-45.8c-6.3-6.3-14.6-9.5-22.9-9.5s-16.6,3.2-22.9,9.5L224,273.8V32c0-17.7-14.3-32-32-32s-32,14.3-32,32v241.8l-16.7-16.7c-12.7-12.7-33.2-12.7-45.8,0c-12.7,12.7-12.7,33.2,0,45.8L169.1,374.4999z"></path>
							<path class="lexicon-icon-outline" d="M352,352c-17.7,0-32,14.3-32,32v64H64v-64c0-17.7-14.3-32-32-32c-17.7,0-32,14.3-32,32v128h32h32h289h31v-31v-33v-64C384,366.3,369.7,352,352,352z"></path>
						</g>
					</svg>
				</button>
			</aui:form>
		</li>
		<!-- RESET FILTROS -->
		<ul class="navbar-nav">
			<li class="nav-item">
				<portlet:renderURL var="resetAllFiltersRenderURL">
					<portlet:param name="mvcPath" value="/migratedContentPortlet/view.jsp"/>
					<portlet:param name="search" value=""/>
				</portlet:renderURL>
				<aui:form method="POST" action="<%=resetAllFiltersRenderURL%>">
					<button class="btn nav-link nav-link-monospaced btn-unstyled" aria-label="trash" type="submit" data-title="Limpiar filtros" title='<%=LanguageUtil.get(request, "aragon.migration.contentportlet.clean-filters")%>'>
						<svg aria-hidden="true" class="lexicon-icon lexicon-icon-trash" focusable="false" viewBox="0 0 512 512">
							<title>trash</title>
							<path class="lexicon-icon-outline" d="M64.4,440.7c0,39.3,31.9,71.3,71.3,71.3h240.6c39.3,0,71.3-31.9,71.3-71.3v-312H64.4V440.7z M128.2,192.6h255.5v231.7c0,13.1-10.7,23.8-23.8,23.8H152c-13.1,0-23.8-10.7-23.8-23.8V192.6z"></path>
							<polygon class="lexicon-icon-outline" points="351.8,32.9 351.8,0 160.2,0 160.2,32.9 64.4,32.9 64.4,96.1 447.6,96.1 447.6,32.9 "></polygon>
							<rect class="lexicon-icon-outline" x="287.9" y="223.6" width="63.9" height="191.6"></rect>
							<rect class="lexicon-icon-outline" x="160.2" y="223.6" width="63.9" height="191.6"></rect>
						</svg>
					</button>
				</aui:form>
			</li>
		</ul>
	</div>
</nav>
<!-- RESULTADOS -->
<%
PortletURL searchContainerIteratorURL = renderResponse.createRenderURL();
searchContainerIteratorURL.setParameter("mvcPath", "/migratedContentPortlet/view.jsp");
searchContainerIteratorURL.setParameter("search", search);
%>
<div class="container-fluid-1280 main-content-body">
	<!-- BADGES -->
	<div class="form-group input-text-wrapper">
		<c:if test="<%=!search.isEmpty()%>">
			<portlet:renderURL var="resetSearchFilterRenderURL">
				<portlet:param name="mvcPath" value="/migratedContentPortlet/view.jsp"/>
				<portlet:param name="search" value=""/>
			</portlet:renderURL>
			<a href="<%=resetSearchFilterRenderURL%>" style="text-decoration: none;" title="Limpiar filtro">
				<span class="badge badge-primary"><%=search%><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="padding-left: 5px;"></i></span>
			</a>
		</c:if>
	</div>
	<!-- Table -->
	<liferay-ui:search-container emptyResultsMessage="No se han encontrado contenidos" iteratorURL="<%=searchContainerIteratorURL%>" delta="10">
		<%
		Long contentsCountLong = (Long)request.getAttribute("totalResults");
		List<ContentUrl> contentsList = (List<ContentUrl>)request.getAttribute("contentUrls");
		searchContainer.setResults(contentsList);
		searchContainer.setTotal(contentsCountLong.intValue());
		%>
		<liferay-ui:search-container-row className="es.aragon.base.migration.model.ContentUrl" modelVar="contentUrl">
			<%
			String urlOrigin = contentUrl.getUrlOrigin();
			if (urlOrigin == null) {
				urlOrigin = StringPool.BLANK;
			}
			//Truncate title to 100 characters
			urlOrigin = urlOrigin.length() > 100 ? urlOrigin.substring(0,100) + "..." : urlOrigin;
			%>
			<liferay-ui:search-container-column-text name="aragon.migration.contentportlet.url-origin" value="<%=urlOrigin%>" title="<%=contentUrl.getUrlOrigin()%>" />
			<%
			String contentDestinationURL = StringPool.BLANK;
			long contentDestinationId = contentUrl.getContentDestinationId();
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(contentDestinationId);
			if (journalArticle != null) {
				contentDestinationURL = "/-/" + journalArticle.getUrlTitle(locale);
			}
			//Truncate title to 100 characters
			String contentDestinationUrlLabel = contentDestinationURL.length() > 100? contentDestinationURL.substring(0,100) + "..." : contentDestinationURL;
			%>
			<liferay-ui:search-container-column-text name="aragon.migration.contentportlet.url-destination" value="<%=contentDestinationUrlLabel%>" title="<%=contentDestinationURL%>" />
			<liferay-ui:search-container-column-text align="center" name="actions">
				<liferay-ui:icon-menu>
					<c:if test="<%=contentUrl.getUrlOrigin() != null && !contentUrl.getUrlOrigin().trim().isEmpty() %>">
						<liferay-ui:icon image="view" message="Ver en Vignette" target="_blank" url="<%=contentUrl.getUrlOrigin()%>"></liferay-ui:icon>
					</c:if>
					<c:if test="<%=contentDestinationURL != null && !contentDestinationURL.trim().isEmpty() %>">
						<liferay-ui:icon image="view" message="Ver en el portal" target="_blank" url="<%=contentDestinationURL%>"></liferay-ui:icon>
					</c:if>
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>