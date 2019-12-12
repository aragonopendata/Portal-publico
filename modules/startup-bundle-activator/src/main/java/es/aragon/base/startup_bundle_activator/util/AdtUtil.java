package es.aragon.base.startup_bundle_activator.util;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplateModel;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.LocaleUtil;

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
 * Utilidades para la importación de adts
 */
public class AdtUtil {
	
	/**
	 * Añade o actualiza todos los adts de la carpeta /resource/adts al sitio web indicado 
	 * @param userId Usuario que realiza la acción
	 * @param groupId Identificador del sitio web
	 * @param serviceContext ServiceContext
	 * @throws Exception
	 */
	public static void addAdts(long userId, long groupId, ServiceContext serviceContext) throws Exception {
		//Configurar el serviceContext
		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		try {
			//Se recorre el listado de adts obtenidos desde la carpeta /resources/adts y se añaden o actualizan al sitio web correspondiente según la configuración
			List<DDMTemplateModel> templates = getAdts();
			for (DDMTemplateModel template : templates) {
				long templateClassNameId = ClassNameLocalServiceUtil.getClassNameId(template.getClassName());
				long resourceClassNameId = ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.display.template.PortletDisplayTemplate");
				String templateKey = template.getTemplateKey();
				boolean cacheable = template.getCacheable();
				String script = template.getScript();
				boolean smallImage = template.getSmallImage();
				//Hacer esto para que no falle si el idioma por defecto no es inglés
				Map<Locale, String> nameMap = new HashMap<Locale, String>();
				nameMap.put(LocaleUtil.SPAIN, template.getName());
				nameMap.put(LocaleUtil.US, template.getName()); 
				//Hacer esto para que no falle si el idioma por defecto no es inglés
				Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
				descriptionMap.put(LocaleUtil.SPAIN, template.getDescription());
				descriptionMap.put(LocaleUtil.US, template.getDescription()); 
				//Se comprueba si existe el adt para ver si hay que crearlo o actualizarlo
				DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(groupId, templateClassNameId, templateKey);
				if (ddmTemplate == null) {
					_log.info("Creando adt: " + template.getTemplateKey());
					ddmTemplate = DDMTemplateLocalServiceUtil.addTemplate(userId, groupId, templateClassNameId, 0L, resourceClassNameId, templateKey, nameMap, descriptionMap, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, TemplateConstants.LANG_TYPE_FTL, script, cacheable, smallImage, null, null, serviceContext);
				} else {
					_log.info("Actualizando adt: " + template.getTemplateKey());
					ddmTemplate = DDMTemplateLocalServiceUtil.updateTemplate(userId, ddmTemplate.getTemplateId(), 0L, nameMap, descriptionMap, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, TemplateConstants.LANG_TYPE_FTL, script, cacheable, smallImage, null, null, serviceContext);
				}
			}
		} catch(Exception e) {
			_log.error("Ha habido un error al importar los adts: " + e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve un listado con todos los adts detectados dentro de la carpeta /resources/adts del módulo
	 * @return Listado de adts encontrados
	 */
	private static List<DDMTemplateModel> getAdts() {
		List<DDMTemplateModel> list = new ArrayList<DDMTemplateModel>();
		String [] resources = StartupBundleActivatorUtil.getBundleEntries(StartupBundleActivatorConstants.STARTUP_ADTS_DIRECTORY_NAME, "*.ftl", false);
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
			throw new Exception("Invalid asset template header");
		}
		//Leer nombre de la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Template Name:(.+)")) {
			throw new Exception("Invalid asset template header (template name)");
		}
		templateModel.setName(line.trim().split("Name:")[1].trim());
		//Leer descripción de la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Template Description:(.+)")) {
			throw new Exception("Invalid asset template header (description)");
		}
		templateModel.setDescription(line.trim().split("Description:")[1].trim());
		//Leer clave de la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Template Key:(.+)")) {
			throw new Exception("Invalid asset template header (template key)");
		}
		templateModel.setTemplateKey(line.trim().split("Key:")[1].trim());
		//Leer clave de la estructura asociada a la plantilla
		line = in.readLine();
		if (line == null || !line.matches("Structure Key:(.+)")) {
			throw new Exception("Invalid asset template header (structure key)");
		}
		//Leer atributo cacheable
		line = in.readLine();
		if (line == null || !line.matches("Cacheable:(.+)")) {
			throw new Exception("Invalid asset template header (cacheable)");
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
		//Leer className
		line = in.readLine();
		if (line == null || !line.matches("Class Name:(.*)")) {
			throw new Exception("Invalid template header (small image)");
		}
		try {
			templateModel.setClassName(line.trim().split("Class Name:")[1].trim());
		} catch (Exception e) {
			throw new Exception("Invalid header value (small image must be boolean)");
		}
		//Fin de cabecera
		line = in.readLine();
		if (line == null || !line.trim().equals("-->")) {
			throw new Exception("Invalid asset template header");
		}
		//Script de la plantilla
		line = in.readLine();
		String templateBody = "";
		while (line != null) {
			templateBody += line + "\n";
			line = in.readLine();
		}
		if (templateBody.length() == 0) {
			throw new Exception("Asset Template body is empty");
		}
		templateModel.setScript(templateBody);
		//Return del modelo configurado
		return templateModel;
	}
	
	/**
	 * Log de la clase
	 */
	private static Log _log = LogFactoryUtil.getLog(AdtUtil.class.getName());

}
