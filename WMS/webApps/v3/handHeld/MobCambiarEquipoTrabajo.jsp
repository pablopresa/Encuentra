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



</head>
	<body id="dt_example" style="border-left: 0; width: 210px;"  onload="document.forms['contactform']['cod'].focus()">
		<c:if test="${uLogeado!=null}">
		<div class="products-main" align="center" style=" width: width: 210px; float: left;">
			<form class="form-horizontal" role="form" method="post"  action="<%=basePath%>cambioEquipoTrabajo.do?mob=1">
				<p style="color: red; font-size: 12pt">Trabajando en Grupo de Trabajo &nbsp;<c:out value="${uLogeado.equipo_trabajo}"></c:out></p>
					
					<select class="form-control" name="equipo" id="equipo" >
						<c:forEach var="d" items="${equipos_trabajo}">
							<c:if test="${d.id==uLogeado.equipo_trabajo}">
								<option value="${d.id}" selected="selected">${d.descripcion}</option>
							</c:if>
							<c:if test="${d.id!=uLogeado.equipo_trabajo}">
								<option value="${d.id}">${d.descripcion}</option>
							</c:if>
						</c:forEach>
					</select>
			<p> </p>
				<input class="col-100 button" type="submit" name="1" value="Cambiar Grupo de Trabajo"/>

			</form>
			<a href="<%=basePath%>/MenuMob.do?sec=M.Z"><input class="col-100 button" type="button" name="2"  value="Ir a menú"/></a> 
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
	</body>
</html>	