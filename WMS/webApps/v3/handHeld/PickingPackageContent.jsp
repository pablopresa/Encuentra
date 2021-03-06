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
		<form  id="contactform" class="rounded" >
			<div class="row">
				<a href="<%=basePath%>/v3/handHeld/MobLineaRepoForm.jsp"> <input class="col-100 button" type="button" name="2" value="VOLVER"/></a>
			</div>
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td style="width: 10%; text-align: center;">Cantidad</td>
							<td style="width: 90%; text-align: center;">Barra</td>							
						</tr>
						<tr>
							<td ><input type="number" name="qty" id="qty" value="1" min="1"/></td>
							<td style="width: 90%; text-align: center;"><input type="text" name="test" id="test" onkeypress="onTestChange(event);" /></td>							
						</tr>
							
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<label for="lblCantidad">Contador:&nbsp;<strong	id="lblCantidad"></strong></label>
					<table style=" border-collapse: collapse; border-spacing: 10px;" id="PickingBulto">
						<tr>
							<td>Articulo</td>
							<td>Cantidad</td>
							<td>Cont</td>
							<td>OK</td>
						</tr>
						 <c:forEach var="e" items="${modeloPickingBulto.listaElementos}">
					 		<tr>
						 		<td>${e.idArticulo} </td>
						 		<td>${e.cantidad} </td>
						 		<td id="tdPick${e.idArticulo}">${e.pick}</td>
						 		<c:if test="${e.cantidad==e.pick}">
						 			<td><img alt="ok" src="<%=basePath%>imagenes/icons/accept.png" border="0" style="width: 20px;"></td>
						 		</c:if>
						 		<c:if test="${e.cantidad!=e.pick}">
						 			<td id="tdOk${e.idArticulo}">&nbsp;</td>
						 		</c:if>
					 		<tr>
						</c:forEach>
					</table>
					
				</div>
			</div>
			<div class="row">
				<input class="col-100 button" type="button" onclick="finalizarPicking()" value="FINALIZAR"/>
			</div>
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
       var nicoContenido = ${modeloPickingBulto.elementos_json};
       console.log(nicoContenido);
       console.log(nicoContenido["0002210NE0000"]);
       var elemento = nicoContenido["0002210NE0000"];
       if (elemento == undefined){
    	   console.log("no existe");
       }
       console.log(elemento.cantidad);
       elemento.cantidad=10;
       console.log(elemento.cantidad);
       console.log(nicoContenido["asaaaaaaaaaaaa"]);
       var elemento2 = nicoContenido["asaaaaaaaaaaaa"];
       if (elemento2 == undefined){
    	   console.log("no existe");
       }
 </script>
 
 <script type="text/javascript">
	var coleccion = ${modeloPickingBulto.elementos_json};
	var barras = ${modeloPickingBulto.barrasJson};
	var count = ${modeloPickingBulto.count};
	var cantidadTotal = ${modeloPickingBulto.cantidadTotal};

	

	function onTestChange(event) {
		var char = event.which || event.keyCode;

		// If the user has pressed enter
		if (char == 13) 
		{
			var cod = document.getElementById("test").value;
			var qty = 1;
			try {
				qty = document.getElementById("qty").value;
				qty = parseInt(qty);
			} catch (e) {
				e.printStackTrace();
			}
			if (cod != "") 
			{
				var articulo = barras[cod];
				
				if(articulo != undefined)
				{
					var elemento = coleccion[articulo];
					
					if(elemento.pick + qty <= elemento.cantidad){
						//elemento.pick++;
						//count++;
						
						var xhttp = new XMLHttpRequest();
						xhttp.onreadystatechange = function() {
						    if (this.readyState == 4 && this.status == 200) 
						    {
						    	var doc = document.getElementById("PickingBulto");
						    	var response = xhttp.response.trim()
						    	//response = response.replaceAll("\"","'");
						    	var json = JSON.parse(response);
								
						    	if(json.menError){
						    		showAlert(json.menError);
						    	}
						    	else{
						    		coleccion = json.elementos_json;
						    		count = json.count;	
						    		doc.innerHTML = json.table;
						    		
						    		if(validate()){
										window.location.assign('<%=basePath%>/v3/handHeld/AlternativePickingPackage.jsp');
									}
						    	}
						    }
						  };
						  xhttp.open("POST", "<%=basePath%>/PickingPackage.do", true);
						  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
						  xhttp.send("idArticulo="+articulo+"&qty="+qty);
						
					 	//if(elemento.pick == elemento.cantidad){
							//document.getElementById("tdOk"+elemento.idArticulo).innerHTML = "<img src='<%=basePath%>imagenes/icons/accept.png' border='0' style='width: 20px;'>";
						//}
						//document.getElementById("tdPick"+elemento.idArticulo).innerHTML = elemento.pick;
						
						document.getElementById("test").value = "";
						document.getElementById("test").focus();
						document.getElementById("lblCantidad").innerHTML = count;
						
						
					}
					else{
						document.getElementById("test").value = "";
						var alarma = document.getElementById('xyz');
						alarma.play().catch(function() {
						});
						alarma.addEventListener('ended', showAlert("Este codigo de barras se reconoce para un articulo de los que debe verificar. Si no lo ha verificado, ingrese una cantidad menor"));
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
				document.getElementById("test").value = "";
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

	function showAlert(msj) {
		alert(msj);
	}
	
	function validate(){
		if(count == cantidadTotal){
			return true;
		}
		else{
			return false;
		}
	}
	
	function finalizarPicking(){
		if(validate()){
			window.location.assign('<%=basePath%>/v3/handHeld/AlternativePickingPackage.jsp');
		}
		else{
			if (confirm('No ha pickeado todos los articulos. ?Desea continuar?')){
				window.location.assign('<%=basePath%>/v3/handHeld/AlternativePickingPackage.jsp');
			}
		}
	}

	
</script>
 
 <c:remove var="menError" scope="session" />
</html>