package es.aragon.base.migration.portlet;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;
import es.aragon.base.migration.constants.AragonMigrationPortletKeys;
import es.aragon.base.migration.factory.MigrationToolFactory;
import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.model.ContentMetadata;
import es.aragon.base.migration.model.ContentUrl;
import es.aragon.base.migration.service.ContentLocalService;
import es.aragon.base.migration.service.ContentMetadataLocalService;
import es.aragon.base.migration.service.ContentRelatedLocalService;
import es.aragon.base.migration.service.ContentUrlLocalService;
import es.aragon.base.migration.util.AreaUtil;
import es.aragon.base.migration.util.ExportUtil;

/**
 * @author pfalcon
 * @author anunez
 * @author jjorge
 * @author aguillo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/contentPortlet/view.jsp",
		"javax.portlet.name=" + AragonMigrationPortletKeys.CONTENT_PORTLET_KEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ContentPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		long contentId = ParamUtil.getLong(renderRequest, "contentId", 0);
		//Solo se hace la búsqueda cuando se navega por la view jsp, nunca en el edit
		if (contentId == 0) {
			//Paginacion
			int delta = ParamUtil.getInteger(renderRequest, "delta", 10);
			int cur = ParamUtil.getInteger(renderRequest, "cur", 1);
			int start = delta * (cur - 1);
			int end = delta * cur;
			//Filtrado
			long areaId = ParamUtil.getLong(renderRequest, "areaId", 0);
			//String typeName = ParamUtil.getString(renderRequest, "typeName", StringPool.BLANK);
			String typeName = "text/html";
			//Para usuarios no administradores solo se muestran los contenidos del grupo al que está asociado
			long[] areaIds = new long[] {};
			boolean admin = false;
			
			if(areaId == 0) {
				if(permissionChecker.isOmniadmin()) {
					admin = true;
				} else {
					areaIds = AreaUtil.getUserAreaIds(themeDisplay.getUser().getUserGroupIds());
				}
			} else {
				areaIds = new long[] {areaId};
			}
			
			long assignedUserId = ParamUtil.getLong(renderRequest, "assignedUserId", 0);
			long journalFolderId = ParamUtil.getLong(renderRequest, "journalFolderId", JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			long statusId = ParamUtil.getLong(renderRequest, "statusId", 0);
			long actionId = ParamUtil.getLong(renderRequest, "actionId", -1);
			int visibility = ParamUtil.getInteger(renderRequest, "visibility", -1);
			String search = ParamUtil.getString(renderRequest, "search", StringPool.BLANK);	
			//Consulta de resultados
			long totalResults = getContentsSize(admin, areaIds, typeName, 0,journalFolderId, assignedUserId, statusId, actionId, visibility, search);
			List<Content> contents = getContents(admin, areaIds, typeName, 0, journalFolderId, assignedUserId, statusId, actionId, visibility, search, start, end);
			//Envio de resultados a la vista
			renderRequest.setAttribute("totalResults", totalResults);
			renderRequest.setAttribute("contents", contents);
		} 
		//En el edit se envia la url del selector de imagenes de documentos y multimedia
		else {
			//URL para el popup de seleccion de imagenes
			String imagesSelectorURL = getImagesSelectorURL(httpServletRequest);
			renderRequest.setAttribute("imagesSelectorURL", imagesSelectorURL);
		}
		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		String actionName = ParamUtil.getString(resourceRequest, "actionName");
		if (actionName.equals("export_csv")) {
			//Results
			long areaId = ParamUtil.getLong(resourceRequest, "areaId", 0);
			String typeName = "text/html";
			long journalFolderId = ParamUtil.getLong(resourceRequest, "journalFolderId", 0);
			long assignedUserId = ParamUtil.getLong(resourceRequest, "assignedUserId", 0);
			long statusId = ParamUtil.getLong(resourceRequest, "statusId", 0);
			long actionId = ParamUtil.getLong(resourceRequest, "actionId", -1);
			int visibility = ParamUtil.getInteger(resourceRequest, "visibility", -1);
			String search = ParamUtil.getString(resourceRequest, "search", StringPool.BLANK);	
			long[] areaIds = new long[] {};
			boolean admin = false;
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
	
			if(areaId == 0) {
				if(permissionChecker.isOmniadmin()) {
					admin = true;
				} else {
					areaIds = AreaUtil.getUserAreaIds(themeDisplay.getUser().getUserGroupIds());
				}
			} else {
				areaIds = new long[] {areaId};
			}
			
			List<Content> contents = getContents(admin, areaIds, typeName, 0, journalFolderId, assignedUserId, statusId, actionId, visibility, search, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			//Get export data
			String fileName = "migration_contents.csv";
			byte[] bytes = ExportUtil.getContentsExportData(contents);
			String contentType = ContentTypes.APPLICATION_TEXT;
			PortletResponseUtil.sendFile(resourceRequest, resourceResponse,	fileName, bytes, contentType);
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	/**
	 * Action for edit content form
	 * @param actionRequest Action request
	 * @param actionResponse Action response
	 */
	public void editContent(ActionRequest actionRequest, ActionResponse actionResponse) {
		try {
			//Constants
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long lastModifiedUserId = themeDisplay.getUserId();
			String groupGuestName = GroupConstants.GUEST;
			Group guestGroup = _groupLocalService.getGroup(themeDisplay.getCompanyId(), groupGuestName);
			long vocabularyGroups[] = { guestGroup.getGroupId() };
			String backURL = ParamUtil.getString(actionRequest, "backURL");
			long contentId = ParamUtil.getLong(actionRequest, "contentId", 0);
			//Form params
			String title = ParamUtil.getString(actionRequest, "title", StringPool.BLANK);
			String originalBody = ParamUtil.getString(actionRequest, "original_body", StringPool.BLANK);
			long statusId = ParamUtil.getLong(actionRequest, "statusId", 1);
			long actionId = ParamUtil.getLong(actionRequest, "actionId", 0);
			int visibility = ParamUtil.getInteger(actionRequest, "visibility", 0);
			long userGroupId = ParamUtil.getLong(actionRequest, "userGroupId", 0);
			long journalFolderId = ParamUtil.getLong(actionRequest, "journalFolderId", 0);
			String tags = "";
			List<AssetVocabulary> vocabularies = _assetVocabularyLocalService.getGroupsVocabularies(vocabularyGroups, JournalArticle.class.getName());
			for (AssetVocabulary vocabulary : vocabularies) {
				long vocabularyId = vocabulary.getVocabularyId();
				long journalArticleClassNameId = PortalUtil.getClassNameId(JournalArticle.class.getName());
				String requiredAllArticles = "requiredClassNameIds="+journalArticleClassNameId+":-1";
				String vocabularySelectedCategories = ParamUtil.getString(actionRequest, "tags_"+vocabularyId, StringPool.BLANK);
				if (Validator.isNotNull(vocabularySelectedCategories)) {
					if (Validator.isNotNull(tags)) {
						tags = tags + ",";
					}
					tags = tags + vocabularySelectedCategories;
				}
				if (vocabulary.getSettings().contains(requiredAllArticles) && Validator.isNull(vocabularySelectedCategories)) {
					SessionErrors.add(actionRequest, "error-categories");
					actionResponse.setRenderParameter("mvcPath", "/contentPortlet/edit.jsp");
					actionRequest.setAttribute("contentId", contentId);
					return;
				}
			}
			String comments = ParamUtil.getString(actionRequest, "comments");
			//Update content
			Content content = _contentLocalService.fetchContent(contentId);
			if (content != null) {
				content.setTitle(title);
				content.setOriginalBody(originalBody);
				content.setStatusId(statusId);
				content.setActionId(actionId);
				content.setVisibility(visibility);
				content.setTags(tags);
				content.setUserGroupId(userGroupId);
				content.setLastModifiedUserId(lastModifiedUserId);
				content.setDateModified(new Date());
				content.setComments(comments);
				content.setJournalFolderId(journalFolderId);
				_contentLocalService.updateContent(content);
			}
			//Create or update content metadata
			/*String primaryBody = ParamUtil.getString(actionRequest, "primaryBody");
			String primaryImage = ParamUtil.getString(actionRequest, "primaryImage");
			String primaryVideo = ParamUtil.getString(actionRequest, "primaryVideo");
			String secondaryBody = ParamUtil.getString(actionRequest, "secondaryBody");
			String imagesGalleryTitle = ParamUtil.getString(actionRequest, "imagesGalleryTitle");
			String imagesGalleryImages = ParamUtil.getString(actionRequest, "imagesGalleryImages");
			String linksListTitle = ParamUtil.getString(actionRequest, "linksListTitle");
			String linksListLinks = ParamUtil.getString(actionRequest, "linksListLinks");
			ContentMetadata contentMetadata = _contentMetadataLocalService.fetchByContentId(contentId);
			if (contentMetadata != null) {
				contentMetadata.setPrimaryBody(primaryBody);
				contentMetadata.setPrimaryImage(primaryImage);
				contentMetadata.setPrimaryVideo(primaryVideo);
				contentMetadata.setSecondaryBody(secondaryBody);
				contentMetadata.setImagesGalleryTitle(imagesGalleryTitle);
				contentMetadata.setImagesGalleryImages(imagesGalleryImages);
				contentMetadata.setLinksListTitle(linksListTitle);
				contentMetadata.setLinksListLinks(linksListLinks);
				_contentMetadataLocalService.updateContentMetadata(contentMetadata);
			} else {
				contentMetadata = _contentMetadataLocalService.addContentMetadata(contentId, primaryBody, primaryImage, primaryVideo, secondaryBody, imagesGalleryTitle, imagesGalleryImages, linksListTitle, linksListLinks);
			}*/
			//Redirect
			actionResponse.sendRedirect(backURL);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "error-general");
			_log.error("There was an error editing the content: " + e.toString());
			e.printStackTrace();
		}
	}
	

	
	/**
	 * Gets the content list size filtered by parameters
	 * @param areaId Area identifier
	 * @param typeName Type name
	 * @param userGroupId UserGroup identifier
	 * @param assignedUserId Assigned User identifier
	 * @param statusId
	 * @param actionId
	 * @param search
	 * @return
	 */
	private long getContentsSize(boolean admin, long[] areaIds, String typeName, long userGroupId, long journalFolderId, long assignedUserId, long statusId, long actionId, int visibility, String search) {
		long total = 0;
		if(!admin && areaIds.length == 0) {
			return total;
		}
		try {
			DynamicQuery dynamicQuery = getContentsQuery(admin, areaIds, typeName, userGroupId, journalFolderId, assignedUserId, statusId, actionId, visibility, search);
			total = _contentLocalService.dynamicQueryCount(dynamicQuery);
		} catch (Exception e) {
			_log.error(e, e);
		}
		return total;
	}
	
	private List<Content> getContents(boolean admin, long[] areaIds, String typeName, long userGroupId, long journalFolderId, long assignedUserId, long statusId, long actionId, int visibility, String search, int start, int end) {
		List<Content> results = new ArrayList<Content>();
		if(!admin && areaIds.length == 0) {
			return results;
		}
		try {
			DynamicQuery dynamicQuery = getContentsQuery(admin, areaIds, typeName, userGroupId, journalFolderId, assignedUserId, statusId, actionId, visibility, search);
			results = _contentLocalService.dynamicQuery(dynamicQuery, start, end);
		} catch (Exception e) {
			_log.error(e, e);
		}
		return results;
	}
	
	public DynamicQuery getContentsQuery(boolean admin, long areaIds[], String typeName, long userGroupId, long journalFolderId, long assignedUserId, long statusId, long actionId, int visibility, String search) {
		DynamicQuery dynamicQuery = _contentLocalService.dynamicQuery();
		if(!admin && areaIds.length > 0) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
			for(long areaId: areaIds) {
				disjunction.add(PropertyFactoryUtil.forName("areaId").eq(areaId));
			}
			dynamicQuery.add(disjunction);
		}
		if (Validator.isNotNull(typeName)) {
			dynamicQuery.add(PropertyFactoryUtil.forName("contentType").eq(typeName));
		}
		if (journalFolderId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("journalFolderId").eq(journalFolderId));
		} else if (journalFolderId == -1) {
			dynamicQuery.add(PropertyFactoryUtil.forName("journalFolderId").eq(0L));
		}
		if (assignedUserId != 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("assignedUserId").eq(assignedUserId));
		}
		if (statusId != 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("statusId").eq(statusId));
		}
		if (actionId != -1) {
			dynamicQuery.add(PropertyFactoryUtil.forName("actionId").eq(actionId));
		}
		if (visibility != -1) {
			dynamicQuery.add(PropertyFactoryUtil.forName("visibility").eq(visibility));
		}
		
		if (Validator.isNotNull(search)) {
			Criterion or = RestrictionsFactoryUtil.or(
					RestrictionsFactoryUtil.ilike("excerpt", "%"+search+"%"),
					RestrictionsFactoryUtil.ilike("title", "%"+search+"%")
			);
			dynamicQuery.add(RestrictionsFactoryUtil.or(
					or,
					RestrictionsFactoryUtil.ilike("url", "%"+search+"%")
			));
		}
		Order orderBy = OrderFactoryUtil.asc("contentId");
		dynamicQuery.addOrder(orderBy);
		return dynamicQuery;
	}
	
	public void assignContentToMyself(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long assignedUserId = themeDisplay.getUserId();
		long contentId = ParamUtil.getLong(actionRequest, "contentId");
		String currentUrl = ParamUtil.getString(actionRequest, "currentUrl");
		Content content = _contentLocalService.fetchContent(contentId);
		if(Validator.isNotNull(content)) {
			content.setAssignedUserId(assignedUserId);
			_contentLocalService.updateContent(content);
		}else {
			_log.info("NO SE HA PODIDO ASIGNAR EL CONTENIDO AL USUARIO CON ID: " + assignedUserId);
		}
		try {
			actionResponse.sendRedirect(currentUrl);
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	public void startMigration(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long groupId = themeDisplay.getScopeGroupId();
		long companyId = themeDisplay.getCompanyId();
		long userId = themeDisplay.getUserId();
		Locale defaultLocale = themeDisplay.getSiteDefaultLocale();
		
		Company company = _companyLocalService.fetchCompany(companyId);
		
		if(Validator.isNotNull(company)) {
			
			// Estructura y plantilla BASIC-WEB-CONTENT de global
			DDMStructure ddmStructure = fetchStructureByName(AragonUtilitiesConstant.STRUCTURE_NAME_CONTENIDO_FINAL, groupId);
			DDMTemplate ddmTemplate = fetchTemplateByName(AragonUtilitiesConstant.STRUCTURE_NAME_CONTENIDO_FINAL, groupId);
			
			if(Validator.isNotNull(ddmStructure) && Validator.isNotNull(ddmTemplate)) {
				
				// Si el idioma por defecto de la estructura no es castellano, no se inicia el proceso de migracion
				if(ddmStructure.getDefaultLanguageId() != LocaleUtil.toLanguageId(defaultLocale)) {
					
			        String portalUrl = PortalUtil.getPortalURL(actionRequest);
			        
			        int start = ParamUtil.getInteger(actionRequest, "start", QueryUtil.ALL_POS);
			        int end = ParamUtil.getInteger(actionRequest, "end", QueryUtil.ALL_POS);
			        if(start < 0) {
			        	start = 0;
			        }
			        if(end >= _contentLocalService.getContentsCount()) {
			        	end = _contentLocalService.getContentsCount() - 1;
			        }
			        if(start > end) {
			        	start = end;
			        }
			        
			        List<Content> contents = _contentLocalService.getContents(start, end);
			        if(Validator.isNotNull(contents)) {
			        	int processPercent = 0;
			        	int processTotalURLs = contents.size();
			        	int processCurrentURL = 0;
			        	for(Content content : contents) {
			        		processCurrentURL++;
			        		// Si el contenido esta marcado como migrado, se inicia su proceso de migracion al portal
			        		
					        		ServiceContext serviceContext = new ServiceContext();
									serviceContext.setScopeGroupId(groupId);
									serviceContext.setCompanyId(companyId);
									serviceContext.setAttribute("defaultLanguageId", LocaleUtil.toLanguageId(defaultLocale));
									serviceContext.setUserId(userId);
							        serviceContext.setAddGroupPermissions(true);
							        serviceContext.setAddGuestPermissions(true);
							        serviceContext.setScopeGroupId(groupId);
							        
									if(content.getContentType().equals("text/html")) {
										
										/*
										// Si el contenido es html, se migra como journal article
										ContentMetadata contentMetadata = _contentMetadataLocalService.fetchByContentId(content.getContentId());
										try {
											MigrationToolFactory.migrateContent(groupId, companyId, userId, content, contentMetadata, ddmStructure,
												ddmTemplate, serviceContext, portalUrl, defaultLocale, 
												_journalArticleLocalService, _journalFolderLocalService, _roleLocalService, 
												_dlFolderLocalService, _dlAppLocalService, _dlFileEntryLocalService,
												_assetCategoryLocalService, _assetTagLocalService, _assetVocabularyLocalService,
												_contentUrlLocalService, _layoutLocalService, _freemarkerUtilities,
												_counterLocalService);
										}catch(Exception e) {
											_log.error(e);
										}
										*/
									}else{
										// Si el contenido es distinto de html, se migra como documento
										try {
											
											MigrationToolFactory.migrateDocument(userId, groupId, companyId, FOLDER_TREEPATH, content, portalUrl, 
													serviceContext, _dlFolderLocalService, _dlAppLocalService, _roleLocalService,
													_dlFileEntryLocalService, _contentUrlLocalService, _counterLocalService,
													_assetCategoryLocalService);
											
										}catch(Exception e) {
											_log.error(e);
										}
									}
				        		
			        		
			        		if(((100*processCurrentURL)/processTotalURLs)>processPercent) {
								processPercent = ((100*processCurrentURL)/processTotalURLs);
								_log.info("PROCESO DE LA MIGRACION EN UN "+processPercent+"%");
							}
			        	}
			        	_log.info("INICIALIZANDO PROCESO DE ACTUALIZACION DE REFERENCIAS...");
			        	/*
			        	MigrationToolFactory.updateJournalArticleReferences(_contentRelatedLocalService, 
			        			_contentUrlLocalService, _journalArticleLocalService);
			        	*/
			        	_log.info("FINALIZADO PROCESO DE ACTUALIZACION DE REFERENCIAS");
			        }
				}
			}
		}
	}
	
	public void startMigrationByFilter(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
	}

	public DDMStructure fetchStructureByName(String name, long groupId) {
		List<DDMStructure> ddmStructures = _ddmStructureLocalService.getStructures(groupId);
		try {
			Locale locale = PortalUtil.getSiteDefaultLocale(groupId);
			if(Validator.isNotNull(ddmStructures)) {
				for(DDMStructure ddmStructure : ddmStructures) {
					if(name.equalsIgnoreCase(ddmStructure.getName(locale))) {
						return ddmStructure;
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
		return null;
	}
	
	public DDMTemplate fetchTemplateByName(String name, long groupId) {
		List<DDMTemplate> ddmTemplates = _ddmTemplateLocalService.getTemplates(groupId, PortalUtil.getClassNameId(DDMStructure.class.getName()));
		try {
			Locale locale = PortalUtil.getSiteDefaultLocale(groupId);
			if(Validator.isNotNull(ddmTemplates)) {
				for(DDMTemplate ddmTemplate : ddmTemplates) {
					if(name.equalsIgnoreCase(ddmTemplate.getName(locale))) {
						return ddmTemplate;
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
		return null;
	}
	
	/**
	 * Return the url used to build the image selector popup
	 * @param renderRequest Render request
	 * @return Url used to build the image selector popup
	 */
	private String getImagesSelectorURL(HttpServletRequest renderRequest){
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(renderRequest);
		List<ItemSelectorReturnType> myDesiredItemSelectorReturnTypes = new ArrayList<ItemSelectorReturnType>();
		myDesiredItemSelectorReturnTypes.add(new FileEntryItemSelectorReturnType());
		ImageItemSelectorCriterion imageItemSelectorCriterion = new ImageItemSelectorCriterion();
		imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(myDesiredItemSelectorReturnTypes);
		PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(requestBackedPortletURLFactory, "ItemSelectedEventName", imageItemSelectorCriterion);
		itemSelectorURL.setParameter("displayType", "image");
        return itemSelectorURL.toString();
	}
	
	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;
	
	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;
	
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	
	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;
	
	@Reference
	private JournalFolderLocalService _journalFolderLocalService;
	
	@Reference
	private ClassNameLocalService _classNameLocalService;
	
	@Reference
	private RoleLocalService _roleLocalService;
	
	@Reference
	private DLFolderLocalService _dlFolderLocalService;
	
	@Reference
	private DLAppLocalService _dlAppLocalService;
	
	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;
	
	@Reference
	private CompanyLocalService _companyLocalService;
	
	@Reference
	private AssetTagLocalService _assetTagLocalService;
	
	@Reference
	private ContentLocalService _contentLocalService;
	
	@Reference
	private GroupLocalService _groupLocalService;
	
	@Reference
	private ContentUrlLocalService _contentUrlLocalService;
	
	@Reference
	private CounterLocalService _counterLocalService;
	
	@Reference
	private JournalContentSearchLocalService _journalContentSearchLocalService;
	
	@Reference
	private LayoutLocalService _layoutLocalService;
	
	@Reference
	private ContentMetadataLocalService _contentMetadataLocalService;
	
	@Reference
	private ContentRelatedLocalService _contentRelatedLocalService;
	
	@Reference
	private ItemSelector itemSelector;
	
    @Reference
    FreemarkerUtilities _freemarkerUtilities;
	
	private final static String FOLDER_TREEPATH = "Documentos antiguos del portal";
	
	/**
	 * Log of the class
	 */
	private final Log _log = LogFactoryUtil.getLog(ContentPortlet.class);

}