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
	var cantidadBajada = 0;
	var coleccion = ${json};
	
	function ejecutarFormulario(){
		var select3 = document.getElementById('sel3');
		var text = "";
		if(confirm("?Desea continuar?")){
			if(select3.length == 0){
				alert("Debe seleccionar al menos un bulto");	
			} else {
				for (i = 0; i < select3.length; i++) {
					if( i != select3.length-1){
						text += select3.options[i].value+"-"+select3.options[i].text+",";
					} else {
						text += select3.options[i].value+"-"+select3.options[i].text+"";
					}
				}
				var aux = document.getElementById("idOculto");
				aux.value=text;
				document.getElementById("formulario").submit();
			}
		   
		}
	}
	
	function Inicio(){
		var select1 = document.getElementById('idSelect'); 
		var cantidadNecesitada = parseInt(document.getElementById('strongCantidadNecesitada').innerHTML); 
		ShowSelected();
		for (i=0; i < select1.length; i++)
		{ 
			var idOjo = select1.options[i].value;
			
			for (let j = 0 ; j < coleccion.listaUbicaciones.length ; j++)
			{ 
				
				if ( coleccion.listaUbicaciones[j].idOjo == idOjo)
				{
					var ojo = coleccion.listaUbicaciones[j];
					var descrip = ojo.descripcionEstanteria;
					var estante = ojo.idModulo;
					var modulo = ojo.idEstante;
					var idOjo = ojo.idOjo;
					
					
					var bultos = ojo.listaBultos;
			
					var acumulador = 0;
					for(let k=0; k<bultos.length;k++)
					{
						var idBulto = bultos[k].idBulto;
						var cantidad = bultos[k].cantidadArticulos;
						acumulador += cantidad;
					}
			
					if(acumulador >= cantidadNecesitada)
					{
						select1.value = idOjo;
						ShowSelected();
						break;
					}
			
					
					
					break;
				}
			}
			
			if(select1.value = idOjo){
				break;
			}
			
			
		}
		
		
	}

	
	function ShowSelected()
	{
	
		//var input = document.getElementById('inputLector');
		//input.value = "";
		var idOjo = document.getElementById('idSelect').value;
		for (let i = 0 ; i < coleccion.listaUbicaciones.length ; i++){
			if ( coleccion.listaUbicaciones[i].idOjo == idOjo){
				var ojo = coleccion.listaUbicaciones[i];
				var descrip = ojo.descripcionEstanteria;
				var estante = ojo.idModulo;
				var modulo = ojo.idEstante;
				var idOjo = ojo.idOjo;
				break;
			}	
		}
		document.getElementById('sel4').innerHTML = descrip; //Seteo la descripcion al cambiar de ojo
		document.getElementById('sel5').innerHTML = " Estante: "+estante;
		document.getElementById('sel6').innerHTML = " | Modulo: "+modulo;
		document.getElementById('sel7').innerHTML = " | Ojo: "+idOjo;
		
		var bultos = ojo.listaBultos;
		var html = "";
		for(let i=0; i<bultos.length;i++)
		{
			
			var obj = bultos[i];
			var idBulto = bultos[i].idBulto;
			var cantidad = bultos[i].cantidadArticulos;
			html += '<option value='+idOjo+'-'+cantidad+'> '+idBulto+'('+cantidad+')</option>';
		}
		
		document.getElementById('sel2').innerHTML = html;
	} 
	
	
	
	
	
	
	
	
	function preventBack() {
    	window.history.forward();
	}

	setTimeout("preventBack()", 0);
	window.onunload = function() {
    	null
	};


	
</script>
</head>
<body onload="Inicio()">

<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body id="dt_example" style="border-left: 0;"></body>


