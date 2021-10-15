<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<input type="text" value= "<%=basePath%>" id ="basePath" style="display: none">   							
  <div class="table-responsive">
     <table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
		<thead class="thead-dark">
            <tr>
              	<th class="text-center">Marca</th>
               	<th class="text-center">Genero</th>
               	<th class="text-center">Categoria</th>
               	<th class="text-center">Clase</th>
               	<th class="text-center">Articulo</th>
                <th class="text-center">Distintas ubicaciones</th>
				<th class="text-center">Ojos</th>
				<th class="text-center">Cantidad</th>
				<th class="text-center"></th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.marca}</td>
				<td class="text-center">${l.genero}</td>
				<td class="text-center">${l.categoria}</td>
				<td class="text-center">${l.clase}</td>
				<td class="text-center">${l.articulo}</td>
				<td class="text-center">${l.ojosCantidad}</td>
				<td class="text-center">${l.ojos}</td>
				<td class="text-center">${l.total}</td>
				<!-- <td class="text-center"> <a href="#" onclick="mostrar('${l.articulo}')">ver</a> </td>  -->
				<td class="text-center"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dive${l.articulo}">Ver</button></td>
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>
 <div id="mymodal" class="modal fade" role="dialog">
		
		</div>
	
        <c:forEach var="c" items="${lista}">
	     <!--    <div id="dive${c.articulo}" style="display: none;"> --> 
		<div id="dive${c.articulo}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
					<div class="modal-header">
                        <h5 class="modal-title">Articulo &nbsp;${c.articulo}</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
		    		</div>
		    		<form action="<%=basePath%>/consolidar.do">
						<div class="modal-body">
							<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
								<thead>
									<tr>
										<th>Ubicacion</th>
										<th>Cantidad</th>
									<!--	<th></th> -->
									</tr>
								</thead>
								<c:forEach var='a' items='${c.listaArticulos}'>
									<tbody>
										<tr>
											<td>${a.ubicacion}</td>
											<td>${a.cantidad}</td>
										<!--	<td><input type="checkbox" name="${a.sku}-${a.ubicacion}"></td> -->
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>
                    	<div class="modal-footer">
                        	<button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
		        			<!--  <input type="submit" class="btn btn-info" value="Tarea de Consolidacion">
		        			<input type="text" name="save" value="true" style="display: none;"> -->
		      			</div>
	      			</form>
	    		</div>
			</div>
		</div>
	</c:forEach>
        <!--  
        <script type="text/javascript">
			function mostrar(idmodal){
				  var firstDivContent = document.getElementById('mymodal');
				  firstDivContent.innerHTML="";
				  var secondDivContent = document.getElementById('dive'+idmodal);
				    
				  firstDivContent.innerHTML = secondDivContent.innerHTML
				
				
				$("#mymodal").modal();
			}
		</script>
		-->

		