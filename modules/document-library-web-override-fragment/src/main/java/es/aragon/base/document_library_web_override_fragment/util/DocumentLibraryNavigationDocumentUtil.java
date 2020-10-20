package es.aragon.base.document_library_web_override_fragment.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.exception.PortalException;

public class DocumentLibraryNavigationDocumentUtil {
	public String getFullPath2(DLFileEntry file) {
		String fullPath = "";
		if(file != null) {
			String fileName = file.getFileName();
			
			try {
				DLFolder folder = file.getFolder();
				if(!folder.isRoot()) {
					fullPath =  fullPath(folder) + " / " + fileName;
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		return fullPath;
	}
	
	protected String fullPath(DLFolder folder) throws PortalException {
	    String folderName = folder.getName();
	    DLFolder parent = folder.getParentFolder();

	    if (parent == null) {
	        return folderName;
	    }
	    else {
	        return fullPath(parent) + " / "+folderName;
	    }
	}
}
