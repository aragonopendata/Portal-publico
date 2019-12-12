package es.aragon.base.resources.importer.util;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.aragon.base.resources.importer.constants.ResourcesImporterConstants;

/**
 * @author Alex
 */
public class StructureUtil {

	/**
	 * Devuelve un listado con todas las estructuras detectadas dentro de la carpeta /resources/data/structures
	 * @param groupId Identificador del sitio web en el que se importaran las estructuras
	 * @return Listado de estructuras encontradas
	 */
	public static List<DDMStructure> getDDMStructures(long groupId) {
		
		List<DDMStructure> result = new ArrayList<DDMStructure>();
        DDMStructure structureModel;
        String[] resources = ResourcesImporterUtil.getBundleEntries(ResourcesImporterConstants.STRUCTURES_DIRECTORY_NAME, "*.json", false);
        if (resources != null && resources.length > 0 && resources[0] != null) {
	        for (String resource : resources) {
	            try {
	            	String contentFile = ResourcesImporterUtil.getStringFromInputStream(StructureUtil.class.getResourceAsStream(resource));
	                structureModel = readStructureModel(resource, groupId, contentFile);
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
	 * Busca la estructura en la carpeta resources a partir de la structureKey
	 * @param structureKey Clave de la estructura a partir de la cual se realizara la busqueda
	 * @param groupId Identificador del sitio web en el que se importaran las estructuras
	 * @return Estructuras encontrada
	 */
	public static DDMStructure fetchStructureModel(String structureKey, long groupId) {
		List<DDMStructure> strutureModelList = getDDMStructures(groupId);
		if(Validator.isNotNull(strutureModelList)) {
			for(DDMStructure structureModel : strutureModelList) {
				if(structureKey.equalsIgnoreCase(structureModel.getStructureKey())) {
					return structureModel;
				}
			}
		}
		return null;
	}
	
	/**
     * Devuelve una estructura a partir del fichero JSON obtenido en la ruta indicada
     * @param path Ruta del JSON
     * @param groupId Identificador del sitio web en el que se importaran las estructuras
     * @return Objeto estructura correspondiente al contenido del JSON
     * @throws Exception
     */
    private static DDMStructure readStructureModel(String path, long groupId, String contentFile) throws Exception {
    	DDMStructure structureModel = DDMStructureLocalServiceUtil.createDDMStructure(0);
    	InputStream is = StructureUtil.class.getResourceAsStream(path);
    	BufferedReader in = new BufferedReader(new InputStreamReader(is));
    	
    	//Inicio de fichero
    	String line = in.readLine();
    	if(line == null || !line.trim().equals("{")) {
    		throw new Exception("Invalid structure header");
    	}
    	
    	//Metadatos
    	StringBuffer structureMetadata = new StringBuffer("");
    	
    	//Leer nombre de la estructura
    	line = in.readLine();
    	if(line == null || !line.matches("\t\"Structure Name\":(.+)")) {
    		throw new Exception("Invalid structure header (structure name)");
    	}else {
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
    	
    	structureModel.setStructureId(0);
    	
    	DDMForm ddmForm = DDMUtil.getDDMForm(contentFile);
    	structureModel.setDDMForm(ddmForm);
    	
    	JSONObject contentJson = JSONFactoryUtil.createJSONObject(contentFile);
    	if(contentJson.has("Structure Name")) {
    		contentJson.remove("Structure Name");
    	}
    	if(contentJson.has("Structure Description")) {
    		contentJson.remove("Structure Description");
    	}
    	if(contentJson.has("Structure Key")) {
    		contentJson.remove("Structure Key");
    	}
    	contentFile = contentJson.toJSONString();
    	structureModel.setDefinition(contentFile);
    	
    	long structureClassNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);
		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(groupId, structureClassNameId, structureModel.getStructureKey());
		if(Validator.isNotNull(ddmStructure)) {
			structureModel.setUserId(ddmStructure.getUserId());
			structureModel.setUserName(ddmStructure.getVersionUserName());
			structureModel.setModifiedDate(ddmStructure.getModifiedDate());
			structureModel.setStructureId(ddmStructure.getStructureId());
		}
    	
    	//Return de la entidad configurada
    	return structureModel;
    }
    
    /**
     * Actualiza las estructuras seleccionadas y crea aquellas que no existen.
     * @param structuresSelected Array que contiene el structureKey de las structuras seleccionadas
     * @param ThemeDisplay  
     */
    public static void updateStructuresSelected(String[] structuresSelected, ThemeDisplay themeDisplay) {
    	
    	long groupId = themeDisplay.getScopeGroupId();
		long userId = themeDisplay.getUserId();
		
		long structureClassNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAddGroupPermissions(true);
		
		for(String structureKey : structuresSelected) {
			DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(groupId, structureClassNameId, structureKey);
			DDMStructure structureModel = StructureUtil.fetchStructureModel(structureKey, groupId);
			if(Validator.isNotNull(structureModel)) {
				
        		DDMForm ddmForm = structureModel.getDDMForm();
        		DDMFormLayout ddmFormLayout = DDMUtil.getDefaultDDMFormLayout(ddmForm);
        		
				if(Validator.isNotNull(ddmStructure)) {
					//Actualizamos estructura
					try {
						_log.info("Actualizando estructura: " + structureKey);  
						ddmStructure = DDMStructureLocalServiceUtil.updateStructure(userId, ddmStructure.getStructureId(), 
								DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, structureModel.getNameMap(), 
								structureModel.getDescriptionMap(), ddmForm, ddmFormLayout, serviceContext);
					} catch (PortalException e) {
						_log.error("Error al actualizar la estructura " + structureKey);
					}
				}else{
					//Creamos estructura
	    			try {
	    				_log.info("Creando estructura: " + structureKey);
						ddmStructure = DDMStructureLocalServiceUtil.addStructure(userId, groupId, 
								DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, structureClassNameId, 
								structureKey, structureModel.getNameMap(), structureModel.getDescriptionMap(), 
								ddmForm, ddmFormLayout, StorageType.JSON.toString(), DDMStructureConstants.TYPE_DEFAULT, 
								serviceContext);
					} catch (PortalException e) {
						_log.error("Error al crear la estructura " + structureKey);
					}
				}
			}else {
				_log.info("No se ha encontrado la estructura: " + structureKey);
			}
		}
    }
    
    public static boolean isStructureUpdated(long structureId, long groupId) {
    	DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchDDMStructure(structureId);
    	if(Validator.isNotNull(ddmStructure)) {
    		String ddmStructureContent = ddmStructure.getDefinition().trim();
    		DDMStructure ddmStructureModel = fetchStructureModel(ddmStructure.getStructureKey(), groupId);
    		if(Validator.isNotNull(ddmStructureModel)) {
    			String structureModelContent = ddmStructureModel.getDefinition().trim();
    			if(structureModelContent.equals(ddmStructureContent)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private static final Log _log = LogFactoryUtil.getLog(StructureUtil.class.getName());
}
