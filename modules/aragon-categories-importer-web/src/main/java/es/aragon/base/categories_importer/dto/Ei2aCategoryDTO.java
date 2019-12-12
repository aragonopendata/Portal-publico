package es.aragon.base.categories_importer.dto;

/**
 * @author pfalcon
 * Category data transfer object to process imported categories from ei2a system.
 */
public class Ei2aCategoryDTO {

	/**
	 * Category title
	 */
	private String title;
	
	/**
	 * Category identifier
	 */
	private String ei2aIdentifier;
	
	/**
	 * Category parent identifier
	 */
	private String ei2aParentIdentifier;
	
	/**
	 * Gets the category title
	 * @return Category title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the category title
	 * @param title Category title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the category identifier
	 * @return Category identifier
	 */
	public String getEi2aIdentifier() {
		return ei2aIdentifier;
	}
	
	/**
	 * Sets the category identifier
	 * @param ei2aIdentifier Category identifier
	 */
	public void setEi2aIdentifier(String ei2aIdentifier) {
		this.ei2aIdentifier = ei2aIdentifier;
	}

	/**
	 * Gets the parent category identifier
	 * @return ei2aParentIdentifier Parent category identifier
	 */
	public String getEi2aParentIdentifier() {
		return ei2aParentIdentifier;
	}

	/**
	 * Sets the parent category identifier
	 * @param ei2aParentIdentifier Parent category identifier
	 */
	public void setEi2aParentIdentifier(String ei2aParentIdentifier) {
		this.ei2aParentIdentifier = ei2aParentIdentifier;
	}
	
}
