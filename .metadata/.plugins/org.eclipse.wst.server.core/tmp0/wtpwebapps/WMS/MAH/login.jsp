<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Encuentra</title>
    <!-- Favicon icon -->
    <link rel="shortcut icon" href="<%=basePath%>/v3/assets/images/encuentra_favicon.ico">
    <!-- HTML5 Shim and Respond.js IE10 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 10]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="CodedThemes" />

    <!-- fontawesome icon -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/fonts/fontawesome/css/fontawesome-all.min.css">
    <!-- animation css -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/plugins/animation/css/animate.min.css">
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
<script type="text/javascript">

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  
}

</script>

</head>

<style>
body {
  background-image: url('<%=basePath%>v3/assets/login-background.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed; 
  background-size: cover;
}
</style>

<body onload="setCookie('empresa', 'MAH', 365);">
    <div class="auth-wrapper">
        <div class="auth-content rounded">
            <div class="card rounded">
	                <div class="card-body text-center border border-success rounded">
	                    <div class="mb-4">
	                        <i class="feather icon-unlock auth-icon"></i>
	                    </div>
	                    <h3 class="mb-4">Login</h3>
	                    <form action="<%=basePath%>/loginEncuentra.do" method="post">
		                    <div class="input-group mb-3">
		                        <input type="text" class="form-control" placeholder="Usuario" name="nombreUsuario">
		                    </div>
		                    <div class="input-group mb-4">
		                        <input type="password" class="form-control" placeholder="Contraseña" name="password">
		                    </div>
		                    <input class="d-none" type="text" maxlength="2" name="pc" value="1"><br/>
		                     <input class="d-none" type="text" maxlength="2" name="idEmpresa" value="7"><br/>
		                    <input type="submit" value="Entrar" class="btn btn-success shadow-2 mb-4">
		                    <p style="color: red;">${mensaje}</p>
	                    </form>
	                </div>
            </div>
        </div>
    </div>

    <!-- Required Js -->
     <script src="<%=basePath%>v3/assets/js/vendor-all.min.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/pcoded.min.js"></script>
    
<%request.getSession(true).invalidate(); %>
</body>
</html>