<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css"	media="handheld" />
<link rel="stylesheet" id="smthemenewprint-css"	href="<%=basePath%>v3/assets/handheld/formsHandHel.css" type="text/css" /> -->
<c:choose>
  <c:when test="${css=='IE'}">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
  </c:otherwise>
</c:choose>

</head>
<body id="dt_example" style="border-left: 0;" >
	
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/CerrarCaja.do" style="float: left;">
		<div class="field" align="center">
			<div class="field" align="center">
				<label for="lblArticulo">Destino</label>
				<input type="text" name="idDestino" id="destino"  onchange="Send();"/>
			</div>
		</div>

		
		<input type="text" name="cerrarPick" value="${cerrarPick}" style="display: none;"/>
		<input type="text" name="actPick" value="${actPick}" style="display: none;"/>
		<div style="height: 5px;"></div>
		<a href="<%=basePath%>DarPickingVC.do?idPick=${cerrarPick}&accion=${actPick}">
			<input class="col-100 button" type="button" value="Volver"/>
		</a>
	</form>

	<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
	<script type="text/javascript">
		window.onload = function() {
		  document.getElementById("destino").focus();
		};
	</script>
	<script type="text/javascript">
	
	
	function Send()
	{
		if(confirm("Esta seguro que quiere cerrar esta caja? Esto generara un movimiento de stock"))
		{
			document.forms["contactform"].submit();
		}	
	}
	
	
	function showAlert(msj) {
		alert(msj);
	}
	
</script>

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
</body>
</html>






























