package es.aragon.base.aragon_most_visited_pages.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;
import es.aragon.base.aragon_most_visited_pages.service.MostVisitedPageLocalServiceUtil;

public class MostVisitedPagesUtil {
	
	public static void addBBDD() {
		HashMap<String, Integer> listPages = createListPages();
		Set<String> pageTitles = new HashSet<>();
		Iterator it = listPages.entrySet().iterator();
		Map.Entry pathKey = null;
		boolean existPages = false;
		List<MostVisitedPage> mostVisitedPageList = MostVisitedPageLocalServiceUtil.getMostVisitedPages(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		String url = "https://www.aragon.es";
		String tit="";
		int count = 0;
		URL u = null;
		HttpURLConnection conn=null;
		int code=0;
		
		if(Validator.isNotNull(mostVisitedPageList)){
			for (MostVisitedPage mostVisitedPage : mostVisitedPageList) {
				try {
					MostVisitedPageLocalServiceUtil.deleteMostVisitedPage(mostVisitedPage.getMostVisitedPageId());
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
		while (it.hasNext() && count < 10) {
			pathKey = (Map.Entry) it.next();
			try {
				tit = ReturnTitleUtil.getPageTitle(url + pathKey.getKey());
				if(!pageTitles.contains(tit)) {
					u = new URL(url+pathKey.getKey());
					conn = (HttpURLConnection) u.openConnection();
					code = conn.getResponseCode();
					if((!tit.contains("302")) && (!tit.contains("Error")) && (!tit.equals("")) && (!tit.contains("301")) && code == 200){
						MostVisitedPageLocalServiceUtil.addMostVisitedPage(pathKey.getKey().toString(), Integer.parseInt(pathKey.getValue().toString()), tit, count);
						pageTitles.add(tit);
						count++;
					}
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	public static String readJsonFromUrl() {
		//String urlString="https://opendata.aragon.es/gapi/get_pages_elastic?format=json&portal=www.aragon.es&days=now-1d%2Fd";    				
		String urlString = "https://opendata.aragon.es/gapi/export_pages?extension=json&portal=24&days=now-7d%2Fd";
		String content = "";
		try {
			URL url = new URL(urlString);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();
			content = stringBuilder.toString();
		} catch (Exception e) {
			e.getMessage();
		}
		return content;
	}
	public static HashMap<String, Integer> createListPages() {
		HashMap<String, Integer> listPages = new HashMap();
		String content = readJsonFromUrl(); 
		try {
			if (content != null && content != "") {
				JSONObject rootJsonObject = JSONFactoryUtil.createJSONObject(content);
				JSONArray jsonArray = rootJsonObject.getJSONArray("pages");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String path = jsonObject.getString("path");
					if(path.startsWith("//")) {
						path= path.replaceAll("//", "/");
					}
					int visits = jsonObject.getInt("visits");
					if ((!path.contains(".pdf")) && !(path.equals("/"))) {
						if (!listPages.containsKey(path)) {
							listPages.put(path, visits);
						} else {
							visits = visits + listPages.get(path);
							listPages.put(path, visits);
						}
					}
				}
			}
		} catch (JSONException e) {
			e.getMessage();
		}
		return sortByValue(listPages);
		
	}
	private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<>(hm.entrySet());
		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
	
	
}
