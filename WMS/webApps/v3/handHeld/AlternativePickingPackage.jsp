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
		<form onsubmit="return validate(this);" id="contactform" class="rounded" method="post" action="<%=basePath%>/ControlarRemito.do" style="float: left;">
			<div class="row">
				
				<a href="<%=basePath%>/v3/handHeld/PickingPackageContent.jsp"> <input class="col-100 button" type="button" name="2" value="VOLVER"/></a>
				
			</div>
			<div class="row">
				<h2>Este bulto contiene mas unidades de las que se necesitan, ¿Como desea continuar?
				</h2>
				<!--  <h3>Bulto con ${modeloPickingBulto.cantidadTotal} unidades</h3>
				<h3>Usted conto ${modeloPickingBulto.count} unidades</h3>-->
			</div>
			<div class="row">
				<input class="col-100 button" type="button" value="Tomar bulto y especificar ubicacion de sobrantes" onclick="inputOption(0)"/>
				<br>
				<input class="col-100 button" type="button" value="Tomar articulos del bulto" onclick="inputOption(2)"/>
				
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
		
	</script>	
</c:if>


 
 <script type="text/javascript">
	function inputOption(option){
		location.replace("<%=basePath%>OptionPickingPackage.do?option="+option);
	}
</script>
 
 <c:remove var="menError" scope="session" />
</html>