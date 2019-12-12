package es.aragon.base.crawler.service.permission;

import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import es.aragon.base.crawler.model.Page;

/**
 * 
 * @author Mikel Jorge
 *
 */
public interface PagePermissionChecker {

	public static final String ADD_PAGE = "ADD_PAGE";
	public static final String TOP_LEVEL_RESOURCE = "es.aragon.base.crawler";
	public static final String RESOURCE_NAME = Page.class.getName();
	
	public boolean containsTopLevel(PermissionChecker permissionChecker, long groupId, String actionId);
	
	public boolean contains(PermissionChecker permissionChecker, long groupId, long pageId, String actionId);
	
	public void check(PermissionChecker permissionChecker, long groupId, long pageId, String actionId)
			throws AuthException;
}
