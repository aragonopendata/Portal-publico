/**
 * Disallow saving absolute urls in dialog link
 * @param ev
 * @returns
 */
                  
CKEDITOR.on( 'dialogDefinition', function( ev ) {
    // Take the dialog name and its definition from the event data.
    var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
    // Check if the definition is from the dialog window you are interested in (the "Link" dialog window).
    if ( dialogName == 'link' ) {
        // Get a reference to the "Link Info" tab.
        var infoTab = dialogDefinition.getContents('info');
        // Set the default value for the URL field.
        var urlField = infoTab.get('url');
        urlField[ 'default' ] = '/-/enlace-a-paginas-aragon-es';
        var onOk = dialogDefinition.onOk;
        dialogDefinition.onOk = function(e) {
            var data = {}, editor = this.getParentEditor(),attributes = {};
            this.commitContent(data);
            if (data.type == 'url') {
            	var urlString = data.url.url;
			   if (urlString.startsWith("www.aragon.es") || urlString.startsWith("aragon.es") ){
					alert("La url debe ser relativa. Elimina la parte: 'www.aragon.es'.");
			        return false;
			   }
            }
            onOk && onOk.apply( this, e );
        };
    }
//    if ( dialogName == 'numberedListStyle' ) {
//    	var infoTab = dialogDefinition.getContents( 'info' );
//        var onOk = dialogDefinition.onOk;
//        
//        dialogDefinition.onOk = function(e) {
//        	var editor = this.getParentEditor(),
//			element = getListElement( editor, 'ol' );
//        	var start =element["$"]["start"];
//        	element && this.commitContent( element );
//        }
//    }
//	function getListElement( editor, listTag ) {
//		var range;
//		try {
//			range = editor.getSelection().getRanges()[ 0 ];
//		} catch ( e ) {
//			return null;
//		}
//
//		range.shrink( CKEDITOR.SHRINK_TEXT );
//		return editor.elementPath( range.getCommonAncestor() ).contains( listTag, 1 );
//	}
});
/**
 * Event fired when the CKEDITOR instance is completely created, fully initialized and ready for interaction.
 * @param event Fired event object
 */
ckEditor.on("instanceReady", function(event) {
	//Sets default language
	ckEditor.config.language = "es";
	ckEditor.config.defaultLanguage = "es";
	CKEDITOR.config.extraPlugins = 'scayt';
	CKEDITOR.config.disableNativeSpellChecker = false;
	//CkEditor DOM root element
	var editorId = "cke_" + event.editor.name;
	var editorName = event.editor.name;
	CKEDITOR.config.scayt_autoStartup = true;
	CKEDITOR.config.scayt_sLang = 'es_ES';
	//Add custom language selector
	CKEDITOR.addCustomLanguageComponent(editorId);
	//Add custom table component
	CKEDITOR.addCustomTableComponent(editorId);
	//Add custom check Accesibility component
	CKEDITOR.addCustomCheckAccessibilityComponent(editorId, editorName);
	//Add custom iframe component
	CKEDITOR.addCustomIframeComponent(editorId);
	//Remove resource button only if user has not rights for it 
	if (!TAGHasPermission){
		$('.cke_button__source').remove();
	}
});

/**
 * Adds a custom language selector component in the ckeditor toolbar
 * @param editorId Editor root element identifier
 */
CKEDITOR.addCustomLanguageComponent = function(editorId) {
	//Gets the editor root element
	var editor = document.getElementById(editorId);
	//Generates the button html
	var customLanguageListHTML = ''
		+	'<a id="' + editorId + '_custom_language_list" class="cke_button cke_button_off" title="' + Liferay.Language.get("aragon.ckeditor_hook.button_language.label") + '" tabindex="-1" role="button" onclick="CKEDITOR.showLanguageList(\'' + editorId + '\')">'
		+		'<span id="' + editorId + '_custom_language_list_icon" class="cke_button_icon" style="background-image:url(\'/o/frontend-editor-ckeditor-web/ckeditor/plugins/icons.png?t=I8QJ\');background-position:0 -1248px;background-size:auto;">&nbsp;</span>'
		+		'<span id="' + editorId + '_custom_language_list_arrow" class="cke_combo_arrow" style="margin-left: 4px;"></span>'
		+		'<span id="' + editorId + '_custom_buttom_label" class="cke_button_label" aria-hidden="false">' + Liferay.Language.get("aragon.ckeditor_hook.button_language.label") + '</span>'
		+	'</a>';
	//Appends the button in the ckeditor toolbar
	editor.lastElementChild.firstElementChild.lastElementChild.children[3].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(customLanguageListHTML), 'afterend');
	//Generates a separator element html
	var separatorHTML = '<span class="cke_toolbar_separator" role="separator"></span>';
	//Appends the separator after the language button
	editor.lastElementChild.firstElementChild.lastElementChild.children[3].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(separatorHTML), 'afterend');
}

