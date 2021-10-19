<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
       
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking Mayorista&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Confirmar todo</h3>   
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
                             Pickings
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                            		<thead>
                                        <tr>
                                            <th>Picking</th>
											<th>Cliente</th>
											<th>Unidades</th>
											<th>Confirmar todo</th>
										</tr>
								    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${pickingstoAll}">
                                    			<tr class="gradeD">
                                    			<td><c:out value="${a.iid}"></c:out></td>
												<td><c:out value="${a.descripcion}"></c:out></td>
												<td><c:out value="${a.id}"></c:out></td>
												<td><a href="<%=basePath%>ConfirmarPickAll.do?idPicking=${a.iid}"><i class="fa fa-arrow-right"></i></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</form>
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
