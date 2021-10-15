<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:choose>
	<c:when test="${css=='IE'}">
		<link rel="stylesheet"
			href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
	</c:when>
	<c:otherwise>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
	</c:otherwise>
</c:choose>
<!-- Js Jquery se debe importar antes -->
	<script src="<%=basePath%>v3/assets/plugins/jquery/js/jquery.min.js"></script>
</head>
<body>

	<c:if test="${uLogeado!=null}">

		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

<body >
	<c:if test="${uLogeado!=null}">
		<form id="contactform" class="rounded" method="post" action="<%=basePath%>/OptionPickingPackage.do?option=1" style="float: left;">
			<div class="row">
				
				<a href="<%=basePath%>/v3/handHeld/AlternativePickingPackage.jsp"> <input class="col-100 button" type="button" name="2" value="VOLVER"/></a>
				
			</div>
			<div class="row">
				<h2>Indicar ubicacion donde se dejara el sobrante de este bulto
				</h2>
				<!--  <h3>Bulto con ${modeloPickingBulto.cantidadTotal} unidades</h3>
				<h3>Usted conto ${modeloPickingBulto.count} unidades</h3>-->
			</div>
			<div class="row">
				<input class="col-100" type="text" value="0" id="idOjo" name="idOjo" style="text-align: center; margin-bottom: 15px;"/>
				<br>
				<input class="col-100 button" type="submit" value="Ingresar" />
				
			</div>
			
		</form>
		<audio id="xyz" src="<%=basePath%>audio/errors.mp3" preload="auto"></audio>
	</c:if>
</body>

		</html>


	</c:if>


<c:if test="${menError!=null}">
	<script type="text/javascript"> 
		var alarma = document.getElementById('xyz');
		alarma.play().catch(function() {
	    document.getElementById('xyz').play();
		});
		alarma.addEventListener('ended', showAlert);

		function showAlert() {
		 alert('${menError}');
		}	
		
		window.onload = function() {
         document.getElementById("idOjo").focus();
       };		
		
	</script>	
</c:if>


 
 <script type="text/javascript">
	function inputOption(option){
		location.replace("<%=basePath%>OptionPickingPackage.do?option="+option);
	}
</script>
 
 <c:remove var="menError" scope="session" />
</html>