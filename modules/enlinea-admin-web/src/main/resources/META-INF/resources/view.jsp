<%@ include file="init.jsp" %>

<%
	final String navigation = ParamUtil.getString(request, "navigation", "pages");
	
	PortletURL portletURL = renderResponse.createRenderURL();
	
	portletURL.setParameter("mvcRenderCommandName", "/");
	portletURL.setParameter("navigation", navigation);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
				navigationItem -> {
					navigationItem.setActive(navigation.equals("pages"));
					navigationItem.setHref(renderResponse.createRenderURL());
					navigationItem.setLabel(LanguageUtil.get(request, "pages"));
				});
				add(
				navigationItem -> {
					navigationItem.setActive(navigation.equals("procedures"));
					navigationItem.setHref(renderResponse.createRenderURL(), "navigation", "procedures");
					navigationItem.setLabel(LanguageUtil.get(request, "procedures"));
				});
			}
		}
	%>"
/>

<c:choose>
	<c:when test='<%= navigation.equals("procedures") %>'>
		<liferay-util:include page="/view_procedures.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/view_pages.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>