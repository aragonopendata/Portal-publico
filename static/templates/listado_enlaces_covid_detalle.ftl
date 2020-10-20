<!--
Template Name: Listado de enlaces - COVID Detalle
Template Description: Listado de enlaces para COVID Detalle
Template Key: LISTADO_ENLACES_COVID_DETALLE
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->
<div class="container u-container-mobile-0">
	<#if tema.getSiblings()?has_content>
		<ul class="links-list-covid-detalle">
			<#list tema.getSiblings() as cur_link>
				<li class="col-md-3 col-sm-6">
					<#assign linkTarget = "">
					<#if getterUtil.getBoolean(cur_link.blank.getData())>
						<#assign linkTarget = "target='_blank'">
					</#if>
					<a href="${cur_link.enlace.getData()}" ${linkTarget}>
						<img src="${cur_link.icono.getData()}" alt="" />
						<span class="name">${cur_link.getData()}</span>
					</a>
				</li>
			</#list>
		</ul>
	</#if>
</div>