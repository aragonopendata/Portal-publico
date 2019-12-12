<!--
Template Name: Aceptación de cookies - General
Template Description: Popup de aceptación de cookies
Template Key: ACEPTACION_COOKIES_GENERAL
Structure Key: ACEPTACION_COOKIES
Cacheable: true
Small Image: false
-->

<div id="modal_aceptacion_cookies"></div>
<script>
	var cookiesAcceptedCookieName = "COOKIES_ACCEPTED";
	var cookiesAcceptedCookieValue = "";
	//Comprobar si las cookies ya han sido aceptadas
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookiesArray = decodedCookie.split(';');
    for (var i = 0; i < cookiesArray.length; i++) {
        var cookieEntry = cookiesArray[i];
        while (cookieEntry.charAt(0) == ' ') {
            cookieEntry = cookieEntry.substring(1);
        }
        if (cookieEntry.indexOf(cookiesAcceptedCookieName + "=") == 0) {
            cookiesAcceptedCookieValue = cookieEntry.substring((cookiesAcceptedCookieName + "=").length, cookieEntry.length);
        }
    }
	if (cookiesAcceptedCookieValue != "true") {
		YUI().use(
			'aui-modal',
			function(Y) {
				var modal = new Y.Modal(
					{
						headerContent: '<h4>${.vars['reserved-article-title'].data}</h4>',
						bodyContent: '${textoAceptacion.getData()}',
						centered: true,
						destroyOnHide: false,
						modal: true,
						render: '#modal_aceptacion_cookies',
						zIndex: 1,
						toolbars: {},
						visible: true,
						width: 450,

					}
				).render();
				modal.addToolbar(
					[
						{
							label: 'Aceptar',
							on: {
								click: function() {
									//Almacenar la cookie donde se indica que las cookies han sido aceptadas
									var dateNow = new Date();
									dateNow.setTime(dateNow.getTime() + (365 * 24 * 60 * 60 * 1000));
									document.cookie = cookiesAcceptedCookieName + "=" + "true" + ";" + "expires=" + dateNow.toGMTString() + ";path=/";
									//Ocultar el popup
									modal.hide();
								}
							}
						}
					]
				);
			}
		);
	}
</script>