<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="es.aragon.base.journal_web_overridde_fragment.util.JournalWebOverrideFragmentUtil"%>

<%
JournalWebOverrideFragmentUtil journalWebOverrideFragmentUtil = new JournalWebOverrideFragmentUtil();
%>

<%-- Hide components to non admin users --%>
<c:if test="<%=!permissionChecker.isOmniadmin()%>">
	<style>
		#portlet_com_liferay_journal_web_portlet_JournalPortlet #displayPage  {
			display: none;
			visibility: hidden;
		}
		#portlet_com_liferay_journal_web_portlet_JournalPortlet #smallImage  {
			display: none;
			visibility: hidden;
		}
		#portlet_com_liferay_journal_web_portlet_JournalPortlet #relatedAssets  {
			display: none;
			visibility: hidden;
		}
		#portlet_com_liferay_journal_web_portlet_JournalPortlet #permissions  {
			display: none;
			visibility: hidden;
		}
	</style>
	<script>
		//Ocultar botones de visibilidad
		$( document ).ready(function() {
			$("#<portlet:namespace/>assetPriority").parent().hide();
		});
	</script>
</c:if>

<%-- Hide visibility vocabularies --%>
<c:if test="<%=!permissionChecker.isOmniadmin() && !permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())%>">
	<%
	AssetVocabulary visibilidad = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Visibilidad");
	long visibilidadId = 0;
	if (visibilidad != null) {
		visibilidadId = visibilidad.getVocabularyId();
	}
	%>
	<script>
		$( document ).ready(function() {
			/*Disable friendlyURL form input*/
			$("#<portlet:namespace/>friendlyURL").enable(false);
			
			if (<%=visibilidadId != 0%>) {
				var visibilidad = $("#_com_liferay_journal_web_portlet_JournalPortlet_assetCategoriesLabel_" + "<%=visibilidadId %>")[0];
				if(visibilidad != null) {
					$(visibilidad).parent().hide();
				}
			}
		});
	</script>
</c:if>

<%--Limit image descriptions to 150 characters--%>
<script>
	$(document).ready(function() {
		$( ".form-group input[id $='Alt']" ).prop('maxLength', 150);
	});
	//Keyboard event function to limit "description" field of content-final to 150 characters
	$( ".form-group input[id $='Alt']").keydown(function() {
		//Save number characters from input
		num_caracteres = $(this).val().length;
		//Save id 
		var id= $(this).attr('id');
		//Get max characters ( limit 150)
		var maxLength = $(this).attr('maxLength');
		if (num_caracteres>= maxLength){
			//If it does not exist id from text html with alert, we create it
			if(!$('#'+id+'_length').length){
				// create div and add id (id+_length) and class 
				var div = document.createElement("div");
				div.id=id+'_length';
				div.setAttribute("class", "form-validator-stack help-block");
				// create second div and add text, class and role
				var div2=document.createElement("div");
				div2.innerHTML="Máximo 150 carácteres.";
				div2.setAttribute("class", "required");
				div2.setAttribute("role", "alert");
				// the second div is the son of the first
				div.appendChild(div2);
				// create input sibling: firts div
				$( this).after(div);
			}
		}
		//If exist text id, remove text id
		if((num_caracteres<maxLength) && ($('#'+id+'_length').length)){
			$('#'+id+'_length').remove();
		}
	});
</script>

<!-- Hide text editor in summary field -->
<style>
	.ae-ui .ae-toolbars {
		display: none;
	}
</style>


<!--Hide web/guest in friendly URL -->
<script>
	$(document).ready(function() {
		$("#_com_liferay_journal_web_portlet_JournalPortlet_friendlyURLInputAddon").text(function(index, text) {
		    return text.replace("/web/guest", "");
		});
	});

</script>

<!--Add new button to copy the friendly url to clipboard-->
<script>
	$(document).ready(function(){
		//Parent Elements for copy button and hidden element
		var parentCopyButton=document.getElementById( "_com_liferay_journal_web_portlet_JournalPortlet_friendlyURLBoundingBox"); 
		var parentHiddenElement=document.getElementById("_com_liferay_journal_web_portlet_JournalPortlet_friendlyURLBoundingBox");//input a partir del cual a�adiremos el input oculto
		
		//HTML of the copy button
		var elementsButton='<div class="input-group-item input-group-item-shrink input-group-prepend"><button id="_com_liferay_journal_web_portlet_JournalPortlet_copyFriendlyUrl" class="btn btn-secondary" type="button"><svg class="lexicon-icon lexicon-icon-paste" viewBox="0 0 512 512"><g><path class="lexicon-icon-body" fill="none" d="M412,128h-60V68c0-37.4-30.6-68-68-68H100C62.6,0,32,30.6,32,68v248c0,37.4,30.6,68,68,68h60v60  c0,37.4,30.6,68,68,68h184c37.4,0,68-30.6,68-68V196C480,158.6,449.4,128,412,128z"></path><path class="lexicon-icon-outline" d="M412,128h-60V68c0-37.4-30.6-68-68-68H100C62.6,0,32,30.6,32,68v248c0,37.4,30.6,68,68,68h60v60c0,37.4,30.6,68,68,68h184  c37.4,0,68-30.6,68-68V196C480,158.6,449.4,128,412,128z M160,196v124H96V64h192v64h-60C190.6,128,160,158.6,160,196z M416,448H224  V192h192V448z"></path></g></svg></button></div>';
		
		//New button created
		var newButtonId=document.getElementById("_com_liferay_journal_web_portlet_JournalPortlet_copyFriendlyUrl");
		
		//New element to save value of concat url
		var hiddenElement='<input type="text" name="urlConcatenada" id="concatFriendlyURL" style="position:absolute;z-index:-1;">';
		var urlElement=document.getElementById("concatFriendlyURL");
	
		//conditional for copyButton and hidden element
		if(newButtonId==null && urlElement==null && parentCopyButton != null){
			
			//add elements to html (copyButton and hidden element)
			parentCopyButton.insertAdjacentElement('afterBegin', createElementFromHTML(elementsButton));
			parentHiddenElement.parentElement.insertAdjacentElement('afterBegin', createElementFromHTML(hiddenElement));
			
			var copyButton= document.getElementById('_com_liferay_journal_web_portlet_JournalPortlet_copyFriendlyUrl');
			copyButton.addEventListener('click', function(event) {
				
			//First and second part of URL	
			var partOneURL= document.getElementById("_com_liferay_journal_web_portlet_JournalPortlet_friendlyURLInputAddon").innerText;
			var partTwoURL=document.getElementById("_com_liferay_journal_web_portlet_JournalPortlet_friendlyURL").value;
				
			var fullURL=partOneURL+partTwoURL;
			//Assign the complete URL to the input hidden
			document.getElementById("concatFriendlyURL").value=fullURL;
	
			var seleccion=document.getElementById("concatFriendlyURL");
			seleccion.select();
	
				  try {
				    var successful = document.execCommand("copy");
				    var msg = successful ? 'successful' : 'unsuccessful';
				    console.log('Copying text command was ' + msg);
				  } catch (err) {
				    console.log('Oops, unable to copy');
				  }
				});
		}
	});
	
	createElementFromHTML = function (html) {
		var div = document.createElement("div");
		div.innerHTML = html.trim();
		return div.firstChild;
	};
</script>






