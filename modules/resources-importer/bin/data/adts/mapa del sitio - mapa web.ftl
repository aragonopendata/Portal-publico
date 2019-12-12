<style>
	.mapa-web .collapse {
		padding-left: 50px ;
	}
	.mapa-web .arrow-open {
		margin: 2rem 0;
	}
</style>
<div class="container u-container-mobile-0">
	<div class="list-news-module mapa-web">
		<#if entries?has_content>
			<ul class="listado">
				<#list entries as entry>
					<#if layoutPermission.containsWithoutViewableGroup(permissionChecker, entry, "VIEW")>
						<li class="col-12 listado__item">
							<#assign layoutType = entry.getLayoutType()/>
							<a class="link" <#if layoutType.isBrowsable()> href="${portalUtil.getLayoutURL(entry, themeDisplay)}"</#if>>
								<span class="title">${entry.getName(locale)}</span>
							</a>
							<@displayPages depth=1 pages=entry.getChildren(permissionChecker) identifier=entry?index parentLabel=entry.getName(locale)/>
						</li>
					</#if>
				</#list>
			</ul>
		</#if>
		<#macro displayPages depth pages identifier parentLabel>
			<#if pages?has_content && ((depth < displayDepth?number) || (displayDepth?number == 0))> 
				<button class="arrow-open js-arrow-open collapsed" data-toggle="collapse" data-target="#collapse_${identifier}" aria-expanded="false" aria-controls="collapse_${identifier}" aria-label="Mostrar paginas hijas de ${parentLabel}" data-label-closed="Mostrar paginas hijas de ${parentLabel}" data-label-opened="Ocultar paginas hijas de ${parentLabel}"></button>
				<div id="collapse_${identifier}" class="collapse">
					<ul class="listado">
						<#list pages as page> 
							<li class="col-12 listado__item">
								<#assign pageType = page.getLayoutType()/>
								<a class="link" <#if pageType.isBrowsable()> href="${portalUtil.getLayoutURL(page, themeDisplay)}"</#if>>
									<span class="title">${page.getName(locale)}</span>
								</a>
								<#assign newIdentifier = identifier/>
								<#assign newIdentifier += "_"/>
								<#assign newIdentifier += page?index/>
								<@displayPages depth=depth + 1 pages=page.getChildren(permissionChecker) identifier=newIdentifier parentLabel=page.getName(locale)/> 
							</li>
						</#list> 
					</ul>
				</div>
			</#if>
		</#macro>
	</div>
</div>