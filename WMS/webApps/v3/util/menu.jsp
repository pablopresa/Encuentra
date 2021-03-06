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
    <style>
        .datepicker>.datepicker-days 
        {
            display: block;
        }

        ol.linenums {
            margin: 0 0 0 -8px;
        }
        
        .custom-file-input ~ .custom-file-label::after {
		    content: "Elegir";
		}
        .btn-toolbar {
            margin: 0 auto;
        }
        #btnBotonGuardarDatos {
        	position: fixed;
			bottom: 50%;
			right: 0px;
			z-index: 99;
			font-size: 18px;
			border: none;
			outline: none;
			color: white;
			cursor: pointer;
			padding: 15px;
			border-radius: 4px;
        	box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
        }
    </style>


</head>
<body  <c:if test="${colapsar!=null}"> class="bg-white"</c:if>>
<c:if test="${sinMenu!='yes'}">
    <!-- [ Pre-loader ] start -->
    <div class="loader-bg">
        <div class="loader-track">
            <div class="loader-fill"></div>
        </div>
    </div>
    <!-- [ Pre-loader ] End -->

    <!-- [ navigation menu ] start -->
    
    
    
    <nav class="pcoded-navbar <c:if test="${colapsar!=null}">navbar-collapsed</c:if>">
        <div class="navbar-wrapper">
            <div class="navbar-brand header-logo">
                <a href="<%=basePath%>v3/util/index.jsp" class="b-brand">
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
							<li data-username="${m.tags}" class="nav-item pcoded-hasmenu">
								
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
                    
                </ul>
            </div>
        </div>
    </nav>
    <!-- [ navigation menu ] end -->



    <!-- [ Header ] start -->
    <header class="navbar pcoded-header navbar-expand-lg navbar-light">
        <div class="m-header">
            <a class="mobile-menu" id="mobile-collapse1" href="#!"><span></span></a>
            <a href="<%=basePath%>v3/util/index.jsp" class="b-brand">
               <div class="b-bg">
               	   <div style="font-size: 21px;">
                  		<i class="feather icon-package"></i>
                   </div>
               </div>
               <span class="b-title">Encuentra</span>
           </a>
        </div>
        <a class="mobile-menu" id="mobile-header" href="#!">
            <i class="feather icon-more-horizontal"></i>
        </a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li><a href="#!" class="full-screen" onclick="javascript:toggleFullScreen()"><i class="feather icon-maximize"></i></a></li>
                <c:if test="${uLogeado.perfil==1}">
	                <li class="nav-item dropdown">
	                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">Administrar</a>
	                    <ul class="dropdown-menu">
	                        <li class="text-center"
								style="padding-bottom: 5px; padding-top: 5px;"><button
								class="btn-rounded btn-danger sweet-sincro" id="btnSincronizar">Sincronizar&nbsp;</button></li>
							
							<li class="text-center"
											style="padding-bottom: 5px; padding-top: 5px;"><label for="inventMode"
											class="col-md-4 control-label" style="color: rgb(63,77,103); padding-right: 100px;">
												Modo Inventario&nbsp;</label> 
								<c:if test="${uLogeado.inventario}">
									<div class="switch d-inline m-r-10">
										<input type="checkbox" name="inventMode" id="iMode" onchange="ModoInventario()" value="1"
											checked> <label for="iMode" class="cr"></label>
									</div>
									
								</c:if> <c:if test="${uLogeado.inventario==false}">
									<div class="switch d-inline m-r-10">
										<input type="checkbox" name="inventMode" id="iMode" onchange="ModoInventario()"> 
										<label for="iMode" class="cr"></label>
									</div>
								</c:if>
							</li>
	                    </ul>
	                </li>
                </c:if>
                
                
                <li class="nav-item">
                    <div class="main-search">
                        <div class="input-group">
                            <input type="text" id="m-search" class="form-control" placeholder="Buscar en men? . . .">
                            <a href="#!" class="input-group-append search-close">
                                <i class="feather icon-x input-group-text"></i>
                            </a>
                            <span class="input-group-append search-btn btn btn-primary">
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
                                <h6 class="d-inline-block m-b-0">Notificaciones</h6>
                                <div class="float-right">
                                    <a href="#!" class="m-r-10">Marcar leido</a>
                                    <a href="#!">limpiar</a>
                                </div>
                            </div>
                            <ul class="noti-body">
                                <li class="n-title">
                                    <p class="m-b-0">Pr?ximamente</p>
                                </li>
                                <!--
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
                                -->
                            </ul>
                            <div class="noti-footer">
                                <a href="#!">show all</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li><a href="#!" class="displayChatbox"><i class="icon feather icon-package"></i></a></li>
                <li>
                    <div class="dropdown drp-user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon feather icon-settings"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right profile-notification">
                            <div class="pro-head">
                                <img src="<%=basePath%>v3/assets/images/user/avatar-2.jpg" class="img-radius" alt="User-Profile-Image">
                                <span>${uLogeado.nick}&nbsp;</span>
                                <a href="<%=basePath%>LogoutEncuentra.do" class="dud-logout" title="Logout">
                                    <i class="feather icon-log-out"></i>
                                </a>
                            </div>
                            <ul class="pro-body">
                                <li><a class="dropdown-item md-trigger" data-modal="modal-Equipo"><i class="feather icon-monitor"></i>Equipo <span class="badge badge-pill badge-secondary" id="li_equipo">${uLogeado.idEquipo}</span></a></li>
                                <li><a class="dropdown-item md-trigger" data-modal="modal-Depo"><i class="feather icon-home"></i>Dep?sito <span class="badge badge-pill badge-secondary" id="li_depo">${uLogeado.deposito}</span></a></li>
                                <li><a class="dropdown-item md-trigger" data-modal="modal-Pass"><i class="feather icon-user"></i>Cambiar Contrase?a</a></li>
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
                    <div class="main-body">
                        <div class="page-wrapper">
                            <!-- [ Main Content ] start -->
     </c:if>                 
