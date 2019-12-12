<!--
Template Name: Enlinea info ayuda widget
Template Description: Informacion de ayuda de enlinea en modo widget flotante
Template Key: ENLINEA_INFO_AYUDA_WIDGET
Structure Key: ENLINEA_INFO_AYUDA_WIDGET
Cacheable: true
Small Image: false
-->
<div class="en-linea position-relative">
	<div class="aside-block-module aside-block-module--ayuda">
		<p class="h2">
			<a href="javascript:void(0)" data-control-collapse="${.vars['reserved-article-id'].data}-help-widget" aria-expanded="false">
				${.vars['reserved-article-title'].data}
			</a>
		</p>
		<#if linkTitle.getSiblings()?has_content>
		<ul class="collapse" data-content-collapse="${.vars['reserved-article-id'].data}-help-widget" id="${.vars['reserved-article-id'].data}-help-widget">
			<#list linkTitle.getSiblings() as cur_linkTitle>
			<li class="d-flex flex-wrap">
				<span class="icon-theme" style="background-image: url('${cur_linkTitle.icon.getData()}');"></span>
				<#if cur_linkTitle.information.getData()?? && cur_linkTitle.information.getData() != "">
				<div class="informacion">
					<a href="${cur_linkTitle.link.getData()}">${cur_linkTitle.getData()}</a>
					<p>
						${cur_linkTitle.information.getData()}
					</p>
				</div>
				<#else>
				<a href="${cur_linkTitle.link.getData()}" class="info-link">${cur_linkTitle.getData()}</a>
				</#if>
			</li>
			</#list>
		</ul>
		</#if>
	</div>
</div>