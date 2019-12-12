<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page
import="es.aragon.base.migration.service.ContentLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.journal.service.JournalArticleLocalService"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalService"%>

<%@ include file="init.jsp"%>

<%
HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(request);
long contentId = GetterUtil.getLong(httpReq.getParameter("contentId"), 0);
%>

<c:if test="<%=contentId > 0%>">
<%
String title = StringPool.BLANK;
String subtitle = StringPool.BLANK;
String body = StringPool.BLANK;

Content content = ContentLocalServiceUtil.fetchContent(contentId);
if (content != null) {
title = content.getTitle();
body = content.getOriginalBody();
}
String pattern = "dd/MM/yyyy";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
String[]category = null;

String fechaCreacion = simpleDateFormat.format(content.getDateCreated()); 
if(content.getTags() != null && !content.getTags().isEmpty()){
category = content.getTags().split(",");
}

%>
<style>
	html body .control-menu, .autofit-section, .portlet-topper,	.lfr-product-menu-panel {
	display: none !important;
	}
	
	.has-control-menu #wrapper {
	margin: 0 !important;
	padding: 0 !important;
	}
	
	.preview-overlay {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	z-index: 1000;
	}
	
	.lfr-product-menu-panel {
	display: none;
	}
	
	#_com_liferay_site_navigation_breadcrumb_web_portlet_SiteNavigationBreadcrumbPortlet_breadcrumbs-defaultScreen {
		display: none;
	}
</style>
<div class="preview-overlay"></div>
<div>
	<section>
		<nav role="navigation" aria-label="Estás en:">
			<div class="breadcrumb">
				<div class="container">
					<ul class="breadcrumb__listado">
						<li><a href="/" class="link">Inicio</a></li> 
						<li><strong class="final" aria-current="page"><%=title%></strong></li>
					</ul>
				</div> 
			</div>
		</nav> 
	</section>
</div>
<div class="container">
	<div class="detail-news-module u-padding-bottom-6">
		<h1 class="detail-news-module__h1"><%=title%></h1>
		<div class="detail-news-module__date-categories">
			<p class="date"><span><%=fechaCreacion%></span></p>
				<ul class="categories">
					<c:if test="<%=category != null && category.length > 0%>">
						<%for (String nodo : category) {%>
						<%AssetCategory categoria = AssetCategoryLocalServiceUtil.fetchAssetCategory(GetterUtil.getLong(nodo,0)); %>
						<li class="categories__item"><span class="name"><%=categoria.getTitle(locale) %></span></li>
						<%}
						%>
					</c:if>
				</ul>	
		</div>
	</div>

	<div class="detail-news-module__intro">
		<p><%=content.getShortExcerpt()%></p>
	</div>
	<div>
		<p><%=body%></p>
	</div>
</div>

</c:if>