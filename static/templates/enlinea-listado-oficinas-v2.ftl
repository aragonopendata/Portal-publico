<style>
	.listado-oficinas-enlinea .titulo-bloque-oficinas-tipo {
		font-size: 2.2rem;
		line-height: 3.36rem;
		font-family: "Signika-SemiBold",sans-serif;
		margin-bottom: 2rem;
	}
	.listado-oficinas-enlinea .titulo-bloque-oficinas-provincia {
		font-size: 1.6rem;
		line-height: 2.4rem;
		margin-bottom: 2rem;
		font-weight: bold;
	}
	.listado-oficinas-enlinea .office-filters {
		margin: 4rem 0;
	}
	.listado-oficinas-enlinea .office-filters select {
		display: block;
		width: 100%;
	}
	.listado-oficinas-enlinea .bloque-oficinas .u-btn {
		display: inline-block;
		width: auto !important;
		padding: 1rem !important;
	}
	.listado-oficinas-enlinea .titulo-oficina {
		font-size: 2rem;
		line-height: 2.6rem;
		margin-bottom: 2rem;
		font-weight: bold;
		text-decoration: underline;
		text-decoration-color: #f05442;
	}
	@media (max-width: 991px) {
		.listado-oficinas-enlinea .bloque-oficinas .u-btn {
			margin-bottom: 2rem;
		}
	}
	@media (max-width: 768px) {
		.listado-oficinas-enlinea .office-filters select {
			margin-bottom: 2rem;
		}
	}
</style>

