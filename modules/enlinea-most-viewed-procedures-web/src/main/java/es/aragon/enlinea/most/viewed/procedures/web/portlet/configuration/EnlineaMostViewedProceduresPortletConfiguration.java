package es.aragon.enlinea.most.viewed.procedures.web.portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Asier Guillo
 */
@Meta.OCD(id = "es.aragon.enlinea.most.viewed.procedures.web.portlet.configuration.EnlineaMostViewedProceduresPortletConfiguration")
public interface EnlineaMostViewedProceduresPortletConfiguration {

	@Meta.AD(required = false)
	public String presentFilters();
	
	@Meta.AD(required = false)
	public String documentFilters();
	
}
