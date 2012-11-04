
<%@ page import="fileslist.DirBean"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'dirBean.label', default: 'DirBean')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'accordion/demo.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'accordion/style.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'dirList.css')}" type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'menu/styles.css')}" type="text/css">

<g:javascript src="jquery.mousewheel.js" />
<g:javascript src="jquery.easing.1.3.js" />
<g:javascript src="jquery.vaccordion.js" />
<g:javascript src="jquery.fileDownload.js" />
<g:javascript src="dirBeanList.js" />
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li class='active '><a href='#'><span>Home</span></a></li>
			<li><a href='#'><span>Item1</span></a></li>
			<li><a href='#'><span>Item2</span></a></li>
			<li><a href='#'><span>Item3</span></a></li>
		</ul>
	</div>
	<a href="#list-dirBean" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
			
	<div id="dialogDiv" title="Preparing download ..."></div>
	
	<div id="borderDiv">
		
		<a href="<g:createLink action="list" params="[sort: 'newest']"/>"><button id='orderNewestBtn' class="orderButton">Last added first</button></a>
		 
		<a href="<g:createLink action="list" params="[sort: 'name']"/>"><button id='orderByNameBtn' class="orderButton">Sort by name</button></a>
		<div id="va-accordion" class="va-container">
			
			<div class="va-nav">
				<span class="va-nav-prev">Previous</span> <span class="va-nav-next">Next</span>
			</div>
			
			<div class="va-wrapper">
				<g:each in="${dirBeanInstanceList}" status="i" var="dirBeanInstance">
					<div class="folderSlice">
						<div style="border-style: solid; border-width: 2px; height: 500px">
							
							<h3 class="folderTitle">
								${fieldValue(bean: dirBeanInstance, field: "name")}
							</h3>
							
							<div>
								<button class="dlButton"
									onclick="downloadAlbum(${dirBeanInstance.id})">DOWNLOAD</button>
							</div>
							
							<ul class='tracksList'>
								<g:each in="${dirBeanInstance.files}">
									<li class='trackItem'>
										${it.name}
									</li>
								</g:each>
							</ul>
							
							<div class='rotFrame'>
								<div class='dirIcon'>
									<div class='front face'>
										<g:if test="${dirBeanInstance.frontCover != null}">
											<img
												src="${request.getContextPath()}/resources/${fieldValue(bean: dirBeanInstance, field: "name")}/${fieldValue(bean: dirBeanInstance, field: "frontCover")}"
												alt="Image not available" width="128" height="128">
										</g:if>
										<g:else>
											<img src="../images/dirIcon.png" alt="Image not available"
												width="128" height="128">
										</g:else>
									</div>
									
									<div class='back face center'>
										<g:if test="${dirBeanInstance.backCover != null}">
											<img class='backCover'
												src="${request.getContextPath()}/resources/${fieldValue(bean: dirBeanInstance, field: "name")}/${fieldValue(bean: dirBeanInstance, field: "backCover")}"
												alt="Image not available" width="128" height="128">
										</g:if>
										<g:else>
											<img class='backCover' src="../images/dirIcon.png"
												alt="Image not available" width="128" height="128">
										</g:else>
									</div>
								</div>
							</div>
						</div>
					</div>
				</g:each>
			</div>
		</div>
	</div>
</body>
</html>
