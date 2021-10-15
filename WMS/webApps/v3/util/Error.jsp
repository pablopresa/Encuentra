<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>404 Dash Able Bootstrap Admin Template</title>
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
	<!-- Google font-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
	<div id="container" class="container">
		<ul id="scene" class="scene">
			<li class="layer" data-depth="1.00"><img src="images/404-01.png"></li>
			<li class="layer" data-depth="0.60"><img src="images/shadows-01.png"></li>
			<li class="layer" data-depth="0.20"><img src="images/monster-01.png"></li>
			<li class="layer" data-depth="0.40"><img src="images/text-01.png"></li>
			<li class="layer" data-depth="0.10"><img src="images/monster-eyes-01.png"></li>
		</ul>
		<h1>Sucedio un Error - Encuentra no ha podido encontrar la pagina</h1>
		
	</div>
	<!-- Scripts -->
	<script src="js/parallax.js"></script>
	<script>
	// Pretty simple huh?
	var scene = document.getElementById('scene');
	var parallax = new Parallax(scene);
	</script>

</body>
</html>
