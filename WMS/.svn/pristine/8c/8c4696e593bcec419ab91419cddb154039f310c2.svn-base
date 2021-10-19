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
	                	<c:if test="${menError!=null}">
	                		<h6><strong>Mensaje:</strong></h6>
		                    <div class="alert alert-info">
		                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
		                    </div>
	                    </c:if>
            			<iframe class="col-md-12" style="height: 1600px; width:100%; border: none" src="<%=basePath%>v3/assets/agenda/agenda.jsp" frameborder="0" allowFullScreen="true"></iframe>
            		</div>
            	</div>
            </div>
            
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
