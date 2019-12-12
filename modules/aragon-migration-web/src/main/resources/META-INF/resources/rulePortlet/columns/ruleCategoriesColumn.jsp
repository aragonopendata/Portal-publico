<%@page import="es.aragon.base.migration.model.Rule"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetVocabulary"%>
<%@page import="java.util.Locale"%>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>

<%@ include file="../init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Rule rule = (Rule) row.getObject();

Map<Long, List<AssetCategory>> vocabulariesMap = new HashMap<Long, List<AssetCategory>>();

String contentTags = rule.getTags();
if (contentTags != null & !contentTags.isEmpty()) {
	String [] contentTagsStringArray = contentTags.split(",");
	if (contentTagsStringArray != null && contentTagsStringArray.length > 0) {
		for (String contentTagString : contentTagsStringArray) {
			long categoryId = Long.parseLong(contentTagString);
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
			if (assetCategory != null) {
				List<AssetCategory> categories;
				if (vocabulariesMap.containsKey(assetCategory.getVocabularyId())) {
					categories = vocabulariesMap.get(assetCategory.getVocabularyId());
				} 
				else {
					categories = new ArrayList<AssetCategory>();
				}
				categories.add(assetCategory);
				vocabulariesMap.put(assetCategory.getVocabularyId(), categories);
			}
		}
	}
}
%>

<%
for (Map.Entry<Long, List<AssetCategory>> entry : vocabulariesMap.entrySet()) {
	AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(entry.getKey());
	if (assetVocabulary != null) {
%>
		<div>
			<b><%=assetVocabulary.getTitle(Locale.getDefault())%>: </b> 
			<%
			List<AssetCategory> vocabularyCategories = entry.getValue();
			if (vocabularyCategories != null && !vocabularyCategories.isEmpty()) {
				for (AssetCategory vocabularyCategory : vocabularyCategories) {
			%>
					<span class="badge badge-primary">
						<%=vocabularyCategory.getTitle(locale)%> 
						<%
						try {
						%>
						<span style="padding-left: 5px;">
							<liferay-ui:icon-help message="<%=vocabularyCategory.getPath(locale)%>"/>
						</span>
						<%
						} catch (Exception e) {
							%> </span> <% 
						}
						%>
					</span>
			<%
				}
			}
			%>	
		</div>
<%
	}
}
%>