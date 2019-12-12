package es.aragon.base.categories_importer.util.assetcategory;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

public class ExpandoUtil {

	public static final String TABLE_NAME = "voc_redirect_map";
	public static final String CAT_MAP_COLUMN_NAME = "redirect-elements";
	public static final String OLD_URL_MAP_COLUMN_NAME = "old-url-map-elements";
	public static final String NEW_URL_MAP_COLUMN_NAME = "new-url-map-redirect-elements";
	public static final String SEPARATOR = "-SEPARATOR-";
	public static final int COLUMN_TYPE = ExpandoColumnConstants.STRING;

	private static void verifyExpandoInformation(long companyId) {
		String className = AssetVocabulary.class.getName();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(className);
		ExpandoTable expandoTable = null;
		try {
			expandoTable = ExpandoTableLocalServiceUtil.fetchTable(companyId, classNameId, TABLE_NAME);
			if (expandoTable == null) {
				expandoTable = ExpandoTableLocalServiceUtil.addTable(companyId, classNameId, TABLE_NAME);
			}
		} catch (PortalException e) {
			_log.error("Ha habido un error al obtener o conssultar el expando " + TABLE_NAME + " de " + className
					+ " en la instancia " + companyId + ": " + e.toString());
		}
		
		ExpandoColumn expandoColumn = null;
		try {
			// Consultar expando, si no existe se crea
			expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(companyId, className, TABLE_NAME, CAT_MAP_COLUMN_NAME);
			if (expandoColumn == null) {
				expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(expandoTable.getTableId(), CAT_MAP_COLUMN_NAME, COLUMN_TYPE, "");
				_log.info("Creando campo personalizado: " + CAT_MAP_COLUMN_NAME);
			}
			expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(companyId, className, TABLE_NAME, OLD_URL_MAP_COLUMN_NAME);
			if (expandoColumn == null) {
				expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(expandoTable.getTableId(), OLD_URL_MAP_COLUMN_NAME, COLUMN_TYPE, "");
				_log.info("Creando campo personalizado: " + OLD_URL_MAP_COLUMN_NAME);
			}
			expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(companyId, className, TABLE_NAME, NEW_URL_MAP_COLUMN_NAME);
			if (expandoColumn == null) {
				expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(expandoTable.getTableId(), NEW_URL_MAP_COLUMN_NAME, COLUMN_TYPE, "");
				_log.info("Creando campo personalizado: " + NEW_URL_MAP_COLUMN_NAME);
			}
		} catch (Exception e) {
			_log.error("Ha habido un error al incluir el expando: " + e.toString());
		}
	}
	
	public static void setCategoryMapValue(long companyId, long vocabularyId, String data) {
		setValue(companyId, vocabularyId, CAT_MAP_COLUMN_NAME, data);
	}
	
	public static void addCategoryMapValue(long companyId, long vocabularyId, String data) {
		addValue(companyId, vocabularyId, CAT_MAP_COLUMN_NAME, data);
	}
	
	public static String getCategoryMapValue(long companyId, long vocabularyId) {
		return getValue(companyId, vocabularyId, CAT_MAP_COLUMN_NAME);
	}
	
	public static void setOldUrlValue(long companyId, long vocabularyId, String data) {
		setValue(companyId, vocabularyId, OLD_URL_MAP_COLUMN_NAME, data);
	}
	
	public static void addOldUrlValue(long companyId, long vocabularyId, String data) {
		addValue(companyId, vocabularyId, OLD_URL_MAP_COLUMN_NAME, data);
	}
	
	public static String getOldUrlValue(long companyId, long vocabularyId) {
		return getValue(companyId, vocabularyId, OLD_URL_MAP_COLUMN_NAME);
	}
	
	public static void setNewUrlValue(long companyId, long vocabularyId, String data) {
		setValue(companyId, vocabularyId, NEW_URL_MAP_COLUMN_NAME, data);
	}
	
	public static void addNewUrlValue(long companyId, long vocabularyId, String data) {
		addValue(companyId, vocabularyId, NEW_URL_MAP_COLUMN_NAME, data);
	}
	
	public static String getNewUrlValue(long companyId, long vocabularyId) {
		return getValue(companyId, vocabularyId, NEW_URL_MAP_COLUMN_NAME);
	}
	
	public static void addOldUrlLayoutsValue(long companyId, long vocabularyId, Layout vocabularyLayout) {
		List<Layout> layouts = vocabularyLayout.getAllChildren();
		for(Layout layout : layouts) {
			long categoryId = GetterUtil.getLong(layout.getExpandoBridge().getAttribute("related-category-id", Boolean.FALSE), 0);
			if(categoryId>0) {
				String layoutFriendlyURL = layout.getFriendlyURL();
				String data = categoryId + "," + layoutFriendlyURL;
				ExpandoUtil.addOldUrlValue(companyId, vocabularyId, data);
			}
		}
	}
	
	/**
	 * Use this method to set all info to your custom data
	 * @param companyId
	 * @param vocabularyId
	 * @param data to set included data. It will override existing data.
	 */
	public static void setValue(long companyId, long vocabularyId, String columnName, String data) {
		verifyExpandoInformation(companyId);
		String className = AssetVocabulary.class.getName();
		try {
			ExpandoValueLocalServiceUtil.addValue(companyId, className, TABLE_NAME, columnName, vocabularyId, data);
		} catch (PortalException e) {
			_log.error("Error setting info to expando column", e);
		}
	}
	
	/**
	 * Use this method to include more data info
	 * @param companyId
	 * @param vocabularyId
	 * @param data to add included data. Primary key is the first numeric secuence
	 */
	public static void addValue(long companyId, long vocabularyId, String columnName, String data) {
		verifyExpandoInformation(companyId);
		String className = AssetVocabulary.class.getName();
		try {
			String currentValue = getValue(companyId, vocabularyId, columnName);
			String primaryKey = data.split(",")[0];
			if(Validator.isNotNull(currentValue)) {
				boolean addItem = true;
				String[] elements = currentValue.split(SEPARATOR);
				for(String element : elements) {
					String elementPrimaryKey = element.split(",")[0];
					if(primaryKey.equals(elementPrimaryKey)) {
						addItem = false;
					}
				}
				if(addItem) {
					currentValue = currentValue + SEPARATOR + data;
				}
			} else {
				currentValue = data;
			}
			ExpandoValueLocalServiceUtil.addValue(companyId, className, TABLE_NAME, columnName, vocabularyId, currentValue);
		} catch (PortalException e) {
			_log.error("Error setting info to expando column", e);
		}
	}
	
	public static String getValue(long companyId, long vocabularyId, String columnName) {
		verifyExpandoInformation(companyId);
		String result = "";
		String className = AssetVocabulary.class.getName();
		ExpandoValue value = ExpandoValueLocalServiceUtil.getValue(companyId, className, TABLE_NAME, columnName, vocabularyId);
		if(Validator.isNotNull(value)) {
			try {
				result = value.getString();
			} catch (PortalException e) {
				_log.error("Error getting info to expando column", e);
			}
		}
		return result;
	}

	private static Log _log = LogFactoryUtil.getLog(ExpandoUtil.class.getName());

}