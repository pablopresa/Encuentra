
		
		
		
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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Usuarios&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Seguridad de Usuarios&nbsp;</h3>   
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
                             <h5>Elija el grupo de Usuarios que desea actualizar</h5>
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" action="<%=basePath%>/SeguridadUsuarios.do">
                            	<div class="row">           
                            	
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">								          
								          <label class="col-md-4 control-label" for="estanteria" class="col-md-4 control-label">Grupo de Usuarios:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="grupo" class="form-control">
													<option value="" selected="selected"></option>
													<c:forEach var="gr" items="${lstGrupos}">
														<c:if test="${gr.id!=grupoX}">
															<option value="${gr.id}"><c:out value="${gr.descripcion}"></c:out> </option>
														</c:if>
														<c:if test="${gr.id==grupoX}">
															<option value="${gr.id}" selected="selected"><c:out value="${gr.descripcion}"></c:out> </option>
														</c:if>
													</c:forEach>
												</select>
								          </div>
								        </div>
								    </div>
								    
													
													
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Buscar" />
										  	</div>											
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">											
											<div class="col-md-8">
										  		<a type="button" class="btn btn-success" onclick="openM()" href="#">Agregar Grupo</a>
										  	</div>											
										</div>
									</div>
									
									</div>	     
							</form>
						</div>
						
						<hr/>
						<div class="card-header">
					    	<h5>Listado de Usuarios</h5>
						</div>
                        <div class="card-body">
							<div class="alert alert-info">
                    			<strong style="color: grey"> <c:out value="Actualizando Grupo "></c:out></strong>      
                    			<c:forEach var="gr" items="${lstGrupos}">
									<c:if test="${gr.id==grupoX}">
										<strong style="color: grey"><c:out value="${gr.descripcion}"></c:out></strong>
									</c:if>											
								</c:forEach>      
                    		</div>
                            	<form class="form-horizontal" id="f1" name="frm" role="form" method="post" action="<%=basePath%>/SeguridadUsuariosAlta.do">
                            		<div class="row">
	                            		<div class="col-md-4">
		                            		<label for="sel2">Todos los Usuarios</label>
		     								 <select multiple class="form-control" id="sel2" name="sellist2" style="height: 500px;">
		     								 	<c:forEach var="us" items="${lstUsuarios}">
													<option value="${us.id}"><c:out value="${us.descripcion}"></c:out> </option>	
												</c:forEach>
		        							</select>
	        							</div>
	                                    
	                                   <div class="col-md-2" style="margin-top: 20%; margin-left: 9%; margin-right: 3%;" >
	                                  	 <div class="row">
	                                   		<a href="javascript:addRemoveUser('add');" ><button type="button" class="btn btn-info btn-lg" style="width: 150px;">Agregar</button></a>   									 
	                                   	 </div>
	   								   <div class="row">
	   									 	<a href="javascript:addRemoveUser('remove');" ><button type="button" class="btn btn-info btn-lg" style="width: 150px;">Quitar</button></a>
	   									 </div>
	   								   </div>
	                                    	
	                                    <div class="col-md-4">	
											<label for="sel2">Usuarios del Grupo</label>									
	     								 <select multiple class="form-control" id="sel2" name="sellist3" style="height: 500px;">
	     								 	<c:forEach var="gu" items="${grupoUsuarios}">
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
					<button type='button' class='close' data-dismiss='modal'>&times;</button>
	    			<h4 class='modal-title'>Nuevo Grupo</h4>
	    		</div>
				<div class='modal-body'>
				<form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/GrupoSeguridadUsuarios.do">
					<div class="form-group" style="float: left;">
						<label>Nombre:&nbsp;</label>						
						<input type="text"  class="form-control" style="width: 300px; " name="nomGrupo" value="${nomGrupo}">	
						<input type="submit" name="Submit"  class="btn btn-info"  value="Agregar" />					
					</div>
				</form> 				
				</div>
      			<div class='modal-footer' style="margin-top: 75px">
        			<button type='button' class='btn btn-info' data-dismiss='modal'>Close</button>
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
function addRemoveUser(opcion) 
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
					  
					 	 req = '<%=basePath%>/SeguridadUsuariosAlta.do?opcion=add&altas='+valores;
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
					  	req = '<%=basePath%>/SeguridadUsuariosAlta.do?opcion=remove&altas='+valores;
					  }
						//SendAjaxPost(req)
						window.location.assign(req);
				      //document.getElementById('f1').submit();
				  }

</script>

        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>