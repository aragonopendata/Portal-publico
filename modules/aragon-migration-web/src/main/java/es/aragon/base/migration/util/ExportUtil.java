package es.aragon.base.migration.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.aragon.base.migration.model.Area;
import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.service.AreaLocalServiceUtil;

public class ExportUtil {

	public static final String CSV_SEPARATOR = ";";
	public static final String[] EXPORT_CSV_CONTENTS_HEADERS = {"Nombre", "URL original", "Area", "Categorias", "Carpeta destino", "Migrar", "Comentarios"};
	
	public static byte[] getContentsExportData(List<Content> contents) {
		StringBuilder stringBuilder = new StringBuilder();
		//Headers
		for (String header : ExportUtil.EXPORT_CSV_CONTENTS_HEADERS) {
			stringBuilder.append(CSVUtil.encode(header));
			stringBuilder.append(ExportUtil.CSV_SEPARATOR);
		}
		stringBuilder.append(StringPool.NEW_LINE);
		if (contents != null && !contents.isEmpty()) {
			for (Content content : contents) {
				//Titulo
				stringBuilder.append(CSVUtil.encode(content.getTitle()));
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//URL original
				stringBuilder.append(CSVUtil.encode(content.getUrl()));
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//Area
				Area area = AreaLocalServiceUtil.fetchArea(content.getAreaId());
				if (area != null) {
					stringBuilder.append(CSVUtil.encode(area.getName()));
				} else {
					stringBuilder.append(CSVUtil.encode(""));
				}
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//Categorias
				stringBuilder.append(CSVUtil.encode(getCategories(content.getTags())));
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//Carpeta destino
				JournalFolder folder = JournalFolderLocalServiceUtil.fetchJournalFolder(content.getJournalFolderId());
				if (folder != null) {
					stringBuilder.append(CSVUtil.encode(folder.getName()));
				} else {
					stringBuilder.append(CSVUtil.encode(""));
				}
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//Migrar
				String migrar = "Si";
				if (content.getActionId() == 0) {
					migrar = "No";
				}
				stringBuilder.append(CSVUtil.encode(migrar));
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//Comentarios
				stringBuilder.append(CSVUtil.encode(content.getComments()));
				stringBuilder.append(ExportUtil.CSV_SEPARATOR);
				//New line
				stringBuilder.append(StringPool.NEW_LINE);
			}
		}
		return stringBuilder.toString().getBytes();
	}
	
	private static String getCategories(String tags) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<Long, List<String>> vocabulariesMap = new HashMap<>();
		long[] categoryIds = StringUtil.split(tags, 0L);
		for(long categoryId : categoryIds) {
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
			if(assetCategory != null) {
				List<String> categories;
				if(vocabulariesMap.containsKey(assetCategory.getVocabularyId())) {
					categories = vocabulariesMap.get(assetCategory.getVocabularyId());
				} 
				else {
					categories = new ArrayList<>();
				}
				categories.add(assetCategory.getName());
				vocabulariesMap.put(assetCategory.getVocabularyId(), categories);
			}
		}
		for(Map.Entry<Long, List<String>> entry : vocabulariesMap.entrySet()) {
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(entry.getKey());
			if (assetVocabulary != null) {
				stringBuilder.append(assetVocabulary.getName() + ":");
				String separator = " ";
				for(String categoryName : entry.getValue()) {
					stringBuilder.append(separator);
					separator = ", ";
					stringBuilder.append(categoryName);
				}
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
		
}
