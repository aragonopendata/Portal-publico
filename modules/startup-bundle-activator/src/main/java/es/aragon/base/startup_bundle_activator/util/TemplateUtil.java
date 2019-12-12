package es.aragon.base.startup_bundle_activator.util;

import com.liferay.dynamic.data.mapping.kernel.DDMStructureManagerUtil;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplateModel;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.aragon.base.startup_bundle_activator.constants.StartupBundleActivatorConstants;

/**
 * @author pfalcon
 * Utilidades para la importación de plantillas
 */
public class TemplateUtil {
	
	/**
	 * Añade o actualiza todas las plantillas de la carpeta /resource/templates al sitio web indicado 
	 * @param userId Usuario que realiza la acción
	 * @param groupId Identificador del sitio web al que se importarán las plantillas
	 * @param serviceContext ServiceContext
	 * @throws Exception
	 */
	public static void addDDMTemplates(long userId, long groupId, ServiceContext serviceContext) throws Exception {
		//Configurar el serviceContext
		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		try {
			//Se recorre el listado de plantillas obtenidas desde la carpeta /resources/templates y se añaden o actualizan al sitio web correspondiente según la configuración
			List<DDMTemplateModel> templates = getTemplates();
			for (DDMTemplateModel template : templates) {
				long templateClassNameId =  ClassNameLocalServiceUtil.getClassNameId(com.liferay.dynamic.data.mapping.model.DDMStructure.class); 
				long resourceClassNameId = PortalUtil.getClassNameId(JournalArticle.class);
				long ddmStructureId = 0;
				try {
					ddmStructureId = DDMStructureManagerUtil.fetchStructure(groupId, resourceClassNameId, template.getClassName()).getStructureId();
				} catch (Exception e) {
					_log.warn("No se ha enconcontrado la estructura asociada a la plantilla, se va a crear la plantilla sin estructura asociada");
				}
				//Hacer esto para que no falle si el idioma por defecto no es inglés
				Map<Locale, String> nameMap = new HashMap<Locale, String>();
				nameMap.put(LocaleUtil.SPAIN, template.getName());
				nameMap.put(LocaleUtil.US, template.getName()); 
				//Hacer esto para que no falle si el idioma por defecto no es inglés
				Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
				descriptionMap.put(LocaleUtil.SPAIN, template.getDescription());
				descriptionMap.put(LocaleUtil.US, template.getDescription()); 
				//Se comprueba si existe la plantilla para ver si hay que crearla o actualizarla
				DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(groupId, templateClassNameId, template.getTemplateKey());
				if (ddmTemplate == null) {
					_log.info("Creando plantilla: " + template.getTemplateKey());
					ddmTemplate = DDMTemplateLocalServiceUtil.addTemplate(userId, groupId, templateClassNameId, ddmStructureId, resourceClassNameId, template.getTemplateKey(), nameMap, descriptionMap, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, TemplateConstants.LANG_TYPE_FTL, template.getScript(), template.getCacheable(), false, null, null, serviceContext);
				} else {
					_log.info("Actualizando plantilla " + template.getTemplateKey());
					ddmTemplate = DDMTemplateLocalServiceUtil.updateTemplate(userId, ddmTemplate.getTemplateId(), ddmStructureId, nameMap, descriptionMap, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, TemplateConstants.LANG_TYPE_FTL, template.getScript(), template.getCacheable(), false, null, null, serviceContext);
				}
			}
		} catch(Exception e) {
			_log.error("Ha habido un error al importar las plantillas: " + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve un listado con todas las plantillas detectadas dentro de la carpeta /resources/templates del módulo
	 * @return Listado de plantillas encontradas
	 */
	private static List<DDMTemplateModel> getTemplates()  {
		List<DDMTemplateModel> list = new ArrayList<DDMTemplateModel>();
		String [] resources = StartupBundleActivatorUtil.getBundleEntries(StartupBundleActivatorConstants.STARTUP_TEMPLATES_DIRECTORY_NAME, "*.ftl", false);
		DDMTemplateModel templateModel;
		if (resources[0] != null) {
			for(String resource : resources) {
				try {
					templateModel = readTemplateModel(resource);
					list.add(templateModel);
				} catch (Exception e) {
					_log.error("Ha habido un error al importar la plantilla " + resource + ": " + e.toString());
					e.printStackTrace();
				}
			}
		}
		return list;        
	}

    /**
     * Devuelve una plantilla a partir del fichero FTL obtenido en la ruta indicada
     * @param path Ruta del FTL
     * @return Objeto plantilla correspondiente al contenido del FTL
     * @throws Exception
     */
	private static DDMTemplate readTemplateModel(String path) throws Exception {
		DDMTemplate templateModel = DDMTemplateLocalServiceUtil.createDDMTemplate(0);
		InputStream is = StructureUtil.class.getResourceAsStream(path);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		//Inicio de cabecera
		String line = in.readLine();
		if (line == null || !line.trim().equals("<!--")) {
			throw new Exception("Invalid template header");
		}
		//Leer nombre de la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Template Name:(.+)")) {
			throw new Exception("Invalid template header (template name)");
		}
		templateModel.setName(line.trim().split("Name:")[1].trim());
		//Leer descripción de la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Template Description:(.+)")) {
			throw new Exception("Invalid template header (description)");
		}
		templateModel.setDescription(line.trim().split("Description:")[1].trim());
		//Leer clave de la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Template Key:(.+)")) {
			throw new Exception("Invalid template header (template key)");
		}
		templateModel.setTemplateKey(line.trim().split("Key:")[1].trim());
		//Leer clave de la estructura asociada a la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Structure Key:(.+)")) {
			throw new Exception("Invalid template header (structure key)");
		}
		try {
			templateModel.setClassName(line.trim().split("Key:")[1].trim());
		} catch (Exception e) {
			//throw new Exception("Invalid header value (structure key)");
		}
		//Leer atributo cacheable
		line = in.readLine();
		if (line == null || !line.matches("Cacheable:(.+)")) {
			throw new Exception("Invalid template header (cacheable)");
		}
		try {
			templateModel.setCacheable(Boolean.valueOf(line.trim().split("Cacheable:")[1].trim()));
		} catch (Exception e) {
			throw new Exception("Invalid header value (cacheable must be boolean)");
		}
		//Leer imagen de miniatura
		line = in.readLine();
		if (line == null || !line.matches("Small Image:(.*)")) {
			throw new Exception("Invalid template header (small image)");
		}
		try {
			templateModel.setSmallImage(Boolean.valueOf(line.trim().split("Small Image:")[1].trim()));
		} catch (Exception e) {
			throw new Exception("Invalid header value (small image must be boolean)");
		}
		//Fin de cabecera
		line = in.readLine();
		if (line == null || !line.trim().equals("-->")) {
			throw new Exception("Invalid template header");
		}
		//Script de la plantilla
		line = in.readLine();
		String templateBody = "";
		while (line != null) {
			templateBody += line + "\n";
			line = in.readLine();
		}
		if (templateBody.length() == 0) {
			throw new Exception("Template body is empty");
		}
		templateModel.setScript(templateBody);
		//Return del modelo configurado
		return templateModel;
	}
	
	/**
	 * Log de la clase
	 */
	private static Log _log = LogFactoryUtil.getLog(TemplateUtil.class.getName());
	
}
