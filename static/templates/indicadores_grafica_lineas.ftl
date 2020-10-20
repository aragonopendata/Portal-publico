<div class="container">
	<div class="indicadores-grafica-lineas">
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
								"lineTension":0
							};
							<#if nombreDeIndicador.dato.getSiblings()?has_content>
								<#list nombreDeIndicador.dato.getSiblings() as dato>
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
								</#list>
							</#if>
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
				"type":"line",
				"data": {
					"labels": labels,
					"datasets": dataSets
				},
				"options":{}
			};
			var canvasEle = document.getElementById("indicador-${.vars['reserved-article-id'].data}");
			var canvasOpts = {};
			new Chart(canvasEle,canvasData,canvasOpts);
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
					<#assign auxData=minDate>
					<#list 1..rangoDeDias as x>
						<tr>${valoresTabla[auxData?string("yyyy/MM/dd")]}</tr>
						<#assign auxData = ((auxData?long + 86400000)?number_to_date)>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
</div>