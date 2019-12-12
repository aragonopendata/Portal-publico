<%@ include file="init.jsp" %>

<style>
	.padding-top {
		margin-top: 1.25rem;
		margin-bottom: 1.25rem;
	}
</style>

<nav class="navbar navbar-collapse-absolute navbar-expand-md navbar-underline navigation-bar navigation-bar-secondary">
    <div class="container-fluid container-fluid-max-xl">
        <div class="navbar-collapse collapse" ref="content">
            <div class="container-fluid container-fluid-max-xl">
                <ul class="navbar-nav">
                	<portlet:renderURL var="webContentNavURL">
					    <portlet:param name="mvcPath" value="/view.jsp" />
					    <portlet:param name="menuId" value="Mapa web contenidos web" />
					</portlet:renderURL>
                    <li class="nav-item" data-nav-item-index="2" data-onclick="null">
                        <a href="<%=webContentNavURL%>" class='nav-link'><liferay-ui:message key="aragon-portal-utilities.nav.web-content"/></a>
                    </li>
                    
					<portlet:renderURL var="dlFilesNavURL">
					    <portlet:param name="mvcPath" value="/view.jsp" />
					    <portlet:param name="menuId" value="Mapa web documentos y multimendia" />
					</portlet:renderURL>
                    <li class="nav-item" data-nav-item-index="2" data-onclick="null">
                        <a href="<%=dlFilesNavURL%>" class='nav-link'><liferay-ui:message key="aragon-portal-utilities.nav.dl-file"/></a>
                    </li>
                    
                    <c:if test="<%=isAdmin %>" >
	                    <portlet:renderURL var="userLogNav">
						    <portlet:param name="mvcPath" value="/view.jsp" />
						    <portlet:param name="menuId" value="Historial de usuarios" />
						</portlet:renderURL>
	                    <li class="nav-item" data-nav-item-index="2" data-onclick="null">
	                        <a href="<%=userLogNav%>" class='nav-link'><liferay-ui:message key="aragon-portal-utilities.nav.user-log"/></a>
	                    </li> 
                    </c:if>
                    
                   	<portlet:renderURL var="urlFinderNav">
					    <portlet:param name="mvcPath" value="/view.jsp" />
					    <portlet:param name="menuId" value="Buscador de URL's" />
					</portlet:renderURL>
                    <li class="nav-item" data-nav-item-index="2" data-onclick="null">
                        <a href="<%=urlFinderNav%>" class='nav-link'><liferay-ui:message key="aragon-portal-utilities.nav.url-finder"/></a>
                    </li>
                    
                     <c:if test="<%=isAdmin %>" >
						<portlet:renderURL var="semaphoreNav">
					    	<portlet:param name="mvcPath" value="/view.jsp" />
					    	<portlet:param name="menuId" value="Configuracion" />
						</portlet:renderURL>
	                    <li class="nav-item" data-nav-item-index="2" data-onclick="null">
	                        <a href="<%=semaphoreNav%>" class='nav-link'><liferay-ui:message key="aragon-portal-utilities.nav.semaphore"/></a>
	                    </li>
                     </c:if>   
                </ul>
            </div>
        </div>
    </div>
</nav>