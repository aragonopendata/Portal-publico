package es.aragon.base.journal.service.wrapper;

import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.journal.service.JournalFolderLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alex
 */
@Component(
	immediate = true,
	property = {
			"service.ranking:Integer=100"
	},
	service = ServiceWrapper.class
)
public class JournalFolderServiceWrapper extends JournalFolderLocalServiceWrapper{

	public JournalFolderServiceWrapper() {
		super(null);
	}
	
	public JournalFolderServiceWrapper(JournalFolderLocalService journalFolderLocalService) {
		super(journalFolderLocalService);
	}
	
	@Override
	public JournalFolder addFolder(long userId, long groupId, long parentFolderId, String name, String description, ServiceContext serviceContext) throws PortalException {
		JournalFolder journalFolder = super.addFolder(userId, groupId, parentFolderId, name, description, serviceContext);
		//Add access permission to guest role
		Role guest = RoleLocalServiceUtil.fetchRole(journalFolder.getCompanyId(), RoleConstants.GUEST);
		ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.fetchResourcePermission(journalFolder.getCompanyId(), JournalFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(journalFolder.getFolderId()), guest.getRoleId());
		if (resourcePermission != null && !resourcePermission.hasActionId(ActionKeys.ACCESS)) {
			_log.debug("Adding access permission to folder " + journalFolder.getFolderId());
			resourcePermission.addResourceAction(ActionKeys.ACCESS);
			ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
		}
		//Add create subfolder permission to site member role
		Role siteMember = RoleLocalServiceUtil.fetchRole(journalFolder.getCompanyId(), RoleConstants.SITE_MEMBER);
		ResourcePermission siteMemberResourcePermission = ResourcePermissionLocalServiceUtil.fetchResourcePermission(journalFolder.getCompanyId(), JournalFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(journalFolder.getFolderId()), siteMember.getRoleId());
		if (siteMemberResourcePermission != null && !siteMemberResourcePermission.hasActionId(ActionKeys.ADD_SUBFOLDER)) {
			_log.debug("Adding add subfolder permission to folder " + journalFolder.getFolderId());
			siteMemberResourcePermission.addResourceAction(ActionKeys.ADD_SUBFOLDER);
			ResourcePermissionLocalServiceUtil.updateResourcePermission(siteMemberResourcePermission);
		}
		return journalFolder;
	}
		
	/**
	 * Log of the class
	 */
	private Log _log = LogFactoryUtil.getLog(JournalFolderLocalServiceWrapper.class);
	
	/**
	 * Allows to find the appropriate service that is nullifying in the implementation.
	 * @param journalFolderLocalService Journal folder local service
	 */
	@Reference(unbind = "-")
	private void serviceSetter(JournalFolderLocalService journalFolderLocalService) {
	    setWrappedService(journalFolderLocalService);
	}
	
}
