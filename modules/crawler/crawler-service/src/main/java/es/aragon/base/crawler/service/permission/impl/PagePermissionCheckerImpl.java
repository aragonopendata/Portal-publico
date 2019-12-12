package es.aragon.base.crawler.service.permission.impl;

import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

import es.aragon.base.crawler.constants.PageConstants;
import es.aragon.base.crawler.service.permission.PagePermissionChecker;

/**
 * 
 * @author Mikel Jorge
 *
 */
@Component(
	immediate = true,
	property = {
		"model.class.name=" + PageConstants.RESOURCE_NAME
	},
	service = PagePermissionChecker.class 
)
public class PagePermissionCheckerImpl implements PagePermissionChecker {

	@Override
	public boolean containsTopLevel(PermissionChecker permissionChecker, long groupId, String actionId) {
		return permissionChecker.hasPermission(groupId, TOP_LEVEL_RESOURCE, groupId, actionId);
	}
	
	@Override
	public boolean contains(PermissionChecker permissionChecker, long groupId, long pageId, String actionId) {
		return permissionChecker.hasPermission(groupId, RESOURCE_NAME, pageId, actionId);
	}
	
	@Override
	public void check(PermissionChecker permissionChecker, long groupId, long pageId, String actionId)
			throws AuthException {
		
		if (!contains(permissionChecker, groupId, pageId, actionId)) {
			throw new AuthException();
		}
	}
}
