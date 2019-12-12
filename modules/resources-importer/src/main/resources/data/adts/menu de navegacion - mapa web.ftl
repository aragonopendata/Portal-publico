<style>
	.mapa-web .collapse {
		padding-left: 50px ;
	}
	.mapa-web .arrow-open {
		margin: 2rem 0;
	}
	.dga-view .list-news-module .listado__item p.link {
		margin: 0;
	}
	.dga-view .list-news-module .listado__item p.link span{
		cursor: default;
	}
	.dga-view .list-news-module .listado__item p.link:hover .title {
		text-decoration: none;
	}
</style>
<#assign layoutLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")/>
<#assign freemarkerUtilities = serviceLocator.findService("es.aragon.base.freemarker_utilities.api.FreemarkerUtilities")/>
<#assign itemsCounter = 0/>
<div class="container u-container-mobile-0">
	<div class="list-news-module mapa-web">
		<#if entries?has_content>
			<ul class="listado">
				<!--Inicio-->
				<li class="col-12 listado__item">
					<a class="link" href="/">
						<span class="title">Inicio</span>
					</a>
				</li>
				<!-- Buscador -->
				<#assign searcherUrl = freemarkerUtilities.getSearcherUrl(themeDisplay.getLayout().getGroup())>
				<#if searcherUrl?? && searcherUrl?has_content>
					<li class="col-12 listado__item">
						<a class="link" href="${searcherUrl}">
							<span class="title">Buscador</span>
						</a>
					</li>
				</#if>
				<!-- Temas -->
				<#if layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/temas")??>
					<#assign temasLayout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/temas")/>
					<#assign childLayoutsLinksMap = freemarkerUtilities.getChildLayoutsLinksMap(temasLayout)/>
					<#assign itemsCounter = itemsCounter + 1/>
					<li class="col-12 listado__item">
						<a class="link" href="${temasLayout.getFriendlyURL()}">
							<span class="title">${temasLayout.getName(locale)}</span>
						</a>
						<#if childLayoutsLinksMap?? && childLayoutsLinksMap?has_content>
							<button class="arrow-open js-arrow-open collapsed" data-toggle="collapse" data-target="#collapse_${itemsCounter}" aria-expanded="false" aria-controls="collapse_${itemsCounter}" aria-label="Mostrar paginas hijas de ${entry.getName()}" data-label-closed="Mostrar paginas hijas de ${entry.getName()}" data-label-opened="Ocultar paginas hijas de ${entry.getName()}"></button>
							<div id="collapse_${itemsCounter}" class="collapse">
								<ul class="listado">
									<#list childLayoutsLinksMap?keys as childLayout>
										<#assign itemsCounter = itemsCounter + 1/>
										<li class="col-12 listado__item">
											<a class="link" href="${childLayoutsLinksMap[childLayout]}">
												<span class="title">${childLayout}</span>
											</a>
										</li>
									</#list>
								</ul>
							</div>
						</#if>
					</li>
				</#if>
				<!-- Resto de menu de navegacion -->
				<#list entries as entry>
					<#if entry??>
						<#assign itemsCounter = itemsCounter + 1/>
						<li class="col-12 listado__item">
							<#assign entryURL = entry.getURL()/>
							<#if entry.getLayout()?? && entry.getLayout()?has_content>
								<#assign entryURL = freemarkerUtilities.getLayoutFullURL(entry.getLayout(), themeDisplay.getScopeGroupId(), locale)/>
							</#if>
							<#if entryURL != themeDisplay.getPortalURL()>
								<a class="link" href="${entryURL}">
									<span class="title">${entry.getName()}</span>
								</a>
							<#else>
								<p class="link">
									<span class="title">${entry.getName()}</span>
								</p>
							</#if>
							<#assign pages = entry.getChildren()/>
							<#if pages?? && pages?has_content>
								<button class="arrow-open js-arrow-open collapsed" data-toggle="collapse" data-target="#collapse_${itemsCounter}" aria-expanded="false" aria-controls="collapse_${itemsCounter}" aria-label="Mostrar paginas hijas de ${entry.getName()}" data-label-closed="Mostrar paginas hijas de ${entry.getName()}" data-label-opened="Ocultar paginas hijas de ${entry.getName()}"></button>
								<div id="collapse_${itemsCounter}" class="collapse">
									<ul class="listado">
										<#list pages as page> 
											<#assign itemsCounter = itemsCounter + 1/>
											<li class="col-12 listado__item">
												<#assign childEntryURL = page.getURL()/>
												<#if page.getLayout()?? && page.getLayout()?has_content>
													<#assign childEntryURL = freemarkerUtilities.getLayoutFullURL(page.getLayout(), themeDisplay.getScopeGroupId(), locale)/>
												</#if>
												<#if childEntryURL != themeDisplay.getPortalURL()>
													<a class="link" href="${childEntryURL}">
														<span class="title">${page.getName()}</span>
													</a>
												<#else>
													<p class="link">
														<span class="title">${page.getName()}</span>
													</p>
												</#if>
											</li>
										</#list>
									</ul>
								</div>
							</#if>
						</li>
					</#if>
				</#list>
			</ul>
		</#if>
	</div>
</div>