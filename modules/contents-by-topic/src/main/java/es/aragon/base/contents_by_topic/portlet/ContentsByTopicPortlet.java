package es.aragon.base.contents_by_topic.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.contents_by_topic.constants.ContentsByTopicConstants;
import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;

/** 
 * Contents by topic portlet class
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + ContentsByTopicConstants.PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ContentsByTopicPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) {
		try {
			renderRequest.setAttribute("freemarkerUtilities", _freemarkerUtilities);			
			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			LOG.error(e, e);
		}
	}

	/**
	 * Log of the class
	 */
	private final Log LOG = LogFactoryUtil.getLog(ContentsByTopicPortlet.class);

	@Reference
	FreemarkerUtilities _freemarkerUtilities;

}