package ckeditor.hook.module;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = DynamicInclude.class)
public class CKEditorDynamicInclude implements DynamicInclude {

	private static Log log = LogFactoryUtil.getLog(CKEditorDynamicInclude.class);
	
	private BundleContext bundleContext;
	
	private Boolean hasPermission;
	
	private static final String IFRAME_ROL = "Gestor de Aplicaciones";
	
	// This method retrieve the bundle containing your custom JS file. Retrieve the JS file as a URL and inject its contents into de editor.
	@Override
	public void include(HttpServletRequest request, HttpServletResponse response, String key) {
		//CHECK IFRAME PERMISSION
		hasPermission = false;
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		//Omniadmin can add an iframe to the page
		if (permissionChecker.isOmniadmin()) {
			hasPermission = true;
		}
		//Gestor de Aplicaciones can add an iframe to the page
		List<Role> userRoles = themeDisplay.getUser().getRoles();
		if (userRoles != null && !userRoles.isEmpty()) {
			for (Role role : userRoles) {
				if (role.getName() != null && role.getName().equalsIgnoreCase(IFRAME_ROL)) {
					hasPermission = true;
					break;
				}
			}
		}
		Bundle bundle = bundleContext.getBundle();
		try {
			// load our JS file to control ckEditor's events and override them if it's necesary
			loadPluginFiles(response, bundle, "/META-INF/resources/html/editors/ckeditor/extension");
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	private void loadPluginFiles(HttpServletResponse response, Bundle bundle, String path) throws IOException {
		loadPluginFiles(response, bundle, path, "*.js");
	}
	
	private void loadPluginFiles(HttpServletResponse response, Bundle bundle, String path, String filePattern) throws IOException {
		
		Enumeration<URL> entriesEnum = bundle.findEntries(path, filePattern, true);
		
		if (entriesEnum != null) {
			
			while(entriesEnum.hasMoreElements()) {
				URL url = entriesEnum.nextElement();
				
				if (url.getPath().contains("CKEditorOverride.js")) {
					InputStream inputStream = url.openStream();
					inputStream = modifyContent(inputStream);
					StreamUtil.transfer(inputStream, response.getOutputStream(), false);
				}
				else {
					StreamUtil.transfer(url.openStream(), response.getOutputStream(), false);
				}
			}
		}
	}
	
	private InputStream modifyContent(InputStream inputStream) {
		
		// first read file
		StringBuilder inputStreamContentBuilder = new StringBuilder("");
		
		try {
			Reader inputStreamReader = new InputStreamReader(inputStream);
			int data = inputStreamReader.read();
			
			while(data != -1){
				inputStreamContentBuilder.append((char) data);
			    data = inputStreamReader.read();
			}
			
			inputStreamReader.close();
		} catch (IOException e) {
			log.error("Error reading CKEditorOverride.js before passing it to JS side: ", e);
		}
		
		String inputStreamContent = inputStreamContentBuilder.toString();
		
		// modify every needed [TAG]'s
		inputStreamContent = inputStreamContent.replaceAll("TAGHasPermission", hasPermission.toString());
		
		return new ByteArrayInputStream(inputStreamContent.getBytes(Charset.forName("UTF-8")));
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register("com.liferay.frontend.editor.ckeditor.web#ckeditor#onEditorCreate");
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
	    this.bundleContext = bundleContext;
	}
}
