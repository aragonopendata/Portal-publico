package es.aragon.enlinea.db.connection.dao;

/**
 * @author Asier Guillo
 */
public class Document {

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(long procedureId) {
		this.procedureId = procedureId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	private long documentId;
	private long companyId;
	private long groupId;
	private long procedureId;
	private String name;
	private String url;
	private int order;
	
}
