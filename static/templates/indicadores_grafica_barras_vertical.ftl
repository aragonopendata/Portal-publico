<style>
	.btn-view-more{
		text-align: left;
		background: transparent;
        color: inherit;
        border: none;
        padding: 0;
        padding-top: 30px;
        font-family: "open_sans-semibold",sans-serif;
    	font-size: 1.8rem;
    	color: #000;
    	text-decoration: none;
   		position: relative;
    	line-height: 4.4rem;
    	cursor: pointer;
	    top: 0;
	    right: 0;
	    position: relative;
	    margin: auto;
	    display: block;
	}
	.btn-view-more::before{
	    content: '\f107';
	    font-family: 'fontawesome-alloy';
	    position: absolute;
	    top: 52px;
	    left: 100px;
	    font-size: 30px;
	    font-size: 3rem;
	    line-height: 2.6rem;
	    color: #333;
	    transform: translate(-50%,-50%) rotate(0);
	    transition: all 300ms linear;
	}
	.btn-view-more[aria-expanded=true]::before {
		top: 47px;
	    left: 116px;
    	transform: translate(-52%,-20%) rotate(180deg);
    	color: #f05442;
	}
	.btn-view-more:focus {
         outline:none;
    }
	.oculto{
		display:none;
	}
	
</style>

