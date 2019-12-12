package es.aragon.base.migration.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.migration.constants.AragonMigrationPortletKeys;

/**
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/previewPortlet/view.jsp",
		"javax.portlet.name=" + AragonMigrationPortletKeys.PREVIEW_PORTLET_KEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PreviewPortlet extends MVCPortlet {

}
