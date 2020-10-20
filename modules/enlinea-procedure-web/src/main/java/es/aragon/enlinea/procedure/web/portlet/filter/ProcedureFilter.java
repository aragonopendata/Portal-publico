package es.aragon.enlinea.procedure.web.portlet.filter;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.enlinea.db.connection.dao.Procedure;
import es.aragon.enlinea.procedure.web.portlet.route.ProcedureFriendlyURLMapper;

/**
 * @author Asier Guillo
 */
@Component(
	enabled = true,
	immediate = true, 
	property = {
		"servlet-context-name=",
		"dispatcher=FORWARD",
		"dispatcher=REQUEST",
		"servlet-filter-name=Procedure Filter",
		"url-pattern=/*",
		"url-regex-ignore-pattern=^/html/.+\\.(css|gif|html|ico|jpg|js|png)(\\?.*)?$"
	},
	service = Filter.class
)
public class ProcedureFilter extends BaseFilter {
	
	private static final Log log = LogFactoryUtil.getLog(ProcedureFilter.class);
	private static final String PATTERN = Portal.FRIENDLY_URL_SEPARATOR + ProcedureFriendlyURLMapper.MAPPING + "/";
	
	@Reference
	private Portal portal;

	@Override
	protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws Exception {
		String requestURI = GetterUtil.getString(request.getRequestURI());
		if(requestURI.contains(PATTERN)) {
			String friendlyURL = requestURI.substring(requestURI.lastIndexOf(PATTERN) + PATTERN.length());
			if(!existsProcedure(request, friendlyURL)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				processFilter(
						ProcedureFilter.class.getName(), request, response, filterChain);
			}
		} else {
			processFilter(
					ProcedureFilter.class.getName(), request, response, filterChain);
		}
	}
	
	private boolean existsProcedure(HttpServletRequest request, String friendlyURL) {
		long groupId = 0;
		try {
			groupId = portal.getScopeGroupId(request);
		} catch (PortalException e1) {
			log.error("Error obtaining groupId from request");
		}
		SearchContext searchContext = SearchContextFactory.getInstance(
				new long[0], new String[0], new HashMap<>(), 
				portal.getCompanyId(request), "", null, LocaleUtil.getDefault(),
				groupId, TimeZoneUtil.getDefault(), portal.getUserId(request));
		searchContext.setStart(0);
		searchContext.setEnd(1);
		BooleanQuery booleanQuery = new BooleanQueryImpl();
		BooleanFilter filter = new BooleanFilter();
		filter.addRequiredTerm("entryClassName", Procedure.class.getName());
		booleanQuery.setPreBooleanFilter(filter);
		booleanQuery.addRequiredTerm("friendlyURL", friendlyURL);
		try {
			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			if(Validator.isNotNull(hits) && hits.getLength() > 0) {
				String procedureURL = GetterUtil.getString(hits.getDocs()[0].get("friendlyURL"), "");
				if(procedureURL.equals(friendlyURL)) {
					return true;
				}
			}
		} catch (SearchException e) {
			log.error("Error getting procedure by ES", e);
		}
		return false;
	}

	@Override
	protected Log getLog() {
		return log;
	}

}
