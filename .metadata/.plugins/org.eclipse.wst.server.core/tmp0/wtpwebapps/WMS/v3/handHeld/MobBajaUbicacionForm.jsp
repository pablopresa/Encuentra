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
	var simbolo = ' + '
	
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
	
	
	function onTestChange(event) 
	{
	    var char = event.which || event.keyCode;
	
	    // If the user has pressed enter
	    if (char == 13) 
		{
			var cod = document.getElementById("test").value;
			var quant =  TryParseInt(document.getElementById("canti").value,1);
			if(cod!="")
			{
				
				document.getElementById("test").value = "";
				if(simbolo==' + ')
				{
					codigos.push(cod+":"+quant);
					count+=quant;
				}
				else
				{
					codigos.push(cod+":"+(quant*-1));
					count-=quant;
				}
				
				document.getElementById("lblCantidad").innerHTML = count;
				document.getElementById("destino").value = codigos.toString();
				event.preventDefault();
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
		if(confirm("Desea confirmar ?"))
		{
			document.forms["contactform"].submit();
		}	
		
		
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
	<form id="contactform" class="rounded" method="post"  action="<%=basePath%>/BajarUbicacion.do" style="float: left;">
		<div class="row">
			<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.A'"  value="Ir a menu"/>
		</div>
		<div class="row">
			<div class="col-100">
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
						<td style="width: 65%">Barra</td>
						<td style="width: 25%">Cant</td>
						<td style="width: 10%">+-</td>
					</tr>
					<tr>
						<td style="width: 65%"><input type="text" name="test" id="test"  onkeypress="onTestChange(event);"/></td>
						<td style="width: 25%"><input type="number" min="1"  name="canti" id="canti"  value=1 /></td>
						<td style="width: 10%"><div style="text-align: center;" id="sim" onclick="ChangeSim(); return false;"> + </div></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-100">
				<label for="lblCantidad">Contador: ${cantRecepSaved}&nbsp;<strong id="lblCantidad"></strong></label>
				<textarea name="articulos" id="destino" readonly="readonly"></textarea>
				
				<input type="button" class="col-100 button" onclick="subm(event,1)" value="Bajar" />												
				<input class="col-100 button" type="button" name="1"  onclick="subm3()"  value="Reiniciar"/>
			</div>
		</div>
	</form>
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
	</c:if>
</body>

</html>	
		
		
</c:if>
</body>
</html>