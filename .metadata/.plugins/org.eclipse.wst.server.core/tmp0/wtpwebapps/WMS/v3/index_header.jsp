<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>encuentra - FORUS</title>
    
     <link rel="icon" href="<%=basePath%>/imagenes/favicon.png">
    
    
	<!-- BOOTSTRAP STYLES-->
    <link href="<%=basePath%>v3/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="<%=basePath%>v3/assets/css/font-awesome.css" rel="stylesheet" >
    <!-- MORRIS CHART STYLES-->
    <link href="<%=basePath%>v3/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- CHOSEN -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
    
    <!-- CUSTOM STYLES-->
    <link href="<%=basePath%>v3/assets/css/custom.css" rel="stylesheet">
    <link href="<%=basePath%>v3/assets/css/style.css" rel="stylesheet">
    
	<link href="<%=basePath%>v3/assets/Picker/assets/daterangepicker.css" rel="stylesheet">
    
    <script type="text/javascript" src="<%=basePath%>v3/assets/Picker/assets/jquery-2.js"></script>
    
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-bs/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
    
    
    <c:if test="${uLogeado==null}">
     <script type="text/javascript">
            window.location.href = "<%=basePath%>v3/login.jsp"
      </script>
    </c:if>
     <!-- GOOGLE FONTS-->
   
   <style type="text/css">
   
		#dataTables-inventTTL tbody {
		  font-size: 22pt;
		  font-weight: bolder;
		}   
		@font-face {
		  font-family: 'Open Sans';
		  font-style: normal;
		  font-weight: 400;
		  src: local('Open Sans'), local('OpenSans'), url(<%=basePath%>v3/assets/fonts/cJZKeOuBrn4kERxqtaUH3VtXRa8TVwTICgirnJhmVJw.woff2) format('woff2');
		}
		
		html, body {
		  height: 100%;
		}
		.wrapper {
		  min-height: 100%;
		  /* equal to footer height */
		  margin-bottom: -100px; 
		}
		.wrapper:after {
		  content: "";
		  display: block;
		}
		.site-footer, .wrapper:after {
		  height: 100px; 
		}
		.site-footer {
		  background: #DDD;
		  color: black;
		  text-align: center;
		}
		
   </style>
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=basePath%>loginEncuentra.do?pc=1">encuentra</a> 
            </div>
            
  <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> ${uLogeado.nick}&nbsp; <a href="<%=basePath%>darDepositosE.do?mob=0" class="btn btn-success square-btn-adjust">En Deposito ${uLogeado.deposito}</a>&nbsp;<a href="<%=basePath%>darEquiposDeposito.do?mob=0" class="btn btn-info square-btn-adjust">Equipo ${uLogeado.idEquipo}</a>&nbsp;<a href="<%=basePath%>v3/login.jsp" class="btn btn-danger square-btn-adjust">Salir</a> </div>
        </nav>     
        <!-- /. NAV TOP  -->
		
