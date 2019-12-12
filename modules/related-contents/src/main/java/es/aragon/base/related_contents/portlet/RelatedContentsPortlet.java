package es.aragon.base.related_contents.portlet;

import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.related_contents.constants.RelatedContentsConstants;
import es.aragon.base.related_contents.util.RelatedContentsUtil;

/**
 * @author pfalcon
 * Portlet de Contenidos Relacionados
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + RelatedContentsConstants.RELATED_CONTENTS_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RelatedContentsPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<String> journalArticleDisplayList = new ArrayList<String>();
			//Portlet preferences
			PortletPreferences portletPreferences = renderRequest.getPreferences();
			//Origin
			String originType = GetterUtil.getString(portletPreferences.getValue("originType", null), "web_content_url");
			//Obtains the origin journal article
			JournalArticle displayedJournalArticle = null;
			//Current URL web content display
			if (originType.equals("web_content_url")) {
				displayedJournalArticle = RelatedContentsUtil.getJournalArticleByContextUrl(themeDisplay.getScopeGroupId(), themeDisplay.getURLCurrent());
			}
			//Web content display journal article
			else {
				String layoutColumnId = GetterUtil.getString(portletPreferences.getValue("layoutColumnId", null), "column-1");
				displayedJournalArticle = RelatedContentsUtil.getJournalArticleDisplayedInPage(themeDisplay.getLayout(), layoutColumnId);	
			}			
			if (displayedJournalArticle != null) {
				//Se obtienen el resto de preferencias
				String groupFilterType = GetterUtil.getString(portletPreferences.getValue("groupFilterType", null), "current");
				long[] groupIds = null;
				//Grupo actual
				if (groupFilterType.equals(RelatedContentsConstants.GROUP_FILTER_TYPE_CURRENT)) {
					groupIds = new long[1];
					groupIds[0] = themeDisplay.getScopeGroupId();
				} 
				//Selección manual de grupos
				else if (groupFilterType.equals(RelatedContentsConstants.GROUP_FILTER_TYPE_MANUAL)) {
					groupIds = GetterUtil.getLongValues(portletPreferences.getValues("groupIds", null), new long[0]);
				}
				String structureFilterType = GetterUtil.getString(portletPreferences.getValue("structureFilterType", null), "sameAsOrigin");
				long[] structureIds = null;
				//Seleccion manual de estructuras
				if (structureFilterType.equals(RelatedContentsConstants.STRUCTURE_FILTER_TYPE_MANUAL)) {
					structureIds = GetterUtil.getLongValues(portletPreferences.getValues("structureIds", null), new long[0]);
				} 
				//Misma estructura que el origen
				else if (structureFilterType.equals(RelatedContentsConstants.STRUCTURE_FILTER_TYPE_SAME_AS_ORIGIN)) {
					DDMStructure originStructure = displayedJournalArticle.getDDMStructure();
					if (originStructure != null) {
						structureIds = new long[1];
						structureIds[0] = displayedJournalArticle.getDDMStructure().getStructureId();
					}
				}
				String categoryFilterType = GetterUtil.getString(portletPreferences.getValue("categoryFilterType", null), "any");
				String orderByCol = GetterUtil.getString(portletPreferences.getValue("orderByCol", null), "publishDate");
				String orderByType = GetterUtil.getString(portletPreferences.getValue("orderByType", null), "desc");
				int resultsNumber =  GetterUtil.getInteger(portletPreferences.getValue("resultsNumber", null), 3);
				String templateKey = GetterUtil.getString(portletPreferences.getValue("templateKey", null), "NOTICIA_LISTADO_SIMPLE");
				//Se obtienen las categorías del artículo
				long[] displayedArticleCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(JournalArticle.class.getName(), displayedJournalArticle.getResourcePrimKey());
				//Se obtiene un artículo más del configurado por si viene el propio resultado en la consulta
				List<JournalArticle> journalArticleList = RelatedContentsUtil.getFilteredJournalArticles(displayedArticleCategoryIds, categoryFilterType, orderByCol, orderByType, structureIds, groupIds, 0, resultsNumber + 1);
				//Se obtiene el html de los resultados obtenidos según la plantilla de visualización configurada.
				if (Validator.isNotNull(journalArticleList)) {
					int contador = 0;
					for (JournalArticle journalArticle : journalArticleList) {
						if (journalArticle.getArticleId() != displayedJournalArticle.getArticleId() && contador < resultsNumber) {
							journalArticleDisplayList.add(RelatedContentsUtil.getJournalArticleHtml(renderRequest, renderResponse, journalArticle, templateKey, themeDisplay));
							contador++;
						}
					}
				}
				//Pasar el listado de resultados html a la vista
				renderRequest.setAttribute("journalArticleDisplayList", journalArticleDisplayList);
				//Ocultar el portlet si no hay resultados
				if (journalArticleDisplayList == null || journalArticleDisplayList.isEmpty()) {
					renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
				}
			} else {
				_log.warn("No se ha encontrado un contenido web de origen");
			}
			//Se pasa el listado de resultados a la vista 
			renderRequest.setAttribute("journalArticleDisplayList", journalArticleDisplayList);
			//Se oculta el portlet en caso de no haber resultados
			if (journalArticleDisplayList == null || journalArticleDisplayList.isEmpty()) {
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			}
		} catch (Exception e) {
			_log.error("Ha habido un error al obtener los contenidos relacionados: " + e.toString());
			e.printStackTrace();
		}
		super.render(renderRequest, renderResponse);
	}
	
	/**
	 * Log de la clase
	 */
	private final Log _log = LogFactoryUtil.getLog(RelatedContentsPortlet.class);
	
}