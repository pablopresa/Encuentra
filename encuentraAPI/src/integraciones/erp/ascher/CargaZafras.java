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
@WebServiceClient(name = "CargaZafras", targetNamespace = "PpGg", wsdlLocation = "http://encuentra.200.com.uy/WSDLS/acargazafras.aspx.xml")
public class CargaZafras
    extends Service
{

    private final static URL CARGAZAFRAS_WSDL_LOCATION;
    private final static WebServiceException CARGAZAFRAS_EXCEPTION;
    private final static QName CARGAZAFRAS_QNAME = new QName("PpGg", "CargaZafras");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://encuentra.200.com.uy/WSDLS/acargazafras.aspx.xml");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CARGAZAFRAS_WSDL_LOCATION = url;
        CARGAZAFRAS_EXCEPTION = e;
    }

    public CargaZafras() {
        super(__getWsdlLocation(), CARGAZAFRAS_QNAME);
    }

    public CargaZafras(WebServiceFeature... features) {
        super(__getWsdlLocation(), CARGAZAFRAS_QNAME, features);
    }

    public CargaZafras(URL wsdlLocation) {
        super(wsdlLocation, CARGAZAFRAS_QNAME);
    }

    public CargaZafras(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CARGAZAFRAS_QNAME, features);
    }

    public CargaZafras(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CargaZafras(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CargaZafrasSoapPort
     */
    @WebEndpoint(name = "CargaZafrasSoapPort")
    public CargaZafrasSoapPort getCargaZafrasSoapPort() {
        return super.getPort(new QName("PpGg", "CargaZafrasSoapPort"), CargaZafrasSoapPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CargaZafrasSoapPort
     */
    @WebEndpoint(name = "CargaZafrasSoapPort")
    public CargaZafrasSoapPort getCargaZafrasSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("PpGg", "CargaZafrasSoapPort"), CargaZafrasSoapPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CARGAZAFRAS_EXCEPTION!= null) {
            throw CARGAZAFRAS_EXCEPTION;
        }
        return CARGAZAFRAS_WSDL_LOCATION;
    }

}
