<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:choose>
  <c:when test="${css=='IE'}">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
  </c:otherwise>
</c:choose>
</head>
<body>


<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


</head>
<body>
	<div class="container">
		<c:if test="${menError!=null}">
			<strong style="color: red">${menError}</strong>
		</c:if>

				
			<c:if test="${(not empty sec)&&(sec!='M')}">
				<div class="row">
					<div class="col-100">
						<c:forEach var="m" items="${menu}">
								<c:if test="${m.visible}">
									<c:if test="${m.id == 'M'}"> <!-- Menu colectores -->
										<c:forEach var="h" items="${m.hijos}">
											<c:if test="${h.visible}">
												<c:if test="${h.id == sec}">
													<c:forEach var="n" items="${h.hijos}"> <!-- Sub Men?s colectores -->
															<c:if test="${n.visible}">
																<a href="<%=basePath%>${n.href}"><input	class="col-100 button" type="button" name="1" value="${n.descripcion}" /></a>
															</c:if>
														
														</c:forEach>
													</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:if>
						</c:forEach>
						<!-- Volver al men? -->
						<c:if test="${sec!='M'}">
							<a href="<%=basePath%>MenuMob.do?sec=${n.padre}"><button class="col-100 button button-danger"><i class="fa fa-arrow-circle-left fa-2x" ></i></button></a>
						</c:if>
					</div>
				</div>
			</c:if>
			<c:if test="${(empty sec)||(sec=='M')}">
				<div class="row">
					<div class="col-100">
						<c:forEach var="m" items="${menu}">
								<c:if test="${m.visible}">
									<c:if test="${m.id == 'M'}">
										<c:forEach var="h" items="${m.hijos}">
											<c:if test="${h.visible}">
												<a href="<%=basePath%>${h.href}"><input
													class="col-100 button" type="button" name="1" value="${h.descripcion}" /></a>
											</c:if>
										</c:forEach>
										<!-- Volver al men? -->
										<c:if test="${m.padre=='0'}">
										<a href="<%=basePath%>v3/util/index.jsp"><button class="col-100 button button-danger"><i class="fa fa-laptop fa-2x" ></i></button></a>
										</c:if>
										<c:if test="${m.padre!='0'}">
										<a href="<%=basePath%>MenuMob.do?sec=${m.padre}"><input class="col-100 button button-danger" type="button" name="1"	value="Volver" /></a>
										</c:if>
									</c:if>
								</c:if>
						</c:forEach>
					</div>
				</div>
			</c:if>
	</div>
</body>
</c:if>
</html>	       
