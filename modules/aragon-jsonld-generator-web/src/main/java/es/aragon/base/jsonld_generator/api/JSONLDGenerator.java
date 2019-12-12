package es.aragon.base.jsonld_generator.api;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;

public interface JSONLDGenerator {

	/**
	 * Obtiene metadatos de la pagina en formato JSON-LD.
	 * @param themeDisplay
	 * @return Informacion global de la pagina en formato JSON-LD.
	 */
	public String getJsonLD(ThemeDisplay themeDisplay);
	
	
	/**
	 * Obtiene metadatos de la pagina en el contexto E2IA. 
	 * @param themeDisplay
	 * @return Informacion de la pagina en el contexto E2IA.
	 */
	public String getE2IAInformation(ThemeDisplay themeDisplay);
	
	/**
	 * Obtiene metadatos de la pagina en el contexto Schema.org. 
	 * @param themeDisplay
	 * @return Informacion de la pagina en el contexto Schema.org.
	 */
	public String getSchemaOrgInformation(ThemeDisplay themeDisplay);
	
	/**
	 * Ofrece una ampliacion del Grafo de Conocimiento de Google para que registre correctamente informacion de la pagina
	 * con otras redes sociales. Se aplican guias correspondientes a contacto corporativo, logo, redes sociales, etc.
	 * @param themeDisplay
	 * @return Informacion adicional en formato JSON-LD
	 */
	public String getKnowledgeGraphExtension(ThemeDisplay themeDisplay);
	
	/**
	 * Obtiene metadatos de los articulos mostrados en el listado de noticias en formato JSON-LD.
	 * @param articles. Articulos de los cuales se extraeran metadatos
	 * @param themeDisplay
	 * @return Informacion del articulo en formato JSON-LD.
	 */
	public String getItemListJsonLD(List<JournalArticle> articles, ThemeDisplay themeDisplay);


	public String getVocabularyFromJsonLD(AssetVocabulary vocabulary, AssetCategory categoryId, Locale locale,
			ThemeDisplay themeDisplay);


	public String getCategoriesJsonLD2(List<AssetCategory> categories, ThemeDisplay themeDisplay);


	public String getJournalArticleJsonLD2(List<AssetCategory> categories, ThemeDisplay themeDisplay);

	
}
