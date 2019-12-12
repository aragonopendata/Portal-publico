package es.aragon.base.categories_importer.util.assetcategory;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author pfalcon
 * Legislatures import utilities class
 */
public class LegislaturesUtil {

	public static int getLatestRegisteredLegistureId() {
		int result = 0;
		LOG.info("Searching latest registered legislature id");
		try {
			//Read the Organismos JSON from OpenData
			URL url = new URL("https://opendata.aragon.es/GA_OD_Core/download?view_id=159&formato=json&name=Organigrama%20del%20Gobierno%20de%20Arag%C3%B3n&nameRes=Entidades");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String organismosJSONArrayString = StringPool.BLANK;
			while ((line = in.readLine()) != null) {
				organismosJSONArrayString = organismosJSONArrayString + line;
			}
			//JSON processing
			if (!organismosJSONArrayString.isEmpty()) {
				JSONArray organismosJSONArray = JSONFactoryUtil.createJSONArray(organismosJSONArrayString);
				for (int i = 1; i < organismosJSONArray.length(); i++) {
					//"ID_ENTIDAD", "ID_ENTIDAD_PADRE", "ID_LEGISLATURA", "NIVEL", "ORDEN", "NOMBRE", "OBSERVACIONES", "DEPENDENCIA_DIRECTA", "COD_SIU", "EDIFICIO", "DIRECCION", "CP", "LOCALIDAD", "PROVINCIA", "TELEFONO", "COOR_X", "COOR_Y", "RNUM"
					JSONArray organismoJSONArray = organismosJSONArray.getJSONArray(i);
					int idLegislatura = organismoJSONArray.getInt(2);
					if (idLegislatura > result) {
						result = idLegislatura;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("There was an error getting the latest registered legislature id: " + e.toString());
			e.printStackTrace();
		}
		return result;
	}

	private static final Log LOG = LogFactoryUtil.getLog(LegislaturesUtil.class);

}
