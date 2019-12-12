package es.aragon.base.language_resource_bundle;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Asier
 */
@Component(
	immediate = true,
	property = {
			"language.id="
		},
	service = ResourceBundle.class
)
public class DefaultResourceBundle extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		return resourceBundle.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return resourceBundle.getKeys();
	}
	
	private final ResourceBundle resourceBundle = ResourceBundle.getBundle("content.Language", UTF8Control.INSTANCE);

}
