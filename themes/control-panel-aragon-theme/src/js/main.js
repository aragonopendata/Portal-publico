
/* Used to control CKEditor's Language component. If the component is showed and the user clicks outside, this function hide's showed popup. */ 
$( document ).ready(function() {
    window.onclick = function(event) {
        
        var languageList = document.getElementById("cke_language_list");
        if (languageList != null) {
        	if (event.target.id != "cke_custom_language_list" && event.target.id != "cke_custom_language_list_icon" && event.target.id != "cke_custom_language_list_arrow") {
                languageList.parentNode.removeChild(languageList);
            }
        }
        
        var customCKEditorIframePopUp = $("#cke_custom_iframe_popup");
        if (customCKEditorIframePopUp != null) {
        	
        	var clickedElement = event.target;
            if (clickedElement.id != "cke_custom_iframe" && clickedElement.id != "cke_custom_iframe_icon" && !clickedElement.classList.contains("ckeditor-custom-iframe")) {
            	customCKEditorIframePopUp.remove();
            }
        }
        
        var customCKEditorTablePopUp = $("#cke_custom_table_popup");
        if (customCKEditorTablePopUp != null) {
        	
        	var clickedElement = event.target;
            if (clickedElement.id != "ckeditor-custom-table" && clickedElement.id != "cke_custom_table_icon" && !clickedElement.classList.contains("ckeditor-custom-table")) {
            	customCKEditorTablePopUp.remove();
            }
        }
    }
});

