<!--
Template Name: Idiomas
Template Description: Visualizador de los idiomas disponibles
Template Key: IDIOMAS
Structure Key: 
Cacheable: true
Small Image: false
Class Name: com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry
-->
<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#if entries?has_content>
	<#list entries as entry>
		<#if !entry.getURL()??>
			<#if entry.getLocale().toLanguageTag()== "es-ES">
				<p><button role="button" aria-expanded="false" class="language__select" aria-haspopup="true" tabindex="0" aria-label="español: Idioma actual. Pulsa para seleccionar otro idioma">${entry.getLocale().getDisplayLanguage(locale)}</button></p>
			</#if>
			<#if entry.getLocale().toLanguageTag()== "en-US">
				<p><button role="button" aria-expanded="false" class="language__select" aria-haspopup="true" tabindex="0" aria-label="english: Current language. Press to select another language">${entry.getLocale().getDisplayLanguage(locale)}</button></p>
			</#if>
		</#if>
	</#list>
	<ul class="language__list">
		<#list entries as entry>
			<#if !entry.getURL()??>
		    	<li>${entry.getLocale().getDisplayLanguage(locale)}</li>
		    </#if>
			<#if !entry.isDisabled() && entry.getURL()??>
				<#assign entryURL = freemarkerUtilities.getTranslatedContentUrl(entry.getURL(), themeDisplay.getScopeGroupId(), entry.getLocale()) />
				<#assign langRemplace = entry.getLanguageId()?replace("_", "-") />
				<li>
					<button class="language__list__item" lang="${langRemplace}" onclick="javascript:window.location.href='${entryURL}'; return false;" style="cursor: pointer;">${entry.getLocale().getDisplayLanguage(entry.getLocale())}</button>
				</li>
			</#if>
		</#list>
	</ul>
</#if>