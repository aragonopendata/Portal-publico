/*
AccDC API - 3.4 for jQuery (12/11/2017)
Copyright 2010-2017 Bryan Garaventa (WhatSock.com)
Part of AccDC, a Cross-Browser JavaScript accessibility API, distributed under the terms of the Open Source Initiative OSI - MIT License
*/
(function(pL){var accDCVersion="3.4 (12/11/2017)",getEl=function(e){if(document.getElementById){return document.getElementById(e)}else{if(document.all){return document.all[e]}else{return null}}},createEl=function(t){var o=document.createElement(t);if(arguments.length===1){return o}if(arguments[1]){setAttr(o,arguments[1])}if(arguments[2]){css(o,arguments[2])}if(arguments[3]){addClass(o,arguments[3])}if(arguments[4]){o.appendChild(arguments[4])}return o},createText=function(s){return document.createTextNode(s)},createAttr=function(a){return document.createAttribute(a)},getAttr=function(e,n){if(!e){return null}var a;if(e.getAttribute){a=e.getAttribute(n)}if(!a&&e.getAttributeNode){a=e.getAttributeNode(n)}if(!a&&e[n]){a=e[n]}return a},remAttr=function(e,n){if(!e){return false}var a=isArray(n)?n:[n];for(var i=0;i<a.length;i++){if(e.removeAttribute){e.removeAttribute(a[i])}}return e},getText=function(n){if(!n){return""}return n.innerText||n.textContent||pL.find.getText([n])||""},css=function(obj,p,v){if(!obj){return null}if(obj.nodeName&&typeof p==="string"&&!v){return obj.style&&obj.style[p]?obj.style[p]:xGetComputedStyle(obj,p)}var o=isArray(obj)?obj:[obj],check="top left bottom right width height";for(var i=0;i<o.length;i++){if(typeof p==="string"){try{o[i].style[xCamelize(p)]=check.indexOf(p)!==-1&&typeof v==="number"?v+"px":v}catch(ex){
	/*@cc_on
	@if (@_jscript_version <= 5.7) // IE7 and down
	if (p != 'display') continue;
	var s = '',
	t = o[i].nodeName.toLowerCase();
	switch(t){
	case 'table' :
	case 'tr' :
	case 'td' :
	case 'li' :
	s = 'block';
	break;
	case 'caption' :
	s = 'inline';
	break;
	}
	o[i].style[p] = s;
	@end @*/
	}}else{if(typeof p==="object"){for(var a=1;a<arguments.length;a++){for(var n in arguments[a]){try{o[i].style[xCamelize(n)]=check.indexOf(n)!==-1&&typeof arguments[a][n]==="number"?arguments[a][n]+"px":arguments[a][n]}catch(ex){
	/*@cc_on
	@if (@_jscript_version <= 5.7) // IE7 and down
	if (n != 'display') continue;
	var s = '',
	t = o[i].nodeName.toLowerCase();
	switch(t){
	case 'table' :
	case 'tr' :
	case 'td' :
	case 'li' :
	s = 'block';
	break;
	case 'caption' :
	s = 'inline';
	break;
	}
	o[i].style[n] = s;
	@end @*/
	}}}}}}return obj},trim=function(str){return str.replace(/^\s+|\s+$/g,"")},setAttr=function(obj,name,value){if(!obj){return null}if(typeof name==="string"){obj.setAttribute(name,value)}else{if(typeof name==="object"){for(n in name){obj.setAttribute(n,name[n])}}}return obj},isArray=function(v){return v&&typeof v==="object"&&typeof v.length==="number"&&typeof v.splice==="function"&&!(v.propertyIsEnumerable("length"))},inArray=function(search,stack){if(stack.indexOf){return stack.indexOf(search)}for(var i=0;i<stack.length;i++){if(stack[i]===search){return i}}return -1},hasClass=function(obj,cn){if(!obj||!obj.className){return false}var names=cn.split(" "),i=0;for(var n=0;n<names.length;n++){if(obj.className.indexOf(names[n])!==-1){i+=1}}if(i===names.length){return true}return false},addClass=function(obj,cn){if(!obj){return null}pL(obj).addClass(cn);return obj},remClass=function(obj,cn){if(!obj){return null}pL(obj).removeClass(cn);return obj},firstChild=function(e,t){var e=e?e.firstChild:null;while(e){if(e.nodeType===1&&(!t||t.toLowerCase()===e.nodeName.toLowerCase())){break}e=e.nextSibling}return e},lastChild=function(e,t){var e=e?e.lastChild:null;while(e){if(e.nodeType===1&&(!t||t.toLowerCase()===e.nodeName.toLowerCase())){break}e=e.previousSibling}return e},insertBefore=function(f,s){if(!f){return s}f.parentNode.insertBefore(s,f);return s},nowI=0,now=function(v){return new Date().getTime()+(nowI++)},sraCSS={position:"absolute",clip:"rect(1px 1px 1px 1px)",clip:"rect(1px, 1px, 1px, 1px)",clipPath:"inset(50%)",padding:0,border:0,height:"1px",width:"1px",overflow:"hidden",whiteSpace:"nowrap"},sraCSSClear=function(o){css(o,{position:"",clip:"auto",clipPath:"none",padding:"",height:"",width:"",overflow:"",whiteSpace:"normal"});return o},getWin=function(){return{width:window.document.documentElement.clientWidth||window.document.body.clientWidth,height:window.document.documentElement.clientHeight||window.document.body.clientHeight}},transition=function(ele,targ,config){if(!ele){return}var uTotalTime=config.duration,iTargetY=targ.top,iTargetX=targ.left,startY=xTop(ele),startX=xLeft(ele);var dispX=iTargetX-startX,dispY=iTargetY-startY,freq=Math.PI/(2*uTotalTime),startTime=new Date().getTime(),tmr=setInterval(function(){var elapsedTime=new Date().getTime()-startTime;if(elapsedTime<uTotalTime){var f=Math.abs(Math.sin(elapsedTime*freq));xTop(ele,Math.round(f*dispY+startY));xLeft(ele,Math.round(f*dispX+startX));config.step.apply(ele)}else{clearInterval(tmr);xLeft(ele,iTargetX);xTop(ele,iTargetY);config.complete.apply(ele)}},10)},xOffset=function(c,forceAbsolute,forceRelative,returnTopLeftOnly){if(c&&c.nodeType===1){var r={},position=css(c,"position");if(forceAbsolute||position=="absolute"){r=getAbsolutePos(c)}else{if(forceRelative||position=="relative"){r.top=c.offsetTop;r.left=c.offsetLeft}else{r=c.getBoundingClientRect()}}if(!returnTopLeftOnly){r.height=xHeight(c);r.width=xWidth(c);r.right=r.left+r.width;r.bottom=r.top+r.height}else{r.height=r.width=r.right=r.bottom=""}return r}else{return null}},getAbsolutePos=function(obj){var curleft=curtop=0;do{curleft+=obj.offsetLeft;curtop+=obj.offsetTop}while(obj=obj.offsetParent);return{left:curleft,top:curtop}},xCamelize=function(cssPropStr){var i,c,a,s;a=cssPropStr.split("-");s=a[0];for(i=1;i<a.length;i++){c=a[i].charAt(0);s+=a[i].replace(c,c.toUpperCase())}return s},xGetComputedStyle=function(e,p,i){if(!e){return null}var s,v="undefined",dv=document.defaultView;if(dv&&dv.getComputedStyle){if(e==document){e=document.body}s=dv.getComputedStyle(e,"");if(s){v=s.getPropertyValue(p)}}else{if(e.currentStyle){v=e.currentStyle[xCamelize(p)]}else{return null}}return i?(parseInt(v)||0):v},xNum=function(){for(var i=0;i<arguments.length;i++){if(isNaN(arguments[i])||typeof arguments[i]!=="number"){return false}}return true},xDef=function(){for(var i=0;i<arguments.length;i++){if(typeof arguments[i]==="undefined"){return false}}return true},xStr=function(){for(var i=0;i<arguments.length;i++){if(typeof arguments[i]!=="string"){return false}}return true},xHeight=function(e,h){var css,pt=0,pb=0,bt=0,bb=0;if(!e){return 0}if(xNum(h)){if(h<0){h=0}else{h=Math.round(h)}}else{h=-1}css=xDef(e.style);if(css&&xDef(e.offsetHeight)&&xStr(e.style.height)){if(h>=0){if(document.compatMode=="CSS1Compat"){pt=xGetComputedStyle(e,"padding-top",1);if(pt!==null){pb=xGetComputedStyle(e,"padding-bottom",1);bt=xGetComputedStyle(e,"border-top-width",1);bb=xGetComputedStyle(e,"border-bottom-width",1)}else{if(xDef(e.offsetHeight,e.style.height)){e.style.height=h+"px";pt=e.offsetHeight-h}}}h-=(pt+pb+bt+bb);if(isNaN(h)||h<0){return}else{e.style.height=h+"px"}}h=e.offsetHeight}else{if(css&&xDef(e.style.pixelHeight)){if(h>=0){e.style.pixelHeight=h}h=e.style.pixelHeight}}return h},xWidth=function(e,w){var css,pl=0,pr=0,bl=0,br=0;if(!e){return 0}if(xNum(w)){if(w<0){w=0}else{w=Math.round(w)}}else{w=-1}css=xDef(e.style);if(css&&xDef(e.offsetWidth)&&xStr(e.style.width)){if(w>=0){if(document.compatMode=="CSS1Compat"){pl=xGetComputedStyle(e,"padding-left",1);if(pl!==null){pr=xGetComputedStyle(e,"padding-right",1);bl=xGetComputedStyle(e,"border-left-width",1);br=xGetComputedStyle(e,"border-right-width",1)}else{if(xDef(e.offsetWidth,e.style.width)){e.style.width=w+"px";pl=e.offsetWidth-w}}}w-=(pl+pr+bl+br);if(isNaN(w)||w<0){return}else{e.style.width=w+"px"}}w=e.offsetWidth}else{if(css&&xDef(e.style.pixelWidth)){if(w>=0){e.style.pixelWidth=w}w=e.style.pixelWidth}}return w},xTop=function(e,iY){if(!e){return 0}var css=xDef(e.style);if(css&&xStr(e.style.top)){if(xNum(iY)){e.style.top=iY+"px"}else{iY=parseInt(e.style.top);if(isNaN(iY)){iY=xGetComputedStyle(e,"top",1)}if(isNaN(iY)){iY=0}}}else{if(css&&xDef(e.style.pixelTop)){if(xNum(iY)){e.style.pixelTop=iY}else{iY=e.style.pixelTop}}}return iY},xLeft=function(e,iX){if(!e){return 0}var css=xDef(e.style);if(css&&xStr(e.style.left)){if(xNum(iX)){e.style.left=iX+"px"}else{iX=parseInt(e.style.left);if(isNaN(iX)){iX=xGetComputedStyle(e,"left",1)}if(isNaN(iX)){iX=0}}}else{if(css&&xDef(e.style.pixelLeft)){if(xNum(iX)){e.style.pixelLeft=iX}else{iX=e.style.pixelLeft}}}return iX},pointerPos=function(e){var posx=0,posy=0;if(e.pageX||e.pageY){posx=e.pageX;posy=e.pageY}else{if(e.clientX||e.clientY){posx=e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft;posy=e.clientY+document.body.scrollTop+document.documentElement.scrollTop}}return{x:posx,y:posy}};var $A=function(dc,dcA,dcI,onReady,disableAsync){if(typeof dc==="object"&&!isArray(dc)&&"id" in dc){}else{disableAsync=onReady;onReady=dcI;dcI=dcA;dcA=dc;dc=null}var fn=function(){if(disableAsync){pL.ajaxSetup({async:false})}pL.accDC(dcA,dcI,dc);if(disableAsync){pL.ajaxSetup({async:true})}};if(onReady){pL(fn)}else{fn()}};$A.reg={};$A.fn={globalDC:{},wheel:{},debug:false};pL.extend($A,{xOffset:xOffset,xHeight:xHeight,xWidth:xWidth,xTop:xTop,xLeft:xLeft,pointerPos:pointerPos,getAbsolutePos:getAbsolutePos,xDef:xDef,xNum:xNum,transition:transition,isArray:isArray,internal:pL,version:accDCVersion,sraCSS:sraCSS,sraCSSClear:sraCSSClear,getEl:getEl,createEl:createEl,getAttr:getAttr,remAttr:remAttr,createText:createText,getText:getText,css:css,setAttr:setAttr,inArray:inArray,hasClass:hasClass,addClass:addClass,remClass:remClass,globalDCMerge:function(){$A.find("*",function(dc){pL.extend(true,dc,$A.fn.globalDC)})},genId:function(id){return now(id||"AccDC")},announce:function(str,noRepeat,aggr){if(typeof str!=="string"){str=getText(str)}return String.prototype.announce.apply(str,[str,null,noRepeat,aggr])},query:function(sel,con,call){if(con&&typeof con==="function"){call=con;con=null}var r=[];if(isArray(sel)){r=sel}else{if(typeof sel!=="string"){r.push(sel)}else{pL.find(sel,con,r)}}if(call&&typeof call==="function"){pL.each(r,call)}return r},find:function(ids,fn){var ids=ids.split(",");for(var id in $A.reg){if(ids[0]==="*"||inArray(id,ids)!==-1){fn.apply($A.reg[id],[$A.reg[id]])}}},destroy:function(id,p){if(!$A.reg[id]){return false}var r=$A.reg[id],a=r.accDCObj,c=r.containerDiv;if(p&&r.loaded){var lc=lastChild(c);while(lc){pL(a).after(lc);lc=lastChild(c)}}if(r.loaded){pL(a).remove()}r.accDCObj=r.containerDiv=a=c=null;var iv=r.indexVal,wh=r.siblings;wh.splice(iv,1);for(var i=0;i<wh.length;i++){wh[i].indexVal=i;wh[i].siblings=wh}if($A.reg[id].parent&&$A.reg[id].parent.children&&$A.reg[id].parent.children.length){var pc=-1,cn=$A.reg[id].parent.children;for(var i=0;i<cn.length;i++){if(cn[i].id==id){pc=i}}if(pc>=0){$A.reg[id].parent.children.splice(pc,1)}}delete $A.reg[id]},morph:function(dc,obj,dcI){if(dc.nodeType===1&&dc.nodeName){dcI=obj;obj=dc;dc=null}var c={fn:{morph:true,morphObj:obj},autoStart:true};pL.extend(c,dcI);pL.accDC([c],null,dc)},setFocus:function(o){var oTI=null;if(getAttr(o,"tabindex")){oTI=getAttr(o,"tabindex")}setAttr(o,"tabindex",-1);o.focus();if(oTI){setAttr(o,"tabindex",oTI)}else{remAttr(o,"tabindex")}return o}});$A.load=function(target,source,hLoadData,callback){return pL(target).load(source,hLoadData,callback)};$A.get=function(source,hGetData,callback,hGetType){return pL.get(source,hGetData,callback,hGetType)};$A.getJSON=function(source,hJSONData,callback){return pL.getJSON(source,hJSONData,callback)};$A.getScript=function(source,callback,disableAsync){if(typeof callback==="boolean"){disableAsync=callback;callback=null}if(disableAsync){pL.ajaxSetup({async:false})}pL.getScript(source,callback);if(disableAsync){pL.ajaxSetup({async:true})}};$A.post=function(source,hPostData,callback,hPostType){return pL.post(source,hPostData,callback,hPostType)};$A.ajax=function(ajaxOptions){return pL.ajax(ajaxOptions)};$A.bind=function(ta,e,fn){if(e=="load"&&(ta=="body"||ta==window||ta==document||ta==document.body)){pL(function(ev){fn(ev)})}else{pL(ta).on(e,fn)}return ta};$A.unbind=function(ta,e){pL(ta).off(e);return ta};$A.trigger=function(ta,e){pL(ta).trigger(e);return ta};window[(window.AccDCNamespace?window.AccDCNamespace:"$A")]=$A;var calcPosition=function(dc,objArg,posVal){var obj=objArg||dc.posAnchor;if(obj&&typeof obj=="string"){obj=pL(obj).get(0)}else{if(!obj){obj=dc.triggerObj}}if(!obj){return}var autoPosition=posVal||dc.autoPosition,pos={},aPos={height:xHeight(dc.accDCObj),width:xWidth(dc.accDCObj)},oPos=xOffset(obj),position=css(dc.accDCObj,"position");if(position=="absolute"&&css(obj,"position")!="fixed"){oPos=xOffset(obj,true)}if(autoPosition==1){pos.left=oPos.left;pos.top=oPos.top-aPos.height}else{if(autoPosition==2){pos.left=oPos.right;pos.top=oPos.top-aPos.height}else{if(autoPosition==3){pos.left=oPos.right;pos.top=oPos.top}else{if(autoPosition==4){pos.left=oPos.right;pos.top=oPos.bottom}else{if(autoPosition==5){pos.left=oPos.left;pos.top=oPos.bottom}else{if(autoPosition==6){pos.left=oPos.left-aPos.width;pos.top=oPos.bottom}else{if(autoPosition==7){pos.left=oPos.left-aPos.width;pos.top=oPos.top}else{if(autoPosition==8){pos.left=oPos.left-aPos.width;pos.top=oPos.top-aPos.height}else{if(autoPosition==9){pos.left=oPos.left;pos.top=oPos.top}else{if(autoPosition==10){pos.left=oPos.right-aPos.width;pos.top=oPos.top-aPos.height}else{if(autoPosition==11){pos.left=oPos.right-aPos.width;pos.top=oPos.top}else{if(autoPosition==12){pos.left=oPos.right-aPos.width;pos.top=oPos.bottom}}}}}}}}}}}}if(typeof dc.offsetTop==="number"&&(dc.offsetTop<0||dc.offsetTop>0)){pos.top+=dc.offsetTop}if(typeof dc.offsetLeft==="number"&&(dc.offsetLeft<0||dc.offsetLeft>0)){pos.left+=dc.offsetLeft}css(dc.accDCObj,pos)};String.prototype.announce=function announce(strm,loop,noRep,aggr){if(String.announce.loaded){if(!String.announce.liveRendered&&!aggr&&String.announce.placeHolder){String.announce.liveRendered=true;document.body.appendChild(String.announce.placeHolder)}if(!String.announce.alertRendered&&aggr&&String.announce.placeHolder2){String.announce.alertRendered=true;document.body.appendChild(String.announce.placeHolder2)}}if(strm&&strm.nodeName&&strm.nodeType===1){strm=getText(strm)}var obj=strm||this,str=strm?strm:this.toString();if(typeof str!=="string"){return obj}if(!loop&&inArray(str,String.announce.alertMsgs)===-1){String.announce.alertMsgs.push(str)}if((String.announce.alertMsgs.length==1||loop)){var timeLength=String.announce.baseDelay+(String.announce.iterate(String.announce.alertMsgs[0],/\s|\,|\.|\:|\;|\!|\(|\)|\/|\?|\@|\#|\$|\%|\^|\&|\*|\\|\-|\_|\+|\=/g)*String.announce.charMultiplier);if(!(noRep&&String.announce.lastMsg==String.announce.alertMsgs[0])){String.announce.lastMsg=String.announce.alertMsgs[0];if(aggr){String.announce.placeHolder2.innerHTML=String.announce.alertMsgs[0]}else{String.announce.placeHolder.innerHTML=String.announce.alertMsgs[0]}}String.announce.alertTO=setTimeout(function(){String.announce.placeHolder.innerHTML=String.announce.placeHolder2.innerHTML="";String.announce.alertMsgs.shift();if(String.announce.alertMsgs.length>=1){String.prototype.announce(String.announce.alertMsgs[0],true,noRep,aggr)}},timeLength)}return obj};String.announce={alertMsgs:[],clear:function(){if(this.alertTO){clearTimeout(this.alertTO)}this.alertMsgs=[]},baseDelay:1000,charMultiplier:10,lastMsg:"",iterate:function(str,regExp){var iCount=0;str.replace(regExp,function(){iCount++});return iCount},loaded:false,liveRendered:false,alertRendered:false};$A.bind(window,"load",function(){if(!String.announce.placeHolder){String.announce.placeHolder=createEl("div",{"aria-live":"polite"},sraCSS);String.announce.placeHolder2=createEl("div",{role:"alert"},sraCSS)}String.announce.loaded=true});pL.accDC=function(accDCObjects,gImport,parentDC){var wheel=[],ids=[],getScript=function(dc,u,f){pL.ajax({async:false,type:"GET",url:u,data:null,success:function(){if(f){return f.apply(dc,arguments)}},dataType:"script"})},changeTabs=function(dc,isClose){var dc=wheel[dc.indexVal];if(dc.isTab){if(dc.tabState){for(var w=0;w<wheel.length;w++){var wl=wheel[w];if(wl.isTab){var ss=pL(wl.triggerObj).data("sra");if(ss){if(wl.loaded){pL(ss).html("<span>&nbsp;"+wl.tabRole+"&nbsp;"+wl.tabState+"</span>")}else{pL(ss).html("<span>&nbsp;"+wl.tabRole+"</span>")}}}}$A.query(dc.trigger,function(){if(this!=dc.triggerObj){pL(pL(this).data("sra")).html("<span>&nbsp;"+dc.tabRole+"</span>")}})}}else{if(dc.isToggle){if(dc.toggleState){$A.query(dc.trigger,function(){var ss=pL(this).data("sra");if(ss){if(!isClose){pL(ss).html("<span>&nbsp;"+dc.toggleRole+"&nbsp;"+dc.toggleState+"</span>")}else{pL(ss).html("<span>&nbsp;"+dc.toggleRole+"</span>")}}})}}}return wheel[dc.indexVal]=dc},loadAccDCObj=function(dc){var dc=wheel[dc.indexVal];if((dc.loaded&&!dc.allowReopen&&!dc.isToggle)||dc.fn.override||dc.lock||dc.loading||dc.closing){return dc}else{if(dc.loaded&&(dc.allowReopen||dc.isToggle)){dc.fn.bypass=true;closeAccDCObj(dc);dc.fn.bypass=false;if(dc.isToggle){return dc}}}dc.cancel=false;dc.content="";var nid=now();dc.accDCObjId=dc.fn.accDCObjId="AccDC"+nid;dc.closeId="AccDC"+(nid+(nowI+=1));dc.containerId=dc.containerDivId="AccDC"+(nid+(nowI+=1));if(dc.importCSS){dc.fn.importCSSId="AccDC"+(nid+(nowI+=1))}dc.fn.sraStart=createEl("div",null,sraCSS);dc.fn.sraEnd=createEl("div",null,sraCSS);dc.containerDiv=createEl("div",{id:dc.containerId});dc.accDCObj=createEl("div",{id:dc.fn.accDCObjId});if(dc.className){addClass(dc.accDCObj,dc.className)}if(dc.showHiddenBounds){pL(dc.accDCObj).append(dc.fn.sraStart)}pL(dc.accDCObj).append(dc.containerDiv);if(dc.showHiddenBounds){pL(dc.accDCObj).append(dc.fn.sraEnd)}var events={mouseOver:function(ev){dc.mouseOver.apply(this,[ev,dc])},mouseOut:function(ev){dc.mouseOut.apply(this,[ev,dc])},resize:function(ev){dc.resize.apply(this,[ev,dc])},scroll:function(ev){dc.scroll.apply(this,[ev,dc])},click:function(ev){dc.click.apply(this,[ev,dc])},dblClick:function(ev){dc.dblClick.apply(this,[ev,dc])},mouseDown:function(ev){dc.mouseDown.apply(this,[ev,dc])},mouseUp:function(ev){dc.mouseUp.apply(this,[ev,dc])},mouseMove:function(ev){dc.mouseMove.apply(this,[ev,dc])},mouseEnter:function(ev){dc.mouseEnter.apply(this,[ev,dc])},mouseLeave:function(ev){dc.mouseLeave.apply(this,[ev,dc])},keyDown:function(ev){dc.keyDown.apply(this,[ev,dc])},keyPress:function(ev){dc.keyPress.apply(this,[ev,dc])},keyUp:function(ev){dc.keyUp.apply(this,[ev,dc])},error:function(ev){dc.error.apply(this,[ev,dc])},focusIn:function(ev){dc.focusIn.apply(this,[ev,dc])},focusOut:function(ev){dc.focusOut.apply(this,[ev,dc])}},toBind={};for(var ev in events){if(dc[ev]&&typeof dc[ev]==="function"){toBind[ev.toLowerCase()]=events[ev]}}$A.bind(dc.accDCObj,toBind);if(!dc.ranJSOnceBefore){dc.ranJSOnceBefore=true;if(dc.reverseJSOrder){dc.runOnceBefore.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runOnceBefore){dc.fn.proto.runOnceBefore.apply(dc,[dc])}if($A.fn.globalDC.runOnceBefore){$A.fn.globalDC.runOnceBefore.apply(dc,[dc])}}dc.reverseJSOrderPass=true}if(dc.runJSOnceBefore.length){for(var j=0;j<dc.runJSOnceBefore.length;j++){getScript(dc,dc.runJSOnceBefore[j])}}if(dc.allowCascade){if(dc.fn.proto.runJSOnceBefore&&dc.fn.proto.runJSOnceBefore.length){for(var j=0;j<dc.fn.proto.runJSOnceBefore.length;j++){getScript(dc,dc.fn.proto.runJSOnceBefore[j])}}if($A.fn.globalDC.runJSOnceBefore&&$A.fn.globalDC.runJSOnceBefore.length){for(var j=0;j<$A.fn.globalDC.runJSOnceBefore.length;j++){getScript(dc,$A.fn.globalDC.runJSOnceBefore[j])}}}if(!dc.reverseJSOrder&&!dc.reverseJSOrderPass){dc.runOnceBefore.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runOnceBefore){dc.fn.proto.runOnceBefore.apply(dc,[dc])}if($A.fn.globalDC.runOnceBefore){$A.fn.globalDC.runOnceBefore.apply(dc,[dc])}}}else{dc.reverseJSOrderPass=false}}if(dc.reverseJSOrder){dc.runBefore.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runBefore){dc.fn.proto.runBefore.apply(dc,[dc])}if($A.fn.globalDC.runBefore){$A.fn.globalDC.runBefore.apply(dc,[dc])}}dc.reverseJSOrderPass=true}if(dc.runJSBefore.length){for(var j=0;j<dc.runJSBefore.length;j++){getScript(dc,dc.runJSBefore[j])}}if(dc.allowCascade){if(dc.fn.proto.runJSBefore&&dc.fn.proto.runJSBefore.length){for(var j=0;j<dc.fn.proto.runJSBefore.length;j++){getScript(dc,dc.fn.proto.runJSBefore[j])}}if($A.fn.globalDC.runJSBefore&&$A.fn.globalDC.runJSBefore.length){for(var j=0;j<$A.fn.globalDC.runJSBefore.length;j++){getScript(dc,$A.fn.globalDC.runJSBefore[j])}}}if(!dc.reverseJSOrder&&!dc.reverseJSOrderPass){dc.runBefore.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runBefore){dc.fn.proto.runBefore.apply(dc,[dc])}if($A.fn.globalDC.runBefore){$A.fn.globalDC.runBefore.apply(dc,[dc])}}}else{dc.reverseJSOrderPass=false}if(dc.cancel){dc.cancel=dc.loading=false;return dc}dc.loading=true;if(dc.showHiddenBounds){setAttr(dc.fn.sraStart,{id:"h"+now(),role:"heading","aria-level":dc.ariaLevel});pL(dc.fn.sraStart).append("<span>"+dc.role+"&nbsp;"+dc.accStart+"</span>");if(dc.showHiddenClose){dc.fn.closeLink=createEl("a",{id:dc.closeId,href:"#"},dc.sraCSS,dc.closeClassName);dc.fn.closeLink.innerHTML=dc.accClose;insertBefore(dc.fn.sraEnd,dc.fn.closeLink);if(dc.displayHiddenClose){$A.bind(dc.fn.closeLink,{focus:function(){sraCSSClear(this)},blur:function(){css(this,dc.sraCSS)}})}else{setAttr(dc.fn.closeLink,"tabindex","-1")}}pL(dc.fn.sraEnd).append("<span>"+dc.role+"&nbsp;"+dc.accEnd+"</span>")}if(dc.forceFocus){setAttr(dc.fn.sraStart,"tabindex",-1);css(dc.fn.sraStart,"outline","none")}if(dc.displayInline){css([dc.accDCObj,dc.containerDiv],"display","inline")}switch(dc.mode){case 1:pL(dc.containerDiv).load(dc.source,dc.hLoadData,function(responseText,textStatus,XMLHttpRequest){dc.hLoad(responseText,textStatus,XMLHttpRequest,dc);parseRemaining(dc)});break;case 2:dc.request=pL.get(dc.source,dc.hGetData,function(source,textStatus){dc.hGet(source,textStatus,dc);dc.hSource(dc.content);parseRemaining(dc)},dc.hGetType);break;case 3:dc.request=pL.getJSON(dc.source,dc.hJSONData,function(source,textStatus){dc.hJSON(source,textStatus,dc);dc.hSource(dc.content);parseRemaining(dc)});break;case 4:dc.request=pL.getScript(dc.source,function(source,textStatus){dc.hScript(source,textStatus,dc);dc.hSource(dc.content);parseRemaining(dc)});break;case 5:dc.request=pL.post(dc.source,dc.hPostData,function(source,textStatus){dc.hPost(source,textStatus,dc);dc.hSource(dc.content);parseRemaining(dc)},dc.hPostType);break;case 6:dc.request=pL.ajax(dc.ajaxOptions);break;default:dc.hSource(dc.source);parseRemaining(dc)}return wheel[dc.indexVal]=dc},parseRemaining=function(dc){var dc=wheel[dc.indexVal];dc.runDuring.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runDuring){dc.fn.proto.runDuring.apply(dc,[dc])}if($A.fn.globalDC.runDuring){$A.fn.globalDC.runDuring.apply(dc,[dc])}}if(dc.cancel){dc.cancel=dc.loading=false;return dc}for(var w=0;w<wheel.length;w++){var wl=wheel[w];if(wl.loaded&&!wl.allowMultiple){wl.fn.bypass=true;dc.close(wl);wl.fn.bypass=false}}css(dc.accDCObj,dc.cssObj);if(dc.autoFix){setAutoFix(dc)}if(dc.fn.morph&&dc.fn.morphObj){pL(dc.fn.morphObj).after(dc.accDCObj);pL(dc.containerDiv).append(dc.fn.morphObj);dc.fn.morph=false}else{if(dc.isStatic){if(dc.append){pL(dc.isStatic).append(dc.accDCObj)}else{if(dc.prepend){if(!firstChild(pL(dc.isStatic).get(0))){pL(dc.isStatic).append(dc.accDCObj)}else{insertBefore(firstChild(pL(dc.isStatic).get(0)),dc.accDCObj)}}else{pL(dc.isStatic).html(dc.accDCObj)}}}else{if(dc.targetObj&&(!dc.returnFocus||dc.triggerObj)){pL(dc.targetObj).after(dc.accDCObj)}else{if(dc.triggerObj){pL(dc.triggerObj).after(dc.accDCObj)}else{if($A.fn.debug){alert("Error: The dc.triggerObj property must be programatically set if no trigger or targetObj is specified during setup. View the Traversal and Manipulation section in the WhatSock.com Core API documentation for additional details.")}}}}}if(dc.importCSS){dc.fn.cssLink=createEl("link",{id:dc.fn.importCSSId,rel:"stylesheet",type:"text/css",href:dc.importCSS});dc.accDCObj.appendChild(dc.fn.cssLink)}if(dc.isDraggable&&dc.drag.persist&&dc.drag.x&&dc.drag.y){css(dc.accDCObj,{left:dc.drag.x,top:dc.drag.y})}else{if(dc.autoPosition>0&&!dc.isStatic&&!dc.autoFix){calcPosition(dc)}}var forceFocus=dc.forceFocus;dc.loading=false;dc.loaded=true;if(dc.isTab||dc.isToggle){changeTabs(dc)}$A.query("."+dc.closeClassName,dc.accDCObj,function(){$A.bind(this,"click",function(ev){dc.close();ev.preventDefault()})});$A.bind(dc.fn.closeLink,"focus",function(ev){dc.tabOut(ev,dc)});if(dc.timeoutVal){dc.timer=setTimeout(function(){dc.timeout(dc)},dc.timeoutVal)}if(dc.dropTarget&&dc.accDD.on){dc.accDD.dropTargets=[];dc.accDD.dropAnchors=[];$A.query(dc.dropTarget,function(){dc.accDD.dropAnchors.push(this);dc.accDD.dropTargets.push(this)})}if(!dc.ranJSOnceAfter){dc.ranJSOnceAfter=true;if(dc.reverseJSOrder){dc.runOnceAfter.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runOnceAfter){dc.fn.proto.runOnceAfter.apply(dc,[dc])}if($A.fn.globalDC.runOnceAfter){$A.fn.globalDC.runOnceAfter.apply(dc,[dc])}}dc.reverseJSOrderPass=true}if(dc.runJSOnceAfter.length){for(var j=0;j<dc.runJSOnceAfter.length;j++){getScript(dc,dc.runJSOnceAfter[j])}}if(dc.allowCascade){if(dc.fn.proto.runJSOnceAfter&&dc.fn.proto.runJSOnceAfter.length){for(var j=0;j<dc.fn.proto.runJSOnceAfter.length;j++){getScript(dc,dc.fn.proto.runJSOnceAfter[j])}}if($A.fn.globalDC.runJSOnceAfter&&$A.fn.globalDC.runJSOnceAfter.length){for(var j=0;j<$A.fn.globalDC.runJSOnceAfter.length;j++){getScript(dc,$A.fn.globalDC.runJSOnceAfter[j])}}}if(!dc.reverseJSOrder&&!dc.reverseJSOrderPass){dc.runOnceAfter.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runOnceAfter){dc.fn.proto.runOnceAfter.apply(dc,[dc])}if($A.fn.globalDC.runOnceAfter){$A.fn.globalDC.runOnceAfter.apply(dc,[dc])}}}else{dc.reverseJSOrderPass=false}}if(dc.reverseJSOrder){dc.runAfter.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runAfter){dc.fn.proto.runAfter.apply(dc,[dc])}if($A.fn.globalDC.runAfter){$A.fn.globalDC.runAfter.apply(dc,[dc])}}dc.reverseJSOrderPass=true}if(dc.runJSAfter.length){for(var j=0;j<dc.runJSAfter.length;j++){getScript(dc,dc.runJSAfter[j])}}if(dc.allowCascade){if(dc.fn.proto.runJSAfter&&dc.fn.proto.runJSAfter.length){for(var j=0;j<dc.fn.proto.runJSAfter.length;j++){getScript(dc,dc.fn.proto.runJSAfter[j])}}if($A.fn.globalDC.runJSAfter&&$A.fn.globalDC.runJSAfter.length){for(var j=0;j<$A.fn.globalDC.runJSAfter.length;j++){getScript(dc,$A.fn.globalDC.runJSAfter[j])}}}if(!dc.reverseJSOrder&&!dc.reverseJSOrderPass){dc.runAfter.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runAfter){dc.fn.proto.runAfter.apply(dc,[dc])}if($A.fn.globalDC.runAfter){$A.fn.globalDC.runAfter.apply(dc,[dc])}}}else{dc.reverseJSOrderPass=false}if((parseInt(dc.shadow.horizontal)||parseInt(dc.shadow.vertical))&&dc.shadow.color){setShadow(dc)}if(dc.autoFix&&(!dc.isDraggable||!dc.drag.persist||!dc.drag.x||!dc.drag.y)){sizeAutoFix(dc)}if(dc.isDraggable){setDrag(dc)}if(forceFocus){$A.setFocus(dc.fn.sraStart)}if($A.fn.debug&&!getEl(dc.containerId)){alert("Error: The Automatic Accessibility Framework has been overwritten within the AccDC Dynamic Content Object with id="+dc.id+'. New content should be added in a proper manner using the "source", "containerDiv", or "content" properties to ensure accessibility. View the Setup, Traversal and Manipulation, and Mode Handlers sections in the WhatSock.com Core API documentation for additional details.')}if(dc.announce){$A.announce(dc.containerDiv)}if($A.bootstrap){$A.bootstrap(dc.containerDiv)}return wheel[dc.indexVal]=dc},closeAccDCObj=function(dc){var dc=wheel[dc.indexVal];dc.runBeforeClose.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runBeforeClose){dc.fn.proto.runBeforeClose.apply(dc,[dc])}if($A.fn.globalDC.runBeforeClose){$A.fn.globalDC.runBeforeClose.apply(dc,[dc])}}if(!dc.loaded||dc.lock){return dc}dc.closing=true;if(dc.isDraggable){unsetDrag(dc)}pL(dc.accDCObj).remove();if(dc.fn.containsFocus&&!dc.fn.bypass){dc.fn.toggleFocus=true}dc.fn.override=true;if(dc.returnFocus&&dc.triggerObj&&!dc.fn.bypass){if(dc.triggerObj.nodeName.toLowerCase()=="form"){var s=pL(dc.triggerObj).find('*[type="submit"]').get(0);if(s&&s.focus){s.focus()}}else{if(dc.triggerObj.focus){dc.triggerObj.focus()}else{$A.setFocus(dc.triggerObj)}}}dc.loaded=dc.fn.override=false;if(dc.isTab||dc.isToggle){changeTabs(dc,true)}dc.fn.triggerObj=dc.triggerObj;dc.closing=false;dc.runAfterClose.apply(dc,[dc]);if(dc.allowCascade){if(dc.fn.proto.runAfterClose){dc.fn.proto.runAfterClose.apply(dc,[dc])}if($A.fn.globalDC.runAfterClose){$A.fn.globalDC.runAfterClose.apply(dc,[dc])}}return wheel[dc.indexVal]=dc},unsetTrigger=function(dc){var dc=wheel[dc.indexVal];$A.query(dc.fn.triggerB,function(){$A.unbind(this,dc.fn.bindB);if(dc.isTab||dc.isToggle){pL(this).data("sra").remove()}});dc.fn.triggerB=dc.fn.bindB="";return wheel[dc.indexVal]=dc},setTrigger=function(dc){var dc=wheel[dc.indexVal];unsetTrigger(dc);return wheel[dc.indexVal]=setBindings(dc)},setAutoFix=function(dc){var dc=wheel[dc.indexVal];if(!dc.loading&&!dc.loaded){return dc}var cs={position:"fixed",right:"",bottom:"",top:"",left:""};switch(dc.autoFix){case 1:cs.top=0;cs.left="40%";break;case 2:cs.top=0;cs.right=0;break;case 3:cs.top="40%";cs.right=0;break;case 4:cs.bottom=0;cs.right=0;break;case 5:cs.bottom=0;cs.left="40%";break;case 6:cs.bottom=0;cs.left=0;break;case 7:cs.top="40%";cs.left=0;break;case 8:cs.top=0;cs.left=0;break;case 9:cs.top="40%";cs.left="40%";default:cs=dc.cssObj}css(dc.accDCObj,cs);return wheel[dc.indexVal]=dc},sizeAutoFix=function(dc){var dc=wheel[dc.indexVal];if(!dc.loading&&!dc.loaded){return dc}var win=getWin();var bodyW=win.width,bodyH=win.height,aW=xWidth(dc.accDCObj),aH=xHeight(dc.accDCObj);if(bodyW>aW){var npw=parseInt(aW/bodyW*100/2)}else{var npw=50}if(bodyH>aH){var nph=parseInt(aH/bodyH*100/2)}else{var nph=50}switch(dc.autoFix){case 1:case 5:css(dc.accDCObj,"left",50-npw+"%");break;case 3:case 7:css(dc.accDCObj,"top",50-nph+"%");break;case 9:css(dc.accDCObj,{left:50-npw+"%",top:50-nph+"%"})}if(dc.offsetTop<0||dc.offsetTop>0||dc.offsetLeft<0||dc.offsetLeft>0){var cs=xOffset(dc.accDCObj);cs.top+=dc.offsetTop;cs.left+=dc.offsetLeft;css(dc.accDCObj,cs)}return wheel[dc.indexVal]=dc},setShadow=function(dc){var dc=wheel[dc.indexVal];css(dc.accDCObj,{"box-shadow":dc.shadow.horizontal+" "+dc.shadow.vertical+" "+dc.shadow.blur+" "+dc.shadow.color,"-webkit-box-shadow":dc.shadow.horizontal+" "+dc.shadow.vertical+" "+dc.shadow.blur+" "+dc.shadow.color,"-moz-box-shadow":dc.shadow.horizontal+" "+dc.shadow.vertical+" "+dc.shadow.blur+" "+dc.shadow.color});return wheel[dc.indexVal]=dc},setDrag=function(dc){if($A.setDragAndDrop&&typeof $A.setDragAndDrop=="function"&&$A.setDragAndDrop.setDrag&&typeof $A.setDragAndDrop.setDrag=="function"){$A.setDragAndDrop.setDrag.apply(this,[dc,wheel,pL])}},unsetDrag=function(dc,uDrop){if($A.setDragAndDrop&&typeof $A.setDragAndDrop=="function"&&$A.setDragAndDrop.unsetDrag&&typeof $A.setDragAndDrop.unsetDrag=="function"){$A.setDragAndDrop.unsetDrag.apply(this,[dc,uDrop,wheel,pL])}},autoStart=[],setBindings=function(dc){dc.fn.toggleFocus=dc.fn.containsFocus=false;dc.bind=dc.binders||dc.bind;if(inArray("focus",dc.bind.split(" "))>=0){dc.fn.containsFocus=true}dc.fn.triggerB=dc.trigger;dc.fn.bindB=dc.bind;$A.query(dc.trigger,function(){if(this.nodeName.toLowerCase()=="a"&&!this.href){setAttr(this,"href","#")}$A.bind(this,dc.bind,function(ev){dc.triggerObj=this;dc.open();ev.preventDefault()});if((dc.isTab&&(dc.tabRole||dc.tabState))||(dc.isToggle&&(dc.toggleRole||dc.toggleState))){var ss=createEl("span",null,sraCSS);pL(this).append(ss);pL(this).data("sra",ss);dc.fn.sraCSSObj=ss}if(dc.isTab){pL(ss).html(dc.loaded?("<span>&nbsp;"+dc.tabRole+"&nbsp;"+dc.tabState+"</span>"):("<span>&nbsp;"+dc.tabRole+"</span>"))}else{if(dc.isToggle){pL(ss).html(dc.loaded?("<span>&nbsp;"+dc.toggleRole+"&nbsp;"+dc.toggleState+"</span>"):("<span>&nbsp;"+dc.toggleRole+"</span>"))}}});return dc},AccDCInit=function(dc){dc=setBindings(dc);dc.sraCSS=sraCSS;dc.sraCSSClear=sraCSSClear;var f=function(){};f.prototype=dc;return window[(window.AccDCNamespace?window.AccDCNamespace:"$A")].reg[dc.id]=$A.reg[dc.id]=new f()},svs=["runJSOnceBefore","runOnceBefore","runJSBefore","runBefore","runDuring","runJSOnceAfter","runOnceAfter","runJSAfter","runAfter","runBeforeClose","runAfterClose"];for(var a=0;a<accDCObjects.length;a++){var dc={id:"",fn:{},trigger:"",setTrigger:function(dc){var dc=dc||this;if(!dc.trigger||!dc.bind){if($A.fn.debug){alert("Error: Both of the dc.trigger and dc.bind properties must be set before this function can be used. View the Setup section in the WhatSock.com Core API documentation for additional details.")}return dc}return setTrigger(dc)},unsetTrigger:function(dc){var dc=dc||this;if(!dc.fn.triggerB||!dc.fn.bindB){return dc}return unsetTrigger(dc)},targetObj:null,role:"",accStart:"Start",accEnd:"End",accClose:"Close",ariaLevel:2,showHiddenClose:true,displayHiddenClose:true,showHiddenBounds:true,source:"",bind:"",displayInline:false,allowCascade:false,reverseJSOrder:false,runJSOnceBefore:[],runOnceBefore:function(dc){},runJSBefore:[],runBefore:function(dc){},runDuring:function(dc){},runJSOnceAfter:[],runOnceAfter:function(dc){},runJSAfter:[],runAfter:function(dc){},runBeforeClose:function(dc){},runAfterClose:function(dc){},allowMultiple:false,allowReopen:false,isToggle:false,toggleRole:"",toggleState:"",forceFocus:false,returnFocus:true,isStatic:"",prepend:false,append:false,isTab:false,tabRole:"Tab",tabState:"Selected",autoStart:false,announce:false,lock:false,mode:0,hSource:function(source,dc){var dc=dc||this;pL(dc.containerDiv).html(source);return dc},hLoadData:"",hLoad:function(responseText,textStatus,XMLHttpRequest,dc){},hGetData:{},hGetType:"",hGet:function(data,textStatus,dc){},hJSONData:{},hJSON:function(data,textStatus,dc){},hScript:function(data,textStatus,dc){},hPostData:{},hPostType:"",hPost:function(data,textStatus,dc){},ajaxOptions:{beforeSend:function(XMLHttpRequest){dc.hBeforeSend(this,XMLHttpRequest,dc)},success:function(source,textStatus,XMLHttpRequest){dc.hSuccess(this,source,textStatus,XMLHttpRequest,dc);dc.hSource(dc.content);parseRemaining(dc)},complete:function(XMLHttpRequest,textStatus){dc.hComplete(this,XMLHttpRequest,textStatus,dc)},error:function(XMLHttpRequest,textStatus,errorThrown){dc.hError(this,XMLHttpRequest,textStatus,errorThrown,dc)}},hBeforeSend:function(options,XMLHttpRequest,dc){},hSuccess:function(options,data,textStatus,XMLHttpRequest,dc){dc.content=data},hComplete:function(options,XMLHttpRequest,textStatus,dc){},hError:function(options,XMLHttpRequest,textStatus,errorThrown,dc){},open:function(dc){var dc=dc||this;if(dc.fn.toggleFocus){dc.fn.toggleFocus=false}else{loadAccDCObj(dc)}return dc},close:function(dc){var dc=dc||this;return closeAccDCObj(dc)},isDraggable:false,drag:{handle:null,maxX:null,maxY:null,persist:false,x:null,y:null,confineTo:null,init:null,override:null},onDragStart:function(ev,dd,dc){},onDragEnd:function(ev,dd,dc){},onDrag:function(ev,dd,dc){},dropTarget:null,dropInit:null,drop:{},onDropStart:function(ev,dd,dc){},onDrop:function(ev,dd,dc){},onDropEnd:function(ev,dd,dc){},setDrag:function(dc){var dc=dc||this;return setDrag(dc)},unsetDrag:function(dc,uDrop){if(dc&&typeof dc==="boolean"){uDrop=dc;dc=this}else{var dc=dc||this}unsetDrag(dc,uDrop);return dc},accDD:{on:false,dragText:"Move",toText:"to",dropTargets:[],dropEffect:"move",actionText:"Dragging",returnFocusTo:"",isDragging:false,dragClassName:"",dragLinkStyle:{},duration:500,fireDrag:function(ev,dc){var os=xOffset(this);dc.accDD.dragDD={drag:this,proxy:this,drop:dc.accDD.dropTargets,available:dc.accDD.dropTargets,startX:os.left+(os.width/2),startY:os.top+(os.height/2),deltaX:0,deltaY:0,originalX:os.left,originalY:os.top,offsetX:0,offsetY:0};dc.accDD.dragDD.target=pL(dc.drag.handle).get(0)||this;dc.onDragStart.apply(this,[ev,dc.accDD.dragDD,dc])},fireDrop:function(ev,dc){var that=this,os=xOffset(this);dc.accDD.dropDD={target:this,drag:dc.accDD.dragDD.drag,proxy:dc.accDD.dragDD.proxy,drop:dc.accDD.dragDD.drop,available:dc.accDD.dragDD.available,startX:dc.accDD.dragDD.startX,startY:dc.accDD.dragDD.startY,originalX:dc.accDD.dragDD.originalX,originalY:dc.accDD.dragDD.originalY,deltaX:0,deltaY:0,offsetX:os.left,offsetY:os.top};function update(){var os=xOffset(dc.accDD.dragDD.drag);dc.accDD.dragDD.offsetY=os.top;dc.accDD.dragDD.offsetX=os.left}transition(dc.accDD.dragDD.drag,{top:dc.accDD.dropDD.offsetY,left:dc.accDD.dropDD.offsetX},{duration:dc.accDD.duration,step:function(){update();dc.onDrag.apply(dc.accDD.dragDD.drag,[ev,dc.accDD.dragDD,dc])},complete:function(){update();if(dc.accDD.dragDD.originalY<=dc.accDD.dragDD.offsetY){dc.accDD.dragDD.deltaY=dc.accDD.dropDD.deltaY=dc.accDD.dragDD.originalY-dc.accDD.dragDD.offsetY}else{if(dc.accDD.dragDD.originalY>=dc.accDD.dragDD.offsetY){dc.accDD.dragDD.deltaY=dc.accDD.dropDD.deltaY=0-(dc.accDD.dragDD.offsetY-dc.accDD.dragDD.originalY)}}if(dc.accDD.dragDD.originalX<=dc.accDD.dragDD.offsetX){dc.accDD.dragDD.deltaX=dc.accDD.dropDD.deltaX=dc.accDD.dragDD.originalX-dc.accDD.dragDD.offsetX}else{if(dc.accDD.dragDD.originalX>=dc.accDD.dragDD.offsetX){dc.accDD.dragDD.deltaX=dc.accDD.dropDD.deltaX=0-(dc.accDD.dragDD.offsetX-dc.accDD.dragDD.originalX)}}var rft=dc.accDD.returnFocusTo;dc.onDropStart.apply(that,[ev,dc.accDD.dropDD,dc]);dc.onDrop.apply(that,[ev,dc.accDD.dropDD,dc]);dc.onDropEnd.apply(that,[ev,dc.accDD.dropDD,dc]);dc.onDragEnd.apply(dc.accDD.dragDD.drag,[ev,dc.accDD.dragDD,dc]);$A.setFocus((rft.nodeType===1?rft:pL(rft).get(0))||dc.accDCObj);dc.accDD.isDragging=false;setAttr(dc.accDCObj,"aria-grabbed","false")}})}},tabOut:function(ev,dc){},timeoutVal:0,timeout:function(dc){},className:"",closeClassName:"accDCCloseCls",cssObj:{},importCSS:"",css:function(prop,val,mergeCSS,dc){var dc=dc||this;if(typeof prop==="string"&&val){if(mergeCSS){dc.cssObj[prop]=val}css(dc.accDCObj,prop,val);return dc}else{if(prop&&typeof prop==="object"){if(val&&typeof val==="boolean"){pL.extend(dc.cssObj,prop)}css(dc.accDCObj,prop);return dc}else{if(prop&&typeof prop==="string"){return css(dc.accDCObj,prop)}}}},children:[],parent:null,autoPosition:0,offsetTop:0,offsetLeft:0,offsetParent:null,posAnchor:null,setPosition:function(obj,posVal,save,dc){if(typeof obj==="number"){dc=save;save=posVal;posVal=obj}var dc=dc||this;if(save){dc.posAnchor=obj||dc.posAnchor;dc.autoPosition=posVal||dc.autoPosition}calcPosition(dc,obj,posVal);return dc},applyFix:function(val,dc){var dc=dc||this;if(val){dc.autoFix=val}setAutoFix(dc);if(dc.autoFix>0){sizeAutoFix(dc)}return dc},shadow:{horizontal:"0px",vertical:"0px",blur:"0px",color:""},setShadow:function(dc,shadow){if(arguments.length===1&&!("id" in dc)){shadow=dc;dc=this}if(shadow){pL.extend(dc.shadow,shadow)}return setShadow(dc)},AccDCInit:function(){return this}},aO=accDCObjects[a],gImport=gImport||{},gO={},iO={};if(aO.mode==6){var ajaxOptions=dc.ajaxOptions}if(typeof aO.allowCascade!=="boolean"){aO.allowCascade=gImport.allowCascade}if(typeof aO.allowCascade!=="boolean"){aO.allowCascade=$A.fn.globalDC.allowCascade||dc.allowCascade}if(aO.allowCascade){for(var s=0;s<svs.length;s++){gO[svs[s]]=$A.fn.globalDC[svs[s]];iO[svs[s]]=gImport[svs[s]]}}if(!pL.isEmptyObject($A.fn.globalDC)){pL.extend(true,dc,$A.fn.globalDC)}if(!pL.isEmptyObject(gImport)){pL.extend(true,dc,gImport)}pL.extend(true,dc,aO);if(aO.mode==6&&ajaxOptions){pL.extend(dc.ajaxOptions,ajaxOptions)}if(dc.allowCascade){for(var s=0;s<svs.length;s++){$A.fn.globalDC[svs[s]]=gO[svs[s]]}dc.fn.proto=iO}if(dc.id&&dc.role){ids.push(dc.id);if(dc.autoStart){autoStart.push(dc.id)}dc.indexVal=wheel.length;wheel[dc.indexVal]=AccDCInit(dc);if(parentDC){var chk=-1,p=$A.reg[parentDC.id],c=$A.reg[wheel[dc.indexVal].id];for(var i=0;i<p.children.length;i++){if(c.id===p.children[i].id){chk=i}}if(chk>=0){p.children.slice(chk,1,c)}else{p.children.push(c)}c.parent=p;var t=c;while(t.parent){t=t.parent}c.top=t}else{wheel[dc.indexVal].top=wheel[dc.indexVal]}}else{if($A.fn.debug){alert("Error: To ensure both proper functionality and accessibility, every AccDC Dynamic Content Object must have a unique ID and an informative ROLE. View the Setup and Automatic Accessibility Framework sections in the WhatSock.com Core API documentation for additional details.")}}}for(var a=0;a<wheel.length;a++){wheel[a].siblings=wheel}for(var s=0;s<autoStart.length;s++){var dc=$A.reg[autoStart[s]];var t=pL(dc.trigger).get(0);dc.triggerObj=t?t:null;dc.open()}};if(window.InitAccDC&&window.InitAccDC.length){pL.ajaxSetup({async:false});for(var i=0;i<window.InitAccDC.length;i++){$A.getScript(window.InitAccDC[i])}pL.ajaxSetup({async:true})}})($);
	
	
/*!
ARIA Calendar Module R2.15
Copyright 2019 Bryan Garaventa (WhatSock.com)
Refactoring Contributions Copyright 2018 Danny Allen (dannya.com) / Wonderscore Ltd (wonderscore.co.uk)
Part of AccDC, a Cross-Browser JavaScript accessibility API, distributed under the terms of the Open Source Initiative OSI - MIT License
*/

(function(){

	$A.setCalendar = function(pId, trigger, targ, commentsEnabled, callback, config){

		var config = config || {}, helpTextShort = config.helpTextShort ? config.helpTextShort : 'Pulsar H para desplegar ayuda.',
			helpText =
				config.helpText
					? config.helpText
					: 'Pulsar las flechas para navegar por los días, PageUp y PageDown para navegar por los meses, Alt+PageUp y Alt+PageDown para navegar por los años, Escape para cancelar.',

		// Toggles for openOnFocus support.
		openOnFocusHelpText = config.openOnFocusHelpText
			? config.openOnFocusHelpText : 'Pulsar flecha abajo para navegar por el calendario, o Escape para cerrar.',
			onFocusInit = false, onFocusTraverse = false,

		// Control the behavior of date selection clicks
		handleClick = (callback && typeof callback === 'function') ? callback : function(ev, dc){
			// format selected calendar value and set into input field
			targ.value = dc.formatDate(dc);

			dc.close();
			targ.focus();
		}, pressed = {}, changePressed = function(ev){
			pressed.alt = ev.altKey;
			pressed.ctrl = ev.ctrlKey;
			pressed.shift = ev.shiftKey;
		};

		// Calendar object declaration start
		$A(
						[
						{
						id: pId,
						role: config.role || 'Calendario',
						// isStatic: 'body',
						// append: true,
						trigger: trigger,
						bind: 'opendatepicker',
						// Toggles for openOnFocus support.
						returnFocus: false,
						openOnFocus: (config.openOnFocus === true),
						openOnFocusHelpText: openOnFocusHelpText,
						showEscBtn: (config.showEscBtn === true),
						escBtnName: config.escBtnName || 'Cerrar',
						escBtnIcon: config.escBtnIcon || '&times;',
						allowReopen: true,
						showHiddenClose: false,
						controlType: 'DatePicker',
						tooltipTxt: config.tooltipTxt || 'Pulsar Escape para cancelar',
						markedTxt: config.markedTxt || 'Seleccionado',
						disabledTxt: config.disabledTxt || 'Deshabilitado',
						commentedTxt: config.commentedTxt || 'Has Comment',
						prevTxt: config.prevTxt || 'Previo',
						nextTxt: config.nextTxt || 'Siguiente',
						monthTxt: config.monthTxt || 'Mes',
						yearTxt: config.yearTxt || 'Año',
						leftButtonYearText: config.leftButtonYearText || '&#8656;',
						rightButtonYearText: config.rightButtonYearText || '&#8658;',
						leftButtonMonthText: config.leftButtonMonthText || '&#8592;',
						rightButtonMonthText: config.rightButtonMonthText || '&#8594;',
						drawFullCalendar: (config.drawFullCalendar === true),
						highlightToday: (config.highlightToday === true),
						pageUpDownNatural: true,
						autoPosition: isNaN(config.autoPosition) ? 9 : config.autoPosition,
						offsetTop: isNaN(config.offsetTop) ? 0 : config.offsetTop,
						offsetLeft: isNaN(config.offsetLeft) ? 0 : config.offsetLeft,
						posAnchor: config.posAnchor,
						targetObj: config.targetObj,
						inputDateFormat: config.inputDateFormat || 'DD/MM/YYYY',
						audibleDateFormat: config.audibleDateFormat || 'D, dddd MMMM YYYY',
						initialDate: ((config.initialDate instanceof Date) ? config.initialDate : new Date()),
						minDate: ((config.minDate !== undefined) ? (config.minDate instanceof Date
							? config.minDate : new Date((new Date()).setDate((new Date()).getDate() + config.minDate))) : undefined),
						maxDate: ((config.maxDate !== undefined) ? (config.maxDate instanceof Date
							? config.maxDate : new Date((new Date()).setDate((new Date()).getDate() + config.maxDate))) : undefined),
						disableWeekdays: (config.disableWeekdays !== undefined) ? config.disableWeekdays : false,
						disableWeekends: (config.disableWeekends !== undefined) ? config.disableWeekends : false,
						cssObj: config.cssObj ||
										{
										position: 'absolute',
										zIndex: 1
										},
						className: config.className || 'calendar',
						range:
										{
										disabledWDays: [],
										0:
														{
														name: config.months && config.months[0] ? config.months[0] : 'Enero',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										1:
														{
														name: config.months && config.months[1] ? config.months[1] : 'Febrero',
														max: 28,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										2:
														{
														name: config.months && config.months[2] ? config.months[2] : 'Marzo',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										3:
														{
														name: config.months && config.months[3] ? config.months[3] : 'Abril',
														max: 30,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										4:
														{
														name: config.months && config.months[4] ? config.months[4] : 'Mayo',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										5:
														{
														name: config.months && config.months[5] ? config.months[5] : 'Junio',
														max: 30,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										6:
														{
														name: config.months && config.months[6] ? config.months[6] : 'Julio',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										7:
														{
														name: config.months && config.months[7] ? config.months[7] : 'Agosto',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										8:
														{
														name: config.months && config.months[8] ? config.months[8] : 'Septiembre',
														max: 30,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										9:
														{
														name: config.months && config.months[9] ? config.months[9] : 'Octubre',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										10:
														{
														name: config.months && config.months[10] ? config.months[10] : 'Noviembre',
														max: 30,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										11:
														{
														name: config.months && config.months[11] ? config.months[11] : 'Deciembre',
														max: 31,
														marked: {},
														disabled: {},
														disabledWDays: [],
														comments: {},
														message: {}
														},
										wDays:
														[
														
														{
														shrt: config.days && config.days[0] ? config.days[0].s : 'D',
														lng: config.days && config.days[0] ? config.days[0].l : 'Domingo'
														},
														{
														shrt: config.days && config.days[1] ? config.days[1].s : 'L',
														lng: config.days && config.days[1] ? config.days[1].l : 'Lunes'
														},
														{
														shrt: config.days && config.days[2] ? config.days[2].s : 'M',
														lng: config.days && config.days[2] ? config.days[2].l : 'Martes'
														},
														{
														shrt: config.days && config.days[3] ? config.days[3].s : 'M',
														lng: config.days && config.days[3] ? config.days[3].l : 'Miércoles'
														},
														{
														shrt: config.days && config.days[4] ? config.days[4].s : 'J',
														lng: config.days && config.days[4] ? config.days[4].l : 'Jueves'
														},
														{
														shrt: config.days && config.days[5] ? config.days[5].s : 'V',
														lng: config.days && config.days[5] ? config.days[5].l : 'Viernes'
														},
														{
														shrt: config.days && config.days[6] ? config.days[6].s : 'S',
														lng: config.days && config.days[6] ? config.days[6].l : 'Sábado'
														}
														],
										// Change the week day offset for the calendar display
										wdOffset: 1
										},
						getWDay: function(dc, d, r){
							var d = typeof d === 'number' ? d : dc.range.current.wDay, o = dc.range.wdOffset;

							if (o < 0)
								d = (d + o) < 0 ? 7 + o : d + o;

							else if (o > 0)
								d = (d + o) > 6 ? -1 + ((d + o) - 6) : d + o;

							if (r)
								d = 6 - d;
							return d;
						},
						getDateOrdinalSuffix: function(i){
							var j = i % 10, k = i % 100;

							if (j === 1 && k !== 11){
								return i + 'st';
							}

							if (j === 2 && k !== 12){
								return i + 'nd';
							}

							if (j === 3 && k !== 13){
								return i + 'rd';
							}

							return i + 'th';
						},
						formatDate: function(dc, dateFormatTokens, dateFormat){
							if (!dateFormatTokens)
								dateFormatTokens =
												{
												'YYYY': dc.range.current.year,
												'MMMM': dc.range[dc.range.current.month].name,
												'dddd': dc.range.wDays[dc.range.current.wDay].lng,
												'MM': ('00' + (dc.range.current.month + 1)).slice(-2),
												'DD': ('00' + dc.range.current.mDay).slice(-2),
												'Do': dc.getDateOrdinalSuffix(dc.range.current.mDay),
												'M': (dc.range.current.month + 1),
												'D': dc.range.current.mDay
												};

							// if dateFormat is not specified, use component default
							if (typeof dateFormat !== 'string')
								dateFormat = dc.inputDateFormat;

							var re = new RegExp(Object.keys(dateFormatTokens).join('|'), 'gi');

							return dateFormat.replace(re, function(matched){
								return dateFormatTokens[matched];
							});
						},
						modifyDateValues: function(values, modifications){
							// Note: Months are zero based
							for (var key in modifications){
								var modification = modifications[key];

								if (key === 'month'){
									values.month += modification;

									if (modification < 0){
										// Subtraction
										if (values.month < 0){
											values.month = 11;

											if (values.year){
												values.year -= 1;
											}
										}
									}

									else{
										// Addition
										if (values.month > 11){
											values.month = 0;

											if (values.year){
												values.year += 1;
											}
										}
									}
								}
							}

							return values;
						},
						setFocus: function(o, p, s){
							var dc = this;

							if (!o)
								return false;

							this.current = o;
							$A.query('td.dayInMonth.selected', this.containerDiv, function(i, p){
								$A.setAttr(p,
												{
												tabindex: '-1'
												});

								$A.remClass(p, 'selected');
							});
							$A.addClass(o, 'selected');
							$A.setAttr(o,
											{
											tabindex: '0'
											});

							if (!s){
								if (dc.navBtn === 'PM'){
									dc.buttons.pM.focus();
									$A.announce(dc.range[dc.range.current.month].name);
									dc.navBtnS = true;
								}

								else if (dc.navBtn === 'NM'){
									dc.buttons.nM.focus();
									$A.announce(dc.range[dc.range.current.month].name);
									dc.navBtnS = true;
								}

								else if (dc.navBtn === 'PY'){
									dc.buttons.pY.focus();
									$A.announce(dc.range.current.year.toString());
									dc.navBtnS = true;
								}

								else if (dc.navBtn === 'NY'){
									dc.buttons.nY.focus();
									$A.announce(dc.range.current.year.toString());
									dc.navBtnS = true;
								}

								else{
									// Toggles for openOnFocus support.
									if (!dc.openOnFocus || (dc.openOnFocus && !onFocusInit && onFocusTraverse))
										o.focus();
								}
							}

							if (dc.fn.navBtn)
								dc.fn.navBtn = '';

							else
								dc.navBtn = '';

							return true;
						},
						setCurrent: function(dc){
							dc.range.current =
											{
											mDay: dc.date.getDate(),
											month: dc.date.getMonth(),
											year: dc.date.getFullYear(),
											wDay: dc.date.getDay()
											};
						},
						setDayMarked: function(dc, dateObj, isMarked){
							var year = dateObj.getFullYear(), month = dateObj.getMonth(), day = dateObj.getDate();

							if (isMarked){
								// initialise marked array for month if it doesn't exist
								if (typeof dc.range[month].marked[year] !== 'object'){
									dc.range[month].marked[year] = [];
								}

								// set day as marked
								dc.range[month].marked[year].push(day);
							}

							else{
								// unset day as marked
								if (typeof dc.range[month].marked[year] === 'object'){
									var arrIndex = dc.range[month].marked[year].indexOf(day);

									if (arrIndex !== -1){
										delete dc.range[month].marked[year][arrIndex];
									}
								}
							}
						},
						clearAllMarked: function(dc){
							for (var month in dc.range){
								dc.range[month].marked = {};
							}
						},
						setDayDisabled: function(dc, dateObj, isDisabled){
							var year = dateObj.getFullYear(), month = dateObj.getMonth(), day = dateObj.getDate();

							if (isDisabled){
								// initialise disabled array for month if it doesn't exist
								if (typeof dc.range[month].disabled[year] !== 'object'){
									dc.range[month].disabled[year] = [];
								}

								// set day as disabled
								dc.range[month].disabled[year].push(day);
							}

							else{
								// unset day as disabled
								if (typeof dc.range[month].disabled[year] === 'object'){
									var arrIndex = dc.range[month].disabled[year].indexOf(day);

									if (arrIndex !== -1){
										delete dc.range[month].disabled[year][arrIndex];
									}
								}
							}
						},
						setMonthDisabled: function(dc, dateObj, isDisabled){
							var year = dateObj.getFullYear(), month = dateObj.getMonth();

							if (isDisabled){
								// reset month disabled array
								dc.range[month].disabled[year] = [];

								// set each day in month as disabled
								for (var day = 1; day <= dc.range[month].max; day++){
									dc.range[month].disabled[year].push(day);
								}
							}

							else{
								// unset month as disabled
								dc.range[month].disabled[year] = [];
							}
						},
						setDayOfWeekDisabled: function(dc, dateObj, daysOfWeek, isDisabled){
							var year = dateObj.getFullYear(), month = dateObj.getMonth();

							// initialise disabled array for month if it doesn't exist
							if (typeof dc.range[month].disabled[year] !== 'object'){
								dc.range[month].disabled[year] = [];
							}

							// initialise local modifiable date that we will use to call the native getDay() method on
							var date = new Date(year, month, 1);

							for (var day = 1; day <= dc.range[month].max; day++){
								date.setDate(day);

								if (daysOfWeek.indexOf(date.getDay()) !== -1){
									if (isDisabled){
										dc.range[month].disabled[year].push(day);
									}

									else{
										// unset day as disabled
										var arrIndex = dc.range[month].marked[year].indexOf(day);

										if (arrIndex !== -1){
											delete dc.range[month].marked[year][arrIndex];
										}
									}
								}
							}
						},
						setWeekdaysDisabled: function(dc, dateObj, isDisabled){
							// 0 = Sunday, 6 = Saturday
							dc.setDayOfWeekDisabled(dc, dateObj, [1, 2, 3, 4, 5], isDisabled);
						},
						setWeekendsDisabled: function(dc, dateObj, isDisabled){
							// 0 = Sunday, 6 = Saturday, which are the days we are not setting
							dc.setDayOfWeekDisabled(dc, dateObj, [0, 6], isDisabled);
						},
						clearAllDisabled: function(dc){
							for (var month in dc.range){
								dc.range[month].disabled = {};
							}
						},
						setMonthMessage: function(dc, dateObj, message){
							var year = dateObj.getFullYear(), month = dateObj.getMonth();

							if (( typeof message === 'string') && (message.length > 0)){
								// set month message
								dc.range[month].message[year] = message;
							}

							else{
								// unset month message
								delete dc.range[month].message[year];
							}
						},
						clearAllMessage: function(dc){
							for (var month in dc.range){
								dc.range[month].message = {};
							}
						},
						isDisabledDate: function(dc, counter, dateObj, cmpObj){
							if (!cmpObj){
								cmpObj = dc.range.current;
							}

							var disabled = dc.range[cmpObj.month].disabled[cmpObj.year], disabledAll = dc.range[cmpObj.month].disabled['*'],
								disabledWDays = dc.range[cmpObj.month].disabledWDays, disabledAllWDays = dc.range.disabledWDays;

							var wkd = dateObj.getDay();

							return ! !((disabled && $A.inArray(counter, disabled) !== -1)
								|| (disabledAll && $A.inArray(counter, disabledAll) !== -1)
									|| (disabledWDays.length && $A.inArray(wkd, disabledWDays) !== -1)
									|| (disabledAllWDays.length && $A.inArray(wkd, disabledAllWDays) !== -1) || dc.isOutsideDateRange(dateObj));
						},
						isOutsideDateRange: function(dateObj){
							var dateCmp = this.createDateComparisonValue(dateObj);

							return ((this.minDateComparisonValue && (dateCmp < this.minDateComparisonValue))
								|| (this.maxDateComparisonValue && (dateCmp > this.maxDateComparisonValue)));
						},
						createDayCell: function(i, cellDateObj, cssClasses, isDisabled, isSelected){
							var dc = this;

							var cell = '<td ';

							// set correct ARIA attributes
							if (isSelected){
								cell += 'aria-current="date" ';
							}

							if (isDisabled){
								cell += 'aria-disabled="true" ';
							}

							cell += 'aria-label="';

							// draw comment?
							var comments = dc.range[dc.range.current.month].comments[dc.range.current.year],
								commentsAll = dc.range[dc.range.current.month].comments['*'];

							var comm = '';

							if (comments && comments[i])
								comm = comments[i];

							else if (commentsAll && commentsAll[i])
								comm = commentsAll[i];

							if (comm){
								cell += dc.commentedTxt.replace(/<|>|\"/g, '') + ' ';
							}

							var month = cellDateObj.getMonth();
							var dateFormatTokens =
											{
											'YYYY': cellDateObj.getFullYear(),
											'MMMM': dc.range[month].name,
											'dddd': dc.range.wDays[cellDateObj.getDay()].lng,
											'MM': ('00' + (month + 1)).slice(-2),
											'DD': ('00' + i).slice(-2),
											'Do': dc.getDateOrdinalSuffix(i),
											'M': (month + 1),
											'D': i
											};

							// set audible date value
							var re = new RegExp(Object.keys(dateFormatTokens).join('|'), 'gi');

							cell += dc.audibleDateFormat.replace(re, function(matched){
								return dateFormatTokens[matched];
							});

							if (comm){
								cell += comm.replace(/<|>|\n/g, ' ').replace(/\"/g, '\"');
							}
							cell += '" role="link" tabindex="-1" ';

							// CSS classes
							cell += 'class="day ' + (cssClasses ? cssClasses : '');

							if (dc.highlightToday === true){
								if (dc.createDateComparisonValue(cellDateObj) === dc.currentDateComparisonValue){
									cell += ' dayToday';
								}
							}

							// set date as visually marked?
							var isMarked = (dc.range[dc.range.current.month].marked[dc.range.current.year]
								&& (dc.range[dc.range.current.month].marked[dc.range.current.year].indexOf(i) !== -1));

							if ((isSelected && !isDisabled) || isMarked){
								cell += ' dayMarked';
							}

							if (isDisabled){
								cell += ' disabled';
							}

							if (comm){
								cell += ' comment';
							}
							cell += '" ';

							// Title attribute
							cell += 'title="';

							if (isMarked){
								cell += dc.markedTxt.replace(/<|>|\"/g, '');
							}

							else if (isDisabled){
								cell += dc.disabledTxt.replace(/<|>|\"/g, '');
							}

							if (comm){
								cell += ' ' + dc.commentedTxt.replace(/<|>|\"/g, '');
							}
							if(isSelected){
								cell += '" id="' + dc.baseId + i + '"><span aria-hidden="true"><strong>' + i + '</strong></span></td>';
							}else{
								cell += '" id="' + dc.baseId + i + '"><span aria-hidden="true">' + i + '</span></td>';
							}
							return cell;
						},
						createDateComparisonValue: function(dateObj){
							return parseInt((dateObj.getFullYear() + ('00' + dateObj.getMonth()).slice(-2)
								+ ('00' + dateObj.getDate()).slice(-2)), 10);
						},
						presetDate: function(dc, initialDate, minDate, maxDate){
							dc = dc || this;
							dc.initialDate = initialDate || dc.initialDate || new Date();
							dc.minDate = minDate || dc.minDate || null;
							dc.maxDate = maxDate || dc.maxDate || null;
							dc.setDateComparisons(dc);
							dc.date = dc.initialDate;
							dc.setCurrent(dc);
							dc.fn.current = {};
							$A.internal.extend(true, dc.fn.current, dc.range.current);
						},
						setDate: function(dc, dateObj){
							// if dateObj is not specified, set to an initial value...
							if (dateObj === undefined){
								// ensure initialDate value is within any set date range
								if ((dc.minDate || dc.maxDate) && dc.isOutsideDateRange(dc.initialDate)){
									// initialDate config value is outside of the valid date range, determine an optimal initial date value
									if (dc.initialDate < dc.minDate){
										dateObj = dc.minDate;
									}

									else if (dc.initialDate > dc.maxDate){
										dateObj = dc.maxDate;
									}
								}

								else{
									// set to initialDate config value
									dateObj = dc.initialDate;
								}
							}

							dc.date = dateObj;
							dc.setCurrent(dc);
							dc.fn.current = {};
							$A.internal.extend(true, dc.fn.current, dc.range.current);
						},
						setDateComparisons: function(dc){
							// If we have minDate / maxDate set, ensure they don't have time precision, and create comparison value
							if (dc.minDate instanceof Date){
								dc.minDate.setHours(0, 0, 0, 0);
								dc.minDateComparisonValue = dc.createDateComparisonValue(dc.minDate);
							}

							if (dc.maxDate instanceof Date){
								dc.maxDate.setHours(0, 0, 0, 0);
								dc.maxDateComparisonValue = dc.createDateComparisonValue(dc.maxDate);
							}

							if (dc.initialDate instanceof Date){
								dc.currentDate = dc.initialDate;
							}

							else{
								dc.currentDate = new Date();
							}
							// Cache current date for comparison
							dc.currentDateComparisonValue = dc.createDateComparisonValue(dc.currentDate);
						},
						storeCurrentDate: function(dc){
							dc.date = new Date(dc.range.current.year, dc.range.current.month, dc.range.current.mDay);
						},
						setDisabled: function(dc, s){
							if (typeof dc === "boolean"){
								s = dc;
								dc = this;
							}

							else
								dc = dc || this;
							dc.disabled = s ? true : false;
							$A.setAttr([targ, trigger], "disabled", dc.disabled);

							if (!dc.disabled)
								$A.remAttr([targ, trigger], "disabled");
						},
						updateDisabled: function(){
							var dc = this;
							$A.query('td[aria-disabled="true"]', dc.containerDiv, function(i, o){
								$A.internal.data(o, "disabled", true);
							});
						},
						checkDisabled: function(o){
							return $A.internal.data(o, "disabled") || false;
						},
						runOnceBefore: function(dc){
							if (!(dc.date instanceof Date)){
								dc.setDateComparisons(dc);
								dc.setDate(dc);
							}
						},
						runBefore: function(dc){
							// Run custom specified function?
							if (typeof config.runBefore === 'function'){
								config.runBefore(dc);
							}

							// based on config option, disable weekdays?
							if (dc.disableWeekdays){
								dc.setWeekdaysDisabled(dc, dc.date, true);
							}

							// based on config option, disable weekends?
							if (dc.disableWeekends){
								dc.setWeekendsDisabled(dc, dc.date, true);
							}

							if (config.ajax && typeof config.ajax === 'function' && !dc.stopAjax && !dc.ajaxLoading){
								dc.ajaxLoading = dc.cancel = true;
								dc.fn.navBtn = dc.navBtn;
								config.ajax.apply(dc, [dc, false]);
							}

							if (dc.range.current.month === 1)
								dc.range[1].max = (new Date(dc.range.current.year, 1, 29).getMonth() === 1) ? 29 : 28;
							dc.baseId = 'b' + $A.genId();
							dc.prevBtnId = dc.baseId + 'p';
							dc.nextBtnId = dc.baseId + 'n';
							dc.monthCellId = dc.baseId + "YC";

							// Calculate prev/next month date values, and whether they are within the allowed date range
							var prevDateValues = dc.modifyDateValues(
											{
											month: dc.range.current.month,
											year: dc.range.current.year
											},
											{
											month: -1
											});

							var prevMonth = new Date();
							prevMonth.setMonth(prevDateValues.month);
							prevMonth.setFullYear(prevDateValues.year);

							var nextDateValues = dc.modifyDateValues(
											{
											month: dc.range.current.month,
											year: dc.range.current.year
											},
											{
											month: 1
											});

							var nextMonth = new Date();
							nextMonth.setMonth(nextDateValues.month);
							nextMonth.setFullYear(nextDateValues.year);

							// Draw the year display and prev/next year buttons?
							var yearSelector = '';

							if (!config.condenseYear){
								var hasPrevYear = !dc.isOutsideDateRange(new Date((dc.range.current.year - 1), 0, 1)),
									hasNextYear = !dc.isOutsideDateRange(new Date((dc.range.current.year + 1), 0, 1));

								yearSelector = '<tr class="yearSelector" role="presentation">' +
									'<td class="nav prev btn year' + (!hasPrevYear ? ' disabled' : '') + '" accesskey="1" title="'
									+ dc.prevTxt.replace(/<|>|\"/g, '') + ' '
									+ dc.yearTxt.replace(/<|>|\"/g, '') + '" aria-label="' + dc.prevTxt.replace(/<|>|\"/g, '') + ' '
									+ dc.yearTxt.replace(/<|>|\"/g, '') + '"'
									+ (!hasPrevYear ? ' aria-disabled="true" tabindex="-1"' : ' tabindex="0"') + ' role="button" id="'
									+ dc.prevBtnId + 'Y"><span aria-hidden="true">' + dc.leftButtonYearText + '</span></td>' +
									'<td title="'
									+ dc.tooltipTxt.replace(/<|>|\"/g, '') + '" colspan="5" class="year" role="presentation"><span>'
									+ dc.range.current.year + '</span></td>' +
									'<td class="nav next nav prev btn' + (!hasNextYear ? ' disabled' : '') + '" accesskey="2" title="'
									+ dc.nextTxt.replace(/<|>|\"/g, '') + ' '
									+ dc.yearTxt.replace(/<|>|\"/g, '') + '" aria-label="' + dc.nextTxt.replace(/<|>|\"/g, '') + ' '
									+ dc.yearTxt.replace(/<|>|\"/g, '') + '"'
									+ (!hasNextYear ? ' aria-disabled="true" tabindex="-1"' : ' tabindex="0"') + ' role="button" id="'
									+ dc.nextBtnId + 'Y"><span aria-hidden="true">' + dc.rightButtonYearText + '</span></td></tr>';
							}

							// Draw the month display and prev/next month buttons
							var hasPrevMonth =
								!dc.isOutsideDateRange(new Date(prevDateValues.year, prevDateValues.month, dc.range[prevDateValues.month].max)),
								hasNextMonth = !dc.isOutsideDateRange(new Date(nextDateValues.year, nextDateValues.month, 1));

							var monthSelector = '<tr class="monthSelector" role="presentation">' +
								'<td class="nav prev btn month' + (!hasPrevMonth ? ' disabled' : '') + '" accesskey="3" title="'
								+ dc.prevTxt.replace(/<|>|\"/g, '') + ' ' + dc.monthTxt.replace(/<|>|\"/g, '') + '" aria-label="'
								+ dc.prevTxt.replace(/<|>|\"/g, '') + ' ' + dc.monthTxt.replace(/<|>|\"/g, '') + '"'
								+ (!hasPrevMonth ? ' aria-disabled="true" tabindex="-1"' : ' tabindex="0"') + ' role="button" id="'
								+ dc.prevBtnId + '"><span aria-hidden="true">' + dc.leftButtonMonthText + '</span></td>' +
								'<td colspan="5" class="month" id="' + dc.monthCellId +
								'" role="presentation"><span>'
								+ dc.range[dc.range.current.month].name + (!config.condenseYear ? '' : ' ' + dc.range.current.year)
								+ '</span></td>' +
								'<td class="nav next btn month' + (!hasNextMonth ? ' disabled' : '') + '" accesskey="4" title="'
								+ dc.nextTxt.replace(/<|>|\"/g, '') + ' ' + dc.monthTxt.replace(/<|>|\"/g, '') + '" aria-label="'
								+ dc.nextTxt.replace(/<|>|\"/g, '') + ' ' + dc.monthTxt.replace(/<|>|\"/g, '') + '"'
								+ (!hasNextMonth ? ' aria-disabled="true" tabindex="-1"' : ' tabindex="0"') + ' role="button" id="'
								+ dc.nextBtnId + '"><span aria-hidden="true">' + dc.rightButtonMonthText + '</span></td></tr>';

							dc.source = '';

							// Start constructing the Datepicker table element
							// Reconfigured for Esc btn processing
							dc.source += '<table role="presentation" class="calendar">' + yearSelector + monthSelector
								+ '<tr role="presentation">';
							dc.iter = 0;

							// Draw day headers
							for (var i = 0; i < 7; i++){
								var di = dc.getWDay(dc, i), d = dc.range.wDays[di];

								if (!i){
									dc.iter = dc.iterE = (di + 6) > 6 ? -1 + di : di + 6;
									dc.iterS = di;
								}
								dc.source += '<th scope="col" class="week" title="' + d.lng + '" role="presentation"><span>' + d.shrt
									+ '</span></th>';
							}
							dc.source += '</tr><tr role="presentation">';

							// Start drawing day cells
							var m = new Date();
							m.setDate(1);
							m.setMonth(dc.range.current.month);
							m.setFullYear(dc.range.current.year);

							var f = m.getDay();
							m.setDate(dc.range[dc.range.current.month].max);
							var e = m.getDay(), w = dc.iterS;

							// Draw the full calendar? (a full grid containing previous / next month cells)
							if (dc.drawFullCalendar === true){
								var daysInMonth = (new Date(prevDateValues.year, (prevDateValues.month + 1), 0)).getDate(),
									counter = (daysInMonth - (new Date(dc.range.current.year, dc.range.current.month, 0)).getDay()
										+ dc.range.wdOffset);
							}

							while (w !== f){
								w = (w + 1) > 6 ? 0 : w + 1;

								if (dc.drawFullCalendar === true){
									prevMonth.setDate(counter);
									dc.source += dc.createDayCell(counter, prevMonth, 'dayInPrevMonth', dc.isDisabledDate(dc, counter, prevMonth));
									++counter;
								}

								else{
									dc.source += '<td class="empty" role="presentation"><span>&nbsp;</span></td>';
								}
							}

							dc.range.track = {};

							for (var i = 1; i <= 31; i++){
								dc.range.track[dc.baseId + i] = i;
							}

							for (var i = 1; i <= dc.range[dc.range.current.month].max; i++){
								m.setDate(i);

								var isSelected = ((i === dc.fn.current.mDay) && (dc.range.current.month === dc.fn.current.month)
									&& (dc.range.current.year === dc.fn.current.year));

								// Draw calendar day cell
								dc.source += dc.createDayCell(i, m, 'dayInMonth', dc.isDisabledDate(dc, i, m), isSelected);

								var w = m.getDay();

								if (w === dc.iter && i < dc.range[dc.range.current.month].max)
									dc.source += '</tr><tr role="presentation">';
							}

							if (dc.drawFullCalendar === true){
								var counter = 1;
							}

							while (e !== dc.iter){
								e = (e + 1) > 6 ? 0 : e + 1;

								if (dc.drawFullCalendar === true){
									nextMonth.setDate(counter);
									dc.source += dc.createDayCell(counter, nextMonth, 'dayInNextMonth', dc.isDisabledDate(dc, counter, nextMonth));
									++counter;
								}

								else{
									dc.source += '<td class="empty" role="presentation"><span>&nbsp;</span></td>';
								}
							}
							dc.source += '</tr></table>';

							// if a message is set for the month, draw it
							if (dc.range[dc.range.current.month].message[dc.range.current.year]){
								dc.source += '<div class="monthMessage">' +
									'	<p>' + dc.range[dc.range.current.month].message[dc.range.current.year] + '</p>' +
									'</div>';
							}

							// Reconfigured for Esc btn processing
							if (dc.showEscBtn){
								dc.source += '<button id="' + dc.baseId + 'esc" aria-label="' + dc.escBtnName + '" title="' + dc.escBtnName
									+ '" class="esc-button">' + dc.escBtnIcon + '</button>';
							}

							// Close other calendar pickers that are currently open
							$A.find('*', function(dc){
								if (dc.controlType && dc.controlType === 'DatePicker' && dc.loaded)
									dc.close();
							});
						},
						click: function(ev, dc){
							ev.stopPropagation();
						},
						keyDown: function(ev, dc){
							var k = ev.which || ev.keyCode;

							if (k === 72){
								$A.announce(dc.helpText);
								ev.preventDefault();
								ev.stopPropagation();
							}
						},
						runDuring: function(dc){
							dc.datepickerLoaded = false;
							$A.bind('body', 'click.datepicker', function(ev){
								if (dc.datepickerLoaded)
									dc.close();
							});

							$A.setAttr(dc.accDCObj,
											{
											role: 'dialog',
											'data-helptext': dc.helpText,
											'aria-label': dc.range[dc.range.current.month].name
											});

							// Reconfigured for Esc btn processing
							$A.setAttr(dc.containerDiv,
											{
											role: 'application'
											});

							// Reconfigured for Esc btn processing
							if (dc.showEscBtn){
								dc.escBtn = $A.query('button.esc-button', dc.containerDiv)[0];
							}

							dc.fn.sraStart.innerHTML = dc.fn.sraEnd.innerHTML = '';
							$A.setAttr(dc.fn.sraStart,
											{
											'aria-hidden': 'true'
											});

							$A.setAttr(dc.fn.sraEnd,
											{
											'aria-hidden': 'true'
											});
						},
						runAfter: function(dc){
							dc.buttons =
											{
											pY: config.condenseYear ? null : $A.getEl(dc.prevBtnId + 'Y'),
											nY: config.condenseYear ? null : $A.getEl(dc.nextBtnId + 'Y'),
											pM: $A.getEl(dc.prevBtnId),
											nM: $A.getEl(dc.nextBtnId)
											};

							if (!config.condenseYear && dc.disableNavPrevYearBtn)
								$A.setAttr(dc.buttons.pY, 'aria-disabled', 'true');

							if (!config.condenseYear && dc.disableNavNextYearBtn)
								$A.setAttr(dc.buttons.nY, 'aria-disabled', 'true');

							if (dc.disableNavPrevMonthBtn)
								$A.setAttr(dc.buttons.pM, 'aria-disabled', 'true');

							if (dc.disableNavNextMonthBtn)
								$A.setAttr(dc.buttons.nM, 'aria-disabled', 'true');

							if (!dc.prevCurrent)
								dc.prevCurrent = {};
							$A.internal.extend(true, dc.prevCurrent, dc.range.current);

							dc.updateDisabled();

							var nMonth = function(){
								if (dc.disableNavNextMonthBtn && (dc.checkDisabled(dc.buttons.nM)))
									return;

								$A.internal.extend(true, dc.prevCurrent, dc.range.current);

								var dateValues = dc.modifyDateValues(
												{
												month: dc.range.current.month,
												year: dc.range.current.year
												},
												{
												month: 1
												});

								// Only change to next month if its first day is inside the valid date range
								if (!dc.isOutsideDateRange(new Date(dateValues.year, dateValues.month, 1))){
									var day = dc.range.current.mDay
										> dc.range[dateValues.month].max ? dc.range[dateValues.month].max : dc.range.current.mDay,
										intendedDate = new Date(dateValues.year, dateValues.month, day);

									// If intended selected date one month ahead is outside of date range, set
									// selected date to the next available date
									if (dc.isOutsideDateRange(intendedDate))
										dc.date = dc.maxDate;

									else
										dc.date = intendedDate;
								}

								else{
									dc.date = dc.maxDate;
								}

								dc.setCurrent(dc);
								dc.reopen = true;
								dc.open();
							}, pMonth = function(){
								if (dc.disableNavPrevMonthBtn && (dc.checkDisabled(dc.buttons.pM)))
									return;

								$A.internal.extend(true, dc.prevCurrent, dc.range.current);

								var dateValues = dc.modifyDateValues(
												{
												month: dc.range.current.month,
												year: dc.range.current.year
												},
												{
												month: -1
												});

								// Only change to previous month if its last day is inside the valid date range
								if (!dc.isOutsideDateRange(new Date(dateValues.year, dateValues.month, dc.range[dateValues.month].max))){
									var day = dc.range.current.mDay
										> dc.range[dateValues.month].max ? dc.range[dateValues.month].max : dc.range.current.mDay,
										intendedDate = new Date(dateValues.year, dateValues.month, day);

									// If intended selected date one month previously is outside of date range, set
									// selected date to the next available date
									if (dc.isOutsideDateRange(intendedDate))
										dc.date = dc.minDate;

									else
										dc.date = intendedDate;
								}

								else{
									dc.date = dc.minDate;
								}

								dc.setCurrent(dc);
								dc.reopen = true;
								dc.open();
							}, gYear = function(forward){
								if ((!forward && ((!config.condenseYear && dc.checkDisabled(dc.buttons.pY))
									|| (config.condenseYear && dc.disableNavPrevYearBtn)))){

									return;
								}

								else if ((forward && ((!config.condenseYear && dc.checkDisabled(dc.buttons.nY))
									|| (config.condenseYear && dc.disableNavNextYearBtn)))){

									return;
								}

								$A.internal.extend(true, dc.prevCurrent, dc.range.current);

								var month = dc.range.current.month, year = forward ? dc.range.current.year + 1 : dc.range.current.year - 1;

								if (month === 1)
									dc.range[1].max = 28;
								var day = dc.range.current.mDay > dc.range[month].max ? dc.range[month].max : dc.range.current.mDay;

								// Only change year if the intended date is inside of any set date range
								var intendedDate = new Date(year, month, day);

								if (dc.isOutsideDateRange(intendedDate)){
									return;
								}

								dc.date = intendedDate;
								dc.setCurrent(dc);
								dc.reopen = true;
								dc.open();
							};
							var isKP = false;
							$A.bind('#' + dc.containerDivId + ' td.day',
											{
											focus: function(ev){
												if ($A.hasClass(this, 'comment')){
													var tooltipDC = dc.children[0], year = dc.range[dc.range.current.month].comments[dc.range.current.year],
														all = dc.range[dc.range.current.month].comments['*'], comm = '';

													if (year && year[dc.range.current.mDay])
														comm = year[dc.range.current.mDay];

													else if (all && all[dc.range.current.mDay])
														comm = all[dc.range.current.mDay];
													comm = trim(comm.replace(/<|>/g, ''));

													if (comm){
														tooltipDC.source = comm;
														tooltipDC.open();
													}
												}

												else if (dc.children[0].loaded)
													dc.children[0].close();

												if (dc.children[1].openEditor){
													dc.children[1].openEditor = false;
													dc.children[1].reset();
												}
											},
											click: function(ev){
												// If items from a previous / next month are selected, modify the date accordingly
												if ($A.hasClass(this, 'dayInPrevMonth')){
													var prevDateValues = dc.modifyDateValues(
																	{
																	month: dc.range.current.month,
																	year: dc.range.current.year
																	},
																	{
																	month: -1
																	});

													dc.date = new Date(prevDateValues.year, prevDateValues.month, dc.range.track[this.id]);
												}

												else if ($A.hasClass(this, 'dayInNextMonth')){
													var nextDateValues = dc.modifyDateValues(
																	{
																	month: dc.range.current.month,
																	year: dc.range.current.year
																	},
																	{
																	month: 1
																	});

													dc.date = new Date(nextDateValues.year, nextDateValues.month, dc.range.track[this.id]);
												}

												else{
													// Selection in current month, just adjust the date
													dc.date.setDate(dc.range.track[this.id]);
												}

												dc.setCurrent(dc);

												if ($A.hasClass(this, 'selected') || (!commentsEnabled && !$A.hasClass(this, 'comment'))){
													if (!dc.checkDisabled(this)){
														$A.internal.extend(true, dc.fn.current, dc.range.current);
														// Toggles for openOnFocus support.
														onFocusInit = false;
														onFocusTraverse = true;
														dc.storeCurrentDate(dc);
														handleClick.apply(this, [ev, dc, targ]);
													}

													else{
														ev.stopPropagation();
														ev.preventDefault();
													}
												}

												else
													dc.setFocus(this);
												ev.preventDefault();
											},
											keydown: function(ev){
												changePressed(ev);
												var k = ev.which || ev.keyCode;

												if (k === 13){
													isKP = true;

													if (!dc.checkDisabled(this)){
														$A.internal.extend(true, dc.fn.current, dc.range.current);
														// Toggles for openOnFocus support.
														onFocusInit = false;
														onFocusTraverse = true;
														dc.storeCurrentDate(dc);
														handleClick.apply(this, [ev, dc, targ]);
													}

													ev.preventDefault();
												}

												else if (k === 32 && commentsEnabled && config.editor && config.editor.show && !dc.children[1].openEditor){
													dc.children[1].openEditor = true;
													dc.children[1].reset();
													ev.preventDefault();
												}

												else if ((k >= 37 && k <= 40) || k === 27 || (k >= 33 && k <= 36)){
													var wd = dc.range.current.wDay;

													if (k === 37){
														// Left arrow key
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if (dc.range.current.mDay > 1){
															dc.range.current.mDay--;
															dc.range.current.wDay = !wd ? 6 : wd - 1;

															dc.setFocus(dc.range.index[dc.range.current.mDay - 1], this);
														}

														else if (dc.range.current.mDay === 1 && !dc.checkDisabled(dc.buttons.pM)){

															var dateValues = dc.modifyDateValues(
																			{
																			month: dc.range.current.month,
																			year: dc.range.current.year
																			},
																			{
																			month: -1
																			});

															var day = dc.range[dateValues.month].max;

															if (dateValues.month === 1)
																day = (new Date(dateValues.year, 1, 29).getMonth() === 1) ? 29 : 28;

															dc.date = new Date(dateValues.year, dateValues.month, day);
															dc.setCurrent(dc);
															dc.reopen = true;
															dc.open();
														}
													}

													else if (k === 39){
														// Right arrow key
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if (dc.range.current.mDay < dc.range[dc.range.current.month].max){
															dc.range.current.mDay++;
															dc.range.current.wDay = wd === 6 ? 0 : wd + 1;

															dc.setFocus(dc.range.index[dc.range.current.mDay - 1], this);
														}

														else if (dc.range.current.mDay === dc.range[dc.range.current.month].max
															&& !dc.checkDisabled(dc.buttons.nM)){

															var dateValues = dc.modifyDateValues(
																			{
																			month: dc.range.current.month,
																			year: dc.range.current.year
																			},
																			{
																			month: 1
																			});

															dc.date = new Date(dateValues.year, dateValues.month, 1);
															dc.setCurrent(dc);
															dc.reopen = true;
															dc.open();
														}
													}

													else if (k === 38){
														// Up arrow key
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if ((dc.range.current.mDay - 7) > 0){
															dc.range.current.mDay -= 7;

															dc.setFocus(dc.range.index[dc.range.current.mDay - 1], this);
														}

														else if (!dc.checkDisabled(dc.buttons.pM)){
															// Go to previous month
															var dateValues = dc.modifyDateValues(
																			{
																			month: dc.range.current.month,
																			year: dc.range.current.year
																			},
																			{
																			month: -1
																			});

															if (dateValues.month === 1 && (new Date(dateValues.year, 1, 29).getMonth() === 1))
																dc.range[dateValues.month].max = 29;

															else if (dateValues.month === 1)
																dc.range[dateValues.month].max = 28;

															var day = dc.range[dateValues.month].max + (dc.range.current.mDay - 7),
																intendedDate = new Date(dateValues.year, dateValues.month, day);

															// If intended selected date one month previous is outside of date range, do not attempt
															// to select the date cell
															if (!dc.isOutsideDateRange(intendedDate)){
																dc.date = intendedDate;
																dc.setCurrent(dc);
																dc.reopen = true;
																dc.open();
															}
														}
													}

													else if (k === 40){
														// Down arrow key
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if ((dc.range.current.mDay + 7) <= dc.range[dc.range.current.month].max){
															dc.range.current.mDay += 7;

															dc.setFocus(dc.range.index[dc.range.current.mDay - 1], this);
														}

														else if (!dc.checkDisabled(dc.buttons.nM)){
															// Go to next month
															var dateValues = dc.modifyDateValues(
																			{
																			month: dc.range.current.month,
																			year: dc.range.current.year
																			},
																			{
																			month: 1
																			});

															var day = dc.range.current.mDay + 7 - dc.range[dc.range.current.month].max,
																intendedDate = new Date(dateValues.year, dateValues.month, day);

															// If intended selected date one month ahead is outside of date range, do not attempt
															// to select the date cell
															if (!dc.isOutsideDateRange(intendedDate)){
																dc.date = intendedDate;
																dc.setCurrent(dc);
																dc.reopen = true;
																dc.open();
															}
														}
													}

													else if (k === 27){
														// Esc key
														dc.close();
														// Toggles for openOnFocus support.
														onFocusInit = false;
														onFocusTraverse = true;
														targ.focus();
													}

													else if (k === 33){
														// PageUp key
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if (dc.pageUpDownNatural === true){
															if (pressed.alt){
																gYear(false);
															}

															else{
																pMonth();
															}
														}

														else{
															if (pressed.alt){
																gYear(true);
															}

															else{
																nMonth();
															}
														}
													}

													else if (k === 34){
														// PageDown key
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if (dc.pageUpDownNatural === true){
															if (pressed.alt){
																gYear(true);
															}

															else{
																nMonth();
															}
														}

														else{
															if (pressed.alt){
																gYear(false);
															}

															else{
																pMonth();
															}
														}
													}

													else if (k === 36){
														// Home key (goes to the first day of the row)
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if (wd !== dc.iterS && dc.range.current.mDay > 1){
															while (dc.range.current.wDay !== dc.iterS && $A.getEl(dc.baseId + (dc.range.current.mDay - 1))){
																dc.range.current.wDay = (dc.range.current.wDay - 1) < 0 ? 6 : dc.range.current.wDay - 1;
																dc.range.current.mDay--;
															}
															dc.setFocus(dc.range.index[dc.range.current.mDay - 1], this);
														}
													}

													else if (k === 35){
														// End key (goes to the last day of the row)
														$A.internal.extend(true, dc.prevCurrent, dc.range.current);

														if (wd !== dc.iterE && dc.range.current.mDay < dc.range[dc.range.current.month].max){
															while (dc.range.current.wDay !== dc.iterE && $A.getEl(dc.baseId + (dc.range.current.mDay + 1))){
																dc.range.current.wDay = (dc.range.current.wDay + 1) > 6 ? 0 : dc.range.current.wDay + 1;
																dc.range.current.mDay++;
															}
															dc.setFocus(dc.range.index[dc.range.current.mDay - 1], this);
														}
													}
													ev.preventDefault();
												}

												else if (k === 9 && !pressed.alt && !pressed.ctrl && !pressed.shift){
													// Tab key (without any simultaneous modifiers Alt / Ctrl / Shift)
													$A.internal.extend(true, dc.prevCurrent, dc.range.current);

													// Reconfigured for Esc btn processing
													if (!dc.showEscBtn){
														if (!config.condenseYear && !dc.checkDisabled(dc.buttons.pY))
															dc.buttons.pY.focus();

														else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.nY))
															dc.buttons.nY.focus();

														else if (!dc.checkDisabled(dc.buttons.pM))
															dc.buttons.pM.focus();

														else if (!dc.checkDisabled(dc.buttons.nM))
															dc.buttons.nM.focus();

														ev.preventDefault();
													}
												}

												else if (k === 9 && !pressed.alt && !pressed.ctrl && pressed.shift){
													// Tab key (with simultaneous Shift modifier)
													$A.internal.extend(true, dc.prevCurrent, dc.range.current);

													if (!dc.checkDisabled(dc.buttons.nM))
														dc.buttons.nM.focus();

													else if (!dc.checkDisabled(dc.buttons.pM))
														dc.buttons.pM.focus();

													else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.nY))
														dc.buttons.nY.focus();

													else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.pY))
														dc.buttons.pY.focus();

													ev.preventDefault();
												}
											},
											keyup: function(ev){
												changePressed(ev);
												var k = ev.which || ev.keyCode;

												if (k === 13 && !isKP && !dc.isAdd){
													if (!dc.checkDisabled(this)){
														$A.internal.extend(true, dc.fn.current, dc.range.current);

														if (!dc.setFocus.firstOpen){
															// Toggles for openOnFocus support.
															onFocusInit = false;
															onFocusTraverse = true;
															dc.storeCurrentDate(dc);
															handleClick.apply(this, [ev, dc, targ]);
														}
													}

													ev.preventDefault();
												}

												isKP = dc.setFocus.firstOpen = dc.isAdd = false;
											}
											});

							// Reconfigured for Esc btn processing
							if (dc.showEscBtn){
								$A.bind(dc.escBtn,
												{
												click: function(ev){
													dc.close();
													onFocusInit = false;
													onFocusTraverse = true;
													targ.focus();
													ev.preventDefault();
												},
												keydown: function(ev){
													changePressed(ev);
													var k = ev.which || ev.keyCode;

													if (k === 27 || k === 13 || k === 32){
														dc.close();
														onFocusInit = false;
														onFocusTraverse = true;
														targ.focus();
													}

													else if (k === 9 && !pressed.alt && !pressed.ctrl && !pressed.shift){
														// Tab key (without any simultaneous modifiers Alt / Ctrl / Shift)

														if (dc.showEscBtn){
															if (!config.condenseYear && !dc.checkDisabled(dc.buttons.pY))
																dc.buttons.pY.focus();

															else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.nY))
																dc.buttons.nY.focus();

															else if (!dc.checkDisabled(dc.buttons.pM))
																dc.buttons.pM.focus();

															else if (!dc.checkDisabled(dc.buttons.nM))
																dc.buttons.nM.focus();
														}

														ev.preventDefault();
													}
												},
												keyup: function(ev){
													changePressed(ev);
												},
												focus: function(ev){},
												blur: function(ev){}
												});
							}

							$A.bind(dc.buttons.pM,
											{
											click: function(ev){
												dc.navBtn = 'PM';
												pMonth();
												ev.preventDefault();
											},
											keydown: function(ev){
												changePressed(ev);
												var k = ev.which || ev.keyCode;

												if (k === 13 || k === 32){
													dc.navBtn = 'PM';
													pMonth();
													ev.preventDefault();
												}

												else if (k === 27){
													dc.close();
													// Toggles for openOnFocus support.
													onFocusInit = false;
													onFocusTraverse = true;
													targ.focus();
													ev.preventDefault();
												}

												else if (!config.condenseYear && k === 38){
													dc.buttons.pY.focus();
													ev.preventDefault();
												}

												else if (k === 39){
													dc.buttons.nM.focus();
													ev.preventDefault();
												}

												else if (k === 37 || k === 40){
													ev.preventDefault();
												}

												else if (k === 9 && !pressed.alt && !pressed.ctrl && !pressed.shift){
													if (!dc.checkDisabled(dc.buttons.nM))
														dc.buttons.nM.focus();

													else
														$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();

													ev.preventDefault();
												}

												else if (k === 9 && !pressed.alt && !pressed.ctrl && pressed.shift){
													if (!config.condenseYear && !dc.checkDisabled(dc.buttons.nY))
														dc.buttons.nY.focus();

													else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.pY))
														dc.buttons.pY.focus();

													else{
														// Reconfigured for Esc btn processing
														if (dc.showEscBtn)
															dc.escBtn.focus();

														else
															$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();
													}

													ev.preventDefault();
												}
											},
											keyup: function(ev){
												changePressed(ev);
											}
											});
							$A.bind(dc.buttons.nM,
											{
											click: function(ev){
												dc.navBtn = 'NM';
												nMonth();
												ev.preventDefault();
											},
											keydown: function(ev){
												changePressed(ev);
												var k = ev.which || ev.keyCode;

												if (k === 13 || k === 32){
													dc.navBtn = 'NM';
													nMonth();
													ev.preventDefault();
												}

												else if (k === 27){
													dc.close();
													// Toggles for openOnFocus support.
													onFocusInit = false;
													onFocusTraverse = true;
													targ.focus();
													ev.preventDefault();
												}

												else if (!config.condenseYear && k === 38){
													dc.buttons.nY.focus();
													ev.preventDefault();
												}

												else if (k === 37){
													dc.buttons.pM.focus();
													ev.preventDefault();
												}

												else if (k === 39 || k === 40){
													ev.preventDefault();
												}

												else if (k === 9 && !pressed.alt && !pressed.ctrl && !pressed.shift){
													$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();
													ev.preventDefault();
												}

												else if (k === 9 && !pressed.alt && !pressed.ctrl && pressed.shift){
													if (!dc.checkDisabled(dc.buttons.pM))
														dc.buttons.pM.focus();

													else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.nY))
														dc.buttons.nY.focus();

													else if (!config.condenseYear && !dc.checkDisabled(dc.buttons.pY))
														dc.buttons.pY.focus();

													else{
														// Reconfigured for Esc btn processing
														if (dc.showEscBtn)
															dc.escBtn.focus();

														else
															$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();
													}

													ev.preventDefault();
												}
											},
											keyup: function(ev){
												changePressed(ev);
											}
											});

							if (!config.condenseYear)
								$A.bind(dc.buttons.pY,
												{
												click: function(ev){
													dc.navBtn = 'PY';
													gYear();
													ev.preventDefault();
												},
												keydown: function(ev){
													changePressed(ev);
													var k = ev.which || ev.keyCode;

													if (k === 13 || k === 32){
														dc.navBtn = 'PY';
														gYear();
														ev.preventDefault();
													}

													else if (k === 27){
														dc.close();
														// Toggles for openOnFocus support.
														onFocusInit = false;
														onFocusTraverse = true;
														targ.focus();
														ev.preventDefault();
													}

													else if (k === 39){
														dc.buttons.nY.focus();
														ev.preventDefault();
													}

													else if (k === 40){
														dc.buttons.pM.focus();
														ev.preventDefault();
													}

													else if (k === 37 || k === 38){
														ev.preventDefault();
													}

													else if (k === 9 && !pressed.alt && !pressed.ctrl && !pressed.shift){
														if (!dc.checkDisabled(dc.buttons.nY))
															dc.buttons.nY.focus();

														else if (!dc.checkDisabled(dc.buttons.pM))
															dc.buttons.pM.focus();

														else if (!dc.checkDisabled(dc.buttons.nM))
															dc.buttons.nM.focus();

														else
															$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();

														ev.preventDefault();
													}

													else if (k === 9 && !pressed.alt && !pressed.ctrl && pressed.shift){
														// Reconfigured for Esc btn processing
														if (dc.showEscBtn)
															dc.escBtn.focus();

														else
															$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();
														ev.preventDefault();
													}
												},
												keyup: function(ev){
													changePressed(ev);
												}
												});

							if (!config.condenseYear)
								$A.bind(dc.buttons.nY,
												{
												click: function(ev){
													dc.navBtn = 'NY';
													gYear(true);
													ev.preventDefault();
												},
												keydown: function(ev){
													changePressed(ev);
													var k = ev.which || ev.keyCode;

													if (k === 13 || k === 32){
														dc.navBtn = 'NY';
														gYear(true);
														ev.preventDefault();
													}

													else if (k === 27){
														dc.close();
														// Toggles for openOnFocus support.
														onFocusInit = false;
														onFocusTraverse = true;
														targ.focus();
														ev.preventDefault();
													}

													else if (k === 37){
														dc.buttons.pY.focus();
														ev.preventDefault();
													}

													else if (k === 40){
														dc.buttons.nM.focus();
														ev.preventDefault();
													}

													else if (k === 38 || k === 39){
														ev.preventDefault();
													}

													else if (k === 9 && !pressed.alt && !pressed.ctrl && !pressed.shift){
														if (!dc.checkDisabled(dc.buttons.pM))
															dc.buttons.pM.focus();

														else if (!dc.checkDisabled(dc.buttons.nM))
															dc.buttons.nM.focus();

														else
															$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();

														ev.preventDefault();
													}

													else if (k === 9 && !pressed.alt && !pressed.ctrl && pressed.shift){
														if (!dc.checkDisabled(dc.buttons.pY))
															dc.buttons.pY.focus();

														else{
															// Reconfigured for Esc btn processing
															if (dc.showEscBtn)
																dc.escBtn.focus();

															else
																$A.query('td.day[tabindex="0"]', dc.containerDiv)[0].focus();
														}

														ev.preventDefault();
													}
												},
												keyup: function(ev){
													changePressed(ev);
												}
												});

							dc.range.index = $A.query('td.dayInMonth', dc.containerDiv);
							dc.setFocus.firstOpen = true;

							dc.setFocus(dc.range.index[dc.range.current.mDay - 1]);

							if (commentsEnabled && config.editor && config.editor.show)
								dc.children[1].open();

							$A.bind(window, 'resize.datepicker', function(ev){
								dc.setPosition();
							});

							if (dc.openOnFocus)
								$A.setAttr(targ, 'aria-expanded', 'true');

							$A.setAttr(dc.triggerObj, 'aria-expanded', 'true');
							setTimeout(function(){
								dc.datepickerLoaded = true;
							}, 750);

							if (!dc.navBtnS){
								if (!('ontouchstart' in window || navigator.maxTouchPoints > 0 || navigator.msMaxTouchPoints > 0)){
									// Toggles for openOnFocus support.
									if (!config.openOnFocus || (config.openOnFocus === true && !onFocusInit && onFocusTraverse)){
										if (!dc.setFocus.firstOpen)
											$A.announce(dc.helpTextShort);
									}
								}
							}
							dc.navBtnS = false;
						},
						helpTextShort: helpTextShort,
						helpText: helpText,
						runAfterClose: function(dc){
							if (!dc.reopen){
								if (config.resetCurrent){
									dc.date = new Date();
									dc.setCurrent(dc);
									$A.internal.extend(true, dc.fn.current, dc.range.current);
								}

								if (commentsEnabled)
									dc.children[0].close();

								if (commentsEnabled && config.editor && config.editor.show){
									dc.children[1].lock = false;
									dc.children[1].close();
								}
							}

							else
								dc.reopen = false;

							if (config.ajax && typeof config.ajax === 'function')
								dc.lock = dc.ajaxLoading = false;

							$A.unbind(window, '.datepicker');
							$A.unbind('body', '.datepicker');

							$A.setAttr(dc.triggerObj, 'aria-expanded', 'false');

							if (dc.openOnFocus)
								$A.setAttr(targ, 'aria-expanded', 'false');

							// Run custom specified function?
							if (typeof config.runAfterClose === 'function'){
								config.runAfterClose(dc);
							}
						}
						}
						], config.overrides);
		// Calendar object declaration end

		// Comment object declaration start
		$A($A.reg[pId],
						[
						{
						id: pId + 'commentTooltip',
						role: config.comments && config.comments.role || 'Comment',
						returnFocus: false,
						showHiddenClose: false,
						allowReopen: true,
						autoPosition: isNaN(config.comments && config.comments.autoPosition) ? 1 : config.comments.autoPosition,
						offsetTop: isNaN(config.comments && config.comments.offsetTop) ? 0 : config.comments.offsetTop,
						offsetLeft: isNaN(config.comments && config.comments.offsetLeft) ? 0 : config.comments.offsetLeft,
						cssObj:
										{
										position: 'absolute',
										zIndex: $A.reg[pId].cssObj.zIndex
										},
						className: config.comments && config.comments.className || 'commentTooltip',
						runBefore: function(dc){
							dc.triggerObj = dc.parent.accDCObj;
						}
						}
						]);
		// Comment object declaration end

		// Form object declaration start
		$A($A.reg[pId],
						[
						{
						id: pId + 'commentAdd',
						role: config.editor && config.editor.role || 'Edit',
						returnFocus: false,
						allowReopen: true,
						autoPosition: isNaN(config.editor && config.editor.autoPosition) ? 6 : config.editor.autoPosition,
						offsetTop: isNaN(config.editor && config.editor.offsetTop) ? 0 : config.editor.offsetTop,
						offsetLeft: isNaN(config.editor && config.editor.offsetLeft) ? 0 : config.editor.offsetLeft,
						cssObj:
										{
										position: 'absolute',
										zIndex: $A.reg[pId].cssObj.zIndex
										},
						className: config.editor && config.editor.className || 'commentAdd',
						openEditor: false,
						source: '<textarea style="visibility: hidden; display: none;" class="commentTa" title="'
							+ $A.reg[pId + 'commentTooltip'].role + '"></textarea><button title="'
							+ (config.editor && config.editor.role || 'Edit') + ' ' + $A.reg[pId + 'commentTooltip'].role
								+ '" class="commentBtn">' + (config.editor && config.editor.role || 'Edit') + '</button>',
						runBefore: function(dc){
							dc.triggerObj = dc.parent.accDCObj;
						},
						click: function(ev, dc){
							ev.stopPropagation();
						},
						runDuring: function(dc){
							$A.setAttr(dc.accDCObj,
											{
											role: 'dialog',
											'aria-label': dc.role
											});

							$A.setAttr(dc.containerDiv, 'role', 'application');

							dc.fn.sraStart.innerHTML = dc.fn.sraEnd.innerHTML = '';
							$A.setAttr(dc.fn.sraStart,
											{
											'aria-hidden': 'true'
											});

							$A.setAttr(dc.fn.sraEnd,
											{
											'aria-hidden': 'true'
											});
						},
						add: function(dc){
							var comm = trim(dc.textarea.value.replace(/<|>|\n/g, ' '));

							if (!dc.comments[dc.parent.range.current.year])
								dc.comments[dc.parent.range.current.year] = {};
							dc.comments[dc.parent.range.current.year][dc.parent.range.current.mDay] = comm;
							var lbl = dc.parent.range.current.mDay + ', ' + dc.parent.range.wDays[dc.parent.range.current.wDay].lng + ' '
								+ dc.parent.range[dc.parent.range.current.month].name + ' ' + dc.parent.range.current.year, pre = '';

							if ((dc.parent.range[dc.parent.range.current.month].disabled[dc.parent.range.current.year]
								&& $A.inArray(dc.parent.range.current.mDay,
									dc.parent.range[dc.parent.range.current.month].disabled[dc.parent.range.current.year]) !== -1)
								|| (dc.parent.range[dc.parent.range.current.month].disabled['*'] && $A.inArray(dc.parent.range.current.mDay,
									dc.parent.range[dc.parent.range.current.month].disabled['*']) !== -1))
								pre += dc.parent.disabledTxt.replace(/<|>|\"/g, '') + ' ';

							if (!comm)
								$A.remClass(dc.parent.current, 'comment');

							else{
								$A.addClass(dc.parent.current, 'comment');
								pre += dc.parent.commentedTxt.replace(/<|>|\"/g, '') + ' ';
							}
							lbl = pre + lbl;
							$A.setAttr(dc.parent.current,
											{
											title: trim(pre),
											'aria-label': lbl + ' ' + comm.replace(/\"/g, '\"')
											});
						},
						reset: function(){
							var dc = this;

							if (dc.openEditor){
								dc.comments = dc.parent.range[dc.parent.range.current.month].comments;

								if (!dc.textarea)
									dc.textarea = $A.query('textarea', dc.containerDiv, function(){
										$A.css(this,
														{
														visibility: '',
														display: ''
														});

										dc.css('left', dc.parent.accDCObj.offsetLeft);
										$A.bind(this,
														{
														focus: function(ev){
															if (dc.parent.children[0].loaded)
																dc.parent.children[0].close();
														},
														keydown: function(ev){
															var k = ev.which || ev.keyCode;

															if (this.value.length > 800)
																this.value = this.value.substring(0, 799);

															if (k === 13){
																dc.parent.isAdd = true;
																dc.add.apply(this, [dc]);
																dc.parent.current.focus();
																dc.openEditor = false;
																dc.reset();
																ev.preventDefault();
															}

															else if (k === 27){
																dc.parent.current.focus();
																dc.openEditor = false;
																dc.reset();
																ev.preventDefault();
															}
														}
														});
									})[0];

								else{
									$A.css(dc.textarea,
													{
													visibility: '',
													display: ''
													});

									dc.css('left', dc.parent.accDCObj.offsetLeft);
								}
								$A.setAttr(dc.textarea,
												{
												title: dc.parent.range.current.mDay + ', '
													+ dc.parent.range.wDays[dc.parent.range.current.wDay].lng + ' '
													+ dc.parent.range[dc.parent.range.current.month].name + ' ' + dc.parent.range.current.year
												}).focus();

								if (dc.comments[dc.parent.range.current.year]
									&& dc.comments[dc.parent.range.current.year][dc.parent.range.current.mDay])
									dc.textarea.value = dc.comments[dc.parent.range.current.year][dc.parent.range.current.mDay];
								$A.setAttr(dc.commentBtn,
												{
												title: (config.editor && config.editor.action1 || 'Save') + ' ' + $A.reg[pId + 'commentTooltip'].role
												}).innerHTML = config.editor && config.editor.action1 || 'Save';
							}

							else{
								if (dc.textarea){
									dc.textarea.value = '';
									$A.css(dc.textarea,
													{
													visibility: 'hidden',
													display: 'none'
													});
								}

								dc.css('left', dc.parent.accDCObj.offsetLeft + dc.parent.accDCObj.offsetWidth - dc.accDCObj.offsetWidth);
								$A.setAttr(dc.commentBtn,
												{
												title: (config.editor && config.editor.role || 'Edit') + ' ' + $A.reg[pId + 'commentTooltip'].role
												}).innerHTML = config.editor && config.editor.role || 'Edit';
							}
						},
						runAfter: function(dc){
							$A.query('button', dc.containerDiv, function(){
								dc.commentBtn = this;
								$A.bind(this,
												{
												focus: function(ev){
													if (dc.parent.children[0].loaded)
														dc.parent.children[0].close();
												},
												click: function(ev){
													if (dc.openEditor){
														dc.parent.isAdd = true;
														dc.add.apply(this, [dc]);
														dc.parent.current.focus();
														dc.openEditor = false;
														dc.reset();
													}

													else{
														dc.openEditor = true;
														dc.reset();
													}
													ev.preventDefault();
												},
												keydown: function(ev){
													var k = ev.which || ev.keyCode;

													if (k === 27){
														if (dc.openEditor){
															dc.parent.current.focus();
															dc.openEditor = false;
															dc.reset();
														}

														ev.preventDefault();
													}
												}
												});
							});
							dc.reset();
							dc.lock = true;

							$A.bind(window, 'resize.dateeditor', function(ev){
								dc.setPosition();
								dc.reset();
							});

							$A.bind($A.query('textarea', dc.containerDiv)[0], 'keydown', function(ev){
								var k = ev.which || ev.keyCode;

								if (k === 9 && !ev.altKey && !ev.ctrlKey && ev.shiftKey){
									$A.query('button', dc.containerDiv)[0].focus();
									ev.preventDefault();
								}
							});
						},
						tabOut: function(ev, dc){
							$A.query('textarea', dc.containerDiv)[0].focus();
						},
						runBeforeClose: function(dc){
							dc.openEditor = false;
							dc.textarea = null;

							$A.unbind(window, 'resize.dateeditor');

							if (config.ajax && typeof config.ajax === 'function')
								config.ajax.apply(dc.parent, [dc.parent, true]);

							dc.parent.setFocus.firstOpen = true;
						},
						lock: commentsEnabled && config.editor && config.editor.show ? false : true
						}
						]);
		// Form object declaration end

		$A.setAttr(trigger, 'aria-expanded', 'false');

		// Toggles for openOnFocus support.
		var odc = $A.reg[pId], odcDel = false, odcDelFn = function(){
			odcDel = false;
		}, odcFn = function(){
			if (!odcDel && !odc.loaded){
				odcDel = true;
				// Toggles for openOnFocus support.
				onFocusInit = false;
				onFocusTraverse = true;
				$A.trigger(this, 'opendatepicker');
				setTimeout(odcDelFn, 1000);
			}

			else if (!odcDel && odc.loaded){
				odcDel = true;
				odc.close();
				// Toggles for openOnFocus support.
				onFocusInit = false;
				onFocusTraverse = false;
				setTimeout(odcDelFn, 1000);
			}
		};

		$A.bind(trigger,
						{
						click: function(ev){
							odcFn.call(this);
							ev.preventDefault();
						},
						keydown: function(ev){
							var k = ev.which || ev.keyCode;

							if (k === 32){
								odcFn.call(this);
								ev.preventDefault();
								ev.stopPropagation();
							}
						}
						});

		// Toggles for openOnFocus support.
		if (config.openOnFocus === true){
			$A.setAttr(targ, 'aria-expanded', 'false');

			$A.bind(targ,
							{
							touchstart: function(ev){
								if (!odcDel && !odc.loaded && !onFocusInit && !onFocusTraverse){
									odcDel = true;
									$A.trigger(trigger, 'opendatepicker');
									ev.preventDefault();
									setTimeout(odcDelFn, 1000);
								}
							},
							focus: function(ev){
								if (!odcDel && !odc.loaded && !onFocusInit && !onFocusTraverse){
									odcDel = true;
									$A.trigger(trigger, 'opendatepicker');
									$A.announce(odc.openOnFocusHelpText);
									setTimeout(odcDelFn, 1000);
								}
								onFocusInit = true;
								onFocusTraverse = false;
							},
							blur: function(ev){
								if (odc.loaded && onFocusInit && !onFocusTraverse){
									odc.close();
								}
								onFocusInit = false;
							},
							keydown: function(ev){
								var k = ev.which || ev.keyCode;

								if (k === 40 && onFocusInit && !onFocusTraverse && odc.loaded){
									onFocusInit = false;
									onFocusTraverse = true;
									odc.setFocus(odc.range.index[odc.range.current.mDay - 1]);
									$A.announce(odc.helpTextShort);
									ev.preventDefault();
									ev.stopPropagation();
								}

								else if (k === 40 && !odc.loaded && !odcDel){
									odcDel = true;
									onFocusInit = true;
									onFocusTraverse = false;
									$A.trigger(trigger, 'opendatepicker');
									setTimeout(odcDelFn, 1000);
									onFocusInit = false;
									onFocusTraverse = true;
									odc.setFocus(odc.range.index[odc.range.current.mDay - 1]);
									$A.announce(odc.helpTextShort);
									ev.preventDefault();
									ev.stopPropagation();
								}

								else if (k === 27 && onFocusInit && !onFocusTraverse && odc.loaded){
									onFocusInit = false;
									onFocusTraverse = false;
									odc.close();
									ev.preventDefault();
									ev.stopPropagation();
								}

								else if (k === 9 && onFocusInit && !onFocusTraverse && odc.loaded && ev.shiftKey){
									onFocusInit = false;
									onFocusTraverse = false;
									odc.close();
								}

								else if (k === 9 && onFocusInit && !onFocusTraverse && odc.loaded && !ev.shiftKey){
									onFocusInit = false;
									onFocusTraverse = true;
									odc.setFocus(odc.range.index[odc.range.current.mDay - 1]);
									$A.announce(odc.helpTextShort);
									ev.preventDefault();
									ev.stopPropagation();
								}
							}
							});
		}
	};

	var trim = function(str){
		return str.replace(/^\s+|\s+$/g, '');
	};
})();

//SETUP:JS
$A.bind(window, 'load', function(){

	// Syntax : setCalendar( ID , TriggeringElement , TargetEditField , EnableComments , clickHandler , config )

	$A.setCalendar('UniqueCalendarId', document.getElementById('fechaDesdeDateIcon'), document.getElementById('textDesde'), false,
	undefined,
			{
				// Set CSS positioning calculation for the calendar
				autoPosition: 3,
				// Customize with positive or negative offsets
				offsetTop: 0,
				offsetLeft: 5
			});
	
	$A.setCalendar('UniqueCalendarId', document.getElementById('fechaHastaDateIcon'), document.getElementById('textHasta'), false,
	undefined,
			{
				// Set CSS positioning calculation for the calendar
				autoPosition: 3,
				// Customize with positive or negative offsets
				offsetTop: 0,
				offsetLeft: 5
			});
});