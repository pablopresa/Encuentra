<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
       
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Cargar desde Archivo</h3>   
                        <h6>Cargue el archivo y despues guarde los datos</h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             Actualizar/Agregar a ojo
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post" action="<%=basePath%>/cargarFile.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">								          
								          <label class="col-md-4 control-label" for="idOjo" class="col-md-4 control-label">Ojo:&nbsp</label>
								          <div class="col-md-8">
								            	<input  class="form-control" name="idOjo" id="idOjo" value="${idOjo}"/>
								          </div>
								        </div>
								    </div>
                            	
	                            	<div class="col-sm-6 col-lg-4">
	                            	<label class="col-md-4 control-label" class="col-md-4 control-label"></label>
								         <div class="input-group mb-3">
                                             <div class="custom-file">                                             
                                                 <input type="file" class="custom-file-input" id="inputGroupFile02">
                                                 <label class="custom-file-label" for="inputGroupFile02">Subir archivo</label>
                                             </div>
								        </div>
								    </div>
									<div class="col-sm-6 col-lg-4">									
										<div class="form-group">
											<label class="col-md-4 control-label" for="archi" class="col-md-4 control-label"></label>
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Vista previa" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
							<hr>
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" >
                                    <thead>
                                        <tr>
                                           <th>Articulo</th>
											<th>Cantidad</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${articulosS}">
											<tr class="gradeD">
												<td> <c:out value="${a.descripcion}"></c:out> </td>
												<td> <c:out value="${a.id}"></c:out> </td>
											</tr>	
										</c:forEach>
										<tr>
											<td><div style="text-align: center;"> <a href="<%=basePath%>/UpactOjo.do?update=1" class="btn btn-success">Actualizar </a></div> </td>
											<td><div style="text-align: center;"><a href="<%=basePath%>/UpactOjo.do?update=0" class="btn btn-success">Agregar</a></div></td>	
										</tr>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
           
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
