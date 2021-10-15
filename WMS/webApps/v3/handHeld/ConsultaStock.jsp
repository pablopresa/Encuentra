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
<script>
var send=true;
function noenter() 
{
  if(send=true)
  {
  	send = false;
    return true;
  }
  else
  {
  	return !(window.event && window.event.keyCode == 13);
  }
  
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<body id="dt_example" style="border-left: 0;" onload=" document.getElementById('barra').focus();">

	
	
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/ConsultarStockTienda.do" style="float: left;">
	 
		<table>
			<tr>
				<td>
					<div class="field" align="center">
					<label for="lblArticulo">Articulo</label>
					<input type="text" name="barra" id="barra" onKeyPress="return noenter()" />
				</div>
				</td>
			<tr>
			<tr>
				<td>					
					<input type="submit" class="col-100 button" value="Consulta"/>
				</td>
			<tr>
			<tr>
				<td>
					<input class="col-100 button" type="button" name="1"  onclick="VolverMenu();"   value="Ir a menu"/>	
				</td>
			<tr>			
		</table>
		 	
						
	</form>
	<audio id="xyz" src="<%=basePath%>audio/errors.mp3" preload="auto"></audio>
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
	function VolverMenu(){
		window.location='<%=basePath%>MenuMob.do?sec=M.F'
	}
</script>	
</body>
</html>

