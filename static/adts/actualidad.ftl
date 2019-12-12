<#if entries?has_content>
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
				<ul class="last-news-module__listado">
					<#list entries as entry>
						<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(entry.getClassPK())/>
						<#assign assetRenderer = entry.getAssetRenderer()/>
						<#assign entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale))/>
						<#assign imageSrc = freemarkerUtilities.getArticleImage(themeDisplay.getScopeGroupId(), entry, locale)/>
						<#assign imageAlt = freemarkerUtilities.getArticleImageAlt(themeDisplay.getScopeGroupId(), entry, locale)/>
						<#assign displayDate = freemarkerUtilities.getDisplayDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
						<#assign categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale)/>
						<#assign viewURL = freemarkerUtilities.getArticleFullURL(journalArticle, locale)/>
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
			</div>
		</div>
</section>
</#if>