<#assign defaultCitaURL = "https://aplicaciones.aragon.es/cprga_web"/>
<section class="en-linea tramite-body tramite-body--como">
	<div class="container">
		<div class="info">
		    ${description.getData()}
		</div>
		<div class="pt-5 pb-5">
			<p class="text-center"><a href="${defaultCitaURL}" class="u-btn u-btn-red btn-access-news">cita previa registro</a></p>
		</div>
		<#if question.getSiblings()?has_content> 
			<ol class="list">
    		    <#list question.getSiblings() as quest> 
    		        <li class="list__item">
        				<p class="title">
        					<a href="javascript:void(0)" data-control-collapse="oficina-list-section-${quest?index}" aria-expanded="${quest?is_first?c}">
        						${quest.getData()}
        					</a>
        				</p>
        				<div class="info collapse ${quest?is_first?string('show','')}" data-content-collapse="oficina-list-section-${quest?index}" id="oficina-list-section-${quest?index}">
        					<div class="pt-5 pb-5">
                                ${quest.answer.getData()}
                                <#if getterUtil.getBoolean(quest.isOfficeList.getData())>
	                                <div class="listado-oficinas-enlinea">
		                                <section class="office-filters">
	                                		<div class="row">
												<div class="column col-xs-12 col-md-4">
													<label for="tipo" class="oculto">Servicio</label>
													<select id="tipo" onchange="officeFilterChanged();">
														<option value="" selected="selected">Servicio</option>
														<option value='fntm'>Certificado digital FNMT</option>
														<option value='clave'>Clave</option>
														<option value='departamento'>Departamento</option>
														<option value='registro'>Registro</option>	
																			
													</select>
												</div>                                			
												<div class="column col-xs-12 col-md-4">
													<label for="provincia" class="oculto">Provincia</label>
													<select id="provincia" onchange="officeFilterChanged();">
														<option value="" selected="selected">Provincia</option>
														<option value='Huesca'>Huesca</option>
														<option value='Teruel'>Teruel</option>
														<option value='Zaragoza'>Zaragoza</option>
													</select>
												</div>
											</div>
										</section>
	                                    <#if officeName.getSiblings()?has_content>
	                                    <div class="spinner-ajax text-center">
                							<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-spinner-gob-aragon.gif" alt="" style="width:35px;height:35px;">
                						</div>
		                                    <section class="bloque-oficinas" style="display: none;">
		                                    	<div id="departamentos">
		                                    		<h2 id="tit-departamentos" class="titulo-bloque-oficinas-tipo">Departamentos</h2>
		                                    		<div aria-labelledby="tit-departamentos">
														<h3 id="tit-departamentos-huesca" class="titulo-bloque-oficinas-provincia">HUESCA</h3>
														<ol id="departamentos-huesca" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-departamentos-huesca">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("departamento") && cur_officeName.provincia.getData() == "Huesca">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="departamento">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
		                                    			</ol>
														<h3 id="tit-departamentos-teruel" class="titulo-bloque-oficinas-provincia">TERUEL</h3>
														<ol id="departamentos-teruel" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-departamentos-teruel">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("departamento") && cur_officeName.provincia.getData() == "Teruel">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="departamento">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
		                                    			</ol>
														<h3 id="tit-departamentos-zaragoza" class="titulo-bloque-oficinas-provincia">ZARAGOZA</h3>
														<ol id="departamentos-zaragoza" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-departamentos-zaragoza">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("departamento") && cur_officeName.provincia.getData() == "Zaragoza">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="departamento">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
		                                    			</ol>	                                    				                                    			
		                                    		</div>
		                                    	</div>
												<div id="oficinas-registro">
													<h2 id="tit-oficinas-registro" class="titulo-bloque-oficinas-tipo">Oficinas de registro con cita previa</h2>
													<div aria-labelledby="tit-oficinas-registro">		
														<h3 id="tit-oficinas-registro-huesca" class="titulo-bloque-oficinas-provincia">HUESCA</h3>
														<ol id="oficinas-registro-huesca" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-registro-huesca">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("registro") && cur_officeName.provincia.getData() == "Huesca">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="registro">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>
														<h3 id="tit-oficinas-registro-teruel" class="titulo-bloque-oficinas-provincia">TERUEL</h3>
														<ol id="oficinas-registro-teruel" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-registro-teruel">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("registro") && cur_officeName.provincia.getData() == "Teruel">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="registro">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>
														<h3 id="tit-oficinas-registro-zaragoza" class="titulo-bloque-oficinas-provincia">ZARAGOZA</h3>
														<ol id="oficinas-registro-zaragoza" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-registro-zaragoza">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("registro") && cur_officeName.provincia.getData() == "Zaragoza">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="registro">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>								
													</div>
												</div>
												<div id="oficinas-clave">
													<h2 id="tit-oficinas-clave" class="titulo-bloque-oficinas-tipo">Oficinas de Cl@ve con cita previa</h2>
													<div aria-labelledby="tit-oficinas-clave">
														<h3 id="tit-oficinas-clave-huesca" class="titulo-bloque-oficinas-provincia">HUESCA</h3>
														<ol id="oficinas-clave-huesca" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-clave-huesca">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("clave") && cur_officeName.provincia.getData() == "Huesca">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="clave">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>
														<h3 id="tit-oficinas-clave-teruel" class="titulo-bloque-oficinas-provincia">TERUEL</h3>
														<ol id="oficinas-clave-teruel" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-clave-teruel">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("clave") && cur_officeName.provincia.getData() == "Teruel">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="clave">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>
														<h3 id="tit-oficinas-clave-zaragoza" class="titulo-bloque-oficinas-provincia">ZARAGOZA</h3>
														<ol id="oficinas-clave-zaragoza" class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-clave-zaragoza">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("clave") && cur_officeName.provincia.getData() == "Zaragoza">
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="clave">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>
													</div>
												</div>
												<div id="oficinas-fnmt">
													<h2 id="tit-oficinas-fnmt" class="titulo-bloque-oficinas-tipo">Oficinas Certificado Digital FNMT persona física</h2>
													<div aria-labelledby="tit-oficinas-clave">
														<ol id="oficinas-fnmt" class="oficinas-list u-margin-bottom-6">
															<#list officeName.getSiblings() as cur_officeName>
																<#if cur_officeName.officeType.getOptions()?seq_contains("fnmt")>
																	<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}" data-provincia="${cur_officeName.provincia.getData()}" data-tipo="fnmt">
																		<div class="col-xs-12 col-lg-4">
																			<p class="titulo-oficina">${cur_officeName.getData()}</p>
																			<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																				<a class="u-btn u-btn-red btn-access-news" href="${cur_officeName.isOpenOffice.urlAppointment.getData()?has_content?string(cur_officeName.isOpenOffice.urlAppointment.getData(), defaultCitaURL)}">cita previa</a>
																			</#if>																		
																		</div>
																		<div class="col-xs-12 col-lg-4">
																			<p class="item__adress">${cur_officeName.direction.getData()} - ${cur_officeName.municipio.getData()}, ${cur_officeName.provincia.getData()}</p>
																			<a class="item__phone" href="tel:+${cur_officeName.telephone.getData()}">${cur_officeName.telephone.getData()}</a>
																		</div>
																		<#if getterUtil.getBoolean(cur_officeName.isOpenOffice.getData())>
																			<#if cur_officeName.period.getData()?has_content && cur_officeName.period.schedule.getData()?has_content && cur_officeName.period.getSiblings()?has_content>
																				<div class="col-xs-12 col-lg-4 horarios">
																					<#list cur_officeName.period.getSiblings() as cur_period>
																						<p class="item__time d-flex justify-content-between">
																							<span>${cur_period.getData()}</span>
																							<span class="text-right">${cur_period.schedule.getData()}</span>
																						</p>
																					</#list>
																				</div>
																			</#if>
																		<#else>
																			<div class="col-xs-12 col-lg-4 horarios">
																				<p class="item__time d-flex justify-content-between">Cerrado</p>
																			</div>
																		</#if>
																	</li>
																</#if>
															</#list>
														</ol>
													</div>
												</div>
												<div id="no-such-office-msg" style="display: none;">
													<div class="alert alert-warning">
														<p class="m-0">No se han encontrado oficinas</p>
													</div>
												</div>  
											</section>
										</#if>
									</div>
                                </#if>
        					</div>
				        </div>
	            	</li>
    		    </#list> 
    		</ol>
		</#if>
	</div>
