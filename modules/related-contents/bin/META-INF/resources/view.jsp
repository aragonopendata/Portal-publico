<%@page import="java.util.List"%>

<%@ include file="init.jsp" %>

<%List<String> journalArticleDisplayList = (List<String>)renderRequest.getAttribute("journalArticleDisplayList");%>
<c:choose>
	<c:when test="<%=journalArticleDisplayList != null && !journalArticleDisplayList.isEmpty()%>">
		<div class="portlet-bgblue-title">
			<h2 class="portlet-title-text" id="tePuedeInteresarTitle"> 
				<span class="container">Te puede interesar</span>
			</h2>
		</div>
		<section role="region" aria-labelledby="tePuedeInteresarTitle">
			<div class="last-news-module u-padding-bottom-3">
				<div class="container u-container-mobile-0">
					<ul class="last-news-module__listado last-news-module__listado--interesar row">
						<%for (String journalArticleDisplay : journalArticleDisplayList) {%>
							<li class="col-12 col-sm-12 col-md-4 item">
								<%=journalArticleDisplay%>
							</li>
						<%}%>					
					</ul>
				</div>
			</div>
		</section>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info text-center">
			<div>
				<liferay-ui:message key="related-contents.list.no-such-related-contents" />
			</div>
		</div>
	</c:otherwise>
</c:choose>