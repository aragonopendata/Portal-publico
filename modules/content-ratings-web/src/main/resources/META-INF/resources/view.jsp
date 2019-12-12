<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="es.aragon.base.content_ratings.util.ContentRatingsUtil"%>

<%@ include file="init.jsp" %>

<%
long classNameId = ClassNameLocalServiceUtil.fetchClassName(JournalArticle.class.getName()).getClassNameId();
long classPK = ContentRatingsUtil.getJournalArticleClassPKByUrl(themeDisplay.getScopeGroupId(), themeDisplay.getURLCurrent());
if (classPK == 0) {
	classNameId = ClassNameLocalServiceUtil.fetchClassName(Layout.class.getName()).getClassNameId();
	classPK = themeDisplay.getLayout().getPlid();
}
%>

<section class="u-padding-top-3 u-padding-bottom-3">
	<div class="valoration-module">
		<div class="container">
			<div class="valoration-module__box">
				<h2 class="labelVal">
					<liferay-ui:message key="content-ratings-web.useful-content"/>
				</h2>
				<ul id="contenedorEstrellas" class="listado-stars">
					<%
					double requiredScore;
					for (int i = 1; i <= 5; i++) {
					%>
						<li id="estrella<%=i%>" class='listado-stars__star'>
							<a href="javascript:rate(<%=i%>)" class="link">
								<img src='<%=themeDisplay.getPathThemeImages() + "/dga/icons/icon-star-empty.svg"%>' alt='<%=LanguageUtil.format(request, "content-ratings-web.rate", new Object[]{i})%>'/>
							</a>
						</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</div>
</section>

<portlet:resourceURL var="resourceURL"/>
<script type="text/javascript" charset="UTF-8">
	function rate(scoreval){
		AUI().use('aui-io-request', function(A){
			A.io.request('<%=resourceURL.toString()%>', {
				method: 'post',
				dataType: 'json',
				data: {
					<portlet:namespace />scoreval: scoreval,
					<portlet:namespace />classNameId: "<%=classNameId%>",
					<portlet:namespace />classPK: <%=classPK%>,
				},
				on: {
					success: function() {
						var responseData = this.get('responseData');
						if (responseData.success == "true") {
							updateRatingsCookie('<%=classNameId%>', <%=classPK%>, scoreval);
							refreshRatings();
						} else {
							alert(responseData.msg);
						}
					}
				}
			});
		});
	}
	
	function getContentsRatingsCookieValue() {
		var contentsRatingsCookieName = "CONTENTS_RATINGS";
		var contentsRatingsCookieValue = "";
		var decodedCookie = decodeURIComponent(document.cookie);
		var cookiesArray = decodedCookie.split(';');
		for (var i = 0; i < cookiesArray.length; i++) {
			var cookieEntry = cookiesArray[i];
			while (cookieEntry.charAt(0) == ' ') {
				cookieEntry = cookieEntry.substring(1);
			}
			if (cookieEntry.indexOf(contentsRatingsCookieName + "=") == 0) {
				contentsRatingsCookieValue = cookieEntry.substring((contentsRatingsCookieName + "=").length, cookieEntry.length);
			}
		}
		return contentsRatingsCookieValue;
	}
	
	function refreshRatings() {
		//Obtener valoracion del usuario desde cookies
		var userScore = 0;
		var contentsRatingsCookieValue = getContentsRatingsCookieValue();
	    if (contentsRatingsCookieValue != "") {
	    	var contentsRatingsList = contentsRatingsCookieValue.split('|');
	    	for (var i = 0; i < contentsRatingsList.length; i++) {
	    		var contentRating = contentsRatingsList[i];
	    		var namePkSeparator = contentRating.indexOf("_");
				var keyValueSeparator = contentRating.indexOf(":");
				var classNameId = contentRating.substring(0, namePkSeparator);
	            var classPK = contentRating.substring(namePkSeparator + 1, keyValueSeparator);
	            var contentScore = contentRating.substring(keyValueSeparator + 1);
	            if (classNameId == <%=classNameId%> && classPK == <%=classPK%>) {
					userScore = contentScore;
				}
	    	}
			//Activar/desactivar estrellas en funcion de la puntuacion otorgada por el usuario
			if (userScore > 0) { 
				for (var i = 1; i <= 5; i++) {
					if (userScore >= i) {
						$("#estrella" + i).addClass("active");
						$("#estrella" + i + " a img").attr('src','<%=themeDisplay.getPathThemeImages() + "/dga/icons/icon-star-full.svg"%>');
					} else {
						$("#estrella" + i).removeClass("active");
					}
					if (i == 1) {
						var htmlEstrella = $("#estrella" + i + " a").html();
						$("#estrella" + i).html("<div class='link'></div>");
						$("#estrella" + i + " div").html(htmlEstrella);						
						$("#estrella" + i + " div img").attr('alt', "Has puntuado el contenido con un " + userScore + " sobre 5.");
					} else {
						var htmlEstrella = $("#estrella" + i + " a").html();
						$("#estrella" + i).html("<div class='link'></div>");
						$("#estrella" + i + " div").html(htmlEstrella);
						$("#estrella" + i + " div img").attr('alt', "");
					}
				}
				$(".valoration-module").addClass("disabled");
			}
	    }
	}
	
	function updateRatingsCookie(classNameId, classPK, ratingValue) {
		var contentsRatingsCookieValue = getContentsRatingsCookieValue();
		//Actualizar cookie
		if (contentsRatingsCookieValue != "") {
			contentsRatingsCookieValue = contentsRatingsCookieValue + "|" + classNameId + "_" + classPK + ":" + ratingValue;
		} 
		//Registrar cookie
		else {
			contentsRatingsCookieValue = classNameId + "_" + classPK + ":" + ratingValue;
		}
	    var d = new Date();
	    d.setTime(d.getTime() + (365 * 24 * 60 * 60 * 1000));
	    var expires = "expires=" + d.toGMTString();
	    document.cookie = "CONTENTS_RATINGS" + "=" + contentsRatingsCookieValue + ";" + expires + ";path=/";
	}
	
	$(document).ready(function() {
		refreshRatings();
	});
</script>