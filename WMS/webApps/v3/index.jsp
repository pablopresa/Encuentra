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
 


<!-- fontawesome icon -->
    <link rel="stylesheet" href="<%=basePath%>v3/assets/fonts/fontawesome/css/all.min.css">
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
    <div class="loader-bg">
        <div class="loader-track">
            <div class="loader-fill"></div>
        </div>
    </div>
    <!-- [ Pre-loader ] End -->

    <!-- [ navigation menu ] start -->
    <nav class="pcoded-navbar">
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
                    <li class="nav-item pcoded-menu-caption">
                        <label>WMS</label>
                    </li>
                    <c:forEach var="m" items="${menu}">
						<c:if test="${m.visible}">
							<li data-username="Menu levels Menu level 2.1 Menu level 2.2" class="nav-item pcoded-hasmenu">
								
		                        <a 
		                        	<c:choose><c:when test ="${fn:contains(m.href, '#!')}">href="${m.href}"</c:when><c:otherwise>href="<%=basePath%>${m.href}"</c:otherwise></c:choose>
		                        	<c:if test="${m._blank}">target="_BLANK"</c:if> class="nav-link">
		                        	<span class="pcoded-micon">
		                        		<i class="${m.icon}"></i>
		                        	</span>
		                        	<span class="pcoded-mtext"> ${m.descripcion}</span>
		                        </a>
		                        <c:if test="${m.tieneHijos}">
			                        <ul class="pcoded-submenu">
			                        	<c:forEach var="h" items="${m.hijos}">
		                        			<c:if test="${h.visible}">
		                        				<c:if test="${!h.tieneHijos}">
		                        					<li class=""><a href="<%=basePath%>${h.href}" <c:if test="${h._blank}">target="_BLANK"</c:if> class="">${h.descripcion}</a></li>
		                        				</c:if>
		                        				<c:if test="${h.tieneHijos}">
		                        					<li class="pcoded-hasmenu">
						                                <a 
						                                <c:choose><c:when test ="${fn:contains(h.href, '#!')}">href="${h.href}"</c:when><c:otherwise>href="<%=basePath%>${h.href}"</c:otherwise></c:choose> 
						                                class="">${h.descripcion}</a>
						                                <ul class="pcoded-submenu">
						                                	<c:forEach var="n" items="${h.hijos}">
		                                						<c:if test="${n.visible}">
						                                			<li class=""><a href="<%=basePath%>${n.href}" <c:if test="${n._blank}">target="_BLANK"</c:if> class="">${n.descripcion}</a></li>
						                                		</c:if>
						                                	</c:forEach>
						                                </ul>
						                            </li>				
		                        				</c:if>
		                        			</c:if>
			                            </c:forEach>
			                        </ul>
		                        </c:if>
		                    </li>
						</c:if>
					</c:forEach>
                    
                    
                    
                    <li class="nav-item pcoded-menu-caption">
                        <label>Soporte & feedback</label>
                    </li>
                    <li data-username="Documentation" class="nav-item"><a href="docs.html" class="nav-link" target="_blank"><span class="pcoded-micon"><i class="feather icon-book"></i></span><span class="pcoded-mtext">Documentation</span></a></li>
                    <li data-username="Need Support" class="nav-item"><a href="https://codedthemes.support-hub.io/" class="nav-link" target="_blank"><span class="pcoded-micon"><i class="feather icon-help-circle"></i></span><span class="pcoded-mtext">Need
                                support ?</span></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- [ navigation menu ] end -->



    <!-- [ Header ] start -->
    <header class="navbar pcoded-header navbar-expand-lg navbar-light">
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
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">Dropdown</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#!">Action</a></li>
                        <li><a class="dropdown-item" href="#!">Another action</a></li>
                        <li><a class="dropdown-item" href="#!">Something else here</a></li>
                    </ul>
                </li>
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
                                        <img class="img-radius" src="<%=basePath%>v3/assets/images/user/avatar-1.jpg" alt="Generic placeholder image">
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
                                        <img class="img-radius" src="<%=basePath%>v3/assets/images/user/avatar-2.jpg" alt="Generic placeholder image">
                                        <div class="media-body">
                                            <p><strong>Joseph William</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i>30 min</span></p>
                                            <p>Prchace New Theme and make payment</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="notification">
                                    <div class="media">
                                        <img class="img-radius" src="<%=basePath%>v3/assets/images/user/avatar-3.jpg" alt="Generic placeholder image">
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
                            <div class="pro-head">
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

    <!-- [ chat user list ] start -->
    <section class="header-user-list">
        <div class="h-list-header">
            <div class="input-group">
                <input type="text" id="search-friends" class="form-control" placeholder="Buscar Articulo . . .">
            </div>
        </div>
        <div class="h-list-body">
            <a href="#!" class="h-close-text"><i class="feather icon-chevrons-right"></i></a>
            <div class="main-friend-cont scroll-div">
                <div class="main-friend-list">
                   
                </div>
            </div>
        </div>
    </section>
    <!-- [ chat user list ] end -->

    <!-- [ chat message ] start -->
    
    <!-- [ chat message ] end -->

    <!-- [ Main Content ] start -->
    <div class="pcoded-main-container">
        <div class="pcoded-wrapper">
            <div class="pcoded-content">
                <div class="pcoded-inner-content">
                    <!-- [ breadcrumb ] start -->

                    <!-- [ breadcrumb ] end -->
                    <div class="main-body">
                        <div class="page-wrapper">
                            <!-- [ Main Content ] start -->
                            <div class="row">
                               
                            </div>
                            <!-- [ Main Content ] end -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- [ Main Content ] end -->

    <!-- Warning Section start -->
    <!-- Older IE warning message -->
    <!--[if lt IE 11]>
        <div class="ie-warning">
            <h1>Warning!!</h1>
            <p>You are using an outdated version of Internet Explorer, please upgrade
               <br/>to any of the following web browsers to access this website.
            </p>
            <div class="iew-container">
                <ul class="iew-download">
                    <li>
                        <a href="http://www.google.com/chrome/">
                            <img src="<%=basePath%>v3/assets/images/browser/chrome.png" alt="Chrome">
                            <div>Chrome</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.mozilla.org/en-US/firefox/new/">
                            <img src="<%=basePath%>v3/assets/images/browser/firefox.png" alt="Firefox">
                            <div>Firefox</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.opera.com">
                            <img src="<%=basePath%>v3/assets/images/browser/opera.png" alt="Opera">
                            <div>Opera</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.apple.com/safari/">
                            <img src="<%=basePath%>v3/assets/images/browser/safari.png" alt="Safari">
                            <div>Safari</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                            <img src="<%=basePath%>v3/assets/images/browser/ie.png" alt="">
                            <div>IE (11 & above)</div>
                        </a>
                    </li>
                </ul>
            </div>
            <p>Sorry for the inconvenience!</p>
        </div>
    <![endif]-->
    <!-- Warning Section Ends -->

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
