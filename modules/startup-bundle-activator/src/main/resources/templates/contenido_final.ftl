<!--
Template Name: Contenido Final
Template Description: Contenido Final
Template Key: CONTENIDO_FINAL
Structure Key: CONTENIDO_FINAL
Cacheable: true
Small Image: false
-->
<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>
<#assign portletLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.PortletLocalService")>

<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleId, 0)/>

<#assign articleCategories = freemarkerUtilities.getArticleCategories(journalArticle, locale)/>
<#assign organismos = freemarkerUtilities.filterVocabularyCategoriesFromArticleCategories(themeDisplay.getScopeGroupId(), "Organismos", articleCategories)/>
<#assign temas = freemarkerUtilities.filterVocabularyCategoriesFromArticleCategories(themeDisplay.getScopeGroupId(), "Temas", articleCategories)/>

<#assign cuerpoPrincipal = cuerpo_principal.getData()/>
<#assign cuerpoSecundario = cuerpo_secundario.getData()/>

<#assign h2Elements = []/>
<#if generar_anclas?? && generar_anclas.getData() != "">
	<#assign sourceElements = [] />
	<#assign sourceElements = sourceElements + [cuerpoPrincipal] />
	<#assign sourceElements = sourceElements + [cuerpoSecundario] />
	<#assign h2Elements = freemarkerUtilities.getH2Elements(sourceElements)/>
	<#assign elementsWithAnchors = freemarkerUtilities.addAnchorIds(sourceElements)/>
	<#if elementsWithAnchors?? && elementsWithAnchors?has_content>
		<#if elementsWithAnchors[0]?? && elementsWithAnchors[0] != "">
			<#assign cuerpoPrincipal = elementsWithAnchors[0]/>
		</#if>
		<#if elementsWithAnchors[1]?? && elementsWithAnchors[1] != "">
			<#assign cuerpoSecundario = elementsWithAnchors[1]/>
		</#if>
	</#if>
</#if>
<section>
   <div class="container">
      <div class="detail-news-module u-padding-bottom-6">
         <h1 class="detail-news-module__h1">${.vars['reserved-article-title'].data}</h1>
         <div class="detail-news-module__date-categories"> 
            <#assign displayDate = freemarkerUtilities.getDisplayDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
            <p class="date"><span>${displayDate}</span></p>
            <#assign categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale)/>
            <#if categoryList?has_content>
               <ul class="categories">
                  <#list categoryList as category>
                     <li class="categories__item"><span class="name">${category}</span></li>
                  </#list>
               </ul>
            </#if>
         </div>
         <div class="detail-news-module__intro">
            ${.vars['reserved-article-description'].data}
         </div> 
         <#if generar_anclas?? && generar_anclas.getData() != "" && h2Elements?size gt 1>
           <div class="detail-news-module__anchor" id="anchorsContainer">
              <ul class='row listado' id='anchorsList'>
                 <#list h2Elements as cur_h2Element>
                    <#if cur_h2Element?index < 4>
                       <li class='col-xs-12 col-md-6 listado__item'>
                          <a class='link' href="${cur_h2Element.href}">${cur_h2Element.title}</a>
                       </li>
                    </#if>
                 </#list>
              </ul>
           </div>
         </#if>
         <div class="detail-news-module__body-news">
            <#if imagen_principal.getData()?? && imagen_principal.getData() != "">
               <div class="detail-news-module__body-news__image" id="contenedorImagen">
                  <img alt="${imagen_principal.getAttribute("alt")}" data-fileentryid="${imagen_principal.getAttribute("fileEntryId")}" src="${imagen_principal.getData()}" />
                  <p class="pie-foto">
                     <span tabindex="-1">
                        <#if imagen_principal.pie_imagen_principal.getData()?? && imagen_principal.pie_imagen_principal.getData() != "">
                           ${imagen_principal.pie_imagen_principal.getData()}
                        </#if>
                        <#if imagen_principal.descripcion_imagen_principal.getData()?? && imagen_principal.descripcion_imagen_principal.getData() != "">
                           <a href="#descripcionImagenExtensa"><@liferay.language key="aragon.template.noticia.read-complete-description"/></a>
                        </#if>
                     </span> 
                  </p>
               </div>
            </#if>
            <div class="detail-news-module__body-news__description">
				${cuerpoPrincipal}
            </div>
			<#if video_principal.getData()?? && video_principal.getData() != "">
				<div class="detail-news-module__video" id="contenedorVideo">
					<div class="video-container">
						<a href="${video_principal.getData()}" target="_blank" title="Navega al vídeo en ventana nueva">
							<img id="videoPrincipalThumbnail" src="${themeDisplay.getPathThemeImages()}/dga/default-video.png" alt="Vídeo alojado en ${video_principal.getData()}" class="image">
							<script>
								$(document).ready(function() {
									getVideoThumbnail("${video_principal.getData()}", "videoPrincipalThumbnail");
								});
							</script>
							<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-play.svg" alt="" class="icon-play">
						</a>
					</div>
					<p class="pie-foto">
					<#if video_principal.pie_video_principal.getData()?? && video_principal.pie_video_principal.getData() != "">
						${video_principal.pie_video_principal.getData()}
					</#if>
					<#if video_principal.descripcion_video_principal.getData()?? && video_principal.descripcion_video_principal.getData() != "">
						<a href="#descripcionVideoExtensa"><@liferay.language key="aragon.template.noticia.read-complete-description"/></a>
					</#if>
					</p>
				</div>
			</#if>
            <div class="detail-news-module__body-news__description">
            	${cuerpoSecundario}
            </div>
         </div>
         <#if (imagen_principal.descripcion_imagen_principal.getData()?? && imagen_principal.descripcion_imagen_principal.getData() != "") || (video_principal.descripcion_video_principal.getData()?? && video_principal.descripcion_video_principal.getData() != "")> 
            <div class="detail-news-module__image-acc-description">
               <#if imagen_principal.descripcion_imagen_principal.getData()?? && imagen_principal.descripcion_imagen_principal.getData() != ""> 
                     <h2 id="descripcionImagenExtensa" tabindex="-1" class="detail-news-module__image-acc-description__label"><@liferay.language key="aragon.template.noticia.complete-description"/></h2>
                     <p class="detail-news-module__image-acc-description__text">
                        ${imagen_principal.descripcion_imagen_principal.getData()}
                        <a href="#contenedorImagen"><@liferay.language key="aragon.template.noticia.back-to-image"/></a>
                     </p>
               </#if>
               <#if video_principal.descripcion_video_principal.getData()?? && video_principal.descripcion_video_principal.getData() != ""> 
                  <h2 id="descripcionVideoExtensa" tabindex="-1" class="detail-news-module__image-acc-description__label"><@liferay.language key="aragon.template.noticia.complete-video-description"/></h2>
                  <p class="detail-news-module__image-acc-description__text">
                     ${video_principal.descripcion_video_principal.getData()}
                     <a href="#contenedorVideo"><@liferay.language key="aragon.template.noticia.back-to-video"/></a>
                  </p>
               </#if>
            </div>
         </#if>
      </div>
   </div>
