<!--
Template Name: Noticia - Listado Simple
Template Description: Visualización en listado simple de noticia
Template Key: NOTICIA_LISTADO_SIMPLE
Structure Key: 
Cacheable: true
Small Image: false
-->

<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")/>

<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#assign journalArticle = journalArticleLocalService.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleId, 0)/>
<#assign assetEntry = assetEntryLocalService.getEntry("com.liferay.journal.model.JournalArticle", journalArticle.getResourcePrimKey())/>

<#assign layoutURL = freemarkerUtilities.getArticleFullURL(journalArticleId, locale)>
<#assign displayDate = freemarkerUtilities.getDisplayDate(journalArticle, languageUtil.get(locale, "aragon.short-date-format"))/>
<#assign categoryList = freemarkerUtilities.getArticleCategoriesList(journalArticle, locale)/>

<div class="head-news">
	<a href="${layoutURL}" class="img-title-container">
		<h3 class="title">${.vars['reserved-article-title'].data}</h3>
	</a>
	<p><span class="date">${displayDate}</span></p>
	<#if categoryList?has_content>
		<ul class="categories">
			<#list categoryList as category>
				<li class="categories__item"><span class="name">${category}</span></li>
			</#list>
		</ul>
	</#if>
</div>