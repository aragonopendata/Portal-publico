<!--
Template Name: Listado de oficinas Enlinea
Template Description: Listado de oficinas Enlinea
Template Key: LISTADO_OFICINAS_ENLINEA
Structure Key: LISTADO_OFICINAS_ENLINEA
Cacheable: true
Small Image: false
-->
<div class="en-linea container">
	<div class="col-12 pt-5 pb-5">
		${informacionMapa.getData()}
	</div>
	<ul class="oficinas-list u-margin-bottom-6">
		<#if officeName.getSiblings()?has_content>
			<#list officeName.getSiblings()?sort_by("data") as cur_officeName>
			    <#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>   
			        <li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
					<h2 class="title col-xs-12 col-lg-4">
						<a href="#">${cur_officeName.getData()}</a>
						<a href="#" class="u-btn u-btn-red btn-access-news">cita previa registro</a>
					</h2>
					<div class="dir-tel col-xs-12 col-lg-4">
						<p class="item__adress">${cur_officeName.direction.getData()}</p>
						<a href="tel:+${cur_officeName.telephone.getData()}" class="item__phone">${cur_officeName.telephone.getData()}</a>
					</div>
					<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
						<div class="horarios col-xs-12 col-lg-4">
							<#list cur_officeName.period.getSiblings() as cur_period>
								<p class="item__time d-flex justify-content-between">
									<span>${cur_period.getData()}</span>
									<span class="text-right">${cur_period.schedule.getData()}</span>
								</p>
							</#list>
						</div>
					</#if>
				</li>
			    </#if>
			</#list>
			<#list officeName.getSiblings()?sort_by("data") as cur_officeName>
			    <#if !getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())> 
			        <li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
						<h2 class="title col-xs-12 col-lg-4">
							<a href="#">${cur_officeName.getData()}</a>
						</h2>
						<div class="dir-tel col-xs-12 col-lg-4">
							<p class="item__adress">${cur_officeName.direction.getData()}</p>
							<a href="tel:+${cur_officeName.telephone.getData()}" class="item__phone">${cur_officeName.telephone.getData()}</a>
						</div>
						<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
							<div class="horarios col-xs-12 col-lg-4">
								<#list cur_officeName.period.getSiblings() as cur_period>
									<p class="item__time d-flex justify-content-between">
										<span>${cur_period.getData()}</span>
										<span class="text-right">${cur_period.schedule.getData()}</span>
									</p>
								</#list>
							</div>
						</#if>
					</li>
			    </#if>
			</#list>
		</#if>
	</ul>
</div>