
package integraciones.erp.taface.clienteWSTAFACE_firmar;

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
@WebService(name = "WSIntegracionFirmarComprobante_0205SoapPort", targetNamespace = "TAFACE")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WSIntegracionFirmarComprobante0205SoapPort {

	/**
	 * 
	 * @param parameters
	 * @return returns
	 *         clienteWSTAFACE_firmar.WSIntegracionFirmarComprobante0205ExecuteResponse
	 */
	@WebMethod(operationName = "Execute", action = "TAFACEaction/AWSINTEGRACIONFIRMARCOMPROBANTE_0205.Execute")
	@WebResult(name = "WSIntegracionFirmarComprobante_0205.ExecuteResponse", targetNamespace = "TAFACE", partName = "parameters")
	public WSIntegracionFirmarComprobante0205ExecuteResponse execute(
			@WebParam(name = "WSIntegracionFirmarComprobante_0205.Execute", targetNamespace = "TAFACE", partName = "parameters") WSIntegracionFirmarComprobante0205Execute parameters);

}
