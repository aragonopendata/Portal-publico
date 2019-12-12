package es.aragon.base.portal.utilities.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Map;
import java.util.TreeMap;

public class AragonPortalUtilitiesMapHierarchy<T> {

	Object node;
	
	Map<String, AragonPortalUtilitiesMapHierarchy<T>> hierachy;
	Map<String, T> linkedData;
	
	public AragonPortalUtilitiesMapHierarchy() {
		this(null);
	}
	
	public AragonPortalUtilitiesMapHierarchy(Object object) {
		this.node = object;
		this.hierachy = new TreeMap<>();
		this.linkedData = new TreeMap<>();
	}

	public Object getNode() {
		return node;
	}

	public void setNode(Object node) {
		this.node = node;
	}

	public Map<String, AragonPortalUtilitiesMapHierarchy<T>> getHierachy() {
		return hierachy;
	}

	public void setHierachy(Map<String, AragonPortalUtilitiesMapHierarchy<T>> hierachy) {
		this.hierachy = hierachy;
	}

	public Map<String, T> getLinkedData() {
		return linkedData;
	}

	public void setLinkedData(Map<String, T> journalArticles) {
		this.linkedData = journalArticles;
	}
	
	public AragonPortalUtilitiesMapHierarchy<T> putIfNotExistsHierachyAndReturn(String key, Object node) {
		if(!this.hierachy.containsKey(key)) {
			this.hierachy.put(key, new AragonPortalUtilitiesMapHierarchy<T>(node));
		}
		return this.hierachy.get(key);
	}
	
	public void putIfNotExistsLinkedData(T linkedData) {
		if(linkedData instanceof JournalArticle) {
			JournalArticle key = (JournalArticle) linkedData;
			if(!this.linkedData.containsKey(key.getTitle(LocaleUtil.getDefault()) + key.getUuid())) {
				this.linkedData.put(key.getTitle(LocaleUtil.getDefault()) + key.getUuid(), linkedData);
			}
		} else if(linkedData instanceof DLFileEntry) {
			DLFileEntry key = (DLFileEntry) linkedData;
			if(!this.linkedData.containsKey(key.getFileName() + key.getUuid())) {
				this.linkedData.put(key.getFileName() + key.getUuid(), linkedData);
			}
		}
	}
}
