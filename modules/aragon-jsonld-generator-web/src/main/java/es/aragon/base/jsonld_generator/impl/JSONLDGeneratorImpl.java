package es.aragon.base.jsonld_generator.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalContentSearch;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalContentSearchLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.web.constants.SearchPortletKeys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.aragon_utilities.constants.AragonUtilitiesConstant;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalService;
import es.aragon.base.categories_custom_properties.service.CustomCategoryPropertyLocalServiceUtil;
import es.aragon.base.freemarker_utilities.api.FreemarkerUtilities;
import es.aragon.base.jsonld_generator.api.JSONLDGenerator;

@Component(
		immediate = true, 
		service = JSONLDGenerator.class
	)
public class JSONLDGeneratorImpl implements JSONLDGenerator {
	
	@Override
	public String getJsonLD(ThemeDisplay themeDisplay) {
		String result = "[\n";
		
		result = result + getE2IAInformation(themeDisplay) + StringPool.COMMA;
		result = result + getSchemaOrgInformation(themeDisplay) + StringPool.COMMA;
		result = result + getKnowledgeGraphExtension(themeDisplay);
		
		result = result + "]";
		
		return result;
	}
	
//	@Override
//	public String getJournalArticleJsonLD(JournalArticle article, List<AssetCategory> categories, String imageUrl, ThemeDisplay themeDisplay) {
//		
//		List<String> articleCategories = getCategoriesUrlIds(categories);
//		String publicationDate = getDateFormat(article.getDisplayDate());
//		String modifiedDate = getDateFormat(article.getModifiedDate());
//		String portalURL = PortalUtil.getPortalURL(themeDisplay.getRequest());
//		String logoUrl = portalURL + "/o/aragon-theme/images/dga/logo-dga-color.svg";
//		imageUrl = portalURL + imageUrl;
//		String title = article.getTitle(themeDisplay.getLocale());
//		String url = portalURL + themeDisplay.getLayout().getFriendlyURL(themeDisplay.getLocale());
//		if(title.length() > 110) {
//			title = title.substring(0, 110);
//		}
//		
//		String result = "{\n";
//		
//		// Contexto Schema
//		result = result + "\"" + JSONLD_CONTEXT + "\": \"http://schema.org/\", \n";
//		
//		// Tipo de documento : newsArticle o content. TODO: en funcion del tipo de documento tendremos que coger su identificador de opendata seguramente
//		String documentType = "WebPage";
//		List<AssetCategory> journalCategories = _assetCategoryLocalService.getCategories(JournalArticle.class.getName(), article.getResourcePrimKey());
//		if(categories!=null && categories.size()>0) {
//			for(AssetCategory assetCategory : journalCategories) {
//				try {
//					if(assetCategory.getName().toLowerCase().contains("anuncio")) {
//						documentType = "NewsArticle";
//					}
//				} catch (Exception e) {
//					_log.error("Imposible de procesar la categoria "+assetCategory.getCategoryId());
//				}
//			}
//		}
//		
//		result = result + "\"" + JSONLD_TYPE + "\": \""+documentType+"\", \n";
//		
//		// Titular
//		result = result + "\"" + SCHEMA_HEADLINE + "\": \"" + title.replace("\"", "\'") + "\", \n";
//		
//		// Autor del articulo
//		result = result + "\"" + SCHEMA_AUTOR + "\": { \n";
//		result = result + "\t\"" + JSONLD_TYPE + "\": \"GovernmentOrganization\",\n";
//		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
//		result = result + "\t\"" + JSONLD_NAME + "\": \"Gobierno de Arag\u00f3n\"},\n";
//		
//		// Fecha de publicacion del articulo
//		result = result + "\"" + SCHEMA_DATE_PUBLISHED + "\": \"" + publicationDate + "\", \n";
//		
//		// Fecha de modificacion del articulo
//		result = result + "\"" + SCHEMA_DATE_MODIFIED + "\": \"" + modifiedDate + "\", \n";
//		
//		// Imagen del articulo
//		if(Validator.isNotNull(imageUrl) && imageUrl != "") {
//			result = result + "\"" + SCHEMA_IMAGE + "\": \"" + imageUrl + "\", \n";
//		}
//		
//		// Pagina para la que este articulo es la principal entidad descrita
//		result = result + "\"" + SCHEMA_MAIN_ENTITY_OF_PAGE + "\": { \n";
//		result = result + "\t\"" + JSONLD_TYPE + "\": \"WebPage\",\n";
//		result = result + "\t\"" + JSONLD_ID + "\": \"" + url + "#page-title" + "\"},\n";
//		//result = result + "\t\"" + JSONLD_NAME + "\": \"" + title + "\"},\n";
//		
//		result = result + "\"" + SCHEMA_MAIN_ENTITY + "\": { \n";
//		result = result + "\t\"" + JSONLD_TYPE + "\": \"WebPage\",\n";
//		result = result + "\t\"" + JSONLD_ID + "\": \"" + url + "\",\n";
//		result = result + "\t\"" + JSONLD_NAME + "\": \"" + title.replace("\"", "\'") + "\"},\n";
//		
//		// Publicador
//		result = result + "\"" + SCHEMA_PUBLISHER + "\": { \n";
//		result = result + "\t\"" + JSONLD_TYPE + "\": \"GovernmentOrganization\",\n";
//		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
//		
//		if(Validator.isNotNull(logoUrl) && logoUrl != "") {
//            result = result + "\"" + SCHEMA_LOGO + "\": { \n";
//            result = result + "\t\"" + JSONLD_TYPE + "\": \"ImageObject\",\n";
//            result = result + "\t\"" + SCHEMA_URL + "\": \""+ logoUrl +"\"},\n";
//        }
//		result = result + "\t\"" + JSONLD_NAME + "\": \"Gobierno de Arag\u00f3n\"}\n";
//		
//		// Menciones (categorias del articulo)
//		if(Validator.isNotNull(articleCategories) && articleCategories.size() > 0) {
//			result = result + ",\n";
//			result = result + "\"" + SCHEMA_MENTIONS + "\": [ \n";
//			for (int i = 0; i < articleCategories.size(); i++) {
//				if(i == articleCategories.size()-1) {
//					result = result + "{\"" + JSONLD_ID + "\": \"" + articleCategories.get(i) + "\"}\n";
//				}else {
//					result = result + "{\"" + JSONLD_ID + "\": \"" + articleCategories.get(i) + "\"},\n";
//				}
//			}
//			result = result + "]\n";
//		}
//		
//		result = result + "}";
//		
//		return result;
//	}
	@Override
	public String getJournalArticleJsonLD2(List<AssetCategory> categories, ThemeDisplay themeDisplay) {
		String portalUrl =	PortalUtil.getPortalURL(themeDisplay.getRequest());
		String result = StringPool.BLANK;
		//Get id vocabularies
		AssetVocabulary vocabularyOrganization = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
		AssetVocabulary vocabularyTopic = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
		long idOrganization = (vocabularyOrganization != null) ? vocabularyOrganization.getVocabularyId() : -1 ;
		long idTopic = (vocabularyTopic != null) ? vocabularyTopic.getVocabularyId() : -1 ;
		result = result + "[{\r\n" ; 
		result = result + "\"@context\": {\r\n";
		result = result + "\"ei2a\": \"http://opendata.aragon.es/def/e2ia#\",\r\n" ; 
		result = result + "\"dct\": \"http://purl.org/dc/terms/\",\r\n" ; 
		result = result + "\"dcmi\": \"http://purl.org/dc/dcmitype/\",\r\n" ; 
		result = result + "\"owl\": \"http://www.w3.org/2002/07/owl#\"\r\n" ; 
		result = result + "},\r\n" ; 
		result = result + "\"@type\": [\"ei2a:Document\", \"dcmi:InteractiveResource\"],\r\n" ; 
		result = result + "\"owl:sameAs\": {\r\n" ;
		result = result + "\"@id\": \"\"\r\n" ;
		result = result + "},\r\n" ; 
		String relation = "\"dct:relation\" : [\r\n";
		String subject = "\"dct:subject\" :[\r\n";
		if (!categories.isEmpty()) {
			for (AssetCategory assetCategory : categories) {
				if (assetCategory.getVocabularyId() == idOrganization) {
					CustomCategoryProperty propertyCodSiu = CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(assetCategory.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU);
					if(Validator.isNotNull(propertyCodSiu)) {
						relation = relation + "{\r\n";
						relation = relation + "\"@id\": \"http://opendata.aragon.es/def/ei2a#"+propertyCodSiu.getText()+"\",\r\n"; 
						relation = relation + "\"owl:sameAs\": {\r\n" ;
						relation = relation + "\"@id\": \"" + portalUrl + freemarkerUtilities.getAssetCategoryURL(themeDisplay.getRequest(), assetCategory.getCategoryId(), false) + "\"\r\n" ; 
						relation = relation + "}},\r\n" ; 							
					}
				}else if(assetCategory.getVocabularyId() == idTopic){
					CustomCategoryProperty propertyEi2a= CustomCategoryPropertyLocalServiceUtil.fetchByCategoryIdAndKey(assetCategory.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID);
					if(Validator.isNotNull(propertyEi2a)) {
						subject = subject + "{\r\n";
						subject = subject + "\"@id\": \"http://opendata.aragon.es/def/ei2a#"+propertyEi2a.getText()+"\",\r\n"; 
						subject = subject + "\"owl:sameAs\": {\r\n" ;
						subject = subject + "\"@id\": \"" + portalUrl + freemarkerUtilities.getAssetCategoryURL(themeDisplay.getRequest(), assetCategory.getCategoryId(), false) + "\"\r\n" ; 
						subject = subject + "}},\r\n" ; 	
					}
				}
			}
		}
		if(relation.contains("@id")) {
			relation = relation.substring(0,relation.length()-3);
		}
		result = result + relation;
		result = result + "], \r\n" ;
		
		if(subject.contains("@id")) {
			subject = subject.substring(0,subject.length()-3);
		}
		result = result + subject;
		result = result + "]\r\n" ; 
		result = result + "},\r\n" ; 
		result = result + "{\r\n" ; 
		result = result + "\"@context\": \"http://schema.org/\",\r\n" ; 
		result = result + "\"@type\": \"WebPage\",\r\n" ; 
		result = result + "\"@id\": \"\",\r\n";
		result = result + "\"mentions\": ["; 
		if (!categories.isEmpty()) {
			for (AssetCategory assetCategory : categories) {
				result = result + "{\r\n";
				result = result + "\"@id\": \"" + portalUrl + freemarkerUtilities.getAssetCategoryURL(themeDisplay.getRequest(), assetCategory.getCategoryId(), false) + "\"\r\n"; 
				result = result + "},\r\n"; 
			}
			result = result.substring(0,result.length()-3);
		}
		result = result + "]\r\n" ; 
		result = result + "}]\r\n" ; 
		result = result + "";
		result = result + JSONLD_END_SCRIPT;
		return result;
	}
	public String getOrganismosFromJsonLD(List<AssetCategory> categories, Locale locale ) {
		String result = StringPool.BLANK;
		if (Validator.isNotNull(categories)) {
			for (AssetCategory category : categories) {
				AssetVocabulary assetVocabulary = _assetVocabularyLocalService.fetchAssetVocabulary(category.getVocabularyId());
				if (Validator.isNotNull(assetVocabulary)) {
					CustomCategoryProperty categoryId = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(category.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID);
					if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
						categoryId = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(category.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU);
					}
					String categoryTitle = category.getTitle(locale);
					if (Validator.isNotNull(categoryId) && !categoryId.getText().isEmpty()) {
						if (assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
							result = result + "-SEPARATOR-" + categoryTitle;
						}
					}
				}
			}
		}
		return result;
	}
	@Override
	public String getVocabularyFromJsonLD(AssetVocabulary vocabulary, AssetCategory category, Locale locale, ThemeDisplay themeDisplay) {
		String result = StringPool.BLANK;
		String currentURL = PortalUtil.getPortalURL(themeDisplay.getRequest()) + themeDisplay.getURLCurrent();
		String categoryTitle = category.getTitle(themeDisplay.getLocale());
		String ei2aUrl = "http://opendata.aragon.es/def/e2ia#";
		//Get list properties from category passed by parameter
		List <CustomCategoryProperty> listProperties = CustomCategoryPropertyLocalServiceUtil.findByCategoryId(category.getCategoryId());
		if (listProperties != null && !listProperties.isEmpty()) {
			String coorX = StringPool.BLANK;
			String coorY = StringPool.BLANK;
		    String building = StringPool.BLANK;
		    String address = StringPool.BLANK;
		    String phone = StringPool.BLANK;
		    String city = StringPool.BLANK;
		    String cp = StringPool.BLANK;
		    String region = StringPool.BLANK;
		    String cod_province = StringPool.BLANK;
		    String email = StringPool.BLANK;
		    String position = StringPool.BLANK;
		    String biography = StringPool.BLANK;
		    String imgPath = StringPool.BLANK;
		    String codEi2a = StringPool.BLANK;
		    String cod_siu = StringPool.BLANK;
			for (CustomCategoryProperty categoryPropery : listProperties) {
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_X)) {
					coorX = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COOR_Y)) {
					coorY = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO)) {
					building = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_DIRECCION)) {
					address = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_TELEFONO)) {
					phone = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_LOCALIDAD)) {
					city = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CP)) {
					cp = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EDIFICIO)) {
					building = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COMARCA)) {
					region = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EMAIL)) {
					email = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_CARGO)) {
					position = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_BIOGRAFIA)) {
					biography = categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_FOTO_PATH)) {
					imgPath = PortalUtil.getPortalURL(themeDisplay.getRequest())+categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_PROVINCIA)) {
					cod_province = categoryPropery.getText();
					if (cod_province.equals("44")) {
						cod_province = "Teruel";
					}else if (cod_province.equals("22")) {
						cod_province = "Huesca";
					}else if (cod_province.equals("50")){
						cod_province = "Zaragoza";
					}
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID)) {
					codEi2a =  categoryPropery.getText();
				}
				if (categoryPropery.getKey().equals(AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU)) {
					cod_siu =  categoryPropery.getText();
				}
			}
			//init script
			result = JSONLD_SCRIPT_TYPE_APPLICATION_LD_JSON;
			if (vocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES)) {
				// TEMAS
				List <AssetCategory> assetCategoryChildList = AssetCategoryLocalServiceUtil.getChildCategories(category.getCategoryId());
				result = result + "[{\r\n";
				result = result + "\""+JSONLD_CONTEXT+"\":{\r\n";
				result = result + "\"skos\": \"http://www.w3.org/2004/02/skos/core#\",\r\n" ; 
				result = result + "\"owl\": \"http://www.w3.org/2002/07/owl#\"";
				result = result + "},\r\n" ;
				result = result + "\""+JSONLD_TYPE+"\": \"skos:Concept\",\r\n" ; 
				result = result + "\""+JSONLD_ID+"\": \""+currentURL+"\",\r\n" ; 
				result = result + "\"owl:sameAs\":  \"http://opendata.aragon.es/def/ei2a/categorization#" + codEi2a + "\",";
				
				if(!assetCategoryChildList.isEmpty()) {
					result = result + "\"skos:narrower\": [ ";
					for (AssetCategory categoryChild : assetCategoryChildList) {
					String url = PortalUtil.getPortalURL(themeDisplay.getRequest())+freemarkerUtilities.getAssetCategoryURL(themeDisplay.getRequest(), categoryChild.getCategoryId(), false);
						try {
							result = result	+ "{\""+JSONLD_ID+"\": \""+url+"\"},\r\n" ;
						} catch (Exception e) {						
							e.printStackTrace();
						}
					}
					result = result.substring(0,result.length()-3);
					result = result+"],\r\n" ; 
				}
				result = result + "\"skos:prefLabel\": \""+categoryTitle+"\"\r\n"; 		
				result = result + "}]";
			} else if (vocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
				//ORGANISMOS
				result = result + "[{\r\n";
				result = result + "\"" + JSONLD_CONTEXT + "\":{\r\n";
				result = result + "\"" + EI2A_CONTEXT_EI2A + "\": \"" + ei2aUrl + "\",\r\n";
				result = result + "\"" + JSONLD_DCT + "\": \"http://purl.org/dc/terms/\",\r\n";
				result = result + "\"locn\": \"http://www.w3.org/ns/locn#\",\r\n";										
				result = result + "\"gsp\": \"http://www.opengis.net/ont/geosparql#\",\r\n" ;
				result = result + "\"" + JSONLD_GEO + "\": \"http://www.w3.org/2003/01/geo/wgs84_pos#\",\r\n" ; 
				result = result + "\"org\": \"http://www.w3.org/ns/org#\",\r\n" ; 
				result = result + "\"owl\": \"http://www.w3.org/2002/07/owl#\"\r\n" ;
				result = result + "},\r\n"; 
				result = result +"\"@id\": \"http://opendata.aragon.es/def/ei2a#" + cod_siu + "\",";
				result = result + "\"owl:sameAs\": {\r\n" ;
				result = result + "\"" + JSONLD_ID + "\" : \""+currentURL+"\"\r\n" ;
				result = result + "},\r\n" ;
				result = result + "\"" + JSONLD_TYPE + "\": [\"ei2a:Organization\",\"org:Organization\"],\r\n" ;
				result = result + "\"ei2a:businessName\": \""+categoryTitle+"\r\n" ;
				result = result + "Ambiente\",\r\n" ; 
				if (!phone.equals(StringPool.BLANK)) {
					result = result + "\"ei2a:phone\": \""+phone+"\",\r\n" ;
				}
				result = result + "\"org:hasPrimarySite\": {\r\n" ; 
				result = result + "\""+JSONLD_TYPE+"\": \"dct:Location\",\r\n" ;
				if (!building.equals(StringPool.BLANK)) {
					result = result + "\"locn:geographicName\": \""+building+"\",\r\n" ;
				}
				result = result + "\"locn:address\":{\r\n" ;
				result = result + "\""+JSONLD_TYPE+"\": \"locn:Address\",\r\n" ;
				if (!address.equals(StringPool.BLANK)) {
					result = result + "\"locn:fullAddress\": \""+address+"\",\r\n" ; 
				}
				if (!city.equals(StringPool.BLANK)){
					result = result + "\"locn:postName\": \""+city+"\",\r\n"; 
				}
				if (!cp.equals(StringPool.BLANK)){
					result = result + "\"locn:postCode\": \""+cp+"\",\r\n" ; 
				}
				result = result + "\"locn:adminUnitL1\": \"" + LanguageUtil.get(themeDisplay.getLocale(), "generator-json-ld.spainName") + "\",\r\n" ; 
				result = result + "\"locn:adminUnitL2\": \""+LanguageUtil.get(themeDisplay.getLocale(), "generator-json-ld.aragonName" )+ "\"\r\n" ; 
				result = result + "},\r\n";
				if (!coorX.equals(StringPool.BLANK) && !coorX.equals("0.0") && !coorY.equals(StringPool.BLANK) && !coorX.equals("0.0")){
					result = result + "\"locn:geometry\": {\r\n" ; 
					result = result + "\"" + JSONLD_TYPE + "\": \"gsp:Geometry\",\r\n" ;
					result = result + "\"geo:long\": \""+coorX+"\",\r\n";
					result = result + "\"geo:lat\": \""+coorY+"\",\r\n" ;
					result = result + "\"geo:asWKT\": \"POINT ("+coorX+" "+coorY+")\"}\r\n";
				}else {
					result = result.substring(0, result.length()-3);
				}
				result = result +"}\r\n" ; 
				result = result +"},{\r\n" ;
				result = result + "\"" + JSONLD_CONTEXT + "\": \"http://schema.org/\",\r\n"; 
				result = result + "\"" + JSONLD_TYPE + "\": \"Organization\",\r\n"; 
				result = result + "\""+JSONLD_ID + "\" : \"" + currentURL + "\",\r\n" ; 
				result = result + "\"name\": \"" + categoryTitle + "\",\r\n" ;
				if (!phone.equals(StringPool.BLANK)) {
					result = result + "\"" + SCHEMA_TELEPHONE + "\": \"" + phone +"\",\r\n"; 
				}
				result = result + "\"" + SCHEMA_LOCATION+"\": {\r\n" ;
				result = result + "\"" + JSONLD_TYPE+"\": \"Place\",\r\n" ; 
				if (!building.equals(StringPool.BLANK)) {
					result = result + "\"name\": \""+building+"\",\r\n" ; 
				}
				result = result + "\"" + SCHEMA_ADDRESS + "\":{\r\n" ; 
				//Direccion
				result = result + "\"" + JSONLD_TYPE + "\": \"PostalAddress\",\r\n"; 
				if (!address.equals(StringPool.BLANK)) {
					result = result + "\"streetAddress\": \"" + address + "\",\r\n" ; 
				}
				if (!city.equals(StringPool.BLANK)) {
					result = result + "\"addressLocality\": \"" + city + "\",\r\n" ; 
				}
				if (!cp.equals(StringPool.BLANK)) {
					result = result + "\"postalCode\": \"" + cp +"\",\r\n" ; 
				}
				result = result + "\"addressCountry\": \"" + LanguageUtil.get(themeDisplay.getRequest(), "generator-json-ld.spainName") + "\",\r\n" ; 
				if (!city.equals(StringPool.BLANK)) {
					result = result + "\"addressRegion\": [\"" + LanguageUtil.get(themeDisplay.getRequest(), "generator-json-ld.aragonName")+"\", \"" + city + "\"]\r\n" ; 
				}else {
					result = result.substring(0, result.length()-3);
				}
				result = result + "},\r\n" ; 
				if (!coorX.equals(StringPool.BLANK) && !coorX.equals("0.0") && !coorY.equals(StringPool.BLANK) && !coorX.equals("0.0")){
					result = result + "\"" + JSONLD_GEO + "\": {\r\n" ; 
					result = result + "\"" + JSONLD_TYPE + "\": \"GeoCoordinates\",\r\n"; 
					result = result + "\"" + SCHEMA_LONGITUDE + "\": \"" + coorX + "\",\r\n" ;
					result = result + "\""+SCHEMA_LATITUDE+"\": \"" + coorY + "\"\r\n" ; 
					result = result + "}\r\n" ; 
				}else {
					result = result.substring(0, result.length()-3);
				}
				result = result + "}\r\n" ; 
				result = result + "}]";
			} else if (vocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_MUNICIPALITIES_ES)) {
				//LUGARES
				result = result + "[{\r\n";
				result = result + "\"" + JSONLD_CONTEXT + "\":{\r\n";
				result = result + "\"" + EI2A_CONTEXT_EI2A + "\": \""+ei2aUrl+"\",\r\n";
				result = result + "\"" + JSONLD_DCT + "\": \"http://purl.org/dc/terms/\",\r\n";
				result = result + "\"locn\": \"http://www.w3.org/ns/locn#\",\r\n" ; 
				result = result + "\"gsp\": \"http://www.opengis.net/ont/geosparql#\",\r\n" ; 
				result = result + "\"" + JSONLD_GEO + "\": \"http://www.w3.org/2003/01/geo/wgs84_pos#\",\r\n" ; 
				result = result + "\"org\": \"http://www.w3.org/ns/org#\",\r\n" ; 
				result = result + "\"owl\": \"http://www.w3.org/2002/07/owl#\"\r\n" ; 
				result = result + "}, \r\n" ; 
				result = result + "\"" + JSONLD_TYPE + "\": \"dct:Location\",\r\n" ; 
				result = result + "\"locn:geographicName\": \"" + categoryTitle + "\",\r\n" ;
				result = result + "\"owl:sameAs\": { \"" + JSONLD_ID + "\": \"\" },\r\n" ;
				result = result + "\"locn:address\":{ \r\n" ;
				//Direccion
				result = result + "\"" + JSONLD_TYPE + "\": \"locn:Address\",\r\n" ; 
				if (!address.equals(StringPool.BLANK)) {
					result = result + "\"locn:fullAddress\": \"" + address + "\",\r\n" ; 
				}
				if (!cod_province.equals(StringPool.BLANK)) {
					result = result + "\"locn:postName\": \""+ categoryTitle +"\",\r\n" ; 
				}
				if (!cp.equals(StringPool.BLANK)) {
					result = result + "\"locn:postCode\": \"" + cp + "\",\r\n";
				}
				result = result + "\"locn:adminUnitL1\": \"" + LanguageUtil.get(themeDisplay.getLocale(), "generator-json-ld.spainName") + "\",\r\n" ; 
				result = result + "\"locn:adminUnitL2\": \"" + LanguageUtil.get(themeDisplay.getLocale(), "generator-json-ld.aragonName") + "\"\r\n" ; 
				result = result + "}\r\n"; 
				result = result + "},{\r\n";
				result = result + "\""+JSONLD_CONTEXT + "\": \"http://schema.org/\",\r\n" ; 
				result = result + "\""+JSONLD_TYPE+"\": \"Place\",\r\n" ;
				result = result + "\"name\": \"" +categoryTitle +  "\",\r\n" ;
				result = result + "\"" + JSONLD_ID + "\": \"\",\r\n" ;
				result = result + "\"" + SCHEMA_ADDRESS + "\":{ \r\n" ; 
				result = result + "\"" + JSONLD_TYPE + "\": \"PostalAddress\",\r\n" ;
				if (!address.equals(StringPool.BLANK)) {
					result = result + "\"streetAddress\": \"" + address + "\",\r\n" ; 
				}
				if (!cod_province.equals(StringPool.BLANK)) {
					result = result + "\"addressLocality\": \"" + categoryTitle + "\",\r\n" ;
				}
				if (!cp.equals(StringPool.BLANK)) {
					result = result + "\"postalCode\": \"" + cp + "\",\r\n" ; 
				}
				result = result + "\"addressCountry\": \"" + LanguageUtil.get(themeDisplay.getLocale(), "generator-json-ld.spainName") + "\",\r\n" ;
				if (!cod_province.equals(StringPool.BLANK)) {
					result = result + "\"addressRegion\": [\""+LanguageUtil.get(themeDisplay.getLocale(), "generator-json-ld.aragonName")+"\", \"" + cod_province + "\"]\r\n" ;
				}
				result = result + "}\r\n" ; 
				result = result + "}] \r\n" ; 
			} else if (vocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_PEOPLE_ES)) {
				//PERSONAS
				result = result + "[{\r\n";
				result = result + "\"" + JSONLD_CONTEXT + "\":{\r\n" ; 
				result = result + "\"" + EI2A_CONTEXT_EI2A + "\":\"" + ei2aUrl + "\",\r\n";
				result = result + "\"org\": \"http://www.w3.org/ns/org#\",\r\n" ; 
				result = result + "\"person\":\"http://www.w3.org/ns/person#\",\r\n" ; 
				result = result + "\"foaf\": \"http://xmlns.com/foaf/0.1/\",\r\n" ; 
				result = result + "\"owl\": \"http://www.w3.org/2002/07/owl#\"\r\n" ; 
				result = result + "},\r\n";
				result = result + "\"" + JSONLD_TYPE +"\":[\"person:Person\",\"foaf:Person\"],\r\n"; 
				result = result + "\"owl:sameAs\": { \"" + JSONLD_ID + "\": \"\" },\r\n" ; 
				result = result + "\"ei2a:fullName\":\"" + categoryTitle + "\",\r\n" ;
				if (!imgPath.equals(StringPool.BLANK)) {
					result = result + "\"foaf:depiction\":\"" + imgPath + "\",\r\n" ;
				}
				if (!email.equals(StringPool.BLANK)) {
					result = result + "\"ei2a:email\":\"" + email + "\",\r\n" ;
				}
				if (!phone.equals(StringPool.BLANK)) {
					result = result + "\"ei2a:phone\":\"" + phone + "\",\r\n" ; 
				}
				result = result + "\"org:holds\": {\r\n" ;
				result = result + "\"" + JSONLD_TYPE + "\": \"ei2a:Role\",\r\n" ;
				if (!position.equals(StringPool.BLANK)) {
					result = result + "\"ei2a:roleName\": \""+position+"\"\r\n"; 
				}
				result = result + "},\r\n" ;
				result = result + "\"org:memberOf\": { \r\n" ; 
				result = result + "\"" + JSONLD_ID + "\" : \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\" \r\n" ; 
				result = result + "},\r\n" ; 
				result = result + "\"org:basedAt\":{ \r\n"; 
				result = result + "\""+ JSONLD_ID + "\" : \"http://opendata.aragon.es/recurso/sector-publico/site/sede-edificiopignatelli\" \r\n" ;
				result = result + "}\r\n" ;
				result = result + "},\r\n"; 
				result = result + "{\r\n";
				result = result + "\"" + JSONLD_CONTEXT + "\": \"http://schema.org/\",\r\n" ;
				result = result + "\""+JSONLD_TYPE + "\": \"Person\",\r\n" ; 
				result = result + "\"" + JSONLD_ID + "\": \"\",\r\n" ;
				result = result + "\"name\":\"" + categoryTitle + "\",\r\n" ;
				if (!biography.equals(StringPool.BLANK)) {
					result = result + "\"description\":\"" + biography + "\",\r\n" ; 
				}
				if (!imgPath.equals(StringPool.BLANK)) {
					result = result + "\"image\":\"" + imgPath + "\",\r\n" ;
				}
				if (!email.equals(StringPool.BLANK)) {
					result = result + "\"" + SCHEMA_EMAIL + "\":\""+ email +"\",\r\n";
				}
				if (!phone.equals(StringPool.BLANK)) {
					result = result + "\""+SCHEMA_TELEPHONE+"\":\"" + phone + "\",\r\n" ;
				}
				result = result + "\"hasOccupation\": {\r\n" ;
				result = result + "\""+JSONLD_TYPE+"\": \"OrganizationRole\",\r\n" ;
				if (!position.equals(StringPool.BLANK)) {
					result = result + "\"roleName\": \"" + position + "\"\r\n";
				}
				result = result + "},\r\n" ;
				result = result + "\"worksFor\": { \r\n" ;
				result = result + "\"" + JSONLD_ID + "\" : \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\" \r\n" ;
				result = result + "},\r\n" ;
				result = result + "\"workLocation\": { \r\n" ;
				result = result + "\""+ JSONLD_ID + "\" : \r\n";
				result = result + "\"http://opendata.aragon.es/recurso/sector-publico/site/sede-edificiopignatelli\" \r\n" ;
				result = result + "}\r\n" ; 
				result = result + "}]\r\n" ;
			}
			//end script
			result = result + JSONLD_END_SCRIPT;
		}
		return result;
	}
	
