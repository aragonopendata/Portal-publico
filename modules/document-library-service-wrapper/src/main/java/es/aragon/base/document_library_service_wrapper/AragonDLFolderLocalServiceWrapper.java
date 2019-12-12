package es.aragon.base.document_library_service_wrapper;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceWrapper;
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
 * Aragon custom document library folder service wrapper
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
		"service.ranking:Integer=100"
	},
	service = ServiceWrapper.class
)
public class AragonDLFolderLocalServiceWrapper extends DLFolderLocalServiceWrapper {

	/**
	 * Empty constructor
	 */
	public AragonDLFolderLocalServiceWrapper() {
		super(null);
	}
	
	/**
	 * Constructor with parameters
	 * @param dlFolderLocalService Document library folder local service instance
	 */
	public AragonDLFolderLocalServiceWrapper(DLFolderLocalService dlFolderLocalService) {
		super(dlFolderLocalService);
	}
	
	@Override
	public DLFolder addFolder(long userId, long groupId, long repositoryId, boolean mountPoint, long parentFolderId, String name, String description, boolean hidden, ServiceContext serviceContext) throws PortalException {
		DLFolder dlFolder = super.addFolder(userId, groupId, repositoryId, mountPoint, parentFolderId, name, description, hidden, serviceContext);
		//Add edit permission to sitemember role
		Role siteMember = RoleLocalServiceUtil.fetchRole(dlFolder.getCompanyId(), RoleConstants.SITE_MEMBER);
		ResourcePermission siteMemberResourcePermission = ResourcePermissionLocalServiceUtil.fetchResourcePermission(dlFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlFolder.getFolderId()), siteMember.getRoleId());
		if (!siteMemberResourcePermission.hasActionId(ActionKeys.UPDATE)) {
			LOG.debug("Adding edit permission to folder " + dlFolder.getFolderId());
			siteMemberResourcePermission.addResourceAction(ActionKeys.UPDATE);
			ResourcePermissionLocalServiceUtil.updateResourcePermission(siteMemberResourcePermission);
		}
		return dlFolder;
	}
	
	/**
	 * Log of the class
	 */
	private static final Log LOG = LogFactoryUtil.getLog(AragonDLFolderLocalServiceWrapper.class.getName());
	
	/**
	 * Allows to find the appropriate service that is nullifying in the implementation.
	 * @param dlFolderLocalService DLFolder local service
	 */
	@Reference(unbind = "-")
	private void serviceSetter(DLFolderLocalService dlFolderLocalService) {
	    setWrappedService(dlFolderLocalService);
	}

}