<h2 id="temasTitle" class="oculto">Temas</h2>
<section role="region" aria-labelledby="temasTitle">
	<div class="fast-access-module">
		<div class="container u-container-mobile-0">
			<#if tema.getSiblings()?has_content>
				<ul class="fast-access-module__listado">
				    <#list tema.getSiblings() as cur_tema>
				    	<li class="col-xs-6 col-sm-3 fast-access-module__listado__item">
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