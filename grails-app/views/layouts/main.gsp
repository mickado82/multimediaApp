<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Mickabox"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'multimedia-icon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery', file: 'jquery-ui.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'lessgrid.css')}" type="text/css">
		
		<link href='http://fonts.googleapis.com/css?family=Leckerli+One' rel='stylesheet' type='text/css'>
		
		<g:javascript src="jquery/jquery-1.8.2.js"/>
		<g:javascript src="jquery/jquery-ui-1.9.0.js"/>
		<g:layoutHead/>
        <r:layoutResources />
        
	</head>
	
	<body>
	
	<div id="banner" role="banner">
		<div class="wrapper">

			<div href="index.html" id="logo">
				Mickabox
			</div>

			<nav>
				<ul id="nav" class="sf-menu">
					<li class="current-menu-item"><a href="index.html">home<span class="subheader">welcome</span></a></li>
					<li><a href=<g:createLink controller="dirBean" /> >Audio<span class="subheader">Albums</span></a> </li>
					<li><a href=<g:createLink controller="videoBean" /> >Videos<span class="subheader">Available movies</span></a></li>
					<li><a href="https://github.com/mickado82/multimediaApp" target="_blank">Fork me !!<span class="subheader">Github link</span></a></li>
					<li><a href=<g:createLink controller="logout" /> >Logout<span class="subheader">Disconnect</span></a></li>
				</ul>
			</nav>

			<div class="clearfix"></div>

		</div>
	</div>

	<g:layoutBody />
	<div class="footer" role="contentinfo">
		<div class="wrapper">
			<div class="clearfix"></div>
		</div>
	</div>
	<g:javascript library="application"/>
        <r:layoutResources />
	</body>
</html>