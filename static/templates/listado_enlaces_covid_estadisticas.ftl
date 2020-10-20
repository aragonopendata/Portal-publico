<style>
.destacados-estadisticas-covid .contenedor-destacado .titulo {
    text-transform: uppercase;
    color: #565656;
    font-size: 18px;
    font-weight: normal;
    letter-spacing: 0;
    line-height: 25px;
    height: 50px;
    overflow: hidden;
    text-align: left;
    margin-bottom: 20px;
}

.destacados-estadisticas-covid .contenedor-destacado .fuente a {
    color: #005B7D;
}

.destacados-estadisticas-covid .contenedor-destacado .fuente {
    color: #005B7D;
    font-size: 16px;
    font-weight: bold;
    letter-spacing: 0;
    line-height: 24px;
    text-align: left;
    text-decoration: none;
}

</style>

<#assign hashMapData = { "": "" }>
<#assign dateUpdate = "" />

<#if documentURL.getData() != "">
	<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
	<#assign hashMapData = freemarkerUtilities.getCSVStaticsCovid(documentURL.getData()) />
	<#list hashMapData?keys as key>
		<#if key == "fecha">
			<#assign dateUpdate = hashMapData[key]>
		</#if>
	</#list>
</#if>

<div class="destacados-estadisticas-covid">
	<div class="container">
		<h2 class="introduccion">${.vars['reserved-article-description'].data} ${dateUpdate}</h2>
		<#if titleStatics.getSiblings()?has_content>
			<div class="row">
				<#list titleStatics.getSiblings() as destacado>
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6">
						<div class="contenedor-destacado">
							<img alt="${destacado.icono.getAttribute("alt")}" src="${destacado.icono.getData()}'" />
							<h3 class="titulo">${destacado.getData()}</h3>
							<#if destacado.dataStatics.getData()?? && destacado.dataStatics.getData() != "">
								<p class="cifra">${destacado.dataStatics.getData()}</p>
							<#else>
								<#list hashMapData?keys as key>
									<#if key == destacado.csv_column.getData()>
										<p class="cifra">${hashMapData[key]}</p>
									</#if>
								</#list>							
							</#if>
							<p class="fuente">${destacado.fuente.getData()}</p>
						</div>
					</div>					
				</#list>
			</div>
		</#if>
	</div>
</div>

