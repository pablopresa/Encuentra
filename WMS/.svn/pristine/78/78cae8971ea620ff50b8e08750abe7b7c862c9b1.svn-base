<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
           <nav class="navbar-default navbar-side" role="navigation" style="posicion: absolute;">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
			<li class="text-center">
				<div style="height: 10px;"></div>
			</li>
			<li class="text-center"
				style="padding-bottom: 5px; padding-top: 5px;"><input
				type="button" onclick="sincro()" class="btn btn-danger"
				value="SINCRONIZAR"></li>

			<c:forEach var="m" items="${menu}">
					<c:if test="${m.visible}">
					
						<li>
							<a href="<%=basePath%>${m.href}" <c:if test="${m._blank}">target="_BLANK"</c:if>><i class="${m.icon} fa-3x"></i> ${m.descripcion}<span class="fa arrow"></span></a>
	                        <c:if test="${m.tieneHijos}">
	                        	<ul class="nav nav-second-level">
		                        <c:forEach var="h" items="${m.hijos}">
		                        	<c:if test="${h.visible}">
		                            <li>
		                                <a href="<%=basePath%>${h.href}" <c:if test="${h._blank}">target="_BLANK"</c:if>>${h.descripcion}<span class="fa arrow"></span></a>
		                                <c:if test="${h.tieneHijos}">
		                                <ul class="nav nav-third-level">
		                                <c:forEach var="n" items="${h.hijos}">
		                                	<c:if test="${n.visible}">
		                                    <li>
		                                        <a href="<%=basePath%>${n.href}" <c:if test="${n._blank}">target="_BLANK"</c:if>>${n.descripcion}</a>
		                                    </li>
		                                    </c:if> 
		                               	</c:forEach>
		                                </ul>
		                               </c:if>
		                            </li>
		                           	</c:if>
		                         </c:forEach>
		                         <c:if test="${m.descripcion=='Mantenimiento'}">
									<li class="text-center"
										style="padding-bottom: 5px; padding-top: 5px;"><label
										class="col-md-4 control-label" for="inventMode"
										class="col-md-4 control-label" style="color: white;">
											Modo Inventario&nbsp;</label> <c:if test="${uLogeado.inventario}">
											<input type="checkbox" checked data-toggle="toggle"
												name="inventMode" id="iMode" onchange="ModoInventario()">
										</c:if> <c:if test="${uLogeado.inventario==false}">
											<input type="checkbox" data-toggle="toggle" name="inventMode"
												id="iMode" onchange="ModoInventario()">
										</c:if></li>
								</c:if>
		                        </ul>
		                       
		                   </c:if>
		                   
	                      </li>
	                
	                </c:if>
					</c:forEach>
                </ul>
               
            </div>
            
        </nav>  
        
        <script>
        	function ModoInventario(){
        	
        		var xmlhttp = new XMLHttpRequest();
			    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			    xmlhttp.onreadystatechange = function() {
			        if (xmlhttp.readyState == XMLHttpRequest.DONE ) 
			        {
			           if (xmlhttp.status == 200) 
			           {
			           }
			           else if (xmlhttp.status == 400) 
			           {
			              alert('There was an error 400');
			           }
			           else 
			           {
			               alert('something else other than 200 was returned');
			           }
			        }
			    };
			    var check= document.getElementById('iMode').checked
			    if(!check){
				    if(confirm('Si desactiva el modo inventario, cuando lo vuelva a activar perdera los datos del conteo. Esta Seguro?')){
				    	xmlhttp.open("POST", "<%=basePath%>/ActivarModoInventario.do?activo="+check, true);
				    	xmlhttp.send();
				    	req='';
			    	}
			    	else{
			    	 location.reload();
			    	}
			    }
			    else{
			    	 xmlhttp.open("POST", "<%=basePath%>/ActivarModoInventario.do?activo="+check, true);
				     xmlhttp.send();
				     req='';
			    }
	
        	}
        </script>
        
        <script>
       		function sincro(){
       			if(confirm('Esta seguro que desea sincronizar Encuentra?')){
       				location.assign('<%=basePath%>/sincroManual.do');
       			}
       		}
       </script>
        
       
