<!--
Template Name: Listado sin imagenes
Template Description: Visualización de contenidos sin imagenes
Template Key: LISTADO_SIN_IMAGENES
Structure Key: NOTICIA
Cacheable: true
Small Image: false
Class Name: com.liferay.asset.kernel.model.AssetEntry
-->

<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities") />
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>                                   
<section>
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
					<#assign layoutURL = freemarkerUtilities.getArticleFullURL(themeDisplay, entry) />
					<#assign displayDate = freemarkerUtilities.getDisplayDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
					<#assign categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale)/>	
					
					<li class="col-12 col-sm-12 col-md-4 item no-image">
						<div class="head-news">
								<a href="${layoutURL}" class="link title">${entryTitle}</a>
								<p><span class="date">${displayDate}</span></p>
						</div>
							<#if categoryList?has_content>
								<ul class="categories">
									<#list categoryList as category>
										<li class="categories__item">
											<span class="name">${category}</span>
										</li>
									</#list>
								</ul>
							</#if>
						</a>
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

			<@liferay_ui["icon"]
				cssClass="icon-monospaced visible-interaction"
				icon="pencil"
				markupView="lexicon"
				message=title
				url="javascript:Liferay.Util.openWindow({id:'" + renderResponse.getNamespace() + "editAsset', title: '" + title + "', uri:'" + htmlUtil.escapeURL(editPortletURL.toString()) + "'});"
			/>
		</#if>
	</#if>
</#macro>