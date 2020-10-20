<%@ include file="/init.jsp" %>
<section>
	<div class="list-news-module row" id="contenedor-ultimas-visitas">
	<c:if test="${not empty listLastVisitedPage}">
		<ul id="ultimas-visitas" class="listado collapse">
			<c:forEach var="page" items="${listLastVisitedPage}">
				<li class='col-12 listado__item'>
					<a href='${page.value}' class='link'><span class='title'>${page.key}</span></a>
				</li>
			</c:forEach>
		</ul>
	</c:if>
	</div>
</section>
