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

<script src="<%=basePath%>v3/assets/plugins/jquery/js/jquery.min.js"></script>

</head>
<body>
		<div class="field" align="center">
		
			<table style="padding: 2px;">
				<tr>
					<th style="text-align: center;">PEDIDO</th>
					<th style="text-align: center;">FECHA</th>
					<th></th>
					<th></th>
				</tr>				
				<c:forEach var="p" items="${posiblesPedidos}">
					<tr <c:if test="${p.confirmado=='0'}">style="color: #bfbfbf;" </c:if>>
						<td style="text-align: center;">${p.idPedido}</td>
						<td style="text-align: center;">${p.fecha}</td>
						<td style="text-align: center;"><a href="<%=basePath%>/DeralleVerifVerifEcommerce.do?idPedido=${p.idPedido}&accion=volver"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/information.png" border="0" style="width: 20px;"></a></td> 
						<c:if test="${p.confirmado=='1'}">
							<td style="text-align: center;"><button onclick="subButton(${p.idPedido});" ><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></button></td>
						</c:if>
						<c:if test="${p.confirmado=='0'}">
							<td style="text-align: center;"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px; filter: gray; -webkit-filter: grayscale(1); filter: grayscale(1);"></td>
						</c:if>
					</tr>
				</c:forEach>			
			</table>
			<a href="<%=basePath%>/v3/handHeld/MobVerifReqEcommerce.jsp"><input class="col-100 button" type="button" name="2" value="Volver"></a>
		
		</div>
		
	<script type="text/javascript">
		function subButton(id){
			 $("button").prop("disabled", true);
			 var req = '<%=basePath%>/VerifReqEcommerce.do?pedido='+id;
		     location.assign(req);
		}
		
		$(document).ready(function(){
			$("button").prop("disabled", false);
	});
	</script>	
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






























