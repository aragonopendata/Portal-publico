<%@ include file="/init.jsp" %>

<c:if test="${not empty procedures}">
	<section class="en-linea download-module pt-5 pb-5">
		<div class="container">
			<div class="top-bar top-bar--border-yellow">
				<div class="top-bar__labels">
					<div class="row align-items-center title">
						<div class="head-descargas col-xs-12 col-sm"><liferay-ui:message key="statistics" /></div>
						<div class="head-descargas col-sm-2 hidden-xs text-center"><liferay-ui:message key="terms" /></div>
						<div class="head-descargas col-sm-2 hidden-xs text-center"><liferay-ui:message key="forms" /></div>
						<div class="head-descargas col-sm-2 hidden-xs text-center"><liferay-ui:message key="start-online" /></div>
					</div>
				</div>
			</div>
			<div class="list-news-module">
				<ul class="listado">
					<c:forEach items="${procedures}" var="procedure">
						<li class="d-flex flex-wrap flex-md-nowrap align-items-center listado__item listado__item--dark-border">
							<c:choose>
								<c:when test="${not empty plidProcedureWeb && not empty portletNameProcedureWeb}">
									<liferay-portlet:renderURL plid="${plidProcedureWeb}" portletName="${portletNameProcedureWeb}" var="procedureURL">
										<liferay-portlet:param name="friendlyURL" value="${procedure.getFriendlyURL()}" />
									</liferay-portlet:renderURL>
									<h2 class="col-xs-12 col-md mb-5 mb-md-0">
										<a href="<%= procedureURL %>" class="title">
											${procedure.getName()}
										</a>
									</h2>
								</c:when>
								<c:otherwise>
									<h2>
										<a href="" class="col-xs-12 col-md title mb-5 mb-md-0">
											${procedure.getName()}
										</a>
									</h2>
								</c:otherwise>
							</c:choose>
							<p class="col-xs-4 col-md-2 m-0 p-0 text-center">
								<c:choose>
									<c:when test="${procedure.isUndefinedTerm()}">
										<liferay-ui:message key="undefined-term" />
									</c:when>
									<c:when test="${procedure.isInTerm()}">
										<liferay-ui:message key="in-term" />
										<c:if test="${not empty  procedure.getToDateFormatted()}">
											<span class="plazo"><liferay-ui:message key="in-term-to" arguments="${procedure.getToDateFormatted()}"/></span>
										</c:if>
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="not-in-term" />
									</c:otherwise>
								</c:choose>
							</p>
							<c:choose>
								<c:when test="${empty procedure.getDocuments()}">
									<div class="col-xs-4 col-md-2">
										<span class="icon w-100 d-block text-center"><img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-close-black.svg" alt="#"></span>
									</div>
								</c:when>
								<c:otherwise>
									<a href="" class="col-xs-4 col-md-2" data-toggle="modal" data-target="#${procedure.getProcedureId()}forms">
										<span class="icon w-100 d-block text-center"><img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-download.svg" alt="#"></span>
									</a>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${procedure.getInLevel() < 3 || procedure.isUndefinedTerm()}">
									<div class="col-xs-4 col-md-2">
										<span class="icon w-100 d-block text-center"><img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-close.svg" alt="#"></span>
									</div>
								</c:when>
								<c:otherwise>
									<a href="${procedure.getOnlineURL()}" class="col-xs-4 col-md-2">
										<span class="icon w-100 d-block text-center"><img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-online.svg" alt="#"></span>
									</a>
								</c:otherwise>
							</c:choose>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="text-center mt-4 mb-4">
				<a href="${viewMoreURL}" class="u-btn u-btn-red d-inline-block"><liferay-ui:message key="view-all-results" /></a>
			</div>
		</div>
		<c:forEach items="${procedures}" var="procedure">
			<c:if test="${not empty procedure.getDocuments()}">
				<div class="modal fade tramites-modal" id="${procedure.getProcedureId()}forms" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span><span><liferay-ui:message key="close" /></span>
								</button>
							</div>
							<div class="modal-body p-4 p-md-5">
								<p class="title d-flex align-items-center">
									<span class="icon-theme documents mr-3"></span>${procedure.getName()}
								</p class="title">
								<div class="d-flex">
									<div class="hidden-xs col-md"></div>
									<div class="hidden-xs col-md-2 text-center"><liferay-ui:message key="download" /></div>
								</div>
								<ol class="listado">
									<c:forEach items="${procedure.getDocuments()}" var="document">
										<li>
											<div class="d-flex flex-wrap align-items-center">
												<div class="col-xs-12 col-md mb-5 mb-md-0">
													${document.getName()}
												</div>
												<div class="col-xs-6 col-md-2">
													<span class="icon w-100 d-block text-center">
														<a href="${document.getUrl()}">
															<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-download.svg" alt="#">
														</a>
													</span>
												</div>
											</div>
										</li>
									</c:forEach>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</section>
</c:if>