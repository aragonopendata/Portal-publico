<%@ include file="init.jsp" %>

<script src="<%=themeDisplay.getPathThemeRoot()%>/js/media-player.js"></script>
<script src="<%=themeDisplay.getPathThemeRoot()%>/js/demo.js"></script>
<svg style="display:none">
	<symbol id="symbol-play" viewBox="0 0 32 32">
		<path d="M4 0l24 16L4 32"/>
	</symbol>
	<symbol id="symbol-pause" viewBox="0 0 32 32">
		<path d="M4 0h9v32H4M28 0h-9v32h9"/>
	</symbol>
	<symbol id="symbol-mute" viewBox="0 0 32 32">
		<path d="M0 11h6l10-8.5v27L6 21H0M22 6l10 10-10 10"/>
	</symbol>
	<symbol id="symbol-unmute" viewBox="0 0 32 32">
		<path d="M0 11h6l10-8.5v27L6 21H0"/>
	</symbol>
	<symbol id="symbol-download" viewBox="0 0 32 32">
		<path d="M20 2v12h4l-8 10-8-10h4V2M0 21h5v6h22v-6h5v11H0"/>
	</symbol>
</svg>
<section id="<portlet:namespace/>vozMe">
    <div class="container u-container-mobile-0">
        <div class="u-padding-top-2 u-padding-bottom-5">
            <div class="speaker-box">
                <a id="<portlet:namespace/>mp3" href="javascript:void(0);" title="<liferay-ui:message key='aragon.vozme.message-listen'/>" onclick="getUrlMp3()">
                    <div class="speaker"><p class="oculto"><liferay-ui:message key='aragon.vozme.message-listen-audio'/></p></div>
                </a>
                <div class="<portlet:namespace/>spinner-ajax text-center" style="display:none;">
                	<img src="<%=themeDisplay.getPathThemeImages()%>/dga/icons/icon-spinner-gob-aragon.gif" alt="<liferay-ui:message key='aragon.vozme.message-spinner'/>" style="width:35px;height:35px;">
                </div>
                <p class= "<portlet:namespace/>spinner-ajax text-center" style="display:none;"><liferay-ui:message key='aragon.vozme.estimated-message'/></p>
				<div id ="<portlet:namespace/>audio" class="player text-center" style="display:none;">
					<div class="reproductor-accesible">
						<%
						String audioDataOptions = "" + 
								"{" +
									"prefix: 'media'," +
								  	"show: {" +
								    "play: true," +
								    "mute: true," +
								    "volume: true," +
								    "currentTime: true," +
								    "remainingTime: true," +
								    "time: true," +
								    "download: true," +
								    "fullscreen: false"  +
								  "}," +
								  "lang: {" +
								    "play: 'play'," +
								    "pause: 'pause'," +
								    "mute: 'mute'," +
								    "unmute: 'unmute'," +
								    "volume: 'volume'," +
								    "currentTime: 'current time'," +
								    "remainingTime: 'remaining time'," +
								    "download: 'download'," +
								  "}," +
								  "svgs: {" +
								    "play: '#symbol-play'," +
								    "pause: '#symbol-pause'," +
								    "mute: '#symbol-mute'," +
								    "unmute: '#symbol-unmute'," +
								    "volume: '#symbol-volume'," +
								    "currentTime: '#symbol-currentTime'," +
								    "remainingTime: '#symbol-remainingTime'," +
								    "download: '#symbol-download'," +
								  "}," +
								  "timeDir: 'ltr'," +
								  "volumeDir: 'ltr'" +
								"}";
						%>
						<audio autoplay playsinline class="media-player" id ="<portlet:namespace/>audio-mp3" src="" data-options="<%=audioDataOptions%>" controls>
					</div>
			  	</div>
            </div>
        </div>
    </div>
</section>

<portlet:resourceURL var="listenToResourceURL">
	<portlet:param name="action" value="fromtexttospeech"/>
</portlet:resourceURL>
<script type="text/javascript">
function getUrlMp3(){
	document.getElementById("<portlet:namespace/>mp3").style.display = "none";
	document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[0].style.display = "block";
	document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[1].style.display = "block";
	var lang = "<%=locale%>";
	var speed = 0;
	var text = "";
	var classReadSpeakerOrigin = document.getElementsByClassName("readSpeakerOrigin");
	if (classReadSpeakerOrigin != null){
		for (i = 0; i < classReadSpeakerOrigin.length; i++) {
			text += classReadSpeakerOrigin[i].textContent + ".";
		}
	}
	AUI().use('aui-io-request', function(A){
		A.io.request('<%=listenToResourceURL.toString()%>', {
			method: 'post',
			dataType: 'json',
			data:{
				<portlet:namespace/>lang: lang,
				<portlet:namespace/>speed: speed,
				<portlet:namespace/>text: text
			},
			on: {
				success: function(data) {
					document.getElementById("<portlet:namespace/>mp3").style.display = "none";
					document.getElementById("<portlet:namespace/>audio").style.display = "block";
					var responseData = this.get('responseData');
					if (responseData != null){
						var status = responseData.status;
						if (status == "success") {
							var url = responseData.url;
							document.getElementById("<portlet:namespace/>mp3").href = url;
							$("#<portlet:namespace/>audio-mp3").attr("src", url);
							document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[0].style.display = "none";
							document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[1].style.display = "none";
							document.getElementById("<portlet:namespace/>vozMe").style.display = "block";
						}
					}else {
						document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[0].style.display = "none";
						document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[1].style.display = "block";
						document.getElementsByClassName("<portlet:namespace/>spinner-ajax")[1].innerHTML = "No se ha podido establecer la conexión";
						document.getElementById("<portlet:namespace/>audio").style.display = "none";
					}
				},
				error: function(data) {
					console.log("error");
				}
			}
		});
	});
}

</script>