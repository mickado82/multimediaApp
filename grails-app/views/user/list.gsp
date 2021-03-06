
<%@ page import="security.User"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'user.label', default: 'User')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'user.css')}"
	type="text/css">
	
	<script>
		  $(function() {
		    $( "#adduserbtn" ).button();
		  });
  		</script>
  		
</head>
<body>
	
	<div id="list-user" class="content scaffold-list" role="main" >
		<h1>USERS</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<div style="background-color: white; padding: 20px 0px;">
			<table>
				<thead>
					<tr>
	
						<g:sortableColumn property="username"
							title="${message(code: 'user.username.label', default: 'Username')}" />
	
						<%-- <g:sortableColumn property="password" title="${message(code: 'user.password.label', default: 'Password')}" /> --%>
	
						<g:sortableColumn property="accountExpired"
							title="${message(code: 'user.accountExpired.label', default: 'Account Expired')}" />
	
						<g:sortableColumn property="accountLocked"
							title="${message(code: 'user.accountLocked.label', default: 'Account Locked')}" />
	
						<g:sortableColumn property="enabled"
							title="${message(code: 'user.enabled.label', default: 'Enabled')}" />
	
						<g:sortableColumn property="passwordExpired"
							title="${message(code: 'user.passwordExpired.label', default: 'Password Expired')}" />
	
					</tr>
				</thead>
				<tbody>
					<g:each in="${userInstanceList}" status="i" var="userInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
	
							<td><g:link action="show" id="${userInstance.id}">
									${fieldValue(bean: userInstance, field: "username")}
								</g:link></td>
	
							<%--<td>${fieldValue(bean: userInstance, field: "password")}</td>  --%>
	
							<td><g:formatBoolean boolean="${userInstance.accountExpired}" /></td>
	
							<td><g:formatBoolean boolean="${userInstance.accountLocked}" /></td>
	
							<td><g:formatBoolean boolean="${userInstance.enabled}" /></td>
	
							<td><g:formatBoolean
									boolean="${userInstance.passwordExpired}" /></td>
	
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
			<div  style="text-align: center; margin: 20px;">	
				<g:link action="create"> <button id="adduserbtn">ADD USER</button> </g:link>
			</div>
		</div>
	</div>
</body>
</html>
