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
<link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css"	media="handheld" />
<link rel="stylesheet" id="smthemenewprint-css"	href="<%=basePath%>v3/assets/handheld/formsHandHel.css" type="text/css" /><c:choose>
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
	<c:if test="${menError!=null}">
	<script type="text/javascript">
		alert("${menError}");
	</script>			
	</c:if>
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/RecepcionDestruccion.do" style="float: left;">
		<div class="field" align="center">
			<div class="field" align="center">
				<label for="lblArticulo">Cantidad</label>
				<input type="number" name="canti" id="canti"  value="1"/>
				<label for="lblArticulo">Barra</label>
				<input type="text" name="test" id="test" autofocus onkeypress="onTestChange(event);"/>
				
			</div>
			<div class="field" align="center">
				<label for="lblCantidad">Ubicacion:<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong id="lblDest" style=" color: red;">${destinoSort}</strong></label>
			</div>
			<br /><br />
			<!-- <div class="field" align="center">
				<label for="lblArticulo">Cod. Destino</label>
				<input type="text" name="codDestino" id="codDestino"  onkeypress="onTestChangeII(event);"/>
			</div> -->
			<br/>
		</div>

		<div style="height: 5px;"></div>
		<a href="<%=basePath%>/v3/handHeld/MobListaMisTareas.jsp">
			<div class="button">Ir a menú</div>
		</a>


	</form>

	<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
	<script type="text/javascript">
	
	var articulos = [${arregloArticulos}];
	var articulosPosicion = [${arregloDestinos}];
	var destinoArtCant=[];
	var codDestino = -1;
	var barras = new Array();
	
	<c:forEach var="p" items="${pickings}">
		<c:forEach var="bar" items="${p.barras}">
			barras["${bar}"]="${p.articulo}";
		</c:forEach>
	</c:forEach>
	
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
				var articuloScn = barras[cod];
				//document.getElementById("test").value = "";
				
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
					codDestino = articulosPosicion[pos];
					
					document.getElementById("lblDest").innerHTML = codDestino;
					//document.getElementById("codDestino").focus()
					//document.getElementById("destino").value = codigos.toString()
					
			
					var cod = document.getElementById("test").value;
					var articuloScn = barras[cod];
					var canti = document.getElementById("canti").value;
					location.replace("<%=basePath%>ClasificarTareaII.do?art="+articuloScn+"&canti="+canti+"&destinoSort="+codDestino);
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
	
	function onTestChangeII(event) 
	{
	    var char = event.which || event.keyCode;
	
	    // If the user has pressed enter
	    if (char == 13) 
		{
			var codDe = document.getElementById("codDestino").value;
			
			var cod = document.getElementById("test").value;
			var articuloScn = barras[cod];
			
			if(codDe!=codDestino)
			{
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
			    document.getElementById('xyz').play();
				});
				alarma.addEventListener('ended', showAlert("Destino incorrecto,debe colocar en "+codDestino));
			
				
				//alert("Destino incorrecto, debe colocar en "+codDestino);
				document.getElementById("codDestino").value="";
				document.getElementById("codDestino").focus()
				
				
				event.preventDefault();
			}
			else
			{
				//hago el "submit"
				//String art = request.getParameter("arti");
				//int posicion = Integer.parseInt(request.getParameter("posicion"));
				location.replace("<%=basePath%>ClasificarTareaII.do?art="+articuloScn+"&posicion="+codDestino);
				
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
</body>
</html>