<form name ="formulario" id="formulario" action="<%=basePath%>/PosicionesPosibles.do" method="post">
<c:if test="${uLogeado!=null}">
	<c:forEach var="d" items="${uLogeado.seguridadUI}">
		<c:if test="${d=='box_cant_picking'}">
			<c:set var = "puede" scope = "page" value = "1"/>
		</c:if>
	</c:forEach>
				
				<div align="center">
				<table>
					<div class="field" align=center style="float: center;">
						<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.B'"  value="Volver al menu"/>					
					</div>
				</table>
						
						
					<p>
					<strong id="sel4" style="font-size: 15pt; color: green; font-weight: bold;"><c:out value=""></c:out></strong>
					</p>
					
					<p>
					<strong id="sel5" style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="Estante: "></c:out></strong>
					<strong id="sel6" style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="| Modulo: "></c:out></strong>
					<strong id="sel7" style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="| Ojo: "></c:out></strong>
					</p>
										
																	
					<p>
					<strong style="font-size: 15pt; color: green; font-weight: bold;"><c:out value="${reposicionPicking.getIdArticulo()}"></c:out></strong>
					</p>
					<p>
					<strong style="font-size: 15pt; color: green; font-weight: bold;"><c:out value="${reposicionPicking.getDescripcionArticulo()}"></c:out></strong>
					<br/>
					<img alt="" src="${reposicionPicking.imagen}" style="width: 290px;">
					</p>
					<p>
					<strong style="font-size: 20pt; color: green; font-weight: bold;" id="strongCantidad"><c:out value="0"></c:out></strong>
					<strong style="font-size: 20pt; color: green; font-weight: bold;" ><c:out value=" / "></c:out></strong>
					<strong style="font-size: 20pt; color: green; font-weight: bold;" id="strongCantidadNecesitada"><c:out value='${reposicionPicking.getCantidad()}'></c:out></strong>
					</p>
					<p>
						<strong style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="Unidades packing: "></c:out></strong>
						<strong style="font-size: 10pt; color: green; font-weight: bold;"><c:out value="${reposicionPicking.packing}"></c:out></strong></p>
				</div>
				<table>
					<tr>
						<td colspan="2" style="text-align:center;">
							<p><strong style="text-align:center; font-size: 10pt;"> Ubicaci?n </strong></p>						
						</td>
					</tr>
					<tr>					
				<!--  	 	<td>
					 		<input  type="text" class="form-control" style="width: 90px;" onchange="lector()" id="inputLector" value=""/>
					 	</td>-->
					 	<td>
					 		<select id="idSelect" name="idOjo" class="form-control" onchange="ShowSelected()">
								<c:forEach var="gr" items="${reposicionPicking.getListaUbicaciones()}">							
										<option value="${gr.idOjo}" ><c:out value="${gr.idOjo}"></c:out> </option>
								</c:forEach>
								<c:if test="${reposicionPicking.listaUbicaciones.size() == 1}">
									<option  value="" > Seleccione </option>
								</c:if>
							</select>  
						</td>  
							 
					</tr>
				</table>
							
								
				<table>		
					<tr>
						<td style="text-align: center;">
							<label for="sel2">Bultos posibles</label>
						</td>
						<td>
							&nbsp;
							
						</td>
						<td style="text-align: center;">
							<label for="sel2">Bultos a mover</label>
						</td>
					</tr>
					<tr>
						<td>
							<select id="sel2" name="sellist2" size="4" onchange="setQty(this)"></select>
						</td>
						<td>
							<!-- botones -->
							<input id="Qty" value="" style="width: 48px"/>
							<br/><br/>
							<a href="javascript:subirbajar('bajar');"><button type="button" class="btn btn-info btn-lg" style="width: 60px;">>></button></a>
							<br/><br/>
							<a href="javascript:subirbajar('subir');"><button type="button" class="btn btn-info btn-lg" style="width: 60px;"><<</button></a>
						</td>
						<td>
							<select id="sel3" name="sellist3" size="4" onchange="setQty(this)"></select>
						</td>
					</tr>
				</table>
				<input name="idOculto" id="idOculto" type="text" style="display:none" value="" />
				<div class="field" align="center">
						<div style="height: 5px;"></div>
							<input class="col-100 button" type="button" name="1"  onclick="ejecutarFormulario()"  value="Confirmar"/>					
				</div>
				<div class="field" align="center">
						<div style="height: 5px;"></div>
							<a class="col-100 button" href="<%=basePath%>noEncontradoRepo.do">No Encontrado</a>					
				</div>	
			
		</c:if>

</form>
		
		
		
		
		<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
		<c:if test="${menError!=null}">
			<script type="text/javascript">
				alert('${menError}');
			</script>	
			
		</c:if>
	
	
<script>

function setQty(elemento)
{
	var value = elemento.value;  
	var ojoIn = value.split("-")[0];
	var cantidadIn = parseInt(value.split("-")[1]);
    document.getElementById("Qty").value=cantidadIn;
}

