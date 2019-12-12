package es.aragon.base.migration.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Random;

import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.model.ContentMetadata;

public class MigrationUtil {
	
	public static final String[] CONTENT_TYPE_NAMES = {"application/msword", "application/pdf", "application/rtf", "application/vnd.ms-excel", "application/vnd.ms-powerpoint", "application/vnd.ms-word.document.macroEnabled.12", "application/vnd.oasis.opendocument.text", "application/vnd.openxmlformats-officedocument.pres", "application/vnd.openxmlformats-officedocument.spre", "application/vnd.openxmlformats-officedocument.word", "application/x-7z-compressed", "application/x-msdos-program", "application/zip", "chemical/x-cerius", "image/gif", "image/jpeg", "image/png", "text/html"}; 

	public static ModelPermissions getModelPermissions(long companyId, RoleLocalService _roleLocalService){
		ModelPermissions modelPermissions = new ModelPermissions();
		
		Role guestRole = null;
		try {
			guestRole = _roleLocalService.getRole(companyId, RoleConstants.GUEST);
			if(Validator.isNotNull(guestRole)) {
				modelPermissions.addRolePermissions(guestRole.getName(), new String[] {ActionKeys.VIEW});
			}
		} catch (PortalException e) {
			_log.error(e);
		}
		return modelPermissions;
	}
	
	public static String removeSpecialCharacters(String word){
		word = Normalizer.normalize(word, Normalizer.Form.NFD);
		word = word.replaceAll("\\p{M}", "");
		word = word.replaceAll("[\"'\u2018\u2019\u201c\u201d]", "\"");
		word = word.replace("\"", StringPool.BLANK);
		word = word.replace(StringPool.QUESTION, StringPool.BLANK);
		word = word.replace(StringPool.COLON, StringPool.BLANK);
		word = word.trim();
		if(word.lastIndexOf(".") == word.length() - 1){
			word = word.substring(0, word.length() - 1);
		}	
		return word;
	}
	
	public static String unescapeHTML(String htmlDecoded, boolean excludeLineBreak){
		String html = htmlDecoded;
		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&gt;", ">");
		html = html.replaceAll("&quot;", "\"");
		html = html.replaceAll("&amp;", "&");
		html = html.replaceAll("&#225;", "\u00e1");
		html = html.replaceAll("&#233;", "\u00e9");
		html = html.replaceAll("&#237;", "\u00ed");
		html = html.replaceAll("&#243;", "\u00f3");
		html = html.replaceAll("&#250;", "\u00fa");
		html = html.replaceAll("&#193;", "\u00c1");
		html = html.replaceAll("&#201;", "\u00c9");
		html = html.replaceAll("&#205;", "\u00cd");
		html = html.replaceAll("&#211;", "\u00d3");
		html = html.replaceAll("&#218;", "\u00da");
		html = html.replaceAll("&#241;", "\u00f1");
		html = html.replaceAll("&#209;", "\u00d1");
		html = html.replaceAll("&#250;", "\u00fc");
		html = html.replaceAll("&#220;", "\u00dc");
		html = html.replaceAll("&#191;", "\u00bf");
		
		if(!excludeLineBreak){
			html = html.replaceAll("<BR>", StringPool.BLANK);
			html = html.replaceAll("<br />", StringPool.BLANK);
		}
		return html;
	}
	
	public static String decodeUTF8FromURL(String url) {
		
		url = url.replace("%20", StringPool.SPACE);
		url = url.replace("%22", StringPool.DOUBLE_QUOTE);
		url = url.replace("%2C", StringPool.COMMA);
		url = url.replace("%2E", StringPool.PERIOD);
		url = url.replace("%3A", StringPool.COLON);
		url = url.replace("%3F", StringPool.QUESTION);
		url = url.replace("%BF", StringPool.INVERTED_QUESTION);
		url = url.replace("%C3%81", "\u00c1");
		url = url.replace("%C3%89", "\u00c9");
		url = url.replace("%C3%8D", "\u00cd");
		url = url.replace("%C3%93", "\u00d3");
		url = url.replace("%C3%9A", "\u00da");
		url = url.replace("%C3%A1", "\u00e1");
		url = url.replace("%C3%A9", "\u00e9");
		url = url.replace("%C3%AD", "\u00ed");
		url = url.replace("%C3%B3", "\u00f3");
		url = url.replace("%C3%BA", "\u00fa");
		url = url.replace("%C3%B1", "\u00f1");
		url = url.replace("%C3%91", "\u00d1");
		
		return url;
	}
	
