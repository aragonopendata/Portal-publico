<section class="indice-departamentos u-padding-bottom-4 u-padding-bottom-sm-6">
	<div class="container p-0">
		<div class="row">
			<#if layout.hasChildren()>
				<#list layout.getChildren() as level2Topic>
					<section class="elmts-list col-xs-12 col-lg-6 u-padding-bottom-2">
						<h2 class="title">
							<a href="${level2Topic.getFriendlyURL(locale)}">
							    ${level2Topic.getName(locale)}
					        </a>
						</h2>
						<#if level2Topic.hasChildren()>
							<ul class="listado">
								<#list level2Topic.getChildren() as level3Topic>
									<li class="item">
										<h3>
											<a href="${level3Topic.getFriendlyURL(locale)}" class="link">
												<span class="name">${level3Topic.getName(locale)}</span>
											</a>
										</h3>
									</li>
								</#list>
							</ul>
						</#if>
					</section>
				</#list>
			</#if>
		</div>
	</div>
</section>