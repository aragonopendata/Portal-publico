package es.aragon.base.portal.utilities.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;
import es.aragon.base.semaphore.model.Semaphore;
import es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil;


@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES,
	        "mvc.command.name=" + AragonPortalUtilitiesPortletKeys.ROUTE_CLEAN_SEMAPHORE
	    },
	    service = MVCActionCommand.class
	)
public class AragonPortalUtilitiesSemaphoreCleanActionCommand extends BaseMVCActionCommand {

	
	@Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		long semaphoreId = ParamUtil.getLong(actionRequest, "semaphoreId");
		Semaphore semaphore = SemaphoreLocalServiceUtil.getSemaphore(semaphoreId);
		
		semaphore.setCurrentUsers(0L);
		SemaphoreLocalServiceUtil.updateSemaphore(semaphore);
	}
}
