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
		<p>${.vars['reserved-article-title'].data}</p>
	</div>
	<div class="mapa-localizacion u-margin-bottom-3" id="map"></div>
	<ul class="oficinas-list u-margin-bottom-6">
		<#if officeName.getSiblings()?has_content>
			<#list officeName.getSiblings() as cur_officeName>
				<li class="item row u-margin-bottom-3 flex-column flex-lg-row align-items-center">
					<h2 class="title col-xs-12 col-lg-4">
						<a href="#map">${cur_officeName.getData()}</a>
					</h2>
					<div class="dir-tel col-xs-12 col-lg-4">
						<p class="item__adress">${cur_officeName.direction.getData()}</p>
						<a href="tel:+${cur_officeName.telephone.getData()}" class="item__phone">${cur_officeName.telephone.getData()}</a>
					</div>
					<#if cur_officeName.period.getSiblings()?has_content>
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
			</#list>
		</#if>
	</ul>
</div>

<script>
	var oficinas  = [
		<#if officeName.getSiblings()?has_content>
			<#list officeName.getSiblings() as cur_officeName>
				<#if cur_officeName.direction.latitude.getData()?has_content && cur_officeName.direction.longitude.getData()?has_content>
					["${cur_officeName.getData()}","${cur_officeName.direction.getData()}<br/>Tfno. ${cur_officeName.telephone.getData()}","${cur_officeName.direction.latitude.getData()}","${cur_officeName.direction.longitude.getData()}"]
					<#if (cur_officeName_has_next)>,</#if>
				</#if>
			</#list>
		</#if>
	];
	
	function initMap() {
		var map = new google.maps.Map(document.getElementById('map'), {
			center: {lat: 41.5431557, lng: -0.9083709},
			zoom: 8
		});
		
		for (var i = 0; i < oficinas.length; i++) {
			
			var oficina = oficinas[i];
			var marker = new google.maps.Marker({
				position: {lat: parseFloat(oficina[2]), lng: parseFloat(oficina[3])},
				map: map,
				title: oficina[0]
			});
			
			var contentString = oficina[0] + '<br/>' + oficina[1];
			var infowindow = new google.maps.InfoWindow({
				content: contentString
			});
			
			google.maps.event.addListener(marker,'click', (function(marker,infowindow){
				return function() {
					infowindow.open(map,marker);
				};
			})(marker,infowindow));
			
			marker.setMap(map); 
		}
	}
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=${apiKey.getData()}&callback=initMap" async defer></script>