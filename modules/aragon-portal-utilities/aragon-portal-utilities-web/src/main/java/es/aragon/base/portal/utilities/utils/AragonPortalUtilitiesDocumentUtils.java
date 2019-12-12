package es.aragon.base.portal.utilities.utils;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import java.util.List;

public class AragonPortalUtilitiesDocumentUtils {
	private AragonPortalUtilitiesDocumentUtils() {}
		
	public static String getDLFolderJson(long groupId) {
		JSONArray rootJSONArray = JSONFactoryUtil.createJSONArray();
		JSONObject rootJSONObject = JSONFactoryUtil.createJSONObject();
		
		rootJSONObject.put("name", "Todas las carpetas");
		rootJSONObject.put("id", 0L);
		
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(groupId, 0L, false);
		
		for(DLFolder dlFolder : dlFolders) {
			if(!dlFolder.isInTrash()) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				
				jsonObject.put("name", dlFolder.getName());
				jsonObject.put("id", dlFolder.getFolderId());
				jsonObject.put("nested", getDLFolderJson(groupId, dlFolder.getFolderId()));
				
				jsonArray.put(jsonObject);
			}
		}
		
		rootJSONObject.put("nested", jsonArray);
		rootJSONArray.put(rootJSONObject);

		
		return rootJSONArray.toJSONString();
	}
	
	private static JSONArray getDLFolderJson(long groupId, long parentFolderId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(groupId, parentFolderId);

		for(DLFolder dlFolder : dlFolders) {
			if(!dlFolder.isInTrash()) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				
				jsonObject.put("name", dlFolder.getName());
				jsonObject.put("id", dlFolder.getFolderId());
				jsonObject.put("nested", getDLFolderJson(groupId, dlFolder.getFolderId()));
				
				jsonArray.put(jsonObject);
			}
		}
		
		return jsonArray;
	}
}
