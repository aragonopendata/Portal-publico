<%@ include file="/init.jsp" %>

<style>
	textarea.form-control{
		height: 650px
	}
	textarea.form-control:disabled{
		color:black;
		background: url(http://i.imgur.com/2cOaJ.png);
		background-repeat: no-repeat;
		padding-left: 35px;
		padding-top: 10px;
	    border-color:#ccc
	}
	div.portlet-column-first {
	  border-right: 1px dashed #333
	}
	.row{
	    margin: 20px
	}
</style>

<%
	String originalContent = StringPool.BLANK;
	String moduleContent = StringPool.BLANK;
	String resourceType = ParamUtil.getString(request, "resourceType");
	long resourceId = ParamUtil.getLong(request, "resourceId");
	
	if(DDMTemplate.class.getName().equals(resourceType)){
		String templateType = ParamUtil.getString(request, "templateType");
		DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchDDMTemplate(resourceId);
		if("CMS".equals(templateType)){
			DDMTemplate templateModel = TemplateUtil.fetchTemplateModel(ddmTemplate.getTemplateKey(), themeDisplay.getScopeGroupId());
			moduleContent = templateModel.getScript();
		}else if("ADT".equals(templateType)){
			DDMTemplate templateModel = ApplicationDisplayTemplateUtil.fetchTemplateModel(ddmTemplate.getTemplateKey(), themeDisplay.getScopeGroupId());
			moduleContent = templateModel.getScript();
		}
		originalContent = ddmTemplate.getScript();
	}else if(DDMStructure.class.getName().equals(resourceType)){
		DDMStructure ddmStructre = _ddmStructureLocalService.fetchDDMStructure(resourceId);
		DDMStructure structureModel = StructureUtil.fetchStructureModel(ddmStructre.getStructureKey(), themeDisplay.getScopeGroupId());
		moduleContent = structureModel.getDefinition();
		originalContent = ddmStructre.getDefinition();
	}
	
%>

<div class="row">
	<div class="col portlet-column portlet-column-first">
		<div class="list-group-header"> 
			<div class="list-group-header-title"><liferay-ui:message key="resources-importer.original-content-field"/></div> 
		</div>
		<aui:input label="" name="resources-importer.original-content-field" type="textarea" value="<%=originalContent%>" disabled="<%=true%>"/>
	</div>
	
	<div class="col portlet-column portlet-column-last">
		<div class="list-group-header"> 
			<div class="list-group-header-title"><liferay-ui:message key="resources-importer.module-content-field"/></div> 
		</div>
		<aui:input label="" name="resources-importer.module-content-field" type="textarea" value="<%=moduleContent%>" disabled="<%=true%>"/>
	</div>
</div>