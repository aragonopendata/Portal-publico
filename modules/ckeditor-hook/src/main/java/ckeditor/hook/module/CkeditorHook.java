package ckeditor.hook.module;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

/**
 * @author Mikel Jorge
 */
@Component(
	immediate = true,
	property = {
		"editor.name=ckeditor"
	},
	service = EditorConfigContributor.class
)
public class CkeditorHook extends BaseEditorConfigContributor {

	private static Log _log = LogFactoryUtil.getLog(CkeditorHook.class);
	private static final String CUSTOM_TOOLBAR_FILE = "modified.json";
	private static final String STYLES_FILE = "styles.json";
	
	@Override
	public void populateConfigJSONObject(JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes, ThemeDisplay themeDisplay, RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
		// try to force all paste events as plain texts, not working propertly from Word
		jsonObject.put("forcePasteAsPlainText", true);
		jsonObject.put("pasteFilter", "plain-text");
		jsonObject.put("pasteFromWordRemoveFontStyles", true);
		jsonObject.put("pasteFromWordRemoveStyles", true);
		
		// set showed styles to the one's containted in styles.json file 
		jsonObject.put("stylesSet", customToolbar(STYLES_FILE));
		
		// edit all toolbar's button set to our custom toolbar based on modified.json file
		jsonObject.put("toolbar_editInPlace"	, customToolbar(CUSTOM_TOOLBAR_FILE));
		jsonObject.put("toolbar_tablet"			, customToolbar(CUSTOM_TOOLBAR_FILE));
		jsonObject.put("toolbar_phone"			, customToolbar(CUSTOM_TOOLBAR_FILE));
		jsonObject.put("toolbar_email"			, customToolbar(CUSTOM_TOOLBAR_FILE));
		jsonObject.put("toolbar_liferayArticle"	, customToolbar(CUSTOM_TOOLBAR_FILE));
		jsonObject.put("toolbar_liferay"		, customToolbar(CUSTOM_TOOLBAR_FILE));
		jsonObject.put("toolbar_simple"			, customToolbar(CUSTOM_TOOLBAR_FILE));
	}

	private JSONArray customToolbar(String file) {

		JSONArray jsonArray = null;
		
		try {
			InputStream is = CkeditorHook.class.getResourceAsStream("/toolbar/" + file);
			if (is != null) {
				byte[] encodedContent = IOUtils.toByteArray(is);
				String jsonContentFile = new String(encodedContent, StandardCharsets.UTF_8);
				
				jsonArray = JSONFactoryUtil.createJSONArray(jsonContentFile);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return jsonArray;
	}
}