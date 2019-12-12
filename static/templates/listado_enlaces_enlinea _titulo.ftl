<!--
Template Name: Listado de enlaces - Enlinea con titulo
Template Description: Listado de temas enlinea con titulo
Template Key: LISTADO_ENLACES_ENLINEA_TITULO
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->
<section class="en-linea">
    <div class="fast-access-module">
        <div class="container u-container-mobile-0">
             <h2 class="h2">${.vars['reserved-article-title'].data}</h2>
            <#if tema.getSiblings()?has_content>
                <ul class="row fast-access-module__listado">
                    <#list tema.getSiblings() as cur_tema>
                    <li class="col-xs-6 col-lg-4 fast-access-module__listado__item">
                        <#assign linkTarget = "">
                        <#if getterUtil.getBoolean(cur_tema.blank.getData())>  
                            <#assign linkTarget = "target='_blank'">
                        </#if>
                        <a href="${cur_tema.enlace.getData()}" ${linkTarget} class="link">
                            <div class="icon-theme" style="background-image: url('${cur_tema.icono.getData()}');"></div>
                            <span class="name">${cur_tema.getData()}</span>
                        </a>
                    </li>
                    </#list>
                </ul>
            </#if>
        </div>
    </div>
</section>