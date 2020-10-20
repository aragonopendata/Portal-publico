<!--
Template Name: Aceptación de cookies - General
Template Description: Popup de aceptación de cookies
Template Key: ACEPTACION_COOKIES_GENERAL
Structure Key: ACEPTACION_COOKIES
Cacheable: true
Small Image: false
-->
<section class="banner-aceptacion-cookies">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h2 class="titulo">${.vars['reserved-article-title'].data}</h2>
				<#if textoAceptacion.getData()?? && textoAceptacion.getData()?has_content>
					<div class="descripcion">${textoAceptacion.getData()}</div>
				</#if>
			</div>
		</div>
		<div class="acciones">
			<div class="row">
				<div class="col-md-3">
					<a class="boton" href="javascript:void(0)" onclick="cookiesAccepted()"><@liferay.language key="aragon.template.cookies-preferences.accepted"/></a>
				</div>
				<#if url_button_preferences.getData()?? && url_button_preferences.getData()?has_content>
					<div class="col-md-4">
						<a class="boton" href="${url_button_preferences.getData()}"><@liferay.language key="aragon.template.cookies-preferences.go-to-preferences"/></a>
					</div>
				</#if>
			</div>
		</div>
	</div>
</section>