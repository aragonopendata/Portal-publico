<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/> 
<#assign nameLayout = themeDisplay.getLayout().getName(locale) />
<section class="indice-departamentos u-padding-top-4 u-padding-top-sm-6 u-padding-bottom-1 u-padding-bottom-sm-3">
    <div class="container">
        <div class="row">
        	<#if nameLayout == "Organismos">
        		<#assign childLayoutsLinksMap = freemarkerUtilities.getChildLayoutsLinksMap(themeDisplay.getLayout())/>
	             <section class="elmts-list col-xs-12 col-md-6 u-padding-bottom-2">
	                <h2 class="title"><@liferay.language key="aragon.template.distribucion-organica.aragon-department"/></h2>
	                <ul class="listado">
	                    <#list childLayoutsLinksMap?keys as nameLayout>
	                        <li class="item">
	                            <a href="${childLayoutsLinksMap[nameLayout]}" class="link">
	                                <span class="name">${nameLayout}</span>
	                            </a>
	                        </li>
	                    </#list> 
	                </ul>
	            </section>
	            <#if bloque.getSiblings()?has_content> 
	                <#list bloque.getSiblings() as cur_bloque> 
	                    <section class="elmts-list col-xs-12 col-md-6 u-padding-bottom-2">
	                        <h2 class="title">${cur_bloque.getData()}</h2>
	                         <ul class="listado">
	                            <#if cur_bloque.enlace.getSiblings()?has_content> 
	                                <#list cur_bloque.enlace.getSiblings() as cur_bloqueEnlace> 
	                                    <#assign target = ""/>
	                                    <#if getterUtil.getBoolean(cur_bloque.enlace.openInNewTab.getData())>
	                                        <#assign target = "target='_blank'"/>
	                                    </#if>
	                                    <li class="item">
	                                        <a href="${cur_bloqueEnlace.url.getData()}" class="link">
	                                            <span class="name">${cur_bloqueEnlace.getData()}</span>
	                                        </a>
	                                    </li>
	                                </#list> 
	                            </#if>
	                        </ul>
	                    </section>
	                </#list> 
	            </#if>
	        <#else>
	        	<#assign municipalitiesLinksMap = freemarkerUtilities.getMunicipalitiesLinksMap(themeDisplay.getScopeGroupId(), request, locale, "Municipios")/>
 				<#list municipalitiesLinksMap?keys as provincias>
                    <section class="elmts-list col-xs-12 col-md-4 u-padding-bottom-2">
                        <h2 class="title">${provincias}</h2>
                         <ul class="listado">
                         	<#assign listMunicipalesLinksMap =municipalitiesLinksMap[provincias] />
                             <#list listMunicipalesLinksMap?keys as municipios>
                                <li class="item">
                                    <a href="${listMunicipalesLinksMap[municipios]}" class="link">
                                		<span class="name">${municipios}</span>
                            		</a>
                                </li>
                              </#list>
                        </ul>
                    </section>
           		</#list>
            </#if> 
        </div>
    </div>
</section>