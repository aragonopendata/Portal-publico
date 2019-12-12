<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.journal.model.JournalFolder"%>
<%@page import="com.liferay.journal.model.JournalFolderConstants"%>
<%@page import="com.liferay.journal.service.JournalFolderLocalServiceUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant"%>
<%@page import="es.aragon.base.migration.model.Area"%>
<%@page import="es.aragon.base.migration.model.Content"%>
<%@page import="es.aragon.base.migration.model.ContentMetadata"%>
<%@page import="es.aragon.base.migration.service.AreaLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.service.ContentLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.service.ContentMetadataLocalServiceUtil"%>
<%@page import="es.aragon.base.migration.util.ContentPreviewUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>

<%@include file="init.jsp"%>

<liferay-ui:success key='content-imported-successfully' message='aragon.migration.content-imported-successfully'/>
<liferay-ui:error key='content-imported-error' message='aragon.migration.content-imported-error' />
<liferay-ui:error key='error-categories' message='aragon.migration.content-category-error' />

<%
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm");

//Back URL
String backURL = ParamUtil.getString(request, "backURL");
portletDisplay.setURLBack(backURL);
portletDisplay.setShowBackIcon(true);

//Images selector URL
String imagesSelectorURL = (String)request.getAttribute("imagesSelectorURL");

//Content attributes inicialization
long contentId = ParamUtil.getLong(request, "contentId", 0);
String contentType = StringPool.BLANK;
String title = StringPool.BLANK;
String url = StringPool.BLANK;
String excerpt = StringPool.BLANK;
String originalBody = StringPool.BLANK;
String comments = StringPool.BLANK;
long actionId = 0;
int visibility = 0;
long statusId = 1;
String tags = StringPool.BLANK;
List<UserGroup> userGroups = UserGroupLocalServiceUtil.getGroupUserGroups(themeDisplay.getScopeGroupId());
long userGroupId = 0;
long journalFolderId = 0;
//Content metadata attributes
String contentMetadataPrimaryBody = StringPool.BLANK;
String contentMetadataPrimaryImage = StringPool.BLANK;
String contentMetadataPrimaryVideo = StringPool.BLANK;
String contentMetadataSecondaryBody = StringPool.BLANK;
String contentMetadataImagesGalleryTitle = StringPool.BLANK;
String contentMetadataImagesGalleryImages = StringPool.BLANK;
String contentMetadataLinkListTitle = StringPool.BLANK;
String contentMetadataLinkListLinks = StringPool.BLANK;

//Auxiliares
String modifiedDateString = "-";
String modifiedUserName = "-";

//Bd content data retrieve
Content content = ContentLocalServiceUtil.fetchContent(contentId);
if (content != null) {
	contentType = content.getContentType();
	title = content.getTitle();
	url = content.getUrl();
	excerpt = content.getExcerpt();
	originalBody = content.getOriginalBody();
	contentMetadataPrimaryBody = content.getOriginalBody();
	tags = content.getTags();
	comments = content.getComments();
	actionId = content.getActionId();
	visibility = content.getVisibility();
	statusId = content.getStatusId();
	userGroupId = content.getUserGroupId();
	journalFolderId = content.getJournalFolderId();
	if (content.getDateModified() != null) {
		modifiedDateString = simpleDateFormat.format(content.getDateModified());
	}
	if (content.getLastModifiedUserId() != 0) {
		User lastModifiedUser = UserLocalServiceUtil.fetchUser(content.getLastModifiedUserId());
		if (lastModifiedUser != null) {
			modifiedUserName = lastModifiedUser.getFullName();
		}
	}
	ContentMetadata contentMetadata = ContentMetadataLocalServiceUtil.fetchByContentId(contentId);
	if (contentMetadata != null) {
		if (Validator.isNotNull(contentMetadata.getPrimaryBody())) {
			contentMetadataPrimaryBody = contentMetadata.getPrimaryBody();
		}
		contentMetadataPrimaryImage = contentMetadata.getPrimaryImage();
		contentMetadataPrimaryVideo = contentMetadata.getPrimaryVideo();
		contentMetadataSecondaryBody = contentMetadata.getSecondaryBody();
		contentMetadataImagesGalleryTitle = contentMetadata.getImagesGalleryTitle();
		contentMetadataImagesGalleryImages = contentMetadata.getImagesGalleryImages();
		contentMetadataLinkListTitle = contentMetadata.getLinksListTitle();
		contentMetadataLinkListLinks = contentMetadata.getLinksListLinks();
	}
}
%>

<portlet:actionURL name="editContent" var="editContentActiontURL">
	<portlet:param name="backURL" value="<%=themeDisplay.getURLCurrent()%>"/>