	public static String generateXMLContent(DDMStructure ddmStructure, ContentMetadata contentMetadata, Content content,
			String siteDefaultLanguageId) {
		
		String xmlContentResult = StringPool.BLANK;
		String originalBody = content.getOriginalBody();
		originalBody = originalBody.replaceAll("class=\\\"[a-zA-Z0-9\\-: ;]*\\\"", "");
		originalBody = originalBody.replaceAll("style=\\\"[a-zA-Z0-9\\-: ;]*\\\"", "");
		originalBody = originalBody.replaceAll("class=\\'[a-zA-Z0-9\\-: ;]*\\'", "");
		originalBody = originalBody.replaceAll("style=\\'[a-zA-Z0-9\\-: ;]*\\'", "");
		
		Document document = SAXReaderUtil.createDocument();
		Element rootElement = document.addElement("root");
		rootElement.addAttribute("available-locales", "es_ES");
		rootElement.addAttribute("default-locale", siteDefaultLanguageId);
		
		try {
			
			// Cuerpo principal
			{
				Element dynamicElementElement = generateDynamicElementElement(ddmStructure, rootElement, "cuerpo_principal");
				Element dynamicContentElement = dynamicElementElement.addElement("dynamic-content");
				dynamicContentElement.addAttribute("language-id", siteDefaultLanguageId);
				if(Validator.isNotNull(content.getOriginalBody())){

					dynamicContentElement.addCDATA(originalBody);
				}else {
					dynamicContentElement.addCDATA("");
				}
			}
			
			// Imagen principal
			{
				Element dynamicElementElement = generateDynamicElementElement(ddmStructure, rootElement, "imagen_principal");
				Element dynamicContentElement = dynamicElementElement.addElement("dynamic-content");
				dynamicContentElement.addAttribute("language-id", siteDefaultLanguageId);
				String shortDescription = StringPool.BLANK;
				String longDescription = StringPool.BLANK;
				
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getPrimaryImage())) {
					try {
						JSONObject jsonImageOrigin = JSONFactoryUtil.createJSONObject(contentMetadata.getPrimaryImage());
						String fileEntryIdStr = jsonImageOrigin.getString("fileEntryId");
						String alt = jsonImageOrigin.getString("alt");
						shortDescription = jsonImageOrigin.getString("short-description");
						longDescription = jsonImageOrigin.getString("long-description");
						
						if(Validator.isNotNull(fileEntryIdStr)) {
							DLFileEntry image = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(fileEntryIdStr));
							if(Validator.isNotNull(image)) {
								JSONObject jsonObjectResult = JSONFactoryUtil.createJSONObject();
								jsonObjectResult.put("groupId", image.getGroupId());
								jsonObjectResult.put("name", image.getFileName());
								jsonObjectResult.put("alt", alt);
								jsonObjectResult.put("title", image.getFileName());
								jsonObjectResult.put("type", "document");
								jsonObjectResult.put("uuid", image.getUuid());
								jsonObjectResult.put("fileEntryId", image.getFileEntryId());
								dynamicContentElement.addCDATA(jsonObjectResult.toJSONString());
							}else {
								_log.info("No se ha encontrado la imagen principal");
							}
						}
					} catch (JSONException e) {
						_log.error("Ha ocurrido un error al procesar el json de la imagen principal. " + e);
					}
				}else {
					dynamicContentElement.addCDATA("");
				}
				
				// Pie de la imagen principal
				Element subDynamicElementShortDescription = generateDynamicElementElement(ddmStructure, dynamicElementElement, "pie_imagen_principal");
				Element subDynamicContentShortDescription = subDynamicElementShortDescription.addElement("dynamic-content");
				subDynamicContentShortDescription.addCDATA(shortDescription);
				