function subirbajar(accion) 
{
	var strongCantidad = document.getElementById('strongCantidad');
	var acumulador = parseInt(strongCantidad.innerHTML);
	
 	if(accion=='bajar')
    {
 		var origen = document.getElementById("sel2");
        var x = origen.selectedIndex;	
        var destino = document.getElementById("sel3");
        
    	var cantidad = document.getElementById("Qty").value;
    	var valueIn = origen.options[origen.selectedIndex].value;
    	var ojoIn = valueIn.split("-")[0];
    	var cantidadIn = parseInt(valueIn.split("-")[1]);
  
    	
    	if(cantidad>cantidadIn)
    	{
    		alert("No puede mover "+cantidad+" porque en el bulto hay "+cantidadIn)
    		
    	}
    	else
    	{
    		var poner = document.createElement('option');
    		var textoO = origen.options[origen.selectedIndex].text;
    		textoO = textoO.replace("("+cantidadIn+")", "("+cantidad+")");
    		
        	poner.text =textoO; 
            
        	poner.value = ojoIn+'-'+cantidad;
        	acumulador+=parseInt(cantidad);
        	
        	
        	destino.add(poner);
        	var textoD = origen.options[origen.selectedIndex].text;
        	var idBultoT = textoD.replace("("+cantidadIn+")", "");
            if(cantidadIn-cantidad>0)  //(Cantidad del bulto - cantidad seleccionada es mayor que 0)
            {
            	origen.options[origen.selectedIndex].value=ojoIn+'-'+parseInt(cantidadIn-cantidad);
            	
            	
            	textoD = textoD.replace("("+cantidadIn+")", "("+(cantidadIn-cantidad)+")");
            	origen.options[origen.selectedIndex].text=textoD;
            }
            else
            {
            	origen.remove(x);	
            }
    		
            actualizarBultoIn(idBultoT,cantidad,true,ojoIn)	
    	}
        
        
        
        
    }
    else  //Agregar
    {
    	//va de destion a origen
        var origen = document.getElementById("sel2");
        var destino = document.getElementById("sel3");
        
        var textoD = destino.options[destino.selectedIndex].text;
        var valueIn = destino.options[destino.selectedIndex].value;
    	var ojoIn = valueIn.split("-")[0];
    	var cantidadIn = parseInt(valueIn.split("-")[1]);
        
        var idBultoT = textoD.replace("("+cantidadIn+")", "");
        
        actualizarBultoIn(idBultoT,cantidadIn,false,ojoIn);
        
        //var poner = document.createElement('option');
    	
    	acumulador-=parseInt(cantidadIn);
    	
    	
    	
        //poner.value = cantidad;
    	
    	
    	//origen.add(poner);
        
		destino.remove(destino.selectedIndex); 
        
        
    
    }
 	
 	strongCantidad.innerHTML = acumulador;
}
function actualizarBultoIn(idBultoI,cantidadBajada,baja,ojoIn)
{
	var select1 = document.getElementById('idSelect'); 
	var idOjo = select1.options[i].value;
	
	for (let j = 0 ; j < coleccion.listaUbicaciones.length ; j++)
	{ 
		
		if ( coleccion.listaUbicaciones[j].idOjo == idOjo)
		{
			var ojo = coleccion.listaUbicaciones[j];
			var bultos = ojo.listaBultos;
			var posicionEliminar = -1;
			var found=false;
			for(let k=0; k<bultos.length;k++)
			{
				if(bultos[k].idBulto==idBultoI && idOjo==ojoIn)
				{
					found=true;
					var cantidad = 0;
					if(baja==true)
					{
						
						cantidad =bultos[k].cantidadArticulos-cantidadBajada; 
						if(cantidad>0)
						{
							bultos[k].cantidadArticulos=cantidad;	
						}
						else
						{
							posicionEliminar=k;
						}
					}
					else
					{
						var cantidaK =parseInt(bultos[k].cantidadArticulos)+parseInt(cantidadBajada);
						bultos[k].cantidadArticulos=cantidaK;
					}
					
					
					break;
				}
			}
			if(baja==false && found==false)
			{
				const bultoAdd = {
						idBulto: idBultoI,
						cantidadArticulos: cantidadBajada
						};
				bultos.push(bultoAdd);
				
			}
	
			if(posicionEliminar >= 0)
			{
				bultos.splice(posicionEliminar, 1)
				break;
			}
	
			
			
				
		}
	}
	
}
</script>
		
		
</c:if>

<c:remove var="menError" scope="session" />
</body>
</html>