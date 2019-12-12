package es.aragon.base.startup_bundle_activator.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author pfalcon
 * Utilidades genéricas del módulo
 */
public class StartupBundleActivatorUtil {
	
	/**
	 * Comprueba si una configuración del portlet.properties aplica a la instancia indicada
	 * @param company Instancia del portal
	 * @param settings Valor de la configuración recogida desde el portlet.properties
	 * @return Booleano que indica si la configuración aplica a la compañía indicada o no
	 */
	public static boolean applyToCurrentCompanyId(Company company, String settings) {
		boolean result = Boolean.FALSE;
		if (Validator.isNotNull(settings)) {
			//Si el valor es "all", la configuración aplica a todas las instancias del portal
			if (settings.equalsIgnoreCase("all")) {
				result = Boolean.TRUE;
			} else {
				//Se comprueba si el identificador web de la instancia se encuentra entre los indicados en la configuración
				String[] webIds = settings.split(",");
		        for (String webId : webIds) {
		            if (company.getWebId().trim().toLowerCase().equals(webId.trim().toLowerCase())) {
		            	result = Boolean.TRUE;
		            }
		        }
			}
		}
        return result;
    }
	
	/**
	 * Devuelve el identificador del sitio web configurado en el portlet.properties para la instancia indicada
	 * @param companyId Identificador de instancia
	 * @param settings Valor de la configuración recogida desde el portlet.properties
	 * @return Identificador del sitio web encontrado; 0 en caso de que no se haya encontrado
	 */
	public static long getGroupIdByConfig(long companyId, String settings) {
		long groupId = 0;
		if (Validator.isNotNull(settings)) {
			//Se comprueba si alguno de los sitios web de la instancia tiene la friendly url indicada en la configuracion
			String[] groupsfriendLyURL = settings.split(",");
    		for (String groupfriendLyURL : groupsfriendLyURL) {
                Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(companyId, groupfriendLyURL);
                if (group != null) {
                	groupId = group.getGroupId();
                }
            }
		}
		return groupId;
	}
	
	/**
	 * Devuelve la información de cada línea de la cabecera de los ficheros de importación limpiando los caracteres innecesarios
	 * @param line Fila del fichero de importación
	 * @return Información procesada sin caracteres extraños
	 */
	public static String getData(String line){
		String data = line.substring(line.indexOf(":") + 1, line.length());
		if (data.contains("\"")) {
			data = StringUtil.replace(data, StringPool.QUOTE, StringPool.SPACE);
		}
		if (data.startsWith(" ")) {
			data = data.substring(1, data.length());
			while (data.startsWith(" ")) {
				data = data.substring(1, data.length());
			}
		}
		if (data.endsWith(",")) {
			data = data.substring(0, data.length()-1);
		}
		return data;
	}
	
	/**
	 * Devuelve una cadena a partir de un stream de datos
	 * @param is Stream de datos
	 * @return Cadena obtenida del stream
	 */
	public static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
    public static String[] getBundleEntries (String path, String filePattern, boolean recurse) {
        Bundle bundle = FrameworkUtil.getBundle(StartupBundleActivatorUtil.class);
        Enumeration<URL> entriesEnum = bundle.findEntries(path, filePattern, recurse);
        String [] paths = new String[Collections.list(entriesEnum).size()];
        entriesEnum = bundle.findEntries(path, filePattern, recurse);
        int i = 0;
        if (entriesEnum != null) {
            while (entriesEnum.hasMoreElements()) {
            	 paths[i] = entriesEnum.nextElement().getPath();
            	 i++;
            }
        }
        return paths;
    }
	
}
