<%@page import="com.liferay.petra.string.StringPool"%>
<%@ include file="init.jsp" %>

<%
List<SocialNetwork> socialNetworks = (List<SocialNetwork>)renderRequest.getAttribute("socialNetworkList");
%>

<c:if test="<%=socialNetworks != null && !socialNetworks.isEmpty()%>">
	<section>
		<div class="share-module u-padding-bottom-3">
			<div class="container">
				<div class="share-module__box">
					<h2 class="headerShare"><liferay-ui:message key="social-network-web.share-this-content-message" /></h2>
					<ul class="rrss">
						<%
						for (int i = 0; i < socialNetworks.size(); i++) { 
							long imageId = socialNetworks.get(i).getImageId();
							String url = socialNetworks.get(i).getUrl();
							url = SocialNetworkWebPortlet.getFullUrl(themeDisplay, url);
							String alt = socialNetworks.get(i).getAlt();
							String extraParams = StringPool.BLANK;
							if (!socialNetworks.get(i).getExtraParameters().isEmpty()) {
								String[] extraParameters = socialNetworks.get(i).getExtraParameters().split(",");
								for (String extraParameter : extraParameters) {
									String[] params = extraParameter.split("=");
									String paramName = params[0];
									String paramValue = params[1];
									extraParams = extraParams + paramName + StringPool.EQUAL + StringPool.QUOTE + paramValue + StringPool.QUOTE + StringPool.SPACE;
								}
							}
							
						%>
							<li>
								<a href="<%=url%>" class="icon-rrss" <%=extraParams%>>
									<img src="<%=themeDisplay.getPathImage()%>/template?img_id=<%=imageId%>&t=<%=WebServerServletTokenUtil.getToken(imageId) %>" alt="<%=alt%>"/>
								</a>
							</li>
						<%
						}
						%>
					</ul>
				</div>
			</div>
		</div>
	</section>
</c:if>