/**
 * Event fired when the CKEDITOR instance is completely created, fully initialized and ready for interaction.
 * @param event Fired event object
 */
ckEditor.on("instanceReady", function(event) {
	//Sets default language
	ckEditor.config.language = "es";
	ckEditor.config.defaultLanguage = "es";
	//CkEditor DOM root element
	var editorId = "cke_" + event.editor.name;
	//Add custom language selector
	CKEDITOR.addCustomLanguageComponent(editorId);
	//Add iframe component
	CKEDITOR.addCustomIframeComponent(editorId);
	//Add custom table component
	CKEDITOR.addCustomTableComponent(editorId);
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
				+	'<div id="' + editorId + '_language_list" class="cke-language-list-content" style="display: block; position: absolute; overflow: auto; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); background-color: white; z-index: 1; top: 33px;">'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'ca-ES\')" style="' + buttonStyles + '">' + Liferay.Language.get("language.ca") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'eu-ES\')" style="' + buttonStyles + '">' + Liferay.Language.get("aragon.ckeditor_hook.select_language_euskera") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'gl-ES\')" style="' + buttonStyles + '">' + Liferay.Language.get("aragon.ckeditor_hook.select_language_gallego") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'en-US\')" style="' + buttonStyles + '">' + Liferay.Language.get("english") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'fr-FR\')" style="' + buttonStyles + '">' + Liferay.Language.get("french") + '</a>'
				+		'<a role="button" onclick="CKEDITOR.selectLanguage(\'' + editorId + '\', \'de-DE\')" style="' + buttonStyles + '">' + Liferay.Language.get("german") + '</a>'
				+	'</div>';
			//Appends the list after the button in the editor toolbar
			languageButton.insertAdjacentElement('afterend', CKEDITOR.createElementFromHTML(languageList));
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