//	@Override
//	public String getCategoriesJsonLD(List<AssetCategory> categories, Locale locale) {
//		String result = StringPool.BLANK;
//		if (Validator.isNotNull(categories)) {
//			String temasId = "http://opendata.aragon.es/def/ei2a/categorization#";
//			String organismosId = "http://opendata.aragon.es/def/ei2a#";
//			//String temasType = "http://opendata.aragon.es/def/ei2a/categorization";
//			//String organismosType = "http://opendata.aragon.es/def/ei2a#PublicEntityType";
//			String temasType = "Thing";
//			String organismosType = "GovernmentOrganization";
//			for (AssetCategory category : categories) {
//				AssetVocabulary assetVocabulary = _assetVocabularyLocalService.fetchAssetVocabulary(category.getVocabularyId());
//				if (Validator.isNotNull(assetVocabulary)) {
//					CustomCategoryProperty categoryId = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(category.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID);
//					if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
//						categoryId = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(category.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU);
//					}
//					String categoryTitle = category.getTitle(locale);
//					String ei2aId = StringPool.BLANK;
//					String ei2aType = StringPool.BLANK;
//					if (Validator.isNotNull(categoryId) && !categoryId.getText().isEmpty()) {
//						result = result + "{\n";
//						result = result + "\t\"" + JSONLD_CONTEXT + "\": \"http://schema.org/\",\n";
//						if (assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES)) {
//							ei2aId = temasId + categoryId.getText();
//							ei2aType = temasType;
//						} else if (assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
//							ei2aId = organismosId + categoryId.getText();
//							ei2aType = organismosType;
//						}
//						result = result + "\t\"" + JSONLD_ID + "\": \"" + ei2aId + "\",\n";
//						result = result + "\t\"" + JSONLD_TYPE + "\": \"" + ei2aType + "\",\n";
//						result = result + "\t\"" + JSONLD_NAME + "\": \"" + categoryTitle + "\"\n";
//						result = result + "}\n";
//						result = result + ",";
//					}
//				}
//			}
//		}
//		if (!result.isEmpty()) {
//			// Eliminamos la coma final
//			result = result.substring(0, result.length()-1);
//			result = "[" + result + "]";
//		}
//		return result;
//	}
	@Override
	public String getCategoriesJsonLD2(List<AssetCategory> categories, ThemeDisplay themeDisplay) {
		String result = StringPool.BLANK;
		String portalUrl = PortalUtil.getPortalURL(themeDisplay.getRequest()); 
		AssetVocabulary vocabularyOrganization = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES);
		AssetVocabulary vocabularyTopic = AssetVocabularyLocalServiceUtil.fetchGroupVocabulary(themeDisplay.getScopeGroupId(), AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES);
		long idOrganization = (vocabularyOrganization != null) ? vocabularyOrganization.getVocabularyId() : -1 ;
		long idTopic = (vocabularyTopic != null) ? vocabularyTopic.getVocabularyId() : -1 ;
		result = result + "[\r\n" ;
		if (!categories.isEmpty()) {
			for (AssetCategory assetCategory : categories) {
				if (assetCategory.getVocabularyId() == idOrganization) {
					CustomCategoryProperty codSiu = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(assetCategory.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU);
					result = result +" {\"@context\": \"http://schema.org/\",\r\n"  ; 
					result = result +" \"@id\": \"" +portalUrl + freemarkerUtilities.getAssetCategoryURL(themeDisplay.getRequest(), assetCategory.getCategoryId(), false) +"\",\r\n"; 
					result = result +" \"@type\": \"GovernmentOrganization\",\r\n"  ;
					result = result +" \"name\": \"" + assetCategory.getTitle(themeDisplay.getLocale()) + "\"\r\n"  ; 
					result = result +" }, {\r\n"  ; 
					result = result +" \"@context\":{\r\n"; 
					result = result +" \"ei2a\": \"http://opendata.aragon.es/def/e2ia#\",\r\n"  ; 
					result = result +" \"org\": \"http://www.w3.org/ns/org#\"\r\n"  ;
					result = result +" },\r\n" ; 
					if (Validator.isNotNull(codSiu)){
						result = result +" \"@id\": \"http://opendata.aragon.es/def/ei2a#" + codSiu.getText() + "\",\r\n"  ; 
					}
					result = result +" \"@type\": [\"ei2a:Organization\",\"org:Organization\"],\r\n"  ;
					result = result +" \"ei2a:businessName\": \"" + assetCategory.getTitle(themeDisplay.getLocale()) + "\"\r\n"  ; 
					result = result +" }," ; 
				}
			}
		}
		for (AssetCategory assetCategory : categories) {
			if (assetCategory.getVocabularyId() == idTopic) {
				CustomCategoryProperty codEi2a = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(assetCategory.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID);
				result = result + " {\r\n" ; 
				result = result + " \"@context\": \"http://schema.org/\",\r\n" ;
				result = result + " \"@id\": \""+portalUrl + freemarkerUtilities.getAssetCategoryURL(themeDisplay.getRequest(), assetCategory.getCategoryId(), false)+"\",\r\n"; 
				result = result + " \"@type\": \"Thing\",\r\n" ;
				result = result + " \"name\": \"" + assetCategory.getTitle(themeDisplay.getLocale()) + "\"\r\n"  ; 
				result = result + " }, {\r\n"  ;
				result = result + " \"@context\": {\r\n"  ; 
				result = result + " \"skos\": \"http://www.w3.org/2004/02/skos/core#\"\r\n"  ; 
				result = result + " },\r\n"  ; 
				if (Validator.isNotNull(codEi2a)) {
					result = result + " \"@id\": \"http://opendata.aragon.es/def/ei2a/categorization#"+codEi2a.getText()+"\",\r\n"  ; 
				}
				result = result + " \"@type\": \"skos:Concept\",\r\n"  ;
				result = result + " \"skos:prefLabel\": \"" + assetCategory.getTitle(themeDisplay.getLocale()) + "\"\r\n"  ;
				result = result + " },"  ; 
			}
		}
		result = result.substring(0, result.length()-1);
		result = result + "]"  ; 
		return result;
	}
	
	@Override
	public String getE2IAInformation(ThemeDisplay themeDisplay) {
		
		Locale locale = themeDisplay.getLocale();
		Layout layout = themeDisplay.getLayout();
		String date = getDateFormat(layout.getModifiedDate());
		
		String result = "{\n";
		
		// Contexto E2IA
		result = result + "\"" + JSONLD_CONTEXT + "\":{ \n";
		result = result + "\t\"" + EI2A_CONTEXT_EI2A + "\": \"http://opendata.aragon.es/def/e2ia#\",\n";
		result = result + "\t\"" + JSONLD_DCT + "\": \"http://purl.org/dc/terms/\",\n";
		result = result + "\t\"" + JSONLD_DCMI + "\": \"http://purl.org/dc/dcmitype/\",\n";
		result = result + "\t\"" + SCHEMA_CONTEXT_SCHEMA + "\": \"http://schema.org/\",\n";
		result = result + "\t\"" +"owl\":\"http://www.w3.org/2002/07/owl#\"\n";
		result = result + "},";
		
		// Tipo de documento
		result = result + "\"" + JSONLD_TYPE + "\": [\""+EI2A_CONTEXT_EI2A+":Document\",\""+JSONLD_DCMI+":InteractiveResource\"],\n";
		result = result + "\"owl:sameAs\": { \"" + JSONLD_ID + "\": \"\" },";
		// Titulo de la pagina
		String pageTitle = layout.getName(locale) + ". " + LanguageUtil.get(locale, "aragon.portal-head-tag");
		try {
			String currentURL = themeDisplay.getURLCurrent();
			if(currentURL.contains("/-/")){
	    		int posicion = currentURL.indexOf("/-/") + 3;
	    		String currentTitleJournal = currentURL.substring(posicion);
	    		JournalArticle journalArticle = _journalArticleLocalService.fetchArticleByUrlTitle(themeDisplay.getScopeGroupId(), currentTitleJournal);
	    		if (Validator.isNotNull(journalArticle)) {
	    			if(Validator.isNotNull(journalArticle.getTitle(locale))) {
	    				pageTitle = journalArticle.getTitle(locale);
	    			}
	    		}
	    	}
		} catch (Exception e) {
			_log.error(e, e);
		}
		
		result = result + "\"" + EI2A_TITLE + "\": \"" + pageTitle.replace("\"", "\'") + "\", \n";
		
		// Descripcion de la pagina
		String description = layout.getDescription(locale);
		result = result + "\"" + EI2A_DESCRIPTION + "\": \"" + description.replace("\"", "\'") + "\", \n";
		
		// Formato
		result = result + "\"" + EI2A_FORMAT + "\": \"text/html\", \n";
		
		// Fecha de actualizacion
		result = result + "\"" + EI2A_DATE + "\": \"" + date + "\", \n";
		
		// Validez temporal y espacial
		result = result + "\"" + EI2A_COVERAGE + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"schema:Place\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/territorio/ComunidadAutonoma/Arag\\u00f3n\"\n";
		result = result + "},";
		
		// Lenguaje
		result = result + "\"" + EI2A_LANGUAGE + "\": \"" + locale.getLanguage() + "\", \n";
		
		// Creador
		result = result + "\"" + EI2A_CREATOR + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"schema:GovernmentOrganization\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
		result = result + "\t\"" + "schema:" + JSONLD_NAME + "\": \"Gobierno de Arag\u00f3n\"\n";
		result = result + "},";
		
		// Publicador
		result = result + "\"" + EI2A_PUBLISHER + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"schema:GovernmentOrganization\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
		result = result + "\t\"" + "schema:" + JSONLD_NAME + "\": \"Gobierno de Arag\u00f3n\"\n";
		result = result + "},";
		
		// Derechos
		result = result + "\"" + EI2A_RIGHTS + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"schema:WebPage\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://www.aragon.es/EnlacesPie/Aviso_Legal\"\n";
		result = result + "}\n";
		
		result = result + "}\n";
		
		return result;
	}

	@Override
	public String getSchemaOrgInformation(ThemeDisplay themeDisplay) {

		Locale locale = themeDisplay.getLocale();
		Layout layout = themeDisplay.getLayout();
		String date = getDateFormat(layout.getModifiedDate());
		
		String result = "{\n";
		
		// Contexto Schema
		result = result + "\"" + JSONLD_CONTEXT + "\": \"http://schema.org/\", \n";
		
		String pageTitle = layout.getName(locale) + ". " + LanguageUtil.get(locale, "aragon.portal-head-tag");
		// Tipo de documento
		if (pageTitle.contains("Inicio.")) {
			result = result + "\"" + JSONLD_TYPE + "\": [\"WebPage\",\"WebSite\"],\n";
		}else {
			result = result + "\"" + JSONLD_TYPE + "\": \"WebPage\",\n";
		}

		result = result + "\""+JSONLD_ID+"\": \"\",";
		
		// Titulo de la pagina
		
		try {
			String currentURL = themeDisplay.getURLCurrent();
			if(currentURL.contains("/-/")){
	    		int posicion = currentURL.indexOf("/-/") + 3;
	    		String currentTitleJournal = currentURL.substring(posicion);
	    		JournalArticle journalArticle = _journalArticleLocalService.fetchArticleByUrlTitle(themeDisplay.getScopeGroupId(), currentTitleJournal);
	    		if (Validator.isNotNull(journalArticle)) {
	    			if(Validator.isNotNull(journalArticle.getTitle(locale))) {
	    				pageTitle = journalArticle.getTitle(locale);
	    			}
	    		}
	    	}
		} catch (Exception e) {
			_log.error(e, e);
		}
		result = result + "\"" + SCHEMA_NAME + "\": \"" + pageTitle.replace("\"", "\'") + "\", \n";
		
		// Descripcion de la pagina
		String description = layout.getDescription(locale);
		result = result + "\"" + SCHEMA_DESCRIPTION + "\": \"" + description.replace("\"", "\'") + "\", \n";
		
		// Formato
		result = result + "\"" + SCHEMA_FORMAT + "\": \"text/html\", \n";
		
		// Fecha de actualizacion
		result = result + "\"" + SCHEMA_DATE_PUBLISHED + "\": \"" + date + "\", \n";
		
		// Validez temporal y espacial
		result = result + "\"" + SCHEMA_COVERAGE + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"Place\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/territorio/ComunidadAutonoma/Arag\\u00f3n\"\n";
		result = result + "},";
		
		// Lenguaje
		result = result + "\"" + SCHEMA_LANGUAGE + "\": \"" + locale.getLanguage() + "\", \n";
		
		// Creador
		result = result + "\"" + SCHEMA_CREATOR + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"GovernmentOrganization\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
		result = result + "\t\"" + JSONLD_NAME + "\": \"Gobierno de Arag\u00f3n\"\n";
		result = result + "},";
		
		// Publicador
		result = result + "\"" + SCHEMA_PUBLISHER + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"GovernmentOrganization\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
		result = result + "\t\"" + JSONLD_NAME + "\": \"Gobierno de Arag\u00f3n\"\n";
		result = result + "},";
		
		// Derechos
		result = result + "\"" + SCHEMA_RIGHTS + "\": { \n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"WebPage\",\n";
		result = result + "\t\"" + JSONLD_ID + "\": \"http://www.aragon.es/EnlacesPie/Aviso_Legal\"\n";
		result = result + "}\n";
		
		result = result + "}\n";
		
		return result;
	}

	@Override
	public String getKnowledgeGraphExtension(ThemeDisplay themeDisplay) {
		
		String result = "{\n";
		String portalURL = PortalUtil.getPortalURL(themeDisplay.getRequest());
		String logoUrl = portalURL + "/o/aragon-theme/images/dga/logo-dga-color.svg";
		
		// Logo
		result = result + "\"" + JSONLD_CONTEXT + "\": \"http://schema.org\",\n";
		result = result + "\"" + JSONLD_TYPE + "\": \"GovernmentOrganization\",\n";
		result = result + "\"" + JSONLD_ID + "\": \"http://opendata.aragon.es/recurso/sectorpublico/organization/gobierno-aragon\",\n";
		result = result + "\"" + SCHEMA_URL + "\": \"http://www.aragon.es\",\n";
		
		if(Validator.isNotNull(logoUrl) && logoUrl != "") {
            result = result + "\"" + SCHEMA_LOGO + "\": { \n";
            result = result + "\t\"" + JSONLD_TYPE + "\": \"ImageObject\",\n";
            result = result + "\t\"" + SCHEMA_URL + "\": \""+ logoUrl +"\"},\n";
        }
		
		// Contacto corporativo
		result = result + "\"" + SCHEMA_CONTACT_POINT + "\": [{\n";
		result = result + "\t\"" + JSONLD_TYPE + "\": \"ContactPoint\",\n";
		result = result + "\t\"" + SCHEMA_TELEPHONE + "\": \"+34 976714000\",\n";
		result = result + "\t\"" + SCHEMA_CONTACT_TYPE + "\": \"customer support\"\n";
		result = result + "}],\n";
		
		// Perfiles de redes sociales
		result = result + "\"" + SCHEMA_SAME_AS + "\": [\n";
		result = result + "\t\"https://www.youtube.com/user/GobiernoAragon\",\n";
		result = result + "\t\"https://twitter.com/GobAragon\",\n";
		result = result + "\t\"https://www.facebook.com/GobAragon\"\n";
		result = result + "]\n";
		result = result + "}\n";
		
		return result;
	}
	
	public List<String> getCategoriesUrlIds(List<AssetCategory> categories){
		List<String> categoriesUrlIds = new ArrayList<String>();
		String temasId = "http://opendata.aragon.es/def/ei2a/categorization#";
		String organismosId = "http://opendata.aragon.es/def/ei2a#";
		if (Validator.isNotNull(categories)) {
			for (AssetCategory category : categories) {
				AssetVocabulary assetVocabulary = _assetVocabularyLocalService.fetchAssetVocabulary(category.getVocabularyId());
				if (Validator.isNotNull(assetVocabulary)) {
					CustomCategoryProperty categoryId = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(category.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_EI2A_ID);
					if(assetVocabulary.getName().equalsIgnoreCase(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
						categoryId = _customCategoryPropertyLocalService.fetchByCategoryIdAndKey(category.getCategoryId(), AragonUtilitiesConstant.CATEGORY_CUSTOM_PROPERTY_COD_SIU);
					}
					String ei2aId = StringPool.BLANK;
					if (Validator.isNotNull(categoryId) && !categoryId.getText().isEmpty()) {
						if (assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_TOPICS_ES)){
							ei2aId = temasId + categoryId.getText();
							categoriesUrlIds.add(ei2aId);
						} else if (assetVocabulary.getName().equals(AragonUtilitiesConstant.VOCABULARY_NAME_ORGANIZATIONS_ES)) {
							ei2aId = organismosId + categoryId.getText();
							categoriesUrlIds.add(ei2aId);
						}
					}
				}
			}
		}
		return categoriesUrlIds;
	}
	
	@Override
	public String getItemListJsonLD(List<JournalArticle> articles, ThemeDisplay themeDisplay) {
		int position = 0;
		String portalURL = PortalUtil.getPortalURL(themeDisplay.getRequest());
		
		String result = JSONLD_SCRIPT_TYPE_APPLICATION_LD_JSON;
		
		result = result + "{\r\n";
		
		result = result + "\"" + JSONLD_CONTEXT + "\": \"http://schema.org/\", \n";
		
		result = result + "\"" + JSONLD_TYPE + "\":\"ItemList\",\r\n";
		
		result = result + "\"" + JSONLD_ITEM_LIST_ELEMENT + "\":[\r\n";
	
		result = result + "{\r\n";
		
		for(JournalArticle article : articles){
			String title = article.getTitle(themeDisplay.getLocale());
			title = title.replace("\"", "\'");
			String url = getArticleFullURL(themeDisplay, article);
			url = portalURL + url;
			if(title.length() > 110) {
				title = title.substring(0, 110);
			}
			position = position + 1;
			
			result = result + "      \"@type\":\"ListItem\",\r\n" + 
					"      \"position\":" + position + ",\r\n" + 
					"      \"name\":\"" + title + "\",\r\n" + 
					"      \"url\":\"" + url + "\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n";
		}
		
		// Eliminamos la coma y el par�ntesis final
		result = result.substring(0, result.length()-10);
		
		// Cerramos el script
		result = result + "]}\r\n";
		
		result = result + JSONLD_END_SCRIPT;
		
		return result;		
	}
	
	public String getDateFormat(Date date) {
		DateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return newDateFormat.format(date);
	}
	
	public String getArticleFullURL(ThemeDisplay themeDisplay, JournalArticle article){
		String result = StringPool.BLANK;
		List<JournalContentSearch> journalContentSearchList = JournalContentSearchLocalServiceUtil.getArticleContentSearches(article.getArticleId());
		if (journalContentSearchList != null && journalContentSearchList.size() > 0) {
			JournalContentSearch journalContentSearch = journalContentSearchList.get(0);
			long layoutId=journalContentSearch.getLayoutId();
			long groupId = journalContentSearch.getGroupId();
			result = getLayoutFullURL(layoutId, groupId, themeDisplay.getLocale());
		}else {
			result = getRenderUrl(themeDisplay, article);
		}
		return result;
	}
	
	public String getLayoutFullURL(long layoutId, long groupId, Locale locale){
		String result = StringPool.BLANK;
		Layout layout = LayoutLocalServiceUtil.fetchLayout(groupId, false, layoutId);
		if (Validator.isNotNull(layout)) {
			result = getLayoutFullURL(layout, groupId, locale);
		}
		return result;
	}
	
	public String getLayoutFullURL(Layout layout, long groupId, Locale locale){
		String groupFriendlyURL = StringPool.BLANK;
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group != null) {
			if (layout.isPrivateLayout()) {
				groupFriendlyURL = "/group" + group.getFriendlyURL();
			} else {
				//No se a�ade la friendly url en caso de una p�gina p�blica
				//groupFriendlyURL = "/web" + group.getFriendlyURL();
			}
		}
		String result = groupFriendlyURL + layout.getFriendlyURL(locale);
		return result;
	}
	
	public String getRenderUrl(ThemeDisplay themeDisplay, JournalArticle article){
		String result = StringPool.BLANK;
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), article.getResourcePrimKey());
		if(Validator.isNotNull(assetEntry)) {
			PortletURL renderUrl =  PortletURLFactoryUtil.create(themeDisplay.getRequest(), SearchPortletKeys.SEARCH, themeDisplay.getLayout(), PortletRequest.RENDER_PHASE);
			try {
				renderUrl.setWindowState(LiferayWindowState.MAXIMIZED);
				renderUrl.setParameter("mvcPath", "/view_content.jsp");
				renderUrl.setParameter("type", "content");
				renderUrl.setParameter("redirect", themeDisplay.getURLCurrent());
				renderUrl.setParameter("assetEntryId", String.valueOf(assetEntry.getEntryId()));
				result = renderUrl.toString();
			} catch (WindowStateException e) {
				_log.error(e);
			}
		}
		return result;
	}
	
	//JSON LD constants
	private final String JSONLD_CONTEXT = "@context";
	private final String JSONLD_TYPE = "@type";
	private final String JSONLD_ID = "@id";
	private final String JSONLD_DCT = "dct";
	private final String JSONLD_DCMI = "dcmi";
	private final String JSONLD_NAME = "name";
	private final String JSONLD_GEO = "geo";
	private final String JSONLD_SCRIPT_TYPE_APPLICATION_LD_JSON = "<script type=\"application/ld+json\">\r\n";
	private final String JSONLD_ITEM_LIST_ELEMENT = "itemListElement";
	private final String JSONLD_END_SCRIPT = "</script>";
	
	//Ei2a constants
	private final String EI2A_CONTEXT_EI2A = "ei2a";
	private final String EI2A_TITLE = "dct:title";
	private final String EI2A_DESCRIPTION = "dct:description";
	private final String EI2A_FORMAT = "dct:format";
	private final String EI2A_DATE = "dct:date";
	private final String EI2A_COVERAGE = "dct:coverage";
	private final String EI2A_LANGUAGE = "dct:language";
	private final String EI2A_CREATOR = "dct:creator";
	private final String EI2A_PUBLISHER = "dct:publisher";
	private final String EI2A_RIGHTS = "dct:rights";
	//Geolocation Constants
	//SchemaConstants
	private final String SCHEMA_CONTEXT_SCHEMA = "schema";
	private final String SCHEMA_NAME = "name";
	private final String SCHEMA_DESCRIPTION = "description";
	private final String SCHEMA_FORMAT = "fileFormat";
	private final String SCHEMA_DATE_PUBLISHED = "datePublished";
	private final String SCHEMA_DATE_MODIFIED = "dateModified";
	private final String SCHEMA_COVERAGE = "spatialCoverage";
	private final String SCHEMA_LANGUAGE = "inLanguage";
	private final String SCHEMA_CREATOR = "creator";
	private final String SCHEMA_PUBLISHER = "publisher";
	private final String SCHEMA_RIGHTS = "license";
	private final String SCHEMA_HEADLINE = "headline";
	private final String SCHEMA_AUTOR = "author";
	private final String SCHEMA_IMAGE = "image";
	private final String SCHEMA_MENTIONS = "mentions";
	private final String SCHEMA_MAIN_ENTITY = "mainEntity";
	private final String SCHEMA_MAIN_ENTITY_OF_PAGE = "mainEntityOfPage";
	private final String SCHEMA_URL = "url";
	private final String SCHEMA_LOGO = "logo";
	private final String SCHEMA_CONTACT_POINT = "contactPoint";
	private final String SCHEMA_TELEPHONE = "telephone";
	private final String SCHEMA_EMAIL = "email";
	private final String SCHEMA_LOCATION = "location";
	private final String SCHEMA_LONGITUDE = "longitude";
	private final String SCHEMA_LATITUDE = "latitude";
	
	private final String SCHEMA_ADDRESS= "address";
	private final String SCHEMA_CONTACT_TYPE = "contactType";
	private final String SCHEMA_SAME_AS = "sameAs";
	
	@Reference
	private CustomCategoryPropertyLocalService _customCategoryPropertyLocalService;
	
	@Reference
	private UserLocalService _userLocalService;
	
	@Reference
	private JournalArticleLocalService _journalArticleLocalService;
	
	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;
	
	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;
	
	@Reference
	private FreemarkerUtilities freemarkerUtilities;
	
	private final Log _log = LogFactoryUtil.getLog(JSONLDGeneratorImpl.class);
	
}
