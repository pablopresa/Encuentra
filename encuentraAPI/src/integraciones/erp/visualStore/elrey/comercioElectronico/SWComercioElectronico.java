
package integraciones.erp.visualStore.elrey.comercioElectronico;

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
* SWComercioElectronico service = new SWComercioElectronico();
* SWComercioElectronicoSoap portType = service.getSWComercioElectronicoSoap();
* portType.reservaNroVentaWeb(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "SWComercioElectronico", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", wsdlLocation = "http://encuentra.200.com.uy/WSDLS/swcomercioelectronico.xml")
public class SWComercioElectronico extends Service {

	private final static URL SWCOMERCIOELECTRONICO_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(integraciones.erp.visualStore.elrey.comercioElectronico.SWComercioElectronico.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = integraciones.erp.visualStore.elrey.comercioElectronico.SWComercioElectronico.class.getResource(".");
			url = new URL(baseUrl, "http://encuentra.200.com.uy/WSDLS/swcomercioelectronico.xml");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://encuentra.200.com.uy/WSDLS/swcomercioelectronico.xml', retrying as a local file");
			logger.warning(e.getMessage());
		}
		SWCOMERCIOELECTRONICO_WSDL_LOCATION = url;
	}

	public SWComercioElectronico(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public SWComercioElectronico() {
		super(SWCOMERCIOELECTRONICO_WSDL_LOCATION,
				new QName("http://tempuri.org/VSServicioWeb/SWComercioElectronico", "SWComercioElectronico"));
	}

	/**
	 * 
	 * @return returns SWComercioElectronicoSoap
	 */
	@WebEndpoint(name = "SWComercioElectronicoSoap")
	public SWComercioElectronicoSoap getSWComercioElectronicoSoap() {
		return super.getPort(
				new QName("http://tempuri.org/VSServicioWeb/SWComercioElectronico", "SWComercioElectronicoSoap"),
				SWComercioElectronicoSoap.class);
	}

	/**
	 * 
	 * @return returns SWComercioElectronicoSoap
	 */
	@WebEndpoint(name = "SWComercioElectronicoSoap12")
	public SWComercioElectronicoSoap getSWComercioElectronicoSoap12() {
		return super.getPort(
				new QName("http://tempuri.org/VSServicioWeb/SWComercioElectronico", "SWComercioElectronicoSoap12"),
				SWComercioElectronicoSoap.class);
	}

}
