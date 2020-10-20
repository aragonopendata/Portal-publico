<%@ include file="init.jsp" %>

<c:if test="${not empty procedure}">
    ${procedureJSONLD}
	<meta name="description" content='<liferay-ui:message key="enlinea.procedure-detail.meta.description" arguments="${procedure.getName()}"/>'>
	<section>
		<nav role="navigation" aria-label='<liferay-ui:message key="aragon.you-are-in" />'>
			<div class="breadcrumb hidden-xs">
				<div class="container">
					<ul class="breadcrumb__listado">
						<li><a href="/" class="link"><liferay-ui:message key="aragon.home" /></a></li>
						<c:if test="${not empty breadcrumbs}">
							<c:forEach items="${breadcrumbs}" var="breadcrumb">
								<li><a href="${breadcrumb.value}" class="link">${breadcrumb.key}</a></li>
							</c:forEach>
						</c:if>
						<li><strong class="final" aria-current="page">${procedure.getName()}</strong></li>
				 	</ul>
				</div>
			</div>
		</nav>
	</section>
	<h1 class="h1-interior readSpeakerOrigin">
		<div class="container">${procedure.getName()}</div>
	</h1>
	<div class="container readSpeakerOrigin">
		<div class="row">
			<div class="col-12 pt-5 pb-5">
				<p>${procedure.getDescription()}</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row flex-column flex-md-row">
			<section class="col-xs-12 col-md-6 col-lg-8">
				<%@ include file="detail.jspf" %>
			</section>
			<section class="col-xs-12 col-md-6 col-lg-4">
				<div class="en-linea u-padding-bottom-4 u-padding-bottom-sm-6">
					<div class="container text-center aside-tramites-block bg-black">
						<%@ include file="processing.jspf" %>
					</div>
					<liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_before_processing" />
					<div class="container text-center aside-tramites-block bg-gray">
						<liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_help" />
					</div>
				</div>
			</section>
		</div>
	</div>
	<section class="u-padding-bottom-6 readSpeakerOrigin">
		<nav class="asociados--autoheight" role="navigation">
			<div class="container">
				<ul class="asociados__listado">
					<li class="col-xs-12 col-sm-6 col-md-4 item">
						<h3 class="title"><liferay-ui:message key="normative" /></h3>
						<p>${procedure.getNormative()}</p>
					</li>
					<li class="col-xs-12 col-sm-6 col-md-4 item">
						<h3 class="title"><liferay-ui:message key="department" /></h3>
						<c:choose>
							<c:when test="${not empty procedure.getResponsibleDepartmentURL()}">
								<p><a href="${procedure.getResponsibleDepartmentURL()}">${procedure.getResponsibleDepartment()}</a></p>
							</c:when>
							<c:otherwise>
								<p>${procedure.getResponsibleDepartment()}</p>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="col-xs-12 col-sm-6 col-md-4 item">
						<h3 class="title"><liferay-ui:message key="related" /></h3>
						<c:if test="${not empty procedure.getRelatedProcedures()}">
							<ul class="submenu-listado">
								<c:forEach items="${procedure.getRelatedProcedures()}" var="relatedProcedure">
									<c:choose>
										<c:when test="${not empty plidProcedureWeb && not empty portletNameProcedureWeb}">
											<liferay-portlet:renderURL plid="${plidProcedureWeb}" portletName="${portletNameProcedureWeb}" var="procedureURL">
												<liferay-portlet:param name="friendlyURL" value="${relatedProcedure.getFriendlyURL()}" />
											</liferay-portlet:renderURL>
											<li class="submenu-listado__item">
												<a href="<%= procedureURL %>" class="link">${relatedProcedure.getName()}</a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="submenu-listado__item">
												<a href="" class="link">${relatedProcedure.getName()}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</c:if>	
					</li>
				</ul>
			</div>
		</nav>
	</section>
	<liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_admin" />
</c:if>