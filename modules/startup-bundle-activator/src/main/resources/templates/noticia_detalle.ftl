<!--
Template Name: Noticia - Detalle
Template Description: Visualización de detalle de noticia
Template Key: NOTICIA_DETALLE
Structure Key: NOTICIA
Cacheable: true
Small Image: false
-->
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")/>
<#assign portletLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.PortletLocalService")>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>
<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>

<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleId, 0)/>
<#assign assetEntry = assetEntryLocalService.getEntry("com.liferay.journal.model.JournalArticle", journalArticle.getResourcePrimKey())/>
<#assign articleCategories = freemarkerUtilities.getArticleCategories(journalArticle, locale)/>

<section role="region" aria-label="Noticia">
	<div class="container">
		<div class="detail-news-module u-padding-bottom-30">
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
			<div class="detail-news-module__intro">${.vars['reserved-article-description'].data}</div> 
			<div class="detail-news-module__body-news">
				<div id="contenedorImagen" class="contenedorImagen"> 
					<div class="detail-news-module__body-news__image">
						<#if imagen.getData()?? && imagen.getData() != ""> 
							<img alt='${imagen.getAttribute("alt")}' data-fileentryid='${imagen.getAttribute("fileEntryId")}' src="${imagen.getData()}" /> 
						</#if>
						<p class="pie-foto">
							<span id="pieFoto" tabindex="-1">
								<#if imagen.descripcion_breve.getData()?? && imagen.descripcion_breve.getData() != "">
									${imagen.descripcion_breve.getData()}
								</#if>
								<#if imagen.descripcion_extensa.getData()?? && imagen.descripcion_extensa.getData() != "">
									<a href="#descripcionExtensa"><@liferay.language key="aragon.template.noticia.read-complete-description"/></a>
								</#if>
							</span>
						</p> 
					</div>
				</div> 
				<div class="detail-news-module__body-news__description">
					${cuerpo.getData()}
				</div>
			</div>
		</div>
		<#if imagen.descripcion_extensa.getData()?? && imagen.descripcion_extensa.getData() != ""> 
			<div class="detail-news-module u-padding-bottom-30">
				<div class="detail-news-module__image-acc-description col">
					<h2 id="descripcionExtensa" tabindex="-1" class="detail-news-module__image-acc-description__label"><@liferay.language key="aragon.template.noticia.complete-description"/></h2> 
					<p class="detail-news-module__image-acc-description__text">${imagen.descripcion_extensa.getData()}</p> 
					<div><a href="#contenedorImagen"><@liferay.language key="aragon.template.noticia.back-to-image"/></a></div>
				</div>
			</div>
		</#if>
	</div>
</section>

<#if portletLocalService.getPortletById("es_aragon_base_social_network_web_portlet_socialnetworkweb")??>
	<div>
		<@liferay_portlet["runtime"] portletName="es_aragon_base_social_network_web_portlet_socialnetworkweb"/>
	</div>
</#if>

<#if serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?? && serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?has_content>
	<#assign jsonLDGenerator = serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")/>
	<script type="application/ld+json">
		${jsonLDGenerator.getJournalArticleJsonLD(journalArticle, articleCategories, imagen.getData(), themeDisplay)}
	</script>
	<script type="application/ld+json">
		${jsonLDGenerator.getCategoriesJsonLD(articleCategories, locale)}
	</script>
</#if>
