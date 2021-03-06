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
<body>

<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


</head>
<body id="dt_example" style="border-left: 0;"  onload="document.getElementById('base').focus();noBack();" onpageshow="if (event.persisted) noBack();" onUnload="">


<c:if test="${uLogeado!=null}">
	<c:forEach var="d" items="${uLogeado.seguridadUI}">
		<c:if test="${d=='box_cant_picking'}">
			<c:set var = "puede" scope = "page" value = "1"/>
		</c:if>
		<c:if test="${d=='label_cantidad_caja'}">
			<c:set var = "label_cantidad_caja" scope = "page" value = "1"/>
		</c:if>
	</c:forEach>
			<div class="products-main" align="center" style=" float: left;">
				<div class="container" style="float: left;">
				<form id="contactform" class="rounded"  action="<%=basePath%>/pasaPaginaRepo.do" style="float: left;">
				<div align="center">
					<table>
						<div class="field" align=center style="float: center;">
							<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.B'"  value="Volver al menu"/>					
						</div>
					</table>	
					<p>
					<strong style="font-size: 17pt; color: orange; font-weight: bold;"><c:out value="${voy.descEstanteria}"></c:out></strong> 
					</p>
					<p>
					<strong style="font-size: 13pt; color: navy; font-weight: bold;">e<c:out value="${voy.estnte}"></c:out></strong>
					<strong style="font-size: 13pt; color: navy; font-weight: bold;">M<c:out value="${voy.modulo}"></c:out></strong> 
					<strong style="font-size: 13pt; color: green; font-weight: bold;"><c:out value="${voy.cubi}"></c:out></strong>
					</p>
				</div>
				
			
				<input type="text" class="input" name="confirmarOjo" id="confirmarOjo2" value="${ojoConfirmado}" style="display: none;"/>	
				<table>
					<tr>
						<td style="text-align: center;">
						<!--	<c:if test="${uLogeado.idEmpresa==5}"> -->
								<img alt="" src="${voy.imagen}" style="width: 110px;">
						<!--	</c:if> -->
						<!--	<c:if test="${uLogeado.idEmpresa!=5}">
								<img alt="" src="${voy.imagen}" style="width: 250px;">
							</c:if> -->
						</td>
					</tr>
					<tr>
						<td>
							<p><strong style="font-size: 10pt;"> <c:out value="${voy.idArticulo}"></c:out> &nbsp;X&nbsp;<c:out value="${voy.solicitada}"></c:out> </strong> 
							<c:if test="${uLogeado.idEmpresa==5}">
									<a href="<%=basePath%>DarOjosArti.do?art=${voy.idArticulo}&mob=1&picking=1" >ver ubicaciones</a>
							</c:if>
							<c:if test="${uLogeado.idEmpresa==10}">
									<a href="<%=basePath%>DarOjosArti.do?art=${voy.idArticulo}&mob=1&picking=1" >ver ubicaciones</a>
							</c:if>
							</p>
							<c:if test="${voy.descripcion != 'Bulto'}">
								<c:if test="${voy.cantDeVenta > 1}">
									<p style="font-size: 8pt; color: #54A800"><strong><c:out value="${voy.descripcion}"></c:out></strong></p>
								</c:if>
								<c:if test="${voy.cantDeVenta <= 1}">
									<p style="font-size: 8pt;"><c:out value="${voy.descripcion}"></c:out></p>
								</c:if>
							</c:if>
							<c:if test="${voy.descripcion == 'Bulto'}">
								<p style="font-size: 8pt;"> Bultos similares disponibles: <br><c:forEach items="${codigosRecomendados}" var="codigo">${codigo}&nbsp;<br></c:forEach></p>
								<a style="font-size: 8pt;" onclick = "showContent()" href="#" id="btnContent">Contenido...</a>
								<div id="boxContent" style="display: none;" >								
									<c:forEach items="${voy.contenido}" var="codigo"><p style="font-size: 8pt;">${codigo.idArticulo} - ${codigo.solicitada}&nbsp; </p></c:forEach>
								</div>
							</c:if>
							<c:if test="${voy.descripcion != 'Bulto'}">
								<c:if test="${voy.cantDeVenta > 1}">
									<p style="font-size: 8pt; color: #54A800"><strong><c:out value="${voy.justificacion}"></c:out></strong></p>
								</c:if>
							<c:if test="${voy.cantDeVenta <= 1}">
									<p style="font-size: 8pt;"><c:out value="${voy.justificacion}"></c:out></p>
								</c:if>  
							</c:if>
						</td>
						
					</tr>
				</table>
				
					<div class="field">
						<table>
							<tr>
								<td><p><strong style="font-size: 10pt;"> Confirmar Ojo	 </strong></p></td>
								<td><input type="text" class="input" name="confirmarOjo" id="confirmarOjo" value="${ojoConfirmado}" onchange="verificarOjo()"/></td>
							</tr>
						</table>
						
					</div>
					
									
					<div class="field">
						<table>
							<tr>	
								<td>
								<c:if test="${puede==1}">
									<input type="number" class="input" name="qty" id="box_cant_picking" value="1" min="1" max="${voy.solicitada}" onchange="validarCantidad(${voy.solicitada},${unidad})" style="width: 60px;"/>
								</c:if>
								</td>
								<td><input type="text" class="input" name="base" id="base" value="${articulo.codBase}" onchange="ExecForm()" /></td>
							</tr>
						</table>
					</div>
					
					<div class="field" align="center">
						<p><font style="color: red"> Cantidad <c:out value="${unidad}"></c:out></font> de <font style="color: green"><c:out value="${voy.solicitada}"></c:out></font></p>
						<c:if test="${label_cantidad_caja == 1}">
							<p>
								<font style="color: red"> Cantidad Pedido <c:out value="${voy.qPedido}"></c:out></font><br/>
								<font style="color: red; font-size: 15pt"> Cantidad Por caja <c:out value="${voy.packing}"></c:out></font>
							</p>
						</c:if>
						
						<c:if test="${uLogeado.idEmpresa==5}">
									<font style="color: red; font-size: 15pt"> Unidad de Venta <c:out value="${voy.cantDeVenta}"></c:out></font>
								</c:if>
						
						<p><font style="color: red"> Linea <c:out value="${cuantasVoy+1}"></c:out></font> de <font style="color: green"><c:out value="${total}"></c:out></font></p>
						<table border="1">
							<tr>
								<td style="padding: 5px;">Orig.</td>
								<td style="padding: 5px;">Dest.</td>
								<td style="padding: 5px;">Stock</td>
							</tr>
							<tr style="font-weight: bold;">
								<td style="padding: 5px;"><c:out value="${voy.idDepOrigen}"></c:out></td>
								<td style="color: red; padding: 5px;"><c:out value="${voy.idDepDestino}"></c:out></td>
								<td style="padding: 5px;"><c:out value="${voy.sotck}"></c:out></td>
							</tr>
						</table>
						<div style="height: 5px;"></div>
					<!--<a href="<%=basePath%>ReOrdenarRecorrido.do"> <div class="button">Buscar otra Ubicacion</div></a>-->
					<input class="col-100 button" type="button" name="1"  onclick="VolverMenu(${idTarea});"   value="Pausar Tarea"/>
					<!--  <input class="col-100 button" type="button" name="2"  onclick="ReOrdenar();"   value="Buscar otra Ubicacion"/>-->
					<input class="col-100 button" type="button" name="3"  onclick="noEncontrado('${voy.idArticulo}',${cuantasVoy},${unidad});"   value="No Encontrado"/>
				</form>
				</div>
			</div>
				
		</c:if>
		<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
	<c:if test="${menError!=null}">
		<script type="text/javascript">
			alert('${menError}');
		</script>
		<!-- <script type="text/javascript"> 
			var alarma = document.getElementById('xyz');
			alarma.play().catch(function() {
		    document.getElementById('xyz').play();
			});
			alarma.addEventListener('ended', showAlert);

			function showAlert() {
			 alert('${menError}');
			}			
			
		</script> -->	
		
	</c:if>
	</body>
	<script type="text/javascript">
			function ExecForm(){
				document.getElementById('contactform').submit();
				
			}
			function VolverMenu(id){
				window.location='<%=basePath%>pausarTarea.do?idTarea='+id;
			}
			function ReOrdenar(){
				window.location='<%=basePath%>ReOrdenarRecorrido.do'
			}
			function noEncontrado(idArt,cuantas,unidad){
				window.location='<%=basePath%>pasaPaginaRepo.do?noEncontrado='+idArt+'&cuantasVoy='+cuantas+'&unidad='+unidad;
			}
			function showContent(){
				var element = document.getElementById("boxContent");
				if(element.style.display=="block"){
					element.style.display="none"
						document.getElementById("btnContent").innerHTML = 'Contenido...';
				}
				else{
					element.style.display="block"
					document.getElementById("btnContent").innerHTML = 'Ocultar contenido';
				}
			}
		</script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		var ojo="${voy.cubi}";
		$(document).ready(function (){
			if(ojo == 'SIN ASIGNAR'){
				document.getElementById('confirmarOjo2').value=ojo;
				document.getElementById('confirmarOjo').value=ojo;
				document.getElementById('confirmarOjo').readOnly = true;
				document.getElementById('base').focus();				
			}
			else{
				document.getElementById('confirmarOjo').focus();
			}
			
			})
		function verificarOjo (){
			var o = document.getElementById('confirmarOjo').value;
			if (ojo == o){
				document.getElementById('confirmarOjo2').value=o;
				document.getElementById('base').focus();
			
			}
			else
			{
				document.getElementById('confirmarOjo').focus();
				document.getElementById('confirmarOjo').value='';
				alert('Tome la mercaderia de donde se indica');
			}
		}
		
		function sub(){
			var o = document.getElementById('confirmarOjo').value;
			if (o==ojo){
				document.getElementById('contactform').submit;
			}
			else{
				document.getElementById('confirmarOjo').focus();
				document.getElementById('confirmarOjo').value='';
				alert('Tome la mercaderia de donde se indica');
			}
		}
		
		function validarCantidad(max,unidad){
			cantidad = document.getElementById('box_cant_picking').value;
			if(cantidad > max-unidad){
				document.getElementById('box_cant_picking').focus();
				document.getElementById('box_cant_picking').value = 1;
				alert('Debe ingresar una cantidad menor o igual a la que se solicita');
			}
		}
		
		
		window.history.forward();
        function noBack()
        {
            window.history.forward();
        }
	</script>

</html>	
		
		
</c:if>
<c:remove var="menError" scope="session" />
</body>
</html>