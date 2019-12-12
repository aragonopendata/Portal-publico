<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>

<%@ include file="init.jsp" %>

<%
//Get expando category
long categoryId = (Long) renderRequest.getAttribute("categoryId");
AssetCategory currentCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
AssetVocabulary vocabulay = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(currentCategory.getVocabularyId());
//Get render params
String coorY = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_X), StringPool.BLANK);
String coorX = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_Y), StringPool.BLANK);
String localidad = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD), StringPool.BLANK);
String direccion = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION), StringPool.BLANK);
String cp = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP), StringPool.BLANK);
String email = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL), StringPool.BLANK);
String provincia = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_PROVINCIA), StringPool.BLANK);
String telefono = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO), StringPool.BLANK);
String tituloMap = StringPool.BLANK;
if (vocabulay.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
	tituloMap = GetterUtil.getString(renderRequest.getAttribute(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO), StringPool.BLANK);
} else {
	tituloMap = GetterUtil.getString(renderRequest.getAttribute("TITLE"), StringPool.BLANK);
}
%>
<c:if test="<%=Validator.isNotNull(coorX) && Validator.isNotNull(coorY) && !coorY.equals("0.0") &&  !coorX.equals("0.0") && !coorY.equals("null") &&  !coorX.equals("null")%>" >
	<h2 id="titLocalizacionInterior" class="oculto"><liferay-ui:message key="organization-map.section-title"/></h2>
	<section class="localizacion u-padding-bottom-5" role="region"  aria-labelledby="titLocalizacionInterior">
		<div class="map-container" tabindex="0">
		    <div id="map1" class="map-iframe"></div>
			<c:if test="<%=Validator.isNotNull(tituloMap) || Validator.isNotNull(localidad) || Validator.isNotNull(direccion) || Validator.isNotNull(cp) || Validator.isNotNull(email) || Validator.isNotNull(provincia) || Validator.isNotNull(telefono) %>">
				<div class="data-place">
					<c:if test="<%=Validator.isNotNull(tituloMap)%>">
						<div class="data-place__name">
							<h3><%=tituloMap %></h3>
						</div>
					</c:if>
					<div class="data-place__direction">
						<p>
							<%=direccion%> <%=cp%> <%=localidad%> <c:if test="<%=Validator.isNotNull(provincia)%>">(<%=provincia%>)</c:if>
						</p>
					</div>
					<c:if test="<%=Validator.isNotNull(telefono)%>">
						<%
						String telefonoText = telefono.toString();
						if (telefonoText.length() == 9) {
							telefonoText = telefonoText.substring(0, 3) + " " + telefonoText.substring(3, 6) + " " + telefonoText.substring(6, 9);
						}
						%>
						<p>
							<liferay-ui:message key="phone"/>: <a href="tel:+<%=telefono%>" class="data-place__tfno d-inline"><%=telefonoText%></a>
						</p>
					</c:if>
					<c:if test="<%=Validator.isNotNull(email)%>">
						<p>
							<liferay-ui:message key="email"/>: <a href="mailto:<%=email%>" class="data-place__mail"><%=email %></a>
						</p>
					</c:if>
				</div>
			</c:if>
		</div>
	</section>
</c:if>

<link rel="stylesheet" href="https://unpkg.com/leaflet@0.7.7/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@0.7.7/dist/leaflet.js"></script>
<script src="https://unpkg.com/leaflet.nontiledlayer@1.0.7/dist/NonTiledLayer.js"></script>

<script type="text/javascript">
	var coorX ="<%=coorX%>";
	var coorY ="<%=coorY%>";
	var center = [coorX, coorY];
	
	var map1 = L.map('map1', {
        center: center,
        zoom: 18
    });

    L.nonTiledLayer.wms('https://idearagon.aragon.es/arcgis/services/AragonReferencia/Basico_NEW/MapServer/WMSServer', {
        layers: '0,2,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19',
        format: 'image/png',
        styles: '',
        transparent: true,
        attribution: '<a href="https://idearagon.aragon.es/" target="_blank">Datos servidos por IGEAR</a>',
    }).addTo(map1);
    L.marker([coorX, coorY]).addTo(map1);
	
</script>
