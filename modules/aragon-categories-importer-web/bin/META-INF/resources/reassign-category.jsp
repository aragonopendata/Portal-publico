<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.categories_importer.model.ImportCategoryRegistry"%>
<%@page import="es.aragon.base.categories_importer.service.ImportCategoryRegistryLocalServiceUtil"%>

<%@ include file="init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL", StringPool.BLANK);
if (Validator.isNotNull(backURL)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);
}
long importCategoryRegistryId = ParamUtil.getLong(request, "importCategoryRegistryId", -1);
%>


<c:if test="<%=importCategoryRegistryId != -1%>">
	<%ImportCategoryRegistry importCategoryRegistry = ImportCategoryRegistryLocalServiceUtil.fetchImportCategoryRegistry(importCategoryRegistryId);%>
	<c:if test="<%=importCategoryRegistry != null%>">
		<%AssetCategory sourceAssetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(importCategoryRegistry.getCategoryId());%>
		<c:if test="<%=sourceAssetCategory != null%>">
			<%
			renderResponse.setTitle("Reasignar contenidos con la categoría " + sourceAssetCategory.getTitle(LocaleUtil.getDefault()));
			portletDisplay.setDescription("Desde aqui podras agregar las categorias seleccionadas a todos los contenidos y documentos categorizados con " + sourceAssetCategory.getTitle(LocaleUtil.getDefault()));
			%>
			<portlet:actionURL name="relocateCategories" var="relocateCategoriesActionURL">
				<portlet:param name="sourceAssetCategoryId" value="<%=String.valueOf(sourceAssetCategory.getCategoryId())%>"/>
				<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>"/>
			</portlet:actionURL>
			<liferay-frontend:edit-form method="post" action="<%=relocateCategoriesActionURL%>">
				<liferay-frontend:edit-form-body>
					<liferay-frontend:fieldset-group>
						<liferay-frontend:fieldset label="aragon-categories-importer-web.label.content-category" collapsed="<%=false%>" collapsible="<%=false%>">
							<liferay-ui:error key="no-such-new-category-error" message="aragon-categories-importer-web.error-message"/>
							<aui:field-wrapper label="aragon-categories-importer-web.label.new-categories">
								<liferay-asset:asset-categories-selector hiddenInput="newCategoryIds" className="<%=JournalArticle.class.getName()%>" categoryIds="<%=String.valueOf(sourceAssetCategory.getCategoryId())%>"/>
							</aui:field-wrapper>
						</liferay-frontend:fieldset>
					</liferay-frontend:fieldset-group>
				</liferay-frontend:edit-form-body>
				<liferay-frontend:edit-form-footer>
					<aui:button type="submit" value="aragon-categories-importer-web.button.reassign"></aui:button>
				</liferay-frontend:edit-form-footer>
			</liferay-frontend:edit-form>
		</c:if>
	</c:if>
</c:if>