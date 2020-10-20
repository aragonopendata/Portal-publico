<style>
.dga-view .elmts-list .listado .item .link .name-como{
	font-size:2rem;
}

</style>

<div class="portlet-bgblue-title"> <h2 class="portlet-title-text" id="actualidadTitle"> <span class="container">${.vars['reserved-article-title'].data}</span> </h2> </div>
<section class="indice-departamentos">
    <div class="container">
        <div class="row">
            <#if bloque.getSiblings()?has_content> 
                <#list bloque.getSiblings() as cur_bloque> 
                    <section class="elmts-list col-xs-12 col-md-6 u-padding-bottom-2">
                    	<#if cur_bloque.getData()?has_content && cur_bloque.getData() != ""> 
                        	<h2 class="title">${cur_bloque.getData()}</h2>
                        </#if>
                         <ul class="listado">
                            <#if cur_bloque.enlace.getSiblings()?has_content> 
                                <#list cur_bloque.enlace.getSiblings() as cur_bloqueEnlace> 
                                    <#assign target = ""/> 
                                    <#if getterUtil.getBoolean(cur_bloque.enlace.openInNewTab.getData())> 
                                    	<#assign target = "target='_blank'"/> 
                                    </#if> 
                                    <li class="item u-padding-top-2 u-padding-bottom-2"> 
                                    	<a href="${cur_bloqueEnlace.url.getData()}" class="link"> <span class="name-como">${cur_bloqueEnlace.getData()}</span> </a> 
                                    </li> 
                                    </#list> 
                             </#if> 
                          </ul> 
                        </section> 
                   </#list> 
             </#if> 
          </div> 
      </div> 
</section>