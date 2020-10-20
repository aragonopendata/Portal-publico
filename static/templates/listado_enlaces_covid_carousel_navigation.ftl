<!--
Template Name: Listado de enlaces - COVID Carousel navigation
Template Description: Listado de enlaces para COVID Carousel navigation
Template Key: LISTADO_ENLACES_COVID_CAROUSEL_NAVIGATION
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->
<#if tema.getSiblings()?has_content>
	<section role="navigation" class="carousel-enlaces-covid" aria-labelledby="tituloGaleriaSection">
	<p class="ocultovisual" id="tituloGaleriaSection">Menú de la sección Coronavirus:</p>
		<div class="container">
			<div class="gallery-module">
				<div class="col">
					<div class="swiper-container">
						<ul class="swiper-wrapper my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
							<#list tema.getSiblings() as cur_link>
							<li class="swiper-slide link" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
								<#assign linkTarget = "">
								<#if getterUtil.getBoolean(cur_link.blank.getData())>
									<#assign linkTarget = "target='_blank'">
								</#if>
								<span class="icon-theme icon-big" style="background-image: url('${cur_link.icono.getData()}');"></span>
								<a href="${cur_link.enlace.getData()}" ${linkTarget} class="web">
									<span>${cur_link.getData()}</span>
								</a>
							</li>
							</#list>
						</ul>
						<div class="swiper-pagination"></div>
						<ul class="list-unstyled m-0" aria-hidden=true>
							<li title="Anterior" class="swiper-button-prev" aria-hidden=true><span class="d-none">Mostrar elementos anteriores</span></li>
							<li title="Siguiente" class="swiper-button-next" aria-hidden=true><span class="d-none">Mostrar elementos siguientes</span></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="${themeDisplay.getPathThemeRoot()}/js/swiper.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/ekko-lightbox.js"></script>
</#if>
