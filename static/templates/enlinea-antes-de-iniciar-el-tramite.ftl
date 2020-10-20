<#if section.getSiblings()?has_content>
	<div class="container text-center aside-tramites-block bg-gray inicio-tramites mb-5 readSpeakerOrigin">
		<h2 class="text-left u-padding-top-1">
			<a href="javascript:void(0)" data-control-collapse="${.vars['reserved-article-id'].data}-info-tramite-online" aria-expanded="false">
				${.vars['reserved-article-title'].data}
			</a>
		</h2>
		<div class="collapse" data-content-collapse="${.vars['reserved-article-id'].data}-info-tramite-online" id="info-tramite-online">
			<#list section.getSiblings() as cur_section>
				<div class="d-flex align-items-start info">
					<span class="icon-theme" style="background-image: url('${cur_section.icon.getData()}');"></span>
					<div class="text">
						${cur_section.getData()}
					</div>
				</div>
			</#list>
		</div>
	</div>
</#if>