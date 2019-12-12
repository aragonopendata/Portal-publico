package es.aragon.base.search.web.friendlyurl;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.search.web.constants.SearchWebPortletKeys;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
		property = {
			"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
			"javax.portlet.name=" + SearchWebPortletKeys.SEARCH_WEB
		},
		service = FriendlyURLMapper.class
)
public class SearchFriendlyURLMapper extends DefaultFriendlyURLMapper{

	@Override
	public String getMapping() {
		return "search";
	}
	
}
