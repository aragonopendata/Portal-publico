<%@page import="es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage"%>
<%@page import="es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalService"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<liferay-portlet:actionURL name="addMostVisitedPage" var="addMostVisitedPage"/>
<liferay-portlet:renderURL varImpl="iteratorURL">
	<liferay-portlet:param name="jspPage" value="/view.jsp"/>
</liferay-portlet:renderURL>
<section>
	<div class="list-news-module">
		<ul class="listado collapse" id="mas-leido">
			<% 
			MostVisitedPageLocalService mostVisitedPageLocalService = (MostVisitedPageLocalService)renderRequest.getAttribute("mostVisitedPageLocalService");
			List<MostVisitedPage> pages = mostVisitedPageLocalService.getMostVisitedPages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (pages != null && pages.size() > 0) {
				int i = 0;
				String style= "";
				for (MostVisitedPage mvp : pages) {
					if (mvp.isVisible()) {
						i++;
						if (i <= 5) {
							%>
							<li class="col-12 listado__item" style="<%=style %>">
								<a class="link" href="http://www.aragon.es<%=mvp.getPath()%>">
									<span class="title"><%= mvp.getTitle()%></span>
								</a>
							</li>
							<%
						}
					}
				}
			}
			%>
		</ul>
	</div>
</section>