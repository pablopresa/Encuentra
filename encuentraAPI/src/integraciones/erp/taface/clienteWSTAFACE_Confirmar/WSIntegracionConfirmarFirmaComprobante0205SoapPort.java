
package integraciones.erp.taface.clienteWSTAFACE_Confirmar;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "WSIntegracionConfirmarFirmaComprobante_0205SoapPort", targetNamespace = "TAFACE")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WSIntegracionConfirmarFirmaComprobante0205SoapPort {

	/**
	 * 
	 * @param parameters
	 * @return returns
	 *         clienteWSTAFACE_Confirmar.WSIntegracionConfirmarFirmaComprobante0205ExecuteResponse
	 */
	@WebMethod(operationName = "Execute", action = "TAFACEaction/AWSINTEGRACIONCONFIRMARFIRMACOMPROBANTE_0205.Execute")
	@WebResult(name = "WSIntegracionConfirmarFirmaComprobante_0205.ExecuteResponse", targetNamespace = "TAFACE", partName = "parameters")
	public WSIntegracionConfirmarFirmaComprobante0205ExecuteResponse execute(
			@WebParam(name = "WSIntegracionConfirmarFirmaComprobante_0205.Execute", targetNamespace = "TAFACE", partName = "parameters") WSIntegracionConfirmarFirmaComprobante0205Execute parameters);

}