/**
 * Shows the available lenguage options 
 * @param editorId Editor root element identifier
 */
CKEDITOR.showLanguageList = function(editorId) {
	//Gets the language button
	var languageButton = document.getElementById(editorId + "_custom_language_list");
	if (languageButton != null) {
		//Creates the languages list element if is not created
		var languageListElement = document.getElementById(editorId + "_language_list");
		if (languageListElement == null) {
			//Generates the list html
			var buttonStyles = 'padding: 12px 16px; text-decoration: none; display: block; cursor: pointer;'
			var languageList = ''
				+	'<div id="' + editorId + '_language_list" class="cke-language-list-content" style="display: block; position: absolute; height:200px; overflow: auto; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); background-color: white; z-index: 1; top: 33px;">'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'de-DE\')" style="' + buttonStyles + '">' + Liferay.Language.get("german") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'ar-SA\')" style="' + buttonStyles + '">' + Liferay.Language.get("arabic") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'bg-BG\')" style="' + buttonStyles + '">' + Liferay.Language.get("bulgarian") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'ca-ES\')" style="' + buttonStyles + '">' + Liferay.Language.get("language.ca") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'zh-CN\')" style="' + buttonStyles + '">' + Liferay.Language.get("chinese") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'en-US\')" style="' + buttonStyles + '">' + Liferay.Language.get("english") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'cast-ES\')" style="' + buttonStyles + '">' + Liferay.Language.get("spanish") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'fr-FR\')" style="' + buttonStyles + '">' + Liferay.Language.get("french") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'it-IT\')" style="' + buttonStyles + '">' + Liferay.Language.get("italian") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'ro-RO\')" style="' + buttonStyles + '">' + Liferay.Language.get("romanian") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'ru-RU\')" style="' + buttonStyles + '">' + Liferay.Language.get("russian") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'uk-UA\')" style="' + buttonStyles + '">' + Liferay.Language.get("ukrainian") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'ur-PK\')" style="' + buttonStyles + '">' + Liferay.Language.get("aragon.ckeditor_hook.select_language_urdu") + '</a>'
				+	'</div>';
			//Appends the list after the button in the editor toolbar
			languageButton.insertAdjacentElement('afterend', CKEDITOR.createElementFromHTML(languageList));
			let test = document.getElementById(editorId + '_language_list');
			test.addEventListener("mouseover", function( event ) {   
			  event.target.style.backgroundColor  = "#DCDEDA";
			}, false);
			test.addEventListener("mouseout", function( event ) {   
				  event.target.style.backgroundColor  = "#ffffff";
				}, false);

		} else {
			//Remove the languages list
			$('#' + editorId + '_language_list').remove();
		}
	}
};

/**
 * Encloses the selected text in the editor's iframe inside a span tag with the setted lang attribute
 * @param editorId Editor root element identifier
 * @param language Lang attribute value
 */
CKEDITOR.selectLanguage = function(editorId, language) {
	//Gets the editor iframe
	var editorIframe = $("#" + editorId + " iframe.cke_wysiwyg_frame")[0];
	if (editorIframe != null) {
		var editorIframeDocument = (editorIframe.contentDocument || editorIframe.contentWindow);
		//Gets the selected text in the iframe
		var selectedText = editorIframeDocument.getSelection().toString();
		//Encloses the selected text inside a span tag
		var formatedHTML = '<span lang="' + language + '">' + selectedText + '</span>';
		CKEDITOR.instances[editorId.substring(4)].insertHtml(formatedHTML);
		//Removes the languages list
		$('#' + editorId + '_language_list').remove();
	}
};

/**
 * Creates a custom check AAA button
 * @param editorId Editor root element identifier
 */
