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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css"	media="handheld" />
<link rel="stylesheet" id="smthemenewprint-css"	href="<%=basePath%>v3/assets/handheld/formsHandHel.css" type="text/css" /> -->
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
<body id="dt_example" style="border-left: 0;" >
	
 	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/confirmarPick.do" style="float: left;">
		<div class="field" align="center">
			<div class="field" align="center">
				<label for="lblArticulo">Barra</label>
				<input type="text" name="test" id="test"  onkeypress="setArticulo(event);"/>
			</div>
			
			<c:if test="${paramDetalle}">
				<div class="field" align="left">
					<h4><strong style="margin-left: 10px;" id="lblDesc"></strong></h4>
					<h4><strong style="margin-left: 10px;" id="lblArtDesc"></strong></h4>
					<img  id="imagenArt" alt='' src='' style='width: 280px;'>
					
					<h4>Destino:<strong style="margin-left: 10px;" id="lblDestino"></strong></h4>
					<h4>Solicitud:<strong style="margin-left: 10px;" id="lblSolicitud"></strong></h4>
					<h4>Pendiente de verificar :<strong style="margin-left: 10px;" id="lblPendiente"></strong></h4>
					<h4 style="color: red">Caja cerrada por :<strong style="margin-left: 10px;" id="lblEmpaque"></strong></h4>
					<h4>Cantidad Pedido :<strong style="margin-left: 10px;" id="lblQtyped"></strong></h4>
					<c:if test="${paramCantidad}">
						<h4 style="float: left; width: auto;">Verifico</h4>
						<input style="float: left; width: auto; margin-left: 10px; margin-top: 10px; text-align: center;" type="number" name="qty" id="qty" id="qty" value="1" min="1" onkeypress="setCantidad(event);"/>
					</c:if>
					<a href="#" onclick="setCantidadButton()">
						<input class="col-100 button" type="button" value="Enviar"/>
					</a>
				</div>
			</c:if>
			
			<div style="clear: both; float: none;"></div>
			<br/>
		</div>

		<div style="height: 5px;"></div>
		<a href="#" onclick="CloseBox()">
			<input class="col-100 button" type="button" value="CERRAR CAJA"/>
		</a>
		<!-- <a href="#" onclick="Finish()">
			<input class="col-100 button" type="button" value="FINALIZAR"/>
		</a> -->
		<a href="<%=basePath%>DarPickings.do?destino=verificar">
			<input class="col-100 button" type="button" value="Ir a men?"/>
		</a>
	<c:set var="cerrarPick" value="${pickings.get(0).idPicking}" scope="session"></c:set>
	<c:set var="actPick" value="verifica" scope="session"></c:set>

	</form> 

	<script type="text/javascript">
		var pick = ${pickings.get(0).idPicking};
		function Finish(){
			if(confirm('Seguro que quiere finalizar esta verificacion?')){
				location.replace("<%=basePath%>FinDescartarPick.do?idPick="+pick+"&pag=verificar");
			}
		}
		function CloseBox(){		
			
			location.replace("<%=basePath%>v3/handHeld/CloseBox.jsp");			
		}
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
       <script type="text/javascript">
             window.onload = function() {
               document.getElementById("test").focus();
             };
       </script>


	<script type="text/javascript">
	  
	var clasificador = ${clasificador_json};
	var thisArticulo;
	var paramVerificacion = ${paramVerificacion}
	
	var articulos = [${arregloArticulos}];
	var articulosPosicion = [${arregloDestinos}];
	var arregloDestinosQty = [${arregloDestinosQty}];
	var destinoArtCant=[];
	var codDestino = -1;
	var barras = new Array();
	
	<c:forEach var="p" items="${pickings}">
		<c:forEach var="bar" items="${p.barras}">
			barras["${bar}"]="${p.articulo}";
		</c:forEach>
	</c:forEach>
	
	function primerDestino(objeto) 
	{
		var primero;
		for (let key in objeto) {
			 primero = objeto[key];
			break;
		}
		return primero[0];
	}
	
	function setArticulo(event) 
	{
	    var char = event.which || event.keyCode;
	    // If the user has pressed enter
	    if (char == 13) 
		{
			var input = document.getElementById("test").value;
			if(input!="")
			{
				var articulo = barras[input.toUpperCase()];
				//document.getElementById("test").value = "";
				
				var objeto_destinos = clasificador[articulo];
				if(objeto_destinos == null)
				{
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
				    document.getElementById('xyz').play();
					});
					alarma.addEventListener('ended', showAlert("El articulo no esta en la lista del picking"));
					
					//alert("El articulo no esta en la lista del picking");
					document.getElementById("test").value="";
					document.getElementById("test").focus()
				}
				else
				{
					try {
						var articulo_destino = primerDestino(objeto_destinos);
						thisArticulo = articulo_destino;						
						
						document.getElementById("imagenArt").src = articulo_destino.imagen;
						document.getElementById("lblArtDesc").innerHTML = articulo_destino.articulo+"<br/>"+articulo_destino.justificacion;
						document.getElementById("lblEmpaque").innerHTML = articulo_destino.empaque;
						document.getElementById("lblDestino").innerHTML = articulo_destino.destino.descripcion;
						document.getElementById("lblSolicitud").innerHTML = articulo_destino.solicitud;
						document.getElementById("lblQtyped").innerHTML = articulo_destino.sol;
						
						if(paramVerificacion){
								document.getElementById("lblPendiente").innerHTML = articulo_destino.pick - articulo_destino.verificada;
							}
							else{
								document.getElementById("lblPendiente").innerHTML = articulo_destino.sol - articulo_destino.verificada;
							}
						//document.getElementById("qty").value = articulo_destino.sol - articulo_destino.verificada;
					} catch (e) {
						
					}						
					var input_qty = document.getElementById("qty");
					if(input_qty == undefined){
						document.getElementById("test").disabled = true;
						location.replace("<%=basePath%>ConfirmarDistPicking.do?art="+thisArticulo.articulo+"&posicion=&qty=1");
					}
					else{
						document.getElementById("qty").focus();
					}
					
				}				
				event.preventDefault();
			}
	        return false;
	    }
	    else 
		{
	        return true;
	    }
	}
	
	function setCantidad(event) 
	{
	    var char = event.which || event.keyCode;
	
	    // If the user has pressed enter
	    if (char == 13 || char == 9) 
		{		
			var qty = 1;
			try {
				qty = document.getElementById("qty").value;
				qty = parseInt(qty);
			} catch (e) {
			}
			
			var pendientes = thisArticulo.sol - thisArticulo.verificada
			if(paramVerificacion){
				pendientes = thisArticulo.pick - thisArticulo.verificada
			}
			
			if(qty <= pendientes){
				document.getElementById("test").disabled = true;
				location.replace("<%=basePath%>ConfirmarDistPicking.do?art="+thisArticulo.articulo+"&posicion=&qty="+qty);
			}
			else{
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
			    document.getElementById('xyz').play();
				});
				alarma.addEventListener('ended', showAlert("Debe ingresar una cantidad menor"));
				document.getElementById("qty").focus();
				
				event.preventDefault();
			}		
	    }
	    else 
		{
	        return true;
	    }
	}
	
	function setCantidadButton() 
	{		
			var qty = 1;
			try {
				qty = document.getElementById("qty").value;
				qty = parseInt(qty);
			} catch (e) {
			}
			
			if(qty <= thisArticulo.sol - thisArticulo.verificada){
				document.getElementById("test").disabled = true;
				location.replace("<%=basePath%>ConfirmarDistPicking.do?art="+thisArticulo.articulo+"&posicion=&qty="+qty);
			}
			else{
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
			    document.getElementById('xyz').play();
				});
				alarma.addEventListener('ended', showAlert("Debe ingresar una cantidad menor"));
				document.getElementById("qty").focus();
				
				event.preventDefault();
			}		
	}
	
	function darPosicionArticulo(str) 
	{
		fLen = articulos.length;
		for (i = 0; i < fLen; i++) 
		{
		    var ar = articulos[i];
		    if(ar==str)
		    {
		 		return i;   	
		    }
		}
		
		return -1;
		 
	}
	
	
	function onTestChange(event) 
	{
	    var char = event.which || event.keyCode;
	    // If the user has pressed enter
	    if (char == 13) 
		{
			var cod = document.getElementById("test").value;
			if(cod!="")
			{
				var articuloScn = barras[cod.toUpperCase()];
				var pos = darPosicionArticulo(articuloScn);
				if(pos==-1)
				{
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
				    document.getElementById('xyz').play();
					});
					alarma.addEventListener('ended', showAlert("El articulo no esta en la lista del picking"));
					
					//alert("El articulo no esta en la lista del picking");
					document.getElementById("test").value="";
					document.getElementById("test").focus()
				}
				else
				{
					document.getElementById("test").disabled = true;
					location.replace("<%=basePath%>ConfirmarDistPicking.do?art="+articuloScn+"&posicion="+"");
				}				
				event.preventDefault();
			}
	        return false;
	    }
	    else 
		{
	        return true;
	    }
	}
	
	function subm(event,type) 
	{
		document.getElementById("typeSave").value = type
		
		if(type==0)
		{
			if(confirm("Desea confirmar las cantidades ("+count+")?"))
			{
				document.forms["contactform"].submit();
			}	
		}
		else
		{
			document.forms["contactform"].submit();
		}
		
	}
	
	function showAlert(msj) {
		alert(msj);
	}
	
</script>
<c:remove var="menError" scope="session" />
</body>
</html>

