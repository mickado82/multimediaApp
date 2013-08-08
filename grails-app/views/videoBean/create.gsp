<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
		<script>
		  $(function() {
		    $( "#savebtn" ).button();
		  });
  		</script>
  		
	</head>
	<body>
		<div id="create-videoBean" class="content scaffold-create" role="main">
			<h1>Create new record</h1>
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
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<div id="savebtndiv">
				<fieldset class="buttons">
						<input id="savebtn" type="submit" value="CREATE"/>
				</fieldset>
					</div>
			</g:form>
		</div>
	</body>
</html>
