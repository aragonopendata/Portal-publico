package es.aragon.base.portal.utilities.portlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;
import es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils;
import es.aragon.base.process.queue.service.QueuedProcessLocalServiceUtil;


@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES,
	        "mvc.command.name=" + AragonPortalUtilitiesPortletKeys.ROUTE_DL_FILE_ENTRY
	    },
	    service = MVCActionCommand.class
	)
public class AragonPortalUtilitiesDlEntryMapActionCommand extends BaseMVCActionCommand {

	
	@Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long folderId = ParamUtil.getLong(actionRequest, "folderIdToMap");
		long dlFolderId = AragonPortalUtilitiesCommonUtils.fetchFolder(AragonPortalUtilitiesCommonUtils.DOCUMENT_FOLDER_PATH, themeDisplay.getScopeGroupId()).getFolderId();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		jsonObject.put("folderId", folderId);
		jsonObject.put("portalURL", themeDisplay.getPortalURL());
		jsonObject.put("dlFolderId", dlFolderId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		
		jsonObject.put("requestDate", dateFormat.format(new Date()));
		
		QueuedProcessLocalServiceUtil.addQueuedProcess(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), 
				themeDisplay.getUser(), "DocumentsAndMultimediaMapBuild", jsonObject);
		
		String subject = "Solicitud generación mapa web de documentos y multimedia";
		String body = "Tu mapa web de documentos y multimedia será generado dentro de poco.\nRecibirás una notificación en el correo cuando este listo.";
		
		AragonPortalUtilitiesCommonUtils.sendMailWithPlainText(themeDisplay.getUser().getEmailAddress(), subject, body);
		
		SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
		SessionMessages.add(actionRequest, "success-request");
	}
}
