package es.aragon.enlinea.procedure.web.portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Asier Guillo
 */
@Meta.OCD(id = "es.aragon.enlinea.procedure.web.portlet.configuration.EnlineaProcedurePortletConfiguration")
public interface EnlineaProcedurePortletConfiguration {
	
	@Meta.AD(required = false, deflt = "")
	public String presential();
	
	@Meta.AD(required = false, deflt = "")
	public String presentialAndOnline();
	
	@Meta.AD(required = false, deflt = "")
	public String online();
	
}