</section>
<#if galeria_contenidos_titulo.contenido?? && galeria_contenidos_titulo.contenido.tipo.getData()?has_content>
	<#if galeria_contenidos_titulo?? &&  galeria_contenidos_titulo.getData()?has_content>
		<div class="container">
			<div class="detail-news-module__body-news__description">
				<h2 id="tituloGaleriaSection">${galeria_contenidos_titulo.getData()}</h2>
			</div>
		</div>
	<#else>
		<h2 class="oculto" id="tituloGaleriaSection">Galería de elementos relacionados</h2>
	</#if>
	<section role="region" class="u-padding-bottom-6" aria-labelledby="tituloGaleriaSection">
		<div class="container">
			<div class="gallery-module">
				<div class="col">
					<div class="swiper-container">
						<ul class="swiper-wrapper my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
							<#list galeria_contenidos_titulo.contenido.getSiblings() as tipoContenido>
								<#if tipoContenido.tipo.getData() == "doc">
									<#if tipoContenido.galeria_imagenes_imagen.getData()?? && tipoContenido.galeria_imagenes_imagen.getData() != "">
										<#assign fileEntry = freemarkerUtilities.getFileEntryByURL("${tipoContenido.galeria_imagenes_imagen.getData()}")/>
										<#if fileEntry?? && fileEntry?has_content>
											<#assign fileEntryType = fileEntry.getMimeType()/>
											<#if fileEntryType == "image/jpeg" || fileEntryType == "image/gif" || fileEntryType == "image/tiff" || fileEntryType == "image/bmp">
												<#assign imageURL =  freemarkerUtilities.getImageURL("${tipoContenido.galeria_imagenes_imagen.getData()}")/>
												<li class="swiper-slide" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
													<a title="Pulsa para mostrar la imagen más grande en una ventana modal" href="${imageURL}" data-toggle="lightbox" data-gallery="mixedgallery" data-footer="${htmlUtil.escape(tipoContenido.galeria_imagenes_imagen.descripcion.getData())}">
														<img src="${tipoContenido.galeria_imagenes_imagen.getData()}" class="img-fluid" alt="${htmlUtil.escape(tipoContenido.galeria_imagenes_imagen.descripcion.getData())}"/>
													</a>
												</li>
											<#else>
												<#assign fileTypeAndSize = freemarkerUtilities.getFileTypeAndSizeString(fileEntry)/>
												<li class="swiper-slide link" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
													<a href="${tipoContenido.galeria_imagenes_imagen.getData()}" target="_blank" title="Descargar documento. Se abre en ventana nueva." class="document"> 
														<span>${htmlUtil.escape(tipoContenido.galeria_imagenes_imagen.descripcion.getData())}</span>
														<strong>${fileTypeAndSize}</strong>
													</a>
												</li>
											</#if>
										</#if>
									</#if>
								<#else>
									<li class="swiper-slide link" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
										<a href="${htmlUtil.escape(tipoContenido.tituloEnlace.link.getData())}" target="_blank" title="Página web. Se abre en ventana nueva." class="web">
											<span>${htmlUtil.escape(tipoContenido.tituloEnlace.getData())}</span>
										</a>
									</li>
								</#if>
							</#list>
						</ul>
						<div class="swiper-pagination"></div>
						<ul class="list-unstyled" aria-hidden=true>
							<li title="Anterior" class="swiper-button-prev" aria-hidden=true></li>
							<li title="Siguiente" class="swiper-button-next" aria-hidden=true></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="${themeDisplay.getPathThemeRoot()}/js/swiper.min.js"></script>
	<script src="${themeDisplay.getPathThemeRoot()}/js/ekko-lightbox.js"></script>
