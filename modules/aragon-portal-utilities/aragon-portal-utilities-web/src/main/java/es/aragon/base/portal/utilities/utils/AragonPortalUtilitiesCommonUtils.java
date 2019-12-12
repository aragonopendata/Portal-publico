package es.aragon.base.portal.utilities.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;

public class AragonPortalUtilitiesCommonUtils {
	private AragonPortalUtilitiesCommonUtils() {
		
	}
	
	public static DLFileEntry uploadDocumentNoContext(long userId, long groupId, String fileName, String content, long dlFolderId) throws IOException {
		
		File file = new File(fileName);
		InputStream inputStream = null;
		DLFileEntry dlFileEntry = null;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    writer.write(content);
		    writer.close();
			
			String mimeType = ContentTypes.TEXT_HTML_UTF8;
			String title = fileName;
			String description = "This file is added via programatically";
			
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(false);
			serviceContext.setAddGuestPermissions(false);
		
		
			inputStream = new FileInputStream( file );
			
			dlFileEntry = DLFileEntryLocalServiceUtil.addFileEntry(userId, groupId, groupId, dlFolderId, fileName, mimeType, title, description, "", 0, null, file, inputStream, file.length(), serviceContext);
			
			inputStream.close();

			Map<String, Serializable> workflowContext = Collections.emptyMap();
			return DLFileEntryLocalServiceUtil.updateStatus(userId, dlFileEntry.getFileVersion().getFileVersionId(), WorkflowConstants.STATUS_APPROVED, null, workflowContext);
		} catch (IOException | PortalException e) {
			_log.error(e.getMessage());
		} finally {
			file.delete();
		}
		
		return dlFileEntry;
	}
	
	public static void updloadWorkBook(ActionRequest actionRequest, String folderPath, Workbook workBook, String fileName) throws PortalException, IOException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		fileUploadworkbook(themeDisplay, actionRequest, folderPath, workBook, fileName);
	}

	private static void fileUploadworkbook(ThemeDisplay themeDisplay, ActionRequest actionRequest, String folderPath, Workbook workBook, String fileName) throws PortalException, IOException{
		
		File file = new File(fileName);
		
		FileOutputStream writer = new FileOutputStream(file);
		workBook.write(writer);
		workBook.close();
		writer.close();
		
		String mimeType = ContentTypes.TEXT_HTML_UTF8;
		String title = fileName;
		String description = "This file is added via programatically";
		long repositoryId = themeDisplay.getScopeGroupId();
		DLFolder dlFolder = fetchFolder(folderPath, themeDisplay.getScopeGroupId());
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);
		try {
			InputStream inputStream = new FileInputStream( file );
			DLAppServiceUtil.addFileEntry(repositoryId, dlFolder.getFolderId(), fileName, mimeType, title, description, "", inputStream, file.length(), serviceContext);
			inputStream.close();
		} catch (IOException e) {
			_log.error(e.getMessage());
		} finally {
			file.delete();
		}
	}
	
	public static DLFolder fetchFolder(String folderPath, long groupId) {
		String[] folderNames = folderPath.split("/");
		long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		DLFolder returnFolder = null;
		for(int iterator = 1; iterator < folderNames.length; iterator++) {
			DLFolder dlFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, parentFolderId, folderNames[iterator]);
			if(Validator.isNotNull(dlFolder)) {
				returnFolder = dlFolder;
				parentFolderId = dlFolder.getFolderId();
			} else {
				return null;
			}
		}
		return returnFolder;
	}
	
	public static void sendMailWithPlainText(String toAdress, String subject, String body) {
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		try {
	    		fromAddress = new InternetAddress("notificaciones@aragon.es");
	    		toAddress = new InternetAddress(toAdress);
	    		MailMessage mailMessage = new MailMessage();
	    		mailMessage.setTo(toAddress);
	    		mailMessage.setFrom(fromAddress);
	    		mailMessage.setSubject(subject);
	    		mailMessage.setBody(body);
	    		MailServiceUtil.sendEmail(mailMessage);
		} catch (AddressException e) {
		    	e.printStackTrace();
		}
	}
	
	private static List<DLFileEntry> getFilesFromDLFolder(long groupId, String folderPath, int start, int end) {
		DLFolder parentFolder = fetchFolder(folderPath, groupId);
		if(Validator.isNotNull(parentFolder)) {
			return DLFileEntryLocalServiceUtil.getFileEntries(groupId, parentFolder.getFolderId(), 0, start, end,
					OrderByComparatorFactoryUtil.create("DLFileEntry", "createDate", false));
		} else {
			return new ArrayList<>();
		}
	}
	
	private static int getFilesFromDLFolderCount(long groupId, String folderPath) {
		DLFolder parentFolder = fetchFolder(folderPath, groupId);
		if(Validator.isNotNull(parentFolder)) {
			return DLFileEntryLocalServiceUtil.getFileEntriesCount(groupId, parentFolder.getFolderId());
		} else {
			return 0;
		}
	}
	
	public static int getDocumentAndMultimediaMapsCount(long groupId) {
		return getFilesFromDLFolderCount(groupId, DOCUMENT_FOLDER_PATH);
	}
	
	public static List<DLFileEntry> getDocumentAndMultimediaMaps(long groupId, int start, int end) {
		return getFilesFromDLFolder(groupId, DOCUMENT_FOLDER_PATH, start, end);
	}
	
	public static int getWebContentMapsCount(long groupId) {
		return getFilesFromDLFolderCount(groupId, WEB_CONTENT_FOLDER_PATH);
	}
	
	public static List<DLFileEntry> getWebContentMaps(long groupId, int start, int end) {
		return getFilesFromDLFolder(groupId, WEB_CONTENT_FOLDER_PATH, start, end);
	}
	
	public static int getUserLogsCount(long groupId) {
		return getFilesFromDLFolderCount(groupId, USER_LOG_FOLDER_PATH);
	}
	
	public static List<DLFileEntry> getUserLogs(long groupId, int start, int end) {
		return getFilesFromDLFolder(groupId, USER_LOG_FOLDER_PATH, start, end);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(AragonPortalUtilitiesCommonUtils.class);
	public static final String DOCUMENT_FOLDER_PATH = "/Informes generados/Mapa web/Documentos y multimedia";
	public static final String WEB_CONTENT_FOLDER_PATH = "/Informes generados/Mapa web/Contenidos webs";
	public static final String USER_LOG_FOLDER_PATH = "/Informes generados/Historico usuarios";
}
