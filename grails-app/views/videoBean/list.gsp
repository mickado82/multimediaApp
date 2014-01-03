
<%@ page import="videoslist.VideoBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'videoBean.label', default: 'VideoBean')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		
		<script>
		  $(function() {
		    $( "#addbtn" ).button();
		  });
  		</script>
  
	</head>
	<body>
		<div id="list-videoBean" class="content scaffold-list" role="main" style="max-width: none">
			<h1>REFERENCED MEDIAS</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>						
						<g:sortableColumn property="name" title="${message(code: 'videoBean.name.label', default: 'Name')}" />
						
						<g:sortableColumn property="path" title="${message(code: 'videoBean.path.label', default: 'Path')}" />
					
						<g:sortableColumn property="label" title="${message(code: 'videoBean.label.label', default: 'Label')}" />
					
						<g:sortableColumn property="imdbId" title="${message(code: 'videoBean.available.label', default: 'IMDB id')}" />

						<g:sortableColumn property="available" title="${message(code: 'videoBean.available.label', default: 'Available')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${videoBeanInstanceList}" status="i" var="videoBeanInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${videoBeanInstance.id}">${fieldValue(bean: videoBeanInstance, field: "name")}</g:link></td>
						
						<td>
							<g:if test="${videoBeanInstance.path}">
    							 ${fieldValue(bean: videoBeanInstance, field: "path")}
							</g:if>
							<g:else>
    							NONE
							</g:else>
						
						</td>
						
						<td>
							<g:if test="${videoBeanInstance.label}">
    							 ${fieldValue(bean: videoBeanInstance, field: "label")}
							</g:if>
							<g:else>
    							NONE
							</g:else>
						</td>
						
						<td>${fieldValue(bean: videoBeanInstance, field: "imdbId")}</td>
						<td>${fieldValue(bean: videoBeanInstance, field: "available")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>	
			<div id="addbtndiv">	
				<g:link action="create"> <button id="addbtn">ADD VIDEO</button> </g:link>
			</div>
			<div class="pagination">
				<g:paginate total="${videoBeanInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
