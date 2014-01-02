
<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		
		<script>
		  $(function() {
		    $( "#deletebtn" ).button();
		    $( "#createBtn").button();
		  });
  		</script>
	</head>
	<body>
		<div id="show-videoBean" class="content scaffold-show" role="main">
			<h1>Record details</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ul class="property-list videoBean">
			
				<g:if test="${videoBeanInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label">Name :</span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${videoBeanInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoBeanInstance?.available}">
				<li class="fieldcontain">
					<span id="available-label" class="property-label">Found in directory :</span>
					
						<span class="property-value" aria-labelledby="available-label"><g:formatBoolean boolean="${videoBeanInstance?.available}" /></span>
					
				</li>
				</g:if>
				<g:else>
					<li class="fieldcontain">
						<div style="color: rgb(255,0,0);">File has not been found on server !!</div>
					</li>
				</g:else>
			
				<g:if test="${videoBeanInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label">Path : </span>
					
						<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${videoBeanInstance}" field="path"/></span>
					
				</li>
				</g:if>
			
			</ul>
			<g:form action = "delete">
				<g:hiddenField name="id" value="${videoBeanInstance?.id}" />
				<fieldset class="buttons">
					<input id="deletebtn" type="submit" value="DELETE" onclick="return confirm('Are you sure???')"/>
					<a  href=<g:createLink action="create" /> id="createBtn">CREATE</a>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
