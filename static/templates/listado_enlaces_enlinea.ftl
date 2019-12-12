<!--
Template Name: Listado de enlaces - Enlinea
Template Description: Listado de temas enlinea
Template Key: LISTADO_ENLACES_ENLINEA
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->
<section class="en-linea">
    <div class="fast-access-module">
        <div class="container u-container-mobile-0">
            <#if tema.getSiblings()?has_content>
                <ul class="row fast-access-module__listado">
                    <#list tema.getSiblings() as cur_tema>
                    <li class="col-xs-6 col-lg-4 fast-access-module__listado__item">
                        <#assign linkTarget = "">
                        <#if getterUtil.getBoolean(cur_tema.blank.getData())>  
                            <#assign linkTarget = "target='_blank'">
                        </#if>
                        <h2>
	                        <a href="${cur_tema.enlace.getData()}" ${linkTarget} class="link">
	                          	<div class="icon-theme" style="background-image: url('${cur_tema.icono.getData()}');"></div>
	                            <span class="name">${cur_tema.getData()}</span>
	                        </a>
                    	</h2>
                   	</li>
                    </#list>
                </ul>
            </#if>
        </div>
    </div>
</section>