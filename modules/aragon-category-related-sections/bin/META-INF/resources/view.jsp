<%@ include file="init.jsp" %>

<section class="filtrado-tema u-padding-bottom-2">
	<ul class="listado">
		<li class="col-xs-12 listado__item">
			<a href="${proceduresURL}" class="link" target="_blank" title='<liferay-ui:message key="title.procedures-link"/>'>
				<div class="icon">
					<img src="<%=themeDisplay.getPathThemeImages()%>/dga/icons/icon-tramites.svg" alt="">
				</div>
				<p class="name"><liferay-ui:message key="label.procedures" arguments='${originCategoryName}'/></p>
			</a>
		</li>
		<li class="col-xs-12 listado__item">
			<a href="${presentURL}" class="link">
				<div class="icon">
					<img src="<%=themeDisplay.getPathThemeImages()%>/dga/icons/icon-actualidad.svg" alt="">
				</div>
				<p class="name"><liferay-ui:message key="label.present" arguments='${originCategoryName}'/></p>
			</a>
		</li>
		<li class="col-xs-12 listado__item">
			<a href="${openDataURL}" class="link" title="Se abre en un nuevo portal web" target="_blank">
				<div class="icon">
					<img src="<%=themeDisplay.getPathThemeImages()%>/dga/icons/icon-documents.svg" alt="">
				</div>
				<p class="name"><liferay-ui:message key="label.openData" arguments='${originCategoryName}'/></p>
			</a>
		</li>
	</ul>
</section>