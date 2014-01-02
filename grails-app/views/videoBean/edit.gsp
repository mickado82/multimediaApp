<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		
		<script type="text/javascript">
		$(function() {
		    $( "#videoEditSaveBtn, #videoEditDeleteBtn" )
		      .button();
		  });
		</script>
	</head>
	<body>
		<div id="edit-videoBean" class="content scaffold-edit" role="main">
			<h1>Edit record</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${videoBeanInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${videoBeanInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form method="POST" action="update">
				<g:hiddenField name="id" value="${videoBeanInstance?.id}" />
				<g:hiddenField name="version" value="${videoBeanInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">	
					<input id="videoEditSaveBtn" type="submit" value="SAVE"/>
					<g:actionSubmit id="videoEditDeleteBtn" action="delete" value="DELETE" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
