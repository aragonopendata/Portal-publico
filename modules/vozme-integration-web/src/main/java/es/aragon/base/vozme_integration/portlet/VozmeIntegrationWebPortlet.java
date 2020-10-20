package es.aragon.base.vozme_integration.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.osgi.service.component.annotations.Component;
import org.apache.commons.lang3.StringUtils;
import es.aragon.base.vozme_integration.constants.VozmeIntegrationWebPortletKeys;

/**
 * @author migarcia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.aragon",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + VozmeIntegrationWebPortletKeys.VOZME_INTEGRATION_WEB_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VozmeIntegrationWebPortlet extends MVCPortlet {
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		String action = ParamUtil.getString(resourceRequest, "action");
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if (action.equals("fromtexttospeech")) {
			//URL text to speech
			String url = "http://www.fromtexttospeech.com/";
			PrintWriter writer = resourceResponse.getWriter();
			JSONObject resultJSONObject = JSONFactoryUtil.createJSONObject();
			BufferedReader in = null;
			InputStream inputStream = null;
			try {
				//get parameters
				String lang = ParamUtil.getString(resourceRequest, "lang");
				String text = ParamUtil.getString(resourceRequest, "text");
				String speed = ParamUtil.getString(resourceRequest, "speed");
				String voice = StringPool.BLANK;
				resourceResponse.setContentType("text/html");
				HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
				//add request header
				httpClient.setRequestMethod("POST");
				httpClient.setRequestProperty("content-type", "application/x-www-form-urlencoded");
				httpClient.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
				httpClient.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
				//value lang and voice
				switch (lang) {
				  case "es_Es":
					  lang = "Spanish";
					  voice = "IVONA Conchita22 (Spanish [Modern])";
				    break;
				  case "en_US":
				    lang = "US English";
				    voice = "IVONA Kimberly22";
				    break;
				  case "en_EN":
					  lang = "British English";
					  voice = "IVONA Amy22 (UK English)";
				    break;
				  case "fr_FR":
					  lang = "French";
					  voice = "IVONA Mathieu22 (French)";
				    break;
				  case "de_DE":
					  lang = "German";
					  voice = "IVONA Hans22 (German)";
				    break;
				  case "it_IT":
					  lang = "Italian";
					  voice = "IVONA Carla22 (Italian)";
				    break;
				  case "ru_RU":
					  lang = "Russian";
					  voice = "IVONA Tatyana22 (Russian)";
				    break;
				  default:
					  lang = "Spanish";
					  voice = "IVONA Conchita22 (Spanish [Modern])";
			          break;
				}
				//parameters post
				Map<String,Object> params = new LinkedHashMap<>();
		        params.put("action", "process_text");
		        params.put("input_text", StringUtils.normalizeSpace(text));
		        params.put("language", lang);
		        params.put("speed", speed);
		        params.put("voice", voice);
		        StringBuilder postData = new StringBuilder();
		        for (Map.Entry<String,Object> param : params.entrySet()) {
		            if (postData.length() != 0) {
		            	postData.append('&');
		            }
		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		            postData.append('=');
		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		        }
		        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		        httpClient.setDoOutput(true);
		        httpClient.getOutputStream().write(postDataBytes);			
				int responseCode = httpClient.getResponseCode();
				LOG.info("responseCode: "+responseCode);
				inputStream = httpClient.getInputStream();
				if (Validator.isNotNull(inputStream)) {
				    if (200 <= responseCode && responseCode <= 299) {
				        inputStream = httpClient.getInputStream();
				    } else {
				        inputStream = httpClient.getErrorStream();
				    }
				}
			    in = new BufferedReader(new InputStreamReader(inputStream));
			    StringBuilder response = new StringBuilder();
			    String currentLine = StringPool.BLANK;
			    while ((currentLine = in.readLine()) != null) 
			        response.append(currentLine);
			    //get url MP3
			    org.jsoup.nodes.Document doc = Jsoup.parse(response.toString());
			    Element link = doc.select(".input_text a").first();
			    if (Validator.isNotNull(link)) {
			    	String linkHref = link.attr("href");
			    	String encodedFile = StringPool.BLANK;
			    	if (!linkHref.isEmpty() && !linkHref.equals(StringPool.BLANK)) {
			    		//open connection with audio mp3 from url
			    		URL imageUrl = new URL("http://fromtexttospeech.com" + linkHref);
			            URLConnection ucon = imageUrl.openConnection();
			            InputStream is = ucon.getInputStream();
			            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			            byte[] buffer = new byte[4096];
			            int read = 0;
			            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
			                baos.write(buffer, 0, read);
			            }
			            baos.flush();
			            //convert audio to base64. This is necesary because update chrome version
			            encodedFile = "data:audio/mp3;base64,"+ Base64.getEncoder().encodeToString(baos.toByteArray());
			    		resultJSONObject.put("status", "success");
			    		resultJSONObject.put("url", encodedFile);
			    	}else {
			    		resultJSONObject.put("status", "error");
			    		resultJSONObject.put("url", encodedFile);
			    	}	
			    }else {
			    	LOG.error("Not found element html");
			    }
			} catch (Exception e) {
				LOG.error("There was a error get url from http://fromtexttospeech.com", e);
			} finally {
				in.close();
				inputStream.close();
				writer.write(resultJSONObject.toString());
				writer.flush();
				writer.close();
			}
		}
		super.serveResource(resourceRequest, resourceResponse);
	}

	
	/**
	 * Log of the class
	 */
	private static final Log LOG = LogFactoryUtil.getLog(VozmeIntegrationWebPortlet.class);
}