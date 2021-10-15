
package integraciones.erp.ascher;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CargaArticulosPPyK", targetNamespace = "PpGg", wsdlLocation = "http://201.217.140.35/WSPPGGFE/aCargaArticulosPPyK.aspx?wsdl")
public class CargaArticulosPPyK
    extends Service
{

    private final static URL CARGAARTICULOSPPYK_WSDL_LOCATION;
    private final static WebServiceException CARGAARTICULOSPPYK_EXCEPTION;
    private final static QName CARGAARTICULOSPPYK_QNAME = new QName("PpGg", "CargaArticulosPPyK");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://201.217.140.35/WSPPGGFE/aCargaArticulosPPyK.aspx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CARGAARTICULOSPPYK_WSDL_LOCATION = url;
        CARGAARTICULOSPPYK_EXCEPTION = e;
    }

    public CargaArticulosPPyK() {
        super(__getWsdlLocation(), CARGAARTICULOSPPYK_QNAME);
    }

    public CargaArticulosPPyK(WebServiceFeature... features) {
        super(__getWsdlLocation(), CARGAARTICULOSPPYK_QNAME, features);
    }

    public CargaArticulosPPyK(URL wsdlLocation) {
        super(wsdlLocation, CARGAARTICULOSPPYK_QNAME);
    }

    public CargaArticulosPPyK(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CARGAARTICULOSPPYK_QNAME, features);
    }

    public CargaArticulosPPyK(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CargaArticulosPPyK(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CargaArticulosPPyKSoapPort
     */
    @WebEndpoint(name = "CargaArticulosPPyKSoapPort")
    public CargaArticulosPPyKSoapPort getCargaArticulosPPyKSoapPort() {
        return super.getPort(new QName("PpGg", "CargaArticulosPPyKSoapPort"), CargaArticulosPPyKSoapPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CargaArticulosPPyKSoapPort
     */
    @WebEndpoint(name = "CargaArticulosPPyKSoapPort")
    public CargaArticulosPPyKSoapPort getCargaArticulosPPyKSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("PpGg", "CargaArticulosPPyKSoapPort"), CargaArticulosPPyKSoapPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CARGAARTICULOSPPYK_EXCEPTION!= null) {
            throw CARGAARTICULOSPPYK_EXCEPTION;
        }
        return CARGAARTICULOSPPYK_WSDL_LOCATION;
    }

}
