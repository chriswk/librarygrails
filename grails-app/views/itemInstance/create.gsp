
<%@ page import="com.isharelib.library.domain.ItemInstance" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'itemInstance.label', default: 'ItemInstance')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${itemInstanceInstance}">
            <div class="errors">
                <g:renderErrors bean="${itemInstanceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="item"><g:message code="itemInstance.item.label" default="Item" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstanceInstance, field: 'item', 'errors')}">
                                    <g:select name="item.id" from="${com.isharelib.library.domain.Item.list()}" optionKey="id" value="${itemInstanceInstance?.item?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="owner"><g:message code="itemInstance.owner.label" default="Owner" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstanceInstance, field: 'owner', 'errors')}">
                                    <g:select name="owner.id" from="${com.isharelib.library.domain.Collector.list()}" optionKey="id" value="${itemInstanceInstance?.owner?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
