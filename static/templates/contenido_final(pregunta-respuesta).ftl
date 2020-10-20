<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#assign cuerpoPrincipal = cuerpo_principal.getData()/>
<#assign cuerpoSecundario = cuerpo_secundario.getData()/>
<#assign sourceElements = [] />
<#assign sourceElements = sourceElements + [cuerpoPrincipal] />
<#assign h2Elements = freemarkerUtilities.getQuestionAnswerTemplate(sourceElements)/>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>
<#assign portletLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.PortletLocalService")>

<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#if journalArticleLocalService.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleId, 0)??>
	<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleId, 0)/>
<#else>
	<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleId, -1)/>
</#if>
<#assign articleCategories = freemarkerUtilities.getArticleCategories(journalArticle, locale)/>
<#assign organismos = freemarkerUtilities.filterVocabularyCategoriesFromArticleCategories(themeDisplay.getScopeGroupId(), "Organismos", articleCategories)/>
<#assign temas = freemarkerUtilities.filterVocabularyCategoriesFromArticleCategories(themeDisplay.getScopeGroupId(), "Temas", articleCategories)/>
<#assign municipios = freemarkerUtilities.filterVocabularyCategoriesFromArticleCategories(themeDisplay.getScopeGroupId(), "Municipios", articleCategories)/>
<#assign procedures = freemarkerUtilities.filterVocabularyCategoriesFromArticleCategories(themeDisplay.getScopeGroupId(), "Tramites", articleCategories)/>
<#assign cuerpoPrincipal = cuerpo_principal.getData()/>

<#assign VOID = freemarkerUtilities.incrementViewCounter(journalArticle.getResourcePrimKey(), themeDisplay.getUserId())/>
<#assign isNotice = freemarkerUtilities.getArticleCategoriesNotices(journalArticle, locale)/>

<div class="container">
	<div class="detail-news-module u-padding-bottom-6">	
		<h1 id="readSpeakerOrigin" class="detail-news-module__h1 readSpeakerOrigin">${.vars['reserved-article-title'].data}</h1>
		<div class="detail-news-module__date-categories readSpeakerOrigin"> 
			<#assign displayDate = freemarkerUtilities.getDisplayDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
			<#if isNotice =="true">
				<p class="date"><span>${displayDate}</span></p>
			</#if>
			<#assign categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale)/>
			<#if categoryList?has_content>
				<ul class="categories">
					<#list categoryList as category>
						<li class="categories__item"><span class="name">${category}</span></li>
					</#list>
				</ul>
			</#if>
		</div>
		<div class="detail-news-module__intro readSpeakerOrigin">
			<p>${.vars['reserved-article-description'].data}</p>
		</div>
		<div class="answer-question">
			<div class="en-linea tramite-body tramite-body preguntas-frecuentes-list">
				<ol class="list readSpeakerOrigin">
					<#list h2Elements as cur_h2Element>					
						<li class="list__item">
							<h2 class="title">
								<a href="javascript:void(0)" data-control-collapse="${.vars['reserved-article-id'].data}-list-section-${cur_h2Element?index}" style="padding-left:5.6rem; padding-right:7rem;" aria-expanded="false">
									${cur_h2Element.question}
								</a>
							</h2>
							<div class="info collapse" data-content-collapse="${.vars['reserved-article-id'].data}-list-section-${cur_h2Element?index}" id="${.vars['reserved-article-id'].data}-list-section-${cur_h2Element?index}">
								<div class="pt-5 pl-5 detail-news-module__body-news__description">
									${cur_h2Element.answer}
								</div>
							</div>
						</li>
					</#list>
				</ol>
			</div>
		</div>
		<#if cuerpoSecundario != "">
			<div class="detail-news-module__body-news__description readSpeakerOrigin">
				${cuerpoSecundario}
			</div>
		</#if>
	</div>
