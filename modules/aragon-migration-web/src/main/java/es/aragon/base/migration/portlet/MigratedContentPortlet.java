package es.aragon.base.migration.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.migration.constants.AragonMigrationPortletKeys;
import es.aragon.base.migration.model.ContentUrl;
import es.aragon.base.migration.service.ContentUrlLocalService;

/**
 * @author alex
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/migratedContentPortlet/view.jsp",
		"javax.portlet.name=" + AragonMigrationPortletKeys.MIGRATED_CONTENT_PORTLET_KEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MigratedContentPortlet extends MVCPortlet{
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		List<ContentUrl> contentsMigrated = _contentUrlLocalService.getContentUrls(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		long contentId = ParamUtil.getLong(resourceRequest, "exportType", 0);
		
		if(Validator.isNotNull(contentsMigrated) && contentsMigrated.size() > 0) {
			if(contentId==0) {
				//Generate excel file with all URL map
				HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(resourceRequest);
				HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
				
				Workbook excel = new HSSFWorkbook();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				Sheet sheetPermissions = excel.createSheet("Contenidos migrados");
				
				// Se crea un estilo negrita y centrado para el header
				CellStyle boldStyle = excel.createCellStyle();
				Font font = excel.createFont();
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				boldStyle.setFont(font);
				boldStyle.setAlignment(boldStyle.ALIGN_CENTER);
				
				// Cabecera
				String[] head = {"ID CONTENIDO ORIGEN","ID CONTENIDO DESTINO","URL ORIGEN","URL DESTINO"};
				Row headRow = sheetPermissions.createRow(0);
				
				for (int h = 0; h < 4; h++) {
					Cell headCell = headRow.createCell(h);
					headCell.setCellStyle(boldStyle);
					headCell.setCellValue(head[h]);
				}
				
				// Contenidos
				for(int r = 0; r < contentsMigrated.size(); r++) {
					ContentUrl contentUrl = contentsMigrated.get(r);
					String[] dataContentMigrated = new String[]{
							String.valueOf(contentUrl.getContentOriginId()),
							String.valueOf(contentUrl.getContentDestinationId()),
							contentUrl.getUrlOrigin(),
							contentUrl.getUrlDestination()
					};
					Row row = sheetPermissions.createRow(r+1);
					for(int c = 0; c < dataContentMigrated.length; c++) {
						Cell cell = row.createCell(c);
						cell.setCellValue(dataContentMigrated[c]);
					}
				}
				
				excel.write(baos);
				baos.flush();
				baos.close();
				
		        ServletResponseUtil.sendFile(httpServletRequest, httpServletResponse, FILE_NAME,
		        		baos.toByteArray(),"application/download");
			} else if (contentId==1) {
		        //Generate URL redirect files to send to AST
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baos);
				
				StringBuilder reglas = new StringBuilder();
				reglas.append("RewriteEngine On");
				
				int counter = 0;
				int filesCounter = 5;
				int processPercent = 0;
				int processTotalURLs = contentsMigrated.size();
				int processCurrentURL = 0;
				for(ContentUrl contentUrl : contentsMigrated) {
					processCurrentURL++;
					String urlToReplace = contentUrl.getUrlOrigin().replaceAll("http://www.aragon.es", "");
					urlToReplace = urlToReplace.replaceAll("https://www.aragon.es", "");
					urlToReplace = urlToReplace.replaceAll("\\(", "");
					urlToReplace = urlToReplace.replaceAll("\\)", "");
					urlToReplace = urlToReplace.replaceAll("%20", " ");
					urlToReplace = urlToReplace.replaceAll(" ", "\\\\ ");
					
					// If redirect rule contains any portal URL, no process it and continue
					if(urlToReplace.equals("/")){
						continue;
					}
					if(urlToReplace.contains("idTramite")) {
						continue;
					}
					/*
					DynamicQuery dynamicQuery = _layoutLocalService.dynamicQuery();
					dynamicQuery.add(RestrictionsFactoryUtil.eq("friendlyURL", urlToReplace));
					long totalPages = _layoutLocalService.dynamicQueryCount(dynamicQuery);
					if(totalPages!=0) {
						continue;
					}
					*/
					
					String urlToFinish = contentUrl.getUrlDestination().replaceAll("%20", " ");
					urlToFinish = urlToFinish.replaceAll(" ", "\\\\ ");
					if(!urlToFinish.contains("?")) {
						urlToFinish = urlToFinish + "?";
					}
					
					String[] urlList = urlToReplace.split("\\?");
					if(urlList.length>1 && urlList[0].length()>0 && urlList[1].length()>0) {
						reglas.append("\n");
						reglas.append("RewriteCond \"%{QUERY_STRING}\" \""+urlList[1]+"$\"");
						counter++;
						reglas.append("\n");
						reglas.append("RewriteRule ^"+urlList[0]+" "+urlToFinish+" [L,R=301]");
						counter++;
					} else {
						reglas.append("\n");
						reglas.append("RewriteRule ^"+urlToReplace+"$ "+urlToFinish+" [L,R=301]");
						counter++;
					}
					
					if(counter>499) {
						counter = 0;
						filesCounter++;
						zos.putNextEntry(new ZipEntry("redirecciones_"+filesCounter+".conf"));
						zos.write(reglas.toString().getBytes());
						zos.closeEntry();
						reglas = new StringBuilder();
						reglas.append("RewriteEngine On");
					}
					if(((100*processCurrentURL)/processTotalURLs)>processPercent) {
						processPercent = ((100*processCurrentURL)/processTotalURLs);
						_log.info("Estado del proceso en un "+processPercent+"%");
					}
				}
				
				if(counter<500) {
					filesCounter++;
					zos.putNextEntry(new ZipEntry("redirecciones_"+filesCounter+".conf"));
					zos.write(reglas.toString().getBytes());
					zos.closeEntry();
				}
				
				zos.flush();
				baos.flush();
				zos.close();
				baos.close();
		        
		        PortletResponseUtil.sendFile(resourceRequest, resourceResponse,	"ficheros.zip", baos.toByteArray(), "application/zip");
		        
			}
		}else {
			_log.info("NO EXISTEN CONTENIDOS MIGRADOS");
		}
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		//Paginacion
		int delta = ParamUtil.getInteger(renderRequest, "delta", 10);
		int cur = ParamUtil.getInteger(renderRequest, "cur", 1);
		int start = delta * (cur - 1);
		int end = delta * cur;
		
		//Filtrado
		String search = ParamUtil.getString(renderRequest, "search", StringPool.BLANK);	
		
		//Consulta de resultados
		long totalResults = getContentsSize(search);
		List<ContentUrl> contents = getContents(search, start, end);
		
		//Envio de resultados a la vista
		renderRequest.setAttribute("totalResults", totalResults);
		renderRequest.setAttribute("contentUrls", contents);
		
		super.render(renderRequest, renderResponse);
	}
	
	private long getContentsSize(String search) {
		long total = 0;
		try {
			DynamicQuery dynamicQuery = _contentUrlLocalService.dynamicQuery();
			if (Validator.isNotNull(search)) {
				dynamicQuery.add(RestrictionsFactoryUtil.or(
					RestrictionsFactoryUtil.ilike("urlOrigin", "%"+search+"%"),
					RestrictionsFactoryUtil.ilike("urlDestination", "%"+search+"%")
				));
			}
			Order orderBy = OrderFactoryUtil.asc("contentOriginId");
			dynamicQuery.addOrder(orderBy);
			total = _contentUrlLocalService.dynamicQueryCount(dynamicQuery);
		} catch (Exception e) {
			_log.error(e, e);
		}
		return total;
	}
	
	private List<ContentUrl> getContents(String search, int start, int end) {
		List<ContentUrl> results = new ArrayList<ContentUrl>();
		try {
			DynamicQuery dynamicQuery = _contentUrlLocalService.dynamicQuery();
			if (Validator.isNotNull(search)) {
				dynamicQuery.add(RestrictionsFactoryUtil.or(
						RestrictionsFactoryUtil.ilike("urlOrigin", "%"+search+"%"),
						RestrictionsFactoryUtil.ilike("urlDestination", "%"+search+"%")
				));
			}
			Order orderBy = OrderFactoryUtil.asc("contentOriginId");
			dynamicQuery.addOrder(orderBy);
			results = _contentUrlLocalService.dynamicQuery(dynamicQuery, start, end);
		} catch (Exception e) {
			_log.error(e, e);
		}
		return results;
	}
	
	@Reference
	private ContentUrlLocalService _contentUrlLocalService;
	
	@Reference
	private LayoutLocalService _layoutLocalService;

	private static final String FILE_NAME = "Contenidos_migrados.xls";
	private static final Log _log = LogFactoryUtil.getLog(MigratedContentPortlet.class.getName());
}