</portlet:actionURL>
<liferay-frontend:edit-form action="<%=editContentActiontURL%>" method="post" name="fm" onSubmit="presubmit();" autocomplete="off">
	<aui:input name="contentId" type="hidden" value="<%=contentId%>"/>
	<liferay-frontend:edit-form-body>
		<div class="alert alert-warning text-center">
			<strong class="lead"><%=LanguageUtil.get(request, "modified-date") %>:</strong>
			<span style="margin-right: 15px;"><%=modifiedDateString%></span>
			<strong class="lead"><%=LanguageUtil.get(request, "user") %>:</strong>
			<span><%=modifiedUserName%></span>
		</div>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset label="source" collapsed="<%=false%>" collapsible="<%=true%>">
				<strong>URL</strong>
				<div class="form-group input-text-wrapper">
					<a href="<%=url%>" target="_blank"><%=url%></a>
				</div>
				<aui:input name="title" label="aragon.migration.contentportlet.title" value="<%=title%>" readonly="readonly"/>
				<%
				Area area = AreaLocalServiceUtil.fetchArea(content.getAreaId());
				String areaName = StringPool.BLANK;
				if (area != null) {
					areaName = area.getName();
				}
				%>
				<aui:input name="content_type" type="hidden" value="<%=contentType%>"/>
				<aui:input name="area" label="aragon.migration.contentportlet.area" disabled="true" value="<%=areaName%>"/>
				<aui:input name="excerpt" type="hidden" value="<%=excerpt%>"/>
				<aui:input type="hidden" name="original_body" value="<%=originalBody%>"></aui:input>
				<strong>Contenido</strong>
				<div style="border-style: solid; border-width: .0625rem .0625rem .0625rem .0625rem; border-color: #a7a9bc; background-color: #f1f2f5; padding: 20px;">
					<%= originalBody %>
				</div>
			    </br>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="Opciones adicionales" collapsed="<%=false%>" collapsible="<%=true%>">
				<aui:select label="aragon.migration.contentportlet.journal-folders" name="journalFolderId">
					<aui:option value="" selected="<%=journalFolderId == 0 ? true : false %>" label="select"></aui:option>
					<%List<JournalFolder> journalFolders = JournalFolderLocalServiceUtil.getFolders(themeDisplay.getScopeGroupId(), JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);%>
					<c:if test="<%=journalFolders != null && !journalFolders.isEmpty()%>">
						<%
						for (JournalFolder journalFolder : journalFolders) {
							if(permissionChecker.hasPermission(journalFolder.getGroupId(), journalFolder.getModelClassName(), journalFolder.getFolderId(), ActionKeys.VIEW)) {
						%>
							<aui:option value="<%=journalFolder.getFolderId()%>" selected="<%=journalFolderId == journalFolder.getFolderId() ? true : false %>" label="<%=journalFolder.getName()%>"></aui:option>
						<%
							}
						}%>
					</c:if>
				</aui:select>
			    <div style="display:none">
			    	<aui:select name="userGroupId" label="aragon.migration.contentportlet.group-user">
				    	<aui:option label="select" value="<%=userGroupId%>" selected="<%=userGroupId == 0 ? true : false%>"></aui:option>
				    	<c:if test="<%=userGroups != null%>">
					    	<%for (UserGroup userGroup : userGroups) {%>
					    		<aui:option label="<%=userGroup.getName()%>" value="<%=userGroup.getUserGroupId()%>" selected="<%=userGroup.getUserGroupId() == userGroupId ? true : false%>"></aui:option>
					    	<%}%>
				    	</c:if>
				    </aui:select>
					<aui:select name="statusId" label="Revisado">
				    	<aui:option label="no" value="1" selected="<%=statusId == 1 ? true : false%>"></aui:option>
				    	<aui:option label="yes" value="2" selected="<%=statusId == 2 ? true : false%>"></aui:option>
				    </aui:select>
			    </div>
			    <aui:select name="actionId" label="Migrar">
			    	<aui:option label="no" value="0" selected="<%=actionId == 0 ? true : false%>"></aui:option>
			    	<aui:option label="yes" value="1" selected="<%=actionId == 1 ? true : false%>"></aui:option>
			    </aui:select>			    
			    <aui:select name="visibility" label="Contenido agregador">
			    	<aui:option label="no" value="0" selected="<%=visibility == 0 ? true : false%>"></aui:option>
			    	<aui:option label="yes" value="1" selected="<%=visibility == 1 ? true : false%>"></aui:option>
			    </aui:select>
			    </br>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="notes" collapsed="<%=false%>" collapsible="<%=true%>">
				<aui:input name="comments" label="comment" type="textarea" value="<%=comments%>"/>
				</br>
			</liferay-frontend:fieldset>
			<liferay-frontend:fieldset label="aragon.migration.contentportlet.clasification" collapsed="<%=false%>" collapsible="<%=true%>">
			    <liferay-asset:asset-categories-selector hiddenInput="tags" categoryIds="<%=tags%>" className="<%=JournalArticle.class.getName()%>"/>
				</br>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" value="save"></aui:button>
		<%String previewURL = ContentPreviewUtil.getPreviewURL(themeDisplay.getScopeGroupId(), Boolean.TRUE, ContentPreviewUtil.PREVIEW_URL_BASE) + "?contentId=" + contentId;%>
		<c:if test="<%=previewURL != null && !previewURL.isEmpty()%>">
			<aui:button value="Vista previa" href="javascript:" onClick="<%="showPreview(" + contentId + ");"%>"></aui:button>
			<script>
				function showPreview(contentId) {
					AUI().use(
						'aui-base', 
						'aui-io-plugin-deprecated', 
						'liferay-util-window', 
						'liferay-portlet-url', 
						'aui-dialog-iframe-deprecated',	
						function (A) {
							var url = "<%=previewURL%>";
							console.log("url: " + url);
							var popUpWindow = Liferay.Util.Window.getWindow({
								dialog: {
									centered: true,
									constrain2view: true,
									modal: true,
									resizable: false
								}
							}).plug(A.Plugin.DialogIframe, {
								autoLoad: false,
								uri:url.toString()
							}).render();
							popUpWindow.show();
							popUpWindow.titleNode.html("Previsualizar el contenido");
						}
					);
				}
			</script>
		</c:if>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>
