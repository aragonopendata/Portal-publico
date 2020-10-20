<%@ include file="/init.jsp" %>

<div class="en-linea u-padding-bottom-4 u-padding-bottom-sm-6">
	<div class="container text-center aside-tramites-block bg-gray">
		<c:if test="${not empty procedures}">
			<div class="aside-block-module u-margin-bottom-2">
				<h2><liferay-ui:message key="most-viewed-procedures-from-x" arguments="${topicName}"/></h2>
				<ul>
					<c:forEach items="${procedures}" var="procedure">
						<c:choose>
							<c:when test="${not empty plidProcedureWeb && not empty portletNameProcedureWeb}">
								<liferay-portlet:renderURL plid="${plidProcedureWeb}" portletName="${portletNameProcedureWeb}" var="procedureURL">
									<liferay-portlet:param name="friendlyURL" value="${procedure.getFriendlyURL()}" />
								</liferay-portlet:renderURL>
								<li><a href="<%= procedureURL %>">${procedure.getName()}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="">${procedure.getName()}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
		<c:if test="${not empty topicalityURL && not empty documentsURL}">
			<section class="filtrado-tema p-0">
				<ul class="listado">
					<li class="col-xs-12 listado__item">
						<a href="${topicalityURL}" class="link">
							<div class="icon">
								<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-actualidad.svg" alt="Icono de actualidad">
							</div>
							<p class="name"><liferay-ui:message key="topicality"/></p>
						</a>
					</li>
					<li class="col-xs-12 listado__item">
						<a href="${documentsURL}" class="link">
							<div class="icon">
								<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-documents.svg" alt="Icono de documento">
							</div>
							<p class="name"><liferay-ui:message key="documents"/></p></span>
						</a>
					</li>
				</ul>
			</section>
		</c:if>
	</div>
</div>