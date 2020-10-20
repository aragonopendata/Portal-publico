<section class="indice-departamentos u-padding-bottom-4 u-padding-bottom-sm-6">
	<ul class="container p-0">	
		<#if layout.hasChildren()>
			<#list layout.getChildren() as level2Topic>
				<li class="elmts-list col-xs-12 col-lg-6 u-padding-bottom-2">
					<div class="title">						
						<a href="${level2Topic.getFriendlyURL(locale)}">
						    ${level2Topic.getName(locale)}
				        </a>
					</div>
					<#if level2Topic.hasChildren()>
						<ul class="listado">
							<#list level2Topic.getChildren() as level3Topic>
								<li class="item">
									<a href="${level3Topic.getFriendlyURL(locale)}" class="link">
										<span class="name">${level3Topic.getName(locale)}</span>
									</a>
								</li>
							</#list>
						</ul>
					</#if>
				</li>
			</#list>
		</#if>
	</ul>
</section>