<!--
Template Name: Listado de enlaces - COVID Carousel
Template Description: Listado de enlaces para COVID Carousel
Template Key: LISTADO_ENLACES_COVID_CAROUSEL
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->
<#if tema.getSiblings()?has_content>
	<section role="complementary" class="carousel-enlaces-covid" aria-labelledby="tituloGaleriaDocumentos">
	<h2 class="ocultovisual" id="tituloGaleriaDocumentos">Documentos relevantes sobre el coronavirus COVID-19:</h2>
		<div class="container">
			<div class="gallery-module">
				<div class="col">
					<div class="swiper-container">
						<ul class="swiper-wrapper my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
							<#list tema.getSiblings() as cur_link>
							<li class="swiper-slide link" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
								<#assign linkTarget = "">
								<#assign linkTitle = "">
								<#if getterUtil.getBoolean(cur_link.blank.getData())>
									<#assign linkTarget = "target='_blank'">
									<#assign linkTitle = "title='Se abre en ventana nueva'">
								</#if>
								<span class="icon-theme icon-big" style="background-image: url('${cur_link.icono.getData()}');"></span>
								<a href="${cur_link.enlace.getData()}" ${linkTarget}  ${linkTitle}  class="web">
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
