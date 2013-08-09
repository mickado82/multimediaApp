
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
		    $( "#createvideobtn > a >button").button();
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
			
				<g:if test="${videoBeanInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label">Path : </span>
					
						<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${videoBeanInstance}" field="path"/></span>
					
				</li>
				</g:if>
			
			</ul>
			<g:form action = "delete">
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${videoBeanInstance?.id}" />
					<div id="deletebtndiv">
						<input id="deletebtn" type="submit" value="DELETE" onclick="return confirm('Are you sure???')"/>
					</div>
				</fieldset>
			</g:form>
			<div id="createvideobtn" style="text-align: center; padding: 20px;">
				<a href=<g:createLink action="create" /> ><button >CREATE NEW</button></a>
			</div>
		</div>
	</body>
</html>
