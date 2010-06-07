
<%@ page import="com.isharelib.library.domain.RequestMap" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'requestMap.label', default: 'RequestMap')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'requestMap.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="url" title="${message(code: 'requestMap.url.label', default: 'Url')}" />
                        
                            <g:sortableColumn property="configAttribute" title="${message(code: 'requestMap.configAttribute.label', default: 'Config Attribute')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${requestMapInstanceList}" status="i" var="requestMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${requestMapInstance.id}">${fieldValue(bean: requestMapInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: requestMapInstance, field: "url")}</td>
                        
                            <td>${fieldValue(bean: requestMapInstance, field: "configAttribute")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${requestMapInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
