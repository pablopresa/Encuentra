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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<c:choose>
  <c:when test="${css=='IE'}">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css" />
  </c:otherwise>
</c:choose>



<script type="text/javascript">

var listaOculta = [];

// falta hacer que cuando entre ya esten todos cargados en la lista
function color(d) {
	
	
	var element = document.getElementById(d);
	var getColor = obtenerColor(element); //retorna true si es gris

	if (!getColor) {
		element.style.filter = "grayscale(100%)";
		for ( var c = 0 in listaOculta) {
			if (listaOculta[c] == d) {
				listaOculta.splice(c, 1);
				break;
			}
		}
	} else {
		element.style.filter = null;
		listaOculta.push(d);
	}  
	
	console.log(listaOculta);
}

function obtenerColor(d) {
	return (d.style.filter == "grayscale(100%)");
}
	
	
	function confirmar(){
		var texto = document.getElementById("nuevoOjo").value;
		if (texto.length > 0 ){
			if(confirm("Estas seguro??")){
				alert("hacer logica");
			}
		} else {
			var valores = document.getElementsByName("nombre");
			var checkeado = false;
			for(var i=0 ; i<valores.length; i++){
				if(valores[i].checked){
					checkeado = true;
					break;
				}
			}
			if(checkeado){
				if(confirm("Estas seguro??")){
					document.getElementById("formulario").submit();
				}
			}else{
				alert("Seleccione alguna posicion posible");
			} 
			
			}		
		}

</script>



<c:if test="${uLogeado!=null}">



</head>
 
<body>
    <form id="formulario" action="<%=basePath%>/gonzaa.do" method="post">
		<c:if test="${uLogeado!=null}">
			<c:forEach var="d" items="${uLogeado.seguridadUI}">
				<c:if test="${d=='box_cant_picking'}">
					<c:set var="puede" scope="page" value="1" />
				</c:if>
			</c:forEach>
			
			<div class="products-main" align="center" style="float: left;">
				<div class="container" style="float: left;">
					<div align="center">
					<p>
					<strong style="font-size: 15pt; color: green; font-weight: bold;"><c:out value="Bultos a Guardar"></c:out></strong>
					</p>
					<p>
					<strong style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="Seleccione los bultos a guardar en el ojo indicado"></c:out></strong>
					</p>
					</div>
				</div>



					<table id="PedidoDesc">
						<tr>
							<th style="width: 70%; text-align: center;">Bultos</th>
	
							<th style="width: 30px;">Guardar</th>
						</tr>

						<div class="overflow-auto">
							<c:forEach var="lista" items="${asd}">
								<tr class="gradeD">
									<td style="text-align: center;">${lista}</td>

									<td style="text-align: center;">
										 <a  onclick="color('${lista}')"><img id="${lista}" alt="Iniciar" src="<%=basePath%>imagenes/icons/accept.png" border="0" style="width: 20px;"></a>
									</td>
								</tr>

							</c:forEach>

						</div>
					</table>
					
					

					<div class="field" align="center">
						<div style="height: 5px;"></div>
						
						<input class="col-100 button" type="button" name="1"
							onclick="confirmar()" value="Confirmar" />
					</div>
				</div>
		</c:if>
	</form>




	<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
	<c:if test="${menError!=null}">
		<script type="text/javascript">
				alert('${menError}');
			</script>

	</c:if></c:if>

<c:remove var="menError" scope="session" />
    
</body>
</html>