package es.aragon.base.content_ratings.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.content_ratings.constants.ContentRatingsWebPortletKeys;
import es.aragon.base.content_ratings.service.RatingLocalService;

/**
 * @author pfalcon
 * Content ratings portlet class
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ContentRatingsWebPortletKeys.CONTENT_RATINGS_WEB_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ContentRatingsWebPortlet extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		JSONObject responseJsonObject = JSONFactoryUtil.createJSONObject();
		try {
			//Gets theme display from request
			ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			//Gets request parameters
			int scoreVal = ParamUtil.getInteger(resourceRequest, "scoreval", 0);
			long classNameId = ParamUtil.getLong(resourceRequest, "classNameId", 0);
			long classPK = ParamUtil.getLong(resourceRequest, "classPK", 0);
			//Creates response json object
			boolean success = Boolean.TRUE;
			String errorMsg = StringPool.BLANK;
			//Converts the rating to double
			double score = 0;
			if (scoreVal > 0) {
				score = new BigDecimal(scoreVal * 0.2).setScale(1, RoundingMode.HALF_UP).doubleValue();
			}
			//Validates score
			if (score == 0) {
				errorMsg = score + " no es una puntuacion valida";
				success = Boolean.FALSE;
			}
			//Validates the resource to rate
			if (classNameId != 0 && classPK != 0) {
				if (classNameId == _classNameLocalService.getClassNameId(JournalArticle.class)) {
					JournalArticle journalArticle =_journalArticleLocalService.fetchLatestArticle(classPK);
					if (journalArticle == null) {
						errorMsg = "No se ha encontrado el contenido web a valorar";
						success = Boolean.FALSE;
					}
				} else if (classNameId == _classNameLocalService.getClassNameId(Layout.class)) {
					Layout layout = _layoutLocalService.fetchLayout(classPK);
					if (layout == null) {
						errorMsg = "No se ha encontrado el layout a valorar";
						success = Boolean.FALSE;
					}
				} else {
					errorMsg = "El tipo de recurso no esta soportado por el sistema de valoraciones";
					success = Boolean.FALSE;
				}
			} else {
				errorMsg = "No existe el recurso a valorar";
				success = Boolean.FALSE;
			}
			if (success) {
				//Adds the rating to database
				_ratingLocalService.addRating(themeDisplay.getCompanyId(), classNameId, classPK, score);
				//Populate response object
				responseJsonObject.put("success", "true");
				responseJsonObject.put("msg", "Se ha valorado el recurso correctamente");
				_log.info("Rating saved succesfully");
			} else {
				responseJsonObject.put("success", "false");
				responseJsonObject.put("msg", "Ha habido un error al valorar el recurso: " + errorMsg);
				_log.error("There was an error saving the rating: " + errorMsg);
			}
		} catch (Exception e) {
			responseJsonObject.put("success", "false");
			responseJsonObject.put("msg", "Ha habido un error al valorar el recurso: " + e.toString());
			_log.error("There was an error saving the rating: " + e.toString());
		} finally {
			//Write response object in the response
			resourceResponse.setContentType("text/html");
			PrintWriter writer = resourceResponse.getWriter();
			writer.print(responseJsonObject.toString());
			writer.flush();
			writer.close();
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	@Reference
	private ClassNameLocalService _classNameLocalService;
	
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	
	@Reference
	private LayoutLocalService _layoutLocalService;
	
	@Reference
	private RatingLocalService _ratingLocalService;
	
	/**
	 * Log of the class
	 */
	private Log _log = LogFactoryUtil.getLog(ContentRatingsWebPortlet.class);
	
}