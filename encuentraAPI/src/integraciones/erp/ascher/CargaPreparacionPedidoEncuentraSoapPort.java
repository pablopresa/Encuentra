
package integraciones.erp.ascher;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CargaPreparacionPedidoEncuentraSoapPort", targetNamespace = "PpGg")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CargaPreparacionPedidoEncuentraSoapPort {


    /**
     * 
     * @param parameters
     * @return
     *     returns ppgg.CargaPreparacionPedidoEncuentraExecuteResponse
     */
    @WebMethod(operationName = "Execute", action = "PpGgaction/ACARGAPREPARACIONPEDIDOENCUENTRA.Execute")
    @WebResult(name = "CargaPreparacionPedidoEncuentra.ExecuteResponse", targetNamespace = "PpGg", partName = "parameters")
    public CargaPreparacionPedidoEncuentraExecuteResponse execute(
        @WebParam(name = "CargaPreparacionPedidoEncuentra.Execute", targetNamespace = "PpGg", partName = "parameters")
        CargaPreparacionPedidoEncuentraExecute parameters);

}
