<%@ page import="fileslist.FileBean" %>



<div class="fieldcontain ${hasErrors(bean: fileBeanInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="fileBean.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${fileBeanInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fileBeanInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="fileBean.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="size" required="" value="${fileBeanInstance.size}"/>
</div>

