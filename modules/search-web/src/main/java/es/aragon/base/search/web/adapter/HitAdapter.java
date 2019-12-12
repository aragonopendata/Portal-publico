package es.aragon.base.search.web.adapter;

import com.liferay.petra.string.StringPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Class used to adapt every search's hit to show it's information
 * @author Mikel Jorge
 *
 */
public class HitAdapter {
	
	private final String PATTERN = "dd/MM/yyyy";
	
	private String title;
	private String displayDate;
	private String url;
	private String assetEntryId;
	private String target;
	private List<String> categories;
	private String className;
	
	public HitAdapter(String title, Date date, String url, long assetEntryId, String target, List<String> categories, String className) {
		this.title = title;
		if(date != null) {
			DateFormat df = new SimpleDateFormat(PATTERN); 
			df.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
			this.displayDate = df.format(date);
		} else {
			this.displayDate = StringPool.BLANK;
		}
		this.url = url;
		this.assetEntryId = String.valueOf(assetEntryId);
		this.target = target;
		this.categories = categories;
		this.className = className;
	}

	public String getTitle() {
		return title;
	}

	public String getDisplayDate() {
		return displayDate;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getAssetEntryId() {
		return assetEntryId;
	}
	
	public String getTarget() {
		return target;
	}
	
	public List<String> getCategories() {
		return categories;
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
}
