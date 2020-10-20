package es.aragon.enlinea.admin.web.portlet.configuration.icon;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;

import es.aragon.enlinea.admin.web.constants.EnlineaAdminPortletKeys;

/**
 * @author Asier Guillo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + EnlineaAdminPortletKeys.ENLINEA_ADMIN_WEB_PORTLET
	},
	service = PortletConfigurationIcon.class
)
public class DeleteProceduresPortletConfigurationIcon extends BasePortletConfigurationIcon {

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		return true;
	}
	
	@Override
	public String getURL(PortletRequest portletRequest, PortletResponse portletResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletURL actionUrl =  PortletURLFactoryUtil.create(portletRequest, themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE);
		try {
			actionUrl.setWindowState(LiferayWindowState.NORMAL);
		} catch (WindowStateException e) {
			e.printStackTrace();
		}
		try {
			actionUrl.setPortletMode(LiferayPortletMode.VIEW);
		} catch (PortletModeException e) {
			e.printStackTrace();
		}
		actionUrl.setParameter("javax.portlet.action", EnlineaAdminPortletKeys.ACTION_DELETE_PROCEDURES);
		return actionUrl.toString();
	}
	
	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)),
			"delete-procedures");
	}
	
	@Override
	public ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return new AggregateResourceBundle(
			resourceBundle, super.getResourceBundle(locale));
	}

}
