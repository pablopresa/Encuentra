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
function barraFocus() 
{
  if(window.event.keyCode == 13)
  {
  	document.getElementById('barra').focus();
  	return false;
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

	<c:if test="${idPickingVE!=null}">
		<c:set var = "idPickVE" scope = "page" value = "${idPickingVE}"/>
	</c:if>
	<c:if test="${idPickingVE==null}">
		<c:set var = "idPickVE" scope = "page" value = "${0}"/>
	</c:if>
	
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/VerifReqEcommerce.do" style="float: left;">
	 
		<div class="field" align="center">
			<div class="field" align="center">
				<label for="lblArticulo">Pedidos en picking:</label>
				<input type="text" name="idPickingVE" id="idPickingVE" onKeyPress="return barraFocus()" value="${idPickVE}" />
			</div>
			<div class="field" align="center">
				<label for="lblArticulo">Barra/Articulo</label>
				<input type="text" name="barra" id="barra" onKeyPress="return noenter()" />
			</div>
			<br />
			
			<div class="field" align="center">
				<input type="submit" class="col-100 button" value="Verificar" />
			</div>
			
		</div>
		
		<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.C'"  value="Ir a menu"/>	
						
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
</body>
</html>






























