package es.aragon.base.startup_bundle_activator;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import es.aragon.base.startup_bundle_activator.constants.StartupBundleActivatorConstants;
import es.aragon.base.startup_bundle_activator.util.AdtUtil;
import es.aragon.base.startup_bundle_activator.util.CustomFieldUtil;
import es.aragon.base.startup_bundle_activator.util.StartupBundleActivatorUtil;
import es.aragon.base.startup_bundle_activator.util.StructureUtil;
import es.aragon.base.startup_bundle_activator.util.TemplateUtil;

/**
 * @author pfalcon
 * Al activarse este módulo, se ejecuta la función start, que cargará según la configuración una serie de datos en el portal como estructuras, plantillas, etc.
 */
public class StartupBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		/*
		_log.info("Inicio de la ejecucion del startup");
		//Obtención del ServiceContext
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAddGroupPermissions(true);
		//Obtención de la configuración
		String importStructuresCompanies = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_STRUCTURES_COMPANIES);
		String importStructuresSites = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_STRUCTURES_SITES);
		String importTemplatesCompanies = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_TEMPLATES_COMPANIES);
		String importTemplatesSites = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_TEMPLATES_SITES);
		String importAdtsCompanies = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_ADTS_COMPANIES);
		String importAdtsSites = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_ADTS_SITES);
		String importCustomFieldsCompanies = _configuration.get(StartupBundleActivatorConstants.STARTUP_IMPORT_CUSTOM_FIELDS_COMPANIES);
		//Iteración de todas las instancias del portal
		List<Company> companyList = CompanyLocalServiceUtil.getCompanies();
		for (Company company : companyList) {
			long companyId = company.getCompanyId();
			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
			//Importación de estructuras
			if (StartupBundleActivatorUtil.applyToCurrentCompanyId(company, importStructuresCompanies)) {
				_log.info("Inicio de la importacion de estructuras para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
				long groupId = StartupBundleActivatorUtil.getGroupIdByConfig(company.getCompanyId(), importStructuresSites);
				if (groupId > 0) {
					_log.info("Inicio - Group " + groupId);
					StructureUtil.addDDMStructures(defaultUserId, groupId, serviceContext);
					_log.info("Fin - Group " + groupId);
				}
				_log.info("Fin de la importacion de estructuras para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
			}
			//Importación de plantillas
			if (StartupBundleActivatorUtil.applyToCurrentCompanyId(company, importTemplatesCompanies)) {
				_log.info("Inicio de la importacion de plantillas para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
				long groupId = StartupBundleActivatorUtil.getGroupIdByConfig(company.getCompanyId(), importTemplatesSites);
				if (groupId > 0) {
					_log.info("Inicio - Group " + groupId);
					TemplateUtil.addDDMTemplates(defaultUserId, groupId, serviceContext);
					_log.info("Fin - Group " + groupId);
				}
				_log.info("Fin de la importacion de plantillas para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
	        }
			//Importación de adts
			if (StartupBundleActivatorUtil.applyToCurrentCompanyId(company, importAdtsCompanies)) {
				_log.info("Inicio de la importacion de adts para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
				long groupId = StartupBundleActivatorUtil.getGroupIdByConfig(company.getCompanyId(), importAdtsSites);
				if (groupId > 0) {
					_log.info("Inicio - Group " + groupId);
					AdtUtil.addAdts(defaultUserId, groupId, serviceContext);
					_log.info("Fin - Group " + groupId);
				}
				_log.info("Fin de la importacion de adts para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
			}
			//Importación de campos personalizados
			if (StartupBundleActivatorUtil.applyToCurrentCompanyId(company, importCustomFieldsCompanies)) {
				_log.info("Inicio de la importacion de campos personalizados para la instancia " + company.getName() + " (" + company.getVirtualHostname() + ")");
				CustomFieldUtil.addCustomFields(companyId);
			}
		}
		_log.info("Fin de la ejecucion del startup");
		*/
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {

	}
	
	/**
	 * Log de la clase
	 */
	private final Log _log = LogFactoryUtil.getLog(StartupBundleActivator.class);
	
	/**
	 *  Configuración obtenida del portlet.properties del módulo
	 */
    private static final Configuration _configuration = ConfigurationFactoryUtil.getConfiguration(StartupBundleActivator.class.getClassLoader(), "portlet");
	
}