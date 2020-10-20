package es.aragon.enlinea.procedure.web.portlet.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.db.connection.api.EnlineaDBService;
import es.aragon.enlinea.db.connection.dao.Procedure;
import es.aragon.enlinea.procedure.web.constants.EnlineaProcedurePortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaProcedurePortletKeys.ENLINEA_PROCEDURE_WEB_PORTLET,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class ProcedureDetailMVCRenderCommand implements MVCRenderCommand {
	
	private static Log log = LogFactoryUtil.getLog(ProcedureDetailMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		String friendlyURL = ParamUtil.getString(renderRequest, "friendlyURL", "");
		if(!friendlyURL.isEmpty()) {			
			Procedure procedure = enlineaDBService.getProcedure(httpRequest, friendlyURL);
			if(Validator.isNotNull(procedure)) {
				PortalUtil.setPageTitle(procedure.getName(), httpRequest);
				if(Validator.isNotNull(procedure.getKeywords()) && !procedure.getKeywords().isEmpty()) {
					procedure.setRelatedProcedures(enlineaDBService.getRelatedProcedures(httpRequest, procedure.getProcedureId(), procedure.getKeywords()));
				}else {
					procedure.setRelatedProcedures(enlineaDBService.getRelatedProcedures(httpRequest, procedure.getProcedureId(), procedure.getName()));
				}
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				if(!procedure.getRelatedProcedures().isEmpty()) {
					String portletId = "es_aragon_enlinea_procedure_web_portlet_EnlineaProcedurePortlet";
				    try {
						long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), portletId);
						renderRequest.setAttribute("portletNameProcedureWeb", portletId);
						renderRequest.setAttribute("plidProcedureWeb", plid);
					} catch (PortalException e) {
						log.error("Error getting default plid of portlet " + portletId);
					}
				}
				Map<String, String> breadcrumb = getBreadcrumb(procedure.getCategoryIds(), themeDisplay);
				renderRequest.setAttribute("breadcrumbs", breadcrumb);
				countProcedureView(renderRequest.getPortletSession(), themeDisplay, procedure);
				String procedureJSONLD = getProcedureJSONLD(procedure, themeDisplay.getPortalURL() + themeDisplay.getURLCurrent());
				Map<Locale, String> moreInformationMap = LocalizationUtil.getLocalizationMap(renderRequest.getPreferences(), "moreInformation");
				String moreInformation = "";
				if(moreInformationMap.containsKey(themeDisplay.getLocale())) {
					moreInformation = moreInformationMap.get(themeDisplay.getLocale());
					moreInformation = moreInformation.replace("[$PROCEDUREID$]", String.valueOf(procedure.getProcedureId()));
				} 
				if(moreInformation.isEmpty() && moreInformationMap.containsKey(LocaleUtil.getDefault())) {
					moreInformation = moreInformationMap.get(LocaleUtil.getDefault());
					moreInformation = moreInformation.replace("[$PROCEDUREID$]", String.valueOf(procedure.getProcedureId()));
				}
				renderRequest.setAttribute("moreInformation", moreInformation);
				renderRequest.setAttribute("procedureJSONLD", procedureJSONLD);
			}
			renderRequest.setAttribute("procedure", procedure);
		}
		return "/view.jsp";
	}
	
	private Map<String, String> getBreadcrumb(List<Long> categories, ThemeDisplay themeDisplay) {
		Map<String, String> breadcrumb = new LinkedHashMap<>();
		long level = -1;
		AssetCategory category = null;
		AssetVocabulary assetVocabulary = assetVocabularyLocalService.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), "Temas");
		if(Validator.isNotNull(assetVocabulary)) {
			for(long categoryId : categories) {
				try {
					AssetCategory assetCategory = assetCategoryLocalService.fetchAssetCategory(categoryId);
					if(Validator.isNotNull(assetCategory) 
							&& assetCategory.getVocabularyId() == assetVocabulary.getVocabularyId()
							&& assetCategory.getAncestors().size() > level) {
						level = assetCategory.getAncestors().size();
						category = assetCategory;
					}
				} catch(PortalException e) {
					log.error("Error obtaining category " + categoryId + " ancestors");
				}
			}
		}
		if(Validator.isNotNull(category)) {
			ExpandoTable expandoTable = expandoTableLocalService.fetchDefaultTable(themeDisplay.getCompanyId(), Layout.class.getName());
			if(Validator.isNotNull(expandoTable)) {
				ExpandoColumn expandoColumn = expandoColumnLocalService.getColumn(expandoTable.getTableId(), "topic-category-id");
				if(Validator.isNotNull(expandoColumn)) {
					DynamicQuery dinamicQuery = expandoValueLocalService.dynamicQuery()
							.add(RestrictionsFactoryUtil.eq("tableId", expandoTable.getTableId()))
							.add(RestrictionsFactoryUtil.eq("columnId", expandoColumn.getColumnId()))
							.add(RestrictionsFactoryUtil.eq("data", String.valueOf(category.getCategoryId())));
					List<ExpandoValue> values = expandoValueLocalService.dynamicQuery(dinamicQuery, 0, 1);
					if(!values.isEmpty()) {
						Layout layout = layoutLocalService.fetchLayout(values.get(0).getClassPK());
						if(Validator.isNotNull(layout)) {
							try {
								List<Layout> ancestors = layout.getAncestors();
								Collections.reverse(ancestors);
								for(Layout ancestor : ancestors) {
									breadcrumb.put(ancestor.getName(themeDisplay.getLocale()), ancestor.getFriendlyURL(themeDisplay.getLocale()));
								}
							} catch (PortalException e) {
								log.error("Error obtaining layout " + layout.getPlid() + " ancestors");
							}
							breadcrumb.put(layout.getName(themeDisplay.getLocale()), layout.getFriendlyURL(themeDisplay.getLocale()));
						}
					}
				}
			}
		}
		if(breadcrumb.isEmpty()) {
			String rootFriendlyURL = GetterUtil.getString(themeDisplay.getScopeGroup().getExpandoBridge().getAttribute("procedure-url", false), "");
			if(!rootFriendlyURL.isEmpty()) {
				Layout rootLayout = layoutLocalService.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, rootFriendlyURL);
				if(Validator.isNotNull(rootLayout)) {
					breadcrumb.put(rootLayout.getName(themeDisplay.getLocale()), rootLayout.getFriendlyURL(themeDisplay.getLocale()));
				}
			}
		}
		return breadcrumb;
	}
	
	private void countProcedureView(PortletSession portletSession, ThemeDisplay themeDisplay, Procedure procedure) {
		String attributeName = "viewedProcedure" + procedure.getProcedureId();
		if(Validator.isNull(portletSession.getAttribute(attributeName, PortletSession.PORTLET_SCOPE))) {
			ClassName className = classNameLocalService.fetchClassName(Procedure.class.getName());
			if(Validator.isNull(className)) {
				className = classNameLocalService.addClassName(Procedure.class.getName());
			}
			if(Validator.isNotNull(className)) {
				AssetEntry assetEntry = assetEntryLocalService.fetchEntry(className.getClassNameId(), procedure.getProcedureId());
				try {
					if(Validator.isNull(assetEntry)) {
						assetEntry = assetEntryLocalService.updateEntry(
								themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
								new Date(), new Date(),
								Procedure.class.getName(), procedure.getProcedureId(),
								null, 0, null, null, true, true,
								new Date(), null, null, null, 
								ContentTypes.TEXT_HTML,
								procedure.getName(),
								null, null, null, null, 0, 0, 0.0);
					}
					assetEntry.setViewCount(assetEntry.getViewCount() + 1);
					assetEntryLocalService.updateAssetEntry(assetEntry);
					portletSession.setAttribute(attributeName, "true", PortletSession.PORTLET_SCOPE);
				} catch(PortalException e) {
					log.error("Error creating assetEntry object for procedure " + procedure.getProcedureId(), e);
				}
			}
		}
	}

	private String getProcedureJSONLD(Procedure procedure, String currentURL) {
		StringBuilder procedureJSONLD = new StringBuilder("<script type=\"application/ld+json\">\r\n");
		procedureJSONLD.append("[{\r\n");
		procedureJSONLD.append("\"@context\":{\r\n");
		procedureJSONLD.append("\"ei2a\": \"http://opendata.aragon.es/def/e2ia#\",\r\n");
		procedureJSONLD.append("\"dct\": \"http://purl.org/dc/terms/\",\r\n");
		procedureJSONLD.append("\"owl\": \"http://www.w3.org/2002/07/owl#\"\r\n");
		procedureJSONLD.append("},\r\n");
		procedureJSONLD.append("\"@type\": \"ei2a:Procedure\",\n");
		procedureJSONLD.append("\"owl:sameAs\": {\r\n");
		procedureJSONLD.append("\"@id\" : \"" + currentURL + "\"\r\n");
		procedureJSONLD.append("},\r\n");
		procedureJSONLD.append("\"dct:title\": \"" + procedure.getName().replace("\"", "\'") + "\"\n");
		procedureJSONLD.append("},{\r\n");
		procedureJSONLD.append("\"@context\": \"http://schema.org/\",\r\n");
		procedureJSONLD.append("\"@type\": \"GovernmentService\",\r\n");
		procedureJSONLD.append("\"@id\" : \"" + currentURL + "\",\r\n");
		procedureJSONLD.append("\"name\": \"" + procedure.getName().replace("\"", "\'") + "\"\n");
		procedureJSONLD.append("}],\n");
		procedureJSONLD.append("</script>");
		return procedureJSONLD.toString();
	}
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
	@Reference
	private AssetVocabularyLocalService assetVocabularyLocalService;
	@Reference
	private AssetEntryLocalService assetEntryLocalService;
	@Reference
	private ClassNameLocalService classNameLocalService;
	@Reference
	private EnlineaDBService enlineaDBService;
	@Reference
	private ExpandoColumnLocalService expandoColumnLocalService;
	@Reference
	private ExpandoTableLocalService expandoTableLocalService;
	@Reference
	private ExpandoValueLocalService expandoValueLocalService;
	@Reference
	private LayoutLocalService layoutLocalService;
	
}
