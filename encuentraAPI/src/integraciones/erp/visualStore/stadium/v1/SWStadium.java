
package integraciones.erp.visualStore.stadium.v1;

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
* SWStadium service = new SWStadium();
* SWStadiumSoap portType = service.getSWStadiumSoap();
* portType.grabarTransferencia(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "SWStadium", targetNamespace = "http://tempuri.org/VSServicioWeb/SWStadium", wsdlLocation = "http://10.108.0.16/ServicioWebVs/SWStadium.asmx?WSDL")
public class SWStadium extends Service {

	private final static URL SWSTADIUM_WSDL_LOCATION;
	private final static Logger logger = Logger.getLogger(SWStadium.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = SWStadium.class.getResource(".");
			url = new URL(baseUrl, "http://10.108.0.16/ServicioWebVs/SWStadium.asmx?WSDL");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://10.108.0.16/ServicioWebVs/SWStadium.asmx?WSDL', retrying as a local file");
			logger.warning(e.getMessage());
		}
		SWSTADIUM_WSDL_LOCATION = url;
	}

	public SWStadium(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public SWStadium() {
		super(SWSTADIUM_WSDL_LOCATION, new QName("http://tempuri.org/VSServicioWeb/SWStadium", "SWStadium"));
	}

	/**
	 * 
	 * @return returns SWStadiumSoap
	 */
	@WebEndpoint(name = "SWStadiumSoap")
	public SWStadiumSoap getSWStadiumSoap() {
		return super.getPort(new QName("http://tempuri.org/VSServicioWeb/SWStadium", "SWStadiumSoap"),
				SWStadiumSoap.class);
	}

	/**
	 * 
	 * @return returns SWStadiumSoap
	 */
	@WebEndpoint(name = "SWStadiumSoap12")
	public SWStadiumSoap getSWStadiumSoap12() {
		return super.getPort(new QName("http://tempuri.org/VSServicioWeb/SWStadium", "SWStadiumSoap12"),
				SWStadiumSoap.class);
	}

}
