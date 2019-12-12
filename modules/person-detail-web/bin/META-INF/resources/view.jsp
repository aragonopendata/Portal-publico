<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>

<%@ include file="init.jsp" %>

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

<% 
String biografia = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_BIOGRAFIA), StringPool.BLANK);
String title = GetterUtil.getString(renderRequest.getAttribute("TITLE"), StringPool.BLANK);
String cargo = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CARGO), StringPool.BLANK);
String fotoNombre = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_NOMBRE), StringPool.BLANK);
String srcFoto = "http://servicios.aragon.es/organigrama/" + fotoNombre;
String telefono = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO), StringPool.BLANK).replace(StringPool.SPACE, StringPool.BLANK);
%>
<section>
	<div class="container u-container-mobile-0">
		<div class="detail-news-module detail-news-module--persona u-padding-bottom-6">
			<c:if test="<%=Validator.isNotNull(title)%>">
				<h1 class="detail-news-module__h1 detail-news-module--persona__h1"><span class="inline"><%=title %></span></h1>
			</c:if>
			<c:if test="<%=Validator.isNotNull(cargo)%>">
				<h2 class="detail-news-module--persona__cargo"><%=cargo %></h2>
			</c:if>
			<c:if test="<%=Validator.isNotNull(biografia)||Validator.isNotNull(fotoNombre) %>">
				<div class="detail-news-module__body-news detail-news-module--persona__body-news">
					<c:if test="<%=Validator.isNotNull(biografia)%>">
						<div class="detail-news-module__body-news__description detail-news-module--persona__body-news__description">
							<p><%=biografia %></p>
						 </div>
					</c:if>
					<c:if test="<%=Validator.isNotNull(fotoNombre)%>">  
						<div class="detail-news-module__body-news__image detail-news-module--persona__body-news__image">
							<img src="<%=srcFoto %>" alt="">
						</div>
					</c:if>           
				</div>
			</c:if>
			<div class="aditional-info-card">
				<c:if test="${not empty EDIFICIO}">
					<div class="aditional-info-card__name">
						${EDIFICIO}
					</div>
				</c:if>
				<div class="aditional-info-card__paragraph">
					${DIRECCION} ${CP} ${LOCALIDAD} <c:if test="${not empty PROVINCIA}">(${PROVINCIA})</c:if>
				</div>
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