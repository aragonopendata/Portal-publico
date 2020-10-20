<!--
Template Name: Listado de enlaces - COVID Home - Sin enlace
Template Description: Listado de enlaces para COVID Home sin enlace /coronavirus
Template Key: LISTADO_ENLACES_COVID_HOME_SIN_ENLACE
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->

<div class="links-list-covid-home">
   <div class="container u-container-mobile-0">
      <div class="banner-home-covid19">
         <div class="col-md-3">
            <img src="${themeDisplay.getPathThemeImages()}/dga/aragonresponsable4.png" alt="#AragónResponsable"/> 
         </div>
         <div class="col-md-6">
            <p class="covid19-consulta">Consulta toda la información oficial sobre el</p><p class="covid19-consulta">Coronavirus COVID-19 en Aragón</p>
         </div>
         <div class="col-md-3">
            <p class="covid19-info-text">Información</p><p class="covid19-info-text">COVID-19</p>
            <p class="covid19-phone"><a href="tel:+34976696382" title="Teléfono de información general sobre el coronavirus">976 696 382</a></p>
         </div>
      </div>
      <div class="banner-home-covid19 covid19-consulta">
         <div class="banner-icon banner-icon-question">
          <a href="/coronavirus/ayudas/alquiler" title="Se abre en una nueva pestaña del navegador" target="_blank">Ayudas para el pago del alquiler por la situación derivada de la COVID-19</a>
         </div>
      </div>
      <#if tema.getSiblings()?has_content>
         <ul>
            <#list tema.getSiblings() as cur_link>
               <li class="col-lg-3 col-md-4 col-sm-6">
                  <#assign linkTarget = "">
                  <#if getterUtil.getBoolean(cur_link.blank.getData())>
                     <#assign linkTarget = "target='_blank'">
                  </#if>
                  <a href="${cur_link.enlace.getData()}" ${linkTarget}>
                     <span class="icon-theme" style="background-image: url('${cur_link.icono.getData()}');"></span> <span class="name">${cur_link.getData()}</span>
                  </a>
               </li>
            </#list>
         </ul>
      </#if>
	</div>
</div>