<script>
	function presubmit() {
		//Imagen principal
		var primaryImage = {};
		primaryImage["fileEntryId"] = document.getElementById("primaryFileEntryId").value;
		primaryImage["alt"] = document.getElementById("primaryImageAlt").value;
		primaryImage["short-description"] = document.getElementById("primaryImageShortDescription").value;
		primaryImage["long-description"] = document.getElementById("primaryImageLongDescription").value;
		$("#<portlet:namespace/>primaryImage").val(JSON.stringify(primaryImage));
		//Video principal
		var primaryVideo = {};
		primaryVideo["url"] = document.getElementById("primaryVideoURL").value;
		primaryVideo["short-description"] = document.getElementById("primaryVideoShortDescription").value;
		primaryVideo["long-description"] = document.getElementById("primaryVideoLongDescription").value;
		$("#<portlet:namespace/>primaryVideo").val(JSON.stringify(primaryVideo));
		//Galeria de imagenes
		var imagesGalleryImages = [];
		$("div.imagesGalleryImage").each(function(i, imageContainer) {
			var counter = $(imageContainer).attr("data-imageCounter");
			var imagesGalleryImage = {};
			imagesGalleryImage["fileEntryId"] = document.getElementById("imagesGalleryImage" + counter + "FileEntryId").value;
			imagesGalleryImage["alt"] = document.getElementById("imagesGalleryImage" + counter + "Alt").value;
			imagesGalleryImages.push(imagesGalleryImage);
		});
		$("#<portlet:namespace/>imagesGalleryImages").val(JSON.stringify(imagesGalleryImages));
		//Listado de enlaces
		var linksListLinks = [];
		$("div.linksListLink").each(function(i, linkContainer) {
			var counter = $(linkContainer).attr("data-linkCounter");
			var linksListLink = {};
			linksListLink["title"] = document.getElementById("linksListLink" + counter + "Title").value;
			linksListLink["url"] = document.getElementById("linksListLink" + counter + "URL").value;
			//linksListLink["type"] =  document.getElementById("linksListLink" + counter + "Type").value;
			linksListLinks.push(linksListLink);
		});
		$("#<portlet:namespace/>linksListLinks").val(JSON.stringify(linksListLinks));
	}
</script>

<%-- Hide visibility vocabularies --%>
<%
AssetVocabulary visibilidad = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Visibilidad");
AssetVocabulary visible = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Visible en navegacion");
long visibilidadId = 0;
long visibleId = 0;
if (visibilidad != null) {
	visibilidadId = visibilidad.getVocabularyId();
}
if (visible != null) {
	visibleId = visible.getVocabularyId();
}
%>
<c:if test="<%=permissionChecker.isOmniadmin()%>">
	<script>
		$( document ).ready(function() {
			if (<%=visibilidadId != 0%>) {
				var visibilidad = $("#_es_aragon_base_migration_ContentPortlet_assetCategoriesLabel_" + "<%=visibilidadId %>")[0];
				if(visibilidad != null) {
					$(visibilidad).parent().hide();
				}
			}
			if (<%=visibleId != 0%>) {
				var visible = $("#_es_aragon_base_migration_ContentPortlet_assetCategoriesLabel_" + "<%=visibleId %>")[0];
				if (visible != null) {
					$(visible).parent().hide();
				}
			}
		});
	</script>
</c:if>