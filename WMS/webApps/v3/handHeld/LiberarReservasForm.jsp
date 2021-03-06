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


<script type="text/javascript">
	
	
 function eliminar(idOjo,idArticulo){
	 if(confirm("?Desea continuar?")){
	 var a = '<%=basePath%>/liberarReservas.do?ojoReservado='+idOjo+'&articuloReservado='+idArticulo;
		 location.href = a;
	 }
		 
 }
	
	function handleEnter (event) {
		 var char = event.which || event.keyCode;
			
		    // If the user has pressed enter
		    if (char == 13 || char == 1) 
			{
	    	 var ojo = document.getElementById('base').value;
	 		 var a = '<%=basePath%>/MostrarReservas.do?ojoReservado='+ojo;
	 		 location.href = a;

	     	} else {
	     		return true;
	     	}
		    }
	

	
</script>
</head>
<body>

<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body id="dt_example" style="border-left: 0;"></body>



<c:if test="${uLogeado!=null}">
	<c:forEach var="d" items="${uLogeado.seguridadUI}">
		<c:if test="${d=='box_cant_picking'}">
			<c:set var = "puede" scope = "page" value = "1"/>
		</c:if>
	</c:forEach>
				
				<div class="row col-100">	
						<label for="base">Ingrese cod. Ubicaci?n</label>
					</div>
					<div class="row col-100">
						<input type="text" class="input" value="${ojoSeleccionado}" name="ojoReservado" id="base" onkeypress="handleEnter(event)"/>
					</div>
									

					<tr>
						<td>
						<div class="row col-100">	
							<label for="base">Liberar reservas</label>
						</div>
							<table id="sel2" name="sellist2" size="4" onchange="setQty(this)">
							<c:if test="${mensaje != null}">
										<label>Este ubicacion no contiene mercaderia reservada</label>
									</c:if>
									
							<c:if test="${mensaje == null}">
							<thead>
							<tr>
								<th> Articulo </th>
								<th> Descripcion</th>
								<th> Cantidad Reservada</th>
							</tr>
							</thead> 
							<tbody>

									
									<c:forEach var="gr" items="${lista}">	
										<tr>						
											<td>${gr.idArticulo}</td>
											<td>${gr.descripcion}</td>
											<td>${gr.cantidad}</td>
											<td><button  class="col-90 button" onclick="eliminar('${gr.idOjo}','${gr.idArticulo}')">Eliminar</button></td>
										</tr>
									</c:forEach>
									</c:if>
							
							</tbody>
							
							</table>
						</td>
					</tr>
					

					<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.A'"  value="Ir a menu"/>
		</c:if>


		
		
		
		<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
		<c:if test="${menError!=null}">
			<script type="text/javascript">
				alert('${menError}');
			</script>	
			
		</c:if>
	
	
<script>
	



</script>
		
		
</c:if>

<c:remove var="menError" scope="session" />
</body>
</html>