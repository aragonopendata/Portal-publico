<!--
Template Name: Enlinea contenido de ayuda
Template Description: Contenido de ayuda de Enlinea
Template Key: AYUDA_ENLINEA
Structure Key: AYUDA_ENLINEA
Cacheable: true
Small Image: false
-->
<section class="en-linea tramite-body tramite-body--como">
    <div class="info">
        ${description.getData()}
    </div>
    <#if sectionName.getData()!= "" && sectionName.getSiblings()?has_content>
        <ol class="list">
            <#list sectionName.getSiblings() as cur_sectionName>
                <#if cur_sectionName.getData()?has_content && cur_sectionName.sectionContent.getData()?has_content>
                    <li class="list__item">
                        <p class="title">
                            <a href="javascript:void(0)" data-control-collapse="${.vars['reserved-article-id'].data}-list-section-${cur_sectionName?index}" aria-expanded="${cur_sectionName?is_first?c}">
                                ${cur_sectionName.getData()}
                            </a>
                        </p>
                        <div class="info collapse ${cur_sectionName?is_first?string('show','')}" data-content-collapse="${.vars['reserved-article-id'].data}-list-section-${cur_sectionName?index}" id="${.vars['reserved-article-id'].data}-list-section-${cur_sectionName?index}">
                            <div class="pt-5 pb-5">
                                ${cur_sectionName.sectionContent.getData()}
                            </div>
                        </div>
                    </li>
                </#if>
            </#list>
        </ol>
    </#if>
</section>