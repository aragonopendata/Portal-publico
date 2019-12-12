package es.aragon.base.script_utilities.portlet;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.script_utilities.constants.AragonScriptUtilitiesPortletKeys;

/**
 * @author migarcia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AragonScriptUtilitiesPortletKeys.ARAGON_SCRIPT_UTILITIES_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AragonScriptUtilitiesPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Folder folder =getFolder(themeDisplay);
		if( Validator.isNotNull(folder)) {
			String url = StringPool.BLANK;
			String fileName = StringPool.BLANK;
			try {
				List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.getFileEntries(themeDisplay.getScopeGroupId(), folder.getFolderId());
				if( Validator.isNotNull(dlFileEntries)) {
					for (DLFileEntry file : dlFileEntries) {
						if( file.getStatus() == 0) {
							url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" + 
									file.getFolderId() +  "/" +file.getTitle();
							fileName = file.getFileName();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Send parameter to view
			renderRequest.setAttribute("urlExcel", url);
			renderRequest.setAttribute("fileName", fileName);
		}
		super.render(renderRequest, renderResponse);
	}
	/**
	 * For the correct operation: the excel must have 3 columns:
	 * In the first, indication the urls of the final-contents that containing erroneous urls.
	 * In the second, the url we want to modify.
	 * In the third, the url with which we want to replace the erroneous url.
	 * Remove header excel.
	 * Only reading XLS and not XLSX
	 * @param request
	 * @param response
	 */
	public void updateRedirectUrl (ActionRequest request, ActionResponse response) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			_log.info("Start replacing urls...");
			long start = System.currentTimeMillis();
			// Get variables
			String fileName = ParamUtil.getString(request, "fileName", "");
			// Get folder where is file
			Folder folder = getFolder(themeDisplay);
			if( Validator.isNotNull(fileName) && fileName !="" && Validator.isNotNull(folder)) {
				// Get file excel from documents and media
				DLFileEntry file = DLFileEntryLocalServiceUtil.fetchFileEntryByFileName(themeDisplay.getScopeGroupId(), folder.getFolderId(), fileName);
				// Read excel
				InputStream inputStream = file.getContentStream();
				Workbook workbookOriginal = new HSSFWorkbook(inputStream);
				Sheet sheet = workbookOriginal.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				int countFoundArticles = 0;
				int existJournal = 0;
				int urlErrorFormat = 0;
				ArrayList <String> listJournalNotFound = new ArrayList<>();
				// Itering all rows from exel
				while (rowIterator.hasNext()) {
					// Get URLs from Excel
					Row row = rowIterator.next();
					String friendlyURLCol1 = row.getCell(0).getStringCellValue();
					String wrongUrlCol2 = row.getCell(1).getStringCellValue();
					String rightUrlCol3 = row.getCell(2).getStringCellValue();
					// Writte cell copy
//					String status = StringPool.BLANK;
//					Cell cell = row.getCell(3);
//					if( cell != null && !cell.equals(StringPool.BLANK)) {
//						status = row.getCell(3).getStringCellValue();
//					}
					// Process COL 1 -----> Get only journal article url
					String regExCol1 = ".*?\\/-\\/(.*)";
					Pattern patternCol1 = Pattern.compile(regExCol1);
					Matcher matcherCol1 = patternCol1.matcher(friendlyURLCol1);
					// Process COL 2 and COL 3 -----> Get only correct format url
					String regExCol2and3 = "^(http:\\/\\/|https:\\/\\/)(.*)";
					Pattern patterCol2and3 = Pattern.compile(regExCol2and3);
					Matcher matcherCheckUrlCol2 = patterCol2and3.matcher(wrongUrlCol2);
					Matcher matcherCheckUrlCol3 = patterCol2and3.matcher(rightUrlCol3);
						// If is a journal article and URLs has a correct format...
						if (matcherCol1.find() && matcherCheckUrlCol2.find() && matcherCheckUrlCol3.find()){
							// Get titleUrl journal article
							MatchResult resultCol1 = matcherCol1.toMatchResult();
							String friendlyURL = resultCol1.group(1);
							if (Validator.isNotNull(friendlyURL)){
								// Get journal article with urlTitle
								JournalArticle journalArticleByFriendlyUrl = _journalArticleLocalService.fetchArticleByUrlTitle(themeDisplay.getScopeGroupId(), friendlyURL);	
								// Checking is not in trash and is not expired
								if (Validator.isNotNull(journalArticleByFriendlyUrl) &&  journalArticleByFriendlyUrl.getStatus() != WorkflowConstants.STATUS_EXPIRED && journalArticleByFriendlyUrl.getStatus() != WorkflowConstants.STATUS_IN_TRASH) {
									existJournal++;
									List<JournalArticle> listAllVersion = _journalArticleLocalService.getArticlesByResourcePrimKey(journalArticleByFriendlyUrl.getResourcePrimKey());
									String decodeURLCol2Absolute = StringPool.BLANK;
									String escapeHtmlAbsolute = StringPool.BLANK;
									// Sometimes, jounal's content stored the urls with slash at the end, so is necesary looking for urls from excel, with "plus" slash. 
									// This is necessary to avoid duplication in the end slash when replacing
									String withSlash = wrongUrlCol2;
									if (!wrongUrlCol2.endsWith("/")) {
										withSlash = wrongUrlCol2+"/";
									}
									String withoutSlash = wrongUrlCol2;
									// Sometimes, jounal's content stored the urls without slash at the end, so is necesary looking for urls from excel, without slash
									if (wrongUrlCol2.endsWith("/")) {
										withoutSlash = wrongUrlCol2.substring(0,wrongUrlCol2.length()-1);
									}
									// Process COL 2 -----> To compare urls is necesary decode URL from excel because in the content field from journal-article is stored with spaces
									try {
										decodeURLCol2Absolute = URLDecoder.decode(wrongUrlCol2, "UTF-8"); 
										escapeHtmlAbsolute = HtmlUtil.escape(wrongUrlCol2);
									} catch (UnsupportedEncodingException e) {
										_log.error("Not encoding url",e);
									}
									// Convert urls from col2 and col3 in relative
									String regExCol2 = ".*?[.es|.com](\\/.*)";
									Pattern pattern = Pattern.compile(regExCol2);
									Matcher matcherCol2 = pattern.matcher(wrongUrlCol2);
									String friendlyURLCol2 = wrongUrlCol2;
									String decodeURLCol2Relative = decodeURLCol2Absolute;
									String escapeHtmlRelative = escapeHtmlAbsolute;
									if (matcherCol2.find()){
										MatchResult resultCol2 = matcherCol2.toMatchResult();
										friendlyURLCol2  = resultCol2.group(1);
										if (friendlyURLCol2.equals("/")) {
											friendlyURLCol2 = wrongUrlCol2;
										}
										decodeURLCol2Relative = URLDecoder.decode(friendlyURLCol2, "UTF-8");
										escapeHtmlRelative = HtmlUtil.escape(friendlyURLCol2);
									}
									boolean found = false;
									String contentJournal = StringPool.BLANK;
									// Itereting all versions of the article
									try {
										for (JournalArticle article:listAllVersion ) {
											contentJournal = article.getContent();
											if (contentJournal.contains(withSlash)) {
												contentJournal = article.getContent().replaceFirst(withSlash, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											}else if (contentJournal.contains(wrongUrlCol2)){
												contentJournal = article.getContent().replaceFirst(wrongUrlCol2, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											} else if(contentJournal.contains(withoutSlash)) {
												contentJournal = article.getContent().replaceFirst(withoutSlash, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
											} else if (contentJournal.contains(friendlyURLCol2)) {
												contentJournal = article.getContent().replaceFirst(friendlyURLCol2, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											} else if (contentJournal.contains(decodeURLCol2Absolute)) {
												contentJournal = article.getContent().replaceFirst(decodeURLCol2Absolute, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											} else if (contentJournal.contains(decodeURLCol2Relative)) {
												contentJournal = article.getContent().replaceFirst(decodeURLCol2Relative, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											} else if (contentJournal.contains(escapeHtmlAbsolute)) {
												contentJournal = article.getContent().replaceFirst(escapeHtmlAbsolute, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											} else if (contentJournal.contains(escapeHtmlRelative)){
												contentJournal = article.getContent().replaceFirst(escapeHtmlRelative, rightUrlCol3);
												_journalArticleLocalService.updateContent(themeDisplay.getScopeGroupId(), article.getArticleId(), article.getVersion(), contentJournal);
												found = true;
											}
										}
										// Writte excel
										if (found) {
											countFoundArticles++;
										} else {
											listJournalNotFound.add(wrongUrlCol2+ " in journal: "+friendlyURLCol1);
										}
									} catch (Exception e){
										_log.error("There was an error update url : " + e.toString());
									}
								} else {
									_log.warn("Not exist journal: "+friendlyURL);
								}
							}
						} else {
							_log.warn("Unprocessed url: "+friendlyURLCol1);
							urlErrorFormat++;
						}
				}
				for (String titleJournal : listJournalNotFound) {
					_log.warn("Not found url: "+titleJournal);
				}
				// Closing the workbook
				workbookOriginal.close();			 
				int rowNum = sheet.getLastRowNum()+1;
				int percent = 0;
				if (existJournal > 0 ) {
					percent = (countFoundArticles*100)/existJournal;
				}
				// Information log
				_log.info("Finish modified urls.");
				_log.info("Total rows: "+rowNum+" ; Total journal processed: "+existJournal+ " , No processed: "+urlErrorFormat+ " , Total URL modified: "+countFoundArticles+"/"+existJournal+" , Percent:"+percent+"%");
				long end = System.currentTimeMillis();
				long total = end - start;
				_log.info("TIEMPO TOTAL PARA GENERARLO: "+total);
			}
		} catch (Exception e) {
			_log.error("There was an error remplacing url : " + e.toString());
		}
	}
	
	/**
	 * Upload excel file
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void uploadDocument(ActionRequest actionRequest,ActionResponse actionResponse) throws IOException,PortletException, PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		createFolder(actionRequest, themeDisplay);
		fileUpload(themeDisplay, actionRequest);
	}
	/**
	 * Create folder if not exist
	 * @param actionRequest
	 * @param themeDisplay
	 * @return
	 */
	public Folder createFolder(ActionRequest actionRequest,ThemeDisplay themeDisplay){
		boolean folderExist = isFolderExist(themeDisplay);
		Folder folder = null;
		if (!folderExist) {
			long repositoryId = themeDisplay.getScopeGroupId();		
			try {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), actionRequest);
				folder = DLAppServiceUtil.addFolder(repositoryId,PARENT_FOLDER_ID, ROOT_FOLDER_NAME,ROOT_FOLDER_DESCRIPTION, serviceContext);
			} catch (PortalException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}			
		}
		return folder;
	}
	/**
	 * File upload document and media
	 * @param themeDisplay
	 * @param actionRequest
	 */
	public void fileUpload(ThemeDisplay themeDisplay,ActionRequest actionRequest){
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		if( Validator.isNotNull(uploadPortletRequest)) {
			String fileName=uploadPortletRequest.getFileName("uploadedFile");		 			
			File file = uploadPortletRequest.getFile("uploadedFile");
			String mimeType = uploadPortletRequest.getContentType("uploadedFile");
			String title = fileName;
			String description = "This file is added via programatically";
			long repositoryId = themeDisplay.getScopeGroupId();
			if (mimeType.equals(ContentTypes.APPLICATION_VND_MS_EXCEL)) {
				try{  
					Folder folder = getFolder(themeDisplay);
					if (Validator.isNotNull(fileName) && Validator.isNotNull(mimeType) && Validator.isNotNull(folder) && Validator.isNotNull(file)) {
						ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);
						InputStream is = new FileInputStream( file );
						List<DLFileEntry> dlFileEntriesList = DLFileEntryLocalServiceUtil.getFileEntries(themeDisplay.getScopeGroupId(), folder.getFolderId());
						if(dlFileEntriesList.isEmpty()) {
							DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), fileName, mimeType, title, description, "", is, file.length(), serviceContext);
						}else {
							// There can only be one single document excel, so delete all documents from folder
							try {
								for (DLFileEntry fileEntry : dlFileEntriesList) {
									DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntry);
								}
								DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), fileName, mimeType, title, description, "", is, file.length(), serviceContext);
							} catch (Exception e) {
								_log.info("Error remplace old file", e);
							}
						}
					}else {
						_log.warn("Not found file selected");
					}
				} catch (Exception e) {
					_log.error("Error upload file", e);
					e.printStackTrace();
				}
			}else {
				_log.warn("The file is not a XLS");
			}
		}else {
			_log.info("Not found upload portlet request");
		}
	}
	/**
	 * Get folder with the parameter name and root
	 * @param themeDisplay
	 * @return
	 */
	public Folder getFolder(ThemeDisplay themeDisplay){
		Folder folder = null;
		if( isFolderExist(themeDisplay)) {
			try {
				folder =DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
				
			} catch (Exception e) {	
				_log.info("No exist folder");
			}
		}
		return folder;
	}
	/**
	 * Check is folder exist
	 * @param themeDisplay
	 * @return
	 */
	public boolean isFolderExist(ThemeDisplay themeDisplay){
		boolean folderExist = false;
		try {
			DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
			folderExist = true;
		} catch (Exception e) {	
			_log.info("No exist folder");
		}
		return folderExist;
	}
	
	/**
	 * Log of the class
	 */
	private static final Log _log = LogFactoryUtil.getLog(AragonScriptUtilitiesPortlet.class);
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	private static String ROOT_FOLDER_NAME = "Herramienta script utilities";
	private static String ROOT_FOLDER_DESCRIPTION = "Documentos excel para reemplazar urls";
	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
}