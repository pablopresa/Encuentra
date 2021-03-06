
package integraciones.erp.sapBO.stadium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
* WMSSAPB1 service = new WMSSAPB1();
* WMSSAPB1Soap portType = service.getWMSSAPB1Soap();
* portType.loginAsync(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "WMSSAPB1", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://10.108.0.22:81/WMSSAPB1.asmx?WSDL")
public class WMSSAPB1 extends Service {

	private static final URL WMSSAPB1_WSDL_LOCATION;
	private static final Logger logger = Logger.getLogger(integraciones.erp.sapBO.stadium.WMSSAPB1.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = integraciones.erp.sapBO.stadium.WMSSAPB1.class.getResource(".");
			url = new URL(baseUrl, "http://10.108.0.22:81/WMSSAPB1.asmx?WSDL");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://10.108.0.22:81/WMSSAPB1.asmx?WSDL', retrying as a local file");
			logger.warning(e.getMessage());
		}
		WMSSAPB1_WSDL_LOCATION = url;
	}

	public WMSSAPB1(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public WMSSAPB1() {
		super(WMSSAPB1_WSDL_LOCATION, new QName("http://tempuri.org/", "WMSSAPB1"));
	}

	/**
	 * 
	 * @return returns WMSSAPB1Soap
	 */
	@WebEndpoint(name = "WMSSAPB1Soap")
	public WMSSAPB1Soap getWMSSAPB1Soap() {
		return super.getPort(new QName("http://tempuri.org/", "WMSSAPB1Soap"), WMSSAPB1Soap.class);
	}

	/**
	 * 
	 * @return returns WMSSAPB1Soap
	 */
	@WebEndpoint(name = "WMSSAPB1Soap12")
	public WMSSAPB1Soap getWMSSAPB1Soap12() {
		return super.getPort(new QName("http://tempuri.org/", "WMSSAPB1Soap12"), WMSSAPB1Soap.class);
	}

}
