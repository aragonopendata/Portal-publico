package es.aragon.base.resources.importer.portlet;

import es.aragon.base.resources.importer.constants.ResourcesImporterPortletKeys;
import es.aragon.base.resources.importer.util.ApplicationDisplayTemplateUtil;
import es.aragon.base.resources.importer.util.StructureUtil;
import es.aragon.base.resources.importer.util.TemplateUtil;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alex
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ResourcesImporterPortletKeys.ResourcesImporter,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ResourcesImporterPortlet extends MVCPortlet {
	
	public void updateStructuresSelected(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String[] structuresSelected = ParamUtil.getStringValues(actionRequest, "rowStructures");
		boolean existStructureSelected = structuresSelected.length == 1 && "false".equals(structuresSelected[0])? false: true;
		
		if(existStructureSelected) {
			StructureUtil.updateStructuresSelected(structuresSelected, themeDisplay);
		}
	}
	
	public void updateTemplatesSelected(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String[] templatesSelected = ParamUtil.getStringValues(actionRequest, "rowTemplates");
		boolean existTemplatesSelected = templatesSelected.length == 1 && "false".equals(templatesSelected[0])? false: true;
		
		if(existTemplatesSelected) {
			TemplateUtil.updateTemplatesSelected(templatesSelected, themeDisplay);
		}
	}
	
	public void updateAdtTemplatesSelected(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String[] adtTemplatesSelected = ParamUtil.getStringValues(actionRequest, "rowAdtTemplates");
		boolean existAdtTemplatesSelected = adtTemplatesSelected.length == 1 && "false".equals(adtTemplatesSelected[0])? false: true;
		
		if(existAdtTemplatesSelected) {
			ApplicationDisplayTemplateUtil.updateTemplatesSelected(adtTemplatesSelected, themeDisplay);
		}
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		
		List<DDMStructure> ddmStructures = StructureUtil.getDDMStructures(groupId);
		List<DDMTemplate> ddmTemplates = TemplateUtil.getDDMTemplates(groupId);
		List<DDMTemplate> adtTemplates = ApplicationDisplayTemplateUtil.getAdtTemplates(groupId);
		
		renderRequest.setAttribute("structuresList", ddmStructures);
		renderRequest.setAttribute("templatesList", ddmTemplates);
		renderRequest.setAttribute("adtTemplatesList", adtTemplates);
		
		renderRequest.setAttribute(DDMTemplateLocalService.class.getName(), _ddmTemplateLocalService);
		renderRequest.setAttribute(DDMStructureLocalService.class.getName(), _ddmStructureLocalService);
		
		super.render(renderRequest, renderResponse);
	}
	
	@Reference(unbind = "-")
	private ClassNameLocalService _classNameLocalService;
	
	@Reference(unbind = "-")
	private DDMStructureLocalService _ddmStructureLocalService;
	
	@Reference(unbind = "-")
	private DDMTemplateLocalService _ddmTemplateLocalService;
}