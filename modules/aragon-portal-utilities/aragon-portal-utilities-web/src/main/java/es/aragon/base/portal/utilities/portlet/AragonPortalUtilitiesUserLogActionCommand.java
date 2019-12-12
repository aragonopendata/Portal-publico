package es.aragon.base.portal.utilities.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;
import es.aragon.base.portal.utilities.utils.AragonPortalUtilitiesCommonUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES,
	        "mvc.command.name=" + AragonPortalUtilitiesPortletKeys.ROUTE_USER_LOG
	    },
	    service = MVCActionCommand.class
	)
public class AragonPortalUtilitiesUserLogActionCommand extends BaseMVCActionCommand {

	
	@Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		

		Date fromDate = ParamUtil.getDate(actionRequest, "fromDate", new SimpleDateFormat("dd/MM/yyyy"));
		Date toDate = ParamUtil.getDate(actionRequest, "toDate", new SimpleDateFormat("dd/MM/yyyy"));
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		DateFormat dateHourFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		dateHourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		
		DateFormat dateFormatMillis = new SimpleDateFormat("yyyyMMddHHmmss");
		dateFormatMillis.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        DynamicQuery dynamicQuery = JournalArticleLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.between("modifiedDate", fromDate, new Date(toDate.getTime() + (1000 * 60 * 60 * 24))));
        dynamicQuery.addOrder(OrderFactoryUtil.desc("modifiedDate"));
        
        List<JournalArticle> journalArticles = null;
        
        try {
        	journalArticles = JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
        	
			Workbook workbook = new HSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Informacion");
        	int rowCount = 0;
        	
        	// Set headers
	        Row row = sheet.createRow(rowCount++);
	        row.createCell(0).setCellValue("Usuario");
	        row.createCell(1).setCellValue("Fecha de modificacion");
	        row.createCell(2).setCellValue("Contenido web");		        
	        row.createCell(3).setCellValue("URL");

    		Date lastDate = null;
    		long lastUserId = 0;
    		String lastArticleId = "";
        	for (JournalArticle journalArticle : journalArticles) {
        		// Clean actions in short time

        		if(lastDate == null || !(journalArticle.getUserId() == lastUserId && journalArticle.getArticleId().equals(lastArticleId) && 
        				(Math.abs(lastDate.getTime() - journalArticle.getModifiedDate().getTime()) / (60 * 1000) % 60) < 1)) {
        			
        			lastDate = journalArticle.getModifiedDate();
        			lastArticleId = journalArticle.getArticleId();
        			lastUserId = journalArticle.getUserId();
        			
	        		row = sheet.createRow(rowCount++);

	        		// set User
	        		row.createCell(0).setCellValue(UserLocalServiceUtil.getUser(journalArticle.getUserId()).getScreenName());
	        		
	        		// set modified date
	        		row.createCell(1).setCellValue(dateHourFormat.format(journalArticle.getModifiedDate()));
	        		
	        		// set journalArticle title
	        		row.createCell(2).setCellValue(journalArticle.getTitle(LocaleUtil.getDefault()));
	        		
	        		// set url to journalArticle
	        		row.createCell(3).setCellValue(themeDisplay.getPortalURL().concat("/-/").concat(journalArticle.getUrlTitle()));
        		}
        	}

			String fileName = "Informe_historico_usuarios_" + dateFormat.format(fromDate) + "_a_"+ dateFormat.format(toDate) + "_" + dateFormatMillis.format(new Date()) + ".xls";

	        AragonPortalUtilitiesCommonUtils.updloadWorkBook(actionRequest, AragonPortalUtilitiesCommonUtils.USER_LOG_FOLDER_PATH, workbook, fileName);
        } catch (Exception e) {
        	_log.error("NO SE HA PODIDO GENERAR EL INFORME DE USUARIOS.");
		}			
	}
	
	private static final Log _log = LogFactoryUtil.getLog(AragonPortalUtilitiesUserLogActionCommand.class);
}