</section>
<script>
	function officeFilterChanged() {
		var provincia = "";
		var tipo = "";
		if (document.getElementById("provincia") != null) {
			var provinciaElement = document.getElementById("provincia");
			provincia = provinciaElement.value;
		}
		if (document.getElementById("tipo") != null) {
			var tipoElement = document.getElementById("tipo");
			tipo = tipoElement.value;
		}
		filterConvocatories(provincia, tipo);
	}

	function filterConvocatories(provincia, tipo) {
		var matchCount = 0;
		$(".bloque-oficinas").hide();
		//Loop office
		$(".bloque-oficinas .item").each(function(index) {
			var matched = true;
			if (provincia != "") {
				if ($(this).data("provincia") != provincia) {
					matched = false;
				}
			}
			if (tipo != "") {
				if ($(this).data("tipo") != tipo) {
					matched = false;
				}
			}
			if (matched) {
				matchCount++;
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		$(".spinner-ajax").hide();
		//Show or hide no results message
		if (matchCount == 0) {
			$("#no-such-office-msg").show();
		} else {
			$("#no-such-office-msg").hide();
		}
		//Show or hide DEPARTMENTS block title
		var showDepartmentsTitle = false;
		var showDepartmentsZaragozaTitle = false;
		var showDepartmentsHuescaTitle = false;
		var showDepartmentsTeruelTitle = false;
		$("#departamentos .item").each(function(index) {
			if ($(this).css("display") == "flex") {
				showDepartmentsTitle = true;
			}
		});
		$("#departamentos-zaragoza .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null) {
				showDepartmentsZaragozaTitle = true;
			}
		});
		$("#departamentos-huesca .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null) {
				showDepartmentsHuescaTitle = true;
			}
		});
		$("#departamentos-teruel .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null)  {
				showDepartmentsTeruelTitle = true;
			}
		});
		if (showDepartmentsTitle) {
			$("#departamentos h2").show();
			if (showDepartmentsZaragozaTitle) {
				$("#tit-departamentos-zaragoza").show();
			} else {
				$("#tit-departamentos-zaragoza").hide();
				$("#departamentos-zaragoza").remove();
			}
			if (showDepartmentsHuescaTitle) {
				$("#tit-departamentos-huesca").show();
			} else {
				$("#tit-departamentos-huesca").hide();
				$("#departamentos-huesca").remove();
			}
			if (showDepartmentsTeruelTitle) {
				$("#tit-departamentos-teruel").show();
			} else {
				$("#tit-departamentos-teruel").hide();
				$("#departamentos-teruel").remove();
			}
		} else {
			$("#departamentos h2").hide();
			$("#departamentos h3").hide();
		}
		//Show or hide REGISTRY office block title
		var showOfficeRegistryTitle = false;
		var showOfficeRegistryZaragozaTitle = false;
		var showOfficeRegistryHuescaTitle = false;
		var showOfficeRegistryTeruelTitle = false;
		$("#oficinas-registro .item").each(function(index) {
			if ($(this).css("display") == "flex") {
				showOfficeRegistryTitle = true;
			}
		});
		$("#oficinas-registro-zaragoza .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null) {
				showOfficeRegistryZaragozaTitle = true;
			}
		});
		$("#oficinas-registro-huesca .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null) {
				showOfficeRegistryHuescaTitle = true;
			}
		});
		$("#oficinas-registro-teruel .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null)  {
				showOfficeRegistryTeruelTitle = true;
			}
		});
		if (showOfficeRegistryTitle) {
			$("#oficinas-registro h2").show();
			if (showOfficeRegistryZaragozaTitle) {
				$("#tit-oficinas-registro-zaragoza").show();
			} else {
				$("#tit-oficinas-registro-zaragoza").hide();
				$("#oficinas-registro-zaragoza").remove();
			}
			if (showOfficeRegistryHuescaTitle) {
				$("#tit-oficinas-registro-huesca").show();
			} else {
				$("#tit-oficinas-registro-huesca").hide();
				$("#oficinas-registro-huesca").remove();
			}
			if (showOfficeRegistryTeruelTitle) {
				$("#tit-oficinas-registro-teruel").show();
			} else {
				$("#tit-oficinas-registro-teruel").hide();
				$("#oficinas-registro-teruel").remove();
			}
		} else {
			$("#oficinas-registro h2").hide();
			$("#oficinas-registro h3").hide();
		}
		//Show or hide CLAVE office block title
		var showClaveOfficeTitle = false;
		var showOfficeClaveZaragozaTitle = false;
		var showOfficeClaveHuescaTitle = false;
		var showOfficeClaveTeruelTitle = false;
		$("#oficinas-clave .item").each(function(index) {	
			if ($(this).css("display") == "flex") {
				showClaveOfficeTitle = true;
			}
		});
		$("#oficinas-clave-zaragoza .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null) {
				showOfficeClaveZaragozaTitle = true;
			}
		});
		$("#oficinas-clave-huesca .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null) {
				showOfficeClaveHuescaTitle = true;
			}
		});
		$("#oficinas-clave-teruel .item").each(function(index) {
			if ($(this).css("display") == "flex" && $(this) != null)  {
				showOfficeClaveTeruelTitle = true;
			}
		});
		if (showClaveOfficeTitle) {
			$("#oficinas-clave h2").show();
			if (showOfficeClaveZaragozaTitle) {
				$("#tit-oficinas-clave-zaragoza").show();
			} else {
				$("#tit-oficinas-clave-zaragoza").hide();
				$("#oficinas-clave-zaragoza").remove();
			}
			if (showOfficeClaveHuescaTitle) {
				$("#tit-oficinas-clave-huesca").show();
			} else {
				$("#tit-oficinas-clave-huesca").hide();
				$("#oficinas-clave-huesca").remove();
			}
			if (showOfficeClaveTeruelTitle) {
				$("#tit-oficinas-clave-teruel").show();
			} else {
				$("#tit-oficinas-clave-teruel").hide();
				$("#oficinas-clave-teruel").remove();
			}
		} else {
			$("#oficinas-clave h2").hide();
			$("#oficinas-clave h3").hide();
		}
		//Show or hide FNMT office block title
		var showFnmtOfficeTitle = false;
		$("#oficinas-fnmt .item").each(function(index) {	
			if ($(this).css("display") == "flex") {
				showFnmtOfficeTitle = true;
			}
		});
		if (showFnmtOfficeTitle) {
			$("#oficinas-fnmt h2").show();
			$("#oficinas-fnmt h3").show();
		} else {
			$("#oficinas-fnmt h2").hide();
			$("#oficinas-fnmt h3").hide();
		}
		$(".bloque-oficinas").fadeIn("slow");
	}
	window.onload = function() {
		officeFilterChanged();
	};
</script>