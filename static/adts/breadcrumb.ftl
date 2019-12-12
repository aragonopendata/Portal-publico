<!--
Template Name: Migas portal www.aragon.es
Template Description: Visualizador de los idiomas disponibles
Template Key: MIGAS-PORTAL-DGA
Structure Key: 
Cacheable: true
Small Image: false
Class Name: com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry
-->
<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities") />
<#assign journalsPageUrl = freemarkerUtilities.getGroupExpandoAttributeValue(themeDisplay.getScopeGroupId(), "JOURNALS_PAGE")/>

<section>
	<nav role="navigation" aria-label='<@liferay.language key="aragon.you-are-in"/>'>
		<div class="breadcrumb hidden-xs">
			<div class="container">
				<ul class="breadcrumb__listado">
					<li><a href="/" class="link"><@liferay.language key="aragon.home"/></a></li>
					<#if entries?has_content>
						<#list entries as curEntry>
							<#assign url = "">
							<#if curEntry.getURL()??>
								<#assign url = curEntry.getURL()>
							</#if>
							<#if curEntry.getTitle()!="Eventos">
								<#if curEntry?is_last>
									<li><strong class="final" aria-current="page">${curEntry.getTitle()}</strong></li>
								<#else>
									<#if !url?contains(journalsPageUrl)>
										<li><a href="${url}" class="link">${curEntry.getTitle()}</a></li>
									</#if>
								</#if>
							</#if>
						</#list>
					</#if>
			 	</ul>
			</div>
		</div>
	</nav>
</section>