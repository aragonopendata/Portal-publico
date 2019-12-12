<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>

<%@ include file="init.jsp" %>

<%
String telefono = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO), StringPool.BLANK).replace(StringPool.SPACE, StringPool.BLANK);
%>

<style>
	.aditional-info-card {
		padding: 1.5rem;
		border: 0.1rem solid #979797;
		font-family: "open_sans-semibold",sans-serif;
		font-size: 1.6rem;
		line-height: 2.24rem;
	}
	.aditional-info-card__name {
		font-size: 1.8rem;
		line-height: 2.52rem;
		margin-bottom: 2rem;
		position: relative;
	}
	.aditional-info-card__name::after  {
		content: "";
		border-bottom: 0.2rem solid #F05442;
		position: absolute;
		bottom: -0.5rem;
		height: 0.4rem;
		width: 100%;
		left: 0px;
	}
	.aditional-info-card__paragraph {
		margin-bottom: 2rem;
	}
	.aditional-info-card__paragraph strong {
		font-family: "open_sans-bold",sans-serif;
	}
	.aditional-info-card__underlined-link {
		margin-bottom: 2rem;
		color: #083D77;
		display: block;
		text-decoration: underline;
	}
	.aditional-info-card__link {
	    margin-bottom: 2rem;
	    color: #083D77;
	    display: block;
	}
</style>

<section>
	<div class="container u-container-mobile-0">
		<div class="detail-news-module u-padding-bottom-6">
			<h1 class="detail-news-module__h1">${townName}</h1>
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
			</div>
      	</div>
   	</div>
</section>