package es.aragon.enlinea.db.connection.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import es.aragon.enlinea.db.connection.constants.FriendlyURLConstants;
import es.aragon.enlinea.db.connection.constants.SQLQueryConstants;
import es.aragon.enlinea.db.connection.dao.Document;
import es.aragon.enlinea.db.connection.dao.Procedure;

/**
 * @author Asier Guillo
 */
public class ProcedureUtil {
	
	private static final Log log = LogFactoryUtil.getLog(ProcedureUtil.class);
	
	private static final String BASE_URL = "https://aplicaciones.aragon.es";
	
	private ProcedureUtil() {}
	
	public static Procedure parseProcedureFromDB(ResultSet resultSet, long companyId, long groupId, User user, Set<String> friendlyURLs) {
		try {
			Procedure procedure = new Procedure();
			procedure.setProcedureId(resultSet.getLong(SQLQueryConstants.SIGNATURA));
			procedure.setCompanyId(companyId);
			procedure.setGroupId(groupId);
			procedure.setUserId(user.getUserId());
			procedure.setUserName(user.getFullName());
			procedure.setCreateDate(new Date());
			procedure.setModifiedDate(new Date());
			procedure.setName(
					treatName(loadStringFromOptions(resultSet, SQLQueryConstants.DENOMINACION_LC, SQLQueryConstants.DENOMINACION)));
			procedure.setKeywords(
					loadString(resultSet, SQLQueryConstants.PALCLAVES).replaceAll(",", " ").replaceAll("  ", " "));
			procedure.setDescription(
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.OBJETO_LC, SQLQueryConstants.OBJETO)));
			procedure.setApplicant(
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.BENEFICIA_LC, SQLQueryConstants.BENEFICIA)));
			String requirements = 
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.REQUISITOS_LC, SQLQueryConstants.REQUISITOS));
			String observations = 
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.OBSERVACIONES_LC, SQLQueryConstants.OBSERVAC));
			if(requirements.equals(observations)) {
				procedure.setRequirementsAndObservations(requirements);
			} else {
				procedure.setRequirementsAndObservations(requirements + "<br>" + observations);
			}
			procedure.setDocumentation(
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.DOCUMENTA_LC, SQLQueryConstants.DOCUMENTA)));
			procedure.setNormative(
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.NORMATIVA_LC, SQLQueryConstants.NORMATIVA)));
			procedure.setFromDate(resultSet.getDate(SQLQueryConstants.FECPRESENTACIONDESDE));
			procedure.setToDate(resultSet.getDate(SQLQueryConstants.FECPRESENTACIONHASTA));
			procedure.setResolutionTime(
					replaceToHTMLTags(loadStringFromOptions(resultSet, SQLQueryConstants.TIEMPOTRAMI_LC, SQLQueryConstants.TIEMPOTRAMI)));
			procedure.setInLevel(resultSet.getInt(SQLQueryConstants.INNIVELACTUAL));
			procedure.setOnlineURL(treatOnlineURL(loadString(resultSet, SQLQueryConstants.URLTRAMITACIONONLINE)));
			procedure.setResponsibleDepartment(StringUtil.upperCaseFirstLetter(StringUtil.lowerCase(loadString(resultSet, SQLQueryConstants.DESCRIPCION))));
			procedure.setResponsibleDepartmentURL(loadString(resultSet, SQLQueryConstants.URLDEPARTAMENTORESPONSABLE_LC));
			procedure.setFriendlyURL(generateFriendlyURL(procedure.getName(), friendlyURLs));
			return procedure;
		} catch (SQLException e) {
			log.error("Error parsing procedure information", e);
		}
		return null;
	}
	
	public static Procedure parseProcedureFromES(com.liferay.portal.kernel.search.Document document) {
		Procedure procedure = new Procedure();
		procedure.setProcedureId(GetterUtil.getLong(document.get("procedureId"), 0));
		procedure.setName(GetterUtil.getString(document.get("name"), StringPool.BLANK));
		procedure.setKeywords(GetterUtil.getString(document.get("keywords"), StringPool.BLANK));
		procedure.setDescription(GetterUtil.getString(document.get("description"), StringPool.BLANK));
		procedure.setApplicant(GetterUtil.getString(document.get("applicant"), StringPool.BLANK));
		procedure.setRequirementsAndObservations(GetterUtil.getString(document.get("requirementsAndObservations"), StringPool.BLANK));
		procedure.setDocumentation(GetterUtil.getString(document.get("documentation"), StringPool.BLANK));
		procedure.setNormative(GetterUtil.getString(document.get("normative"), StringPool.BLANK));
		try {
			procedure.setFromDate(document.getDate("fromDate"));
		} catch (ParseException e) {
			procedure.setFromDate(null);
		}
		try {
			procedure.setToDate(document.getDate("toDate"));
		} catch (ParseException e) {
			procedure.setToDate(null);
		}
		procedure.setResolutionTime(GetterUtil.getString(document.get("resolutionTime"), StringPool.BLANK));
		procedure.setInLevel(GetterUtil.getInteger(document.get("inLevel"), 1));
		procedure.setOnlineURL(GetterUtil.getString(document.get("onlineURL"), StringPool.BLANK));
		procedure.setResponsibleDepartment(GetterUtil.getString(document.get("responsibleDepartment"), StringPool.BLANK));
		procedure.setResponsibleDepartmentURL(GetterUtil.getString(document.get("responsibleDepartmentURL"), StringPool.BLANK));
		procedure.setFriendlyURL(GetterUtil.getString(document.get("friendlyURL"), StringPool.BLANK));
		String[] categories = document.getValues("assetCategoryIds");
		for(String category :  categories) {
			long categoryId = GetterUtil.getLong(category, 0);
			if(categoryId != 0) {
				procedure.addCategoryId(categoryId);
			}
		}
		return procedure;
	}
	
	public static Procedure parseProcedureByTopicFromDB(ResultSet resultSet) {
		try {
			Procedure procedure = new Procedure();
			procedure.setProcedureId(resultSet.getLong(SQLQueryConstants.SIGNATURA));
			procedure.setName(
					treatName(loadStringFromOptions(resultSet, SQLQueryConstants.DENOMINACION_LC, SQLQueryConstants.DENOMINACION)));
			procedure.setFromDate(resultSet.getDate(SQLQueryConstants.FECPRESENTACIONDESDE));
			procedure.setToDate(resultSet.getDate(SQLQueryConstants.FECPRESENTACIONHASTA));
			return procedure;
		} catch (SQLException e) {
			log.error("Error parsing procedure information", e);
		}
		return null;
	}
	
	public static Document parseDocumentFromDB(ResultSet resultSet) {
		try {
			Document document = new Document();
			document.setName(resultSet.getString(SQLQueryConstants.DESCRIPCION));
			document.setUrl(BASE_URL + resultSet.getString(SQLQueryConstants.URL));
			return document;
		} catch (SQLException e) {
			log.error("Error parsing document information", e);
		}
		return null;
	}
	
	public static Document parseDocumentForIndex(ResultSet resultSet, long companyId, long groupId) {
		try {
			Document document = new Document();
			document.setCompanyId(companyId);
			document.setGroupId(groupId);
			document.setProcedureId(resultSet.getLong(SQLQueryConstants.SIGNATURA));
			document.setName(resultSet.getString(SQLQueryConstants.DESCRIPCION));
			document.setUrl(BASE_URL + resultSet.getString(SQLQueryConstants.URL));
			document.setOrder(resultSet.getInt(SQLQueryConstants.ORDEN));
			return document;
		} catch (SQLException e) {
			log.error("Error parsing document information", e);
		}
		return null;
	}
	
	public static Document parseDocumentFromES(com.liferay.portal.kernel.search.Document doc) {
		Document document = new Document();
		document.setProcedureId(GetterUtil.getLong(doc.get("procedureId"), 0));
		document.setName(GetterUtil.getString(doc.get("name"), StringPool.BLANK));
		document.setUrl(GetterUtil.getString(doc.get("url"), StringPool.BLANK));
		document.setOrder(GetterUtil.getInteger(doc.get("order"), 0));
		return document;
	}
	
	private static String loadStringFromOptions(ResultSet resultSet, String option1Column, String option2Column) throws SQLException {
		String option1 = resultSet.getString(option1Column);
		String option2 = resultSet.getString(option2Column);
		if(Validator.isNotNull(option1) && !option1.isEmpty()) {
			return option1;
		} else if(Validator.isNotNull(option2)  && !option2.isEmpty()) {
			return option2;
		} else {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", ProcedureUtil.class);
			return LanguageUtil.get(resourceBundle, "no-information");
		}
	}
	
	private static String loadString(ResultSet resultSet, String column) throws SQLException {
		String value = resultSet.getString(column);
		if(Validator.isNotNull(value) && !value.isEmpty()) {
			return value;
		} else {
			return StringPool.BLANK;
		}
	}
	
	private static String replaceToHTMLTags(String text) {
		String newText = text;
		newText = newText.replace("\n\r", "<BR>");
		newText = newText.replace("\r\n", "<BR>");
		newText = newText.replace("\n", "<BR>");
		newText = newText.replace("\r", "<BR>");
		return newText;
	}
	
	private static String treatName(String str) {
		if(Validator.isNull(str)) {
			return StringPool.BLANK;
		}
		
		str = StringUtil.lowerCase(str);
		
		List<String> excepcionesFirstLetter = new ArrayList<>(Arrays.asList("espa\u00f1a","espana","arag\u00f3n",
				"aragon","zaragoza","huesca","teruel","garcipollera","ejulve",
				"maestrazgo","tortosa","beceite","vi\u00f1amala","benasque","goya"));
		
		List<String> excepcionesUpperCase = new ArrayList<>(Arrays.asList("pymes","(pac)",
				"(earea)","igear","o.c.m.","(ecai)","(vtc)","(vspc)","(vs)","(vpc)","(mpc)","(vt)",
				"(ot)","u.e.","(vd)","(proa)","(adia)","(gnl)","(iai)","(repia)",
				"(fse)","a.t.r.i.a.s","iseal","glp","iass","ico/iaf","(mile)","(itv)",
				"ico","a)","b)","c)","d)"));
		
		StringBuilder treatedStr = new StringBuilder();
		String[] tokens = StringUtil.split(str, " ");
		for(String token : tokens) {
			if(treatedStr.length() != 0) {
				treatedStr.append(" ");
			}
			String treatedToken = token;
			if(excepcionesFirstLetter.contains(treatedToken)) {
				treatedToken = StringUtil.upperCaseFirstLetter(treatedToken);
			}
			if(excepcionesUpperCase.contains(treatedToken)) {
				treatedToken = StringUtil.upperCase(treatedToken);
			}
			if(treatedToken.equals("Aragon")) {
				treatedToken = "Arag\u00f3n";
			}
			if(treatedToken.equals("innoempresa")) {
				treatedToken = "InnoEmpresa";
			}
			treatedStr.append(treatedToken);
		}
		str = treatedStr.toString();
		
		str = str.replace("montes universales", "Montes universales");
		str = str.replace("los circos", "Los circos");
		str = str.replace("orihuela del tremedal", "Orihuela del tremedal");
		str = str.replace("\u00e1ngela l\u00f3pez jim\u00e9nez", "\u00c1ngela L\u00f3pez Jim\u00e9nez");
		
		str = str.replace("(mdl y mdp)", "(MDL Y MDP)");
		str = str.replace("itc ep-5", "ITC EP-5");
		
		str = StringUtil.upperCaseFirstLetter(str);
		
		return str;
	}
	
	private static String treatOnlineURL(String str) {
		if(Validator.isNotNull(str) && !str.isEmpty() && str.contains("/ett")) {
			str = BASE_URL + str;
		}
		if(!str.startsWith("https://") || !str.startsWith("http://")) {
			str = "http://" + str;
		}
		return str;
	}
	
	private static String generateFriendlyURL(String name, Set<String> friendlyURLs) {
		name = name.trim().toLowerCase();
		// Normalize and delete accents
		name = Normalizer.normalize(name, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		// Delete stopwords
		for(String s : FriendlyURLConstants.STOPWORDS) {
			name = name.replaceAll("\\b" + s + "\\b", "");
		}
		// Delete non alphanumeric characters
		name = name.replaceAll("^[^\\p{Alnum}]+", "");
		name = name.replaceAll("[^\\p{Alnum}]+$", "");
		name = name.replaceAll("[^\\p{Alnum}]+", " ");
		// Replace spaces
		name = name.replaceAll(" ", "-");
		String auxName = name;
		int i = 0;
		while(friendlyURLs.contains(auxName)) {
			i++;
			auxName = name + "-" + i; 
		}
		return name;
	}
	
}
