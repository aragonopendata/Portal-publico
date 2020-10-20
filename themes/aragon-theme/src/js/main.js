AUI().ready(
    //This function gets loaded when all the HTML, not including the portlets, is loaded.
    function() {
      moveDivYui3();
      registerVisitedPage();
      assignBackURL();
    }
);

Liferay.Portlet.ready(
    /*This function gets loaded after each and every portlet on the page.
    portletId: the current portlet's id
    node: the Alloy Node object of the current portlet
    */
    function(portletId, node) {
    }
);

Liferay.on('allPortletsReady',
    /*
    This function gets loaded when everything, including the portlets, is on
    the page.
    */
    function() {
    }
);

/**
 * Assigns an url to the back buttons
 * @returns
 */
function assignBackURL() {
  if (document.referrer && document.referrer !== "") {
    $('.back_button_container').each(
      function() {
        $(this).find("a").attr("href", document.referrer);
        $(this).removeClass("oculto");
      }
    );    
  }
}
/**
 * Change position item because it given error accessibility
 * @returns
 */
function moveDivYui3(){
	var divYui3 = $('#yui3-css-stamp');
	if (divYui3 != null){
		$("body").append(divYui3);
	}
}

/**
 * User accepted all cookies
 */
function cookiesAccepted(){
	var allCookiesAccepted = "GOOGLE_ANALYTICS-1[COOKIE-SEPARATOR]LAST_VISITED_PAGES-1[COOKIE-SEPARATOR]MARKETING-1";
	setCookie("COOKIES_ACCEPTED", allCookiesAccepted, 30);
	$(".banner-aceptacion-cookies").remove();
	location.reload();
}
//Obtencion de miniatura de video desde su url
function getVideoThumbnail(url, imgElementId) {
	var videoType = "";
	var videoID = "";
	var match ="";
	if (url.indexOf('youtu') > -1) {
		match = url.match(/^.*(youtu\.be\/|vi?\/|u\/\w\/|embed\/|\?vi?=|\&vi?=)([^#\&\?]*).*/);
		if(RegExp.$2 != null && RegExp.$2 != undefined){
			videoID = RegExp.$2;
		}
		videoType = 'youtube';
	} else if (url.indexOf('vimeo') > -1) {
		match = url.match(/https?:\/\/(?:www\.|player\.)?vimeo.com\/(?:channels\/(?:\w+\/)?|groups\/([^\/]*)\/videos\/|album\/(\d+)\/video\/|video\/|)(\d+)(?:$|\/|\?)/);
		if(RegExp.$3 != null && RegExp.$3 != undefined){
			videoID = RegExp.$3;
		}
		videoType = 'vimeo';
	}
	if (videoType == 'youtube') {
		$("#" + imgElementId).attr("src", 'https://img.youtube.com/vi/' + videoID + '/hqdefault.jpg');
	} else if (videoType == 'vimeo') {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "https://vimeo.com/api/v2/video/" + videoID + ".json", true);
		xhr.onload = function (e) {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					var data = xhr.responseText;
					var parsedData = JSON.parse(data);
					var thumbSRClarge = parsedData[0].thumbnail_large;
					var thumbSplit = thumbSRClarge.split(/\d{3}(?=.jpg)/);
					$("#" + imgElementId).attr("src", thumbSplit[0] + '1280x720' + thumbSplit[1]);
				} else {
					console.error(xhr.statusText);
				}
			}
		};
		xhr.send();
	}
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    //var decodedCookie = decodeURIComponent(document.cookie);
    var codedCookie = document.cookie;
    var ca = codedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
 

/**
 * Updates the LAST_VISITED_PAGES cookie with the current page information
 */
function registerVisitedPage() {
	//Check if user has accepted the cookies
	const LAST_VISITED_PAGES = "LAST_VISITED_PAGES";
	var cookiesAcceptedCookieName = getCookie("COOKIES_ACCEPTED");
	if (cookiesAcceptedCookieName.indexOf("LAST_VISITED_PAGES-1") != -1 || cookiesAcceptedCookieName == ""){
		//Gets current page title
		var currentPageTitle = document.title;
		var url = document.location.href;
		if (currentPageTitle.lastIndexOf('.') != -1) {
			currentPageTitle = currentPageTitle.substring(0, currentPageTitle.lastIndexOf('.'));
		}
		if(url.indexOf('?') != -1){
			var indexUrl = url.indexOf('?');
			url = url.substring(0, indexUrl );
		}
		//Saves the current page title in the cookie if is not "Inicio" or "Estado" 
		if (currentPageTitle != "Inicio" && currentPageTitle != "Estado") {
			//Gets last visited pages cookie
			var lastVisitedPagesCookie = getCookie(LAST_VISITED_PAGES);
			//Declares the register separator between title and value
			var titleValueSeparator = "[COOKIE_SEPARATOR]";
			//Updates the cookie value
			if (lastVisitedPagesCookie != "") {
				//Gets the last visited pages value separated by '|'
				var lastVisitedPagesList = lastVisitedPagesCookie.split('|');
				
				//Deletes the current page from list if exists
				for (var i = 0; i < lastVisitedPagesList.length; i++) {
					var lastVisitedPageEntry = lastVisitedPagesList[i].trim();
					var lastVisitedPageEntryTitleValueSeparatorIndex = lastVisitedPageEntry.indexOf(titleValueSeparator);
					var lastVisitedPageEntryTitle = lastVisitedPageEntry.substring(0, lastVisitedPageEntryTitleValueSeparatorIndex);
					var lastVisitedPageEntryURL = lastVisitedPageEntry.substring(lastVisitedPageEntryTitleValueSeparatorIndex + titleValueSeparator.length, lastVisitedPageEntry.length);
					if (lastVisitedPageEntryTitle == encodeURIComponent(currentPageTitle)) {
						lastVisitedPagesList.splice(i, 1);
					} else if (lastVisitedPageEntryURL == document.location) {
						lastVisitedPagesList.splice(i, 1);
					}
				}
				//Adds the current page in the first position of the cookie value
				lastVisitedPagesCookie = encodeURIComponent(currentPageTitle) + titleValueSeparator + url;
				//Adds the previous pages to the cookie value (4 max)
				if (lastVisitedPagesList != null && lastVisitedPagesList.length > 0) {
					var visitedPagesCount = 0;
					for (var j = 0; j < lastVisitedPagesList.length && visitedPagesCount < 4; j++) {
						if (lastVisitedPagesList[j].trim().indexOf(titleValueSeparator) != -1) {
							lastVisitedPagesCookie = lastVisitedPagesCookie + "|" + lastVisitedPagesList[j].trim();
							visitedPagesCount++;
						}
					}
				}
			} else {
				lastVisitedPagesCookie = encodeURIComponent(currentPageTitle) + titleValueSeparator + url;	
			}
			//Saves the cookie in the browser
			setCookie(LAST_VISITED_PAGES, lastVisitedPagesCookie, 30);
		}
	}else {
		document.cookie = "LAST_VISITED_PAGES" + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	}
}

//Prevents the standard onsubmit function
$("#portal_searcher_form").submit(function(event) {
	event.preventDefault();
});

/**
 * Assigns onsubmit to the theme search form
 */
function onClickSearchOrFilterPortal(searchType){
  var searchURLPortal = document.getElementById("searchURLPortal").value;
  //Default search url
  var url = searchURLPortal + "/-/search/type/" + searchType + "/page/0";
  //Gets the original searched text
  var searchedText = $("#searchTextHeader")[0].value;
  //Limits the searched text to 400
  searchedText = searchedText.length > 400 ? searchedText.substring(0, 400) : searchedText;
  if (searchedText != null && searchedText != "") {
    searchedText = searchedText.replace(/\//g, "0x2f");
    searchedText = searchedText.replace(/\\/g, "0x5c");
    searchedText = searchedText.replace(/\./g, "0x2e");
    searchedText = searchedText.replace(/[!'()*]/g, "");
    searchedText = encodeURIComponent(searchedText);
    url = url + "/q/" + searchedText;
  }
  window.location.href = url;
}

/**
 * Removes all accents from the given string
 * @param text String to remove accents
 * @returns The formatted String
 */
function cleanString(text) {
    var accents = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇç";
    var original = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunncc";
    for (var i = 0; i < accents.length; i++) {
        text = text.replace(new RegExp(accents.charAt(i), 'g'), original.charAt(i));
    }
    return text;
}

// ESTOS SON JS PARA EL DISENO
var mainApp = (function(){

  /***********  Funciones privadas  ****************/
  
    /************************* CONTROL MENÚ PRINCIPAL ************************************/
    // despliegue menú principal
    var desplegarMenu = function(){
      // Desplegar menú y  bloquear navegación en resto de página
      $('.navbar-toggler').on('click', function(){
        if(!$(this).find('.navbar-menu-icon').hasClass('active')){
          $(this).attr('aria-label','Cerrar menú');
        }
        else{
          $(this).attr('aria-label','Abrir menú');
        }
        $(this).find('.navbar-menu-icon').toggleClass('active');
  
        if(window.innerWidth < 768){
          $('html,body').addClass('noScroll');
        }
      });
      
      $('.nav-link').click(function(){
        $('.language__list').hide();
      })
    }
  
    //Despliegue menú de cabecera segundo nivel 
    var desplegarMenuSec = function(){
      //desplegar menu secundario onmouseover
      if(window.innerWidth > 768){
          $('.nav-item.dropdown').hover(
            function(){
              $('.nav-item.dropdown .dropdown-menu').removeClass('show');
              $('.nav-item.dropdown').find('.nav-link').attr('aria-expanded',false);
              $(this).children().focus();
              $(this).addClass('show');
              $(this).find('.nav-link').attr('aria-expanded',true);
              $(this).find('.dropdown-menu').addClass('show');
              $(this).keydown(function(e) {
                if(e.which === 27) {
                  $(this).find('.nav-link').focus();
                }
              });
            },
            function(){
              $(this).removeClass('show');
              $(this).find('.nav-link').attr('aria-expanded',false);
              $(this).find('.dropdown-menu').removeClass('show');
            }
          );
          $('.breadcrumb .link').focus(
            function(){
              $('.navbar-collapse').removeClass('show');
              $('.navbar-toggler').addClass('collapsed');
              $('.navbar-toggler').attr('aria-label','Abrir menú');
              //desbloqear navegación
              $('html,body').removeClass('noScroll');
              $('html, body').animate({ scrollTop: 0 }, 'slow');
            }
          )
      }
    }
  
    // Cerrar menú responsive
    var cerrarMenu = function(){
        $('.close').click(function(){
          $(this).parent().removeClass('show');
          $('.navbar-toggler').addClass('collapsed');
          $('.navbar-toggler').attr('aria-label','Abrir menú');
          //desbloqear navegación
          $('html,body').removeClass('noScroll');
          $(this).parent().parent().find('.nav-link').attr('aria-expanded','false');
        });
        $('.close').keydown(function(e) {
          if(e.which == 13) {
            $(this).parent().removeClass('show');
            $('.navbar-toggler').addClass('collapsed');
            $('.navbar-toggler').attr('aria-label','Abrir menú');
            $('html,body').removeClass('noScroll');
            $(this).parent().parent().find('.nav-link').attr('aria-expanded','false');
          }
        });
        $('.close').focusout(function(){
          $('.language__select').focus();
        });
    }
    /************************* FIN CONTROL MENÚ PRINCIPAL ************************************/

  /* CONTROL de los enlaces de salto a contenido */
  var saltoContenidoTeclado = function(){
    if($('.js-salto-contenido')){

      $('.js-salto-contenido').click(function(){
        $('html, body').animate({ scrollTop: 0 }, 'slow');
      });
      var footerToTop = $('.footer').offset();
      footerToTop = footerToTop.top - 100;
      $('.js-salto-nav').click(function(){
        $('html, body').animate({ scrollTop: footerToTop }, 'slow');
      });
      $('.js-salto-contenido,.js-salto-nav').keydown(function(e) {
        if(e.which == 27) {
          $('.logo-dga').focus();
        }
      });
    }
  }

  // Modificar tabindex en elementos de LifeRay
  var updateTabIndex = function(){
    if(window.innerWidth < 768){
      $('.portlet-bgwhite-title h2 a').attr('tabindex',0);
      $('.dga-view .footer__links__list > li .title').attr('tabindex',0);
    }
    else{
      $('.portlet-bgwhite-title h2 a').attr('tabindex',-1);
      $('.dga-view .footer__links__list > li .title').attr('tabindex',-1);
    }
  }

  // Mostrar/Ocultar selector de idioma
//Mostrar/Ocultar selector de idioma
  var selectorIdioma = function () {

  	function showMenu() {
  		$('.language__list').show();
  		$('.language').addClass('active');
  		$('.language__select').attr('aria-expanded', true);
  	}

  	function hideMenu() {
  		$('.language__list').hide();
  		$('.language').removeClass('active');
  		$('.language__select').attr('aria-expanded', false);
  	}

  	$('.language').hover(
  		function () {
  			if ($(window).width() > 768) {
  				$('.language__select').focus();
  				showMenu();
  			}
  		},
  		function () {
  			if ($(window).width() > 768) {
  				hideMenu();
  			}
  		}
  	);

  	$('.language').click(
  		function () {
  			if (!$('.language').hasClass('active')) {
  				showMenu();
  			} else {
  				hideMenu();
  			}
  		}
  	);

  	$('.language').keydown(function (e) {
  		if (e.which == 13) {
  			if (!$('.language').hasClass('active')) {
  				showMenu();
  			} else {
  				hideMenu();
  			}
  		}
  		if (e.which === 27) {
  			hideMenu();
  		}
  	});

  	$('.language__list li:last-child').focusout(function () {
  		hideMenu();
  	});
  }

  // Código para mostrar correctamente imágenes con atributo objetc-fit en ie11 
  var IExplorer = function(contenedor){
      $(contenedor).each(function() {
        var $container = $(this),
        imgUrl = $container.find("img").prop("src");
        if (imgUrl) {
          $container.css("backgroundImage", 'url(' + imgUrl + ')').addClass("custom-object-fit");
          $container.find('img').addClass('featured-image');
        }
      });
      //Oculto SVG bandera europa para que dse muestre png de la bandera en IE11, donde no funciona el SVG
      $('.european-flag-head .image,.european-flag img').css('opacity','0');
  }  

  // Deshabilitar el colapso en módulos en los que el contenido aparece
  // plegado y tiene un CTA para ser desplegado
  var disableCollapse = function(ruta,width){
    $(ruta).click(function(e){
      if ($(window).width() >= width) { 
        // Should prevent the collapsible and default anchor linking 
        // behavior for screen sizes equal or larger than 768px.
        e.preventDefault();
        e.stopPropagation();
      }    
    });
  }

  // Ocultar el menú mobile
  var ocultarMenuAuto = function(){
    var $win = $(window);
    var $box = $(".navbar-collapse,.navbar-toggler");

    $win.on("click.Bst", function(event){		
    if ($box.has(event.target).length == 0 && !$box.is(event.target)){
        $('.close').parent().removeClass('show');
        $('.navbar-toggler').addClass('collapsed');
        $('.navbar-toggler').attr('aria-label','Abrir menú');
        $('html,body').removeClass('noScroll');
        $('.close').parent().parent().find('.nav-link').attr('aria-expanded','false');
      }
    });

    $('.navbar-toggler').keydown(function(e) {
      if(e.which === 27) {
        $('.close').parent().removeClass('show');
        $('.navbar-toggler').addClass('collapsed');
        $('.navbar-toggler').attr('aria-label','Abrir menú');
        $('html,body').removeClass('noScroll');
        $('.close').parent().parent().find('.nav-link').attr('aria-expanded','false');
      }
    });
  }

  // Foco al abandona vía teclado la galeria de fotos
  var cerrarGalTeclado = function(){
    $('.ekko-lightbox .modal-header .close').on('click',function(e) {
      if(e.which === 13) {
        $('.dga-view .gallery-module .swiper-container .swiper-button-next').focus();
      }
    });
  }

  //limitar caracteres en un texto
  var limitarCaracteres = function (texto,numero){
    $(texto).each(function () {
        len=$(this).text().length;
        str= $(this).text().substr(0,numero);
        lastIndexOf = str.lastIndexOf(" "); 
        if(len > numero) {
            $(this).text(str.substr(0, lastIndexOf) + '…');
        }
    });
  }

  // Funcionamiento botones de Zoom
  var increaseFontSize = function(){

    function changeFont(fontSize) {
      return function() {
          $('html').css('font-size', fontSize + '%');
          sessionStorage.setItem('fSize', fontSize);
      }
    }

    var normalFont = changeFont(62.5),
        littleFont = changeFont(48.625),
        largeFont  = changeFont(125);
        
    // gestion click  botones al decrecer fuente
    $('.zoom__neg').on('click', function(){
        $('body').removeClass('zoom-in');
        $('.zoom__pos a').attr('aria-disabled','false');
        if($('.zoom__pos').hasClass('active')){
          normalFont();
          $('.zoom > li').removeClass('active');
          $(this).find('a').attr('aria-disabled','false');
          $('.dga-view header .navbar-collapse .language').addClass('hidden-sm').addClass('hidden-md').addClass('hidden-lg').removeClass('show');
        }
        else{
          littleFont();
          $(this).addClass('active');
          $('body').addClass('zoom-out');
          $(this).find('a').attr('aria-disabled','true');
        }
        stickyBar();
    });
    // gestion teclado  botones al decrecer fuente
    $('.zoom__neg').keydown(function(e) {
        $('body').removeClass('zoom-in');
        $('.zoom__pos a').attr('aria-disabled','false');
        if(e.which == 13) {
          if($('.zoom__pos').hasClass('active')){
            normalFont();
            $('.zoom > li').removeClass('active');
            $(this).find('a').attr('aria-disabled','false');
            $('.dga-view header .navbar-collapse .language').addClass('hidden-sm').addClass('hidden-md').addClass('hidden-lg').removeClass('show');
          }
          else{
            littleFont();
            $(this).addClass('active');
            $('body').addClass('zoom-out');
            $(this).find('a').attr('aria-disabled','true');
          }
        }
        stickyBar();
    });
    // gestion click  botones al aumentar fuente
    $('.zoom__pos').on('click', function(){
        $('body').removeClass('zoom-out');
        $('.zoom__neg a').attr('aria-disabled','false');
        if($('.zoom__neg').hasClass('active')){
          normalFont();
          $('.zoom > li').removeClass('active');
          $(this).find('a').attr('aria-disabled','false');
        }
        else{
          largeFont();
          $(this).addClass('active');
          $('body').addClass('zoom-in');
          $('.dga-view header .navbar-collapse .language').removeClass('hidden-sm').removeClass('hidden-md').removeClass('hidden-lg').addClass('show');
          $(this).find('a').attr('aria-disabled','true');
        }
        stickyBar();
    });
    // gestion teclado  botones al aumentar fuente
    $('.zoom__pos').keydown(function(e) {
        $('body').removeClass('zoom-out');
        $('.zoom__neg a').attr('aria-disabled','false');
        if(e.which == 13) {
          if($('.zoom__neg').hasClass('active')){
              normalFont();
              $('.zoom > li').removeClass('active');
              $(this).find('a').attr('aria-disabled','false');
          }
          else{
            largeFont();
            $(this).addClass('active');
            $('body').addClass('zoom-in');
            $('.dga-view header .navbar-collapse .language').removeClass('hidden-sm').removeClass('hidden-md').removeClass('hidden-lg').addClass('show');
            $(this).find('a').attr('aria-disabled','true');
          }
        }
        stickyBar();
    });

    
  }

  // Inicialización del Slider de contenido final
  var swiperSlider = function(){
    /* 1 of 2 : SWIPER */
    var mySwiper = new Swiper(".swiper-container", {
      loop: false,
      slidesPerView: 3,
      spaceBetween: 45,
      centeredSlides: false,
      a11y: {
        prevSlideMessage: 'Mostrar elementos anteriores',
        nextSlideMessage: 'Mostrar elementos siguientes'
      },
      breakpoints: {
        // when window width is <= 480px
        767: {
          slidesPerView: 1
        },
        // when window width is <= 640px
        1200: {
          slidesPerView: 2
        }
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
      on: {
        init: function() {
          if($(window).width() < 767){
            if($('.my-gallery .swiper-slide').length < 2){
              $('.swiper-button-prev').hide();
              $('.swiper-button-next').hide();
            }
          }
          else if($(window).width() < 1200){
            if($('.my-gallery .swiper-slide').length < 3){
              $('.swiper-button-prev').hide();
              $('.swiper-button-next').hide();
            }
          }
          else if($(window).width() >= 1200){
            if($('.my-gallery .swiper-slide').length == 3){
              $('.swiper-button-prev').hide();
              $('.swiper-button-next').hide();
            }
          }
        },
        resize: function () {
          if($(window).width() < 767){
            if($('.my-gallery .swiper-slide').length < 2){
              $('.swiper-button-prev').hide();
              $('.swiper-button-next').hide();
            }
          }
          else if($(window).width() < 1200){
            if($('.my-gallery .swiper-slide').length < 3){
              $('.swiper-button-prev').hide();
              $('.swiper-button-next').hide();
            }
          }
          else if($(window).width() >= 1200){
            if($('.my-gallery .swiper-slide').length == 3){
              $('.swiper-button-prev').hide();
              $('.swiper-button-next').hide();
            }
          }
        }
      }
      

    });

    $(document).on("click", '[data-toggle="lightbox"]', function(event) {
      event.preventDefault();
      $(this).ekkoLightbox({
        title:'Galería de imágenes',
        rightArrow:'<a href="#" role="button" aria-label="Mostrar la siguiente imagen"><span>❯</span></a>',
        leftArrow:'<a href="#" role="button" aria-label="Mostrar la imagen anterior"><span>❮</span></a>',
        strings:{
          close: 'Cerrar'
        }
      });
    });

  }

  // Enlaces ancla en página de contenido final
  var anchorCustom = function(){
    var count = $(".detail-news-module__anchor .listado__item").length;
    var multipoTres = count % 3;
    var multipoDos = count % 2;
    if($(window).width() >= 992){
      if(multipoTres == 0){
        $(".detail-news-module__anchor .listado__item:nth-last-child(3) .link,.detail-news-module__anchor .listado__item:nth-last-child(2) .link,.detail-news-module__anchor .listado__item:nth-last-child(1) .link").addClass('no-border');
      }
      else if(multipoDos == 0){
        $(".detail-news-module__anchor .listado__item:nth-last-child(2) .link,.detail-news-module__anchor .listado__item:nth-last-child(1) .link").addClass('no-border');
      }
      else{
        $(".detail-news-module__anchor .listado__item:nth-last-child(1) .link").addClass('no-border');
      }
    }
    else if($(window).width() >= 768 && $(window).width() < 992){
      if(multipoDos == 0){
        $(".detail-news-module__anchor .listado__item:nth-last-child(2) .link,.detail-news-module__anchor .listado__item:nth-last-child(1) .link").addClass('no-border');
      }
      else{
        $(".detail-news-module__anchor .listado__item:nth-last-child(1) .link").addClass('no-border');
      }
    }
    else{
      $(".detail-news-module__anchor .listado__item .link").removeClass("no-border");
      $(".detail-news-module__anchor .listado__item:nth-last-child(1) .link").addClass('no-border');
    }

    $(".detail-news-module__anchor .listado__item .link").click(function() {
      var link = $(this).attr('href');
      var position = $(link).offset();
      positionContent = position.top + 30 - $('.header').height();
      $('html,body').animate({ 
        scrollTop: positionContent }
      , 1000);
    });
  }

  // Sticky Bar
  var stickyBar = function(){
    // ACTIVACIÓN DE LA STICKY BAR
      var firstScroll = $('.old-version').height();
      firstScroll = firstScroll + 10;
      firstScroll = firstScroll;
      var lastScrollTop = 0;
      $(window).scroll(function (event) {
        if ($(window).width() >= 768){
          $('.header').removeClass('sticky');
          $('.header').css('top','0');
          $('.dga-view').css('padding-top','0');
        }
        else
        {
          var scrollPosition = $(window).scrollTop(); // get posición scroll
          if($('body').hasClass('zoom-in') == false){
            if(scrollPosition >= firstScroll){
              $('.header').addClass('sticky');
              $('.header').css('top','-' + firstScroll + 'px');
              $('.dga-view').css('padding-top', $('.header').height());
            }
            else{
              $('.header').removeClass('sticky');
              $('.header').css('top','0');
              $('.dga-view').css('padding-top','0');
            }
          }
          else{
              $('.header').removeClass('sticky');
              $('.header').css('top','0');
              $('.dga-view').css('padding-top','0');
          }
          // posición del buscador al activarse Sticky bar
          //if( $(window).width() < 1200){
            $('.interior .dga-view .navbar .search-module').removeClass('is-not-visible').addClass('is-visible');
            $('.header').addClass('gray-border');
            if (scrollPosition > lastScrollTop && scrollPosition > firstScroll){
                // downscroll code
                  $('.interior .dga-view .navbar .search-module').removeClass('is-visible').addClass('is-not-visible');
                  $('.header').removeClass('gray-border');
                } else {
                  // upscroll code
                  $('.interior .dga-view .navbar .search-module').removeClass('is-not-visible').addClass('is-visible');
                  $('.header').addClass('gray-border');
            }
            lastScrollTop = scrollPosition;
        }
      });
  }

  // Apertura/Cierre Filtro buscador
  var abrirCerrarFiltrado = function(){
    $('.js-abrir-filtro-buscador').on('click', function(){
      $('.filtrado-buscador').removeClass('hidden-xs');
      $('html,body').addClass('noScroll-busq');
      $('.js-abrir-filtro-buscador').attr("aria-expanded", "true");
    });
    $('.js-filtrado-close-btn').on('click', function(){
      $('.filtrado-buscador').addClass('hidden-xs');
      $('html,body').removeClass('noScroll-busq');
      $('.js-abrir-filtro-buscador').attr("aria-expanded", "false");
    });
  }

  //Cambiar la etiqueta aria-label al ejecutarse un evento
  var changeAriaLabel = function (){
    $('.js-arrow-open').on('click',function(){
      var ariaLabel = $(this).attr('aria-label');
      var labelClosed = $(this).attr('data-label-closed');
      var labelOpened = $(this).attr('data-label-opened');
      if(ariaLabel == labelClosed){
          $(this).attr('aria-label',labelOpened);
      }
      else{
          $(this).attr('aria-label',labelClosed);
      }

    });
  }
  
	/**
	 * Toggle para listados de enlaces del footer
	 */
	var slideToggleFooter = function () {
		$('.footer__links').find('[data-control-collapse]').on('click', function () {
			if ($(window).width() <= 767) {
				var et = $(this).data('control-collapse');
				var ec = $('[data-content-collapse="' + et + '"]');
				$(this).toggleClass('active');
				$(ec).toggleClass('active');
				$(ec).slideToggle();
				$(this).attr("aria-expanded") === "true" ?
				$(this).attr("aria-expanded", "false") :
				$(this).attr("aria-expanded", "true");
			}
		});
	}

	/**
	 * Toggle para listados de enlaces de enlinea
	 */
	var slideToggleCustom = function () {
		$('.en-linea').not('.preguntas-frecuentes-list').find('[data-control-collapse]').on('click', function () {
			var et = $(this).data('control-collapse');
			var ec = $('[data-content-collapse="' + et + '"]');
			if ($(this).attr("aria-expanded") === "false") {
				$(ec).slideDown(500, function () {
					$(et).addClass('active');
					$(ec).addClass('active');
				});
				$(this).attr("aria-expanded", "true");
			} else {
				$(ec).slideUp(500, function () {
					$(et).removeClass('active');
					$(ec).removeClass('active');
				});
				$(this).attr("aria-expanded", "false");
			}
		});
	}
	
	/**
	 * Toggle para listados de preguntas frecuentes
	 */
	var slideTogglePreguntasFrecuentes = function () {
		$('.preguntas-frecuentes-list').find('[data-control-collapse]').on('click', function () {
			var et = $(this).data('control-collapse');
			var ec = $('[data-content-collapse="' + et + '"]');
			if ($(this).attr("aria-expanded") === "false") {
				$('.preguntas-frecuentes-list').find('[aria-expanded=true]').each(function() {
					var et = $(this).data('control-collapse');
					var ec = $('[data-content-collapse="' + et + '"]');
					$(ec).slideUp(500, function () {
						$(et).removeClass('show');
						$(ec).removeClass('show');
					});
					$(this).attr("aria-expanded", "false");
				});
				$(ec).slideDown(500, function () {
					$(et).addClass('show');
					$(ec).addClass('show');
				});
				$(this).attr("aria-expanded", "true");
			} else {
				$(ec).slideUp(500, function () {
					$(et).removeClass('show');
					$(ec).removeClass('show');
				});
				$(this).attr("aria-expanded", "false");
			}
		});
	}

	/**
	 * Eliminacion de atributo style de los collapse
	 */
	var slideToggleFooterClean = function () {
		var dataNum = $('[data-content-collapse]').length;
		for (var i= 0; i < dataNum; i++) {
			$('[data-content-collapse]')[i].removeAttribute("style");
		}
	} 
	
  return{
    saltoContenidoTeclado : function(){
      saltoContenidoTeclado();
    },
    updateTabIndex : function(){
      updateTabIndex();
    },
    changeAriaLabel : function(){
      changeAriaLabel();
    },
    desplegarMenu : function(){
      desplegarMenu();
    },
    desplegarMenuSec : function(){
      desplegarMenuSec();
    },
    cerrarMenu : function(){
      cerrarMenu();
    },
    selectorIdioma : function(){
      selectorIdioma();
    },
    IExplorer : function(){
      IExplorer('.img-title-container .image');
      IExplorer('.swiper-slide a');
    },
    disableCollapse : function(){
      disableCollapse('.modules-two-columns a[data-toggle="collapse"]',580);
      disableCollapse('.resultados .info-resultados [data-toggle="collapse"]',768);
      disableCollapse('.dga-view .footer__links__list > li .title[data-toggle="collapse"]',768);
    },
    ocultarMenuAuto : function(){
      ocultarMenuAuto();
    },
    cerrarGalTeclado : function(){
      cerrarGalTeclado();
    },
    limitarCaracteres : function(){
      limitarCaracteres($('.detail-news-module__anchor .listado__item .link','50'));
    },
    increaseFontSize : function(){
      increaseFontSize();
    },
    swiperSlider : function(){
      swiperSlider();
    },
    anchorCustom : function(){
      anchorCustom();
    },
    stickyBar : function(){
      stickyBar();
    },
    abrirCerrarFiltrado : function(){
      abrirCerrarFiltrado();
    },
    slideToggleFooter : function(){
    	slideToggleFooter();
      },    
    slideToggleCustom : function(){
      slideToggleCustom();
    },
	slideTogglePreguntasFrecuentes : function(){
      slideTogglePreguntasFrecuentes();
    },
    slideToggleFooterClean: function(){
    	slideToggleFooterClean();
    }
  }
})();

$(document).ready(function() {
    // On load
    mainApp.saltoContenidoTeclado();
    mainApp.updateTabIndex();
    mainApp.desplegarMenuSec();
    mainApp.cerrarMenu();
    mainApp.selectorIdioma();
    mainApp.disableCollapse();
    mainApp.ocultarMenuAuto();
    mainApp.cerrarGalTeclado();
    mainApp.limitarCaracteres();
    mainApp.increaseFontSize();
    mainApp.desplegarMenu();
    mainApp.anchorCustom();
    mainApp.abrirCerrarFiltrado();
    mainApp.changeAriaLabel();
    mainApp.stickyBar();
    mainApp.slideToggleFooter();
    mainApp.slideToggleCustom();
	mainApp.slideTogglePreguntasFrecuentes();
    mainApp.slideToggleFooterClean();
    // Evitamos inicializar la función cuando el slider no esté implementado en la página cargada
    if($('.swiper-wrapper').hasClass('my-gallery') == true){
      mainApp.swiperSlider();
    }

    var userAgent, ieReg, ie;
    userAgent = window.navigator.userAgent;
    ieReg = /msie|Trident.*rv[ :]*11\./gi;
    ie = ieReg.test(userAgent);
    /* Imágenes object-fit en ie11 */
    if(ie) {
        mainApp.IExplorer();
    }
    
    // Cambio en la anchura dispositivo
    $(window).resize(function() {
      mainApp.updateTabIndex();
      mainApp.desplegarMenuSec();
      mainApp.anchorCustom();
      mainApp.stickyBar();
      mainApp.slideToggleFooterClean();
      if($(window).width() > 767){
        $('html,body').removeClass('noScroll-busq');
      }
    });
});


function extractExtension(href){
    var extension= href.split("/");
    var finalExt = extension[extension.length - 2].match("\.(.+)");
    return "." + finalExt[finalExt.length - 1] + "/";
}