</div>
	<#assign isAvailable = false />
	<#if galeria_contenidos_titulo.contenido??>
		<#list galeria_contenidos_titulo.contenido.getSiblings() as content>
			<#if content.tipo.getData() == "link" && content.tituloEnlace.getData()?? && content.tituloEnlace.link.getData()?? && content.tituloEnlace.link.getData() != "" >
				<#assign isAvailable = true />
			<#elseif content.tipo.getData() == "doc" && content.galeria_imagenes_imagen.getData()?? && content.galeria_imagenes_imagen.getData() != "" >
				<#assign isAvailable = true />
			</#if>
		</#list>
	</#if>
	<#if isAvailable == true>
		<#if galeria_contenidos_titulo?? &&  galeria_contenidos_titulo.getData()?has_content>
			<div class="container">
				<div class="detail-news-module__body-news__description readSpeakerOrigin">
					<h2 id="tituloGaleriaSection">${galeria_contenidos_titulo.getData()}</h2>
				</div>
			</div>
		<#else>
			<h2 class="oculto" id="tituloGaleriaSection"><@liferay.language key="aragon.template.contenido-final.gallery-related"/></h2>
		</#if>
		<section role="region" class="u-padding-bottom-6 readSpeakerOrigin" aria-labelledby="tituloGaleriaSection">
			<div class="container">
				<div class="gallery-module">
					<div class="col">
						<div class="swiper-container">
							<ul class="swiper-wrapper my-gallery" itemscope itemtype="http://schema.org/ImageGallery">
								<#list galeria_contenidos_titulo.contenido.getSiblings() as tipoContenido>
									<#if tipoContenido.galeria_imagenes_imagen.getData()?? && tipoContenido.galeria_imagenes_imagen.getData() != "">
											<#assign fileEntry = freemarkerUtilities.getFileEntryByURL("${tipoContenido.galeria_imagenes_imagen.getData()}")/>
											<#if fileEntry?? && fileEntry?has_content>
												<#assign fileEntryType = fileEntry.getMimeType()/>
												<#if fileEntryType?contains("image/")>
													<li class="swiper-slide" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
														<a title="Pulsa para mostrar la imagen más grande en una ventana modal" href="${tipoContenido.galeria_imagenes_imagen.getData()}" data-type="image" data-toggle="lightbox" data-gallery="mixedgallery" data-footer="${htmlUtil.escape(tipoContenido.galeria_imagenes_imagen.descripcion.getData())}">
															<img src="${tipoContenido.galeria_imagenes_imagen.getData()}" class="img-fluid" alt="${htmlUtil.escape(tipoContenido.galeria_imagenes_imagen.descripcion.getData())}"/>
														</a>
													</li>
												<#else>
												<#assign fileTypeAndSize = freemarkerUtilities.getFileTypeAndSizeString(fileEntry)/>
												<li class="swiper-slide link" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
													<a href="${tipoContenido.galeria_imagenes_imagen.getData()}" target="_blank" title="<@liferay.language key="aragon.template.contenido-final.download-document"/>" class="document"> 
														<span>${htmlUtil.escape(tipoContenido.galeria_imagenes_imagen.descripcion.getData())}</span>
														<strong>${fileTypeAndSize}</strong>
													</a>
												</li>
											</#if>
										</#if>
									<#elseif tipoContenido.tipo.getData() == "link" && tipoContenido.tituloEnlace.getData()?? && tipoContenido.tituloEnlace.getData() != "" && tipoContenido.tituloEnlace.link.getData()?? && tipoContenido.tituloEnlace.link.getData() != "">
										<li class="swiper-slide link" itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
											<a href="${htmlUtil.escape(tipoContenido.tituloEnlace.link.getData())}" target="_blank" title="<@liferay.language key="aragon.template.contenido-final.web-page"/>" class="web">
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
	<#if isNotice =="false">
		<#assign modifiedDate = freemarkerUtilities.getLastPublishDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
		<div class ="container">
			<div class="detail-news-module__date-categories"> 
				<p class="date"><span><@liferay.language key="last-updated"/>: ${modifiedDate}</span></p>
			</div>
		</div>
	</#if>
	<#if listado_enlaces_titulo.titulo_enlace?? && listado_enlaces_titulo.titulo_enlace.getData()?has_content>
		<h2 class="oculto" id="tituloListadoEnlacesSection">Información y recursos asociados</h2>
		<section role="region" class="download-module u-padding-bottom-3 readSpeakerOrigin" aria-labelledby="tituloListadoEnlacesSection">
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
										<a href="${enlaceURL}" class="link download" target="_blank" title="<@liferay.language key="aragon.template.contenido-final.open-url"/>">
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
	<section class="u-padding-bottom-6 readSpeakerOrigin">
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
				<#if municipios?has_content>
					<li class="col-xs-12 col-sm-6 col-md-3 item">
						<p class="title">Municipios asociados</p>
						<ul class="submenu-listado">
						<#list municipios?sort_by("title") as municipio>
							<#assign municipiohref = freemarkerUtilities.getAssetCategoryURL(request, municipio.getCategoryId(), false)/>
							<li class="submenu-listado__item municipalitieName">
								<a href="${municipiohref}" class="link">${municipio.getTitle(locale)}</a>
							</li>
						</#list>
						</ul>
					</li>
				</#if>
				<#if procedures?has_content>
					<li class="col-xs-12 col-sm-6 col-md-3 item">
						<p class="title">Trámites asociados</p>
						<ul class="submenu-listado">
						<#list procedures as procedure>
							<#assign procedurehref = freemarkerUtilities.getAssetcategoryProcedureURL(request, procedure.getCategoryId())/>					
							<#assign customTitleProcedure = freemarkerUtilities.getCustomTitleProcedures(procedure, locale)/>
							<#if procedurehref != "" && customTitleProcedure != "">
								<li class="submenu-listado__item">
									<a href="${procedurehref}" class="link">${customTitleProcedure}</a>
								</li>
							</#if>
						</#list>
						</ul>
					</li>
				</#if>
				<li class="col-xs-12 col-sm-6 col-md-3 item">
					<p class="title">Datos abiertos</p>
					<ul class="submenu-listado">
						<#assign openDatahref = freemarkerUtilities.getOpenDataURLFromJournal(journalArticle)/>
						<li class="submenu-listado__item">
							<a href="${openDatahref}" class="link" target="_blank" title="Se abre en un nuevo portal web">Aragón Open Data </a>
						</li>
					</ul>
				</li>
			</ul>
			</div>
		</nav>
	</section>
	<#if portletLocalService.getPortletById("es_aragon_base_social_network_web_portlet_socialnetworkweb")??>
	   <div>
	      <@liferay_portlet["runtime"] portletName="es_aragon_base_social_network_web_portlet_socialnetworkweb"/>
	   </div>
	</#if>
	<#if serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?? && serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?has_content>
		<#assign jsonLDGenerator = serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")/>
		<#assign jsonLDGeneratorJournal = jsonLDGenerator.getJournalArticleJsonLD2(articleCategories,themeDisplay) />
		<#if jsonLDGeneratorJournal != "">
			<script type="application/ld+json"> 
				${jsonLDGeneratorJournal}
			</script>
		</#if>
	<#assign allCategoriesArticle = freemarkerUtilities.getAllArticleCategories(journalArticle, locale)/>
	<#assign jsonLDGeneratorCategories = jsonLDGenerator.getCategoriesJsonLD2(allCategoriesArticle, themeDisplay) />
		<#if jsonLDGeneratorCategories != "">
			<script type="application/ld+json">
				${jsonLDGeneratorCategories}
			</script>
		</#if>
		<input type="hidden" id="organisms" value="${jsonLDGenerator.getOrganismosFromJsonLD(articleCategories, locale)}">
	</#if>


<script type="application/ld+json">
	{
		"@context": "https://schema.org",
		"@type": "FAQPage",
		"mainEntity": [
		<#list h2Elements as cur_h2Element>
			{
				"@type": "Question",
				"name": "${cur_h2Element.question}",
				"acceptedAnswer": {
					"@type": "Answer",
					"text": "${cur_h2Element.answer}"
				}
			}
			<#if !cur_h2Element?is_last>
				,
			</#if>
		</#list>
		]
	}
</script>
