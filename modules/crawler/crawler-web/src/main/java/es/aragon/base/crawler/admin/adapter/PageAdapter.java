package es.aragon.base.crawler.admin.adapter;

/**
 * 
 * @author Mikel Jorge
 *
 */
public class PageAdapter {

	// page title
	private String title;
	
	// page content type
	private String contentType;
	
	// page meta description
	private String metaDescription;
	
	// page meta keywords
	private String metaKeywords;
	
	// page content
	private String content;
	
	// page url
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
