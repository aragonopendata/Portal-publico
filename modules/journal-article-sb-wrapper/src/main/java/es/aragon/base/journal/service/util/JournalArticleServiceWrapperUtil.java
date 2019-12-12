package es.aragon.base.journal.service.util;

import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalArticleServiceWrapperUtil {

	public static boolean isNumeric(String numString) {  
	    return numString != null && numString.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public static String convertDateFormat(Date date) {
		DateFormat df = new SimpleDateFormat("'a-'yyyy/'m-'MM/'d-'dd");
		return df.format(date);
	}
	
	public static String fetchFriendlyUrlByArticle(JournalArticle journalArticle){
		String articleFriendlyUrl = StringPool.BLANK;
		if(journalArticle.getExpandoBridge().hasAttribute("articleFriendlyUrl")){
			articleFriendlyUrl = (String)journalArticle.getExpandoBridge().getAttribute("articleFriendlyUrl", false);
			if(!articleFriendlyUrl.isEmpty()){
				return articleFriendlyUrl;
			}
		}
		return articleFriendlyUrl;
	}
	
	public static String removeHtmlTags(String html){
		html = HtmlUtil.getHtml().extractText(html);
		return html;
	}
}
