<%@ include file="init.jsp" %>

<%@ page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %>
<%@ page import="com.liferay.asset.kernel.model.AssetEntry" %>
<%@ page import="com.liferay.layout.page.template.model.LayoutPageTemplateEntry" %>
<%@ page import="com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.ClassName" %>
<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.model.LayoutPrototype" %>
<%@ page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %>
<%@ page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ page import="java.util.List" %>

<%
String presentFilters = portletPreferences.getValue("presentFilters", "");
String documentFilters = portletPreferences.getValue("documentFilters", "");
long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(themeDisplay.getCompanyId(), true);
String classNameResource = StringPool.BLANK;
//get groupId to get site's vocabularies
long groupId = 0;
Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getSiteGroupId());

// if we are in a template layout
if (group.getType() == 0) {
	LayoutPrototype layoutPrototype = LayoutPrototypeLocalServiceUtil.getLayoutPrototype(Long.valueOf(group.getGroupKey()));
	List<LayoutPageTemplateEntry> layoutPageTemplateEntries = LayoutPageTemplateEntryLocalServiceUtil.getLayoutPageTemplateEntries(-1, -1);
	for (LayoutPageTemplateEntry layoutPageTemplateEntry : layoutPageTemplateEntries) {
		if (layoutPageTemplateEntry.getLayoutPrototypeId() == Long.valueOf(group.getGroupKey())) {
			groupId = layoutPageTemplateEntry.getGroupId();
			break;
		}
	}
}
else {
	// we are in a normal layout
	groupId = group.getGroupId();
}
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<aui:input type="hidden" name="categoriesGroupId" value="<%= groupId %>" />
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<label><strong><liferay-ui:message key="configuration.label.present-filters" /></strong></label>
			<liferay-asset:asset-categories-selector hiddenInput="presentFilters" groupIds="<%= new long[] {groupId} %>" categoryIds="<%= presentFilters %>" className="<%= AssetEntry.class.getName() %>" />
			<br>
			<aui:select label="configuration.label.document-filters" name="documentFilters" helpMessage="configuration.label.document-helpmessage">
			<%for(long classNameId : classNameIds) {
				ClassName className = ClassNameLocalServiceUtil.fetchClassName(classNameId);
				if(Validator.isNotNull(className)) {
					classNameResource = ResourceActionsUtil.getModelResource(themeDisplay.getLocale(), className.getValue());%>
					<aui:option value="<%=classNameId%>" selected="<%=documentFilters.equals(String.valueOf(classNameId))? true : false%>" ><%= classNameResource %></aui:option>
				<%}%>
			<%}%>
			</aui:select>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />
		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>