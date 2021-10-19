<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:choose>
  <c:when test="${css=='IE'}">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
  </c:otherwise>
</c:choose>
</head>
<body>

<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>

<link href="<%=basePath%>v3/assets/signature/w3.css" type="text/css" rel="stylesheet" />

    <style type="text/css">
        .m-signature-pad--body canvas {
            position: relative;
            left: 0;
            top: 0;
            width: 70%;
            height: 200px;
            border: 1px solid #CCCCCC;
        }    
    </style>

<c:if test="${menError!=null}">
<script type="text/javascript">

	alert("${menError}");
</script>
	
</c:if>

<script type="text/javascript"> 
	
	function nextel(event) 
	{ 
		if(event==13)
		{  
			document.getElementById("test").focus(); 
			
		} 
	}
	
	
	 
</script>
<script type="text/javascript">
	var codigos = [];
	var count = 0;
	var simbolo = ' + '
	
	
	
	

	
	function subm(event) 
	{
		submitForm();
		document.forms["contactform"].submit();
			
	}
	
	
</script>




</head>
<body onload=" document.getElementById('base').focus();">
	<c:if test="${uLogeado!=null}">
	<form id="contactform" class="rounded" method="post"  action="<%=basePath%>/EntregarPedidoPickup.do?idPedido=${pedidoE.idPedido}"  enctype="multipart/form-data" style="float: left;">
		<div class="row">
			<div class="col-100">
			<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>v3/handHeld/MobPedidoEcommerceEnUbi.jsp'"   value="Ir a menu"/>
				
			</div>
		</div>
		<div class="row">
			<div class="col-100">
				<table>
					<tr>
						<td colspan="2">Entregando pedido <p style="color: red; font-size: 14pt"><strong> ${pedidoE.idPedido} - ${pedidoE.fecha} </strong></p></td>
					</tr>
					<tr>
						<td>Documento Retira</td>
						<td><input type="number" class="inputCH" name="idOjo" id="base" onkeypress="nextel(event.keyCode)"  required/></td>	
					</tr>
				</table>
				
			</div>
		</div>
		<div class="row">
			<div id="signature-pad" class="m-signature-pad">
	            <div class="m-signature-pad--body">
	                <canvas></canvas>
	                <input type="hidden" name="signature" id="signature" value="">
	            </div>
	        </div>        
	
	        
	        <button type="button" class="col-100 button" onclick="signaturePad.clear();">borrar firma</button>
        <div/>
		<div class="row">
			<div class="col-100">
				<table>
					<tr>
						<td>
							<input type="button" class="col-100 button" onclick="subm(event,1)" value="Confirmar entrega" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	</c:if>
</body>
<script src="<%=basePath%>v3/assets/signature/signature_pad.js"></script>
<script type="text/javascript">
var wrapper = document.getElementById("signature-pad"),
   canvas = wrapper.querySelector("canvas"),
   signaturePad;

/**
*  Behandlung der Größenänderung der Unterschriftenfelds
*/
function resizeCanvas() {
    var oldContent = signaturePad.toData();
    var ratio =  Math.max(window.devicePixelRatio || 1, 1);
    canvas.width = canvas.offsetWidth * ratio;
    canvas.height = canvas.offsetHeight * ratio;
    canvas.getContext("2d").scale(ratio, ratio);
    signaturePad.clear();
    signaturePad.fromData(oldContent);
}


/**
*  Speichern des Inhaltes als Bild
*/
function download(filename) {
  var blob = dataURLToBlob(signaturePad.toDataURL());
  var url = window.URL.createObjectURL(blob);

  var a = document.createElement("a");
  a.style = "display: none";
  a.href = url;
  a.download = filename;

  document.body.appendChild(a);
  a.click();

  window.URL.revokeObjectURL(url);
}

/**
* DataURL in Binär umwandeln
*/
function dataURLToBlob(dataURL) {
  // Code von https://github.com/ebidel/filer.js
  var parts = dataURL.split(';base64,');
  var contentType = parts[0].split(":")[1];
  var raw = window.atob(parts[1]);
  var rawLength = raw.length;
  var uInt8Array = new Uint8Array(rawLength);

  for (var i = 0; i < rawLength; ++i) {
    uInt8Array[i] = raw.charCodeAt(i);
  }

  return new Blob([uInt8Array], { type: contentType });
}

/**
* Behanlung beim Absenden, damit der Inhalt des Canvas
* übermittelt werden kann, wird dieser als DataURL dem
* versteckten Feld zugewiesen    
*/
function submitForm() {
    //Unterschrift in verstecktes Feld übernehmen
    document.getElementById('signature').value = signaturePad.toDataURL();
}


var signaturePad = new SignaturePad(canvas);
signaturePad.minWidth = 1; //minimale Breite des Stiftes
signaturePad.maxWidth = 5; //maximale Breite des Stiftes
signaturePad.penColor = "#000000"; //Stiftfarbe
signaturePad.backgroundColor = "#FFFFFF"; //Hintergrundfarbe

window.onresize = resizeCanvas;
resizeCanvas();

</script>  


  
</html>	
		
		
</c:if>
</body>
</html>