<%@page import="com.liferay.dynamic.data.mapping.model.DDMStructure"%>
<%@page import="com.liferay.dynamic.data.mapping.model.DDMTemplate"%>
<%@page import="com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil"%>
<%@page import="com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.util.KeyValuePair"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.IntStream"%>
<%@page import="java.util.stream.LongStream"%>

<%@ include file="init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>" var="configurationActionURL"/>
<liferay-portlet:renderURL portletConfiguration="<%=true%>" var="configurationRenderURL" />

<%List<Group> groupsList = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, Boolean.TRUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);%>
<liferay-frontend:edit-form action="<%=configurationActionURL%>" method="post" name="fm">
	<aui:input name="<%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>"/>
	<aui:input name="redirect" type="hidden" value="<%=configurationRenderURL%>"/>
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset collapsible="<%= false %>" label="source">
				<aui:select name="preferences--originType--" label="Contenido web de origen"  onChange="originTypeChanged(this.value)">
					<aui:option label="Obtener por URL (compatible con urls de tipo '/-/')" value="web_content_url" selected="<%=originType.equals("web_content_url") ? true : false%>"></aui:option>
					<aui:option label="Obtener de un visor de contenido" value="web_content_display" selected="<%=originType.equals("web_content_display") ? true : false%>"></aui:option>
				</aui:select>
				<div id="webContentDisplayOriginTypeOptions">
					<aui:select name="preferences--layoutColumnId--" label="related-contents.config.origin-page-column.label" helpMessage="related-contents.config.origin-page-column.help">
						<aui:option value="<%=RelatedContentsConstants.LAYOUT_COLUMN_ANY%>" label="related-contents.config.origin-page-column.option.any"></aui:option>
						<%List<String> columns = layoutTypePortlet.getLayoutTemplate().getColumns();%>
						<c:if test="<%=columns != null && !columns.isEmpty()%>">
							<%for (String column : columns) {%>
								<aui:option value="<%=column%>" selected="<%=layoutColumnId.equals(column) ? true : false%>"><%=column%></aui:option>
							<%}%>
						</c:if>
					</aui:select>
				</div>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset collapsible="<%= false %>" label="related-contents.config.results-filter-label">
				<aui:select name="preferences--groupFilterType--" label="related-contents.config.scope.label" onChange="changeGroupIdsSelectorContainerVisibility(this.value)">
					<aui:option value="<%=RelatedContentsConstants.GROUP_FILTER_TYPE_ANY%>" label="related-contents.config.scope.option.any" selected="<%=groupFilterType.equals(RelatedContentsConstants.GROUP_FILTER_TYPE_ANY) ? true : false%>"/>
					<aui:option value="<%=RelatedContentsConstants.GROUP_FILTER_TYPE_CURRENT%>" label="related-contents.config.scope.option.current" selected="<%=groupFilterType.equals(RelatedContentsConstants.GROUP_FILTER_TYPE_CURRENT) ? true : false%>"/>
					<aui:option value="<%=RelatedContentsConstants.GROUP_FILTER_TYPE_MANUAL%>" label="related-contents.config.scope.option.manual" selected="<%=groupFilterType.equals(RelatedContentsConstants.GROUP_FILTER_TYPE_MANUAL) ? true : false%>"/>
				</aui:select>
				<aui:input name="preferences--groupIds--" type="hidden"/>
				<div id="groupIdsSelectorContainer" style="display: none;">
					<%
					List<KeyValuePair> availableGroups = new ArrayList<KeyValuePair>();
					List<KeyValuePair> selectedGroups = new ArrayList<KeyValuePair>();
					if (groupsList != null && !groupsList.isEmpty()) {
						for (Group currentGroup : groupsList) {
							//Si el array de sites configurados contiene el actual se agrega al listado de seleccionados, en caso contrario al de disponibles
							boolean isSelected = Boolean.FALSE;
							if (groupIds != null && groupIds.length > 0) {
								for (long auxGroupId : groupIds) {
									if (auxGroupId == currentGroup.getGroupId()) {
										isSelected = Boolean.TRUE;
									}
								}
							}
							if (isSelected) {
								selectedGroups.add(new KeyValuePair(String.valueOf(currentGroup.getGroupId()), currentGroup.getDescriptiveName(locale)));
							} else {
								availableGroups.add(new KeyValuePair(String.valueOf(currentGroup.getGroupId()), currentGroup.getDescriptiveName(locale)));
							}
						}
					}
					%>
					<liferay-ui:input-move-boxes leftBoxName="availableGroups" leftList="<%=availableGroups%>" leftTitle="available" rightBoxName="selectedGroups" rightList="<%=selectedGroups%>" rightTitle="selected"/>
				</div>
				<aui:select name="preferences--structureFilterType--" label="related-contents.config.structure-filter.label" onChange="changeStructureIdsSelectorContainerVisibility(this.value)">
					<aui:option value="<%=RelatedContentsConstants.STRUCTURE_FILTER_TYPE_ANY%>" label="related-contents.config.structure-filter.option.any" selected="<%=structureFilterType.equals(RelatedContentsConstants.STRUCTURE_FILTER_TYPE_ANY) ? true : false%>"/>
					<aui:option value="<%=RelatedContentsConstants.STRUCTURE_FILTER_TYPE_SAME_AS_ORIGIN%>" label="related-contents.config.structure-filter.option.same-as-origin" selected="<%=structureFilterType.equals(RelatedContentsConstants.STRUCTURE_FILTER_TYPE_SAME_AS_ORIGIN) ? true : false%>"/>
					<aui:option value="<%=RelatedContentsConstants.STRUCTURE_FILTER_TYPE_MANUAL%>" label="related-contents.config.structure-filter.option.manual" selected="<%=structureFilterType.equals(RelatedContentsConstants.STRUCTURE_FILTER_TYPE_MANUAL) ? true : false%>"/>
				</aui:select>
				<aui:input name="preferences--structureIds--" type="hidden"/>
				<div id="structureIdsSelectorContainer" style="display: none;">
					<%
					List<KeyValuePair> availableStructures = new ArrayList<KeyValuePair>();
					List<KeyValuePair> selectedStructures = new ArrayList<KeyValuePair>();
					if (!themeDisplay.getScopeGroup().isSite()) {
						//Si estamos en una plantilla se muestran las estructuras de todos los sites
						if (groupsList != null && !groupsList.isEmpty()) {
							for (Group currentGroup : groupsList) {
								List<DDMStructure> structuresList = DDMStructureLocalServiceUtil.getStructures(currentGroup.getGroupId(), ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
								if (structuresList != null && !structuresList.isEmpty()) {
									for (DDMStructure ddmStructure : structuresList) {
										//Si el array de estructuras configuradas contiene la actual se agrega al listado de seleccionadas, en caso contrario al de disponibles
										boolean isSelected = Boolean.FALSE;
										if (structureIds != null  && structureIds.length > 0) {
											for (long auxStructureId : structureIds) {
												if (auxStructureId == ddmStructure.getStructureId()) {
													isSelected = Boolean.TRUE;
												}
											}
										}
										if (isSelected) {
											selectedStructures.add(new KeyValuePair(String.valueOf(ddmStructure.getStructureId()), ddmStructure.getName(locale) + " (" + currentGroup.getDescriptiveName(locale) + ")"));
										} else {
											availableStructures.add(new KeyValuePair(String.valueOf(ddmStructure.getStructureId()), ddmStructure.getName(locale) + " (" + currentGroup.getDescriptiveName(locale) + ")"));
										}
									}
								}
							}
						}
					} else {
						//Si estamos en un site se muestran las estructuras del site actual y de global
						List<DDMStructure> totalStructuresList = new ArrayList<DDMStructure>();
						List<DDMStructure> globalStructuresList = DDMStructureLocalServiceUtil.getStructures(themeDisplay.getCompanyGroupId(), ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						List<DDMStructure> siteStructuresList = DDMStructureLocalServiceUtil.getStructures(themeDisplay.getScopeGroupId(), ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						totalStructuresList.addAll(globalStructuresList);
						totalStructuresList.addAll(siteStructuresList);
						if (totalStructuresList != null && !totalStructuresList.isEmpty()) {
							for (DDMStructure ddmStructure : totalStructuresList) {
								//Si el array de estructuras configuradas contiene la actual se agrega al listado de seleccionadas, en caso contrario al de disponibles
								boolean isSelected = Boolean.FALSE;
								if (structureIds != null  && structureIds.length > 0) {
									for (long auxStructureId : structureIds) {
										if (auxStructureId == ddmStructure.getStructureId()) {
											isSelected = Boolean.TRUE;
										}
									}
								}
								if (isSelected) {
									selectedStructures.add(new KeyValuePair(String.valueOf(ddmStructure.getStructureId()), ddmStructure.getName(locale) + (ddmStructure.getGroupId() == themeDisplay.getCompanyGroupId() ? " (Global)" : "")));
								} else {
									availableStructures.add(new KeyValuePair(String.valueOf(ddmStructure.getStructureId()), ddmStructure.getName(locale) + (ddmStructure.getGroupId() == themeDisplay.getCompanyGroupId() ? " (Global)" : "")));
								}
							}
						}
					}
					%>
					<liferay-ui:input-move-boxes leftBoxName="availableStructures" leftList="<%=availableStructures%>" leftTitle="available" rightBoxName="selectedStructures" rightList="<%=selectedStructures%>" rightTitle="selected"/>
				</div>
				<aui:select name="preferences--categoryFilterType--" label="related-contents.config.category-filter.label">
					<aui:option value="<%=RelatedContentsConstants.CATEGORY_FILTER_TYPE_ANY%>" selected="<%=categoryFilterType.equals(RelatedContentsConstants.CATEGORY_FILTER_TYPE_ANY) ? true : false%>" label="related-contents.config.category-filter.option.any" />
					<aui:option value="<%=RelatedContentsConstants.CATEGORY_FILTER_TYPE_ALL%>" selected="<%=categoryFilterType.equals(RelatedContentsConstants.CATEGORY_FILTER_TYPE_ALL) ? true : false%>" label="related-contents.config.category-filter.option.all" />
				</aui:select>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset collapsible="<%= false %>" label="related-contents.config.select-presentation-preferences-label">
				<aui:select name="preferences--orderByCol--" label="related-contents.config.order-by-col.label">
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_COL_PUBLISH_DATE%>" selected="<%=orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_PUBLISH_DATE) ? true : false%>" label="related-contents.config.order-by-col.option.publish-date"/>
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_COL_CREATE_DATE%>" selected="<%=orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_CREATE_DATE) ? true : false%>" label="related-contents.config.order-by-col.option.create-date" />
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_COL_MODIFIED_DATE%>" selected="<%=orderByCol.equals(AragonUtilitiesConstant.ORDER_BY_COL_MODIFIED_DATE) ? true : false%>" label="related-contents.config.order-by-col.option.modified-date" />
				</aui:select>
				<aui:select name="preferences--orderByType--" label="related-contents.config.order-by-type.label">
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_TYPE_DESC%>" selected="<%=orderByType.equals(AragonUtilitiesConstant.ORDER_BY_TYPE_DESC) ? true : false%>" label="related-contents.config.order-by-type.option.desc"/>
					<aui:option value="<%=AragonUtilitiesConstant.ORDER_BY_TYPE_ASC%>" selected="<%=orderByType.equals(AragonUtilitiesConstant.ORDER_BY_TYPE_ASC) ? true : false%>" label="related-contents.config.order-by-type.option.asc"/>
				</aui:select>
				<aui:input name="preferences--resultsNumber--" label="related-contents.config.results-number.label" value="<%=resultsNumber%>">
					<aui:validator name="number"></aui:validator>
				</aui:input>
				<aui:select name="preferences--templateKey--" label="related-contents.config.result-template.label">
					<aui:option value="<%=RelatedContentsConstants.DEFAULT_DISPLAY_TEMPLATE%>" selected="<%=templateKey.equals(RelatedContentsConstants.DEFAULT_DISPLAY_TEMPLATE) ? true : false%>" label="related-contents.config.result-template.option.default"></aui:option>
					<%
					if (!themeDisplay.getScopeGroup().isSite()) {
						//Si estamos en una plantilla se muestran las plantillas de todos los sites
						if (groupsList != null & !groupsList.isEmpty()) {
							for (Group currentGroup : groupsList) {
								if (currentGroup.isSite() && currentGroup.isActive()) {
									List<DDMTemplate> ddmTemplates = DDMTemplateLocalServiceUtil.getTemplates(currentGroup.getGroupId(), ClassNameLocalServiceUtil.getClassNameId(DDMStructure.class));
									if (ddmTemplates != null && !ddmTemplates.isEmpty()) {
					%>
										<optgroup label="<%=currentGroup.getName(locale)%>">
											<%for (DDMTemplate ddmTemplate : ddmTemplates) {%>
												<aui:option value="<%=ddmTemplate.getTemplateKey()%>" selected="<%=templateKey.equals(ddmTemplate.getTemplateKey()) ? true : false%>"><%=ddmTemplate.getName(locale)%></aui:option>
											<%}%>
										</optgroup>
					<%
									}
								}
							}
						}
					} else {
						//Si estamos en un site solo mostramos las plantillas del site actual y de global
						List<DDMTemplate> globalDdmTemplates = DDMTemplateLocalServiceUtil.getTemplates(themeDisplay.getCompanyGroupId(), ClassNameLocalServiceUtil.getClassNameId(DDMStructure.class));
						if (globalDdmTemplates != null && !globalDdmTemplates.isEmpty()) {
					%>
							<optgroup label="Global">
								<%for (DDMTemplate ddmTemplate : globalDdmTemplates) {%>
									<aui:option value="<%=ddmTemplate.getTemplateKey()%>" selected="<%=templateKey.equals(ddmTemplate.getTemplateKey()) ? true : false%>"><%=ddmTemplate.getName(locale)%></aui:option>
								<%}%>
							</optgroup>
					<%
						}
						List<DDMTemplate> siteDdmTemplates = DDMTemplateLocalServiceUtil.getTemplates(themeDisplay.getScopeGroupId(), ClassNameLocalServiceUtil.getClassNameId(DDMStructure.class));
						if (siteDdmTemplates != null && !siteDdmTemplates.isEmpty()) {
					%>
							<optgroup label="<%=themeDisplay.getScopeGroup().getDescriptiveName(locale)%>">
								<%for (DDMTemplate ddmTemplate : siteDdmTemplates) {%>
									<aui:option value="<%=ddmTemplate.getTemplateKey()%>" selected="<%=templateKey.equals(ddmTemplate.getTemplateKey()) ? true : false%>"><%=ddmTemplate.getName(locale)%></aui:option>
								<%}%>
							</optgroup>
					<%
						}
					}
					%>
				</aui:select>
			</liferay-frontend:fieldset>								
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit"/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<script type="text/javascript">
	function originTypeChanged(originTypeValue) {
		if (originTypeValue == "web_content_display") {
			$("#webContentDisplayOriginTypeOptions").show();
		} else {
			$("#webContentDisplayOriginTypeOptions").hide();
		}
	}
	
	function changeGroupIdsSelectorContainerVisibility(groupFilterType) {
		if (groupFilterType == "manual") {
			$("#groupIdsSelectorContainer").show();
		} else {
			$("#groupIdsSelectorContainer").hide();
		}
	}
	
	function changeStructureIdsSelectorContainerVisibility(structureFilterType) {
		if (structureFilterType == "manual") {
			$("#structureIdsSelectorContainer").show();
		} else {
			$("#structureIdsSelectorContainer").hide();
		}
	}
	
	$(document).ready(function() {
		originTypeChanged('<%=originType%>');
		changeGroupIdsSelectorContainerVisibility('<%=groupFilterType%>');
		changeStructureIdsSelectorContainerVisibility('<%=structureFilterType%>');
	});
</script>
<aui:script use="liferay-util-list-fields">
	A.one('#<portlet:namespace/>fm').on('submit', function(event) {
		//Moveboxes de sitios web
		var selectedGroups = Liferay.Util.listSelect('#<portlet:namespace/>selectedGroups');
		A.one('#<portlet:namespace/>groupIds').val(selectedGroups);
		//Moveboxes de estructuras
		var selectedStructures = Liferay.Util.listSelect('#<portlet:namespace/>selectedStructures');
		A.one('#<portlet:namespace/>structureIds').val(selectedStructures);
		//Submit del formulario
		submitForm('#<portlet:namespace/>fm');
	});
</aui:script>