CKEDITOR.addCustomCheckAccessibilityComponent = function(editorId, editorName) {
	// Creating checkAAA button
	var editor = document.getElementById(editorId);
	//Generates the button html
	var customCheckAccessibilityButtonHTML = ''
		+	'<a id="' + editorId + '_custom_CheckAccessibility" class="cke_button cke_button_off" title="checkAccessibility" tabindex="-1" role="button" onclick="CKEDITOR.showPopUpCheckAccessibility(\'' + editorId + '\',\'' + editorName + '\')" data-toggle="modal" data-target="#exampleModalCenter">'
		+		'<span id="' + editorId + '_custom_checkAccessibility_icon" class="cke_button_icon" style="background-image:url(\'/o/frontend-editor-ckeditor-web/ckeditor/plugins/icons.png?t=I8QJ\');background-position:0 -528px;background-size:auto;">&nbsp;</span>'
		+		'<span id="' + editorId + '_custom_checkAccessibility_buttom_label" class="cke_button_label cke_button__source_label" aria-hidden="false">AAA</span>'
		+	'</a>';
	
	//Appends the button in the ckeditor toolbar
	editor.lastElementChild.firstElementChild.lastElementChild.children[4].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(customCheckAccessibilityButtonHTML), 'afterend');
	//Generates a separator element html
	var separatorHTML = '<span class="cke_toolbar_separator" role="separator"></span>';
	//Appends the separator after the checkAccesibility button
	editor.lastElementChild.firstElementChild.lastElementChild.children[4].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(separatorHTML), 'afterend');
}

/**
 * Shows the checkAccessibility popUp
 * @param editorId Editor root element identifier
 */
CKEDITOR.showPopUpCheckAccessibility = function(editorId, editorName) {
	//Gets the checkAccessibility button
	var checkAccessibilityButton = document.getElementById(editorId + "_custom_CheckAccessibility");
	if (checkAccessibilityButton != null) {
		//Creates the checkAccessibility menu element if is not created
		var checkAccessibilityMenu = document.getElementById(editorId + "_custom_checkAccessibility_popup");
		if (checkAccessibilityMenu == null) {
			//Generates the menu html
			var containerCss = 'white-space: normal; padding: 12px 16px; display: block; width:100%; height:300px; position: absolute; overflow: auto; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); background-color: white; top: 33px; left: 0px; z-index: 1;';
			var buttonCss = 'background-color: buttonface; padding: 2px 6px 3px; border-width: 2px; border-style: outset; border-color: buttonface; margin: 0 auto; margin-top: 10px; display: block;';
			var copyrightCss = 'font-size:8px; color:grey';
			var source = CKEDITOR.instances[editorName].document.getBody().getHtml();
			//var source = document.getElementById('source').value;     
			var checkAccessibilityPopUp = ''
				+	'<div id="' + editorId + '_custom_checkAccessibility_popup" class="cke-custom-checkAccessibility-popup ckeditor-custom-checkAccessibility" style="' + containerCss + '">'
				+		'<iframe id="evalCode" style="height:0;width:0;opacity:0" aria-hidden="true"></iframe>'
				+ 		'<div id="htmlcs" class="results"><div id="htmlcsres"></div></div>'
				+       '<p style="' + copyrightCss + '">Copyright 2019, PayPal under [the BSD license]</p>';
				+	'</div>'		
			checkAccessibilityButton.insertAdjacentElement('afterend', CKEDITOR.createElementFromHTML(checkAccessibilityPopUp));
	        var SCRIPT_REGEX = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi;
	        while (SCRIPT_REGEX.test(source)) {
	            source = source.replace(SCRIPT_REGEX, "");
	        } 
	        var elementFrame = document.getElementById('evalCode');
	        var element='';
	        if (elementFrame.contentDocument) {
	            element = elementFrame.contentDocument;
	        } else if (element.contentWindow) {
	            element = elementFrame.contentWindow.document;
	        }
	        element.body.innerHTML = "";
	        element.body.innerHTML ='<div id="__HTMLCS-source-wrap">' + source + '</div>';
            var options = {
                    standard :"WCAG2AAA"
                }
	        htmlcsTool(element, options);

            	
		} else {
			//Removes the checkAccessibility menu
			$('#' + editorId + '_custom_checkAccessibility_popup').remove();
		}
        var alerta = $('#htmlcsres').text();
       if (alerta == ""){
    	   $("#htmlcsres").append( "<p style='font-size: 15px;font-weight: 500;color: green, padding:20px;'>¡Enhorabuena! No hay errores.</p>" );
       }
	}
}; 
/**
 * Creates a custom iframe button only if user has permissions for it (tag replaced by CkEditorDymanicInclude file before sending to client side)
 * @param editorId Editor root element identifier
 */
