import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

long companyId = 20100;
long groupId = 20127;
DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFolder.class);
dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
List<DLFolder> dlFolderList = DLFolderLocalServiceUtil.dynamicQuery(dynamicQuery);
Role memberRole = RoleLocalServiceUtil.fetchRole(companyId, RoleConstants.SITE_MEMBER);
for (DLFolder dlFolder : dlFolderList) {
	ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.fetchResourcePermission(companyId, DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlFolder.getFolderId()), memberRole.getRoleId());	
	if (resourcePermission != null ) {
		if (!resourcePermission.hasActionId(ActionKeys.UPDATE)) {
			resourcePermission.addResourceAction(ActionKeys.UPDATE);
			ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
			out.println("Permiso de modificar para site member otorgado al dlfolder " + dlFolder.getFolderId());
		}
	}
}