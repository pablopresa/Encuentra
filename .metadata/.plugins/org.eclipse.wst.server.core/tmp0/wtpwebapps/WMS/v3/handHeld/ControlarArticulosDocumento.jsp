<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<!-- Js Jquery se debe importar antes -->
	<script src="<%=basePath%>v3/assets/plugins/jquery/js/jquery.min.js"></script>
</head>
<body>

	<c:if test="${uLogeado!=null}">

		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

<body >
	<c:if test="${uLogeado!=null}">
		<form onsubmit="return validate(this);" id="contactform" class="rounded" method="post" action="<%=basePath%>/ControlarRemito.do" style="float: left;">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>pausarTarea.do?sale=si"><div class="button">Ir a menú</div></a>

				</div>
			</div>
			<c:if test="${recontrola!=null}">
				<input type="text" name="Recontrola" value="${recontrola}">
			</c:if>
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td style="width: 65%">Barra</td>
						</tr>
						<tr>
							<td style="width: 65%"><input type="text" name="test" id="test" onkeypress="onTestChange(event);" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<label for="lblCantidad">Contador:&nbsp;<strong	id="lblCantidad"></strong></label>
					<table style=" border-collapse: collapse; border-spacing: 10px; ">
						<tr>
							<td>Doc.</td>
							<td>Articulo</td>
							<td>Cant</td>
							<td>Cont</td>
							<td>OK</td>
						</tr>
						 <c:forEach var="d" items="${remitosIn}">
						 	<c:forEach var="a" items="${d.lineas}">
						 		<tr>
						 		<td>${d.numeroDoc} </td>
						 		<td>${a.idArticulo} </td>
						 		<td>${a.cantidad} </td>
						 		<td id="tdVerificada${d.numeroDoc}_${a.entrega}_${a.idArticulo}">${a.cantidadVerificada}</td>
						 		<c:if test="${a.cantidad==a.cantidadVerificada}">
						 			<td><img alt="ok" src="<%=basePath%>imagenes/icons/accept.png" border="0" style="width: 20px;"></td>
						 		</c:if>
						 		<c:if test="${a.cantidad!=a.cantidadVerificada}">
						 			<td id="tdOk${d.numeroDoc}_${a.entrega}_${a.idArticulo}">&nbsp;</td>
						 		</c:if>
						 		<tr>
						 	</c:forEach>
						</c:forEach>
					</table>
					
					
					<input class="col-100 button" id="ingbtn" type="submit" value="INGRESAR"/>
					<a href="<%=basePath%>reiniciarCantidadesR.do"> <input class="col-100 button button-danger" type="button" name="2" value="REINICIAR"/></a>
				</div>
			</div>
			<input type="text" name="depo" value="${depo}" style="display: none;">
		</form>
		<audio id="xyz" src="<%=basePath%>audio/errors.mp3" preload="auto"></audio>
	</c:if>
