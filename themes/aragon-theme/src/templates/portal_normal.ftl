<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
	<head>
		<#-- Google Tag Manager --> 
		<script>
			(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src= 'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f); })(window,document,'script','dataLayer','GTM-PFFGV26');
		</script> 
		<#-- End Google Tag Manager -->
		<#-- Chart --> 
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
		<#-- End Chart -->
		<meta content="initial-scale=1.0, width=device-width" name="viewport" />
		<meta charset="utf-8"/>
		<#-- Font awesome  -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<#-- End Font awesome -->
		<#-- Page title -->
		<#if (html_title_format=="procedure")>
			<title><@liferay.language_format arguments="${themeDisplay.getLayout().getName(locale)}" key="enlinea.procedure-title"/>. <@liferay.language key="aragon.portal-head-tag"/></title>
		<#else>
			<title>${the_title}. <@liferay.language key="aragon.portal-head-tag"/></title>
		</#if>
		<#-- Meta description -->
		<#if (dynamic_meta_description=="procedure-distributor")>
			<meta name="description" content='<@liferay.language_format arguments="${themeDisplay.getLayout().getName(locale)}" key="enlinea.procedure-distributor.meta.description"/>'>
		</#if>
		<#-- Top include -->
		<@liferay_util["include"] page=top_head_include />
		<#-- JSON-LD -->
		<#if (themeDisplay.getResponse().getStatus() == 200)>
			<#if serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?? && serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")?has_content>
				<#assign jsonLDGenerator = serviceLocator.findService("es.aragon.base.jsonld_generator.api.JSONLDGenerator")/>
				<script type="application/ld+json">
					${jsonLDGenerator.getJsonLD(themeDisplay)}
				</script>
			</#if>
		</#if>
		<#if theme_display.getURLCurrent() ?contains("/en/")>
			<meta name="robots" content="noindex,nofollow" />
		</#if>
		<script type="text/javascript">
			window.rsDocReaderConf = {lang: 'es_es'};
		</script>
  		<meta property="og:title" content='${the_title}. <@liferay.language key="aragon.portal-head-tag"/>' />
	</head>
	<body class="${css_class}" id="senna_surface1">
		<#-- Google Tag Manager (noscript) --> 
		<noscript><iframe title="Tag Manager de Google" src="https://www.googletagmanager.com/ns.html?id=GTM-PFFGV26" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript> 
		<#-- End Google Tag Manager (noscript) -->
		<@liferay_util["include"] page=body_top_include />
		<@liferay.control_menu />
		<div class="lfr-spa-loading-bar"></div>
      	<div class="tooltip fade clay-tooltip-bottom" hidden="" role="tooltip">
         	<div class="arrow"></div>
         	<div class="tooltip-inner"></div>
      	</div>
      	<div id="senna_surface1-screen_2" style="display: block;" class="flipped">
      		<nav class="quick-access-nav" id="dvqk_quickAccessNav" aria-label="Saltar zonas del contenido">
            	<ul>
               		<li><a href="#content" class="js-salto-contenido" accesskey="0"><@liferay.language key="aragon.content-break"/></a></li>
               		<li><a href="#footer" class="js-salto-nav" accesskey="2"><@liferay.language key="aragon.footer-break"/></a></li>
            	</ul>
         	</nav>
			<div class="container-fluid dga-view" id="wrapper">
  				<#-- ACEPTACION DE COOKIES-->
  				<#if show_banner_cookies=="true">
	  				<#assign existCookieAcceptedCookies = false >
	  				<#if request.getCookies()?? && request.getCookies()?has_content>
						<#assign cookies = request.getCookies()>
						<#list cookies as cookie>
							<#if cookie.name == "COOKIES_ACCEPTED">
								<#assign existCookieAcceptedCookies = true >
							</#if>
						</#list>
					</#if>
					<#if existCookieAcceptedCookies == false>
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
						<#assign theme_groupID = htmlUtil.escape(theme_display.getCompanyGroupId()?string) />
						<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
						<@liferay_portlet["runtime"] defaultPreferences="${freeMarkerPortletPreferences}" portletProviderAction=portletProviderAction.VIEW instanceId="bannerCookies" portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
						${freeMarkerPortletPreferences.reset()}
					</#if>
				</#if>
				<#-- HEADER -->
				<header class="header" id="banner" aria-label="Cabecera" role="banner" tabindex="-1">
					<div class="navbar container">
						<div id="heading">
			                <#if layout_header_style=="home">
			                	<#-- LOGO PARA LA HOME -->
		                        <h1 class="site-title">
		                           <a href="/" class="logo-dga">
		                              <img src="${images_folder}/dga/logo-dga-color.svg" alt='<@liferay.language key="aragon.return-home"/>'>
		                           </a>
		                        </h1>
	                        <#else>
	                        	<#-- LOGO PARA EL RESTO DE PÁGINAS -->
		                        <div class="site-title">
		                           <a href="/" class="logo-dga">
		                              <img src="${images_folder}/dga/logo-dga-color.svg" alt='<@liferay.language key="aragon.return-home"/>'>
		                           </a>
		                        </div>
	                        </#if>
						</div>
						<div class="navigation">
							<#-- UNION EUROPEA -->
							<a href="${euro_flag_url}" class="european-flag-head" title="Más información sobre los Fondos Estructurales y de Inversión Europeos (Fondos EIE)">
								<img class="image" src="${images_folder}/dga/flag_europe.svg" alt='<@liferay.language key='aragon.ue-feder'/>'>
							</a>
							<#-- ZOOM -->
							<ul class="zoom hidden-xs">
								<li class="zoom__pos"><a aria-disabled="false" tabindex="0">A<sup>+</sup><span class="oculto"><@liferay.language key="aragon.increase-text-size-200"/></span></a></li>
								<li class="zoom__neg"><a aria-disabled="false" tabindex="0">A<sup>-</sup><span class="oculto"><@liferay.language key="aragon.decrease-text-size-200"/></span></a></li>
							</ul>
							<#-- IDIOMA -->
							<div class="language hidden-xs">
								<@liferay_portlet["runtime"] portletName="com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet" instanceId="desktopLanguageSelector"/>
							</div>
							<#-- ACCESO AL MENU -->
							<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navMenuButton" aria-controls="navMenuButton" aria-expanded="false" aria-label="Abrir menú">
								<span class="navbar-menu-icon">
									<span></span>
									<span></span>
									<span></span>
									<strong><@liferay.language key="menu"/></strong>
								</span>
							</button>
						</div>
						<#-- MENU DE NAVEGACION -->
						<#include "${full_templates_path}/navigation.ftl" />
					</div>
					<#-- ZOOM MOVIL -->
					<ul class="zoom responsive hidden-sm hidden-md hidden-lg">
						<li class="zoom__pos"><a href="#" aria-disabled="false" tabindex="0">A<sup>+</sup><span class="oculto">Aumentar el tamaño de texto un 200%</span></a></li>
						<li class="zoom__neg"><a href="#" aria-disabled="false" tabindex="0">A<sup>-</sup><span class="oculto">Disminuir el tamaño de texto un 200%</span></a></li>
					</ul>
				</header>
				<section id="content" tabindex="-1" role="main">
					<div class="portlet-notitle">
						<div class="portlet-content">
							<div class="portlet-body">
								<section role="search" aria-label="Buscador" >
									<div class="search-module bg-gray">
										<div class="container">
											<div class="row">
												<div class="col-md-3 my-auto">
													<#if show_back_button=="true">
														<div class="back_button_container hidden-xs oculto">
															<a href="#">Volver atrás</a>
														</div>
													</#if>
												</div>
												<div class="col-md-6">
													<form id="portal_searcher_form" action="javascript:void(0)" method="get" class="access-to-search input-group" onsubmit="onClickSearchOrFilterPortal('${searchType}')" autocomplete="off">
														<input type="hidden" id ="searchURLPortal" value="${searcherUrl}">
														<div class="search-input-container">
															<input id="searchTextHeader" maxlength="400" type="search" placeholder='<@liferay.language key="aragon.search-box-placeholder"/>' class="search-input" title='<@liferay.language key="search"/>'>
														</div>
														<button class="search-btn" type="submit">
															<img src="${images_folder}/dga/icons/icon-lupa.svg" class="lupa" alt='<@liferay.language key="search"/>'/>
															<img src="${images_folder}/dga/icons/icon-lupa-negative.svg" class="lupa-focus" alt='<@liferay.language key="search"/>'/>
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</section>
								<#if show_back_button=="true">
									<div class="container hidden-sm hidden-md hidden-lg">
										<div class="back_button_container">
											<a href="#">Volver atrás</a>
										</div>
									</div>
								</#if>
							</div>
						</div>
					</div>
					<#assign show_warning_advertisement = freeMarkerUtilities.getThemeLayoutPropertyValue(themeDisplay.getLayout(), "show-warning-advertisement")/>
					<#if show_warning_advertisement == "true">					
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
						<#assign theme_groupID = htmlUtil.escape(theme_display.getCompanyGroupId()?string) />
						<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
						<@liferay_portlet["runtime"] defaultPreferences="${freeMarkerPortletPreferences}" portletProviderAction=portletProviderAction.VIEW instanceId="msgAdvertisement" portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
						${freeMarkerPortletPreferences.reset()}
					</#if>
					<#if layout_header_style=="default">
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
						<#assign VOID = freeMarkerPortletPreferences.setValue("displayStyle", "MIGAS-PORTAL-DGA") />
						<@liferay.breadcrumbs default_preferences="${freeMarkerPortletPreferences}"  />
						${freeMarkerPortletPreferences.reset()}				
					</#if>
					<#assign show_warning_alert = freeMarkerUtilities.getThemeLayoutPropertyValue(themeDisplay.getLayout(), "show-warning-alert")/>
					<#if show_warning_alert == "true">
						<#assign warning_alert_msg = freeMarkerUtilities.getThemeLayoutPropertyValue(themeDisplay.getLayout(), "warning-alert-msg")/>
						<#if warning_alert_msg != "">
							<div class="container">
								<div class="row">
									<div class="col-12">
										<div class="alert alert-warning" style="font-size: 15px; width: 100%; margin: 25px 0;">
											${warning_alert_msg}
										</div>
									</div>
								</div>
							</div>
						</#if>
					</#if>
					<#if show_enlinea_help=="true">
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
						<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
						<@liferay_portlet["runtime"]
							defaultPreferences="${freeMarkerPortletPreferences}"
							portletProviderAction=portletProviderAction.VIEW
							instanceId="enlineaHelpWidget"
							portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
						${freeMarkerPortletPreferences.reset()}		
					</#if>
					<#if show_page_title=="true">
						<div class="h1-interior">
							<h1 class="container">${themeDisplay.getLayout().getName(locale)}</h1>
						</div>
					</#if>
					<#if show_procedure_title=="true">
						<div class="h1-interior">
							<h1 class="container"><@liferay.language_format arguments="${themeDisplay.getLayout().getName(locale)}" key="enlinea.procedure-title"/></h1>
						</div>
					</#if>
					<#if selectable>
						<@liferay_util["include"] page=content_include />
					<#else>
						${portletDisplay.recycle()}
						${portletDisplay.setTitle(the_title)}
						<@liferay_theme["wrap-portlet"] page="portlet.ftl">
							<@liferay_util["include"] page=content_include />
						</@>
					</#if>
					<#if procedure_page=="false">
						<div class="aragon-layout-tpl">
							<div class="portlet-layout row modules-two-columns">
								<div class="col portlet-column portlet-column-first" role="complementary" aria-labelledby="portal-most-visited-pages">
									<div class="portlet-boundary portlet-static portlet-static-end portlet-bgwhite-title">
										<section class="portlet">
											<div class="portlet-content">
												<h2 id="mostVisitedTitle" class="portlet-title-text">
													<a data-toggle="collapse" href="#mas-leido" role="button" aria-expanded="false" aria-controls="mas-leido" id="portal-most-visited-pages"><@liferay.language key="aragon.the-most-visited"/></a>
												</h2>
											</div>
										</section>
									</div>
									<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
									<#assign VOID = freeMarkerPortletPreferences.setValue("selectedCount", "5") />
									<@liferay_portlet["runtime"] defaultPreferences="${freeMarkerPortletPreferences}" portletProviderAction=portletProviderAction.VIEW instanceId="footerAragonMostVisitedPages" portletName="es_aragon_base_aragon_most_visited_pages_web"/>
									${freeMarkerPortletPreferences.reset()}
								</div>
								<div class="col portlet-column portlet-column-last" role="complementary" aria-labelledby="portal-last-visited-pages">
									<div class="portlet-boundary portlet-static portlet-static-end portlet-bgwhite-title">
										<section class="portlet">
											<div class="portlet-content">
												<h2 id="lastVisitedTitle" class="portlet-title-text">
													<a data-toggle="collapse" href="#ultimas-visitas" role="button" aria-expanded="false" aria-controls="ultimas-visitas" id="portal-last-visited-pages"><@liferay.language key="aragon.last-visits-pages"/></a>
												</h2>
											</div>
										</section>
									</div>
									<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
									<@liferay_portlet["runtime"] defaultPreferences="${freeMarkerPortletPreferences}" portletProviderAction=portletProviderAction.VIEW portletName="es_aragon_base_last_visited_pages_lastvisitedpagesportlet" />
									${freeMarkerPortletPreferences.reset()}
								</div>
							</div>
						</div>
					</#if>
				</section>
				<footer id="footer" role="contentinfo" class="footer" tabindex="-1" aria-label="Enlaces relevantes del portal">
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
					<#assign theme_groupID = htmlUtil.escape(theme_display.getCompanyGroupId()?string) />
					<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
					<@liferay_portlet["runtime"] defaultPreferences="${freeMarkerPortletPreferences}" portletProviderAction=portletProviderAction.VIEW instanceId="footerContentTop" portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
					${freeMarkerPortletPreferences.reset()}
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
					<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
					<#assign theme_groupID = htmlUtil.escape(theme_display.getCompanyGroupId()?string) />
					<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
					<@liferay_portlet["runtime"] defaultPreferences="${freeMarkerPortletPreferences}" portletProviderAction=portletProviderAction.VIEW instanceId="footerContentBottom" portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
					${freeMarkerPortletPreferences.reset()}
				</footer>
			</div>
			<#-- END WRAPPER -->
			<@liferay_util["include"] page=body_bottom_include />
			<@liferay_util["include"] page=bottom_include />
		</div>
	</body>
</html>