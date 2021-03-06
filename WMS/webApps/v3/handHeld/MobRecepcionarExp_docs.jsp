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
			href="<%=basePath%>v2/assets/handheld/buscador.css" media="screen" />
	</c:when>
	<c:otherwise>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="<%=basePath%>v2/assets/css/responsiveForms.css" type="text/css">
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
			function seleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=1;
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=1
			   }      
			}
			
			//La funci?n seleccionar_todo() realiza un recorrido por todos los elementos del formulario. Para hacer un recorrido por todos los campos se utiliza el array "elements", que guarda una referencia con cada elemento que haya dentro del formulario.
			
			//En el recorrido comprueba si el elemento actual es de tipo "checkbox" (recordar que el array elements contiene todos los elementos, pero s?lo deseamos operar con los que sean checkbox) y en ese caso, simplemente se actualiza el atributo "checked" al valor 1, con lo que el chekbox se marcar?.
			
			function deseleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=0
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=0
			   }
			}
			
			
			</script>


<script type="text/javascript">
	
	function subm(accion) 
	{
		document.getElementById("accion").value = accion;
		switch (accion)
		{
			
			case 0:
				if (confirm("Desea confirmar el alta?"))
				{
					document.forms["contactform"].submit();
				}
			break;
			case 1:
				if (confirm("Desea crear de nuevo el bulto?"))
				{
					document.forms["contactform"].submit();
				}
			break;
			case 2:
				if (confirm("Desea descartar el bulto?"))
				{
					document.forms["contactform"].submit();
				}
			break;
			default:
			break;
		
		
		}
		
	}

	
	 
	
</script>




</head>

<c:forEach var="d" items="${uLogeado.seguridadUI}">
	<c:if test="${d=='btn_confirmarSV'}">
		<c:set var = "puede" scope = "page" value = "1"/>
	</c:if>
</c:forEach>
<body>
	<c:if test="${uLogeado!=null}">
		<div class="rounded">
			<div class="row">
				
					<a href="<%=basePath%>pausarTarea.do?sale=si"><input class="col-100 button button-danger" type="button" value="Ir a Menu"/></a>
				
			</div>
			<div class="row">
				<form action="<%=basePath%>darBarrasControl.do"  id="f1" name="f1">
					<div class="col-100">
						<table class="table table-compact table-bordered table-hover responsive"  width="100%">
							<thead>
								<tr>
									<th colspan="4"><strong>Lista de documentos</strong></th>
								</tr>
							    <tr>
							   	 	<th><a href="javascript:seleccionar_todo()">Todos</a><br/><a href="javascript:deseleccionar_todo()">Ninguno</a> </th>
		                    		<th>Orgn.</th>
		                    		<th>Tipo</th>
		                            <th>Documento</th>
		                            <th>Ctd.</th>
								</tr>
							</thead>
		                    <tbody>
		                    <c:forEach var="documentosToCheck" items="${recepcionablesS}">
			                    <c:forEach var="d" items="${documentosToCheck.documentos}">
									<tr>	
										<td style="padding: 1px; vertical-align: middle; text-align: center;"><input type="checkbox" name="CHK${d.depositoO.id}-${d.numeroDoc}"></td>					
										<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.depositoO.descripcion}&nbsp;(${d.depositoO.id})</td>
										<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.razon.descripcion}</td>
										<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.numeroDoc}</td>
										<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.cantidad}</td>
									</tr>
								</c:forEach>
							</c:forEach>
		                   	</tbody>
						</table>
					</div>
					<div class="col-100">
						<input class="col-100 button" type="submit" name="accion" value="Verificarlos"/>
						<c:if test="${puede==1}">
							<!--  <a href="<%=basePath%>/RecepExpSV.do">--> 
							<input class="col-100 button" style="background-color: #FFA500;" type="submit" name="accion" id="btn_confirmarSV" value="Confirmar sin verificar"/>
							
						</c:if>
					
						<a href="<%=basePath%>MenuMob.do?sec=M.E"> <input class="col-100 button button-danger" type="button" value="Cancelar"/></a>
					</div>
				
				</form>
			</div>
			
					
			
			
		</div>	
	</c:if>
	
</body>

		</html>


	</c:if>
</body>
</html>