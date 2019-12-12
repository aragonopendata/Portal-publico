<%@ include file="/init.jsp" %>

<section>
	<div class="list-news-module row" id="contenedor-ultimas-visitas"></div>
</section>
<script>
	$(document).ready(function() {
		var lastVisitedPagesCookieName = "LAST_VISITED_PAGES";
		var lastVisitedPagesCookieValue = "";
	    //Obtains the cookie value
		var decodedCookie = decodeURIComponent(document.cookie);
	    var cookiesArray = decodedCookie.split(';');
	    for (var i = 0; i < cookiesArray.length; i++) {
	        var cookieEntry = cookiesArray[i];
	        while (cookieEntry.charAt(0) == ' ') {
	            cookieEntry = cookieEntry.substring(1);
	        }
	        if (cookieEntry.indexOf(lastVisitedPagesCookieName + "=") == 0) {
	        	lastVisitedPagesCookieValue = cookieEntry.substring((lastVisitedPagesCookieName + "=").length, cookieEntry.length);
	        }
	    }
	    //Add entries list to the view
		if (lastVisitedPagesCookieValue != "") {
			var visitedPagesList = lastVisitedPagesCookieValue.split('|');
			var cookieSeparator = "[COOKIE_SEPARATOR]";
			for (var i = 0; i < visitedPagesList.length - (visitedPagesList.length - <%= selectedCount %>); i++) {
				var visitedPage = visitedPagesList[i];
				if (visitedPage != null) {
					visitedPage = visitedPage.trim();
					var separator = visitedPage.indexOf(cookieSeparator);
		            var visitedPageTitle = visitedPage.substring(0, separator);
		            var visitedPageURL = visitedPage.substring(separator + cookieSeparator.length, visitedPage.length);
		            if (visitedPageURL.length > 0 && visitedPageTitle.length > 0) {
			            if (document.getElementById('ultimas-visitas') == null) {
			            	document.getElementById('contenedor-ultimas-visitas').innerHTML = '<ul id="ultimas-visitas" class="listado collapse"></ul>';
			            }
	    				document.getElementById('ultimas-visitas').innerHTML += "<li class='col-12 listado__item'><a href='" + visitedPageURL + "' class='link'><span class='title'>" + visitedPageTitle + "</span></a></li>";
	    			}
				}
			}
		}
	});
</script>
