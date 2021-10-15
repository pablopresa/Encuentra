<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="es">

<head>
    <title>Encuentra</title>
    <!-- Favicon icon -->
    <link rel="icon" href="<%=basePath%>/imagenes/favicon.png">
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
    <meta name="description" content="Datta Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs."/>
    <meta name="keywords" content="admin templates, bootstrap admin templates, bootstrap 4, dashboard, dashboard templets, sass admin templets, html admin templates, responsive, bootstrap admin templates free download,premium bootstrap admin templates, datta able, datta able bootstrap admin template">
    <meta name="author" content="Codedthemes" />

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

</head>

<body>
    <!-- [ Pre-loader ] start -->
    <!--
    <div class="loader-bg">
        <div class="loader-track">
            <div class="loader-fill"></div>
        </div>
    </div>
    -->
    <!-- [ Pre-loader ] End -->

    <!-- [ navigation menu ] start -->
    
    <nav class="pcoded-navbar menupos-static navbar-dark active-red brand-red icon-colored active-red">
        <div class="navbar-wrapper">
            <div class="navbar-brand header-logo">
                <a href="index.html" class="b-brand">
                    <div class="b-bg">
                        <i class="feather icon-package"></i>
                    </div>
                    <span class="b-title">Encuentra</span>
                </a>
                <a class="mobile-menu" id="mobile-collapse" href="#!"><span></span></a>
            </div>
            <div class="navbar-content scroll-div">
                <ul class="nav pcoded-inner-navbar">

                    <li data-username="dashboard Default Ecommerce CRM Analytics Crypto Project" class="nav-item pcoded-hasmenu active pcoded-trigger">
                        <a href="#!" class="nav-link"><span class="pcoded-micon"><i class="feather icon-home"></i></span><span class="pcoded-mtext">Dashboard</span></a>
                        <ul class="pcoded-submenu">
                            <li class=""><a href="index.html" class="">Default</a></li>
                            <li class=""><a href="dashboard-ecommerce.html" class="">Ecommerce</a></li>
                            <li class=""><a href="dashboard-crm.html" class="">CRM</a></li>
                            <li class=""><a href="dashboard-analytics.html" class="">Analytics</a></li>
                            <li class=""><a href="dashboard-crypto.html" class="">Crypto<span class="pcoded-badge label label-danger">NEW</span></a></li>
                            <li class=""><a href="dashboard-project.html" class="">Project</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- [ navigation menu ] end -->

    <!-- [ Header ] start -->
    <header class="navbar pcoded-header navbar-expand-lg navbar-light header-dark">
        <div class="m-header">
            <a class="mobile-menu" id="mobile-collapse1" href="#!"><span></span></a>
            <a href="index.html" class="b-brand">
               <div class="b-bg">
                   <i class="feather icon-trending-up"></i>
               </div>
               <span class="b-title">Datta Able</span>
           </a>
        </div>
        <a class="mobile-menu" id="mobile-header" href="#!">
            <i class="feather icon-more-horizontal"></i>
        </a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li><a href="#!" class="full-screen" onclick="javascript:toggleFullScreen()"><i class="feather icon-maximize"></i></a></li>
                <li class="nav-item">
                    <div class="main-search">
                        <div class="input-group">
                            <input type="text" id="m-search" class="form-control" placeholder="Search . . .">
                            <a href="#!" class="input-group-append search-close">
                                <i class="feather icon-x input-group-text"></i>
                            </a>
                            <span class="input-group-append search-btn btn btn-info">
                                <i class="feather icon-search input-group-text"></i>
                            </span>
                        </div>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li>
                    <div class="dropdown">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown"><i class="icon feather icon-bell"></i></a>
                        <div class="dropdown-menu dropdown-menu-right notification">
                            <div class="noti-head">
                                <h6 class="d-inline-block m-b-0">Notifications</h6>
                                <div class="float-right">
                                    <a href="#!" class="m-r-10">mark as read</a>
                                    <a href="#!">clear all</a>
                                </div>
                            </div>
                            <ul class="noti-body">
                                <li class="n-title">
                                    <p class="m-b-0">NEW</p>
                                </li>
                                <li class="notification">
                                    <div class="media">
                                        <img class="img-radius" src="<%=basePath%>/v3/assets/images/user/avatar-1.jpg" alt="Generic placeholder image">
                                        <div class="media-body">
                                            <p><strong>John Doe</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i>30 min</span></p>
                                            <p>New ticket Added</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="n-title">
                                    <p class="m-b-0">EARLIER</p>
                                </li>
                                <li class="notification">
                                    <div class="media">
                                        <img class="img-radius" src="<%=basePath%>/v3/assets/images/user/avatar-2.jpg" alt="Generic placeholder image">
                                        <div class="media-body">
                                            <p><strong>Joseph William</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i>30 min</span></p>
                                            <p>Prchace New Theme and make payment</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="notification">
                                    <div class="media">
                                        <img class="img-radius" src="<%=basePath%>/v3/assets/images/user/avatar-3.jpg" alt="Generic placeholder image">
                                        <div class="media-body">
                                            <p><strong>Sara Soudein</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i>30 min</span></p>
                                            <p>currently login</p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <div class="noti-footer">
                                <a href="#!">show all</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li><a href="#!" class="displayChatbox"><i class="icon feather icon-mail"></i></a></li>
                <li>
                    <div class="dropdown drp-user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon feather icon-settings"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right profile-notification">
                            <div class="pro-head" style="background-color: #ff5252">
                                <img src="assets/images/user/avatar-1.jpg" class="img-radius" alt="User-Profile-Image">
                                <span>${uLogeado.nick}&nbsp;</span>
                                <a href="auth-signin.html" class="dud-logout" title="Logout">
                                    <i class="feather icon-log-out"></i>
                                </a>
                            </div>
                            <ul class="pro-body">
                                <li><a href="<%=basePath%>darEquiposDeposito.do?mob=0" class="dropdown-item"><i class="feather icon-monitor"></i>Equipo <span class="badge badge-pill badge-secondary">${uLogeado.idEquipo}</span></a></li>
                                <li><a href="<%=basePath%>darDepositosE.do?mob=0" class="dropdown-item"><i class="feather icon-home"></i>Depósito <span class="badge badge-pill badge-secondary">${uLogeado.deposito}</span></a></li>
                                <li><a href="<%=basePath%>v2/cambiarContrasena.jsp" class="dropdown-item"><i class="feather icon-user"></i>Cambiar Contraseña</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </header>
    <!-- [ Header ] end -->

    <!-- Required Js -->
    <script src="<%=basePath%>v3/assets/js/vendor-all.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/pcoded.js"></script>
    
    <!-- amchart js -->
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/amcharts.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/gauge.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/serial.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/light.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/pie.min.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/ammap.min.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/usaLow.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/radar.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/amchart/js/worldLow.js"></script>
    <!-- notification Js -->
    <script src="<%=basePath%>v3/assets/plugins/notification/js/bootstrap-growl.min.js"></script>

    <!-- dashboard-custom js -->
    <script src="<%=basePath%>v3/assets/js/pages/dashboard-custom.js"></script>

</body>

</html>
