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
				<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Procesar Ventas</h3>   
                        <h6>Procesamiento de movimientos para reabastecer tiendas</h6>
                    </div>
                </div>
                <br>
            	<div class="row">		
            		
                    <div class="col-md-12 col-sm-12 col-xs-12">            
                                            
                    <div class="card" >
                        <div class="card-header">
                        	<h5>
	                            <i class="fa fa-comments fa-fw"></i>
	                            Procesamiento de Informacion
	                            <div class="btn-group pull-right">
                            </h5> 
                        </div>
                        <div class="card-body">
                            <p>
                   		<a href="<%=basePath%>SincronizarVentas.do?central=1" onclick="this.addEventListener('click', clickStopper, false);" class="btn btn-danger btn-lg">Procesar</a>
                   
                   		
                   	</p>
                   	<br>
                   	<div class="progress mb-4">
                                                <div class="progress-bar progress-bar-striped bg-info progress-bar-animated" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>
                                            </div>
                        </div>
                   </div>
                                            
                    <div class="chat-panel card chat-boder chat-panel-head" >
                        <div class="card-header">
                        	<h5>
	                            <i class="fa fa-comments fa-fw"></i>
	                            Mensajes del Proceso
	                            <div class="btn-group pull-right">
                            </h5> 
                        </div>
                        <div class="card-body" id="panel">
                            <ul class="chat-box" id="resultado">
                                <li class="left clearfix">
                                    
                                </li>
                                
                            </ul>
                        </div>
                   </div>

                        

                    </div>
                    
                </div>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
		<script>
		function createCORSRequest(method, url) {
		  var xhr = new XMLHttpRequest();
		  if ("withCredentials" in xhr) {
		    // XHR for Chrome/Firefox/Opera/Safari.
		    xhr.open(method, url, true);
		  } else if (typeof XDomainRequest != "undefined") {
		    // XDomainRequest for IE.
		    xhr = new XDomainRequest();
		    xhr.open(method, url);
		  } else {
		    // CORS not supported.
		    xhr = null;
		  }
		  return xhr;
		}
		
		
		
			/*setInterval(function() 
			{
	         	js_fun();
	        }, 1000);*/

			function js_fun() 
			{
	
				var json;
				var url = '<%=basePath%>JSONLogSincRep.do?idSinc=${idSinc}';
			 	var xhr = createCORSRequest('GET', url);
				if (!xhr) 
				{
					alert('CORS not supported');
					return;
				}
				// Response handlers.
				xhr.onload = function() 
				{
					var text =xhr.responseText;
					recorrer2(text);
				};
				
				xhr.onerror = function() 
				{
					alert('Atención: parece que el Servidor no está disponible');
				};
				
				xhr.send();
				
			}
		
			function recorrer2(text) 
			{
				var arr = JSON.parse(text);
				var i;
				for(i = 0; i < arr.length; i++) 
				{
					//hay que actualizar el avance
					
					//var bars = document.getElementsByClassName("progress-bar");
					//bars[i].style.width = arr[i].Porcent+"%";
					//bars[i].innerHTML = arr[i].Porcent+"%";
					var clase = "";
					var icon = "";
					if(arr[i].evento.indexOf("ERROR") > -1)
					{
						clase = "btn btn-danger btn-circle";
						icon = "fa fa-exclamation"
						
					}
					else
					{
						clase = "btn btn-success btn-circle";
						icon = "fa fa-check"
					}
					 var li = document.createElement("li");
					 li.className = "left clearfix";
     				li.innerHTML = "<span class='chat-img pull-left'> "+
                                   "     <button type='button' class='"+clase+"'><i class='"+icon+"'></i></button>"+
                                   " </span> "+
                                    "<div class='chat-body'> "+                                        
                                    "        <strong >encuentra</strong> "+
                                    "        <small class='pull-right text-muted'> "+
                                    "            <i class='fa fa-clock-o fa-fw'></i>"+arr[i].fecha+
                                    "        </small> "+                                      
                                    "    <p> "+arr[i].evento+"    </p> "+
                                    "</div>"
     				

					var bars = document.getElementsByClassName("progress-bar");
					bars[i].style.width = arr[i].Porcent+"%";
					bars[i].innerHTML = arr[i].Porcent+"%";
     
    				document.getElementById("resultado").appendChild(li);
					var element = document.getElementById("panel");
    				element.scrollTop = element.scrollHeight;
					
				}	
			}
		
		

				  function clickStopper(e)
				  {
				    alert("El proceso ya comenzó"); // you can remove this line
				    e.preventDefault(); // equivalent to 'return false'
				  }


		
		
		</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
