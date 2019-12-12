package es.aragon.base.language_resource_bundle;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author pfalcon
 * ResourceBundle para inglés
 */
@Component(
	immediate = true, property = "language.id=en_US",
	service = ResourceBundle.class
)
public class EnglishResourceBundle extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		return _resourceBundle.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return _resourceBundle.getKeys();
	}
	
	/**
	 * Bundle obtenido del fichero de lenguaje para inglés
	 */
	private final ResourceBundle _resourceBundle = ResourceBundle.getBundle("content.Language_en_US", UTF8Control.INSTANCE);

}