<div class="container">
	<div class="indicadores-grafica-barras-vertical">
		<div >
			<h2>${.vars['reserved-article-title'].data}</h2>
			<canvas id="indicador-${.vars['reserved-article-id'].data}" aria-label="Gráfica ${.vars['reserved-article-title'].data}. Los datos se muestran en formato tabla al lado de la gráfica.">Gráfica ${.vars['reserved-article-title'].data}. Los datos se muestran en formato tabla al lado de la gráfica.</canvas>
		</div>
		<script>
			var coloursList = ['#E00000', '#726012', '#B200FD', '#008040', '#D43900', '#0000E0'];
			var minDate;
			var maxDate;
			var dataSets = [];
			<#if indicador.getSiblings()?has_content>
				<#list indicador.getSiblings() as indicadorNode> 
					<#if indicadorNode.nombreDeIndicador.getSiblings()?has_content>
						<#list indicadorNode.nombreDeIndicador.getSiblings() as nombreDeIndicador>
							var borderColor = coloursList[${nombreDeIndicador?index}];
							var dataSet = {
								"label":"${nombreDeIndicador.getData()}",
								"data":[],
								"fill":false,
								"borderColor":borderColor,
								"backgroundColor": borderColor,
								"borderWidth": 1
							};
							<#if nombreDeIndicador.dato.getSiblings()?has_content>
								<#list nombreDeIndicador.dato.getSiblings() as dato>
									<#if dato.fecha.getData()?contains("-")>
										<#assign compareDate=(dato.fecha.getData()+"T00:00:00.000Z")?datetime.iso>
										<#if (!minDate?? || ((minDate?long) > (compareDate?long)))>
											<#assign minDate=compareDate>
										</#if>
										<#if (!maxDate?? || ((maxDate?long) < (compareDate?long)))>
											<#assign maxDate=compareDate>
										</#if>
										if(minDate==undefined || minDate>new Date("${dato.fecha.getData()}")){
											minDate = new Date("${dato.fecha.getData()}");
										}
										if(maxDate==undefined || maxDate<new Date("${dato.fecha.getData()}")){
											maxDate = new Date("${dato.fecha.getData()}");
										}
										dataPoint = {}
										dataPoint["x"] = "${dato.fecha.getData()}";
										dataPoint["y"] = ${dato.numero.getData()};
										if(dataSet["data"].length==0){
											dataSet["data"].push(dataPoint);
										} else {
											var dataAdded = false;
											for (i = 0; i < dataSet["data"].length; i++) {
												if(dataSet["data"][i].x>"${dato.fecha.getData()}"){
													dataSet["data"].splice(i, 0, dataPoint);
													dataAdded = true;
													break;
												}
											}
											if(!dataAdded) {
												dataSet["data"].push(dataPoint);
											}
										}
									</#if>
								</#list>
							</#if>
							dataSet["data"] = Array.from(dataSet["data"], dataPoint => dataPoint.y);
							dataSets.push(dataSet);
						</#list>
					</#if>
				</#list> 
			</#if>
			var labels = [];
			var currentDate = minDate;
			while (currentDate <= maxDate) {
				var dateYear = currentDate.getFullYear();
				var dateMonth = ((currentDate.getMonth()+1)>9 ? '' : '0') + (currentDate.getMonth()+1);
				var dateDay = (currentDate.getDate()>9 ? '' : '0') + currentDate.getDate()
				var dateStr = dateYear + '/' + dateMonth + '/' + dateDay;
				labels.push(dateStr);
				currentDate.setDate(currentDate.getDate() + 1);
			}
			var canvasData = 
			{
				"type":"bar",
				"data": {
					"labels": labels,
					"datasets": dataSets
				},
				"options": {
					"scales": {
						"yAxes": [{
							"ticks": {
								"beginAtZero": true
							}
						}]
					}
				}
				
			};
			var canvasEle = document.getElementById("indicador-${.vars['reserved-article-id'].data}");
			var canvasOpts = {};
			new Chart(canvasEle,canvasData,canvasOpts);
			 $(".chartjs-hidden-iframe").prop('title', 'Grafica datos coronavirus');
		</script>

		<#assign rangoDeDias = ((maxDate?long/86400000)-(minDate?long/86400000)+1)>
		<#assign tablaHeader = "<th>Fecha</th>">
		<#assign auxData=minDate>
		<#assign valoresTabla={}>
		<#list 1..rangoDeDias as x>
			<#assign valoresTabla = (valoresTabla + {auxData?string("yyyy/MM/dd"): "<td>"+auxData?string("yyyy/MM/dd")+"</td>" })>
			<#assign auxData = ((auxData?long + 86400000)?number_to_date)>
		</#list>

		<#if indicador.getSiblings()?has_content>
			<#list indicador.getSiblings() as indicadorNode> 
				<#if indicadorNode.nombreDeIndicador.getSiblings()?has_content>
					<#list indicadorNode.nombreDeIndicador.getSiblings() as nombreDeIndicador>
						<#assign tablaHeader = tablaHeader+"<th>"+nombreDeIndicador.getData()+"</th>">
						<#if nombreDeIndicador.dato.getSiblings()?has_content>
							<#assign auxData=minDate>
							<#assign valoresPorDia={}>
							<#list 1..rangoDeDias as x>
								<#assign valoresPorDia = (valoresPorDia + {auxData?string("yyyy/MM/dd"): "<td>-</td>" })>
								<#assign auxData = ((auxData?long + 86400000)?number_to_date)>
							</#list>
							<#list nombreDeIndicador.dato.getSiblings() as dato>
								<#assign auxData=(dato.fecha.getData()+"T00:00:00.000Z")?datetime.iso>
								<#assign valoresPorDia = (valoresPorDia + {auxData?string("yyyy/MM/dd"): "<td>"+dato.numero.getData()+"</td>" })>
							</#list>
						</#if>
						<#assign auxData=minDate>
						<#list 1..rangoDeDias as x>
							<#assign valoresTabla = (valoresTabla + {auxData?string("yyyy/MM/dd"): valoresTabla[auxData?string("yyyy/MM/dd")]+valoresPorDia[auxData?string("yyyy/MM/dd")] })>
							<#assign auxData = ((auxData?long + 86400000)?number_to_date)>
						</#list>
					</#list>
				</#if>
			</#list> 
		</#if>
		<div class="tabla-coronavirus">
			<h2>${.vars['reserved-article-description'].data}</h2>
			<p id="custom-table-${.vars['reserved-article-id'].data}">
				<#if descripciontabla?? && descripciontabla.getData()?? && descripciontabla.getData() != "" >
					${descripciontabla.getData()}
				</#if>
			</p>
			<table aria-describedby="custom-table-${.vars['reserved-article-id'].data}">
				<tbody>
					<tr>
						${tablaHeader}
					</tr>
					<#assign auxData=maxDate>
					<#list 1..15 as x>
						<tr>${valoresTabla[auxData?string("yyyy/MM/dd")]}</tr>
						<#assign auxData = ((auxData?long - 86400000)?number_to_date)>
					</#list>
					<#list 16..rangoDeDias as x>
						<tr class="oculto ${.vars['reserved-article-id'].data}">${valoresTabla[auxData?string("yyyy/MM/dd")]}</tr>
						<#assign auxData = ((auxData?long - 86400000)?number_to_date)>
					</#list>
				</tbody>
			</table>
		</div>
		<button id="btn-show-data-${.vars['reserved-article-id'].data}" class="btn-view-more js-arrow-open collapsed" aria-expanded="false" aria-label="Mostrar datos más de 15 días" data-label-closed="Mostrar datos" data-label-opened="Ocultar datos" onclick="viewMore('${.vars['reserved-article-id'].data}')">Ver más</button>
	</div>
</div>
<script>
var status = "more";

function viewMore(idTable){
	if(status == "more"){
		$("tr."+idTable).removeClass( "oculto" );
		$("#btn-show-data-"+idTable).text("Ver menos");
		$("#btn-show-data-"+idTable).attr("aria-expanded","true");
		status= "less";
	}else { 
		$("tr."+idTable).addClass( "oculto" );
		$("#btn-show-data-"+idTable).text("Ver más");
		$("#btn-show-data-"+idTable).attr("aria-expanded","false");
		status= "more";
	}
}

</script>