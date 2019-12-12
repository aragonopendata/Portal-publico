<!--
Template Name: Enlinea info ayuda
Template Description: Informacion de ayuda de enlinea
Template Key: ENLINEA_INFO_AYUDA
Structure Key: ENLINEA_INFO_AYUDA
Cacheable: true
Small Image: false
-->
<div class="aside-block-module">
	<h2>${.vars['reserved-article-title'].data}</h2>
	<#if linkTitle.getSiblings()?has_content>
	<ul>
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