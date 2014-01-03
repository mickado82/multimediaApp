
<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		
		<script>
		$(document).ready(function() {
			//Underline the corresponding menu item
			$("#videoMenuItem").addClass("current-menu-item");
			});
		</script>
		
	</head>
	<body>
		
		<div id="list-videoBean" class="content scaffold-list" role="main">
			<h1>Videos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'videoBean.name.label', default: 'Name')}" />
						<g:sortableColumn property="size" title="${message(code: 'videoBean.name.label', default: 'Size (GB)')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${videoBeanInstanceList}" status="i" var="videoBeanInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<g:if test="${videoBeanInstance.available == true}">
						
								<!-- We want to display the label if any. Display the file name if no label -->
								<g:set var="movieName" value="${videoBeanInstance?.label ?: videoBeanInstance?.name}" />
								
								<!-- Test the path to build the link -->
								<g:if test="${videoBeanInstance?.path}">
									<td><a href="${request.getContextPath()}/video/${videoBeanInstance.path}/${videoBeanInstance.name}">${movieName}</a></td>
								</g:if>
								<g:else>
	     							<td><a href="${request.getContextPath()}/video/${videoBeanInstance.name}">${movieName}</a></td>
								</g:else>
								
								
								<td>${fieldValue(bean: videoBeanInstance, field: "size")}</td>
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
