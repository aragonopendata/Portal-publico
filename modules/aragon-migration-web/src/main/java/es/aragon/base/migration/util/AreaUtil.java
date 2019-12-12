package es.aragon.base.migration.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import es.aragon.base.migration.model.Area;
import es.aragon.base.migration.service.AreaLocalServiceUtil;

public class AreaUtil {
	
	private AreaUtil() {}

	public static long[] getUserAreaIds(long[] userGroupIds) {
		ArrayList<Long> areaIds = new ArrayList<>();
		for(long userGroupId : userGroupIds) {
			UserGroup userGroup = UserGroupLocalServiceUtil.fetchUserGroup(userGroupId);
			if(Validator.isNotNull(userGroup)) {
				String userGroupName = userGroup.getName();
				String[] areas = new String[] {};
				if(userGroupName.equals("Grupo de usuarios Acci\u00f3n exterior")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Presidencia"};
				} else if(userGroupName.equals("Grupo de usuarios de Administraci\u00f3n electr\u00f3nica")) {
					areas = new String[] {"Otros contenidos", "Departamentos / InnovacionInvestigacionUniversidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Administraci\u00f3n local")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Presidencia"};
				} else if(userGroupName.equals("Grupo de usuarios de Agricultura")) {
					areas = new String[] {"Otros contenidos","Departamentos / DesarrolloRuralSostenibilidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Alimentaci\u00f3n")) {
					areas = new String[] {"Otros contenidos", "Departamentos / DesarrolloRuralSostenibilidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Aragoneses en el exterior")) {
					areas = new String[] {"Otros contenidos", "Departamentos / CiudadaniaDerechosSociales"};
				} else if(userGroupName.equals("Grupo de usuarios de Artesan\u00eda Comercio Ferias")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo"};
				} else if(userGroupName.equals("Grupo de usuarios de Comit\u00e9 Bio\u00e9tica")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Sanidad", "Organos Consultivos / ComiteBioeticaAragon"};
				} else if(userGroupName.equals("Grupo de usuarios de Consumo")) {
					areas = new String[] {"Otros contenidos", "Departamentos / CiudadaniaDerechosSociales"};
				} else if(userGroupName.equals("Grupo de usuarios de Contrataci\u00f3n Patrimonio")) {
					areas = new String[] {"Otros contenidos", "Departamentos / HaciendaAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios de Cooperaci\u00f3n")) {
					areas = new String[] {"Otros contenidos", "Departamentos / CiudadaniaDerechosSociales"};
				} else if(userGroupName.equals("Grupo de usuarios de Cultura")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EducacionCulturaDeporte"};
				} else if(userGroupName.equals("Grupo de usuarios de Deporte")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EducacionCulturaDeporte"};
				} else if(userGroupName.equals("Grupo de usuarios de Economia")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo"};
				} else if(userGroupName.equals("Grupo de usuarios de Empresas")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo"};
				} else if(userGroupName.equals("Grupo de usuarios de Energia")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo"};
				} else if(userGroupName.equals("Grupo de usuarios de Funci\u00f3n p\u00fablica")) {
					areas = new String[] {"Otros contenidos", "Departamentos / HaciendaAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios de I+D+I")) {
					areas = new String[] {"Otros contenidos", "Departamentos / InnovacionInvestigacionUniversidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Igualdad y familias")) {
					areas = new String[] {"Otros contenidos", "Departamentos / CiudadaniaDerechosSociales"};
				} else if(userGroupName.equals("Grupo de usuarios de Industria")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo", "Organos Consultivos / ConsejoIndustriaAragon"};
				} else if(userGroupName.equals("Grupo de usuarios de Interior Convenios Medios")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Presidencia"};
				} else if(userGroupName.equals("Grupo de usuarios de Intervenci\u00f3n")) {
					areas = new String[] {"Otros contenidos", "Departamentos / HaciendaAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios de Justicia")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Presidencia"};
				} else if(userGroupName.equals("Grupo de usuarios de Medio ambiente")) {
					areas = new String[] {"Otros contenidos", "Departamentos / DesarrolloRuralSostenibilidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Medio rural")) {
					areas = new String[] {"Otros contenidos", "Departamentos / DesarrolloRuralSostenibilidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Mineria")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo"};
				} else if(userGroupName.equals("Grupo de usuarios de Patrimonio cultural")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EducacionCulturaDeporte"};
				} else if(userGroupName.equals("Grupo de usuarios de Presupuestos")) {
					areas = new String[] {"Otros contenidos", "Departamentos / HaciendaAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios de Relaciones institucionales")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Presidencia"};
				} else if(userGroupName.equals("Grupo de usuarios de Salud")) {
					areas = new String[] {"Otros contenidos", "Organismos Autonomos / ServicioAragonesSalud"};
				} else if(userGroupName.equals("Grupo de usuarios de Sanidad Ciudadano")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Sanidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Sanidad Profesionales")) {
					areas = new String[] {"Otros contenidos", "Departamentos / Sanidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Servicios ciudadano")) {
					areas = new String[] {"Otros contenidos", "Departamentos / HaciendaAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios de TIC")) {
					areas = new String[] {"Otros contenidos", "Departamentos / InnovacionInvestigacionUniversidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Trabajo")) {
					areas = new String[] {"Otros contenidos", "Departamentos / EconomiaIndustriaEmpleo"};
				} else if(userGroupName.equals("Grupo de usuarios de Tributos")) {
					areas = new String[] {"Otros contenidos", "Departamentos / HaciendaAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios de Turismo")) {
					areas = new String[] {"Otros contenidos", "Departamentos / VertebracionTerritorioMovilidadVivienda"};
				} else if(userGroupName.equals("Grupo de usuarios de Universidad")) {
					areas = new String[] {"Otros contenidos", "Departamentos / InnovacionInvestigacionUniversidad"};
				} else if(userGroupName.equals("Grupo de usuarios de Urbanismo")) {
					areas = new String[] {"Otros contenidos", "Departamentos / VertebracionTerritorioMovilidadVivienda"};
				} else if(userGroupName.equals("Grupo de usuarios de Vivienda")) {
					areas = new String[] {"Otros contenidos", "Departamentos / VertebracionTerritorioMovilidadVivienda"};
				} else if(userGroupName.equals("Grupo de usuarios del CESA")) {
					areas = new String[] {"Otros contenidos", "Organos Consultivos / ConsejoEconomicoSocialAragon"};
				} else if(userGroupName.equals("Grupo de usuarios del Consejo Consultivo")) {
					areas = new String[] {"Otros contenidos", "Organos Consultivos / ConsejoConsultivoAragon", "Organos Consultivos / JuntaConsultivaContratacionAdministrativa"};
				} else if(userGroupName.equals("Grupo de usuarios del IAA")) {
					areas = new String[] {"Otros contenidos", "Entidades Derecho Publico / InstitutoAragonesAgua"};
				} else if(userGroupName.equals("Grupo de usuarios del IAAP")) {
					areas = new String[] {"Otros contenidos", "Institutos / InstitutoAragonesAdministracionPublica"};
				} else if(userGroupName.equals("Grupo de usuarios del IAEST")) {
					areas = new String[] {"Otros contenidos", "Institutos / InstitutoAragonesEstadistica"};
				} else if(userGroupName.equals("Grupo de usuarios del IAJ")) {
					areas = new String[] {"Otros contenidos", "Organismos Autonomos / InstitutoAragonesJuventud"};
				} else if(userGroupName.equals("Grupo de usuarios del IAM")) {
					areas = new String[] {"Otros contenidos", "Organismos Autonomos / InstitutoAragonesMujer"};
				} else if(userGroupName.equals("Grupo de usuarios del IASS")) {
					areas = new String[] {"Otros contenidos", "Organismos Autonomos / IASS"};
				} else if(userGroupName.equals("Grupo de usuarios del IGEAR")) {
					areas = new String[] {"Otros contenidos", "Institutos / IGEAR"};
				} else if(userGroupName.equals("Grupo de usuarios del INAGA")) {
					areas = new String[] {"Otros contenidos", "Entidades Derecho Publico / InstitutoAragonesGestionAmbiental"};
				} else if(userGroupName.equals("Grupo de usuarios del ISSLA")) {
					areas = new String[] {"Otros contenidos", "Institutos / InstitutoAragonesSeguridadSaludLaboral"};
				} else if(userGroupName.equals("Grupos de usuario de Carreteras Trasporte Territorio")) {
					areas = new String[] {"Otros contenidos", "Departamentos / VertebracionTerritorioMovilidadVivienda"};
				}
				for(String area : areas) {
					DynamicQuery dynamicQuery = AreaLocalServiceUtil.dynamicQuery();
					dynamicQuery.add(PropertyFactoryUtil.forName("name").eq(area));
					ArrayList<Area> areasByName = new ArrayList<Area>(AreaLocalServiceUtil.dynamicQuery(dynamicQuery));
					for(Area areabyName : areasByName) {
						areaIds.add(areabyName.getAreaId());
					}
				}
			}
		}
		return areaIds.stream().mapToLong(l -> l).toArray();
	}
	
	public static List<Area> getUserAreas(long[] userGroupIds) {
		List<Area> userAreas = new ArrayList<>();
		long[] userAreaIds = getUserAreaIds(userGroupIds);
		for(long userAreaId : userAreaIds) {
			Area userArea = AreaLocalServiceUtil.fetchArea(userAreaId);
			if(Validator.isNotNull(userArea)) {
				userAreas.add(userArea);
			}
		}
		return userAreas;
	}
	

}