</#if>
<#if listado_enlaces_titulo.getData()?has_content>
	<h2 class="oculto" id="tituloListadoEnlacesSection">Información y recursos asociados</h2>
	<section role="region" class="download-module u-padding-bottom-3" aria-labelledby="tituloListadoEnlacesSection">
		<div class="top-bar top-bar--border-yellow">
			<div class="container top-bar__labels">
				<p class="title">
					<span class="pull-left">${listado_enlaces_titulo.getData()}</span>
				</p>
			</div>
		</div>
		<#if listado_enlaces_titulo.titulo_enlace.getSiblings()?has_content>
			<div class="container u-container-mobile-0">
				<div class="list-news-module">
					<ul class="listado">
						<#list listado_enlaces_titulo.titulo_enlace.getSiblings() as cur_enlace>
							<#if cur_enlace.getData()?has_content>
								<#assign enlaceURL = cur_enlace.enlace_url.getData()/>
								<#if cur_enlace.documento.getData()?has_content>
									<#assign enlaceURL = cur_enlace.documento.getData()/>
								</#if>
								<#assign enlaceSize = ""/>
								<#if cur_enlace.enlace_size?? && cur_enlace.enlace_size.getData()?has_content>
									<#assign enlaceSize = "(" + cur_enlace.enlace_size.getData() + ")"/>
								</#if>
								<li class="col-12 listado__item listado__item--dark-border">
									<a href="${enlaceURL}" class="link download" target="_blank" title="Abrir enlace en ventana nueva">
										<span class="title pull-left">${cur_enlace.getData()}</span>
										<#if cur_enlace.enlace_tipo.getData() == "page">
											<span class="icon pull-right">
												<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-external-link.svg" alt="Página web">
											</span>
										<#else>
											<span class="icon pull-right">
												<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-${cur_enlace.enlace_tipo.getData()}.svg" alt="Documento ${cur_enlace.enlace_tipo.getData()} ${enlaceSize}">
											</span>
										</#if>
									</a>
								</li>
							</#if>
						</#list>
					</ul>
				</div>
			</div>
		</#if>
	</section>
</#if>
<#if temas?has_content || organismos?has_content>
   <section class="u-padding-bottom-6">
      <nav class="asociados" aria-label="Menús asociados" role="navigation">
         <div class="container">
            <ul class="asociados__listado">
               <#if organismos?has_content>
                  <li class="col-xs-12 col-sm-6 col-md-3 item">
                     <p class="title">Organismos asociados</p>
                     <ul class="submenu-listado">
                        <#list organismos as organismo>
                           <#assign organismoshref = freemarkerUtilities.getAssetCategoryURL(request, organismo.getCategoryId(), false)/>
                           <li class="submenu-listado__item">
                              <a href="${organismoshref}" class="link">${organismo.getTitle(locale)}</a>
                           </li>
                        </#list>
                     </ul>
                  </li>
               </#if>
               <#if temas?has_content>
                  <li class="col-xs-12 col-sm-6 col-md-3 item">
                     <p class="title">Temas asociados</p>
                     <ul class="submenu-listado">
                        <#list temas as tema>
                           <#assign temashref = freemarkerUtilities.getAssetCategoryURL(request, tema.getCategoryId(), false)/>
                           <li class="submenu-listado__item">
                              <a href="${temashref}" class="link">${tema.getTitle(locale)}</a>
                           </li>
                        </#list>
                     </ul>
                  </li>
               </#if>
            </ul>
         </div>
      </nav>
   </section>
</#if>

<#if portletLocalService.getPortletById("es_aragon_base_social_network_web_portlet_socialnetworkweb")??>
   <div>
      <@liferay_portlet["runtime"] portletName="es_aragon_base_social_network_web_portlet_socialnetworkweb"/>
   </div>
</#if>

<#if serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?? && serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?has_content>
	<#assign jsonLDGenerator = serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")/>
	<script type="application/ld+json">
		${jsonLDGenerator.getJournalArticleJsonLD(journalArticle, articleCategories, imagen_principal.getData(), themeDisplay)}
	</script>
	<script type="application/ld+json">
		${jsonLDGenerator.getCategoriesJsonLD(articleCategories, locale)}
	</script>
</#if>
