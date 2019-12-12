package es.aragon.base.portal.utilities.utils;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.aragon.base.portal.utilities.constants.AragonPortalUtilitiesPortletKeys;
import es.aragon.base.semaphore.model.Semaphore;
import es.aragon.base.semaphore.service.SemaphoreLocalServiceUtil;

public class AragonPortalUtilitiesSearchUrlUtil {

	private AragonPortalUtilitiesSearchUrlUtil() {
		
	}
	
	/**
	 * 
	 * @param groupId
	 * @param companyId
	 * @param url
	 * @param start
	 * @param end
	 * @return null if semaphore is full, or list with found results 
	 * @throws SQLException
	 */
	public static List<String> findReferencesInAllJournalArticleContents(long groupId, long companyId, String url, int start, int end) throws SQLException {
		List<String> referencedArticlesURL = new ArrayList<>();
		if(url == null || url.equals("")) {
			return referencedArticlesURL;
		}
		// Add or fetch existing semaphore
		Semaphore semaphore = SemaphoreLocalServiceUtil.addSemaphore(groupId, AragonPortalUtilitiesPortletKeys.ARAGON_PORTAL_UTILITIES, AragonPortalUtilitiesPortletKeys.MAX_SEMAPHORE_USERS);
		if(SemaphoreLocalServiceUtil.semaphoreWait(semaphore)) {
			try {
				Connection mySQLConnection = DataAccess.getConnection();
				Statement stmt = mySQLConnection.createStatement();
				
				String query = "SELECT urlTitle" + 
						" FROM journalarticle WHERE groupId = " + groupId + 
						" AND companyId = " + companyId +
						" AND status = " + WorkflowConstants.STATUS_APPROVED +
						" AND (articleId,version) IN" + 
						" ( SELECT articleId, MAX(version)" + 
						" FROM journalarticle" + 
						" GROUP BY articleId" + 
						" ) AND content like '%" + url + "%'" + 
						" ORDER BY modifiedDate desc" +
						" LIMIT " + (end - start) +
						" OFFSET " + start + ";";
				ResultSet resultSet = stmt.executeQuery(query);
				while (resultSet.next()) {
					referencedArticlesURL.add(resultSet.getString(1));
				}
				stmt.close();
				resultSet.close();
				mySQLConnection.close();
			} finally {
		        // release semaphore
		        SemaphoreLocalServiceUtil.semaphoreSignal(semaphore);
			}
		}
		return referencedArticlesURL;
	}
	public static int findReferencesInAllJournalArticleContentsCount(long groupId, long companyId, String url, List<String> results) throws SQLException {
		if(results.isEmpty()) {
			return 0;
		} else {
			return findReferencesInAllJournalArticleContentsCount(groupId, companyId, url);
		}
	}

	public static int findReferencesInAllJournalArticleContentsCount(long groupId, long companyId, String url) throws SQLException {
		int returnCount = 0;
		if(url == null || url.equals("")) {
			return returnCount;
		}
		
		Connection mySQLConnection = DataAccess.getConnection();
		Statement stmt = mySQLConnection.createStatement();		
		String query = "SELECT count(*) as count" + 
				" FROM journalarticle WHERE groupId = " + groupId + 
				" AND companyId = " + companyId +
				" AND status = " + WorkflowConstants.STATUS_APPROVED +
				" AND (articleId,version) IN" + 
				" ( SELECT articleId, MAX(version)" + 
				" FROM lportalaragon.journalarticle" + 
				" GROUP BY articleId" + 
				" ) AND content like '%" + url + "%'" + 
				";";
		ResultSet resultSet = stmt.executeQuery(query);
		while (resultSet.next()) {
			returnCount = resultSet.getInt("count");
		}
		stmt.close();
		resultSet.close();
		mySQLConnection.close();
		
		return returnCount;
	}
	
	
}
