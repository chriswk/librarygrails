
<%@ page import="com.isharelib.library.domain.Collector" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'collector.label', default: 'Collector')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${collectorInstance}">
            <div class="errors">
                <g:renderErrors bean="${collectorInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${collectorInstance?.id}" />
                <g:hiddenField name="version" value="${collectorInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="username"><g:message code="collector.username.label" default="Username" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'username', 'errors')}">
                                    <g:textField name="username" value="${collectorInstance?.username}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password"><g:message code="collector.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" value="${collectorInstance?.password}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="email"><g:message code="collector.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${collectorInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="enabled"><g:message code="collector.enabled.label" default="Enabled" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'enabled', 'errors')}">
                                    <g:checkBox name="enabled" value="${collectorInstance?.enabled}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="passwordExpired"><g:message code="collector.passwordExpired.label" default="Password Expired" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'passwordExpired', 'errors')}">
                                    <g:checkBox name="passwordExpired" value="${collectorInstance?.passwordExpired}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="collection"><g:message code="collector.collection.label" default="Collection" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'collection', 'errors')}">
                                    
<ul>
<g:each in="${collectorInstance?.collection?}" var="c">
    <li><g:link controller="itemInstance" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="itemInstance" action="create" params="['collector.id': collectorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'itemInstance.label', default: 'ItemInstance')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="accountExpired"><g:message code="collector.accountExpired.label" default="Account Expired" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'accountExpired', 'errors')}">
                                    <g:checkBox name="accountExpired" value="${collectorInstance?.accountExpired}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="accountLocked"><g:message code="collector.accountLocked.label" default="Account Locked" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'accountLocked', 'errors')}">
                                    <g:checkBox name="accountLocked" value="${collectorInstance?.accountLocked}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="loans"><g:message code="collector.loans.label" default="Loans" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'loans', 'errors')}">
                                    
<ul>
<g:each in="${collectorInstance?.loans?}" var="l">
    <li><g:link controller="loan" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="loan" action="create" params="['collector.id': collectorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'loan.label', default: 'Loan')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="authorities"><g:message code="collector.authorities.label" default="Authorities" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: collectorInstance, field: 'authorities', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
