package es.aragon.base.startup_bundle_activator.util;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author alex
 * Utilidades para la importación de campos personalizados
 */
public class CustomFieldUtil {

	/**
	 * Añade una serie de expandos al company indicado.
	 * @param companyId Identificador de instancia.
	 */
	public static void addCustomFields(long companyId){
		ExpandoColumn expandoColumn = null;
		
	    //Expandos para JournalArticle
	    ExpandoTable journalArticleExpandoTable = getOrAddExpandoTable(companyId, JournalArticle.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	    //articleFriendlyUrl
	    expandoColumn = getOrAddExpandoColumn(companyId, JournalArticle.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "articleFriendlyUrl", journalArticleExpandoTable, ExpandoColumnConstants.STRING, StringPool.BLANK, true, null);
	    addViewPermission(expandoColumn,companyId);
	}
	
	/**
	 * Devuelve la tabla de expando correspondiente a los parámetros indicados. En caso de no existir, se crea.
	 * @param companyId Identificador de instancia.
	 * @param className Nombre de la clase.
	 * @param tableName Nombre de la tabla.
	 * @return Tabla de Expando encontrada o creada.
	 */
	public static ExpandoTable getOrAddExpandoTable(long companyId, String className, String tableName) {
	    ExpandoTable expandoTable = null;
	    try {
	        expandoTable = ExpandoTableLocalServiceUtil.fetchDefaultTable(companyId, className);
	        if (expandoTable == null) {
	        	expandoTable = ExpandoTableLocalServiceUtil.addTable(companyId, className, tableName);
	        }
	    } catch (PortalException e) {
	        _log.error("Ha habido un error al obtener o conssultar el expando " + tableName + " de " + className + " en la instancia " + companyId + ": " + e.toString());
	    }
	    return expandoTable;
	}
	
	/**
	 * Devuelve la columna de expando correspondiente a los parámetros indicados. En caso de no existir, se crea.
	 * @param companyId Identificador de instancia.
	 * @param className Nombre de la clase.
	 * @param tableName Nombre de la tabla.
	 * @param columnName Nombre de la columna.
	 * @param expandoTable Tabla de expando.
	 * @param type Tipo.
	 * @param defaultData Valor por defecto.
	 * @param hidden Oculto.
	 * @param displayType Tipo de visualización.
	 * @return Columna de expando encontrada o creada.
	 */
	public static ExpandoColumn getOrAddExpandoColumn(long companyId, String className, String tableName, String columnName, ExpandoTable expandoTable, int type, Object defaultData, boolean hidden, String displayType) {
	    ExpandoColumn expandoColumn = null;
	    try {
	    	//Consultar expando, si no existe se crea
	    	expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(companyId, className, tableName, columnName);
	        if (expandoColumn == null) {
	        	expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(expandoTable.getTableId(), columnName, type, defaultData);
	        	_log.info("Creando campo personalizado: " + columnName);
	        } else{
	        	expandoColumn = ExpandoColumnLocalServiceUtil.updateColumn(expandoColumn.getColumnId(), columnName, type, defaultData);
	        	_log.info("Actualizando campo personalizado: " + columnName);
	        }
	        //Si se ha encontrado o creado el expando, se setean las properties correspondientes según los parámetros recibidos y se actualiza el expando.
	        if (expandoColumn != null) {
	        	UnicodeProperties up = new UnicodeProperties();
	        	if (hidden) {
		        	up.put(ExpandoColumnConstants.PROPERTY_HIDDEN, "true");
	        	}
	        	if (displayType != null && !displayType.trim().isEmpty()) {
	        		up.put(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE, displayType);
	        	}
	        	try {
	        		if(!up.isEmpty()) {
		        		expandoColumn.setTypeSettingsProperties(up);
						ExpandoColumnLocalServiceUtil.updateExpandoColumn(expandoColumn);
	        		}
				} catch (Exception e) {
					_log.error("Ha habido un error al modificar la configuración del expando: " + e.toString());
				}
	        }
	    } catch (Exception e) {
	        _log.error("Ha habido un error al añadir el expando: " + e.toString());
	    }       

	    return expandoColumn;
	}
	
	/**
	 * Añade permisos de visuización del expando para guest.
	 * @param expandoColumn Columna de expando.
	 * @param companyId Identificador de instancia.
	 */
	public static void addViewPermission(ExpandoColumn expandoColumn, long companyId){	
		Role guest = null;
		try {
			guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
			ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, ExpandoColumn.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(expandoColumn.getColumnId()), guest.getRoleId(), new String[] {ActionKeys.VIEW, ActionKeys.UPDATE});
		} catch (PortalException e) {
			_log.error("Ha habido un error al asignar el permiso de ver al expando: " + e.toString());
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(CustomFieldUtil.class.getName());
}
