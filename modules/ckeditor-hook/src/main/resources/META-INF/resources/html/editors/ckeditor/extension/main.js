//https://github.com/squizlabs/HTML_CodeSniffer.git
function htmlcsTool(ifrDoc, options){
    var standard = options.standard || 'WCAG2A';
    var source = ifrDoc.body;
    // console.log(options.standard, source)
    HTMLCS.process(standard, source, function() {
        var msgs = HTMLCS.getMessages();
        var content = [];
        var heading = "";
        var type = '';
        var outerHTML = '';
        
        var htmlcsDiv = document.getElementById('htmlcsres');
        var htmlcsres = document.createElement("div");
        htmlcsres.setAttribute("id", "htmlcsres");
        htmlcsDiv.innerHTML='';  

        try {
            var principles = {
                    'Principle1': 'Perceivable',
                    'Principle2': 'Operable',
                    'Principle3': 'Understandable',
                    'Principle4': 'Robust'
                };

            if (msgs.length === 0) {
                content.push({'message':'No violations found'});
                return;
            }

            var errors   = 0;
            var warnings = 0;
            var notices  = 0;
            var count=1;

            for (var i = 0; i < msgs.length; i++) {
                var msg = msgs[i];
                if (msg.type != HTMLCS.NOTICE){
                    var temp_obj = {};

                    switch (msg.type) {
                        case HTMLCS.ERROR:
                            type = 'Error';
                            break;
                        case HTMLCS.WARNING:
                            type = 'Warning';
                            warnings++;
                        break;
                        case HTMLCS.NOTICE:
                            type = 'Notice';
                            notices++;
                        break;
                        default:
                            type = 'Unknown:' + msg.type +' ';
                        break;
                    }

                    if (msg.element.innerHTML && msg.element.innerHTML.length > 50) {
                        var outerHTML = msg.element.outerHTML.replace(msg.element.innerHTML, msg.element.innerHTML.substr(0, 50) + '...');
                    } else {
                        var outerHTML = msg.element.outerHTML;
                    }

                    // Get the success criterion so we can provide a link.
                    var msgParts   = msg.code.split('.');
                    var principle  = msgParts[1];
                    var sc         = msgParts[3].split('_').slice(0, 3).join('_');
                    var techniques = msgParts[4];
                    techniques     = techniques.split(',');

                    msgParts.shift();
                    msgParts.unshift('[Standard]');
                    var noStdMsgParts = msgParts.join('.');

                    // if(type !== "Warning" &&  type !== "Notice" && prtyArr.indexOf(priority) !=-1 ) {       //&& prtyArr.indexOf(priority) === 0
                        errors += 1;
                        temp_obj["Tipo"] = type.toLowerCase();
                        temp_obj["Mensaje"] = msg.msg;
                        temp_obj["Codigo"] = splitLine(outerHTML.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;'), 30);
                        temp_obj["Norma"] = '<a href="http://www.w3.org/TR/WCAG20/#' + principles[principle].toLowerCase() + '" target="_blank">' + principles[principle] + '</a>';
                        var technique='';
                        for (var j = 0; j < techniques.length; j++) {
                            technique += '<a href="http://www.w3.org/TR/WCAG20-TECHS/' + techniques[j] + '" target="_blank">' + techniques[j] + '</a>';

                        }
                        temp_obj["Tecnica"] = technique;
                        count++;
                        content.push(temp_obj);
                    // }
                }
            } //Closing for loop

        } catch (e) {
            console.log('Error:', e.toString());
        }     
        htmlcsres.appendChild(buildHtmlTable(content , '','yes' ) );
        htmlcsDiv.parentNode.replaceChild(htmlcsres, htmlcsDiv);
        window.stop();
    });

}