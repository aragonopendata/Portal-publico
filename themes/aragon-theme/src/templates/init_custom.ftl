<#assign layout_header_style = getterUtil.getString(themeDisplay.getThemeSetting("layout-header-style"))/>
<#assign euro_flag_url = getterUtil.getString(themeDisplay.getThemeSetting("euro-flag-url"))/>
<#assign show_page_title = getterUtil.getString(themeDisplay.getThemeSetting("show-page-title"))/>
<#assign show_back_button = getterUtil.getString(themeDisplay.getThemeSetting("show-back-button"))/>
<#assign show_enlinea_help = getterUtil.getString(themeDisplay.getThemeSetting("show-enlinea-help"))/>
<#assign show_most_visited = getterUtil.getString(themeDisplay.getThemeSetting("show-most-visited"))/>
<#assign show_procedure_title = getterUtil.getString(themeDisplay.getThemeSetting("show-procedure-title"))/>
<#assign html_title_format = getterUtil.getString(themeDisplay.getThemeSetting("html-title-format"))/>
<#assign dynamic_meta_description = getterUtil.getString(themeDisplay.getThemeSetting("dynamic-meta-description"))/>

<#assign freeMarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities") />
<#assign searcherUrl = freeMarkerUtilities.getSearcherUrl(themeDisplay.getLayout().getGroup())>

<#assign isJournalPage = freeMarkerUtilities.isJournalPage(request)/>
<#if isJournalPage>
	<#assign
		void = themeDisplay.setLanguageId("en_US")
		void = themeDisplay.setLocale(freeMarkerUtilities.getLocale("en_US"))
		void = user.setLanguageId("en_US")
		locale = themeDisplay.getLocale()
		void = request.getSession().setAttribute("org.apache.struts.action.LOCALE", locale)
	/>
</#if>