<!--
Template Name: Listado de enlaces - Servicios
Template Description: Listado de servicios
Template Key: LISTADO_ENLACES_SERVICIOS
Structure Key: LISTADO_ENLACES
Cacheable: true
Small Image: false
-->
<#if tema.getSiblings()?has_content>
	<div class="access-links">
		<ul class="container access-links__list">
			<#list tema.getSiblings() as cur_tema>
				<li class="access-links__list__items">
					<#assign linkTarget = "">
					<#if getterUtil.getBoolean(cur_tema.blank.getData())>  
						<#assign linkTarget = "target='_blank'">
					</#if>
					<a href="${cur_tema.enlace.getData()}" ${linkTarget}>
						<div class="svg-container">
							<div style="background-image: url('${cur_tema.icono.getData()}');"></div>
						</div>
						<#if cur_tema.descripcion?? && cur_tema.descripcion.getData()?has_content>
							<span class="title">
								<abbr title="${cur_tema.descripcion.getData()}">${cur_tema.getData()}</abbr>
							</span>
						<#else>
							<span class="title">${cur_tema.getData()}</span>
						</#if>
					</a>
				</li>
			</#list>
		</ul>
	</div>
</#if>