</body>

		</html>


	</c:if>


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
	var remitos = [];
	var cantidades = [];
	var count = 0;
	var countOriginal = 0;
	var simbolo = ' + '
	var articulos = [];
	var barrasBarras = [];
	var barrasArticulos = [];
	var entregas = [];
	
	
	
	
	
	<c:forEach var="d" items="${remitosIn}">
		<c:forEach var="a" items="${d.lineas}">
			<c:if test="${a.cantidad>a.cantidadVerificada}">
				remitos.push('${d.numeroDoc}');
				articulos.push('${a.idArticulo}');
				cantidades.push('${a.cantidad}');
				countOriginal+=${a.cantidad};
				entregas.push('${a.entrega}');
			</c:if>
		</c:forEach>
	</c:forEach>
	
	<c:forEach var="ab" items="${barrasR}">
		barrasArticulos.push('${ab.id}');
		barrasBarras.push('${ab.descripcion}');
	</c:forEach>

	

	function onTestChange(event) {
		var chara = event.which || event.keyCode;

		// If the user has pressed enter
		if (chara == 13) 
		{
			var cod = document.getElementById("test").value;
			if (cod != "") 
			{
				var indice = barrasBarras.indexOf(cod);
				
				if(indice!=-1)
				{
					var articuloEnBarra = barrasArticulos[indice];//busco el cod de barra
					
					
					var indice2 = articulos.indexOf(articuloEnBarra);//busco el indice del articulo
					if(indice2!=-1)
					{
						
						document.getElementById("ingbtn").disabled = true;
						
						var movimientoStock = remitos[indice2];
						var entregaAfceta = entregas[indice2];
						var cantidadMovimiento = cantidades[indice2];
						
						var cantVer = document.getElementById("tdVerificada"+movimientoStock+"_"+entregaAfceta+"_"+articuloEnBarra).innerHTML;
						
						cantidadMovimiento = cantidadMovimiento-1;
						cantidades[indice2] =cantidadMovimiento;
						var xhttp = new XMLHttpRequest();
						xhttp.onreadystatechange = function() {
						    if (this.readyState == 4 && this.status == 200) 
						    {
						      
						    }
						  };
						  xhttp.open("GET", "<%=basePath%>setCantArtMovStockRecep.do?doc="+movimientoStock+"&articulo="+articuloEnBarra+"&entregaAfceta="+entregaAfceta, true);
						  xhttp.send();
						
						document.getElementById("tdVerificada"+movimientoStock+"_"+entregaAfceta+"_"+articuloEnBarra).innerHTML = parseInt(cantVer)+1;
						
						count++;
						
						if(cantidadMovimiento==0)
						{
							 document.getElementById("tdOk"+movimientoStock+"_"+entregaAfceta+"_"+articuloEnBarra).innerHTML = "<img src='<%=basePath%>imagenes/icons/accept.png' border='0' style='width: 20px;'>"
							 delete remitos[indice2];
							 delete cantidades[indice2];
							 delete articulos[indice2];
						}
						document.getElementById("test").value = "";
						document.getElementById("test").focus();
						document.getElementById("lblCantidad").innerHTML = count;
						document.getElementById("ingbtn").disabled = false;
						
					}
					else
					{
						document.getElementById("test").value = "";
						var alarma = document.getElementById('xyz');
						alarma.play().catch(function() {
						});
						alarma.addEventListener('ended', showAlert("Este codigo de barras se reconoce para un articulo de los que debe verificar. Pero ya se verificó"));
					}	
								
					
					
					
					
				}
				else
				{				
					document.getElementById("test").value = "";
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
					});
					alarma.addEventListener('ended', showAlert("Este codigo de barras no se reconoce para ningun articulo de los que debe verificar"));				
				}
			}				
			else
			{				
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
				});
				alarma.addEventListener('ended', showAlert("Ingrese un codigo de barras"));				
			}			
			event.preventDefault();
		  	return false;
	    }
	    else 
		{
	        return true;
	    }
		
		event.preventDefault();
		return false;
	}

	function subm3() {
		if (confirm("Desea Reiniciar  (" + count + ")?")) {
			codigos = [];
			count = 0;
			document.getElementById("lblCantidad").innerHTML = count;
			document.getElementById("destino").value = codigos.toString();
		}
	}
	
	function showAlert(msj) {
		alert(msj);
	}
	
	
	function validate(form) 
	{

    	var valid = true;	
    	if(count < countOriginal)
    	{
    		valid = false;
    	}
    	
		

    if(!valid) 
    {
    	<c:if test="${recontrola!=null}">
    		return confirm('Aviso, ******los movimientos en Visual se confirmaran por todas las unidades***** se enviara un mail con la diferencia para que devuelva los articulos que no tiene');
    	</c:if>
    	<c:if test="${recontrola==null}">
    		
    		return confirm('Parece que no ha leido todos los articulos, confirma?');	
    	</c:if>
       
    }
    else 
    {
     	return true;   
    }
}
	
</script>
 
 
</html>