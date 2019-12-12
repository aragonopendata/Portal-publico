package es.aragon.base.portal.utilities.scheduler;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils;
import es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesContentUtils;
import es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesMapHierarchy;
import es.aragon.base.process.queue.model.QueuedProcess;
import es.aragon.base.process.queue.service.QueuedProcessLocalServiceUtil;

public class SchedulerTaskBuildMaps {
	
	private static final Log _log = LogFactoryUtil.getLog(SchedulerTaskBuildMaps.class);
	
	
	public void run(){
		List<QueuedProcess> webContentMapExecuting = QueuedProcessLocalServiceUtil.fetchExecutingQueuedProcess(20127, "WebContentMapBuild");
		List<QueuedProcess> documentsAndMultimediaMapExecuting = QueuedProcessLocalServiceUtil.fetchExecutingQueuedProcess(20127, "DocumentsAndMultimediaMapBuild");
		
		if(webContentMapExecuting.isEmpty() && documentsAndMultimediaMapExecuting.isEmpty()) {
			runPendingWebContentMaps();
			runPendingWebDocumentAndMedia();
		}
	}

	private void runPendingWebContentMaps() {
		List<QueuedProcess> queuedProcessesPending = QueuedProcessLocalServiceUtil.fetchPendingQueuedProcess(20127, "WebContentMapBuild");
		if(!queuedProcessesPending.isEmpty()) {
			_log.info("GENERANDO " + queuedProcessesPending.size() + " MAPAS WEBS DE CONTENIDOS WEB");
		}
		
		// Set all status to executing
		for(QueuedProcess queuedProcess : queuedProcessesPending) {
			queuedProcess.setStatus(1);
			QueuedProcessLocalServiceUtil.updateQueuedProcess(queuedProcess);
		}
		
		for(QueuedProcess queuedProcess : queuedProcessesPending) {
			queuedProcess.setExecutionStartedDate(new Date());
			JSONObject jsonObject;
			try {
				jsonObject = JSONFactoryUtil.createJSONObject(queuedProcess.getJsonParameters());
				
				long folderId = jsonObject.getLong("folderId");
				long dlFolderId = jsonObject.getLong("dlFolderId");
				String portalURL = jsonObject.getString("portalURL");
				String requestDate = jsonObject.getString("requestDate");
				
				AragonPortalUtilitiesMapHierarchy<JournalArticle> dataMap = AragonPortalUtilitiesContentUtils.getWebContentMapData(queuedProcess.getGroupId(), folderId, portalURL);

				
				String folderNamePath = "Todas las carpetas";
				
		        if(folderId != 0) {
		            folderNamePath = AragonPortalUtilitiesContentUtils.getJournalFolderPathName(folderId, "");
		        }
				
				String content = AragonPortalUtilitiesContentUtils.buildHtmlfromDataMapWebContent(dataMap, folderId, folderNamePath, portalURL);
		        

				String formatedFolderNamePath = folderNamePath.concat("_").concat(requestDate);
		        
		        String fileName = FriendlyURLNormalizerUtil.normalize("Mapa_web_contenidos_web_" + formatedFolderNamePath.replace("/", "_").replace(" ", "-")) + ".html";
				
				DLFileEntry dlFileEntry = AragonPortalUtilitiesCommonUtils.uploadDocumentNoContext(queuedProcess.getUserId(), queuedProcess.getGroupId(), fileName, content, dlFolderId);
				queuedProcess.setStatus(0);
				
				User user = UserLocalServiceUtil.fetchUser(queuedProcess.getUserId());
				if(Validator.isNotNull(user)) {
					String subject = "Generación del mapa web de contenidos webs finalizada";
					String body;
					if(Validator.isNotNull(dlFileEntry)) {
						body = "Puedes encontrar el mapa web " + folderNamePath + " en la carpeta Informes generados -> Mapa Web -> Contenidos webs\n"
								+ "o puedes acceder directamente desde este enlace:\n\n"
								+  portalURL + "/documents/" + dlFileEntry.getGroupId() + "/"+ dlFileEntry.getFolderId() + "/" + dlFileEntry.getFileName();
					} else {
						body = "No se ha podido generar el mapa web, intentalo más tarde.";
					}
					
					AragonPortalUtilitiesCommonUtils.sendMailWithPlainText(user.getEmailAddress(), subject, body);
				}
			} catch (Exception e) {
				queuedProcess.setStatus(4);
				_log.info("NO SE HA PODIDO GENERERAR " + queuedProcess.getProcessName() + " " + queuedProcess.getJsonParameters());
			} finally {
				queuedProcess.setExecutionFinishedDate(new Date());
				QueuedProcessLocalServiceUtil.updateQueuedProcess(queuedProcess);
			}
		}
	}
		
