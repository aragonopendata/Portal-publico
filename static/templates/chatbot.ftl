<#--${html.getData()}-->
<div class="chatbot-container">
	<div class="container">
		<div class="chatbot-close">
			<button class="border-0 bg-transparent" aria-label="Mostrar enlace a Covid19AragónBot" onclick="openChatbot()">
				<span class="chatbot-icon-open">
					<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-plus.svg" alt=""/>
				</span>
				<img src="${themeDisplay.getPathThemeImages()}/dga/covid/chatbot/boton-BOT-mini.png" alt=""/>
			</button>
		</div>
		<div class="chatbot-open" role="dialog" aria-labelledby="titCoviBot">
			<h2 id="titCoviBot" class="oculto">Covid19AragónBot</h2>
			<button class="chatbot-icon-close" onclick="closeChatbot()" aria-label="Ocultar enlace a Covid19AragónBot">
				<span>
					<img src="${themeDisplay.getPathThemeImages()}/dga/icons/icon-plus.svg" alt=""/>
				</span>
			</button>
			<p>
				<a href="https://t.me/COVID19AragonBot" target="_blank" title="Se abre en ventana nueva">
					<img src="${themeDisplay.getPathThemeImages()}/dga/covid/chatbot/boton-BOT-open.svg" alt="Covid19AragónBot"/>
					<span class="oculto">Antes de llamar, pincha aquí y pregunta a nuestro bot, tal vez te pueda ayudar<span>
				</a>
			</p>
		</div>
	</div>
</div>
<script>
	function closeChatbot(){
		$('.chatbot-open').css("display","none");
		$('.chatbot-close').css("display", "inline");
	}
	function openChatbot(){
		$('.chatbot-close').css("display","none");
		$('.chatbot-open').css("display", "inline");
		$('#titCoviBot').focus();
	}
</script>