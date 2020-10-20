package es.aragon.enlinea.db.connection.constants;

/**
 * @author Asier Guillo
 */
public class SQLQueryConstants {
	
	private SQLQueryConstants() {}
	
	public static final String SIGNATURA = "SIGNATURA";
	public static final String DENOMINACION = "DENOMINACION";
	public static final String DENOMINACION_LC = "DENOMINACION_LC";
	public static final String PALCLAVES = "PALCLAVES";
	public static final String OBJETO = "OBJETO";
	public static final String OBJETO_LC = "OBJETO_LC";
	public static final String BENEFICIA = "BENEFICIA";
	public static final String BENEFICIA_LC = "BENEFICIA_LC";
	public static final String REQUISITOS = "REQUISITOS";
	public static final String REQUISITOS_LC = "REQUISITOS_LC";
	public static final String OBSERVAC = "OBSERVAC";
	public static final String OBSERVACIONES_LC = "OBSERVACIONES_LC";
	public static final String DOCUMENTA = "DOCUMENTA";
	public static final String DOCUMENTA_LC = "DOCUMENTA_LC";
	public static final String NORMATIVA = "NORMATIVA";
	public static final String NORMATIVA_LC = "NORMATIVA_LC";
	public static final String FECPRESENTACIONDESDE = "FECPRESENTACIONDESDE";
	public static final String FECPRESENTACIONHASTA = "FECPRESENTACIONHASTA";
	public static final String FECPUBLICACI = "FECPUBLICACI";
	public static final String TIEMPOTRAMI = "TIEMPOTRAMI";
	public static final String TIEMPOTRAMI_LC = "TIEMPOTRAMI_LC";
	public static final String INNIVELACTUAL = "INNIVELACTUAL";
	public static final String URLTRAMITACIONONLINE = "URLTRAMITACIONONLINE";
	public static final String URLDEPARTAMENTORESPONSABLE_LC = "URLDEPARTAMENTORESPONSABLE_LC";
	public static final String LUGARPRESENTACION_LC = "LUGARPRESENTACION_LC";
	public static final String PLAZOSINDEFINIR = "PLAZOSINDEFINIR";
	
	public static final String DESCRIPCION = "DESCRIPCION";
	public static final String URL = "URL";
	public static final String ES_FORMULARIO = "ES_FORMULARIO";
	public static final String ORDEN = "ORDEN";
	
	public static final String[] PROCEDURE_FIELDS = new String[] { 
			SIGNATURA, DENOMINACION, DENOMINACION_LC, PALCLAVES,
			OBJETO, OBJETO_LC, BENEFICIA, BENEFICIA_LC,
			REQUISITOS, REQUISITOS_LC, OBSERVAC, OBSERVACIONES_LC,
			DOCUMENTA, DOCUMENTA_LC, NORMATIVA, NORMATIVA_LC, FECPUBLICACI, LUGARPRESENTACION_LC,
			FECPRESENTACIONDESDE, FECPRESENTACIONHASTA,
			TIEMPOTRAMI, TIEMPOTRAMI_LC, INNIVELACTUAL, URLTRAMITACIONONLINE,
			URLDEPARTAMENTORESPONSABLE_LC, PLAZOSINDEFINIR
	};
	
	public static final String[] PROCEDURE_TOPIC_FIELDS = new String[] { 
			"P." + SIGNATURA, DENOMINACION, DENOMINACION_LC,
			FECPRESENTACIONDESDE, FECPRESENTACIONHASTA
	};
	
	public static final String[] PROCEDURE_DOCUMENT_FIELDS = new String[] { 
			"D." + SIGNATURA, DESCRIPCION, URL, ES_FORMULARIO, ORDEN
	};
	
	public static final String[] PROCEDURE_DEPARTMENT_FIELDS = new String[] { 
			"D." + DESCRIPCION
	};
	
}
