
<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-videoBean" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-videoBean" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'videoBean.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="path" title="${message(code: 'videoBean.path.label', default: 'Path')}" />
						
						<g:sortableColumn property="available" title="${message(code: 'videoBean.available.label', default: 'Available')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${videoBeanInstanceList}" status="i" var="videoBeanInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${videoBeanInstance.id}">${fieldValue(bean: videoBeanInstance, field: "name")}</g:link></td>
					
						<td>
							<g:if test="${videoBeanInstance.path}">
    							 ${fieldValue(bean: videoBeanInstance, field: "path")}
							</g:if>
							<g:else>
    							NONE
							</g:else>
						
						</td>
						
						<td>${fieldValue(bean: videoBeanInstance, field: "available")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${videoBeanInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