CKEDITOR.addCustomIframeComponent = function(editorId) {
	// Creating iframe button only if user has rights for it (tag replaced by CkEditorDymanicInclude file before sending to client side)
	if (TAGHasPermission) {
		//Gets the editor root element
		var editor = document.getElementById(editorId);
		//Generates the button html
		var customIframeButtonHTML = ''
			+	'<a id="' + editorId + '_custom_iframe" class="cke_button cke_button_off" title="Iframe" tabindex="-1" role="button" onclick="CKEDITOR.showIframeMenu(\'' + editorId + '\')" data-toggle="modal" data-target="#exampleModalCenter">'
			+		'<span id="' + editorId + '_custom_iframe_icon" class="cke_button_icon" style="background-image:url(\'/o/frontend-editor-ckeditor-web/ckeditor/plugins/icons.png?t=I8QJ\');background-position:0 -1008px;background-size:auto;">&nbsp;</span>'
			+		'<span id="' + editorId + '_custom_iframe_buttom_label" class="cke_button_label" aria-hidden="false">' + Liferay.Language.get("aragon.ckeditor_hook.button_iframe") +'</span>'
			+	'</a>';
		
		//Appends the button in the ckeditor toolbar
		editor.lastElementChild.firstElementChild.lastElementChild.children[4].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(customIframeButtonHTML), 'afterend');
		//Generates a separator element html
		var separatorHTML = '<span class="cke_toolbar_separator" role="separator"></span>';
		//Appends the separator after the iframe button
		editor.lastElementChild.firstElementChild.lastElementChild.children[4].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(separatorHTML), 'afterend');
	}
}
/**
 * Shows the iframe options menu 
 * @param editorId Editor root element identifier
 */
CKEDITOR.showIframeMenu = function(editorId) {
	//Gets the iframe button
	var iframeButton = document.getElementById(editorId + "_custom_iframe");
	if (iframeButton != null) {
		//Creates the iframe menu element if is not created
		var iframeMenu = document.getElementById(editorId + "_custom_iframe_popup");
		if (iframeMenu == null) {
			//Generates the menu html
			var containerCss = 'padding: 12px 16px; display: block; position: absolute; overflow: auto; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); background-color: white; top: 33px; z-index: 1;';
			var inputCss = 'border-width: 2px; border-style: inset;';
			var buttonCss = 'background-color: buttonface; padding: 2px 6px 3px; border-width: 2px; border-style: outset; border-color: buttonface; margin: 0 auto; margin-top: 10px; display: block;';
			var iframePopUp = ''
			+	'<div id="' + editorId + '_custom_iframe_popup" class="cke-custom-iframe-popup ckeditor-custom-iframe" style="' + containerCss + '">'
			+		'<form autocomplete="off" class="ckeditor-custom-iframe">'
			+			'<div class="ckeditor-custom-iframe">'
			+				'<span class="ckeditor-custom-iframe">' + Liferay.Language.get("aragon.ckeditor_hook.menu_custom_iframe_url") +'</span>'
			+				'<input type="text" id="' + editorId + '_customIframeURL" name="customIframeURL" class="ckeditor-custom-iframe" style="' + inputCss + '"/>'
			+			'</div>'
			+			'<div class="ckeditor-custom-iframe">'
			+				'<button type="button" class="ckeditor-custom-iframe" style="' + buttonCss + '" onClick="CKEDITOR.createCustomIframe(\'' + editorId + '\')">' + Liferay.Language.get("aragon.ckeditor_hook.button_create_iframe") + '</button>'
			+			'</div>'
			+		'</form>'
			+	'</div>';
			//Appends the menu after the iframe button in the editor toolbar
			iframeButton.insertAdjacentElement('afterend', CKEDITOR.createElementFromHTML(iframePopUp));
			//Focus the menu input
			document.getElementById(editorId + '_customIframeURL').focus();
		} else {
			//Removes the iframe menu
			$('#' + editorId + '_custom_iframe_popup').remove();
		}
	}
};

/**
 * Adds an iframe tag with the selected url
 * @param editorId Editor root element identifier
 */
