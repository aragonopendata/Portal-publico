<!--
Template Name: Actualidad
Template Description: Visualización de noticias con imagenes
Template Key: ACTUALIDAD
Structure Key: NOTICIA
Cacheable: true
Small Image: false
Class Name: com.liferay.asset.kernel.model.AssetEntry
-->

<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities") />
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>

<div class="portlet-bgblue-title">
	<h2 class="portlet-title-text" id="actualidadTitle"> 
		<span class="container">Actualidad</span>
	</h2>
</div>
<section role="region" aria-labelledby="actualidadTitle">
	<div class="last-news-module">
		<div class="container u-container-mobile-0">
			<#if entries?has_content>
			<ul class="last-news-module__listado">
				<#list entries as entry>
					<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(entry.getClassPK())/>
					<#assign assetRenderer = entry.getAssetRenderer()/>
					<#assign entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale))/>
					<#assign imageSrc = freemarkerUtilities.getArticleImage(themeDisplay.getScopeGroupId(), entry, locale)/>
					<#assign imageAlt = freemarkerUtilities.getArticleImageAlt(themeDisplay.getScopeGroupId(), entry, locale)/>
					<#assign displayDate = freemarkerUtilities.getDisplayDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
					<#assign categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale)/>

					<#assign viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, entry) />
					<#assign viewURL = httpUtil.removeParameter(viewURL, "redirect") />
					<#if assetLinkBehavior != "showFullContent">
						<#assign viewURL = freemarkerUtilities.removeUrlParameters(assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, entry, true)) />
					</#if>	
					<li class="col-12 col-sm-12 col-md-4 item">
						<div class="head-news">
							<a href="${viewURL}" class="img-title-container">
								<h3 class="title">${entryTitle}</h3>
								<div class="image">
									<img src="${imageSrc}" alt="${imageAlt}" class="image">
								</div>
							</a>	
							<p><span class="date">${displayDate}</span></p>
							<#if categoryList?has_content>
								<ul class="categories">
									<#list categoryList as category>
										<li class="categories__item">
											<span class="name">${category}</span>
										</li>
									</#list>
								</ul>
							</#if>
						</div>
					</li>
				</#list>
			</ul>
			</#if>
		</div>
	</div>
</section>
<#macro getEditIcon>
	<#if assetRenderer.hasEditPermission(themeDisplay.getPermissionChecker())>
		<#assign redirectURL = renderResponse.createRenderURL() />
		${redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp")}
		${redirectURL.setWindowState("pop_up")}
		<#assign editPortletURL = assetRenderer.getURLEdit(renderRequest, renderResponse, windowStateFactory.getWindowState("pop_up"), redirectURL)!"" />
		<#if validator.isNotNull(editPortletURL)>
			<#assign title = languageUtil.format(locale, "edit-x", entryTitle, false) />
			<@liferay_ui["icon"] cssClass="icon-monospaced visible-interaction" icon="pencil" markupView="lexicon" message=title url="javascript:Liferay.Util.openWindow({id:'" + renderResponse.getNamespace() + "editAsset', title: '" + title + "', uri:'" + htmlUtil.escapeURL(editPortletURL.toString()) + "'});"/>
		</#if>
	</#if>
</#macro>