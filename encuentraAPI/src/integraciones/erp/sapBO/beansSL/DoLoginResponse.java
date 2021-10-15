

package integraciones.erp.sapBO.beansSL;


public class DoLoginResponse {


private String odata_Metadata;

private String SessionId;

private String Version;

private Integer SessionTimeout;

public String getSessionId() {
	return SessionId;
}

public void setSessionId(String sessionId) {
	SessionId = sessionId;
}

public String getVersion() {
	return Version;
}

public void setVersion(String version) {
	Version = version;
}

public Integer getSessionTimeout() {
	return SessionTimeout;
}

public void setSessionTimeout(Integer sessionTimeout) {
	SessionTimeout = sessionTimeout;
}

public String getOdata_Metadata() {
	return odata_Metadata;
}

public void setOdata_Metadata(String odata_Metadata) {
	this.odata_Metadata = odata_Metadata;
}



public DoLoginResponse(String odata_Metadata, String sessionId, String version, Integer sessionTimeout) {
	this.odata_Metadata = odata_Metadata;
	this.SessionId = sessionId;
	this.Version = version;
	this.SessionTimeout = sessionTimeout;
}


public DoLoginResponse() {
}




}
