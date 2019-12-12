package es.aragon.base.migration.portlet;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Date;
import java.net.URLDecoder;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.aragon.base.migration.constants.AragonMigrationPortletKeys;
import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.model.ContentRelated;
import es.aragon.base.migration.model.Rule;
import es.aragon.base.migration.service.ContentLocalService;
import es.aragon.base.migration.service.ContentRelatedLocalService;
import es.aragon.base.migration.service.RuleLocalService;

/**
 * @author pfalcon
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/rulePortlet/view.jsp",
		"javax.portlet.name=" + AragonMigrationPortletKeys.RULE_PORTLET_KEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RulePortlet extends MVCPortlet {

	public void saveRule(ActionRequest actionRequest, ActionResponse actionResponse) {
	
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			//Parameters
			long ruleId = ParamUtil.getLong(actionRequest, "ruleId", 0);
			int typeId = ParamUtil.getInteger(actionRequest, "typeId", 0);
			long userGroupId = ParamUtil.getLong(actionRequest, "userGroupId", 0);
			long actionId= ParamUtil.getLong(actionRequest, "actionId", 0);
			long journalFolderId = ParamUtil.getLong(actionRequest, "journalFolderId", 0);
			String availableGroupId = StringPool.BLANK;
			String url = ParamUtil.getString(actionRequest, "url", StringPool.BLANK);
			// check if the url has two '%'. It means the url has been copied with html codification and need to be decoded
			if (url.indexOf('%', url.indexOf('%') + 1) != -1) {
				try {
					url = URLDecoder.decode(url, "UTF-8");
				} catch (IllegalArgumentException e) {
					_log.error("Error decoding url '" + url + "'", e);
				}
			}
			String tags = "";
			String groupGuestName = GroupConstants.GUEST;
			Group guestGroup = _groupLocalService.getGroup(themeDisplay.getCompanyId(), groupGuestName);
			long vocabularyGroups[] = { guestGroup.getGroupId() };
			List<AssetVocabulary> vocabularies = _assetVocabularyLocalService.getGroupsVocabularies(vocabularyGroups, JournalArticle.class.getName());
			for (AssetVocabulary vocabulary : vocabularies) {
				long vocabularyId = vocabulary.getVocabularyId();
				String vocabularySelectedCategories = ParamUtil.getString(actionRequest, "tags_" + vocabularyId, StringPool.BLANK);
				if (Validator.isNotNull(vocabularySelectedCategories)) {
					if (Validator.isNotNull(tags)) {
						tags = tags + ",";
					}
					tags = tags + vocabularySelectedCategories;
				}
			}
			User user= themeDisplay.getUser();
			long[] groupIds = user.getUserGroupIds();
			for ( long groupId : groupIds) {
					if (Validator.isNotNull(availableGroupId)) {
						availableGroupId = availableGroupId+",";
					}
					availableGroupId = availableGroupId + StringPool.OPEN_CURLY_BRACE+groupId+StringPool.CLOSE_CURLY_BRACE;
			}
			//Create
			if (ruleId == 0) {
				_ruleLocalService.addRule(typeId, userGroupId, journalFolderId, 0, themeDisplay.getUserId(), url, tags, availableGroupId, actionId);
			}
			//Update
			else {
				Rule rule = _ruleLocalService.fetchRule(ruleId);
				if (rule != null) {
					rule.setUrl(url);
					rule.setTags(tags);
					rule.setUserGroupId(userGroupId);
					rule.setJournalFolderId(journalFolderId);
					rule.setActionId(actionId);
					rule.setAvailableUserGroupId(availableGroupId);
					Date modifiedDate = new Date();
					rule.setModifiedDate(modifiedDate);
					rule.setUserId(themeDisplay.getUserId());
					_ruleLocalService.updateRule(rule);
				}
			}
			
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "error-general");
			_log.error("There was an error adding the rule: " + e.toString());
			e.printStackTrace();
		}
		
	}
	
	public void deleteRule(ActionRequest actionRequest, ActionResponse actionResponse) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long ruleId = ParamUtil.getLong(actionRequest, "ruleId", 0);
		Rule rule = _ruleLocalService.fetchRule(ruleId);
		_log.warn("REGLA ["+rule.getUrl()+"] ELIMINADA POR EL USUARIO "+themeDisplay.getUserId());
		try {
			_ruleLocalService.deleteRule(ruleId);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "error-general");
			_log.error("There was an error deleting the rule: " + e.toString());
			e.printStackTrace();
		}
	}
	
	public void applyRule(ActionRequest actionRequest, ActionResponse actionResponse) {
		
		long startMilis = System.currentTimeMillis();
		long ruleId = ParamUtil.getLong(actionRequest, "ruleId", 0);
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Rule rule = _ruleLocalService.fetchRule(ruleId);
		
		if (Validator.isNotNull(rule)) {
			//List<Content> contents = _contentLocalService.getContents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			List<Content> contents = getRuleRelatedContens(rule);
			
			if (Validator.isNotNull(contents)) {
				
				int type = rule.getTypeId();
				
				//Category rules
				if (type == 1) {
					_log.info("INICIO - APLICANDO REGLA [URL:" + rule.getUrl() + ", CATEGORY_IDS: " + rule.getTags() + "]"+" por el USERID "+themeDisplay.getUserId());
				}
				//Migration
				if (type == 2) {
					_log.info("INICIO - APLICANDO REGLA [URL:" + rule.getUrl() + ", ACTION_MIGRATION_IDS: " + rule.getActionId() + "]"+" por el USERID "+themeDisplay.getUserId());
				}
			
				//Journal Folder rules
				if (type == 3) {
					_log.info("INICIO - APLICANDO REGLA [URL:" + rule.getUrl() + ", JOURNALFOLDER_ID: " + rule.getJournalFolderId() + "]"+" por el USERID "+themeDisplay.getUserId());
				}
				
				// Aplicamos regla a contenidos
				long userId = themeDisplay.getUserId();
				applyRuleToContents(userId, rule, contents);
				
				// Aplicamos misma regla a documentos que hagan referencia a los contenidos que cumplan dicha regla
				//applyRuleToReferencedDocuments(userId, rule);
				
				_log.info("FIN - REGLA APLICADA");
				
			} else {
				_log.info("NO EXISTEN CONTENIDOS QUE CUMPLAN LA REGLA ESPECIFICADA");
			}
		} else {
			_log.info("NO SE HA ENCONTRADO LA REGLA");
		}
		
		long endMilis = System.currentTimeMillis();
		long totalTime = endMilis - startMilis;
		System.out.println("TIEMPO TOTAL DE APLICAR REGLA: "+totalTime+"ms");
	}
	
	public void applyRuleToContents(long userId, Rule rule, List<Content> contents) {
		int type = rule.getTypeId();
		String nameNav="";
		for (Content content : contents) {
			if("text/html".equals(content.getContentType())) {
				if (content.getUrl().contains(rule.getUrl())) {
					if (type == 1) {
						//Category rules
						nameNav="Categoria";
						String contentCategoriesStr = content.getTags();
						if (Validator.isNotNull(contentCategoriesStr) && !contentCategoriesStr.isEmpty()) {
							String ruleCategoriesStr = rule.getTags();
							if (Validator.isNotNull(ruleCategoriesStr) && !ruleCategoriesStr.isEmpty()) {
								String[] ruleCategoriesArray = ruleCategoriesStr.split(StringPool.COMMA);
								if (ruleCategoriesArray.length > 0) {
									for (String ruleCategory : ruleCategoriesArray) {
										// Si el contenido no contiene la categoria que se le va a aplicar con la regla se concatena el id de esta al string de ids del contenido
										if (!contentCategoriesStr.contains(ruleCategory)) {
											contentCategoriesStr = contentCategoriesStr + StringPool.COMMA + ruleCategory;
										}
									}
								}
							}
						} else {
							contentCategoriesStr = rule.getTags();
						}
						content.setTags(contentCategoriesStr);
					}
					else if (type == 2) {
						nameNav="Migracion";
						content.setActionId(rule.getActionId());
					}
					else if (type == 3) {
						nameNav="Carpeta";
						content.setJournalFolderId(rule.getJournalFolderId());
					}
					content.setLastModifiedUserId(userId);
					content.setDateModified(new Date());
					_contentLocalService.updateContent(content);
					_log.info("Regla aplicada desde "+nameNav+" a contenido con ID "+ content.getContentId()+" por el USERID "+userId);
					applyRuleToReferencedDocuments(userId, rule, content);
				}
			}
		}
		
	}
	/*
	public void revertRule(ActionRequest actionRequest, ActionResponse actionResponse) {
		long ruleId = ParamUtil.getLong(actionRequest, "ruleId", 0);
		Rule rule = _ruleLocalService.fetchRule(ruleId);
		
		if (Validator.isNotNull(rule)) {
			List<Content> contents = _contentLocalService.getContents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (Validator.isNotNull(contents)) {
				
				int type = rule.getTypeId();
				
				//Category rules
				if (type == 1) {
					_log.info("INICIO - ELIMINANDO REGLA [URL:" + rule.getUrl() + ", CATEGORY_IDS: " + rule.getTags() + "]");
				}
				//Migration rules
				if (type == 2) {
					_log.info("INICIO - ELIMINANDO REGLA [URL:" + rule.getUrl() + ", ACTION_MIGRATION_IDS: " + rule.getActionId() + "]");
				}
				//Journal Folder rules
				if (type == 3) {
					_log.info("INICIO - ELIMINANDO REGLA [URL:" + rule.getUrl() + ", JOURNALFOLDER_ID: " + rule.getJournalFolderId() + "]");
				}
				
				// Eliminamos regla de contenidos
				revertRuleToContents(rule, contents);
				
				// Eliminamos misma regla de documentos que hagan referencia a los contenidos que cumplan dicha regla
				revertRuleToReferencedDocuments(rule);
				
				_log.info("FIN - REGLA ELIMINADA");
				
			} else {
				_log.info("NO EXISTEN CONTENIDOS QUE CUMPLAN LA REGLA ESPECIFICADA");
			}
		} else {
			_log.info("NO SE HA ENCONTRADO LA REGLA");
		}
	
	}
	*/
	
	/*
	public void revertRuleToContents(Rule rule, List<Content> contents) {
		int type = rule.getTypeId();
		
		for (Content content : contents) {
			if("text/html".equals(content.getContentType())) {
				if (content.getUrl().contains(rule.getUrl())) {
					if (type == 1) {
						//Category rules
				
						content.setTags(null);
					}
					if (type == 2) {
						
						content.setActionId(0);
					}
					
					if (type == 3) {
						
						content.setJournalFolderId(0);
					}
					_contentLocalService.updateContent(content);
					_log.info("Regla eliminada contenido con ID "+ content.getContentId());
				}
			}
		}
	}
	*/
	
	/*
	public void revertRuleToReferencedDocuments(Rule rule) {
		List<ContentRelated> contentsToProcess = _contentRelatedLocalService.getContentRelateds(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		if(Validator.isNotNull(contentsToProcess)) {
			int type = rule.getTypeId();
			for(ContentRelated contentToProcess : contentsToProcess) {
				
				long mainContentId = contentToProcess.getContentParentId();
				long relatedContentId = contentToProcess.getContentLinkedId();
				
				Content mainContent = _contentLocalService.fetchContent(mainContentId);
				Content relatedContent = _contentLocalService.fetchContent(relatedContentId);
				
				if(Validator.isNotNull(mainContent) && mainContent.getUrl().contains(rule.getUrl())) {
					// Si el contenido cumple la regla, procedemos a eliminar la regla a los documentos referenciados
					if(Validator.isNotNull(relatedContent)) {
						if(!"text/html".equals(relatedContent.getContentType())) {
							// Si es un documento, eliminamos la regla
							if(type == 1) {
							
								relatedContent.setTags(null);
								_contentLocalService.updateContent(relatedContent);
								_log.info("Regla eliminada de documento con ID " + relatedContent.getContentId());
							}
							if(type == 2) {
								
								relatedContent.setActionId(0);
								_contentLocalService.updateContent(relatedContent);
								_log.info("Regla eliminada de documento con ID " + relatedContent.getContentId());
							}
							
						}
					}
				}
			}
		}
	}
	*/
	
	public void applyRuleToReferencedDocuments(long userId, Rule rule, Content content) {
		//List<ContentRelated> contentsToProcess = _contentRelatedLocalService.getContentRelateds(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		List<ContentRelated> contentsToProcess = getRuleRelatedDocuments(content);
		if(Validator.isNotNull(contentsToProcess)) {
			int type = rule.getTypeId();
			for(ContentRelated contentToProcess : contentsToProcess) {
				
				long mainContentId = contentToProcess.getContentParentId();
				long relatedContentId = contentToProcess.getContentLinkedId();
				
				Content mainContent = _contentLocalService.fetchContent(mainContentId);
				Content relatedContent = _contentLocalService.fetchContent(relatedContentId);
				
				if(Validator.isNotNull(mainContent) && mainContent.getUrl().contains(rule.getUrl())) {
					// Si el contenido cumple la regla, procedemos a aplicar la regla a los documentos referenciados
					if(Validator.isNotNull(relatedContent)) {
						if(!"text/html".equals(relatedContent.getContentType())) {
							// Si es un documento, aplicamos la regla
							if(type == 1) {
								//Category rules
								String contentCategoriesStr = relatedContent.getTags();
								if (Validator.isNotNull(contentCategoriesStr) && !contentCategoriesStr.isEmpty()) {
									String ruleCategoriesStr = rule.getTags();
									if (Validator.isNotNull(ruleCategoriesStr) && !ruleCategoriesStr.isEmpty()) {
										String[] ruleCategoriesArray = ruleCategoriesStr.split(StringPool.COMMA);
										if (ruleCategoriesArray.length > 0) {
											for (String ruleCategory : ruleCategoriesArray) {
												// Si el documento no contiene la categoria que se le va a aplicar con la regla se concatena el id de esta al string de ids del contenido
												if (!contentCategoriesStr.contains(ruleCategory)) {
													contentCategoriesStr = contentCategoriesStr + StringPool.COMMA + ruleCategory;
												}
											}
										}
									}
								} else {
									contentCategoriesStr = rule.getTags();
								}
								relatedContent.setTags(contentCategoriesStr);
								relatedContent.setLastModifiedUserId(userId);
								relatedContent.setDateModified(new Date());
								_contentLocalService.updateContent(relatedContent);
								_log.info("Regla aplicada a documento con ID " + relatedContent.getContentId()+" por el USERID "+userId);
							}
							if(type ==2) {
								//Migration rules
							
								relatedContent.setActionId(rule.getActionId());
								_contentLocalService.updateContent(relatedContent);
								_log.info("Regla aplicada a documento con ID " + relatedContent.getContentId()+" por el USERID "+userId);
							}
						
							
						}
					}
				}
			}
		}
	}
	
	public void appyAllRules(ActionRequest actionRequest, ActionResponse actionResponse) {
		long startMilis = System.currentTimeMillis();
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		
		List<Rule> rules = _ruleLocalService.getRules(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		if (Validator.isNotNull(rules) && rules.size() > 0) {
			//List<Content> contents = _contentLocalService.getContents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			int totalRules = 0;
			for(Rule rule : rules) {
				totalRules++;
				List<Content> contents = getRuleRelatedContens(rule);
				if (Validator.isNotNull(contents) && contents.size() > 0) {
					applyRuleToContents(userId, rule, contents);
					//applyRuleToReferencedDocuments(userId, rule);
				}
				_log.info("NUMERO DE REGLA APLICADA: "+totalRules+"/"+rules.size());
			}
		}
		
		long endMilis = System.currentTimeMillis();
		long totalTime = endMilis - startMilis;
		System.out.println("TIEMPO TOTAL DE APLICAR TODAS LAS REGLA: "+totalTime+"ms");
		
	}
	
	public List<Content> getRuleRelatedContens(Rule rule) {
		List<Content> results = new ArrayList<Content>();
		if(Validator.isNotNull(rule.getUrl())) {
			DynamicQuery dynamicQuery = _contentLocalService.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.ilike("url", "%"+rule.getUrl()+"%"));
			results = _contentLocalService.dynamicQuery(dynamicQuery, -1, -1);
		}
		return results;
	}
	
	
	public List<ContentRelated> getRuleRelatedDocuments(Content content) {
		DynamicQuery dynamicQuery = _contentRelatedLocalService.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("contentParentId", content.getContentId()));
		List<ContentRelated> results = _contentRelatedLocalService.dynamicQuery(dynamicQuery, -1, -1);
		return results;
	}
	
	@Reference
	private RuleLocalService _ruleLocalService;
	
	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;
	
	@Reference
	private GroupLocalService _groupLocalService;
	
	@Reference
	private ContentLocalService _contentLocalService;
	
	@Reference
	private ContentRelatedLocalService _contentRelatedLocalService;
	
	/**
	 * Log of the class
	 */
	private final Log _log = LogFactoryUtil.getLog(RulePortlet.class);

}