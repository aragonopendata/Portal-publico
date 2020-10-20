package es.aragon.enlinea.db.connection.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import es.aragon.enlinea.db.connection.dao.Document;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
public interface EnlineaDBService {
	
	public List<Procedure> getProceduresForIndex();
	public List<Document> getDocumentsForIndex();
	public String getProceduresCount();
	public String getDocumentsCount();
	public Procedure getProcedure(HttpServletRequest httpRequest, long procedureId);
	public Procedure getProcedure(HttpServletRequest httpRequest, String friendlyURL);
	public List<Procedure> getProceduresByTopic(HttpServletRequest httpRequest, long topicCategoryId, int size);
	public List<Procedure> getRelatedProcedures(HttpServletRequest httpRequest, long procedureId, String keywords);
	public Map<Procedure, String> getProcedureReport();
	
}
