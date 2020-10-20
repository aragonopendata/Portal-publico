<!--
Template Name: Footer - General
Template Description: Footer general del portal
Template Key: FOOTER_GENERAL
Structure Key: FOOTER
Cacheable: true
Small Image: false
-->

<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#assign enlineaLastProcedures = freemarkerUtilities.getLastProcedures(request, scopeGroupId)/>
<#assign layoutLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")/>

<#assign page_group = layout.getGroup()/>
<#assign site_default_public_url = htmlUtil.escape(page_group.getDisplayURL(themeDisplay, false))/>

<div class="footer__policies">
	<ul class="container footer__policies__listado">
		<#assign botonTarget = ""/>
		<#if getterUtil.getBoolean(cabecera.boton.botonOpenInNewTab.getData())>
			<#assign botonTarget = "target='_blank'"/>
		</#if>
		<li class="logo link">
	        <a href="${cabecera.boton.botonUrl.getData()}" ${botonTarget} class="hidden-md hidden-lg u-btn u-btn-red"><strong>${cabecera.boton.getData()}</strong></a>
       </li>
       <li class="logo-eu link hidden-sm hidden-sm hidden-md hidden-lg">
       	    <a href="http://www.aragon.es/Fondos_Europeos" class="european-flag" title="<@liferay.language key="aragon.template.footer-general.information-eie"/>" target="_blank">
	            <img class="image" src="${themeDisplay.getPathThemeImages()}/dga/flag_europe.svg" alt="<@liferay.language key="aragon.ue-feder"/>">
				<p class="claim">
					<span>Fondo Europeo <@liferay.language key="aragon.template.footer-general.develop-region"/></span>
				</p>
				<p class="sub-claim"><@liferay.language key="aragon.template.footer-general.build-europe"/></p>
	        </a>
       </li>
		<#if cabecera.enlaceCabecera.getSiblings()?has_content> 
			<#list cabecera.enlaceCabecera.getSiblings() as curEnlace>
				<#assign target = ""/>
				<#if getterUtil.getBoolean(curEnlace.enlaceCabeceraOpenInNewTab.getData())>
					<#assign target = "target='_blank'"/>
				</#if>
				<li class="link"><a href="${curEnlace.enlaceCabeceraUrl.getData()}" ${target}>${curEnlace.getData()}</a></li>
			</#list> 
		</#if>
		<li class="link cta hidden-xs hidden-sm"><a href="${cabecera.boton.botonUrl.getData()}" ${botonTarget} class="u-btn u-btn-red"><strong>${cabecera.boton.getData()}</strong></a></li>
	</ul>
