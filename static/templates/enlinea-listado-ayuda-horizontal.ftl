<!--
Template Name: Listado de ayuda Enlinea
Template Description: Listado de links pagina de ayuda de Enlinea
Template Key: LISTADO_AYUDA_ENLINEA
Structure Key: LISTADO_AYUDA_ENLINEA
Cacheable: true
Small Image: false
-->
<div class="portlet-bgblue-title">
   <div class="portlet-title-text">
       <span class="container">${.vars['reserved-article-title'].data}</span>
   </div>
</div>
<div class="en-linea interior bg-gray">
	<div class="container d-flex flex-wrap justify-content-between text-center mb-0 aside-block">
	<#if sectionTitle.getSiblings()?has_content>
		<#list sectionTitle.getSiblings() as cur_sectionTitle>
			<div class="aside-block-module">
				<div class="aside-block-module__title">
					<#if cur_sectionTitle.sectionImage.getData()?? && cur_sectionTitle.sectionImage.getData() != "">
						<i class="icon-theme icon-big" style="background-image: url('${cur_sectionTitle.sectionImage.getData()}');"></i>
					</#if>
					<#if cur_sectionTitle.linkTitle.getSiblings()?has_content && cur_sectionTitle.linkTitle.getSiblings()[0].getData()?? && cur_sectionTitle.linkTitle.getSiblings()[0].getData() != "">
						<a href="javascript:void(0)" data-control-collapse="${.vars['reserved-article-id'].data}-help-section-h-${cur_sectionTitle?index}" aria-expanded="false" class="name-container with-content">
					<#else>
						<a class="name-container" href="${cur_sectionTitle.sectionTitleURL.getData()}">
					</#if>
					<p class="name">${cur_sectionTitle.getData()}</p>
					<#if cur_sectionTitle.sectionSubtitle.getData()?? && cur_sectionTitle.sectionSubtitle.getData() != "">
						<p class="name-subtitle">${cur_sectionTitle.sectionSubtitle.getData()}</p>
					</#if>
					</a>
				</div>
				<#if cur_sectionTitle.linkTitle.getSiblings()?has_content && cur_sectionTitle.linkTitle.getSiblings()[0].getData()?? && cur_sectionTitle.linkTitle.getSiblings()[0].getData() != "">
					<ul class="aside-block-module__tramites collapse" data-content-collapse="${.vars['reserved-article-id'].data}-help-section-h-${cur_sectionTitle?index}" id="${.vars['reserved-article-id'].data}-help-section-h-${cur_sectionTitle?index}">
						<#list cur_sectionTitle.linkTitle.getSiblings() as cur_linkTitle>
							<#if cur_linkTitle.getData()?? && cur_linkTitle.getData() != "">
								<li>
									<a href="${cur_linkTitle.linkURL.getData()}">
										${cur_linkTitle.getData()}
									</a>
								</li>
							</#if>
						</#list>
					</ul>
				</#if>
			</div>
		</#list>
	</#if>
	</div>
</div>