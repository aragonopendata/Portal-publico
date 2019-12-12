<nav role="navigation" aria-label="Menú principal" class="navbar-collapse collapse" id="navMenuButton">
    <#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
	<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBorders", "false") />
	<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupShowBordersDefault", "false") />
	<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupUseCustomTitle", "false") />
	<#assign VOID = freeMarkerPortletPreferences.setValue("groupId", '${group_id}') />
	<#assign VOID = freeMarkerPortletPreferences.setValue("displayStyle", "MENU-NAVEGACION") />
	
	<div class="language hidden-sm hidden-md hidden-lg">
		<@liferay_portlet["runtime"] portletName="com_liferay_site_navigation_language_web_portlet_SiteNavigationLanguagePortlet" instanceId="mobileLanguageSelector"/>
	</div>

	<@liferay_portlet["runtime"]
		defaultPreferences="${freeMarkerPortletPreferences}"
		portletProviderAction=portletProviderAction.VIEW
		instanceId="navigationMenu"
		portletName="com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet" />
		
	${freeMarkerPortletPreferences.reset()}	      
	
	<a role="link" tabindex="0" class="navbar-menu-icon close hidden-sm hidden-md hidden-lg">
   		<span></span>
   		<span></span>
    	<span></span>
    	<strong><@liferay.language key="close"/></strong>
	</a>    
</nav>