<!--
Template Name: Preguntas frecuentes
Template Description: Preguntas frecuentes
Template Key: PREGUNTAS_FRECUENTES
Structure Key: PREGUNTAS_FRECUENTES
Cacheable: true
Small Image: false
-->
<section class="en-linea tramite-body">
	<div class="container u-container-mobile-0">
		<h2 class="ocultovisual">Preguntas frecuentes</h2> 
		<#if question.getSiblings()?has_content>
			<ol class="list">
				<#list question.getSiblings() as cur_question>
					<#if cur_question.getData()?has_content && cur_question.response.getData()?has_content>
						<li class="list__item">
							<p class="title">
								<a href="javascript:void(0)" data-control-collapse="${.vars['reserved-article-id'].data}-list-section-${cur_question?index}" aria-expanded="false" title="Pulsa para mostrar u ocultar la respuesta">
									${cur_question.getData()}
								</a>
							</p>
							<div class="info collapse" data-content-collapse="${.vars['reserved-article-id'].data}-list-section-${cur_question?index}" id="${.vars['reserved-article-id'].data}-list-section-${cur_question?index}">
								<div class="pt-5 detail-news-module__body-news__description">
									${cur_question.response.getData()}
								</div>
							</div>
						</li>
					</#if>
				</#list>
			</ol>
		</#if>
	</div>
</section>

<#if question.getSiblings()?has_content>
	<script type="application/ld+json">
		{
			"@context": "https://schema.org",
			"@type": "FAQPage",
			"mainEntity": [
			<#list question.getSiblings() as cur_question>
				{
					"@type": "Question",
					"name": "${cur_question.getData()}",
					"acceptedAnswer": {
						"@type": "Answer",
						"text": "${cur_question.response.getData()}"
					}
				}
				<#if !cur_question?is_last>
					,
				</#if>
			</#list>
			]
		}
	</script>
</#if>