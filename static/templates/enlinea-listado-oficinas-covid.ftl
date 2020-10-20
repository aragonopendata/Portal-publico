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
</style>

<section class="en-linea tramite-body tramite-body--como listado-oficinas-enlinea">
	<div class="container">
		<div class="info">
			${informacionMapa.getData()}
		</div>
		<div class="pt-5 pb-5">
			<p class="text-center"><a href="https://aplicaciones.aragon.es/cprga_web" class="u-btn u-btn-red btn-access-news">cita previa registro</a></p>
		</div>
		<ol class="list">
			<li class="list__item">
				<p class="title">
					<a href="javascript:void(0)" data-control-collapse="oficina-list-section-1" aria-expanded="true">
						¿En qué casos es necesario que vaya al Registro?
					</a>
				</p>
				<div class="info collapse show" data-content-collapse="oficina-list-section-1" id="oficina-list-section-1">
					<div class="pt-5 pb-5">
						<p>Se aconseja la presentación preferentemente de solicitudes y documentos por registro telemático a través de REGA (Registro electrónico del Gobierno de Aragón) o de REC (Registro Electrónico de la Administración del Estado), tanto si es por persona física o jurídica.</p>
						<p>En el caso de ser una persona física y no tener los recursos para tramitar telemáticamente, pedirás cita previa para acudir presencialmente a la Oficina de Asistencia en Materia de Registro más cercana a tu domicilio, guardando las medidas de prevención frente al COVID-19</p>
						<p>También puedes solicitar cita previa para acudir a una Oficina Cl@ve, donde te facilitaran clave permanente para poder efectuar trámites telemáticos.</p>
						<p>Importante, si acudes a un registro presencialmente y quieres una copia del original, deberás aportar la copia, ya que no se habilitan fotocopias en las unidades de registro.</p>
					</div>
				</div>
			</li>
			<li class="list__item">
				<p class="title">
					<a href="javascript:void(0)" data-control-collapse="oficina-list-section-2" aria-expanded="false">
						¿Qué oficinas de Registro y cl@ve con cita previa están abiertas?
					</a>
				</p>
				<div class="info collapse" data-content-collapse="oficina-list-section-2" id="oficina-list-section-2">
					<div class="pt-5 pb-5">
						<p>Las oficinas irán abriendo conforme avance el periodo de desescalada del COVID-19. Informaremos desde esta página.</p>
					</div>
					<#if officeName.getSiblings()?has_content>
						<h2 id="tit-oficinas-registro" class="titulo-bloque-oficinas-tipo">Oficinas de registro con cita previa</h2>
						<div aria-labelledby="tit-oficinas-registro">
							<h3 id="tit-oficinas-registro-huesca" class="titulo-bloque-oficinas-provincia">HUESCA</h3>
							<ol class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-registro-huesca">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("registro") && cur_officeName.provincia.getData() == "Huesca">
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>Registro</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>
							</ol>
							<h3 id="tit-oficinas-registro-teruel" class="titulo-bloque-oficinas-provincia">TERUEL</h3>
							<ol class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-registro-teruel">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("registro") && cur_officeName.provincia.getData() == "Teruel">
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>Registro</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>
							</ol>
							<h3 id="tit-oficinas-registro-zaragoza" class="titulo-bloque-oficinas-provincia">ZARAGOZA</h3>
							<ol class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-registro-zaragoza">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("registro") && cur_officeName.provincia.getData() == "Zaragoza">
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>Registro</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>
							</ol>								
						</div>
						<h2 id="tit-oficinas-clave" class="titulo-bloque-oficinas-tipo">Oficinas de Cl@ve con cita previa</h2>
						<div aria-labelledby="tit-oficinas-clave">
							<h3 id="tit-oficinas-clave-huesca" class="titulo-bloque-oficinas-provincia">HUESCA</h3>
							<ol class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-clave-huesca">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("clave") && cur_officeName.provincia.getData() == "Huesca">
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>Cl@ve</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>
							</ol>
							<h3 id="tit-oficinas-clave-teruel" class="titulo-bloque-oficinas-provincia">TERUEL</h3>
							<ol class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-clave-teruel">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("clave") && cur_officeName.provincia.getData() == "Teruel">
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>Cl@ve</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>
							</ol>
							<h3 id="tit-oficinas-clave-zaragoza" class="titulo-bloque-oficinas-provincia">ZARAGOZA</h3>
							<ol class="oficinas-list u-margin-bottom-6" aria-labelledby="tit-oficinas-clave-zaragoza">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("clave") && cur_officeName.provincia.getData() == "Zaragoza">
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>Cl@ve</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>		
							</ol>														
						</div>
						<h2 id="tit-oficinas-fnmt" class="titulo-bloque-oficinas-tipo">Oficinas Certificado Digital FNMT persona física</h2>
						<div aria-labelledby="tit-oficinas-clave">
							<ol class="oficinas-list u-margin-bottom-6">
								<#list officeName.getSiblings() as cur_officeName>
									<#if cur_officeName.officeType.getOptions()?seq_contains("fnmt")>
										<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center" data-municipio="${cur_officeName.municipio.getData()}">
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.provincia.getData()}</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>FNMT</p>
											</div>
											<div class="col-xs-12 col-lg-3">
												<p>${cur_officeName.municipio.getData()}</p>
											</div>
											<div class="dir-tel col-xs-12 col-lg-3">
												<p class="item__adress">${cur_officeName.getData()} - ${cur_officeName.direction.getData()}</p>
											</div>
										</li>
									</#if>
								</#list>
							</ol>
						</div>
					</#if>
				</div>
			</li>
			<li class="list__item">
				<p class="title">
					<a href="javascript:void(0)" data-control-collapse="oficina-list-section-3" aria-expanded="false">
						¿Qué horarios tienen las Oficinas de Registro?
					</a>
				</p>
				<div class="info collapse" data-content-collapse="oficina-list-section-3" id="oficina-list-section-3">
					<div class="pt-5 pb-5">
						<p>Las Oficinas de Registro y  Cl@ve  se abrirán con horario de lunes a viernes de 9:00 a 14:00 horas, el ciudadano deberá acudir con cita previa.</p>
					</div>
				</div>			
			</li>
			<li class="list__item">
				<p class="title">
					<a href="javascript:void(0)" data-control-collapse="oficina-list-section-4" aria-expanded="false">
						¿Qué medidas de seguridad se van a establecer para evitar contagios por COVID-19?
					</a>
				</p>
				<div class="info collapse" data-content-collapse="oficina-list-section-4" id="oficina-list-section-4">
					<div class="pt-5 pb-5">
						<p>Se establecerán las medidas preventivas para la prevención de contagios del COVID-19 en los centros administrativos del Gobierno de Aragón.</p>
					</div>
				</div>			
			</li>
			<li class="list__item">
				<p class="title">
					<a href="javascript:void(0)" data-control-collapse="oficina-list-section-5" aria-expanded="false">
						Si no consigo cita antes de que finalice el plazo de presentación de mi solicitud o tengo problemas con la aplicación de cita previa, ¿qué puedo hacer?
					</a>
				</p>
				<div class="info collapse" data-content-collapse="oficina-list-section-5" id="oficina-list-section-5">
					<div class="pt-5 pb-5">
						<p>En el supuesto de que no hayas podido obtener cita previa y no puedas hacer tu trámite electrónicamente, podrás llamar al teléfono de atención de cita previa <a href="tel:+34976360710">976 36 07 10</a> de 8:00h a 18:30h</p>
					</div>
				</div>			
			</li>				
		</ol>
	</div>
</section>