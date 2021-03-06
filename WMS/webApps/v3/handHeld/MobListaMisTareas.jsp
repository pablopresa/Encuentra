<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<script>

function preventBack() {
	window.history.forward();
}

setTimeout("preventBack()", 0);
window.onunload = function() {
	null
};
</script>


</head>
	<body id="dt_example" style="border-left: 0; width: 210px;"  onload="document.forms['contactform']['cod'].focus()">
		<c:if test="${uLogeado!=null}">
		<div class="products-main" align="center" style=" width: width: 210px; float: left;">
			<div id="page-wrap" style="float: left; width: width: 210px;">
				Usuario: &nbsp;<c:out value="${uLogeado.nick}"></c:out><a href="<%=basePath%>/v3/handHeld/login.jsp">Cerrar Sesion</a>
				<table>
					
						<tr>
							<th style="width: 50px;">Tarea</th>
							<th style="width: 50px;">Estado</th>
							<th style="width: 50px;">Cant.</th>
							<th style="width: 50px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th style="width: 50px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th style="width: 50px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						</tr>
					
					
					<c:forEach var="d" items="${tarMob}">
						<tr>
							<td style="width: 50px;"><div align="center"><c:out value="${d.tipo.descripcion}"></c:out>&nbsp;&nbsp;<c:out value="${d.descripcion}"></c:out>${d.responsable.descripcion}</div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${d.estado.descripcion}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${d.cantidad}"></c:out></div></td>
							<c:if test="${d.tipo.id==0}">
								<c:if test="${d.estado.id==0}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/eventoTarea.do?estado=1&idDoc=0&main=${d.main}&tarea=${d.tarea}&idRepo=-100&nombreTarea=${d.descripcion}"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==4}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/eventoTarea.do?estado=1&idDoc=0&main=${d.main}&tarea=${d.tarea}&idRepo=-100&nombreTarea=${d.descripcion}"><img alt="Continuar" src="<%=basePath%>imagenes/icons/arrow_rotate_clockwise.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==1}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/eventoTarea.do?estado=1&idDoc=0&main=${d.main}&tarea=${d.tarea}&idRepo=-100&nombreTarea=${d.descripcion}"><img alt="ir" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<td style="width: 50px;"><div align="center">--</div></td>
								<td style="width: 50px;"><div align="center"><a><img alt="Iniciar" src="<%=basePath%>imagenes/icons/calculator.png" border="0" style="width: 20px;"></a></div></td>
							</c:if>
							<c:if test="${d.tipo.id<3 && d.tipo.id>0}">
								<c:if test="${d.estado.id==0}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/eventoTarea.do?estado=1&idDoc=${d.idRepo}&main=${d.main}&tarea=${d.tarea}&idRepo=${d.idRepo}&nombreTarea=${d.descripcion}&idDoc=${d.idRepo}"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==4}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/eventoTarea.do?estado=1&idDoc=${d.idRepo}&main=${d.main}&tarea=${d.tarea}&idRepo=${d.idRepo}&nombreTarea=${d.descripcion}&idDoc=${d.idRepo}"><img alt="Continuar" src="<%=basePath%>imagenes/icons/arrow_rotate_clockwise.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==1}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/eventoTarea.do?estado=1&idDoc=${d.idRepo}&main=${d.main}&tarea=${d.tarea}&idRepo=${d.idRepo}&nombreTarea=${d.descripcion}&idDoc=${d.idRepo}"><img alt="ir" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<td style="width: 50px;"><div align="center">--</div></td>
								<td style="width: 50px;"><div align="center"><a><img alt="Iniciar" src="<%=basePath%>imagenes/icons/calculator.png" border="0" style="width: 20px;"></a></div></td>
							</c:if>
							
							<c:if test="${d.tipo.id==5}">
								<c:if test="${d.estado.id==0}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/ClasificarTarea.do?idMain=${d.main}&idTarea=${d.tarea}"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==4}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/ClasificarTarea.do?idMain=${d.main}&idTarea=${d.tarea}"><img alt="Continuar" src="<%=basePath%>imagenes/icons/arrow_rotate_clockwise.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==1}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/ClasificarTarea.do?idMain=${d.main}&idTarea=${d.tarea}"><img alt="Continuar" src="<%=basePath%>imagenes/icons/arrow_rotate_clockwise" border="0" style="width: 20px;"></a></div></td>
								</c:if>
							
								 <!-- <td style="width: 50px;"><div align="center"><a href="<%=basePath%>/cambiarEstadoTarea.do?idTarea=${d.tarea}&tipo=2&dis=1"><img alt="Terminar" src="<%=basePath%>imagenes/icons/stop.png" border="0" style="width: 20px;"></a></div></td>
								<td style="width: 50px;"><div align="center"><a><img alt="Dispositivo" src="<%=basePath%>imagenes/icons/computer.png" border="0" style="width: 20px;"></a></div></td>
								  -->
								  <td style="width: 50px;"></td>
								  <td style="width: 50px;"></td>
							</c:if>
							<c:if test="${d.tipo.id==117}"> <!-- Muestro las que estan asignadas, en ejercucion y pausadas -->
								<c:if test="${d.estado.id==0}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/BultosABajar.do?estado=0&tarea=${d.tarea}"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==4}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/BultosABajar.do?estado=4&idDoc=0&main=${d.main}&tarea=${d.tarea}&idRepo=-100&nombreTarea=${d.descripcion}"><img alt="Continuar" src="<%=basePath%>imagenes/icons/arrow_rotate_clockwise.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<c:if test="${d.estado.id==1}">
									<td style="width: 50px;"><div align="center"><a href="<%=basePath%>/BultosABajar.do?estado=1&idDoc=0&main=${d.main}&tarea=${d.tarea}&idRepo=-100&nombreTarea=${d.descripcion}"><img alt="ir" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
								</c:if>
								<td style="width: 50px;"><div align="center">--</div></td>
								<td style="width: 50px;"><div align="center"><a><img alt="Iniciar" src="<%=basePath%>imagenes/icons/calculator.png" border="0" style="width: 20px;"></a></div></td>
							</c:if>
						</tr>
					</c:forEach>
					
					
					
					
				</table>
				<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.B'"  value="Ir a menu"/>		
						
			</div>
		</div>
		</c:if>
		<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
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
	<c:remove var="menError" scope="session" />
	</body>
</html>	
		
		
