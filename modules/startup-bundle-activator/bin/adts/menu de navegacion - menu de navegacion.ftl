<!--
Template Name: Menu de navegacion aragon.es
Template Description: Visualización del menu de navegacion de aragon.es
Template Key: MENU-NAVEGACION
Structure Key: 
Cacheable: true
Small Image: false
Class Name: com.liferay.portal.kernel.theme.NavItem
-->

<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#assign layoutLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")/>
<ul class="navbar-nav mr-auto">
	<!-- Temas -->
	<#if layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/temas")??>
		<#assign temasLayout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/temas")/>
		<#assign nav_item_dropdown = ""/>
		<#assign nav_item_dropdown_toggle = ""/>
		<#assign nav_item_extra_params= ""/>
		<#assign childLayoutsLinksMap = freemarkerUtilities.getChildLayoutsLinksMap(temasLayout)/>
		<#if childLayoutsLinksMap?? && childLayoutsLinksMap?has_content>
			<#assign nav_item_dropdown = "dropdown"/>
			<#assign nav_item_dropdown_toggle = "dropdown-toggle"/>
			<#assign nav_item_extra_params = "data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'"/>
		<#else>
			<#assign nav_item_dropdown_toggle = "solo" />
		</#if>
		<li class="nav-item ${nav_item_dropdown}">
			<#if themeDisplay.getLayout().getFriendlyURL(locale) == temasLayout.getFriendlyURL()>
				<a class="nav-link ${nav_item_dropdown_toggle}" href="${temasLayout.getFriendlyURL()}" id="dropdownTemas" aria-current="page" ${nav_item_extra_params} >
					<strong>${temasLayout.getName(locale)}</strong>
				</a>
			<#else>
				<a class="nav-link ${nav_item_dropdown_toggle}" href="${temasLayout.getFriendlyURL()}" id="dropdownTemas" ${nav_item_extra_params}>
					<span>${temasLayout.getName(locale)}</span>
				</a>
			</#if>
			<#if childLayoutsLinksMap?? && childLayoutsLinksMap?has_content>
				<ul class="dropdown-menu" aria-labelledby="dropdownTemas">
					<#list childLayoutsLinksMap?keys as childLayout>
						<#if themeDisplay.getLayout().getFriendlyURL(locale) == childLayoutsLinksMap[childLayout]>
							<li>
								<a class="dropdown-item" href="${childLayoutsLinksMap[childLayout]}" aria-current="page">
									<strong>${childLayout}</strong>
								</a>
							</li>
						<#else>
							<li>
								<a class="dropdown-item" href="${childLayoutsLinksMap[childLayout]}">${childLayout}</a>
							</li>
						</#if>
					</#list> 
				</ul>
			</#if>
		</li>
	</#if>
	<#assign nav_items = entries />
	<#assign itemsCounter = 0/>
	<#list nav_items as nav_item>
		<#assign itemsCounter = itemsCounter + 1/>
		<#assign nav_item_dropdown = ""/>
		<#assign nav_item_dropdown_toggle = ""/>
		<#assign nav_item_extra_params= ""/>	
		<#if nav_item.hasChildren()>
			<#assign nav_item_dropdown = "dropdown"/>
			<#assign nav_item_dropdown_toggle = "dropdown-toggle"/>
			<#assign nav_item_extra_params = "data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'"/>
		<#else>
			<#assign nav_item_dropdown_toggle = "solo" />
		</#if>
		<li class="nav-item ${nav_item_dropdown}">
			<#assign navItemURL = nav_item.getURL()/>
			<#if nav_item.getLayout()?? && nav_item.getLayout()?has_content>
				<#assign navItemURL = freemarkerUtilities.getLayoutFullURL(nav_item.getLayout(), themeDisplay.getScopeGroupId(), locale)/>
			</#if>
			<#if themeDisplay.getLayout().getFriendlyURL(locale) == navItemURL>
				<a class="nav-link ${nav_item_dropdown_toggle}" href="${navItemURL}" id="dropdown${itemsCounter}" aria-current="page" ${nav_item_extra_params}>
					<strong>${nav_item.getName()}</strong>
				</a>
			<#else>
				<a class="nav-link ${nav_item_dropdown_toggle}" href="${navItemURL}" id="dropdown${itemsCounter}" ${nav_item_extra_params}>
					<span>${nav_item.getName()}</span>
				</a>			
			</#if>
			<#if nav_item.hasChildren()>
				<ul class="dropdown-menu" aria-labelledby="dropdown${itemsCounter}">
					<#list nav_item.getChildren() as nav_child>
						<#assign childNavItemURL = nav_child.getURL()/>
						<#if nav_child.getLayout()?? && nav_child.getLayout()?has_content>
							<#assign childNavItemURL = freemarkerUtilities.getLayoutFullURL(nav_child.getLayout(), themeDisplay.getScopeGroupId(), locale)/>
						</#if>
						<#if themeDisplay.getLayout().getFriendlyURL(locale) == childNavItemURL>
							<li>
								<a class="dropdown-item" href="${childNavItemURL}" aria-current="page" ${nav_child.getTarget()}>
									<strong>${nav_child.getName()}</strong>
								</a>
							</li>
						<#else>
							<li>
								<a class="dropdown-item" href="${childNavItemURL}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
							</li>						
						</#if>
					</#list>
				</ul>
			</#if>
		</li>
	</#list>
</ul>