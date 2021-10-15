<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="es">

<head>
<title>Notas de versi�n</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description"
	content="Datta Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
<meta name="keywords"
	content="admin templates, bootstrap admin templates, bootstrap 4, dashboard, dashboard templets, sass admin templets, html admin templates, responsive, bootstrap admin templates free download,premium bootstrap admin templates, datta able, datta able bootstrap admin template">
<meta name="author" content="Codedthemes" />

<!-- Favicon icon -->
<link rel="icon" href="<%=basePath%>v3/assets/images/favicon.ico"
	type="image/x-icon">
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="<%=basePath%>v3/assets/fonts/fontawesome/css/fontawesome-all.min.css">
<!-- animation css -->
<link rel="stylesheet"
	href="<%=basePath%>v3/assets/plugins/animation/css/animate.min.css">
<!-- vendor css -->
<link rel="stylesheet" href="<%=basePath%>v3/assets/css/style.css">

</head>

<body>
	<div class="">
		<div class="">
			<div class="pcoded-content">
				<div class="pcoded-inner-content">
					<div class="main-body">
						<div class="page-wrapper">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-12">
										<h2 class="mb-3">Notas de versi�n:</h2>
										<hr>
									</div>
									<div class="alert alert-primary" role="alert">
										<p>
											<i class="far fa-lightbulb"></i> Hacer clic en la fecha para
											ver las notas de una versi�n
										</p>
									</div>
								</div>
								
								
								<!-- [ collapse 8] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleEight">
										<div class="card">
											<div class="card-header" id="headingEight">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseEight" aria-expanded="false"
														aria-controls="collapseEight">27 de Agosto, 2021</a>
												</h5>
											</div>
											<div id="collapseEight" class="card-body collapse"
												aria-labelledby="headingEight"
												data-parent="#accordionExampleEight">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agregan funcionalidades</h5> <br>
														
														
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> En el M�dulo de Picking se agrega la observaci�n de la l�nea y la observaci�n general; la misma se puede visualizar en Listado de pendientes, Picking y Verificaci�n.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Implementaci�n para poder filtrar por familias de productos en ubicaciones. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agrega un nuevo bot�n para reimprimir las etiquetas al cierre del picking. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se crea una nueva Interfaz para el ingreso de picking desde un archivo Excel con el siguiente formato:
articulo | destino | cantidad | justificacion | Pedido.  </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se podr� visualizar desde la Lectura del Ojo, la descripci�n del art�culo. </li>
														
														</ul> <br>
													</li>
												</ul>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Ajustes en la l�gica de la reposici�n. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Correcci�n al editar la forma de env�o de un pedido. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Edici�n de la etiqueta de env�o. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Edici�n de la etiqueta de Pickup. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se actualiza la l�gica para la Consolidaci�n de pedidos.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agrega casu�stica de combos en �tems dentro de pedidos.   </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agreg� el n�mero de remito en Picking.   </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agregan nuevos controles en Operaciones de Ojo.   </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se ajusta la l�gica al cancelar una reserva.   </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se ajusta la l�gica dentro del m�dulo de Picking.   </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se ajust� el rendimiento del m�dulo Pickup.   </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se ajust� visualmente para la medida del Colector los campos de Conteo de Ojos y en Operaciones de Ojo.   </li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								
								<!-- [ collapse 7] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleSeven">
										<div class="card">
											<div class="card-header" id="headingSeven">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseSeven" aria-expanded="false"
														aria-controls="collapseSeven">02 de Agosto, 2021</a>
												</h5>
											</div>
											<div id="collapseSeven" class="card-body collapse"
												aria-labelledby="headingSeven"
												data-parent="#accordionExampleSeven">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agregan funcionalidades</h5> <br>
														
														
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Implementaci�n de prioridades en sincronizaci�n de pedidos.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Nueva funcionalidad al verificar un pedido de Ecommerce con Visual Store. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Nuevo reporte de art�culos en ubicaciones. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agrega env�o de mails para faltante de picking.  </li>
														</ul> <br>
													</li>
												</ul>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Ajustes varios en el cambio de estados de un pedido. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Amplitud visual del campo en Operaciones de ojo. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Control adicional al crear una tarea. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de bug al editar una estanter�a. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de bug al pedir art�culos en la reposici�n. </li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								
								<!-- [ collapse 6] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleSix">
										<div class="card">
											<div class="card-header" id="headingSix">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseSix" aria-expanded="false"
														aria-controls="collapseSix">26 de Julio, 2021</a>
												</h5>
											</div>
											<div id="collapseSix" class="card-body collapse"
												aria-labelledby="headingSix"
												data-parent="#accordionExampleSix">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agregan funcionalidades</h5> <br>
														
														
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Nueva funcionalidad en ABM Ojo. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se implement� automatizaci�n que procesa los eventos de cambio de estado. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Nuevo KPI que indica Unidades en Almac�n. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Nuevo KPI que indica el Promedio de unidades pickeadas por hora en las ultimas 24 horas. </li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Nuevo KPI que indica el Promedio unidades verificadas por hora en las ultimas 24 horas. </li>
														</ul> <br>
													</li>
												</ul>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Mejoras en la visualizaci�n y configuraci�n del ABM Ojo.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Mejoras en la visualizaci�n del Pickup.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Al confirmar o clasificar un pedido se genera autom�ticamente el PDF de la etiqueta.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se corrigi� en la secci�n de reposici�n de picking los valores de m�dulo y
																de estante que visualmente estaban intercambiadas.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n del bug de picking desaparecidos.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de refresh de stocks.</li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								
								<!-- [ collapse 5] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleFive">
										<div class="card">
											<div class="card-header" id="headingFive">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseFive" aria-expanded="false"
														aria-controls="collapseFive">Mayo, 2021</a>
												</h5>
											</div>
											<div id="collapseFive" class="card-body collapse"
												aria-labelledby="headingFive"
												data-parent="#accordionExampleFive">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agregan funcionalidades</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Reporte para comparar stocks Encuentra - Visual (Forus) </li>
														</ul> <br>
													</li>
												</ul>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se corrigi� un error por el cual el 
																pedido no pasaba a items confirmados, al confirmar todos sus items mediante el monitor de pedidos</li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<!-- [ collapse 4] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleFour">
										<div class="card">
											<div class="card-header" id="headingFour">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseFour" aria-expanded="false"
														aria-controls="collapseFour">19 de marzo, 2021</a>
												</h5>
											</div>
											<div id="collapseFour" class="card-body collapse"
												aria-labelledby="headingFour"
												data-parent="#accordionExampleFour">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Mejoras internas en Log-in y Log-out</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Cambio interno en la muestra del PDF de las etiquetas de estanter�as.</li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<!-- [ collapse 3] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleThree">
										<div class="card">
											<div class="card-header" id="headingThree">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseThree" aria-expanded="false"
														aria-controls="collapseThree">3 de febrero, 2021</a>
												</h5>
											</div>
											<div id="collapseThree" class="card-body collapse"
												aria-labelledby="headingThree"
												data-parent="#accordionExampleThree">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agregan funcionalidades</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Picking por bultos</li>
														</ul>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Reposici�n mayorista </li>
														</ul>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Afectaci�n a ordenes mayoristas </li>
														</ul> <br>
													</li>
													<br>
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n para que las tareas no se pickeen m�s de las cantidades solicitadas.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n para evitar hacer expediciones dobles.</li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<!-- [ collapse 2] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExampleTwo">
										<div class="card">
											<div class="card-header" id="headingTwo">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseTwo" aria-expanded="false"
														aria-controls="collapseTwo">19 de enero, 2021</a>
												</h5>
											</div>
											<div id="collapseTwo" class="card-body collapse"
												aria-labelledby="headingTwo"
												data-parent="#accordionExampleTwo">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agregan funcionalidades</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Par�metro para
																remisi�n por bulto, se agrega par�metro que permite a
																los administradores configurar el sistema, permitiendo
																remitir mercader�a al cerrar bulto, o remitir varios
																bultos en un solo documento desde pickings verificados.</li>
														</ul> <br>
													</li>
													<br>
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de
																conflictos en reporte Picking.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de
																conflictos en reporte Productividad Picking.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de
																conflictos en reporte Expedicion Movimientos.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Soluci�n de
																conflictos en reporte Bultos por rango de fecha y por
																destino.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se suben los
																intentos de confirmaci�n (contra Visual), en los remitos
																de recepci�n de mercader�a.</li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<!-- [ collapse 1] start -->
								<div class="col-sm-12">
									<hr>
									<div class="accordion" id="accordionExample">
										<div class="card">
											<div class="card-header" id="headingOne">
												<h5 class="mb-0">
													<a href="#!" class data-toggle="collapse"
														data-target="#collapseOne" aria-expanded="false"
														aria-controls="collapseOne">28 de diciembre, 2020</a>
												</h5>
											</div>
											<div id="collapseOne" class="card-body collapse"
												aria-labelledby="headingOne" data-parent="#accordionExample">

												<h3>Nuevas funcionalidades</h3>
												<br>
												<ul class="list-group">
													<li class="list-group-item">
														<h5>Se agrega una secci�n con nuevos reportes</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Reposicionamiento
																(Consolidaci�n)</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Ajustes por
																diferencias en rango de fechas.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Picking.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Cumplimiento
																ordenes.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Productividad
																Picking.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Frecuencia
																ubicaciones/producto.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Expedicion
																Movimientos.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Bultos por rango de
																fecha y por destino.</li>
														</ul> <br> <img src="img/281220.png">
													</li>
													<br>
													<li class="list-group-item">
														<h5>Bug fixes & mejoras</h5> <br>
														<ul class="list-group list-group-flush">
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agrega un
																control para evitar que al momento de cierre de caja se
																generen remitos duplicados.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agrega un fix
																para la inconsistencia entre unidades remitidas en ERP y
																Encuentra. Tambi�n se actualiza el guardado de la fecha
																en que son remitidas.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agrega un
																control para evitar picking duplicados al dar de alta
																una tarea de Picking.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se mejora la
																performance de la funcionalidad de cambio de estado de
																pedidos y lectura del estado en el que est�n los mismos.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se soluciona
																problema de la generaci�n de doble tracking para el
																courier Mirtrans.</li>
															<li class="list-group-item"><i
																class="far fa-hand-point-right"></i> Se agregan a la
																cola de movimientos los movimientos que fallan cuando el
																ERP no responde correctamente.</li>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath%>v3/assets/js/vendor-all.min.js"></script>
	<script
		src="<%=basePath%>v3/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>v3/assets/js/pcoded.min.js"></script>


</body>

</html>