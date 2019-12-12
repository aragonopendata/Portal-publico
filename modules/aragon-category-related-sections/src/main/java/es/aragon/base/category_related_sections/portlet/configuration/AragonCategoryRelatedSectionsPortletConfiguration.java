package es.aragon.base.category_related_sections.portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Asier Guillo
 */
@Meta.OCD(id = "es.aragon.base.category_related_sections.portlet.configuration.AragonCategoryRelatedSectionsPortletConfiguration")
public interface AragonCategoryRelatedSectionsPortletConfiguration {

	@Meta.AD(required = false)
	public String presentFilters();
	
	@Meta.AD(required = false)
	public String documentFilters();
	
}
