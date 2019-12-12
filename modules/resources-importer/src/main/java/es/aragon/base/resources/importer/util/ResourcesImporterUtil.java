package es.aragon.base.resources.importer.util;

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
 * @author Alex
 */
public class ResourcesImporterUtil {

	/**
	 * Devuelve un listado con las rutas de los ficheros contenidos por el path indicado que cumplen el patron especificado
	 * @param path Ruta sobre la que buscar
	 * @param filePattern Patron de búsqueda
	 * @param recurse Indica si la funcion ha de mirar dentro de subcarpetas
	 * @return Listado de rutas de los ficheros encontrados
	 */
    public static String[] getBundleEntries (String path, String filePattern, boolean recurse) {
        Bundle bundle = FrameworkUtil.getBundle(ResourcesImporterUtil.class);
        Enumeration<URL> entriesEnum = bundle.findEntries(path, filePattern, recurse);
        String [] paths = null;
        
        if (entriesEnum != null) {
        	paths = new String[Collections.list(entriesEnum).size()];
        	int i = 0;
            while (entriesEnum.hasMoreElements()) {
            	 paths[i] = entriesEnum.nextElement().getPath();
            	 i++;
            }
        }
        return paths;
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
}