</div>
<div class="footer__links">
	<ul class="container footer__links__list">
		<!-- Temas -->
		<#if layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/temas")??>
			<#assign temasLayout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/temas")/>
			<#assign childLayoutsLinksMap = freemarkerUtilities.getChildLayoutsLinksMap(temasLayout)/>
			<#if childLayoutsLinksMap?? && childLayoutsLinksMap?has_content>
				<li class="col-xs-12 col-sm-12 col-md-3">			
					<div>
						<a class="title" data-toggle="collapse" data-control-collapse="footer-links-temas" href="javascript:void(0)" role="button" aria-expanded="true" aria-controls="footer-links-temas" tabindex="-1">
							<span class="oculto">(Enlaces a) </span>Temas
						</a>
					</div>
					<ul class="sec-list collapse show" data-control-collapse="footer-links-temas" data-content-collapse="footer-links-temas" id="footer-links-temas">
						<#list childLayoutsLinksMap?keys as nameLayout>
							<li>
								<a href="${childLayoutsLinksMap[nameLayout]}">${nameLayout}</a>
							</li>
						</#list>
					</ul>
				</li>
			</#if>
		</#if>
		<!-- Columnas del contenido -->
		<#if contenido.columna.getSiblings()?has_content> 
			<#list contenido.columna.getSiblings() as curColumna> 
				<li class="col-xs-12 col-sm-12 col-md-${curColumna.claseColumna.getData()}">			
					<div>
						<a class="title" data-toggle="collapse" data-control-collapse="footer-links-${curColumna?index}" href="javascript:void(0)" role="button" aria-expanded="true" aria-controls="footer-links-${curColumna?index}" tabindex="-1">
						<span class="oculto">(Enlaces a) </span>${curColumna.getData()}</a>
					</div>
					<ul class="sec-list collapse show" data-control-collapse="footer-links-${curColumna?index}" data-content-collapse="footer-links-${curColumna?index}" id="footer-links-${curColumna?index}">
						<#if curColumna.enlaceColumna.getSiblings()?has_content>
							<#list curColumna.enlaceColumna.getSiblings() as curEnlace>
								<#assign target = ""/>
								<#if getterUtil.getBoolean(curEnlace.enlaceColumnaOpenInNewTab.getData())>
									<#assign target = "target='_blank'"/>
								</#if>
								<li>
									<a href="${curEnlace.enlaceColumnaUrl.getData()}" ${target}>${curEnlace.getData()}</a>
								</li>
							</#list>
						</#if>
					</ul>
				</li>
			</#list>
		</#if>
		<!-- Tramites -->
		<#if enlineaLastProcedures?has_content>
			<li class="col-xs-12 col-sm-12 col-md-3">			
				<div>
					<a class="title" data-toggle="collapse" data-control-collapse="footer-links-enlinea-procedures" href="javascript:void(0)" role="button" aria-expanded="true" aria-controls="footer-links-enlinea-procedures" tabindex="-1">
					<span class="oculto"><@liferay.language key="aragon.template.footer-general.link-to"/> </span><@liferay.language key="aragon.enlinea-procedures-title"/></a>
				</div>
				<ul class="sec-list collapse show" data-control-collapse="footer-links-enlinea-procedures" data-content-collapse="footer-links-enlinea-procedures" id="footer-links-enlinea-procedures">
					<#list enlineaLastProcedures as enlineaItem>
						<li>
							<a href="${enlineaItem[0]}">${enlineaItem[1]}</a>
						</li>
					</#list>
				</ul>
			</li>
		</#if>
	</ul>
</div>
<div class="footer__rrss-contact container">
	<div class="row">
		<#if pie.redSocial.getSiblings()?has_content> 
			<ul class="col-xs-12 col-sm-12 col-md-3 rrss">
				<#list pie.redSocial.getSiblings() as curRedSocial> 
					<#if curRedSocial.getData()?? && curRedSocial.getData() != "">
						<#assign target = ""/>
						<#if getterUtil.getBoolean(curRedSocial.redSocialOpenInNewTab.getData())>
							<#assign target = "target='_blank'"/>
						</#if>
						<li>
							<a href="${curRedSocial.redSocialURL.getData()}" class="icon-rrss" ${target}>
								<img src="${curRedSocial.getData()}" alt="${curRedSocial.getAttribute("alt")}">
							</a>
						</li>
					</#if> 
				</#list>
			</ul>
		</#if>
		<div class="col-xs-12 col-sm-12 col-md-6">
			<span class="name">${pie.copyright.getData()}</span>
			<span>${pie.direccion.getData()} <#if pie.telefono?? && pie.telefono.getData()?has_content> - <abbr title="<@liferay.language key="aragon.template.footer-general.phone"/>">Tfno.</abbr> <a href="tel:+976714000">976 714 000</a></#if></span>
		</div>
		<a href="/-/fondos-europeos-gobierno-de-aragon" class="hidden-xs col-sm-3 european-flag" title="<@liferay.language key="aragon.template.footer-general.information-eie"/>">
			<img class="image" src="${themeDisplay.getPathThemeImages()}/dga/flag_europe.svg" alt="<@liferay.language key="aragon.ue-feder"/>">
			<p class="claim">
				<span>Fondo Europeo <@liferay.language key="aragon.template.footer-general.develop-region"/></span>
			</p>
			<p class="sub-claim"><@liferay.language key="aragon.template.footer-general.build-europe"/></p>
        </a>	
	</div>
</div>