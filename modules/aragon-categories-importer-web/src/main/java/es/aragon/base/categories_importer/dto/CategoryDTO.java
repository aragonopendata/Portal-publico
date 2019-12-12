package es.aragon.base.categories_importer.dto;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author pfalcon
 * Category data transfer object to process imported categories from Aragon Open Data.
 */
public class CategoryDTO {
	
	/**
	 * Category identifier
	 */
	private String id;
	
	/**
	 * Parent category identifier
	 */
	private String parentId;
	
	/**
	 * Category name
	 */
	private String name;
	
	/**
	 * Translated title values
	 */
	private Map<Locale, String> titleMap;
	
	/**
	 * Translated description values
	 */
	private Map<Locale, String> descriptionMap;
	
	/**
	 * Custom properties map
	 */
	private Map<String, String> customProperties;
	
	/**
	 * Gets de category identifier
	 * @return Category identifier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the category identifier
	 * @param id Category identifier
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the parent category identifier
	 * @return Parent category identifier
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * Sets the parent category identifier
	 * @param parentId Parent category identifier
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * Gets the category name
	 * @return Category name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the category name
	 * @param name Category name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the translated title values
	 * @return Translated title values
	 */
	public Map<Locale, String> getTitleMap() {
		return titleMap;
	}
	
	/**
	 * Sets the translated title values
	 * @param titleMap Translated title values
	 */
	public void setTitleMap(Map<Locale, String> titleMap) {
		this.titleMap = titleMap;
	}
	
	/**
	 * Gets the translated description values
	 * @return Translated description values
	 */
	public Map<Locale, String> getDescriptionMap() {
		return descriptionMap;
	}
	
	/**
	 * Sets the translated description values
	 * @param descriptionMap The translated description values
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		this.descriptionMap = descriptionMap;
	}
	
	/**
	 * Gets the custom properties map
	 * @return Custom properties map
	 */
	public Map<String, String> getCustomProperties() {
		return customProperties;
	}

	/**
	 * Sets the custom properties map
	 * @param Custom properties map
	 */
	public void setCustomProperties(Map<String, String> customProperties) {
		this.customProperties = customProperties;
	}

	/**
	 * Adds a new property to the custom properties map
	 * @param key Custom property key
	 * @param value Custom property value
	 */
	public void addCustomProperty(String key, String value) {
		Map<String, String> customPropertiesMap = this.customProperties;
		if (customPropertiesMap == null) {
			customPropertiesMap = new HashMap<String, String>();
		}
		customPropertiesMap.put(key, value);
		setCustomProperties(customPropertiesMap);
	}
	
}
