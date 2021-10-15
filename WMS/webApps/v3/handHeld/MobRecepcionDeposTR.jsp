<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
       
        
        <jsp:include page="/v3/util/index_headerSM.jsp"></jsp:include>
		
		  
		  
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
		  
		  
		  <script language="Javascript" type="text/javascript">
				if (document.cookie != "") {
            		//if (confirm("Eliminar todas las Cookies?")) {
                		la_cookie = document.cookie.split("; ")
                		fecha_fin = new Date
                		fecha_fin.setDate(fecha_fin.getDate()-1)
						for (i=0; i<la_cookie.length; i++) {
                    		mi_cookie = la_cookie[i].split("=")[0]
                    		document.cookie = mi_cookie + "=;expires=" + fecha_fin.toGMTString()
                		}
                		//document.write("Se han eliminado: " + la_cookie.length + " Cookies ")
            		//}
        		}
    	</script> 
		  
		  
        <!-- /. NAV SIDE  -->
        
		    	
                
                
                <div class="row" >
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                    <div class="panel-heading">
                            &nbsp;Lista de Depositos con Remitos en transito &nbsp;
                        </div>
                        <div class="panel-body">
                        	<div>
                        		<table class="table table-compact table-bordered table-hover" id="encuentra-mob" border=1 frame=void rules=rows cellpadding="1" >
	                                    <thead>
	                                        <tr>
	                                           	<th>Id Dep. Destino</th>
	                                           	<th>Descripcion</th>
	                                           	<th>Usar</th>
											</tr>
										</thead>
	                                    <tbody>
	                                    	<c:forEach var="d" items="${deposTran}">
												<tr class="gradeD">						
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.id}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.descripcion}(${d.idII})</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;"><a href="<%=basePath%>selDepoRecepTR.do?idDc=${d.id}"><img alt="Usar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a> </td>
												</tr>
											</c:forEach>
	                                    </tbody>
									</table>
	                          	
	                          
						</div>
                    </div>
                 </div>
            </div>
           
            <jsp:include page="/v3/util/footer_mob.jsp"></jsp:include>
