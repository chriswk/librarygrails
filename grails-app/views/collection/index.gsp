
<%@ page import="com.isharelib.library.domain.Item" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <sec:loggedInUserInfo field="username" />'s collection
            <div class="list">
              <table>
                <thead>
                  <tr>
                    <g:sortableColumn property="id" title="${message(code: 'itemInstance.id.label', default: 'Id')}" />
                    <g:sortableColumn property="item.title" title="${message(code: 'item.title.label', default: 'Title')}" />
                    <g:sortableColumn property="item.releaseYear" title="${message(code: 'item.releaseYear.label', default: 'Release Year')}" />
                    <g:sortableColumn property="isAvailable" title="Is available" />
                    <th><g:message code="item.itemType.label" default="Item type" /></th>
                  </tr>
                </thead>
                <tbody>
                <g:each in="${collection}" var="instance">
                  <tr>
                    <td><g:link action="show" id="${instance.id}" controller="itemInstance">${fieldValue(bean: instance, field: "id")}</g:link></td>
                    <td>${instance.item.title}</td>
                    <td><g:formatDate format="yyyy" date="${instance.item.releaseYear}" /></td>
                    <td>${instance.isAvailable}</td>
                    <td>${instance.item.itemType}</td>
                  </tr>
                </g:each>
                </tbody>
              </table>
            </div>
        </div>
    </body>
</html>
