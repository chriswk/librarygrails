
<%@ page import="com.isharelib.library.domain.ItemInstance" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'itemInstance.label', default: 'ItemInstance')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'itemInstance.id.label', default: 'Id')}" />
                        
                            <th><g:message code="itemInstance.item.label" default="Item" /></th>
                   	    
                            <th><g:message code="itemInstance.owner.label" default="Owner" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${itemInstanceInstanceList}" status="i" var="itemInstanceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${itemInstanceInstance.id}">${fieldValue(bean: itemInstanceInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: itemInstanceInstance, field: "item")}</td>
                        
                            <td>${fieldValue(bean: itemInstanceInstance, field: "owner")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${itemInstanceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
