package es.aragon.base.related_contents.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import es.aragon.base.related_contents.constants.RelatedContentsConstants;

/**
 * @author pfalcon
 */
public class RelatedContentsUtil {
	
	/**
	 * Devuelve el primer journalArticle mostrado en un visor de contenido de la página indicada.
	 * @param layout Página de la que se obtendrán los visores de contenido.
	 * @param columnId Identificador de la columna del layout de la que se obtendrán los visores de contenido (si no se pasa este parámetro, se buscará en todas las columnas de la página).
	 * @return Identificador del primer resultado obtenido; 0 en caso de que no se haya encontrado ningún resultado
	 */
	public static JournalArticle getJournalArticleDisplayedInPage(Layout layout, String columnId) {
		JournalArticle journalArticle = null;
		try {
			LayoutTypePortlet layoutTypePortlet = (LayoutTypePortlet)layout.getLayoutType();
			List<Portlet> layoutPortletList;
			if (columnId.equals(RelatedContentsConstants.LAYOUT_COLUMN_ANY)) {
				layoutPortletList = layoutTypePortlet.getAllPortlets();
			} else {
				layoutPortletList = layoutTypePortlet.getAllPortlets(columnId);
			}
			if (layoutPortletList != null && !layoutPortletList.isEmpty()) {
				for (Portlet layoutPortlet : layoutPortletList) {
					if (layoutPortlet.getPluginId().contains("com_liferay_journal_content_web_portlet_JournalContentPortlet")) {
						PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(layout, layoutPortlet.getPortletId(), layoutPortlet.getDefaultPreferences());
						if (portletPreferences != null) {
							long assetEntryId = GetterUtil.getLong(portletPreferences.getValue("assetEntryId", null), 0);
							AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchAssetEntry(assetEntryId);
							if (assetEntry != null) {
								journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(assetEntry.getClassPK(), WorkflowConstants.STATUS_APPROVED);
								return journalArticle;
							}
						}
					}
				}

			}
		} catch (Exception e) {
			_log.error("Ha habido un error al obtener el artículo de la página: " + e.toString());
			e.printStackTrace();
		}
		return journalArticle;
	}
	
	/**
	 * Devuelve un listado de contenidos web filtrados por los parámetros indicados.
	 * @param categoryIds Ids de las categoría que deben contener los resultados devueltos.
	 * @param categoryFilterType Indica si los resultados deben contener todas("all") o alguna("any") de las categorías indicadas.
	 * @param orderByCol Indica el nombre del campo de la tabla assetentry por la que se quiere ordenar.
	 * @param orderByType Tipo de orden. Puede ser ascendente("asc") o descendente ("desc").
	 * @param structureIds Identificadores de las estructuras para filtrar los resultados.
	 * @param groupIds Identificadores de los sitios web para filtrar los resultados.
	 * @param start Indicador de primer registro para paginar.
	 * @param end Indicador de último registro para paginar.
	 * @return Listado de contenidos web encontrados
	 */
	public static List<JournalArticle> getFilteredJournalArticles(long[] categoryIds, String categoryFilterType, String orderByCol, String orderByType, long[] structureIds, long[] groupIds, int start, int end) {
		List<JournalArticle> result = new ArrayList<JournalArticle>();
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
		if (categoryIds != null && categoryIds.length > 0) {
			if (categoryFilterType.equals(RelatedContentsConstants.CATEGORY_FILTER_TYPE_ALL)) {
				assetEntryQuery.setAllCategoryIds(categoryIds);
			} else if (categoryFilterType.equals(RelatedContentsConstants.CATEGORY_FILTER_TYPE_ANY)) {
				assetEntryQuery.setAnyCategoryIds(categoryIds);
			}
		}
		assetEntryQuery.setOrderByCol1(orderByCol);
		assetEntryQuery.setOrderByType1(orderByType);
		assetEntryQuery.setClassName(JournalArticle.class.getName());
		if (structureIds != null && structureIds.length > 0) {
			assetEntryQuery.setClassTypeIds(structureIds);
		}
		if (groupIds != null && groupIds.length > 0) {
			assetEntryQuery.setGroupIds(groupIds);
		}
		assetEntryQuery.setStart(start);
		assetEntryQuery.setEnd(end);
		assetEntryQuery.setAndOperator(true);
		List<AssetEntry> assetEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
		for (AssetEntry assetEntry : assetEntryList) {
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(assetEntry.getClassPK(), WorkflowConstants.STATUS_APPROVED);
			if (journalArticle != null) {
				result.add(journalArticle);
			}
		}
		return result;
	}
	
	/**
	 * Devuelve el html de un contenido web para una plantilla determinada.
	 * @param portletRequest Request.
	 * @param portletResponse Response.
	 * @param journalArticle Contenido web.
	 * @param templateKey Clave de la plantilla.
	 * @param themeDisplay ThemeDisplay.
	 * @return Cadena de texto con el html ya procesado.
	 */
	public static String getJournalArticleHtml(PortletRequest portletRequest, PortletResponse portletResponse, JournalArticle journalArticle, String templateKey, ThemeDisplay themeDisplay) {
		String result = StringPool.BLANK;
		try {
			if (templateKey.equals(RelatedContentsConstants.DEFAULT_DISPLAY_TEMPLATE)) {
				result += "<h2>" + journalArticle.getTitle(themeDisplay.getLocale()) + "</h2>";
			} else {
				PortletRequestModel portletRequestModel = new PortletRequestModel(portletRequest, portletResponse);
				JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, templateKey, "view", themeDisplay.getLanguageId(), 1, portletRequestModel, themeDisplay);
				result = journalArticleDisplay.getContent();
			}
		} catch (Exception e) {
			_log.error("Ha habido un error al obtener el html para el journalArticle con id " + journalArticle.getPrimaryKey() + ": " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Gets a journal article from his context url ("/-/")
	 * @param groupId Group identifier
	 * @param url Context url
	 * @return A journal article from his context url ("/-/")
	 */
	public static JournalArticle getJournalArticleByContextUrl(long groupId, String url) {
		JournalArticle journalArticle = null;
		if (url != null && !url.trim().isEmpty()) {
			String journalArticleFriendlyURL = "";
			if (url.contains("/-/")) {
				journalArticleFriendlyURL = url.substring(url.indexOf("/-/") + 3, url.length());
			}
			if (journalArticleFriendlyURL != null && !journalArticleFriendlyURL.isEmpty()) {
				journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticleByUrlTitle(groupId, journalArticleFriendlyURL, WorkflowConstants.STATUS_APPROVED);
			}
		}
		return journalArticle;
	}
	
	/**
	 * Log de la clase
	 */
	private final static Log _log = LogFactoryUtil.getLog(RelatedContentsUtil.class);
	
}