				// Descripcion extensa de la imagen principal
				Element subDynamicElementLongDescription = generateDynamicElementElement(ddmStructure, dynamicElementElement, "descripcion_imagen_principal");
				Element subDynamicContentLongDescription = subDynamicElementLongDescription.addElement("dynamic-content");
				subDynamicContentLongDescription.addCDATA(longDescription);
			}
			
			// Video principal
			{
				Element dynamicElementElement = generateDynamicElementElement(ddmStructure, rootElement, "video_principal");
				Element dynamicContentElement = dynamicElementElement.addElement("dynamic-content");
				dynamicContentElement.addAttribute("language-id", siteDefaultLanguageId);
				String shortDescription = StringPool.BLANK;
				String longDescription = StringPool.BLANK;
				
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getPrimaryVideo())) {
					try {
						JSONObject jsonImageOrigin = JSONFactoryUtil.createJSONObject(contentMetadata.getPrimaryVideo());
						String url = jsonImageOrigin.getString("url");
						shortDescription = jsonImageOrigin.getString("short-description");
						longDescription = jsonImageOrigin.getString("long-description");
						
						dynamicContentElement.addCDATA(url);
					} catch (JSONException e) {
						_log.error("Ha ocurrido un error al procesar el json del video principal. " + e);
					}
				}else {
					dynamicContentElement.addCDATA("");
				}
				
				// Pie del video principal
				Element subDynamicElementShortDescription = generateDynamicElementElement(ddmStructure, dynamicElementElement, "pie_video_principal");
				Element subDynamicContentShortDescription = subDynamicElementShortDescription.addElement("dynamic-content");
				subDynamicContentShortDescription.addCDATA(shortDescription);
				
				// Descripcion extensa del video principal
				Element subDynamicElementLongDescription = generateDynamicElementElement(ddmStructure, dynamicElementElement, "descripcion_video_principal");
				Element subDynamicContentLongDescription = subDynamicElementLongDescription.addElement("dynamic-content");
				subDynamicContentLongDescription.addCDATA(longDescription);
			}
			
			// Cuerpo secundario
			{
				Element dynamicElementElement = generateDynamicElementElement(ddmStructure, rootElement, "cuerpo_secundario");
				Element dynamicContentElement = dynamicElementElement.addElement("dynamic-content");
				dynamicContentElement.addAttribute("language-id", siteDefaultLanguageId);
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getSecondaryBody())) {
					dynamicContentElement.addCDATA(unescapeHTML(contentMetadata.getSecondaryBody(), true));
				}else {
					dynamicContentElement.addCDATA("");
				}
			}
			
			// Galeria de imagenes
			{
				Element dynamicElementElement = generateDynamicElementElement(ddmStructure, rootElement, "galeria_contenidos_titulo");
				Element dynamicContentElement = dynamicElementElement.addElement("dynamic-content");
				dynamicContentElement.addAttribute("language-id", siteDefaultLanguageId);
				
				// Titulo de la galeria de imagenes
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getImagesGalleryTitle())) {
					dynamicContentElement.addCDATA(contentMetadata.getImagesGalleryTitle());
				}else {
					dynamicContentElement.addCDATA("");
				}
				
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getImagesGalleryImages())) {
					try {
						JSONArray imagesOriginJsonArray = JSONFactoryUtil.createJSONArray(contentMetadata.getImagesGalleryImages());
						if(Validator.isNotNull(imagesOriginJsonArray)) {
							for(int i = 0; i < imagesOriginJsonArray.length(); i++) {
								JSONObject jsonImageOrigin = imagesOriginJsonArray.getJSONObject(i);
								String fileEntryIdStr = jsonImageOrigin.getString("fileEntryId");
								String alt = jsonImageOrigin.getString("alt");
								
								if(Validator.isNotNull(fileEntryIdStr)) {
									DLFileEntry image = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(fileEntryIdStr));
									if(Validator.isNotNull(image)) {
										JSONObject jsonObjectResult = JSONFactoryUtil.createJSONObject();
										jsonObjectResult.put("groupId", image.getGroupId());
										jsonObjectResult.put("name", image.getFileName());
										jsonObjectResult.put("alt", alt);
										jsonObjectResult.put("title", image.getFileName());
										jsonObjectResult.put("type", "document");
										jsonObjectResult.put("uuid", image.getUuid());
										jsonObjectResult.put("fileEntryId", image.getFileEntryId());
										
										// Imagen de la galeria de imagenes
										Element subDynamicElementImage = generateDynamicElementElement(ddmStructure, dynamicElementElement, "galeria_imagenes_imagen");
										Element subDynamicContentImage = subDynamicElementImage.addElement("dynamic-content");
										subDynamicContentImage.addCDATA(jsonObjectResult.toJSONString());
										
									}else {
										_log.info("No se ha encontrado la imagen principal");
									}
								}
							}
						}
						
					} catch (JSONException e) {
						_log.error("Ha ocurrido un error al procesar el json de la galeria de imagenes. " + e);
					}
				}else {
					// Si no hay imagenes, se debe meter un valor vacio
					Element subDynamicElementImage = generateDynamicElementElement(ddmStructure, dynamicElementElement, "galeria_imagenes_imagen");
					Element subDynamicContentImage = subDynamicElementImage.addElement("dynamic-content");
					subDynamicContentImage.addCDATA("");
				}
			}
			
			// Titulo de la lista de enlances a documentos
			{
				Element dynamicElementElement = generateDynamicElementElement(ddmStructure, rootElement, "listado_enlaces_titulo");
				Element dynamicContentElement = dynamicElementElement.addElement("dynamic-content");
				dynamicContentElement.addAttribute("language-id", siteDefaultLanguageId);
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getLinksListTitle())) {
					dynamicContentElement.addCDATA(contentMetadata.getLinksListTitle());
				}else {
					dynamicContentElement.addCDATA("");
				}
				
				if(Validator.isNotNull(contentMetadata) && Validator.isNotNull(contentMetadata.getLinksListLinks())) {
					try {
						JSONArray listLinksOriginJsonArray = JSONFactoryUtil.createJSONArray(contentMetadata.getLinksListLinks());
						if(Validator.isNotNull(listLinksOriginJsonArray)) {
							for(int i = 0; i < listLinksOriginJsonArray.length(); i++) {
								JSONObject jsonLinkOrigin = listLinksOriginJsonArray.getJSONObject(i);
								String fileEntryIdStr = jsonLinkOrigin.getString("fileEntryId");
								String titleDoc = jsonLinkOrigin.getString("title");
								String urlDoc = jsonLinkOrigin.getString("url");
								
								// Titulo de enlace
								Element subDynamicElementLinkTitle = generateDynamicElementElement(ddmStructure, dynamicElementElement, "titulo_enlace");
								Element subDynamicContentLinkTitle = subDynamicElementLinkTitle.addElement("dynamic-content");
								if(Validator.isNotNull(titleDoc)) {
									subDynamicContentLinkTitle.addCDATA(titleDoc);
								}else {
									subDynamicContentLinkTitle.addCDATA("");
								}
								
								if(Validator.isNotNull(fileEntryIdStr)) {
									DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(fileEntryIdStr));
									if(Validator.isNotNull(fileEntry)) {
										JSONObject jsonObjectResult = JSONFactoryUtil.createJSONObject();
										jsonObjectResult.put("groupId", fileEntry.getGroupId());
										jsonObjectResult.put("title", fileEntry.getFileName());
										jsonObjectResult.put("type", "document");
										jsonObjectResult.put("uuid", fileEntry.getUuid());
										
										// Documento
										Element subDynamicElementDocument = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "documento");
										Element subDynamicContentDocument = subDynamicElementDocument.addElement("dynamic-content");
										subDynamicContentDocument.addCDATA(jsonObjectResult.toJSONString());
										
										// Enlace vacio (si existe un documento, el enlace debera ser vacio)
										Element subDynamicElementLinkUrl = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_url");
										Element subDynamicContentLinkUrl = subDynamicElementLinkUrl.addElement("dynamic-content");
										subDynamicContentLinkUrl.addCDATA("");
										
										// Tipo (1 si es enlace, 2 si es documento)
										Element subDynamicElementTypeDoc = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_tipo");
										Element subDynamicContentTypeDoc = subDynamicElementTypeDoc.addElement("dynamic-content");
										subDynamicContentTypeDoc.addCDATA("2");
										
									}else {
										_log.info("No se ha encontrado un documento de la lista de enlaces");
									}
								}else if(Validator.isNotNull(urlDoc)) {
									
									// Documento
									Element subDynamicElementDocument = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "documento");
									Element subDynamicContentDocument = subDynamicElementDocument.addElement("dynamic-content");
									subDynamicContentDocument.addCDATA("");
									
									// Enlace vacio (si existe un documento, el enlace debera ser vacio)
									Element subDynamicElementLinkUrl = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_url");
									Element subDynamicContentLinkUrl = subDynamicElementLinkUrl.addElement("dynamic-content");
									subDynamicContentLinkUrl.addCDATA(urlDoc);
									
									// Tipo (1 si es enlace, 2 si es documento)
									Element subDynamicElementTypeDoc = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_tipo");
									Element subDynamicContentTypeDoc = subDynamicElementTypeDoc.addElement("dynamic-content");
									subDynamicContentTypeDoc.addCDATA("1");
								
								}else {
									// Si no hay lista de enlaces se rellenan los campos con valores vacios
									
									// Documento
									Element subDynamicElementDocument = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "documento");
									Element subDynamicContentDocument = subDynamicElementDocument.addElement("dynamic-content");
									subDynamicContentDocument.addCDATA("");
									
									// Enlace vacio (si existe un documento, el enlace debera ser vacio)
									Element subDynamicElementLinkUrl = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_url");
									Element subDynamicContentLinkUrl = subDynamicElementLinkUrl.addElement("dynamic-content");
									subDynamicContentLinkUrl.addCDATA("");
									
									// Tipo (1 si es enlace, 2 si es documento)
									Element subDynamicElementTypeDoc = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_tipo");
									Element subDynamicContentTypeDoc = subDynamicElementTypeDoc.addElement("dynamic-content");
									subDynamicContentTypeDoc.addCDATA("");
								}
							}
						}
						
					} catch (JSONException e) {
						_log.error("Ha ocurrido un error al procesar el json de los enlaces. " + e);
					}
				}else {
					// Si no hay enlaces, se deben meter valores vacios
					
					// Titulo de enlace
					Element subDynamicElementLinkTitle = generateDynamicElementElement(ddmStructure, dynamicElementElement, "titulo_enlace");
					Element subDynamicContentLinkTitle = subDynamicElementLinkTitle.addElement("dynamic-content");
					subDynamicContentLinkTitle.addCDATA("");
					
					// Documento
					Element subDynamicElementDocument = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "documento");
					Element subDynamicContentDocument = subDynamicElementDocument.addElement("dynamic-content");
					subDynamicContentDocument.addCDATA("");
					
					// Enlace vacio (si existe un documento, el enlace debera ser vacio)
					Element subDynamicElementLinkUrl = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_url");
					Element subDynamicContentLinkUrl = subDynamicElementLinkUrl.addElement("dynamic-content");
					subDynamicContentLinkUrl.addCDATA("");
					
					// Tipo (1 si es enlace, 2 si es documento)
					Element subDynamicElementTypeDoc = generateDynamicElementElement(ddmStructure, subDynamicElementLinkTitle, "enlace_tipo");
					Element subDynamicContentTypeDoc = subDynamicElementTypeDoc.addElement("dynamic-content");
					subDynamicContentTypeDoc.addCDATA("");
				}
			}
			
			xmlContentResult = document.asXML().toString();
			
		} catch (PortalException e) {
			_log.error("Error al generar el contenido XML del articulo. " + e);
		}
		
		return xmlContentResult;
	}
	
	public static Element generateDynamicElementElement(DDMStructure ddmStructure, Element rootElement, 
			String fieldName) throws PortalException{
		String fieldType = ddmStructure.getFieldType(fieldName);
		String indexType = ddmStructure.getFieldProperty(fieldName, "indexType");
		String instanceId = generateRandomInstanceId();
		
		Element dynamicElementElement = rootElement.addElement("dynamic-element");
		dynamicElementElement.addAttribute("name", fieldName);
		dynamicElementElement.addAttribute("type", fieldType);
		dynamicElementElement.addAttribute("index-type", indexType);
		dynamicElementElement.addAttribute("instance-id", instanceId);
		
		return dynamicElementElement;
	}
	
	public static String generateRandomInstanceId() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new SecureRandom();
		StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
	}
	
	/**
	 * Convierte un array de objetos Long a primitivo
	 * @param array Array de objetos Long
	 * @return Array de longs
	 */
	public static long[] toPrimitive(Long[] array) {
        final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].longValue();
        }
        return result;
    }
	
	public static InputStream getInputStreamFromURL(URL url) {
		
		InputStream is = null;
		try {
			is = url.openStream ();
		} catch (IOException e) {
			_log.error(e);
		}
		return is;
	}
	
	public static String modifyUrl(String url) {
		String part1 = url.substring(0, url.indexOf("aragon.es"));
		String part2 = url.substring(part1.length(), url.length());
		String result = part1 + "www." + part2;
		return result;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(MigrationUtil.class.getName());
}
