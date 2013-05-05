
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
				<li><a class="home" href="${createLink(uri: '/')}">Musik</a></li>
			</ul>
		</div>
		<div id="list-videoBean" class="content scaffold-list" role="main">
			<h1>Videos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'videoBean.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${videoBeanInstanceList}" status="i" var="videoBeanInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<g:if test="${videoBeanInstance.available == true}">
							<g:if test="${videoBeanInstance?.path}">
								<td><a href="${request.getContextPath()}/video/${videoBeanInstance.path}/${videoBeanInstance.name}">${fieldValue(bean: videoBeanInstance, field: "name")}</a></td>
							</g:if>
							<g:else>
     							<td><a href="${request.getContextPath()}/video/${videoBeanInstance.name}">${fieldValue(bean: videoBeanInstance, field: "name")}</a></td>
							</g:else>
						</g:if>
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