	private void runPendingWebDocumentAndMedia() {
		List<QueuedProcess> queuedProcessesPending = QueuedProcessLocalServiceUtil.fetchPendingQueuedProcess(20127, "DocumentsAndMultimediaMapBuild");
		if(!queuedProcessesPending.isEmpty()) {
			_log.info("GENERANDO " + queuedProcessesPending.size() + " MAPAS WEBS DE DOCUMENTOS Y MULTIMEDIA");
		}
		
		// Set all status to executing
		for(QueuedProcess queuedProcess : queuedProcessesPending) {
			queuedProcess.setStatus(1);
			QueuedProcessLocalServiceUtil.updateQueuedProcess(queuedProcess);
		}
		
		for(QueuedProcess queuedProcess : queuedProcessesPending) {
			queuedProcess.setExecutionStartedDate(new Date());
			JSONObject jsonObject;
			try {
				jsonObject = JSONFactoryUtil.createJSONObject(queuedProcess.getJsonParameters());
				
				long folderId = jsonObject.getLong("folderId");
				long dlFolderId = jsonObject.getLong("dlFolderId");
				String portalURL = jsonObject.getString("portalURL");
				String requestDate = jsonObject.getString("requestDate");
				
				AragonPortalUtilitiesMapHierarchy<DLFileEntry> dataMap = AragonPortalUtilitiesContentUtils.getDlFileEntryMapData(queuedProcess.getGroupId(), folderId, portalURL);

				
				String folderNamePath = "Todas las carpetas";
				
		        if(folderId != 0) {
		            folderNamePath = AragonPortalUtilitiesContentUtils.getDlFolderPathName(folderId, "");
		        }
				
				String content = AragonPortalUtilitiesContentUtils.buildHtmlfromDataMapDlFileEntry(dataMap, folderId, folderNamePath, portalURL);
		        

		        String formatedFolderNamePath = folderNamePath.concat("_").concat(requestDate);
		        
		        String fileName = FriendlyURLNormalizerUtil.normalize("Mapa_web_documentos_y_multimedia_" + formatedFolderNamePath.replace("/", "_").replace(" ", "-")) + ".html";
				
				DLFileEntry dlFileEntry = AragonPortalUtilitiesCommonUtils.uploadDocumentNoContext(queuedProcess.getUserId(), queuedProcess.getGroupId(), fileName, content, dlFolderId);
				
				queuedProcess.setStatus(0);
				User user = UserLocalServiceUtil.fetchUser(queuedProcess.getUserId());
				if(Validator.isNotNull(user)) {
					String subject = "Generación del mapa web de documentos y multimedia finalizada";
					String body;
					if(Validator.isNotNull(dlFileEntry)) {
						body = "Puedes encontrar el mapa web de " + folderNamePath + " en la carpeta Informes generados -> Mapa Web -> Documentos y multimedia\n"
								+ "o puedes acceder directamente desde este enlace:\n\n"
								+  portalURL + "/documents/" + dlFileEntry.getGroupId() + "/"+ dlFileEntry.getFolderId() + "/" + dlFileEntry.getFileName();
					} else {
						body = "No se ha podido generar el mapa web, intentalo más tarde.";
					}
					
					AragonPortalUtilitiesCommonUtils.sendMailWithPlainText(user.getEmailAddress(), subject, body);
				}
			} catch (Exception e) {
				queuedProcess.setStatus(4);
				_log.info("NO SE HA PODIDO GENERERAR " + queuedProcess.getProcessName() + " " + queuedProcess.getJsonParameters());
			} finally {
				queuedProcess.setExecutionFinishedDate(new Date());
				QueuedProcessLocalServiceUtil.updateQueuedProcess(queuedProcess);
			}
		}
	}
}
