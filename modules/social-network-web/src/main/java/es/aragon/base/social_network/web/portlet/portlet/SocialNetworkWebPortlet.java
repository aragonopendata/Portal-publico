package es.aragon.base.social_network.web.portlet.portlet;

import es.aragon.base.social_network.sb.model.SocialNetwork;
import es.aragon.base.social_network.sb.service.SocialNetworkLocalService;
import es.aragon.base.social_network.web.portlet.constants.SocialNetworkWebPortletKeys;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.display-category=category.hidden",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SocialNetworkWebPortletKeys.SOCIAL_NETWORK_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class SocialNetworkWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long groupId = themeDisplay.getScopeGroupId();
		
		List<SocialNetwork> socialNetworkList = _socialNetworkLocalService.getSocialNetworksByCompanyIdAndGroupId(companyId, groupId);
		if(Validator.isNull(socialNetworkList)) {
			socialNetworkList = new ArrayList<SocialNetwork>();
		}
		renderRequest.setAttribute("socialNetworkList", socialNetworkList);
		renderRequest.setAttribute(SocialNetworkLocalService.class.getName(), _socialNetworkLocalService);
		
		super.render(renderRequest, renderResponse);
	}
	
	public static String getFullUrl(ThemeDisplay themeDisplay, String url) {
		String title = StringPool.BLANK;
		if(url.contains("[$URL$]")) {
			url = url.replace("[$URL$]", themeDisplay.getPortalURL() + HtmlUtil.escapeURL(themeDisplay.getURLCurrent()));
		}
		if(url.contains("[$TITLE$]")) {
			String urlTitle = themeDisplay.getURLCurrent().substring(3);
			JournalArticle titleJournal = JournalArticleLocalServiceUtil.fetchArticleByUrlTitle(themeDisplay.getScopeGroupId(), urlTitle);
			if( Validator.isNotNull(titleJournal)) {
				title = titleJournal.getTitle(themeDisplay.getLocale());
			}else {
				title = themeDisplay.getLayout().getName(themeDisplay.getLocale());
			}
			url = url.replace("[$TITLE$]", HtmlUtil.escapeURL(title));
			try {
				URLEncoder.encode(url, "UTF-8").replace("+", "%20");
			}catch (Exception e) {
				_log.error(e);
			}
		}
		return url;
	}
	
	@Reference(unbind = "-")
	private SocialNetworkLocalService _socialNetworkLocalService;
	
	private static final Log _log = LogFactoryUtil.getLog(SocialNetworkWebPortlet.class.getName());
}
