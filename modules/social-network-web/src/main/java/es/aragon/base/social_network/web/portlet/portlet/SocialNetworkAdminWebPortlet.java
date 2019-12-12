package es.aragon.base.social_network.web.portlet.portlet;

import es.aragon.base.social_network.sb.model.SocialNetwork;
import es.aragon.base.social_network.sb.service.SocialNetworkLocalService;
import es.aragon.base.social_network.web.portlet.constants.SocialNetworkWebPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.display-category=category.hidden",
		"javax.portlet.init-param.template-path=/admin/",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + SocialNetworkWebPortletKeys.SOCIAL_NETWORK_ADMIN_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class SocialNetworkAdminWebPortlet extends MVCPortlet {
	
	public void addSocialNetwork(ActionRequest actionRequest, ActionResponse actionResponse){
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		long groupId = themeDisplay.getScopeGroupId();
		long companyId = themeDisplay.getCompanyId();
		String url = ParamUtil.getString(actionRequest, "url");
		String alt = ParamUtil.getString(actionRequest, "alt");
		String extraParameters = ParamUtil.getString(actionRequest, "extraParameters");
		User user = themeDisplay.getUser();
		boolean success = true;
		
		long socialNetworkId = _counterLocalService.increment(SocialNetwork.class.getName());
		SocialNetwork socialNetwork = _socialNetworkLocalService.createSocialNetwork(socialNetworkId);
		socialNetwork.setCompanyId(companyId);
		socialNetwork.setGroupId(groupId);
		socialNetwork.setUserId(userId);
		socialNetwork.setUrl(url);
		socialNetwork.setAlt(alt);
		socialNetwork.setCreateDate(new Date());
		socialNetwork.setModifiedDate(new Date());
		
		if(Validator.isNotNull(user)){
			socialNetwork.setUserName(user.getFullName());
		}
		
		Pattern p = Pattern.compile(pattern);
		if(!extraParameters.isEmpty()){
			if(p.matcher(extraParameters).matches()){
				socialNetwork.setExtraParameters(extraParameters);
			}else{
				SessionErrors.add(actionRequest, "social-network-web.incorrect-extra-parameters-message");
				success = false;
			}
		}
		
		if(success){
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			File imageFile = uploadPortletRequest.getFile("smallFile");
			long imageId = _counterLocalService.increment(Image.class.getName());
			try {
				_imageLocalService.updateImage(imageId, imageFile);
				socialNetwork.setImageId(imageId);
				_socialNetworkLocalService.addSocialNetwork(socialNetwork);
			} catch (PortalException e) {
				_log.error(e);
			}
			SessionMessages.add(actionRequest, "social-network-web.saved-successfully-message");
		}
	}
	
	public void updateSocialNetwork(ActionRequest actionRequest, ActionResponse actionResponse){
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		long groupId = themeDisplay.getScopeGroupId();
		long companyId = themeDisplay.getCompanyId();
		String url = ParamUtil.getString(actionRequest,"url");
		String alt = ParamUtil.getString(actionRequest,"alt");
		String extraParameters = ParamUtil.getString(actionRequest, "extraParameters");
		User user = themeDisplay.getUser();
		boolean success = true;
		
		long socialNetworkId = ParamUtil.getLong(actionRequest, "socialNetworkId");
		SocialNetwork socialNetwork = _socialNetworkLocalService.fetchSocialNetwork(socialNetworkId);
		
		if(Validator.isNotNull(socialNetwork)){
			
			long imageId = socialNetwork.getImageId();
			
			socialNetwork.setCompanyId(companyId);
			socialNetwork.setGroupId(groupId);
			socialNetwork.setUserId(userId);
			socialNetwork.setUrl(url);
			socialNetwork.setAlt(alt);
			
			if(Validator.isNotNull(user)){
				socialNetwork.setUserName(user.getFullName());
			}
			
			Pattern p = Pattern.compile(pattern);
			if(!extraParameters.isEmpty()){
				if(p.matcher(extraParameters).matches()){
					socialNetwork.setExtraParameters(extraParameters);
				}else{
					SessionErrors.add(actionRequest, "social-network-web.incorrect-extra-parameters-message");
					success = false;
				}
			}else{
				socialNetwork.setExtraParameters(extraParameters);
			}

			if(success){
				UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
				File imageFile = uploadPortletRequest.getFile("smallFile");
				
				try {
					if(Validator.isNotNull(imageFile) && imageFile.isFile()){
						socialNetwork.setImageId(imageId);
						_imageLocalService.updateImage(imageId, imageFile);
					}
					_socialNetworkLocalService.updateSocialNetwork(socialNetwork);
					
					SessionMessages.add(actionRequest, "social-network-web.updated-successfully-updated-message");
				} catch (PortalException e) {
					_log.error(e);
				}
			}
		}
	}

	public void deleteSocialNetwork(ActionRequest actionRequest, ActionResponse actionResponse){
		
		long socialNetworkId = ParamUtil.getLong(actionRequest, "socialNetworkId");
		SocialNetwork socialNetwork = _socialNetworkLocalService.fetchSocialNetwork(socialNetworkId);
		if(Validator.isNotNull(socialNetwork)){
			try {
				// Borramos la imagen asociada a la red social
				long imageId = socialNetwork.getImageId();
				_imageLocalService.deleteImage(imageId);
				
				// Borramos la red social
				_socialNetworkLocalService.deleteSocialNetwork(socialNetworkId);
				
				SessionMessages.add(actionRequest, "social-network-web.removed-successfully-message");
			}catch (Exception e) {
				_log.error(e);
			}
		}
	}
	
	public void goEditForm(ActionRequest actionRequest, ActionResponse actionResponse) {
		
		long socialNetworkId = ParamUtil.getLong(actionRequest, "socialNetworkId");
		SocialNetwork socialNetwork = _socialNetworkLocalService.fetchSocialNetwork(socialNetworkId);
		if(Validator.isNotNull(socialNetwork)){
			actionRequest.setAttribute("socialNetworkId", socialNetworkId);
			actionResponse.setRenderParameter("mvcPath", "/admin/edit_social_network.jsp");
		}
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long groupId = themeDisplay.getScopeGroupId();
		
		List<SocialNetwork> socialNetworkList = _socialNetworkLocalService.getSocialNetworksByCompanyIdAndGroupId(companyId, groupId);

		renderRequest.setAttribute("socialNetworkList", socialNetworkList);
		renderRequest.setAttribute(SocialNetworkLocalService.class.getName(), _socialNetworkLocalService);
		
		super.render(renderRequest, renderResponse);
	}

	@Reference(unbind = "-")
	private CounterLocalService _counterLocalService;
	
	@Reference(unbind = "-")
	private SocialNetworkLocalService _socialNetworkLocalService;
	
	@Reference(unbind = "-")
	private ImageLocalService _imageLocalService;
	
	private static String pattern = "(([^\\s,=]+=[^\\s,=]+)(?:,\\s*)?)+";
	
	private static final Log _log = LogFactoryUtil.getLog(SocialNetworkAdminWebPortlet.class.getName());
}