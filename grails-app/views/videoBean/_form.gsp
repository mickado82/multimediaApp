<%@ page import="videoslist.VideoBean" %>


<%-- 
<div class="fieldcontain ${hasErrors(bean: videoBeanInstance, field: 'available', 'error')} ">
	<label for="available">
		<g:message code="videoBean.available.label" default="Available" />
		
	</label>
	<g:checkBox name="available" value="${videoBeanInstance?.available}" />
</div>
 --%>
<div class="fieldcontain ${hasErrors(bean: videoBeanInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="videoBean.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${videoBeanInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoBeanInstance, field: 'path', 'error')} ">
	<label for="path">
		<g:message code="videoBean.path.label" default="Path" />
		
	</label>
	<g:textField name="path" value="${videoBeanInstance?.path}"/>
</div>

