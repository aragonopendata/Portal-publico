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
	<#assign isFoundTranslate = false>
	<#assign classButonLanguage = "language_translate">
	<#list entries as entry>
		<#if entry.getURL()?has_content>
			<#if freemarkerUtilities.isTranslated(themeDisplay, entry.getLocale())>
				<#assign isFoundTranslate = true>
				<#assign classButonLanguage = "language__select">
			</#if>
		</#if>
	</#list>
	<#if themeDisplay.getLanguageId() == "es_ES">
		<p><button role="button" aria-expanded="false" class="${classButonLanguage}" aria-haspopup="true" tabindex="0" aria-label="español: Idioma actual. Pulsa para seleccionar otro idioma">${locale.getDisplayLanguage(locale)}</button></p>
	<#elseif themeDisplay.getLanguageId() == "en_US">
		<p><button role="button" aria-expanded="false" class="${classButonLanguage}" aria-haspopup="true" tabindex="0" aria-label="english: Current language. Press to select another language">${locale.getDisplayLanguage(locale)}</button></p>
	<#else>
		<p><button role="button" aria-expanded="false" class="${classButonLanguage}" aria-haspopup="true" tabindex="0">${locale.getDisplayLanguage(locale)}</button></p>
	</#if>
	<#if isFoundTranslate == true>
		<ul class="language__list">
			<#list entries as entry>
				<#if !entry.getURL()??>
	                <li>${entry.getLocale().getDisplayLanguage(locale)}</li>
	            <#elseif !entry.isDisabled() && entry.getURL()?has_content>		
					<#assign entryURL = freemarkerUtilities.getTranslatedContentUrl(entry.getURL(), themeDisplay.getScopeGroupId(), entry.getLocale()) />
					<li>
						<button class="language__list__item" lang="${entry.getLocale().toLanguageTag()}" onclick="javascript:window.location.href='${entryURL}'; return false;" style="cursor: pointer;">${entry.getLocale().getDisplayLanguage(entry.getLocale())}</button>
					</li>
				</#if>
			</#list>
		</ul>
	</#if>
	<#if !themeDisplay.isSignedIn() && isFoundTranslate == false && locale?string == "en_US">
		<script>window.location.href='/c/portal/update_language?p_l_id=${themeDisplay.getPlid()}&redirect=${themeDisplay.getURLCurrent()}&languageId=es_ES';</script>
	</#if>
</#if>