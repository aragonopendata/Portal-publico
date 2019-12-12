<!--
Template Name: Idiomas
Template Description: Visualizador de los idiomas disponibles
Template Key: IDIOMAS
Structure Key: 
Cacheable: true
Small Image: false
Class Name: com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry
-->

<#if entries?has_content>
	<#list entries as entry>
		<#if !entry.getURL()??>
			<p><span role="button" aria-expanded="false" class="language__select" aria-haspopup="true" tabindex="0">${entry.getLocale().getDisplayLanguage(locale)}</span></p>
		</#if>
	</#list>
	<ul class="language__list">
		<#list entries as entry>
			<#if !entry.getURL()??>
		    	<li>${entry.getLocale().getDisplayLanguage(locale)}</li>
		    </#if>
			<#if !entry.isDisabled() && entry.getURL()??>
				<li><a href=${entry.getURL()} lang="${entry.getLanguageId()}">${entry.getLocale().getDisplayLanguage(locale)}</a></li>
			</#if>
		</#list>
	</ul>
</#if>