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
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Expedición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Envío&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Modificar orden de recorrido</h3>   
                        <h6>Mueva para arriba o para abajo </h6>
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
                             Destinos
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" >
                                    <thead>
                                        <tr>
                                           <th style="visibility: hidden;"></th>
											<th>Deposito</th>
											<th>Subir</th>
											<th>Bajar</th>
											<th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="de" items="${envio.depositos}">
										<tr class="gradeD">
											<td style="visibility: hidden;"></td>
											<td><c:out value="${de.idDeposito}"></c:out></td>
											<td><a href="<%=basePath%>modOrdenEnvio.do?acc=sube&idDeposito=${de.idDeposito}">Subir</a> </td>
											<td><a href="<%=basePath%>modOrdenEnvio.do?acc=baja&idDeposito=${de.idDeposito}">Bajar</a> </td>
											<td><a href="<%=basePath%>modOrdenEnvio.do?acc=eliminar&idDeposito=${de.idDeposito}">Eliminar</a> </td>
										</tr>
										</c:forEach>
										<tr>
											<td colspan="5" style="text-align: right;"><a class="btn btn-info" href="<%=basePath%>/v3/altaEnvioII.jsp">Volver</a></td>
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
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
