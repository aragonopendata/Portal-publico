package es.aragon.base.aragon.calendar.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import com.liferay.portal.kernel.theme.ThemeDisplay;

import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import es.aragon.base.aragon.calendar.constants.CalendarConstants;
import es.aragon.base.aragon.calendar.utils.CalendarPortletUtils;
import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;

/**
 * @author Isabel Garcia Torrijo
 */
@Component(immediate = true, property = { "javax.portlet.name=" + CalendarConstants.CALENDAR_PORTLET_KEY,
		"mvc.command.name=/" }, service = MVCRenderCommand.class)

public class ViewCalendarEventsListMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		addCalendarEventListAttributes(renderRequest);
		return "/calendar/view.jsp";
	}

	/**
	 * Adds Calendar event list attributes
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addCalendarEventListAttributes(RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Date startDate = new Date();
		Date endDate = null;
		// Filter structure
		DDMStructure ddmStructure = CalendarPortletUtils.fetchStructureByName(
				AragonUtilitiesConstant.STRUCTURE_NAME_CONTENIDO_FINAL, themeDisplay.getScopeGroupId());
		long structureId = 0;
		if (Validator.isNotNull(ddmStructure)) {
			structureId = ddmStructure.getStructureId();
		}
		// Filter category
		AssetCategory assetCategoryEvent = null;
		AssetVocabulary vocabularyDocument = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(
				themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_DOCUMENT_TYPE_ES);
		List<Long> mustContainCategoryIds = new ArrayList<Long>();
		List<Long> mustNotContainCategoryIds = new ArrayList<Long>();
		// Add "agenda" category
		if (Validator.isNotNull(vocabularyDocument)) {
			assetCategoryEvent = AssetCategoryLocalServiceUtil.fetchCategory(themeDisplay.getScopeGroupId(), 0,
					AragonUtilitiesConstant.CATEGORY_NAME_AGENDA, vocabularyDocument.getVocabularyId());
			if (Validator.isNotNull(assetCategoryEvent)) {
				mustContainCategoryIds.add(assetCategoryEvent.getCategoryId());
			}
		}
		//Get list events
		List<JournalArticle> events = CalendarPortletUtils.getFilteredJournalArticles(themeDisplay.getRequest(),
				themeDisplay.getScopeGroupId(), structureId, mustContainCategoryIds, mustNotContainCategoryIds, 100, startDate, endDate);
		JSONArray jsonArray = CalendarPortletUtils.getEventsJSONArray(events, TimeZone.getTimeZone("Europe/Paris"),
				portal.getOriginalServletRequest(portal.getHttpServletRequest(renderRequest)));
		
		renderRequest.setAttribute("events", jsonArray);
	}

	@Reference
	private Portal portal;

	private static final Log logger = LogFactoryUtil.getLog(ViewCalendarEventsListMVCRenderCommand.class);

}
