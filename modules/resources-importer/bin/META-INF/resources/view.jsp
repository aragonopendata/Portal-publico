<%@ include file="/init.jsp" %>

<style>
	a.btn{
    	border: none;
    	font-size: 13px;
    	text-decoration: underline;
    	padding: 0px
	}
	a.btn-template{
		margin-left: 32px
	}
	a.btn-default:hover{
    	background-color:#ffffff
	}
	div.input-checkbox-wrapper{
		margin-top: 10px
	}
</style>

<jsp:useBean id="structuresList" type="java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>" scope="request"/>
<jsp:useBean id="templatesList" type="java.util.List<com.liferay.dynamic.data.mapping.model.DDMTemplate>" scope="request"/>
<jsp:useBean id="adtTemplatesList" type="java.util.List<com.liferay.dynamic.data.mapping.model.DDMTemplate>" scope="request"/>

<div class="container-fluid-1280 main-content-body">

	<liferay-ui:tabs
	    names='<%= "resources-importer.cms-structures-title,resources-importer.cms-templates-title,resources-importer.adt-templates-title" %>'
	    refresh="<%= false %>"
	    type="tabs nav-tabs-default"
	>
		<!-- ESTRUCTURAS -->
		<liferay-ui:section>
			<%@ include file="/tabs/structures.jspf" %>
		</liferay-ui:section>
		
		<!-- PLANTILLAS -->
		<liferay-ui:section>
			<%@ include file="/tabs/templates.jspf" %>
		</liferay-ui:section>
		
		<!-- PLANTILLAS DE VISUALIZACION DE LA APLICACION -->
		<liferay-ui:section>
			<%@ include file="/tabs/application_display_templates.jspf" %>
		</liferay-ui:section>
		
	</liferay-ui:tabs>
</div>