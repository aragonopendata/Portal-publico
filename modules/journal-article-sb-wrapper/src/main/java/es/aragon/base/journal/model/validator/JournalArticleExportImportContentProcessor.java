package es.aragon.base.journal.model.validator;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author jjorge
 * Sobreescrito servicio de validacion de content para los JournalArticle para que a la hora de guardar no valide el contenido.
 */
@Component(
	property = {
		"model.class.name=com.liferay.journal.model.JournalArticle",
		"service.ranking:Integer=-1"
	},
	service = {
		ExportImportContentProcessor.class
	}
)
public class JournalArticleExportImportContentProcessor implements ExportImportContentProcessor<String> {

	@Override
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content, boolean exportReferencedContent,
			boolean escapeContent)
		throws Exception {
		
		return content;
	}

	@Override
	public String replaceImportContentReferences(PortletDataContext portletDataContext, StagedModel stagedModel,
			String content) throws Exception {
		
		return content;
	}

	@Override
	public void validateContentReferences(long groupId, String content) throws PortalException {
		
	}
	
	//Load service after portal is initialized
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
		
	}
	
}
