
<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-videoBean" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-videoBean" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list videoBean">
			
				<g:if test="${videoBeanInstance?.available}">
				<li class="fieldcontain">
					<span id="available-label" class="property-label"><g:message code="videoBean.available.label" default="Available" /></span>
					
						<span class="property-value" aria-labelledby="available-label"><g:formatBoolean boolean="${videoBeanInstance?.available}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoBeanInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="videoBean.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${videoBeanInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoBeanInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label"><g:message code="videoBean.path.label" default="Path" /></span>
					
						<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${videoBeanInstance}" field="path"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${videoBeanInstance?.id}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
