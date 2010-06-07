
<%@ page import="com.isharelib.library.domain.Collector" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'collector.label', default: 'Collector')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'collector.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="username" title="${message(code: 'collector.username.label', default: 'Username')}" />
                        
                            <g:sortableColumn property="password" title="${message(code: 'collector.password.label', default: 'Password')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'collector.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="enabled" title="${message(code: 'collector.enabled.label', default: 'Enabled')}" />
                        
                            <g:sortableColumn property="passwordExpired" title="${message(code: 'collector.passwordExpired.label', default: 'Password Expired')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${collectorInstanceList}" status="i" var="collectorInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${collectorInstance.id}">${fieldValue(bean: collectorInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: collectorInstance, field: "username")}</td>
                        
                            <td>${fieldValue(bean: collectorInstance, field: "password")}</td>
                        
                            <td>${fieldValue(bean: collectorInstance, field: "email")}</td>
                        
                            <td><g:formatBoolean boolean="${collectorInstance.enabled}" /></td>
                        
                            <td><g:formatBoolean boolean="${collectorInstance.passwordExpired}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${collectorInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
