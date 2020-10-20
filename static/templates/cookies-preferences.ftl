<section class="configuracion-cookies">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h2 class="titulo">${.vars['reserved-article-title'].data}</h2> 
				<div class="descripcion">${descriptionCookiesPolicy.getData()}</div>
				<#if titleCookie.getSiblings()?has_content> 
					<form id="formularioConfiguracionCookies" class="formulario">
						<#assign contador = 0> 						
						<#list titleCookie.getSiblings() as cookie> 
							<#if cookie.getData()?has_content && cookie.getData() != "">
    							<fieldset>
	    							<legend>${titleCookie.getData()}</legend>
	    							<div class="contenido-cookie"> 
		    				          ${cookie.cookieDescription.getData()}
		    				             <#if getterUtil.getBoolean(cookie.cookieDescription.configurableCookie.getData())>    				            
		        				            <div class="acciones">
		        				            	<label class="contenedor-radio">
		        				            		Acepto
		        				            		<input type="radio" name="check_cookies_${contador}[]" value="${cookie.cookieDescription.cookieName.getData()}-1" checked>
		        				            		<span class="checkmark"></span>
		        				            	</label>
		    									<label class="contenedor-radio">
		    										No acepto
		    										<input type="radio" name="check_cookies_${contador}[]" value="${cookie.cookieDescription.cookieName.getData()}-0">
		    										<span class="checkmark"></span>
		    									</label>
		        				            </div>
		        				            <#assign contador++>
		    				            </#if>
	    				            </div>
    				            </fieldset>
				            </#if>
						</#list>
						<#if infoCookies.getData()?has_content && infoCookies.getData()!="">
                            <div class="informacion-adicional">${infoCookies.getData()}</div>
                        </#if>
						<input type="submit" value="Guardar cambios">
					</form>
				</#if>
			</div>
		</div>
	</div>
</section>
<script>
	//Check if the user has the cookie saved
	window.onload = function() {
		var itemsCookies = getCookie("COOKIES_ACCEPTED");
		if (itemsCookies != "") {
			itemsCookies = itemsCookies.split("[COOKIE-SEPARATOR]");
		}
		var allRadio = document.querySelectorAll('input[type=radio]');
		for (var j = 0;  j < allRadio.length; j++) {
			for (var i = 0; i < itemsCookies.length; i++){
				if (allRadio[j].value == itemsCookies[i]) {
					allRadio[j].checked = true;
				}
			}
		}
	}
	//Create cookie with the preferences
	$("#formularioConfiguracionCookies").on('submit', function() {
		var selections = '';
		var cookie_separator = "";
		var allRadio = document.querySelectorAll('input[type=radio]');
		for (var j = 0;  j < allRadio.length; j++) {
			if (allRadio[j].checked) {
				selections += cookie_separator + allRadio[j].value;
	        	cookie_separator = "[COOKIE-SEPARATOR]";
			}
		}
	  	if (selections != ""){
	  		setCookie("COOKIES_ACCEPTED", selections, 30);
	  	}
	  	$(".banner-aceptacion-cookies").remove();
	});
</script>