CKEDITOR.createCustomIframe = function(editorId) {
	//Gets the iframe URL input
	var customIframeInput = document.getElementById(editorId + "_customIframeURL");
	if (customIframeInput != null) {
		//Gets the selected iframe URL
		var customIframeURL = customIframeInput.value;		
		// Get selected text
		var editorIframe = $("#" + editorId + " iframe.cke_wysiwyg_frame")[0];
		if (editorIframe != null) {
			var editorIframeDocument = (editorIframe.contentDocument || editorIframe.contentWindow);
			//Gets the selected text in the iframe
			var selectedText = editorIframeDocument.getSelection().toString();
			//Adds the iframe tag with the selected url
			var formatedHTML = selectedText + '<div style="position: relative; padding-bottom: 56.25%; height: 0; overflow: hidden;"><iframe src="' + customIframeURL + '" style="position: absolute; top:0; left: 0; width: 100%; height: 100%;"/></div>';
			CKEDITOR.instances[editorId.substring(4)].insertHtml(formatedHTML);
			//Removes the iframe menu
			$('#' + editorId + '_custom_iframe_popup').remove();
		}
	}
};
/**
 * Adds a custom table component in the ckeditor toolbar
 * @param editorId Editor root element identifier
 */
CKEDITOR.addCustomTableComponent = function(editorId) {
	//Gets the editor root element
	var editor = document.getElementById(editorId);
	//Generates the button html
	var customTableButtonHTML = ''
		+	'<a id="' + editorId + '_custom_table" class="cke_button cke_button_off" title="Table" tabindex="-1" role="button" onclick="CKEDITOR.showTableMenu(\'' + editorId + '\')">'
		+		'<span id="cke_custom_table_icon" class="cke_button_icon" style="background-image:url(\'/o/frontend-editor-ckeditor-web/ckeditor/plugins/icons.png?t=I8QJ\');background-position:0 -2064px;background-size:auto;">&nbsp;</span>'
		+		'<span id="cke_custom_table_buttom_label" class="cke_button_label" aria-hidden="false">' + Liferay.Language.get("table") +'</span>'
		+	'</a>';
	//Appends the button in the ckeditor toolbar
	editor.lastElementChild.firstElementChild.lastElementChild.children[4].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(customTableButtonHTML), 'afterend');
	//Generates a separator element html
	var separatorHTML = '<span class="cke_toolbar_separator" role="separator"></span>';
	//Appends the separator after the language button
	editor.lastElementChild.firstElementChild.lastElementChild.children[4].firstElementChild.appendChild(CKEDITOR.createElementFromHTML(separatorHTML), 'afterend');
}

/**
 * Shows the custom table menu popup 
 * @param editorId Editor root element identifier
 */
CKEDITOR.showTableMenu = function(editorId) {
	//Gets the table button
	var tableButton = document.getElementById(editorId + "_custom_table");
	if (tableButton != null) {
		//Creates the table menu if is not created
		var tableMenuElement = document.getElementById(editorId + "_custom_table_popup");
		if (tableMenuElement == null) {
			//Generates the table menu html
			var containerCss = "padding: 12px 16px; display: block; position: absolute; overflow: auto; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); background-color: white; top: 33px; z-index: 1;";
			var inputCss = 'border-style: inset; background-color: #fff; border: 1px solid #bcbcbc; padding: 4px 6px; outline: 0; width: 60%; width: 95%; box-sizing: border-box; border-radius: 2px; min-height: 28px; margin-left: 1px;';
			var buttonCss = 'background-color: buttonface; padding: 2px 6px 3px; border-width: 2px; border-style: outset; border-color: buttonface; margin: 0 auto; margin-top: 10px; display: block; color: #fff; background: #09863e; border: 1px solid #09863e;';
			var tablePopUp = ''
				+	'<div id="' + editorId + '_custom_table_popup" class="cke-custom-table-popup ckeditor-custom-table" style="' + containerCss + '">'
				+		'<form autocomplete="off" class="ckeditor-custom-table">'
				+			'<div class="ckeditor-custom-table">'
				+				'<span class="ckeditor-custom-table">' + Liferay.Language.get("aragon.ckeditor_hook.customtable_rows_tittle") + '</span>'
				+				'<br>'
				+				'<input type="number" id="' + editorId + '_customTableRows" name="' + editorId + 'customTableRows" class="ckeditor-custom-iframe" style="' + inputCss + '" onClick="this.focus();" value="2" />'
				+			'</div>'
				+			'<br>'
				+			'<div class="ckeditor-custom-table">'
				+				'<span class="ckeditor-custom-table">' + Liferay.Language.get("columns") +': </span>'
				+				'<br>'
				+				'<input type="number" id="' + editorId + '_customTableColumns" name="' + editorId + 'customTableColumns" class="ckeditor-custom-iframe" style="' + inputCss + '" onClick="this.focus();" value="2" />'
				+			'</div>'
				+			'<div class="ckeditor-custom-table">'
				+				'<button type="button" class="ckeditor-custom-table" style="' + buttonCss + '" onClick="CKEDITOR.createCustomTable(\'' + editorId + '\')" onmouseover="this.style.backgroundColor = \'darkSeaGreen\';" onmouseout="this.style.backgroundColor = \'green\';">' + Liferay.Language.get("aragon.ckeditor_hook.buttom_create_table_tittle") + '</button>'
				+			'</div>'
				+		'</form>'
				+	'</div>';
			//Appends the list after the button in the editor toolbar
			tableButton.insertAdjacentElement('afterend', CKEDITOR.createElementFromHTML(tablePopUp));
		} else {
			//Removes the table menu
			$('#' + editorId + '_custom_table_popup').remove();
		}
	}
};

