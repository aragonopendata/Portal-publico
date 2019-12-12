package es.aragon.enlinea.db.connection.dao;

import com.liferay.petra.string.StringPool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Asier Guillo
 */
public class Procedure {
	
	public Procedure() {
		categoryIds = new ArrayList<>();
		categoryTitles = new ArrayList<>();
		documents = new ArrayList<>();
		relatedProcedures = new ArrayList<>();
	}

	public long getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(long procedureId) {
		this.procedureId = procedureId;
	}

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getRequirementsAndObservations() {
		return requirementsAndObservations;
	}

	public void setRequirementsAndObservations(String requirementsAndObservations) {
		this.requirementsAndObservations = requirementsAndObservations;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getNormative() {
		return normative;
	}

	public void setNormative(String normative) {
		this.normative = normative;
	}
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}
	
	public String getToDateFormatted() {
		return new SimpleDateFormat("dd/MM").format(toDate);
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public boolean isInTerm() {
		Calendar cNow = Calendar.getInstance();
		Date now = cNow.getTime();
		cNow.set(cNow.get(Calendar.YEAR), cNow.get(Calendar.MONTH), cNow.get(Calendar.DATE), 0, 0, 0);
		cNow.set(Calendar.MILLISECOND, 0);
		Date today = cNow.getTime();
		return ((fromDate == null && toDate == null)
				|| (fromDate == null && (toDate.after(today) || toDate.equals(today)))
				|| (fromDate != null && toDate != null && fromDate.before(now) && (toDate.after(today) || toDate.equals(today)))
				|| (toDate == null && fromDate.before(now)));
	}
	
	public String getResolutionTime() {
		return resolutionTime;
	}

	public void setResolutionTime(String resolutionTime) {
		this.resolutionTime = resolutionTime;
	}

	public int getInLevel() {
		return inLevel;
	}

	public void setInLevel(int inLevel) {
		this.inLevel = inLevel;
	}

	public String getOnlineURL() {
		return onlineURL;
	}

	public void setOnlineURL(String onlineURL) {
		this.onlineURL = onlineURL;
	}

	public String getResponsibleDepartment() {
		return responsibleDepartment;
	}

	public void setResponsibleDepartment(String responsibleDepartment) {
		this.responsibleDepartment = responsibleDepartment;
	}

	public String getResponsibleDepartmentURL() {
		return responsibleDepartmentURL;
	}

	public void setResponsibleDepartmentURL(String responsibleDepartmentURL) {
		this.responsibleDepartmentURL = responsibleDepartmentURL;
	}
	
	public String getFriendlyURL() {
		return friendlyURL;
	}

	public void setFriendlyURL(String friendlyURL) {
		this.friendlyURL = friendlyURL;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	public void addCategoryId(long categoryId) {
		categoryIds.add(categoryId);
	}
	
	public boolean containsCategoryId(long categoryId) {
		return categoryIds.contains(categoryId);
	}

	public List<String> getCategoryTitles() {
		return categoryTitles;
	}
	
	public void addCategoryTitle(String categoryTitle) {
		categoryTitles.add(categoryTitle);
	}

	public void setCategoryTitles(List<String> categoryTitles) {
		this.categoryTitles = categoryTitles;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	public void addDocument(Document document) {
		documents.add(document);
	}

	public List<Procedure> getRelatedProcedures() {
		return relatedProcedures;
	}

	public void setRelatedProcedures(List<Procedure> relatedProcedures) {
		this.relatedProcedures = relatedProcedures;
	}
	
	public String getContent() {
		return procedureId + StringPool.BLANK
				+ name + StringPool.BLANK 
				+ keywords + StringPool.BLANK
				+ description + StringPool.BLANK
				+ applicant + StringPool.BLANK
				+ requirementsAndObservations + StringPool.BLANK
				+ documentation + StringPool.BLANK
				+ normative + StringPool.BLANK;	
	}

	private long procedureId;
	private long companyId;
	private long groupId;
	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private String name;
	private String keywords;
	private String description;
	private String applicant;
	private String requirementsAndObservations;
	private String documentation;
	private String normative;
	private Date fromDate;
	private Date toDate;
	private String resolutionTime;
	private int inLevel;
	private String onlineURL;
	private String responsibleDepartment;
	private String responsibleDepartmentURL;
	private String friendlyURL;
	private List<Long> categoryIds;
	private List<String> categoryTitles;
	private List<Document> documents;
	private List<Procedure> relatedProcedures;
	
}
