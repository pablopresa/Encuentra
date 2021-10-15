<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Encuentra - WMS</title>
    <link rel="shortcut icon" href="<%=basePath%>/v3/assets/images/encuentra_favicon.ico">
    <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 11]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
 
	

<!-- fontawesome icon -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/fonts/fontawesome/css/all.min.css">
    <!-- animation css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/animation/css/animate.min.css">
    <!-- modal-window-effects css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/modal-window-effects/css/md-modal.css">
     <!-- bootstrap-tagsinput-latest css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/bootstrap-tagsinput-latest/css/bootstrap-tagsinput.css">
    <!-- vendor css -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/fonts/feather/css/feather.css">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/jquery-scrollbar/css/perfect-scrollbar.css">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/fonts/datta/datta-icon.css">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/animation/css/animate.min.css">
    <!-- notification css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/notification/css/notification.min.css">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/css/style.css">
    <!-- Bootstrap datetimepicker css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datepicker3.min.css">
    
    <!-- select 2 css-->
    <link href="<%=basePath%>v3/assets/plugins/select2/css/select2.min.css" rel="stylesheet">
    
     <!-- data tables css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/data-tables/css/datatables.min.css">
    <!-- daterangepicker -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
     <!-- tree view -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/bootstrap-treeview/css/bootstrap-treeview.css">
    <!-- Toolbar css -->
	<link href="<%=basePath%>v3/assets/plugins/toolbar/css/jquery.toolbar.css" rel="stylesheet">
	<!-- Js Jquery se debe importar antes -->
	<script src="<%=basePath%>v3/assets/plugins/jquery/js/jquery.min.js"></script>
	
	<link href='https://fonts.googleapis.com/css?family=Libre Barcode 39 Text' rel='stylesheet'>

	<!-- Script para cuadro de datepicker -->
	<script>
        var page = {
            bootstrap: 3
        };

        function swap_bs() {
            page.bootstrap = 3;
        }
    </script>
</head>

 
  <body>
  <div class="row" >
            <div class="col-md-12">
                <div class="card">
					<div class="card-header">
						<h5>Unidades Expedidas</h5>
					</div>
				  <div style="margin: 50px;">
				   	${matriz}
				   </div>
			   </div>
		   </div>
   </div>
  </body>

</html>
