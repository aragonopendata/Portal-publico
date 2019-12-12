package es.aragon.base.startup_bundle_activator.util;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.aragon.base.startup_bundle_activator.constants.StartupBundleActivatorConstants;

/**
 * @author pfalcon
 * Utilidades para la importación de estructuras
 */
public class StructureUtil {
	
	/**
	 * Añade o actualiza todas las estructuras de la carpeta /resource/structures al sitio web indicado 
	 * @param userId Usuario que realiza la acción
	 * @param groupId Identificador del sitio web al que se importarán las estructuras
	 * @param serviceContext ServiceContext
	 * @throws Exception
	 */
	public static void addDDMStructures(long userId, long groupId, ServiceContext serviceContext) throws Exception {
		//Obtener el sitio web y configurar el serviceContext
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group != null) {
			serviceContext.setCompanyId(group.getCompanyId());
		}
		serviceContext.setUserId(userId);
        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);
        serviceContext.setScopeGroupId(groupId);
        serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
        try {
            //Se recorre el listado de estructuras obtenidas desde la carpeta /resources/structures y se añaden o actualizan al sitio web correspondiente según la configuración
        	List<DDMStructure> structuresList = getStructures();
            int index = 0;
            for (DDMStructure structure : structuresList) {
                long structureClassNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);
            	String serializedJSONDDMForm = StartupBundleActivatorUtil.getStringFromInputStream(StructureUtil.class.getResourceAsStream(StartupBundleActivatorUtil.getBundleEntries(StartupBundleActivatorConstants.STARTUP_STRUCTURES_DIRECTORY_NAME, "*.json", false)[index]));
        		DDMForm ddmForm = DDMUtil.getDDMForm(serializedJSONDDMForm);
        		DDMFormLayout ddmFormLayout = DDMUtil.getDefaultDDMFormLayout(ddmForm);
	        	//Se comprueba si existe la estructura para ver si hay que crearla o actualizarla
        		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(groupId, structureClassNameId, structure.getStructureKey());
        		if (ddmStructure == null) {
        			_log.info("Creando estructura: " + structure.getStructureKey());
        			ddmStructure = DDMStructureLocalServiceUtil.addStructure(userId, groupId, DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, structureClassNameId, structure.getStructureKey(), structure.getNameMap(), structure.getDescriptionMap(), ddmForm, ddmFormLayout, StorageType.JSON.toString(), DDMStructureConstants.TYPE_DEFAULT, serviceContext);
        		} else {
        			_log.info("Actualizando estructura: " + structure.getStructureKey());        			
        			DDMStructureLocalServiceUtil.updateStructure(userId, ddmStructure.getStructureId(), DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, structure.getNameMap(), structure.getDescriptionMap(), ddmForm, ddmFormLayout, serviceContext);
        		}
        		index++;
            }
        } catch(Exception e) {
        	_log.error("Ha habido un error al importar las estructuras: " + e.toString());
            e.printStackTrace();
        }
    }
    
	/**
	 * Devuelve un listado con todas las estructuras detectadas dentro de la carpeta /resources/structures del módulo
	 * @return Listado de estructuras encontradas
	 */
    private static List<DDMStructure> getStructures()  {
        List<DDMStructure> result = new ArrayList<DDMStructure>();
        DDMStructure structureModel;
        String[] resources = StartupBundleActivatorUtil.getBundleEntries(StartupBundleActivatorConstants.STARTUP_STRUCTURES_DIRECTORY_NAME, "*.json", false);
        if (resources != null && resources.length > 0 && resources[0] != null) {
	        for (String resource : resources) {
	            try {
	                structureModel = readStructureModel(resource);
	                result.add(structureModel);
	            } catch (Exception e) {
	            	_log.error("Ha habido un error al importar la estructura " + resource + ": " + e.toString());
	                e.printStackTrace();
	            }
	        }
        }
        return result;        
    }
    
    /**
     * Devuelve una estructura a partir del fichero JSON obtenido en la ruta indicada
     * @param path Ruta del JSON
     * @return Objeto estructura correspondiente al contenido del JSON
     * @throws Exception
     */
    private static DDMStructure readStructureModel(String path) throws Exception {
    	DDMStructure structureModel = DDMStructureLocalServiceUtil.createDDMStructure(0);
    	InputStream is = StructureUtil.class.getResourceAsStream(path);
    	BufferedReader in = new BufferedReader(new InputStreamReader(is));
    	//Inicio de fichero
    	String line = in.readLine();
    	if (line == null || !line.trim().equals("{")) {
    		throw new Exception("Invalid structure header");
    	}
    	//Metadatos
    	StringBuffer structureMetadata = new StringBuffer("");
    	//Leer nombre de la estructura
    	line = in.readLine();
    	if (line == null || !line.matches("\t\"Structure Name\":(.+)")) {
    		throw new Exception("Invalid structure header (structure name)");
    	} else {
    		structureMetadata.append(line.trim());
    	}
    	//Leer descripción de la estructura
    	line = in.readLine();
    	if (line == null || !line.matches("\t\"Structure Description\":(.+)")) {
    		throw new Exception("Invalid structure header (description)");
    	} else {
    		structureMetadata.append(line.trim());
    	}
    	//Leer clave de la estructura
    	line = in.readLine();
    	if(line == null || !line.matches("\t\"Structure Key\":(.+)")) {
    		throw new Exception("Invalid structure header (key)");
    	} else {
    		structureMetadata.append(line.trim());
    	}
    	//Se almacenan los metadatos en un JSON para facilitar su procesamiento
    	JSONObject structureMetadataJSONObject = JSONFactoryUtil.createJSONObject("{" + structureMetadata.toString() + "}");
    	//Setear nombre con sus traducciones
    	String name = structureMetadataJSONObject.getString("Structure Name");
    	try {
    		JSONObject nameJSONObject = JSONFactoryUtil.createJSONObject(name);
    		Map<Locale, String> nameMap = new HashMap<Locale, String>();
    		for (Iterator<String> keys = nameJSONObject.keys(); keys.hasNext();) {
    			String languageId = keys.next();
    			String translatedName = nameJSONObject.getString(languageId);
    			nameMap.put(LocaleUtil.fromLanguageId(languageId), translatedName);
    		}
    		structureModel.setNameMap(nameMap, LocaleUtil.SPAIN);
    	} catch (JSONException e) {
    		structureModel.setName(name);
		}
    	//Setear descripción con sus traducciones
    	String description = structureMetadataJSONObject.getString("Structure Description");
    	try {
    		JSONObject descriptionJSONObject = JSONFactoryUtil.createJSONObject(description);
    		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
    		for (Iterator<String> keys = descriptionJSONObject.keys(); keys.hasNext();) {
    			String languageId = keys.next();
    			String translatedDescription = descriptionJSONObject.getString(languageId);
    			descriptionMap.put(LocaleUtil.fromLanguageId(languageId), translatedDescription);
    		}
    		structureModel.setDescriptionMap(descriptionMap, LocaleUtil.SPAIN);
    	} catch (JSONException e) {
    		structureModel.setDescription(description);
		}
    	//Setear key
    	structureModel.setStructureKey(structureMetadataJSONObject.getString("Structure Key"));
    	//Return de la entidad configurada
    	return structureModel;
    }
	
	/**
	 * Log de la clase
	 */
	private static Log _log = LogFactoryUtil.getLog(StructureUtil.class.getName());

}
