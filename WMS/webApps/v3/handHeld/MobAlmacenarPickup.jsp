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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
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



<c:if test="${menError!=null}">
<script type="text/javascript">

	alert("${menError}");
</script>
	
</c:if>

<script type="text/javascript"> 
	
	function nextel(event) 
	{ 
		if(event==13)
		{  
			document.getElementById("test").focus(); 
			
		} 
	}
	
	
	 
</script>
<script type="text/javascript">
	var codigos = [];
	var count = 0;
	var pedidosALL = [${arrayPedidosDepo}];
	
	function TryParseInt(str,defaultValue) 
	{
		 var retValue = defaultValue;
		 if(str !== null) 
		 {
		 	str = str.replace(" ", "")
			 if(str.length > 0) {
				 if (!isNaN(str)) {
					 retValue = parseInt(str);
				 }
			 }
		 }
		 return retValue;
	}
	
	function existe(pedido)
	{
		fLen = pedidosALL.length;
		
		
		for (i = 0; i < fLen; i++) 
		{
		    var current = pedidosALL[i];
		    if(current==pedido)
		    {
		    	
		    	pedidosALL.splice(i, 1);
		    	
		    	return true;
		    }
		}
		return false;
	
	
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
				if(existe(cod)){
					document.getElementById("test").value = "";
					
					codigos.push(cod+",");
					count+=1;									
					
					document.getElementById("lblCantidad").innerHTML = count;
					document.getElementById("destino").value = codigos.toString();
					event.preventDefault();
				}
				else{
					alert("Este pedido no esta en la lista para esta Tienda");
					//var alarma = document.getElementById('xyz');
					//alarma.play().catch(function() {
				    <!--document.getElementById('xyz').play();-->
					//});
					//alarma.addEventListener('ended', showAlert("Este pedido no esta en la lista para esta Tienda"));
					//alert("Este pedido no está en la lista para enviar al deposito seleccionado")
				}
			}
	        return false;
	    }
	    else 
		{
	        return true;
	    }
	}
	

	
	function subm(event) 
	{
		document.forms["contactform"].submit();		
	}
	
	function subm3() 
	{
		if(confirm("Desea Reiniciar  ("+count+")?"))
		{
			codigos = [];
			count = 0;
			simbolo = ' + '
			document.getElementById("lblCantidad").innerHTML = count;
			document.getElementById("destino").value = codigos.toString();
			var color = '#d3d3ab';
			document.getElementById("sim").innerHTML = simbolo;
			document.getElementById("sim").style.backgroundColor = color;
		}
	}
	
	function ChangeSim() 
	{
		var color;
	    if(simbolo==' + ')
		{
			simbolo = ' - ';
			 color = '#ff6666';
		}
		else
		{
			simbolo = ' + ';
			color ='#d3d3ab';
		}
		
		document.getElementById("sim").innerHTML = simbolo;
		document.getElementById("sim").style.backgroundColor = color;
		
	}
	
	
</script>




</head>
<body onload=" document.getElementById('base').focus();">
	<c:if test="${uLogeado!=null}">
	<form id="contactform" class="rounded" method="post"  action="<%=basePath%>/AlmacenarPickup.do" style="float: left;">
		
		<div class="row">
			<div class="col-100">
			<input type="text" name="artValido" id="artValido" style="display: none;">
				<table>
					<tr>
						<td>Codigo de Ubicacion</td>
					</tr>
					<tr>
						<td><input type="text" class="inputCH" name="idOjo" id="base" onkeypress="nextel(event.keyCode)" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>Barra</td>
					</tr>
					<tr>
						<td><input type="text" name="test" id="test"  onkeypress="onTestChange(event);"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-100">
				<label for="lblCantidad">Contador: ${cantRecepSaved}&nbsp;<strong id="lblCantidad"></strong></label>
				<textarea name="articulos" id="destino" readonly="readonly"></textarea>
				
				<input type="button" class="col-100 button" onclick="subm(event,1)" value="Ingresar" />
			
				<input type="button" class="col-100 button" onclick="subm3()" value="Reiniciar"/>
				<input class="col-100 button" type="button" name="1"  onclick="VolverMenu();"   value="Ir a menu"/>
						
				
			</div>
		</div>
		<input type="text" id="sessionViva" style="display: none;">
		<input type="text" name="paraquien" value="${para}" style="display: none;">
	</form> 
	</c:if>
</body>

</html>	

<script type="text/javascript">
	
           
        
</script>

<script type="text/javascript">
	function VolverMenu(){
		window.location='<%=basePath%>/MenuMob.do?sec=M.G'
	}
</script>
		
</c:if>
</body>
</html>