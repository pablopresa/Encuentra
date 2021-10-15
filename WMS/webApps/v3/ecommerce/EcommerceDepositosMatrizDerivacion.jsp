<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
		
				<a href="#"	onClick="if(confirm('¿Seguro que desea actualizar los valores?'))
									{
                            						myFunction();
									}
									else {
										alert('Sin Cambios');
									}">
					<button id="btnBotonGuardarDatos" type="button" class="btn-success">
						<i class="material-icons">save</i>
					</button>
				</a>
				
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Ecommerce&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Matriz de Derivación</h3> 
                    	<h5 class="text-danger"> Algunos campos son editables al hacer clic</h5>   
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             <h5>Comportamiento por depósito</h5>
                        </div>
                      <form id="formParametros" action="<%=basePath%>InitEcommerceMatrizDerivacion.do?init=false">
                        <div class="card-body">
                             <div class="table-responsive">
							    <table class="table table-striped table-bordered table-hover" id="encuentra-default">
							        <thead class="thead-dark">
                                        <tr>
											<th class="text-center">Número</th>
											<th class="text-center">Nombre</th>
											<th class="text-center">Prioridad</th>
											<th class="text-center">Prepara Pickup</th>
											<th class="text-center">Prepara Delivery</th>
											<th class="text-center">Prepara Envio CD</th>
											<th class="text-center">Grupo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="e" items="${depositosMatrizDeriv}">
											<tr class="gradeD">												
												<td class="text-center"><c:out value="${e.id}"></c:out></td>
												<td class="text-center"><c:out value="${e.nombre}"></c:out></td>
												<td class="text-center" contenteditable='true'><c:out value="${e.parametros.prioridad}"></c:out></td>
												<td class="selectEditable text-center"><c:if test="${e.parametros.preparaPickup!='true'}">NO</c:if><c:if test="${e.parametros.preparaPickup=='true'}">SI</c:if></td>
												<td class="selectEditable text-center"><c:if test="${e.parametros.preparaDelivery!='true'}">NO</c:if><c:if test="${e.parametros.preparaDelivery=='true'}">SI</c:if></td>
												<td class="selectEditable text-center"><c:if test="${e.parametros.preparaEnvioCD!='true'}">NO</c:if><c:if test="${e.parametros.preparaEnvioCD=='true'}">SI</c:if></td>
												<td class="text-center" contenteditable='true'><c:out value="${e.parametros.idGrupo}"></c:out></td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                      </form>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
          <script type="text/javascript">
          
          <!-- Transforma tds en inputs -->
          
          $('td').on('click', function() {
              var $this = $(this);
              //pregunto si es editable
              var value = $(this).attr('contenteditable');
              if (value == "true") {
	              var $input = $('<input>', {
	                  value: $this.text(),
	                  type: 'text',
	                  blur: function() {
	                     $this.text(this.value);
	                  },
	                  keyup: function(e) {
	                     if (e.which === 13) $input.blur();
	                  }
	              }).appendTo( $this.empty() ).focus();
              }
          });
          
          <!-- Transforma tds en selects -->
          
          (function() {
              $("#encuentra-default").on("click", ".selectEditable", function() {
                var $this = $(this),
                    current = $this.text(),
                    select;
                if ($this.find("select").length) {
                  return;
                }
                select = $(
                  '<select>' +
                  '<option>NO</option>' +
                  '<option>SI</option>' +
                  '</select>'
                );
                $this.html(select);
                select.val(current).focus();
              });
              $("#encuentra-default").on("blur", ".selectEditable select", function() {
                var $this = $(this);
                $this.closest('td').text($this.val());
              });
            })();
          
          function sendReqConsulta(parametros)
	     	{
	 		    var xmlhttp = new XMLHttpRequest();
	 		    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	 		    xmlhttp.onreadystatechange = function() {
	 		        if (xmlhttp.readyState == XMLHttpRequest.DONE ) 
	 		        {
	 		           if (xmlhttp.status == 400) 
	 		           {
	 		              alert('There was an error 400');
	 		           }
	 		        }
	 		    };
	 			
	 		    xmlhttp.open("POST", "<%=basePath%>/InitEcommerceMatrizDerivacion.do?init=false&parametrosDeposSet="+encodeURIComponent(parametros), true);
	 		    xmlhttp.send();
	 		    req='';
	 		}
          
          function devolverBooleano(entrada)
          {
        	  if(entrada == 'SI')
        		  return 'true';
        	  
        	  return 'false';
          }
          
          function myFunction() {
	        	var miTabla = document.getElementById('encuentra-default');
	        	
	        	//Creo mi arreglo de datos
	        	let datosTabla =[];
	        	
	        	// LOOP THROUGH EACH ROW OF THE TABLE AFTER HEADER.
	            for (i = 1; i < miTabla.rows.length; i++) {
	            	
	            	// obtengo las celdas de la fila actual
	                var objCells = miTabla.rows.item(i).cells;
	            	
	            	let datos = {
	   					  "numero": objCells.item(0).innerText,
	   					  "nombre": objCells.item(1).innerText,
	   					  "prioridad": objCells.item(2).innerText,
	   					  "preparaPickup": devolverBooleano(objCells.item(3).innerText),
	   					  "preparaDelivery": devolverBooleano(objCells.item(4).innerText),
	   					  "preparaEnvioCD": devolverBooleano(objCells.item(5).innerText),
	   					  "grupo": objCells.item(6).innerText
	   					 };
	            	datosTabla.push(datos);
	            }
	            console.log(JSON.stringify(datosTabla, null, 4));
	      		var myJSON = JSON.stringify(datosTabla)
	      		sendReqConsulta(myJSON);
           }
      		<!--
          function myFunction2() {
          		var myTable = $("#encuentra-default").DataTable();
        		  
        		//So, to get all data do:
        		var form_data  = myTable.rows().data();
        		
        		var f = form_data;
        		
        		let datosTabla =[];
        		
        		for(var i=0 ; f.length>i;i++){
       			  let datos = {
       					  "numero": f[i][0],
       					  "nombre": f[i][1],
       					  "prioridad": f[i][2],
       					  "preparaPickup": f[i][3],
       					  "preparaDelivery": f[i][4],
       					  "preparaEnvioCD": f[i][5],
       					  "grupo": f[i][6]
       					 };
       			  datosTabla.push(datos);
        		 
        		}
        		console.log(JSON.stringify(datosTabla, null, 4));
        		var myJSON = JSON.stringify(datosTabla)
        		sendReqConsulta(myJSON);
            }
      		-->
		  </script>     
                
                
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
