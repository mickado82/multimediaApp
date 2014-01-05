
<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<g:javascript src="videoBeanBrowse.js" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'videoBean.css')}" type="text/css">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Allan:700" type="text/css">
		<link href='http://fonts.googleapis.com/css?family=Chivo:900italic,400' rel='stylesheet' type='text/css'>
		
		<style type="text/css">
		
		
		
		</style>
		
	</head>
	<body>
		
		<div id="list-videoBean" class="content scaffold-list" role="main">
			<h1>Videos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<div id="accordion">
				<g:each in="${videoBeanInstanceList}" status="i" var="videoBeanInstance">
					<g:if test="${videoBeanInstance.available == true}">
					<!-- We want to display the label if any. Display the file name if no label -->
					<g:set var="movieName" value="${videoBeanInstance?.label ?: videoBeanInstance?.name}" />
					 <h3 id=${videoBeanInstance.tMovieDbId}>${movieName}</h3>
					 <div>
						<p class = "moviDescDiv">
						</p>
						<p class = "movieLinkDiv">
							<span style = "float: left;">
								<i>Server file name: ${videoBeanInstance.name}</i>
							</span>
							<span style = "float: right;">
								<!-- Test the path to build the link -->
								<g:if test="${videoBeanInstance?.path}">
									<a class = "videoDlBtn"  href="${request.getContextPath()}/video/${videoBeanInstance.path}/${videoBeanInstance.name}">Download</a>
								</g:if>
								<g:else>
	     							<a class = "videoDlBtn"  href="${request.getContextPath()}/video/${videoBeanInstance.name}">Download</a>
								</g:else>
							</span>
					  	</p>
					  </div>
				  </g:if>
				</g:each>
			</div>
			<!-- End of the accordion -->
		</div>
	</body>
</html>
