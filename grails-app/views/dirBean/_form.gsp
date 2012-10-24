<%@ page import="fileslist.DirBean" %>



<div class="fieldcontain ${hasErrors(bean: dirBeanInstance, field: 'files', 'error')} ">
	<label for="files">
		<g:message code="dirBean.files.label" default="Files" />
		
	</label>
	<g:select name="files" from="${fileslist.FileBean.list()}" multiple="multiple" optionKey="id" size="5" value="${dirBeanInstance?.files*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dirBeanInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="dirBean.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${dirBeanInstance?.name}"/>
</div>

