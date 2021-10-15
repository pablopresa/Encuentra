
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
@WebServiceClient(name = "AccionesEncuentra", targetNamespace = "PpGg", wsdlLocation = "http://201.217.140.35/WSPPGGFE/aAccionesEncuentra.aspx?WSDL")
public class AccionesEncuentra
    extends Service
{

    private final static URL ACCIONESENCUENTRA_WSDL_LOCATION;
    private final static WebServiceException ACCIONESENCUENTRA_EXCEPTION;
    private final static QName ACCIONESENCUENTRA_QNAME = new QName("PpGg", "AccionesEncuentra");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://201.217.140.35/WSPPGGFE/aAccionesEncuentra.aspx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ACCIONESENCUENTRA_WSDL_LOCATION = url;
        ACCIONESENCUENTRA_EXCEPTION = e;
    }

    public AccionesEncuentra() {
        super(__getWsdlLocation(), ACCIONESENCUENTRA_QNAME);
    }

    public AccionesEncuentra(WebServiceFeature... features) {
        super(__getWsdlLocation(), ACCIONESENCUENTRA_QNAME, features);
    }

    public AccionesEncuentra(URL wsdlLocation) {
        super(wsdlLocation, ACCIONESENCUENTRA_QNAME);
    }

    public AccionesEncuentra(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ACCIONESENCUENTRA_QNAME, features);
    }

    public AccionesEncuentra(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AccionesEncuentra(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AccionesEncuentraSoapPort
     */
    @WebEndpoint(name = "AccionesEncuentraSoapPort")
    public AccionesEncuentraSoapPort getAccionesEncuentraSoapPort() {
        return super.getPort(new QName("PpGg", "AccionesEncuentraSoapPort"), AccionesEncuentraSoapPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AccionesEncuentraSoapPort
     */
    @WebEndpoint(name = "AccionesEncuentraSoapPort")
    public AccionesEncuentraSoapPort getAccionesEncuentraSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("PpGg", "AccionesEncuentraSoapPort"), AccionesEncuentraSoapPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ACCIONESENCUENTRA_EXCEPTION!= null) {
            throw ACCIONESENCUENTRA_EXCEPTION;
        }
        return ACCIONESENCUENTRA_WSDL_LOCATION;
    }

}
