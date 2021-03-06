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
<c:choose>
  <c:when test="${css=='IE'}">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
  </c:otherwise>
</c:choose><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: center;
    background-color: #4CAF50;
    color: white;
}
</style>

</head>
	<body id="dt_example" style="border-left: 0; width: 210px;"  onload="document.forms['contactform']['cod'].focus()">
		<c:if test="${uLogeado!=null}">
		<div class="products-main" align="center" style=" width: width: 210px; float: left;">
			<div id="page-wrap" style="float: left; width: width: 210px;">
				Usuario: &nbsp;<c:out value="${uLogeado.nick}"></c:out><a href="<%=basePath%>/v3/handHeld/login.jsp">Cerrar Sesion</a>
				<p><b> ${mensaje} ${art} <br/> ${descripcionArticulo}<br/> </b></p>
				<p><b> ${descripcionOjo} <br/></b></p>

				<p><b style="color: red">Empaque: ${packing}</b> </p>
				<p><img alt="" src="${urlFoto}" style="width: 280px;"> </p>
				<table id="customers">
					<tr>
						<th style="width: 30px;">Art</th>
						
						<c:if test="${bandera==1}">
							<th style="width: 30px;">Des</th>
						</c:if>
						
						<c:if test="${bandera==2}">
							<th style="width: 30px;">DesArt</th>
						</c:if>
						<th style="width: 30px;">Ete</th>
						<th style="width: 30px;">Mod</th>
						<th style="width: 30px;">Ojo</th>
						<th style="width: 30px;">Cant</th>
						<th style="width: 30px;">Res</th>
					</tr>
					
					
						<c:forEach var="ot" items="${ojosTienen}">
						<tr class="gradeD">
							<td><c:out value="${ot.articulo}"></c:out></td>
							
							<c:if test="${bandera==1}">
							<td><c:out value="${ot.descripcion}"></c:out></td>
							</c:if>
							
							<c:if test="${bandera==2}">
								<td><c:out value="${ot.descArticulo}"></c:out></td>
							</c:if>
							
							<td><c:out value="${ot.modulo}"></c:out></td>
							<td><c:out value="${ot.estante}"></c:out></td>
							<c:if test="${ot.idBulto==''}"><td><c:out value="${ot.idOjo}"></c:out></td></c:if>
							<c:if test="${ot.idBulto!=''}"><td><c:out value="${ot.idOjo}"></c:out><br>(${ot.idBulto})</td></c:if>
							<td><c:out value="${ot.cantidad}"></c:out></td>
							<td><c:out value="${ot.cantidadReservada}"></c:out></td>
						</tr>
						</c:forEach>
					
					
					
				</table>
				<input class="col-100 button" type="button" name="1"  onclick="Volver();"   value="Volver"/>	
				<input class="col-100 button" type="button" name="1"  onclick="VolverMenu();"   value="Ir a menu"/>	
						
			</div>
		</div>
		</c:if>
		
		<script type="text/javascript">
			function VolverMenu(){
				window.location='<%=basePath%>pausarTarea.do?sale=si'
			}
			function Volver(){
				window.location='<%=basePath%>v3/handHeld/MobArtEnUbi.jsp'
			}
		</script>
		
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
		
		
