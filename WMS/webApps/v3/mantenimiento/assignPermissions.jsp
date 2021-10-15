<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
		
	           	<div class="row">
                	<div class="col-md-12">
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Usuarios&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Permisos Grupos de Usuarios&nbsp;</h3>   
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
                    <div class="card">
                        <div class="card-header">
                             <h5>Elija el grupo de Usuarios que desea actualizar</h5>
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" action="<%=basePath%>/AsignarPermisos.do">
                            	<div class="row">           
                            	
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">								          
								          <label class="col-md-4 control-label" for="estanteria" class="col-md-4 control-label">Grupo de Usuarios:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="grupo" class="form-control">
													<option value="" selected="selected"></option>
														<c:forEach var="gr" items="${lstGrupos}">
															<option value="${gr.id}"><c:out value="${gr.descripcion}"></c:out> </option>
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
									
									
									</div>	     
							</form>
						</div>
						
						<hr/>
						<div class="card-header">
					    	Permisos
						</div>
                        <div class="card-body">
							<div class="alert alert-info">
                    			<strong style="color: grey"> <c:out value="Permisos del grupo ${nombreGrupo}"></c:out></strong>            
                    		</div>
                            <div class="arbol">
                            	
                            </div>
                        </div>
                                     
                        <hr/>
						
                        </div>
                        
              


                       <input class="hidden" type="text" value="${datosArbolCheck}" id="datosArbolCheck" />
                       
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            
            
  
<jsp:include page="/v3/util/footer.jsp"></jsp:include>
<script>
   

		function sendReqConsulta(permisos,grupo)
    	{
		    var xmlhttp = new XMLHttpRequest();
		    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		    xmlhttp.onreadystatechange = function() {
		        if (xmlhttp.readyState == XMLHttpRequest.DONE ) 
		        {
		           if (xmlhttp.status == 400) 
		           {
		              alert('There was an error 400');
		           }
		        }
		    };

		   
			
		    xmlhttp.open("POST", "<%=basePath%>/AsignarPermisosAlta.do?permisos="+permisos+"&grupoPermisos="+grupo, true);
		    xmlhttp.send();
		    req='';
		}     

		var data = ${datosArbol}
		var i;
				var valores = [];
				for (i = 0; i < data.length; i++) 
				{
					var padre = data[i];
					////console.log(padre.text)
					var y;
					for (y = 0; y < padre.children.length; y++) 
					{
						var hijo = padre.children[y];
						if(hijo!=null)
						{
							//console.log('\t'+ hijo.text)
							if(hijo.children!=null && hijo.children.length>0)
							{
								var z;
								for (z = 0; z < hijo.children.length; z++) 
								{
									var nieto = hijo.children[z];
									if(nieto!=null)
									{
										//console.log('\t\t'+ nieto.text)
										if(nieto.check)
										{
											valores.push(nieto.id)
										}
									}
									
								}
							}
							else
							{
								if(hijo.check)
								{
									valores.push(hijo.id)
								}
							
							}
						}
						else
						{
							if(padre.check)
							{
								valores.push(padre.id)
							}
						}
					}
				}
				
			    let tree = new Tree('.arbol', {
			        data: [{ id: '-1', text: 'Menu', children: data }],
			        closeDepth: 0,
			        loaded: function () 
			        {
			        
			        	
			           this.values = valores; 
			            //console.log(this.selectedNodes)
			            //console.log(this.values)
			            
			        },
			        onChange: function () 
			        {
			        	var i;
			        	var inns = [];
						for (i = 0; i < this.selectedNodes.length; i++) 
						{
						  var nodo = this.selectedNodes[i];
						  if(nodo.id!='-1')
						  {
						  	inns.push(nodo.id);
						  }
						  
						}
						sendReqConsulta(inns.toString(),${grupoPermisos})
						 
			            
			        }
			    })
	

    
</script>
        
        
		
