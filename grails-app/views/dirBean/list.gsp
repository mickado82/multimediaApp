
<%@ page import="fileslist.DirBean"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'dirBean.label', default: 'DirBean')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>


<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'menu/styles.css')}" type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'spinner.css')}" type="text/css">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Allan:700" type="text/css">
	
<link href='http://fonts.googleapis.com/css?family=Chivo:900italic,400' rel='stylesheet' type='text/css'>

<g:javascript src="jquery.waterwheelCarousel.min.js" />
<g:javascript src="dirBeanList.js" />

<script type="text/javascript">
	//We need to take URL from the GSP as it wouldn't be processed in the js file 
	requestAlbumsURL = "${createLink(action: 'queryList')}"
</script>

</head>
<body>
			
	<div id="dialogDiv" title="Preparing download ..."></div>

	<!-- MAIN -->
	<div id="main">

		<!-- Content -->
		<div id="content">

			<!-- waterwheel -->
			<div id="waterwheel-wrapper">
				<div id="waterwheel">
<%--					<img class="cover" src="../images/coversTests/1.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/2.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/3.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/4.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/1.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/2.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/3.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/4.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/2.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/3.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/4.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/4.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/2.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/3.jpg" alt="alt text" width="128" height="128"/>--%>
<%--					<img class="cover" src="../images/coversTests/4.jpg" alt="alt text" width="128" height="128"/>--%>
				</div>
				<a id="prev_btn" onclick="waterwheel.prev()"></a>
				<a id="next_btn" onclick="waterwheel.next()"></a>
				<div class="loader">
				    <span></span>
				    <span></span>
				    <span></span>
				</div>
			</div>
			<div id="shadow-waterwheel"></div>
			<!-- ENDS waterwheel -->


			<!-- Headline -->
			<div id="headline">Album title</div>
			<!-- ENDS Headline -->


			<!-- featured -->
			<div id="features" >
				<div style="float:left; padding-top: 20px;">Songs list</div>
				<button id="dl_btn" style="float:right;">Download</button>
			</div>
			
			<div id="songsdiv">
			</div>
		
		</div>
		<!-- ENDS content -->

		<div class="clearfix"></div>
		<div class="shadow-main"></div>


	</div>
	<!-- ENDS MAIN -->

</body>
</html>
