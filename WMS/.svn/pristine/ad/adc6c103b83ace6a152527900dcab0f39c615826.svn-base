<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


    <script src="<%=basePath%>v3/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="<%=basePath%>v3/assets/js/dataTables/rowgroup.js"></script>
    <script src="<%=basePath%>v3/assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script src="<%=basePath%>v3/assets/js/dataTables/dataTables.fixedHeader.min.js"></script>

	</div>
	<!-- 
	<footer class="site-footer">
	  Mensajes Respuesta SAP
	  <div class="table-responsive">
      	<table class="table table-striped table-bordered table-hover">
        	<thead>
            	<tr>
                	<th>Fecha</th>
					<th>Acción</th>
					<th>Mensaje</th>
				</tr>
            </thead>
            <tbody>
            <c:forEach var="l" items="${logsSAP}">
	            <tr class="gradeD">
					<td>${l.fecha}</td>
					<td>${l.id}</td>
					<td>${l.descripcion}</td>
				</tr>
			</c:forEach>
			</tbody>
         </table>
     	</div>
	</footer>
	 -->
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="<%=basePath%>v3/assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="<%=basePath%>v3/assets/js/jquery.metisMenu.js"></script>
     <!-- MORRIS CHART SCRIPTS -->
     <script src="<%=basePath%>v3/assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/morris/morris.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="<%=basePath%>v3/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="<%=basePath%>v3/assets/js/dataTables/dataTables.bootstrap.js"></script>
    
    
     <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-bs/js/dataTables.bootstrap.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/jszip/dist/jszip.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/pdfmake/build/vfs_fonts.js"></script>
    
    
    <script>
            $(document).ready(function () 
            {
            	var table = $('#encuentra-defaultRep').dataTable({"rowsGroup": [0], "fixedHeader": true, "responsive": true, "pageLength": 5000});
                       
            });
    </script>
    
    <script>
            $(document).ready(function () 
            {
            	var table = $('#encuentra-defaultRepCol').dataTable({ "pageLength": 5000});
                       
            });
    </script>
    
    <script>
            $(document).ready(function () 
            {
            	var table = $('#encuentra-defaultSM').dataTable({"rowsGroup": [0,5,6,7,8], "fixedHeader": true, "responsive": true, "pageLength": 5000});
                       
            });
    </script>
    
         <!-- CUSTOM SCRIPTS -->
          <script>
            $(document).ready(function () {
                $('#encuentra-default').dataTable({pageLength: 5000,language: {
					    decimal: ",",
					    thousands: "."
					  }});
                $('#encuentra-default-II').dataTable({"pageLength": 5000});
                $('#encuentra-defaultVisual').dataTable({"pageLength": 5000});
                
                
                
                
                var tablaInvent = $('#dataTables-invent').DataTable({
			        pageLength: 5000,
			        responsive: true,
			        ajax: {
			        		url:"<%=basePath%>/ReporteInventarioJSON.do",
			        		timeout: 3000
			        		},
			        columns: [
						{ "data": "Estanteria", "title":"Estanteria" },
						{ "data": "Cod_Ubicacion", "title":"Cod. Ubicacion" },
						{ "data": "Estante" , "title":"Estante"},
						{ "data": "Modulo" , "title":"Modulo"},
						{ "data": "Cantidad", "title":"Cantidad" },
						{ "data": "Actualizado" , "title":"Actualizado"} 
						]
			    });
                
                
                
                
                
                var tablaInventTTL = $('#dataTables-inventTTL').DataTable({
			        	bPaginate: false,
    					bLengthChange: false,
    					bFilter: false,
			        
			        responsive: true,
			        ajax: {
			        		url:"<%=basePath%>/ReporteInventarioEstadoJSON.do",
			        		timeout: 3100
			        		},
			        		// { "data2":[{"desde":"14/02 11:54", "hasta":"14/02 16:43", "cantidad":"908", "UnidadesHora":"0.05", "porcentaje":"0.21"}]} 
			        columns: [
								{ "data": "desde", "title":"Contando desde" },
								{ "data": "hasta", "title":"Ultima actualizacion" },
								{ "data": "cantidad" , "title":"Total conteo"},
								{ "data": "UnidadesHora" , "title":"Unidades hora"},
								{ "data": "porcentaje" , "title":"Porcentaje"} 
								]
			    });
                
                setInterval( function () {
				    tablaInvent.ajax.reload();
				    
				}, 10000 );
                
                
                setInterval( function () {
				    tablaInventTTL.ajax.reload();
				}, 11000 );
                
			    $('#dataTables-encomienda').DataTable({
			        dom: "<'row'<'form-inline' <'col-sm-offset-5'B>>>"
					 +"<'row' <'form-inline' <'col-sm-1'f>>>"
					 +"<rt>"
					 +"<'row'<'form-inline'"
					 +" <'col-sm-6 col-md-6 col-lg-6'l>"
					 +"<'col-sm-6 col-md-6 col-lg-6'p>>>",//'Bfrtip',
                buttons:[                	
		            {
		                text:      '<i class="fa fa-file-excel-o"></i>',
		                titleAttr: 'Excel',
		                className: 'btn btn-success',
		                action: 	function(){
		                				exportarExcel();
		                }
		            }
                ]
			    } );

            });
            
            function exportarExcel(){
            	var req = '<%=basePath%>InformeEncomiendasExport.do';
					window.location.assign(req);
            }
    </script>
    
    <script type="text/javascript">
		function clickStopper(e)
		{
			alert("Ya se envio la solicitud al servidor"); 
			e.preventDefault(); 
			//para usar agregar lo siguiente dentro de ONCICK
			//this.addEventListener('click', clickStopper, false);
		}
	</script>
      <!-- CUSTOM SCRIPTS -->
    <script src="<%=basePath%>v3/assets/js/custom.js"></script>
    
   
</body>
</html>






























