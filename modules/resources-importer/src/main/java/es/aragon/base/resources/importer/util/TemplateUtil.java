package es.aragon.base.resources.importer.util;

import com.liferay.dynamic.data.mapping.kernel.DDMStructureManagerUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import es.aragon.base.resources.importer.constants.ResourcesImporterConstants;

/**
 * @author Alex
 */
public class TemplateUtil {

	/**
	 * Devuelve un listado con todas las plantillas detectadas dentro de la carpeta /resources/data/templates
	 * @param groupId Identificador del sitio web en el que se importaran las plantillas 
	 * @return Listado de plantillas encontradas
	 */
	public static List<DDMTemplate> getDDMTemplates(long groupId) {
		
		List<DDMTemplate> result = new ArrayList<DDMTemplate>();
		DDMTemplate templateModel;
        String[] resources = ResourcesImporterUtil.getBundleEntries(ResourcesImporterConstants.TEMPLATES_DIRECTORY_NAME, "*.ftl", false);
        if (resources != null && resources.length > 0 && resources[0] != null) {
	        for (String resource : resources) {
	            try {
	            	templateModel = readTemplateModel(resource, groupId);
	                result.add(templateModel);
	            } catch (Exception e) {
	            	_log.error("Ha habido un error al importar la plantilla " + resource + ": " + e.toString());
	                e.printStackTrace();
	            }
	        }
        }
        return result;
	}
	
	/**
	 * Busca la plantilla en la carpeta resources a partir de la templateKey
	 * @param templateKey Clave de la plantilla a partir de la cual se realizara la busqueda
	 * @param groupId Identificador del sitio web en el que se importaran las plantillas
	 * @return Plantilla encontrada
	 */
	public static DDMTemplate fetchTemplateModel(String templateKey, long groupId) {
		List<DDMTemplate> templateModelList = getDDMTemplates(groupId);
		if(Validator.isNotNull(templateModelList)) {
			for(DDMTemplate templateModel : templateModelList) {
				if(templateKey.equalsIgnoreCase(templateModel.getTemplateKey())) {
					return templateModel;
				}
			}
		}
		return null;
	}
	
	/**
     * Devuelve una plantilla a partir del fichero FTL obtenido en la ruta indicada
     * @param path Ruta del FTL
     * @param groupId Identificador del sitio web en el que se importaran las plantillas 
     * @return Objeto plantilla correspondiente al contenido del FTL
     * @throws Exception
     */
	private static DDMTemplate readTemplateModel(String path, long groupId) throws Exception {
		
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
		
		templateModel.setTemplateId(0);
		
		long templateClassNameId = ClassNameLocalServiceUtil.getClassNameId(DDMStructure.class);
		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(groupId, templateClassNameId, templateModel.getTemplateKey());
		if(Validator.isNotNull(ddmTemplate)) {
			templateModel.setUserId(ddmTemplate.getUserId());
			templateModel.setUserName(ddmTemplate.getVersionUserName());
			templateModel.setModifiedDate(ddmTemplate.getModifiedDate());
			templateModel.setTemplateId(ddmTemplate.getTemplateId());
		}
		
		//Return del modelo configurado
		return templateModel;
	}
	
	/**
     * Actualiza las plantillas seleccionadas y crea aquellas que no existen.
     * @param templatesSelected Array que contiene la templateKey de las plantillas seleccionadas
     * @param ThemeDisplay  
     */
    public static void updateTemplatesSelected(String[] templatesSelected, ThemeDisplay themeDisplay) {
    	
    	long groupId = themeDisplay.getScopeGroupId();
		long userId = themeDisplay.getUserId();
		
		long templateClassNameId = ClassNameLocalServiceUtil.getClassNameId(DDMStructure.class);
		long resourceClassNameId = PortalUtil.getClassNameId(JournalArticle.class);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAddGroupPermissions(true);
		
		for(String templateKey : templatesSelected) {
			DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(groupId, templateClassNameId, templateKey);
			DDMTemplate templateModel = TemplateUtil.fetchTemplateModel(templateKey, groupId);
			if(Validator.isNotNull(templateModel)) {
				
				long ddmStructureId = 0;
				try {
					ddmStructureId = DDMStructureManagerUtil.fetchStructure(groupId, resourceClassNameId, templateModel.getClassName()).getStructureId();
				} catch (Exception e) {
					_log.warn("No se ha enconcontrado la estructura asociada a la plantilla. Se creara la plantilla sin estructura asociada");
				}
				
				if(Validator.isNotNull(ddmTemplate)) {
					//Actualizamos plantilla
					try {
						_log.info("Actualizando plantilla: " + templateKey);  
						ddmTemplate = DDMTemplateLocalServiceUtil.updateTemplate(userId, ddmTemplate.getTemplateId(), 
								ddmStructureId, templateModel.getNameMap(), templateModel.getDescriptionMap(), 
								DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, TemplateConstants.LANG_TYPE_FTL, 
								templateModel.getScript(), templateModel.getCacheable(), false, null, null, serviceContext);
					} catch (PortalException e) {
						_log.error("Error al actualizar la plantilla " + templateKey);
					}
					
				}else{
					//Creamos plantilla
	    			try {
	    				_log.info("Creando plantilla: " + templateKey);
						ddmTemplate = DDMTemplateLocalServiceUtil.addTemplate(userId, groupId, templateClassNameId, 
								ddmStructureId, resourceClassNameId, templateKey, templateModel.getNameMap(), 
								templateModel.getDescriptionMap(), DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, 
								TemplateConstants.LANG_TYPE_FTL, templateModel.getScript(), templateModel.getCacheable(), 
								false, null, null, serviceContext);
					} catch (PortalException e) {
						_log.error("Error al crear la plantilla " + templateKey);
					}
				}
			}else {
				_log.info("No se ha encontrado la plantilla: " + templateKey);
			}
		}
    }
    
    public static boolean isTemplateUpdated(long templateId, long groupId) {
    	DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchDDMTemplate(templateId);
    	if(Validator.isNotNull(ddmTemplate)) {
    		String ddmTemplateScript = ddmTemplate.getScript().trim();
    		DDMTemplate ddmTemplateModel = fetchTemplateModel(ddmTemplate.getTemplateKey(), groupId);
    		if(Validator.isNotNull(ddmTemplateModel)) {
    			String templateModelScript = ddmTemplateModel.getScript().trim();
    			if(templateModelScript.equals(ddmTemplateScript)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private static final Log _log = LogFactoryUtil.getLog(TemplateUtil.class.getName());
}
