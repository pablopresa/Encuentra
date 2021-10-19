<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<link rel="stylesheet"
			href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
	</c:when>
	<c:otherwise>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
	</c:otherwise>
</c:choose>

<!--   <link rel="stylesheet" href="../assets/css/EstiloScroll.css" />   -->

<script type="text/javascript">
	function confirmar(){
		var cantidadAMover = document.getElementById("cantidad").value

		var cantBajada = document.getElementById("cantidadBajada").innerHTML;
		var cantidadBajada = Number(cantBajada);
		
		if(cantidadAMover > 0 && cantidadAMover <= cantidadBajada){
		var texto = document.getElementById("nuevoOjo").value;
		if (texto.length > 0 ){
			if(confirm("¿Desea continuar?")){
				document.getElementById("formulario").submit();
			}
		} else {
			var valores = document.getElementsByName("posicionSeleccionada");
			var checkeado = false;
			for(var i=0 ; i<valores.length; i++){
				if(valores[i].checked){
					checkeado = true;
					break;
				}
			}
			if(checkeado){
				if(confirm("¿Desea continuar?")){
					document.getElementById("formulario").submit();
				}
			}else{
				alert("Seleccione alguna posicion posible");
			} 
			
			}		
		} else {
			if (cantidadAMover <= 0){
				alert("La cantidad debe ser positiva");
			} else {
				alert("La cantidad a mover debe ser menor a la cantidad total");
			}
		}
		}
	
		function preventBack() {
        	window.history.forward();
    	}

    	setTimeout("preventBack()", 0);
    	window.onunload = function() {
        	null
    	};
	
</script>


<c:if test="${uLogeado!=null}">

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
</head>
<body id="dt_example" style="border-left: 0;">


	<form id="formulario" action="<%=basePath%>/bultosAGuardar.do"
		method="post">
		<c:if test="${uLogeado!=null}">
			<c:forEach var="d" items="${uLogeado.seguridadUI}">
				<c:if test="${d=='box_cant_picking'}">
					<c:set var="puede" scope="page" value="1" />
				</c:if>
			</c:forEach>
			<div class="products-main" align="center" style="float: left;">
				<div class="container" style="float: left;">
					<div align="center">
						<table>
						<div class="field" align=center style="float: center;">
							<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.B'"  value="Volver al menu"/>					
						</div>
					</table>
						<p>
							<strong style="font-size: 20pt; color: green; font-weight: bold;"><c:out
									value="Posiciones Posibles"></c:out></strong>
						</p>
						<p>
							<strong style="font-size: 15pt; color: green; font-weight: bold;"><c:out
									value="${reposicionPicking.idArticulo}"></c:out></strong>
						</p>
						<p>
							<strong style="font-size: 15pt; color: green; font-weight: bold;"><c:out value="${reposicionPicking.getDescripcionArticulo()}"></c:out></strong>
							<br/>
							<img alt="" src="${reposicionPicking.imagen}" style="width: 290px;">
						</p>
						<table align="center">
							<tr>
								
								
								<p>
									<strong style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="Unidades packing: "></c:out></strong>
									<strong
								style="font-size: 10pt; color: green; font-weight: bold;"><c:out	
									value="${reposicionPicking.packing}"></c:out></strong>
								</p>
								
						 		<p>
									<strong style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="Cantidad restante"></c:out></strong>
									<strong id="cantidadBajada"
								style="font-size: 10pt; color: green; font-weight: bold;"><c:out	
									value="${reposicionPicking.cantidadBajada-reposicionPicking.cantidadMovida}"></c:out></strong>
								</p>
							</tr>
						</table>	
					</div>



					<c:if test="${posicionesPosibles.size() == 0}">
							<strong style="font-size: 10pt;"> No se encontraron posiciones posibles, cree una nueva.
										</strong>
					</c:if>
					
					<c:if test="${posicionesPosibles.size() >= 1}">
					<table id="PedidoDesc">
						<tr>
							<th style="width: 70%; text-align: center;" colspan="4">Ubicacion</th>
							<th style="width: 10px;">&nbsp</th>
							<th style="width: 30px;">Usar</th>
						</tr>
						<tr>
							<td>Estanteria</td>
							<td>Modulo</td>
							<td>Estante</td>
							<td>idOjo</td>
						</tr>
						
						
							<div class="overflow-auto">
								<c:forEach var="d" items="${posicionesPosibles}">
									<tr class="gradeD">
										<td style="text-align: center;" colspan="6">${d.nombreEstanteria}</td>
									</tr>
									<tr class="gradeD">
										<td style="text-align: center;">${d.idEstanteria}</td>
										<td style="text-align: center;">${d.idEstante}</td>
										<td style="text-align: center;">${d.modulo}</td>
										<td style="text-align: center;">${d.idOjo}</td>
										<td>&nbsp</td>
										<td><input name="posicionSeleccionada" type="radio" id="${d.idOjo}" value="${d.idOjo}"  /></td>
									</tr>
	
								</c:forEach>
							</div>

					</table>
					</c:if>

					<div class="field" align="center">
						<div style="height: 5px;"></div>
						<table>
							<tr>
								<td>
									<p align="center">
										<strong style="font-size: 10pt;"> Nuevo Ojo </strong>
									</p>

								</td>
								<td><input type="text" class="input" name="confirmarOjo"
									id="nuevoOjo" value="" onchange="" /></td>

							</tr>
						</table>

						<table>
							<tr>
								<td>
									<p align="center">
										<strong style="font-size: 10pt;"> Cantidad a Guardar
										</strong>
									</p>
								</td>
								<td><input style="width: 100px;" type="number"
									class="input" name="confirmarCantidad" id="cantidad" value="${reposicionPicking.cantidadBajada-reposicionPicking.cantidadMovida}" />
								</td>

							</tr>
						</table>

						<input class="col-100 button" type="button" name="1"
							onclick="confirmar()" value="Confirmar" />
					</div>


					<!-- 	<input name="objOculto" id="objOculto" type="text" style="display:none" value="${idOculto}" />
					<input name="bultosOcultos" id="bultosOcultos" type="text" style="display:none" value="${json}" /> -->

				</div>
		</c:if>
	</form>




	<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
	<c:if test="${menError!=null}">
		<script type="text/javascript">
				alert('${menError}');
			</script>

	</c:if>
	</c:if>

	<c:remove var="menError" scope="session" />
</body>
</html>