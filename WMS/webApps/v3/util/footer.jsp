<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="md-modal md-effect-1" id="modal-Equipo">
			        <div class="md-content">
			            <h3 class="theme-bg2">Cambiar equipo de impresi?n</h3>
			            <div class="row">
				            <div class="col-md-2"></div>
				            <div class="col-md-8">
				            
								<div class="form-group">
									<label class="col-md-6 control-label" for="p1">N? Equipo Actual:</label>
									<div class="col-md-12">
								  		<input class="form-control md-12" readonly="readonly" name="actual" id="actual" value="${uLogeado.idEquipo}"/>
									</div>
								</div>												
							
								<div class="form-group">
									<label class="col-md-4 control-label" for="perfil">Equipo:</label>
									<div class="col-md-12">
								  		<select class="form-control md-12" name="equipo" id="equipo" >
									  		<c:forEach var="d" items="${equipos}">
									  			<c:if test="${d.id==uLogeado.idEquipo}">
									  				<option value="${d.id}" selected="selected">${d.descripcion}</option>
									  			</c:if>
									  			<c:if test="${d.id!=uLogeado.idEquipo}">
									  				<option value="${d.id}">${d.descripcion}</option>
									  			</c:if>
									  		</c:forEach>
									  	</select>
								  	</div>
								</div>					
							
								<div class="row">											
									<button class="btn btn-primary md-close" >Cerrar</button>
									<button onclick="sendEquipo()" class="btn btn-success md-close">Confirmar</button>
								</div>
										
				                <!--<a class="btn btn-primary md-close" onclick="closeModal('modal-${d.numeroDoc}')" href="#">Cerrar</a>  -->
				                
				            </div>
				            <div class="col-md-2"></div>
				    	</div>        
			        </div>
			    </div>
			    
			    
			    <div class="md-modal md-effect-1" id="modal-Depo">
			        <div class="md-content">
			            <h3 class="theme-bg2">Cambiar Dep?sito</h3>
			            <div class="row">
				            <div class="col-md-2"></div>
				            <div class="col-md-8">
				              
								<div class="form-group">
									<label class="col-md-6 control-label" for="p1">N? Dep?sito Actual:</label>
									<div class="col-md-12">
								  		<input class="form-control" readonly="readonly" name="actual" id="actualD" value="${uLogeado.deposito}"/>
									</div>
								</div>											
							
								<div class="form-group">
									<label class="col-md-4 control-label" for="perfil">Dep?sito:</label>
									<div class="col-md-12">
								  		<select class="form-control" name="deposito" id="deposito" >
									  		<c:forEach var="d" items="${depositoU}">
									  			<c:if test="${d.id==uLogeado.deposito}">
									  				<option value="${d.id}" selected="selected">${d.descripcion}</option>
									  			</c:if>
									  			<c:if test="${d.id!=uLogeado.deposito}">
									  				<option value="${d.id}">${d.descripcion}</option>
									  			</c:if>
									  		</c:forEach>
									  	</select>
								  	</div>
								</div>				
							
								<div class="row">											
									<button class="btn btn-primary md-close" >Cerrar</button>
									<button onclick="sendDepo()" class="btn btn-success md-close">Confirmar</button>
								</div>
									
				                <!--<a class="btn btn-primary md-close" onclick="closeModal('modal-${d.numeroDoc}')" href="#">Cerrar</a>  -->
				                
				            </div>
				            <div class="col-md-2"></div>
				    	</div>       
			        </div>
			    </div>
			    
			    
			    <div class="md-modal md-effect-1" id="modal-Pass">
			        <div class="md-content">
			             <h3 class="theme-bg2">Cambiar Contrase?a</h3>
			             
			            <div class="row">
				            <div class="col-md-2"></div>
				            <div class="col-md-8">
				            	<div class="form-group">									
									<label id="menErr" class="col-md-12 control-label" style="color: red;"></label>
								</div>	
				             
								<div class="form-group">
									<label class="col-md-6 control-label" for="p1">Password:</label>
									<div class="col-md-12">
								  		<input required class="form-control" name="p1" id="p1" value="${p1}" type="password"/>
									</div>
								</div>												
							
								<div class="form-group">
									<label class="col-md-6 control-label" for="p1">Repetir Password:</label>
									<div class="col-md-12">
								  		<input required class="form-control" name="p2" id="p2" value="${p2}" type="password"/>
									</div>
								</div>			
							
								<div class="row">											
									<button class="btn btn-primary md-close" >Cerrar</button>
									<button onclick="sendPass()" class="btn btn-success md-close">Confirmar</button>
								</div>
									
				                <!--<a class="btn btn-primary md-close" onclick="closeModal('modal-${d.numeroDoc}')" href="#">Cerrar</a>  -->
				                
				            </div>
				            <div class="col-md-2"></div>
				    	</div>        			            
			        </div>
			    </div>
			    
			    
			    
			    <div class="md-overlay" id="md-ov"></div>
			    
			    
			    
    <script>
    	
    	if(document.getElementsByClassName("arrow-togglable")!=null)
    	{
    		var elements = document.getElementsByClassName("arrow-togglable");
    	    var currentIndex = 0;
    	
    	    document.onkeydown = function(e) {
    	      switch (e.keyCode) {
    	        case 38:
    	          currentIndex = (currentIndex == 0) ? elements.length - 1 : --currentIndex;
    	          elements[currentIndex].select();
    	          elements[currentIndex].focus();
    	          elements[currentIndex].select();
    	          
    	          
    	          break;
    	        case 40:
    	          currentIndex = ((currentIndex + 1) == elements.length) ? 0 : ++currentIndex;
    	          elements[currentIndex].select();
    	          elements[currentIndex].focus();
    	          elements[currentIndex].select();
    	          

    	          break;
    	      }
    	    };
    	}
	    
    
    		
		function sendEquipo()
    	{
		    var v1 = document.getElementById("actual").value;
		    var v2 = document.getElementById("equipo").value;
		    
		    var params ={
							actual : v1,
							equipo : v2 
						};
						
			$.ajax({
						url : "<%=basePath%>cambioEquipo.do?mob=0",
						type : "post",
						dataType : "html",
						data : 
							params 

						
					}).then(function(data) {
						$("#modal-Equipo").removeClass("md-show");
						document.getElementById('li_equipo').innerHTML = params.equipo;
						document.getElementById('actual').value = params.equipo;
					});			
		} 
		
		function sendDepo()
    	{
		    var v1 = document.getElementById("actualD").value;
		    var v2 = document.getElementById("deposito").value;
		    
		    var params ={
							actual : v1,
							deposito : v2 
						};
						
			$.ajax({
						url : "<%=basePath%>cambioDeposito.do?mob=0",
						type : "post",
						dataType : "html",
						data : 
							params 

						
					}).then(function(data) {
						$("#modal-Depo").removeClass("md-show");
						document.getElementById('li_depo').innerHTML = params.deposito;
						document.getElementById('actualD').value = params.deposito;
						location.reload();
					});			
		} 
		
		function sendPass()
    	{
		    var v1 = document.getElementById("p1").value;
		    var v2 = document.getElementById("p2").value;
		    
		    var params ={
							p1 : v1,
							p2 : v2 
						};
						
			$.ajax({
						url : "<%=basePath%>cambioContrasena.do?mob=0",
						type : "post",
						dataType : "html",
						data : 
							params 

						
					}).then(function(data) {
						data = data.split('\n').join('');
						if(data===" "){
							$("#modal-Pass").removeClass("md-show");
			       		}
			       		else{
			       			document.getElementById('menErr').innerHTML = data;
			       		}
						
						
					});			
		} 
    
    </script>
    
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
				    if(confirm('Si desactiva el Modo Inventario, cuando lo vuelva a activar perdera los datos del conteo. Esta Seguro?')){
				    	xmlhttp.open("POST", "<%=basePath%>/ActivarModoInventario.do?activo="+check, true);
				    	xmlhttp.send();
				    	req='';
			    	}
			    	else{
			    	 location.reload();
			    	}
			    }
			    else{
			    	if(confirm('Esta seguro que desea activar el Modo Inventario')){
				    	 xmlhttp.open("POST", "<%=basePath%>/ActivarModoInventario.do?activo="+check, true);
					     xmlhttp.send();
					     req='';
			    	}
			    	else{
			    	 location.reload();
			    	}
			    	
			    }
	
        	}
        </script>
        
		<script type="text/javascript">
       		function sincro()
       		{
       			if(confirm('Esta seguro que desea Sincronizar Encuentra?'))
       			{
	       			  var btn = document.getElementById("btnSincronizar");
				      // disable button
				      btn.disabled = true; 
				      // add spinner to button
				      btn.innerHTML ='<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Sincronizando...';
			      
       				location.assign('<%=basePath%>/sincroManual.do');
       			}
       		}
       </script>
       
       <script>
       $('.sweet-sincro').on('click', function() {
        swal({
                title: "Atencion!",
                text: "Esta seguro que desea Sincronizar Encuentra?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete) {
                	var btn = document.getElementById("btnSincronizar");
				      // disable button
				      btn.disabled = true; 
				      // add spinner to button
				      btn.innerHTML ='<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Sincronizando...';
			      
     				location.assign('<%=basePath%>/sincroManual.do');
                } else {
                    
                }
            });
    });
       </script>

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
    
    <!-- datepicker js -->
    <script src="<%=basePath%>v3/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/pages/ac-datepicker.js"></script>
    
     <!-- datatable Js -->
    <script src="<%=basePath%>v3/assets/plugins/data-tables/js/datatables.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/pages/tbl-datatable-custom.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/data-tables/js/rowgroup.js"></script>
    
    
     <!-- select 2 Js -->
    <script src="<%=basePath%>v3/assets/plugins/select2/js/select2.full.min.js"></script>
 	
 	 <!-- modal-window-effects Js -->
    <script src="<%=basePath%>v3/assets/plugins/modal-window-effects/js/classie.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/modal-window-effects/js/modalEffects.js"></script>
    
    <!-- bootstrap-tagsinput-latest Js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.11.1/typeahead.bundle.min.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/bootstrap-tagsinput-latest/js/bootstrap-tagsinput.min.js"></script>
    <!-- bootstrap-maxlength Js -->
    <script src="<%=basePath%>v3/assets/plugins/bootstrap-maxlength/js/bootstrap-maxlength.min.js"></script>
     <!-- form-advance custom js -->
    <script src="<%=basePath%>v3/assets/js/pages/form-advance-custom.js"></script>
    <!-- Toolbar Js -->
    <script src="<%=basePath%>v3/assets/plugins/toolbar/js/jquery.toolbar.min.js"></script>
    <!-- sweet alert Js -->
    <script src="<%=basePath%>v3/assets/plugins/sweetalert/js/sweetalert.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/pages/ac-alert.js"></script>
    <!-- toolbar Js -->
    <script src="<%=basePath%>v3/assets/plugins/toolbar/js/jquery.toolbar.min.js"></script>    
    <script src="<%=basePath%>v3/assets/js/pages/ac-toolbar.js"></script>
    
    
    <c:remove var="menError" scope="session" />
</body>

</html>
