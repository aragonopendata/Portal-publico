<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>

<%@ include file="init.jsp" %>

<%
String telefono = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO), StringPool.BLANK).replace(StringPool.SPACE, StringPool.BLANK);
%>
<section>
	<div class="container u-container-mobile-0">
		<div class="detail-news-module u-padding-bottom-6 u-padding-top-6">
			<div class="aditional-info-card">
				<div class="aditional-info-card__name">
					Datos del municipio
				</div>
				<c:if test="${not empty PROVINCIA}">
					<div class="aditional-info-card__paragraph">
						<strong>Provincia:</strong> ${PROVINCIA}
					</div>
				</c:if>	
				<c:if test="${not empty COMARCA}">
					<div class="aditional-info-card__paragraph">
						<strong>Comarca:</strong> ${COMARCA}
					</div>
				</c:if>
				<c:if test="${not empty CP}">
					<div class="aditional-info-card__paragraph">
						<strong>CP:</strong> ${CP}
					</div>
				</c:if>
				<c:if test="${not empty ALCALDE}">
					<div class="aditional-info-card__paragraph">
						<strong>Alcalde:</strong> ${ALCALDE}
					</div>
				</c:if>
				<c:if test="${not empty HABITANTES}">
					<div class="aditional-info-card__paragraph">
						<strong>Habitantes:</strong> ${HABITANTES}
					</div>
				</c:if>
				<c:if test="<%=Validator.isNotNull(telefono)%>">
					<%
					String telefonoText = telefono.toString();
					if (telefonoText.length() == 9) {
						telefonoText = telefonoText.substring(0, 3) + " " + telefonoText.substring(3, 6) + " " + telefonoText.substring(6, 9);
					}
					%>
					<a href="tel:+<%=telefono%>" class="aditional-info-card__underlined-link"><%=telefonoText%></a>
				</c:if>
				<c:if test="${not empty EMAIL}">
					<a href="mailto:${EMAIL}" class="aditional-info-card__link">${EMAIL}</a>
				</c:if>
				<div class="aditional-info-card__paragraph">
					<a href="${CODIGO_MUN}" target="_blank" title="Se abre en un nuevo portal web">Consultar datos abiertos</a>
				</div>
				
			</div>
      	</div>
   	</div>
</section>