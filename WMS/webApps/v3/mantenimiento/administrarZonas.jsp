
		
		
		
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <!-- /. NAV SIDE  -->
        
        <link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Zonas</h3>   
                        <h6></h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card card-default">
                        <div class="card-header">
                             <h5>Elija la zona de estanterias que desea actualizar</h5>
                        </div>
                        <div class="card-body">
                        <form class="form-inline" role="form" action="<%=basePath%>/FiltrarZona.do">
			            	<div class="form-group mx-sm-3 mb-8">
								<label for="estanteria">Zonas:&nbsp;</label>
								<select name="zona" class="form-control">
									<option value="" selected="selected"></option>
									<c:forEach var="gr" items="${lstZonas}">
										<c:if test="${gr.id!=zonaSeleccionada}">
											<option value="${gr.id}"><c:out value="${gr.descripcion}"></c:out> </option>
										</c:if>
										<c:if test="${gr.id==zonaSeleccionada}">
											<option value="${gr.id}" selected="selected"><c:out value="${gr.descripcion}"></c:out> </option>
										</c:if>
									</c:forEach>
								</select>
							</div>
			                <button type="submit" class="btn btn-primary" value="Buscar"><i class="feather icon-search"></i>Filtrar</button>
			                <button type="button" class="btn btn-secondary" onclick="openM()"><i class="feather icon-plus-circle"></i>Nueva Zona</button>
						</form>
						</div>
						<hr/>
						<div class="card-header">
					    	<h5>Listado de Estanterias del Almacén</h5>
						</div>
                        <div class="card-body">
							<div class="alert alert-info">
                    			<strong style="color: grey"> <c:out value="Actualizando Zona "></c:out></strong>      
                    			<c:forEach var="gr" items="${lstZonas}">
									<c:if test="${gr.id==zonaSeleccionada}">
										<strong style="color: grey"><c:out value="${gr.descripcion}"></c:out></strong>
									</c:if>											
								</c:forEach>      
                    		</div>
                            	<form class="form-horizontal" id="f1" name="frm" role="form" method="post" action="<%=basePath%>/AsociarEstanteriasAZona.do">
                            		<div class="row">
	                            		<div class="col-md-4">
		                            		<label for="sel2">Todos las Estanterias</label>
		     								 <select multiple class="form-control" id="sel2" name="sellist2" style="height: 500px;">
		     								 	<c:forEach var="us" items="${lstEstanterias}">
													<option value="${us.id}"><c:out value="${us.descripcion}"></c:out> </option>	
												</c:forEach>
		        							</select>
	        							</div>
	                                    
	                                   <div class="col-md-2" style="margin-top: 20%; margin-left: 9%; margin-right: 3%;" >
	                                  	 <div class="row">
	                                   		<a href="javascript:addRemoveZona('add');" ><button type="button" class="btn btn-info btn-lg" style="width: 150px;">Agregar</button></a>   									 
	                                   	 </div>
	   								   <div class="row">
	   									 	<a href="javascript:addRemoveZona('remove');" ><button type="button" class="btn btn-info btn-lg" style="width: 150px;">Quitar</button></a>
	   									 </div>
	   								   </div>
	                                    	
	                                    <div class="col-md-4">	
											<label for="sel2">Estanterias de la Zona</label>									
	     								 <select multiple class="form-control" id="sel2" name="sellist3" style="height: 500px;">
	     								 	<c:forEach var="gu" items="${grupoEstanterias}">
												<option value="${gu.id}"><c:out value="${gu.descripcion}"></c:out> </option>	
											</c:forEach>
	        							</select>
										</div>
									 </div>	
                                    
                                    
                                    
                                </form>
                            </div>
                                     
                        <hr/>
						
                        </div>
                        
              

<div id="mymodal" class="modal fade" role="dialog">
		
	</div>


	<div id="dive" style="visibility: hidden;">
		<div class='modal-dialog'>
	    <!-- Modal content-->
			<div class='modal-content'>
				<div class='modal-header'>
				 	<h5 class="modal-title">Alta nueva zona</h5>
					<button type='button' class='close' data-dismiss='modal'>&times;</button>
	    		</div>
				<div class='modal-body'>
				<form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/AgregarNuevaZona.do">
					<div class="form-group">
						<label>Nombre:</label>						
						<input type="text"  class="form-control" name="nomZona" value="${nomGrupo}">	
					</div>
					<button type="submit" name="Submit" class="btn btn-secondary"><i class="feather icon-plus-circle"></i>Agregar</button>		
				</form> 				
				</div>
      			<div class='modal-footer' style="margin-top: 75px">
        			<button type='button' class='btn btn-info' data-dismiss='modal'>Cerrar</button>
      			</div>
      			
    		</div>
		</div>
	</div>

                       
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            
 <script type="text/javascript">
function openM()
{
	  var firstDivContent = document.getElementById('mymodal');
	  firstDivContent.innerHTML="";
	  var secondDivContent = document.getElementById('dive');
	    
	  firstDivContent.innerHTML = secondDivContent.innerHTML
	
	
	$("#mymodal").modal();
}
</script> 
  
  
<script type="text/javascript">
function addRemoveZona(opcion) 
				{	
					var valores="";	
					var req = '';
					if(opcion=='add'){
						for (i = 0; i < document.frm.sellist2.length; i++) { 
 				 	
 				 	  		if(document.frm.sellist2.options[i].selected){
 				 	  			console.log("entro");
					  
					  			console.log("indice " +i);
					  			var id = document.frm.sellist2.options[i].value;
					  			console.log("valor " +id);	
					  			var txt =	document.frm.sellist2.options[i].text;
					  			console.log("txt "+ txt); 
						
								console.log("creo opcion"); 
					  			var selOpcion=new Option(txt, id);
					  			console.log("hice opcion"); 
     				  			eval(document.frm.sellist3.options[document.frm.sellist3.length]=selOpcion);
					  			console.log("termina cliente");					  
					  
					  			valores+=" "+id
 				 	  		}
 				 							  
					  	}
					  
					 	 req = '<%=basePath%>/AsociarEstanteriasAZona.do?opcion=add&altas='+valores;
					  }
					  if(opcion=='remove'){
					  
					 	 for (i = 0; i < document.frm.sellist3.length; i++) { 
 				 	
 				 	  		if(document.frm.sellist3.options[i].selected){
 				 	  			console.log("entro");
					  
					  			console.log("indice " +i);
					  			var id = document.frm.sellist3.options[i].value;
					  			console.log("valor " +id);	
					  			var txt =	document.frm.sellist3.options[i].text;
					  			console.log("txt "+ txt); 
										  
					  
					  			valores+=" "+id
 				 	  		}
 				 							  
					  	}
					  	req = '<%=basePath%>/AsociarEstanteriasAZona.do?opcion=remove&altas='+valores;
					  }
						//SendAjaxPost(req)
						window.location.assign(req);
				      //document.getElementById('f1').submit();
				  }

</script>

        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>