/**
 * Adds a table tag with the selected rows and columns
 * @param editorId Editor root element identifier
 */
CKEDITOR.createCustomTable = function(editorId) {
	//Gets the rows and columns inputs
	var customTableRowsInput = document.getElementById(editorId + "_customTableRows");
	var customTableColumnsInput =document.getElementById(editorId + "_customTableColumns");
	if (customTableRowsInput != null && customTableColumnsInput != null) {
		//Gets the rows and columns values
		var customTableRows = customTableRowsInput.value;
		var customTableColumns = customTableColumnsInput.value;
		//Validate the rows value
		if (customTableRows == null || customTableRows < 2) {
			customTableRows = 2;
		} else {
			customTableRows = parseInt(customTableRows, "10");
		}
		//Validate the columns value
		if (customTableColumns == null || customTableColumns < 2) {
			customTableColumns = 2;
		} else {
			customTableColumns = parseInt(customTableColumns, "10");
		}
		//Gets the editor iframe
		var editorIframe = $("#" + editorId + " iframe.cke_wysiwyg_frame")[0];
		if (editorIframe != null) {
			var editorIframeDocument = (editorIframe.contentDocument || editorIframe.contentWindow);
			//Gets the selected text in the iframe
			var selectedText = editorIframeDocument.getSelection().toString();
			//Generates the table html code
			var tableId = parseInt(Math.floor(Math.random() * 10000), "10");
			//Table header
			var tableHeader = '';
			for (var i = 1; i <= customTableColumns; i++) {
				tableHeader += '<th>' + Liferay.Language.get("column") +'-' + i + '</th>';
			}
			//Table body
			var tableBody = '';
			for (var i = 0; i < customTableRows; i++) {
				tableBody += '<tr>';
				for (var j = 0; j < customTableColumns; j++) {
					tableBody += '<td></td>';
				}
				tableBody += '</tr>';
			}
			//Adds the table tag in the editor content
			var generatedHTML = selectedText
			+	'<div class=“table-layer”>'
			+		'<h2>' + Liferay.Language.get("job-title") +'</h2>'
			+		'<p id="custom-table-' + tableId + '">' + Liferay.Language.get("meta-description")  +'</p>'
			+		'<table aria-describedby="custom-table-' + tableId + '">'
			+			'<tbody>'
			+				'<tr>'
			+					tableHeader
			+				'</tr>'
			+				tableBody
			+			'</tbody>'
			+		'</table>'
			+	'</div>';
			CKEDITOR.instances[editorId.substring(4)].insertHtml(generatedHTML);
			//Removes the table menu
			$('#' + editorId + '_custom_table_popup').remove();
		}
	}
};

/**
 * Returns a DOM element from the given HTML fragment
 * @param html HTML fragment
 * @returns A DOM element with the given HTML
 */
CKEDITOR.createElementFromHTML = function (html) {
	var div = document.createElement("div");
	div.innerHTML = html.trim();
	return div.firstChild;
};

